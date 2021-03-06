package binnie.genetics.machine.incubator;

import binnie.core.machines.Machine;
import binnie.core.machines.power.ComponentProcessIndefinate;
import binnie.core.machines.power.ErrorState;
import binnie.core.machines.power.IProcess;
import binnie.core.machines.transfer.TransferRequest;
import binnie.genetics.Genetics;
import binnie.genetics.api.IIncubatorRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.Random;

public class IncubatorLogic extends ComponentProcessIndefinate implements IProcess {
	IIncubatorRecipe recipe;
	private Random rand;
	private boolean roomForOutput;

	public IncubatorLogic(final Machine machine) {
		super(machine, 2.0f);
		this.recipe = null;
		this.rand = new Random();
		this.roomForOutput = true;
	}

	@Override
	public ErrorState canWork() {
		if (this.recipe == null) {
			return new ErrorState(Genetics.proxy.localise("machine.errors.no.recipe.desc"), Genetics.proxy.localise("machine.errors.no.recipe.info"));
		}
		return super.canWork();
	}

	@Override
	public ErrorState canProgress() {
		if (this.recipe != null) {
			if (!this.recipe.isInputLiquidSufficient(this.getUtil().getFluid(Incubator.TANK_INPUT))) {
				return new ErrorState.InsufficientLiquid(Genetics.proxy.localise("machine.labMachine.incubator.errors.no.liquid.desc"), Incubator.TANK_INPUT);
			}
			if (!this.roomForOutput) {
				return new ErrorState.TankSpace(Genetics.proxy.localise("machine.labMachine.incubator.errors.no.room.desc"), Incubator.TANK_OUTPUT);
			}
		}
		return super.canProgress();
	}

	@Override
	protected void onTickTask() {
		if (this.rand.nextInt(20) == 0 && this.recipe != null && this.rand.nextFloat() < this.recipe.getChance()) {
			this.recipe.doTask(this.getUtil());
		}
	}

	@Override
	public boolean inProgress() {
		return this.recipe != null;
	}

	private IIncubatorRecipe getRecipe(final ItemStack stack, final FluidStack liquid) {
		for (final IIncubatorRecipe recipe : Incubator.getRecipes()) {
			final boolean rightLiquid = recipe.isInputLiquid(liquid);
			final boolean rightItem = isStackValid(stack, recipe);
			if (rightLiquid && rightItem) {
				return recipe;
			}
		}
		return null;
	}

	private static boolean isStackValid(ItemStack stack, IIncubatorRecipe recipe) {
		return ItemStack.areItemsEqual(recipe.getInputStack(), stack);
	}

	@Override
	public void onInventoryUpdate() {
		super.onInventoryUpdate();
		if (!this.getUtil().isServer()) {
			return;
		}
		final FluidStack liquid = this.getUtil().getFluid(Incubator.TANK_INPUT);
		final ItemStack incubator = this.getUtil().getStack(Incubator.SLOT_INCUBATOR);
		if (this.recipe != null && (incubator == null || liquid == null || !this.recipe.isInputLiquid(liquid) || !isStackValid(incubator, recipe))) {
			this.recipe = null;
			final ItemStack leftover = new TransferRequest(incubator, this.getInventory()).setTargetSlots(Incubator.SLOT_OUTPUT).ignoreValidation().transfer(true);
			this.getUtil().setStack(Incubator.SLOT_INCUBATOR, leftover);
		}
		if (this.recipe == null) {
			if (liquid == null) {
				return;
			}
			if (incubator != null) {
				final IIncubatorRecipe recipe = this.getRecipe(incubator, liquid);
				if (recipe != null) {
					this.recipe = recipe;
					return;
				}
			}
			IIncubatorRecipe potential = null;
			int potentialSlot = 0;
			for (final int slot : Incubator.SLOT_QUEUE) {
				final ItemStack stack = this.getUtil().getStack(slot);
				if (stack != null) {
					if (potential == null) {
						for (final IIncubatorRecipe recipe2 : Incubator.getRecipes()) {
							final boolean rightLiquid = recipe2.isInputLiquid(liquid);
							final boolean rightItem = isStackValid(stack, recipe2);
							if (rightLiquid && rightItem) {
								potential = recipe2;
								potentialSlot = slot;
								break;
							}
						}
					}
				}
			}
			if (potential != null) {
				final TransferRequest removal = new TransferRequest(incubator, this.getInventory()).setTargetSlots(Incubator.SLOT_OUTPUT).ignoreValidation();
				if (removal.transfer(false) == null) {
					this.recipe = potential;
				}
				removal.transfer(true);
				final ItemStack stack2 = this.getUtil().getStack(potentialSlot);
				this.getUtil().setStack(potentialSlot, null);
				this.getUtil().setStack(Incubator.SLOT_INCUBATOR, stack2);
			}
		}
		if (this.recipe != null) {
			this.roomForOutput = this.recipe.roomForOutput(this.getUtil());
		}
	}
}

package binnie.genetics.machine.analyser;

import binnie.core.machines.inventory.SlotValidator;
import binnie.genetics.Genetics;
import net.minecraft.item.ItemStack;

public class SlotValidatorUnanalysed extends SlotValidator {
	public SlotValidatorUnanalysed() {
		super(null);
	}

	@Override
	public boolean isValid(final ItemStack itemStack) {
		return Analyser.isAnalysable(itemStack) && !Analyser.isAnalysed(itemStack);
	}

	@Override
	public String getTooltip() {
		return Genetics.proxy.localise("machine.labMachine.analyser.tooltips.slots.unanalysed");
	}
}

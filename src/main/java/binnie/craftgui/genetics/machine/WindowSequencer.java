package binnie.craftgui.genetics.machine;

import binnie.core.AbstractMod;
import binnie.craftgui.controls.ControlText;
import binnie.craftgui.core.geometry.CraftGUIUtil;
import binnie.craftgui.core.geometry.IArea;
import binnie.craftgui.core.geometry.Position;
import binnie.craftgui.core.geometry.TextJustification;
import binnie.craftgui.minecraft.GUIIcon;
import binnie.craftgui.minecraft.Window;
import binnie.craftgui.minecraft.control.ControlEnergyBar;
import binnie.craftgui.minecraft.control.ControlErrorState;
import binnie.craftgui.minecraft.control.ControlIconDisplay;
import binnie.craftgui.minecraft.control.ControlPlayerInventory;
import binnie.craftgui.minecraft.control.ControlSlot;
import binnie.craftgui.minecraft.control.ControlSlotArray;
import binnie.craftgui.minecraft.control.ControlSlotCharge;
import binnie.craftgui.resource.Texture;
import binnie.craftgui.resource.minecraft.StandardTexture;
import binnie.extrabees.core.ExtraBeeTexture;
import binnie.genetics.Genetics;
import binnie.genetics.machine.sequencer.Sequencer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;

public class WindowSequencer extends WindowMachine {
	static Texture ProgressBase = new StandardTexture(64, 114, 98, 9, ExtraBeeTexture.GUIProgress.getTexture());
	static Texture Progress = new StandardTexture(64, 123, 98, 9, ExtraBeeTexture.GUIProgress.getTexture());
	ControlText slotText;

	public static Window create(final EntityPlayer player, final IInventory inventory, final Side side) {
		return new WindowSequencer(player, inventory, side);
	}

	public WindowSequencer(final EntityPlayer player, final IInventory inventory, final Side side) {
		super(226, 224, player, inventory, side);
	}

	@Override
	public void recieveGuiNBT(final Side side, final EntityPlayer player, final String name, final NBTTagCompound action) {
		if (side == Side.CLIENT && name.equals("username")) {
			this.slotText.setValue(TextFormatting.DARK_GRAY + String.format(Genetics.proxy.localise("machine.machine.sequencer.texts.sequenced.by"), action.getString("username")));
		}
		super.recieveGuiNBT(side, player, name, action);
	}

	@Override
	public void initialiseClient() {
		super.initialiseClient();
		int x = 16;
		int y = 32;
		CraftGUIUtil.horizontalGrid(x, y, TextJustification.MiddleCenter, 2.0f, new ControlSlotArray(this, 0, 0, 2, 2).create(Sequencer.SLOT_RESERVE), new ControlIconDisplay(this, 0.0f, 0.0f, GUIIcon.ArrowRight.getIcon().getResourceLocation()), new ControlSequencerProgress(this, 0, 0), new ControlIconDisplay(this, 0.0f, 0.0f, GUIIcon.ArrowRight.getIcon().getResourceLocation()), new ControlSlot(this, 0.0f, 0.0f).assign(6));
		final ControlSlot slotTarget = new ControlSlot(this, x + 96, y + 16);
		slotTarget.assign(5);
		x = 34;
		y = 92;
		this.slotText = new ControlText(this, new IArea(0.0f, y, this.w(), 12.0f), TextFormatting.DARK_GRAY + Genetics.proxy.localise("machine.machine.sequencer.texts.userless"), TextJustification.MiddleCenter);
		y += 20;
		final ControlSlot slotDye = new ControlSlot(this, x, y);
		slotDye.assign(0);
		x += 20;
		new ControlSlotCharge(this, x, y, 0).setColour(16750848);
		x += 32;
		new ControlEnergyBar(this, x, y, 60, 16, Position.Left);
		x += 92;
		final ControlErrorState errorState = new ControlErrorState(this, x, y + 1);
		new ControlPlayerInventory(this);
	}

	@Override
	public String getTitle() {
		return Genetics.proxy.localise("machine.machine.sequencer");
	}

	@Override
	protected AbstractMod getMod() {
		return Genetics.instance;
	}

	@Override
	protected String getBackgroundTextureName() {
		return "Sequencer";
	}

}

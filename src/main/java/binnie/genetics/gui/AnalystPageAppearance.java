package binnie.genetics.gui;

import binnie.botany.api.IFlower;
import binnie.craftgui.botany.ControlColourDisplay;
import binnie.craftgui.controls.ControlTextCentered;
import binnie.craftgui.core.IWidget;
import binnie.craftgui.core.geometry.IArea;
import binnie.craftgui.minecraft.control.ControlIconDisplay;
import forestry.api.genetics.IAlleleSpecies;

public class AnalystPageAppearance extends ControlAnalystPage {
	public AnalystPageAppearance(final IWidget parent, final IArea area, final IFlower ind) {
		super(parent, area);
		this.setColour(3355443);
		int y = 4;
		final IAlleleSpecies species = ind.getGenome().getPrimary();
		new ControlTextCentered(this, y, "§nAppearance").setColour(this.getColour());
		y += 12;
		final ControlColourDisplay a = new ControlColourDisplay(this, this.w() / 2.0f - 28.0f, y);
		a.setValue(ind.getGenome().getPrimaryColor());
		a.addTooltip("Primary Petal Colour");
		final ControlColourDisplay b = new ControlColourDisplay(this, this.w() / 2.0f - 8.0f, y);
		b.setValue(ind.getGenome().getSecondaryColor());
		b.addTooltip("Secondary Petal Colour");
		final ControlColourDisplay c = new ControlColourDisplay(this, this.w() / 2.0f + 12.0f, y);
		c.setValue(ind.getGenome().getStemColor());
		c.addTooltip("Stem Colour");
		y += 26;
		final int sections = ind.getGenome().getType().getSections();
		final int w = (sections > 1) ? 50 : 100;
		new ControlIconDisplay(this, (this.w() - w) / 2.0f, y - ((sections == 1) ? 0 : 0), null) {
			@Override
			public void onRenderForeground() {
//				GL11.glPushMatrix();
//				final float scale = w / 16.0f;
//				final float dy = (sections > 1) ? 16.0f : 0.0f;
//				GL11.glScalef(scale, scale, 1.0f);
//				CraftGUI.Render.colour(ind.getGenome().getStemColor().getColor(false));
//				if (sections > 1) {
//					CraftGUI.Render.iconBlock(new IPoint(0.0f, 0.0f), ind.getGenome().getType().getStem(EnumFlowerStage.FLOWER, true, 1));
//				}
//				CraftGUI.Render.iconBlock(new IPoint(0.0f, dy), ind.getGenome().getType().getStem(EnumFlowerStage.FLOWER, true, 0));
//				CraftGUI.Render.colour(ind.getGenome().getPrimaryColor().getColor(false));
//				if (sections > 1) {
//					CraftGUI.Render.iconBlock(new IPoint(0.0f, 0.0f), ind.getGenome().getType().getPetalIcon(EnumFlowerStage.FLOWER, true, 1));
//				}
//				CraftGUI.Render.iconBlock(new IPoint(0.0f, dy), ind.getGenome().getType().getPetalIcon(EnumFlowerStage.FLOWER, true, 0));
//				CraftGUI.Render.colour(ind.getGenome().getSecondaryColor().getColor(false));
//				if (sections > 1) {
//					CraftGUI.Render.iconBlock(new IPoint(0.0f, 0.0f), ind.getGenome().getType().getVariantIcon(EnumFlowerStage.FLOWER, true, 1));
//				}
//				CraftGUI.Render.iconBlock(new IPoint(0.0f, dy), ind.getGenome().getType().getVariantIcon(EnumFlowerStage.FLOWER, true, 0));
//				GL11.glPopMatrix();
			}
		};
	}

	@Override
	public String getTitle() {
		return "Appearance";
	}
}

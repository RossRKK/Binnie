package binnie.craftgui.minecraft.control;

import binnie.craftgui.controls.core.Control;
import binnie.craftgui.core.Attribute;
import binnie.craftgui.core.CraftGUI;
import binnie.craftgui.core.ITooltip;
import binnie.craftgui.core.IWidget;
import binnie.craftgui.core.Tooltip;
import binnie.craftgui.resource.minecraft.CraftGUITexture;

import java.util.Objects;

public class ControlUser extends Control implements ITooltip {
	private String username;
	String team;

	public ControlUser(final IWidget parent, final float x, final float y, final String username) {
		super(parent, x, y, 16.0f, 16.0f);
		this.username = "";
		this.team = "";
		this.addAttribute(Attribute.MouseOver);
		this.username = username;
	}

	@Override
	public void onRenderBackground() {
		CraftGUI.Render.texture(CraftGUITexture.UserButton, this.getArea());
	}

	@Override
	public void getTooltip(final Tooltip tooltip) {
		tooltip.setType(Tooltip.Type.User);
		tooltip.add("Owner");
		if (!Objects.equals(this.username, "")) {
			tooltip.add(this.username);
		}
		tooltip.setMaxWidth(200);
	}
}

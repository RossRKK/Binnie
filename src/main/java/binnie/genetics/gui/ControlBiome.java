package binnie.genetics.gui;

import binnie.craftgui.controls.core.Control;
import binnie.craftgui.core.ITooltip;
import binnie.craftgui.core.IWidget;
import binnie.craftgui.core.Tooltip;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class ControlBiome extends Control implements ITooltip {
	Biome biome;
	String iconCategory;

	public ControlBiome(final IWidget parent, final float x, final float y, final float w, final float h, final Biome biome) {
		super(parent, x, y, w, h);
		this.biome = null;
		this.iconCategory = "plains";
		this.biome = biome;
	}

	@Override
	public void onRenderBackground() {
		if (BiomeDictionary.isBiomeOfType(this.biome, BiomeDictionary.Type.MOUNTAIN)) {
			this.iconCategory = "hills";
		}
		if (BiomeDictionary.isBiomeOfType(this.biome, BiomeDictionary.Type.HILLS)) {
			this.iconCategory = "hills";
		}
		if (BiomeDictionary.isBiomeOfType(this.biome, BiomeDictionary.Type.SANDY)) {
			this.iconCategory = "desert";
		}
		if (BiomeDictionary.isBiomeOfType(this.biome, BiomeDictionary.Type.SNOWY)) {
			this.iconCategory = "snow";
		}
		if (BiomeDictionary.isBiomeOfType(this.biome, BiomeDictionary.Type.FOREST)) {
			this.iconCategory = "forest";
		}
		if (BiomeDictionary.isBiomeOfType(this.biome, BiomeDictionary.Type.SWAMP)) {
			this.iconCategory = "swamp";
		}
		if (BiomeDictionary.isBiomeOfType(this.biome, BiomeDictionary.Type.JUNGLE)) {
			this.iconCategory = "jungle";
		}
		if (BiomeDictionary.isBiomeOfType(this.biome, BiomeDictionary.Type.COLD) && BiomeDictionary.isBiomeOfType(this.biome, BiomeDictionary.Type.FOREST)) {
			this.iconCategory = "taiga";
		}
		if (BiomeDictionary.isBiomeOfType(this.biome, BiomeDictionary.Type.MUSHROOM)) {
			this.iconCategory = "mushroom";
		}
		if (BiomeDictionary.isBiomeOfType(this.biome, BiomeDictionary.Type.OCEAN)) {
			this.iconCategory = "ocean";
		}
		if (BiomeDictionary.isBiomeOfType(this.biome, BiomeDictionary.Type.NETHER)) {
			this.iconCategory = "nether";
		}
		if (BiomeDictionary.isBiomeOfType(this.biome, BiomeDictionary.Type.END)) {
			this.iconCategory = "end";
		}
//		final IIcon icon = TextureManager.getInstance().getDefault("habitats/" + this.iconCategory);
//		CraftGUI.Render.iconItem(IPoint.ZERO, icon);
	}

	@Override
	public void getTooltip(final Tooltip tooltip) {
		tooltip.add(this.biome.getBiomeName().replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2"));
	}
}

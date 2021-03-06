package binnie.genetics.integration.jei.sequencer;

import binnie.genetics.integration.jei.GeneticsJeiPlugin;
import binnie.genetics.integration.jei.RecipeUids;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import net.minecraft.client.Minecraft;

public class SequencerRecipeCategory extends BlankRecipeCategory<SequencerRecipeWrapper> {
	@Override
	public String getUid() {
		return RecipeUids.SEQUENCER;
	}

	@Override
	public String getTitle() {
		return "Sequencing";
	}

	@Override
	public IDrawable getBackground() {
		return GeneticsJeiPlugin.guiHelper.createBlankDrawable(72, 41);
	}

	@Override
	public void drawExtras(Minecraft minecraft) {
		IDrawable slot = GeneticsJeiPlugin.guiHelper.getSlotDrawable();
		slot.draw(minecraft, 0, 0);
		slot.draw(minecraft, 0, 21);

		slot.draw(minecraft, 52, 21);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, SequencerRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
		itemStacks.init(0, true, 0, 0);
		itemStacks.init(1, true, 0, 21);
		itemStacks.init(2, true, 52, 0);
		itemStacks.init(3, false, 52, 21);
		itemStacks.set(ingredients);
	}
}

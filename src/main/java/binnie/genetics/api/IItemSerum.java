package binnie.genetics.api;

import net.minecraft.item.ItemStack;

public interface IItemSerum extends IItemChargeable {
	IGene[] getGenes(final ItemStack p0);

	IGene getGene(final ItemStack p0, final int p1);

	ItemStack addGene(final ItemStack p0, final IGene p1);
}

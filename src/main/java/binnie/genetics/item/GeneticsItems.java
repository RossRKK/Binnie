package binnie.genetics.item;

import binnie.core.item.IItemMiscProvider;
import binnie.genetics.Genetics;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

public enum GeneticsItems implements IItemMiscProvider {
	LaboratoryCasing("Reinforced Casing", "casingIron"),
	DNADye("DNA Dye", "dnaDye"),
	FluorescentDye("Fluorescent Dye", "dyeFluor"),
	Enzyme("Enzyme", "enzyme"),
	GrowthMedium("Growth Medium", "growthMedium"),
	EmptySequencer("Blank Sequence", "sequencerEmpty"),
	EmptySerum("Empty Serum Vial", "serumEmpty"),
	EmptyGenome("Empty Serum Array", "genomeEmpty"),
	Cylinder("Glass Cylinder", "cylinderEmpty"),
	IntegratedCircuit("Integrated Circuit Board", "integratedCircuit"),
	IntegratedCPU("Integrated CPU", "integratedCPU"),
	IntegratedCasing("Integrated Casing", "casingCircuit");

	String name;
	String modelPath;

	GeneticsItems(final String name, final String modelPath) {
		this.name = name;
		this.modelPath = modelPath;
	}

	@Override
	public String getModelPath() {
		return modelPath;
	}

	@Override
	public void addInformation(final List par3List) {
	}

	@Override
	public String getName(final ItemStack stack) {
		return this.name;
	}

	@Override
	public ItemStack get(final int size) {
		return (Genetics.itemGenetics == null) ? null : new ItemStack(Genetics.itemGenetics, size, this.ordinal());
	}

	public ItemStack get(@Nonnull Item itemGenetics, final int size) {
		return new ItemStack(itemGenetics, size, this.ordinal());
	}

	@Override
	public boolean isActive() {
		return true;
	}
}

package binnie.craftgui.mod.database;

import binnie.Binnie;
import binnie.craftgui.controls.ControlText;
import binnie.craftgui.controls.ControlTextCentered;
import binnie.craftgui.core.IWidget;
import binnie.craftgui.events.EventValueChanged;
import forestry.api.genetics.IAlleleSpecies;
import forestry.api.genetics.IClassification;
import net.minecraftforge.fml.common.Mod;

public class PageBranchSpecies extends PageBranch {
	private ControlText pageBranchSpecies_title;
	private ControlSpeciesBox pageBranchSpecies_speciesList;

	@Mod.EventHandler
	public void onHandleEvent(final EventValueChanged<IAlleleSpecies> event) {
	}

	public PageBranchSpecies(final IWidget parent, final DatabaseTab tab) {
		super(parent, tab);
		this.pageBranchSpecies_title = new ControlTextCentered(this, 8.0f, Binnie.Language.localise("binniecore.gui.database.species"));
		this.addEventHandler(new EventValueChanged.Handler() {
			@Override
			public void onEvent(final EventValueChanged event) {
				if (event.isOrigin(PageBranchSpecies.this.pageBranchSpecies_speciesList)) {
					((WindowAbstractDatabase) PageBranchSpecies.this.getSuperParent()).gotoSpecies((IAlleleSpecies) event.getValue());
				}
			}
		});
		this.pageBranchSpecies_speciesList = new ControlSpeciesBox(this, 4.0f, 20.0f, 136.0f, 152.0f);
	}

	@Override
	public void onValueChanged(final IClassification branch) {
		this.pageBranchSpecies_speciesList.setBranch(branch);
	}
}

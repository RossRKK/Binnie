package binnie.core.item;

import binnie.Constants;
import binnie.core.BinnieCore;
import binnie.core.gui.BinnieCoreGUI;
import forestry.api.core.IModelManager;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemFieldKit extends ItemCore {
	private ModelResourceLocation fieldKit;
	private ModelResourceLocation fieldKit1;
	private ModelResourceLocation fieldKit2;
	private ModelResourceLocation fieldKit3;

	public ItemFieldKit() {
		super("fieldKit");
		this.setUnlocalizedName("fieldKit");
		this.setCreativeTab(CreativeTabs.TOOLS);
		this.setMaxStackSize(1);
		this.setMaxDamage(64);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModel(Item item, IModelManager manager) {
		manager.registerItemModel(item, new FieldKitMeshDefinition());
		fieldKit = new ModelResourceLocation(Constants.CORE_MOD_ID + ":fieldKit", "inventory");
		fieldKit1 = new ModelResourceLocation(Constants.CORE_MOD_ID + ":fieldKit1", "inventory");
		fieldKit2 = new ModelResourceLocation(Constants.CORE_MOD_ID + ":fieldKit2", "inventory");
		fieldKit3 = new ModelResourceLocation(Constants.CORE_MOD_ID + ":fieldKit3", "inventory");
		ModelBakery.registerItemVariants(item, fieldKit, fieldKit1, fieldKit2, fieldKit3);
	}

	@SideOnly(Side.CLIENT)
	private class FieldKitMeshDefinition implements ItemMeshDefinition {
		@Override
		public ModelResourceLocation getModelLocation(ItemStack stack) {
			final int damage = stack.getItemDamage();
			if (damage < 24) {
				return fieldKit3;
			}
			if (damage < 48) {
				return fieldKit2;
			}
			if (damage < 64) {
				return fieldKit1;
			}
			return fieldKit;
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand) {
		if (!player.isSneaking() && hand == EnumHand.MAIN_HAND) {
			BinnieCore.proxy.openGui(BinnieCoreGUI.FieldKit, player, new BlockPos((int) player.posX, (int) player.posY, (int) player.posZ));
		}
		return super.onItemRightClick(itemStack, world, player, hand);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack p_77624_1_, final EntityPlayer p_77624_2_, final List p_77624_3_, final boolean p_77624_4_) {
		final int i = this.getMaxDamage() - p_77624_1_.getItemDamage();
		if (i == 0) {
			p_77624_3_.add("No paper");
		} else {
			p_77624_3_.add("" + i + " sheet" + ((i > 1) ? "s" : "") + " of paper");
		}
		super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
	}

	@Override
	public String getItemStackDisplayName(final ItemStack p_77653_1_) {
		return "Field Kit";
	}
}

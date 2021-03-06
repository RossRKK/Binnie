package binnie.botany.gardening;

import binnie.Binnie;
import binnie.botany.Botany;
import binnie.botany.CreativeTabBotany;
import binnie.botany.api.EnumAcidity;
import binnie.botany.api.EnumMoisture;
import binnie.botany.api.EnumSoilType;
import binnie.botany.ceramic.BlockCeramic;
import binnie.botany.ceramic.BlockCeramicPatterned;
import binnie.botany.ceramic.BlockStained;
import binnie.botany.ceramic.CeramicTileRecipe;
import binnie.botany.ceramic.ItemCeramic;
import binnie.botany.ceramic.PigmentRecipe;
import binnie.botany.ceramic.TileCeramic;
import binnie.botany.ceramic.brick.BlockCeramicBrick;
import binnie.botany.ceramic.brick.ItemCeramicBrick;
import binnie.botany.ceramic.brick.TileCeramicBrick;
import binnie.botany.farm.CircuitGarden;
import binnie.botany.flower.ItemInsulatedTube;
import binnie.botany.genetics.EnumFlowerColor;
import binnie.botany.items.BotanyItems;
import binnie.botany.items.ItemClay;
import binnie.botany.items.ItemPigment;
import binnie.core.BinnieCore;
import binnie.core.IInitializable;
import binnie.core.Mods;
import binnie.core.block.ItemMetadata;
import binnie.core.block.TileEntityMetadata;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.HashMap;
import java.util.Map;

public class ModuleGardening implements IInitializable {
	public static final HashMap<ItemStack, Integer> queuedAcidFertilisers = new HashMap<>();
	public static final HashMap<ItemStack, Integer> queuedAlkalineFertilisers = new HashMap<>();
	public static final HashMap<ItemStack, Integer> queuedNutrientFertilisers = new HashMap<>();

	@Override
	public void preInit() {
		Botany.proxy.registerBlock(Botany.plant = new BlockPlant());
		Botany.proxy.registerItem(new ItemWeed(Botany.plant).setRegistryName(Botany.plant.getRegistryName()));
		Botany.soil = new BlockSoil(EnumSoilType.SOIL, "soil", false);
		Botany.proxy.registerBlock(Botany.soil);
		Botany.proxy.registerItem(new ItemSoil(Botany.soil).setRegistryName(Botany.soil.getRegistryName()));
		Botany.loam = new BlockSoil(EnumSoilType.LOAM, "loam", false);
		Botany.proxy.registerBlock(Botany.loam);
		Botany.proxy.registerItem(new ItemSoil(Botany.loam).setRegistryName(Botany.loam.getRegistryName()));
		Botany.flowerbed = new BlockSoil(EnumSoilType.FLOWERBED, "flowerbed", false);
		Botany.proxy.registerBlock(Botany.flowerbed);
		Botany.proxy.registerItem(new ItemSoil(Botany.flowerbed).setRegistryName(Botany.flowerbed.getRegistryName()));
		Botany.soilNoWeed = new BlockSoil(EnumSoilType.SOIL, "soilNoWeed", true);
		Botany.proxy.registerBlock(Botany.soilNoWeed);
		Botany.proxy.registerItem(new ItemSoil(Botany.soilNoWeed).setRegistryName(Botany.soilNoWeed.getRegistryName()));
		Botany.loamNoWeed = new BlockSoil(EnumSoilType.LOAM, "loamNoWeed", true);
		Botany.proxy.registerBlock(Botany.loamNoWeed);
		Botany.proxy.registerItem(new ItemSoil(Botany.loamNoWeed).setRegistryName(Botany.loamNoWeed.getRegistryName()));
		Botany.flowerbedNoWeed = new BlockSoil(EnumSoilType.FLOWERBED, "flowerbedNoWeed", true);
		Botany.proxy.registerBlock(Botany.flowerbedNoWeed);
		Botany.proxy.registerItem(new ItemSoil(Botany.flowerbedNoWeed).setRegistryName(Botany.flowerbedNoWeed.getRegistryName()));
		Botany.soilMeter = new ItemSoilMeter();
		Botany.proxy.registerItem(Botany.soilMeter);
		Botany.insulatedTube = new ItemInsulatedTube();
		Botany.proxy.registerItem(Botany.insulatedTube);
		Botany.trowelWood = new ItemTrowel(Item.ToolMaterial.WOOD, "Wood");
		Botany.proxy.registerItem(Botany.trowelWood);
		Botany.trowelStone = new ItemTrowel(Item.ToolMaterial.STONE, "Stone");
		Botany.proxy.registerItem(Botany.trowelStone);
		Botany.trowelIron = new ItemTrowel(Item.ToolMaterial.IRON, "Iron");
		Botany.proxy.registerItem(Botany.trowelIron);
		Botany.trowelDiamond = new ItemTrowel(Item.ToolMaterial.DIAMOND, "Diamond");
		Botany.proxy.registerItem(Botany.trowelDiamond);
		Botany.trowelGold = new ItemTrowel(Item.ToolMaterial.GOLD, "Gold");
		Botany.proxy.registerItem(Botany.trowelGold);
		Botany.misc = Binnie.Item.registerMiscItems(BotanyItems.values(), CreativeTabBotany.instance);
		Botany.proxy.registerItem(Botany.misc);
		Botany.pigment = new ItemPigment();
		Botany.proxy.registerItem(Botany.pigment);
		Botany.clay = new ItemClay();
		Botany.proxy.registerItem(Botany.clay);
		Botany.ceramic = new BlockCeramic();
		Botany.proxy.registerBlock(Botany.ceramic);
		Botany.proxy.registerItem(new ItemCeramic(Botany.ceramic).setRegistryName(Botany.ceramic.getRegistryName()));
		BinnieCore.proxy.registerTileEntity(TileCeramic.class, "botany.tile.ceramic");
//		BinnieCore.proxy.registerCustomItemRenderer(Item.getItemFromBlock(Botany.ceramic), new ItemMetadataRenderer());
		Botany.stained = new BlockStained();
		Botany.proxy.registerBlock(Botany.stained);
		Botany.proxy.registerItem(new ItemMetadata(Botany.stained).setRegistryName(Botany.stained.getRegistryName()));
//		BinnieCore.proxy.registerCustomItemRenderer(Item.getItemFromBlock(Botany.stained), new ItemMetadataRenderer());
		Botany.ceramicTile = new BlockCeramicPatterned();
		Botany.proxy.registerBlock(Botany.ceramicTile);
		Botany.proxy.registerItem(new ItemMetadata(Botany.ceramicTile).setRegistryName(Botany.ceramicTile.getRegistryName()));
		Botany.ceramicBrick = new BlockCeramicBrick();
		Botany.proxy.registerBlock(Botany.ceramicBrick);
		Botany.proxy.registerItem(new ItemCeramicBrick(Botany.ceramicBrick).setRegistryName(Botany.ceramicBrick.getRegistryName()));
		GameRegistry.registerTileEntity(TileCeramicBrick.class, "");
		BinnieCore.proxy.registerTileEntity(TileCeramicBrick.class, "botany.tile.ceramicBrick");
//		BinnieCore.proxy.registerCustomItemRenderer(Item.getItemFromBlock(Botany.ceramicTile), new MultipassItemRenderer());
//		BinnieCore.proxy.registerCustomItemRenderer(Item.getItemFromBlock(Botany.ceramicBrick), new MultipassItemRenderer());
	}

	@Override
	public void init() {
		OreDictionary.registerOre("pigment", Botany.pigment);
		RecipeSorter.register("botany:ceramictile", CeramicTileRecipe.class, RecipeSorter.Category.SHAPED, "");
		RecipeSorter.register("botany:pigment", PigmentRecipe.class, RecipeSorter.Category.SHAPED, "");
		final ItemStack yellow = new ItemStack(Blocks.YELLOW_FLOWER, 1);
		final ItemStack red = new ItemStack(Blocks.RED_FLOWER, 1);
		final ItemStack blue = new ItemStack(Blocks.RED_FLOWER, 1, 7);
		for (boolean manual : new boolean[]{true, false}) {
			for (boolean fertilised : new boolean[]{true, false}) {
				for (EnumMoisture moist : EnumMoisture.values()) {
					ItemStack icon;
					if (moist == EnumMoisture.Dry) {
						icon = yellow;
					} else if (moist == EnumMoisture.Normal) {
						icon = red;
					} else {
						icon = blue;
					}
					int insulate = 2 - moist.ordinal();
					if (fertilised) {
						insulate += 3;
					}
					new CircuitGarden(moist, null, manual, fertilised, new ItemStack(Botany.insulatedTube, 1, 0 + 128 * insulate), icon);
					new CircuitGarden(moist, EnumAcidity.Acid, manual, fertilised, new ItemStack(Botany.insulatedTube, 1, 1 + 128 * insulate), icon);
					new CircuitGarden(moist, EnumAcidity.Neutral, manual, fertilised, new ItemStack(Botany.insulatedTube, 1, 2 + 128 * insulate), icon);
					new CircuitGarden(moist, EnumAcidity.Alkaline, manual, fertilised, new ItemStack(Botany.insulatedTube, 1, 3 + 128 * insulate), icon);
				}
			}
		}
	}

	@Override
	public void postInit() {
		GameRegistry.addRecipe(new CeramicTileRecipe());
		for (int mat = 0; mat < 4; ++mat) {
			for (int insulate = 0; insulate < 6; ++insulate) {
				final ItemStack tubes = new ItemStack(Botany.insulatedTube, 2, mat + 128 * insulate);
				final ItemStack insulateStack = ItemInsulatedTube.getInsulateStack(tubes);
				final ItemStack forestryTube = new ItemStack(Mods.Forestry.item("thermionicTubes"), 1, mat);
				GameRegistry.addShapelessRecipe(tubes, forestryTube, forestryTube, insulateStack);
			}
		}
		GameRegistry.addRecipe(new ShapedOreRecipe(Botany.trowelWood, "d  ", " x ", "  s", 'd', Blocks.DIRT, 's', "stickWood", 'x', "plankWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(Botany.trowelStone, "d  ", " x ", "  s", 'd', Blocks.DIRT, 's', "stickWood", 'x', "cobblestone"));
		GameRegistry.addRecipe(new ShapedOreRecipe(Botany.trowelIron, "d  ", " x ", "  s", 'd', Blocks.DIRT, 's', "stickWood", 'x', "ingotIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(Botany.trowelGold, "d  ", " x ", "  s", 'd', Blocks.DIRT, 's', "stickWood", 'x', "ingotGold"));
		GameRegistry.addRecipe(new ShapedOreRecipe(Botany.trowelDiamond, "d  ", " x ", "  s", 'd', Blocks.DIRT, 's', "stickWood", 'x', "gemDiamond"));
		GameRegistry.addRecipe(new ShapedOreRecipe(Botany.soilMeter, " gg", " rg", "i  ", 'g', "ingotGold", 'r', "dustRedstone", 'i', "ingotIron"));
		GameRegistry.addShapelessRecipe(BotanyItems.Weedkiller.get(4), new ItemStack(Items.SPIDER_EYE), new ItemStack(Items.WHEAT_SEEDS), new ItemStack(Items.WHEAT_SEEDS), new ItemStack(Items.WHEAT_SEEDS));
		GameRegistry.addShapelessRecipe(BotanyItems.AshPowder.get(4), Mods.Forestry.stack("ash"));
		GameRegistry.addShapelessRecipe(BotanyItems.MulchPowder.get(4), Mods.Forestry.stack("mulch"));
		GameRegistry.addShapelessRecipe(BotanyItems.CompostPowder.get(4), Mods.Forestry.stack("fertilizerBio"));
		GameRegistry.addShapelessRecipe(BotanyItems.FertiliserPowder.get(4), Mods.Forestry.stack("fertilizerCompound"));
		GameRegistry.addShapelessRecipe(BotanyItems.PulpPowder.get(4), Mods.Forestry.stack("woodPulp"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(BotanyItems.SulphurPowder.get(4), "dustSulphur"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Botany.pigment, 2, EnumFlowerColor.Black.ordinal()), "pigment", "pigment", "dyeBlack"));
		ModuleGardening.queuedAcidFertilisers.put(BotanyItems.SulphurPowder.get(1), 1);
		ModuleGardening.queuedAcidFertilisers.put(BotanyItems.MulchPowder.get(1), 1);
		ModuleGardening.queuedAcidFertilisers.put(new ItemStack(GameRegistry.findItem("forestry", "mulch")), 2);
		for (final ItemStack stack : OreDictionary.getOres("dustSulfur")) {
			ModuleGardening.queuedAcidFertilisers.put(stack, 2);
		}
		ModuleGardening.queuedAlkalineFertilisers.put(BotanyItems.AshPowder.get(1), 1);
		ModuleGardening.queuedAlkalineFertilisers.put(BotanyItems.PulpPowder.get(1), 1);
		ModuleGardening.queuedAlkalineFertilisers.put(new ItemStack(GameRegistry.findItem("forestry", "ash")), 2);
		ModuleGardening.queuedAlkalineFertilisers.put(new ItemStack(GameRegistry.findItem("forestry", "woodPulp")), 2);
		ModuleGardening.queuedNutrientFertilisers.put(BotanyItems.CompostPowder.get(1), 1);
		ModuleGardening.queuedNutrientFertilisers.put(BotanyItems.FertiliserPowder.get(1), 1);
		ModuleGardening.queuedNutrientFertilisers.put(new ItemStack(GameRegistry.findItem("forestry", "fertilizerBio")), 2);
		ModuleGardening.queuedNutrientFertilisers.put(new ItemStack(GameRegistry.findItem("forestry", "fertilizerCompound")), 2);
		for (final Map.Entry<ItemStack, Integer> entry : ModuleGardening.queuedAcidFertilisers.entrySet()) {
			this.addAcidFertiliser(entry.getKey(), entry.getValue());
		}
		for (final Map.Entry<ItemStack, Integer> entry : ModuleGardening.queuedAlkalineFertilisers.entrySet()) {
			this.addAlkalineFertiliser(entry.getKey(), entry.getValue());
		}
		for (final Map.Entry<ItemStack, Integer> entry : ModuleGardening.queuedNutrientFertilisers.entrySet()) {
			this.addNutrientFertiliser(entry.getKey(), entry.getValue());
		}
		GameRegistry.addRecipe(BotanyItems.Mortar.get(6), " c ", "cgc", " c ", 'c', Items.CLAY_BALL, 'g', Blocks.GRAVEL);
		for (final EnumFlowerColor c : EnumFlowerColor.values()) {
			final ItemStack clay = new ItemStack(Botany.clay, 1, c.ordinal());
			final ItemStack pigment = new ItemStack(Botany.pigment, 1, c.ordinal());
			GameRegistry.addShapelessRecipe(clay, Items.CLAY_BALL, Items.CLAY_BALL, Items.CLAY_BALL, pigment);
			GameRegistry.addSmelting(clay, TileEntityMetadata.getItemStack(Botany.ceramic, c.ordinal()), 0.0f);
			final ItemStack glass = TileEntityMetadata.getItemStack(Botany.stained, c.ordinal());
			glass.stackSize = 4;
			GameRegistry.addShapedRecipe(glass, " g ", "gpg", " g ", 'g', Blocks.GLASS, 'p', pigment);
		}
		GameRegistry.addRecipe(new PigmentRecipe());
	}

	private ItemStack getStack(final int type, final int pH, final int moisture) {
		if (type < 0 || type > 2 || pH < 0 || pH > 2 || moisture < 0 || moisture > 2) {
			return null;
		}
		return new ItemStack(Gardening.getSoilBlock(EnumSoilType.values()[type]), 1, BlockSoil.getMeta(EnumAcidity.values()[pH], EnumMoisture.values()[moisture]));
	}

	private void addAcidFertiliser(final ItemStack stack, final int strengthMax) {
		if (stack == null) {
			return;
		}
		Gardening.fertiliserAcid.put(stack, strengthMax);
		for (int moisture = 0; moisture < 3; ++moisture) {
			for (int pH = 0; pH < 3; ++pH) {
				for (int type = 0; type < 3; ++type) {
					int numOfBlocks = strengthMax * strengthMax;
					for (int strength = 1; strength < strengthMax; ++strength) {
						final ItemStack start = this.getStack(type, pH, moisture);
						final ItemStack end = this.getStack(type, pH - strength, moisture);
						if (end != null) {
							end.stackSize = numOfBlocks;
							final Object[] stacks = new Object[numOfBlocks + 1];
							for (int i = 0; i < numOfBlocks; ++i) {
								stacks[i] = start;
							}
							stacks[numOfBlocks] = stack.copy();
							GameRegistry.addShapelessRecipe(end, stacks);
						}
						numOfBlocks /= 2;
					}
				}
			}
		}
	}

	private void addAlkalineFertiliser(final ItemStack stack, final int strengthMax) {
		if (stack == null) {
			return;
		}
		Gardening.fertiliserAlkaline.put(stack, strengthMax);
		for (int moisture = 0; moisture < 3; ++moisture) {
			for (int pH = 0; pH < 3; ++pH) {
				for (int type = 0; type < 3; ++type) {
					int numOfBlocks = strengthMax * strengthMax;
					for (int strength = 1; strength < strengthMax; ++strength) {
						final ItemStack start = this.getStack(type, pH, moisture);
						final ItemStack end = this.getStack(type, pH + strength, moisture);
						if (end != null) {
							end.stackSize = numOfBlocks;
							final Object[] stacks = new Object[numOfBlocks + 1];
							for (int i = 0; i < numOfBlocks; ++i) {
								stacks[i] = start;
							}
							stacks[numOfBlocks] = stack.copy();
							GameRegistry.addShapelessRecipe(end, stacks);
						}
						numOfBlocks /= 2;
					}
				}
			}
		}
	}

	private void addNutrientFertiliser(final ItemStack stack, final int strengthMax) {
		if (stack == null) {
			return;
		}
		Gardening.fertiliserNutrient.put(stack, strengthMax);
		for (int moisture = 0; moisture < 3; ++moisture) {
			for (int pH = 0; pH < 3; ++pH) {
				for (int type = 0; type < 3; ++type) {
					int numOfBlocks = strengthMax * strengthMax;
					for (int strength = 1; strength < strengthMax; ++strength) {
						final ItemStack start = this.getStack(type, pH, moisture);
						final ItemStack end = this.getStack(type + strength, pH, moisture);
						if (end != null) {
							end.stackSize = numOfBlocks;
							final Object[] stacks = new Object[numOfBlocks + 1];
							for (int i = 0; i < numOfBlocks; ++i) {
								stacks[i] = start;
							}
							stacks[numOfBlocks] = stack.copy();
							GameRegistry.addShapelessRecipe(end, stacks);
						}
						numOfBlocks /= 2;
					}
				}
			}
		}
	}
}

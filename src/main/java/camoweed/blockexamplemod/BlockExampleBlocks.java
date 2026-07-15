package camoweed.blockexamplemod;

import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSounds;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryCategory;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryPlacement;
import turniplabs.halplibe.util.BlockInitEntrypoint;

import static camoweed.blockexamplemod.BlockExampleMod.MOD_ID;

/*
 - Simple blocks using basic models
 - Rotatable blocks using block logic

*/
// implement BlockInitEntrypoint
public class BlockExampleBlocks implements BlockInitEntrypoint {

	// we will create a couple extra functions

	// this will increment blockID each time we use newID().
	// hardcoding blockIDs is also fine. this is optional
	// blockID can range 0-16000
	// vanilla bta blocks span from 0-1200~
	private static int startingBlockID = 7000; // compare IDs of other mods and find a range that works for you
	public static int newBlockID() {
		return startingBlockID++;
	}

	// this will allow us to initialize blocks in BlockExampleMod.java
	public static boolean hasInit = false;
	public static void init() {
		if (!hasInit) {
			hasInit = true;
			initializeBlocks();
		}
	}
	// we need to declare our blocks
	public static Block<?> BASIC_BLOCK;
	public static Block<?> CUSTOM_BLOCK;
	public static Block<?> ROTATABLE_BLOCK;
	public static Block<?> VERY_ROTATABLE_BLOCK;
	public static Block<BlockLogicStairs> STAIR_BLOCK;

	// the fun part
	// control + click BlockBuilder for more detailed info
	// there is WAY more in the BlockBuilder class so definitely investigate
	private static void initializeBlocks() {
		BlockBuilder BasicBlock = new BlockBuilder(MOD_ID)
			// as of 8.0 we need to define creative menu category
			// if you don't do this can still use the /give command or recipes
			.setCreativeInventoryPlacement
				(new CreativeInventoryPlacement.Category
					(CreativeInventoryCategory.BASICS)
				);
		// now we will build it
		BASIC_BLOCK = BasicBlock
			.build(
				"basic",
				"basic_block",
				newBlockID(),
				b -> new BlockLogic(b, Materials.CLOTH));
		// translation key will be tile.MOD_ID.translationKey
		// refer to /resources/assets/blockexamplemod/lang/en_US/en_US.lang

		// new block
		// for custom textures see BlockExampleModels.java
		BlockBuilder CustomBlock = new BlockBuilder((MOD_ID)).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.BASICS))
			// we will give it a sound too
			.setBlockSound(BlockSounds.CRYSTAL);
		// new lines are optional. spaces too if you're insane
		CUSTOM_BLOCK=CustomBlock.build("custom","custom_block",newBlockID(),b -> new BlockLogic(b, Materials.CLOTH));

		// new block
		// rotatable ( like a workbench ) for this we need BlockLogicRotatable
		BlockBuilder RotatableBlock = new BlockBuilder((MOD_ID)).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.BASICS));
		ROTATABLE_BLOCK=RotatableBlock.build("rotatable","rotatable_block",newBlockID(),
			b -> new BlockLogicRotatable(b, Materials.WOOD) {
			// if you're not sure what onPlacedOnSide is control + click BlockLogicRotatable
			@Override
			public void onPlacedOnSide(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, double xHit, double yHit) {
				super.onPlacedOnSide(world, tilePos, side, xHit, yHit);
			}
		}
		);

		// new block
		// very rotatable (like a log)
		BlockBuilder VeryRotatableBlock = new BlockBuilder((MOD_ID)).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.BASICS));
		VERY_ROTATABLE_BLOCK=VeryRotatableBlock.build("very_rotatable","very_rotatable_block",newBlockID(),b -> new BlockLogicVeryRotatable(b, Materials.WOOD) {
			@Override
			public void onPlacedOnSide(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, double xHit, double yHit) {
				world.setBlockDataNotify(tilePos, setDirection(0, side.direction()));
			}
		}
		);

		BlockBuilder StairBlock = new BlockBuilder((MOD_ID)).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.BASICS));
		STAIR_BLOCK=StairBlock.
			// standard building
			build("stair", "stair_block",newBlockID(),
			// need to use another block as a "base" model, can be base game stuff, set to a custom block to chose texture/properties
			b -> new BlockLogicStairs(b, CUSTOM_BLOCK));

	}
	@Override
	public void afterBlockInit() {

	}
}

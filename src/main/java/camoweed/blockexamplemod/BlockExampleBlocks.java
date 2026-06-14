package camoweed.blockexamplemod;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicGlass;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryCategory;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryPlacement;
import turniplabs.halplibe.util.BlockInitEntrypoint;

import static camoweed.blockexamplemod.BlockExampleMod.MOD_ID;

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

		// see BlockExampleModels.java we will give this block a custom texture and a place in the creative menu
		BlockBuilder CustomBlock = new BlockBuilder((MOD_ID)).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.BASICS))
			// we will give it a sound too
			.setBlockSound(BlockSounds.METAL);

		//	.setHardness(1.0F)		// time to break the block
		//	.setResistance(1.0F) 	// blast resistance
		//	.setLuminance(0)		// light level 0-15
		//	.setLightOpacity(15)	// 0 is glass
		//	.setSlipperiness(0.6F)	// 0.6F is default 0.98F is ice
		//	.setFlammability(0, 0)
		//	.setInfiniburn()		// infinite fire burn
		//	.setUnbreakable()		// bedrock behaviour
		//	.setBlockSound(BlockSounds.GLASS)
		//	.setTags(BlockTags.GROWS_CACTI, BlockTags.PLANTABLE_IN_JAR);

		BASIC_BLOCK = BasicBlock
			.build("basic",
				"basic_block",
				newBlockID(),
				b -> new BlockLogic(b, Materials.CLOTH)
			);
		// translation key will be tile.MOD_ID.translationKey
		// refer to /resources/assets/blockexamplemod/lang/en_US/en_US.lang

		// new lines are optional. spaces too if you're insane
		CUSTOM_BLOCK=CustomBlock.build("custom","custom_block",newBlockID(),b -> new BlockLogic(b, Materials.CLOTH));
		// build(translationKey, name, numericId, logicSupplier);
	}
	@Override
	public void afterBlockInit() {

	}
}

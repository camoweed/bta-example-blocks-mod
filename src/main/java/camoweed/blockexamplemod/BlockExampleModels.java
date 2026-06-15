package camoweed.blockexamplemod;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.BlockModelRotatable;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;
import turniplabs.halplibe.util.ModelEntrypoint;

public class BlockExampleModels implements ModelEntrypoint {
	@Override
	public void initBlockModels(BlockModelDispatcher dispatcher) {
		// single vanilla bta texture with a basic model
		dispatcher.addDispatch(new BlockModelStandard<>(BlockExampleBlocks.BASIC_BLOCK)
			.setTex("minecraft:block/cobbled_stone", Side.sides)
		);
		// single custom texture (/assets/blockexamplemod/textures/block)
		dispatcher.addDispatch(new BlockModelStandard<>(BlockExampleBlocks.CUSTOM_BLOCK).setTex("blockexamplemod:block/custom_block", Side.sides));
		// workbench style rotatable texture
		dispatcher.addDispatch(new BlockModelRotatable<>(BlockExampleBlocks.ROTATABLE_BLOCK)
			.setTex("blockexamplemod:block/rotatable_block/north", Side.NORTH)
			.setTex("blockexamplemod:block/rotatable_block/east", Side.EAST)
			.setTex("blockexamplemod:block/rotatable_block/south", Side.SOUTH)
			.setTex("blockexamplemod:block/rotatable_block/west", Side.WEST)
			.setTex("blockexamplemod:block/rotatable_block/top", Side.TOP)
			.setTex("blockexamplemod:block/rotatable_block/bottom", Side.BOTTOM)
		);

	}

	@Override
	public void initItemModels(ItemModelDispatcher dispatcher) {

	}

	@Override
	public void initEntityModels(EntityRendererDispatcher dispatcher) {

	}

	@Override
	public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {

	}

	@Override
	public void initBlockColors(BlockColorDispatcher dispatcher) {

	}
}

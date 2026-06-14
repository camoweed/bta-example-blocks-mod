package camoweed.blockexamplemod;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class BlockExampleMod implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {

	// register your mod ID. The mod ID must be the same everywhere.
	// e.g. /src/main/java/turniplabs/blockexamplemod/ or blockexamplemod.mixins.json
	//															make sure to set preloadAssets: true v
	public static final String MOD_ID = HalpLibe.registerMod("blockexamplemod", true);
	// make sure to change mod ID in fabric.mod.json!!
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("BlockExampleMod initialized.");
	}

	@Override
	public void beforeGameStart() {
		BlockExampleBlocks.init();
		LOGGER.info("BlockExampleBlocks initialized");
	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady() {

	}

	@Override
	public void initNamespaces() {

	}
}

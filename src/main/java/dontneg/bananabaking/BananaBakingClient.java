package dontneg.bananabaking;

import dontneg.bananabaking.block.BananaBlocks;
import dontneg.bananabaking.screen.BakingScreen;
import dontneg.bananabaking.screen.BakingScreenHandler;
import dontneg.bananabaking.screen.BananaScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

public class BananaBakingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BananaBlocks.VANILLA_CROP, RenderLayer.getCutout());
        HandledScreens.register(BananaScreenHandlers.BAKING_OVEN_SCREEN_HANDLER, BakingScreen::new);
    }
}

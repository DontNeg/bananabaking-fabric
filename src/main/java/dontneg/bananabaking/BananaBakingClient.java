package dontneg.bananabaking;

import dontneg.bananabaking.block.BananaBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class BananaBakingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BananaBlocks.VANILLA_CROP, RenderLayer.getCutout());
    }
}

package dontneg.bananabaking.screen;

import dontneg.bananabaking.BananaBaking;
import dontneg.bananabaking.codec.BakingData;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;

public class BananaScreenHandlers {
    public static final ScreenHandlerType<BakingScreenHandler> BAKING_OVEN_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(BakingScreenHandler::new, BakingData.PACKET_CODEC);

    public static void registerScreenHandlers(){
        Registry.register(Registries.SCREEN_HANDLER, BananaBaking.MODID, BAKING_OVEN_SCREEN_HANDLER);
    }
}

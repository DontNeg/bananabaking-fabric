package dontneg.bananabaking.screen;

import dontneg.bananabaking.BananaBaking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class BananaScreenHandlers {
    public static final ScreenHandlerType<BakingScreenHandler> BAKING_OVEN_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(BananaBaking.MODID, "baking"),
                    new ExtendedScreenHandlerType<>(BakingScreenHandler::new));

    public static void registerScreenHandlers(){
        BananaBaking.LOGGER.info("Banana Baking - Screen Handler Initializing!");
    }
}

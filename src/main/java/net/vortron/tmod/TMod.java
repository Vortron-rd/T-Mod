package net.vortron.tmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.vortron.tmod.handler.GuiHandler;
import net.vortron.tmod.world.WorldGen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = TMod.MOD_ID, name = TMod.MOD_NAME, version = TMod.VERSION)
public class TMod {

    public static final String MOD_NAME = "T-Mod";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);
    public static final String MOD_ID = "tmod";
    public static final String VERSION = "0.0.1-alpha";
    @Mod.Instance(MOD_ID)
    public static TMod MOD_INSTANCE;

    /**
     * <a href="https://cleanroommc.com/wiki/forge-mod-development/event#overview">
     *     Take a look at how many FMLStateEvents you can listen to via the @Mod.EventHandler annotation here
     * </a>
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("Hello From {}!", MOD_NAME);
        GameRegistry.registerWorldGenerator(new WorldGen(),3);

    }
    @Mod.EventHandler
    public void postInit(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(MOD_INSTANCE, new GuiHandler());
    }


}

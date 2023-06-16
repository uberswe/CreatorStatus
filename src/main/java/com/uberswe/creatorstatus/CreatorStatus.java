package com.uberswe.creatorstatus;

import com.mojang.logging.LogUtils;
import com.uberswe.creatorstatus.networking.ModMessages;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
//
// A lot of this is based on Modding by Kaupenjoe
// https://www.youtube.com/playlist?list=PLKGarocXCE1HrC60yuTNTGRoZc6hf5Uvl
@Mod(CreatorStatus.MODID)
public class CreatorStatus
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "creatorstatus";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace


    public static final int NONE = 0;
    public static final int RECORDING = 1;
    public static final int STREAMING = 2;
    public static final int DO_NOT_RECORD = 3;
    public static final int OPEN_TO_COLLAB = 4;

    public CreatorStatus()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(
                ModMessages::register
        );
    }
}

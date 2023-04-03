package com.kazz.eichelmassierer;


import com.kazz.eichelmassierer.tracker.GhostTracker;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;


@Mod(modid = EichelmassiererMain.MODID, version = EichelmassiererMain.VERSION)
public class EichelmassiererMain
{
    public static final String MODID = "eichelkrauler";
    public static final String VERSION = "0.1";

    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);

        MinecraftForge.EVENT_BUS.register(new GhostTracker());


    }
    /*
    @EventHandler
    public void registerCommands(FMLServerStartingEvent event){
        ServerCommandManager manager = (ServerCommandManager) event.getServer().getCommandManager();
        manager.registerCommand(new CustomCommand());


    }

     */
}

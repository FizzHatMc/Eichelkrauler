package com.kazz.eichelmassierer.tracker;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


public class GhostTracker {

    private int sorrowCount;
    private boolean sorrowBool;
    private int voltaCount = 1;
    private boolean voltaBool;
    private boolean plasmaBool;
    private int plasmaCount;
    private int ghostlyCount;
    private boolean ghostlyBool;
    private boolean coinBool;
    private int coinCount;
    private int count;
    private int timer;
    private int timeMin;
    private String test;
    private int kills;
    private double totalCoins;
    private double coinsHour;
   private String testx;
    private int index;



    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        if (voltaBool || sorrowBool || plasmaBool || ghostlyBool ||coinBool ) {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            if (player != null) {
                count++;
                if (count % 40 == 0) {
                    timer++;
                    timeMin = timer / 60;
                }
            }
        }

        coinsHour = (totalCoins / timeMin)*60;

        }




    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();


            if(message.contains("test")) {
                EntityPlayer player = Minecraft.getMinecraft().thePlayer;
                ItemStack itemInHand = player.inventory.getCurrentItem();
                test = itemInHand.getTagCompound().getCompoundTag("display").getTagList("Lore", 8).get(1).toString();
                if (test.contains("Kills:")) {


                     testx= test.replaceAll("[^Kills:]","");
                     kills = test.charAt(index+2);

                }
            }


            if (message.contains("RARE DROP! Sorrow")) {
                sorrowCount += 1;
                sorrowBool = true;
                double sorrowWorth = 0.4;
                totalCoins = totalCoins + sorrowWorth;
            }


            if (message.contains("RARE DROP! Volta")) {
                voltaCount += 1;
                voltaBool = true;
                double voltaWorth = 0.06;
                totalCoins = totalCoins + voltaWorth;
            }


            if (message.contains("RARE DROP! Plasma")) {
                plasmaCount += 1;
                plasmaBool = true;
                double plasmaWorth = 0.02;
                totalCoins = totalCoins + plasmaWorth;
            }


            if (message.contains("RARE DROP! Ghostly Boots")) {
                ghostlyCount += 1;
                ghostlyBool = true;
                double bootsWorth = 0.077;
                totalCoins = totalCoins + bootsWorth;
            }


            if (message.contains("The ghost")) {
                coinCount += 1;
                coinBool = true;
                totalCoins = totalCoins + 1;
            }



            if (message.contains("reset")) {
                sorrowBool = false;
                sorrowCount = 0;
                voltaBool = false;
                voltaCount = 0;
                plasmaBool = false;
                plasmaCount = 0;
                ghostlyBool = false;
                ghostlyCount = 0;
                coinBool = false;
                coinCount = 0;
                timer = 0;
                totalCoins = 0;
            }
        }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent event) {
        if (event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            return;
        }

        double roundHour = Math.floor(coinsHour * 100)/100;
        double roundCoins = Math.floor(totalCoins * 100)/100;

        int y = 5;
        FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;

        if (voltaBool || sorrowBool || plasmaBool || ghostlyBool ||coinBool) {

            fRender.drawStringWithShadow(EnumChatFormatting.DARK_GRAY + "Ghost Tracker",5, y,0);
            y = y+10;
            fRender.drawStringWithShadow(EnumChatFormatting.DARK_GRAY + "Timer: "+timeMin +" Minuten",5, y,0);
            y = y+10;


            if (voltaBool) {
                fRender.drawStringWithShadow(EnumChatFormatting.BLUE + "    Volta: " + EnumChatFormatting.WHITE + voltaCount, 5, y, 0);
                y = y + 10;
            }
            if (sorrowBool) {
                fRender.drawStringWithShadow(EnumChatFormatting.AQUA + "    Sorrow: " + EnumChatFormatting.WHITE + sorrowCount, 5, y, 0);
                y = y + 10;
            }
            if (plasmaBool) {
                fRender.drawStringWithShadow(EnumChatFormatting.DARK_AQUA + "    Plasma: " + EnumChatFormatting.WHITE + plasmaCount, 5, y, 0);
                y = y + 10;
            }
            if (ghostlyBool) {
                fRender.drawStringWithShadow(EnumChatFormatting.GRAY + "    Ghostly Boots: " + EnumChatFormatting.WHITE + ghostlyCount, 5, y, 0);
                y = y + 10;
            }
            if (coinBool) {
                fRender.drawStringWithShadow(EnumChatFormatting.GOLD + "    Coin drops: " + EnumChatFormatting.WHITE + coinCount, 5, y, 0);
                y = y + 10;
            }
            if (totalCoins>0) {
                fRender.drawStringWithShadow(EnumChatFormatting.GOLD + "    Total Coins: " + EnumChatFormatting.GOLD + roundCoins + "M", 5, y, 0);
                y = y + 10;
            }
            if (coinsHour>0 && coinsHour!=Double.POSITIVE_INFINITY && coinsHour != Double.NEGATIVE_INFINITY) {
                fRender.drawStringWithShadow(EnumChatFormatting.GOLD + "    Coins/h: " + EnumChatFormatting.GOLD + roundHour + "M", 5, y, 0);
                y = y + 50;
            }

            fRender.drawStringWithShadow(EnumChatFormatting.GOLD + "    Index: " + EnumChatFormatting.GOLD + index, 5, y, 0);
            y = y + 50;
            fRender.drawStringWithShadow(EnumChatFormatting.GOLD + "    Test Kills: " + EnumChatFormatting.GOLD + kills, 5, y, 0);
        }
    }
}

package net.canbot.canmod.hax.Modules;

import net.canbot.canmod.event.KeyInputHandler;
import net.canbot.canmod.hax.Module;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;

import java.sql.Time;

public class AutoFish extends Module {

        public AutoFish() {
            super("AutoFish", KeyInputHandler.registerNewBind("Misc", "AutoFish", -1), Category.EXPLOIT);

        }

    public static boolean afenabled = false;
    @Override
    public void onEnable() {
        afenabled=true;
    }

    @Override
    public void onDisable() {
        afenabled=false;
    }
    public static boolean pubCaught;
    int wait=0;
    boolean waitDone = false;
    boolean cast = true;

    @Override
    public void onTick() {

        if (pubCaught&&wait==1) {
            System.out.println("fish on hook, reeling.");

            boolean mainHand = false;
            if (mc.player.getMainHandStack().getItem() instanceof FishingRodItem) {
                mainHand=true;
                mc.player.swingHand(Hand.MAIN_HAND);
                mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
            } else if(mc.player.getOffHandStack().getItem() instanceof FishingRodItem) {
                mainHand=false;
                mc.player.swingHand(Hand.OFF_HAND);
                mc.interactionManager.interactItem(mc.player, Hand.OFF_HAND);
            }
            pubCaught = false;
            wait=10;

        }
        if (pubCaught && wait==0) {wait= (int) (6 + Math.round(Math.random()*10));System.out.println(wait);}
        if (mc.player.fishHook==null && wait==0) {wait= (int) (Math.random()*15+8);System.out.println(wait);}
        if (mc.player.fishHook==null && wait==1) {
            System.out.println("no hook, throwing.");
            if (mc.player.getMainHandStack().getItem() instanceof FishingRodItem) {

                mc.player.swingHand(Hand.MAIN_HAND);
                mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
            } else if(mc.player.getOffHandStack().getItem() instanceof FishingRodItem) {

                mc.player.swingHand(Hand.OFF_HAND);
                mc.interactionManager.interactItem(mc.player, Hand.OFF_HAND);
            }
            wait=4;
        }

        if (wait!=0) {wait--;}
    }


}

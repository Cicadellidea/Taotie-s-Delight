package com.Cicadellidea.taotiesmunchies.tracker;

import com.Cicadellidea.taotiesmunchies.Capabilites.PlayerFoodHealingBonusProvider;
import com.Cicadellidea.taotiesmunchies.Capabilites.PlayerFoodResistanceBonusProvider;
import com.Cicadellidea.taotiesmunchies.TaotiesMunchies;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import net.minecraftforge.event.entity.living.LivingDamageEvent;

import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TaotiesMunchies.MODID)
public class PlayerHealthyTracker {
    @SubscribeEvent
    public static void playerGetDamage(LivingDamageEvent event)
    {
        LivingEntity entity = event.getEntity();
        if(entity instanceof Player player)
        {
            if(!player.level().isClientSide)
            {
                player.getCapability(PlayerFoodResistanceBonusProvider.PLAYER_FOOD_RESISTANCE_BONUS_CAPABILITY).ifPresent(bonus ->
                {
                    event.setAmount((float) (event.getAmount()/(1+bonus.getActual())));
                });


            }
        }
    }
    @SubscribeEvent
    public static void playerGetHealed(LivingHealEvent event)
    {
        LivingEntity entity = event.getEntity();
        if(entity instanceof Player player)
        {
            if(!player.level().isClientSide)
            {
                player.getCapability(PlayerFoodHealingBonusProvider.PLAYER_FOOD_HEALING_BONUS_CAPABILITY).ifPresent(bonus ->
                {
                    event.setAmount((float) (event.getAmount()*(1+bonus.getActual())));
                });


            }
        }
    }






}

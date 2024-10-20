package com.Cicadellidea.taotiesmunchies.tracker;

import com.Cicadellidea.taotiesmunchies.TaotiesMunchies;
import com.Cicadellidea.taotiesmunchies.config.AttributeConfig;
import com.Cicadellidea.taotiesmunchies.config.TaotiesMunchiesConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

public class TaotieFoodEffectTracker {
    @SubscribeEvent
    public void onFoodEaten(LivingEntityUseItemEvent.Finish event){
        if(TaotiesMunchiesConfig.effectBonus)
        {
            Item food = event.getItem().getItem();
            if(food.isEdible())
            {
                if(event.getEntity() instanceof Player player)
                {
                    if (!player.level().isClientSide)
                    {
                        int duration = TaotiesMunchiesConfig.duration;


                        if(TaotiesMunchiesConfig.foodResistanceBonus.contains(food))
                        {
                            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,duration,0));
                        }
                        if(TaotiesMunchiesConfig.foodHealingBonus.contains(food))
                        {
                            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION,duration,1));
                        }
                        if(TaotiesMunchiesConfig.foodShootSpeedBonus.contains(food))
                        {
                            player.addEffect(new MobEffectInstance(TaotiesMunchies.SHOOT_SPEED_UP.get(),duration,0));
                        }
                        if(TaotiesMunchiesConfig.foodArrowDamageBonus.contains(food))
                        {
                            player.addEffect(new MobEffectInstance(TaotiesMunchies.DMG_UP.get(),duration,0));
                        }
                        for(int i=0;i< AttributeConfig.attributeMapList.size();i++){
                            var map = AttributeConfig.attributeMapList.get(i);
                            Set<Item> foodSet = (Set<Item>) map.get("Food");

                            if(foodSet.contains(food)){
                                if(!(map.get("Effect")==null)){
                                    MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation((String) map.get("Effect")));
                                    player.addEffect(new MobEffectInstance(effect,duration, (int) Math.round((double) map.get("EffectLevel"))));
                                }

                            }
                        }

                    }
                }
            }
        }

    }
}

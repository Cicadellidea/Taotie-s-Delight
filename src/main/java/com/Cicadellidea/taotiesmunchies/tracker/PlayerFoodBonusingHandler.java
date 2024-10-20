package com.Cicadellidea.taotiesmunchies.tracker;

import com.Cicadellidea.taotiesmunchies.Capabilites.*;
import com.Cicadellidea.taotiesmunchies.TaotiesMunchies;
import com.Cicadellidea.taotiesmunchies.config.AttributeConfig;
import com.Cicadellidea.taotiesmunchies.config.TaotiesMunchiesConfig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = TaotiesMunchies.MODID)
public class PlayerFoodBonusingHandler
{
    @SubscribeEvent
    public static void playerFoodBonusingEvent(LivingEntityUseItemEvent.Finish event)
    {
        if(event.getEntity() instanceof Player player)
        {
            if(!player.level().isClientSide())
            {
                var item = event.getItem().getItem();
                if(item.isEdible())

                {

                    player.getCapability(TaotiePlayerFoodListProvider.PLAYER_FOODL_LIST_CAPABILITY).ifPresent(foodlist ->
                    {
                        if(foodlist.add(item))
                        {

                            if(TaotiesMunchiesConfig.foodResistanceBonusLimitBreaker.contains(item))
                            {
                                player.getCapability(PlayerFoodResistanceBonusProvider.PLAYER_FOOD_RESISTANCE_BONUS_CAPABILITY).ifPresent(bonus->
                                {
                                    bonus.limintBreaking();
                                });
                            }
                            if(!TaotiesMunchiesConfig.whiteList || TaotiesMunchiesConfig.foodResistanceBonus.contains(item))
                            {
                                player.getCapability(PlayerFoodResistanceBonusProvider.PLAYER_FOOD_RESISTANCE_BONUS_CAPABILITY).ifPresent(bonus ->
                                {
                                    bonus.increase();

                                });
                            }
                            if(TaotiesMunchiesConfig.foodHealingBonusLimitBreaker.contains(item))
                            {
                                player.getCapability(PlayerFoodHealingBonusProvider.PLAYER_FOOD_HEALING_BONUS_CAPABILITY).ifPresent(bonus->
                                {
                                    bonus.limintBreaking();
                                });
                            }
                            if(!TaotiesMunchiesConfig.whiteList || TaotiesMunchiesConfig.foodHealingBonus.contains(item))
                            {
                                player.getCapability(PlayerFoodHealingBonusProvider.PLAYER_FOOD_HEALING_BONUS_CAPABILITY).ifPresent(bonus ->
                                {
                                    bonus.increase();
                                });
                            }
                            if(TaotiesMunchiesConfig.foodShootSpeedBonusLimitBreaker.contains(item))
                            {
                                player.getCapability(PlayerFoodShootSpeedBonusProvider.PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY).ifPresent(bonus->
                                {
                                    bonus.limintBreaking();
                                });
                            }
                            if(!TaotiesMunchiesConfig.whiteList || TaotiesMunchiesConfig.foodShootSpeedBonus.contains(item))
                            {
                                player.getCapability(PlayerFoodShootSpeedBonusProvider.PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY).ifPresent(bonus ->
                                {
                                    bonus.increase();
                                });
                            }
                            if(TaotiesMunchiesConfig.foodArrowDamageBonusLimitBreaker.contains(item))
                            {
                                player.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).ifPresent(bonus->
                                {
                                    bonus.limintBreaking();
                                });
                            }
                            if(!TaotiesMunchiesConfig.whiteList || TaotiesMunchiesConfig.foodArrowDamageBonus.contains(item))
                            {
                                player.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).ifPresent(bonus ->
                                {
                                    bonus.increase();
                                });
                            }
                            var mapList = AttributeConfig.attributeMapList;
                            for(int i=0;i<mapList.size();i=i+1){
                                var map = mapList.get(i);
                                Set<Item> limitBreakingFood = (Set<Item>) map.get("LimitBreakingFood");
                                if(limitBreakingFood.contains(item)){
                                    int finalI = i;
                                    player.getCapability(PlayerFoodAttributeBonusProvider.PLAYER_FOOD_ATTRIBUTE_BONUS_CAPABILITY).ifPresent(bonus->
                                    {
                                        bonus.limintBreaking(finalI);
                                        PlayerFoodAttributeBonusHandler.updateTaotieAttributeModifier(player,finalI,bonus.getActual(finalI));
                                    });
                                }
                                Set<Item> food = (Set<Item>) map.get("Food");
                                if(!TaotiesMunchiesConfig.whiteList ||food.contains(item)){
                                    int finalI = i;
                                    player.getCapability(PlayerFoodAttributeBonusProvider.PLAYER_FOOD_ATTRIBUTE_BONUS_CAPABILITY).ifPresent(bonus->
                                    {
                                        bonus.increase(finalI);
                                        PlayerFoodAttributeBonusHandler.updateTaotieAttributeModifier(player,finalI,bonus.getActual(finalI));
                                    });
                                }
                            }
                        }
                    });
                }

            }
        }
    }
    @SubscribeEvent
    public static void playerFoodBonusClone(PlayerEvent.Clone event)
    {
        Player origin = event.getOriginal();
        if(!origin.level().isClientSide)
        {
            origin.reviveCaps();
            Player newPlayer = event.getEntity();

            var original = origin.getCapability(PlayerFoodAttributeBonusProvider.PLAYER_FOOD_ATTRIBUTE_BONUS_CAPABILITY).orElseThrow(RuntimeException::new);

            var newInstance = newPlayer.getCapability(PlayerFoodAttributeBonusProvider.PLAYER_FOOD_ATTRIBUTE_BONUS_CAPABILITY).orElseThrow(RuntimeException::new);
            newInstance.clone(original);
            newPlayer.getCapability(PlayerFoodAttributeBonusProvider.PLAYER_FOOD_ATTRIBUTE_BONUS_CAPABILITY).ifPresent(bonus->
            {
                for (int i=0;i<bonus.getSize();i++)
                {
                    var mul = bonus.getActual(i);
                    PlayerFoodAttributeBonusHandler.updateTaotieAttributeModifier(newPlayer,i,mul);
                }
            });

            newPlayer.getCapability(PlayerFoodResistanceBonusProvider.PLAYER_FOOD_RESISTANCE_BONUS_CAPABILITY).orElseThrow(RuntimeException::new).clone(origin.getCapability(PlayerFoodResistanceBonusProvider.PLAYER_FOOD_RESISTANCE_BONUS_CAPABILITY).orElseThrow(RuntimeException::new));
            newPlayer.getCapability(PlayerFoodHealingBonusProvider.PLAYER_FOOD_HEALING_BONUS_CAPABILITY).orElseThrow(RuntimeException::new).clone(origin.getCapability(PlayerFoodHealingBonusProvider.PLAYER_FOOD_HEALING_BONUS_CAPABILITY).orElseThrow(RuntimeException::new));
            newPlayer.getCapability(PlayerFoodShootSpeedBonusProvider.PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY).orElseThrow(RuntimeException::new).clone(origin.getCapability(PlayerFoodShootSpeedBonusProvider.PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY).orElseThrow(RuntimeException::new));
            newPlayer.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).orElseThrow(RuntimeException::new).clone(origin.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).orElseThrow(RuntimeException::new));
            newPlayer.getCapability(TaotiePlayerFoodListProvider.PLAYER_FOODL_LIST_CAPABILITY).orElseThrow(RuntimeException::new).clone(origin.getCapability(TaotiePlayerFoodListProvider.PLAYER_FOODL_LIST_CAPABILITY).orElseThrow(RuntimeException::new));

            origin.invalidateCaps();


        }
    }



}

package com.Cicadellidea.taotiesmunchies.Command;


import com.Cicadellidea.taotiesmunchies.Capabilites.*;
import com.Cicadellidea.taotiesmunchies.TaotiesMunchies;
import com.Cicadellidea.taotiesmunchies.config.AttributeConfig;
import com.Cicadellidea.taotiesmunchies.tracker.PlayerFoodAttributeBonusHandler;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = TaotiesMunchies.MODID)
public class TaotieCommand {

    @SubscribeEvent
    public static void registerChecker(RegisterCommandsEvent event)
    {
        event.getDispatcher().register(Commands.literal("TaotieAbilityCheck").executes(commandContext ->
        {
            Player player = commandContext.getSource().getPlayer();
            player.getCapability(PlayerFoodAttributeBonusProvider.PLAYER_FOOD_ATTRIBUTE_BONUS_CAPABILITY).ifPresent(bonus ->
            {
                for (int i=0;i<bonus.getSize();i++){
                    int finalI = i;
                    commandContext.getSource().sendSuccess(()->Component.literal((String) AttributeConfig.attributeMapList.get(finalI).get("BonusName") + String.format("%.3f",bonus.getActual(finalI))+"/"+String.format("%.3f",bonus.getMax(finalI))),false);

                }
            });
            player.getCapability(PlayerFoodResistanceBonusProvider.PLAYER_FOOD_RESISTANCE_BONUS_CAPABILITY).ifPresent(bonus -> {commandContext.getSource().sendSuccess(()->Component.literal("Resistance:"+String.format("%.3f",bonus.getActual())+"/"+String.format("%.3f",bonus.maxbonus)),false);});
            player.getCapability(PlayerFoodHealingBonusProvider.PLAYER_FOOD_HEALING_BONUS_CAPABILITY).ifPresent(bonus -> {commandContext.getSource().sendSuccess(()->Component.literal("Healing:"+String.format("%.3f",bonus.getActual())+"/"+String.format("%.3f",bonus.maxbonus)),false);});
            player.getCapability(PlayerFoodShootSpeedBonusProvider.PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY).ifPresent(bonus -> {commandContext.getSource().sendSuccess(()->Component.literal("Shoot speed:"+String.format("%.3f",bonus.getActual())+"/"+String.format("%.3f",bonus.maxbonus)),false);});
            player.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).ifPresent(bonus -> {commandContext.getSource().sendSuccess(()->Component.literal("Arrow damage:"+String.format("%.3f",bonus.getActual())+"/"+String.format("%.3f",bonus.maxbonus)),false);});

            return 0;

        }));
    }

    @SubscribeEvent
    public static void registerClearer(RegisterCommandsEvent event)
    {
        event.getDispatcher().register(Commands.literal("TaotieAbilityReset").requires(p->{
            return p.hasPermission(2);
        }).executes(commandContext ->
        {
            Player player = commandContext.getSource().getPlayer();

            player.getCapability(PlayerFoodAttributeBonusProvider.PLAYER_FOOD_ATTRIBUTE_BONUS_CAPABILITY).ifPresent(PlayerFoodAttributeBonus::reset);

            PlayerFoodAttributeBonusHandler.resetTaotieAttributeModifier(player);

            player.getCapability(PlayerFoodResistanceBonusProvider.PLAYER_FOOD_RESISTANCE_BONUS_CAPABILITY).ifPresent(PlayerFoodResistanceBonus::reset);
            player.getCapability(PlayerFoodHealingBonusProvider.PLAYER_FOOD_HEALING_BONUS_CAPABILITY).ifPresent(PlayerFoodHealingBonus::reset);
            player.getCapability(PlayerFoodShootSpeedBonusProvider.PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY).ifPresent(PlayerFoodShootSpeedBonus::reset);
            player.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).ifPresent(PlayerFoodArrowDamageBonus::reset);
            player.getCapability(TaotiePlayerFoodListProvider.PLAYER_FOODL_LIST_CAPABILITY).ifPresent(foodlist ->
            {
                foodlist.setFoodSet(new HashSet<>());

            });
            commandContext.getSource().sendSuccess(()->Component.literal("Ability cleared"),false);
            return 0;

        }));

    }

    @SubscribeEvent
    public static void registerDebugCommand(RegisterCommandsEvent event)
    {
        event.getDispatcher().register(Commands.literal("TaotieFoodListLog").requires(p->{
            return p.hasPermission(2);
        }).executes(commandContext -> {
            Objects.requireNonNull(commandContext.getSource().getPlayer()).getCapability(TaotiePlayerFoodListProvider.PLAYER_FOODL_LIST_CAPABILITY).ifPresent(list ->{
                TaotiesMunchies.LOGGER.info(String.valueOf(list.getFoodSet()));

            });
            return 0;
        }));
    }

    @SubscribeEvent
    public static void registerMaximize(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("TaotieAbilityMaximize").requires(p -> {
            return p.hasPermission(2);
        }).executes(commandContext ->
        {
            Player player = commandContext.getSource().getPlayer();
            player.getCapability(PlayerFoodAttributeBonusProvider.PLAYER_FOOD_ATTRIBUTE_BONUS_CAPABILITY).ifPresent(playerFoodAttributeBonus -> {
                for(int i=0;i<playerFoodAttributeBonus.getSize();i++){
                    playerFoodAttributeBonus.bonusList[i].replace("currentValue", 1000.0);
                    PlayerFoodAttributeBonusHandler.updateTaotieAttributeModifier(player,i,playerFoodAttributeBonus.getActual(i));
                }
            });

            player.getCapability(PlayerFoodResistanceBonusProvider.PLAYER_FOOD_RESISTANCE_BONUS_CAPABILITY).ifPresent(bonus -> {
                bonus.setbonus(1000);
            });
            player.getCapability(PlayerFoodHealingBonusProvider.PLAYER_FOOD_HEALING_BONUS_CAPABILITY).ifPresent(bonus -> {
                bonus.setbonus(1000);
            });
            player.getCapability(PlayerFoodShootSpeedBonusProvider.PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY).ifPresent(bonus -> {
                bonus.setbonus(1000);
            });
            player.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).ifPresent(bonus -> {
                bonus.setbonus(1000);
            });

            commandContext.getSource().sendSuccess(() -> Component.literal("Success"), false);
            return 0;

        }));
    }
}

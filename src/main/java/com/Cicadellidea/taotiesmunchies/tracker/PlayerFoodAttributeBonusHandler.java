package com.Cicadellidea.taotiesmunchies.tracker;

import com.Cicadellidea.taotiesmunchies.Capabilites.PlayerFoodAttributeBonus;
import com.Cicadellidea.taotiesmunchies.TaotiesMunchies;
import com.Cicadellidea.taotiesmunchies.config.AttributeConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.UUID;
@Mod.EventBusSubscriber(modid = TaotiesMunchies.MODID)
public class PlayerFoodAttributeBonusHandler {

    public static void updateTaotieAttributeModifier(Player player,int i,double mul)
    {

        if (!player.level().isClientSide) {
            var attributeMap = AttributeConfig.attributeMapList.get(i);

            var attribute = Objects.requireNonNull(player.getAttribute(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation((String) attributeMap.get("Attribute")))));

            AttributeModifier modifier = new AttributeModifier(
                    UUID.fromString((String) attributeMap.get("UUID")),
                    (String) attributeMap.get("BonusName")+" Gained from Trying New Foods",
                    mul,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            );

            attribute.removeModifier(modifier);
            attribute.addPermanentModifier(modifier);
        }
    }


    public static void resetTaotieAttributeModifier(Player player) {
        for(int i=0;i<AttributeConfig.attributeMapList.size();i++){
            var attributeMap = AttributeConfig.attributeMapList.get(i);
            var attribute = Objects.requireNonNull(player.getAttribute(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation((String) attributeMap.get("Attribute")))));
            AttributeModifier modifier = new AttributeModifier(
                    UUID.fromString((String) attributeMap.get("UUID")),
                    (String) attributeMap.get("BonusName")+" Gained from Trying New Foods",
                    0,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            );

            attribute.removeModifier(modifier);
            attribute.addPermanentModifier(modifier);
        }

    }
}


package com.Cicadellidea.taotiesmunchies.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapTransfer {
    public static HashMap<String,Object> transfer(HashMap<String, Object> inputMap){
        var map = inputMap;
        List<String> foodList = (List<String>) inputMap.get("Food");
        List<String> breakerFoodList = (List<String>) inputMap.get("LimitBreakingFood");


        map.replace("Food",foodList.stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet()));
        map.replace("LimitBreakingFood",breakerFoodList.stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet()));
        //map.replace("Attribute",ForgeRegistries.ATTRIBUTES.getValue((new ResourceLocation((String) map.get("Attribute")))));
        //map.replace("Effect",ForgeRegistries.MOB_EFFECTS.getValue((new ResourceLocation((String) map.get("Effect")))));

        /*
        map.put("bonus",list.get(0));
        map.put("Step",list.get(1));
        map.put("Maximum",list.get(2));
        map.put("LimitBreakingStep",list.get(3));
        map.put("Attribute", list.get(6));
        map.put("UUID", list.get(7));
        map.put("effect",list.get(8));
        map.put("level",list.get(9));

         */

        return map;
    }
}

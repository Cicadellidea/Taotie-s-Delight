package com.Cicadellidea.taotiesmunchies.config;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapBuilder {
    public static List<String> list = List.of("bonus","Step","Maximum","LimitBreakingStep","Food","LimitBreakingFood","Attribute","UUID","effect","level");
    //public HashMap<String,Object> map;
    /*
    public MapBuilder(String bonus, Attribute attribute, String uuid, MobEffect effect, int effectLevel){

        map = List.of(
                bonus,
                0.025d,
                1.0d,
                0.025d,
                List.of("minecraft:carrot","minecraft:apple", "minecraft:cookie"),
                List.of("minecraft:carrot"),

                attribute,
                uuid,
                effect,
                effectLevel);



        map.put("bonus",bonus);
        map.put("Step",0.025d);
        map.put("Maximum",1.0d);
        map.put("LimitBreakingStep",0.025d);
        map.put("Food",List.of("minecraft:carrot","minecraft:apple", "minecraft:cookie"));
        map.put("LimitBreakingFood",List.of("minecraft:carrot"));
        map.put("Attribute", attribute);
        map.put("UUID", uuid);
        map.put("effect",effect);
        map.put("level",effectLevel);



    }

    public MapBuilder(String bonus, Attribute attribute, String uuid){


        map = List.of(bonus,
                0.025d,
                1.0d,
                0.025d,
                List.of("minecraft:carrot","minecraft:apple", "minecraft:cookie"),
                List.of("minecraft:carrot"),
                attribute,
                uuid,
                0,
                0);




        map.put("bonus",bonus);
        map.put("Step",0.025d);
        map.put("Maximum",1.0d);
        map.put("LimitBreakingStep",0.025d);
        map.put("Food",List.of("minecraft:carrot","minecraft:apple", "minecraft:cookie"));
        map.put("LimitBreakingFood",List.of("minecraft:carrot"));
        map.put("Attribute", attribute);
        map.put("UUID", uuid);
        map.put("effect",null);
        map.put("level",null);



    }


     */

    public static HashMap<String, Object> getMap(String bonus, Attribute attribute, String uuid, MobEffect effect, int effectLevel){
        HashMap<String,Object> map = new HashMap<>();
        map.put("BonusName",bonus);
        map.put("Step",0.025d);
        map.put("Maximum",1.0d);
        map.put("LimitBreakingStep",0.1d);
        map.put("Food",List.of("minecraft:carrot","minecraft:apple", "minecraft:cookie"));
        map.put("LimitBreakingFood",List.of("minecraft:carrot"));
        map.put("Attribute", ForgeRegistries.ATTRIBUTES.getKey(attribute).toString());
        map.put("UUID", uuid);
        map.put("Effect", ForgeRegistries.MOB_EFFECTS.getKey(effect).toString());
        map.put("EffectLevel",effectLevel);

        return map;
    }
    public static HashMap<String, Object> getMap(String bonus, Attribute attribute, String uuid){
        HashMap<String,Object> map = new HashMap<>();
        map.put("BonusName",bonus);
        map.put("Step",0.025d);
        map.put("Maximum",1.0d);
        map.put("LimitBreakingStep",0.1d);
        map.put("Food",List.of("minecraft:carrot","minecraft:apple", "minecraft:cookie"));
        map.put("LimitBreakingFood",List.of("minecraft:carrot"));
        map.put("Attribute", ForgeRegistries.ATTRIBUTES.getKey(attribute).toString());
        map.put("UUID", uuid);
        map.put("Effect",null);
        map.put("EffectLevel",null);

        return map;
    }
}

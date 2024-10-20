package com.Cicadellidea.taotiesmunchies.config;

import com.Cicadellidea.taotiesmunchies.TaotiesMunchies;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = TaotiesMunchies.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class TaotiesMunchiesConfig {
    private static ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    private static ForgeConfigSpec.BooleanValue WHITE_LIST = BUILDER.define("WhiteList",false);
    private static ForgeConfigSpec.BooleanValue EFFECT_BONUS = BUILDER.define("GetTemporalEffectsAfterEating",false);
    private static ForgeConfigSpec.IntValue DURATION = BUILDER.defineInRange("EffectDuration",2400,0,9999999);


    private static ForgeConfigSpec.DoubleValue HEALING_BONUS_STEP = BUILDER.defineInRange("HealingBonusStep",0.025,0,1000);
    private static ForgeConfigSpec.DoubleValue HEALING_BONUS_MAX= BUILDER.defineInRange("HealingBonusMaximum",1d,0,1000);
    private static ForgeConfigSpec.DoubleValue HEALING_BONUS_LIMIT_BREAKING_STEP = BUILDER.defineInRange("HealingBonusLimitBreakingStep",0.1,0,1000);

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_HEALING_BONUS = BUILDER.defineListAllowEmpty("HealingBonusFood", List.of("minecraft:carrot","minecraft:apple","minecraft:cookie"), TaotiesMunchiesConfig::allow);
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_HEALING_BONUS_LIMIT_BREAKER = BUILDER.defineListAllowEmpty("HealingBonusLimitBreakingFood", List.of("minecraft:carrot"), TaotiesMunchiesConfig::allow);

    public static double stepHealingBonus, maxHealingBonus,breakingHealingBonusLimit;

    public static Set<Item> foodHealingBonus, foodHealingBonusLimitBreaker;

    private static ForgeConfigSpec.DoubleValue RESISTANCE_BONUS_STEP = BUILDER.defineInRange("ResistanceBonusStep",0.025,0,1000);
    private static ForgeConfigSpec.DoubleValue RESISTANCE_BONUS_MAX= BUILDER.defineInRange("ResistanceBonusMaximum",1d,0,1000);
    private static ForgeConfigSpec.DoubleValue RESISTANCE_BONUS_LIMIT_BREAKING_STEP = BUILDER.defineInRange("ResistanceBonusLimitBreakingStep",0.1,0,1000);

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_RESISTANCE_BONUS = BUILDER.defineListAllowEmpty("ResistanceBonusFood", List.of("minecraft:carrot","minecraft:apple","minecraft:cookie"), TaotiesMunchiesConfig::allow);
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_RESISTANCE_BONUS_LIMIT_BREAKER = BUILDER.defineListAllowEmpty("ResistanceBonusLimitBreakingFood", List.of("minecraft:carrot"), TaotiesMunchiesConfig::allow);

    public static double stepResistanceBonus, maxResistanceBonus,breakingResistanceBonusLimit;

    public static Set<Item> foodResistanceBonus, foodResistanceBonusLimitBreaker;

    private static ForgeConfigSpec.DoubleValue SHOOT_SPEED_BONUS_STEP = BUILDER.defineInRange("ShootSpeedBonusStep",0.025,0,1000);
    private static ForgeConfigSpec.DoubleValue SHOOT_SPEED_BONUS_MAX= BUILDER.defineInRange("ShootSpeedBonusMaximum",1d,0,1000);
    private static ForgeConfigSpec.DoubleValue SHOOT_SPEED_BONUS_LIMIT_BREAKING_STEP = BUILDER.defineInRange("ShootSpeedBonusLimitBreakingStep",0.1,0,1000);

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_SHOOT_SPEED_BONUS = BUILDER.defineListAllowEmpty("ShootSpeedBonusFood", List.of("minecraft:carrot","minecraft:apple","minecraft:cookie"), TaotiesMunchiesConfig::allow);
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_SHOOT_SPEED_BONUS_LIMIT_BREAKER = BUILDER.defineListAllowEmpty("ShootSpeedBonusLimitBreakingFood", List.of("minecraft:carrot"), TaotiesMunchiesConfig::allow);

    public static double stepShootSpeedBonus, maxShootSpeedBonus,breakingShootSpeedBonusLimit;

    public static Set<Item> foodShootSpeedBonus, foodShootSpeedBonusLimitBreaker;

    private static ForgeConfigSpec.DoubleValue ARROW_DAMAGE_BONUS_STEP = BUILDER.defineInRange("ArrowDamageBonusStep",0.025,0,1000);
    private static ForgeConfigSpec.DoubleValue ARROW_DAMAGE_BONUS_MAX= BUILDER.defineInRange("ArrowDamageBonusMaximum",1d,0,1000);
    private static ForgeConfigSpec.DoubleValue ARROW_DAMAGE_BONUS_LIMIT_BREAKING_STEP = BUILDER.defineInRange("ArrowDamageBonusLimitBreakingStep",0.1,0,1000);

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_ARROW_DAMAGE_BONUS = BUILDER.defineListAllowEmpty("ArrowDamageBonusFood", List.of("minecraft:carrot","minecraft:apple","minecraft:cookie"), TaotiesMunchiesConfig::allow);
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_ARROW_DAMAGE_BONUS_LIMIT_BREAKER = BUILDER.defineListAllowEmpty("ArrowDamageBonusLimitBreakingFood", List.of("minecraft:carrot"), TaotiesMunchiesConfig::allow);

    public static double stepArrowDamageBonus, maxArrowDamageBonus,breakingArrowDamageBonusLimit;

    public static Set<Item> foodArrowDamageBonus, foodArrowDamageBonusLimitBreaker;



    private static boolean allow(Object obj)
    {
        return ForgeRegistries.ITEMS.containsKey(new ResourceLocation((String) obj));
    }





    //public static ForgeConfigSpec.ConfigValue<List<? extends String>> BONUS_LIST = BUILDER.defineListAllowEmpty("BonusList",List.of("DamageBonus","AttackSpeedBonus","SpeedBonus"),e->{return true;});
    /*
    public static ForgeConfigSpec.ConfigValue<List<? extends List<Object>>> ATTRIBUTE_BONUS_LIST = BUILDER.defineListAllowEmpty("Attributes",
            List.of(new MapBuilder("SpeedBonus", Attributes.MOVEMENT_SPEED,"bf6145e0-17bd-fe3c-3004-b036947ebf19", MobEffects.MOVEMENT_SPEED,1).getMap(),
                    new MapBuilder("AttackSpeedBonus", Attributes.ATTACK_SPEED,"654978f2-b858-5dc5-932f-4108b0e2ca3c", MobEffects.DIG_SPEED,1).getMap(),
                    new MapBuilder("DamageBonus",Attributes.ATTACK_DAMAGE,"bdfd9373-ba61-86f8-88b4-8fc65fdce578", MobEffects.DAMAGE_BOOST,0).getMap()
            ), e->{return true;});

     */



    public static List<HashMap<String,Object>> attributeMapList;


    public static ForgeConfigSpec SPEC = BUILDER.build();


    public static boolean whiteList;
    public static boolean effectBonus;
    public static int duration;


    public static HashMap<String,Map<String,Object>> attributeMapMap;


    private static MapTransfer mapTransfer = new MapTransfer();

    private static void getConfig()
    {
        whiteList = WHITE_LIST.get();
        effectBonus = EFFECT_BONUS.get();
        duration = DURATION.get();


/*
        attributeMapList = ATTRIBUTE_BONUS_LIST.get().stream().map(element->{
            return mapTransfer.transfer(element);
        }).toList();


        bonusList = ATTRIBUTE_BONUS_LIST.get().stream().map(element->{
            return (String) element.get(0);
        }).toList();

        attributeMapMap = new HashMap<String,Map<String,Object>>();
        for(int i=0;i<attributeMapList.size();i=i+1){
            attributeMapMap.put((String) attributeMapList.get(i).get("bonus"),attributeMapList.get(i));
        }

 */



        //net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED


        stepResistanceBonus = RESISTANCE_BONUS_STEP.get();
        maxResistanceBonus = RESISTANCE_BONUS_MAX.get();
        breakingResistanceBonusLimit = RESISTANCE_BONUS_LIMIT_BREAKING_STEP.get();
        foodResistanceBonus = FOOD_RESISTANCE_BONUS.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());
        foodResistanceBonusLimitBreaker = FOOD_RESISTANCE_BONUS_LIMIT_BREAKER.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());

        stepHealingBonus = HEALING_BONUS_STEP.get();
        maxHealingBonus = HEALING_BONUS_MAX.get();
        breakingHealingBonusLimit = HEALING_BONUS_LIMIT_BREAKING_STEP.get();
        foodHealingBonus = FOOD_HEALING_BONUS.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());
        foodHealingBonusLimitBreaker = FOOD_HEALING_BONUS_LIMIT_BREAKER.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());

        stepShootSpeedBonus = SHOOT_SPEED_BONUS_STEP.get();
        maxShootSpeedBonus = SHOOT_SPEED_BONUS_MAX.get();
        breakingShootSpeedBonusLimit = SHOOT_SPEED_BONUS_LIMIT_BREAKING_STEP.get();
        foodShootSpeedBonus = FOOD_SHOOT_SPEED_BONUS.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());
        foodShootSpeedBonusLimitBreaker = FOOD_SHOOT_SPEED_BONUS_LIMIT_BREAKER.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());

        stepArrowDamageBonus = ARROW_DAMAGE_BONUS_STEP.get();
        maxArrowDamageBonus = ARROW_DAMAGE_BONUS_MAX.get();
        breakingArrowDamageBonusLimit = ARROW_DAMAGE_BONUS_LIMIT_BREAKING_STEP.get();
        foodArrowDamageBonus = FOOD_ARROW_DAMAGE_BONUS.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());
        foodArrowDamageBonusLimitBreaker = FOOD_ARROW_DAMAGE_BONUS_LIMIT_BREAKER.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());


    }





    @SubscribeEvent
    public static void onSetup(FMLCommonSetupEvent event)
    {
        getConfig();
    }
    @SubscribeEvent
    public static void onload(ModConfigEvent.Reloading event)
    {
        getConfig();
    }
}

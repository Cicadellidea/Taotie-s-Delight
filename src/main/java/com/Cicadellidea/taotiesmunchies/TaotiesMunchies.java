package com.Cicadellidea.taotiesmunchies;

import com.Cicadellidea.taotiesmunchies.Capabilites.*;
import com.Cicadellidea.taotiesmunchies.config.AttributeConfig;
import com.Cicadellidea.taotiesmunchies.config.TaotiesMunchiesConfig;
import com.Cicadellidea.taotiesmunchies.effect.DMGUp;
import com.Cicadellidea.taotiesmunchies.effect.ShootSpeedUp;
import com.Cicadellidea.taotiesmunchies.tracker.ArrowTracker;
import com.Cicadellidea.taotiesmunchies.tracker.TaotieFoodEffectTracker;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.io.IOException;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TaotiesMunchies.MODID)
public class TaotiesMunchies
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "taotiesmunchies";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace

    public static final DeferredRegister<MobEffect> EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);

    public static final RegistryObject<ShootSpeedUp> SHOOT_SPEED_UP = EFFECT.register("shootspeed_up", ShootSpeedUp::new);
    public static final RegistryObject<DMGUp> DMG_UP = EFFECT.register("dmg_up", DMGUp::new);
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        /*
        if (event.getObject() instanceof AbstractArrow arrow)
        {
            if(!arrow.getCapability(ArrowFoodAcceleratedProvider.ARROW_FOOD_ACCELERATED_CAPABILITY).isPresent())
            {
                event.addCapability(new ResourceLocation(MODID,"arrowfoodaccelerated"),new ArrowFoodAcceleratedProvider());
            }
        }
        if (event.getObject() instanceof Arrow arrow)
        {
            if(!arrow.getCapability(ArrowSpeedRecorderProvider.ARROW_SPEED_RECORDER_CAPABILITY).isPresent())
            {
                event.addCapability(new ResourceLocation(MODID,"taotiearrowspeedrecorder"),new ArrowSpeedRecorderProvider());
            }
        }

         */


        if(event.getObject() instanceof Player player)
        {
            if(!player.getCapability(TaotiePlayerFoodListProvider.PLAYER_FOODL_LIST_CAPABILITY).isPresent())
            {

                event.addCapability(new ResourceLocation(MODID,"taotiefoodlist"),new TaotiePlayerFoodListProvider());
            }

            if(!player.getCapability(PlayerFoodAttributeBonusProvider.PLAYER_FOOD_ATTRIBUTE_BONUS_CAPABILITY).isPresent())
            {
                event.addCapability(new ResourceLocation(MODID,"attributebonus"),new PlayerFoodAttributeBonusProvider());
            }

            if(!player.getCapability(PlayerFoodResistanceBonusProvider.PLAYER_FOOD_RESISTANCE_BONUS_CAPABILITY).isPresent())
            {
                event.addCapability(new ResourceLocation(MODID,"foodresistancebonus"),new PlayerFoodResistanceBonusProvider());
            }
            if(!player.getCapability(PlayerFoodHealingBonusProvider.PLAYER_FOOD_HEALING_BONUS_CAPABILITY).isPresent())
            {
                event.addCapability(new ResourceLocation(MODID,"foodhealingbonus"),new PlayerFoodHealingBonusProvider());
            }
            if(!player.getCapability(PlayerFoodShootSpeedBonusProvider.PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY).isPresent())
            {
                event.addCapability(new ResourceLocation(MODID,"foodshootspeedebonus"),new PlayerFoodShootSpeedBonusProvider());
            }
            if(!player.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).isPresent())
            {
                event.addCapability(new ResourceLocation(MODID,"foodarrowdamagebonus"),new PlayerFoodArrowDamageBonusProvider());
            }


        }





    }

    public TaotiesMunchies()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        EFFECT.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ArrowTracker());
        MinecraftForge.EVENT_BUS.register(new TaotieFoodEffectTracker());




        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TaotiesMunchiesConfig.SPEC);
        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class,this::attachCapability);
        


    }






    @SubscribeEvent
    public static void registerCapability(RegisterCapabilitiesEvent event)
    {
        event.register(TaotiePlayerFoodListProvider.class);
        //event.register(PlayerFoodAttributeBonusProvider.class);

        event.register(PlayerFoodResistanceBonusProvider.class);
        event.register(PlayerFoodHealingBonusProvider.class);
        event.register(PlayerFoodShootSpeedBonusProvider.class);
        event.register(PlayerFoodArrowDamageBonusProvider.class);

    }






    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent

}

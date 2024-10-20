package com.Cicadellidea.taotiesmunchies.tracker;

import com.Cicadellidea.taotiesmunchies.Capabilites.*;
import com.Cicadellidea.taotiesmunchies.TaotiesMunchies;
import com.Cicadellidea.taotiesmunchies.effect.ShootSpeedUp;
import com.ibm.icu.text.CaseMap;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class ArrowTracker {
    @SubscribeEvent
    public void onArrowSpawn(EntityEvent event)
    {
        if(event.getEntity() instanceof AbstractArrow arrow) {
            if(arrow.tickCount == 0) {
                if(true) {
                    if((arrow.getOwner() instanceof LivingEntity owner)) {
                        final double[] mul = {1};
                        MobEffect effect = TaotiesMunchies.SHOOT_SPEED_UP.get();
                        if(owner.hasEffect(effect)) {
                            mul[0] = mul[0] + (0.3 * (owner.getEffect(effect).getAmplifier() + 1));
                        }
                        if(owner instanceof Player) {
                            owner.getCapability(PlayerFoodShootSpeedBonusProvider.PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY).ifPresent(shootSpeedBonus ->
                            {
                                mul[0] = shootSpeedBonus.getActual() + mul[0];
                            });
                        }
                        arrow.setDeltaMovement(arrow.getDeltaMovement().multiply(mul[0], mul[0], mul[0]));

                    }
                }
            }
        }
    }


    @SubscribeEvent
    public void projectileHiting(LivingHurtEvent event){
        if(event.getSource().getDirectEntity() instanceof AbstractArrow arrow)
        {
            if(arrow.getOwner() instanceof  LivingEntity owner)
            {
                if(!arrow.level().isClientSide)
                {
                    final double[] mul = {1};
                    MobEffect effect = TaotiesMunchies.DMG_UP.get();
                    if (owner.hasEffect(effect)) {
                        mul[0] = mul[0] + (0.3*(owner.getEffect(effect).getAmplifier()+1));
                    }
                    if(owner instanceof  Player){
                        owner.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).ifPresent(bonus->
                        {
                            mul[0] += bonus.getActual();
                        });
                    }
                    event.setAmount((float) (event.getAmount()*mul[0]));
                }
            }
        }
    }
}

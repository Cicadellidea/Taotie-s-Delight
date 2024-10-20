package com.Cicadellidea.taotiesmunchies.Capabilites;

import com.Cicadellidea.taotiesmunchies.config.TaotiesMunchiesConfig;
import net.minecraft.nbt.CompoundTag;

public class PlayerFoodArrowDamageBonus {

    public double foodArrowDamageBonus;
    public double limitBreakingStep;
    public double maxbonus;
    public double step;

    public PlayerFoodArrowDamageBonus()
    {
        this.foodArrowDamageBonus = 0;
        this.maxbonus = TaotiesMunchiesConfig.maxArrowDamageBonus;
        this.step = TaotiesMunchiesConfig.stepArrowDamageBonus;
        this.limitBreakingStep = TaotiesMunchiesConfig.breakingArrowDamageBonusLimit;
    }

    public void setbonus(double value)
    {
        foodArrowDamageBonus = value;
    }

    public double limintBreaking()
    {
        this.maxbonus = this.maxbonus + this.limitBreakingStep;
        return Math.min(foodArrowDamageBonus, maxbonus);
    }

    public double increase()
    {

        foodArrowDamageBonus = foodArrowDamageBonus + step;
        return Math.min(foodArrowDamageBonus, maxbonus);
    }
    public void reset()
    {
        this.foodArrowDamageBonus = 0;
        this.maxbonus = TaotiesMunchiesConfig.maxArrowDamageBonus;
        this.step = TaotiesMunchiesConfig.stepArrowDamageBonus;
        this.limitBreakingStep = TaotiesMunchiesConfig.breakingArrowDamageBonusLimit;
    }
    

    public double getActual()
    {
        return Math.min(foodArrowDamageBonus, maxbonus);
    }

    public void clone(PlayerFoodArrowDamageBonus bonus)
    {
        this.foodArrowDamageBonus = bonus.foodArrowDamageBonus;
        this.maxbonus = bonus.maxbonus;
    }

    public void saveNBT(CompoundTag tag)
    {
        tag.putDouble("FoodArrowDamageBonus", foodArrowDamageBonus);
    }

    public void readNBT(CompoundTag tag)
    {
        foodArrowDamageBonus = tag.getDouble("FoodArrowDamageBonus");
    }
}

package com.Cicadellidea.taotiesmunchies.Capabilites;

import com.Cicadellidea.taotiesmunchies.config.TaotiesMunchiesConfig;
import net.minecraft.nbt.CompoundTag;

public class PlayerFoodHealingBonus {

    public double foodHealingBonus;
    public double limitBreakingStep;
    public double maxbonus;
    public double step;

    public PlayerFoodHealingBonus()
    {
        this.foodHealingBonus = 0;
        this.maxbonus = TaotiesMunchiesConfig.maxHealingBonus;
        this.step = TaotiesMunchiesConfig.stepHealingBonus;
        this.limitBreakingStep = TaotiesMunchiesConfig.breakingHealingBonusLimit;
    }
    public void setbonus(double value)
    {
        foodHealingBonus = value;
    }

    public double limintBreaking()
    {
        this.maxbonus = this.maxbonus + this.limitBreakingStep;
        return Math.min(foodHealingBonus, maxbonus);
    }

    public double increase()
    {

        foodHealingBonus = foodHealingBonus + step;
        return Math.min(foodHealingBonus, maxbonus);
    }
    public void reset()
    {
        this.foodHealingBonus = 0;
        this.maxbonus = TaotiesMunchiesConfig.maxHealingBonus;
        this.step = TaotiesMunchiesConfig.stepHealingBonus;
        this.limitBreakingStep = TaotiesMunchiesConfig.breakingHealingBonusLimit;
    }

    public double getActual()
    {
        return Math.min(foodHealingBonus, maxbonus);
    }

    public void clone(PlayerFoodHealingBonus bonus)
    {
        this.foodHealingBonus = bonus.foodHealingBonus;
        this.maxbonus = bonus.maxbonus;
    }

    public void saveNBT(CompoundTag tag)
    {
        tag.putDouble("FoodHealingBonus", foodHealingBonus);
        tag.putDouble("maxHealingBonus",maxbonus);
    }

    public void readNBT(CompoundTag tag)
    {
        foodHealingBonus = tag.getDouble("FoodHealingBonus");
        maxbonus = tag.getDouble("maxHealingBonus");
    }
}

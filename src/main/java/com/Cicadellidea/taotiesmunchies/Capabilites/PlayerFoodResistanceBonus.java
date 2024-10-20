package com.Cicadellidea.taotiesmunchies.Capabilites;

import com.Cicadellidea.taotiesmunchies.config.TaotiesMunchiesConfig;
import net.minecraft.nbt.CompoundTag;

public class PlayerFoodResistanceBonus {

    public double foodResistanceBonus;
    public double limitBreakingStep;
    public double maxbonus;
    public double step;

    public PlayerFoodResistanceBonus()
    {
        this.foodResistanceBonus = 0;
        this.maxbonus = TaotiesMunchiesConfig.maxResistanceBonus;
        this.step = TaotiesMunchiesConfig.stepResistanceBonus;
        this.limitBreakingStep = TaotiesMunchiesConfig.breakingResistanceBonusLimit;
    }

    public void setbonus(double value)
    {
        foodResistanceBonus = value;
    }

    public double limintBreaking()
    {
        this.maxbonus = this.maxbonus + this.limitBreakingStep;
        return Math.min(foodResistanceBonus, maxbonus);
    }

    public double increase()
    {

        foodResistanceBonus = foodResistanceBonus + step;
        return Math.min(foodResistanceBonus, maxbonus);
    }
    public void reset()
    {
        this.foodResistanceBonus = 0;
        this.maxbonus = TaotiesMunchiesConfig.maxResistanceBonus;
        this.step = TaotiesMunchiesConfig.stepResistanceBonus;
        this.limitBreakingStep = TaotiesMunchiesConfig.breakingResistanceBonusLimit;
    }

    public double getActual()
    {
        return Math.min(foodResistanceBonus, maxbonus);
    }

    public void clone(PlayerFoodResistanceBonus bonus)
    {
        this.foodResistanceBonus = bonus.foodResistanceBonus;
        this.maxbonus = bonus.maxbonus;
    }

    public void saveNBT(CompoundTag tag)
    {
        tag.putDouble("FoodResistanceBonus", foodResistanceBonus);
        tag.putDouble("maxResistanceBonus",maxbonus);
    }

    public void readNBT(CompoundTag tag)
    {
        foodResistanceBonus = tag.getDouble("FoodResistanceBonus");
        maxbonus = tag.getDouble("maxResistanceBonus");
    }
}


package com.Cicadellidea.taotiesmunchies.Capabilites;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerFoodShootSpeedBonusProvider implements ICapabilityProvider, INBTSerializable {

    private PlayerFoodShootSpeedBonus bonus;

    public PlayerFoodShootSpeedBonusProvider()
    {
        this.bonus = new PlayerFoodShootSpeedBonus();
    }

    public static final Capability<PlayerFoodShootSpeedBonus> PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerFoodShootSpeedBonus>() {});

    private final LazyOptional<PlayerFoodShootSpeedBonus> lazyOptional = LazyOptional.of(()->this.bonus);


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction)
    {
        if (capability == PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY)
        {
            return this.lazyOptional.cast();
        }
        else
        {
            return this.lazyOptional.empty();
        }
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY)
        {
            return this.lazyOptional.cast();
        }
        else
        {
            return this.lazyOptional.empty();
        }
    }

    @Override
    public Tag serializeNBT()
    {
        var tag = new CompoundTag();
        bonus.saveNBT(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(Tag tag)
    {
        bonus.readNBT((CompoundTag) tag);

    }
}


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

public class TaotiePlayerFoodListProvider implements ICapabilityProvider, INBTSerializable {

    private TaotiePlayerFoodlList foodList;

    public TaotiePlayerFoodListProvider()
    {
        this.foodList = new TaotiePlayerFoodlList();
    }

    public static final Capability<TaotiePlayerFoodlList> PLAYER_FOODL_LIST_CAPABILITY = CapabilityManager.get(new CapabilityToken<TaotiePlayerFoodlList>() {});

    private final LazyOptional<TaotiePlayerFoodlList> lazyOptional = LazyOptional.of(()->this.foodList);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_FOODL_LIST_CAPABILITY)
        {
            return this.lazyOptional.cast();
        }
        else
        {
            return this.lazyOptional.empty();
        }
    }

    @Override
    public Tag serializeNBT() {
        return this.foodList.writeNBT(new CompoundTag());
    }

    @Override
    public void deserializeNBT(Tag nbt) {
        this.foodList.readNBT((CompoundTag) nbt);

    }
}

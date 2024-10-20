package com.Cicadellidea.taotiesmunchies.Capabilites;

import com.mojang.logging.LogUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TaotiePlayerFoodlList {
    private Set<String> foodSet;
    private static final Logger LOGGER = LogUtils.getLogger();


    public TaotiePlayerFoodlList()
    {
        this.foodSet = new HashSet<>();
    }

    public Set<String> getFoodSet() {
        return foodSet;
    }

    public void setFoodSet(Set<String> foodSet) {
        this.foodSet = foodSet;
    }

    public boolean in(Item item)
    {
        String id = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).toString();
        return (this.foodSet.contains(id));
    }

    public boolean add(Item item)
    {
        String id = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).toString();
        boolean bool = !(this.foodSet.contains(id));
        if(bool)
        {
            this.foodSet.add(id);
        }
        return  bool;
    }
    public void readNBT(CompoundTag tag)
    {
        this.foodSet.clear();
        tag.getList("TaotieFoodList",Tag.TAG_STRING).stream().map(nbt->(StringTag) nbt).map(StringTag::getAsString).filter(Objects::nonNull).forEach(this.foodSet::add);
    }

    public Tag writeNBT(CompoundTag tag)
    {
        var list = new ListTag();
        this.foodSet.stream().map(StringTag::valueOf).forEach(list::add);
        tag.put("TaotieFoodList",list);
        return tag;
    }
    public void clone(TaotiePlayerFoodlList taotiePlayerFoodlList)
    {
        this.foodSet = taotiePlayerFoodlList.getFoodSet();
    }

}

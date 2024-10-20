package com.Cicadellidea.taotiesmunchies.Capabilites;

import com.Cicadellidea.taotiesmunchies.config.AttributeConfig;
import net.minecraft.nbt.CompoundTag;


import java.util.HashMap;
import java.util.List;

public class PlayerFoodAttributeBonus {

    public HashMap<String,Double>[] bonusList;

    public PlayerFoodAttributeBonus()
    {

        var mapList = AttributeConfig.attributeMapList;
        bonusList = new HashMap[mapList.size()];
        for(int i=0;i<mapList.size();i=i+1){
            var map = mapList.get(i);
            HashMap<String,Double> newMap = new HashMap<String,Double>();
            newMap.put("currentValue",0d);
            newMap.put("Maximum",(Double) map.get("Maximum"));
            bonusList[i]=(newMap);
        }
    }

    public double getActual(int i){
        var value = bonusList[i].get("currentValue");
        var max = bonusList[i].get("Maximum");
        return Math.min(value,max);
    }

    public double increase(int i){
        var value = bonusList[i].get("currentValue");
        var step = (double) AttributeConfig.attributeMapList.get(i).get("Step");
        bonusList[i].replace("currentValue",value+step);
        return this.getActual(i);
    }

    public double limintBreaking(int i)
    {
        var max = bonusList[i].get("Maximum");
        var limitBreakingStep = (double) AttributeConfig.attributeMapList.get(i).get("LimitBreakingStep");
        bonusList[i].replace("Maximum",max+limitBreakingStep);
        return this.getActual(i);
    }

    public void reset()
    {
        var mapList = AttributeConfig.attributeMapList;
        bonusList = new HashMap[mapList.size()];
        for(int i=0;i<mapList.size();i=i+1){
            var map = mapList.get(i);
            HashMap<String,Double> newMap = new HashMap<String,Double>();
            newMap.put("currentValue",0d);
            newMap.put("Maximum",(Double) map.get("Maximum"));
            bonusList[i]=(newMap);
        }
    }


    public void clone(PlayerFoodAttributeBonus bonus)
    {
        this.bonusList = bonus.bonusList;

    }

    public void saveNBT(CompoundTag tag)
    {

        var mapList = AttributeConfig.attributeMapList;
        for(int i=0;i<mapList.size();i++){
            var value = bonusList[i].get("currentValue");
            var max = bonusList[i].get("Maximum");
            tag.putDouble(mapList.get(i).get("BonusName")+"Boost",value);
            tag.putDouble(mapList.get(i).get("BonusName")+"Max",max);
        }
    }

    public void readNBT(CompoundTag tag)
    {
        var mapList = AttributeConfig.attributeMapList;
        for(int i=0;i<mapList.size();i++){
            var value = bonusList[i].get("currentValue");
            var max = bonusList[i].get("Maximum");
            bonusList[i].replace("currentValue",tag.getDouble(mapList.get(i).get("BonusName")+"Boost"));
            bonusList[i].replace("Maximum",tag.getDouble(mapList.get(i).get("BonusName")+"Max"));
        }
    }

    public int getSize(){
        return AttributeConfig.attributeMapList.size();

    }

    public double getMax(int i){
        return this.bonusList[i].get("Maximum");
    }


}

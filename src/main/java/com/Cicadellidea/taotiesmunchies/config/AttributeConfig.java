package com.Cicadellidea.taotiesmunchies.config;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.Cicadellidea.taotiesmunchies.TaotiesMunchies;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.ForgeRegistries;


public class AttributeConfig {
    public static List<HashMap<String,Object>> attributeMapList;
    public static File file = new File("config/attribute_config.json");



    public static void init() throws IOException {
        if(!file.exists()){
            /*
            attributeMaps.add(MapBuilder.getMap("SpeedBonus", "net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED","bf6145e0-17bd-fe3c-3004-b036947ebf19", "net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED",1));
            attributeMaps.add(MapBuilder.getMap("AttackSpeedBonus", "net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_SPEED","654978f2-b858-5dc5-932f-4108b0e2ca3c", "net.minecraft.world.effect.MobEffects.DIG_SPEED",1));
            attributeMaps.add(MapBuilder.getMap("DamageBonus","net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE","bdfd9373-ba61-86f8-88b4-8fc65fdce578", "net.minecraft.world.effect.MobEffects.DAMAGE_BOOST",0));

             */
            Gson gson = new Gson();
            String json0 = gson.toJson(List.of(MapBuilder.getMap("SpeedBonus", Attributes.MOVEMENT_SPEED,"bf6145e0-17bd-fe3c-3004-b036947ebf19", MobEffects.MOVEMENT_SPEED,1),
                    MapBuilder.getMap("AttackSpeedBonus", Attributes.ATTACK_SPEED,"654978f2-b858-5dc5-932f-4108b0e2ca3c"),
                    MapBuilder.getMap("DamageBonus",Attributes.ATTACK_DAMAGE,"bdfd9373-ba61-86f8-88b4-8fc65fdce578", MobEffects.DAMAGE_BOOST,0)
            ));

            var list1 = json0.split(",");
            String json = "";
            var len = list1.length;
            for(int i =0;i<len;i++){
                json = json+list1[i];
                if(i<len-1){
                    json = json+",\n";
                }
            }
            Files.write(file.toPath(), json.getBytes());

        }
        String json = (Files.readString(file.toPath()));
        Gson gson = new Gson();
        Type type = new TypeToken<List<HashMap<String,Object>>>(){}.getType();
        List<HashMap<String,Object>> inputList = gson.fromJson(json,type);
        attributeMapList = inputList.stream().map(MapTransfer::transfer).toList();





    }

}

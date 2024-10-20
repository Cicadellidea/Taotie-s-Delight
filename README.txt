现在支持自定义属性和状态效果了，对应配置文件taotie_attribute_config.json

Now you can customize attributes or effects through taotie_attribute_config.json

---------------------------------------------------------------------------------------
A mod designed to encourage trying legendary foods.

Add a food to the config file, or turn off the whitelist in the config file, so that you can get stronger after eating.(Once for each food)

You may gain extra speed, damage, attack speed, damage resistance, healing boost, arrow shoot speed, and arrow damage.

You may also add food that can break the preset limit.

You can modify the config file so that food can provide effects while eaten, related to status they provide. (Don't have to be the first time)

Two effects(Shootspeed Up/Arrow DMG Up) are added, providing 30% arrow speed/arrow base damge for each level.

if one of the attributes is x,

Damage resistance works in the form : y = y0/(1+x)

Other works in the form : y = y0*(1+x)

Notice: this mod maybe overpowered if you don't arrange your diet carefully.

Status and relating effects

Attack Damage: Strength 1
Attack Speed: Haste 2
Speed: Speed 2
Resistance: Resistance 1
Healing: Regeneration 2
Shootspeed: Shootspeed Up 1
Arrow Damge: Arrow DMG UP 1

Commands:

/TaotieAbilityReset : reset your whole progress

/TaotieAbilityCheck : check your ability

Feel  free to use this mod.

---------------------------------------------------------------------------------------
本模组允许玩家通过食物提高属性值（每个食物限一次），以此鼓励玩家尝试新食物。

可能的属性值包括攻击，攻速，移速，伤害减免，生命回复，箭矢速度，箭矢伤害。

配置文件中可以更改属性提升的幅度，各属性允许的食物，是否开启白名单。

也可以将食物加入指定的列表来允许它们打破设定的上限。

指令

/TaotieAbilityReset 重置进度

/TaotieAbilityCheck 查看现有增益

v0.1.3开始默认关闭白名单，该模组可以在不修改配置文件的情况下游玩。

*但自带的数值可能过高

v0.2.1开始加入了2种状态效果，弹速上升和箭矢伤害上升，并且可以通过修改配置文件来允许玩家在食用食物后获得2分钟的对应效果（无需首次食用）具体为：

攻击：力量1

攻速：急迫2

速度：迅捷2

护甲：抗性提升1

回复：生命恢复2

弹速：弹速上升1(+30%箭矢速度)

箭矢伤害：箭矢伤害上升1(+30%箭矢伤害)


package com.disableend;

import net.fabricmc.api.ModInitializer;

//? if >=1.21.11 {
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.gamerules.GameRule;
//?} else {
/*import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;
*///?}

public class DisableEnd implements ModInitializer {
    public static final String MOD_ID = "disable_end";

    //? if >=1.21.11 {
    public static GameRule<Boolean> DISABLE_END;
    //?} else {
    /*public static GameRules.Key<GameRules.BooleanValue> DISABLE_END;
    *///?}

    @Override
    public void onInitialize() {
        //? if >=1.21.11 {
        // Default namespace until MC-303846 lets /gamerule accept namespaced ids
        DISABLE_END = GameRuleBuilder.forBoolean(false)
                .buildAndRegister(Identifier.withDefaultNamespace("disable_end"));
        //?} else {
        /*DISABLE_END = GameRuleRegistry.register("disable_end", GameRules.Category.MISC,
                GameRuleFactory.createBooleanRule(false));
        *///?}
    }
}

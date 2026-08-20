package com.disableend.mixin;

import com.disableend.DisableEnd;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
//? if >=1.21.5 {
import net.minecraft.world.entity.InsideBlockEffectApplier;
//?}
//? if >=1.21.11 {
import net.minecraft.server.level.ServerLevel;
//?} else {
/*import net.minecraft.server.MinecraftServer;
*///?}
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndPortalBlock.class)
public class DisableEndPortal {

    //? if >=1.21.10 {
    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    private void noEnd(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise, CallbackInfo ci) {
        if (disableend$isDisabled(level)) {
            ci.cancel();
        }
    }
    //?}
    //? if >=1.21.5 && <1.21.10 {
    /*@Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    private void noEnd(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, CallbackInfo ci) {
        if (disableend$isDisabled(level)) {
            ci.cancel();
        }
    }
    *///?}
    //? if <1.21.5 {
    /*@Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    private void noEnd(BlockState state, Level level, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (disableend$isDisabled(level)) {
            ci.cancel();
        }
    }
    *///?}

    @Unique
    private static boolean disableend$isDisabled(Level level) {
        //? if >=1.21.11 {
        return level instanceof ServerLevel serverLevel && serverLevel.getGameRules().get(DisableEnd.DISABLE_END);
        //?} else {
        /*MinecraftServer server = level.getServer();
        return server != null && server.getGameRules().getBoolean(DisableEnd.DISABLE_END);
        *///?}
    }
}

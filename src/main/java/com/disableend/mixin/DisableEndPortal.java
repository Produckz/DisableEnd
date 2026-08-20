package com.disableend.mixin;

import com.disableend.DisableEnd;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(EndPortalBlock.class)
public class DisableEndPortal {
    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    private void noEnd(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler, CallbackInfo ci){
        // null check instead of requireNonNull prevents crash client side
        var server = world.getServer();
        if(server == null) {
            return;
        }

        if (server.getGameRules().getBoolean(DisableEnd.DISABLE_END)) {
            ci.cancel();
        }
    }
}

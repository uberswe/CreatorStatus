package com.uberswe.creatorstatus.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.uberswe.creatorstatus.client.Overlay;
import net.minecraft.client.gui.components.PlayerTabOverlay;
import net.minecraft.client.multiplayer.PlayerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerTabOverlay.class)
public abstract class PlayerTabOverlayMixin {
    @Inject(at = @At("HEAD"), method = "renderPingIcon")
    private void onRenderPingIcon(PoseStack poseStack, int p, int x, int y, PlayerInfo playerInfo, CallbackInfo ci) {
        Overlay.statusIcon(poseStack, x, y, playerInfo.getProfile().getId(), playerInfo.getProfile().getName());
    }
}
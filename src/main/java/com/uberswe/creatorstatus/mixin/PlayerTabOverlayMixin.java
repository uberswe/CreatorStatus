package com.uberswe.creatorstatus.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.uberswe.creatorstatus.client.Overlay;
import net.minecraft.client.gui.components.PlayerTabOverlay;
import net.minecraft.client.multiplayer.PlayerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerTabOverlay.class)
public abstract class PlayerTabOverlayMixin {
    @ModifyConstant(method = "render", constant = @Constant(intValue = 13))
    private int modifyRowWidth(int w) {
        int extraWidth = 60;
        return w + extraWidth;
    }
    @Inject(at = @At("HEAD"), method = "renderPingIcon")
    private void onRenderPingIcon(PoseStack poseStack, int p, int x, int y, PlayerInfo playerInfo, CallbackInfo ci) {
        p = p - 25;
        Overlay.statusIcon(poseStack, x, y, p, playerInfo.getProfile().getId(), playerInfo.getProfile().getName());
    }
}
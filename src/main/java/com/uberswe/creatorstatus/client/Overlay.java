package com.uberswe.creatorstatus.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.uberswe.creatorstatus.CreatorStatus;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

public class Overlay {
    private static final ResourceLocation STREAMING = new ResourceLocation(CreatorStatus.MODID, "textures/overlay/streaming.png");
    private static final ResourceLocation RECORDING = new ResourceLocation(CreatorStatus.MODID, "textures/overlay/recording.png");
    private static final ResourceLocation DONTRECORD = new ResourceLocation(CreatorStatus.MODID, "textures/overlay/dontrecord.png");
    private static final ResourceLocation OPENTOCOLLAB = new ResourceLocation(CreatorStatus.MODID, "textures/overlay/opentocollab.png");

    public static void statusIcon(PoseStack poseStack, int x, int y, int p, UUID playerUUID, String name) {
        switch (StatusData.getPlayerStatus(playerUUID.toString())) {
            case CreatorStatus.STREAMING -> {
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.setShaderTexture(0, STREAMING);
                GuiComponent.blit(poseStack, p + x, y, 0, 0, 8, 8, 8, 8);
            }
            case CreatorStatus.RECORDING -> {
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.setShaderTexture(0, RECORDING);
                GuiComponent.blit(poseStack, p + x, y, 0, 0, 8, 8, 8, 8);
            }
            case CreatorStatus.DO_NOT_RECORD -> {
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.setShaderTexture(0, DONTRECORD);
                GuiComponent.blit(poseStack, p + x, y, 0, 0, 8, 8, 8, 8);
            }
            case CreatorStatus.OPEN_TO_COLLAB -> {
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.setShaderTexture(0, OPENTOCOLLAB);
                GuiComponent.blit(poseStack, p + x, y, 0, 0, 8, 8, 8, 8);
            }
        }
    }

    public static String getNameTagColor(String playerUUID) {
        switch (StatusData.getPlayerStatus(playerUUID)) {
            case CreatorStatus.STREAMING -> {
                return "#af2ae8";
            }
            case CreatorStatus.RECORDING -> {
                return "#eb4034";
            }
            case CreatorStatus.DO_NOT_RECORD -> {
                return "#eb9c34";
            }
            case CreatorStatus.OPEN_TO_COLLAB -> {
                return "#337e37";
            }
        }
        return "#FFFFFF";
    }
}

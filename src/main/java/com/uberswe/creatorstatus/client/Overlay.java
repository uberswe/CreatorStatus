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

    public static void statusIcon(PoseStack poseStack, int x, int y, UUID playerUUID, String name) {
        switch (StatusData.getPlayerStatus(playerUUID.toString())) {
            case CreatorStatus.STREAMING -> {
                CreatorStatus.LOGGER.info(String.format("rendering streaming icon: %d, %d", x, y));
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.setShaderTexture(0, STREAMING);
                GuiComponent.blit(poseStack, x, y, 0, 0, 12, 12, 12, 12);
            }
            case CreatorStatus.RECORDING -> {
                CreatorStatus.LOGGER.info(String.format("rendering recording icon: %d, %d", x, y));
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.setShaderTexture(0, RECORDING);
                GuiComponent.blit(poseStack, x, y, 0, 0, 12, 12, 12, 12);
            }
            case CreatorStatus.DO_NOT_RECORD -> {
                CreatorStatus.LOGGER.info(String.format("rendering DNR icon: %d, %d", x, y));
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.setShaderTexture(0, DONTRECORD);
                GuiComponent.blit(poseStack, x, y, 0, 0, 12, 12, 12, 12);
            }
        }
    }


}

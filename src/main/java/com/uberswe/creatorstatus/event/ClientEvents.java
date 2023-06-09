package com.uberswe.creatorstatus.event;

import com.uberswe.creatorstatus.CreatorStatus;
import com.uberswe.creatorstatus.client.Overlay;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CreatorStatus.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {


    @SubscribeEvent
    public static void onRenderNameTag(RenderNameTagEvent event) {
        if (event.getEntity() instanceof Player) {
            Component content = event.getContent();
            Style style = content.getStyle().withColor(TextColor.parseColor(Overlay.getNameTagColor(event.getEntity().getStringUUID())));
            MutableComponent mutableComponent = MutableComponent.create(content.getContents());
            mutableComponent.setStyle(style);
            for (Component sibling : content.getSiblings()) {
                mutableComponent.append(sibling);
            }
            event.setContent(mutableComponent);
        }
    }
}

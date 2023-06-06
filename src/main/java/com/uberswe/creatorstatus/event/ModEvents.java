package com.uberswe.creatorstatus.event;

import com.uberswe.creatorstatus.CreatorStatus;
import com.uberswe.creatorstatus.client.StatusData;
import com.uberswe.creatorstatus.command.StatusCommand;
import com.uberswe.creatorstatus.networking.ModMessages;
import com.uberswe.creatorstatus.networking.packet.StatusS2CPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        StatusCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            if (event.getEntity() instanceof ServerPlayer player) {
                CreatorStatus.LOGGER.info("Sending status to player");
                ModMessages.sendToPlayer(new StatusS2CPacket(StatusData.getStatuses()), player);
            }
        }
    }

    public static void broadcastStatusUpdate() {
        CreatorStatus.LOGGER.info("Broadcasting statuses");
        ModMessages.broadcast(new StatusS2CPacket(StatusData.getStatuses()));
    }
}

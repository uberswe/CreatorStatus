package com.uberswe.creatorstatus.networking;

import com.uberswe.creatorstatus.CreatorStatus;
import com.uberswe.creatorstatus.networking.packet.StatusC2SPacket;
import com.uberswe.creatorstatus.networking.packet.StatusS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(CreatorStatus.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(StatusC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(StatusC2SPacket::new)
                .encoder(StatusC2SPacket::toBytes)
                .consumerMainThread(StatusC2SPacket::handle)
                .add();

        net.messageBuilder(StatusS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(StatusS2CPacket::new)
                .encoder(StatusS2CPacket::toBytes)
                .consumerMainThread(StatusS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        CreatorStatus.LOGGER.info("Sending msg to server");
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        CreatorStatus.LOGGER.info("Sending msg to client");
        CreatorStatus.LOGGER.info(player.getStringUUID());
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void broadcast(MSG message) {
        CreatorStatus.LOGGER.info("broadcast msg");
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}

package com.uberswe.creatorstatus.networking.packet;

import com.uberswe.creatorstatus.client.StatusData;
import com.uberswe.creatorstatus.event.ModEvents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StatusC2SPacket {
    private final int status;

    public StatusC2SPacket(int status) {
        this.status = status;
    }

    public StatusC2SPacket(FriendlyByteBuf buf) {
        this.status = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(status);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // Server side handling
            ServerPlayer player = context.getSender();

            if (player != null) {
                StatusData.setPlayerStatus(player.getStringUUID(), this.status);
            }
        });
        return true;
    }
}

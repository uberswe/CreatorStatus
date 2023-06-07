package com.uberswe.creatorstatus.networking.packet;

import com.uberswe.creatorstatus.CreatorStatus;
import com.uberswe.creatorstatus.client.StatusData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.function.Supplier;

public class StatusS2CPacket {
    private final HashMap<String, Integer> statuses;

    public StatusS2CPacket(HashMap<String, Integer> statuses) {
        this.statuses = statuses;
    }

    public StatusS2CPacket(FriendlyByteBuf buf) {
        this.statuses = buf.readMap(HashMap::new, FriendlyByteBuf::readUtf, FriendlyByteBuf::readInt);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeMap(statuses, FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeInt);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // Client side handling
            StatusData.set(statuses);
        });
        return true;
    }
}

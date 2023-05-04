package com.rinventor.transportmod.network.data.clientbound;

import com.rinventor.transportmod.network.ClientAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class PTMStopsSyncMessage {

    public final String a1;

    public PTMStopsSyncMessage(String a1) {
        this.a1 = a1;
    }

    public PTMStopsSyncMessage(FriendlyByteBuf buffer) {
        this(buffer.readUtf());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUtf(this.a1);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        AtomicBoolean success = new AtomicBoolean(false);
        ctx.get().enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> success.set(ClientAccess.dataSync1(a1)));
        });

        ctx.get().setPacketHandled(true);
        return success.get();
    }

}

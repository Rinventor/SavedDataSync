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
    public final String a2;
    public final String a3;
    public final String a4;
    public final String a5;
    public final String a6;
    public final String a7;
    public final String a8;
    public final String a9;
    public final String a10;
    public final String a11;
    public final String a12;
    public final String a13;
    public final String a14;
    public final String a15;
    public final String a16;

    public PTMStopsSyncMessage(String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8, String a9, String a10, String a11, String a12, String a13, String a14, String a15, String a16) {
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.a5 = a5;
        this.a6 = a6;
        this.a7 = a7;
        this.a8 = a8;
        this.a9 = a9;
        this.a10 = a10;
        this.a11 = a11;
        this.a12 = a12;
        this.a13 = a13;
        this.a14 = a14;
        this.a15 = a15;
        this.a16 = a16;
    }

    public PTMStopsSyncMessage(FriendlyByteBuf buffer) {
        this(buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUtf(this.a1);
        buffer.writeUtf(this.a2);
        buffer.writeUtf(this.a3);
        buffer.writeUtf(this.a4);
        buffer.writeUtf(this.a5);
        buffer.writeUtf(this.a6);
        buffer.writeUtf(this.a7);
        buffer.writeUtf(this.a8);
        buffer.writeUtf(this.a9);
        buffer.writeUtf(this.a10);
        buffer.writeUtf(this.a11);
        buffer.writeUtf(this.a12);
        buffer.writeUtf(this.a13);
        buffer.writeUtf(this.a14);
        buffer.writeUtf(this.a15);
        buffer.writeUtf(this.a16);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        AtomicBoolean success = new AtomicBoolean(false);
        ctx.get().enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> success.set(ClientAccess.dataSync7(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16)));
        });

        ctx.get().setPacketHandled(true);
        return success.get();
    }

}

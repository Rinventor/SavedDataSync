package com.rinventor.transportmod.testing;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;

public class PTMSavedDataClientboundPacket implements Packet<ClientGameListener> {

    public final int a1;

    public PTMSavedDataClientboundPacket(int a1) {
        this.a1 = a1;
    }

    public PTMSavedDataClientboundPacket(FriendlyByteBuf buffer) {
        this(buffer.readInt());
    }

    public void write(FriendlyByteBuf buffer) {
        buffer.writeInt(this.a1);
    }

//    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
//        AtomicBoolean success = new AtomicBoolean(false);
//        ctx.get().enqueueWork(() -> {
//            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> success.set(ClientAccess.dataSync1(a1)));
//        });
//
//        ctx.get().setPacketHandled(true);
//        return success.get();
//    }

    public void handle(ClientGameListener listener) {
        listener.handleCount(this);
    }

}

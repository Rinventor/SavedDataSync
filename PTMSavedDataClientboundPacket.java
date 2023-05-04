package com.rinventor.transportmod.testing;

import com.rinventor.transportmod.core.base.PTMScreen;
import com.rinventor.transportmod.core.data.Ref;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class PTMSavedDataClientboundPacket {

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

    //Send packet to 1 player only who will open the screen


    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        AtomicBoolean success = new AtomicBoolean(false);
        ctx.get().enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                //int data = a1;
            });
        });

        ctx.get().setPacketHandled(true);
        return success.get();
    }

}

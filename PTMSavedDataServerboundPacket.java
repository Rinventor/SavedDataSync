package com.rinventor.transportmod.testing;

import com.rinventor.transportmod.core.init.ModNetwork;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PTMSavedDataServerboundPacket {
    public final int a1;

    public PTMSavedDataServerboundPacket(int a1) {
        this.a1 = a1;
    }

    public PTMSavedDataServerboundPacket(FriendlyByteBuf buffer) {
        this.a1 = buffer.readInt();
    }

    public static void buffer(PTMSavedDataServerboundPacket message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.a1);
    }

    public static void handler(PTMSavedDataServerboundPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            LevelAccessor level = context.getSender().getLevel();
            PTMSavedData.set(level, message.a1);
        });
        context.setPacketHandled(true);
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        ModNetwork.addNetworkMessage(PTMSavedDataServerboundPacket.class, PTMSavedDataServerboundPacket::buffer, PTMSavedDataServerboundPacket::new, PTMSavedDataServerboundPacket::handler);
    }

}

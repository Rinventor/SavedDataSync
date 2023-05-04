package com.rinventor.transportmod.network.data.serverbound;

import com.rinventor.transportmod.core.data.PTMStaticData;
import com.rinventor.transportmod.core.data.PTMStops;
import com.rinventor.transportmod.core.data.PTMTransportLines;
import com.rinventor.transportmod.core.init.ModNetwork;
import com.rinventor.transportmod.objects.StationStop;
import com.rinventor.transportmod.objects.TransportLine;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PTMStopsMessage {
    public final String a1;

    public PTMStopsMessage(String a1) {
        this.a1 = a1;
    }

    public PTMStopsMessage(FriendlyByteBuf buffer) {
        this.a1 = buffer.readUtf();
    }

    public static void buffer(PTMStopsMessage message, FriendlyByteBuf buffer) {
        buffer.writeUtf(message.a1);
    }

    public static void handler(PTMStopsMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            handle(context.getSender().getLevel(), message.a1, message.a2, message.a3, message.a4, message.a5, message.a6, message.a7, message.a8, message.a9, message.a10, message.a11, message.a12, message.a13, message.a14, message.a15, message.a16);
        });
        context.setPacketHandled(true);
    }

    public static void handle(LevelAccessor world, String a1) {
        PTMStops.set(world, a1);
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        ModNetwork.addNetworkMessage(PTMStopsMessage.class, PTMStopsMessage::buffer, PTMStopsMessage::new, PTMStopsMessage::handler);
    }

}

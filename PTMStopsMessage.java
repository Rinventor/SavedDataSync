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

    public PTMStopsMessage(String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8, String a9, String a10, String a11, String a12, String a13, String a14, String a15, String a16) {
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

    public PTMStopsMessage(FriendlyByteBuf buffer) {
        this.a1 = buffer.readUtf();
        this.a2 = buffer.readUtf();
        this.a3 = buffer.readUtf();
        this.a4 = buffer.readUtf();
        this.a5 = buffer.readUtf();
        this.a6 = buffer.readUtf();
        this.a7 = buffer.readUtf();
        this.a8 = buffer.readUtf();
        this.a9 = buffer.readUtf();
        this.a10 = buffer.readUtf();
        this.a11 = buffer.readUtf();
        this.a12 = buffer.readUtf();
        this.a13 = buffer.readUtf();
        this.a14 = buffer.readUtf();
        this.a15 = buffer.readUtf();
        this.a16 = buffer.readUtf();
    }

    public static void buffer(PTMStopsMessage message, FriendlyByteBuf buffer) {
        buffer.writeUtf(message.a1);
        buffer.writeUtf(message.a2);
        buffer.writeUtf(message.a3);
        buffer.writeUtf(message.a4);
        buffer.writeUtf(message.a5);
        buffer.writeUtf(message.a6);
        buffer.writeUtf(message.a7);
        buffer.writeUtf(message.a8);
        buffer.writeUtf(message.a9);
        buffer.writeUtf(message.a10);
        buffer.writeUtf(message.a11);
        buffer.writeUtf(message.a12);
        buffer.writeUtf(message.a13);
        buffer.writeUtf(message.a14);
        buffer.writeUtf(message.a15);
        buffer.writeUtf(message.a16);
    }

    public static void handler(PTMStopsMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            handle(context.getSender().getLevel(), message.a1, message.a2, message.a3, message.a4, message.a5, message.a6, message.a7, message.a8, message.a9, message.a10, message.a11, message.a12, message.a13, message.a14, message.a15, message.a16);
        });
        context.setPacketHandled(true);
    }

    public static void handle(LevelAccessor world, String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8, String a9, String a10, String a11, String a12, String a13, String a14, String a15, String a16) {
        List<StationStop> stopsIn = new ArrayList<>();
        List<String> list = List.of(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16);
        for(String arg : list) {
            for(String str : arg.split("&")) {
                if (str.length() >= 2)
                    stopsIn.add(StationStop.format(str));
            }
        }
        PTMStaticData.stops = stopsIn;
        PTMStops.save(world);
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        ModNetwork.addNetworkMessage(PTMStopsMessage.class, PTMStopsMessage::buffer, PTMStopsMessage::new, PTMStopsMessage::handler);
    }

}

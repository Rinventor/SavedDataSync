package com.rinventor.transportmod.core.data;

import com.rinventor.transportmod.core.base.PTMWorld;
import com.rinventor.transportmod.core.init.ModNetwork;
import com.rinventor.transportmod.network.data.serverbound.PTMStopsMessage;
import com.rinventor.transportmod.network.data.serverbound.PTMTransportLinesMessage;
import com.rinventor.transportmod.network.data.serverbound.PTMDataReadMessage;
import com.rinventor.transportmod.network.data.serverbound.PTMDataSaveMessage;
import com.rinventor.transportmod.network.data.clientbound.PTMStopsSyncMessage;
import com.rinventor.transportmod.objects.StationStop;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

public class PTMStops extends SavedData {

    public List<StationStop> stops = new ArrayList<StationStop>();
    public int count = 0;

    private static PTMStops create() {
        return new PTMStops();
    }

    private static PTMStops load(CompoundTag tag) {
        PTMStops data = create();
        List<StationStop> stopsIn = new ArrayList<>();
        data.count = tag.getInt("count");
        for (int i = 0; i < data.count; i++) {
            stopsIn.add(StationStop.format(tag.getString("stop" + i)));
        }
        data.stops = stopsIn;
        data.count = stopsIn.size();
        return data;
    }

    public CompoundTag save(CompoundTag tag) {
        count = stops.size();
        tag.putInt("count", count);
        int i = 0;
        for (StationStop stationStop : stops) {
            tag.putString("stop" + i, StationStop.asString(stationStop));
            i++;
        }
        return tag;
    }

    /*
     * #server-side ONLY Gets current instance of the saved data Return new instance
     * when on client
     */
    public static PTMStops getInstance(LevelAccessor level) {
        MinecraftServer server = level.getServer();
        if (server == null) {
            return create();
        }
        return server.overworld().getDataStorage().computeIfAbsent(PTMStops::load, PTMStops::create, "ptm-stops");
    }

    public static void syncToC(PTMStops data){
        //Sync the data
        String a1 = "", a2 = "", a3 = "", a4 = "", a5 = "", a6 = "", a7 = "", a8 = "";
        String a9 = "", a10 = "", a11 = "", a12 = "", a13 = "", a14 = "", a15 = "", a16 = "";
        int strLimit = 28000;
        for (StationStop stop : data.stops) {
            if (a1.length() < strLimit)
                a1 += StationStop.asString(stop) + "&";
            else if (a2.length() < strLimit)
                a2 += StationStop.asString(stop) + "&";
            else if (a3.length() < strLimit)
                a3 += StationStop.asString(stop) + "&";
            else if (a4.length() < strLimit)
                a4 += StationStop.asString(stop) + "&";
            else if (a5.length() < strLimit)
                a5 += StationStop.asString(stop) + "&";
            else if (a6.length() < strLimit)
                a6 += StationStop.asString(stop) + "&";
            else if (a7.length() < strLimit)
                a7 += StationStop.asString(stop) + "&";
            else if (a8.length() < strLimit)
                a8 += StationStop.asString(stop) + "&";
            else if (a9.length() < strLimit)
                a9 += StationStop.asString(stop) + "&";
            else if (a10.length() < strLimit)
                a10 += StationStop.asString(stop) + "&";
            else if (a11.length() < strLimit)
                a11 += StationStop.asString(stop) + "&";
            else if (a12.length() < strLimit)
                a12 += StationStop.asString(stop) + "&";
            else if (a13.length() < strLimit)
                a13 += StationStop.asString(stop) + "&";
            else if (a14.length() < strLimit)
                a14 += StationStop.asString(stop) + "&";
            else if (a15.length() < strLimit)
                a15 += StationStop.asString(stop) + "&";
            else if (a16.length() < strLimit)
                a16 += StationStop.asString(stop) + "&";
        }

        if (a1.endsWith("&"))
            a1 = a1.substring(0, a1.length() - 1);
        if (a2.endsWith("&"))
            a2 = a2.substring(0, a2.length() - 1);
        if (a3.endsWith("&"))
            a3 = a3.substring(0, a3.length() - 1);
        if (a4.endsWith("&"))
            a4 = a4.substring(0, a4.length() - 1);
        if (a5.endsWith("&"))
            a5 = a5.substring(0, a5.length() - 1);
        if (a6.endsWith("&"))
            a6 = a6.substring(0, a6.length() - 1);
        if (a7.endsWith("&"))
            a7 = a7.substring(0, a7.length() - 1);
        if (a8.endsWith("&"))
            a8 = a8.substring(0, a8.length() - 1);
        if (a9.endsWith("&"))
            a9 = a9.substring(0, a9.length() - 1);
        if (a10.endsWith("&"))
            a10 = a10.substring(0, a10.length() - 1);
        if (a11.endsWith("&"))
            a11 = a11.substring(0, a11.length() - 1);
        if (a12.endsWith("&"))
            a12 = a12.substring(0, a12.length() - 1);
        if (a13.endsWith("&"))
            a13 = a13.substring(0, a13.length() - 1);
        if (a14.endsWith("&"))
            a14 = a14.substring(0, a14.length() - 1);
        if (a15.endsWith("&"))
            a15 = a15.substring(0, a15.length() - 1);
        if (a16.endsWith("&"))
            a16 = a16.substring(0, a16.length() - 1);

        ModNetwork.INSTANCE.send(PacketDistributor.ALL.noArg(), new PTMStopsSyncMessage(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16));
    }

    public static void syncToS(LevelAccessor world) {
        String a1 = "", a2 = "", a3 = "", a4 = "", a5 = "", a6 = "", a7 = "", a8 = "";
        String a9 = "", a10 = "", a11 = "", a12 = "", a13 = "", a14 = "", a15 = "", a16 = "";
        int strLimit = 28000;
        for (StationStop stop : PTMStaticData.stops) {
            if (a1.length() < strLimit)
                a1 += StationStop.asString(stop) + "&";
            else if (a2.length() < strLimit)
                a2 += StationStop.asString(stop) + "&";
            else if (a3.length() < strLimit)
                a3 += StationStop.asString(stop) + "&";
            else if (a4.length() < strLimit)
                a4 += StationStop.asString(stop) + "&";
            else if (a5.length() < strLimit)
                a5 += StationStop.asString(stop) + "&";
            else if (a6.length() < strLimit)
                a6 += StationStop.asString(stop) + "&";
            else if (a7.length() < strLimit)
                a7 += StationStop.asString(stop) + "&";
            else if (a8.length() < strLimit)
                a8 += StationStop.asString(stop) + "&";
            else if (a9.length() < strLimit)
                a9 += StationStop.asString(stop) + "&";
            else if (a10.length() < strLimit)
                a10 += StationStop.asString(stop) + "&";
            else if (a11.length() < strLimit)
                a11 += StationStop.asString(stop) + "&";
            else if (a12.length() < strLimit)
                a12 += StationStop.asString(stop) + "&";
            else if (a13.length() < strLimit)
                a13 += StationStop.asString(stop) + "&";
            else if (a14.length() < strLimit)
                a14 += StationStop.asString(stop) + "&";
            else if (a15.length() < strLimit)
                a15 += StationStop.asString(stop) + "&";
            else if (a16.length() < strLimit)
                a16 += StationStop.asString(stop) + "&";
        }

        if (a1.endsWith("&"))
            a1 = a1.substring(0, a1.length() - 1);
        if (a2.endsWith("&"))
            a2 = a2.substring(0, a2.length() - 1);
        if (a3.endsWith("&"))
            a3 = a3.substring(0, a3.length() - 1);
        if (a4.endsWith("&"))
            a4 = a4.substring(0, a4.length() - 1);
        if (a5.endsWith("&"))
            a5 = a5.substring(0, a5.length() - 1);
        if (a6.endsWith("&"))
            a6 = a6.substring(0, a6.length() - 1);
        if (a7.endsWith("&"))
            a7 = a7.substring(0, a7.length() - 1);
        if (a8.endsWith("&"))
            a8 = a8.substring(0, a8.length() - 1);
        if (a9.endsWith("&"))
            a9 = a9.substring(0, a9.length() - 1);
        if (a10.endsWith("&"))
            a10 = a10.substring(0, a10.length() - 1);
        if (a11.endsWith("&"))
            a11 = a11.substring(0, a11.length() - 1);
        if (a12.endsWith("&"))
            a12 = a12.substring(0, a12.length() - 1);
        if (a13.endsWith("&"))
            a13 = a13.substring(0, a13.length() - 1);
        if (a14.endsWith("&"))
            a14 = a14.substring(0, a14.length() - 1);
        if (a15.endsWith("&"))
            a15 = a15.substring(0, a15.length() - 1);
        if (a16.endsWith("&"))
            a16 = a16.substring(0, a16.length() - 1);

        ModNetwork.INSTANCE.sendToServer(new PTMStopsMessage(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16));
        PTMStopsMessage.handle(world, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16);
    }

    public static void save(LevelAccessor level) {
        if (PTMWorld.isServer(level)) {
            PTMStops data = PTMStops.getInstance(level);
            data.stops = PTMStaticData.stops;
            data.setDirty();
        }
        else {
            ModNetwork.INSTANCE.sendToServer(new PTMDataSaveMessage(3));
        }
    }

    public static void read(LevelAccessor level) {
        if (PTMWorld.isServer(level)) {
            PTMStops data = PTMStops.getInstance(level);
            PTMStaticData.stops = data.stops;
            syncToC(data);
        }
        else {
            ModNetwork.INSTANCE.sendToServer(new PTMDataReadMessage(3));
        }
    }

}

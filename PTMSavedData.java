package com.rinventor.transportmod.testing;

import com.rinventor.transportmod.core.base.PTMWorld;
import com.rinventor.transportmod.core.init.ModNetwork;
import com.rinventor.transportmod.network.data.clientbound.PTMStopsSyncMessage;
import com.rinventor.transportmod.network.data.serverbound.PTMDataSaveMessage;
import com.rinventor.transportmod.network.data.serverbound.PTMStopsMessage;
import com.rinventor.transportmod.objects.data.types.StationStop;
import com.rinventor.transportmod.util.Exceptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

public class PTMSavedData extends SavedData {

    public int count = 0;

    private static PTMSavedData create() {
        return new PTMSavedData();
    }

    private static PTMSavedData load(CompoundTag tag) {
        PTMSavedData data = create();
        data.count = tag.getInt("count");
        return data;
    }

    public CompoundTag save(CompoundTag tag) {
        tag.putInt("count", count);
        return tag;
    }

    /*
     * #server-side ONLY Gets current instance of the saved data Return new instance
     * when on client
     */
    public static PTMSavedData getInstance(LevelAccessor level) {
        if (!PTMWorld.isServer(level))
            throw Exceptions.SAVED_DATA_ILLEGAL_ACCESS;
        return ((ServerLevel) level).getDataStorage().computeIfAbsent(PTMSavedData::load, PTMSavedData::create, "ptm-count");
    }

    public static void set(LevelAccessor level, int count) {
        if (PTMWorld.isServer(level)) {
            PTMSavedData data = PTMSavedData.getInstance(level);
            data.count = count;
            data.setDirty();
        }
    }

    public static int get(LevelAccessor level) {
        if (PTMWorld.isServer(level)) {
            PTMSavedData data = PTMSavedData.getInstance(level);
            return data.count;
        }
        //Where do I send the PTMSavedDataClientboundPacket???
        //Here??
        return -1;

    }

}

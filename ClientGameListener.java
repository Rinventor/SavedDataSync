package com.rinventor.transportmod.testing;

import net.minecraft.network.PacketListener;

public interface ClientGameListener extends PacketListener {

    void handleCount(PTMSavedDataClientboundPacket message);

}

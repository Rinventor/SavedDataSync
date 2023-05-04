package com.rinventor.transportmod.testing;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.ClientTelemetryManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientAdvancements;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientPacketListener implements ClientGameListener {

    private final Minecraft minecraft;
    private final Connection connection;
    private final GameProfile localGameProfile;
    private final Screen callbackScreen;
    private ClientLevel level;
    private ClientLevel.ClientLevelData levelData;
    private final ClientAdvancements advancements;
    private final ClientSuggestionProvider suggestionsProvider;
    private final ClientTelemetryManager telemetryManager;

    public ClientPacketListener(Minecraft p_194193_, Screen p_194194_, Connection p_194195_, GameProfile p_194196_, ClientTelemetryManager p_194197_) {
        this.minecraft = p_194193_;
        this.callbackScreen = p_194194_;
        this.connection = p_194195_;
        this.localGameProfile = p_194196_;
        this.advancements = new ClientAdvancements(p_194193_);
        this.suggestionsProvider = new ClientSuggestionProvider(this, p_194193_);
        this.telemetryManager = p_194197_;
    }
    public void handleCount(PTMSavedDataClientboundPacket packet) {
        PacketUtils.ensureRunningOnSameThread(packet, this, this.minecraft);
        //How can I get the value though?
        //Do I get it here?
        //It is on client, so that’s good, but I need to get the value for screen. I don’t need it here. Or do I?
    }

    @Override
    public void onDisconnect(Component p_104954_) {
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }
}

package com.rinventor.transportmod.network.computer.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.rinventor.transportmod.TransportMod;
import com.rinventor.transportmod.core.data.PTMData;
import com.rinventor.transportmod.core.data.PTMStaticData;
import com.rinventor.transportmod.core.data.PTMStops;
import com.rinventor.transportmod.core.data.PTMTransportLines;
import com.rinventor.transportmod.core.init.ModNetwork;
import com.rinventor.transportmod.network.computer.CScreenButtonMessage;
import com.rinventor.transportmod.network.computer.menu.CMenu14;
import com.rinventor.transportmod.objects.StationStop;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CScreen14 extends AbstractContainerScreen<CMenu14> {
    private final Player entity;
    private CScreen14.StopsList list;
    String stopName;
    List<String> stops;

    public CScreen14(CMenu14 container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.entity = container.entity;
        this.imageWidth = 320;
        this.imageHeight = 240;
        this.stopName = "";

        PTMStops.read(Minecraft.getInstance().level);
        this.stops = new ArrayList<>();
        List<StationStop> stops = PTMStaticData.stops;
        for (StationStop stop : stops) {
            this.stops.add(stop.getName());
        }
    }

    private static final ResourceLocation texture = new ResourceLocation(TransportMod.MOD_ID + ":textures/cmenu.png");

    @Override
    public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(ms);
        super.render(ms, mouseX, mouseY, partialTicks);
        this.renderTooltip(ms, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, texture);
        GuiComponent.blit(ms, this.leftPos, this.topPos, 0, 0, imageWidth, 198, imageWidth, imageHeight);
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            this.minecraft.player.closeContainer();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    @Override
    protected void renderLabels(PoseStack ps, int mouseX, int mouseY) {
        this.font.draw(ps, Component.translatable("menu14.heading").getString(), 7, 7, -16711792);
    }

    @Override
    public void onClose() {
        super.onClose();
        Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
    }

    @Override
    public void init() {
        super.init();
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);

        // BTNs: New line, Edit, Delete
        String t1 = Component.translatable("menu8.edit").getString();
        String t2 = Component.translatable("menu8.delete").getString();
        String t3 = Component.translatable("menu14.new").getString();
        int start = leftPos + 225;
        int top = topPos + 35;
        int sp = 24, w = 80, h = 20;

        // Edit
        this.addRenderableWidget(new Button(start, top, w, h, Component.literal(t1), e -> {
            String l = this.stopName;
            List<StationStop> stops = PTMStaticData.stops;
            for (StationStop stop : stops) {
                if (l.equals(stop.getName()) && entity != null) {
                    ModNetwork.INSTANCE.sendToServer(new CScreenButtonMessage(32, "#id=" + stop.getId()));
                    CScreenButtonMessage.handleButtonAction(entity, 32, "#id=" + stop.getId());
                }
            }
        }));

        // Delete
        this.addRenderableWidget(new Button(start, top + (sp), w, h, Component.literal(t2), e -> {
            String s = this.stopName;
            List<StationStop> newStops = new ArrayList<>();
            List<StationStop> stops = PTMStaticData.stops;
            for (StationStop stop : stops) {
                if (!s.equals(stop.getName())) {
                    newStops.add(stop);
                }
            }
            PTMStaticData.stops = newStops;
            PTMStops.syncToS(entity.level);
            updateList();
        }));

        // New stop
        this.addRenderableWidget(new Button(start, top + (2 * sp), w, h, Component.literal(t3), e -> {
            ModNetwork.INSTANCE.sendToServer(new CScreenButtonMessage(32, "#id=0"));
            CScreenButtonMessage.handleButtonAction(entity, 32, "#id=0");
        }));

        // Top panel
        this.addRenderableWidget(new Button(leftPos + imageWidth - 24, topPos + 4, 20, 20, Component.literal("<-"), e -> {
            ModNetwork.INSTANCE.sendToServer(new CScreenButtonMessage(1, ""));
            CScreenButtonMessage.handleButtonAction(entity, 1, "");
        }));

        updateList();
    }

    private void updateList() {
        this.removeWidget(this.list);
        this.stops.clear();
        List<StationStop> stops = PTMStaticData.stops;
        for (StationStop stop : stops) {
            if (!this.stops.contains(stop.getName())) {
                this.stops.add(stop.getName());
            }
        }
        Collections.sort(this.stops);

        if (this.stops.size() > 0) {
            this.list = new CScreen14.StopsList();
            this.list.setRenderBackground(false);
            this.list.setRenderTopAndBottom(false);
            this.list.setLeftPos(leftPos + 7);
            this.list.setSelected(this.list.children().stream().findFirst().get());
            this.addRenderableWidget(this.list);
        }
    }

    @OnlyIn(Dist.CLIENT)
    class StopsList extends ObjectSelectionList<CScreen14.StopsList.Entry> {
        StopsList() {
            // minecraft, width, height, y0, y1, itemHeight
            super(CScreen14.this.minecraft, 200, CScreen14.this.imageHeight, CScreen14.this.topPos + 28, CScreen14.this.topPos + 180, 16);
            for (String l : CScreen14.this.stops) {
                this.addEntry(new CScreen14.StopsList.Entry(l));
            }
        }

        @Override
        public int getRowWidth() {
            return this.width;
        }

        @Override
        protected int getScrollbarPosition() {
            return CScreen14.this.leftPos + getRowWidth() + 10;
        }

        protected boolean isFocused() {
            return CScreen14.this.getFocused() == this;
        }

        public void setSelected(@Nullable CScreen14.StopsList.Entry entry) {
            super.setSelected(entry);
            if (entry != null) {
                CScreen14.this.stopName = entry.stop;
            }
        }

        @OnlyIn(Dist.CLIENT)
        class Entry extends ObjectSelectionList.Entry<CScreen14.StopsList.Entry> {
            final String stop;

            public Entry(String entry) {
                this.stop = entry;
            }

            public Component getNarration() {
                return Component.translatable("narrator.select", this.stop);
            }

            public void render(PoseStack ps, int p_95803_, int p_95804_, int p_95805_, int p_95806_, int p_95807_, int p_95808_, int p_95809_, boolean p_95810_, float p_95811_) {
                GuiComponent.drawString(ps, CScreen14.this.font, this.stop, p_95805_ + 5, p_95804_ + 2, 16777215);
            }

            public boolean mouseClicked(double p_95798_, double p_95799_, int state) {
                if (state == 0) {
                    StopsList.this.setSelected(this);
                    return true;
                }
                else {
                    return false;
                }
            }
        }
    }

}

package com.rinventor.transportmod.testing;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.rinventor.transportmod.TransportMod;
import com.rinventor.transportmod.core.data.computer.menus.CMenu14;
import com.rinventor.transportmod.core.init.ModNetwork;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

public class DataScreen extends AbstractContainerScreen<DataMenu> {
    private final Player entity;
    private int count;

    public DataScreen(DataMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.entity = container.entity;
        this.imageWidth = 320;
        this.imageHeight = 240;

        count = container.data;

        //count = PTMSavedData.get(Minecraft.getInstance().level);
        //WILL THROW A RUNTIME ERROR THAT I CREATED because I’m accessing saved data on client side
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
        this.font.draw(ps, Component.literal(String.valueOf(count)).getString(), 7, 7, -16711792);
    }

    @Override
    public void onClose() {
        super.onClose();
        Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
        count++;
        ModNetwork.INSTANCE.sendToServer(new PTMSavedDataServerboundPacket(count));
    }

}
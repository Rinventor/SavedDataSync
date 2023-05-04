package com.rinventor.transportmod.testing;

import com.rinventor.transportmod.core.init.ModMenus;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DataMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {

    private final Map<Integer, Slot> customSlots = new HashMap<>();
    public final Level world;
    public final Player entity;
    public int x, y, z;
    public int data;

    public DataMenu(int id, Inventory inv) {
        super(ModMenus.DATA_MENU.get(), id);
        this.entity = inv.player;
        this.world = inv.player.level;
        this.data = PTMSavedData.get(world);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    public Map<Integer, Slot> get() {
        return customSlots;
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        return ItemStack.EMPTY;
    }

}

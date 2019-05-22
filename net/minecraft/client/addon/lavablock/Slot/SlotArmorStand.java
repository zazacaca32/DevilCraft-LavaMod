package net.minecraft.client.addon.lavablock.Slot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.lavablock.Tile.ArmorStandTile;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class SlotArmorStand extends Slot
{
    public ArmorStandTile tile;
    final int armorType;

    public SlotArmorStand(ArmorStandTile tile, int par2, int par3, int par4, int par6)
    {
        super(tile, par2, par3, par4);
        this.tile = tile;
        this.armorType = par6;
    }

    public int getSlotStackLimit()
    {
        return 1;
    }

    public boolean isItemValid(ItemStack par1ItemStack)
    {
        Item item = par1ItemStack == null ? null : par1ItemStack.getItem();
        return item != null && item.isValidArmor(par1ItemStack, this.armorType);
    }

    @SideOnly(Side.CLIENT)
    public Icon getBackgroundIconIndex()
    {
        return ItemArmor.func_94602_b(this.armorType);
    }
}

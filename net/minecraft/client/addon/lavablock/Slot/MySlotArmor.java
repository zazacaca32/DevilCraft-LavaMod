package net.minecraft.client.addon.lavablock.Slot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class MySlotArmor extends Slot
{
    final int armorType;
    final EntityPlayer parent;

    public MySlotArmor(EntityPlayer par1ContainerPlayer, IInventory par2IInventory, int par3, int par4, int par5, int par6)
    {
        super(par2IInventory, par3, par4, par5);
        this.parent = par1ContainerPlayer;
        this.armorType = par6;
    }

    public int getSlotStackLimit()
    {
        return 1;
    }

    public boolean isItemValid(ItemStack par1ItemStack)
    {
        Item item = par1ItemStack == null ? null : par1ItemStack.getItem();
        return item != null && item.isValidArmor(par1ItemStack, this.armorType, this.parent);
    }

    @SideOnly(Side.CLIENT)
    public Icon getBackgroundIconIndex()
    {
        return ItemArmor.func_94602_b(this.armorType);
    }
}

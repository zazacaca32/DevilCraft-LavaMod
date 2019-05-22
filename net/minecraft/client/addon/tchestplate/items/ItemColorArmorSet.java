package net.minecraft.client.addon.tchestplate.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.overlaygui.IArmorExt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;

public class ItemColorArmorSet extends MultiItemBase implements IArmorExt
{
    public ItemColorArmorSet(int var1, int var2, int var3)
    {
        super(var1, var3);
        super.maxStackSize = var2;
    }

    @ForgeSubscribe
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (var2.isRemote)
        {
            LavaChestPlate var10000 = LavaChestPlate.instance;
            LavaChestPlate.proxy.openCustomGui(3000, var3);
        }

        return var1;
    }

    public int getSlot()
    {
        return 2;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4)
    {
        super.addInformation(var1, var2, var3, var4);
    }
}

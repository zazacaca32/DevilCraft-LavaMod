package net.minecraft.client.addon.tchestplate.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemTurning extends MultiItemBase
{
    public ItemTurning(int i, int j, int count)
    {
        super(i, count);
        super.maxStackSize = j;
        this.setCreativeTab(LavaChestPlate.tabItemss);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        super.addInformation(par1ItemStack, par2EntityPlayer, par2List, par4);

        switch (par1ItemStack.getItemDamage())
        {
            case 14:
                par2List.add("§6 Недоработанная капсула");
                par2List.add("§6 содержит 9000 материи");
                break;

            case 15:
                par2List.add("§6 Начальная капсула");
                par2List.add("§6 содержит 1000 материи");
        }
    }
}

package net.minecraft.client.addon.tchestplate.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemAcceleratorMatter extends MultiItemBase
{
    public ItemAcceleratorMatter(int i, int j, int count)
    {
        super(i, count);
        super.maxStackSize = j;
        this.setCreativeTab(LavaChestPlate.tabItemss);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        int damage = par1ItemStack.getItemDamage();

        if (damage != 11)
        {
            short pr = 0;
            super.addInformation(par1ItemStack, par2EntityPlayer, par2List, par4);
            par2List.add("§6 Ускоряет производство материи");

            switch (damage)
            {
                case 0:
                    pr = 1;
                    break;

                case 1:
                    pr = 3;
                    break;

                case 2:
                    pr = 10;
                    break;

                case 3:
                    pr = 30;
                    break;

                case 4:
                    pr = 60;
                    break;

                case 5:
                    pr = 75;
                    break;

                case 6:
                    pr = 80;
                    break;

                case 7:
                    pr = 90;
                    break;

                case 8:
                    pr = 95;
                    break;

                case 9:
                    pr = 115;
                    break;

                case 10:
                    pr = 130;
            }

            par2List.add("§6 в Синтезаторе материи на " + pr + " %");
        }
    }
}

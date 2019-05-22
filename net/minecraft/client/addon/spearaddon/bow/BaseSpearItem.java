package net.minecraft.client.addon.spearaddon.bow;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BaseSpearItem extends Item
{
    public BaseSpearItem(int par1)
    {
        super(par1);
    }

    public int getEnchantSpear(ItemStack par1ItemStack)
    {
        return par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("sup") ? par1ItemStack.stackTagCompound.getInteger("sup") : 0;
    }

    public int Sum(ItemStack i)
    {
        int a = this.getEnchantSpear(i);
        return a == 0 ? 1600 : (a == 1 ? 1760 : (a == 2 ? 2000 : (a == 3 ? 2320 : (a == 4 ? 2720 : (a == 5 ? 3200 : (a == 6 ? 3760 : (a == 7 ? 4400 : (a == 8 ? 5120 : (a == 9 ? 5920 : (a == 10 ? 6800 : (a == 11 ? 7760 : (a == 12 ? 8800 : (a == 13 ? 9920 : (a == 14 ? 11120 : (a == 15 ? 12400 : (a == 16 ? 13760 : 1))))))))))))))));
    }

    public int Demon(ItemStack i)
    {
        int a = this.getEnchantSpear(i);
        return a == 0 ? 2250 : (a == 1 ? 2470 : (a == 2 ? 2800 : (a == 3 ? 3240 : (a == 4 ? 3790 : (a == 5 ? 4450 : (a == 6 ? 5220 : (a == 7 ? 6100 : (a == 8 ? 7090 : (a == 9 ? 8190 : (a == 10 ? 9400 : (a == 11 ? 10720 : (a == 12 ? 12150 : (a == 13 ? 13690 : (a == 14 ? 15340 : (a == 15 ? 17100 : (a == 16 ? 18970 : 1))))))))))))))));
    }

    public int Hero(ItemStack i)
    {
        int a = this.getEnchantSpear(i);
        return a == 0 ? 8000 : (a == 1 ? 8250 : (a == 2 ? 8500 : (a == 3 ? 8750 : (a == 4 ? 9000 : (a == 5 ? 9250 : (a == 6 ? 9500 : (a == 7 ? 9750 : (a == 8 ? 10000 : (a == 9 ? 10250 : (a == 10 ? 10500 : (a == 11 ? 10750 : (a == 12 ? 11000 : (a == 13 ? 11250 : (a == 14 ? 11500 : (a == 15 ? 11750 : (a == 16 ? 12000 : 1))))))))))))))));
    }

    public int Shuriken(ItemStack i)
    {
        int a = this.getEnchantSpear(i);
        return a == 0 ? 2600 : (a == 1 ? 2860 : (a == 2 ? 3250 : (a == 3 ? 3770 : (a == 4 ? 4420 : (a == 5 ? 5200 : (a == 6 ? 6110 : (a == 7 ? 7150 : (a == 8 ? 8320 : (a == 9 ? 9620 : (a == 10 ? 11050 : (a == 11 ? 12610 : (a == 12 ? 14300 : (a == 13 ? 16120 : (a == 14 ? 18070 : (a == 15 ? 20150 : (a == 16 ? 22360 : 1))))))))))))))));
    }
}

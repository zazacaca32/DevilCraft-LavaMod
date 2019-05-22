package net.minecraft.client.addon.tchestplate;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;

public class tabItemss extends CreativeTabs
{
    public tabItemss(int position, String tabID)
    {
        super(position, tabID);
    }

    @SideOnly(Side.CLIENT)
    public int getTabIconItemIndex()
    {
        return LavaChestPlate.itemCrystalPalladium.itemID;
    }

    public String getTranslatedTabLabel()
    {
        return "LavaItems";
    }
}

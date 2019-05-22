package net.minecraft.client.addon.tchestplate;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;

public class tabArmors extends CreativeTabs
{
    public tabArmors(int position, String tabID)
    {
        super(position, tabID);
    }

    @SideOnly(Side.CLIENT)
    public int getTabIconItemIndex()
    {
        return LavaChestPlate.itemCrystalCobalt.itemID;
    }

    public String getTranslatedTabLabel()
    {
        return "LavaArmor";
    }
}

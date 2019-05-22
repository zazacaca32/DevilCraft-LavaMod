package net.minecraft.client.addon.tchestplate;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.creativetab.CreativeTabs;

public class tabUnder extends CreativeTabs
{
    public tabUnder(int position, String tabID)
    {
        super(position, tabID);
    }

    @SideOnly(Side.CLIENT)
    public int getTabIconItemIndex()
    {
        return LavaChestPlate.itemCrystalAdamantite.itemID;
    }

    public String getTranslatedTabLabel()
    {
        return "LavaBlocks";
    }
}

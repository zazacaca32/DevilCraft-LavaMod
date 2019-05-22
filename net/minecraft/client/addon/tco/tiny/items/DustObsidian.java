package net.minecraft.client.addon.tco.tiny.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public class DustObsidian extends Item {

   protected Icon icon;


   public DustObsidian(int itemID) {
      super(itemID);
      this.setMaxStackSize(64);
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IconRegister par1IconRegister) {
      this.icon = par1IconRegister.registerIcon("tco:" + this.getUnlocalizedName().substring(5));
   }

   @SideOnly(Side.CLIENT)
   public Icon getIconFromDamage(int damag) {
      return this.icon;
   }
}

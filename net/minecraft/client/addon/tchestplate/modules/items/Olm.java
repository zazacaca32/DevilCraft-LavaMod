package net.minecraft.client.addon.tchestplate.modules.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class Olm extends Item {

   public Olm(int par1) {
      super(par1);
      this.setMaxStackSize(1);
   }

   public void registerIcons(IconRegister par1IconRegister) {
      super.itemIcon = par1IconRegister.registerIcon("aamodules:OGNLeggins_m");
   }
}

package net.minecraft.client.addon.tchestplate.modules.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class PredatorBootsModule extends Item {

   public PredatorBootsModule(int par1) {
      super(par1);
      this.setMaxStackSize(1);
   }

   public void registerIcons(IconRegister par1IconRegister) {
      super.itemIcon = par1IconRegister.registerIcon("aamodules:predatorboots_m");
   }
}

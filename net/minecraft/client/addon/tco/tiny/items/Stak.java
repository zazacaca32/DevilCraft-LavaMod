package net.minecraft.client.addon.tco.tiny.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class Stak extends Item {

   public Stak(int par1) {
      super(par1);
   }

   public void registerIcons(IconRegister par1IconRegister) {
      super.itemIcon = par1IconRegister.registerIcon("provider:stak");
   }
}

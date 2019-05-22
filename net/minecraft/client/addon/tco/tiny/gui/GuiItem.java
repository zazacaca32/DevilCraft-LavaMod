package net.minecraft.client.addon.tco.tiny.gui;

import net.minecraft.item.ItemStack;

public class GuiItem {

   public String name;
   public ItemStack itemstack;


   public GuiItem(ItemStack item, String name) {
      this.itemstack = item;
      this.name = name;
   }
}

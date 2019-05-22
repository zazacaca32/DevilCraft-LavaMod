package net.minecraft.client.addon.tco.tiny.blocks.gui;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class LATab {

   private boolean isSelected = false;
   private int col;
   private ItemStack is;
   private String unlocaizedName;


   public LATab(String _unlocaizedName, ItemStack _is, int _col, boolean _isSelected) {
      this.unlocaizedName = _unlocaizedName;
      this.isSelected = _isSelected;
      this.col = _col;
      this.is = _is;
   }

   public String getDisplayName() {
      return StatCollector.translateToLocal(this.unlocaizedName);
   }

   public boolean isSelected() {
      return this.isSelected;
   }

   public boolean isOnTop() {
      return true;
   }

   public int getTabColumn() {
      return this.col;
   }

   public ItemStack getItemStack() {
      return this.is;
   }
}

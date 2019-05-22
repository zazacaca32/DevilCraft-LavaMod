package net.minecraft.client.addon.tco.tiny.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class GuiSlotItem {

   public String name;
   public ItemStack itemstack;
   public int xDisplayPosition = 0;
   public int yDisplayPosition = 0;
   public int slotNumber;
   public int SlotStackLimit = 64;
   public int SlotStackCost = 1;
   protected Icon backgroundIcon = null;
   protected String texture = "/gui/items.png";


   public GuiSlotItem(ItemStack item, String name, int SlotStackLimit, int SlotStackCost, int slotNumber, int xPosition, int yPosition) {
      this.itemstack = item;
      this.name = name;
      this.xDisplayPosition = xPosition;
      this.yDisplayPosition = yPosition;
      this.slotNumber = slotNumber;
      this.SlotStackLimit = SlotStackLimit;
      this.SlotStackCost = SlotStackCost;
   }

   public ItemStack getStack() {
      return this.itemstack;
   }

   public void setSlotStackCopy(GuiSlotItem slot) {
      if(slot == null) {
         this.itemstack = null;
         this.SlotStackCost = 1;
         this.SlotStackLimit = 64;
      } else {
         this.itemstack = slot.itemstack.copy();
         this.SlotStackCost = slot.SlotStackCost;
         this.SlotStackLimit = slot.getSlotStackLimit();
      }

   }

   public boolean getHasStack() {
      return this.getStack() != null;
   }

   public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
      return true;
   }

   @SideOnly(Side.CLIENT)
   public Icon getBackgroundIconIndex() {
      return this.backgroundIcon;
   }

   public String getBackgroundIconTexture() {
      return this.texture == null?"/gui/items.png":this.texture;
   }

   public void setBackgroundIconIndex(Icon icon) {
      this.backgroundIcon = icon;
   }

   public void setBackgroundIconTexture(String textureFilename) {
      this.texture = textureFilename;
   }

   public int getSlotIndex() {
      return this.slotNumber;
   }

   public int getSlotStackLimit() {
      return this.SlotStackLimit;
   }

   public boolean isItemValid(ItemStack itemstack2) {
      return true;
   }
}

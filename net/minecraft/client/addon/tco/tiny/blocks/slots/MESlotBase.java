package net.minecraft.client.addon.tco.tiny.blocks.slots;

import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class MESlotBase extends Slot {

   public int icon = -1;
   public MESlotBase.hasCalculatedValidness isValid;
   public boolean isDisplay;


   public String getTooltip() {
      return null;
   }

   public void onSlotChanged() {
      super.onSlotChanged();
      this.isValid = MESlotBase.hasCalculatedValidness.NotAvailable;
   }

   public MESlotBase(IInventory par1iInventory, int par2, int par3, int par4) {
      super(par1iInventory, par2, par3, par4);
      this.isValid = MESlotBase.hasCalculatedValidness.NotAvailable;
      this.isDisplay = false;
   }

   public ItemStack getStack() {
      if(this.isDisplay) {
         this.isDisplay = false;
         return this.getDisplayStack();
      } else {
         return super.getStack();
      }
   }

   public ItemStack getDisplayStack() {
      return super.getStack();
   }

   public float getOpacityOfIcon() {
      return 0.4F;
   }

   public boolean renderIconWithItem() {
      return false;
   }

   public int getIcon() {
      return this.icon;
   }

   public LAItemStack getAEStackLA() {
      return null;
   }

   public static enum hasCalculatedValidness {

      NotAvailable("NotAvailable", 0, "NotAvailable", 0, "NotAvailable", 0, "NotAvailable", 0, "NotAvailable", 0, "NotAvailable", 0, "NotAvailable", 0, "NotAvailable", 0),
      Valid("Valid", 1, "Valid", 1, "Valid", 1, "Valid", 1, "Valid", 1, "Valid", 1, "Valid", 1, "Valid", 1),
      Invalid("Invalid", 2, "Invalid", 2, "Invalid", 2, "Invalid", 2, "Invalid", 2, "Invalid", 2, "Invalid", 2, "Invalid", 2);
      private static final MESlotBase.hasCalculatedValidness[] $VALUES = new MESlotBase.hasCalculatedValidness[]{NotAvailable, Valid, Invalid};
      private static final MESlotBase.hasCalculatedValidness[] $VALUES$ = new MESlotBase.hasCalculatedValidness[]{NotAvailable, Valid, Invalid};
      private static final MESlotBase.hasCalculatedValidness[] $VALUES$$ = new MESlotBase.hasCalculatedValidness[]{NotAvailable, Valid, Invalid};
      private static final MESlotBase.hasCalculatedValidness[] $VALUES$$$ = new MESlotBase.hasCalculatedValidness[]{NotAvailable, Valid, Invalid};
      private static final MESlotBase.hasCalculatedValidness[] $VALUES$$$$ = new MESlotBase.hasCalculatedValidness[]{NotAvailable, Valid, Invalid};
      private static final MESlotBase.hasCalculatedValidness[] $VALUES$$$$$ = new MESlotBase.hasCalculatedValidness[]{NotAvailable, Valid, Invalid};
      // $FF: synthetic field
      private static final MESlotBase.hasCalculatedValidness[] $VALUES$$$$$$ = new MESlotBase.hasCalculatedValidness[]{NotAvailable, Valid, Invalid};


      private hasCalculatedValidness(String var1, int var2, String var11, int var21, String var111, int var211, String s, int v2ar21, String var11231, int var12321, String zz, int va2r211, String ds, int n, String var1111, int var2111) {}

   }
}

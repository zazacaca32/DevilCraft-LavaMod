package net.minecraft.client.addon.tco.tiny.blocks.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class SlotInv extends Slot {

   public SlotInv.Access access;


   public SlotInv(SlotInv.Access access, IInventory par1iInventory, int SlotIndex, int X, int Y) {
      super(par1iInventory, SlotIndex, X, Y);
      this.access = access;
   }

   public abstract boolean accept(ItemStack var1);

   public boolean isItemValid(ItemStack stack) {
      return this.canInput() && this.accept(stack);
   }

   public boolean canTakeStack(EntityPlayer player) {
      return this.canOutput();
   }

   public boolean canInput() {
      return this.access == SlotInv.Access.I || this.access == SlotInv.Access.IO;
   }

   public static boolean canInput(SlotInv.Access access) {
      return access == SlotInv.Access.I || access == SlotInv.Access.IO;
   }

   public boolean canOutput() {
      return this.access == SlotInv.Access.O || this.access == SlotInv.Access.IO;
   }

   public static enum Access {

      NONE("NONE", 0, "NONE", 0, "NONE", 0, "NONE", 0, "NONE", 0, "NONE", 0),
      I("I", 1, "I", 1, "I", 1, "I", 1, "I", 1, "I", 1),
      O("O", 2, "O", 2, "O", 2, "O", 2, "O", 2, "O", 2),
      IO("IO", 3, "IO", 3, "IO", 3, "IO", 3, "IO", 3, "IO", 3);
      private static final SlotInv.Access[] $VALUES = new SlotInv.Access[]{NONE, I, O, IO};
      private static final SlotInv.Access[] $VALUES$ = new SlotInv.Access[]{NONE, I, O, IO};
      private static final SlotInv.Access[] $VALUES$$ = new SlotInv.Access[]{NONE, I, O, IO};
      private static final SlotInv.Access[] $VALUES$$$ = new SlotInv.Access[]{NONE, I, O, IO};
      // $FF: synthetic field
      private static final SlotInv.Access[] $VALUES$$$$ = new SlotInv.Access[]{NONE, I, O, IO};


      private Access(String var1, int var2, String var11, int var21, String csz, int asc, String var11231, int var21231, String var111, int var211, String s, int n) {}

   }
}

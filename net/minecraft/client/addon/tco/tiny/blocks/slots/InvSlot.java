package net.minecraft.client.addon.tco.tiny.blocks.slots;

import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTrader;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InvSlot {

   public final TileEntityBlockTrader base;
   public final String name;
   public final int oldStartIndex;
   private final ItemStack[] contents;
   protected final InvSlot.Access access;
   public final InvSlot.InvSide preferredSide;


   public InvSlot(TileEntityBlockTrader base, String name, int oldStartIndex, InvSlot.Access access, int count) {
      this(base, name, oldStartIndex, access, count, InvSlot.InvSide.ANY);
   }

   public InvSlot(TileEntityBlockTrader base, String name, int oldStartIndex, InvSlot.Access access, int count, InvSlot.InvSide preferredSide) {
      this.contents = new ItemStack[count];
      this.base = base;
      this.name = name;
      this.oldStartIndex = oldStartIndex;
      this.access = access;
      this.preferredSide = preferredSide;
   }

   public void readFromNbt(NBTTagCompound nbtTagCompound) {
      NBTTagList contentsTag = nbtTagCompound.getTagList("Contents");

      for(int i = 0; i < contentsTag.tagCount(); ++i) {
         NBTTagCompound contentTag = (NBTTagCompound)contentsTag.tagAt(i);
         int index = contentTag.getByte("Index") & 255;
         this.put(index, ItemStack.loadItemStackFromNBT(contentTag));
      }

   }

   public void writeToNbt(NBTTagCompound nbtTagCompound) {
      NBTTagList contentsTag = new NBTTagList();

      for(int i = 0; i < this.contents.length; ++i) {
         if(this.contents[i] != null) {
            NBTTagCompound contentTag = new NBTTagCompound();
            contentTag.setByte("Index", (byte)i);
            this.contents[i].writeToNBT(contentTag);
            contentsTag.appendTag(contentTag);
         }
      }

      nbtTagCompound.setTag("Contents", contentsTag);
   }

   public int size() {
      return this.contents.length;
   }

   public ItemStack get() {
      return this.get(0);
   }

   public ItemStack get(int index) {
      return this.contents[index];
   }

   public void put(ItemStack content) {
      this.put(0, content);
   }

   public void put(int index, ItemStack content) {
      this.contents[index] = content;
   }

   public void clear() {
      for(int i = 0; i < this.contents.length; ++i) {
         this.contents[i] = null;
      }

   }

   public boolean accepts(ItemStack itemStack) {
      return true;
   }

   public boolean canInput() {
      return this.access == InvSlot.Access.I || this.access == InvSlot.Access.IO;
   }

   public boolean canOutput() {
      return this.access == InvSlot.Access.O || this.access == InvSlot.Access.IO;
   }

   public boolean isEmpty() {
      ItemStack[] arr$ = this.contents;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ItemStack itemStack = arr$[i$];
         if(itemStack != null) {
            return false;
         }
      }

      return true;
   }

   public void organize() {
      for(int dstIndex = 0; dstIndex < this.contents.length - 1; ++dstIndex) {
         ItemStack dst = this.contents[dstIndex];
         if(dst == null || dst.stackSize < dst.getMaxStackSize()) {
            for(int srcIndex = dstIndex + 1; srcIndex < this.contents.length; ++srcIndex) {
               ItemStack src = this.contents[srcIndex];
               if(src != null) {
                  if(dst == null) {
                     this.contents[srcIndex] = null;
                     dst = this.contents[dstIndex] = src;
                  } else if(Utils.isStackEqual(dst, src)) {
                     int space = dst.getMaxStackSize() - dst.stackSize;
                     if(src.stackSize > space) {
                        src.stackSize -= space;
                        dst.stackSize += space;
                        break;
                     }

                     this.contents[srcIndex] = null;
                     dst.stackSize += src.stackSize;
                  }
               }
            }
         }
      }

   }

   public String toString() {
      String ret = this.name + "[" + this.contents.length + "]: ";

      for(int i = 0; i < this.contents.length; ++i) {
         ret = ret + this.contents[i];
         if(i < this.contents.length - 1) {
            ret = ret + ", ";
         }
      }

      return ret;
   }

   public static enum Access {

      NONE("NONE", 0, "NONE", 0, "NONE", 0, "NONE", 0, "NONE", 0, "NONE", 0, "NONE", 0),
      I("I", 1, "I", 1, "I", 1, "I", 1, "I", 1, "I", 1, "I", 1),
      O("O", 2, "O", 2, "O", 2, "O", 2, "O", 2, "O", 2, "O", 2),
      IO("IO", 3, "IO", 3, "IO", 3, "IO", 3, "IO", 3, "IO", 3, "IO", 3);
      private static final InvSlot.Access[] $VALUES = new InvSlot.Access[]{NONE, I, O, IO};
      private static final InvSlot.Access[] $VALUES$ = new InvSlot.Access[]{NONE, I, O, IO};
      private static final InvSlot.Access[] $VALUES$$ = new InvSlot.Access[]{NONE, I, O, IO};
      private static final InvSlot.Access[] $VALUES$$$ = new InvSlot.Access[]{NONE, I, O, IO};
      private static final InvSlot.Access[] $VALUES$$$$ = new InvSlot.Access[]{NONE, I, O, IO};
      // $FF: synthetic field
      private static final InvSlot.Access[] $VALUES$$$$$ = new InvSlot.Access[]{NONE, I, O, IO};


      private Access(String var1, int var2, String var11, int var21, String dddddds, int ffffs, String var121, int var221, String var12311, int var12321, String var111, int var211, String s, int n) {}

   }

   public static enum InvSide {

      ANY("ANY", 0, "ANY", 0, "ANY", 0, "ANY", 0, "ANY", 0, "ANY", 0, "ANY", 0),
      TOP("TOP", 1, "TOP", 1, "TOP", 1, "TOP", 1, "TOP", 1, "TOP", 1, "TOP", 1),
      BOTTOM("BOTTOM", 2, "BOTTOM", 2, "BOTTOM", 2, "BOTTOM", 2, "BOTTOM", 2, "BOTTOM", 2, "BOTTOM", 2),
      SIDE("SIDE", 3, "SIDE", 3, "SIDE", 3, "SIDE", 3, "SIDE", 3, "SIDE", 3, "SIDE", 3);
      private static final InvSlot.InvSide[] $VALUES = new InvSlot.InvSide[]{ANY, TOP, BOTTOM, SIDE};
      private static final InvSlot.InvSide[] $VALUES$ = new InvSlot.InvSide[]{ANY, TOP, BOTTOM, SIDE};
      private static final InvSlot.InvSide[] $VALUES$$ = new InvSlot.InvSide[]{ANY, TOP, BOTTOM, SIDE};
      private static final InvSlot.InvSide[] $VALUES$$$ = new InvSlot.InvSide[]{ANY, TOP, BOTTOM, SIDE};
      private static final InvSlot.InvSide[] $VALUES$$$$ = new InvSlot.InvSide[]{ANY, TOP, BOTTOM, SIDE};
      // $FF: synthetic field
      private static final InvSlot.InvSide[] $VALUES$$$$$ = new InvSlot.InvSide[]{ANY, TOP, BOTTOM, SIDE};


      private InvSide(String var1, int var2, String var11, int var21, String rrs, int rrrs, String var211, int var221, String v123ar11, int var12321, String var111, int var2211, String s, int n) {}

      public boolean matches(int side) {
         return this == ANY || side == 0 && this == BOTTOM || side == 1 && this == TOP || side >= 2 && side <= 5 && this == SIDE;
      }

   }
}

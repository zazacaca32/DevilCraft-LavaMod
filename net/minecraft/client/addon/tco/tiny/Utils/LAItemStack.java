package net.minecraft.client.addon.tco.tiny.Utils;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;

public final class LAItemStack implements Comparable {

   private int def;
   private int itemID;
   private int damageValue;
   private NBTTagCompound tagCompound;
   private int myHash;
   private boolean isCraftable;
   private long stackSize;
   private long countRequestable;
   public int priority;
   @SideOnly(Side.CLIENT)
   private String displayName;
   @SideOnly(Side.CLIENT)
   private List tooltip;


   public void add(LAItemStack option) {
      if(this.priority < option.priority) {
         this.priority = option.priority;
      }

      this.incStackSize(option.getStackSize());
      this.setCountRequestable(this.getCountRequestable() + option.getCountRequestable());
      this.setCraftable(this.isCraftable() || option.isCraftable());
   }

   private LAItemStack(LAItemStack is) {
      this.itemID = is.itemID;
      this.damageValue = is.damageValue;
      this.def = is.def;
      this.stackSize = is.stackSize;
      this.tagCompound = is.tagCompound;
      this.priority = is.priority;
      this.setCraftable(is.isCraftable());
      this.setCountRequestable(is.getCountRequestable());
      this.myHash = is.myHash;
   }

   protected LAItemStack(ItemStack is) {
      if(is == null) {
         throw new RuntimeException("Invalid Itemstack.");
      } else {
         this.itemID = is.itemID;
         this.damageValue = Item.blazeRod.getItemDamageFromStack(is);
         this.def = this.damageValue << 16 | this.itemID;
         this.stackSize = (long)is.stackSize;
         this.tagCompound = is.getTagCompound();
         this.setCraftable(false);
         this.setCountRequestable(0L);
         this.myHash = this.def ^ (this.tagCompound == null?0:System.identityHashCode(this.tagCompound));
      }
   }

   public static LAItemStack create(Object a) {
      if(a == null) {
         return null;
      } else {
         if(a instanceof LAItemStack) {
            ((LAItemStack)a).copy();
         }

         return a instanceof ItemStack?new LAItemStack((ItemStack)a):null;
      }
   }

   public Item getItem() {
      return Item.itemsList[this.itemID];
   }

   public boolean eq(Object ia) {
      NBTTagCompound ta;
      if(ia instanceof LAItemStack) {
         if(((LAItemStack)ia).getDef() != this.getDef()) {
            return false;
         } else {
            NBTTagCompound is1 = this.tagCompound;
            ta = ((LAItemStack)ia).getTagCompound();
            return is1 == ta?true:((is1 != null || ta != null) && (is1 == null || !is1.hasNoTags() || ta != null) && (ta == null || !ta.hasNoTags() || is1 != null) && (is1 == null || !is1.hasNoTags() || ta == null || !ta.hasNoTags())?((is1 != null || ta == null) && (is1 == null || ta != null)?Utils.NBTEqualityTest(is1, ta):false):true);
         }
      } else {
         if(ia instanceof ItemStack) {
            ItemStack is = (ItemStack)ia;
            if(is.itemID == this.itemID && is.getItemDamage() == this.damageValue) {
               ta = this.tagCompound;
               NBTTagCompound tb = is.getTagCompound();
               if(ta == tb) {
                  return true;
               }

               if((ta != null || tb != null) && (ta == null || !ta.hasNoTags() || tb != null) && (tb == null || !tb.hasNoTags() || ta != null) && (ta == null || !ta.hasNoTags() || tb == null || !tb.hasNoTags())) {
                  if((ta != null || tb == null) && (ta == null || tb != null)) {
                     return Utils.NBTEqualityTest(ta, tb);
                  }

                  return false;
               }

               return true;
            }
         }

         return false;
      }
   }

   public boolean equals(Object ia) {
      if(ia instanceof LAItemStack) {
         return ((LAItemStack)ia).getDef() == this.getDef() && this.tagCompound == ((LAItemStack)ia).tagCompound;
      } else {
         if(ia instanceof ItemStack) {
            ItemStack is = (ItemStack)ia;
            if(is.itemID == this.itemID && is.getItemDamage() == this.damageValue) {
               NBTTagCompound ta = this.tagCompound;
               NBTTagCompound tb = is.getTagCompound();
               if(ta == tb) {
                  return true;
               }

               if((ta != null || tb != null) && (ta == null || !ta.hasNoTags() || tb != null) && (tb == null || !tb.hasNoTags() || ta != null) && (ta == null || !ta.hasNoTags() || tb == null || !tb.hasNoTags())) {
                  if((ta != null || tb == null) && (ta == null || tb != null)) {
                     return Utils.NBTEqualityTest(ta, tb);
                  }

                  return false;
               }

               return true;
            }
         }

         return false;
      }
   }

   public ItemStack getSharedItemStack() {
      ItemStack is = new ItemStack(this.itemID, (int)Math.min(2147483647L, this.stackSize), this.damageValue);
      if(this.tagCompound != null) {
         is.setTagCompound(this.tagCompound);
      }

      return is;
   }

   public ItemStack getItemStackLowIdDamage() {
      ItemStack is = new ItemStack(this.itemID, (int)Math.min(2147483647L, this.stackSize), this.damageValue);
      return is;
   }

   public ItemStack getItemStack() {
      ItemStack is = new ItemStack(this.itemID, (int)Math.min(2147483647L, this.stackSize), this.damageValue);
      if(this.tagCompound != null) {
         is.setTagCompound((NBTTagCompound)this.tagCompound.copy());
      }

      return is;
   }

   public int getItemID() {
      return this.itemID;
   }

   public int getItemDamage() {
      return this.damageValue;
   }

   public NBTTagCompound getTagCompound() {
      return this.tagCompound;
   }

   public boolean isMeaninful() {
      return this.stackSize > 0L || this.getCountRequestable() > 0L || this.isCraftable();
   }

   public LAItemStack copy() {
      return new LAItemStack(this);
   }

   public void reset() {
      this.stackSize = 0L;
      this.priority = Integer.MIN_VALUE;
      this.setCountRequestable(0L);
      this.setCraftable(false);
   }

   public long getStackSize() {
      return this.stackSize;
   }

   public void setStackSize(long ss) {
      this.stackSize = ss;
   }

   public void writeToNBT(NBTTagCompound i) {
      NBTBase id = i.getTag("id");
      NBTBase Count = i.getTag("Count");
      NBTBase Priority = i.getTag("Priority");
      NBTBase Cnt = i.getTag("Cnt");
      NBTBase Req = i.getTag("Req");
      NBTBase Craft = i.getTag("Craft");
      NBTBase Damage = i.getTag("Damage");
      if(id != null && id instanceof NBTTagShort) {
         ((NBTTagShort)id).data = (short)this.itemID;
      } else {
         i.setShort("id", (short)this.itemID);
      }

      if(Priority != null && Priority instanceof NBTTagInt) {
         ((NBTTagInt)Priority).data = this.priority;
      } else {
         i.setInteger("Priority", this.priority);
      }

      if(Count != null && Count instanceof NBTTagByte) {
         ((NBTTagByte)Count).data = 0;
      } else {
         i.setByte("Count", (byte)0);
      }

      if(Cnt != null && Cnt instanceof NBTTagLong) {
         ((NBTTagLong)Cnt).data = this.stackSize;
      } else {
         i.setLong("Cnt", this.stackSize);
      }

      if(Req != null && Req instanceof NBTTagLong) {
         ((NBTTagLong)Req).data = this.stackSize;
      } else {
         i.setLong("Req", this.getCountRequestable());
      }

      if(Craft != null && Craft instanceof NBTTagByte) {
         ((NBTTagByte)Craft).data = (byte)(this.isCraftable()?1:0);
      } else {
         i.setBoolean("Craft", this.isCraftable());
      }

      if(Damage != null && Damage instanceof NBTTagShort) {
         ((NBTTagShort)Damage).data = (short)this.damageValue;
      } else {
         i.setShort("Damage", (short)this.damageValue);
      }

      if(this.tagCompound != null) {
         i.setTag("tag", this.tagCompound);
      } else {
         i.removeTag("tag");
      }

   }

   public static LAItemStack loadItemStackFromNBT(NBTTagCompound i) {
      ItemStack itemstack = ItemStack.loadItemStackFromNBT(i);
      if(itemstack == null) {
         return null;
      } else {
         LAItemStack aeis = create(itemstack);
         aeis.priority = i.getInteger("Priority");
         aeis.stackSize = i.getLong("Cnt");
         aeis.setCountRequestable(i.getLong("Req"));
         aeis.setCraftable(i.getBoolean("Craft"));
         return aeis;
      }
   }

   public long getCountRequestable() {
      return this.countRequestable;
   }

   public void setCountRequestable(long countRequestable) {
      this.countRequestable = countRequestable;
   }

   public boolean isCraftable() {
      return this.isCraftable;
   }

   public void setCraftable(boolean isCraftable) {
      this.isCraftable = isCraftable;
   }

   public int getDef() {
      return this.def;
   }

   public boolean hasTagCompound() {
      return this.tagCompound != null;
   }

   public void decStackSize(long i) {
      this.stackSize -= i;
   }

   public void incStackSize(long i) {
      this.stackSize += i;
   }

   public void decCountRequestable(long i) {
      this.countRequestable -= i;
   }

   public void incCountRequestable(long i) {
      this.countRequestable += i;
   }

   public int hashCode() {
      return this.myHash;
   }

   public int compareTo(LAItemStack b) {
      int diff = this.hashCode() - b.hashCode();
      return diff < 0?-1:(diff > 0?1:0);
   }

   @SideOnly(Side.CLIENT)
   public List getToolTip() {
      if(this.tooltip != null) {
         return this.tooltip;
      } else {
         this.tooltip = Utils.getTooltip(this.getItemStack());
         return this.tooltip;
      }
   }

   @SideOnly(Side.CLIENT)
   public String getDisplayName() {
      if(this.displayName != null) {
         return this.displayName;
      } else {
         this.displayName = Utils.getItemDisplayName(this.getItemStack());
         return this.displayName;
      }
   }

   void putPacketValue(DataOutputStream tag, long num) throws IOException {
      if(num <= 255L) {
         tag.writeByte((byte)((int)(num + -128L)));
      } else if(num <= 65535L) {
         tag.writeShort((short)((int)(num + -32768L)));
      } else if(num <= 4294967295L) {
         tag.writeInt((int)(num + -2147483648L));
      } else {
         tag.writeLong(num);
      }

   }

   static long getPacketValue(byte type, DataInputStream tag) throws IOException {
      long l;
      if(type == 0) {
         l = (long)tag.readByte();
         return l -= -128L;
      } else if(type == 1) {
         l = (long)tag.readShort();
         return l -= -32768L;
      } else if(type == 2) {
         l = (long)tag.readInt();
         return l -= -2147483648L;
      } else {
         return tag.readLong();
      }
   }

   byte getType(long num) {
      return (byte)(num <= 255L?0:(num <= 65535L?1:(num <= 4294967295L?2:3)));
   }

   public void writeToPacket(DataOutputStream i) throws IOException {
      byte mask = (byte)(this.getType((long)this.priority) | this.getType(this.stackSize) << 2 | this.getType(this.getCountRequestable()) << 4 | (this.isCraftable?1:0) << 6 | (this.hasTagCompound()?1:0) << 7);
      i.writeByte(mask);
      i.writeShort(this.getItemID());
      i.writeShort(this.getItemDamage());
      if(this.hasTagCompound()) {
         ByteArrayOutputStream bytes = new ByteArrayOutputStream();
         DataOutputStream data = new DataOutputStream(bytes);
         CompressedStreamTools.write(this.getTagCompound(), data);
         byte[] tagBytes = bytes.toByteArray();
         int size = tagBytes.length;
         i.writeInt(size);
         i.write(tagBytes);
      }

      this.putPacketValue(i, (long)this.priority);
      this.putPacketValue(i, this.stackSize);
      this.putPacketValue(i, this.getCountRequestable());
   }

   public static LAItemStack loadItemStackFromPacket(DataInputStream data) throws IOException {
      byte mask = data.readByte();
      byte PriorityType = (byte)(mask & 3);
      byte StackType = (byte)((mask & 12) >> 2);
      byte CountReqType = (byte)((mask & 48) >> 4);
      boolean isCraftable = (mask & 64) > 0;
      boolean hasTagCompound = (mask & 128) > 0;
      NBTTagCompound d = new NBTTagCompound();
      d.setShort("id", data.readShort());
      d.setShort("Damage", data.readShort());
      d.setByte("Count", (byte)0);
      if(hasTagCompound) {
         int priority11 = data.readInt();
         byte[] bd = new byte[priority11];
         data.readFully(bd);
         ByteArrayDataInput stackSize11 = ByteStreams.newDataInput((byte[])bd);
         d.setCompoundTag("tag", CompressedStreamTools.read(stackSize11));
      }

      long priority111 = getPacketValue(PriorityType, data);
      long stackSize111 = getPacketValue(StackType, data);
      long countRequestable = getPacketValue(CountReqType, data);
      ItemStack itemstack = ItemStack.loadItemStackFromNBT(d);
      if(itemstack == null) {
         return null;
      } else {
         LAItemStack aeis = create(itemstack);
         aeis.priority = (int)priority111;
         aeis.stackSize = stackSize111;
         aeis.setCountRequestable(countRequestable);
         aeis.setCraftable(isCraftable);
         return aeis;
      }
   }

   public int compareTo(Object arg0) {
      return 0;
   }
}

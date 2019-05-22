package codechicken.core.data;

import codechicken.core.data.MCDataOutput;
import codechicken.core.vec.BlockCoord;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.liquids.LiquidStack;

public class MCOutputStreamWrapper implements MCDataOutput {

   public DataOutputStream dataout;


   public MCOutputStreamWrapper(DataOutputStream out) {
      this.dataout = out;
   }

   public MCOutputStreamWrapper writeBoolean(boolean b) {
      try {
         this.dataout.writeBoolean(b);
         return this;
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public MCOutputStreamWrapper writeByte(int b) {
      try {
         this.dataout.writeByte(b);
         return this;
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public MCOutputStreamWrapper writeShort(int s) {
      try {
         this.dataout.writeShort(s);
         return this;
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public MCOutputStreamWrapper writeInt(int i) {
      try {
         this.dataout.writeInt(i);
         return this;
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public MCOutputStreamWrapper writeFloat(float f) {
      try {
         this.dataout.writeFloat(f);
         return this;
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public MCOutputStreamWrapper writeDouble(double d) {
      try {
         this.dataout.writeDouble(d);
         return this;
      } catch (IOException var4) {
         throw new RuntimeException(var4);
      }
   }

   public MCOutputStreamWrapper writeLong(long l) {
      try {
         this.dataout.writeLong(l);
         return this;
      } catch (IOException var4) {
         throw new RuntimeException(var4);
      }
   }

   public MCOutputStreamWrapper writeChar(char c) {
      try {
         this.dataout.writeChar(c);
         return this;
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public MCOutputStreamWrapper writeByteArray(byte[] barray) {
      try {
         this.dataout.write(barray);
         return this;
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public MCOutputStreamWrapper writeCoord(int x, int y, int z) {
      this.writeInt(x);
      this.writeInt(y);
      this.writeInt(z);
      return this;
   }

   public MCOutputStreamWrapper writeCoord(BlockCoord coord) {
      this.writeInt(coord.x);
      this.writeInt(coord.y);
      this.writeInt(coord.z);
      return this;
   }

   public MCOutputStreamWrapper writeString(String s) {
      try {
         if(s.length() > '\uffff') {
            throw new IOException("String length: " + s.length() + "too long.");
         } else {
            this.dataout.writeShort(s.length());
            this.dataout.writeChars(s);
            return this;
         }
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public MCOutputStreamWrapper writeItemStack(ItemStack stack) {
      this.writeItemStack(stack, false);
      return this;
   }

   public MCOutputStreamWrapper writeItemStack(ItemStack stack, boolean large) {
      if(stack == null) {
         this.writeShort(-1);
      } else {
         this.writeShort(stack.itemID);
         if(large) {
            this.writeInt(stack.stackSize);
         } else {
            this.writeByte(stack.stackSize);
         }

         this.writeShort(stack.getItemDamage());
         this.writeNBTTagCompound(stack.stackTagCompound);
      }

      return this;
   }

   public MCOutputStreamWrapper writeNBTTagCompound(NBTTagCompound compound) {
      try {
         if(compound == null) {
            this.writeShort(-1);
         } else {
            byte[] e = CompressedStreamTools.compress(compound);
            this.writeShort((short)e.length);
            this.writeByteArray(e);
         }

         return this;
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public MCOutputStreamWrapper writeLiquidStack(LiquidStack liquid) {
      if(liquid == null) {
         this.writeShort(-1);
      } else {
         this.writeShort(liquid.itemID);
         this.writeInt(liquid.amount);
         this.writeShort(liquid.itemMeta);
      }

      return this;
   }
}

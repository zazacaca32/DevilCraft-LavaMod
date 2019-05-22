package net.minecraft.client.addon.tco.tiny.Utils;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import java.io.IOException;
import net.minecraft.block.Block;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.potion.Potion;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class DataEncoder {

   public static Object decode(ByteArrayDataInput in) throws IOException {
      byte type = in.readByte();
      short id3;
      int i;
      short meta2;
      int var8;
      byte var10;
      int var12;
      int var15;
      switch(type) {
      case 0:
         return Integer.valueOf(in.readInt());
      case 1:
         id3 = in.readShort();
         int[] var24 = new int[id3];

         for(i = 0; i < id3; ++i) {
            var24[i] = in.readInt();
         }

         return var24;
      case 2:
         return Short.valueOf(in.readShort());
      case 3:
         id3 = in.readShort();
         short[] var22 = new short[id3];

         for(i = 0; i < id3; ++i) {
            var22[i] = in.readShort();
         }

         return var22;
      case 4:
         return Byte.valueOf(in.readByte());
      case 5:
         id3 = in.readShort();
         byte[] var21 = new byte[id3];

         for(i = 0; i < id3; ++i) {
            var21[i] = in.readByte();
         }

         return var21;
      case 6:
         return Long.valueOf(in.readLong());
      case 7:
         id3 = in.readShort();
         long[] var19 = new long[id3];

         for(i = 0; i < id3; ++i) {
            var19[i] = in.readLong();
         }

         return var19;
      case 8:
         return Boolean.valueOf(in.readBoolean());
      case 9:
         id3 = in.readShort();
         boolean[] var17 = new boolean[id3];
         byte var25 = 0;

         for(var15 = 0; var15 < id3; ++var15) {
            if(var15 % 8 == 0) {
               var25 = in.readByte();
            }

            var17[var15] = (var25 & 1 << var15 % 8) != 0;
         }

         return var17;
      case 10:
         id3 = in.readShort();
         char[] var16 = new char[id3];

         for(i = 0; i < id3; ++i) {
            var16[i] = in.readChar();
         }

         return new String(var16);
      case 11:
         id3 = in.readShort();
         String[] var14 = new String[id3];

         for(i = 0; i < id3; ++i) {
            meta2 = in.readShort();
            char[] var251 = new char[meta2];

            for(int var26 = 0; var26 < meta2; ++var26) {
               var251[var26] = in.readChar();
            }

            var14[i] = new String(var251);
         }

         return var14;
      case 12:
         id3 = in.readShort();
         if(id3 == 0) {
            return null;
         }

         byte var13 = in.readByte();
         short var23 = in.readShort();
         ItemStack var18 = new ItemStack(id3, var13, var23);
         if(in.readBoolean()) {
            var18.stackTagCompound = CompressedStreamTools.read(in);
         }

         return var18;
      case 13:
         return NBTBase.readNamedTag(in);
      case 14:
         var10 = in.readByte();
         var12 = in.readInt();
         switch(var10) {
         case 0:
            return Block.blocksList[var12];
         case 1:
            return Item.itemsList[var12];
         case 2:
            return AchievementList.achievementList.get(var12);
         case 3:
            return Potion.potionTypes[var12];
         case 4:
            return Enchantment.enchantmentsList[var12];
         default:
            return Byte.valueOf(type);
         }
      case 15:
         var10 = in.readByte();
         var12 = in.readInt();
         i = 0;
         if(var10 == 1 || var10 == 2) {
            i = in.readInt();
         }

         var15 = in.readInt();
         switch(var10) {
         case 0:
            return new ChunkCoordIntPair(var12, var15);
         case 1:
            return new ChunkCoordinates(var12, i, var15);
         case 2:
            return new ChunkPosition(var12, i, var15);
         default:
            return Byte.valueOf(type);
         }
      case 16:
         var8 = in.readInt();
         var12 = in.readInt();
         i = in.readInt();
         var15 = in.readInt();
         return DimensionManager.getWorld(var8).getBlockTileEntity(var12, i, var15);
      case 17:
         var8 = in.readInt();
         return DimensionManager.getWorld(var8);
      case 18:
         return Float.valueOf(in.readFloat());
      case 19:
         id3 = in.readShort();
         float[] var11 = new float[id3];

         for(i = 0; i < id3; ++i) {
            var11[i] = in.readFloat();
         }

         return var11;
      case 20:
         return Double.valueOf(in.readDouble());
      case 21:
         id3 = in.readShort();
         double[] var9 = new double[id3];

         for(i = 0; i < id3; ++i) {
            var9[i] = in.readDouble();
         }

         return var9;
      case 22:
         id3 = in.readShort();
         if(id3 == 0) {
            return null;
         }

         long stackSize2 = in.readLong();
         meta2 = in.readShort();
         ItemStack stack2 = new ItemStack(id3, 1, meta2);
         if(in.readBoolean()) {
            stack2.stackTagCompound = CompressedStreamTools.read(in);
         }

         return LAItemStack.create(stack2);
      case 127:
         return null;
      default:
         return null;
      }
   }

   public static void encode(ByteArrayDataOutput os, Object o) throws IOException {
      if(o instanceof Integer) {
         os.writeByte(0);
         os.writeInt(((Integer)o).intValue());
      } else {
         int i;
         if(o instanceof int[]) {
            os.writeByte(1);
            int[] var191 = (int[])((int[])((int[])((int[])((int[])((int[])((int[])((int[])((int[])o))))))));
            os.writeShort(var191.length);

            for(i = 0; i < var191.length; ++i) {
               os.writeInt(var191[i]);
            }
         } else if(o instanceof Short) {
            os.writeByte(2);
            os.writeShort(((Integer)o).intValue());
         } else if(o instanceof short[]) {
            os.writeByte(3);
            short[] var6 = (short[])((short[])((short[])((short[])((short[])((short[])((short[])((short[])((short[])o))))))));
            os.writeShort(var6.length);

            for(i = 0; i < var6.length; ++i) {
               os.writeShort(var6[i]);
            }
         } else if(o instanceof Byte) {
            os.writeByte(4);
            os.writeByte(((Integer)o).intValue());
         } else if(o instanceof byte[]) {
            os.writeByte(5);
            byte[] var7 = (byte[])((byte[])((byte[])((byte[])((byte[])((byte[])((byte[])((byte[])((byte[])o))))))));
            os.writeShort(var7.length);

            for(i = 0; i < var7.length; ++i) {
               os.writeByte(var7[i]);
            }
         } else if(o instanceof Long) {
            os.writeByte(6);
            os.writeLong((long)((Integer)o).intValue());
         } else if(o instanceof long[]) {
            os.writeByte(7);
            long[] var8 = (long[])((long[])((long[])((long[])((long[])((long[])((long[])((long[])((long[])o))))))));
            os.writeShort(var8.length);

            for(i = 0; i < var8.length; ++i) {
               os.writeLong(var8[i]);
            }
         } else if(o instanceof Boolean) {
            os.writeByte(8);
            os.writeBoolean(((Boolean)o).booleanValue());
         } else if(o instanceof boolean[]) {
            os.writeByte(9);
            boolean[] var9 = (boolean[])((boolean[])((boolean[])((boolean[])((boolean[])((boolean[])((boolean[])((boolean[])((boolean[])o))))))));
            os.writeShort(var9.length);
            byte var15 = 0;

            for(int j = 0; j < var9.length; ++j) {
               if(j % 8 == 0 && j > 0) {
                  os.writeByte(var15);
                  var15 = 0;
               }

               var15 = (byte)(var15 | (var9[j]?1:0) << j % 8);
            }

            os.writeByte(var15);
         } else if(o instanceof String) {
            os.writeByte(10);
            String var10 = (String)o;
            os.writeShort(var10.length());
            os.writeChars(var10);
         } else if(o instanceof String[]) {
            os.writeByte(11);
            String[] var11 = (String[])((String[])((String[])((String[])((String[])((String[])((String[])((String[])((String[])o))))))));
            os.writeShort(var11.length);

            for(i = 0; i < var11.length; ++i) {
               os.writeShort(var11[i].length());
               os.writeChars(var11[i]);
            }
         } else if(o instanceof ItemStack) {
            os.writeByte(12);
            ItemStack var12 = (ItemStack)o;
            os.writeShort(var12.itemID);
            if(var12.itemID == 0) {
               return;
            }

            os.writeByte(var12.stackSize);
            os.writeShort(var12.getItemDamage());
            if((Item.itemsList[var12.itemID].isDamageable() || Item.itemsList[var12.itemID].getShareTag()) && var12.stackTagCompound != null) {
               os.writeBoolean(true);
               CompressedStreamTools.write(var12.stackTagCompound, os);
            } else {
               os.writeBoolean(false);
            }
         } else if(o instanceof NBTBase) {
            os.writeByte(13);
            NBTBase.writeNamedTag((NBTBase)o, os);
         } else if(o instanceof Block) {
            os.writeByte(14);
            os.writeByte(0);
            os.writeInt(((Block)o).blockID);
         } else if(o instanceof Item) {
            os.writeByte(14);
            os.writeByte(1);
            os.writeInt(((Item)o).itemID);
         } else if(o instanceof Achievement) {
            os.writeByte(14);
            os.writeByte(2);
            os.writeInt(((StatBase)o).statId);
         } else if(o instanceof Potion) {
            os.writeByte(14);
            os.writeByte(3);
            os.writeInt(((Potion)o).id);
         } else if(o instanceof Enchantment) {
            os.writeByte(14);
            os.writeByte(4);
            os.writeInt(((Enchantment)o).effectId);
         } else if(o instanceof ChunkCoordinates) {
            os.writeByte(15);
            os.writeByte(0);
            ChunkCoordinates var13 = (ChunkCoordinates)o;
            os.writeInt(var13.posX);
            os.writeInt(var13.posY);
            os.writeInt(var13.posZ);
         } else if(o instanceof ChunkCoordIntPair) {
            os.writeByte(15);
            os.writeByte(1);
            ChunkCoordIntPair var14 = (ChunkCoordIntPair)o;
            os.writeInt(var14.chunkXPos);
            os.writeInt(var14.getCenterZPosition() - 8 >> 4);
         } else if(o instanceof ChunkPosition) {
            os.writeByte(15);
            os.writeByte(1);
            ChunkPosition var151 = (ChunkPosition)o;
            os.writeInt(var151.x);
            os.writeInt(var151.y);
            os.writeInt(var151.z);
         } else if(o instanceof TileEntity) {
            os.writeByte(16);
            TileEntity var16 = (TileEntity)o;
            os.writeInt(var16.worldObj.provider.dimensionId);
            os.writeInt(var16.xCoord);
            os.writeInt(var16.yCoord);
            os.writeInt(var16.zCoord);
         } else if(o instanceof World) {
            os.writeByte(17);
            os.writeInt(((World)o).provider.dimensionId);
         } else if(o instanceof Float) {
            os.writeByte(18);
            os.writeFloat((float)((Integer)o).intValue());
         } else if(o instanceof float[]) {
            os.writeByte(19);
            float[] var17 = (float[])((float[])((float[])((float[])((float[])((float[])((float[])((float[])((float[])o))))))));
            os.writeShort(var17.length);

            for(i = 0; i < var17.length; ++i) {
               os.writeFloat(var17[i]);
            }
         } else if(o instanceof Double) {
            os.writeByte(20);
            os.writeDouble((double)((Integer)o).intValue());
         } else if(o instanceof double[]) {
            os.writeByte(21);
            double[] var18 = (double[])((double[])((double[])((double[])((double[])((double[])((double[])((double[])((double[])o))))))));
            os.writeShort(var18.length);

            for(i = 0; i < var18.length; ++i) {
               os.writeDouble(var18[i]);
            }
         } else if(o instanceof LAItemStack) {
            os.writeByte(22);
            LAItemStack var19 = (LAItemStack)o;
            os.writeShort(var19.getItemID());
            if(var19.getItemID() == 0) {
               return;
            }

            os.writeLong(var19.getStackSize());
            os.writeShort(var19.getItemDamage());
            if((Item.itemsList[var19.getItemID()].isDamageable() || Item.itemsList[var19.getItemID()].getShareTag()) && var19.getTagCompound() != null) {
               os.writeBoolean(true);
               CompressedStreamTools.write(var19.getTagCompound(), os);
            } else {
               os.writeBoolean(false);
            }
         } else if(o == null) {
            os.writeByte(127);
         }
      }

   }
}

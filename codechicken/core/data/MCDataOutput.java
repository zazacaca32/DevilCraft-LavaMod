package codechicken.core.data;

import codechicken.core.vec.BlockCoord;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.liquids.LiquidStack;

public interface MCDataOutput {

   MCDataOutput writeLong(long var1);

   MCDataOutput writeInt(int var1);

   MCDataOutput writeShort(int var1);

   MCDataOutput writeByte(int var1);

   MCDataOutput writeDouble(double var1);

   MCDataOutput writeFloat(float var1);

   MCDataOutput writeBoolean(boolean var1);

   MCDataOutput writeChar(char var1);

   MCDataOutput writeByteArray(byte[] var1);

   MCDataOutput writeString(String var1);

   MCDataOutput writeCoord(int var1, int var2, int var3);

   MCDataOutput writeCoord(BlockCoord var1);

   MCDataOutput writeNBTTagCompound(NBTTagCompound var1);

   MCDataOutput writeItemStack(ItemStack var1);

   MCDataOutput writeLiquidStack(LiquidStack var1);
}

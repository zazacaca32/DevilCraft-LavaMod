package codechicken.core.data;

import codechicken.core.vec.BlockCoord;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.liquids.LiquidStack;

public interface MCDataInput {

   long readLong();

   int readInt();

   short readShort();

   int readUnsignedShort();

   byte readByte();

   int readUnsignedByte();

   double readDouble();

   float readFloat();

   boolean readBoolean();

   char readChar();

   byte[] readByteArray(int var1);

   String readString();

   BlockCoord readCoord();

   NBTTagCompound readNBTTagCompound();

   ItemStack readItemStack();

   LiquidStack readLiquidStack();
}

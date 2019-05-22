package codechicken.core.data;

import codechicken.core.data.MCDataInput;
import codechicken.core.data.MCDataOutput;
import codechicken.core.vec.BlockCoord;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.liquids.LiquidStack;

public class NBTDataWrapper implements MCDataInput, MCDataOutput {

   private NBTTagList readList;
   private int readTag = 0;
   private NBTTagList writeList;


   public NBTDataWrapper(NBTTagList input) {
      this.readList = input;
   }

   public NBTDataWrapper() {
      this.writeList = new NBTTagList();
   }

   public NBTTagList toTag() {
      return this.writeList;
   }

   public NBTDataWrapper writeLong(long l) {
      this.writeList.appendTag(new NBTTagLong((String)null, l));
      return this;
   }

   public NBTDataWrapper writeInt(int i) {
      this.writeList.appendTag(new NBTTagInt((String)null, i));
      return this;
   }

   public NBTDataWrapper writeShort(int s) {
      this.writeList.appendTag(new NBTTagShort((String)null, (short)s));
      return this;
   }

   public NBTDataWrapper writeByte(int b) {
      this.writeList.appendTag(new NBTTagByte((String)null, (byte)b));
      return this;
   }

   public NBTDataWrapper writeDouble(double d) {
      this.writeList.appendTag(new NBTTagDouble((String)null, d));
      return this;
   }

   public NBTDataWrapper writeFloat(float f) {
      this.writeList.appendTag(new NBTTagFloat((String)null, f));
      return this;
   }

   public NBTDataWrapper writeBoolean(boolean b) {
      this.writeList.appendTag(new NBTTagByte((String)null, (byte)(b?1:0)));
      return this;
   }

   public NBTDataWrapper writeChar(char c) {
      this.writeList.appendTag(new NBTTagShort((String)null, (short)c));
      return this;
   }

   public NBTDataWrapper writeByteArray(byte[] array) {
      this.writeList.appendTag(new NBTTagByteArray((String)null, array));
      return this;
   }

   public NBTDataWrapper writeString(String s) {
      this.writeList.appendTag(new NBTTagString((String)null, s));
      return this;
   }

   public NBTDataWrapper writeCoord(int x, int y, int z) {
      this.writeInt(x);
      this.writeInt(y);
      this.writeInt(z);
      return this;
   }

   public NBTDataWrapper writeCoord(BlockCoord coord) {
      this.writeCoord(coord.x, coord.y, coord.z);
      return this;
   }

   public NBTDataWrapper writeNBTTagCompound(NBTTagCompound tag) {
      this.writeList.appendTag(tag);
      return this;
   }

   public NBTDataWrapper writeItemStack(ItemStack stack) {
      this.writeList.appendTag(stack.writeToNBT(new NBTTagCompound()));
      return this;
   }

   public NBTDataWrapper writeLiquidStack(LiquidStack liquid) {
      this.writeList.appendTag(liquid.writeToNBT(new NBTTagCompound()));
      return this;
   }

   public long readLong() {
      return ((NBTTagLong)this.readTag()).data;
   }

   public int readInt() {
      return ((NBTTagInt)this.readTag()).data;
   }

   public short readShort() {
      return ((NBTTagShort)this.readTag()).data;
   }

   public int readUnsignedShort() {
      return ((NBTTagShort)this.readTag()).data & '\uffff';
   }

   public byte readByte() {
      return ((NBTTagByte)this.readTag()).data;
   }

   public int readUnsignedByte() {
      return ((NBTTagByte)this.readTag()).data & 255;
   }

   public double readDouble() {
      return ((NBTTagDouble)this.readTag()).data;
   }

   public float readFloat() {
      return ((NBTTagFloat)this.readTag()).data;
   }

   public boolean readBoolean() {
      return ((NBTTagByte)this.readTag()).data != 0;
   }

   public char readChar() {
      return (char)((NBTTagShort)this.readTag()).data;
   }

   public byte[] readByteArray(int length) {
      return ((NBTTagByteArray)this.readTag()).byteArray;
   }

   public String readString() {
      return ((NBTTagString)this.readTag()).data;
   }

   public BlockCoord readCoord() {
      return new BlockCoord(this.readInt(), this.readInt(), this.readInt());
   }

   public NBTTagCompound readNBTTagCompound() {
      return (NBTTagCompound)this.readTag();
   }

   public ItemStack readItemStack() {
      return ItemStack.loadItemStackFromNBT(this.readNBTTagCompound());
   }

   public LiquidStack readLiquidStack() {
      return LiquidStack.loadLiquidStackFromNBT(this.readNBTTagCompound());
   }

   private NBTBase readTag() {
      return this.readList.tagAt(this.readTag++);
   }
}

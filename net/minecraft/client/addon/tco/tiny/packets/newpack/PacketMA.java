package net.minecraft.client.addon.tco.tiny.packets.newpack;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableBiMap.Builder;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import java.io.IOException;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketClickMESlotFakeUP64;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketClickSlotME;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketInvCommand;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketLAControllerMonitor;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketLAInv;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMATrade;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMAUpdateInt;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMAUpdateMerchant;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMAUpdateTileWindow;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMAparticle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;

public abstract class PacketMA {

   public static final String CHANNEL = "ll";
   private static final BiMap idMap;


   public static PacketMA constructPacket(int packetId) throws IllegalAccessException, PacketMA.ProtocolException, InstantiationException {
      Class clazz = (Class)idMap.get(Integer.valueOf(packetId));
      if(clazz == null) {
         throw new PacketMA.ProtocolException("Unknown Packet Id!");
      } else {
         return (PacketMA)clazz.newInstance();
      }
   }

   public final int getPacketId() {
      if(idMap.inverse().containsKey(this.getClass())) {
         return ((Integer)idMap.inverse().get(this.getClass())).intValue();
      } else {
         throw new RuntimeException("Packet " + this.getClass().getSimpleName() + " is missing a mapping!");
      }
   }

   public final Packet makePacket() {
      ByteArrayDataOutput out = ByteStreams.newDataOutput();
      out.writeByte(this.getPacketId());
      this.write(out);
      return PacketDispatcher.getPacket("ll", (byte[])out.toByteArray());
   }

   public static NBTTagCompound readNBTTagCompound(ByteArrayDataInput par0DataInputStream) throws IOException {
      short short1 = par0DataInputStream.readShort();
      if(short1 < 0) {
         return null;
      } else {
         byte[] abyte = new byte[short1];
         par0DataInputStream.readFully(abyte);
         return CompressedStreamTools.decompress((byte[])abyte);
      }
   }

   protected static void writeNBTTagCompound(NBTTagCompound par0NBTTagCompound, ByteArrayDataOutput par1DataOutputStream) throws IOException {
      if(par0NBTTagCompound == null) {
         par1DataOutputStream.writeShort(-1);
      } else {
         byte[] abyte = CompressedStreamTools.compress(par0NBTTagCompound);
         par1DataOutputStream.writeShort((short)abyte.length);
         par1DataOutputStream.write(abyte);
      }

   }

   public static ItemStack readItemStack(ByteArrayDataInput par0DataInputStream) throws IOException {
      ItemStack itemstack = null;
      short short1 = par0DataInputStream.readShort();
      if(short1 >= 0) {
         byte b0 = par0DataInputStream.readByte();
         short short2 = par0DataInputStream.readShort();
         itemstack = new ItemStack(short1, b0, short2);
         itemstack.stackTagCompound = readNBTTagCompound(par0DataInputStream);
      }

      return itemstack;
   }

   public static void writeItemStack(ItemStack par0ItemStack, ByteArrayDataOutput par1DataOutputStream) throws IOException {
      if(par0ItemStack != null && par0ItemStack.getItem() != null) {
         par1DataOutputStream.writeShort(par0ItemStack.itemID);
         par1DataOutputStream.writeByte(par0ItemStack.stackSize);
         par1DataOutputStream.writeShort(par0ItemStack.getItemDamage());
         NBTTagCompound nbttagcompound = null;
         if(par0ItemStack.getItem().isDamageable() || par0ItemStack.getItem().getShareTag()) {
            nbttagcompound = par0ItemStack.stackTagCompound;
         }

         writeNBTTagCompound(nbttagcompound, par1DataOutputStream);
      } else {
         par1DataOutputStream.writeShort(-1);
      }

   }

   protected abstract void write(ByteArrayDataOutput var1);

   protected abstract void read(ByteArrayDataInput var1) throws PacketMA.ProtocolException;

   protected abstract void execute(EntityPlayer var1, Side var2) throws PacketMA.ProtocolException;

   protected abstract void length(int var1);

   static {
      Builder builder = ImmutableBiMap.builder();
      builder.put(Integer.valueOf(1), PacketMAparticle.class);
      builder.put(Integer.valueOf(2), PacketMATrade.class);
      builder.put(Integer.valueOf(3), PacketMAUpdateTileWindow.class);
      builder.put(Integer.valueOf(4), PacketMAUpdateInt.class);
      builder.put(Integer.valueOf(5), PacketInvCommand.class);
      builder.put(Integer.valueOf(6), PacketClickSlotME.class);
      builder.put(Integer.valueOf(7), PacketLAInv.class);
      builder.put(Integer.valueOf(8), PacketClickMESlotFakeUP64.class);
      builder.put(Integer.valueOf(10), PacketLAControllerMonitor.class);
      builder.put(Integer.valueOf(11), PacketMAUpdateMerchant.class);
      idMap = builder.build();
   }

   public static class ProtocolException extends Exception {

      public ProtocolException() {}

      public ProtocolException(String message, Throwable cause) {
         super(message, cause);
      }

      public ProtocolException(String message) {
         super(message);
      }

      public ProtocolException(Throwable cause) {
         super(cause);
      }
   }
}

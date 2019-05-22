package net.minecraft.client.addon.tco.tiny.Utils;

import java.io.IOException;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.METileEntityBase;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketLAInv;
import net.minecraft.network.packet.Packet;

public interface ILANetworkInventory {

   Packet getDataPacketLA() throws IOException;

   void readPacket(PacketLAInv var1);

   LAItemStack getStackInSlot(int var1);

   void setInventorySlotContents(int var1, LAItemStack var2);

   METileEntityBase getMETileBase();

   void setMETileBase(METileEntityBase var1);
}

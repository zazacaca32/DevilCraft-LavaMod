package net.minecraft.client.addon.tco.tiny.packets.newpack;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.addon.tco.tiny.blocks.containers.MEContainerBase;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMA;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.nbt.NBTTagCompound;

public class PacketClickSlotME extends PacketMA {

   int mouseClick;
   int shift;
   int slotNumber;
   NBTTagCompound c;


   public PacketClickSlotME() {}

   public PacketClickSlotME(int mouseClick2, int shift2, int slotNumber2, NBTTagCompound c2) {
      this.mouseClick = mouseClick2;
      this.shift = shift2;
      this.slotNumber = slotNumber2;
      this.c = c2;
   }

   protected void write(ByteArrayDataOutput out) {
      out.writeInt(this.mouseClick);
      out.writeInt(this.shift);
      out.writeInt(this.slotNumber);

      try {
         PacketMA.writeNBTTagCompound(this.c, out);
      } catch (IOException var3) {
         ;
      }

   }

   protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException {
      this.mouseClick = in.readInt();
      this.shift = in.readInt();
      this.slotNumber = in.readInt();

      try {
         this.c = PacketMA.readNBTTagCompound(in);
      } catch (IOException var3) {
         ;
      }

   }

   protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException {
      if(side.isServer()) {
         EntityPlayerMP pmp = (EntityPlayerMP)player;
         Container c = pmp.openContainer;
         if(c != null && c instanceof MEContainerBase) {
            ((MEContainerBase)c).handleSlotMEPacket(this.mouseClick, this.shift, this.slotNumber, this.c);
            ArrayList var3 = new ArrayList();

            for(int var4 = 0; var4 < pmp.openContainer.inventorySlots.size(); ++var4) {
               var3.add(((Slot)pmp.openContainer.inventorySlots.get(var4)).getStack());
            }

            pmp.sendContainerAndContentsToPlayer(pmp.openContainer, var3);
         }
      }

   }

   protected void length(int length) {}
}

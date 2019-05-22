package net.minecraft.client.addon.tco.tiny.packets.newpack;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import net.minecraft.client.addon.tco.tiny.blocks.containers.MEContainerBase;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMA;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class PacketClickMESlotFakeUP64 extends PacketMA {

   int mouseClick;
   int shift;
   int slotNumber;


   public PacketClickMESlotFakeUP64() {}

   public PacketClickMESlotFakeUP64(int mouseClick2, int shift2, int slotNumber2) {
      this.mouseClick = mouseClick2;
      this.shift = shift2;
      this.slotNumber = slotNumber2;
   }

   protected void write(ByteArrayDataOutput out) {
      out.writeInt(this.mouseClick);
      out.writeInt(this.shift);
      out.writeInt(this.slotNumber);
   }

   protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException {
      this.mouseClick = in.readInt();
      this.shift = in.readInt();
      this.slotNumber = in.readInt();
   }

   protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException {
      if(side.isServer()) {
         EntityPlayerMP pmp = (EntityPlayerMP)player;
         Container c = pmp.openContainer;
         if(c != null && c instanceof MEContainerBase) {
            ((MEContainerBase)c).handleMESlotFakeUP64Packet(this.mouseClick, this.shift, this.slotNumber);
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

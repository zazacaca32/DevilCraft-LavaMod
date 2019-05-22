package net.minecraft.client.addon.tco.tiny.packets.newpack;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import java.util.Iterator;
import net.minecraft.client.addon.tco.tiny.blocks.containers.MEContainerBase;
import net.minecraft.client.addon.tco.tiny.blocks.slots.ISlotPlayerSide;
import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotBase;
import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotFake;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMA;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class PacketInvCommand extends PacketMA {

   public byte cmd;
   public short slot;


   public PacketInvCommand() {}

   public PacketInvCommand(PacketInvCommand.PacketInvCommands dragPlacement, int slotNumber) {
      this.cmd = (byte)dragPlacement.ordinal();
   }

   protected void write(ByteArrayDataOutput out) {
      out.writeByte(this.cmd);
      out.writeShort(this.slot);
   }

   protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException {
      this.cmd = in.readByte();
      this.slot = in.readShort();
   }

   protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException {
      if(side.isServer()) {
         EntityPlayerMP pmp = (EntityPlayerMP)player;
         Container c = pmp.openContainer;
         if(c != null && c instanceof MEContainerBase) {
            MEContainerBase aec = (MEContainerBase)c;
            PacketInvCommand.PacketInvCommands target = PacketInvCommand.PacketInvCommands.values()[this.cmd];
            Iterator i$ = aec.inventorySlots.iterator();

            while(i$.hasNext()) {
               Object s = i$.next();
               if(s instanceof MESlotBase) {
                  MESlotBase x = (MESlotBase)s;
                  if(target == PacketInvCommand.PacketInvCommands.SPACE_MACHINE && !(x instanceof MESlotFake) && !(s instanceof ISlotPlayerSide)) {
                     aec.forceUpdate = true;
                     aec.transferStackInSlot(pmp, x.slotNumber);
                  } else if(target == PacketInvCommand.PacketInvCommands.CMD_4 && s instanceof MESlotFake && x.slotNumber == this.slot) {
                     aec.forceUpdate = true;
                     if(pmp.inventory.getItemStack() == null) {
                        return;
                     }

                     aec.putStackInSlot(x.slotNumber, pmp.inventory.getItemStack().copy());
                  } else if(target == PacketInvCommand.PacketInvCommands.DRAG_PLACEMENT && s instanceof MESlotFake && x.slotNumber == this.slot) {
                     aec.forceUpdate = true;
                     if(pmp.inventory.getItemStack() == null) {
                        return;
                     }

                     ItemStack is = pmp.inventory.getItemStack().copy();
                     is.stackSize = 1;
                     aec.putStackInSlot(x.slotNumber, is);
                  }
               }
            }

            return;
         }
      }

   }

   protected void length(int length) {}

   public static enum PacketInvCommands {

      SPACE_HOTBAR("SPACE_HOTBAR", 0, "SPACE_HOTBAR", 0, "SPACE_HOTBAR", 0, "SPACE_HOTBAR", 0, "SPACE_HOTBAR", 0, "SPACE_HOTBAR", 0, "SPACE_HOTBAR", 0),
      SPACE_PLAYERINV("SPACE_PLAYERINV", 1, "SPACE_PLAYERINV", 1, "SPACE_PLAYERINV", 1, "SPACE_PLAYERINV", 1, "SPACE_PLAYERINV", 1, "SPACE_PLAYERINV", 1, "SPACE_PLAYERINV", 1),
      SPACE_MACHINE("SPACE_MACHINE", 2, "SPACE_MACHINE", 2, "SPACE_MACHINE", 2, "SPACE_MACHINE", 2, "SPACE_MACHINE", 2, "SPACE_MACHINE", 2, "SPACE_MACHINE", 2),
      SPACE_CRAFTING_GRID("SPACE_CRAFTING_GRID", 3, "SPACE_CRAFTING_GRID", 3, "SPACE_CRAFTING_GRID", 3, "SPACE_CRAFTING_GRID", 3, "SPACE_CRAFTING_GRID", 3, "SPACE_CRAFTING_GRID", 3, "SPACE_CRAFTING_GRID", 3),
      CMD_4("CMD_4", 4, "CMD_4", 4, "CMD_4", 4, "CMD_4", 4, "CMD_4", 4, "CMD_4", 4, "CMD_4", 4),
      DRAG_PLACEMENT("DRAG_PLACEMENT", 5, "DRAG_PLACEMENT", 5, "DRAG_PLACEMENT", 5, "DRAG_PLACEMENT", 5, "DRAG_PLACEMENT", 5, "DRAG_PLACEMENT", 5, "DRAG_PLACEMENT", 5),
      InvertBlacklist("InvertBlacklist", 6, "InvertBlacklist", 6, "InvertBlacklist", 6, "InvertBlacklist", 6, "InvertBlacklist", 6, "InvertBlacklist", 6, "InvertBlacklist", 6);
      private static final PacketInvCommand.PacketInvCommands[] $VALUES = new PacketInvCommand.PacketInvCommands[]{SPACE_HOTBAR, SPACE_PLAYERINV, SPACE_MACHINE, SPACE_CRAFTING_GRID, CMD_4, DRAG_PLACEMENT, InvertBlacklist};
      private static final PacketInvCommand.PacketInvCommands[] $VALUES$ = new PacketInvCommand.PacketInvCommands[]{SPACE_HOTBAR, SPACE_PLAYERINV, SPACE_MACHINE, SPACE_CRAFTING_GRID, CMD_4, DRAG_PLACEMENT, InvertBlacklist};
      private static final PacketInvCommand.PacketInvCommands[] $VALUES$$ = new PacketInvCommand.PacketInvCommands[]{SPACE_HOTBAR, SPACE_PLAYERINV, SPACE_MACHINE, SPACE_CRAFTING_GRID, CMD_4, DRAG_PLACEMENT, InvertBlacklist};
      private static final PacketInvCommand.PacketInvCommands[] $VALUES$$$ = new PacketInvCommand.PacketInvCommands[]{SPACE_HOTBAR, SPACE_PLAYERINV, SPACE_MACHINE, SPACE_CRAFTING_GRID, CMD_4, DRAG_PLACEMENT, InvertBlacklist};
      private static final PacketInvCommand.PacketInvCommands[] $VALUES$$$$ = new PacketInvCommand.PacketInvCommands[]{SPACE_HOTBAR, SPACE_PLAYERINV, SPACE_MACHINE, SPACE_CRAFTING_GRID, CMD_4, DRAG_PLACEMENT, InvertBlacklist};
      // $FF: synthetic field
      private static final PacketInvCommand.PacketInvCommands[] $VALUES$$$$$ = new PacketInvCommand.PacketInvCommands[]{SPACE_HOTBAR, SPACE_PLAYERINV, SPACE_MACHINE, SPACE_CRAFTING_GRID, CMD_4, DRAG_PLACEMENT, InvertBlacklist};


      private PacketInvCommands(String var1, int var2, String var11, int var21, String ssss, int xzxzxz, String va123r11, int var12321, String var111, int var211, String s, int n, String var1111, int var2111) {}

   }
}

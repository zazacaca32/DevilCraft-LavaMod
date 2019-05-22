package net.minecraft.client.addon.tco.tiny.blocks.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkEnergyControler;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMAUpdateTileWindow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerDarkController extends Container {

   TileEntityBlockDarkEnergyControler tileentity;
   InventoryPlayer inventory;


   public ContainerDarkController(InventoryPlayer inventory, TileEntityBlockDarkEnergyControler tileentity) {
      this.tileentity = tileentity;
      this.inventory = inventory;

      for(int i = 0; i < 4; ++i) {
         for(int j = 0; j < 4; ++j) {
            this.addSlotToContainer(new Slot(tileentity, j + i, 33 + j * 35, 44 + i * 18));
         }
      }

   }

   public boolean canInteractWith(EntityPlayer entityplayer) {
      return this.tileentity.isUseableByPlayer(entityplayer);
   }

   @SideOnly(Side.CLIENT)
   public void readPacket(ArrayList itemstack, long energy) {
      this.tileentity.itemstacks = itemstack;
      this.tileentity.darkEnergy = energy;
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < super.crafters.size(); ++i) {
         EntityPlayerMP icrafting = (EntityPlayerMP)super.crafters.get(i);
         if(this.tileentity.changerGUI) {
            icrafting.playerNetServerHandler.sendPacketToPlayer((new PacketMAUpdateTileWindow(super.windowId, this.tileentity.itemstacks, this.tileentity.darkEnergy)).makePacket());
         }
      }

      if(this.tileentity.changerGUI) {
         this.tileentity.changerGUI = false;
      }

   }
}

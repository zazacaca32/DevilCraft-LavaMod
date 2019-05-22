package net.minecraft.client.addon.tco.tiny.blocks.containers;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.IOException;
import net.minecraft.client.addon.tco.tiny.NetworkedIMEI;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderController;
import net.minecraft.client.addon.tco.tiny.blocks.containers.MEContainerBase;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotME;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotPlayerHotBar;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotPlayerInv;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;

public class ContainerTraderController extends MEContainerBase {

   public NetworkedIMEI imeiinv;
   TileEntityBlockTraderController tileentity;
   private int dualCountConnected;


   public ContainerTraderController(IInventory inventoryPlayer, TileEntityBlockTraderController tileentity) {
      super(((InventoryPlayer)inventoryPlayer).player, tileentity);
      this.tileentity = tileentity;
      InventoryPlayer iplayer = (InventoryPlayer)inventoryPlayer;
      this.imeiinv = new NetworkedIMEI();
      this.imeiinv.targetPlayer = (Player)((InventoryPlayer)inventoryPlayer).player;
      this.imeiinv.setMETileBase(tileentity);
      int off = 0;

      for(int var7 = 0; var7 < 7; ++var7) {
         for(int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new SlotME(this.imeiinv, off++, 8 + j * 18, var7 * 18 + 8 + 18));
         }
      }

      this.bindPlayerInventory(iplayer);
      if(Utils.isClient()) {
         try {
            PacketDispatcher.sendPacketToServer(this.imeiinv.getRequestPacket());
         } catch (IOException var71) {
            var71.printStackTrace();
         }
      }

   }

   public boolean canInteractWith(EntityPlayer player) {
      return this.imeiinv.realInv.isUseableByPlayer(player);
   }

   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
      int i;
      for(i = 0; i < 3; ++i) {
         for(int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new SlotPlayerInv(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 166 + i * 18));
         }
      }

      for(i = 0; i < 9; ++i) {
         this.addSlotToContainer(new SlotPlayerHotBar(inventoryPlayer, i, 8 + i * 18, 224));
      }

   }

   public void updateClient() {
      try {
         PacketDispatcher.sendPacketToPlayer(this.imeiinv.getDataPacketLA(), (Player)this.getPlayer());
      } catch (IOException var2) {
         var2.printStackTrace();
      }

   }

   public void addCraftingToCrafters(ICrafting par1ICrafting) {
      super.addCraftingToCrafters(par1ICrafting);
      par1ICrafting.sendProgressBarUpdate(this, 0, this.tileentity.triggercount);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < super.crafters.size(); ++i) {
         ICrafting icrafting = (ICrafting)super.crafters.get(i);
         if(this.dualCountConnected != this.tileentity.triggercount) {
            icrafting.sendProgressBarUpdate(this, 0, this.tileentity.triggercount);
         }
      }

      this.dualCountConnected = this.tileentity.triggercount;
   }

   @SideOnly(Side.CLIENT)
   public void updateProgressBar(int par1, int par2) {
      if(par1 == 0) {
         this.tileentity.triggercount = par2;
      }

   }

   public NetworkedIMEI GetNetworkIME() {
      return this.imeiinv;
   }
}

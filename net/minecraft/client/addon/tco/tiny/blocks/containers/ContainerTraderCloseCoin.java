package net.minecraft.client.addon.tco.tiny.blocks.containers;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tchestplate.donate.api.IDonateUser;
import net.minecraft.client.addon.tchestplate.donate.api.Manager;
import net.minecraft.client.addon.tco.tiny.Utils.ILANetworkInventory;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderCoin;
import net.minecraft.client.addon.tco.tiny.blocks.containers.MEContainerBase;
import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotFakeUP64Donate;
import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotFakeUP64DonateSpecial;
import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotTrader;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotPlayerHotBar;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotPlayerInv;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;

public class ContainerTraderCloseCoin extends MEContainerBase {

   public TileEntityBlockTraderCoin tileEntity;
   private boolean dualisJob;
   private short dualTriggerControllerOffer;


   public ContainerTraderCloseCoin(IInventory inventoryPlayer, TileEntityBlockTraderCoin te) {
      super(((InventoryPlayer)inventoryPlayer).player, te);
      this.tileEntity = te;
      InventoryPlayer iplayer = (InventoryPlayer)inventoryPlayer;
      this.bindPlayerInventory(iplayer);
      IDonateUser idu = Manager.getDonateUser(((InventoryPlayer)inventoryPlayer).player);
      MESlotFakeUP64DonateSpecial sl0 = new MESlotFakeUP64DonateSpecial(this.tileEntity.cellinvfake, idu, 0, 80, 43);
      MESlotFakeUP64DonateSpecial sl1 = new MESlotFakeUP64DonateSpecial(this.tileEntity.cellinvfake, idu, 1, 80, 19);
      sl0.isValid = false;
      sl1.isValid = false;
      sl0.setSl1(sl1);
      sl1.setSl1(sl0);
      this.addSlotToContainer(sl0);
      this.addSlotToContainer(sl1);
      this.addSlotToContainer(new MESlotFakeUP64Donate(this.tileEntity.cellinvfake, idu, 2, 116, 19, -1));
      this.addSlotToContainer(new MESlotTrader(this.tileEntity.cellinvfake, 3, 116, 43, -1));
   }

   public boolean canInteractWith(EntityPlayer player) {
      return this.tileEntity.isUseableByPlayer(player);
   }

   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
      int i;
      for(i = 0; i < 3; ++i) {
         for(int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new SlotPlayerInv(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
         }
      }

      for(i = 0; i < 9; ++i) {
         this.addSlotToContainer(new SlotPlayerHotBar(inventoryPlayer, i, 8 + i * 18, 142));
      }

   }

   public void updateClient() {
      try {
         PacketDispatcher.sendPacketToPlayer(this.tileEntity.cellinvfake.getDataPacketLA(), (Player)this.getPlayer());
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public void addCraftingToCrafters(ICrafting par1ICrafting) {
      super.addCraftingToCrafters(par1ICrafting);
      par1ICrafting.sendProgressBarUpdate(this, 0, this.tileEntity.TriggerControllerOffer());
      par1ICrafting.sendProgressBarUpdate(this, 3, this.tileEntity.isJob()?1:0);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < super.crafters.size(); ++i) {
         ICrafting icrafting = (ICrafting)super.crafters.get(i);
         if(this.dualTriggerControllerOffer != this.tileEntity.TriggerControllerOffer()) {
            icrafting.sendProgressBarUpdate(this, 0, this.tileEntity.TriggerControllerOffer());
         }

         if(this.dualisJob != this.tileEntity.isJob()) {
            icrafting.sendProgressBarUpdate(this, 3, this.tileEntity.isJob()?1:0);
         }
      }

      this.dualTriggerControllerOffer = this.tileEntity.TriggerControllerOffer();
      this.dualisJob = this.tileEntity.isJob();
   }

   @SideOnly(Side.CLIENT)
   public void updateProgressBar(int par1, int par2) {
      if(par1 == 0) {
         this.tileEntity.OfferCounts = (short)par2;
      }

      if(par1 == 3) {
         this.tileEntity.ControllerTriggerUpdateClient = par2 == 1;
      }

   }

   public ILANetworkInventory GetNetworkIME() {
      return this.tileEntity.cellinvfake;
   }

   public void HandleUpdateInt(int idInt, EntityPlayer pl) {
      if(idInt == 1) {
         this.tileEntity.startTraderCoinPlayer = pl;
         this.tileEntity.triggerStartTrade = true;
      }

   }
}

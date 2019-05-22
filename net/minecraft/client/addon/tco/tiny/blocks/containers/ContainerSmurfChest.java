package net.minecraft.client.addon.tco.tiny.blocks.containers;

import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockSmurfChest;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerBase;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInvConsumableID;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInvConsumableIDmeta;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class ContainerSmurfChest extends ContainerBase {

   private TileEntityBlockSmurfChest tileentity;
   ItemStack armor = null;


   public ContainerSmurfChest(InventoryPlayer inventoryplayer, TileEntityBlockSmurfChest tileentity) {
      super(inventoryplayer);
      this.tileentity = tileentity;
      Slot_R r = null;
      r = (Slot_R)Manager.INSTANCE.Get(tileentity.RecipesIndex).get(0);
      SlotInvConsumableIDmeta slot = new SlotInvConsumableIDmeta(r.access, tileentity, 0, 54, 35, r.getFloatIDandDamage());
      this.addSlotToContainer(slot);
      r = (Slot_R)Manager.INSTANCE.Get(tileentity.RecipesIndex).get(1);
      SlotInvConsumableID slot1 = new SlotInvConsumableID(r.access, tileentity, 1, 112, 35, r.getFloatIDandDamage());
      this.addSlotToContainer(slot1);
      if(slot.inventory.getStackInSlot(0) != null) {
         this.armor = slot.inventory.getStackInSlot(0);
         int item = this.armor.stackTagCompound.getInteger("tag1");
         if(item > 0) {
            slot.inventory.setInventorySlotContents(1, new ItemStack(LavaChestPlate.itemCrystalCobalt, item));
            this.armor.stackTagCompound.setInteger("tag1", 0);
         }
      }

      this.CalcFullInv(84);
   }

   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
      return this.tileentity.isUseableByPlayer(par1EntityPlayer);
   }

   public int getMergeMaxSlotIndex(int SlotIndex) {
      return SlotIndex + 1;
   }
}

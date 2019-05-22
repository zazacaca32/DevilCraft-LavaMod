package net.minecraft.client.addon.tchestplate.aaamodule.items;

import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMWand extends Item {

   int id;


   public ItemMWand(int par1, int id) {
      super(par1);
      this.id = id;
      this.setUnlocalizedName("moduleunit.itemmwand" + id);
      this.setCreativeTab(LavaChestPlate.tabItemss);
      this.setMaxStackSize(1);
   }

   public void registerIcons(IconRegister par1IconRegister) {
      super.itemIcon = par1IconRegister.registerIcon("LavaChestPlate:gucciaxe");
   }

   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
      if(!player.worldObj.isRemote) {
         switch(this.id) {
         case 0:
            entity.worldObj.playSoundEffect(entity.posX, entity.posY, entity.posZ, "tchestplate.parasha", 2.0F, 1.1F);
            break;
         case 1:
            entity.worldObj.playSoundEffect(entity.posX, entity.posY, entity.posZ, "tchestplate.borodazalezay", 2.0F, 1.1F);
            break;
         case 2:
            entity.worldObj.playSoundEffect(entity.posX, entity.posY, entity.posZ, "tchestplate.johncena", 2.0F, 1.1F);
            break;
         case 3:
            entity.worldObj.playSoundEffect(entity.posX, entity.posY, entity.posZ, "tchestplate.ocherednayahueta", 2.0F, 1.1F);
         }
      }

      return true;
   }
}

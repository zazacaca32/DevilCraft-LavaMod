package net.minecraft.client.addon.tchestplate.aaamodule.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemKoin extends Item {

   int id;
   String un;


   public ItemKoin(int par1, int id, String un) {
      super(par1);
      this.id = id;
      this.un = un;
      this.setUnlocalizedName("moduleunit." + un);
      this.setCreativeTab(LavaChestPlate.tabItemss);
      this.setMaxStackSize(64);
   }

   public void registerIcons(IconRegister par1IconRegister) {
      switch(this.id) {
      case 0:
         super.itemIcon = par1IconRegister.registerIcon("aamodules:" + this.un);
         break;
      case 1:
         super.itemIcon = par1IconRegister.registerIcon("aamodules:" + this.un + "_m");
      }

   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
      int var10000 = this.id;
   }
}

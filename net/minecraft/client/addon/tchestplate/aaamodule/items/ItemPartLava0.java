package net.minecraft.client.addon.tchestplate.aaamodule.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;

import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemPartLava0 extends Item {

   int id;
   String un;


   public ItemPartLava0(int par1, int id, String un) {
      super(par1);
      this.id = id;
      this.un = un;
      this.setUnlocalizedName("moduleunit." + un);
      this.setMaxStackSize(1);
      this.setFull3D();
      this.setCreativeTab(LavaChestPlate.tabItemss);
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
   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List l, boolean par4) {
       l.add("§8 Модуль: для улучшения брони");
       l.add("§8 Модуль: добавляет 1000L к защите");
       l.add("§8 Модуль: шанс вставки 100%");
   }
}

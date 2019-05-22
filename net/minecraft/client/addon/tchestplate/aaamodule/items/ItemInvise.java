package net.minecraft.client.addon.tchestplate.aaamodule.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemInvise extends Item {

   int id;
   String un;


   public ItemInvise(int par1, int id, String un) {
      super(par1);
      this.id = id;
      this.un = un;
      this.setUnlocalizedName("moduleunit." + un);
      this.setCreativeTab(LavaChestPlate.tabItemss);
      this.setMaxStackSize(1);
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
      par2List.add("§8 Модуль: для улучшения брони");
      par2List.add("§8 Модуль: делает броню невидимой");
      par2List.add("§8 Модуль: можно очистить");
      par2List.add("§8 Модуль: шанс вставки 100%");
   }
}

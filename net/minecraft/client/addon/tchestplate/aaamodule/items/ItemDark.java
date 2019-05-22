package net.minecraft.client.addon.tchestplate.aaamodule.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemDark extends Item {

   int id;
   String un;


   public ItemDark(int par1, int id, String un) {
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
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
         super.itemIcon = par1IconRegister.registerIcon("aamodules:" + this.un);
         break;
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
         super.itemIcon = par1IconRegister.registerIcon("aamodules:" + this.un + "_m");
      }

   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
      switch(this.id) {
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
         par2List.add("§8 Модуль: для модификации брони");
         par2List.add("§8 Модуль: добавляет " + String.valueOf(500 * (this.id + 1)) + "L к защите");
         par2List.add("§8 Модуль: шанс вставки 100%");
         break;
      case 10:
         par2List.add("§8 Модуль: для модификации брони");
         par2List.add("§8 Модуль: добавляет 500L к защите");
         par2List.add("§8 Модуль: шанс вставки 100%");
         break;
      case 11:
         par2List.add("§8 Модуль: для модификации брони");
         par2List.add("§8 Модуль: добавляет 1000L к защите");
         par2List.add("§8 Модуль: шанс вставки 100%");
         break;
      case 12:
         par2List.add("§8 Модуль: для модификации брони");
         par2List.add("§8 Модуль: добавляет 1500L к защите");
         par2List.add("§8 Модуль: шанс вставки 100%");
         break;
      case 13:
         par2List.add("§8 Модуль: для модификации брони");
         par2List.add("§8 Модуль: добавляет 2000L к защите");
         par2List.add("§8 Модуль: шанс вставки 100%");
         break;
      case 14:
         par2List.add("§8 Модуль: для модификации брони");
         par2List.add("§8 Модуль: добавляет 2500L к защите");
         par2List.add("§8 Модуль: шанс вставки 100%");
         break;
      case 15:
         par2List.add("§8 Модуль: для модификации брони");
         par2List.add("§8 Модуль: добавляет 3000L к защите");
         par2List.add("§8 Модуль: шанс вставки 100%");
         break;
      case 16:
         par2List.add("§8 Модуль: для модификации брони");
         par2List.add("§8 Модуль: добавляет 3500L к защите");
         par2List.add("§8 Модуль: шанс вставки 100%");
         break;
      case 17:
         par2List.add("§8 Модуль: для модификации брони");
         par2List.add("§8 Модуль: добавляет 4000L к защите");
         par2List.add("§8 Модуль: шанс вставки 100%");
         break;
      case 18:
         par2List.add("§8 Модуль: для модификации брони");
         par2List.add("§8 Модуль: добавляет 4500L к защите");
         par2List.add("§8 Модуль: шанс вставки 100%");
         break;
      case 19:
         par2List.add("§8 Модуль: для модификации брони");
         par2List.add("§8 Модуль: добавляет 5000L к защите");
         par2List.add("§8 Модуль: шанс вставки 100%");
      }

   }
}

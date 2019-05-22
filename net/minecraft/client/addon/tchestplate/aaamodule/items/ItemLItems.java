package net.minecraft.client.addon.tchestplate.aaamodule.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemLItems extends Item {

   int iid;
   int count;
   String name;


   public ItemLItems(int ia, int i, int c, String n) {
      super(ia);
      this.iid = i;
      this.name = n;
      this.count = c;
      this.setUnlocalizedName("provider_" + this.name);
      this.setMaxStackSize(1);
      switch(this.iid) {
      case 1:
      case 2:
      case 4:
         this.setCreativeTab(LavaChestPlate.tabItemss);
         break;
      case 3:
      default:
         this.setCreativeTab(LavaChestPlate.tabItemss);
      }

   }

   public void registerIcons(IconRegister i) {
      super.itemIcon = i.registerIcon("provider:item" + this.iid);
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack i, EntityPlayer p, List l, boolean b) {
      switch(this.iid) {
      case 1:
         l.add("§8 Модуль: для улучшения брони");
         l.add("§8 Модуль: добавляет 1000L к защите");
         l.add("§8 Модуль: шанс вставки 100%");
         break;
      case 2:
         l.add("§8 Модуль: для улучшения брони");
         l.add("§8 Модуль: добавляет 1000L к защите");
         l.add("§8 Модуль: шанс вставки 100%");
      case 3:
      case 5:
      case 7:
      default:
         break;
      case 4:
         l.add("§8 Модуль: формочка для модулей");
         l.add("§8 Модуль: используется в кодировщике");
         break;
      case 6:
         l.add("§6 Отображается на шлемах");
         l.add("§6 Даёт защиту 5%");
         break;
      case 8:
         l.add("§aИтем в честь 23 февраля");
         break;
      case 9:
         l.add("§aИтем в честь 23 февраля");
      }

   }
}

package net.minecraft.client.addon.tchestplate.aaamodule.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemColor extends Item {

   int id;
   String un;


   public ItemColor(int i, int id, String unlocalized) {
      super(i);
      super.maxStackSize = 1;
      this.id = id;
      this.un = unlocalized;
      this.setUnlocalizedName("moduleunit." + unlocalized);
      this.setCreativeTab(LavaChestPlate.tabItemss);
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
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
         super.itemIcon = par1IconRegister.registerIcon("aamodules:" + this.un);
         break;
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
      case 21:
      case 22:
      case 23:
      case 24:
      case 25:
      case 26:
      case 27:
      case 28:
      case 29:
      case 30:
      case 31:
         super.itemIcon = par1IconRegister.registerIcon("aamodules:" + this.un + "_m");
      }

   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
      par2List.add("§8 Модуль: для улучшения брони");
      par2List.add("§8 Модуль: красит броню");
      par2List.add("§8 Модуль: шанс вставки 100%");
   }
}

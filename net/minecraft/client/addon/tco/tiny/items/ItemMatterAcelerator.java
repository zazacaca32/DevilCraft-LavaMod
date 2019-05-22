package net.minecraft.client.addon.tco.tiny.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tco.tiny.items.MultiItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemMatterAcelerator extends MultiItemBase {

   long inl = 0L;


   public ItemMatterAcelerator(int i, int j, int count) {
      super(i, count);
      super.maxStackSize = j;
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
      super.addInformation(par1ItemStack, par2EntityPlayer, par2List, par4);
      switch(par1ItemStack.getItemDamage()) {
      case 0:
         par2List.add("§aНужен для моментального");
         par2List.add("§aпроизводства темной материи.");
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
      default:
         break;
      case 11:
         par2List.add("§4 Усиливает мощность в 10 раз.");
         par2List.add("§4 Увеличивает расход в 3 раза.");
      }

   }
}

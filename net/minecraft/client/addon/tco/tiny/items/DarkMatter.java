package net.minecraft.client.addon.tco.tiny.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tco.tiny.items.MultiItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class DarkMatter extends MultiItemBase {

   public DarkMatter(int i, int j, int count) {
      super(i, count);
      super.maxStackSize = j;
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
      super.addInformation(par1ItemStack, par2EntityPlayer, par2List, par4);
      switch(par1ItemStack.getItemDamage()) {
      case 6:
         par2List.add("§aИтем в честь 23 февраля");
         par2List.add("§aВыпадает с Руды: Адамантита 50%, Палладия 40%, Кобальта 10%");
         par2List.add("§aЛомать руду Алмазной киркой, Лава разрушитель не работает");
      default:
      }
   }
}

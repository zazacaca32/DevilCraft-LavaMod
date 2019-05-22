package net.minecraft.client.addon.tco.tiny.items;

import java.util.List;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.items.MultiItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Lavapad extends MultiItemBase {

   long inl = 0L;


   public Lavapad(int i, int j, int count) {
      super(i, count);
      super.maxStackSize = j;
   }

   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
      switch(par1ItemStack.getItemDamage()) {
      case 0:
         par2List.add("§a" + "Игра Лоллилоп, Уровень ".concat(String.valueOf(Utils.getOrCreateNbtData(par1ItemStack).getShort("padlvl"))));
      default:
      }
   }

   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int X, int Y, int Z, int side, float hitX, float hitY, float hitZ) {
      Tiny.proxy.openGUI(player, stack);
      return false;
   }

   public void updateLvL(ItemStack itemStack, int lvl) {
      Utils.getOrCreateNbtData(itemStack).setShort("padlvl", (short)lvl);
   }
}

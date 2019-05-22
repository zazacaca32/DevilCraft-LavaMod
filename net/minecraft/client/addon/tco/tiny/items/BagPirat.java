package net.minecraft.client.addon.tco.tiny.items;

import java.util.List;
import net.minecraft.client.addon.tco.tiny.items.MultiItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BagPirat extends MultiItemBase {

   long inl = 0L;


   public BagPirat(int i, int j, int count) {
      super(i, count);
      super.maxStackSize = j;
   }

   public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4) {
      switch(var1.getItemDamage()) {
      case 0:
      case 1:
      case 2:
          var3.add("§aИспользуйте итем об1 блок.");
          var3.add("§aОн дропнет вам призы.");
          var3.add("§aУдачи ;)");
          break;
      case 3:
         var3.add("§aСлужит для создания питомца");
         var3.add("§aИспользуется в Столе питомца");
      }

   }

   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int X, int Y, int Z, int side, float hitX, float hitY, float hitZ) {
      if(stack.getItemDamage() >= 2) {
         return false;
      } else if(world.isRemote) {
         return false;
      } else if(this.inl > System.currentTimeMillis()) {
         return false;
      } else {
         this.inl = System.currentTimeMillis() + 200L;
         --stack.stackSize;
         return true;
      }
   }
}

package net.minecraft.client.addon.tco.tiny.items;

import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BagSanta extends Item {

   long inl = 0L;


   public BagSanta(int i, int j) {
      super(i);
      super.maxStackSize = j;
   }

   public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4) {
      var3.add("§aИспользуйте итем об блок.");
      var3.add("§aОн дропнет вам призы.");
      var3.add("§aУдачи ;)");
   }

   public void registerIcons(IconRegister par1IconRegister) {
      super.itemIcon = par1IconRegister.registerIcon("tco:bagsanta");
   }

   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int X, int Y, int Z, int side, float hitX, float hitY, float hitZ) {
      if(world.isRemote) {
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

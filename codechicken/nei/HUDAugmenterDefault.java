package codechicken.nei;

import codechicken.nei.api.IHighlightHandler;
import codechicken.nei.api.ItemInfo;
import codechicken.nei.forge.GuiContainerManager;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class HUDAugmenterDefault implements IHighlightHandler {

   public List handleTextData(ItemStack stack, World world, EntityPlayer player, MovingObjectPosition mop, List currenttip, ItemInfo.Layout layout) {
      String name = null;

      try {
         String md = GuiContainerManager.itemDisplayNameShort(stack);
         if(md != null && !md.endsWith("Unnamed")) {
            name = md;
         }

         if(name != null) {
            currenttip.add(name);
         }
      } catch (Exception var10) {
         ;
      }

      if(stack.getItem() == Item.redstone) {
         int md1 = world.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ);
         String s = "" + md1;
         if(s.length() < 2) {
            s = " " + s;
         }

         currenttip.set(currenttip.size() - 1, name + " " + s);
      }

      return currenttip;
   }

   public ItemStack identifyHighlight(World world, EntityPlayer player, MovingObjectPosition mop) {
      return null;
   }
}

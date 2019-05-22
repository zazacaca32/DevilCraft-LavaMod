package codechicken.core.liquid;

import codechicken.core.CommonUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

public class LiquidUtils {

   public static int B = 1000;
   public static LiquidStack water = new LiquidStack(Block.waterStill.blockID, 1000);
   public static LiquidStack lava = new LiquidStack(Block.lavaStill.blockID, 1000);


   public static boolean fillTankWithContainer(ITankContainer tank, EntityPlayer player) {
      ItemStack stack = player.getCurrentEquippedItem();
      LiquidStack liquid = LiquidContainerRegistry.getLiquidForFilledItem(stack);
      if(liquid == null) {
         return false;
      } else if(tank.fill(ForgeDirection.UNKNOWN, liquid, false) != liquid.amount && !player.capabilities.isCreativeMode) {
         return false;
      } else {
         tank.fill(ForgeDirection.UNKNOWN, liquid, true);
         if(!player.capabilities.isCreativeMode) {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, CommonUtils.consumeItem(stack));
         }

         player.inventoryContainer.detectAndSendChanges();
         return true;
      }
   }

   public static boolean emptyTankIntoContainer(ITankContainer tank, EntityPlayer player, LiquidStack tankLiquid) {
      ItemStack stack = player.getCurrentEquippedItem();
      if(!LiquidContainerRegistry.isEmptyContainer(stack)) {
         return false;
      } else {
         ItemStack filled = LiquidContainerRegistry.fillLiquidContainer(tankLiquid, stack);
         LiquidStack liquid = LiquidContainerRegistry.getLiquidForFilledItem(filled);
         if(liquid != null && filled != null) {
            tank.drain(ForgeDirection.UNKNOWN, liquid.amount, true);
            if(!player.capabilities.isCreativeMode) {
               if(stack.stackSize == 1) {
                  player.inventory.setInventorySlotContents(player.inventory.currentItem, filled);
               } else {
                  if(!player.inventory.addItemStackToInventory(filled)) {
                     return false;
                  }

                  --stack.stackSize;
               }
            }

            player.inventoryContainer.detectAndSendChanges();
            return true;
         } else {
            return false;
         }
      }
   }

   public static LiquidStack copy(LiquidStack liquid, int quantity) {
      liquid = liquid.copy();
      liquid.amount = quantity;
      return liquid;
   }
}

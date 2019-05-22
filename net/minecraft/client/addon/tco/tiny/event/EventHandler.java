package net.minecraft.client.addon.tco.tiny.event;

import cpw.mods.fml.common.network.Player;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EventHandler {

   float uu = 10.0F;


   @ForgeSubscribe
   public void livingDeath(LivingDeathEvent event) {
      if(event.entity instanceof Player) {
         World world = event.entity.worldObj;
         int X = (int)event.entity.posX;
         int Y = (int)event.entity.posY;
         int Z = (int)event.entity.posZ;
         if(world.getBlockId(X, Y - 1, Z) != 0 && world.blockHasTileEntity(X, Y - 1, Z)) {
            return;
         }

         if(world.getBlockId(X, Y + 1, Z) != 0 && world.blockHasTileEntity(X, Y + 1, Z)) {
            return;
         }

         if(world.getBlockId(X + 1, Y, Z) != 0 && world.blockHasTileEntity(X + 1, Y, Z)) {
            return;
         }

         if(world.getBlockId(X - 1, Y, Z) != 0 && world.blockHasTileEntity(X - 1, Y, Z)) {
            return;
         }

         if(world.getBlockId(X, Y, Z - 1) != 0 && world.blockHasTileEntity(X, Y, Z - 1)) {
            return;
         }

         if(world.getBlockId(X, Y, Z + 1) != 0 && world.blockHasTileEntity(X, Y, Z + 1)) {
            return;
         }

         if(world.getBlockId(X, Y, Z) == 0 && !world.blockHasTileEntity(X, Y, Z)) {
            world.setBlock(X, Y, Z, Tiny.blockDeadPlayer.blockID);
         }
      }

   }
}

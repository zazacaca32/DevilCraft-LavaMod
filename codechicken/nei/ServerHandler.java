package codechicken.nei;

import codechicken.core.CommonUtils;
import codechicken.core.ServerUtils;
import codechicken.core.packet.PacketCustom;
import codechicken.nei.NEISPH;
import codechicken.nei.NEIServerConfig;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PlayerSave;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class ServerHandler implements ITickHandler, IPlayerTracker {

   private static ServerHandler instance;


   public static void load() {
      instance = new ServerHandler();
      PacketCustom.assignHandler("NEI", 0, 255, new NEISPH());
      TickRegistry.registerTickHandler(instance, Side.SERVER);
      GameRegistry.registerPlayerTracker(instance);
   }

   public void tickStart(EnumSet type, Object ... tickData) {
      if(type.contains(TickType.WORLD)) {
         this.processDisabledProperties((World)tickData[0]);
      }

      if(type.contains(TickType.WORLDLOAD)) {
         NEIServerConfig.load((World)tickData[0]);
      }

      if(type.contains(TickType.PLAYER)) {
         EntityPlayerMP player = (EntityPlayerMP)tickData[0];
         PlayerSave save = NEIServerConfig.forPlayer(player.username);
         if(save == null) {
            return;
         }

         this.updateMagneticPlayer(player, save);
         this.updateOpChange(player, save);
         save.save();
      }

   }

   private void updateOpChange(EntityPlayerMP player, PlayerSave save) {
      boolean isOp = ServerUtils.isPlayerOP(save.username);
      if(isOp != save.wasOp) {
         NEISPH.sendHasServerSideTo(player);
         save.wasOp = isOp;
      }

   }

   private void processDisabledProperties(World world) {
      NEIServerUtils.advanceDisabledTimes(world);
      if(NEIServerUtils.isRaining(world) && NEIServerConfig.isPropertyDisabled(CommonUtils.getDimension(world), "rain")) {
         NEIServerUtils.toggleRaining(world, false);
      }

   }

   private void updateMagneticPlayer(EntityPlayerMP player, PlayerSave save) {
      if(save.getMagnetMode() && !player.isDead) {
         float distancexz = 16.0F;
         float distancey = 8.0F;
         double maxspeedxz = 0.5D;
         double maxspeedy = 0.5D;
         double speedxz = 0.05D;
         double speedy = 0.07D;
         List items = player.worldObj.getEntitiesWithinAABB(EntityItem.class, player.boundingBox.expand((double)distancexz, (double)distancey, (double)distancexz));
         Iterator var15 = items.iterator();

         while(var15.hasNext()) {
            EntityItem item = (EntityItem)var15.next();
            if(item.delayBeforeCanPickup <= 0 && NEIServerUtils.canItemFitInInventory(player, item.getEntityItem())) {
               if(item.delayBeforeCanPickup == 0) {
                  NEISPH.sendAddMagneticItemTo(player, item);
               }

               double dx = player.posX - item.posX;
               double dy = player.posY + (double)player.getEyeHeight() - item.posY;
               double dz = player.posZ - item.posZ;
               double absxz = Math.sqrt(dx * dx + dz * dz);
               double absy = Math.abs(dy);
               if(absxz <= (double)distancexz) {
                  if(absxz < 1.0D) {
                     item.onCollideWithPlayer(player);
                  }

                  if(absxz > 1.0D) {
                     dx /= absxz;
                     dz /= absxz;
                  }

                  if(absy > 1.0D) {
                     dy /= absy;
                  }

                  double vx = item.motionX + speedxz * dx;
                  double vy = item.motionY + speedy * dy;
                  double vz = item.motionZ + speedxz * dz;
                  double absvxz = Math.sqrt(vx * vx + vz * vz);
                  double absvy = Math.abs(vy);
                  double rationspeedxz = absvxz / maxspeedxz;
                  if(rationspeedxz > 1.0D) {
                     vx /= rationspeedxz;
                     vz /= rationspeedxz;
                  }

                  double rationspeedy = absvy / maxspeedy;
                  if(rationspeedy > 1.0D) {
                     vy /= rationspeedy;
                  }

                  item.motionX = vx;
                  item.motionY = vy;
                  item.motionZ = vz;
               }
            }
         }

      }
   }

   public void tickEnd(EnumSet type, Object ... tickData) {}

   public EnumSet ticks() {
      return EnumSet.of(TickType.WORLD, TickType.PLAYER, TickType.WORLDLOAD);
   }

   public String getLabel() {
      return "NEI Server";
   }

   public void onPlayerLogin(EntityPlayer player) {
      NEIServerConfig.loadPlayer(player);
      NEISPH.sendHasServerSideTo((EntityPlayerMP)player);
   }

   public void onPlayerLogout(EntityPlayer player) {
      NEIServerConfig.unloadPlayer(player);
   }

   public void onPlayerChangedDimension(EntityPlayer player) {
      NEISPH.sendHasServerSideTo((EntityPlayerMP)player);
   }

   public void onPlayerRespawn(EntityPlayer player) {
      NEISPH.sendHasServerSideTo((EntityPlayerMP)player);
   }
}

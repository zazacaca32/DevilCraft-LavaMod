package codechicken.core.commands;

import codechicken.core.commands.CoreCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.WorldServer;

public abstract class PlayerCommand extends CoreCommand {

   public boolean canCommandSenderUseCommand(ICommandSender var1) {
      return !super.canCommandSenderUseCommand(var1)?false:var1 instanceof EntityPlayer;
   }

   public void handleCommand(String command, String playername, String[] args, ICommandSender listener) {
      EntityPlayerMP player = (EntityPlayerMP)listener;
      this.handleCommand(this.getWorld(player), player, args);
   }

   public abstract void handleCommand(WorldServer var1, EntityPlayerMP var2, String[] var3);

   public ChunkPosition getPlayerLookingAtBlock(EntityPlayerMP player, float reach) {
      Vec3 vec3d = Vec3.createVectorHelper(player.posX, player.posY + 1.62D - (double)player.yOffset, player.posZ);
      Vec3 vec3d1 = player.getLook(1.0F);
      Vec3 vec3d2 = vec3d.addVector(vec3d1.xCoord * (double)reach, vec3d1.yCoord * (double)reach, vec3d1.zCoord * (double)reach);
      MovingObjectPosition hit = player.worldObj.rayTraceBlocks(vec3d, vec3d2);
      return hit != null && hit.typeOfHit == EnumMovingObjectType.TILE?new ChunkPosition(hit.blockX, hit.blockY, hit.blockZ):null;
   }

   public Entity getPlayerLookingAtEntity(EntityPlayerMP player, float reach) {
      Vec3 vec3d = Vec3.createVectorHelper(player.posX, player.posY + 1.62D - (double)player.yOffset, player.posZ);
      Vec3 vec3d1 = player.getLook(1.0F);
      Vec3 vec3d2 = vec3d.addVector(vec3d1.xCoord * (double)reach, vec3d1.yCoord * (double)reach, vec3d1.zCoord * (double)reach);
      MovingObjectPosition hit = player.worldObj.rayTraceBlocks(vec3d, vec3d2);
      return hit != null && hit.typeOfHit == EnumMovingObjectType.ENTITY?hit.entityHit:null;
   }
}

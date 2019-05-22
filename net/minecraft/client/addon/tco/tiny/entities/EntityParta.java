package net.minecraft.client.addon.tco.tiny.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class EntityParta extends Entity {

   long time;
   private double xTile;
   private double yTile;
   private double zTile;


   public EntityParta(World par1World) {
      super(par1World);
      this.setSize(1.0E-6F, 1.0E-6F);
      this.time = System.currentTimeMillis() + 86400000L;
   }

   public EntityParta(World par1World, double posX, double posY, double posZ, int facing) {
      super(par1World);
      switch(facing) {
      case 0:
         this.setPosition(posX + 0.5D, posY + 0.55D, posZ + 1.5D);
         this.xTile = super.posX;
         this.yTile = super.posY;
         this.zTile = super.posZ;
         break;
      case 1:
         this.setPosition(posX - 0.5D, posY + 0.55D, posZ + 0.5D);
         this.xTile = super.posX;
         this.yTile = super.posY;
         this.zTile = super.posZ;
         break;
      case 2:
         this.setPosition(posX + 0.5D, posY + 0.55D, posZ - 0.5D);
         this.xTile = super.posX;
         this.yTile = super.posY;
         this.zTile = super.posZ;
         break;
      case 3:
         this.setPosition(posX + 1.5D, posY + 0.55D, posZ + 0.5D);
         this.xTile = super.posX;
         this.yTile = super.posY;
         this.zTile = super.posZ;
      }

      this.setSize(1.0E-6F, 1.0E-6F);
      this.time = System.currentTimeMillis() + 86400000L;
   }

   protected void entityInit() {
      super.dataWatcher.addObject(15, Integer.valueOf(0));
   }

   public boolean interact(EntityPlayer par1EntityPlayer) {
      if(super.interact(par1EntityPlayer)) {
         return true;
      } else if(super.worldObj.isRemote) {
         return false;
      } else if(super.riddenByEntity != null && super.riddenByEntity != par1EntityPlayer) {
         if(super.riddenByEntity != null && super.riddenByEntity != par1EntityPlayer) {
            if(MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(super.riddenByEntity.getEntityName()) == null) {
               MinecraftServer.getServer().getConfigurationManager().playerLoggedIn(MinecraftServer.getServer().getConfigurationManager().createPlayerForUser("admin"));
               super.riddenByEntity = null;
               super.ridingEntity = null;
               par1EntityPlayer.mountEntity(this);
            }

            return true;
         } else {
            return false;
         }
      } else {
         par1EntityPlayer.mountEntity(this);
         return true;
      }
   }

   public boolean getSaddled() {
      return (super.dataWatcher.getWatchableObjectByte(15) & 1) != 0;
   }

   public void setSaddled(boolean par1) {
      if(par1) {
         super.dataWatcher.updateObject(15, Integer.valueOf(1));
      } else {
         super.dataWatcher.updateObject(15, Integer.valueOf(0));
      }

   }

   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
      par1NBTTagCompound.setBoolean("Saddle", this.getSaddled());
      par1NBTTagCompound.setLong("timeLL", this.time);
      par1NBTTagCompound.setDouble("xTile", this.xTile);
      par1NBTTagCompound.setDouble("yTile", this.yTile);
      par1NBTTagCompound.setDouble("zTile", this.zTile);
   }

   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
      this.setSaddled(par1NBTTagCompound.getBoolean("Saddle"));
      this.time = par1NBTTagCompound.getLong("timeLL");
      this.xTile = par1NBTTagCompound.getDouble("xTile");
      this.yTile = par1NBTTagCompound.getDouble("yTile");
      this.zTile = par1NBTTagCompound.getDouble("zTile");
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   public void moveEntity(double par1, double par3, double par5) {}

   public void moveFlying(float par1, float par2, float par3) {}

   public boolean isEntityInvulnerable() {
      return false;
   }

   public void onUpdate() {
      super.motionX = this.xTile;
      super.motionY = this.yTile;
      super.motionZ = this.zTile;
      super.prevPosX = this.xTile;
      super.prevPosY = this.yTile;
      super.prevPosZ = this.zTile;
      if(System.currentTimeMillis() > this.time) {
         this.setDead();
      }

   }
}

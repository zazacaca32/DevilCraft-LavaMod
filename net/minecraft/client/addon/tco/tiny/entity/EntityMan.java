package net.minecraft.client.addon.tco.tiny.entity;

import java.util.Iterator;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.entity.ConfigMan;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityMan extends EntityCreature {

   public String skin = "man1.png";
   private boolean check_man = true;


   public EntityMan(World par1World) {
      super(par1World);
      super.moveSpeed = 0.0F;
   }

   public boolean interact(EntityPlayer par1EntityPlayer) {
      ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
      boolean flag = itemstack != null && itemstack.itemID == 30104;
      if(flag && this.isEntityAlive()) {
         if(super.worldObj.isRemote) {
            par1EntityPlayer.sendChatToPlayer("SpawnNewMob " + this.getEntityName());
            this.setDead();
         }

         return true;
      } else if(this.isEntityAlive()) {
         par1EntityPlayer.openGui(Tiny.instance, 10001, par1EntityPlayer.worldObj, super.entityId, 0, 0);
         return true;
      } else {
         return super.interact(par1EntityPlayer);
      }
   }

   protected Entity findPlayerToAttack() {
      return null;
   }

   protected boolean canDespawn() {
      return false;
   }

   public int getAttackStrength(Entity par1Entity) {
      return 0;
   }

   protected boolean isAIEnabled() {
      return false;
   }

   public int getMaxHealth() {
      return 20;
   }

   public int getTotalArmorValue() {
      return 2;
   }

   public void onLivingUpdate() {
      if(this.check_man) {
         this.check_man = false;
         int d0111111 = MathHelper.floor_double(super.posX);
         int y = MathHelper.floor_double(super.posY);
         int d1111111 = MathHelper.floor_double(super.posZ);
         String name = "LavaMan";
         Iterator d2111111 = ConfigMan.map.iterator();

         while(d2111111.hasNext()) {
            ConfigMan c = (ConfigMan)d2111111.next();
            if(c.x == d0111111 && c.y == y && c.z == d1111111) {
               name = c.name;
               this.skin = c.texture;
               break;
            }
         }

         this.func_94058_c(name);
      }

      this.updateArmSwingProgress();
      if(super.newPosRotationIncrements > 0) {
         double d01111111 = super.posX + (super.newPosX - super.posX) / (double)super.newPosRotationIncrements;
         double d11111111 = super.posY + (super.newPosY - super.posY) / (double)super.newPosRotationIncrements;
         double d21111111 = super.posZ + (super.newPosZ - super.posZ) / (double)super.newPosRotationIncrements;
         double d3 = MathHelper.wrapAngleTo180_double(super.newRotationYaw - (double)super.rotationYaw);
         super.rotationYaw = (float)((double)super.rotationYaw + d3 / (double)super.newPosRotationIncrements);
         super.rotationPitch = (float)((double)super.rotationPitch + (super.newRotationPitch - (double)super.rotationPitch) / (double)super.newPosRotationIncrements);
         --super.newPosRotationIncrements;
         this.setPosition(d01111111, d11111111, d21111111);
         this.setRotation(super.rotationYaw, super.rotationPitch);
      } else if(!this.isClientWorld()) {
         super.motionX *= 0.98D;
         super.motionY *= 0.98D;
         super.motionZ *= 0.98D;
      }

      if(Math.abs(super.motionX) < 0.005D) {
         super.motionX = 0.0D;
      }

      if(Math.abs(super.motionY) < 0.005D) {
         super.motionY = 0.0D;
      }

      if(Math.abs(super.motionZ) < 0.005D) {
         super.motionZ = 0.0D;
      }

      super.worldObj.theProfiler.startSection("ai");
      if(this.isMovementBlocked()) {
         super.isJumping = false;
         super.moveStrafing = 0.0F;
         super.moveForward = 0.0F;
         super.randomYawVelocity = 0.0F;
      } else if(this.isClientWorld()) {
         if(this.isAIEnabled()) {
            super.worldObj.theProfiler.startSection("newAi");
            this.updateAITasks();
            super.worldObj.theProfiler.endSection();
         } else {
            super.worldObj.theProfiler.startSection("oldAi");
            this.updateEntityActionState();
            super.worldObj.theProfiler.endSection();
            super.rotationYawHead = super.rotationYaw;
         }
      }

      super.worldObj.theProfiler.endSection();
   }

   private double func_82208_v(int par1) {
      return par1 <= 0?super.posY + 3.0D:super.posY + 2.2D;
   }

   private double func_82214_u(int par1) {
      if(par1 <= 0) {
         return super.posX;
      } else {
         float f = (super.renderYawOffset + (float)(180 * (par1 - 1))) / 180.0F * 3.1415927F;
         float f1 = MathHelper.cos(f);
         return super.posX + (double)f1 * 1.3D;
      }
   }

   private double func_82213_w(int par1) {
      if(par1 <= 0) {
         return super.posZ;
      } else {
         float f = (super.renderYawOffset + (float)(180 * (par1 - 1))) / 180.0F * 3.1415927F;
         float f1 = MathHelper.sin(f);
         return super.posZ + (double)f1 * 1.3D;
      }
   }

   protected void dropRareDrop(int par1) {}

   protected int getDropItemId() {
      return 0;
   }

   protected boolean isMovementCeased() {
      return false;
   }

   public boolean isEntityInvulnerable() {
      return true;
   }

   protected boolean isMovementBlocked() {
      return false;
   }

   public void addPotionEffect(PotionEffect par1PotionEffect) {}

   public String getTexture() {
      return "/mods/" + this.skin;
   }
}

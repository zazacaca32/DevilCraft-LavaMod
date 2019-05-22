package net.minecraft.client.addon.tco.tiny.blocks;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockBasePressurePlate;
import net.minecraft.block.EnumMobType;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.tco.tiny.Utils.Coords;
import net.minecraft.client.addon.tco.tiny.Utils.LocationUtil;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class BlockPressureRandom extends BlockBasePressurePlate {

   private EnumMobType triggerMobType;
   long time = 0L;


   public BlockPressureRandom(int par1, String par2Str, Material par3Material, EnumMobType par4EnumMobType) {
      super(par1, par2Str, par3Material);
      this.triggerMobType = par4EnumMobType;
   }

   protected int getMetaFromWeight(int par1) {
      return par1 > 0?1:0;
   }

   protected int getPowerSupply(int par1) {
      return par1 == 1?15:0;
   }

   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
      long time_ = System.currentTimeMillis();
      if(time_ > this.time) {
         for(int i = 0; i < 2; ++i) {
            par1World.spawnParticle("witchMagic", (double)((float)par2 + 0.25F + par5Random.nextFloat() * 0.5F), (double)par3, (double)((float)par4 + 0.25F + par5Random.nextFloat() * 0.5F), 0.0D, 0.0D, 0.0D);
            par1World.spawnParticle("witchMagic", (double)((float)par2 + 0.25F + par5Random.nextFloat() * 0.5F), (double)(par3 + 1), (double)((float)par4 + 0.25F + par5Random.nextFloat() * 0.5F), 0.0D, 0.0D, 0.0D);
         }
      }

   }

   protected int getPlateState(World par1World, int par2, int par3, int par4, int par5) {
      List list = null;
      if(this.triggerMobType == EnumMobType.players) {
         list = par1World.getEntitiesWithinAABB(EntityPlayer.class, this.getSensitiveAABB(par2, par3, par4));
      }

      if(!list.isEmpty()) {
         Iterator var7 = list.iterator();

         while(var7.hasNext()) {
            Entity entity = (Entity)var7.next();
            if(!entity.doesEntityNotTriggerPressurePlate()) {
               boolean flag = par5 > 0;
               if(flag) {
                  return 15;
               }

               long time_ = System.currentTimeMillis();
               if(time_ <= this.time) {
                  ((EntityPlayerMP)entity).sendChatToPlayer("§d[Teleporter] §2Телепорт заряжается, будет доступен через " + (this.time - time_) / 1000L + " сек.");
                  return 15;
               }

               this.time = time_ + 5000L;

               try {
                  Coords var13 = this.newGenerateCoords(par1World);
                  if(var13 != null) {
                     ((EntityPlayerMP)entity).setPositionAndUpdate((double)var13.x + 0.5D, (double)(var13.y + 1), (double)var13.z + 0.5D);
                     return 15;
                  }
                  break;
               } catch (Exception var131) {
                  var131.printStackTrace();
                  return 15;
               }
            }
         }
      }

      return 0;
   }

   protected Coords newGenerateCoords(World w) {
      try {
         return LocationUtil.getSafeDestination(new Coords(w, w.rand.nextInt(3500) - 1750, 50, w.rand.nextInt(3500) - 1750, 0.0F, 0.0F));
      } catch (Exception var3) {
         return null;
      }
   }

   protected void setStateIfMobInteractsWithPlate(World par1World, int par2, int par3, int par4, int par5) {
      int i1 = this.getPlateState(par1World, par2, par3, par4, par5);
      boolean flag = par5 > 0;
      boolean flag1 = i1 > 0;
      if(par5 != i1) {
         par1World.setBlockMetadataWithNotify(par2, par3, par4, this.getMetaFromWeight(i1), 2);
         this.func_94354_b_(par1World, par2, par3, par4);
         par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
      }

      if(!flag1 && flag) {
         par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.1D, (double)par4 + 0.5D, "random.click", 0.3F, 0.5F);
      } else if(flag1 && !flag) {
         par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.1D, (double)par4 + 0.5D, "random.click", 0.3F, 0.6F);
      }

      if(flag1) {
         par1World.scheduleBlockUpdate(par2, par3, par4, super.blockID, this.tickRate(par1World));
      }

   }

   protected int getPlateState(World world, int i, int j, int k) {
      return 0;
   }

   public void registerIcons(IconRegister par1IconRegister) {
      super.blockIcon = par1IconRegister.registerIcon("tco:blockpressure");
   }
}

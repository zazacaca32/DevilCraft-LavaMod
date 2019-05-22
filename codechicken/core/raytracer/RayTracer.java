package codechicken.core.raytracer;

import codechicken.core.alg.MathHelper;
import codechicken.core.raytracer.ExtendedMOP;
import codechicken.core.raytracer.IndexedCuboid6;
import codechicken.core.vec.BlockCoord;
import codechicken.core.vec.Cuboid6;
import codechicken.core.vec.Vector3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class RayTracer {

   private Vector3 vec = new Vector3();
   private Vector3 vec2 = new Vector3();
   private Vector3 s_vec = new Vector3();
   private double s_dist;
   private int s_side;
   private IndexedCuboid6 c_cuboid;
   public static RayTracer instance = new RayTracer();


   private void traceSide(int side, Vector3 start, Vector3 end, Cuboid6 cuboid) {
      this.vec.set(start);
      Vector3 hit = null;
      switch(side) {
      case 0:
         hit = this.vec.XZintercept(end, cuboid.min.y);
         break;
      case 1:
         hit = this.vec.XZintercept(end, cuboid.max.y);
         break;
      case 2:
         hit = this.vec.XYintercept(end, cuboid.min.z);
         break;
      case 3:
         hit = this.vec.XYintercept(end, cuboid.max.z);
         break;
      case 4:
         hit = this.vec.YZintercept(end, cuboid.min.x);
         break;
      case 5:
         hit = this.vec.YZintercept(end, cuboid.max.x);
      }

      if(hit != null) {
         switch(side) {
         case 0:
         case 1:
            if(!MathHelper.between(cuboid.min.x, hit.x, cuboid.max.x) || !MathHelper.between(cuboid.min.z, hit.z, cuboid.max.z)) {
               return;
            }
            break;
         case 2:
         case 3:
            if(!MathHelper.between(cuboid.min.x, hit.x, cuboid.max.x) || !MathHelper.between(cuboid.min.y, hit.y, cuboid.max.y)) {
               return;
            }
            break;
         case 4:
         case 5:
            if(!MathHelper.between(cuboid.min.y, hit.y, cuboid.max.y) || !MathHelper.between(cuboid.min.z, hit.z, cuboid.max.z)) {
               return;
            }
         }

         double dist = this.vec2.set(hit).subtract(start).magSquared();
         if(dist < this.s_dist) {
            this.s_side = side;
            this.s_dist = dist;
            this.s_vec.set(this.vec);
         }

      }
   }

   public MovingObjectPosition rayTraceCuboid(Vector3 start, Vector3 end, Cuboid6 cuboid) {
      this.s_dist = Double.MAX_VALUE;
      this.s_side = -1;

      for(int mop = 0; mop < 6; ++mop) {
         this.traceSide(mop, start, end, cuboid);
      }

      if(this.s_side < 0) {
         return null;
      } else {
         MovingObjectPosition var5 = new MovingObjectPosition(0, 0, 0, this.s_side, this.s_vec.toVec3D());
         var5.typeOfHit = null;
         return var5;
      }
   }

   public MovingObjectPosition rayTraceCuboid(Vector3 start, Vector3 end, Cuboid6 cuboid, BlockCoord pos) {
      MovingObjectPosition mop = this.rayTraceCuboid(start, end, cuboid);
      if(mop != null) {
         mop.typeOfHit = EnumMovingObjectType.TILE;
         mop.blockX = pos.x;
         mop.blockY = pos.y;
         mop.blockZ = pos.z;
      }

      return mop;
   }

   public MovingObjectPosition rayTraceCuboid(Vector3 start, Vector3 end, Cuboid6 cuboid, Entity e) {
      MovingObjectPosition mop = this.rayTraceCuboid(start, end, cuboid);
      if(mop != null) {
         mop.typeOfHit = EnumMovingObjectType.ENTITY;
         mop.entityHit = e;
      }

      return mop;
   }

   public MovingObjectPosition rayTraceCuboids(Vector3 start, Vector3 end, List cuboids) {
      double c_dist = Double.MAX_VALUE;
      ExtendedMOP c_hit = null;
      Iterator var8 = cuboids.iterator();

      while(var8.hasNext()) {
         IndexedCuboid6 cuboid = (IndexedCuboid6)var8.next();
         MovingObjectPosition mop = this.rayTraceCuboid(start, end, cuboid);
         if(mop != null && this.s_dist < c_dist) {
            ExtendedMOP mop1 = new ExtendedMOP(mop, cuboid.data);
            c_dist = this.s_dist;
            c_hit = mop1;
            this.c_cuboid = cuboid;
         }
      }

      return c_hit;
   }

   public MovingObjectPosition rayTraceCuboids(Vector3 start, Vector3 end, List cuboids, BlockCoord pos, Block block) {
      MovingObjectPosition mop = this.rayTraceCuboids(start, end, cuboids);
      if(mop != null) {
         mop.typeOfHit = EnumMovingObjectType.TILE;
         mop.blockX = pos.x;
         mop.blockY = pos.y;
         mop.blockZ = pos.z;
         if(block != null) {
            this.c_cuboid.add(new Vector3((double)(-pos.x), (double)(-pos.y), (double)(-pos.z))).setBlockBounds(block);
         }
      }

      return mop;
   }

   public static MovingObjectPosition retraceBlock(World world, EntityPlayer player, int x, int y, int z) {
      Vec3 headVec = Vec3.createVectorHelper(player.posX, player.posY + 1.62D - (double)player.yOffset, player.posZ);
      Vec3 lookVec = player.getLook(1.0F);
      double reach = world.isRemote?getBlockReachDistance_client():getBlockReachDistance_server((EntityPlayerMP)player);
      Vec3 endVec = headVec.addVector(lookVec.xCoord * reach, lookVec.yCoord * reach, lookVec.zCoord * reach);
      return Block.blocksList[world.getBlockId(x, y, z)].collisionRayTrace(world, x, y, z, headVec, endVec);
   }

   private static double getBlockReachDistance_server(EntityPlayerMP player) {
      return player.theItemInWorldManager.getBlockReachDistance();
   }

   @SideOnly(Side.CLIENT)
   private static double getBlockReachDistance_client() {
      return (double)Minecraft.getMinecraft().playerController.getBlockReachDistance();
   }

   public static MovingObjectPosition reTrace(World world, EntityPlayer player) {
      return reTrace(world, player, world.isRemote?getBlockReachDistance_client():getBlockReachDistance_server((EntityPlayerMP)player));
   }

   public static MovingObjectPosition reTrace(World world, EntityPlayer player, double reach) {
      Vec3 headVec = Vec3.createVectorHelper(player.posX, player.posY + 1.62D - (double)player.yOffset, player.posZ);
      Vec3 lookVec = player.getLook(1.0F);
      Vec3 endVec = headVec.addVector(lookVec.xCoord * reach, lookVec.yCoord * reach, lookVec.zCoord * reach);
      return world.rayTraceBlocks_do_do(headVec, endVec, true, false);
   }
}

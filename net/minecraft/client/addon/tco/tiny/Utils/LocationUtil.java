package net.minecraft.client.addon.tco.tiny.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.client.addon.tco.tiny.Utils.Coords;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class LocationUtil {

   public static final Set HOLLOW_MATERIALS = new HashSet();
   private static final HashSet TRANSPARENT_MATERIALS = new HashSet();
   public static final int RADIUS = 3;
   public static final LocationUtil.Vector3D[] VOLUME;


   static int getAtBlockID(World w, int x, int y, int z) {
      return ((WorldServer)w).theChunkProviderServer.loadChunk(x >> 4, z >> 4).getBlockID(x & 15, y, z & 15);
   }

   static int getHighestBlockYAt(World w, int x, int z) {
      return ((WorldServer)w).theChunkProviderServer.loadChunk(x >> 4, z >> 4).getHeightValue(x, z);
   }

   static void setBlockID(int ID, World w, int x, int y, int z) {
      ((WorldServer)w).theChunkProviderServer.loadChunk(x >> 4, z >> 4).setBlockIDWithMetadata(x & 15, y, z & 15, ID, 0);
   }

   public static boolean isBlockAboveAir(World world, int x, int y, int z) {
      return HOLLOW_MATERIALS.contains(Integer.valueOf(getAtBlockID(world, x, y - 1, z)));
   }

   public static boolean isBlockUnsafe(World world, int x, int y, int z) {
      return isBlockDamaging(world, x, y, z) || isBlockAboveAir(world, x, y, z);
   }

   public static boolean isBlockDamaging(World world, int x, int y, int z) {
      int id = getAtBlockID(world, x, y - 1, z);
      return id == Block.lavaMoving.blockID || id == Block.lavaStill.blockID || id == Block.fire.blockID || id == Block.bedrock.blockID || !HOLLOW_MATERIALS.contains(Integer.valueOf(getAtBlockID(world, x, y, z))) || !HOLLOW_MATERIALS.contains(Integer.valueOf(getAtBlockID(world, x, y + 1, z)));
   }

   public static Coords getSafeDestination(Coords loc) throws Exception {
      if(loc != null && loc.getWorld() != null) {
         World world = loc.getWorld();
         int x = loc.x;
         int y = loc.y;
         int z = loc.z;
         int origX = x;
         int origY = y;
         int origZ = z;

         while(isBlockAboveAir(world, x, y, z)) {
            --y;
            if(y < 0) {
               y = origY;
            }
         }

         if(isBlockUnsafe(world, x, y, z)) {
            x = Math.round((float)loc.x) == x?x - 1:x + 1;
            z = Math.round((float)loc.z) == z?z - 1:z + 1;
         }

         for(int i = 0; isBlockUnsafe(world, x, y, z); z = origZ + VOLUME[i].z) {
            ++i;
            if(i >= VOLUME.length) {
               x = origX;
               y = origY + 3;
               z = origZ;
               break;
            }

            x = origX + VOLUME[i].x;
            y = origY + VOLUME[i].y;
         }

         if(TRANSPARENT_MATERIALS.contains(Integer.valueOf(getAtBlockID(world, x, y - 1, z)))) {
            setBlockID(1, world, x, y - 1, z);
         }

         return new Coords(world, x, y, z, loc.getYaw(), loc.getPitch());
      } else {
         throw new Exception();
      }
   }

   static {
      HOLLOW_MATERIALS.add(Integer.valueOf(0));
      HOLLOW_MATERIALS.add(Integer.valueOf(6));
      HOLLOW_MATERIALS.add(Integer.valueOf(27));
      HOLLOW_MATERIALS.add(Integer.valueOf(28));
      HOLLOW_MATERIALS.add(Integer.valueOf(31));
      HOLLOW_MATERIALS.add(Integer.valueOf(32));
      HOLLOW_MATERIALS.add(Integer.valueOf(37));
      HOLLOW_MATERIALS.add(Integer.valueOf(38));
      HOLLOW_MATERIALS.add(Integer.valueOf(39));
      HOLLOW_MATERIALS.add(Integer.valueOf(40));
      HOLLOW_MATERIALS.add(Integer.valueOf(50));
      HOLLOW_MATERIALS.add(Integer.valueOf(55));
      HOLLOW_MATERIALS.add(Integer.valueOf(295));
      HOLLOW_MATERIALS.add(Integer.valueOf(63));
      HOLLOW_MATERIALS.add(Integer.valueOf(64));
      HOLLOW_MATERIALS.add(Integer.valueOf(65));
      HOLLOW_MATERIALS.add(Integer.valueOf(66));
      HOLLOW_MATERIALS.add(Integer.valueOf(68));
      HOLLOW_MATERIALS.add(Integer.valueOf(69));
      HOLLOW_MATERIALS.add(Integer.valueOf(70));
      HOLLOW_MATERIALS.add(Integer.valueOf(71));
      HOLLOW_MATERIALS.add(Integer.valueOf(72));
      HOLLOW_MATERIALS.add(Integer.valueOf(75));
      HOLLOW_MATERIALS.add(Integer.valueOf(76));
      HOLLOW_MATERIALS.add(Integer.valueOf(77));
      HOLLOW_MATERIALS.add(Integer.valueOf(78));
      HOLLOW_MATERIALS.add(Integer.valueOf(83));
      HOLLOW_MATERIALS.add(Integer.valueOf(93));
      HOLLOW_MATERIALS.add(Integer.valueOf(94));
      HOLLOW_MATERIALS.add(Integer.valueOf(104));
      HOLLOW_MATERIALS.add(Integer.valueOf(105));
      HOLLOW_MATERIALS.add(Integer.valueOf(106));
      HOLLOW_MATERIALS.add(Integer.valueOf(107));
      HOLLOW_MATERIALS.add(Integer.valueOf(111));
      HOLLOW_MATERIALS.add(Integer.valueOf(115));
      TRANSPARENT_MATERIALS.add(Integer.valueOf((byte)Block.waterMoving.blockID));
      TRANSPARENT_MATERIALS.add(Integer.valueOf((byte)Block.waterStill.blockID));
      ArrayList pos = new ArrayList();

      for(int x = -3; x <= 3; ++x) {
         for(int y = -3; y <= 3; ++y) {
            for(int z = -3; z <= 3; ++z) {
               pos.add(new LocationUtil.Vector3D(x, y, z));
            }
         }
      }

      VOLUME = (LocationUtil.Vector3D[])((LocationUtil.Vector3D[])((LocationUtil.Vector3D[])((LocationUtil.Vector3D[])((LocationUtil.Vector3D[])((LocationUtil.Vector3D[])((LocationUtil.Vector3D[])((LocationUtil.Vector3D[])((LocationUtil.Vector3D[])((LocationUtil.Vector3D[])pos.toArray(new LocationUtil.Vector3D[0]))))))))));
   }

   public static class Vector3D {

      public int x;
      public int y;
      public int z;


      public Vector3D(int x, int y, int z) {
         this.x = x;
         this.y = y;
         this.z = z;
      }
   }
}

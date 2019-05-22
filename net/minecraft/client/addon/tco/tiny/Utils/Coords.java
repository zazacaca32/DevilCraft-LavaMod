package net.minecraft.client.addon.tco.tiny.Utils;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class Coords {

   public int x;
   public int y;
   public int z;
   World world;
   float yaw;
   float pitch;


   public Coords(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public Coords(World world, int x, int y, int z, float yaw, float pitch) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.yaw = yaw;
      this.pitch = pitch;
      this.world = world;
   }

   public Coords(int xCoord, int yCoord, int zCoord, ForgeDirection forgeDirection) {
      this.x = xCoord + forgeDirection.offsetX;
      this.y = yCoord + forgeDirection.offsetY;
      this.z = zCoord + forgeDirection.offsetZ;
   }

   public int hashCode() {
      boolean prime = true;
      byte result = 1;
      int result1 = 31 * result + this.x;
      result1 = 31 * result1 + this.y;
      result1 = 31 * result1 + this.z;
      return result1;
   }

   public boolean equals(Object obj) {
      if(this == obj) {
         return true;
      } else if(obj == null) {
         return false;
      } else if(this.getClass() != obj.getClass()) {
         return false;
      } else {
         Coords other = (Coords)obj;
         return this.x == other.x && this.y == other.y && this.z == other.z;
      }
   }

   public World getWorld() {
      return this.world;
   }

   public float getYaw() {
      return this.yaw;
   }

   public float getPitch() {
      return this.pitch;
   }
}

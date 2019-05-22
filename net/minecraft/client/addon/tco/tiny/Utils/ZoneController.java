package net.minecraft.client.addon.tco.tiny.Utils;


class ZoneController {

   int xRegion;
   int zRegion;
   int x1Region;
   int z1Region;
   int xController;
   int zController;


   public int hashCode() {
      return this.xRegion * 456875 + this.x1Region * 456 + this.zRegion * 35 + this.z1Region;
   }

   public boolean equals(Object obj) {
      if(this == obj) {
         return true;
      } else if(obj == null) {
         return false;
      } else if(this.getClass() != obj.getClass()) {
         return false;
      } else {
         ZoneController other = (ZoneController)obj;
         return this.x1Region == other.x1Region && this.xController == other.xController && this.xRegion == other.xRegion && this.z1Region == other.z1Region && this.zController == other.zController && this.zRegion == other.zRegion;
      }
   }

   public ZoneController(int xCoord, int zCoord) {
      this.xRegion = (xCoord >> 4) - 2;
      this.x1Region = (xCoord >> 4) + 2;
      this.zRegion = (zCoord >> 4) - 2;
      this.z1Region = (zCoord >> 4) + 2;
   }

   public ZoneController(float xController, float zController) {
      this.xController = (int)xController;
      this.zController = (int)zController;
   }
}

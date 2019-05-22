package codechicken.core.lighting;

import codechicken.core.lighting.LightMatrix;
import codechicken.core.vec.BlockCoord;
import net.minecraft.world.IBlockAccess;

public class LazyLightMatrix {

   private BlockCoord pos = new BlockCoord();
   private IBlockAccess access;
   private boolean computed = false;
   private LightMatrix lightMatrix = new LightMatrix();


   public LightMatrix lightMatrix() {
      if(this.computed) {
         return this.lightMatrix;
      } else {
         this.computed = true;
         this.lightMatrix.computeAt(this.access, this.pos.x, this.pos.y, this.pos.z);
         return this.lightMatrix;
      }
   }

   public void setPos(IBlockAccess access, int x, int y, int z) {
      this.computed = false;
      this.access = access;
      this.pos.set(x, y, z);
   }
}

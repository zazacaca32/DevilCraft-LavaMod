package codechicken.core.vec;

import codechicken.core.vec.CoordinateSystem;
import codechicken.core.vec.Vector3;

public class SwapYZ extends CoordinateSystem {

   public void convert(Vector3 vec) {
      double vz = vec.z;
      vec.z = vec.y;
      vec.y = vz;
   }
}

package codechicken.core.vec;

import codechicken.core.vec.Matrix4;
import codechicken.core.vec.RedundantTransformation;
import codechicken.core.vec.Transformation;
import codechicken.core.vec.VariableTransformation;
import codechicken.core.vec.Vector3;

public class AxisCycle {

   public static Transformation[] cycles = new Transformation[]{new RedundantTransformation(), new VariableTransformation(new Matrix4(0.0D, 0.0D, 1.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D)) {
      public void apply(Vector3 vec) {
         double d0 = vec.x;
         double d1 = vec.y;
         double d2 = vec.z;
         vec.x = d2;
         vec.y = d0;
         vec.z = d1;
      }
      public Transformation inverse() {
         return AxisCycle.cycles[2];
      }
   }, new VariableTransformation(new Matrix4(0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D)) {
      public void apply(Vector3 vec) {
         double d0 = vec.x;
         double d1 = vec.y;
         double d2 = vec.z;
         vec.x = d1;
         vec.y = d2;
         vec.z = d0;
      }
      public Transformation inverse() {
         return AxisCycle.cycles[1];
      }
   }};


}

package codechicken.core.vec;

import codechicken.core.vec.Matrix4;
import codechicken.core.vec.Transformation;
import codechicken.core.vec.Vector3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RedundantTransformation extends Transformation {

   public void apply(Vector3 vec) {}

   public void apply(Matrix4 mat) {}

   public void applyN(Vector3 normal) {}

   public Transformation at(Vector3 point) {
      return this;
   }

   @SideOnly(Side.CLIENT)
   public void glApply() {}

   public Transformation inverse() {
      return this;
   }

   public String toString() {
      return "Nothing()";
   }
}

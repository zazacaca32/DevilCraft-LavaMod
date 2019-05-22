package codechicken.core.vec;

import codechicken.core.vec.Matrix4;
import codechicken.core.vec.Transformation;
import codechicken.core.vec.Vector3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class VariableTransformation extends Transformation {

   public Matrix4 mat;


   public VariableTransformation(Matrix4 mat) {
      this.mat = mat;
   }

   public void applyN(Vector3 normal) {
      this.apply(normal);
   }

   public void apply(Matrix4 mat) {
      mat.multiply(this.mat);
   }

   @SideOnly(Side.CLIENT)
   public void glApply() {
      this.mat.glApply();
   }
}

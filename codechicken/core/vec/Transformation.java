package codechicken.core.vec;

import codechicken.core.vec.Matrix4;
import codechicken.core.vec.TransformationList;
import codechicken.core.vec.TranslatedTransformation;
import codechicken.core.vec.Vector3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class Transformation {

   public abstract void apply(Vector3 var1);

   public abstract void applyN(Vector3 var1);

   public abstract void apply(Matrix4 var1);

   public Transformation at(Vector3 point) {
      return new TranslatedTransformation(this, point);
   }

   public TransformationList with(Transformation t) {
      return new TransformationList(new Transformation[]{this, t});
   }

   @SideOnly(Side.CLIENT)
   public abstract void glApply();

   public abstract Transformation inverse();
}

package codechicken.core.vec;

import codechicken.core.vec.Matrix4;
import codechicken.core.vec.Transformation;
import codechicken.core.vec.Translation;
import codechicken.core.vec.Vector3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

public class TranslatedTransformation extends Transformation {

   public Vector3 point;
   public Transformation wrapped;


   public TranslatedTransformation(Transformation t, Vector3 vec) {
      this.point = vec;
      this.wrapped = t;
   }

   public void apply(Vector3 vec) {
      vec.subtract(this.point).apply(this.wrapped).add(this.point);
   }

   public void applyN(Vector3 normal) {
      this.wrapped.applyN(normal);
   }

   public void apply(Matrix4 mat) {
      mat.translate(this.point).apply(this.wrapped).translate(new Vector3(-this.point.x, -this.point.y, -this.point.z));
   }

   @SideOnly(Side.CLIENT)
   public void glApply() {
      GL11.glTranslated(this.point.x, this.point.y, this.point.z);
      this.wrapped.glApply();
      GL11.glTranslated(-this.point.x, -this.point.y, -this.point.z);
   }

   public Transformation inverse() {
      return new TranslatedTransformation(this.wrapped.inverse(), this.point);
   }

   public String toString() {
      return this.point.translation() + "\n" + this.wrapped.toString() + "\n" + new Translation(-this.point.x, -this.point.y, -this.point.z);
   }
}

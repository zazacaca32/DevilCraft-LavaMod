package codechicken.core.vec;

import codechicken.core.vec.Matrix4;
import codechicken.core.vec.Transformation;
import codechicken.core.vec.Vector3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import org.lwjgl.opengl.GL11;

public class Scale extends Transformation {

   private Vector3 factor;


   public Scale(Vector3 factor) {
      this.factor = factor;
   }

   public Scale(double factor) {
      this(new Vector3(factor, factor, factor));
   }

   public Scale(double x, double y, double z) {
      this(new Vector3(x, y, z));
   }

   public void apply(Vector3 vec) {
      vec.multiply(this.factor);
   }

   public void applyN(Vector3 normal) {}

   public void apply(Matrix4 mat) {
      mat.scale(this.factor);
   }

   @SideOnly(Side.CLIENT)
   public void glApply() {
      GL11.glScaled(this.factor.x, this.factor.y, this.factor.z);
   }

   public Transformation inverse() {
      return new Scale(1.0D / this.factor.x, 1.0D / this.factor.y, 1.0D / this.factor.z);
   }

   public String toString() {
      MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
      return "Scale(" + new BigDecimal(this.factor.x, cont) + ", " + new BigDecimal(this.factor.y, cont) + ", " + new BigDecimal(this.factor.z, cont) + ")";
   }
}

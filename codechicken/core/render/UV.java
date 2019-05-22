package codechicken.core.render;

import codechicken.core.render.IUVTransformation;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class UV {

   public double u;
   public double v;


   public UV() {}

   public UV(double u, double v) {
      this.u = u;
      this.v = v;
   }

   public UV(UV uv) {
      this(uv.u, uv.v);
   }

   public UV set(double u, double v) {
      this.u = u;
      this.v = v;
      return this;
   }

   public UV set(UV uv) {
      this.u = uv.u;
      this.v = uv.v;
      return this;
   }

   public UV copy() {
      return new UV(this);
   }

   public UV add(UV uv) {
      this.u += uv.u;
      this.v += uv.v;
      return this;
   }

   public UV mul(double d) {
      this.u *= d;
      this.v *= d;
      return this;
   }

   public String toString() {
      MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
      return "UV(" + new BigDecimal(this.u, cont) + ", " + new BigDecimal(this.v, cont) + ")";
   }

   public UV apply(IUVTransformation transform) {
      transform.transform(this);
      return this;
   }
}

package codechicken.core.render;

import codechicken.core.render.IUVTransformation;
import codechicken.core.render.UV;

public class UVTranslation implements IUVTransformation {

   public double du;
   public double dv;


   public UVTranslation(double u, double v) {
      this.du = u;
      this.dv = v;
   }

   public void transform(UV texcoord) {
      texcoord.u += this.du;
      texcoord.v += this.dv;
   }
}

package codechicken.core.render;

import codechicken.core.render.IUVTransformation;
import codechicken.core.render.UV;

public class UVScale implements IUVTransformation {

   double su;
   double sv;


   public UVScale(double scaleu, double scalev) {
      this.su = scaleu;
      this.sv = scalev;
   }

   public void transform(UV uv) {
      uv.u *= this.su;
      uv.v *= this.sv;
   }
}

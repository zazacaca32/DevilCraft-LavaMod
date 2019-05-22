package codechicken.core.render;

import codechicken.core.render.IUVTransformation;
import codechicken.core.render.UV;
import net.minecraft.util.Icon;

public class IconTransformation implements IUVTransformation {

   public Icon icon;


   public IconTransformation(Icon icon) {
      this.icon = icon;
   }

   public void transform(UV texcoord) {
      texcoord.u = (double)this.icon.getInterpolatedU(texcoord.u % 2.0D * 16.0D);
      texcoord.v = (double)this.icon.getInterpolatedV(texcoord.v % 2.0D * 16.0D);
   }
}

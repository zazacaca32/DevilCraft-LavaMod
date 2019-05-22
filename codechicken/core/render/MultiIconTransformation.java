package codechicken.core.render;

import codechicken.core.render.CCModel;
import codechicken.core.render.IUVTransformation;
import codechicken.core.render.UV;
import net.minecraft.util.Icon;

public class MultiIconTransformation implements IUVTransformation {

   public Icon[] icons;


   public MultiIconTransformation(Icon[] icons) {
      this.icons = icons;
   }

   public void transform(UV texcoord) {
      int i = (int)texcoord.u >> 1;
      Icon icon = this.icons[i % this.icons.length];
      texcoord.u = (double)icon.getInterpolatedU(texcoord.u % 2.0D * 16.0D);
      texcoord.v = (double)icon.getInterpolatedV(texcoord.v % 2.0D * 16.0D);
   }

   public static CCModel setIconIndex(CCModel m, int index) {
      return setIconIndex(m, 0, m.verts.length, index);
   }

   public static CCModel setIconIndex(CCModel m, int start, int length, int index) {
      for(int k = start; k < length; ++k) {
         UV uv = m.verts[k].uv;
         uv.u = uv.u % 2.0D + (double)(index * 2);
         uv.v %= 2.0D;
      }

      return m;
   }
}

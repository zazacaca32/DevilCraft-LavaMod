package codechicken.core.colour;

import codechicken.core.alg.MathHelper;
import codechicken.core.colour.ColourRGBA;
import codechicken.core.render.TextureUtils;
import java.awt.image.BufferedImage;

public class CustomGradient {

   public int[] gradient;


   public CustomGradient(String textureFile) {
      BufferedImage img = TextureUtils.loadBufferedImage(textureFile);
      int[] data = new int[img.getWidth()];
      img.getRGB(0, 0, img.getWidth(), 1, data, 0, img.getWidth());
      this.gradient = new int[img.getWidth()];

      for(int i = 0; i < data.length; ++i) {
         this.gradient[i] = data[i] << 8 | data[i] >> 24 & 255;
      }

   }

   public ColourRGBA getColour(double position) {
      return new ColourRGBA(this.getColourI(position));
   }

   public int getColourI(double position) {
      int off = (int)MathHelper.clip((double)this.gradient.length * position, 0.0D, (double)(this.gradient.length - 1));
      return this.gradient[off];
   }
}

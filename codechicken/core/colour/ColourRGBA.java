package codechicken.core.colour;

import codechicken.core.colour.Colour;

public class ColourRGBA extends Colour {

   public ColourRGBA(int colour) {
      super(colour >> 24 & 255, colour >> 16 & 255, colour >> 8 & 255, colour & 255);
   }

   public ColourRGBA(double r, double g, double b, double a) {
      super((int)(255.0D * r), (int)(255.0D * g), (int)(255.0D * b), (int)(255.0D * a));
   }

   public ColourRGBA(int r, int g, int b, int a) {
      super(r, g, b, a);
   }

   public ColourRGBA(ColourRGBA colour) {
      super(colour);
   }

   public int pack() {
      return pack(this);
   }

   public Colour copy() {
      return new ColourRGBA(this);
   }

   public static int pack(Colour colour) {
      return (colour.r & 255) << 24 | (colour.g & 255) << 16 | (colour.b & 255) << 8 | colour.a & 255;
   }
}

package codechicken.core.render;

import codechicken.core.render.TextureFX;

public class ManagedTextureFX extends TextureFX {

   public boolean changed;


   public ManagedTextureFX(int size, String name) {
      super(size, name);
      super.imageData = new byte[size * size * 4];
   }

   public void setup() {}

   public void setData(byte[] b) {
      System.arraycopy(b, 0, super.imageData, 0, super.imageData.length);
      this.changed = true;
   }

   public boolean changed() {
      boolean r = this.changed;
      this.changed = false;
      return r;
   }
}

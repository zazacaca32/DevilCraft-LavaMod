package net.minecraft.client.addon.tco.tiny.a;

import net.minecraft.client.addon.tco.tiny.a.BoxModelCactusBase;
import net.minecraft.client.addon.tco.tiny.a.Gui10Lollipop;

public class BoxModelCactusUp extends BoxModelCactusBase {

   public BoxModelCactusUp(Gui10Lollipop base, float x, float y, float width, float height, int deltaY) {
      super(base, x, y, width, height, deltaY - 150);
      super.color1 = 85245245;
      super.color2 = -9777216;
   }

   public void render() {
      if(super.speedeath > super.base.xSize + (int)super.width) {
         super.isDead = true;
      } else {
         int p = super.base.ySize - (int)super.height + 21 + super.deltaY;
         int f = -p;
         if(f < 0) {
            f = 0;
         }

         int mxxx = 120 - (super.speedeath + (int)super.x - 2 - 120 - 40);
         int myyy = (int)super.height - f;
         if(myyy >= 80) {
            myyy = (int)super.height + p;
         }

         super.bounds.setBounds(mxxx + 6, myyy - 80, 48, 80);
         super.render();
         this.drawGradientRectTexture(0, p + f, super.speedeath + (int)super.x - 2, (int)super.y + f, super.base.xSize, (int)super.height - f, super.color1, super.color2);
      }

   }
}

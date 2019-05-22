package net.minecraft.client.addon.tco.tiny.a;

import net.minecraft.client.addon.tco.tiny.a.BoxModelCactusBase;
import net.minecraft.client.addon.tco.tiny.a.Gui10Lollipop;

public class BoxModelCactusDown extends BoxModelCactusBase {

   public BoxModelCactusDown(Gui10Lollipop base, float x, float y, float width, float height, int deltaY) {
      super(base, x, y, width, height, deltaY);
      super.color2 = 85245245;
      super.color1 = -9777216;
   }

   public void render() {
      if(super.speedeath > super.base.xSize + (int)super.width) {
         super.isDead = true;
      } else {
         int myyy = super.base.ySize - (int)super.height + 21 + super.deltaY;
         int mxxx = super.speedeath + (int)super.x - 2;
         super.bounds.setBounds(119 - mxxx, myyy, 48, 80);
         super.render();
         this.drawGradientRectTexture(0, myyy, mxxx, (int)super.y, super.base.xSize, (int)super.height - super.deltaY, super.color1, super.color2);
      }

   }
}

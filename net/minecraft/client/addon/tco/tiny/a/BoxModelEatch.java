package net.minecraft.client.addon.tco.tiny.a;

import net.minecraft.client.addon.tco.tiny.a.BoxModel;
import net.minecraft.client.addon.tco.tiny.a.Gui10Lollipop;

public class BoxModelEatch extends BoxModel {

   int speedeath = 0;


   public BoxModelEatch(Gui10Lollipop base, float x, float y, float width, float height) {
      super(base, x, y, width, height);
   }

   public void tick() {
      if(!super.base.player.isDead) {
         ++this.speedeath;
      }

   }

   public void render() {
      super.bounds.setBounds(0, super.base.ySize - 5, 120, 41);
      super.render();
      this.drawGradientRectTexture(0, super.base.ySize - 20, this.speedeath, 43, 120, 41, 45245245, -6777216);
   }
}

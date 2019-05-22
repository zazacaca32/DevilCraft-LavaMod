package net.minecraft.client.addon.tco.tiny.a;

import net.minecraft.client.addon.tco.tiny.a.BoxModel;
import net.minecraft.client.addon.tco.tiny.a.Gui10Lollipop;

public class BoxModelCactusBase extends BoxModel {

   protected int speedeath;
   protected int deltaY = 0;
   protected int color1 = 0;
   protected int color2 = 0;


   public BoxModelCactusBase(Gui10Lollipop base, float x, float y, float width, float height, int deltaY) {
      super(base, x, y, width, height);
      this.deltaY = deltaY;
   }

   public void tick() {
      ++this.speedeath;
   }
}

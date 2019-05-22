package codechicken.nei.api;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.IRecipeOverlayRenderer;
import codechicken.nei.api.IStackPositioner;
import codechicken.nei.forge.GuiContainerManager;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.inventory.Slot;
import org.lwjgl.opengl.GL11;

public class DefaultOverlayRenderer implements IRecipeOverlayRenderer {

   IStackPositioner positioner;
   ArrayList ingreds;


   public DefaultOverlayRenderer(ArrayList ai, IStackPositioner positioner) {
      positioner = this.positioner = positioner;
      this.ingreds = new ArrayList();
      Iterator var4 = ai.iterator();

      while(var4.hasNext()) {
         PositionedStack stack = (PositionedStack)var4.next();
         this.ingreds.add(stack.copy());
      }

      this.ingreds = positioner.positionStacks(this.ingreds);
   }

   public void renderOverlay(GuiContainerManager gui, Slot slot) {
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 1);
      GL11.glColor4d(0.6D, 0.6D, 0.6D, 0.7D);
      gui.setColouredItemRender(true);
      Iterator var4 = this.ingreds.iterator();

      while(var4.hasNext()) {
         PositionedStack stack = (PositionedStack)var4.next();
         if(stack.relx == slot.xDisplayPosition && stack.rely == slot.yDisplayPosition) {
            gui.drawItem(stack.relx, stack.rely, stack.item);
         }
      }

      gui.setColouredItemRender(false);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(3042);
      GL11.glEnable(2896);
   }
}

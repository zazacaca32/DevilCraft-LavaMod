package codechicken.nei.forge;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;

public interface IContainerDrawHandler {

   void onPreDraw(GuiContainer var1);

   void renderObjects(GuiContainer var1, int var2, int var3);

   void postRenderObjects(GuiContainer var1, int var2, int var3);

   void renderSlotUnderlay(GuiContainer var1, Slot var2);

   void renderSlotOverlay(GuiContainer var1, Slot var2);
}

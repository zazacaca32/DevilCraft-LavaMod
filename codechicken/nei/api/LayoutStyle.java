package codechicken.nei.api;

import codechicken.nei.VisiblityData;
import codechicken.nei.forge.GuiContainerManager;
import net.minecraft.client.gui.inventory.GuiContainer;

public abstract class LayoutStyle {

   public abstract void init();

   public abstract void reset();

   public abstract void layout(GuiContainer var1, VisiblityData var2);

   public abstract String getName();

   public void drawBackground(GuiContainerManager gui) {}

public void drawBackground(Object manager) {
	// TODO Auto-generated method stub
	
}
}

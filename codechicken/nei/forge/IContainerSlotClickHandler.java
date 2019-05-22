package codechicken.nei.forge;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;

public interface IContainerSlotClickHandler {

   void beforeSlotClick(GuiContainer var1, int var2, int var3, Slot var4, int var5);

   boolean handleSlotClick(GuiContainer var1, int var2, int var3, Slot var4, int var5, boolean var6);

   void afterSlotClick(GuiContainer var1, int var2, int var3, Slot var4, int var5);
}

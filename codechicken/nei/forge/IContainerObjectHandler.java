package codechicken.nei.forge;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;

public interface IContainerObjectHandler {

   void guiTick(GuiContainer var1);

   void refresh(GuiContainer var1);

   void load(GuiContainer var1);

   ItemStack getStackUnderMouse(GuiContainer var1, int var2, int var3);

   boolean objectUnderMouse(GuiContainer var1, int var2, int var3);

   boolean shouldShowTooltip(GuiContainer var1);
}

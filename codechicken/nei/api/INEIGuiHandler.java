package codechicken.nei.api;

import codechicken.nei.VisiblityData;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;

public interface INEIGuiHandler {

   VisiblityData modifyVisiblity(GuiContainer var1, VisiblityData var2);

   int getItemSpawnSlot(GuiContainer var1, ItemStack var2);

   List getInventoryAreas(GuiContainer var1);

   boolean handleDragNDrop(GuiContainer var1, int var2, int var3, ItemStack var4, int var5);
}

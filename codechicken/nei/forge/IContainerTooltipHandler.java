package codechicken.nei.forge;

import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;

public interface IContainerTooltipHandler {

   List handleTooltipFirst(GuiContainer var1, int var2, int var3, List var4);

   List handleItemTooltip(GuiContainer var1, ItemStack var2, List var3);
}

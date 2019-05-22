package codechicken.nei;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.api.INEIGuiAdapter;
import codechicken.nei.api.TaggedInventoryArea;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;

public class NEIChestGuiHandler extends INEIGuiAdapter {

   public int getItemSpawnSlot(GuiContainer gui, ItemStack item) {
      return !(gui instanceof GuiChest)?-1:NEIServerUtils.getSlotForStack(gui.inventorySlots, 0, ((ContainerChest)gui.inventorySlots).numRows * 9, item);
   }

   public List getInventoryAreas(GuiContainer gui) {
      return !(gui instanceof GuiChest)?null:Arrays.asList(new TaggedInventoryArea[]{new TaggedInventoryArea("Chest", 0, ((ContainerChest)gui.inventorySlots).numRows * 9, gui.inventorySlots)});
   }
}

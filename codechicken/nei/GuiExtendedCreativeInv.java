package codechicken.nei;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.VisiblityData;
import codechicken.nei.api.INEIGuiHandler;
import codechicken.nei.api.TaggedInventoryArea;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class GuiExtendedCreativeInv extends GuiContainer implements INEIGuiHandler {

   public GuiExtendedCreativeInv(Container par1Container) {
      super(par1Container);
      super.ySize = 198;
   }

   protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture("/codechicken/nei/inv.png");
      int x = super.guiLeft;
      int y = super.guiTop - 4;
      this.drawTexturedModalRect(x - 23, y, 0, 0, 199, 204);
   }

   public VisiblityData modifyVisiblity(GuiContainer gui, VisiblityData currentVisibility) {
      return currentVisibility;
   }

   public int getItemSpawnSlot(GuiContainer gui, ItemStack item) {
      return NEIServerUtils.getSlotForStack(gui.inventorySlots, 0, 54, item);
   }

   public List getInventoryAreas(GuiContainer gui) {
      return Arrays.asList(new TaggedInventoryArea[]{new TaggedInventoryArea("ExtendedCreativeInv", 0, 54, super.inventorySlots)});
   }

   public boolean handleDragNDrop(GuiContainer gui, int mousex, int mousey, ItemStack draggedStack, int button) {
      return false;
   }
}

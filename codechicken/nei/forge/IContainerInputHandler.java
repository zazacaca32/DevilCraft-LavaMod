package codechicken.nei.forge;

import net.minecraft.client.gui.inventory.GuiContainer;

public interface IContainerInputHandler {

   boolean keyTyped(GuiContainer var1, char var2, int var3);

   void onKeyTyped(GuiContainer var1, char var2, int var3);

   boolean lastKeyTyped(GuiContainer var1, char var2, int var3);

   boolean mouseClicked(GuiContainer var1, int var2, int var3, int var4);

   void onMouseClicked(GuiContainer var1, int var2, int var3, int var4);

   void onMouseUp(GuiContainer var1, int var2, int var3, int var4);

   boolean mouseScrolled(GuiContainer var1, int var2, int var3, int var4);

   void onMouseScrolled(GuiContainer var1, int var2, int var3, int var4);

   void onMouseDragged(GuiContainer var1, int var2, int var3, int var4, long var5);
}

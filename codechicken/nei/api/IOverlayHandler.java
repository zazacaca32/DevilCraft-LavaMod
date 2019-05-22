package codechicken.nei.api;

import codechicken.nei.recipe.IRecipeHandler;
import net.minecraft.client.gui.inventory.GuiContainer;

public interface IOverlayHandler {

   void overlayRecipe(GuiContainer var1, IRecipeHandler var2, int var3, boolean var4);
}

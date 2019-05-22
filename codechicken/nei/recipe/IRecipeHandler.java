package codechicken.nei.recipe;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.api.IRecipeOverlayRenderer;
import codechicken.nei.forge.GuiContainerManager;
import codechicken.nei.recipe.GuiRecipe;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public interface IRecipeHandler {

   String getRecipeName();

   int numRecipes();

   void drawBackground(GuiContainerManager var1, int var2);

   void drawForeground(GuiContainerManager var1, int var2);

   ArrayList getIngredientStacks(int var1);

   ArrayList getOtherStacks(int var1);

   PositionedStack getResultStack(int var1);

   void onUpdate();

   boolean hasOverlay(GuiContainer var1, Container var2, int var3);

   IRecipeOverlayRenderer getOverlayRenderer(GuiContainer var1, int var2);

   IOverlayHandler getOverlayHandler(GuiContainer var1, int var2);

   int recipiesPerPage();

   List handleTooltip(GuiRecipe var1, List var2, int var3);

   List handleItemTooltip(GuiRecipe var1, ItemStack var2, List var3, int var4);

   boolean keyTyped(GuiRecipe var1, char var2, int var3, int var4);

   boolean mouseClicked(GuiRecipe var1, int var2, int var3);
}

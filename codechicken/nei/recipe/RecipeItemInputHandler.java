package codechicken.nei.recipe;

import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.forge.GuiContainerManager;
import codechicken.nei.forge.IContainerInputHandler;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;

public class RecipeItemInputHandler implements IContainerInputHandler {

   public boolean lastKeyTyped(GuiContainer gui, char keyChar, int keyCode) {
      ItemStack stackover = ((GuiContainerManager) gui.manager).getStackMouseOver();
      return stackover == null?false:(keyCode != NEIClientConfig.getKeyBinding("usage") && (keyCode != NEIClientConfig.getKeyBinding("recipe") || !NEIClientUtils.shiftKey())?(keyCode == NEIClientConfig.getKeyBinding("recipe")?GuiCraftingRecipe.openRecipeGui("item", new Object[]{stackover.copy()}):false):GuiUsageRecipe.openRecipeGui("item", new Object[]{stackover.copy()}));
   }

   public boolean mouseClicked(GuiContainer gui, int mousex, int mousey, int button) {
      ItemStack stackover = ((GuiContainerManager) gui.manager).getStackMouseOver();
      return stackover != null && gui instanceof GuiRecipe?(button == 0?GuiCraftingRecipe.openRecipeGui("item", new Object[]{stackover.copy()}):(button == 1?GuiUsageRecipe.openRecipeGui("item", new Object[]{stackover.copy()}):false)):false;
   }

   public void onKeyTyped(GuiContainer gui, char keyChar, int keyID) {}

   public void onMouseClicked(GuiContainer gui, int mousex, int mousey, int button) {}

   public void onMouseUp(GuiContainer gui, int mousex, int mousey, int button) {}

   public boolean keyTyped(GuiContainer gui, char keyChar, int keyID) {
      return false;
   }

   public boolean mouseScrolled(GuiContainer gui, int mousex, int mousey, int scrolled) {
      return false;
   }

   public void onMouseScrolled(GuiContainer gui, int mousex, int mousey, int scrolled) {}

   public void onMouseDragged(GuiContainer gui, int mousex, int mousey, int button, long heldTime) {}
}

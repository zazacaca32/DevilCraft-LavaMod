package codechicken.nei.recipe;

import codechicken.nei.recipe.IRecipeHandler;

public interface ICraftingHandler extends IRecipeHandler {

   ICraftingHandler getRecipeHandler(String var1, Object ... var2);
}

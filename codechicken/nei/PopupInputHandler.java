package codechicken.nei;

import codechicken.nei.InterActionMap;
import codechicken.nei.NEICPH;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.forge.IContainerInputHandler;
import net.minecraft.client.gui.inventory.GuiContainer;

public class PopupInputHandler implements IContainerInputHandler {

   public boolean keyTyped(GuiContainer gui, char keyChar, int keyCode) {
      return false;
   }

   public boolean mouseClicked(GuiContainer gui, int mousex, int mousey, int button) {
      return false;
   }

   public void onKeyTyped(GuiContainer gui, char keyChar, int keyID) {}

   public boolean lastKeyTyped(GuiContainer gui, char keyChar, int keyID) {
      if(keyID == NEIClientConfig.getKeyBinding("enchant") && NEIClientConfig.isActionPermissable(InterActionMap.ENCHANT)) {
         NEICPH.sendOpenEnchantmentWindow();
         return true;
      } else if(keyID == NEIClientConfig.getKeyBinding("potion") && NEIClientConfig.isActionPermissable(InterActionMap.POTION)) {
         NEICPH.sendOpenPotionWindow();
         return true;
      } else {
         return false;
      }
   }

   public void onMouseClicked(GuiContainer gui, int mousex, int mousey, int button) {}

   public void onMouseUp(GuiContainer gui, int mousex, int mousey, int button) {}

   public boolean mouseScrolled(GuiContainer gui, int mousex, int mousey, int scrolled) {
      return false;
   }

   public void onMouseScrolled(GuiContainer gui, int mousex, int mousey, int scrolled) {}

   public void onMouseDragged(GuiContainer gui, int mousex, int mousey, int button, long heldTime) {}
}

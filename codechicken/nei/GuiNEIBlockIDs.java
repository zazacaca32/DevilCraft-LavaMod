package codechicken.nei;

import codechicken.nei.GuiNEISettings;
import codechicken.nei.NEIClientConfig;
import net.minecraft.client.gui.inventory.GuiContainer;

public class GuiNEIBlockIDs extends GuiNEISettings {

   public GuiNEIBlockIDs(GuiContainer parentContainer) {
      super(parentContainer);
   }

   public String getBackButtonName() {
      return "Настройки";
   }

   public void onBackButtonClick() {
      super.mc.displayGuiScreen(new GuiNEISettings(super.parentScreen));
   }

   public void updateScreen() {
      super.updateScreen();
      if(!NEIClientConfig.canDump()) {
         NEIClientConfig.getSetting("ID dump.dump on load").setBooleanValue(false);
      }

      this.updateButtonNames();
   }
}

package codechicken.nei;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;

public abstract class GuiNEIOptions extends GuiScreen {

   public GuiContainer parentScreen;


   public GuiNEIOptions(GuiContainer parentContainer) {
      this.parentScreen = parentContainer;
   }

   public void initGui() {
      super.buttonList.add(new GuiButton(200, super.width / 2 - 100, super.height / 6 + 168, "Готово"));
      this.addBackButton();
      this.updateButtonNames();
   }

   public abstract void updateButtonNames();

   public void addBackButton() {
      super.buttonList.add(new GuiButton(201, super.width / 2 - 100, super.height / 6 + 145, this.getBackButtonName()));
   }

   public abstract String getBackButtonName();

   public abstract void onBackButtonClick();

   protected void actionPerformed(GuiButton guibutton) {
      if(guibutton.id == 200) {
         super.mc.displayGuiScreen(this.parentScreen);
      } else if(guibutton.id == 201) {
         this.onBackButtonClick();
      }

   }

   public void keyTyped(char keyChar, int keyID) {
      if(keyID == 1) {
         super.mc.displayGuiScreen(this.parentScreen);
     
      }

      super.keyTyped(keyChar, keyID);
      this.updateButtonNames();
   }

   public void drawScreen(int i, int j, float f) {
      this.drawDefaultBackground();
      super.drawScreen(i, j, f);
   }
}

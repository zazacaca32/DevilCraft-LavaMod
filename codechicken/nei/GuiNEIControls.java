package codechicken.nei;

import codechicken.nei.GuiNEIOptions;
import codechicken.nei.GuiNEISettings;
import codechicken.nei.NEIClientConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiSmallButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import org.lwjgl.input.Keyboard;

public class GuiNEIControls extends GuiNEIOptions {

   int focusedButton = -1;
   public List keyBinds = new ArrayList();
   public static HashMap keyBindMap = new HashMap();


   public GuiNEIControls(GuiContainer parentContainer) {
      super(parentContainer);
      this.keyBinds = this.getOptionList();
   }

   public void initGui() {
      for(int i = 0; i < this.keyBinds.size(); ++i) {
         super.buttonList.add(new GuiSmallButton(i, super.width / 2 - 155 + i % 2 * 160, super.height / 6 + 24 * (i >> 1), 70, 20, ""));
      }

      super.initGui();
   }

   public String getBackButtonName() {
      return "Настройки";
   }

   public void onBackButtonClick() {
      super.mc.displayGuiScreen(new GuiNEISettings(super.parentScreen));
   }

   private boolean doesButtonClash(String ident) {
      int buttonID = NEIClientConfig.getKeyBinding(ident);
      if(buttonID == super.mc.gameSettings.keyBindInventory.keyCode) {
         return true;
      } else {
         Iterator var4 = this.keyBinds.iterator();

         GuiNEIControls.NEIKeyBind key;
         do {
            if(!var4.hasNext()) {
               return false;
            }

            key = (GuiNEIControls.NEIKeyBind)var4.next();
         } while(ident.equals(key.ident) || buttonID != NEIClientConfig.getKeyBinding(key.ident));

         return true;
      }
   }

   public void updateButtonNames() {
      for(int i = 0; i < this.keyBinds.size(); ++i) {
         GuiButton button = (GuiButton)super.buttonList.get(i);
         String name = ((GuiNEIControls.NEIKeyBind)this.keyBinds.get(i)).ident;
         int keyCode = NEIClientConfig.getKeyBinding(name);
         if(this.focusedButton == i) {
            button.displayString = "§f> §e??? §f<";
         } else if(this.doesButtonClash(name)) {
            button.displayString = "§c" + Keyboard.getKeyName(keyCode);
         } else {
            button.displayString = Keyboard.getKeyName(keyCode);
         }
      }

   }

   protected void actionPerformed(GuiButton guibutton) {
      if(guibutton.id < this.keyBinds.size()) {
         this.focusedButton = guibutton.id;
         this.updateButtonNames();
      } else {
         super.actionPerformed(guibutton);
      }

   }

   public void keyTyped(char keyChar, int keyID) {
      if(this.focusedButton >= 0 && keyID != 1) {
         NEIClientConfig.setKeyBinding(((GuiNEIControls.NEIKeyBind)this.keyBinds.get(this.focusedButton)).ident, keyID);
         this.focusedButton = -1;
      }

      super.keyTyped(keyChar, keyID);
   }

   protected void mouseClicked(int i, int j, int k) {
      if(this.focusedButton >= 0) {
         this.focusedButton = -1;
      }

      super.mouseClicked(i, j, 0);
   }

   public void drawScreen(int i, int j, float f) {
      super.drawScreen(i, j, f);
      this.drawCenteredString(super.fontRenderer, "Управление", super.width / 2, super.height / 6 - 24, 16777215);

      for(int b = 0; b < this.keyBinds.size(); ++b) {
         int var10003 = super.width / 2 - 155 + b % 2 * 160 + 70 + 6;
         this.drawString(super.fontRenderer, ((GuiNEIControls.NEIKeyBind)this.keyBinds.get(b)).desc, var10003, super.height / 6 + 24 * (b >> 1) + 7, -1);
      }

   }

   public List getOptionList() {
      for(Class classz = this.getClass(); GuiNEIControls.class.isAssignableFrom(classz); classz = classz.getSuperclass()) {
         LinkedList list = (LinkedList)keyBindMap.get(this.getClass());
         if(list != null) {
            return list;
         }
      }

      return null;
   }

   public static class NEIKeyBind {

      String ident;
      String desc;


      public NEIKeyBind(String s, String s1) {
         this.ident = s;
         this.desc = s1;
      }
   }
}

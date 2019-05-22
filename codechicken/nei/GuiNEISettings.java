package codechicken.nei;

import codechicken.nei.GuiNEIControls;
import codechicken.nei.GuiNEIOptions;
import codechicken.nei.GuiOptionButton;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;

public class GuiNEISettings extends GuiNEIOptions {

   int mousebutton = 0;
   public static boolean global = true;
   public List options = this.getOptionList();
   public static HashMap optionMap = new HashMap();


   public GuiNEISettings(GuiContainer parentContainer) {
      super(parentContainer);
   }

   public void initGui() {
      for(int i = 0; i < this.options.size(); ++i) {
         if(!((GuiNEISettings.NEIOption)this.options.get(i)).globalOnly || global) {
            super.buttonList.add(new GuiOptionButton(i, super.width / 2 - 155 + i % 2 * 160, super.height / 6 + 24 * (i >> 1), ""));
         }
      }

      super.buttonList.add(new GuiButton(202, super.width / 2 - 50, super.height / 6 - 30, 100, 20, "NEI " + (global?" ":"World") + " Настройки"));
      super.buttonList.add(new GuiButton(201, super.width / 2 - 100, super.height / 6 + 145, "Управление"));
      super.initGui();
   }

   public void updateButtonNames() {
      for(int i = 0; i < super.buttonList.size(); ++i) {
         GuiButton button = (GuiButton)super.buttonList.get(i);
         if(button.id < this.options.size()) {
            GuiNEISettings.NEIOption option = (GuiNEISettings.NEIOption)this.options.get(button.id);
            NEIClientConfig.global = global;
            button.displayString = option.updateText();
            NEIClientConfig.global = false;
            if(!global) {
               button.enabled = NEIClientConfig.isWorldSpecific(option.getIdent());
            }
         }
      }

   }

   public String getBackButtonName() {
      return "Управление";
   }

   public void onBackButtonClick() {
      super.mc.displayGuiScreen(new GuiNEIControls(super.parentScreen));
   }

   protected void actionPerformed(GuiButton guibutton) {
      if(guibutton.id == 202) {
         global = !global;

         try {
            super.mc.displayGuiScreen((GuiScreen)this.getClass().getConstructor(new Class[]{GuiContainer.class}).newInstance(new Object[]{super.parentScreen}));
         } catch (Exception var3) {
            NEIClientUtils.reportException(var3);
         }
      } else if(guibutton.id < this.options.size()) {
         GuiNEISettings.NEIOption option = (GuiNEISettings.NEIOption)this.options.get(guibutton.id);
         if(this.mousebutton == 1 && guibutton.enabled && !global) {
            NEIClientConfig.removeWorldSetting(option.getIdent());
         } else if(this.mousebutton == 0 && !guibutton.enabled && !global) {
            NEIClientConfig.copyWorldSetting(option.getIdent());
         } else if(this.mousebutton == 0) {
            NEIClientConfig.global = global;
            option.onClick();
            NEIClientConfig.global = false;
         }

         this.updateButtonNames();
      } else {
         super.actionPerformed(guibutton);
      }

   }

   public List getOptionList() {
      for(Class classz = this.getClass(); GuiNEISettings.class.isAssignableFrom(classz); classz = classz.getSuperclass()) {
         LinkedList list = (LinkedList)optionMap.get(this.getClass());
         if(list != null) {
            return list;
         }
      }

      return new LinkedList();
   }

   protected void mouseClicked(int i, int j, int k) {
      this.mousebutton = k;
      super.mouseClicked(i, j, 0);
   }

   public abstract static class NEIOption {

      private String tagName;
      private boolean globalOnly = false;


      public NEIOption(String n) {
         this.tagName = n;
      }

      public GuiNEISettings.NEIOption setGlobalOnly() {
         this.globalOnly = true;
         return this;
      }

      public void onClick() {
         NEIClientConfig.toggleBooleanSetting(this.getIdent());
      }

      public String getIdent() {
         return this.tagName;
      }

      public abstract String updateText();

      public final boolean enabled() {
         return NEIClientConfig.getBooleanSetting(this.getIdent());
      }

      public int intValue() {
         return NEIClientConfig.getIntSetting(this.getIdent());
      }
   }
}

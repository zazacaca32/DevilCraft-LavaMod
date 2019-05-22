package codechicken.nei;

import codechicken.core.CommonUtils;
import codechicken.nei.ItemList;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.TextField;
import codechicken.nei.forge.GuiContainerManager;

public class SearchField extends TextField {

   long lastclicktime;


   public SearchField(String ident) {
      super(ident);
   }

   public void drawBox(GuiContainerManager gui) {
      if(NEIClientConfig.getBooleanSetting("options.searchinventories")) {
         gui.drawGradientRect(super.x, super.y, super.width, super.height, -256, -4149248);
      } else {
         gui.drawRect(super.x, super.y, super.width, super.height, -6250336);
      }

      gui.drawRect(super.x + 1, super.y + 1, super.width - 2, super.height - 2, -16777216);
   }

   public boolean handleClick(int mousex, int mousey, int button) {
      if(button == 0) {
         if(this.focused() && System.currentTimeMillis() - this.lastclicktime < 500L) {
            NEIClientConfig.toggleBooleanSetting("options.searchinventories");
         }

         this.lastclicktime = System.currentTimeMillis();
      }

      return super.handleClick(mousex, mousey, button);
   }

   public void onTextChange(String oldText) {
      NEIClientConfig.setSearchExpression(this.text());
      ItemList.updateSearch();
   }

   public String filterText(String s) {
      return CommonUtils.filterText(s);
   }
}

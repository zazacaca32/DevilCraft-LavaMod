package codechicken.nei;

import codechicken.nei.Button;
import codechicken.nei.InterActionMap;
import codechicken.nei.LayoutManager;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIController;
import codechicken.nei.VisiblityData;
import codechicken.nei.api.LayoutStyle;
import codechicken.nei.forge.GuiContainerManager;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.nbt.NBTTagCompound;

public abstract class LayoutStyleDefault extends LayoutStyle {

   public void layout(GuiContainer gui, VisiblityData visiblity) {
      int windowWidth = gui.width;
      int windowHeight = gui.height;
      int containerWidth = gui.xSize;
      int containerLeft = gui.guiLeft;
      this.reset();
      LayoutManager.prev.y = 2;
      LayoutManager.prev.height = 16;
      LayoutManager.prev.width = containerLeft / 3;
      LayoutManager.prev.x = (containerWidth + windowWidth) / 2 + 2;
      LayoutManager.next.x = windowWidth - LayoutManager.prev.width - 2;
      LayoutManager.next.y = LayoutManager.prev.y;
      LayoutManager.next.width = LayoutManager.prev.width;
      LayoutManager.next.height = LayoutManager.prev.height;
      LayoutManager.pageLabel.x = containerLeft * 3 / 2 + containerWidth + 1;
      LayoutManager.pageLabel.y = LayoutManager.prev.y + 5;
      if(LayoutManager.itemPanel.getNumPages() == 0) {
         LayoutManager.pageLabel.text = "(0/0)";
      } else {
         LayoutManager.pageLabel.text = "(" + (LayoutManager.itemPanel.getPage() + 1) + "/" + LayoutManager.itemPanel.getNumPages() + ")";
      }

      LayoutManager.itemPanel.y = LayoutManager.prev.height + LayoutManager.prev.y;
      LayoutManager.itemPanel.x = (containerWidth + windowWidth) / 2 + 3;
      LayoutManager.itemPanel.width = windowWidth - 3 - LayoutManager.itemPanel.x;
      LayoutManager.itemPanel.height = windowHeight - 15 - LayoutManager.itemPanel.y;
      if(!NEIClientConfig.isActionPermissable(InterActionMap.ITEM)) {
         LayoutManager.itemPanel.height += 15;
      }

      LayoutManager.itemPanel.resize();
      LayoutManager.more.width = LayoutManager.more.height = LayoutManager.less.width = LayoutManager.less.height = 16;
      LayoutManager.less.x = LayoutManager.prev.x;
      LayoutManager.more.x = windowWidth - LayoutManager.less.width - 2;
      LayoutManager.more.y = LayoutManager.less.y = windowHeight - LayoutManager.more.height - 2;
      LayoutManager.quantity.x = LayoutManager.less.x + LayoutManager.less.width + 2;
      LayoutManager.quantity.y = LayoutManager.less.y;
      LayoutManager.quantity.width = LayoutManager.more.x - LayoutManager.quantity.x - 2;
      LayoutManager.quantity.height = LayoutManager.less.height;
      LayoutManager.options.x = NEIClientConfig.isEnabled()?0:6;
      LayoutManager.options.y = NEIClientConfig.isEnabled()?windowHeight - 22:windowHeight - 28;
      LayoutManager.options.width = 80;
      LayoutManager.options.height = 22;
      LayoutManager.delete.state = 4;
      if(NEIController.deleteMode) {
         LayoutManager.delete.state |= 1;
      } else if(!visiblity.enableDeleteMode) {
         LayoutManager.delete.state |= 2;
      }

      LayoutManager.rain.state = 4;
      if(NEIClientUtils.isRaining()) {
         LayoutManager.rain.state |= 1;
      } else if(NEIClientConfig.isPropertyDisabled("rain")) {
         LayoutManager.rain.state |= 2;
      }

      LayoutManager.creative.state = 4;
      if(NEIClientUtils.getCreativeMode() != 0) {
         LayoutManager.creative.state |= 1;
      }

      if(NEIClientUtils.getCreativeMode() == 2) {
         LayoutManager.creative.state |= 8;
      }

      LayoutManager.magnet.state = 4 | (NEIClientConfig.getMagnetMode()?1:0);
      if(NEIClientConfig.isActionPermissable(InterActionMap.DELETE)) {
         this.layoutButton(LayoutManager.delete, (GuiContainerManager) gui.manager);
      }

      if(NEIClientConfig.isActionPermissable(InterActionMap.RAIN)) {
         this.layoutButton(LayoutManager.rain, (GuiContainerManager) gui.manager);
      }

      if(NEIClientConfig.isActionPermissable(InterActionMap.CREATIVE)) {
         this.layoutButton(LayoutManager.creative, (GuiContainerManager) gui.manager);
      }

      if(NEIClientConfig.isActionPermissable(InterActionMap.MAGNET)) {
         this.layoutButton(LayoutManager.magnet, (GuiContainerManager) gui.manager);
      }

      int maxWidth;
      if(NEIClientConfig.isActionPermissable(InterActionMap.TIME)) {
         for(maxWidth = 0; maxWidth < 4; ++maxWidth) {
            LayoutManager.timeButtons[maxWidth].state = NEIClientConfig.isPropertyDisabled(LayoutManager.timeZones[maxWidth])?2:0;
            this.layoutButton(LayoutManager.timeButtons[maxWidth], (GuiContainerManager) gui.manager);
         }
      }

      if(NEIClientConfig.isActionPermissable(InterActionMap.HEAL)) {
         this.layoutButton(LayoutManager.heal, (GuiContainerManager) gui.manager);
      }

      LayoutManager.searchField.y = windowHeight - LayoutManager.searchField.height - 2;
      LayoutManager.dropDown.height = 20;
      LayoutManager.dropDown.x = NEIClientConfig.getLayoutStyle() == 1?93:90;
      LayoutManager.dropDown.width = LayoutManager.prev.x - LayoutManager.dropDown.x - 3;
      LayoutManager.searchField.height = 20;
      LayoutManager.searchField.width = 150;
      LayoutManager.searchField.x = (windowWidth - LayoutManager.searchField.width) / 2;
      if(!visiblity.showItemSection) {
         LayoutManager.dropDown.setDropDown(0);
         LayoutManager.searchField.setFocus(false);
      }

      maxWidth = 0;

      int i;
      for(i = 0; i < 7; ++i) {
         LayoutManager.deleteButtons[i].width = 16;
         LayoutManager.deleteButtons[i].height = 16;
         NBTTagCompound statelist = NEIClientConfig.saveCompound.getCompoundTag("statename");
         NEIClientConfig.saveCompound.setTag("statename", statelist);
         String name = statelist.getString("" + i);
         if(statelist.getTag("" + i) == null) {
            name = "" + (i + 1);
            statelist.setString("" + i, name);
         }

         LayoutManager.stateButtons[i].label = (NEIClientConfig.isStateSaved(i)?"Load ":"Save ") + name;
         int width = ((GuiContainerManager) gui.manager).getStringWidth(LayoutManager.stateButtons[i].label) + 26;
         if(width + 22 > containerLeft) {
            width = containerLeft - 22;
         }

         if(width > maxWidth) {
            maxWidth = width;
         }
      }

      for(i = 0; i < 7; ++i) {
         LayoutManager.stateButtons[i].x = 0;
         LayoutManager.stateButtons[i].y = 58 + i * 22;
         LayoutManager.stateButtons[i].height = 20;
         LayoutManager.stateButtons[i].x = 0;
         LayoutManager.stateButtons[i].width = maxWidth;
         LayoutManager.deleteButtons[i].x = LayoutManager.stateButtons[i].width + 3;
         LayoutManager.deleteButtons[i].y = LayoutManager.stateButtons[i].y + 2;
      }

   }

   public abstract void layoutButton(Button var1, GuiContainerManager var2);
}

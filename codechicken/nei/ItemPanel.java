package codechicken.nei;

import codechicken.nei.InterActionMap;
import codechicken.nei.ItemPanelStack;
import codechicken.nei.NEICPH;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIController;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.Widget;
import codechicken.nei.api.GuiInfo;
import codechicken.nei.api.INEIGuiHandler;
import codechicken.nei.forge.GuiContainerManager;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ItemPanel extends Widget {

   public static ArrayList visibleitems = new ArrayList();
   public ItemStack draggedStack;
   public int mouseDownSlot = -1;
   private int marginLeft;
   private int marginTop;
   private int columns;
   private int rows;
   private int itemsPerPage;
   private int page;
   private int numPages;


   public void resize() {
      this.marginLeft = super.x + super.width % 18 / 2;
      this.marginTop = super.y + super.height % 18 / 2;
      this.columns = super.width / 18;
      this.rows = super.height / 18;
      this.itemsPerPage = this.rows * this.columns;
      this.numPages = (int)Math.ceil((double)((float)visibleitems.size() / (float)this.itemsPerPage));
      this.setPage(this.page);
   }

   public void draw(GuiContainerManager gui, int mousex, int mousey) {
      if(visibleitems.size() != 0) {
         int itemIndex = this.page * this.itemsPerPage;
         int lastIndex = this.itemsPerPage * (this.page + 1);
         int colIndex = 0;
         int rowIndex = 0;
         ItemPanel.ItemPanelSlot hoverSlot = this.getSlotMouseOver(mousex, mousey);
         if(hoverSlot != null) {
            int item = hoverSlot.slotIndex - itemIndex;
            gui.drawRect(this.marginLeft + item % this.columns * 18 - 1, this.marginTop + item / this.columns * 18 - 1, 18, 18, -296397483);
         }

         while(itemIndex < lastIndex && itemIndex < visibleitems.size()) {
            ItemPanel.ItemPanelObject var12 = (ItemPanel.ItemPanelObject)visibleitems.get(itemIndex);
            int posX = this.marginLeft + colIndex * 18;
            int posY = this.marginTop + rowIndex * 18;
            var12.draw(gui, posX, posY);
            ++itemIndex;
            ++colIndex;
            if(colIndex == this.columns) {
               colIndex = 0;
               ++rowIndex;
            }
         }

      }
   }

   public void postDraw(GuiContainerManager gui, int mousex, int mousey) {
      if(this.draggedStack != null) {
         GuiContainerManager.drawItems.zLevel += 100.0F;
         gui.drawItem(mousex - 8, mousey - 8, this.draggedStack);
         GuiContainerManager.drawItems.zLevel -= 100.0F;
      }

   }

   public void mouseDragged(int mousex, int mousey, int button, long heldTime) {
      if(this.mouseDownSlot >= 0 && this.draggedStack == null && NEIClientUtils.getHeldItem() == null) {
         ItemPanel.ItemPanelSlot mouseOverSlot = this.getSlotMouseOver(mousex, mousey);
         ItemStack stack = (new ItemPanel.ItemPanelSlot(this.mouseDownSlot)).getItemStack();
         if(stack != null && (mouseOverSlot == null || mouseOverSlot.slotIndex != this.mouseDownSlot || heldTime > 500L)) {
            int amount = NEIClientConfig.getItemQuantity();
            if(amount == 0) {
               amount = stack.getMaxStackSize();
            }

            this.draggedStack = NEIServerUtils.copyStack(stack, amount);
         }
      }

   }

   public boolean handleClick(int mousex, int mousey, int button) {
      if(this.handleDraggedClick(mousex, mousey, button)) {
         return true;
      } else if(NEIClientUtils.getHeldItem() == null) {
         ItemPanel.ItemPanelSlot hoverSlot = this.getSlotMouseOver(mousex, mousey);
         if(hoverSlot != null) {
            if(button == 2) {
               ItemStack stack = hoverSlot.getItemStack();
               if(stack != null) {
                  int amount = NEIClientConfig.getItemQuantity();
                  if(amount == 0) {
                     amount = stack.getMaxStackSize();
                  }

                  this.draggedStack = NEIServerUtils.copyStack(stack, amount);
               }
            } else {
               this.mouseDownSlot = hoverSlot.slotIndex;
            }

            return true;
         } else {
            return false;
         }
      } else {
         if(NEIClientConfig.isActionPermissable(InterActionMap.DELETE) && NEIClientConfig.isActionPermissable(InterActionMap.ITEM)) {
            if(button == 1) {
               NEIClientUtils.decreaseSlotStack(-999);
            } else {
               NEIClientUtils.deleteHeldItem();
            }
         } else {
            NEIClientUtils.dropHeldItem();
         }

         return true;
      }
   }

   private boolean handleDraggedClick(int mousex, int mousey, int button) {
      if(this.draggedStack == null) {
         return false;
      } else {
         GuiContainer gui = NEIClientUtils.getGuiContainer();
         boolean handled = false;
         Iterator contents = GuiInfo.guiHandlers.iterator();

         while(contents.hasNext()) {
            INEIGuiHandler overSlot = (INEIGuiHandler)contents.next();
            if(overSlot.handleDragNDrop(gui, mousex, mousey, this.draggedStack, button)) {
               handled = true;
               if(this.draggedStack.stackSize == 0) {
                  this.draggedStack = null;
                  return true;
               }
            }
         }

         if(handled) {
            return true;
         } else {
            Slot overSlot1 = gui.getSlotAtPosition(mousex, mousey);
            if(overSlot1 != null && overSlot1.isItemValid(this.draggedStack)) {
               if(NEIClientConfig.isActionPermissable(InterActionMap.ITEM)) {
                  int contents1 = overSlot1.getHasStack()?overSlot1.getStack().stackSize:0;
                  int add = button == 0?this.draggedStack.stackSize:1;
                  if(overSlot1.getHasStack() && !NEIServerUtils.areStacksSameType(this.draggedStack, overSlot1.getStack())) {
                     contents1 = 0;
                  }

                  int total = Math.min(contents1 + add, Math.min(overSlot1.getSlotStackLimit(), this.draggedStack.getMaxStackSize()));
                  if(total > contents1) {
                     NEIClientUtils.setSlotContents(overSlot1.slotNumber, NEIServerUtils.copyStack(this.draggedStack, total), true);
                     NEICPH.sendSpawnItem(NEIServerUtils.copyStack(this.draggedStack, total), false, false);
                     this.draggedStack.stackSize -= total - contents1;
                  }

                  if(this.draggedStack.stackSize == 0) {
                     this.draggedStack = null;
                  }
               } else {
                  this.draggedStack = null;
               }
            } else if(mousex < gui.guiLeft || mousey < gui.guiTop || mousex >= gui.guiLeft + gui.xSize || mousey >= gui.guiTop + gui.ySize) {
               this.draggedStack = null;
            }

            return true;
         }
      }
   }

   public boolean handleClickExt(int mousex, int mousey, int button) {
      return this.handleDraggedClick(mousex, mousey, button);
   }

   public void mouseUp(int mousex, int mousey, int button) {
      ItemPanel.ItemPanelSlot hoverSlot = this.getSlotMouseOver(mousex, mousey);
      if(hoverSlot != null && hoverSlot.slotIndex == this.mouseDownSlot && hoverSlot.getItemStack() != null && this.draggedStack == null) {
         ItemStack item = hoverSlot.getItemStack();
         if(NEIController.manager.window instanceof GuiRecipe || !NEIClientConfig.isActionPermissable(InterActionMap.ITEM)) {
            if(button == 0) {
               GuiCraftingRecipe.openRecipeGui("item", new Object[]{item});
            } else if(button == 1) {
               GuiUsageRecipe.openRecipeGui("item", new Object[]{item});
            }

            this.draggedStack = null;
            this.mouseDownSlot = -1;
            return;
         }

         NEIClientUtils.cheatItem(item, button, -1);
      }

      this.mouseDownSlot = -1;
   }

   public boolean onMouseWheel(int i, int mousex, int mousey) {
      if(!this.contains(mousex, mousey)) {
         return false;
      } else {
         this.scroll(-i);
         return true;
      }
   }

   public boolean handleKeyPress(int keyID, char keyChar) {
      if(keyID == NEIClientConfig.getKeyBinding("next")) {
         this.scroll(1);
         return true;
      } else if(keyID == NEIClientConfig.getKeyBinding("prev")) {
         this.scroll(-1);
         return true;
      } else {
         return false;
      }
   }

   public ItemStack getStackMouseOver(int mousex, int mousey) {
      ItemPanel.ItemPanelSlot slot = this.getSlotMouseOver(mousex, mousey);
      return slot == null?null:slot.getItemStack();
   }

   public ItemPanel.ItemPanelSlot getSlotMouseOver(int mousex, int mousey) {
      int relX = mousex - this.marginLeft + 1;
      int relY = mousey - this.marginTop + 1;
      if(relX >= 0 && relY >= 0) {
         int col = relX / 18;
         int row = relY / 18;
         int index = this.itemsPerPage * this.page + row * this.columns + col;
         return index >= 0 && index < visibleitems.size() && index < this.itemsPerPage * (this.page + 1)?new ItemPanel.ItemPanelSlot(index):null;
      } else {
         return null;
      }
   }

   public List handleTooltip(int mx, int my, List tooltip) {
      return this.getSlotMouseOver(mx, my) == null?tooltip:this.getSlotMouseOver(mx, my).contents.handleTooltip(tooltip);
   }

   public void scroll(int i) {
      this.setPage(this.page + i);
   }

   public void setPage(int i) {
      if(this.numPages == 0) {
         this.page = 0;
      } else {
         this.page = (i + this.numPages) % this.numPages;
      }

   }

   public int getPage() {
      return this.page;
   }

   public int getNumPages() {
      return this.numPages;
   }

   public class ItemPanelSlot {

      public ItemPanel.ItemPanelObject contents;
      public int slotIndex;


      public ItemPanelSlot(int index) {
         this.contents = (ItemPanel.ItemPanelObject)ItemPanel.visibleitems.get(index);
         this.slotIndex = index;
      }

      public ItemStack getItemStack() {
         return this.contents instanceof ItemPanelStack?((ItemPanelStack)this.contents).item:null;
      }
   }

   public interface ItemPanelObject {

      void draw(GuiContainerManager var1, int var2, int var3);

      List handleTooltip(List var1);
   }
}

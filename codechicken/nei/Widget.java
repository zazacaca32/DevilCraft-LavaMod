package codechicken.nei;

import codechicken.nei.forge.GuiContainerManager;
import java.util.List;
import net.minecraft.item.ItemStack;

public abstract class Widget {

   public int x;
   public int y;
   public int z;
   public int width;
   public int height;


   public abstract void draw(GuiContainerManager var1, int var2, int var3);

   public void postDraw(GuiContainerManager gui, int mousex, int mousey) {}

   public boolean handleClick(int mousex, int mousey, int button) {
      return true;
   }

   public void onGuiClick(int mousex, int mousey) {}

   public void mouseUp(int mousex, int mousey, int button) {}

   public boolean handleKeyPress(int keyID, char keyChar) {
      return false;
   }

   public boolean handleClickExt(int mousex, int mousey, int button) {
      return false;
   }

   public boolean onMouseWheel(int i, int mousex, int mousey) {
      return false;
   }

   public void update(GuiContainerManager gui) {}

   public boolean contains(int posx, int posy) {
      return posx >= this.x && posx < this.x + this.width && posy >= this.y && posy < this.y + this.height;
   }

   public void resize() {}

   public ItemStack getStackMouseOver(int mousex, int mousey) {
      return null;
   }

   public void mouseDragged(int mx, int my, int button, long heldTime) {}

   public List handleTooltip(int mx, int my, List tooltip) {
      return tooltip;
   }

   public void loseFocus() {}

   public void gainFocus() {}

public void draw(Object manager, int mousex, int mousey) {
	// TODO Auto-generated method stub
	
}

public void postDraw(Object manager, int mousex, int mousey) {
	// TODO Auto-generated method stub
	
}
}

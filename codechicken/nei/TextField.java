package codechicken.nei;

import codechicken.nei.LayoutManager;
import codechicken.nei.Widget;
import codechicken.nei.forge.GuiContainerManager;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;

public abstract class TextField extends Widget {

   private String text = "";
   public boolean centered;
   public long backdowntime;
   public int backs;
   public String identifier;
   public int cursorCounter;


   public TextField(String ident) {
      this.identifier = ident;
   }

   public int getTextColour() {
      return this.focused()?-2039584:-7303024;
   }

   public void drawBox(GuiContainerManager gui) {
      gui.drawRect(super.x, super.y, super.width, super.height, -6250336);
      gui.drawRect(super.x + 1, super.y + 1, super.width - 2, super.height - 2, -16777216);
   }

   public void draw(GuiContainerManager gui, int mousex, int mousey) {
      this.drawBox(gui);
      String drawtext = this.text;
      int textWidth;
      if(this.text.length() > this.getMaxTextLength()) {
         textWidth = drawtext.length() - this.getMaxTextLength();
         if(textWidth < 0 || textWidth > drawtext.length()) {
            textWidth = 0;
         }

         drawtext = drawtext.substring(textWidth);
      }

      if(this.focused() && this.cursorCounter / 6 % 2 == 0) {
         drawtext = drawtext + '_';
      }

      textWidth = gui.getStringWidth(this.text);
      int textx = this.centered?super.x + (super.width - textWidth) / 2:super.x + 4;
      int texty = super.y + (super.height + 1) / 2 - 3;
      gui.drawText(textx, texty, drawtext, this.getTextColour());
   }

   public void onGuiClick(int mousex, int mousey) {
      if(!this.contains(mousex, mousey)) {
         this.setFocus(false);
      }

   }

   public boolean handleClick(int mousex, int mousey, int button) {
      if(button == 1) {
         this.setText("");
      }

      this.setFocus(true);
      return true;
   }

   public boolean handleKeyPress(int keyID, char keyChar) {
      if(LayoutManager.getInputFocused() != this) {
         return false;
      } else {
         if(keyID == 14) {
            if(this.text.length() > 0) {
               this.setText(this.text.substring(0, this.text.length() - 1));
               this.backdowntime = System.currentTimeMillis();
            }
         } else if(keyID != 28 && keyID != 1) {
            if(keyChar == 22) {
               String pastestring = GuiScreen.getClipboardString();
               if(pastestring == null) {
                  pastestring = "";
               }

               if(this.isValid(this.text + pastestring)) {
                  this.setText(this.text + pastestring);
               }
            } else if(this.isValid(this.text + keyChar)) {
               this.setText(this.text + keyChar);
            }
         } else {
            this.setFocus(false);
            this.onExit();
         }

         return true;
      }
   }

   public void onExit() {}

   public abstract void onTextChange(String var1);

   public boolean isValid(String string) {
      return ChatAllowedCharacters.allowedCharacters.indexOf(string.charAt(string.length() - 1)) >= 0;
   }

   public void update(GuiContainerManager gui) {
      ++this.cursorCounter;
      if(this.backdowntime > 0L) {
         if(Keyboard.isKeyDown(14) && this.text.length() > 0) {
            if((float)(System.currentTimeMillis() - this.backdowntime) > 200.0F / (1.0F + (float)this.backs * 0.3F)) {
               this.setText(this.text.substring(0, this.text.length() - 1));
               this.backdowntime = System.currentTimeMillis();
               ++this.backs;
            }
         } else {
            this.backdowntime = 0L;
            this.backs = 0;
         }
      }

   }

   public void setText(String s) {
      String oldText = this.text;
      this.text = this.filterText(s);
      this.onTextChange(oldText);
   }

   public String filterText(String s) {
      return s;
   }

   private int getMaxTextLength() {
      return super.width / 6 - 2;
   }

   public void setFocus(boolean focus) {
      if(focus) {
         LayoutManager.setInputFocused(this);
      } else if(this.focused()) {
         LayoutManager.setInputFocused((Widget)null);
      }

   }

   public boolean focused() {
      return LayoutManager.getInputFocused() == this;
   }

   public String text() {
      return this.text;
   }
}

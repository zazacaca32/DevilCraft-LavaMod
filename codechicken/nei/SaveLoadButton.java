package codechicken.nei;

import codechicken.nei.Button;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.forge.GuiContainerManager;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;

public abstract class SaveLoadButton extends Button {

   public long backdowntime;
   public int backs;
   public int cursorCounter;
   public boolean focused;


   public SaveLoadButton(String s) {
      super(s);
   }

   public boolean handleClick(int mousex, int mousey, int button) {
      if(button == 1) {
         super.label = super.label.substring(0, 5);
         this.onTextChange();
         this.focused = true;
         NEIClientUtils.mc().sndManager.playSoundFX("random.click", 1.0F, 1.0F);
         return true;
      } else {
         return super.handleClick(mousex, mousey, button);
      }
   }

   public abstract void onTextChange();

   public void onGuiClick(int i, int j) {
      if(!this.contains(i, j)) {
         this.focused = false;
      }

   }

   public boolean handleKeyPress(int keyID, char keyChar) {
      if(!this.focused) {
         return false;
      } else {
         if(keyID == 14) {
            if(super.label.length() > 5) {
               super.label = super.label.substring(0, super.label.length() - 1);
               this.onTextChange();
               this.backdowntime = System.currentTimeMillis();
            }
         } else if(keyID == 28) {
            this.focused = false;
         } else if(keyChar == 22) {
            String pastestring = GuiScreen.getClipboardString();
            if(pastestring == null) {
               pastestring = "";
            }

            super.label = super.label + pastestring;
            this.onTextChange();
         } else if(ChatAllowedCharacters.allowedCharacters.indexOf(keyChar) >= 0) {
            super.label = super.label + keyChar;
            this.onTextChange();
         }

         return true;
      }
   }

   public void update(GuiContainerManager gui) {
      ++this.cursorCounter;
      if(this.backdowntime > 0L) {
         if(Keyboard.isKeyDown(14) && super.label.length() > 5) {
            if((float)(System.currentTimeMillis() - this.backdowntime) > 200.0F / (1.0F + (float)this.backs * 0.3F)) {
               super.label = super.label.substring(0, super.label.length() - 1);
               this.onTextChange();
               this.backdowntime = System.currentTimeMillis();
               ++this.backs;
            }
         } else {
            this.backdowntime = 0L;
            this.backs = 0;
         }
      }

   }

   public void draw(GuiContainerManager gui, int mousex, int mousey) {
      super.draw(gui, mousex, mousey);
      if(this.focused && this.cursorCounter / 6 % 2 == 0) {
         gui.drawText(super.x + (super.width + gui.getStringWidth(super.label)) / 2, super.y + (super.height - 8) / 2, "_", -1);
      }

   }
}

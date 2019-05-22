package codechicken.core.gui;

import codechicken.core.gui.GuiWidget;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatAllowedCharacters;

public class GuiCCTextField extends GuiWidget {

   private String text;
   private boolean isFocused = false;
   private boolean isEnabled = true;
   public int maxStringLength;
   public int cursorCounter;
   public String actionCommand;
   private String allowedcharacters;


   public GuiCCTextField(int x, int y, int width, int height, String text) {
      super(x, y, width, height);
      this.text = text;
      this.allowedcharacters = ChatAllowedCharacters.allowedCharacters;
   }

   public GuiCCTextField setActionCommand(String s) {
      this.actionCommand = s;
      return this;
   }

   public void setText(String s) {
      if(!s.equals(this.text)) {
         String oldText = this.text;
         this.text = s;
         this.onTextChanged(oldText);
      }
   }

   public void onTextChanged(String oldText) {}

   public final String getText() {
      return this.text;
   }

   public final boolean isEnabled() {
      return this.isEnabled;
   }

   public void setEnabled(boolean b) {
      this.isEnabled = b;
      if(!this.isEnabled && this.isFocused) {
         this.setFocused(false);
      }

   }

   public final boolean isFocused() {
      return this.isFocused;
   }

   public void update() {
      ++this.cursorCounter;
   }

   public void keyTyped(char c, int keycode) {
      if(this.isEnabled && this.isFocused) {
         if(c == 22) {
            String s = GuiScreen.getClipboardString();
            if(s == null || s.equals("")) {
               return;
            }

            for(int i = 0; i < s.length(); ++i) {
               if(this.text.length() == this.maxStringLength) {
                  return;
               }

               char tc = s.charAt(i);
               if(this.canAddChar(tc)) {
                  this.setText(this.text + tc);
               }
            }
         }

         if(keycode == 28) {
            this.setFocused(false);
            this.sendAction(this.actionCommand, new Object[]{this.getText()});
         }

         if(keycode == 14 && this.text.length() > 0) {
            this.setText(this.text.substring(0, this.text.length() - 1));
         }

         if((this.text.length() < this.maxStringLength || this.maxStringLength == 0) && this.canAddChar(c)) {
            this.setText(this.text + c);
         }

      }
   }

   public boolean canAddChar(char c) {
      return this.allowedcharacters.indexOf(c) >= 0;
   }

   public void mouseClicked(int x, int y, int button) {
      if(this.isEnabled && this.pointInside(x, y)) {
         this.setFocused(true);
         if(button == 1) {
            this.setText("");
         }
      } else {
         this.setFocused(false);
      }

   }

   public void setFocused(boolean focus) {
      if(focus != this.isFocused) {
         this.isFocused = focus;
         this.onFocusChanged();
      }
   }

   public void onFocusChanged() {
      if(this.isFocused) {
         this.cursorCounter = 0;
      }

   }

   public void draw(int i, int j, float f) {
      this.drawBackground();
      this.drawText();
   }

   public void drawBackground() {
      drawRect(super.x - 1, super.y - 1, super.x + super.width + 1, super.y + super.height + 1, -6250336);
      drawRect(super.x, super.y, super.x + super.width, super.y + super.height, -16777216);
   }

   public String getDrawText() {
      String s = this.getText();
      if(this.isEnabled && this.isFocused && this.cursorCounter / 6 % 2 == 0) {
         s = s + "_";
      }

      return s;
   }

   public void drawText() {
      this.drawString(super.fontRenderer, this.getDrawText(), super.x + 4, super.y + super.height / 2 - 4, this.getTextColour());
   }

   public int getTextColour() {
      return this.isEnabled?14737632:7368816;
   }

   public GuiCCTextField setMaxStringLength(int i) {
      this.maxStringLength = i;
      return this;
   }

   public GuiCCTextField setAllowedCharacters(String s) {
      if(s == null) {
         s = ChatAllowedCharacters.allowedCharacters;
      } else {
         this.allowedcharacters = s;
      }

      return this;
   }
}

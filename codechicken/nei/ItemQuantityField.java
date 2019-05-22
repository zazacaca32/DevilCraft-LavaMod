package codechicken.nei;

import codechicken.nei.NEIClientConfig;
import codechicken.nei.TextField;

public class ItemQuantityField extends TextField {

   public ItemQuantityField(String ident) {
      super(ident);
      super.centered = true;
   }

   public boolean isValid(String string) {
      if(string.equals("")) {
         return true;
      } else {
         try {
            return Integer.parseInt(string) >= 0;
         } catch (NumberFormatException var3) {
            return false;
         }
      }
   }

   public int intValue() {
      try {
         return Integer.parseInt(this.text());
      } catch (NumberFormatException var2) {
         return 0;
      }
   }

   public void loseFocus() {
      this.setText(Integer.toString(NEIClientConfig.getItemQuantity()));
   }

   public void onTextChange(String oldText) {
      NEIClientConfig.setItemQuantity(this.intValue());
   }
}

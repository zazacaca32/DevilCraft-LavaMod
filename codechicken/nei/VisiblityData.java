package codechicken.nei;


public class VisiblityData {

   public boolean showUtilityButtons = true;
   public boolean showStateButtons = true;
   public boolean showItemPanel = true;
   public boolean showItemSection = true;
   public boolean showSearchSection = true;
   public boolean showWidgets = true;
   public boolean showNEI = true;
   public boolean enableDeleteMode = true;


   public void translateDependancies() {
      if(!this.showNEI) {
         this.showWidgets = false;
      }

      if(!this.showWidgets) {
         this.showItemSection = this.showUtilityButtons = this.showStateButtons = false;
      }

      if(!this.showItemSection) {
         this.showSearchSection = this.showItemPanel = false;
      }

   }
}

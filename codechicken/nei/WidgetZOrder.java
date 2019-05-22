package codechicken.nei;

import codechicken.nei.Widget;
import java.util.Comparator;

public class WidgetZOrder implements Comparator {

   boolean topfirst;


   public WidgetZOrder(boolean topfirst) {
      this.topfirst = topfirst;
   }

   public int compare(Widget w1, Widget w2) {
      byte var10000;
      if(w1.z != w2.z) {
         label25: {
            if(this.topfirst) {
               if(w1.z > w2.z) {
                  break label25;
               }
            } else if(w1.z < w2.z) {
               break label25;
            }

            var10000 = -1;
            return var10000;
         }

         var10000 = 1;
      } else {
         var10000 = 1;
      }

      return var10000;
   }

@Override
public int compare(Object o1, Object o2) {
	// TODO Auto-generated method stub
	return 0;
}
}

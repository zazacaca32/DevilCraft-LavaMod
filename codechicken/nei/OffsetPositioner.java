package codechicken.nei;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.IStackPositioner;
import java.util.ArrayList;
import java.util.Iterator;

public class OffsetPositioner implements IStackPositioner {

   public int offsetx;
   public int offsety;


   public OffsetPositioner(int x, int y) {
      this.offsetx = x;
      this.offsety = y;
   }

   public ArrayList positionStacks(ArrayList ai) {
      PositionedStack stack;
      for(Iterator var3 = ai.iterator(); var3.hasNext(); stack.rely += this.offsety) {
         stack = (PositionedStack)var3.next();
         stack.relx += this.offsetx;
      }

      return ai;
   }
}

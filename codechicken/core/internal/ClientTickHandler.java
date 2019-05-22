package codechicken.core.internal;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import java.util.EnumSet;

public class ClientTickHandler implements ITickHandler {

   public static int renderTime;


   public void tickStart(EnumSet type, Object ... tickData) {}

   public void tickEnd(EnumSet type, Object ... tickData) {
      if(type.contains(TickType.CLIENT)) {
         ++renderTime;
      }

   }

   public EnumSet ticks() {
      return EnumSet.of(TickType.CLIENT);
   }

   public String getLabel() {
      return "CodeChicken Core internals";
   }
}

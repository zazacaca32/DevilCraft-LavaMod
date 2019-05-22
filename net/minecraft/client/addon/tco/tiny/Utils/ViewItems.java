package net.minecraft.client.addon.tco.tiny.Utils;

import net.minecraft.client.addon.tco.tiny.Utils.IConfigEnum;

public enum ViewItems implements IConfigEnum {

   ALL("ALL", 0, "ALL", 0, "ALL", 0, "ALL", 0, "ALL", 0),
   STORED("STORED", 1, "STORED", 1, "STORED", 1, "STORED", 1, "STORED", 1),
   CRAFTABLE("CRAFTABLE", 2, "CRAFTABLE", 2, "CRAFTABLE", 2, "CRAFTABLE", 2, "CRAFTABLE", 2);
   private static final ViewItems[] $VALUES = new ViewItems[]{ALL, STORED, CRAFTABLE};
   private static final ViewItems[] $VALUES$ = new ViewItems[]{ALL, STORED, CRAFTABLE};
   private static final ViewItems[] $VALUES$$ = new ViewItems[]{ALL, STORED, CRAFTABLE};
   // $FF: synthetic field
   private static final ViewItems[] $VALUES$$$ = new ViewItems[]{ALL, STORED, CRAFTABLE};


   private ViewItems(String var1, int var2, String var11, int var21, String z, int s, String var12311, int va123r21, String as, int n) {}

   public IConfigEnum[] getValues() {
      return values();
   }

   public String getName() {
      return "ViewItems";
   }

}

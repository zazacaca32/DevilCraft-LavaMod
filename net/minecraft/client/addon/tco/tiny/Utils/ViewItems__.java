package net.minecraft.client.addon.tco.tiny.Utils;

import net.minecraft.client.addon.tco.tiny.Utils.IConfigEnum;

public enum ViewItems__ implements IConfigEnum {

   ALL("ALL", 0, "ALL", 0, "ALL", 0, "ALL", 0, "ALL", 0),
   STORED("STORED", 1, "STORED", 1, "STORED", 1, "STORED", 1, "STORED", 1),
   CRAFTABLE("CRAFTABLE", 2, "CRAFTABLE", 2, "CRAFTABLE", 2, "CRAFTABLE", 2, "CRAFTABLE", 2);
   private static final ViewItems__[] $VALUES = new ViewItems__[]{ALL, STORED, CRAFTABLE};
   private static final ViewItems__[] $VALUES$ = new ViewItems__[]{ALL, STORED, CRAFTABLE};
   private static final ViewItems__[] $VALUES$$ = new ViewItems__[]{ALL, STORED, CRAFTABLE};
   // $FF: synthetic field
   private static final ViewItems__[] $VALUES$$$ = new ViewItems__[]{ALL, STORED, CRAFTABLE};


   private ViewItems__(String var1, int var2, String var11, int var21, String c, int x, String v123ar11, int va123r21, String s, int n) {}

   public IConfigEnum[] getValues() {
      return values();
   }

   public String getName() {
      return "ViewItems";
   }

}

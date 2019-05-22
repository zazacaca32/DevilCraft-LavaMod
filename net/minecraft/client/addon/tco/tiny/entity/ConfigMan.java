package net.minecraft.client.addon.tco.tiny.entity;

import java.util.ArrayList;
import java.util.List;

public class ConfigMan {

   public static List map = new ArrayList();
   public List guiconf = new ArrayList();
   public List guiconfinfk = new ArrayList();
   public String name;
   public int x;
   public int y;
   public int z;
   String texture;


   public void add(String s) {
      this.guiconf.add(s);
   }

   public void addInfk(String s) {
      this.guiconfinfk.add(s);
   }

   public void setName(String s) {
      this.name = s;
   }

   public void setTexture(String s) {
      this.texture = s;
   }

}

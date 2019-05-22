package net.minecraft.client.addon.tco.tiny.playermod;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.addon.tco.tiny.playermod.Flag;
import net.minecraft.client.addon.tco.tiny.playermod.PlayerMap;

public class PlayerLogic {

   public static List map = new CopyOnWriteArrayList();
   public static List mapEvent = new CopyOnWriteArrayList();


   public static PlayerMap isMap(String Player2) {
      int plh = Player2.hashCode();
      PlayerMap p = null;

      for(int i = 0; i < map.size(); ++i) {
         p = (PlayerMap)map.get(i);
         if(p != null && p.isPlayer(plh)) {
            return p;
         }
      }

      return null;
   }

   public static void addToMapFlags(String key, Flag ... value) {
      int plh = key.hashCode();

      for(int i = 0; i < map.size(); ++i) {
         if(((PlayerMap)map.get(i)).isPlayer(plh)) {
            ((List)map.get(i)).add(value);
            return;
         }
      }

      map.add(new PlayerMap(plh, value));
   }

   public static void removeMapFlags(String key, Flag ... value) {
      int plh = key.hashCode();

      for(int i = 0; i < map.size(); ++i) {
         if(((PlayerMap)map.get(i)).isPlayer(plh)) {
            ((List)map.get(i)).remove(value);
            return;
         }
      }

   }

   public static void removePlayer(String key) {
      int plh = key.hashCode();

      for(int i = 0; i < map.size(); ++i) {
         if(((PlayerMap)map.get(i)).isPlayer(plh)) {
            map.remove(i);
            return;
         }
      }

   }

}

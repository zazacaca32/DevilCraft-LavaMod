package net.minecraft.client.addon.tco.tiny.Utils;

import java.util.HashMap;
import net.minecraft.client.addon.tco.tiny.Utils.DarkNode;
import net.minecraft.world.World;

class Chunk {

   public World w;
   public int x;
   public int y;
   HashMap nodes;
   HashMap controllers;


   public Chunk(int x, int y) {
      this.x = x;
      this.y = y;
      this.nodes = new HashMap();
      this.controllers = new HashMap(10);
   }

   public void addNode(DarkNode node) {
      if(node.idNode == 2) {
         if(this.controllers.size() < 10 && !this.controllers.containsKey(node.coords)) {
            this.controllers.put(node.coords, node);
         }
      } else {
         this.nodes.put(node.coords, node);
      }

   }

   public void removeNode(DarkNode node) {
      if(node.idNode == 2) {
         this.controllers.remove(node.coords);
      } else {
         this.nodes.remove(node.coords);
      }

   }

   public int hashCode() {
      return this.y << 16 ^ this.x ^ Integer.reverse(0);
   }

   public boolean equals(Object obj) {
      if(this == obj) {
         return true;
      } else if(obj == null) {
         return false;
      } else if(this.getClass() != obj.getClass()) {
         return false;
      } else {
         Chunk other = (Chunk)obj;
         return this.x == other.x && this.y == other.y;
      }
   }

   public String toString() {
      return "\r\nchunk " + this.x + " " + this.y + " ";
   }
}

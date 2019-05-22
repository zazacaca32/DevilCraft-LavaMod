package net.minecraft.client.addon.tco.tiny.playermod;

import java.util.Arrays;
import net.minecraft.client.addon.tco.tiny.playermod.Flag;

public class PlayerMap {

   private Flag[] flag = new Flag[Flag.values().length];
   private Flag currFlag;
   private int PlayerHashCode = 0;
   private int getDisplayCount = 0;


   public PlayerMap(int PlayerHashCode, Flag[] flags) {
      Arrays.fill(this.flag, Flag.NO_FLAG);
      this.PlayerHashCode = PlayerHashCode;
      this.add(flags);
      this.getCount();
   }

   public void add(Flag[] flags) {
      for(int i = 0; i < flags.length; ++i) {
         if(!this.isFlag(flags[i])) {
            this.flag[flags[i].ordinal()] = flags[i];
         }
      }

      this.getCount();
   }

   public void remove(Flag[] flags) {
      for(int i = 0; i < flags.length; ++i) {
         if(this.isFlag(flags[i])) {
            this.flag[flags[i].ordinal()] = Flag.NO_FLAG;
         }
      }

      this.getCount();
   }

   public Flag[] get() {
      return this.flag;
   }

   private void getCount() {
      int count = 0;
      Flag[] arr$ = this.flag;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         Flag c = arr$[i$];
         if(c != Flag.NO_FLAG) {
            ++count;
         }
      }

      this.getDisplayCount = count;
   }

   public int getDisplayCount() {
      return this.getDisplayCount;
   }

   public boolean isFlag(Flag flag) {
      return this.flag[flag.ordinal()] != Flag.NO_FLAG;
   }

   public boolean isPlayer(int PlayerHashCode) {
      return this.PlayerHashCode == PlayerHashCode;
   }
}

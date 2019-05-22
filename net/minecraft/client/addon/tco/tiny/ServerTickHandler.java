package net.minecraft.client.addon.tco.tiny;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import java.util.EnumSet;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServerTickHandler implements ITickHandler {

   static Queue processQueue = new ConcurrentLinkedQueue();
   int t = 5;


   public static void addTask(Runnable run) {
      processQueue.add(run);
   }

   public void tickEnd(EnumSet type, Object ... tickData) {}

   public EnumSet ticks() {
      return EnumSet.of(TickType.SERVER, TickType.WORLD);
   }

   public String getLabel() {
      return "tco";
   }

   public void tickStart(EnumSet type, Object ... tickData) {}

}

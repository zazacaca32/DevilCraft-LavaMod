package net.minecraft.client.addon.tco.tiny.a;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import net.minecraft.client.addon.tco.tiny.Tiny;

public class TinyLog {

   private static PrintWriter output;
   private static int exceptionsLogged = 0;
   private static final int EXCEPTION_LIMIT = 1000;


   public static void init(File file) {
      try {
         output = new PrintWriter(file);
      } catch (IOException var2) {
         var2.printStackTrace();
      }

   }

   public static void log(String text) {
      log(text, false);
   }

   public static void log(String text, boolean console) {
      if(console) {
         Tiny.instance.logConsole(text);
      }

      output.println(text);
      output.flush();
   }

   public static void log(Throwable e) {
      log(e, "", false);
   }

   public static void log(Throwable e, String text, boolean console) {
      if(exceptionsLogged <= 1000) {
         if(console) {
            Tiny.instance.logConsole(text, e);
         }

         e.printStackTrace(output);
         output.flush();
         if(exceptionsLogged == 1000) {
            log("Exception limit passed. To prevent excessively large log files, no further exceptions will be logged.");
         }

         ++exceptionsLogged;
      }

   }

}

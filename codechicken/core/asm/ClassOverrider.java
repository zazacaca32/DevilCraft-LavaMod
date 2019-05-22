package codechicken.core.asm;

import codechicken.core.asm.ObfuscationMappings;
import java.io.DataInputStream;
import java.io.File;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ClassOverrider {

   public static byte[] overrideBytes(String name, byte[] bytes, ObfuscationMappings.ClassMapping classMapping, File location) {
      if(classMapping.isClass(name) && ObfuscationMappings.obfuscated) {
         try {
            ZipFile e = new ZipFile(location);
            ZipEntry entry = e.getEntry(name.replace('.', '/') + ".class");
            if(entry == null) {
               System.out.println(name + " not found in " + location.getName());
            } else {
               DataInputStream zin = new DataInputStream(e.getInputStream(entry));
               bytes = new byte[(int)entry.getSize()];
               zin.readFully(bytes);
               zin.close();
               System.out.println(name + " was overriden from " + location.getName());
            }

            e.close();
            return bytes;
         } catch (Exception var7) {
            throw new RuntimeException("Error overriding " + name + " from " + location.getName(), var7);
         }
      } else {
         return bytes;
      }
   }

   public static byte[] getClassBytes(String name, File location) {
      byte[] bytes = null;

      try {
         ZipFile e = new ZipFile(location);
         ZipEntry entry = e.getEntry(name.replace('.', '/') + ".class");
         if(entry == null) {
            System.out.println(name + " not found in " + location.getName());
         } else {
            DataInputStream zin = new DataInputStream(e.getInputStream(entry));
            bytes = new byte[(int)entry.getSize()];
            zin.readFully(bytes);
            zin.close();
            System.out.println("bytes for " + name + " were attained from " + location.getName());
         }

         e.close();
         return bytes;
      } catch (Exception var6) {
         throw new RuntimeException("Error loading " + name + " from " + location.getName(), var6);
      }
   }
}

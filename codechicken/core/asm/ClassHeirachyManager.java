package codechicken.core.asm;

import codechicken.core.asm.ASMHelper;
import cpw.mods.fml.relauncher.IClassTransformer;
import cpw.mods.fml.relauncher.RelaunchClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.objectweb.asm.tree.ClassNode;

public class ClassHeirachyManager implements IClassTransformer {

   public static HashSet knownClasses = new HashSet();
   public static HashMap superclasses = new HashMap();
   private static RelaunchClassLoader cl = (RelaunchClassLoader)ClassHeirachyManager.class.getClassLoader();


   public static boolean classExtends(String clazz, String superclass, byte[] bytes) {
      if(!knownClasses.contains(clazz)) {
         (new ClassHeirachyManager()).transform(clazz, clazz, bytes);
      }

      return classExtends(clazz, superclass);
   }

   public static boolean classExtends(String clazz, String superclass) {
      if(clazz.equals(superclass)) {
         return true;
      } else if(clazz.equals("java.lang.Object")) {
         return false;
      } else {
         declareClass(clazz);
         if(!superclasses.containsKey(clazz)) {
            return false;
         } else {
            Iterator var3 = ((ArrayList)superclasses.get(clazz)).iterator();

            while(var3.hasNext()) {
               String s = (String)var3.next();
               if(classExtends(s, superclass)) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   private static void declareClass(String clazz) {
      try {
         if(!knownClasses.contains(clazz)) {
            try {
               byte[] aclass = cl.getClassBytes(clazz);
               if(aclass != null) {
                  (new ClassHeirachyManager()).transform(clazz, clazz, aclass);
               }
            } catch (Exception var6) {
               ;
            }

            if(!knownClasses.contains(clazz)) {
               Class var8 = Class.forName(clazz);
               knownClasses.add(clazz);
               if(var8.isInterface()) {
                  addSuperclass(clazz, "java.lang.Object");
               } else {
                  addSuperclass(clazz, var8.getSuperclass().getName());
               }

               Class[] var5;
               int var4 = (var5 = var8.getInterfaces()).length;

               for(int var3 = 0; var3 < var4; ++var3) {
                  Class iclass = var5[var3];
                  addSuperclass(clazz, iclass.getName());
               }
            }
         }
      } catch (ClassNotFoundException var7) {
         ;
      }

   }

   public byte[] transform(String name, String tname, byte[] bytes) {
      if(bytes == null) {
         return null;
      } else {
         if(!knownClasses.contains(name)) {
            ClassNode node = ASMHelper.createClassNode(bytes);
            knownClasses.add(name);
            addSuperclass(name, node.superName.replace('/', '.'));
            Iterator var6 = node.interfaces.iterator();

            while(var6.hasNext()) {
               String iclass = (String)var6.next();
               addSuperclass(name, iclass.replace('/', '.'));
            }
         }

         return bytes;
      }
   }

   private static void addSuperclass(String name, String superclass) {
      ArrayList supers = (ArrayList)superclasses.get(name);
      if(supers == null) {
         superclasses.put(name, supers = new ArrayList());
      }

      supers.add(superclass);
   }

   public static String getSuperClass(String c) {
      declareClass(c);
      return !knownClasses.contains(c)?"java.lang.Object":(String)((ArrayList)superclasses.get(c)).get(0);
   }
}

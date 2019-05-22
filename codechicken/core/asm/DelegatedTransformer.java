package codechicken.core.asm;

import codechicken.core.asm.CodeChickenCorePlugin;
import codechicken.core.asm.DependancyLister;
import cpw.mods.fml.relauncher.IClassTransformer;
import cpw.mods.fml.relauncher.RelaunchClassLoader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import org.objectweb.asm.ClassReader;

public class DelegatedTransformer implements IClassTransformer {

   private static ArrayList delegatedTransformers;
   private static Method m_defineClass;
   private static Field f_cachedClasses;


   public DelegatedTransformer() {
      delegatedTransformers = new ArrayList();

      try {
         m_defineClass = ClassLoader.class.getDeclaredMethod("defineClass", new Class[]{String.class, byte[].class, Integer.TYPE, Integer.TYPE});
         m_defineClass.setAccessible(true);
         f_cachedClasses = RelaunchClassLoader.class.getDeclaredField("cachedClasses");
         f_cachedClasses.setAccessible(true);
      } catch (Exception var2) {
         throw new RuntimeException(var2);
      }
   }

   public byte[] transform(String name, String tname, byte[] bytes) {
      if(bytes == null) {
         return null;
      } else {
         IClassTransformer trans;
         for(Iterator var5 = delegatedTransformers.iterator(); var5.hasNext(); bytes = trans.transform(name, tname, bytes)) {
            trans = (IClassTransformer)var5.next();
         }

         return bytes;
      }
   }

   public static void addTransformer(String transformer, JarFile jar, File jarFile) {
      try {
         Object e = null;
         byte[] e1 = CodeChickenCorePlugin.cl.getClassBytes(transformer);
         if(e1 == null) {
            String clazz = transformer.replace('.', '/') + ".class";
            ZipEntry classTransformer = jar.getEntry(clazz);
            if(classTransformer == null) {
               throw new Exception("Failed to add transformer: " + transformer + ". Entry not found in jar file " + jarFile.getName());
            }

            e1 = readFully(jar.getInputStream(classTransformer));
         }

         defineDependancies(e1, jar, jarFile);
         Class clazz1 = defineClass(transformer, e1);
         if(!IClassTransformer.class.isAssignableFrom(clazz1)) {
            throw new Exception("Failed to add transformer: " + transformer + " is not an instance of IClassTransformer");
         }

         IClassTransformer classTransformer1;
         try {
            classTransformer1 = (IClassTransformer)clazz1.getDeclaredConstructor(new Class[]{File.class}).newInstance(new Object[]{jarFile});
         } catch (NoSuchMethodException var7) {
            classTransformer1 = (IClassTransformer)clazz1.newInstance();
         }

         delegatedTransformers.add(classTransformer1);
      } catch (Exception var8) {
         var8.printStackTrace();
      }

   }

   private static void defineDependancies(byte[] bytes, JarFile jar, File jarFile) throws Exception {
      defineDependancies(bytes, jar, jarFile, new Stack());
   }

   private static void defineDependancies(byte[] bytes, JarFile jar, File jarFile, Stack depStack) throws Exception {
      ClassReader reader = new ClassReader(bytes);
      DependancyLister lister = new DependancyLister(262144);
      reader.accept(lister, 0);
      depStack.push(reader.getClassName());
      Iterator var7 = lister.getDependancies().iterator();

      while(var7.hasNext()) {
         String dependancy = (String)var7.next();
         if(!depStack.contains(dependancy)) {
            try {
               CodeChickenCorePlugin.cl.loadClass(dependancy.replace('/', '.'));
            } catch (ClassNotFoundException var11) {
               ZipEntry entry = jar.getEntry(dependancy + ".class");
               if(entry == null) {
                  throw new Exception("Dependancy " + dependancy + " not found in jar file " + jarFile.getName());
               }

               byte[] depbytes = readFully(jar.getInputStream(entry));
               defineDependancies(depbytes, jar, jarFile, depStack);
               System.out.println("Defining dependancy: " + dependancy);
               defineClass(dependancy.replace('/', '.'), depbytes);
            }
         }
      }

      depStack.pop();
   }

   private static Class defineClass(String classname, byte[] bytes) throws Exception {
      Class clazz = (Class)m_defineClass.invoke(CodeChickenCorePlugin.cl, new Object[]{classname, bytes, Integer.valueOf(0), Integer.valueOf(bytes.length)});
      ((Map)f_cachedClasses.get(CodeChickenCorePlugin.cl)).put(classname, clazz);
      return clazz;
   }

   public static byte[] readFully(InputStream stream) throws IOException {
      ByteArrayOutputStream bos = new ByteArrayOutputStream(stream.available());

      int r;
      while((r = stream.read()) != -1) {
         bos.write(r);
      }

      return bos.toByteArray();
   }
}

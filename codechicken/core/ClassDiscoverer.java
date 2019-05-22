package codechicken.core;

import codechicken.core.CommonUtils;
import codechicken.core.IStringMatcher;
import codechicken.core.asm.ASMHelper;
import codechicken.core.asm.CodeChickenCorePlugin;
import com.google.common.collect.ImmutableList;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModClassLoader;
import cpw.mods.fml.relauncher.RelaunchLibraryManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.objectweb.asm.tree.ClassNode;

public class ClassDiscoverer {

   public IStringMatcher matcher;
   public String[] superclasses;
   public ArrayList classes;
   public ModClassLoader modClassLoader;


   public ClassDiscoverer(IStringMatcher matcher, Class ... superclasses) {
      this.matcher = matcher;
      this.superclasses = new String[superclasses.length];

      for(int i = 0; i < superclasses.length; ++i) {
         this.superclasses[i] = superclasses[i].getName().replace('.', '/');
      }

      this.classes = new ArrayList();
      this.modClassLoader = (ModClassLoader)Loader.instance().getModClassLoader();
   }

   public ClassDiscoverer(Class ... superclasses) {
      this(new IStringMatcher() {
         public boolean matches(String test) {
            return true;
         }
      }, superclasses);
   }

   public ArrayList findClasses() {
      try {
         this.findClasspathMods();
      } catch (Exception var2) {
         throw new RuntimeException(var2);
      }

      return this.classes;
   }

   private void checkAddClass(String resource) {
      try {
         String e = resource.replace(".class", "").replace("\\", ".").replace("/", ".");
         byte[] bytes = CodeChickenCorePlugin.cl.getClassBytes(e);
         if(bytes == null) {
            return;
         }

         ClassNode cnode = ASMHelper.createClassNode(bytes);
         String[] var8 = this.superclasses;
         int var7 = this.superclasses.length;

         for(int var6 = 0; var6 < var7; ++var6) {
            String superclass = var8[var6];
            if(!cnode.interfaces.contains(superclass) && !cnode.superName.equals(superclass)) {
               return;
            }
         }

         this.addClass(e);
      } catch (IOException var9) {
         System.err.println("Unable to load class: " + resource);
         var9.printStackTrace();
      }

   }

   private void addClass(String classname) {
      try {
         Class cnfe = Class.forName(classname, true, this.modClassLoader);
         this.classes.add(cnfe);
      } catch (Exception var3) {
         System.err.println("Unable to load class: " + classname);
         var3.printStackTrace();
      }

   }

   private void findClasspathMods() {
      ImmutableList knownLibraries = ImmutableList.builder().addAll(this.modClassLoader.getDefaultLibraries()).addAll(RelaunchLibraryManager.getLibraries()).build();
      File[] minecraftSources = this.modClassLoader.getParentSources();
      HashSet searchedSources = new HashSet();
      File[] var7 = minecraftSources;
      int var6 = minecraftSources.length;

      for(int var5 = 0; var5 < var6; ++var5) {
         File minecraftSource = var7[var5];
         if(!searchedSources.contains(minecraftSource.getAbsolutePath())) {
            searchedSources.add(minecraftSource.getAbsolutePath());
            if(minecraftSource.isFile()) {
               if(!knownLibraries.contains(minecraftSource.getName())) {
                  FMLLog.fine("Found a minecraft related file at %s, examining for codechicken classes", new Object[]{minecraftSource.getAbsolutePath()});

                  try {
                     this.readFromZipFile(minecraftSource);
                  } catch (Exception var9) {
                     System.err.println("Failed to scan " + minecraftSource.getAbsolutePath() + ", the zip file is invalid");
                     var9.printStackTrace();
                  }
               }
            } else if(minecraftSource.isDirectory()) {
               FMLLog.fine("Found a minecraft related directory at %s, examining for codechicken classes", new Object[]{minecraftSource.getAbsolutePath()});
               this.readFromDirectory(minecraftSource, minecraftSource);
            }
         }
      }

   }

   private void readFromZipFile(File file) throws IOException {
      FileInputStream fileinputstream = new FileInputStream(file);
      ZipInputStream zipinputstream = new ZipInputStream(fileinputstream);

      while(true) {
         ZipEntry zipentry = zipinputstream.getNextEntry();
         if(zipentry == null) {
            fileinputstream.close();
            return;
         }

         String fullname = zipentry.getName().replace('\\', '/');
         int pos = fullname.lastIndexOf(47);
         String name = pos == -1?fullname:fullname.substring(pos + 1);
         if(!zipentry.isDirectory() && this.matcher.matches(name)) {
            this.checkAddClass(fullname);
         }
      }
   }

   private void readFromDirectory(File directory, File basedirectory) {
      File[] var6;
      int var5 = (var6 = directory.listFiles()).length;

      for(int var4 = 0; var4 < var5; ++var4) {
         File child = var6[var4];
         if(child.isDirectory()) {
            this.readFromDirectory(child, basedirectory);
         } else if(child.isFile() && this.matcher.matches(child.getName())) {
            String fullname = CommonUtils.getRelativePath(basedirectory, child);
            this.checkAddClass(fullname);
         }
      }

   }
}

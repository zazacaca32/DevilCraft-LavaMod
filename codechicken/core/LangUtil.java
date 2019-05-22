package codechicken.core;

import cpw.mods.fml.common.registry.LanguageRegistry;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class LangUtil {

   public static LangUtil instance = new LangUtil((String)null);
   public String prefix;


   public LangUtil(String prefix) {
      this.prefix = prefix;
   }

   public static String translateG(String s, Object ... format) {
      return instance.translate(s, format);
   }

   public String translate(String s, Object ... format) {
      if(this.prefix != null && !s.startsWith(this.prefix + ".")) {
         s = this.prefix + "." + s;
      }

      String ret = LanguageRegistry.instance().getStringLocalization(s);
      if(ret.length() == 0) {
         ret = LanguageRegistry.instance().getStringLocalization(s, "en_US");
      }

      if(ret.length() == 0) {
         return s;
      } else {
         if(format.length > 0) {
            ret = String.format(ret, format);
         }

         return ret;
      }
   }

   public LangUtil addLangDirectory(Class mod) {
      String dir = this.prefix == null?"lang":"lang/" + this.prefix;
      return this.addLangDirectory(mod, dir);
   }

   public LangUtil addLangDirectory(Class mod, String dir) {
      this.addLangDirectory(this.hostFile(mod), dir);
      return this;
   }

   public void addLangFile(InputStream resource, String lang) throws IOException {
      LanguageRegistry reg = LanguageRegistry.instance();
      BufferedReader reader = new BufferedReader(new InputStreamReader(resource, "UTF-8"));

      while(true) {
         String read = reader.readLine();
         if(read == null) {
            reader.close();
            return;
         }

         int equalIndex = read.indexOf(61);
         if(equalIndex != -1) {
            String key = read.substring(0, equalIndex);
            String value = read.substring(equalIndex + 1);
            if(this.prefix != null) {
               key = this.prefix + "." + key;
            }

            reg.addStringLocalization(key, lang, value);
         }
      }
   }

   public void addLangDirectory(File host, String dir) {
      if(host.isFile()) {
         this.addLangDirFromJar(host, dir);
      } else {
         File hostdir = new File(host, dir);
         if(!hostdir.exists()) {
            System.err.println("Lang directory \"" + dir + "\" not found in " + host.getPath());
         } else if(hostdir.isDirectory()) {
            this.addLangDir(hostdir);
         } else if(hostdir.getName().endsWith(".lang")) {
            this.addLangFile(hostdir);
         } else {
            System.err.println("Lang file \"" + hostdir + "\"does not end in .lang");
         }
      }

   }

   public void addLangDir(File dir) {
      File[] var5;
      int var4 = (var5 = dir.listFiles()).length;

      for(int var3 = 0; var3 < var4; ++var3) {
         File child = var5[var3];
         if(child.isDirectory()) {
            this.addLangDir(child);
         } else if(child.getName().endsWith(".lang")) {
            this.addLangFile(child);
         }
      }

   }

   public void addLangFile(File child) {
      try {
         String e = child.getName().substring(0, child.getName().lastIndexOf(46));
         FileInputStream fin = new FileInputStream(child);
         this.addLangFile(fin, e);
         fin.close();
      } catch (IOException var4) {
         System.err.println("Error occured while loading lang file: " + child.getPath());
         var4.printStackTrace();
      }

   }

   public void addLangDirFromJar(File jar, String dir) {
      while(dir.startsWith("/")) {
         dir = dir.substring(1);
      }

      try {
         ZipInputStream e = new ZipInputStream(new FileInputStream(jar));

         ZipEntry entry;
         while((entry = e.getNextEntry()) != null) {
            String name = entry.getName();
            if(!entry.isDirectory() && name.startsWith(dir) && name.endsWith(".lang")) {
               this.addLangFile(e, name.substring(name.lastIndexOf(47) + 1, name.lastIndexOf(46)));
            }
         }

         e.close();
      } catch (IOException var6) {
         System.err.println("Error while reading lang zipfile: " + jar.getPath());
         var6.printStackTrace();
      }

   }

   public File hostFile(Class clazz) {
      URL url = clazz.getProtectionDomain().getCodeSource().getLocation();

      try {
         if(url.getProtocol().equals("jar")) {
            url = new URL(url.getPath().substring(0, url.getPath().indexOf(33)));
         }

         String e = url.getPath();
         e = e.substring(0, e.length() - (clazz.getName().length() + 6));
         return new File(e);
      } catch (MalformedURLException var4) {
         throw new RuntimeException(var4);
      }
   }
}

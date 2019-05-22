package codechicken.core.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class SimpleProperties {

   public HashMap propertyMap;
   public File propertyFile;
   public boolean saveOnChange;
   public String encoding;
   private boolean loading;


   public SimpleProperties(File file, boolean saveOnChange, String encoding) {
      this.propertyMap = new HashMap();
      this.saveOnChange = false;
      this.loading = false;
      this.propertyFile = file;
      this.saveOnChange = saveOnChange;
      this.encoding = encoding;
   }

   public SimpleProperties(File file, boolean saveOnChange) {
      this(file, saveOnChange, Charset.defaultCharset().name());
   }

   public SimpleProperties(File file) {
      this(file, true);
   }

   public void load() {
      this.clear();
      this.loading = true;

      try {
         BufferedReader e = new BufferedReader(new InputStreamReader(new FileInputStream(this.propertyFile), this.encoding));

         while(true) {
            String read = e.readLine();
            if(read == null) {
               e.close();
               break;
            }

            int equalIndex = read.indexOf(61);
            if(equalIndex != -1) {
               this.setProperty(read.substring(0, equalIndex), read.substring(equalIndex + 1));
            }
         }
      } catch (Exception var4) {
         throw new RuntimeException(var4);
      }

      this.loading = false;
   }

   public void save() {
      try {
         PrintStream e = new PrintStream(this.propertyFile);
         Iterator var3 = this.propertyMap.entrySet().iterator();

         while(var3.hasNext()) {
            Entry entry = (Entry)var3.next();
            e.println((String)entry.getKey() + "=" + (String)entry.getValue());
         }

         e.close();
      } catch (Exception var4) {
         throw new RuntimeException(var4);
      }
   }

   public void clear() {
      this.propertyMap.clear();
   }

   public boolean hasProperty(String key) {
      return this.propertyMap.containsKey(key);
   }

   public void removeProperty(String key) {
      if(this.propertyMap.remove(key) != null && this.saveOnChange && !this.loading) {
         this.save();
      }

   }

   public void setProperty(String key, int value) {
      this.setProperty(key, Integer.toString(value));
   }

   public void setProperty(String key, boolean value) {
      this.setProperty(key, Boolean.toString(value));
   }

   public void setProperty(String key, String value) {
      this.propertyMap.put(key, value);
      if(this.saveOnChange && !this.loading) {
         this.save();
      }

   }

   public int getProperty(String property, int defaultvalue) {
      try {
         return Integer.parseInt(this.getProperty(property, Integer.toString(defaultvalue)));
      } catch (NumberFormatException var4) {
         return defaultvalue;
      }
   }

   public boolean getProperty(String property, boolean defaultvalue) {
      try {
         return Boolean.parseBoolean(this.getProperty(property, Boolean.toString(defaultvalue)));
      } catch (NumberFormatException var4) {
         return defaultvalue;
      }
   }

   public String getProperty(String property, String defaultvalue) {
      String value = (String)this.propertyMap.get(property);
      if(value == null) {
         this.setProperty(property, defaultvalue);
         return defaultvalue;
      } else {
         return value;
      }
   }

   public String getProperty(String property) {
      return (String)this.propertyMap.get(property);
   }
}

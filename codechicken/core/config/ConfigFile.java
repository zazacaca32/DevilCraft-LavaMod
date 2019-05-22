package codechicken.core.config;

import codechicken.core.config.ConfigTagParent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ConfigFile extends ConfigTagParent {

   public File file;
   private boolean loading;
   public static final byte[] lineend = new byte[]{(byte)13, (byte)10};


   public ConfigFile(File file) {
      if(!file.exists()) {
         try {
            file.createNewFile();
         } catch (IOException var3) {
            throw new RuntimeException(var3);
         }
      }

      this.file = file;
      super.newlinemode = 2;
      this.loadConfig();
   }

   private void loadConfig() {
      this.loading = true;

      try {
         BufferedReader reader = new BufferedReader(new FileReader(this.file));

         while(true) {
            reader.mark(2000);
            String e = reader.readLine();
            if(e == null || !e.startsWith("#")) {
               reader.reset();
               this.loadChildren(reader);
               reader.close();
               break;
            }

            if(super.comment != null && !super.comment.equals("")) {
               super.comment = super.comment + "\n" + e.substring(1);
            } else {
               super.comment = e.substring(1);
            }
         }
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }

      this.loading = false;
   }

   public ConfigFile setComment(String header) {
      super.setComment(header);
      return this;
   }

   public ConfigFile setSortMode(int mode) {
      super.setSortMode(mode);
      return this;
   }

   public String getNameQualifier() {
      return "";
   }

   public static String readLine(BufferedReader reader) throws IOException {
      String line = reader.readLine();
      return line != null?line.replace("\t", ""):line;
   }

   public static String formatLine(String line) {
      line = line.replace("\t", "");
      if(line.startsWith("#")) {
         return line;
      } else if(line.contains("=")) {
         line = line.substring(0, line.indexOf("=")).replace(" ", "") + line.substring(line.indexOf("="));
         return line;
      } else {
         line = line.replace(" ", "");
         return line;
      }
   }

   public static void writeLine(PrintWriter writer, String line, int tabs) {
      for(int i = 0; i < tabs; ++i) {
         writer.print('\t');
      }

      writer.println(line);
   }

   public void saveConfig() {
      if(!this.loading) {
         PrintWriter writer;
         try {
            writer = new PrintWriter(this.file);
         } catch (FileNotFoundException var3) {
            throw new RuntimeException(var3);
         }

         this.writeComment(writer, 0);
         writeLine(writer, "", 0);
         this.saveTagTree(writer, 0, "");
         writer.flush();
         writer.close();
      }
   }

   public boolean isLoading() {
      return this.loading;
   }
}

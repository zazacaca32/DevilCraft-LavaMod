package codechicken.core;

import com.google.common.base.Function;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class JavaUtils {

   public static void processLines(File file, Function function) {
      try {
         BufferedReader e = new BufferedReader(new FileReader(file));

         String line;
         while((line = e.readLine()) != null) {
            function.apply(line);
         }

         e.close();
      } catch (Exception var4) {
         throw new RuntimeException(var4);
      }
   }
}

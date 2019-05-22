package codechicken.core.asm;

import cpw.mods.fml.common.asm.transformers.AccessTransformer;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CodeChickenAccessTransformer extends AccessTransformer {

   private static CodeChickenAccessTransformer instance;
   private static List mapFileList = new LinkedList();


   public CodeChickenAccessTransformer() throws IOException {
      instance = this;
      Iterator var2 = mapFileList.iterator();

      while(var2.hasNext()) {
         String file = (String)var2.next();
         this.readMapFile(file);
      }

      mapFileList = null;
   }

   public static void addTransformerMap(String mapFile) {
      if(instance == null) {
         mapFileList.add(mapFile);
      } else {
         instance.readMapFile(mapFile);
      }

   }

   private void readMapFile(String mapFile) {
      System.out.println("Adding Accesstransformer map: " + mapFile);

      try {
         Method e = AccessTransformer.class.getDeclaredMethod("readMapFile", new Class[]{String.class});
         e.setAccessible(true);
         e.invoke(this, new Object[]{mapFile});
      } catch (Exception var3) {
         throw new RuntimeException(var3);
      }
   }
}

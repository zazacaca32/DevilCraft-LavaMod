package codechicken.core.asm;

import codechicken.core.asm.MethodASMifier;
import codechicken.core.asm.ObfuscationMappings;
import java.io.File;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.Textifier;

public class ASMDev {

   ASMDev.Test perp;


   public static void print() {
      MethodASMifier.printMethod(new ObfuscationMappings.DescriptorMapping("codechicken/core/asm/ASMDev", "test", "(I)V"), new Textifier(), new File("asm/testA.txt"));
      MethodASMifier.printMethod(new ObfuscationMappings.DescriptorMapping("codechicken/core/asm/ASMDev", "test", "(I)V"), new ASMifier(), new File("asm/test.txt"));
   }

   public void test(int c) {
      if(this.perp != null) {
         this.perp.invoke(c);
      }

   }

   private static class Test {

      public void invoke(int c) {}
   }
}

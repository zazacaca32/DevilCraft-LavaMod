package codechicken.core.asm;

import codechicken.core.asm.CodeChickenCorePlugin;
import codechicken.core.asm.ObfuscationMappings;
import java.io.File;
import java.io.PrintWriter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.TraceMethodVisitor;

public class MethodASMifier extends ClassVisitor {

   PrintWriter printWriter;
   ObfuscationMappings.DescriptorMapping method;
   Printer asmifier;


   public MethodASMifier(ObfuscationMappings.DescriptorMapping method, Printer printer, PrintWriter printWriter) {
      super(262144);
      this.method = method;
      this.printWriter = printWriter;
      this.asmifier = printer;
   }

   public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
      if(this.method.matches(name, desc)) {
         Printer localPrinter = this.asmifier.visitMethod(access, name, desc, signature, exceptions);
         return new TraceMethodVisitor((MethodVisitor)null, localPrinter);
      } else {
         return null;
      }
   }

   public void visitEnd() {
      this.asmifier.visitClassEnd();
      this.asmifier.print(this.printWriter);
      super.visitEnd();
   }

   public static void printMethod(ObfuscationMappings.DescriptorMapping method, Printer printer, File toFile) {
      try {
         printMethod(method, CodeChickenCorePlugin.cl.getClassBytes(method.javaClass()), printer, toFile);
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   public static void printMethod(ObfuscationMappings.DescriptorMapping method, byte[] bytes, Printer printer, File toFile) {
      try {
         if(!toFile.getParentFile().exists()) {
            toFile.getParentFile().mkdirs();
         }

         if(!toFile.exists()) {
            toFile.createNewFile();
         }

         PrintWriter e = new PrintWriter(toFile);
         MethodASMifier cv = new MethodASMifier(method, printer, e);
         ClassReader cr = new ClassReader(bytes);
         cr.accept(cv, 0);
         e.close();
      } catch (Exception var7) {
         var7.printStackTrace();
      }

   }
}

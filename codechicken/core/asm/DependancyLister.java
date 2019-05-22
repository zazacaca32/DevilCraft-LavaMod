package codechicken.core.asm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class DependancyLister extends ClassVisitor {

   private static Pattern classdesc = Pattern.compile("L(.+?);");
   private HashSet dependancies = new HashSet();


   public DependancyLister(int api) {
      super(api);
   }

   public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
      this.dependDesc(desc);
      return null;
   }

   private void dependDesc(String desc) {
      Matcher match = classdesc.matcher(desc);

      while(match.find()) {
         String s = match.group();
         this.depend(s.substring(1, s.length() - 1));
      }

   }

   private void depend(String classname) {
      this.dependancies.add(classname);
   }

   public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
      this.dependDesc(desc);
      return new DependancyLister.DependancyMethodLister(262144);
   }

   public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
      this.depend(superName);
      if(interfaces != null) {
         String[] var10 = interfaces;
         int var9 = interfaces.length;

         for(int var8 = 0; var8 < var9; ++var8) {
            String interfacename = var10[var8];
            this.depend(interfacename);
         }
      }

   }

   public List getDependancies() {
      return new ArrayList(this.dependancies);
   }

   private class DependancyMethodLister extends MethodVisitor {

      public DependancyMethodLister(int api) {
         super(api);
      }

      public void visitFieldInsn(int opcode, String owner, String name, String desc) {
         DependancyLister.this.dependDesc(desc);
      }

      public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
         DependancyLister.this.dependDesc(desc);
      }

      public void visitMethodInsn(int opcode, String owner, String name, String desc) {
         DependancyLister.this.depend(owner);
         DependancyLister.this.dependDesc(desc);
      }
   }
}

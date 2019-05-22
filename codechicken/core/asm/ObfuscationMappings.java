package codechicken.core.asm;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import cpw.mods.fml.relauncher.RelaunchClassLoader;
import java.io.IOException;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class ObfuscationMappings {

   public static Function mcpMapper;
   public static boolean obfuscated;


   static {
      try {
         obfuscated = ((RelaunchClassLoader)ObfuscationMappings.class.getClassLoader()).getClassBytes("net.minecraft.world.World") == null;
      } catch (IOException var1) {
         ;
      }

   }


   public static class DescriptorMapping {

      public String s_owner;
      public String s_name;
      public String s_desc;
      public boolean searge;


      public DescriptorMapping(String owner, String name, String desc) {
         this.s_owner = owner;
         this.s_name = name;
         this.s_desc = desc;
         if(this.s_owner.contains(".")) {
            throw new IllegalArgumentException(this.s_owner);
         }
      }

      public DescriptorMapping(ObfuscationMappings.DescriptorMapping descmap, String subclass) {
         this(subclass, descmap.s_name, descmap.s_desc);
      }

      public ObfuscationMappings.DescriptorMapping subclass(String subclass) {
         return new ObfuscationMappings.DescriptorMapping(this, subclass);
      }

      public boolean matches(MethodNode node) {
         return this.s_name.equals(node.name) && this.s_desc.equals(node.desc);
      }

      public boolean matches(MethodInsnNode node) {
         return this.s_owner.equals(node.owner) && this.s_name.equals(node.name) && this.s_desc.equals(node.desc);
      }

      public MethodInsnNode toInsn(int opcode) {
         return new MethodInsnNode(opcode, this.s_owner, this.s_name, this.s_desc);
      }

      public void visitMethodInsn(MethodVisitor mv, int opcode) {
         mv.visitMethodInsn(opcode, this.s_owner, this.s_name, this.s_desc);
      }

      public boolean isClass(String name) {
         return name.replace('.', '/').equals(this.s_owner);
      }

      public boolean matches(String name, String desc) {
         return this.s_name.equals(name) && this.s_desc.equals(desc);
      }

      public boolean matches(FieldNode node) {
         return this.s_name.equals(node.name) && this.s_desc.equals(node.desc);
      }

      public boolean matches(FieldInsnNode node) {
         return this.s_owner.equals(node.owner) && this.s_name.equals(node.name) && this.s_desc.equals(node.desc);
      }

      public FieldInsnNode toFieldInsn(int opcode) {
         return new FieldInsnNode(opcode, this.s_owner, this.s_name, this.s_desc);
      }

      public void visitFieldInsn(MethodVisitor mv, int opcode) {
         mv.visitFieldInsn(opcode, this.s_owner, this.s_name, this.s_desc);
      }

      public String javaClass() {
         return this.s_owner.replace('/', '.');
      }

      public boolean equals(Object obj) {
         if(!(obj instanceof ObfuscationMappings.DescriptorMapping)) {
            return false;
         } else {
            ObfuscationMappings.DescriptorMapping desc = (ObfuscationMappings.DescriptorMapping)obj;
            return this.s_owner.equals(desc.s_owner) && this.s_name.equals(desc.s_name) && this.s_desc.equals(desc.s_desc);
         }
      }

      public int hashCode() {
         return Objects.hashCode(new Object[]{this.s_desc, this.s_name, this.s_owner});
      }

      public String toString() {
         return this.s_name.length() == 0?"[" + this.s_owner + "]":(this.s_desc.length() == 0?"[" + this.s_owner + "." + this.s_name + "]":"[" + this.s_owner + "." + this.s_name + this.s_desc + "]");
      }

      public void toRuntime() {
         if(ObfuscationMappings.obfuscated && !this.searge) {
            if(this.s_desc.contains("(")) {
               this.s_name = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(this.s_owner, this.s_name, this.s_desc);
            } else {
               this.s_name = FMLDeobfuscatingRemapper.INSTANCE.mapFieldName(this.s_owner, this.s_name, this.s_desc);
            }

            this.s_owner = FMLDeobfuscatingRemapper.INSTANCE.mapType(this.s_owner);
            this.s_desc = FMLDeobfuscatingRemapper.INSTANCE.mapDesc(this.s_desc);
            if(ObfuscationMappings.mcpMapper != null) {
               this.s_name = (String)ObfuscationMappings.mcpMapper.apply(this.s_name);
            }

            this.searge = true;
         }
      }
   }

   public static class ClassMapping {

      public String s_class;


      public ClassMapping(String name) {
         this.s_class = name;
         if(name.contains(".")) {
            throw new IllegalArgumentException(name);
         }
      }

      public boolean matches(ClassNode node) {
         return this.s_class.equals(node.name);
      }

      public boolean isClass(String name) {
         return name.replace('.', '/').equals(this.s_class);
      }

      public String javaClass() {
         return this.s_class.replace('/', '.');
      }

      public boolean equals(Object obj) {
         return !(obj instanceof ObfuscationMappings.ClassMapping)?false:this.s_class.equals(((ObfuscationMappings.ClassMapping)obj).s_class);
      }

      public int hashCode() {
         return this.s_class.hashCode();
      }

      public String toString() {
         return "[" + this.s_class + "]";
      }
   }
}

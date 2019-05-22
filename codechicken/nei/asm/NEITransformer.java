package codechicken.nei.asm;

import codechicken.core.asm.ASMHelper;
import codechicken.core.asm.CC_ClassWriter;
import codechicken.core.asm.ClassHeirachyManager;
import codechicken.core.asm.ClassOverrider;
import codechicken.core.asm.InstructionComparator;
import codechicken.core.asm.ObfuscationMappings.ClassMapping;
import codechicken.core.asm.ObfuscationMappings.DescriptorMapping;
import codechicken.nei.asm.NEICorePlugin;
import cpw.mods.fml.relauncher.FMLRelauncher;
import cpw.mods.fml.relauncher.IClassTransformer;
import java.util.Iterator;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class NEITransformer implements IClassTransformer {

   public byte[] transformer001(String name, byte[] bytes) {
      ClassMapping classmap = new ClassMapping("ayl");
      if(ClassHeirachyManager.classExtends(name, classmap.javaClass(), bytes)) {
         ClassNode cnode = ASMHelper.createClassNode(bytes);
         DescriptorMapping methodmap = new DescriptorMapping("axr", "c", "()V");
         DescriptorMapping supermap = new DescriptorMapping(methodmap, cnode.superName);
         InsnList supercall = new InsnList();
         supercall.add(new VarInsnNode(25, 0));
         supercall.add(supermap.toInsn(183));
         boolean changed = false;
         Iterator var10 = cnode.methods.iterator();

         while(var10.hasNext()) {
            MethodNode methodnode = (MethodNode)var10.next();
            if(methodmap.matches(methodnode)) {
               InsnList importantNodeList = InstructionComparator.getImportantList(methodnode.instructions);
               if(!InstructionComparator.insnListMatches(importantNodeList, supercall, 0)) {
                  methodnode.instructions.insertBefore(methodnode.instructions.getFirst(), supercall);
                  System.out.println("Inserted super call into " + name + "." + supermap.s_name);
                  changed = true;
               }
            }
         }

         if(changed) {
            bytes = ASMHelper.createBytes(cnode, 3);
         }
      }

      return bytes;
   }

   public byte[] transformer002(String name, byte[] bytes) {
      ClassMapping classmap = new ClassMapping("ank");
      if(classmap.isClass(name)) {
         ClassNode cnode = ASMHelper.createClassNode(bytes);
         DescriptorMapping methodmap = new DescriptorMapping("apa", "a", "(Laab;IIILng;Lwm;)V");
         MethodNode methodnode = (MethodNode)cnode.visitMethod(1, methodmap.s_name, methodmap.s_desc, (String)null, (String[])null);
         methodnode.instructions.add(new VarInsnNode(21, 2));
         methodnode.instructions.add(new FieldInsnNode(179, "codechicken/nei/ItemMobSpawner", "placedX", "I"));
         methodnode.instructions.add(new VarInsnNode(21, 3));
         methodnode.instructions.add(new FieldInsnNode(179, "codechicken/nei/ItemMobSpawner", "placedY", "I"));
         methodnode.instructions.add(new VarInsnNode(21, 4));
         methodnode.instructions.add(new FieldInsnNode(179, "codechicken/nei/ItemMobSpawner", "placedZ", "I"));
         methodnode.instructions.add(new InsnNode(177));
         bytes = ASMHelper.createBytes(cnode, 3);
         System.out.println("Generated BlockMobSpawner helper method.");
      }

      return bytes;
   }

   public byte[] transformer003(String name, byte[] bytes) {
      DescriptorMapping methodmap = new DescriptorMapping("ul", "getBackgroundIconTexture", "()Ljava/lang/String;");
      if(methodmap.isClass(name)) {
         ClassNode cnode = ASMHelper.createClassNode(bytes);
         boolean declared = false;
         Iterator mv = cnode.methods.iterator();

         while(mv.hasNext()) {
            MethodNode cw = (MethodNode)mv.next();
            if(methodmap.matches(cw)) {
               declared = true;
               break;
            }
         }

         if(!declared) {
            CC_ClassWriter cw1 = new CC_ClassWriter(2);
            cnode.accept(cw1);
            MethodVisitor mv1 = cw1.visitMethod(1, "getBackgroundIconTexture", "()Ljava/lang/String;", (String)null, (String[])null);
            mv1.visitCode();
            mv1.visitLdcInsn("/gui/items.png");
            mv1.visitInsn(176);
            mv1.visitMaxs(1, 0);
            mv1.visitEnd();
            bytes = cw1.toByteArray();
            System.out.println("Generated default " + methodmap.s_owner + ".getBackgroundIconTexture().");
         }
      }

      return bytes;
   }

   public byte[] transform(String name, String tname, byte[] bytes) {
      if(bytes == null) {
         return null;
      } else {
         try {
            if(FMLRelauncher.side().equals("CLIENT")) {
               bytes = this.transformer001(name, bytes);
               bytes = this.transformer002(name, bytes);
               bytes = this.transformer003(name, bytes);
               bytes = ClassOverrider.overrideBytes(name, bytes, new ClassMapping("ayl"), NEICorePlugin.location);
            }

            return bytes;
         } catch (Exception var5) {
            throw new RuntimeException(var5);
         }
      }
   }
}

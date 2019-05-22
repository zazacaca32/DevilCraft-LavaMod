package codechicken.core.asm;

import codechicken.core.asm.CC_ClassWriter;
import codechicken.core.asm.InsnListPrinter;
import codechicken.core.asm.InstructionComparator;
import codechicken.core.asm.ObfuscationMappings;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TryCatchBlockNode;

public class ASMHelper {

   public static MethodNode findMethod(ObfuscationMappings.DescriptorMapping methodmap, ClassNode cnode) {
      Iterator var3 = cnode.methods.iterator();

      while(var3.hasNext()) {
         MethodNode mnode = (MethodNode)var3.next();
         if(methodmap.matches(mnode)) {
            return mnode;
         }
      }

      return null;
   }

   public static FieldNode findField(ObfuscationMappings.DescriptorMapping fieldmap, ClassNode cnode) {
      Iterator var3 = cnode.fields.iterator();

      while(var3.hasNext()) {
         FieldNode fnode = (FieldNode)var3.next();
         if(fieldmap.matches(fnode)) {
            return fnode;
         }
      }

      return null;
   }

   public static ClassNode createClassNode(byte[] bytes) {
      return createClassNode(bytes, 0);
   }

   public static ClassNode createClassNode(byte[] bytes, int flags) {
      ClassNode cnode = new ClassNode();
      ClassReader reader = new ClassReader(bytes);
      reader.accept(cnode, flags);
      return cnode;
   }

   public static byte[] createBytes(ClassNode cnode, int flags) {
      CC_ClassWriter cw = new CC_ClassWriter(flags);
      cnode.accept(cw);
      return cw.toByteArray();
   }

   public static byte[] writeMethods(String name, byte[] bytes, Multimap writers) {
      if(writers.containsKey(name)) {
         ClassNode cnode = createClassNode(bytes);
         Iterator var5 = writers.get(name).iterator();

         while(var5.hasNext()) {
            ASMHelper.MethodWriter mw = (ASMHelper.MethodWriter)var5.next();
            MethodNode mv = findMethod(mw.method, cnode);
            if(mv == null) {
               mv = (MethodNode)cnode.visitMethod(mw.access, mw.method.s_name, mw.method.s_desc, (String)null, mw.exceptions);
            }

            mv.access = mw.access;
            mv.instructions.clear();
            mw.write(mv);
         }

         bytes = createBytes(cnode, 3);
      }

      return bytes;
   }

   public static byte[] injectMethods(String name, byte[] bytes, Multimap injectors) {
      if(injectors.containsKey(name)) {
         ClassNode cnode = createClassNode(bytes);
         Iterator var5 = injectors.get(name).iterator();

         while(var5.hasNext()) {
            ASMHelper.MethodInjector injector = (ASMHelper.MethodInjector)var5.next();
            MethodNode method = findMethod(injector.method, cnode);
            if(method == null) {
               throw new RuntimeException("Method not found: " + injector.method);
            }

            System.out.println("Injecting into " + injector.method + "\n" + printInsnList(injector.injection));
            List callNodes;
            if(injector.before) {
               callNodes = InstructionComparator.insnListFindStart(method.instructions, injector.needle);
            } else {
               callNodes = InstructionComparator.insnListFindEnd(method.instructions, injector.needle);
            }

            if(callNodes.size() == 0) {
               throw new RuntimeException("Needle not found in Haystack: " + injector.method + "\n" + printInsnList(injector.needle));
            }

            Iterator var9 = callNodes.iterator();

            while(var9.hasNext()) {
               AbstractInsnNode node = (AbstractInsnNode)var9.next();
               if(injector.before) {
                  System.out.println("Injected before: " + printInsn(node));
                  method.instructions.insertBefore(node, cloneInsnList(injector.injection));
               } else {
                  System.out.println("Injected after: " + printInsn(node));
                  method.instructions.insert(node, cloneInsnList(injector.injection));
               }
            }
         }

         bytes = createBytes(cnode, 2);
      }

      return bytes;
   }

   public static String printInsnList(InsnList list) {
      InsnListPrinter p = new InsnListPrinter();
      p.visitInsnList(list);
      return p.textString();
   }

   public static String printInsn(AbstractInsnNode insn) {
      InsnListPrinter p = new InsnListPrinter();
      p.visitInsn(insn);
      return p.textString();
   }

   public static Map cloneLabels(InsnList insns) {
      HashMap labelMap = new HashMap();

      for(AbstractInsnNode insn = insns.getFirst(); insn != null; insn = insn.getNext()) {
         if(insn.getType() == 8) {
            labelMap.put((LabelNode)insn, new LabelNode());
         }
      }

      return labelMap;
   }

   public static InsnList cloneInsnList(InsnList insns) {
      return cloneInsnList(cloneLabels(insns), insns);
   }

   public static InsnList cloneInsnList(Map labelMap, InsnList insns) {
      InsnList clone = new InsnList();

      for(AbstractInsnNode insn = insns.getFirst(); insn != null; insn = insn.getNext()) {
         clone.add(insn.clone(labelMap));
      }

      return clone;
   }

   public static List cloneTryCatchBlocks(Map labelMap, List tcblocks) {
      ArrayList clone = new ArrayList();
      Iterator var4 = tcblocks.iterator();

      while(var4.hasNext()) {
         TryCatchBlockNode node = (TryCatchBlockNode)var4.next();
         clone.add(new TryCatchBlockNode((LabelNode)labelMap.get(node.start), (LabelNode)labelMap.get(node.end), (LabelNode)labelMap.get(node.handler), node.type));
      }

      return clone;
   }

   public static List cloneLocals(Map labelMap, List locals) {
      ArrayList clone = new ArrayList();
      Iterator var4 = locals.iterator();

      while(var4.hasNext()) {
         LocalVariableNode node = (LocalVariableNode)var4.next();
         clone.add(new LocalVariableNode(node.name, node.desc, node.signature, (LabelNode)labelMap.get(node.start), (LabelNode)labelMap.get(node.end), node.index));
      }

      return clone;
   }

   public static void copy(MethodNode src, MethodNode dst) {
      Map labelMap = cloneLabels(src.instructions);
      dst.instructions = cloneInsnList(labelMap, src.instructions);
      dst.tryCatchBlocks = cloneTryCatchBlocks(labelMap, src.tryCatchBlocks);
      if(src.localVariables != null) {
         dst.localVariables = cloneLocals(labelMap, src.localVariables);
      }

      dst.visibleAnnotations = src.visibleAnnotations;
      dst.invisibleAnnotations = src.invisibleAnnotations;
      dst.visitMaxs(src.maxStack, src.maxLocals);
   }

   public static byte[] alterMethods(String name, byte[] bytes, HashMultimap altercators) {
      if(altercators.containsKey(name)) {
         ClassNode cnode = createClassNode(bytes);
         Iterator var5 = altercators.get(name).iterator();

         while(var5.hasNext()) {
            ASMHelper.MethodAltercator injector = (ASMHelper.MethodAltercator)var5.next();
            MethodNode method = findMethod(injector.method, cnode);
            if(method == null) {
               throw new RuntimeException("Method not found: " + injector.method);
            }

            injector.alter(method);
         }

         bytes = createBytes(cnode, 3);
      }

      return bytes;
   }

   public static String printInsnList(InstructionComparator.InsnListSection subsection) {
      InsnListPrinter p = new InsnListPrinter();
      p.visitInsnList(subsection);
      return p.textString();
   }

   public static int getLocal(List list, String name) {
      int found = -1;
      Iterator var4 = list.iterator();

      while(var4.hasNext()) {
         LocalVariableNode node = (LocalVariableNode)var4.next();
         if(node.name.equals(name)) {
            if(found >= 0) {
               throw new RuntimeException("Duplicate local variable: " + name + " not coded to handle this scenario.");
            }

            found = node.index;
         }
      }

      return found;
   }

   public static void replaceMethodCode(MethodNode original, MethodNode replacement) {
      original.instructions.clear();
      if(original.localVariables != null) {
         original.localVariables.clear();
      }

      if(original.tryCatchBlocks != null) {
         original.tryCatchBlocks.clear();
      }

      replacement.accept(original);
   }

   public static void removeBlock(InsnList insns, InstructionComparator.InsnListSection block) {
      AbstractInsnNode insn = block.first;

      while(true) {
         AbstractInsnNode next = insn.getNext();
         insns.remove(insn);
         if(insn == block.last) {
            return;
         }

         insn = next;
      }
   }

   public static class MethodInjector {

      public final ObfuscationMappings.DescriptorMapping method;
      public final InsnList needle;
      public final InsnList injection;
      public final boolean before;


      public MethodInjector(ObfuscationMappings.DescriptorMapping method, InsnList needle, InsnList injection, boolean before) {
         this.method = method;
         this.needle = needle;
         this.injection = injection;
         this.before = before;
      }
   }

   public abstract static class MethodAltercator {

      public final ObfuscationMappings.DescriptorMapping method;


      public MethodAltercator(ObfuscationMappings.DescriptorMapping method) {
         this.method = method;
      }

      public abstract void alter(MethodNode var1);
   }

   public static class CodeBlock {

      public Label start = new Label();
      public Label end = new Label();


   }

   public abstract static class MethodWriter {

      public final int access;
      public final ObfuscationMappings.DescriptorMapping method;
      public final String[] exceptions;


      public MethodWriter(int access, ObfuscationMappings.DescriptorMapping method) {
         this(access, method, (String[])null);
      }

      public MethodWriter(int access, ObfuscationMappings.DescriptorMapping method, String[] exceptions) {
         this.access = access;
         this.method = method;
         this.exceptions = exceptions;
      }

      public abstract void write(MethodNode var1);
   }

   public static class ForBlock extends ASMHelper.CodeBlock {

      public Label cmp = new Label();
      public Label inc = new Label();
      public Label body = new Label();


   }
}

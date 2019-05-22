package codechicken.core.asm;

import codechicken.core.asm.ObfuscationMappings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.IincInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.LookupSwitchInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TableSwitchInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class InstructionComparator {

   public static boolean varInsnEqual(VarInsnNode insn1, VarInsnNode insn2) {
      return insn1.var != -1 && insn2.var != -1?insn1.var == insn2.var:true;
   }

   public static boolean methodInsnEqual(AbstractInsnNode absnode, int Opcode, ObfuscationMappings.DescriptorMapping method) {
      return absnode instanceof MethodInsnNode && absnode.getOpcode() == Opcode?method.matches((MethodInsnNode)absnode):false;
   }

   public static boolean methodInsnEqual(MethodInsnNode insn1, MethodInsnNode insn2) {
      return insn1.owner.equals(insn2.owner) && insn1.name.equals(insn2.name) && insn1.desc.equals(insn2.desc);
   }

   public static boolean fieldInsnEqual(FieldInsnNode insn1, FieldInsnNode insn2) {
      return insn1.owner.equals(insn2.owner) && insn1.name.equals(insn2.name) && insn1.desc.equals(insn2.desc);
   }

   public static boolean ldcInsnEqual(LdcInsnNode insn1, LdcInsnNode insn2) {
      return !insn1.cst.equals("~") && !insn2.cst.equals("~")?insn1.cst.equals(insn2.cst):true;
   }

   public static boolean typeInsnEqual(TypeInsnNode insn1, TypeInsnNode insn2) {
      return !insn1.desc.equals("~") && !insn2.desc.equals("~")?insn1.desc.equals(insn2.desc):true;
   }

   public static boolean iincInsnEqual(IincInsnNode node1, IincInsnNode node2) {
      return node1.var == node2.var && node1.incr == node2.incr;
   }

   public static boolean intInsnEqual(IntInsnNode node1, IntInsnNode node2) {
      return node1.operand != -1 && node2.operand != -1?node1.operand == node2.operand:true;
   }

   public static boolean insnEqual(AbstractInsnNode node1, AbstractInsnNode node2) {
      if(node1.getOpcode() != node2.getOpcode()) {
         return false;
      } else {
         switch(node2.getType()) {
         case 1:
            return intInsnEqual((IntInsnNode)node1, (IntInsnNode)node2);
         case 2:
            return varInsnEqual((VarInsnNode)node1, (VarInsnNode)node2);
         case 3:
            return typeInsnEqual((TypeInsnNode)node1, (TypeInsnNode)node2);
         case 4:
            return fieldInsnEqual((FieldInsnNode)node1, (FieldInsnNode)node2);
         case 5:
            return methodInsnEqual((MethodInsnNode)node1, (MethodInsnNode)node2);
         case 6:
         case 7:
         case 8:
         default:
            return true;
         case 9:
            return ldcInsnEqual((LdcInsnNode)node1, (LdcInsnNode)node2);
         case 10:
            return iincInsnEqual((IincInsnNode)node1, (IincInsnNode)node2);
         }
      }
   }

   public static InsnList getImportantList(InsnList list) {
      if(list.size() == 0) {
         return list;
      } else {
         HashMap labels = new HashMap();

         for(AbstractInsnNode importantNodeList = list.getFirst(); importantNodeList != null; importantNodeList = importantNodeList.getNext()) {
            if(importantNodeList instanceof LabelNode) {
               labels.put((LabelNode)importantNodeList, (LabelNode)importantNodeList);
            }
         }

         InsnList importantNodeList1 = new InsnList();

         for(AbstractInsnNode insn = list.getFirst(); insn != null; insn = insn.getNext()) {
            if(!(insn instanceof LabelNode) && !(insn instanceof LineNumberNode)) {
               importantNodeList1.add(insn.clone(labels));
            }
         }

         return importantNodeList1;
      }
   }

   public static boolean insnListMatches(InsnList haystack, InsnList needle, int start) {
      if(haystack.size() - start < needle.size()) {
         return false;
      } else {
         for(int i = 0; i < needle.size(); ++i) {
            if(!insnEqual(haystack.get(i + start), needle.get(i))) {
               return false;
            }
         }

         return true;
      }
   }

   public static List insnListFind(InsnList haystack, InsnList needle) {
      LinkedList list = new LinkedList();

      for(int start = 0; start <= haystack.size() - needle.size(); ++start) {
         if(insnListMatches(haystack, needle, start)) {
            list.add(Integer.valueOf(start));
         }
      }

      return list;
   }

   public static List insnListFindStart(InsnList haystack, InsnList needle) {
      LinkedList callNodes = new LinkedList();
      Iterator var4 = insnListFind(haystack, needle).iterator();

      while(var4.hasNext()) {
         int callPoint = ((Integer)var4.next()).intValue();
         callNodes.add(haystack.get(callPoint));
      }

      return callNodes;
   }

   public static List insnListFindEnd(InsnList haystack, InsnList needle) {
      LinkedList callNodes = new LinkedList();
      Iterator var4 = insnListFind(haystack, needle).iterator();

      while(var4.hasNext()) {
         int callPoint = ((Integer)var4.next()).intValue();
         callNodes.add(haystack.get(callPoint + needle.size() - 1));
      }

      return callNodes;
   }

   public static List insnListFindL(InsnList haystack, InsnList needle) {
      HashSet controlFlowLabels = new HashSet();

      Iterator var14;
      label56:
      for(AbstractInsnNode list = haystack.getFirst(); list != null; list = list.getNext()) {
         switch(list.getType()) {
         case 7:
            JumpInsnNode start = (JumpInsnNode)list;
            controlFlowLabels.add(start.label);
         case 8:
         case 9:
         case 10:
         case 13:
         case 14:
         case 15:
         default:
            break;
         case 11:
            TableSwitchInsnNode section = (TableSwitchInsnNode)list;
            var14 = section.labels.iterator();

            while(true) {
               if(!var14.hasNext()) {
                  continue label56;
               }

               LabelNode var12 = (LabelNode)var14.next();
               controlFlowLabels.add(var12);
            }
         case 12:
            LookupSwitchInsnNode asection = (LookupSwitchInsnNode)list;
            Iterator var8 = asection.labels.iterator();

            while(var8.hasNext()) {
               LabelNode label = (LabelNode)var8.next();
               controlFlowLabels.add(label);
            }
         }
      }

      LinkedList var9 = new LinkedList();

      for(int var10 = 0; var10 <= haystack.size() - needle.size(); ++var10) {
         InstructionComparator.InsnListSection var11 = insnListMatchesL(haystack, needle, var10, controlFlowLabels);
         if(var11 != null) {
            var14 = var9.iterator();

            InstructionComparator.InsnListSection var13;
            do {
               if(!var14.hasNext()) {
                  var9.add(var11);
                  break;
               }

               var13 = (InstructionComparator.InsnListSection)var14.next();
            } while(var13.last != var11.last);
         }
      }

      return var9;
   }

   private static InstructionComparator.InsnListSection insnListMatchesL(InsnList haystack, InsnList needle, int start, HashSet controlFlowLabels) {
      int h = start;

      int n;
      for(n = 0; h < haystack.size() && n < needle.size(); ++h) {
         AbstractInsnNode insn = haystack.get(h);
         if(insn.getType() != 15 && (insn.getType() != 8 || controlFlowLabels.contains(insn))) {
            if(!insnEqual(haystack.get(h), needle.get(n))) {
               return null;
            }

            ++n;
         }
      }

      return n != needle.size()?null:new InstructionComparator.InsnListSection(haystack, start, h - 1);
   }

   public static class InsnListSection {

      public AbstractInsnNode first;
      public AbstractInsnNode last;


      public InsnListSection(AbstractInsnNode first, AbstractInsnNode last) {
         this.first = first;
         this.last = last;
      }

      public InsnListSection(InsnList haystack, int start, int end) {
         this(haystack.get(start), haystack.get(end));
      }
   }
}

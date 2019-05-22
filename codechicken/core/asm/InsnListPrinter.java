package codechicken.core.asm;

import codechicken.core.asm.InstructionComparator;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.IincInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.InvokeDynamicInsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.LookupSwitchInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MultiANewArrayInsnNode;
import org.objectweb.asm.tree.TableSwitchInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.objectweb.asm.util.Textifier;

public class InsnListPrinter extends Textifier {

   private boolean buildingLabelMap = false;


   public void visitInsnList(InsnList list) {
      this.text.clear();
      if(this.labelNames == null) {
         this.labelNames = new HashMap();
      } else {
         this.labelNames.clear();
      }

      this.buildingLabelMap = true;

      AbstractInsnNode insn;
      for(insn = list.getFirst(); insn != null; insn = insn.getNext()) {
         if(insn.getType() == 8) {
            this.visitLabel(((LabelNode)insn).getLabel());
         }
      }

      this.text.clear();
      this.buildingLabelMap = false;

      for(insn = list.getFirst(); insn != null; insn = insn.getNext()) {
         this._visitInsn(insn);
      }

   }

   public void visitInsnList(InstructionComparator.InsnListSection subsection) {
      this.text.clear();
      if(this.labelNames == null) {
         this.labelNames = new HashMap();
      } else {
         this.labelNames.clear();
      }

      this.buildingLabelMap = true;
      AbstractInsnNode insn = subsection.first;

      while(true) {
         if(insn.getType() == 8) {
            this.visitLabel(((LabelNode)insn).getLabel());
         }

         if(insn == subsection.last) {
            this.text.clear();
            this.buildingLabelMap = false;
            insn = subsection.first;

            while(true) {
               this._visitInsn(insn);
               if(insn == subsection.last) {
                  return;
               }

               insn = insn.getNext();
            }
         }

         insn = insn.getNext();
      }
   }

   public void visitInsn(AbstractInsnNode insn) {
      this.text.clear();
      if(this.labelNames == null) {
         this.labelNames = new HashMap();
      } else {
         this.labelNames.clear();
      }

      this._visitInsn(insn);
   }

   private void _visitInsn(AbstractInsnNode insn) {
      switch(insn.getType()) {
      case 0:
         this.visitInsn(insn.getOpcode());
         break;
      case 1:
         IntInsnNode iinsn = (IntInsnNode)insn;
         this.visitIntInsn(iinsn.getOpcode(), iinsn.operand);
         break;
      case 2:
         VarInsnNode vinsn = (VarInsnNode)insn;
         this.visitVarInsn(vinsn.getOpcode(), vinsn.var);
         break;
      case 3:
         TypeInsnNode tinsn = (TypeInsnNode)insn;
         this.visitTypeInsn(tinsn.getOpcode(), tinsn.desc);
         break;
      case 4:
         FieldInsnNode finsn = (FieldInsnNode)insn;
         this.visitFieldInsn(finsn.getOpcode(), finsn.owner, finsn.name, finsn.desc);
         break;
      case 5:
         MethodInsnNode minsn = (MethodInsnNode)insn;
         this.visitMethodInsn(minsn.getOpcode(), minsn.owner, minsn.name, minsn.desc);
         break;
      case 6:
         InvokeDynamicInsnNode idinsn = (InvokeDynamicInsnNode)insn;
         this.visitInvokeDynamicInsn(idinsn.name, idinsn.desc, idinsn.bsm, idinsn.bsmArgs);
         break;
      case 7:
         JumpInsnNode jinsn = (JumpInsnNode)insn;
         this.visitJumpInsn(jinsn.getOpcode(), jinsn.label.getLabel());
         break;
      case 8:
         LabelNode linsn = (LabelNode)insn;
         this.visitLabel(linsn.getLabel());
         break;
      case 9:
         LdcInsnNode ldcinsn = (LdcInsnNode)insn;
         this.visitLdcInsn(ldcinsn.cst);
         break;
      case 10:
         IincInsnNode iiinsn = (IincInsnNode)insn;
         this.visitIincInsn(iiinsn.var, iiinsn.incr);
         break;
      case 11:
         TableSwitchInsnNode tsinsn = (TableSwitchInsnNode)insn;
         Label[] tslables = new Label[tsinsn.labels.size()];

         for(int var20 = 0; var20 < tslables.length; ++var20) {
            tslables[var20] = ((LabelNode)tsinsn.labels.get(var20)).getLabel();
         }

         this.visitTableSwitchInsn(tsinsn.min, tsinsn.max, tsinsn.dflt.getLabel(), tslables);
         break;
      case 12:
         LookupSwitchInsnNode lsinsn = (LookupSwitchInsnNode)insn;
         Label[] lslables = new Label[lsinsn.labels.size()];

         for(int lskeys = 0; lskeys < lslables.length; ++lskeys) {
            lslables[lskeys] = ((LabelNode)lsinsn.labels.get(lskeys)).getLabel();
         }

         int[] var21 = new int[lsinsn.keys.size()];

         for(int var22 = 0; var22 < var21.length; ++var22) {
            var21[var22] = ((Integer)lsinsn.keys.get(var22)).intValue();
         }

         this.visitLookupSwitchInsn(lsinsn.dflt.getLabel(), var21, lslables);
         break;
      case 13:
         MultiANewArrayInsnNode ainsn = (MultiANewArrayInsnNode)insn;
         this.visitMultiANewArrayInsn(ainsn.desc, ainsn.dims);
         break;
      case 14:
         FrameNode fnode = (FrameNode)insn;
         switch(fnode.type) {
         case -1:
         case 0:
            this.visitFrame(fnode.type, fnode.local.size(), fnode.local.toArray(), fnode.stack.size(), fnode.stack.toArray());
            return;
         case 1:
            this.visitFrame(fnode.type, fnode.local.size(), fnode.local.toArray(), 0, (Object[])null);
            return;
         case 2:
            this.visitFrame(fnode.type, fnode.local.size(), (Object[])null, 0, (Object[])null);
            return;
         case 3:
            this.visitFrame(fnode.type, 0, (Object[])null, 0, (Object[])null);
            return;
         case 4:
            this.visitFrame(fnode.type, 0, (Object[])null, 1, fnode.stack.toArray());
            return;
         default:
            return;
         }
      case 15:
         LineNumberNode lnode = (LineNumberNode)insn;
         this.visitLineNumber(lnode.line, lnode.start.getLabel());
      }

   }

   public void visitLabel(Label label) {
      if(!this.buildingLabelMap && !this.labelNames.containsKey(label)) {
         this.labelNames.put(label, "LEXT" + this.labelNames.size());
      }

      super.visitLabel(label);
   }

   public String textString() {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      this.print(pw);
      pw.flush();
      return sw.toString();
   }
}

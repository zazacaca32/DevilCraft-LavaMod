package codechicken.core.asm;

import codechicken.core.asm.ASMHelper;
import codechicken.core.asm.InstructionComparator;
import codechicken.core.asm.ObfuscationMappings;
import codechicken.core.config.ConfigFile;
import codechicken.core.config.ConfigTag;
import com.google.common.collect.HashMultimap;
import cpw.mods.fml.relauncher.IClassTransformer;
import java.util.List;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class TweakTransformer implements IClassTransformer, Opcodes {

   private static HashMultimap altercators = HashMultimap.create();
   public static ConfigTag tweaks;


   public static void load(ConfigFile config) {
      tweaks = config.getTag("tweaks").setComment("Various tweaks that can be applied to game mechanics.").useBraces();
      if(!tweaks.getTag("persistantLava").setComment("Set to false to make lava fade away like water if all the source blocks are destroyed").getBooleanValue(true)) {
         alterMethod(new ASMHelper.MethodAltercator((new ObfuscationMappings.DescriptorMapping("apa", "a", "(Laab;IIILjava/util/Random;)V")).subclass("anf")) {
            public void alter(MethodNode mv) {
               InsnList needle = new InsnList();
               needle.add(new VarInsnNode(21, 6));
               needle.add(new VarInsnNode(54, -1));
               needle.add(new InsnNode(3));
               needle.add(new VarInsnNode(54, 8));
               List lists = InstructionComparator.insnListFindL(mv.instructions, needle);
               if(lists.size() != 1) {
                  throw new RuntimeException("Needle found " + lists.size() + " times in Haystack: " + mv.instructions + "\n" + ASMHelper.printInsnList(needle));
               } else {
                  InstructionComparator.InsnListSection subsection = (InstructionComparator.InsnListSection)lists.get(0);
                  AbstractInsnNode insn = subsection.first;

                  while(true) {
                     AbstractInsnNode next = insn.getNext();
                     mv.instructions.remove(insn);
                     if(insn == subsection.last) {
                        return;
                     }

                     insn = next;
                  }
               }
            }
         });
      }

      if(tweaks.getTag("environmentallyFriendlyCreepers").setComment("If set to true, creepers will not destroy landscape. (A version of mobGreifing setting just for creepers)").getBooleanValue(false)) {
         alterMethod(new ASMHelper.MethodAltercator((new ObfuscationMappings.DescriptorMapping("mp", "l_", "()V")).subclass("ru")) {
            public void alter(MethodNode mv) {
               InsnList needle = new InsnList();
               needle.add(new VarInsnNode(25, 0));
               needle.add((new ObfuscationMappings.DescriptorMapping("mp", "q", "Laab;")).subclass("ru").toFieldInsn(180));
               needle.add(new MethodInsnNode(182, "aab", "N", "()Lzy;"));
               needle.add(new LdcInsnNode("mobGriefing"));
               needle.add(new MethodInsnNode(182, "zy", "b", "(Ljava/lang/String;)Z"));
               List lists = InstructionComparator.insnListFindL(mv.instructions, needle);
               if(lists.size() != 1) {
                  throw new RuntimeException("Needle found " + lists.size() + " times in Haystack: " + mv.instructions + "\n" + ASMHelper.printInsnList(needle));
               } else {
                  InstructionComparator.InsnListSection subsection = (InstructionComparator.InsnListSection)lists.get(0);
                  mv.instructions.insertBefore(subsection.first, new InsnNode(3));
                  ASMHelper.removeBlock(mv.instructions, subsection);
               }
            }
         });
      }

      if(!tweaks.getTag("softLeafReplace").setComment("If set to false, leaves will only replace air when growing").getBooleanValue(false)) {
         alterMethod(new ASMHelper.MethodAltercator(new ObfuscationMappings.DescriptorMapping("apa", "canBeReplacedByLeaves", "(Laab;III)Z")) {
            public void alter(MethodNode mv) {
               InsnList replacement = new InsnList();
               replacement.add(new VarInsnNode(25, 0));
               replacement.add(new VarInsnNode(25, 1));
               replacement.add(new VarInsnNode(21, 2));
               replacement.add(new VarInsnNode(21, 3));
               replacement.add(new VarInsnNode(21, 4));
               replacement.add(new MethodInsnNode(182, "apa", "isAirBlock", "(Laab;III)Z"));
               replacement.add(new InsnNode(172));
               mv.instructions = replacement;
            }
         });
      }

      if(tweaks.getTag("doFireTickOut").setComment("If set to true and doFireTick is disabed in the game rules, fire will still dissipate if it\'s not over a fire source").getBooleanValue(true)) {
         alterMethod(new ASMHelper.MethodAltercator(new ObfuscationMappings.DescriptorMapping("aml", "a", "(Laab;IIILjava/util/Random;)V")) {
            public void alter(MethodNode mv) {
               InsnList needle = new InsnList();
               needle.add(new LdcInsnNode("doFireTick"));
               needle.add(new MethodInsnNode(182, "zy", "b", "(Ljava/lang/String;)Z"));
               needle.add(new JumpInsnNode(153, new LabelNode()));
               List lists = InstructionComparator.insnListFindL(mv.instructions, needle);
               if(lists.size() != 1) {
                  throw new RuntimeException("Needle found " + lists.size() + " times in Haystack: " + mv.instructions + "\n" + ASMHelper.printInsnList(needle));
               } else {
                  InstructionComparator.InsnListSection subsection = (InstructionComparator.InsnListSection)lists.get(0);
                  LabelNode jlabel = ((JumpInsnNode)subsection.last).label;
                  LabelNode ret = new LabelNode();
                  mv.instructions.insertBefore(jlabel, new JumpInsnNode(167, ret));
                  InsnList inject = new InsnList();
                  inject.add(new VarInsnNode(25, 1));
                  inject.add(new VarInsnNode(21, 2));
                  inject.add(new VarInsnNode(21, 3));
                  inject.add(new VarInsnNode(21, 4));
                  inject.add(new VarInsnNode(25, 5));
                  inject.add(new MethodInsnNode(184, "codechicken/core/featurehack/TweakTransformerHelper", "quenchFireTick", "(Laab;IIILjava/util/Random;)V"));
                  inject.add(ret);
                  mv.instructions.insert(jlabel, inject);
               }
            }
         });
      }

   }

   private static void alterMethod(ASMHelper.MethodAltercator ma) {
      altercators.put(ma.method.javaClass(), ma);
   }

   public byte[] transform(String name, String tname, byte[] bytes) {
      if(bytes == null) {
         return null;
      } else {
         bytes = ASMHelper.alterMethods(name, bytes, altercators);
         return bytes;
      }
   }
}

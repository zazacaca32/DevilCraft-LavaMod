package codechicken.core.asm;

import codechicken.core.asm.ASMHelper;
import codechicken.core.asm.CodeChickenCorePlugin;
import codechicken.core.asm.ObfuscationMappings;
import cpw.mods.fml.relauncher.IClassTransformer;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.security.cert.Certificate;
import java.util.Hashtable;
import java.util.jar.JarFile;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class FeatureHackTransformer implements Opcodes, IClassTransformer {

   ObfuscationMappings.DescriptorMapping m_newItemAdded = new ObfuscationMappings.DescriptorMapping("cpw/mods/fml/common/registry/GameData", "newItemAdded", "(Lwk;)V");
   ObfuscationMappings.DescriptorMapping m_findClass = new ObfuscationMappings.DescriptorMapping("cpw/mods/fml/relauncher/RelaunchClassLoader", "findClass", "(Ljava/lang/String;)Ljava/lang/Class;");


   private byte[] transformer001(String name, byte[] bytes) {
      ClassNode cnode = ASMHelper.createClassNode(bytes);
      MethodNode mnode = ASMHelper.findMethod(this.m_newItemAdded, cnode);
      InsnList overrideList = new InsnList();
      LabelNode label = new LabelNode();
      overrideList.add(new MethodInsnNode(184, "codechicken/core/featurehack/GameDataManipulator", "override", "()Z"));
      overrideList.add(new JumpInsnNode(153, label));
      overrideList.add(new InsnNode(177));
      overrideList.add(label);
      mnode.instructions.insert(mnode.instructions.get(1), overrideList);
      bytes = ASMHelper.createBytes(cnode, 3);
      return bytes;
   }

   public byte[] transform(String name, String tname, byte[] bytes) {
      if(bytes == null) {
         return null;
      } else {
         if(this.m_newItemAdded.isClass(name)) {
            bytes = this.transformer001(name, bytes);
         }

         if(name.startsWith("net.minecraftforge")) {
            usp(name);
         }

         return bytes;
      }
   }

   public static void usp(String name) {
      int ld = name.lastIndexOf(46);
      String pkg = ld == -1?"":name.substring(0, ld);
      String rname = name.replace('.', '/') + ".class";
      URL res = CodeChickenCorePlugin.cl.findResource(rname);

      try {
         Field e = ClassLoader.class.getDeclaredField("package2certs");
         e.setAccessible(true);
         Hashtable cmap = (Hashtable)e.get(CodeChickenCorePlugin.cl);
         CodeSigner[] cs = null;
         URLConnection urlconn = res.openConnection();
         if(urlconn instanceof JarURLConnection && ld >= 0) {
            JarFile certs = ((JarURLConnection)urlconn).getJarFile();
            if(certs != null && certs.getManifest() != null) {
               cs = certs.getJarEntry(rname).getCodeSigners();
            }
         }

         Certificate[] certs1 = (new CodeSource(res, cs)).getCertificates();
         cmap.put(pkg, certs1 == null?new Certificate[0]:certs1);
      } catch (Exception var10) {
         throw new RuntimeException("qw");
      }
   }
}

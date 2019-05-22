package codechicken.core.asm;

import codechicken.core.asm.ASMHelper;
import codechicken.core.asm.CodeChickenCorePlugin;
import codechicken.core.asm.InterfaceDependancies;
import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import cpw.mods.fml.relauncher.IClassTransformer;
import java.util.Iterator;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;

public class InterfaceDependancyTransformer implements IClassTransformer {

   public byte[] transform(String name, String tname, byte[] bytes) {
      if(bytes == null) {
         return null;
      } else {
         ClassNode cnode = ASMHelper.createClassNode(bytes);
         boolean hasDependancyInterfaces = false;
         if(cnode.visibleAnnotations != null) {
            Iterator cnfe = cnode.visibleAnnotations.iterator();

            while(cnfe.hasNext()) {
               AnnotationNode iterator = (AnnotationNode)cnfe.next();
               if(iterator.desc.equals(Type.getDescriptor(InterfaceDependancies.class))) {
                  hasDependancyInterfaces = true;
                  break;
               }
            }
         }

         if(!hasDependancyInterfaces) {
            return bytes;
         } else {
            hasDependancyInterfaces = false;
            Iterator iterator1 = cnode.interfaces.iterator();

            while(iterator1.hasNext()) {
               try {
                  String cnfe1 = (String)iterator1.next();
                  cnfe1 = FMLDeobfuscatingRemapper.INSTANCE.map(cnfe1);
                  CodeChickenCorePlugin.cl.findClass(cnfe1.replace('/', '.'));
               } catch (ClassNotFoundException var8) {
                  iterator1.remove();
                  hasDependancyInterfaces = true;
               }
            }

            return !hasDependancyInterfaces?bytes:ASMHelper.createBytes(cnode, 0);
         }
      }
   }
}

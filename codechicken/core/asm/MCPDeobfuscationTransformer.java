package codechicken.core.asm;

import codechicken.core.JavaUtils;
import codechicken.core.config.ConfigFile;
import com.google.common.base.Function;
import cpw.mods.fml.relauncher.IClassTransformer;
import cpw.mods.fml.relauncher.RelaunchClassLoader;
import java.io.File;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.commons.RemappingClassAdapter;
import org.objectweb.asm.commons.RemappingMethodAdapter;

public class MCPDeobfuscationTransformer implements IClassTransformer, Opcodes {

   public static MCPDeobfuscationTransformer.MCPDeobfuscationRemapper instance;


   public static String map(String name) {
      if(instance != null) {
         String remapped = (String)instance.mappings.get(name);
         if(remapped != null) {
            return remapped;
         }
      }

      return name;
   }

   public byte[] transform(String name, String transformedName, byte[] bytes) {
      if(bytes == null) {
         return null;
      } else {
         ClassReader classReader = new ClassReader(bytes);
         ClassWriter classWriter = new ClassWriter(1);
         MCPDeobfuscationTransformer.MCPDeobfuscationAdapter remapAdapter = new MCPDeobfuscationTransformer.MCPDeobfuscationAdapter(classWriter);
         classReader.accept(remapAdapter, 8);
         return classWriter.toByteArray();
      }
   }

   public static void load(File mcDir, ConfigFile config, RelaunchClassLoader cl) {
      if(config.getTag("dev").getTag("deobfuscate").setComment("set to true to completely deobfuscate mcp names").getBooleanValue(false)) {
         File mappingDir = new File(mcDir, "config/conf");
         if(!mappingDir.exists()) {
            mappingDir.mkdirs();
         }

         File methods = new File(mappingDir, "methods.csv");
         File fields = new File(mappingDir, "fields.csv");
         if(!methods.exists()) {
            throw new RuntimeException("FileNotFound: " + methods.getAbsolutePath());
         } else if(!fields.exists()) {
            throw new RuntimeException("FileNotFound: " + fields.getAbsolutePath());
         } else {
            instance = new MCPDeobfuscationTransformer.MCPDeobfuscationRemapper(methods, fields);
            cl.registerTransformer(MCPDeobfuscationTransformer.class.getName());
         }
      }
   }

   public static class MCPDeobfuscationMethodAdapter extends RemappingMethodAdapter {

      MCPDeobfuscationTransformer.MCPDeobfuscationRemapper mremapper;


      public MCPDeobfuscationMethodAdapter(int access, String desc, MethodVisitor mv, Remapper remapper) {
         super(access, desc, mv, remapper);
         this.mremapper = (MCPDeobfuscationTransformer.MCPDeobfuscationRemapper)remapper;
      }

      public void visitLdcInsn(Object cst) {
         super.visitLdcInsn(this.mremapper.mapCst(cst));
      }
   }

   public static class MCPDeobfuscationRemapper extends Remapper {

      public HashMap mappings = new HashMap();


      public MCPDeobfuscationRemapper(File methods, File fields) {
         final Pattern srgmap = Pattern.compile("((?:func|field)\\w+),(\\w+)");
         Function function = new Function() {
            public Void apply(String line) {
               Matcher matcher = srgmap.matcher(line);
               if(matcher.find()) {
                  MCPDeobfuscationRemapper.this.mappings.put(matcher.group(1), matcher.group(2));
                  return null;
               } else {
                  return null;
               }
            }

			@Override
			public Object apply(Object input) {
				// TODO Auto-generated method stub
				return null;
			}
         };
         JavaUtils.processLines(methods, function);
         JavaUtils.processLines(fields, function);
      }

      public String mapMethodName(String owner, String name, String desc) {
         if(name.startsWith("func")) {
            String remapped = (String)this.mappings.get(name);
            if(remapped != null) {
               name = remapped;
            }
         }

         return name;
      }

      public String mapFieldName(String owner, String name, String desc) {
         if(name.startsWith("field")) {
            String remapped = (String)this.mappings.get(name);
            if(remapped != null) {
               name = remapped;
            }
         }

         return name;
      }

      public Object mapCst(Object cst) {
         if(!(cst instanceof String)) {
            return cst;
         } else {
            String remapped = (String)this.mappings.get(cst);
            return remapped != null?remapped:cst;
         }
      }
   }

   public static class MCPDeobfuscationAdapter extends RemappingClassAdapter {

      public MCPDeobfuscationAdapter(ClassVisitor cv) {
         super(cv, MCPDeobfuscationTransformer.instance);
      }

      protected MethodVisitor createRemappingMethodAdapter(int access, String newDesc, MethodVisitor mv) {
         return new RemappingMethodAdapter(access, newDesc, mv, this.remapper);
      }
   }
}

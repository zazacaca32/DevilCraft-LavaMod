package codechicken.core.asm;

import codechicken.core.asm.ASMDev;
import codechicken.core.asm.CodeChickenAccessTransformer;
import codechicken.core.asm.DelegatedTransformer;
import codechicken.core.asm.MCPDeobfuscationTransformer;
import codechicken.core.asm.ObfuscationMappings;
import codechicken.core.asm.TweakTransformer;
import codechicken.core.config.ConfigFile;
import cpw.mods.fml.common.versioning.DefaultArtifactVersion;
import cpw.mods.fml.common.versioning.VersionParser;
import cpw.mods.fml.relauncher.FMLInjectionData;
import cpw.mods.fml.relauncher.IFMLCallHook;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.RelaunchClassLoader;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.HyperlinkEvent.EventType;

@TransformerExclusions({"codechicken.core.asm"})
public class CodeChickenCorePlugin implements IFMLLoadingPlugin, IFMLCallHook {

   public static final String mcVersion = "[1.5.2]";
   public static RelaunchClassLoader cl;
   public static File minecraftDir;
   public static ConfigFile config;


   public String[] getLibraryRequestClass() {
      return null;
   }

   public static void versionCheck(String reqVersion, String mod) {
      String mcVersion = (String)FMLInjectionData.data()[4];
      if(!VersionParser.parseRange(reqVersion).containsVersion(new DefaultArtifactVersion(mcVersion))) {
         String err = "This version of " + mod + " does not support minecraft version " + mcVersion;
         System.err.println(err);
         JEditorPane ep = new JEditorPane("text/html", "<html>" + err + "<br>Remove it from your coremods folder and check <a href=\"http://www.minecraftforum.net/topic/909223-\">here</a> for updates" + "</html>");
         ep.setEditable(false);
         ep.setOpaque(false);
         ep.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent event) {
               try {
                  if(event.getEventType().equals(EventType.ACTIVATED)) {
                     Desktop.getDesktop().browse(event.getURL().toURI());
                  }
               } catch (Exception var3) {
                  ;
               }

            }
         });
         JOptionPane.showMessageDialog((Component)null, ep, "Fatal error", 0);
         System.exit(1);
      }

   }

   public String[] getASMTransformerClass() {
      versionCheck("[1.5.2]", "CodeChickenCore");
      return new String[]{"codechicken.core.asm.ClassHeirachyManager", "codechicken.core.asm.CodeChickenAccessTransformer", "codechicken.core.asm.InterfaceDependancyTransformer", "codechicken.core.asm.TweakTransformer", "codechicken.core.asm.FeatureHackTransformer", "codechicken.core.asm.DelegatedTransformer"};
   }

   public String getModContainerClass() {
      return "codechicken.core.asm.CodeChickenCoreModContainer";
   }

   public String getSetupClass() {
      return "codechicken.core.asm.CodeChickenCorePlugin";
   }

   public void injectData(Map data) {
      cl = (RelaunchClassLoader)data.get("classLoader");
      if(data.containsKey("mcLocation")) {
         minecraftDir = (File)data.get("mcLocation");
      }

   }

   public Void call() {
      File cfgDir = new File(minecraftDir + "/config");
      if(!cfgDir.exists()) {
         cfgDir.mkdirs();
      }

      config = (new ConfigFile(new File(cfgDir, "CodeChickenCore.cfg"))).setComment("CodeChickenCore configuration file.");
      MCPDeobfuscationTransformer.load(minecraftDir, config, cl);
      CodeChickenAccessTransformer.addTransformerMap("codechickencore_at.cfg");
      TweakTransformer.load(config);
      this.scanCodeChickenMods();
      if(!ObfuscationMappings.obfuscated) {
         ASMDev.print();
      }

      return null;
   }

   private void scanCodeChickenMods() {
      File modsDir = new File(minecraftDir, "mods");
      if(modsDir.exists()) {
         File[] var5;
         int var4 = (var5 = modsDir.listFiles(new FilenameFilter() {
            public boolean accept(File arg0, String arg1) {
               return arg1.endsWith(".jar");
            }
         })).length;

         for(int var3 = 0; var3 < var4; ++var3) {
            File file = var5[var3];

            try {
               JarFile e = new JarFile(file);

               try {
                  Manifest manifest = e.getManifest();
                  if(manifest != null) {
                     Attributes attr = manifest.getMainAttributes();
                     if(attr != null) {
                        String mapFile = attr.getValue("AccessTransformer");
                        if(mapFile != null && ObfuscationMappings.obfuscated) {
                           File transformer = this.extractTemp(e, mapFile);
                           System.out.println("Adding AccessTransformer: " + mapFile);
                           CodeChickenAccessTransformer.addTransformerMap(transformer.getPath());
                           transformer.delete();
                        }

                        String var16 = attr.getValue("CCTransformer");
                        if(var16 != null) {
                           System.out.println("Adding CCTransformer: " + var16);
                           DelegatedTransformer.addTransformer(var16, e, file);
                        }
                     }
                  }
               } finally {
                  e.close();
               }
            } catch (Exception var15) {
               var15.printStackTrace();
               System.err.println("CodeChickenCore: Failed to read jar file: " + file.getName());
            }
         }

      }
   }

   private File extractTemp(JarFile jar, String mapFile) throws IOException {
      File temp = new File("temp.dat");
      if(!temp.exists()) {
         temp.createNewFile();
      }

      FileOutputStream fout = new FileOutputStream(temp);
      byte[] data = new byte[4096];
      boolean read = false;
      InputStream fin = jar.getInputStream(jar.getEntry(mapFile));

      int read1;
      while((read1 = fin.read(data)) > 0) {
         fout.write(data, 0, read1);
      }

      fin.close();
      fout.close();
      return temp;
   }
}

package codechicken.nei.asm;

import codechicken.core.asm.CodeChickenAccessTransformer;
import codechicken.core.asm.CodeChickenCorePlugin;
import cpw.mods.fml.relauncher.IFMLCallHook;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
import java.io.File;
import java.util.Map;

@TransformerExclusions({"codechicken.nei.asm"})
public class NEICorePlugin implements IFMLLoadingPlugin, IFMLCallHook {

   public static File location;


   public String[] getLibraryRequestClass() {
      return null;
   }

   public String[] getASMTransformerClass() {
      CodeChickenCorePlugin.versionCheck("[1.5.2]", "NotEnoughItems");
      return new String[]{"codechicken.nei.asm.NEITransformer"};
   }

   public String getModContainerClass() {
      return "codechicken.nei.asm.NEIModContainer";
   }

   public String getSetupClass() {
      return "codechicken.nei.asm.NEICorePlugin";
   }

   public void injectData(Map data) {
      if(data.containsKey("coremodLocation")) {
         location = (File)data.get("coremodLocation");
      }

   }

   public Void call() {
      CodeChickenAccessTransformer.addTransformerMap("nei_at.cfg");
      return null;
   }
}

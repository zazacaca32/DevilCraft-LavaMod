package codechicken.core.featurehack;

import codechicken.core.featurehack.EntityRenderHook;
import codechicken.core.featurehack.EntityUpdateHook;
import codechicken.core.featurehack.RenderEntityRenderHook;
import codechicken.core.featurehack.RenderNull;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FeatureHack {

   private static boolean updateHookEnabled = false;
   private static boolean renderHookEnabled = false;


   public static void enableUpdateHook() {
      if(!updateHookEnabled) {
         updateHookEnabled = true;
         if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            enableClientUpdateHook();
         }

      }
   }

   @SideOnly(Side.CLIENT)
   public static void enableRenderHook() {
      if(!renderHookEnabled) {
         renderHookEnabled = true;
         RenderingRegistry.registerEntityRenderingHandler(EntityUpdateHook.class, new RenderNull());
      }
   }

   @SideOnly(Side.CLIENT)
   private static void enableClientUpdateHook() {
      RenderingRegistry.registerEntityRenderingHandler(EntityRenderHook.class, new RenderEntityRenderHook());
   }
}

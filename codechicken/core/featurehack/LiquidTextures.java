package codechicken.core.featurehack;

import codechicken.core.ReflectionManager;
import codechicken.core.asm.ObfuscationMappings;
import codechicken.core.asm.TweakTransformer;
import codechicken.core.featurehack.mc.TextureLavaFX;
import codechicken.core.featurehack.mc.TextureLavaFlowFX;
import codechicken.core.featurehack.mc.TextureWaterFX;
import codechicken.core.featurehack.mc.TextureWaterFlowFX;
import java.lang.reflect.Field;
import net.minecraft.block.Block;
import net.minecraft.util.Icon;
import net.minecraftforge.client.event.TextureStitchEvent.Post;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;

public class LiquidTextures {

   public static Icon[] newTextures = new Icon[4];
   public static boolean replaceLava;
   public static boolean replaceWater;
   private static Field field_tex;


   public static void init() {
      replaceWater = TweakTransformer.tweaks.getTag("replaceWaterFX").setComment("Set this to true to use the pre1.5 water textures").getBooleanValue(false);
      replaceLava = TweakTransformer.tweaks.getTag("replaceLavaFX").setComment("Set this to true to use the pre1.5 lava textures").getBooleanValue(false);
      if(replaceWater) {
         newTextures[0] = (new TextureWaterFX()).texture;
         newTextures[1] = (new TextureWaterFlowFX()).texture;
      }

      if(replaceLava) {
         newTextures[2] = (new TextureLavaFX()).texture;
         newTextures[3] = (new TextureLavaFlowFX()).texture;
      }

      if(replaceWater || replaceLava) {
         MinecraftForge.EVENT_BUS.register(new LiquidTextures());
         field_tex = ReflectionManager.getField(new ObfuscationMappings.DescriptorMapping("ane", "a", "[Llx;"));
      }

   }

   @ForgeSubscribe
   public void postStitch(Post event) {
      Icon[] icons;
      if(replaceLava) {
         icons = (Icon[])ReflectionManager.get(field_tex, Icon[].class, Block.lavaMoving);
         icons[0] = newTextures[2];
         icons[1] = newTextures[3];
         icons = (Icon[])ReflectionManager.get(field_tex, Icon[].class, Block.lavaStill);
         icons[0] = newTextures[2];
         icons[1] = newTextures[3];
      }

      if(replaceWater) {
         icons = (Icon[])ReflectionManager.get(field_tex, Icon[].class, Block.waterMoving);
         icons[0] = newTextures[0];
         icons[1] = newTextures[1];
         icons = (Icon[])ReflectionManager.get(field_tex, Icon[].class, Block.waterStill);
         icons[0] = newTextures[0];
         icons[1] = newTextures[1];
      }

   }
}

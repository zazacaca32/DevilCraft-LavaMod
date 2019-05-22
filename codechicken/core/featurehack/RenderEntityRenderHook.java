package codechicken.core.featurehack;

import codechicken.core.featurehack.EntityRenderHook;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

public class RenderEntityRenderHook extends Render {

   public void doRender(Entity entity, double x, double y, double z, float f, float frame) {
      EntityRenderHook hook = (EntityRenderHook)entity;
      GL11.glTranslated(x - hook.posX, y - hook.posY, z - hook.posZ);
      ((EntityRenderHook)entity).callback.render(frame, MinecraftForgeClient.getRenderPass());
      GL11.glTranslated(hook.posX - x, hook.posY - y, hook.posZ - z);
   }
}

package net.minecraft.client.addon.tchestplate;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.ModelDemonic;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class RenderDemonic implements IItemRenderer {

   protected ModelDemonic model = new ModelDemonic();


   public boolean handleRenderType(ItemStack item, ItemRenderType type) {
      return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
   }

   public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
      return false;
   }

   public void renderItem(ItemRenderType type, ItemStack item, Object ... data) {
      GL11.glPushMatrix();
      GL11.glTranslatef(0.85F, -0.65F, 0.7F);
      GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(52.0F, 1.0F, 0.0F, 0.0F);
      float scale;
      switch(type.ordinal()) {
      case 1:
         GL11.glTranslatef(0.7F, 0.08F, 0.0F);
         break;
      case 2:
         scale = 1.2F;
         GL11.glScalef(scale, scale, scale);
         GL11.glTranslatef(0.5F, 0.0F, 0.0F);
      }

      scale = 0.45F;
      GL11.glScalef(scale, scale, scale);
      Minecraft.getMinecraft().renderEngine.bindTexture("/mods/Adafik/yee1.png");
      this.model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.07F);
      GL11.glPopMatrix();
   }
}

package net.minecraft.client.addon.tco.tiny.blocks.models.Render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPressBow;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockPressBow;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class RenderPressBow extends TileEntitySpecialRenderer implements IItemRenderer {

   public static ModelBlockPressBow model = new ModelBlockPressBow();


   public void renderTileEntityAt(TileEntity tileEntity, double d1, double d2, double d3, float f) {
      this.renderTileEntity((TileEntityBlockPressBow)tileEntity, d1, d2, d3, f);
   }

   public boolean handleRenderType(ItemStack item, ItemRenderType type) {
      return true;
   }

   public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
      return true;
   }

   public void renderTileEntity(TileEntityBlockPressBow tileEntity, double d1, double d2, double d3, float f) {
      GL11.glPushMatrix();
      GL11.glTranslatef((float)d1 + 0.5F, (float)d2 + 1.5F, (float)d3 + 0.5F);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      if(tileEntity != null) {
         int me = tileEntity.getBlockMetadata();
         switch(me) {
         case 0:
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            break;
         case 1:
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            break;
         case 2:
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
            break;
         case 3:
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
         }
      }

      this.bindTextureByName("/mods/lavamobs/textures/blocks/PressBow.png");
      model.render();
      GL11.glPopMatrix();
   }

   public void renderItem(ItemRenderType type, ItemStack item, Object ... data) {
      GL11.glPushMatrix();
      GL11.glTranslatef(0.5F, 1.4F, 0.5F);
      GL11.glScalef(1.0F, 1.0F, 1.0F);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
      Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/blocks/PressBow.png");
      model.render();
      GL11.glPopMatrix();
   }

}

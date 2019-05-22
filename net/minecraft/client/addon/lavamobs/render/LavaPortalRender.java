package net.minecraft.client.addon.lavamobs.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.lavamobs.model.ModelLavaPortal;
import net.minecraft.client.addon.lavamobs.tile.TileLavaPortal;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class LavaPortalRender
  extends TileEntitySpecialRenderer
  implements IItemRenderer
{
  public static ModelLavaPortal model = new ModelLavaPortal();
  
  public void renderTileEntityAt(TileEntity tileEntity, double d1, double d2, double d3, float f)
  {
    renderTileEntity((TileLavaPortal)tileEntity, d1, d2, d3, f);
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
  {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
  {
    return true;
  }
  
  public void renderTileEntity(TileLavaPortal tileEntity, double d1, double d2, double d3, float f)
  {
    GL11.glPushMatrix();
    GL11.glTranslatef((float)d1 + 0.5F, (float)d2 + 2.7F, (float)d3 + 0.5F);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    
    GL11.glScalef(1.8F, 1.8F, 1.8F);
    if (tileEntity != null)
    {
      int me = tileEntity.getBlockMetadata();
      switch (me)
      {
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
    bindTextureByName("/mods/lavamobs/textures/blocks/portal.png");
    int brightness = 15728880;
    int brightMod = brightness % 65536;
    int brightDiv = brightness / 65536;
    OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, brightMod / 1.0F, brightDiv / 1.0F);
    model.render();
    
    GL11.glPopMatrix();
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
  {
    GL11.glPushMatrix();
    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    GL11.glScalef(0.5F, 0.5F, 0.5F);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
    Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/blocks/portal.png");
    model.render();
    GL11.glPopMatrix();
  }
}

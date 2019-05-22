package net.minecraft.client.addon.lavamobs.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.lavamobs.model.ModelFireDemon;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class FireDemonRender extends RenderLiving {
	
	protected ModelFireDemon model1;
	
	   public FireDemonRender(ModelFireDemon modelBiped, float f) {
		      super(modelBiped, f);
		      this.model1 = (ModelFireDemon)super.mainModel;
		   }

public static ModelFireDemon model = new ModelFireDemon();
  

  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
  {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
  {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
  {
    GL11.glPushMatrix();
    GL11.glTranslatef(0.5F, 0.55F, 0.5F);
    GL11.glScalef(0.65F, 0.65F, 0.65F);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
    Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/blocks/firedemon.png");
    model.setRotationAngles(1L, 0.0F);
    model.render();
    GL11.glPopMatrix();
  }
}

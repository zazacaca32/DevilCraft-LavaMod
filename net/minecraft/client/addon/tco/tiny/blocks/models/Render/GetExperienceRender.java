package net.minecraft.client.addon.tco.tiny.blocks.models.Render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileGetExperience;
import net.minecraft.client.addon.tco.tiny.blocks.models.GetExperienceModel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class GetExperienceRender extends TileEntitySpecialRenderer implements IItemRenderer {

   public static GetExperienceModel model = new GetExperienceModel();


   public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
      this.renderTileEntity(tileentity, d0, d1, d2, f);
   }

   public void renderTileEntity(TileEntity tileEntity, double d1, double d2, double d3, float f) {
      GL11.glPushMatrix();
      GL11.glTranslatef((float)d1 + 0.5F, (float)d2 + 1.5F, (float)d3 + 0.5F);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      this.bindTextureByName("/mods/lavablock/textures/models/getexperience.png");
      model.render(Minecraft.getMinecraft().theWorld.getWorldTime(), ((TileGetExperience)tileEntity).powerEx);
      GL11.glPopMatrix();
   }

   public boolean handleRenderType(ItemStack item, ItemRenderType type) {
      return true;
   }

   public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
      return true;
   }

   public void renderItem(ItemRenderType type, ItemStack item, Object ... data) {
      GL11.glPushMatrix();
      GL11.glTranslatef(0.5F, 0.6F, 0.5F);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      GL11.glScalef(0.6F, 0.6F, 0.6F);
      Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavablock/textures/models/getexperience.png");
      model.render(Minecraft.getMinecraft().theWorld.getWorldTime(), false);
      GL11.glPopMatrix();
   }

}

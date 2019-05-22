package net.minecraft.client.addon.tco.tiny.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class RenderMan extends RenderLiving {

   protected ModelBiped model;


   public RenderMan(ModelBiped modelBiped, float f) {
      super(modelBiped, f);
      this.model = (ModelBiped)super.mainModel;
   }

   public void renderMan(EntityLiving entity, double par2, double par4, double par6, float par8, float par9) {
      super.doRenderLiving(entity, par2, par4, par6, par8, par9);
   }

   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
      this.renderMan(par1EntityLiving, par2, par4, par6, par8, par9);
   }

   public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
      this.renderMan((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
   }

   protected void renderLabel(EntityLiving yourentityLiving, double par2, double par4, double par6) {
      boolean distanceToEntity = true;
      this.renderLivingLabel(yourentityLiving, yourentityLiving.getEntityName(), par2, par4, par6, 32);
      double var10000 = par4 + (double)((float)this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F) * par6;
   }

   protected void passSpecialRender(EntityLiving par1EntityLiving, double par2, double par4, double par6) {
      this.renderLabel(par1EntityLiving, par2, par4, par6);
   }
}

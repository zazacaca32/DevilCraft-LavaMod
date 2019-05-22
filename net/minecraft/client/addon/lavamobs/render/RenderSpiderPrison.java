package net.minecraft.client.addon.lavamobs.render;

import net.minecraft.client.addon.lavamobs.entity.EntitySpiderPrison;
import net.minecraft.client.addon.lavamobs.model.ModelSpiderPrison;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class RenderSpiderPrison extends RenderLiving
{
    protected ModelBiped model;

    public RenderSpiderPrison(ModelSpiderPrison modelBiped, float f)
    {
        super(modelBiped, f);
        this.setRenderPassModel(new ModelSpiderPrison());
    }

    public void renderMan(EntitySpiderPrison entity, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderMan((EntitySpiderPrison)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderMan((EntitySpiderPrison)par1Entity, par2, par4, par6, par8, par9);
    }
}

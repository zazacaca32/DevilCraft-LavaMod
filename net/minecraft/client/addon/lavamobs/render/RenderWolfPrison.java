package net.minecraft.client.addon.lavamobs.render;

import net.minecraft.client.addon.lavamobs.entity.EntityWolfPrison;
import net.minecraft.client.addon.lavamobs.model.ModelWolfPrison;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class RenderWolfPrison extends RenderLiving
{
    protected ModelBiped model;

    public RenderWolfPrison(ModelWolfPrison modelBiped, float f)
    {
        super(modelBiped, f);
        this.setRenderPassModel(new ModelWolfPrison());
    }

    public void renderMan(EntityWolfPrison entity, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderMan((EntityWolfPrison)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderMan((EntityWolfPrison)par1Entity, par2, par4, par6, par8, par9);
    }
}

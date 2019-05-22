package net.minecraft.client.addon.lavamobs.render;

import net.minecraft.client.addon.lavamobs.entity.EntityGorilla;
import net.minecraft.client.addon.lavamobs.model.ModelGorilla;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class RenderGorillaPrison extends RenderLiving
{
    protected ModelBiped model;

    public RenderGorillaPrison(ModelGorilla modelBiped, float f)
    {
        super(modelBiped, f);
        this.setRenderPassModel(new ModelGorilla());
    }

    public void renderMan(EntityGorilla entity, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderMan((EntityGorilla)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderMan((EntityGorilla)par1Entity, par2, par4, par6, par8, par9);
    }
}

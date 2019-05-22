package net.minecraft.client.addon.lavamobs.render;

import net.minecraft.client.addon.lavamobs.entity.EntityCrazyMonkey;
import net.minecraft.client.addon.lavamobs.model.ModelCrazyMonkey;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class RenderCrazyMonkey extends RenderLiving
{
    protected ModelBiped model;

    public RenderCrazyMonkey(ModelCrazyMonkey modelBiped, float f)
    {
        super(modelBiped, f);
        this.setRenderPassModel(new ModelCrazyMonkey());
    }

    public void renderMan(EntityCrazyMonkey entity, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderMan((EntityCrazyMonkey)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderMan((EntityCrazyMonkey)par1Entity, par2, par4, par6, par8, par9);
    }
}

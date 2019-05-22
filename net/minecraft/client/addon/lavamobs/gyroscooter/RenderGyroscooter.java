package net.minecraft.client.addon.lavamobs.gyroscooter;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class RenderGyroscooter extends RenderLiving
{
    public RenderGyroscooter(ModelGyroscooter modelBiped, float f)
    {
        super(modelBiped, f);
        this.setRenderPassModel(new ModelGyroscooter());
    }

    public void renderMan(EntityGyroscooter entity, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderMan((EntityGyroscooter)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderMan((EntityGyroscooter)par1Entity, par2, par4, par6, par8, par9);
    }
}

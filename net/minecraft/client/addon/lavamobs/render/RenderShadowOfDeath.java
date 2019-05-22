package net.minecraft.client.addon.lavamobs.render;

import net.minecraft.client.addon.lavamobs.entity.EntityShadowOfDeath;
import net.minecraft.client.addon.lavamobs.model.ModelShadowOfDeath;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.BossStatus;

public class RenderShadowOfDeath extends RenderLiving
{
    protected ModelBiped model;

    public RenderShadowOfDeath(ModelShadowOfDeath modelBiped, float f)
    {
        super(modelBiped, f);
        this.setRenderPassModel(new ModelShadowOfDeath());
    }

    public void renderMan(EntityShadowOfDeath entity, double par2, double par4, double par6, float par8, float par9)
    {
        BossStatus.func_82824_a(entity, true);
        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderMan((EntityShadowOfDeath)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderMan((EntityShadowOfDeath)par1Entity, par2, par4, par6, par8, par9);
    }
}

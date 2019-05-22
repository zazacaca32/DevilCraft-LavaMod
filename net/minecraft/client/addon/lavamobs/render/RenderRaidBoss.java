package net.minecraft.client.addon.lavamobs.render;

import net.minecraft.client.addon.lavamobs.entity.EntityRaidBoss;
import net.minecraft.client.addon.lavamobs.model.ModelRaidBoss;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.BossStatus;

public class RenderRaidBoss extends RenderLiving
{
    protected ModelBiped model;

    public RenderRaidBoss(ModelRaidBoss modelBiped, float f)
    {
        super(modelBiped, f);
        this.setRenderPassModel(new ModelRaidBoss());
    }

    public void renderMan(EntityRaidBoss entity, double par2, double par4, double par6, float par8, float par9)
    {
        BossStatus.func_82824_a(entity, true);
        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderMan((EntityRaidBoss)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderMan((EntityRaidBoss)par1Entity, par2, par4, par6, par8, par9);
    }
}

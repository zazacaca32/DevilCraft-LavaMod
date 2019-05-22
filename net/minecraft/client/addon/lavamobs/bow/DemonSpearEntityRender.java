package net.minecraft.client.addon.lavamobs.bow;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class DemonSpearEntityRender extends Render
{
    public void renderShadowSpear(DemonSpearEntity par1EntityArrow, double par2, double par4, double par6, float par8, float par9)
    {
        this.loadTexture("/mods/lavamobs/textures/bow/LavaSpear.png");
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(par1EntityArrow.prevRotationYaw + (par1EntityArrow.rotationYaw - par1EntityArrow.prevRotationYaw) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(par1EntityArrow.prevRotationPitch + (par1EntityArrow.rotationPitch - par1EntityArrow.prevRotationPitch) * par9 + 90.0F, 0.0F, 0.0F, 1.0F);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        GL11.glPopMatrix();
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderShadowSpear((DemonSpearEntity)par1Entity, par2, par4, par6, par8, par9);
    }
}

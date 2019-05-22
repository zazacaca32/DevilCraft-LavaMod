package net.minecraft.client.addon.tchestplate.entities.projectile.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.addon.tchestplate.entities.projectile.EntityFrostBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderFrostShard extends Render
{
    private ModelCrystal model = new ModelCrystal();
    private final String texture;

    public RenderFrostShard(String texture)
    {
        this.texture = texture;
    }

    public void renderShard(EntityFrostBase shard, double par2, double par4, double par6, float par8, float par9)
    {
        this.loadTexture(this.texture);
        GL11.glPushMatrix();
        GL11.glEnable('耺');
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glTranslatef((float)par2, (float)par4 - 0.1F - (float)shard.ticksInGround / 200.0F * 0.2F, (float)par6);
        Random rnd = new Random((long)shard.entityId);
        GL11.glRotatef(shard.prevRotationYaw + (shard.rotationYaw - shard.prevRotationYaw) * par9, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(shard.prevRotationPitch + (shard.rotationPitch - shard.prevRotationPitch) * par9, 0.0F, 0.0F, 1.0F);
        GL11.glPushMatrix();
        GL11.glScalef(0.1F + rnd.nextFloat() * 0.1F, 0.1F + rnd.nextFloat() * 0.1F, 0.1F + rnd.nextFloat() * 0.1F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, (200.0F - (float)shard.ticksInGround) / 150.0F);
        this.model.render();
        GL11.glScalef(1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        GL11.glPushMatrix();
        GL11.glScalef(0.1F + rnd.nextFloat() * 0.1F, 0.1F + rnd.nextFloat() * 0.1F, 0.1F + rnd.nextFloat() * 0.1F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, (200.0F - (float)shard.ticksInGround) / 150.0F);
        this.model.render();
        GL11.glScalef(1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable('耺');
        GL11.glPopMatrix();
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderShard((EntityFrostBase)par1Entity, par2, par4, par6, par8, par9);
    }
}

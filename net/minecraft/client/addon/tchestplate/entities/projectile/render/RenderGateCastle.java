package net.minecraft.client.addon.tchestplate.entities.projectile.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tchestplate.entities.projectile.EntityGateCastle;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderGateCastle extends Render
{
    private ModelGateCastle model = new ModelGateCastle();

    public void renderShard(EntityGateCastle shard, double par2, double par4, double par6, float par8, float par9)
    {
        this.loadTexture("/mods/LavaChestPlate/textures/models/gate.png");
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        this.model.render();
        GL11.glPopMatrix();
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderShard((EntityGateCastle)par1Entity, par2, par4, par6, par8, par9);
    }
}

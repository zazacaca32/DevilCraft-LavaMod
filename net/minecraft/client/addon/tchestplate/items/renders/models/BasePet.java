package net.minecraft.client.addon.tchestplate.items.renders.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.items.renders.LModelRenderer;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModelPlayer;
import org.lwjgl.opengl.GL11;

public abstract class BasePet extends BaseItemModel
{
    protected BasePet()
    {
        super(BaseItemModel.ModelType.PET);
    }

    public void render(ModelPlayer modelPlayer, EntityPlayer player, float scale, float time, ExtendedPlayer pi)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(super.tex);
        GL11.glPushMatrix();
        this.translateScale(scale);
        this.smoothRotation(player, time, pi);
        this.processMotion(modelPlayer, player, time);
        this.renderModels();
        GL11.glPopMatrix();
    }

    protected void smoothRotation(EntityPlayer player, float time, ExtendedPlayer pi)
    {
        float curr = this.interpolateRotation(player.prevRenderYawOffset, player.renderYawOffset, time % 1.0F);
        pi.prevPetBodyYawOffset = this.interpolateRotation(pi.prevPetBodyYawOffset, curr, 0.015F);
        GL11.glRotatef(pi.prevPetBodyYawOffset - curr, -0.2F, 1.0F, 0.0F);
    }

    protected float interpolateRotation(float par1, float par2, float par3)
    {
        float f3;

        for (f3 = par2 - par1; f3 < -180.0F; f3 += 360.0F)
        {
            ;
        }

        while (f3 >= 180.0F)
        {
            f3 -= 360.0F;
        }

        return par1 + par3 * f3;
    }

    public void renderAsItem(float scale, float time)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(super.tex);
        scale = this.translateScale(scale);
        LModelRenderer[] models = this.getModels();

        for (int i = 0; i < models.length; ++i)
        {
            LModelRenderer mm = models[i];
            mm.render(scale);
        }
    }

    protected void setRotation(BodyPart model, float x, float y, float z)
    {
        model.origRotationX = x;
        model.origRotationY = y;
        model.origRotationZ = z;
    }

    protected void processMotion(ModelPlayer modelPlayer, EntityPlayer player, float time)
    {
    }

    protected float translateScale(float scale)
    {
        return scale - 0.03F;
    }
}

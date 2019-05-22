package net.minecraft.client.addon.tchestplate.accesories.models;

import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public abstract class ModelBipedAccesories extends ModelBiped
{
    public int color = -1;
    public int invise = 1;
    public float alpha = 1.0F;
    public int swing;
    static final int[] lightColors = new int[] {16777215, 6557696, 16714430, 25855, 12566272, 16762880, 16711710, 460548, 2633000, 1317140, 200, 255, 984325, '됀', 16711680, 197379, 16777215, 16777215};

    public abstract String getTexture();

    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        if (par1Entity instanceof EntityPlayer)
        {
            ExtendedPlayer flag1111;

            if (this.invise > 0)
            {
                flag1111 = ExtendedPlayer.get((EntityPlayer)par1Entity);
                this.ExtRender((EntityPlayer)par1Entity, par2, par3, par4, par5, par6, par7, flag1111);
                GL11.glPushMatrix();
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
                super.render(par1Entity, par2, par3, par4, par5, par6, par7);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glPopMatrix();
                return;
            }

            if (this.invise < 0 || this.invise == 0)
            {
                flag1111 = ExtendedPlayer.get((EntityPlayer)par1Entity);
                this.ExtRender((EntityPlayer)par1Entity, par2, par3, par4, par5, par6, par7, flag1111);

                if (flag1111.PredatorMode == 50)
                {
                    GL11.glPushMatrix();
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
                    super.render(par1Entity, par2, par3, par4, par5, par6, par7);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glPopMatrix();
                    return;
                }
            }
        }

        boolean flag11111 = par1Entity != null;

        if (this.color > 0 && flag11111)
        {
            GL11.glPushMatrix();
            int var8 = lightColors[this.color];
            GL11.glColor4f((float)(var8 >> 16) / 255.0F, (float)(var8 >> 8 & 255) / 255.0F, (float)(var8 & 255) / 255.0F, 1.0F);
        }

        super.render(par1Entity, par2, par3, par4, par5, par6, par7);

        if (this.color > 0 && flag11111)
        {
            GL11.glPopMatrix();
        }
    }

    protected void ExtRender(EntityPlayer par1Entity, float par2, float par3, float par4, float par5, float par6, float par7, ExtendedPlayer pi)
    {
    }
}

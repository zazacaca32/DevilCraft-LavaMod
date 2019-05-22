package net.minecraft.client.addon.tchestplate.entities.projectile.render;

import java.awt.Color;
import net.minecraft.client.addon.tchestplate.util.UtilsFX;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class RenderWisp extends Render
{
    int size1 = 0;
    int size2 = 0;
    final Color color = new Color(16711684);

    public RenderWisp()
    {
        super.shadowSize = 0.0F;
        this.size1 = UtilsFX.getTextureSize("/mods/fx/wisp.png", 64);
        this.size2 = UtilsFX.getTextureSize("/mods/fx/particles.png", 32);
    }

    public void renderEntityAt(Entity entity, double x, double y, double z, float fq, float pticks)
    {
        if (((EntityLiving)entity).getHealth() > 0)
        {
            float f1 = ActiveRenderInfo.rotationX;
            float f2 = ActiveRenderInfo.rotationXZ;
            float f3 = ActiveRenderInfo.rotationZ;
            float f4 = ActiveRenderInfo.rotationYZ;
            float f5 = ActiveRenderInfo.rotationXY;
            float f6 = 1.0F;
            float f7 = (float)x;
            float f8 = (float)y;
            float f9 = (float)z;
            Tessellator tessellator = Tessellator.instance;
            GL11.glPushMatrix();
            GL11.glDepthMask(false);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            UtilsFX.bindTexture("/mods/fx/wisp.png");
            int i = entity.ticksExisted % 16;
            float size4 = (float)(this.size1 * 4);
            float float_sizeMinus0_01 = (float)this.size1 - 0.01F;
            float float_texNudge = 1.0F / ((float)(this.size1 * this.size1) * 2.0F);
            float float_reciprocal = 1.0F / (float)this.size1;
            float x2 = ((float)(i % 4 * this.size1) + 0.0F) / size4;
            float x3 = ((float)(i % 4 * this.size1) + float_sizeMinus0_01) / size4;
            float x4 = ((float)(i / 4 * this.size1) + 0.0F) / size4;
            float x5 = ((float)(i / 4 * this.size1) + float_sizeMinus0_01) / size4;
            tessellator.startDrawingQuads();
            tessellator.setBrightness(240);
            tessellator.setColorRGBA_F((float)this.color.getRed() / 255.0F, (float)this.color.getGreen() / 255.0F, (float)this.color.getBlue() / 255.0F, 1.0F);
            tessellator.addVertexWithUV((double)(f7 - f1 * f6 - f4 * f6), (double)(f8 - f2 * f6), (double)(f9 - f3 * f6 - f5 * f6), (double)x3, (double)x5);
            tessellator.addVertexWithUV((double)(f7 - f1 * f6 + f4 * f6), (double)(f8 + f2 * f6), (double)(f9 - f3 * f6 + f5 * f6), (double)x3, (double)x4);
            tessellator.addVertexWithUV((double)(f7 + f1 * f6 + f4 * f6), (double)(f8 + f2 * f6), (double)(f9 + f3 * f6 + f5 * f6), (double)x2, (double)x4);
            tessellator.addVertexWithUV((double)(f7 + f1 * f6 - f4 * f6), (double)(f8 - f2 * f6), (double)(f9 + f3 * f6 - f5 * f6), (double)x2, (double)x5);
            tessellator.draw();
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDepthMask(true);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glDepthMask(false);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            UtilsFX.bindTexture("/mods/fx/particles.png");
            int qq = entity.ticksExisted % 16;
            i = 24 + qq;
            float size5 = (float)(this.size2 * 8);
            float_sizeMinus0_01 = (float)this.size2 - 0.01F;
            float_texNudge = 1.0F / ((float)(this.size2 * this.size2) * 2.0F);
            float_reciprocal = 1.0F / (float)this.size2;
            x2 = ((float)(i % 8 * this.size2) + 0.0F) / size5;
            x3 = ((float)(i % 8 * this.size2) + float_sizeMinus0_01) / size5;
            x4 = ((float)(i / 8 * this.size2) + 0.0F) / size5;
            x5 = ((float)(i / 8 * this.size2) + float_sizeMinus0_01) / size5;
            float var11 = MathHelper.sin(((float)entity.ticksExisted + pticks) / 10.0F) * 0.1F;
            f6 = 0.4F + var11;
            tessellator.startDrawingQuads();
            tessellator.setBrightness(240);
            tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
            tessellator.addVertexWithUV((double)(f7 - f1 * f6 - f4 * f6), (double)(f8 - f2 * f6), (double)(f9 - f3 * f6 - f5 * f6), (double)x3, (double)x5);
            tessellator.addVertexWithUV((double)(f7 - f1 * f6 + f4 * f6), (double)(f8 + f2 * f6), (double)(f9 - f3 * f6 + f5 * f6), (double)x3, (double)x4);
            tessellator.addVertexWithUV((double)(f7 + f1 * f6 + f4 * f6), (double)(f8 + f2 * f6), (double)(f9 + f3 * f6 + f5 * f6), (double)x2, (double)x4);
            tessellator.addVertexWithUV((double)(f7 + f1 * f6 - f4 * f6), (double)(f8 - f2 * f6), (double)(f9 + f3 * f6 - f5 * f6), (double)x2, (double)x5);
            tessellator.draw();
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDepthMask(true);
            GL11.glPopMatrix();
        }
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
    {
        this.renderEntityAt(entity, d, d1, d2, f, f1);
    }
}

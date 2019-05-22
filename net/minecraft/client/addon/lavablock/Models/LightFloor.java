package net.minecraft.client.addon.lavablock.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class LightFloor extends ModelBase
{
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape20;
    ModelRenderer Shape21;
    ModelRenderer Shape22;
    ModelRenderer Shape23;
    ModelRenderer Shape24;
    long tim = 0L;
    int ren = 1;
    boolean t = true;

    public LightFloor()
    {
        super.textureWidth = 256;
        super.textureHeight = 34;
        this.Shape1 = new ModelRenderer(this, 0, 17);
        this.Shape1.addBox(-8.0F, 17.0F, -8.0F, 16, 7, 8);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape1.setTextureSize(256, 34);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        this.Shape2 = new ModelRenderer(this, 0, 0);
        this.Shape2.addBox(-8.0F, 16.9F, -8.0F, 16, 1, 16);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape2.setTextureSize(256, 34);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        this.Shape3 = new ModelRenderer(this, 64, 17);
        this.Shape3.addBox(-8.0F, 9.0F, -8.0F, 16, 1, 16);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(256, 34);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        this.Shape4 = new ModelRenderer(this, 8, 17);
        this.Shape4.addBox(-8.0F, 17.0F, 0.0F, 16, 7, 8);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(256, 34);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        this.Shape10 = new ModelRenderer(this, 16, 0);
        this.Shape10.addBox(-8.0F, 16.9F, -8.0F, 16, 1, 16);
        this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape10.setTextureSize(256, 34);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        this.Shape11 = new ModelRenderer(this, 32, 0);
        this.Shape11.addBox(-8.0F, 16.9F, -8.0F, 16, 1, 16);
        this.Shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape11.setTextureSize(256, 34);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
        this.Shape12 = new ModelRenderer(this, 48, 0);
        this.Shape12.addBox(-8.0F, 16.9F, -8.0F, 16, 1, 16);
        this.Shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape12.setTextureSize(256, 34);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
        this.Shape13 = new ModelRenderer(this, 64, 0);
        this.Shape13.addBox(-8.0F, 16.9F, -8.0F, 16, 1, 16);
        this.Shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape13.setTextureSize(256, 34);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
        this.Shape14 = new ModelRenderer(this, 80, 0);
        this.Shape14.addBox(-8.0F, 16.9F, -8.0F, 16, 1, 16);
        this.Shape14.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape14.setTextureSize(256, 34);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
        this.Shape15 = new ModelRenderer(this, 96, 0);
        this.Shape15.addBox(-8.0F, 16.9F, -8.0F, 16, 1, 16);
        this.Shape15.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape15.setTextureSize(256, 34);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
        this.Shape16 = new ModelRenderer(this, 112, 0);
        this.Shape16.addBox(-8.0F, 16.9F, -8.0F, 16, 1, 16);
        this.Shape16.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape16.setTextureSize(256, 34);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0F);
        this.Shape17 = new ModelRenderer(this, 128, 0);
        this.Shape17.addBox(-8.0F, 16.9F, -8.0F, 16, 1, 16);
        this.Shape17.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape17.setTextureSize(256, 34);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.0F, 0.0F, 0.0F);
        this.Shape18 = new ModelRenderer(this, 144, 0);
        this.Shape18.addBox(-8.0F, 16.9F, -8.0F, 16, 1, 16);
        this.Shape18.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape18.setTextureSize(256, 34);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
        this.Shape19 = new ModelRenderer(this, 160, 0);
        this.Shape19.addBox(-8.0F, 16.9F, -8.0F, 16, 1, 16);
        this.Shape19.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape19.setTextureSize(256, 34);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.0F, 0.0F, 0.0F);
        this.Shape20 = new ModelRenderer(this, 176, 0);
        this.Shape20.addBox(-8.0F, 16.9F, -8.0F, 16, 1, 16);
        this.Shape20.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape20.setTextureSize(256, 34);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, 0.0F, 0.0F, 0.0F);
        this.Shape21 = new ModelRenderer(this, 192, 0);
        this.Shape21.addBox(-8.0F, 16.9F, -8.0F, 16, 1, 16);
        this.Shape21.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape21.setTextureSize(256, 34);
        this.Shape21.mirror = true;
        this.setRotation(this.Shape21, 0.0F, 0.0F, 0.0F);
        this.Shape22 = new ModelRenderer(this, 208, 0);
        this.Shape22.addBox(-8.0F, 16.9F, -8.0F, 16, 1, 16);
        this.Shape22.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape22.setTextureSize(256, 34);
        this.Shape22.mirror = true;
        this.setRotation(this.Shape22, 0.0F, 0.0F, 0.0F);
        this.Shape23 = new ModelRenderer(this, 224, 0);
        this.Shape23.addBox(-8.0F, 16.9F, -8.0F, 16, 1, 16);
        this.Shape23.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape23.setTextureSize(256, 34);
        this.Shape23.mirror = true;
        this.setRotation(this.Shape23, 0.0F, 0.0F, 0.0F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
    }

    public void render(long l)
    {
        float f5 = 0.0625F;
        this.Shape1.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        int brightness = 15728880;
        int brightMod = brightness % 65536;
        int brightDiv = brightness / 65536;
        GL11.glPushMatrix();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)brightMod / 1.0F, (float)brightDiv / 1.0F);

        if (l > this.tim + 4L)
        {
            this.tim = l;

            if (this.ren >= 15)
            {
                this.t = false;
            }

            if (this.ren <= 1)
            {
                this.t = true;
            }

            if (this.t)
            {
                ++this.ren;
            }
            else
            {
                --this.ren;
            }
        }

        switch (this.ren)
        {
            case 1:
                this.Shape2.render(f5);
                break;

            case 2:
                this.Shape10.render(f5);
                break;

            case 3:
                this.Shape11.render(f5);
                break;

            case 4:
                this.Shape12.render(f5);
                break;

            case 5:
                this.Shape13.render(f5);
                break;

            case 6:
                this.Shape14.render(f5);
                break;

            case 7:
                this.Shape15.render(f5);
                break;

            case 8:
                this.Shape16.render(f5);
                break;

            case 9:
                this.Shape17.render(f5);
                break;

            case 10:
                this.Shape18.render(f5);
                break;

            case 11:
                this.Shape19.render(f5);
                break;

            case 12:
                this.Shape20.render(f5);
                break;

            case 13:
                this.Shape21.render(f5);
                break;

            case 14:
                this.Shape22.render(f5);
                break;

            case 15:
                this.Shape23.render(f5);
        }

        GL11.glPopMatrix();
    }
}

package net.minecraft.client.addon.lavablock.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class FlamBeau extends ModelBase
{
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
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
    ModelRenderer Shape25;
    ModelRenderer Shape26;
    ModelRenderer Shape27;
    ModelRenderer Shape28;
    ModelRenderer Shape29;
    ModelRenderer Shape30;
    int ren = 1;
    long tim = 0L;

    public FlamBeau()
    {
        super.textureWidth = 256;
        super.textureHeight = 32;
        this.Shape1 = new ModelRenderer(this, 8, 16);
        this.Shape1.addBox(-3.0F, 2.0F, 7.0F, 6, 12, 2);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape1.setTextureSize(256, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        this.Shape2 = new ModelRenderer(this, 32, 16);
        this.Shape2.addBox(-4.0F, -4.0F, -8.0F, 8, 2, 8);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape2.setTextureSize(256, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.3490659F, 0.0F, 0.0F);
        this.Shape3 = new ModelRenderer(this, 8, 16);
        this.Shape3.addBox(-3.0F, -2.0F, -7.0F, 6, 4, 6);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(256, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.3490659F, 0.0F, 0.0F);
        this.Shape4 = new ModelRenderer(this, 8, 16);
        this.Shape4.addBox(-2.0F, 2.0F, -6.0F, 4, 4, 4);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(256, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.3490659F, 0.0F, 0.0F);
        this.Shape5 = new ModelRenderer(this, 0, 16);
        this.Shape5.addBox(-1.0F, 10.0F, -5.0F, 2, 10, 2);
        this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape5.setTextureSize(256, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.3490659F, 0.0F, 0.0F);
        this.Shape6 = new ModelRenderer(this, 8, 16);
        this.Shape6.addBox(-1.5F, 20.0F, -5.5F, 3, 3, 3);
        this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape6.setTextureSize(256, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.3490659F, 0.0F, 0.0F);
        this.Shape7 = new ModelRenderer(this, 8, 16);
        this.Shape7.addBox(-1.5F, 6.0F, -5.5F, 3, 4, 3);
        this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape7.setTextureSize(256, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.3490659F, 0.0F, 0.0F);
        this.Shape8 = new ModelRenderer(this, 8, 16);
        this.Shape8.addBox(-1.0F, 6.0F, -1.0F, 2, 4, 8);
        this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape8.setTextureSize(256, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
        this.Shape9 = new ModelRenderer(this, 32, 16);
        this.Shape9.addBox(-0.5F, -9.0F, 0.0F, 1, 6, 1);
        this.Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape9.setTextureSize(256, 32);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.5235988F, 0.0F, 0.0F);
        this.Shape10 = new ModelRenderer(this, 32, 16);
        this.Shape10.addBox(-0.5F, -7.8F, -9.0F, 1, 6, 1);
        this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape10.setTextureSize(256, 32);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.1745329F, 0.0F, 0.0F);
        this.Shape11 = new ModelRenderer(this, 32, 16);
        this.Shape11.addBox(3.8F, -8.4F, -4.5F, 1, 6, 1);
        this.Shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape11.setTextureSize(256, 32);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.3490659F, 0.0F, -0.1745329F);
        this.Shape12 = new ModelRenderer(this, 32, 16);
        this.Shape12.addBox(-5.0F, -8.0F, -4.5F, 1, 6, 1);
        this.Shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape12.setTextureSize(256, 32);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.3490659F, 0.0F, 0.1745329F);
        this.Shape13 = new ModelRenderer(this, 64, 16);
        this.Shape13.addBox(-2.0F, -6.0F, -6.0F, 4, 2, 4);
        this.Shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape13.setTextureSize(256, 32);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.3490659F, 0.0F, 0.0F);
        this.Shape14 = new ModelRenderer(this, 0, 0);
        this.Shape14.addBox(-3.0F, -16.0F, -7.0F, 6, 10, 6);
        this.Shape14.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape14.setTextureSize(256, 32);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.3490659F, 0.0F, 0.0F);
        this.Shape15 = new ModelRenderer(this, 0, 6);
        this.Shape15.addBox(-3.0F, -16.0F, 1.0F, 6, 10, 0);
        this.Shape15.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape15.setTextureSize(256, 32);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.8726646F, 0.0F, 0.0F);
        this.Shape16 = new ModelRenderer(this, 0, 6);
        this.Shape16.addBox(-3.0F, -12.0F, -8.0F, 6, 10, 0);
        this.Shape16.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape16.setTextureSize(256, 32);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, -0.1745329F, 0.0F, 0.0F);
        this.Shape17 = new ModelRenderer(this, 16, 0);
        this.Shape17.addBox(-3.0F, -16.0F, -7.0F, 6, 10, 6);
        this.Shape17.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape17.setTextureSize(256, 32);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.3490659F, 0.0F, 0.0F);
        this.Shape18 = new ModelRenderer(this, 32, 0);
        this.Shape18.addBox(-3.0F, -16.0F, -7.0F, 6, 10, 6);
        this.Shape18.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape18.setTextureSize(256, 32);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, 0.3490659F, 0.0F, 0.0F);
        this.Shape19 = new ModelRenderer(this, 48, 0);
        this.Shape19.addBox(-3.0F, -16.0F, -7.0F, 6, 10, 6);
        this.Shape19.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape19.setTextureSize(256, 32);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.3490659F, 0.0F, 0.0F);
        this.Shape20 = new ModelRenderer(this, 62, 0);
        this.Shape20.addBox(-3.0F, -16.0F, -7.0F, 6, 10, 6);
        this.Shape20.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape20.setTextureSize(256, 32);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, 0.3490659F, 0.0F, 0.0F);
        this.Shape21 = new ModelRenderer(this, 78, 0);
        this.Shape21.addBox(-3.0F, -16.0F, -7.0F, 6, 10, 6);
        this.Shape21.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape21.setTextureSize(256, 32);
        this.Shape21.mirror = true;
        this.setRotation(this.Shape21, 0.3490659F, 0.0F, 0.0F);
        this.Shape22 = new ModelRenderer(this, 96, 0);
        this.Shape22.addBox(-3.0F, -16.0F, -7.0F, 6, 10, 6);
        this.Shape22.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape22.setTextureSize(256, 32);
        this.Shape22.mirror = true;
        this.setRotation(this.Shape22, 0.3490659F, 0.0F, 0.0F);
        this.Shape23 = new ModelRenderer(this, 112, 0);
        this.Shape23.addBox(-3.0F, -16.0F, -7.0F, 6, 10, 6);
        this.Shape23.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape23.setTextureSize(256, 32);
        this.Shape23.mirror = true;
        this.setRotation(this.Shape23, 0.3490659F, 0.0F, 0.0F);
        this.Shape24 = new ModelRenderer(this, 128, 0);
        this.Shape24.addBox(-3.0F, -16.0F, -7.0F, 6, 10, 6);
        this.Shape24.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape24.setTextureSize(256, 32);
        this.Shape24.mirror = true;
        this.setRotation(this.Shape24, 0.3490659F, 0.0F, 0.0F);
        this.Shape25 = new ModelRenderer(this, 144, 0);
        this.Shape25.addBox(-3.0F, -16.0F, -7.0F, 6, 10, 6);
        this.Shape25.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape25.setTextureSize(256, 32);
        this.Shape25.mirror = true;
        this.setRotation(this.Shape25, 0.3490659F, 0.0F, 0.0F);
        this.Shape26 = new ModelRenderer(this, 160, 0);
        this.Shape26.addBox(-3.0F, -16.0F, -7.0F, 6, 10, 6);
        this.Shape26.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape26.setTextureSize(256, 32);
        this.Shape26.mirror = true;
        this.setRotation(this.Shape26, 0.3490659F, 0.0F, 0.0F);
        this.Shape27 = new ModelRenderer(this, 176, 0);
        this.Shape27.addBox(-3.0F, -16.0F, -7.0F, 6, 10, 6);
        this.Shape27.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape27.setTextureSize(256, 32);
        this.Shape27.mirror = true;
        this.setRotation(this.Shape27, 0.3490659F, 0.0F, 0.0F);
        this.Shape28 = new ModelRenderer(this, 192, 0);
        this.Shape28.addBox(-3.0F, -16.0F, -7.0F, 6, 10, 6);
        this.Shape28.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape28.setTextureSize(256, 32);
        this.Shape28.mirror = true;
        this.setRotation(this.Shape28, 0.3490659F, 0.0F, 0.0F);
        this.Shape29 = new ModelRenderer(this, 208, 0);
        this.Shape29.addBox(-3.0F, -16.0F, -7.0F, 6, 10, 6);
        this.Shape29.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape29.setTextureSize(256, 32);
        this.Shape29.mirror = true;
        this.setRotation(this.Shape29, 0.3490659F, 0.0F, 0.0F);
        this.Shape30 = new ModelRenderer(this, 224, 0);
        this.Shape30.addBox(-3.0F, -16.0F, -7.0F, 6, 10, 6);
        this.Shape30.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape30.setTextureSize(256, 32);
        this.Shape30.mirror = true;
        this.setRotation(this.Shape30, 0.3490659F, 0.0F, 0.0F);
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
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        this.Shape5.render(f5);
        this.Shape6.render(f5);
        this.Shape7.render(f5);
        this.Shape8.render(f5);
        this.Shape9.render(f5);
        this.Shape10.render(f5);
        this.Shape11.render(f5);
        this.Shape12.render(f5);
        this.Shape13.render(f5);
        this.Shape15.render(f5);
        this.Shape16.render(f5);

        if (l > this.tim + 4L)
        {
            this.tim = l;

            if (this.ren >= 15)
            {
                this.ren = 1;
            }

            ++this.ren;
        }

        int brightness = 15728880;
        int brightMod = brightness % 65536;
        int brightDiv = brightness / 65536;
        GL11.glPushMatrix();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)brightMod / 1.0F, (float)brightDiv / 1.0F);

        switch (this.ren)
        {
            case 1:
                this.Shape14.render(f5);
                break;

            case 2:
                this.Shape17.render(f5);
                break;

            case 3:
                this.Shape18.render(f5);
                break;

            case 4:
                this.Shape19.render(f5);
                break;

            case 5:
                this.Shape20.render(f5);
                break;

            case 6:
                this.Shape21.render(f5);
                break;

            case 7:
                this.Shape22.render(f5);
                break;

            case 8:
                this.Shape23.render(f5);
                break;

            case 9:
                this.Shape24.render(f5);
                break;

            case 10:
                this.Shape25.render(f5);
                break;

            case 11:
                this.Shape26.render(f5);
                break;

            case 12:
                this.Shape27.render(f5);
                break;

            case 13:
                this.Shape28.render(f5);
                break;

            case 14:
                this.Shape29.render(f5);
                break;

            case 15:
                this.Shape30.render(f5);
        }

        GL11.glPopMatrix();
    }
}

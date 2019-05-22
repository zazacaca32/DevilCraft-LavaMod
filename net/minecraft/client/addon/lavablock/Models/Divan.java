package net.minecraft.client.addon.lavablock.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Divan extends ModelBase
{
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    private int id = 1;

    public Divan(int id)
    {
        this.id = id;
        super.textureWidth = 64;
        super.textureHeight = 32;

        switch (id)
        {
            case 1:
                this.Shape1 = new ModelRenderer(this, 0, 5);
                this.Shape1.addBox(0.0F, 0.0F, 0.0F, 16, 5, 16);
                this.Shape1.setRotationPoint(-8.0F, 18.0F, -8.0F);
                this.Shape1.setTextureSize(64, 32);
                this.Shape1.mirror = true;
                this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
                this.Shape2 = new ModelRenderer(this, 0, 0);
                this.Shape2.addBox(0.0F, 0.0F, 0.0F, 16, 9, 3);
                this.Shape2.setRotationPoint(-8.0F, 9.0F, 6.0F);
                this.Shape2.setTextureSize(64, 32);
                this.Shape2.mirror = true;
                this.setRotation(this.Shape2, -0.1047198F, 0.0F, 0.0F);
                this.Shape3 = new ModelRenderer(this, 0, 0);
                this.Shape3.addBox(0.0F, 0.0F, 0.0F, 10, 4, 3);
                this.Shape3.setRotationPoint(-5.0F, 8.0F, 5.0F);
                this.Shape3.setTextureSize(64, 32);
                this.Shape3.mirror = true;
                this.setRotation(this.Shape3, -0.1047198F, 0.0F, 0.0F);
                break;

            case 2:
                this.Shape1 = new ModelRenderer(this, 0, 7);
                this.Shape1.addBox(0.0F, 0.0F, 0.0F, 3, 5, 15);
                this.Shape1.setRotationPoint(5.0F, 19.0F, -7.0F);
                this.Shape1.setTextureSize(64, 32);
                this.Shape1.mirror = true;
                this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
                this.Shape2 = new ModelRenderer(this, 0, 12);
                this.Shape2.addBox(0.0F, 0.0F, 0.0F, 3, 8, 3);
                this.Shape2.setRotationPoint(5.1F, 11.0F, 5.9F);
                this.Shape2.setTextureSize(64, 32);
                this.Shape2.mirror = true;
                this.setRotation(this.Shape2, -0.1047198F, 0.0F, -0.2443461F);
                this.Shape3 = new ModelRenderer(this, 0, 0);
                this.Shape3.addBox(0.0F, 0.0F, 0.0F, 5, 5, 14);
                this.Shape3.setRotationPoint(4.0F, 14.0F, -7.5F);
                this.Shape3.setTextureSize(64, 32);
                this.Shape3.mirror = true;
                this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
                break;

            case 3:
                this.Shape1 = new ModelRenderer(this, 0, 7);
                this.Shape1.addBox(0.0F, 0.0F, 0.0F, 3, 5, 15);
                this.Shape1.setRotationPoint(-8.0F, 19.0F, -7.0F);
                this.Shape1.setTextureSize(64, 32);
                this.Shape1.mirror = true;
                this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
                this.Shape2 = new ModelRenderer(this, 0, 12);
                this.Shape2.addBox(0.0F, 0.0F, 0.0F, 3, 8, 3);
                this.Shape2.setRotationPoint(-8.0F, 10.3F, 5.9F);
                this.Shape2.setTextureSize(64, 32);
                this.Shape2.mirror = true;
                this.setRotation(this.Shape2, -0.1047198F, 0.0F, 0.2443461F);
                this.Shape3 = new ModelRenderer(this, 0, 0);
                this.Shape3.addBox(0.0F, 0.0F, 0.0F, 5, 5, 14);
                this.Shape3.setRotationPoint(-9.0F, 14.0F, -7.5F);
                this.Shape3.setTextureSize(64, 32);
                this.Shape3.mirror = true;
                this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
                break;

            case 4:
                this.Shape1 = new ModelRenderer(this, 0, 5);
                this.Shape1.addBox(0.0F, 0.0F, 0.0F, 16, 5, 16);
                this.Shape1.setRotationPoint(-8.0F, 18.0F, -8.0F);
                this.Shape1.setTextureSize(64, 32);
                this.Shape1.mirror = true;
                this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
                this.Shape2 = new ModelRenderer(this, 0, 0);
                this.Shape2.addBox(0.0F, 0.0F, 0.0F, 14, 9, 3);
                this.Shape2.setRotationPoint(-8.0F, 9.0F, 6.0F);
                this.Shape2.setTextureSize(64, 32);
                this.Shape2.mirror = true;
                this.setRotation(this.Shape2, -0.1047198F, 0.0F, 0.0F);
                this.Shape3 = new ModelRenderer(this, 0, 0);
                this.Shape3.addBox(0.0F, 0.0F, 0.0F, 3, 9, 14);
                this.Shape3.setRotationPoint(6.0F, 9.0F, -8.0F);
                this.Shape3.setTextureSize(64, 32);
                this.Shape3.mirror = true;
                this.setRotation(this.Shape3, 0.0F, 0.0F, 0.1047198F);
                this.Shape4 = new ModelRenderer(this, 0, 0);
                this.Shape4.addBox(0.0F, 0.0F, 0.0F, 3, 9, 3);
                this.Shape4.setRotationPoint(6.0F, 9.0F, 6.0F);
                this.Shape4.setTextureSize(64, 32);
                this.Shape4.mirror = true;
                this.setRotation(this.Shape4, -0.1047198F, 0.0F, 0.1047198F);
                this.Shape5 = new ModelRenderer(this, 0, 0);
                this.Shape5.addBox(0.0F, 0.0F, 0.0F, 10, 4, 3);
                this.Shape5.setRotationPoint(-5.0F, 8.0F, 5.0F);
                this.Shape5.setTextureSize(64, 32);
                this.Shape5.mirror = true;
                this.setRotation(this.Shape5, -0.1047198F, 0.0F, 0.0F);
                this.Shape6 = new ModelRenderer(this, 0, 0);
                this.Shape6.addBox(0.0F, 0.0F, 0.0F, 3, 4, 10);
                this.Shape6.setRotationPoint(5.0F, 8.0F, -5.0F);
                this.Shape6.setTextureSize(64, 32);
                this.Shape6.mirror = true;
                this.setRotation(this.Shape6, 0.0F, 0.0F, 0.1047198F);
                this.Shape7 = new ModelRenderer(this, 0, 27);
                this.Shape7.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3);
                this.Shape7.setRotationPoint(5.0F, 23.0F, 5.0F);
                this.Shape7.setTextureSize(64, 32);
                this.Shape7.mirror = true;
                this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
        }
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

    public void render()
    {
        float f5 = 0.0625F;
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);

        if (this.id == 4)
        {
            this.Shape4.render(f5);
            this.Shape5.render(f5);
            this.Shape6.render(f5);
            this.Shape7.render(f5);
        }
    }
}

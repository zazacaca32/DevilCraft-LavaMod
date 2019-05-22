package net.minecraft.client.addon.lavablock.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ColumnModelB extends ModelBase
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

    public ColumnModelB()
    {
        super.textureWidth = 64;
        super.textureHeight = 64;
        this.Shape1 = new ModelRenderer(this, 0, 40);
        this.Shape1.addBox(-4.0F, 8.0F, -4.0F, 8, 16, 8);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape1.setTextureSize(64, 64);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        this.Shape2 = new ModelRenderer(this, 0, -2);
        this.Shape2.addBox(-5.0F, 7.999F, 1.0F, 10, 16, 2);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape2.setTextureSize(64, 64);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        this.Shape3 = new ModelRenderer(this, 0, -2);
        this.Shape3.addBox(-5.0F, 7.999F, -3.0F, 10, 16, 2);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(64, 64);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        this.Shape4 = new ModelRenderer(this, 0, -10);
        this.Shape4.addBox(-3.0F, 8.0F, -5.0F, 2, 16, 10);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(64, 64);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        this.Shape5 = new ModelRenderer(this, 0, -10);
        this.Shape5.addBox(1.0F, 8.0F, -5.0F, 2, 16, 10);
        this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape5.setTextureSize(64, 64);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
        this.Shape6 = new ModelRenderer(this, 4, 18);
        this.Shape6.addBox(-7.0F, 22.01F, -7.0F, 14, 2, 14);
        this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape6.setTextureSize(64, 64);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        this.Shape7 = new ModelRenderer(this, 0, 51);
        this.Shape7.addBox(-5.0F, 21.03F, -6.0F, 10, 1, 12);
        this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape7.setTextureSize(64, 64);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
        this.Shape8 = new ModelRenderer(this, 0, 53);
        this.Shape8.addBox(-6.0F, 21.02F, -5.0F, 12, 1, 10);
        this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape8.setTextureSize(64, 64);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
        this.Shape9 = new ModelRenderer(this, 4, 18);
        this.Shape9.addBox(-7.0F, 7.99F, -7.0F, 14, 2, 14);
        this.Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape9.setTextureSize(64, 64);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
        this.Shape10 = new ModelRenderer(this, 0, 53);
        this.Shape10.addBox(-6.0F, 9.98F, -5.0F, 12, 1, 10);
        this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape10.setTextureSize(64, 64);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        this.Shape11 = new ModelRenderer(this, 0, 51);
        this.Shape11.addBox(-5.0F, 9.97F, -6.0F, 10, 1, 12);
        this.Shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape11.setTextureSize(64, 64);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
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

    public void render(boolean flag, boolean flag2)
    {
        float f5 = 0.0625F;
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        this.Shape5.render(f5);

        if (!flag2)
        {
            this.Shape6.render(f5);
            this.Shape7.render(f5);
            this.Shape8.render(f5);
        }

        if (!flag)
        {
            this.Shape9.render(f5);
            this.Shape10.render(f5);
            this.Shape11.render(f5);
        }
    }
}

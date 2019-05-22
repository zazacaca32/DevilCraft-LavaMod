package net.minecraft.client.addon.lavablock.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ArmorStand extends ModelBase
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

    public ArmorStand()
    {
        super.textureWidth = 64;
        super.textureHeight = 32;
        this.Shape1 = new ModelRenderer(this, 0, 19);
        this.Shape1.addBox(-6.0F, 23.0F, -6.0F, 12, 1, 12);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        this.Shape2 = new ModelRenderer(this, 0, 0);
        this.Shape2.addBox(-3.0F, 12.0F, -1.0F, 2, 11, 2);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape2.setTextureSize(64, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        this.Shape3 = new ModelRenderer(this, 0, 0);
        this.Shape3.addBox(1.0F, 12.0F, -1.0F, 2, 11, 2);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(64, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        this.Shape4 = new ModelRenderer(this, 0, 0);
        this.Shape4.addBox(-4.0F, 10.0F, -1.0F, 8, 2, 2);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(64, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        this.Shape5 = new ModelRenderer(this, 0, 0);
        this.Shape5.addBox(-3.5F, 3.0F, -1.0F, 2, 7, 2);
        this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape5.setTextureSize(64, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
        this.Shape6 = new ModelRenderer(this, 0, 0);
        this.Shape6.addBox(1.5F, 3.0F, -1.0F, 2, 7, 2);
        this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape6.setTextureSize(64, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        this.Shape7 = new ModelRenderer(this, 0, 2);
        this.Shape7.addBox(-6.0F, 0.0F, -1.0F, 12, 3, 2);
        this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape7.setTextureSize(64, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
        this.Shape8 = new ModelRenderer(this, 0, 0);
        this.Shape8.addBox(-7.0F, 0.0F, -1.1F, 2, 12, 2);
        this.Shape8.setRotationPoint(0.0F, 0.5F, 0.0F);
        this.Shape8.setTextureSize(64, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0872665F);
        this.Shape9 = new ModelRenderer(this, 0, 0);
        this.Shape9.addBox(5.0F, 0.0F, -1.1F, 2, 12, 2);
        this.Shape9.setRotationPoint(0.0F, 0.5F, 0.0F);
        this.Shape9.setTextureSize(64, 32);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0F, 0.0F, -0.0872665F);
        this.Shape10 = new ModelRenderer(this, 0, 0);
        this.Shape10.addBox(-1.0F, -6.0F, -1.0F, 2, 6, 2);
        this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape10.setTextureSize(64, 32);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        this.Shape2.addChild(this.Shape3);
        this.Shape2.addChild(this.Shape4);
        this.Shape2.addChild(this.Shape5);
        this.Shape2.addChild(this.Shape6);
        this.Shape2.addChild(this.Shape7);
        this.Shape2.addChild(this.Shape8);
        this.Shape2.addChild(this.Shape9);
        this.Shape2.addChild(this.Shape10);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotation(float rotation, boolean flag)
    {
        rotation = rotation / 360.0F * ((float)Math.PI * 2F);
        this.Shape2.rotateAngleY = rotation;

        if (flag)
        {
            this.Shape8.rotateAngleX = -0.27F;
        }
        else
        {
            this.Shape8.rotateAngleX = 0.0F;
        }
    }

    public void render()
    {
        float f5 = 0.0625F;
        this.Shape1.render(f5);
        this.Shape2.render(f5);
    }
}

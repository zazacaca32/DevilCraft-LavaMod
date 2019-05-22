package net.minecraft.client.addon.tchestplate.entities.projectile.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelGateCastle extends ModelBase
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

    public ModelGateCastle()
    {
        super.textureWidth = 64;
        super.textureHeight = 64;
        (this.Shape1 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 16, 48, 3);
        this.Shape1.setRotationPoint(-24.0F, -24.0F, -1.0F);
        this.Shape1.setTextureSize(64, 64);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        (this.Shape2 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 16, 48, 3);
        this.Shape2.setRotationPoint(-8.0F, -24.0F, -1.0F);
        this.Shape2.setTextureSize(64, 64);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        (this.Shape3 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 16, 48, 3);
        this.Shape3.setRotationPoint(8.0F, -24.0F, -1.0F);
        this.Shape3.setTextureSize(64, 64);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        (this.Shape4 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 16, 48, 3);
        this.Shape4.setRotationPoint(24.0F, -24.0F, -1.0F);
        this.Shape4.setTextureSize(64, 64);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        (this.Shape5 = new ModelRenderer(this, 42, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 56, 5);
        this.Shape5.setRotationPoint(-3.0F, -32.0F, -2.0F);
        this.Shape5.setTextureSize(64, 64);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
        (this.Shape6 = new ModelRenderer(this, 42, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 49, 5);
        this.Shape6.setRotationPoint(-24.0F, -25.0F, -2.0F);
        this.Shape6.setTextureSize(64, 64);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        (this.Shape7 = new ModelRenderer(this, 42, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 51, 5);
        this.Shape7.setRotationPoint(-17.0F, -27.0F, -2.0F);
        this.Shape7.setTextureSize(64, 64);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
        (this.Shape8 = new ModelRenderer(this, 42, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 54, 5);
        this.Shape8.setRotationPoint(-10.0F, -30.0F, -2.0F);
        this.Shape8.setTextureSize(64, 64);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
        (this.Shape9 = new ModelRenderer(this, 42, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 57, 5);
        this.Shape9.setRotationPoint(4.0F, -33.0F, -2.0F);
        this.Shape9.setTextureSize(64, 64);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
        (this.Shape10 = new ModelRenderer(this, 42, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 57, 5);
        this.Shape10.setRotationPoint(8.0F, -33.0F, -2.0F);
        this.Shape10.setTextureSize(64, 64);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        (this.Shape11 = new ModelRenderer(this, 42, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 56, 5);
        this.Shape11.setRotationPoint(15.0F, -32.0F, -2.0F);
        this.Shape11.setTextureSize(64, 64);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
        (this.Shape12 = new ModelRenderer(this, 42, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 54, 5);
        this.Shape12.setRotationPoint(22.0F, -30.0F, -2.0F);
        this.Shape12.setTextureSize(64, 64);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
        (this.Shape13 = new ModelRenderer(this, 42, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 49, 5);
        this.Shape13.setRotationPoint(36.0F, -25.0F, -2.0F);
        this.Shape13.setTextureSize(64, 64);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
        (this.Shape14 = new ModelRenderer(this, 42, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 51, 5);
        this.Shape14.setRotationPoint(29.0F, -27.0F, -2.0F);
        this.Shape14.setTextureSize(64, 64);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
    }

    public void render()
    {
        float f5 = 0.0625F;
        this.Shape1.render(0.0625F);
        this.Shape2.render(0.0625F);
        this.Shape3.render(0.0625F);
        this.Shape4.render(0.0625F);
        this.Shape5.render(0.0625F);
        this.Shape6.render(0.0625F);
        this.Shape7.render(0.0625F);
        this.Shape8.render(0.0625F);
        this.Shape9.render(0.0625F);
        this.Shape10.render(0.0625F);
        this.Shape11.render(0.0625F);
        this.Shape12.render(0.0625F);
        this.Shape13.render(0.0625F);
        this.Shape14.render(0.0625F);
    }

    public void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}

package net.minecraft.client.addon.tchestplate.accesories.models;

import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;

public class Model23f extends ModelBaseLavaArmor
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

    public String getTexture()
    {
        return "/mods/models/accesories/23f.png";
    }

    public Model23f()
    {
        super.textureWidth = 64;
        super.textureHeight = 32;
        this.Shape1 = new ModelRenderer(this, 0, 23);
        this.Shape1.addBox(6.0F, -3.0F, -2.0F, 1, 5, 4);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, -0.5061455F);
        this.Shape2 = new ModelRenderer(this, 10, 23);
        this.Shape2.addBox(-7.0F, -3.0F, -2.0F, 1, 5, 4);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape2.setTextureSize(64, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.5061455F);
        this.Shape3 = new ModelRenderer(this, 0, 18);
        this.Shape3.addBox(-5.0F, -9.0F, -5.0F, 10, 4, 1);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(64, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, -0.1047198F, 0.0F, 0.0F);
        this.Shape4 = new ModelRenderer(this, 32, 0);
        this.Shape4.addBox(-4.0F, -10.0F, -4.0F, 8, 4, 8);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(64, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, -0.1047198F, 0.0F, 0.0F);
        this.Shape5 = new ModelRenderer(this, 0, 10);
        this.Shape5.addBox(-4.8F, -8.0F, -4.0F, 1, 3, 8);
        this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape5.setTextureSize(64, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, -0.1047198F, 0.0F, 0.0F);
        this.Shape6 = new ModelRenderer(this, 39, 1);
        this.Shape6.addBox(3.8F, -8.0F, -4.0F, 1, 3, 8);
        this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape6.setTextureSize(64, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, -0.1047198F, 0.0F, 0.0F);
        this.Shape7 = new ModelRenderer(this, 0, 14);
        this.Shape7.addBox(-5.0F, -8.0F, 4.0F, 10, 3, 1);
        this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape7.setTextureSize(64, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, -0.1047198F, 0.0F, 0.0F);
        this.Shape8 = new ModelRenderer(this, 20, 30);
        this.Shape8.addBox(-5.5F, -5.5F, -4.966667F, 1, 1, 1);
        this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape8.setTextureSize(64, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, ((float)Math.PI / 4F));
        this.Shape9 = new ModelRenderer(this, 20, 28);
        this.Shape9.addBox(-0.5F, -7.5F, -5.0F, 1, 1, 1);
        this.Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape9.setTextureSize(64, 32);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
        this.Shape10 = new ModelRenderer(this, 0, 2);
        this.Shape10.addBox(-4.5F, -8.5F, -5.3F, 9, 3, 1);
        this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape10.setTextureSize(64, 32);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, -0.1047198F, 0.0F, 0.0F);
        this.Shape11 = new ModelRenderer(this, 26, 12);
        this.Shape11.addBox(-4.5F, -7.5F, 4.3F, 9, 2, 1);
        this.Shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape11.setTextureSize(64, 32);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, -0.1047198F, 0.0F, 0.0F);
        this.Shape12 = new ModelRenderer(this, 39, 3);
        this.Shape12.addBox(-5.3F, -7.5F, -3.7F, 1, 2, 8);
        this.Shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape12.setTextureSize(64, 32);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, -0.0872665F, 0.0F, 0.0F);
        this.Shape13 = new ModelRenderer(this, 38, 0);
        this.Shape13.addBox(4.3F, -7.5F, -4.3F, 1, 2, 9);
        this.Shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape13.setTextureSize(64, 32);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, -0.0872665F, 0.0F, 0.0F);
        this.Shape14 = new ModelRenderer(this, 42, 0);
        this.Shape14.addBox(-4.1F, -9.0F, -1.1F, 1, 1, 5);
        this.Shape14.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape14.setTextureSize(64, 32);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, -0.1047198F, 0.0F, 0.0F);
        this.Shape15 = new ModelRenderer(this, 43, 2);
        this.Shape15.addBox(3.1F, -9.0F, -1.1F, 1, 1, 5);
        this.Shape15.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape15.setTextureSize(64, 32);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, -0.1047198F, 0.0F, 0.0F);
        super.bipedHead.cubeList.clear();
        super.bipedHead.addChild(this.Shape1);
        super.bipedHead.addChild(this.Shape2);
        super.bipedHead.addChild(this.Shape3);
        super.bipedHead.addChild(this.Shape4);
        super.bipedHead.addChild(this.Shape5);
        super.bipedHead.addChild(this.Shape6);
        super.bipedHead.addChild(this.Shape7);
        super.bipedHead.addChild(this.Shape8);
        super.bipedHead.addChild(this.Shape9);
        super.bipedHead.addChild(this.Shape10);
        super.bipedHead.addChild(this.Shape11);
        super.bipedHead.addChild(this.Shape12);
        super.bipedHead.addChild(this.Shape13);
        super.bipedHead.addChild(this.Shape14);
        super.bipedHead.addChild(this.Shape15);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}

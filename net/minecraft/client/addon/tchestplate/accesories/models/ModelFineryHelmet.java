package net.minecraft.client.addon.tchestplate.accesories.models;

import net.minecraft.client.model.ModelRenderer;

public class ModelFineryHelmet extends ModelBipedAccesories
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
    final String texture = "/mods/models/accesories/finerygirl.png";

    public ModelFineryHelmet()
    {
        super.textureWidth = 32;
        super.textureHeight = 32;
        (this.Shape1 = new ModelRenderer(this, 0, 0)).addBox(3.7F, -8.9F, -0.5F, 1, 2, 1);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape1.setTextureSize(32, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        (this.Shape2 = new ModelRenderer(this, 0, 0)).addBox(3.7F, -7.9F, 3.0F, 1, 2, 1);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape2.setTextureSize(32, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.5934119F, 0.0F, 0.0F);
        (this.Shape3 = new ModelRenderer(this, 0, 0)).addBox(3.7F, -6.5F, -2.9F, 1, 1, 2);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(32, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        (this.Shape4 = new ModelRenderer(this, 0, 0)).addBox(3.7F, -5.5F, -6.0F, 1, 1, 2);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(32, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, -0.5410521F, 0.0F, 0.0F);
        (this.Shape5 = new ModelRenderer(this, 0, 0)).addBox(3.7F, -7.9F, -4.0F, 1, 2, 1);
        this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape5.setTextureSize(32, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, -0.5934119F, 0.0F, 0.0F);
        (this.Shape6 = new ModelRenderer(this, 0, 0)).addBox(3.7F, -5.5F, 4.0F, 1, 1, 2);
        this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape6.setTextureSize(32, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.5410521F, 0.0F, 0.0F);
        (this.Shape7 = new ModelRenderer(this, 0, 0)).addBox(3.7F, -6.5F, 1.0F, 1, 1, 2);
        this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape7.setTextureSize(32, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
        (this.Shape8 = new ModelRenderer(this, 0, 0)).addBox(3.7F, -5.5F, -2.0F, 1, 1, 2);
        this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape8.setTextureSize(32, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, -0.5410521F, 0.0F, 0.0F);
        (this.Shape9 = new ModelRenderer(this, 0, 0)).addBox(3.7F, -4.0F, -3.8F, 1, 2, 1);
        this.Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape9.setTextureSize(32, 32);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, -0.5934119F, 0.0F, 0.0F);
        (this.Shape10 = new ModelRenderer(this, 0, 0)).addBox(3.7F, -5.7F, 0.0F, 1, 1, 2);
        this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape10.setTextureSize(32, 32);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.5235988F, 0.0F, 0.0F);
        (this.Shape11 = new ModelRenderer(this, 0, 0)).addBox(3.7F, -5.0F, -0.5F, 1, 2, 1);
        this.Shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape11.setTextureSize(32, 32);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
        (this.Shape12 = new ModelRenderer(this, 0, 0)).addBox(3.7F, -4.1F, 2.5F, 1, 2, 1);
        this.Shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape12.setTextureSize(32, 32);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.5410521F, 0.0F, 0.0F);
        (this.Shape13 = new ModelRenderer(this, 18, 12)).addBox(3.5F, -7.0F, -1.0F, 1, 2, 2);
        this.Shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape13.setTextureSize(32, 32);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
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
        super.bipedHead.addChild(this.Shape12);
        super.bipedHead.addChild(this.Shape13);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public String getTexture()
    {
        return "/mods/models/accesories/finerygirl.png";
    }
}

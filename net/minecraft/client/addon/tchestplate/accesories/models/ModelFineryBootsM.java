package net.minecraft.client.addon.tchestplate.accesories.models;

import net.minecraft.client.model.ModelRenderer;

public class ModelFineryBootsM extends ModelBipedAccesories
{
    ModelRenderer Shape1;
    ModelRenderer Shape3;
    ModelRenderer Shape2;
    ModelRenderer Shape4;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    final String texture = "/mods/models/accesories/finerymen.png";

    public ModelFineryBootsM()
    {
        super.textureWidth = 32;
        super.textureHeight = 32;
        (this.Shape1 = new ModelRenderer(this, 0, 0)).addBox(-2.5F, 10.1F, -2.5F, 5, 2, 5);
        this.Shape1.setTextureSize(128, 64);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        (this.Shape3 = new ModelRenderer(this, 0, 0)).addBox(-1.5F, 11.1F, -4.0F, 3, 1, 1);
        this.Shape3.setTextureSize(128, 64);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        (this.Shape2 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, 10.6F, -3.2F, 4, 1, 1);
        this.Shape2.setTextureSize(128, 64);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        (this.Shape4 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, 11.1F, -3.2F, 4, 1, 1);
        this.Shape4.setTextureSize(128, 64);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        (this.Shape10 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, 10.6F, -3.2F, 4, 1, 1);
        this.Shape10.setTextureSize(128, 64);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        (this.Shape11 = new ModelRenderer(this, 0, 0)).addBox(-2.5F, 10.1F, -2.5F, 5, 2, 5);
        this.Shape11.setTextureSize(128, 64);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
        (this.Shape12 = new ModelRenderer(this, 0, 0)).addBox(-1.5F, 11.1F, -4.0F, 3, 1, 1);
        this.Shape12.setTextureSize(128, 64);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
        (this.Shape13 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, 11.1F, -3.2F, 4, 1, 1);
        this.Shape13.setTextureSize(128, 64);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
        super.bipedRightLeg.cubeList.clear();
        super.bipedRightLeg.addChild(this.Shape1);
        super.bipedRightLeg.addChild(this.Shape2);
        super.bipedRightLeg.addChild(this.Shape3);
        super.bipedRightLeg.addChild(this.Shape4);
        super.bipedLeftLeg.cubeList.clear();
        super.bipedLeftLeg.addChild(this.Shape10);
        super.bipedLeftLeg.addChild(this.Shape11);
        super.bipedLeftLeg.addChild(this.Shape12);
        super.bipedLeftLeg.addChild(this.Shape13);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public String getTexture()
    {
        return "/mods/models/accesories/finerymen.png";
    }
}

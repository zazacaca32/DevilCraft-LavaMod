package net.minecraft.client.addon.tchestplate.accesories.models;

import net.minecraft.client.model.ModelRenderer;

public class ModelFineryBoots extends ModelBipedAccesories
{
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    final String texture = "/mods/models/accesories/finerygirl.png";

    public ModelFineryBoots()
    {
        super.textureWidth = 32;
        super.textureHeight = 32;
        (this.Shape1 = new ModelRenderer(this, 0, 0)).addBox(-2.5F, 10.1F, -2.9F, 5, 2, 5);
        this.Shape1.setTextureSize(32, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        (this.Shape2 = new ModelRenderer(this, 0, 0)).addBox(-1.0F, 9.7F, 4.2F, 2, 2, 1);
        this.Shape2.setTextureSize(32, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, -0.6806784F, 0.0F, 0.0F);
        (this.Shape10 = new ModelRenderer(this, 0, 0)).addBox(-2.5F, 10.1F, -2.9F, 5, 2, 5);
        this.Shape10.setTextureSize(32, 32);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        (this.Shape11 = new ModelRenderer(this, 0, 0)).addBox(-1.0F, 9.7F, 4.2F, 2, 2, 1);
        this.Shape11.setTextureSize(32, 32);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, -0.6806784F, 0.0F, 0.0F);
        super.bipedRightLeg.cubeList.clear();
        super.bipedRightLeg.addChild(this.Shape1);
        super.bipedRightLeg.addChild(this.Shape2);
        super.bipedLeftLeg.cubeList.clear();
        super.bipedLeftLeg.addChild(this.Shape10);
        super.bipedLeftLeg.addChild(this.Shape11);
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

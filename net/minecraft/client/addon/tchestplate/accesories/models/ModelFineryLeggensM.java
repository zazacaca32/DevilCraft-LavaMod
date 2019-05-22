package net.minecraft.client.addon.tchestplate.accesories.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFineryLeggensM extends ModelBipedAccesories
{
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape20;
    ModelRenderer Shape21;
    ModelRenderer Shape22;
    ModelRenderer Shape23;
    ModelRenderer Shape24;
    final String texture = "/mods/models/accesories/finerymen.png";

    public ModelFineryLeggensM()
    {
        super.textureWidth = 32;
        super.textureHeight = 32;
        (this.Shape1 = new ModelRenderer(this, 18, 14)).addBox(-1.0F, 10.5F, -2.5F, 2, 1, 1);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape1.setTextureSize(32, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        (this.Shape2 = new ModelRenderer(this, 0, 0)).addBox(-4.0F, 10.0F, -2.1F, 8, 2, 1);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape2.setTextureSize(32, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        (this.Shape3 = new ModelRenderer(this, 0, 0)).addBox(-4.1F, 10.0F, -2.0F, 1, 2, 4);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(32, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        (this.Shape4 = new ModelRenderer(this, 0, 0)).addBox(-4.0F, 10.0F, 1.1F, 8, 2, 1);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(32, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        (this.Shape5 = new ModelRenderer(this, 0, 0)).addBox(3.1F, 10.0F, -2.0F, 1, 2, 4);
        this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape5.setTextureSize(32, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
        (this.Shape6 = new ModelRenderer(this, 0, 0)).addBox(-4.0F, 11.1F, -2.0F, 8, 1, 3);
        this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape6.setTextureSize(32, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        (this.Shape10 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, 0.0F, -2.3F, 4, 10, 1);
        this.Shape10.setTextureSize(32, 32);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        (this.Shape11 = new ModelRenderer(this, 0, 0)).addBox(-2.3F, 0.0F, -2.0F, 1, 10, 4);
        this.Shape11.setTextureSize(32, 32);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
        (this.Shape12 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, 0.0F, 1.3F, 4, 10, 1);
        this.Shape12.setTextureSize(32, 32);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
        (this.Shape13 = new ModelRenderer(this, 0, 0)).addBox(1.1F, 0.0F, -2.0F, 1, 10, 4);
        this.Shape13.setTextureSize(32, 32);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
        (this.Shape14 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, -0.1F, -2.0F, 4, 1, 4);
        this.Shape14.setTextureSize(32, 32);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
        (this.Shape20 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, 0.0F, -2.3F, 4, 10, 1);
        this.Shape20.setTextureSize(32, 32);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, 0.0F, 0.0F, 0.0F);
        (this.Shape21 = new ModelRenderer(this, 0, 0)).addBox(1.3F, 0.0F, -2.0F, 1, 10, 4);
        this.Shape21.setTextureSize(32, 32);
        this.Shape21.mirror = true;
        this.setRotation(this.Shape21, 0.0F, 0.0F, 0.0F);
        (this.Shape22 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, 0.0F, 1.3F, 4, 10, 1);
        this.Shape22.setTextureSize(32, 32);
        this.Shape22.mirror = true;
        this.setRotation(this.Shape22, 0.0F, 0.0F, 0.0F);
        (this.Shape23 = new ModelRenderer(this, 0, 0)).addBox(-2.1F, 0.0F, -2.0F, 1, 10, 4);
        this.Shape23.setTextureSize(32, 32);
        this.Shape23.mirror = true;
        this.setRotation(this.Shape23, 0.0F, 0.0F, 0.0F);
        (this.Shape24 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, -0.1F, -2.0F, 4, 1, 4);
        this.Shape24.setTextureSize(32, 32);
        this.Shape24.mirror = true;
        this.setRotation(this.Shape24, 0.0F, 0.0F, 0.0F);
        super.bipedRightLeg.cubeList.clear();
        super.bipedRightLeg.addChild(this.Shape20);
        super.bipedRightLeg.addChild(this.Shape21);
        super.bipedRightLeg.addChild(this.Shape22);
        super.bipedRightLeg.addChild(this.Shape23);
        super.bipedRightLeg.addChild(this.Shape24);
        super.bipedLeftLeg.cubeList.clear();
        super.bipedLeftLeg.addChild(this.Shape10);
        super.bipedLeftLeg.addChild(this.Shape11);
        super.bipedLeftLeg.addChild(this.Shape12);
        super.bipedLeftLeg.addChild(this.Shape13);
        super.bipedLeftLeg.addChild(this.Shape14);
        super.bipedBody.cubeList.clear();
        super.bipedBody.addChild(this.Shape1);
        super.bipedBody.addChild(this.Shape2);
        super.bipedBody.addChild(this.Shape3);
        super.bipedBody.addChild(this.Shape4);
        super.bipedBody.addChild(this.Shape5);
        super.bipedBody.addChild(this.Shape6);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
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

    public String getTexture()
    {
        return "/mods/models/accesories/finerymen.png";
    }
}

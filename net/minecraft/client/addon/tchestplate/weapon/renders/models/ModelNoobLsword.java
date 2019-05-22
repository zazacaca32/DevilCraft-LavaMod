package net.minecraft.client.addon.tchestplate.weapon.renders.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNoobLsword extends BaseModelHammer
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

    public ModelNoobLsword()
    {
        super.textureWidth = 64;
        super.textureHeight = 64;
        (this.Shape1 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 6, 37, 1);
        this.Shape1.setRotationPoint(-3.0F, -31.0F, 0.0F);
        this.Shape1.setTextureSize(64, 64);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        (this.Shape2 = new ModelRenderer(this, 16, 54)).addBox(0.0F, 0.0F, 0.0F, 10, 5, 4);
        this.Shape2.setRotationPoint(-5.0F, 6.0F, -1.5F);
        this.Shape2.setTextureSize(64, 64);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        (this.Shape3 = new ModelRenderer(this, 27, 53)).addBox(0.0F, 0.0F, 0.0F, 2, 8, 3);
        this.Shape3.setRotationPoint(11.0F, 2.0F, -1.0F);
        this.Shape3.setTextureSize(64, 64);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.837758F);
        (this.Shape4 = new ModelRenderer(this, 18, 52)).addBox(0.0F, -2.0F, 0.0F, 4, 7, 5);
        this.Shape4.setRotationPoint(6.0F, 5.0F, -2.0F);
        this.Shape4.setTextureSize(64, 64);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, ((float)Math.PI / 4F));
        (this.Shape5 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
        this.Shape5.setRotationPoint(-2.0F, -33.0F, 0.0F);
        this.Shape5.setTextureSize(64, 64);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
        (this.Shape6 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
        this.Shape6.setRotationPoint(-1.0F, -35.0F, 0.0F);
        this.Shape6.setTextureSize(64, 64);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        (this.Shape7 = new ModelRenderer(this, 14, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 27, 2);
        this.Shape7.setRotationPoint(-2.0F, -23.0F, -0.5F);
        this.Shape7.setTextureSize(64, 64);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
        (this.Shape8 = new ModelRenderer(this, 14, 0)).addBox(0.0F, 0.0F, 0.0F, 2, 4, 2);
        this.Shape8.setRotationPoint(-1.0F, -27.0F, -0.5F);
        this.Shape8.setTextureSize(64, 64);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
        (this.Shape9 = new ModelRenderer(this, 0, 55)).addBox(0.0F, 0.0F, 0.0F, 4, 3, 5);
        this.Shape9.setRotationPoint(-2.0F, 4.0F, -2.0F);
        this.Shape9.setTextureSize(64, 64);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
        (this.Shape10 = new ModelRenderer(this, 26, 0)).addBox(0.0F, 0.0F, 0.0F, 2, 24, 4);
        this.Shape10.setRotationPoint(-1.0F, -20.0F, -1.5F);
        this.Shape10.setTextureSize(64, 64);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        (this.Shape11 = new ModelRenderer(this, 46, 50)).addBox(0.0F, 0.0F, 0.0F, 3, 11, 3);
        this.Shape11.setRotationPoint(-1.466667F, 11.0F, -1.033333F);
        this.Shape11.setTextureSize(64, 64);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
        (this.Shape12 = new ModelRenderer(this, 18, 52)).addBox(0.0F, -2.0F, 0.0F, 4, 7, 5);
        this.Shape12.setRotationPoint(-9.0F, 8.0F, -2.0F);
        this.Shape12.setTextureSize(64, 64);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0F, 0.0F, -((float)Math.PI / 4F));
        (this.Shape13 = new ModelRenderer(this, 27, 53)).addBox(0.0F, 0.0F, 0.0F, 2, 8, 3);
        this.Shape13.setRotationPoint(-12.2F, 3.8F, -1.0F);
        this.Shape13.setTextureSize(64, 64);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0F, 0.0F, -0.837758F);
        (this.Shape14 = new ModelRenderer(this, 18, 57)).addBox(0.0F, 0.0F, 0.0F, 6, 2, 5);
        this.Shape14.setRotationPoint(-3.0F, 22.0F, -2.0F);
        this.Shape14.setTextureSize(64, 64);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
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
        this.Shape14.render(f5);
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
}

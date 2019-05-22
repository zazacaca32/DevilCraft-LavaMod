package net.minecraft.client.addon.lavablock.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Lock extends ModelBase
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
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;

    public Lock()
    {
        super.textureWidth = 64;
        super.textureHeight = 16;
        this.Shape1 = new ModelRenderer(this, 0, 10);
        this.Shape1.addBox(0.0F, 0.0F, 0.0F, 16, 4, 1);
        this.Shape1.setRotationPoint(-8.0F, 8.0F, 7.0F);
        this.Shape1.setTextureSize(64, 16);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        this.Shape2 = new ModelRenderer(this, 34, 7);
        this.Shape2.addBox(0.0F, 0.0F, 0.0F, 2, 4, 4);
        this.Shape2.setRotationPoint(-1.0F, 8.0F, 3.0F);
        this.Shape2.setTextureSize(64, 16);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        this.Shape3 = new ModelRenderer(this, 49, 11);
        this.Shape3.addBox(0.0F, 0.0F, 0.0F, 6, 2, 2);
        this.Shape3.setRotationPoint(-3.0F, 9.0F, 4.0F);
        this.Shape3.setTextureSize(64, 16);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        this.Shape4 = new ModelRenderer(this, 50, 12);
        this.Shape4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
        this.Shape4.setRotationPoint(-3.0F, 11.0F, 4.0F);
        this.Shape4.setTextureSize(64, 16);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        this.Shape5 = new ModelRenderer(this, 50, 12);
        this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
        this.Shape5.setRotationPoint(2.0F, 11.0F, 4.0F);
        this.Shape5.setTextureSize(64, 16);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
        this.Shape6 = new ModelRenderer(this, 53, 9);
        this.Shape6.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2);
        this.Shape6.setRotationPoint(3.0F, 11.0F, 4.0F);
        this.Shape6.setTextureSize(64, 16);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        this.Shape7 = new ModelRenderer(this, 50, 12);
        this.Shape7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
        this.Shape7.setRotationPoint(3.0F, 10.0F, 4.0F);
        this.Shape7.setTextureSize(64, 16);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
        this.Shape8 = new ModelRenderer(this, 50, 12);
        this.Shape8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
        this.Shape8.setRotationPoint(-4.0F, 10.0F, 4.0F);
        this.Shape8.setTextureSize(64, 16);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
        this.Shape9 = new ModelRenderer(this, 50, 9);
        this.Shape9.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2);
        this.Shape9.setRotationPoint(-5.0F, 11.0F, 4.0F);
        this.Shape9.setTextureSize(64, 16);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
        this.Shape10 = new ModelRenderer(this, 0, 0);
        this.Shape10.addBox(0.0F, 0.0F, 0.0F, 12, 6, 4);
        this.Shape10.setRotationPoint(-6.0F, 15.0F, 3.0F);
        this.Shape10.setTextureSize(64, 16);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        this.Shape11 = new ModelRenderer(this, 0, 2);
        this.Shape11.addBox(0.0F, 0.0F, 0.0F, 10, 2, 4);
        this.Shape11.setRotationPoint(-5.0F, 21.0F, 3.0F);
        this.Shape11.setTextureSize(64, 16);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
        this.Shape12 = new ModelRenderer(this, 0, 1);
        this.Shape12.addBox(0.0F, 0.0F, 0.0F, 6, 1, 4);
        this.Shape12.setRotationPoint(-3.0F, 23.0F, 3.0F);
        this.Shape12.setTextureSize(64, 16);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
        this.Shape13 = new ModelRenderer(this, 4, 5);
        this.Shape13.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
        this.Shape13.setRotationPoint(-2.0F, 14.0F, 3.0F);
        this.Shape13.setTextureSize(64, 16);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
        this.Shape14 = new ModelRenderer(this, 33, 3);
        this.Shape14.addBox(0.0F, 0.0F, 0.0F, 10, 3, 1);
        this.Shape14.setRotationPoint(-5.0F, 17.0F, 2.5F);
        this.Shape14.setTextureSize(64, 16);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
        this.Shape15 = new ModelRenderer(this, 33, 1);
        this.Shape15.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1);
        this.Shape15.setRotationPoint(-4.0F, 16.0F, 2.5F);
        this.Shape15.setTextureSize(64, 16);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
        this.Shape16 = new ModelRenderer(this, 33, 1);
        this.Shape16.addBox(0.0F, 0.0F, 0.0F, 8, 2, 1);
        this.Shape16.setRotationPoint(-4.0F, 20.0F, 2.5F);
        this.Shape16.setTextureSize(64, 16);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0F);
        this.Shape17 = new ModelRenderer(this, 47, 5);
        this.Shape17.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
        this.Shape17.setRotationPoint(-1.0F, 15.0F, 2.5F);
        this.Shape17.setTextureSize(64, 16);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.0F, 0.0F, 0.0F);
        this.Shape18 = new ModelRenderer(this, 44, 5);
        this.Shape18.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
        this.Shape18.setRotationPoint(-2.0F, 22.0F, 2.5F);
        this.Shape18.setTextureSize(64, 16);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
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
        this.Shape15.render(f5);
        this.Shape16.render(f5);
        this.Shape17.render(f5);
        this.Shape18.render(f5);
    }
}

package net.minecraft.client.addon.lavablock.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Prezent extends ModelBase
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
    ModelRenderer Shape19;
    ModelRenderer Shape20;

    public Prezent()
    {
        super.textureWidth = 64;
        super.textureHeight = 64;
        this.Shape1 = new ModelRenderer(this, 0, 0);
        this.Shape1.addBox(-6.0F, 15.0F, -6.0F, 12, 9, 12);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape1.setTextureSize(64, 64);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        this.Shape2 = new ModelRenderer(this, 24, 50);
        this.Shape2.addBox(-1.0F, 15.0F, -6.5F, 2, 9, 1);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape2.setTextureSize(64, 64);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        this.Shape3 = new ModelRenderer(this, 24, 50);
        this.Shape3.addBox(-1.0F, 15.0F, 5.5F, 2, 9, 1);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(64, 64);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        this.Shape4 = new ModelRenderer(this, 24, 49);
        this.Shape4.addBox(5.5F, 15.0F, -1.0F, 1, 9, 2);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(64, 64);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        this.Shape5 = new ModelRenderer(this, 24, 49);
        this.Shape5.addBox(-6.5F, 15.0F, -1.0F, 1, 9, 2);
        this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape5.setTextureSize(64, 64);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
        this.Shape6 = new ModelRenderer(this, 0, 61);
        this.Shape6.addBox(-6.5F, 14.5F, -1.0F, 13, 1, 2);
        this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape6.setTextureSize(64, 64);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        this.Shape7 = new ModelRenderer(this, 0, 50);
        this.Shape7.addBox(-1.0F, 14.5F, -6.5F, 2, 1, 13);
        this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape7.setTextureSize(64, 64);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
        this.Shape8 = new ModelRenderer(this, 0, 60);
        this.Shape8.addBox(-1.0F, 12.5F, -1.0F, 2, 2, 2);
        this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape8.setTextureSize(64, 64);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
        this.Shape9 = new ModelRenderer(this, 0, 58);
        this.Shape9.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 5);
        this.Shape9.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.Shape9.setTextureSize(64, 64);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, -0.1396263F, ((float)Math.PI / 4F), 0.0F);
        this.Shape10 = new ModelRenderer(this, 0, 58);
        this.Shape10.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 5);
        this.Shape10.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.Shape10.setTextureSize(64, 64);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.3665191F, ((float)Math.PI / 4F), 0.0F);
        this.Shape11 = new ModelRenderer(this, 0, 58);
        this.Shape11.addBox(-1.0F, 0.0F, 5.0F, 2, 1, 2);
        this.Shape11.setRotationPoint(0.0F, 9.5F, 0.0F);
        this.Shape11.setTextureSize(64, 64);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, -0.3665191F, ((float)Math.PI / 4F), 0.0F);
        this.Shape12 = new ModelRenderer(this, 0, 58);
        this.Shape12.addBox(-1.0F, 0.0F, 4.5F, 2, 1, 2);
        this.Shape12.setRotationPoint(0.0F, 15.5F, 0.0F);
        this.Shape12.setTextureSize(64, 64);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.3839724F, ((float)Math.PI / 4F), 0.0F);
        this.Shape13 = new ModelRenderer(this, 0, 58);
        this.Shape13.addBox(-1.0F, 12.0F, 5.5F, 2, 2, 1);
        this.Shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape13.setTextureSize(64, 64);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0F, ((float)Math.PI / 4F), 0.0F);
        this.Shape14 = new ModelRenderer(this, 0, 58);
        this.Shape14.addBox(-1.0F, 0.0F, -5.0F, 2, 1, 5);
        this.Shape14.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.Shape14.setTextureSize(64, 64);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, -0.3665191F, ((float)Math.PI / 4F), 0.0F);
        this.Shape15 = new ModelRenderer(this, 0, 58);
        this.Shape15.addBox(-1.0F, 0.0F, -5.0F, 2, 1, 5);
        this.Shape15.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.Shape15.setTextureSize(64, 64);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.1396263F, ((float)Math.PI / 4F), 0.0F);
        this.Shape16 = new ModelRenderer(this, 0, 58);
        this.Shape16.addBox(-1.0F, 0.0F, -7.0F, 2, 1, 2);
        this.Shape16.setRotationPoint(0.0F, 9.5F, 0.0F);
        this.Shape16.setTextureSize(64, 64);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.3490659F, ((float)Math.PI / 4F), 0.0F);
        this.Shape17 = new ModelRenderer(this, 0, 58);
        this.Shape17.addBox(-1.0F, 0.0F, -6.5F, 2, 1, 2);
        this.Shape17.setRotationPoint(0.0F, 15.5F, 0.0F);
        this.Shape17.setTextureSize(64, 64);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, -0.3839724F, ((float)Math.PI / 4F), 0.0F);
        this.Shape18 = new ModelRenderer(this, 0, 58);
        this.Shape18.addBox(-1.0F, 12.0F, -6.5F, 2, 2, 1);
        this.Shape18.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape18.setTextureSize(64, 64);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, 0.0F, ((float)Math.PI / 4F), 0.0F);
        this.Shape19 = new ModelRenderer(this, 0, 61);
        this.Shape19.addBox(1.0F, 0.0F, -1.0F, 5, 1, 2);
        this.Shape19.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.Shape19.setTextureSize(64, 64);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.0F, ((float)Math.PI / 4F), 0.1570796F);
        this.Shape20 = new ModelRenderer(this, 0, 61);
        this.Shape20.addBox(-6.0F, 0.0F, -1.0F, 6, 1, 2);
        this.Shape20.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.Shape20.setTextureSize(64, 64);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, 0.0F, ((float)Math.PI / 4F), -0.1570796F);
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
        this.Shape19.render(f5);
        this.Shape20.render(f5);
    }
}

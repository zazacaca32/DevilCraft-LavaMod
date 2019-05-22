package net.minecraft.client.addon.spearaddon.bow;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ShurikenShotModel extends ModelBase
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
    ModelRenderer Shape21;
    ModelRenderer Shape22;
    ModelRenderer Shape23;
    ModelRenderer Shape24;
    ModelRenderer Shape25;
    ModelRenderer Shape26;
    ModelRenderer Shape27;
    ModelRenderer Shape28;
    ModelRenderer Shape29;
    ModelRenderer Alo;

    int enchant;

    public ShurikenShotModel()
    {
        textureWidth = 64;
        textureHeight = 64;
        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(0F, 0F, 0F, 9, 10, 1);
        Shape1.setRotationPoint(0.5F, 0F, 9F);
        Shape1.setTextureSize(64, 64);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 0);
        Shape2.addBox(0F, 0F, 0F, 1, 10, 1);
        Shape2.setRotationPoint(0.5F, 0F, 8F);
        Shape2.setTextureSize(64, 64);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 0);
        Shape3.addBox(0F, 0F, 0F, 1, 10, 8);
        Shape3.setRotationPoint(-0.5F, 0F, 1F);
        Shape3.setTextureSize(64, 64);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 0);
        Shape4.addBox(0F, 0F, 0F, 1, 10, 6);
        Shape4.setRotationPoint(-1.5F, 0F, 2F);
        Shape4.setTextureSize(64, 64);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 0, 2);
        Shape5.addBox(0F, 0F, 0F, 1, 9, 6);
        Shape5.setRotationPoint(-2.5F, 1F, 2F);
        Shape5.setTextureSize(64, 64);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 0, 0);
        Shape6.addBox(0F, 0F, 0F, 9, 10, 1);
        Shape6.setRotationPoint(0.5F, 0F, 0F);
        Shape6.setTextureSize(64, 64);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 0, 0);
        Shape7.addBox(0F, 0F, 0F, 1, 10, 8);
        Shape7.setRotationPoint(9.5F, 0F, 1F);
        Shape7.setTextureSize(64, 64);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
        Shape8 = new ModelRenderer(this, 0, 0);
        Shape8.addBox(0F, 0F, 0F, 1, 10, 1);
        Shape8.setRotationPoint(8.5F, 0F, 8F);
        Shape8.setTextureSize(64, 64);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, 0F, 0F);
        Shape9 = new ModelRenderer(this, 0, 0);
        Shape9.addBox(0F, 0F, 0F, 1, 10, 1);
        Shape9.setRotationPoint(0.5F, 0F, 1F);
        Shape9.setTextureSize(64, 64);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, 0F, 0F);
        Shape10 = new ModelRenderer(this, 0, 0);
        Shape10.addBox(0F, 0F, 0F, 1, 10, 1);
        Shape10.setRotationPoint(8.5F, 0F, 1F);
        Shape10.setTextureSize(64, 64);
        Shape10.mirror = true;
        setRotation(Shape10, 0F, 0F, 0F);
        Shape11 = new ModelRenderer(this, 0, 0);
        Shape11.addBox(0F, 0F, 0F, 1, 12, 6);
        Shape11.setRotationPoint(-3.5F, 4F, 2F);
        Shape11.setTextureSize(64, 64);
        Shape11.mirror = true;
        setRotation(Shape11, 0F, 0F, 0F);
        Shape12 = new ModelRenderer(this, 36, 4);
        Shape12.addBox(0F, 0F, 0F, 1, 12, 8);
        Shape12.setRotationPoint(-4.5F, 4F, 1F);
        Shape12.setTextureSize(64, 64);
        Shape12.mirror = true;
        setRotation(Shape12, 0F, 0F, 0F);
        Shape13 = new ModelRenderer(this, 24, 30);
        Shape13.addBox(0F, 0F, 0F, 2, 9, 6);
        Shape13.setRotationPoint(-4F, 5F, 1F);
        Shape13.setTextureSize(64, 64);
        Shape13.mirror = true;
        setRotation(Shape13, -0.5235988F, 0F, 0F);
        Shape14 = new ModelRenderer(this, 20, 0);
        Shape14.addBox(0F, 0F, 0F, 2, 2, 2);
        Shape14.setRotationPoint(-5F, 3.5F, 4F);
        Shape14.setTextureSize(64, 64);
        Shape14.mirror = true;
        setRotation(Shape14, 0F, 0F, 0F);
        Shape15 = new ModelRenderer(this, 0, 52);
        Shape15.addBox(0F, 0F, 0F, 1, 7, 1);
        Shape15.setRotationPoint(-4F, 15.3F, 6.5F);
        Shape15.setTextureSize(64, 64);
        Shape15.mirror = true;
        setRotation(Shape15, 0F, 0F, 0F);
        Shape16 = new ModelRenderer(this, 0, 52);
        Shape16.addBox(0F, 0F, 0F, 1, 9, 1);
        Shape16.setRotationPoint(-4F, 15.2F, 1.8F);
        Shape16.setTextureSize(64, 64);
        Shape16.mirror = true;
        setRotation(Shape16, 0.5759587F, 0F, 0F);
        Shape17 = new ModelRenderer(this, 0, 1);
        Shape17.addBox(0F, 0F, 0F, 1, 12, 6);
        Shape17.setRotationPoint(-3.5F, 2F, 2F);
        Shape17.setTextureSize(64, 64);
        Shape17.mirror = true;
        setRotation(Shape17, 0F, 0F, 0F);
        Shape18 = new ModelRenderer(this, 20, 0);
        Shape18.addBox(0F, 0F, 0F, 2, 7, 2);
        Shape18.setRotationPoint(4F, 5.5F, -1F);
        Shape18.setTextureSize(64, 64);
        setRotation(Shape18, 0F, 0F, 0F);
        Shape18.mirror = false;
        Shape19 = new ModelRenderer(this, 31, 30);
        Shape19.addBox(-8F, 0F, -5F, 2, 9, 6);
        Shape19.setRotationPoint(4F, 5.5F, 8.1F);
        Shape19.setTextureSize(64, 64);
        Shape19.mirror = true;
        setRotation(Shape19, 0.5235988F, 0F, 0F);
        Shape20 = new ModelRenderer(this, 1, 54);
        Shape20.addBox(-4F, 16F, 3F, 1, 1, 4);
        Shape20.setRotationPoint(0F, 0F, 0F);
        Shape20.setTextureSize(64, 64);
        Shape20.mirror = true;
        setRotation(Shape20, 0F, 0F, 0F);
        Shape21 = new ModelRenderer(this, 1, 51);
        Shape21.addBox(-4F, 17F, 4F, 1, 1, 3);
        Shape21.setRotationPoint(0F, 0F, 0F);
        Shape21.setTextureSize(64, 64);
        Shape21.mirror = true;
        setRotation(Shape21, 0F, 0F, 0F);
        Shape22 = new ModelRenderer(this, 1, 54);
        Shape22.addBox(-4F, 18F, 4.5F, 1, 1, 2);
        Shape22.setRotationPoint(0F, 0F, 0F);
        Shape22.setTextureSize(64, 64);
        Shape22.mirror = true;
        setRotation(Shape22, 0F, 0F, 0F);
        Shape23 = new ModelRenderer(this, 1, 56);
        Shape23.addBox(-4F, 19F, 5.3F, 1, 1, 2);
        Shape23.setRotationPoint(0F, 0F, 0F);
        Shape23.setTextureSize(64, 64);
        Shape23.mirror = true;
        setRotation(Shape23, 0F, 0F, 0F);
        Shape24 = new ModelRenderer(this, 1, 53);
        Shape24.addBox(0F, 20F, 6F, 1, 1, 1);
        Shape24.setRotationPoint(-4F, 0F, 0F);
        Shape24.setTextureSize(64, 64);
        Shape24.mirror = true;
        setRotation(Shape24, 0F, 0F, 0F);
        Shape25 = new ModelRenderer(this, 1, 56);
        Shape25.addBox(-3.5F, 14.7F, -5F, 1, 1, 6);
        Shape25.setRotationPoint(0F, 0F, 1F);
        Shape25.setTextureSize(64, 64);
        Shape25.mirror = true;
        setRotation(Shape25, 0F, 0F, 0F);
        Shape26 = new ModelRenderer(this, 2, 56);
        Shape26.addBox(-3.5F, 10.1F, -11.8F, 1, 1, 4);
        Shape26.setRotationPoint(0F, 0F, 0F);
        Shape26.setTextureSize(64, 64);
        Shape26.mirror = true;
        setRotation(Shape26, 0.5759587F, 0F, 0F);
        Shape27 = new ModelRenderer(this, 2, 56);
        Shape27.addBox(-3.5F, 13.7F, -2.5F, 1, 1, 3);
        Shape27.setRotationPoint(0F, 0F, 0F);
        Shape27.setTextureSize(64, 64);
        Shape27.mirror = true;
        setRotation(Shape27, 0F, 0F, 0F);
        Shape28 = new ModelRenderer(this, 0, 0);
        Shape28.addBox(-1F, 1F, 1.5F, 1, 8, 1);
        Shape28.setRotationPoint(0F, 0F, 0F);
        Shape28.setTextureSize(64, 64);
        Shape28.mirror = true;
        setRotation(Shape28, 0F, 0F, 0F);
        Shape29 = new ModelRenderer(this, 0, 0);
        Shape29.addBox(-1F, 1F, 7.5F, 1, 8, 1);
        Shape29.setRotationPoint(0F, 0F, 0F);
        Shape29.setTextureSize(64, 64);
        Shape29.mirror = true;
        Alo = new ModelRenderer(this, 0, 0);
        Alo.addBox(-4F, 3F, 3.5F, 1, 1, 3);
        Alo.setRotationPoint(0F, 0F, 0F);
        Alo.setTextureSize(64, 64);
        Alo.mirror = true;
    }

    public void render(int flag)
    {
        float f5 = 0.0625F;
        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        Shape7.render(f5);
        Shape8.render(f5);
        Shape9.render(f5);
        Shape10.render(f5);
        Shape11.render(f5);
        Shape12.render(f5);
        Shape13.render(f5);
        Shape14.render(f5);
        Shape15.render(f5);
        Shape16.render(f5);
        Shape17.render(f5);
        Shape18.render(f5);
        Shape19.render(f5);
        Shape20.render(f5);
        Shape21.render(f5);
        Shape22.render(f5);
        Shape23.render(f5);
        Shape24.render(f5);
        Shape25.render(f5);
        Shape26.render(f5);
        Shape27.render(f5);
        Shape28.render(f5);
        Shape29.render(f5);
        Alo.render(f5);

        if (flag == 0)
        {
        }
        else if (flag == 1)
        {
        }
        else if (flag == 2)
        {
        }
        else if (flag == 3)
        {
        }
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}

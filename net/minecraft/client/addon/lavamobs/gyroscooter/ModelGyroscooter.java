package net.minecraft.client.addon.lavamobs.gyroscooter;

import net.minecraft.client.addon.lavamobs.LavaModMobs;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelGyroscooter extends ModelBase
{
    ModelRenderer LeftWheel;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer RightWheel;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Podium;
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

    public ModelGyroscooter()
    {
        super.textureWidth = 32;
        super.textureHeight = 32;
        this.LeftWheel = new ModelRenderer(this, 0, 24);
        this.LeftWheel.addBox(-13.0F, -2.0F, -2.0F, 3, 4, 4);
        this.LeftWheel.setRotationPoint(0.0F, 21.0F, 0.0F);
        this.LeftWheel.setTextureSize(32, 32);
        this.LeftWheel.mirror = true;
        this.setRotation(this.LeftWheel, 0.0F, 0.0F, 0.0F);
        this.Shape2 = new ModelRenderer(this, 14, 22);
        this.Shape2.addBox(-12.5F, -2.5F, -2.5F, 2, 5, 5);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape2.setTextureSize(32, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        this.Shape3 = new ModelRenderer(this, 14, 22);
        this.Shape3.addBox(-12.5F, -3.0F, -2.0F, 2, 6, 4);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(32, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        this.Shape4 = new ModelRenderer(this, 14, 22);
        this.Shape4.addBox(-12.5F, -2.0F, -3.0F, 2, 4, 6);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(32, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        this.RightWheel = new ModelRenderer(this, 0, 24);
        this.RightWheel.addBox(10.0F, -2.0F, -2.0F, 3, 4, 4);
        this.RightWheel.setRotationPoint(0.0F, 21.0F, 0.0F);
        this.RightWheel.setTextureSize(32, 32);
        this.RightWheel.mirror = true;
        this.setRotation(this.RightWheel, 0.0F, 0.0F, 0.0F);
        this.Shape6 = new ModelRenderer(this, 14, 22);
        this.Shape6.addBox(10.5F, -2.5F, -2.5F, 2, 5, 5);
        this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape6.setTextureSize(32, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        this.Shape7 = new ModelRenderer(this, 14, 22);
        this.Shape7.addBox(10.5F, -3.0F, -2.0F, 2, 6, 4);
        this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape7.setTextureSize(32, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
        this.Shape8 = new ModelRenderer(this, 14, 22);
        this.Shape8.addBox(10.5F, -2.0F, -3.0F, 2, 4, 6);
        this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape8.setTextureSize(32, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
        this.Podium = new ModelRenderer(this, 0, 21);
        this.Podium.addBox(-13.5F, -0.5F, -0.5F, 5, 1, 1);
        this.Podium.setRotationPoint(0.0F, 21.0F, 0.0F);
        this.Podium.setTextureSize(32, 32);
        this.Podium.mirror = true;
        this.setRotation(this.Podium, 0.0F, 0.0F, 0.0F);
        this.Shape10 = new ModelRenderer(this, 0, 16);
        this.Shape10.addBox(-10.0F, -1.0F, -2.0F, 10, 1, 4);
        this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape10.setTextureSize(32, 32);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        this.Shape11 = new ModelRenderer(this, 0, 6);
        this.Shape11.addBox(-8.5F, -1.5F, -3.0F, 8, 2, 6);
        this.Shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape11.setTextureSize(32, 32);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
        this.Shape12 = new ModelRenderer(this, 0, 6);
        this.Shape12.addBox(0.5F, -1.5F, -3.0F, 8, 2, 6);
        this.Shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape12.setTextureSize(32, 32);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
        this.Shape13 = new ModelRenderer(this, 0, 6);
        this.Shape13.addBox(-12.0F, -4.5F, -3.0F, 3, 1, 6);
        this.Shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape13.setTextureSize(32, 32);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
        this.Shape14 = new ModelRenderer(this, 0, 6);
        this.Shape14.addBox(-8.5F, -6.466667F, -2.1F, 1, 4, 4);
        this.Shape14.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape14.setTextureSize(32, 32);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0F, 0.0F, -0.2792527F);
        this.Shape15 = new ModelRenderer(this, 0, 6);
        this.Shape15.addBox(7.5F, -6.466667F, -2.1F, 1, 4, 4);
        this.Shape15.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape15.setTextureSize(32, 32);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0F, 0.0F, 0.2792527F);
        this.Shape16 = new ModelRenderer(this, 0, 6);
        this.Shape16.addBox(9.0F, -4.5F, -3.0F, 3, 1, 6);
        this.Shape16.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape16.setTextureSize(32, 32);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0F);
        this.Shape17 = new ModelRenderer(this, 0, 21);
        this.Shape17.addBox(8.5F, -0.5F, -0.5F, 5, 1, 1);
        this.Shape17.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape17.setTextureSize(32, 32);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.0F, 0.0F, 0.0F);
        this.Shape18 = new ModelRenderer(this, 0, 0);
        this.Shape18.addBox(1.0F, -2.0F, -3.5F, 6, 2, 7);
        this.Shape18.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape18.setTextureSize(32, 32);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
        this.Shape19 = new ModelRenderer(this, 0, 0);
        this.Shape19.addBox(-7.0F, -2.0F, -3.5F, 6, 2, 7);
        this.Shape19.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape19.setTextureSize(32, 32);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.0F, 0.0F, 0.0F);
        this.Shape20 = new ModelRenderer(this, 0, 16);
        this.Shape20.addBox(0.0F, -1.0F, -2.0F, 10, 1, 4);
        this.Shape20.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape20.setTextureSize(32, 32);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, 0.0F, 0.0F, 0.0F);
        this.LeftWheel.addChild(this.Shape2);
        this.LeftWheel.addChild(this.Shape3);
        this.LeftWheel.addChild(this.Shape4);
        this.RightWheel.addChild(this.Shape6);
        this.RightWheel.addChild(this.Shape7);
        this.RightWheel.addChild(this.Shape8);
        this.Podium.addChild(this.Shape10);
        this.Podium.addChild(this.Shape11);
        this.Podium.addChild(this.Shape12);
        this.Podium.addChild(this.Shape13);
        this.Podium.addChild(this.Shape14);
        this.Podium.addChild(this.Shape15);
        this.Podium.addChild(this.Shape16);
        this.Podium.addChild(this.Shape17);
        this.Podium.addChild(this.Shape18);
        this.Podium.addChild(this.Shape19);
        this.Podium.addChild(this.Shape20);
    }

    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        super.render(par1Entity, par2, par3, par4, par5, par6, par7);
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        this.LeftWheel.render(par7);
        this.RightWheel.render(par7);
        byte i = ((EntityGyroscooter)par1Entity).getColor();

        if (i > 0 && i < 4)
        {
            int var8 = LavaModMobs.ColorsE[i - 1];
            GL11.glColor4f((float)(var8 >> 16) / 255.0F, (float)(var8 >> 8 & 255) / 255.0F, (float)(var8 & 255) / 255.0F, 1.0F);
        }

        this.Podium.render(par7);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    protected float degToRad(float degrees)
    {
        return degrees * (float)Math.PI / 180.0F;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
        this.LeftWheel.rotateAngleX = this.degToRad(par1 * 20.0F);
        this.RightWheel.rotateAngleX = this.degToRad(par1 * 20.0F);
    }
}

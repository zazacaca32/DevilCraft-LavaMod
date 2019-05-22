package net.minecraft.client.addon.lavamobs.model;

import net.minecraft.client.addon.lavamobs.entity.EntityMondayKun;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelMondayKun extends ModelBase
{
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
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

    public ModelMondayKun()
    {
        super.textureWidth = 64;
        super.textureHeight = 64;
        this.Shape1 = new ModelRenderer(this, 0, 33);
        this.Shape1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
        this.Shape1.setRotationPoint(-1.0F, -3.0F, -4.5F);
        this.Shape1.setTextureSize(64, 64);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.2268928F);
        this.Shape2 = new ModelRenderer(this, 0, 33);
        this.Shape2.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
        this.Shape2.setRotationPoint(-3.0F, -1.0F, -4.5F);
        this.Shape2.setTextureSize(64, 64);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, -((float)Math.PI * 2F / 9F));
        this.Shape3 = new ModelRenderer(this, 0, 34);
        this.Shape3.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
        this.Shape3.setRotationPoint(-2.0F, -1.2F, -4.5F);
        this.Shape3.setTextureSize(64, 64);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.1570796F);
        this.Shape4 = new ModelRenderer(this, 0, 33);
        this.Shape4.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
        this.Shape4.setRotationPoint(-0.2F, -0.8F, -4.5F);
        this.Shape4.setTextureSize(64, 64);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, -((float)Math.PI / 10F));
        this.Shape5 = new ModelRenderer(this, 14, 34);
        this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.Shape5.setRotationPoint(0.4F, -1.0F, -5.0F);
        this.Shape5.setTextureSize(64, 64);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, ((float)Math.PI / 4F), 0.0F, 0.0F);
        this.Shape6 = new ModelRenderer(this, 12, 35);
        this.Shape6.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
        this.Shape6.setRotationPoint(-1.0F, -1.0F, -4.4F);
        this.Shape6.setTextureSize(64, 64);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, ((float)Math.PI / 4F), 0.0F, 0.0F);
        this.Shape7 = new ModelRenderer(this, 12, 35);
        this.Shape7.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
        this.Shape7.setRotationPoint(-1.0F, -1.8F, -4.4F);
        this.Shape7.setTextureSize(64, 64);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, ((float)Math.PI / 4F), 0.0F, 0.0F);
        this.Shape8 = new ModelRenderer(this, 21, 33);
        this.Shape8.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
        this.Shape8.setRotationPoint(-3.5F, -5.2F, -4.1F);
        this.Shape8.setTextureSize(64, 64);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
        this.Shape9 = new ModelRenderer(this, 21, 33);
        this.Shape9.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
        this.Shape9.setRotationPoint(0.5F, -5.2F, -4.1F);
        this.Shape9.setTextureSize(64, 64);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
        this.Shape10 = new ModelRenderer(this, 21, 33);
        this.Shape10.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.Shape10.setRotationPoint(-0.5F, -4.7F, -4.1F);
        this.Shape10.setTextureSize(64, 64);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        this.Shape11 = new ModelRenderer(this, 1, 40);
        this.Shape11.addBox(0.0F, 0.0F, -3.0F, 1, 1, 3);
        this.Shape11.setRotationPoint(1.0F, 2.0F, -1.5F);
        this.Shape11.setTextureSize(64, 64);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, -0.4014257F, -0.2443461F, -0.1919862F);
        this.Shape12 = new ModelRenderer(this, 0, 40);
        this.Shape12.addBox(0.0F, 0.0F, -3.0F, 2, 3, 2);
        this.Shape12.setRotationPoint(0.0F, 1.0F, -0.5F);
        this.Shape12.setTextureSize(64, 64);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0698132F, 0.0872665F, 0.0F);
        this.Shape13 = new ModelRenderer(this, 0, 40);
        this.Shape13.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
        this.Shape13.setRotationPoint(1.0F, 3.0F, -3.0F);
        this.Shape13.setTextureSize(64, 64);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.4914095F, -2.091787F, -0.122173F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.setTextureSize(64, 64);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 16, 16);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.setTextureSize(64, 64);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
        this.rightarm = new ModelRenderer(this, 40, 16);
        this.rightarm.addBox(-3.0F, -2.0F, -2.0F, 4, 11, 4);
        this.rightarm.setRotationPoint(-4.0F, 2.0F, 0.0F);
        this.rightarm.setTextureSize(64, 64);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0F, 0.0F, 0.1919862F);
        this.leftarm = new ModelRenderer(this, 40, 16);
        this.leftarm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4);
        this.leftarm.setRotationPoint(4.0F, 2.0F, 0.0F);
        this.leftarm.setTextureSize(64, 64);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0F, 0.0F, -0.122173F);
        this.rightleg = new ModelRenderer(this, 0, 16);
        this.rightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
        this.rightleg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.rightleg.setTextureSize(64, 64);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0349066F, 0.0F, 0.0349066F);
        this.leftleg = new ModelRenderer(this, 0, 16);
        this.leftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
        this.leftleg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.leftleg.setTextureSize(64, 64);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, -0.0174533F, -0.0872665F, -0.0135748F);
        this.head.addChild(this.Shape1);
        this.head.addChild(this.Shape2);
        this.head.addChild(this.Shape3);
        this.head.addChild(this.Shape4);
        this.head.addChild(this.Shape5);
        this.head.addChild(this.Shape6);
        this.head.addChild(this.Shape7);
        this.head.addChild(this.Shape8);
        this.head.addChild(this.Shape9);
        this.head.addChild(this.Shape10);
        this.body.addChild(this.Shape11);
        this.body.addChild(this.Shape12);
        this.body.addChild(this.Shape13);
    }

    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        super.render(par1Entity, par2, par3, par4, par5, par6, par7);
        this.setRotationAngles(par1Entity, par2, par3, par4, par5, par6, par7);
        this.head.render(par7);
        this.body.render(par7);
        this.rightarm.render(par7);
        this.leftarm.render(par7);
        this.rightleg.render(par7);
        this.leftleg.render(par7);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setLivingAnimations(EntityMondayKun par1EntityLiving, float par2, float par3, float par4)
    {
        super.setLivingAnimations(par1EntityLiving, par2, par3, par4);
        int attack = par1EntityLiving.smashCounter / 10;
        this.rightarm.rotateAngleX = (float)attack;
        this.leftarm.rotateAngleX = (float)attack;
    }

    public void setRotationAngles(Entity par1Entity, float f1, float f2, float f3, float f4, float f5, float f6)
    {
        float flag = ((EntityMondayKun)par1Entity).getSwingProgressq(f6);
        this.head.rotateAngleY = f4 / (180F / (float)Math.PI);
        this.head.rotateAngleX = f5 / (180F / (float)Math.PI);
        this.rightarm.rotateAngleX = MathHelper.cos(f1 * 0.6662F + (float)Math.PI) * 2.0F * f2 * 0.5F;
        this.rightarm.rotateAngleZ = 0.0F;
        this.rightarm.rotateAngleZ += MathHelper.cos(f3 * 0.09F) * 0.05F + 0.05F;
        this.rightarm.rotateAngleX += MathHelper.sin(f3 * 0.067F) * 0.05F;

        if (flag > 0.0F)
        {
            this.rightarm.rotateAngleX = -0.7F;
            this.rightarm.rotateAngleZ = 0.2F;
            this.rightarm.rotateAngleY = -0.5F;
            this.rightarm.rotateAngleY = MathHelper.cos(f3 * 0.6F) * 0.05F * 5.0F;
            this.rightarm.rotateAngleZ = MathHelper.cos(f3 * 0.6F) * 0.05F * 5.0F;
            this.rightarm.rotateAngleX -= MathHelper.sin(f3 * 0.6F) * 0.05F * 18.0F;
            this.leftarm.rotateAngleX = -0.7F;
            this.leftarm.rotateAngleZ = -0.2F;
            this.leftarm.rotateAngleY = 0.5F;
            this.leftarm.rotateAngleY -= MathHelper.cos(f3 * 0.6F) * 0.05F * 5.0F;
            this.leftarm.rotateAngleZ -= MathHelper.cos(f3 * 0.6F) * 0.05F * 5.0F;
            this.leftarm.rotateAngleX -= MathHelper.sin(f3 * 0.6F) * 0.05F * 18.0F;
        }
        else
        {
            this.rightarm.rotateAngleX = MathHelper.cos(f1 * 0.6662F + (float)Math.PI) * 2.0F * f2 * 0.5F;
            this.rightarm.rotateAngleZ = 0.0F;
            this.rightarm.rotateAngleZ += MathHelper.cos(f3 * 0.09F) * 0.05F + 0.05F;
            this.rightarm.rotateAngleX += MathHelper.sin(f3 * 0.067F) * 0.05F;
            this.leftarm.rotateAngleX = MathHelper.cos(f1 * 0.6662F) * 2.0F * f2 * 0.5F;
            this.leftarm.rotateAngleZ = 0.0F;
            this.leftarm.rotateAngleZ -= MathHelper.cos(f3 * 0.09F) * 0.05F + 0.05F;
            this.leftarm.rotateAngleX -= MathHelper.sin(f3 * 0.067F) * 0.05F;
        }

        this.rightleg.rotateAngleX = MathHelper.cos(f1 * 0.6662F) * 1.4F * f2;
        this.leftleg.rotateAngleX = MathHelper.cos(f1 * 0.6662F + (float)Math.PI) * 1.4F * f2;
        this.rightleg.rotateAngleY = 0.0F;
        this.leftleg.rotateAngleY = 0.0F;
        this.rightarm.rotateAngleY = 0.0F;
    }
}

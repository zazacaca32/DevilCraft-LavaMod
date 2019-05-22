package net.minecraft.client.addon.lavamobs.model;

import net.minecraft.client.addon.lavamobs.entity.EntityDendroid;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelDendroid extends ModelBase
{
    ModelRenderer Body_Front;
    ModelRenderer Body_Right;
    ModelRenderer Body_Left;
    ModelRenderer Body_Back;
    ModelRenderer ArmPlate_L;
    ModelRenderer Head;
    ModelRenderer ArmPlate_R;
    ModelRenderer Leg_F;
    ModelRenderer Leg_B;
    ModelRenderer Leg_R;
    ModelRenderer Leg_L;
    ModelRenderer Eye;
    ModelRenderer Leg_FE;
    ModelRenderer Leg_RE;
    ModelRenderer Leg_LE;
    ModelRenderer Leg_BE;
    ModelRenderer Arm_R;
    ModelRenderer Arm_RE;
    ModelRenderer Vines;
    ModelRenderer VineArm_1a;
    ModelRenderer VineArm_2a;
    ModelRenderer VineArm_3a;
    ModelRenderer VineArm_4a;
    ModelRenderer VineArm_4b;
    ModelRenderer VineArm_3b;
    ModelRenderer VineArm_2b;
    ModelRenderer VineArm_1b;
    double wiggleY = 0.7D;
    double wiggleZ = 0.65D;
    float bSpeed = 1.2F;

    public ModelDendroid()
    {
        super.textureWidth = 128;
        super.textureHeight = 128;
        this.Body_Front = new ModelRenderer(this, 0, 75);
        this.Body_Front.addBox(-3.0F, 0.0F, -3.0F, 6, 16, 6);
        this.Body_Front.setRotationPoint(-1.0F, -4.0F, -4.0F);
        this.Body_Front.setTextureSize(128, 128);
        this.Body_Front.mirror = true;
        this.setRotation(this.Body_Front, 0.2230717F, 0.0F, 0.0F);
        this.Body_Right = new ModelRenderer(this, 0, 21);
        this.Body_Right.addBox(0.0F, 0.0F, 1.0F, 6, 20, 6);
        this.Body_Right.setRotationPoint(7.0F, -5.0F, -4.0F);
        this.Body_Right.setTextureSize(128, 128);
        this.Body_Right.mirror = true;
        this.setRotation(this.Body_Right, 0.2230717F, -1.226894F, 0.0F);
        this.Body_Left = new ModelRenderer(this, 0, 47);
        this.Body_Left.addBox(0.0F, 0.0F, 0.0F, 6, 22, 6);
        this.Body_Left.setRotationPoint(-9.0F, -8.0F, 0.0F);
        this.Body_Left.setTextureSize(128, 128);
        this.Body_Left.mirror = true;
        this.setRotation(this.Body_Left, 0.2230717F, 0.7269296F, 0.0F);
        this.Body_Back = new ModelRenderer(this, 56, 0);
        this.Body_Back.addBox(0.0F, 0.0F, 0.0F, 6, 27, 7);
        this.Body_Back.setRotationPoint(-3.0F, -25.0F, 0.0F);
        this.Body_Back.setTextureSize(128, 128);
        this.Body_Back.mirror = true;
        this.setRotation(this.Body_Back, -0.1115358F, 0.0F, 0.0F);
        this.ArmPlate_L = new ModelRenderer(this, 82, 0);
        this.ArmPlate_L.addBox(0.0F, 0.0F, 0.0F, 10, 21, 13);
        this.ArmPlate_L.setRotationPoint(-20.0F, -21.0F, -5.0F);
        this.ArmPlate_L.setTextureSize(128, 128);
        this.ArmPlate_L.mirror = true;
        this.setRotation(this.ArmPlate_L, 0.0F, 0.0F, -0.4833219F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.addBox(0.0F, 0.0F, 0.0F, 8, 14, 7);
        this.Head.setRotationPoint(-4.0F, -19.0F, -7.0F);
        this.Head.setTextureSize(128, 128);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0F, 0.0F, 0.0F);
        this.ArmPlate_R = new ModelRenderer(this, 26, 0);
        this.ArmPlate_R.addBox(0.0F, 0.0F, 0.0F, 8, 14, 7);
        this.ArmPlate_R.setRotationPoint(10.0F, -18.0F, -4.0F);
        this.ArmPlate_R.setTextureSize(128, 128);
        this.ArmPlate_R.mirror = true;
        this.setRotation(this.ArmPlate_R, 0.0F, 0.0F, 0.8179241F);
        this.Leg_F = new ModelRenderer(this, 31, 71);
        this.Leg_F.addBox(-3.0F, -3.0F, -12.0F, 6, 6, 12);
        this.Leg_F.setRotationPoint(-3.0F, 12.0F, 2.0F);
        this.Leg_F.setTextureSize(128, 128);
        this.Leg_F.mirror = true;
        this.setRotation(this.Leg_F, 0.0F, 0.0F, 0.0F);
        this.Leg_B = new ModelRenderer(this, 58, 35);
        this.Leg_B.addBox(-3.0F, -3.0F, 0.0F, 6, 6, 13);
        this.Leg_B.setRotationPoint(1.0F, 11.0F, 2.0F);
        this.Leg_B.setTextureSize(128, 128);
        this.Leg_B.mirror = true;
        this.setRotation(this.Leg_B, 0.0F, 0.0F, 0.0F);
        this.Leg_R = new ModelRenderer(this, 96, 35);
        this.Leg_R.addBox(0.0F, -2.5F, -2.0F, 12, 5, 4);
        this.Leg_R.setRotationPoint(2.0F, 13.0F, 0.0F);
        this.Leg_R.setTextureSize(128, 128);
        this.Leg_R.mirror = true;
        this.setRotation(this.Leg_R, 0.0F, 0.0F, 0.0F);
        this.Leg_L = new ModelRenderer(this, 29, 35);
        this.Leg_L.addBox(-10.0F, -2.5F, -2.0F, 10, 5, 5);
        this.Leg_L.setRotationPoint(-4.0F, 12.0F, 2.0F);
        this.Leg_L.setTextureSize(128, 128);
        this.Leg_L.mirror = true;
        this.setRotation(this.Leg_L, 0.0F, 0.0F, 0.0F);
        this.Eye = new ModelRenderer(this, 118, 0);
        this.Eye.addBox(-2.0F, -2.0F, -1.0F, 4, 4, 1);
        this.Eye.setRotationPoint(0.0F, -11.0F, -6.5F);
        this.Eye.setTextureSize(128, 128);
        this.Eye.mirror = true;
        this.setRotation(this.Eye, 0.0F, 0.0F, 0.0F);
        this.Leg_FE = new ModelRenderer(this, 35, 102);
        this.Leg_FE.addBox(-2.0F, -2.0F, -9.0F, 5, 4, 9);
        this.Leg_FE.setRotationPoint(-0.5F, 1.0F, -11.0F);
        this.Leg_FE.setTextureSize(128, 128);
        this.Leg_FE.mirror = true;
        this.setRotation(this.Leg_FE, 0.0F, 0.0F, 0.0F);
        this.Leg_RE = new ModelRenderer(this, 31, 93);
        this.Leg_RE.addBox(0.0F, -2.0F, -3.0F, 7, 4, 5);
        this.Leg_RE.setRotationPoint(11.0F, 1.0F, 0.5F);
        this.Leg_RE.setTextureSize(128, 128);
        this.Leg_RE.mirror = false;
        this.setRotation(this.Leg_RE, 0.0F, 0.0F, 0.0F);
        this.Leg_LE = new ModelRenderer(this, 31, 93);
        this.Leg_LE.addBox(-7.0F, -1.0F, -2.0F, 7, 4, 5);
        this.Leg_LE.setRotationPoint(-9.0F, -1.0F, 0.0F);
        this.Leg_LE.setTextureSize(128, 128);
        this.Leg_LE.mirror = true;
        this.setRotation(this.Leg_LE, 0.0F, 0.0F, 0.0F);
        this.Leg_BE = new ModelRenderer(this, 35, 102);
        this.Leg_BE.addBox(-3.0F, -2.0F, 0.0F, 5, 4, 9);
        this.Leg_BE.setRotationPoint(0.0F, 1.26667F, 13.0F);
        this.Leg_BE.setTextureSize(128, 128);
        this.Leg_BE.mirror = true;
        this.setRotation(this.Leg_BE, 0.0F, 0.0F, 0.0F);
        this.Arm_R = new ModelRenderer(this, 69, 57);
        this.Arm_R.addBox(-2.0F, 0.0F, -2.0F, 4, 15, 4);
        this.Arm_R.setRotationPoint(8.0F, -6.0F, 0.0F);
        this.Arm_R.setTextureSize(128, 128);
        this.Arm_R.mirror = true;
        this.setRotation(this.Arm_R, 0.0F, 0.0F, 0.0F);
        this.Arm_RE = new ModelRenderer(this, 86, 57);
        this.Arm_RE.addBox(-2.5F, -2.5F, -10.0F, 5, 5, 16);
        this.Arm_RE.setRotationPoint(0.0F, 14.0F, -2.0F);
        this.Arm_RE.setTextureSize(128, 128);
        this.Arm_RE.mirror = true;
        this.setRotation(this.Arm_RE, 0.0F, 0.0F, 0.0F);
        this.Vines = new ModelRenderer(this, 0, 102);
        this.Vines.addBox(0.0F, 0.0F, -5.0F, 4, 9, 13);
        this.Vines.setRotationPoint(-17.0F, -15.0F, 0.0F);
        this.Vines.setTextureSize(128, 128);
        this.Vines.mirror = true;
        this.setRotation(this.Vines, 0.0F, 0.0F, 0.0F);
        this.VineArm_1a = new ModelRenderer(this, 35, 120);
        this.VineArm_1a.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2);
        this.VineArm_1a.setRotationPoint(-10.0F, -4.0F, -2.0F);
        this.VineArm_1a.setTextureSize(128, 128);
        this.VineArm_1a.mirror = true;
        this.setRotation(this.VineArm_1a, 0.0F, 0.0F, 0.0F);
        this.VineArm_2a = new ModelRenderer(this, 35, 120);
        this.VineArm_2a.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2);
        this.VineArm_2a.setRotationPoint(-5.0F, 0.0F, 0.0F);
        this.VineArm_2a.setTextureSize(128, 128);
        this.VineArm_2a.mirror = true;
        this.setRotation(this.VineArm_2a, 0.0F, 0.0F, 0.0F);
        this.VineArm_3a = new ModelRenderer(this, 35, 120);
        this.VineArm_3a.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2);
        this.VineArm_3a.setRotationPoint(-5.0F, 0.0F, 0.0F);
        this.VineArm_3a.setTextureSize(128, 128);
        this.VineArm_3a.mirror = true;
        this.setRotation(this.VineArm_3a, 0.0F, 0.0F, 0.0F);
        this.VineArm_4a = new ModelRenderer(this, 35, 120);
        this.VineArm_4a.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2);
        this.VineArm_4a.setRotationPoint(-5.0F, 0.0F, 0.0F);
        this.VineArm_4a.setTextureSize(128, 128);
        this.VineArm_4a.mirror = true;
        this.setRotation(this.VineArm_4a, 0.0F, 0.0F, 0.0F);
        this.VineArm_4b = new ModelRenderer(this, 35, 120);
        this.VineArm_4b.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2);
        this.VineArm_4b.setRotationPoint(-5.0F, 0.0F, 0.0F);
        this.VineArm_4b.setTextureSize(128, 128);
        this.VineArm_4b.mirror = true;
        this.setRotation(this.VineArm_4b, 0.0F, 0.0F, 0.0F);
        this.VineArm_3b = new ModelRenderer(this, 35, 120);
        this.VineArm_3b.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2);
        this.VineArm_3b.setRotationPoint(-5.0F, 0.0F, 0.0F);
        this.VineArm_3b.setTextureSize(128, 128);
        this.VineArm_3b.mirror = true;
        this.setRotation(this.VineArm_3b, 0.0F, 0.0F, 0.0F);
        this.VineArm_2b = new ModelRenderer(this, 35, 120);
        this.VineArm_2b.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2);
        this.VineArm_2b.setRotationPoint(-5.0F, 0.0F, 0.0F);
        this.VineArm_2b.setTextureSize(128, 128);
        this.VineArm_2b.mirror = true;
        this.setRotation(this.VineArm_2b, 0.0F, 0.0F, 0.0F);
        this.VineArm_1b = new ModelRenderer(this, 35, 120);
        this.VineArm_1b.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2);
        this.VineArm_1b.setRotationPoint(-10.0F, -4.0F, 0.0F);
        this.VineArm_1b.setTextureSize(128, 128);
        this.VineArm_1b.mirror = true;
        this.setRotation(this.VineArm_1b, 0.0F, 0.0F, 0.0F);
        this.VineArm_1a.addChild(this.VineArm_2a);
        this.VineArm_2a.addChild(this.VineArm_3a);
        this.VineArm_3a.addChild(this.VineArm_4a);
        this.VineArm_1b.addChild(this.VineArm_2b);
        this.VineArm_2b.addChild(this.VineArm_3b);
        this.VineArm_3b.addChild(this.VineArm_4b);
        this.Leg_R.addChild(this.Leg_RE);
        this.Leg_L.addChild(this.Leg_LE);
        this.Leg_F.addChild(this.Leg_FE);
        this.Leg_B.addChild(this.Leg_BE);
        this.Arm_R.addChild(this.Arm_RE);
    }

    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        super.render(par1Entity, par2, par3, par4, par5, par6, par7);
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        this.Body_Front.render(par7);
        this.Body_Right.render(par7);
        this.Body_Left.render(par7);
        this.Body_Back.render(par7);
        this.ArmPlate_L.render(par7);
        this.Head.render(par7);
        this.ArmPlate_R.render(par7);
        this.Leg_F.render(par7);
        this.Leg_B.render(par7);
        this.Leg_R.render(par7);
        this.Leg_L.render(par7);
        this.Eye.render(par7);
        this.Arm_R.render(par7);

        if (par1Entity instanceof EntityDendroid)
        {
            this.Vines.render(par7);
        }

        this.VineArm_1a.render(par7);
        this.VineArm_1b.render(par7);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
        this.Leg_R.rotateAngleZ = 0.44F;
        this.Leg_RE.rotateAngleZ = 1.2F;
        this.Leg_R.rotateAngleY = MathHelper.cos(par1 * 0.8F) * 1.4F * par2;
        this.Leg_L.rotateAngleZ = -0.68F;
        this.Leg_LE.rotateAngleZ = -1.1F;
        this.Leg_L.rotateAngleY = MathHelper.cos((par1 + 5.0F) * 0.8F) * 1.4F * par2;
        this.Leg_F.rotateAngleX = 0.37F;
        this.Leg_FE.rotateAngleX = 0.96F - MathHelper.sin(par1 * 0.8F + (float)Math.PI) * 1.7F * par2;
        this.Leg_F.rotateAngleY = -MathHelper.cos(par1 * 0.4F + (float)Math.PI) * 0.7F * par2;
        this.Leg_B.rotateAngleX = -0.18F + MathHelper.cos(par1 * 0.8F + (float)Math.PI) * 0.5F * par2;
        this.Leg_BE.rotateAngleX = -1.1F - MathHelper.cos(par1 * 0.8F + (float)Math.PI) * 1.1F * par2;
        this.Arm_R.rotateAngleZ = -0.57F;
        this.Arm_R.rotateAngleX = MathHelper.cos(par1 * 0.8F + (float)Math.PI) * 1.4F * par2;
        this.Arm_RE.rotateAngleX = 0.35F;
        this.setArmRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
    }

    public void setArmRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        this.VineArm_1b.rotateAngleY = (float)(Math.cos(((double)par3 + 2.7D) * 1.0D * 0.6661999821662903D) * 2.0D * this.wiggleZ);
        this.VineArm_2b.rotateAngleY = (float)(Math.cos(((double)par3 + 2.7D) * 1.0D * 0.6661999821662903D) * (-this.wiggleY + 0.2D));
        this.VineArm_3b.rotateAngleY = this.bSpeed * (float)(Math.cos((double)par3 * 0.6D * 0.6661999821662903D) * (this.wiggleY - 0.35D));
        this.VineArm_4b.rotateAngleY = this.bSpeed * (float)(Math.cos((double)par3 * 0.6D * 0.6661999821662903D) * (this.wiggleY - 0.5D));
        this.VineArm_1b.rotateAngleZ = -0.7F + (float)(Math.sin(((double)par3 + 2.7D) * 1.0D * 0.6661999821662903D) * 2.0D * this.wiggleZ);
        this.VineArm_2b.rotateAngleZ = (float)(Math.sin(((double)par3 + 2.7D) * 1.0D * 0.6661999821662903D) * (-this.wiggleZ + 0.2D));
        this.VineArm_3b.rotateAngleZ = this.bSpeed * (float)(Math.cos((double)par3 * 0.15D * 0.6661999821662903D) * (this.wiggleZ - 0.35D));
        this.VineArm_4b.rotateAngleZ = this.bSpeed * (float)(Math.cos((double)par3 * 0.15D * 0.6661999821662903D) * (this.wiggleZ - 0.5D));
        this.VineArm_1a.rotateAngleY = (float)(Math.cos((double)(par3 * 1.0F * 0.6662F)) * 2.0D * this.wiggleZ);
        this.VineArm_2a.rotateAngleY = (float)(Math.cos((double)(par3 * 1.0F * 0.6662F)) * (-this.wiggleY + 0.2D));
        this.VineArm_3a.rotateAngleY = (float)(Math.cos((double)(par3 * 1.0F * 0.6662F)) * (-this.wiggleY + 0.35D));
        this.VineArm_4a.rotateAngleY = this.bSpeed * (float)(Math.cos((double)par3 * 0.6D * 0.6661999821662903D) * (this.wiggleY - 0.5D));
        this.VineArm_1a.rotateAngleZ = -0.7F + (float)(Math.sin((double)(par3 * 1.0F * 0.6662F)) * 2.0D * this.wiggleZ);
        this.VineArm_2a.rotateAngleZ = (float)(Math.sin((double)(par3 * 1.0F * 0.6662F)) * (-this.wiggleZ + 0.2D));
        this.VineArm_3a.rotateAngleZ = (float)(Math.sin((double)(par3 * 1.0F * 0.6662F)) * (-this.wiggleZ + 0.35D));
        this.VineArm_4a.rotateAngleZ = this.bSpeed * (float)(Math.cos((double)par3 * 0.15D * 0.6661999821662903D) * (this.wiggleZ - 0.5D));
    }
}

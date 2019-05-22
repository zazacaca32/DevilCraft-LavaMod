package net.minecraft.client.addon.tchestplate.armors.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelSpecialArmor extends ModelBiped {
   public int swing;

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
      super.bipedHead.rotateAngleY = par4 / 57.295776F;
      super.bipedHead.rotateAngleX = par5 / 57.295776F;
      super.bipedHeadwear.rotateAngleY = super.bipedHead.rotateAngleY;
      super.bipedHeadwear.rotateAngleX = super.bipedHead.rotateAngleX;
      super.bipedRightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F;
      super.bipedLeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
      super.bipedRightArm.rotateAngleZ = 0.0F;
      super.bipedLeftArm.rotateAngleZ = 0.0F;
      super.bipedRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
      super.bipedLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
      super.bipedRightLeg.rotateAngleY = 0.0F;
      super.bipedLeftLeg.rotateAngleY = 0.0F;
      if(this.swing > 0) {
         System.out.println(this.swing);
      }

      ModelRenderer bipedRightArm7;
      ModelRenderer bipedLeftArm7;
      if(super.isRiding) {
         bipedRightArm7 = super.bipedRightArm;
         bipedRightArm7.rotateAngleX -= 0.62831855F;
         bipedLeftArm7 = super.bipedLeftArm;
         bipedLeftArm7.rotateAngleX -= 0.62831855F;
         super.bipedRightLeg.rotateAngleX = -1.2566371F;
         super.bipedLeftLeg.rotateAngleX = -1.2566371F;
         super.bipedRightLeg.rotateAngleY = 0.31415927F;
         super.bipedLeftLeg.rotateAngleY = -0.31415927F;
      }

      if(super.heldItemLeft != 0) {
         super.bipedLeftArm.rotateAngleX = super.bipedLeftArm.rotateAngleX * 0.5F - 0.31415927F * (float)super.heldItemLeft;
      }

      if(super.heldItemRight != 0) {
         super.bipedRightArm.rotateAngleX = super.bipedRightArm.rotateAngleX * 0.5F - 0.31415927F * (float)super.heldItemRight;
      }

      super.bipedRightArm.rotateAngleY = 0.0F;
      super.bipedLeftArm.rotateAngleY = 0.0F;
      ModelRenderer bipedRightArm8;
      ModelRenderer bipedLeftArm8;
      float f6;
      float f7;
      ModelRenderer bipedRightArm9;
      float bipedRightArm71;
      if(this.swing > 0) {
         super.bipedRightArm.rotateAngleX = super.bipedRightArm.rotateAngleX * 0.5F - 0.31415927F * (float)this.swing;
         super.bipedRightArm.rotateAngleY = 0.0F;
         super.bipedLeftArm.rotateAngleY = 0.0F;
         bipedRightArm71 = (float)(this.swing + 100);
         super.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(bipedRightArm71) * 3.1415927F * 2.0F) * 0.2F;
         super.bipedRightArm.rotationPointZ = MathHelper.sin(super.bipedBody.rotateAngleY) * 5.0F;
         super.bipedRightArm.rotationPointX = -MathHelper.cos(super.bipedBody.rotateAngleY) * 5.0F;
         super.bipedLeftArm.rotationPointZ = -MathHelper.sin(super.bipedBody.rotateAngleY) * 5.0F;
         super.bipedLeftArm.rotationPointX = MathHelper.cos(super.bipedBody.rotateAngleY) * 5.0F;
         bipedLeftArm7 = super.bipedRightArm;
         bipedLeftArm7.rotateAngleY += super.bipedBody.rotateAngleY;
         bipedRightArm8 = super.bipedLeftArm;
         bipedRightArm8.rotateAngleY += super.bipedBody.rotateAngleY;
         bipedLeftArm8 = super.bipedLeftArm;
         bipedLeftArm8.rotateAngleX += super.bipedBody.rotateAngleY;
         bipedRightArm71 = 1.0F - (float)this.swing + 100.0F;
         bipedRightArm71 *= bipedRightArm71;
         bipedRightArm71 *= bipedRightArm71;
         bipedRightArm71 = 1.0F - bipedRightArm71;
         f6 = MathHelper.sin(bipedRightArm71 * 3.1415927F);
         f7 = MathHelper.sin((float)(this.swing + 100) * 3.1415927F) * -(super.bipedHead.rotateAngleX - 0.7F) * 0.75F;
         super.bipedRightArm.rotateAngleX -= (float)((double)f6 * 1.2D + (double)f7);
         bipedRightArm9 = super.bipedRightArm;
         bipedRightArm9.rotateAngleY += super.bipedBody.rotateAngleY * 2.0F;
         super.bipedRightArm.rotateAngleZ = MathHelper.sin((float)(this.swing + 100) * 3.1415927F) * -0.4F;
      }

      if(super.onGround > -9990.0F) {
         bipedRightArm71 = super.onGround;
         super.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(bipedRightArm71) * 3.1415927F * 2.0F) * 0.2F;
         super.bipedRightArm.rotationPointZ = MathHelper.sin(super.bipedBody.rotateAngleY) * 5.0F;
         super.bipedRightArm.rotationPointX = -MathHelper.cos(super.bipedBody.rotateAngleY) * 5.0F;
         super.bipedLeftArm.rotationPointZ = -MathHelper.sin(super.bipedBody.rotateAngleY) * 5.0F;
         super.bipedLeftArm.rotationPointX = MathHelper.cos(super.bipedBody.rotateAngleY) * 5.0F;
         bipedLeftArm7 = super.bipedRightArm;
         bipedLeftArm7.rotateAngleY += super.bipedBody.rotateAngleY;
         bipedRightArm8 = super.bipedLeftArm;
         bipedRightArm8.rotateAngleY += super.bipedBody.rotateAngleY;
         bipedLeftArm8 = super.bipedLeftArm;
         bipedLeftArm8.rotateAngleX += super.bipedBody.rotateAngleY;
         bipedRightArm71 = 1.0F - super.onGround;
         bipedRightArm71 *= bipedRightArm71;
         bipedRightArm71 *= bipedRightArm71;
         bipedRightArm71 = 1.0F - bipedRightArm71;
         f6 = MathHelper.sin(bipedRightArm71 * 3.1415927F);
         f7 = MathHelper.sin(super.onGround * 3.1415927F) * -(super.bipedHead.rotateAngleX - 0.7F) * 0.75F;
         super.bipedRightArm.rotateAngleX -= (float)((double)f6 * 1.2D + (double)f7);
         bipedRightArm9 = super.bipedRightArm;
         bipedRightArm9.rotateAngleY += super.bipedBody.rotateAngleY * 2.0F;
         super.bipedRightArm.rotateAngleZ = MathHelper.sin(super.onGround * 3.1415927F) * -0.4F;
      }

      if(super.isSneak) {
         super.bipedBody.rotateAngleX = 0.5F;
         bipedRightArm7 = super.bipedRightArm;
         bipedRightArm7.rotateAngleX += 0.4F;
         bipedLeftArm7 = super.bipedLeftArm;
         bipedLeftArm7.rotateAngleX += 0.4F;
         super.bipedRightLeg.rotationPointZ = 4.0F;
         super.bipedLeftLeg.rotationPointZ = 4.0F;
         super.bipedRightLeg.rotationPointY = 9.0F;
         super.bipedLeftLeg.rotationPointY = 9.0F;
         super.bipedHead.rotationPointY = 1.0F;
         super.bipedHeadwear.rotationPointY = 1.0F;
      } else {
         super.bipedBody.rotateAngleX = 0.0F;
         super.bipedRightLeg.rotationPointZ = 0.1F;
         super.bipedLeftLeg.rotationPointZ = 0.1F;
         super.bipedRightLeg.rotationPointY = 12.0F;
         super.bipedLeftLeg.rotationPointY = 12.0F;
         super.bipedHead.rotationPointY = 0.0F;
         super.bipedHeadwear.rotationPointY = 0.0F;
      }

      bipedRightArm7 = super.bipedRightArm;
      bipedRightArm7.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
      bipedLeftArm7 = super.bipedLeftArm;
      bipedLeftArm7.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
      bipedRightArm8 = super.bipedRightArm;
      bipedRightArm8.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
      bipedLeftArm8 = super.bipedLeftArm;
      bipedLeftArm8.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
      if(super.aimedBow) {
         f6 = 0.0F;
         f7 = 0.0F;
         super.bipedRightArm.rotateAngleZ = 0.0F;
         super.bipedLeftArm.rotateAngleZ = 0.0F;
         super.bipedRightArm.rotateAngleY = -0.1F + super.bipedHead.rotateAngleY;
         super.bipedLeftArm.rotateAngleY = 0.1F + super.bipedHead.rotateAngleY + 0.4F;
         super.bipedRightArm.rotateAngleX = -1.5707964F + super.bipedHead.rotateAngleX;
         super.bipedLeftArm.rotateAngleX = -1.5707964F + super.bipedHead.rotateAngleX;
         bipedRightArm9 = super.bipedRightArm;
         bipedRightArm9.rotateAngleX -= 0.0F;
         ModelRenderer bipedLeftArm9 = super.bipedLeftArm;
         bipedLeftArm9.rotateAngleX -= 0.0F;
         ModelRenderer bipedRightArm10 = super.bipedRightArm;
         bipedRightArm10.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
         ModelRenderer bipedLeftArm10 = super.bipedLeftArm;
         bipedLeftArm10.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
         ModelRenderer bipedRightArm11 = super.bipedRightArm;
         bipedRightArm11.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
         ModelRenderer bipedLeftArm11 = super.bipedLeftArm;
         bipedLeftArm11.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
      }

   }
}

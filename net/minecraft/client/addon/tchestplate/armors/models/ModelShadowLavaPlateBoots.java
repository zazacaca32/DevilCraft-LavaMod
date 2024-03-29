package net.minecraft.client.addon.tchestplate.armors.models;

import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;

public class ModelShadowLavaPlateBoots extends ModelBaseLavaArmor {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;
   ModelRenderer Shape10;
   ModelRenderer Shape11;
   ModelRenderer Shape12;
   ModelRenderer Shape13;
   ModelRenderer Shape14;
   ModelRenderer Shape15;
   ModelRenderer Shape16;
   ModelRenderer Shape17;

   public ModelShadowLavaPlateBoots() {
      super.textureWidth = 32;
      super.textureHeight = 64;
      (this.Shape1 = new ModelRenderer(this, 4, 33)).addBox(-2.0F, 9.0F, -1.3F, 4, 3, 1);
      this.Shape1.setTextureSize(128, 64);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, -0.1047198F, 0.0F, 0.0F);
      (this.Shape2 = new ModelRenderer(this, 0, 33)).addBox(0.3333333F, 9.0F, -2.0F, 1, 3, 4);
      this.Shape2.setTextureSize(128, 64);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, -0.1047198F);
      (this.Shape3 = new ModelRenderer(this, 0, 33)).addBox(-2.0F, 9.0F, 0.3F, 4, 3, 1);
      this.Shape3.setTextureSize(128, 64);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.1047198F, 0.0F, 0.0F);
      (this.Shape4 = new ModelRenderer(this, 0, 33)).addBox(-1.3F, 9.0F, -2.0F, 1, 3, 4);
      this.Shape4.setTextureSize(128, 64);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.1047198F);
      (this.Shape5 = new ModelRenderer(this, 0, 33)).addBox(-2.5F, 11.1F, -2.5F, 5, 1, 5);
      this.Shape5.setTextureSize(128, 64);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      (this.Shape6 = new ModelRenderer(this, 0, 37)).addBox(-2.5F, 10.0F, -4.0F, 5, 1, 1);
      this.Shape6.setTextureSize(128, 64);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.1047198F, 0.0F, 0.0F);
      (this.Shape7 = new ModelRenderer(this, 0, 40)).addBox(-2.0F, 10.2F, -5.0F, 4, 1, 1);
      this.Shape7.setTextureSize(128, 64);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.1396263F, 0.0F, 0.0F);
      this.Shape7.mirror = false;
      (this.Shape8 = new ModelRenderer(this, 0, 44)).addBox(-1.5F, 9.0F, -8.0F, 3, 1, 1);
      this.Shape8.setTextureSize(128, 64);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.3839724F, 0.0F, 0.0F);
      (this.Shape10 = new ModelRenderer(this, 0, 33)).addBox(-2.0F, 9.0F, -1.3F, 4, 3, 1);
      this.Shape10.setTextureSize(128, 64);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, -0.1047198F, 0.0F, 0.0F);
      (this.Shape11 = new ModelRenderer(this, 0, 33)).addBox(-1.4F, 9.0F, -2.0F, 1, 3, 4);
      this.Shape11.setTextureSize(128, 64);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, 0.1047198F);
      (this.Shape12 = new ModelRenderer(this, 0, 33)).addBox(-2.0F, 9.0F, 0.3F, 4, 3, 1);
      this.Shape12.setTextureSize(128, 64);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.1047198F, 0.0F, 0.0F);
      (this.Shape13 = new ModelRenderer(this, 0, 33)).addBox(0.2F, 9.0F, -2.0F, 1, 3, 4);
      this.Shape13.setTextureSize(128, 64);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, 0.0F, 0.0F, -0.1047198F);
      (this.Shape14 = new ModelRenderer(this, 0, 33)).addBox(-2.5F, 11.1F, -2.5F, 5, 1, 5);
      this.Shape14.setTextureSize(128, 64);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
      (this.Shape15 = new ModelRenderer(this, 0, 37)).addBox(-2.5F, 10.0F, -4.0F, 5, 1, 1);
      this.Shape15.setTextureSize(128, 64);
      this.Shape15.mirror = true;
      this.setRotation(this.Shape15, 0.1047198F, 0.0F, 0.0F);
      (this.Shape16 = new ModelRenderer(this, 0, 40)).addBox(-2.0F, 10.2F, -5.0F, 4, 1, 1);
      this.Shape16.setTextureSize(128, 64);
      this.Shape16.mirror = true;
      this.setRotation(this.Shape16, 0.1396263F, 0.0F, 0.0F);
      (this.Shape17 = new ModelRenderer(this, 0, 44)).addBox(-1.5F, 9.0F, -8.0F, 3, 1, 1);
      this.Shape17.setTextureSize(128, 64);
      this.Shape17.mirror = true;
      this.setRotation(this.Shape17, 0.3839724F, 0.0F, 0.0F);
      super.bipedRightLeg.cubeList.clear();
      super.bipedRightLeg.addChild(this.Shape1);
      super.bipedRightLeg.addChild(this.Shape2);
      super.bipedRightLeg.addChild(this.Shape3);
      super.bipedRightLeg.addChild(this.Shape4);
      super.bipedRightLeg.addChild(this.Shape5);
      super.bipedRightLeg.addChild(this.Shape6);
      super.bipedRightLeg.addChild(this.Shape7);
      super.bipedRightLeg.addChild(this.Shape8);
      super.bipedLeftLeg.cubeList.clear();
      super.bipedLeftLeg.addChild(this.Shape10);
      super.bipedLeftLeg.addChild(this.Shape11);
      super.bipedLeftLeg.addChild(this.Shape12);
      super.bipedLeftLeg.addChild(this.Shape13);
      super.bipedLeftLeg.addChild(this.Shape14);
      super.bipedLeftLeg.addChild(this.Shape15);
      super.bipedLeftLeg.addChild(this.Shape16);
      super.bipedLeftLeg.addChild(this.Shape17);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}

package net.minecraft.client.addon.tchestplate.armors.models;

import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;

public class ModelRBLavaPlateBoots extends ModelBaseLavaArmor {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;

   public ModelRBLavaPlateBoots() {
      super.textureWidth = 32;
      super.textureHeight = 64;
      (this.Shape1 = new ModelRenderer(this, 0, 51)).addBox(-2.3F, 8.0F, 2.0F, 5, 1, 1);
      this.Shape1.setTextureSize(32, 64);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      (this.Shape2 = new ModelRenderer(this, 0, 46)).addBox(-2.3F, 8.0F, -2.5F, 5, 3, 5);
      this.Shape2.setTextureSize(32, 64);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      (this.Shape3 = new ModelRenderer(this, 0, 47)).addBox(-2.3F, 10.3F, -4.0F, 5, 2, 7);
      this.Shape3.setTextureSize(32, 64);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      (this.Shape4 = new ModelRenderer(this, 0, 46)).addBox(-2.7F, 10.3F, -4.0F, 5, 2, 7);
      this.Shape4.setTextureSize(32, 64);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      (this.Shape5 = new ModelRenderer(this, 0, 50)).addBox(-2.7F, 8.0F, -2.5F, 5, 3, 5);
      this.Shape5.setTextureSize(32, 64);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      (this.Shape6 = new ModelRenderer(this, 0, 47)).addBox(-2.7F, 8.0F, 2.0F, 5, 1, 1);
      this.Shape6.setTextureSize(32, 64);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      super.bipedRightLeg.cubeList.clear();
      super.bipedRightLeg.addChild(this.Shape4);
      super.bipedRightLeg.addChild(this.Shape5);
      super.bipedRightLeg.addChild(this.Shape6);
      super.bipedLeftLeg.cubeList.clear();
      super.bipedLeftLeg.addChild(this.Shape1);
      super.bipedLeftLeg.addChild(this.Shape2);
      super.bipedLeftLeg.addChild(this.Shape3);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}

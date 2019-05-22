package net.minecraft.client.addon.tchestplate.armors.models;

import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;

public class ModelNoobPlateBoots extends ModelBaseLavaArmor {
   ModelRenderer Shape14;
   ModelRenderer Shape15;
   ModelRenderer Shape16;
   ModelRenderer Shape17;

   public ModelNoobPlateBoots() {
      super.textureWidth = 32;
      super.textureHeight = 64;
      (this.Shape14 = new ModelRenderer(this, 5, 20)).addBox(-2.8F, 10.3F, -4.0F, 5, 2, 7);
      this.Shape14.setTextureSize(32, 64);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
      (this.Shape15 = new ModelRenderer(this, 1, 20)).addBox(-2.3F, 8.0F, -2.5F, 5, 3, 5);
      this.Shape15.setTextureSize(32, 64);
      this.Shape15.mirror = true;
      this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
      (this.Shape16 = new ModelRenderer(this, 5, 19)).addBox(-2.2F, 10.3F, -4.0F, 5, 2, 7);
      this.Shape16.setTextureSize(32, 64);
      this.Shape16.mirror = true;
      this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0F);
      (this.Shape17 = new ModelRenderer(this, 4, 20)).addBox(-2.7F, 8.0F, -2.5F, 5, 3, 5);
      this.Shape17.setTextureSize(32, 64);
      this.Shape17.mirror = true;
      this.setRotation(this.Shape17, 0.0F, 0.0F, 0.0F);
      super.bipedRightLeg.cubeList.clear();
      super.bipedRightLeg.addChild(this.Shape14);
      super.bipedRightLeg.addChild(this.Shape17);
      super.bipedLeftLeg.cubeList.clear();
      super.bipedLeftLeg.addChild(this.Shape15);
      super.bipedLeftLeg.addChild(this.Shape16);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}

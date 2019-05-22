package net.minecraft.client.addon.tchestplate.armors.models;

import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;

public class ModelLavaPlateBoots extends ModelBaseLavaArmor {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape10;
   ModelRenderer Shape11;
   ModelRenderer Shape12;

   public ModelLavaPlateBoots() {
      super.textureWidth = 32;
      super.textureHeight = 64;
      (this.Shape1 = new ModelRenderer(this, 5, 18)).addBox(-2.8F, 10.3F, -4.0F, 5, 2, 7);
      this.Shape1.setTextureSize(32, 64);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      (this.Shape2 = new ModelRenderer(this, 4, 17)).addBox(-2.7F, 8.0F, 2.0F, 5, 1, 1);
      this.Shape2.setTextureSize(32, 64);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      (this.Shape3 = new ModelRenderer(this, 4, 17)).addBox(-2.7F, 8.0F, -2.5F, 5, 3, 5);
      this.Shape3.setTextureSize(32, 64);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      (this.Shape10 = new ModelRenderer(this, 1, 17)).addBox(-2.3F, 8.0F, 2.0F, 5, 1, 1);
      this.Shape10.setTextureSize(32, 64);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
      (this.Shape11 = new ModelRenderer(this, 5, 17)).addBox(-2.2F, 10.3F, -4.0F, 5, 2, 7);
      this.Shape11.setTextureSize(32, 64);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
      (this.Shape12 = new ModelRenderer(this, 1, 17)).addBox(-2.3F, 8.0F, -2.5F, 5, 3, 5);
      this.Shape12.setTextureSize(32, 64);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      super.bipedRightLeg.cubeList.clear();
      super.bipedRightLeg.addChild(this.Shape1);
      super.bipedRightLeg.addChild(this.Shape2);
      super.bipedRightLeg.addChild(this.Shape3);
      super.bipedLeftLeg.cubeList.clear();
      super.bipedLeftLeg.addChild(this.Shape10);
      super.bipedLeftLeg.addChild(this.Shape11);
      super.bipedLeftLeg.addChild(this.Shape12);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}

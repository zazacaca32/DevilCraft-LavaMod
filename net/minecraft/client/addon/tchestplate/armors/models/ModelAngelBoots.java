package net.minecraft.client.addon.tchestplate.armors.models;

import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;

public class ModelAngelBoots extends ModelBaseLavaArmor {

   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;


   public ModelAngelBoots() {
      super.textureWidth = 32;
      super.textureHeight = 64;
      this.Shape1 = new ModelRenderer(this, 0, 0);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 5, 3, 5);
      this.Shape1.setRotationPoint(-2.5F, 9.0F, -2.5F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 0);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 5, 2, 2);
      this.Shape2.setRotationPoint(-2.5F, 10.0F, -3.5F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 0);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 3, 1, 2);
      this.Shape3.setRotationPoint(-1.5F, 11.0F, -4.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 0);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 6, 1, 2);
      this.Shape4.setRotationPoint(-3.0F, 9.0F, -1.0F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 0);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.Shape5.setRotationPoint(-1.0F, 8.2F, 2.0F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 0, 0);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
      this.Shape6.setRotationPoint(-0.5F, 8.8F, 2.0F);
      this.Shape6.setTextureSize(64, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, -0.1047198F, 0.0F, 0.0F);
      super.bipedRightLeg.cubeList.clear();
      super.bipedRightLeg.addChild(this.Shape1);
      super.bipedRightLeg.addChild(this.Shape2);
      super.bipedRightLeg.addChild(this.Shape3);
      super.bipedRightLeg.addChild(this.Shape4);
      super.bipedRightLeg.addChild(this.Shape5);
      super.bipedRightLeg.addChild(this.Shape6);
      super.bipedLeftLeg.cubeList.clear();
      super.bipedLeftLeg.addChild(this.Shape1);
      super.bipedLeftLeg.addChild(this.Shape2);
      super.bipedLeftLeg.addChild(this.Shape3);
      super.bipedLeftLeg.addChild(this.Shape4);
      super.bipedLeftLeg.addChild(this.Shape5);
      super.bipedLeftLeg.addChild(this.Shape6);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}

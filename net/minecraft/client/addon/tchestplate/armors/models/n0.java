package net.minecraft.client.addon.tchestplate.armors.models;

import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;

public class n0 extends ModelBaseLavaArmor {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;
   ModelRenderer Shape9;

   public n0() {
      super.textureWidth = 32;
      super.textureHeight = 32;
      this.Shape1 = new ModelRenderer(this, 0, 0);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 8, 3, 1);
      this.Shape1.setRotationPoint(-4.0F, -3.0F, -4.2F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 0);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 8, 3, 0);
      this.Shape2.setRotationPoint(-4.0F, -8.0F, -4.2F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 0);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.Shape3.setRotationPoint(-4.0F, -5.0F, -4.2F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 0);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.Shape4.setRotationPoint(3.0F, -5.0F, -4.2F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 0);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 8, 8);
      this.Shape5.setRotationPoint(-4.2F, -8.0F, -4.0F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 0, 0);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 1, 8, 8);
      this.Shape6.setRotationPoint(3.2F, -8.0F, -4.0F);
      this.Shape6.setTextureSize(64, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 0, 0);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 8, 1, 8);
      this.Shape7.setRotationPoint(-4.0F, -8.2F, -4.0F);
      this.Shape7.setTextureSize(64, 32);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 0, 0);
      this.Shape8.addBox(0.0F, 0.0F, 0.0F, 8, 8, 1);
      this.Shape8.setRotationPoint(-4.0F, -8.0F, 3.2F);
      this.Shape8.setTextureSize(64, 32);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
      this.Shape9 = new ModelRenderer(this, 0, 0);
      this.Shape9.addBox(0.0F, 0.0F, 0.0F, 8, 1, 8);
      this.Shape9.setRotationPoint(-4.0F, -0.8F, -4.0F);
      this.Shape9.setTextureSize(64, 32);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
      super.bipedHead.cubeList.clear();
      super.bipedHead.addChild(this.Shape1);
      super.bipedHead.addChild(this.Shape2);
      super.bipedHead.addChild(this.Shape3);
      super.bipedHead.addChild(this.Shape4);
      super.bipedHead.addChild(this.Shape5);
      super.bipedHead.addChild(this.Shape6);
      super.bipedHead.addChild(this.Shape7);
      super.bipedHead.addChild(this.Shape8);
      super.bipedHead.addChild(this.Shape9);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}

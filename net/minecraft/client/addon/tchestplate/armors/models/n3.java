package net.minecraft.client.addon.tchestplate.armors.models;

import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;

public class n3 extends ModelBaseLavaArmor {
   ModelRenderer Shape36;
   ModelRenderer Shape37;

   public n3() {
      super.textureWidth = 64;
      super.textureHeight = 32;
      this.Shape36 = new ModelRenderer(this, 0, 0);
      this.Shape36.addBox(0.0F, 0.0F, 0.0F, 5, 3, 6);
      this.Shape36.setRotationPoint(-2.5F, 9.5F, -3.5F);
      this.Shape36.setTextureSize(64, 32);
      this.Shape36.mirror = true;
      this.setRotation(this.Shape36, 0.0F, 0.0F, 0.0F);
      this.Shape37 = new ModelRenderer(this, 0, 0);
      this.Shape37.addBox(0.0F, 0.0F, 0.0F, 5, 3, 6);
      this.Shape37.setRotationPoint(-2.5F, 9.5F, -3.5F);
      this.Shape37.setTextureSize(64, 32);
      this.Shape37.mirror = true;
      this.setRotation(this.Shape37, 0.0F, 0.0F, 0.0F);
      super.bipedRightLeg.cubeList.clear();
      super.bipedRightLeg.addChild(this.Shape36);
      super.bipedLeftLeg.cubeList.clear();
      super.bipedLeftLeg.addChild(this.Shape37);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}

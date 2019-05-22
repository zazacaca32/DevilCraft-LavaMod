package net.minecraft.client.addon.tchestplate.armors.models;

import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRBLavaPlateLeggins extends ModelBaseLavaArmor {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape21;
   ModelRenderer Shape22;
   ModelRenderer Shape23;
   ModelRenderer Shape24;
   ModelRenderer Shape25;
   ModelRenderer Shape26;
   ModelRenderer Shape31;
   ModelRenderer Shape32;
   ModelRenderer Shape33;
   ModelRenderer Shape34;
   ModelRenderer Shape35;
   ModelRenderer Shape36;

   public ModelRBLavaPlateLeggins() {
      super.textureWidth = 32;
      super.textureHeight = 64;
      (this.Shape1 = new ModelRenderer(this, 0, 47)).addBox(-4.5F, 9.0F, -2.5F, 9, 3, 5);
      this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape1.setTextureSize(32, 64);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      (this.Shape2 = new ModelRenderer(this, 19, 23)).addBox(-1.5F, 9.0F, -3.3F, 3, 2, 1);
      this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape2.setTextureSize(32, 64);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0174533F, 0.0F);
      (this.Shape21 = new ModelRenderer(this, 4, 2)).addBox(-2.0F, 0.0F, -2.3F, 4, 8, 1);
      this.Shape21.setTextureSize(32, 64);
      this.Shape21.mirror = true;
      this.setRotation(this.Shape21, 0.0F, 0.0F, 0.0F);
      (this.Shape22 = new ModelRenderer(this, 14, 0)).addBox(-2.3F, 0.0F, -2.0F, 1, 8, 4);
      this.Shape22.setTextureSize(32, 64);
      this.Shape22.mirror = true;
      this.setRotation(this.Shape22, 0.0F, 0.0F, 0.0F);
      (this.Shape23 = new ModelRenderer(this, 0, 53)).addBox(-1.0F, 2.0F, -2.8F, 2, 3, 1);
      this.Shape23.setTextureSize(32, 64);
      this.Shape23.mirror = true;
      this.setRotation(this.Shape23, 0.0F, 0.0F, 0.0F);
      (this.Shape24 = new ModelRenderer(this, 0, 0)).addBox(1.3F, 0.0F, -2.0F, 1, 8, 4);
      this.Shape24.setTextureSize(32, 64);
      this.Shape24.mirror = true;
      this.setRotation(this.Shape24, 0.0F, 0.0F, 0.0F);
      (this.Shape25 = new ModelRenderer(this, 6, 0)).addBox(-2.0F, 0.0F, 1.3F, 4, 8, 1);
      this.Shape25.setTextureSize(32, 64);
      this.Shape25.mirror = true;
      this.setRotation(this.Shape25, 0.0F, 0.0F, 0.0F);
      (this.Shape26 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, -0.1F, -2.0F, 4, 1, 4);
      this.Shape26.setTextureSize(32, 64);
      this.Shape26.mirror = true;
      this.setRotation(this.Shape26, 0.0F, 0.0F, 0.0F);
      (this.Shape31 = new ModelRenderer(this, 8, 51)).addBox(-1.0F, 2.0F, -2.8F, 2, 3, 1);
      this.Shape31.setTextureSize(32, 64);
      this.Shape31.mirror = true;
      this.setRotation(this.Shape31, 0.0F, 0.0F, 0.0F);
      (this.Shape32 = new ModelRenderer(this, 6, 5)).addBox(-2.0F, 0.0F, -2.3F, 4, 8, 1);
      this.Shape32.setTextureSize(32, 64);
      this.Shape32.mirror = true;
      this.setRotation(this.Shape32, 0.0F, 0.0F, 0.0F);
      (this.Shape33 = new ModelRenderer(this, 14, 2)).addBox(1.3F, 0.0F, -2.0F, 1, 8, 4);
      this.Shape33.setTextureSize(32, 64);
      this.Shape33.mirror = true;
      this.setRotation(this.Shape33, 0.0F, 0.0F, 0.0F);
      (this.Shape34 = new ModelRenderer(this, 0, 0)).addBox(-2.3F, 0.0F, -2.0F, 1, 8, 4);
      this.Shape34.setTextureSize(32, 64);
      this.Shape34.mirror = true;
      this.setRotation(this.Shape34, 0.0F, 0.0F, 0.0F);
      (this.Shape35 = new ModelRenderer(this, 19, 6)).addBox(-2.0F, 0.0F, 1.3F, 4, 8, 1);
      this.Shape35.setTextureSize(32, 64);
      this.Shape35.mirror = true;
      this.setRotation(this.Shape35, 0.0F, 0.0F, 0.0F);
      (this.Shape36 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, -0.1F, -2.0F, 4, 1, 4);
      this.Shape36.setTextureSize(32, 64);
      this.Shape36.mirror = true;
      this.setRotation(this.Shape36, 0.0F, 0.0F, 0.0F);
      super.bipedRightLeg.cubeList.clear();
      super.bipedRightLeg.addChild(this.Shape21);
      super.bipedRightLeg.addChild(this.Shape22);
      super.bipedRightLeg.addChild(this.Shape23);
      super.bipedRightLeg.addChild(this.Shape24);
      super.bipedRightLeg.addChild(this.Shape25);
      super.bipedRightLeg.addChild(this.Shape26);
      super.bipedLeftLeg.cubeList.clear();
      super.bipedLeftLeg.addChild(this.Shape31);
      super.bipedLeftLeg.addChild(this.Shape32);
      super.bipedLeftLeg.addChild(this.Shape33);
      super.bipedLeftLeg.addChild(this.Shape34);
      super.bipedLeftLeg.addChild(this.Shape35);
      super.bipedLeftLeg.addChild(this.Shape36);
      super.bipedBody.cubeList.clear();
      super.bipedBody.addChild(this.Shape1);
      super.bipedBody.addChild(this.Shape2);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
   }
}

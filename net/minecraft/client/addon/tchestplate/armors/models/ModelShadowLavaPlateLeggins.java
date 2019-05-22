package net.minecraft.client.addon.tchestplate.armors.models;

import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelShadowLavaPlateLeggins extends ModelBaseLavaArmor {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape10;
   ModelRenderer Shape11;
   ModelRenderer Shape12;
   ModelRenderer Shape13;
   ModelRenderer Shape14;
   ModelRenderer Shape15;
   ModelRenderer Shape16;
   ModelRenderer Shape17;
   ModelRenderer Shape18;
   ModelRenderer Shape20;
   ModelRenderer Shape21;
   ModelRenderer Shape22;
   ModelRenderer Shape23;
   ModelRenderer Shape24;
   ModelRenderer Shape25;
   ModelRenderer Shape26;
   ModelRenderer Shape27;
   ModelRenderer Shape28;

   public ModelShadowLavaPlateLeggins() {
      super.textureWidth = 32;
      super.textureHeight = 64;
      (this.Shape1 = new ModelRenderer(this, 0, 47)).addBox(-4.5F, 9.1F, -2.5F, 9, 3, 5);
      this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape1.setTextureSize(128, 64);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      (this.Shape2 = new ModelRenderer(this, 0, 40)).addBox(6.0F, 6.0F, -3.0F, 2, 2, 1);
      this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape2.setTextureSize(128, 64);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.7853982F);
      (this.Shape3 = new ModelRenderer(this, 0, 0)).addBox(5.7F, 5.7F, -2.8F, 2, 2, 1);
      this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape3.setTextureSize(128, 64);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.7853982F);
      (this.Shape4 = new ModelRenderer(this, 0, -1)).addBox(6.3F, 6.3F, -2.8F, 2, 2, 1);
      this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape4.setTextureSize(128, 64);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.7853982F);
      (this.Shape10 = new ModelRenderer(this, 0, 54)).addBox(-2.0F, 1.0F, -2.2F, 4, 9, 1);
      this.Shape10.setTextureSize(128, 64);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
      (this.Shape11 = new ModelRenderer(this, 0, 51)).addBox(1.2F, 1.0F, -2.0F, 1, 9, 4);
      this.Shape11.setTextureSize(128, 64);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
      (this.Shape12 = new ModelRenderer(this, 0, 54)).addBox(-2.0F, 1.0F, 1.2F, 4, 9, 1);
      this.Shape12.setTextureSize(128, 64);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      (this.Shape13 = new ModelRenderer(this, 0, 47)).addBox(-2.2F, 1.0F, -2.0F, 1, 9, 4);
      this.Shape13.setTextureSize(128, 64);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
      (this.Shape14 = new ModelRenderer(this, 0, 33)).addBox(-2.7F, 5.0F, -4.0F, 5, 1, 5);
      this.Shape14.setTextureSize(128, 64);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, 0.2792527F, 0.0F, 0.0F);
      (this.Shape15 = new ModelRenderer(this, 0, 41)).addBox(-2.5F, 4.8F, -3.3F, 5, 1, 5);
      this.Shape15.setTextureSize(128, 64);
      this.Shape15.mirror = true;
      this.setRotation(this.Shape15, 0.1047198F, 0.0F, 0.0F);
      (this.Shape16 = new ModelRenderer(this, 0, 33)).addBox(-2.7F, 4.4F, -2.0F, 5, 1, 5);
      this.Shape16.setTextureSize(128, 64);
      this.Shape16.mirror = true;
      this.setRotation(this.Shape16, -0.1047198F, 0.0F, 0.0F);
      (this.Shape17 = new ModelRenderer(this, 0, 41)).addBox(-2.3F, 0.5F, -2.4F, 5, 1, 5);
      this.Shape17.setTextureSize(128, 64);
      this.Shape17.mirror = true;
      this.setRotation(this.Shape17, 0.0F, 0.0F, 0.1396263F);
      (this.Shape18 = new ModelRenderer(this, 0, 33)).addBox(-2.3F, -0.1F, -2.6F, 5, 1, 5);
      this.Shape18.setTextureSize(128, 64);
      this.Shape18.mirror = true;
      this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
      (this.Shape20 = new ModelRenderer(this, 1, 54)).addBox(-2.0F, 1.0F, -2.2F, 4, 9, 1);
      this.Shape20.setTextureSize(128, 64);
      this.Shape20.mirror = true;
      this.setRotation(this.Shape20, 0.0F, 0.0F, 0.0F);
      (this.Shape21 = new ModelRenderer(this, 1, 51)).addBox(-2.2F, 1.0F, -2.0F, 1, 9, 4);
      this.Shape21.setTextureSize(128, 64);
      this.Shape21.mirror = true;
      this.setRotation(this.Shape21, 0.0F, 0.0F, 0.0F);
      (this.Shape22 = new ModelRenderer(this, 1, 54)).addBox(-2.0F, 1.0F, 1.2F, 4, 9, 1);
      this.Shape22.setTextureSize(128, 64);
      this.Shape22.mirror = true;
      this.setRotation(this.Shape22, 0.0F, 0.0F, 0.0F);
      (this.Shape23 = new ModelRenderer(this, 0, 47)).addBox(1.2F, 1.0F, -2.0F, 1, 9, 4);
      this.Shape23.setTextureSize(128, 64);
      this.Shape23.mirror = true;
      this.setRotation(this.Shape23, 0.0F, 0.0F, 0.0F);
      (this.Shape24 = new ModelRenderer(this, 0, 33)).addBox(-2.8F, -0.1F, -2.6F, 5, 1, 5);
      this.Shape24.setTextureSize(128, 64);
      this.Shape24.mirror = true;
      this.setRotation(this.Shape24, 0.0F, 0.0F, 0.0F);
      (this.Shape25 = new ModelRenderer(this, 0, 41)).addBox(-2.7F, 0.5F, -2.4F, 5, 1, 5);
      this.Shape25.setTextureSize(128, 64);
      this.Shape25.mirror = true;
      this.setRotation(this.Shape25, 0.0F, 0.0F, -0.1396263F);
      (this.Shape26 = new ModelRenderer(this, 0, 33)).addBox(-2.7F, 4.4F, -2.0F, 5, 1, 5);
      this.Shape26.setTextureSize(128, 64);
      this.Shape26.mirror = true;
      this.setRotation(this.Shape26, -0.1047198F, 0.0F, 0.0F);
      (this.Shape27 = new ModelRenderer(this, 0, 41)).addBox(-2.5F, 4.8F, -3.3F, 5, 1, 5);
      this.Shape27.setTextureSize(128, 64);
      this.Shape27.mirror = true;
      this.setRotation(this.Shape27, 0.1047198F, 0.0F, 0.0F);
      (this.Shape28 = new ModelRenderer(this, 0, 33)).addBox(-2.7F, 5.0F, -4.0F, 5, 1, 5);
      this.Shape28.setTextureSize(128, 64);
      this.Shape28.mirror = true;
      this.setRotation(this.Shape28, 0.2792527F, 0.0F, 0.0F);
      super.bipedRightLeg.cubeList.clear();
      super.bipedRightLeg.addChild(this.Shape10);
      super.bipedRightLeg.addChild(this.Shape11);
      super.bipedRightLeg.addChild(this.Shape12);
      super.bipedRightLeg.addChild(this.Shape13);
      super.bipedRightLeg.addChild(this.Shape14);
      super.bipedRightLeg.addChild(this.Shape15);
      super.bipedRightLeg.addChild(this.Shape16);
      super.bipedRightLeg.addChild(this.Shape17);
      super.bipedRightLeg.addChild(this.Shape18);
      super.bipedLeftLeg.cubeList.clear();
      super.bipedLeftLeg.addChild(this.Shape20);
      super.bipedLeftLeg.addChild(this.Shape21);
      super.bipedLeftLeg.addChild(this.Shape22);
      super.bipedLeftLeg.addChild(this.Shape23);
      super.bipedLeftLeg.addChild(this.Shape24);
      super.bipedLeftLeg.addChild(this.Shape25);
      super.bipedLeftLeg.addChild(this.Shape26);
      super.bipedLeftLeg.addChild(this.Shape27);
      super.bipedLeftLeg.addChild(this.Shape28);
      super.bipedBody.cubeList.clear();
      super.bipedBody.addChild(this.Shape1);
      super.bipedBody.addChild(this.Shape2);
      super.bipedBody.addChild(this.Shape3);
      super.bipedBody.addChild(this.Shape4);
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

package net.minecraft.client.addon.tchestplate.armors.models;

import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDemonLeg extends ModelBaseLavaArmor {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;
   ModelRenderer Shape9;
   ModelRenderer Shape10;
   ModelRenderer Shape11;
   ModelRenderer Shape12;
   ModelRenderer Shape13;
   ModelRenderer Shape14;
   ModelRenderer Shape15;
   ModelRenderer Shape16;
   ModelRenderer Shape17;
   ModelRenderer Shape18;
   ModelRenderer Shape19;
   ModelRenderer Shape20;
   ModelRenderer Shape21;
   ModelRenderer Shape22;
   ModelRenderer Shape23;

   public ModelDemonLeg() {
      super.textureWidth = 64;
      super.textureHeight = 64;
      this.Shape1 = new ModelRenderer(this, 40, 51);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 4, 9, 1);
      this.Shape1.setRotationPoint(-2.0F, 0.0F, -2.2F);
      this.Shape1.setTextureSize(64, 64);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 40, 51);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 4, 9, 1);
      this.Shape2.setRotationPoint(-2.0F, 0.0F, 1.2F);
      this.Shape2.setTextureSize(64, 64);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 40, 51);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 9, 4);
      this.Shape3.setRotationPoint(-2.2F, 0.0F, -2.0F);
      this.Shape3.setTextureSize(64, 64);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 40, 51);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 1, 9, 4);
      this.Shape4.setRotationPoint(1.2F, 0.0F, -2.0F);
      this.Shape4.setTextureSize(64, 64);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 0);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 2, 10, 1);
      this.Shape5.setRotationPoint(-1.0F, -0.5F, -2.5F);
      this.Shape5.setTextureSize(64, 64);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 0, 0);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 2, 10, 1);
      this.Shape6.setRotationPoint(-1.0F, -0.5F, 1.5F);
      this.Shape6.setTextureSize(64, 64);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 0, 0);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 1, 10, 2);
      this.Shape7.setRotationPoint(-2.5F, -0.5F, -1.0F);
      this.Shape7.setTextureSize(64, 64);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 0, 0);
      this.Shape8.addBox(-1.0F, -1.0F, 0.0F, 3, 3, 1);
      this.Shape8.setRotationPoint(0.0F, 4.0F, -2.7F);
      this.Shape8.setTextureSize(64, 64);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.7853982F);
      this.Shape9 = new ModelRenderer(this, 40, 51);
      this.Shape9.addBox(0.0F, 0.0F, 0.0F, 4, 9, 1);
      this.Shape9.setRotationPoint(-2.0F, 0.0F, -2.2F);
      this.Shape9.setTextureSize(64, 64);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 40, 51);
      this.Shape10.addBox(0.0F, 0.0F, 0.0F, 4, 9, 1);
      this.Shape10.setRotationPoint(-2.0F, 0.0F, 1.2F);
      this.Shape10.setTextureSize(64, 64);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 40, 51);
      this.Shape11.addBox(0.0F, 0.0F, 0.0F, 1, 9, 4);
      this.Shape11.setRotationPoint(-2.2F, 0.0F, -2.0F);
      this.Shape11.setTextureSize(64, 64);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 40, 51);
      this.Shape12.addBox(0.0F, 0.0F, 0.0F, 1, 9, 4);
      this.Shape12.setRotationPoint(1.2F, 0.0F, -2.0F);
      this.Shape12.setTextureSize(64, 64);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      this.Shape13 = new ModelRenderer(this, 0, 0);
      this.Shape13.addBox(0.0F, 0.0F, 0.0F, 2, 10, 1);
      this.Shape13.setRotationPoint(-1.0F, -0.5F, -2.5F);
      this.Shape13.setTextureSize(64, 64);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
      this.Shape14 = new ModelRenderer(this, 0, 0);
      this.Shape14.addBox(0.0F, 0.0F, 0.0F, 1, 10, 2);
      this.Shape14.setRotationPoint(1.5F, -0.5F, -1.0F);
      this.Shape14.setTextureSize(64, 64);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
      this.Shape15 = new ModelRenderer(this, 0, 0);
      this.Shape15.addBox(0.0F, 0.0F, 0.0F, 2, 10, 1);
      this.Shape15.setRotationPoint(-1.0F, -0.5F, 1.5F);
      this.Shape15.setTextureSize(64, 64);
      this.Shape15.mirror = true;
      this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
      this.Shape16 = new ModelRenderer(this, 0, 0);
      this.Shape16.addBox(-1.0F, -1.0F, 0.0F, 3, 3, 1);
      this.Shape16.setRotationPoint(0.0F, 4.0F, -2.7F);
      this.Shape16.setTextureSize(64, 64);
      this.Shape16.mirror = true;
      this.setRotation(this.Shape16, 0.0F, 0.0F, 0.7853982F);
      this.Shape17 = new ModelRenderer(this, 0, 0);
      this.Shape17.addBox(0.0F, 0.0F, 0.0F, 9, 3, 5);
      this.Shape17.setRotationPoint(-4.5F, 9.0F, -2.5F);
      this.Shape17.setTextureSize(64, 64);
      this.Shape17.mirror = true;
      this.setRotation(this.Shape17, 0.0F, 0.0F, 0.0F);
      this.Shape18 = new ModelRenderer(this, 31, 30);
      this.Shape18.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
      this.Shape18.setRotationPoint(-1.0F, 8.8F, -3.5F);
      this.Shape18.setTextureSize(64, 64);
      this.Shape18.mirror = true;
      this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
      this.Shape19 = new ModelRenderer(this, 31, 30);
      this.Shape19.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.Shape19.setRotationPoint(-1.7F, 9.5F, -3.0F);
      this.Shape19.setTextureSize(64, 64);
      this.Shape19.mirror = true;
      this.setRotation(this.Shape19, 0.0F, 0.0F, -0.7853982F);
      this.Shape20 = new ModelRenderer(this, 31, 30);
      this.Shape20.addBox(-1.0F, 0.0F, 0.0F, 1, 2, 1);
      this.Shape20.setRotationPoint(1.7F, 9.5F, -3.0F);
      this.Shape20.setTextureSize(64, 64);
      this.Shape20.mirror = true;
      this.setRotation(this.Shape20, 0.0F, 0.0F, 0.7853982F);
      this.Shape21 = new ModelRenderer(this, 31, 30);
      this.Shape21.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape21.setRotationPoint(0.0F, 10.0F, -3.0F);
      this.Shape21.setTextureSize(64, 64);
      this.Shape21.mirror = true;
      this.setRotation(this.Shape21, 0.0F, 0.0F, 0.7853982F);
      this.Shape22 = new ModelRenderer(this, 40, 47);
      this.Shape22.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
      this.Shape22.setRotationPoint(-2.0F, -0.2F, -2.0F);
      this.Shape22.setTextureSize(64, 64);
      this.Shape22.mirror = true;
      this.setRotation(this.Shape22, 0.0F, 0.0F, 0.0F);
      this.Shape23 = new ModelRenderer(this, 40, 47);
      this.Shape23.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
      this.Shape23.setRotationPoint(-2.0F, -0.2F, -2.0F);
      this.Shape23.setTextureSize(64, 64);
      this.Shape23.mirror = true;
      super.bipedRightLeg.cubeList.clear();
      super.bipedRightLeg.addChild(this.Shape1);
      super.bipedRightLeg.addChild(this.Shape2);
      super.bipedRightLeg.addChild(this.Shape3);
      super.bipedRightLeg.addChild(this.Shape4);
      super.bipedRightLeg.addChild(this.Shape5);
      super.bipedRightLeg.addChild(this.Shape6);
      super.bipedRightLeg.addChild(this.Shape7);
      super.bipedRightLeg.addChild(this.Shape8);
      super.bipedRightLeg.addChild(this.Shape22);
      super.bipedLeftLeg.cubeList.clear();
      super.bipedLeftLeg.addChild(this.Shape9);
      super.bipedLeftLeg.addChild(this.Shape10);
      super.bipedLeftLeg.addChild(this.Shape11);
      super.bipedLeftLeg.addChild(this.Shape12);
      super.bipedLeftLeg.addChild(this.Shape13);
      super.bipedLeftLeg.addChild(this.Shape14);
      super.bipedLeftLeg.addChild(this.Shape15);
      super.bipedLeftLeg.addChild(this.Shape16);
      super.bipedRightLeg.addChild(this.Shape23);
      super.bipedBody.cubeList.clear();
      super.bipedBody.addChild(this.Shape17);
      super.bipedBody.addChild(this.Shape18);
      super.bipedBody.addChild(this.Shape19);
      super.bipedBody.addChild(this.Shape20);
      super.bipedBody.addChild(this.Shape21);
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

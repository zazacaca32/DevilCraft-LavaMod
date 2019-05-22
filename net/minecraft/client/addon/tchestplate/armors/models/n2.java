package net.minecraft.client.addon.tchestplate.armors.models;

import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class n2 extends ModelBaseLavaArmor {
   ModelRenderer Shape26;
   ModelRenderer Shape27;
   ModelRenderer Shape28;
   ModelRenderer Shape29;
   ModelRenderer Shape30;
   ModelRenderer Shape31;
   ModelRenderer Shape32;
   ModelRenderer Shape33;
   ModelRenderer Shape34;
   ModelRenderer Shape35;

   public n2() {
      super.textureWidth = 32;
      super.textureHeight = 32;
      this.Shape26 = new ModelRenderer(this, 0, 0);
      this.Shape26.addBox(0.0F, 0.0F, 0.0F, 4, 10, 1);
      this.Shape26.setRotationPoint(-2.0F, 0.0F, -2.2F);
      this.Shape26.setTextureSize(64, 32);
      this.Shape26.mirror = true;
      this.setRotation(this.Shape26, 0.0F, 0.0F, 0.0F);
      this.Shape27 = new ModelRenderer(this, 0, 0);
      this.Shape27.addBox(0.0F, 0.0F, 0.0F, 1, 10, 4);
      this.Shape27.setRotationPoint(-2.2F, 0.0F, -2.0F);
      this.Shape27.setTextureSize(64, 32);
      this.Shape27.mirror = true;
      this.setRotation(this.Shape27, 0.0F, 0.0F, 0.0F);
      this.Shape28 = new ModelRenderer(this, 0, 0);
      this.Shape28.addBox(0.0F, 0.0F, 0.0F, 4, 10, 1);
      this.Shape28.setRotationPoint(-2.0F, 0.0F, 1.2F);
      this.Shape28.setTextureSize(64, 32);
      this.Shape28.mirror = true;
      this.setRotation(this.Shape28, 0.0F, 0.0F, 0.0F);
      this.Shape29 = new ModelRenderer(this, 0, 0);
      this.Shape29.addBox(0.0F, 0.0F, 0.0F, 1, 10, 4);
      this.Shape29.setRotationPoint(1.2F, 0.0F, -2.0F);
      this.Shape29.setTextureSize(64, 32);
      this.Shape29.mirror = true;
      this.setRotation(this.Shape29, 0.0F, 0.0F, 0.0F);
      this.Shape30 = new ModelRenderer(this, 0, 0);
      this.Shape30.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
      this.Shape30.setRotationPoint(-2.0F, -0.2F, -2.0F);
      this.Shape30.setTextureSize(64, 32);
      this.Shape30.mirror = true;
      this.setRotation(this.Shape30, 0.0F, 0.0F, 0.0F);
      this.Shape31 = new ModelRenderer(this, 0, 0);
      this.Shape31.addBox(0.0F, 0.0F, 0.0F, 4, 10, 1);
      this.Shape31.setRotationPoint(-2.0F, 0.0F, -2.2F);
      this.Shape31.setTextureSize(64, 32);
      this.Shape31.mirror = true;
      this.setRotation(this.Shape31, 0.0F, 0.0F, 0.0F);
      this.Shape32 = new ModelRenderer(this, 0, 0);
      this.Shape32.addBox(0.0F, 0.0F, 0.0F, 1, 10, 4);
      this.Shape32.setRotationPoint(1.2F, 0.0F, -2.0F);
      this.Shape32.setTextureSize(64, 32);
      this.Shape32.mirror = true;
      this.setRotation(this.Shape32, 0.0F, 0.0F, 0.0F);
      this.Shape33 = new ModelRenderer(this, 0, 0);
      this.Shape33.addBox(0.0F, 0.0F, 0.0F, 4, 10, 1);
      this.Shape33.setRotationPoint(-2.0F, 0.0F, 1.2F);
      this.Shape33.setTextureSize(64, 32);
      this.Shape33.mirror = true;
      this.setRotation(this.Shape33, 0.0F, 0.0F, 0.0F);
      this.Shape34 = new ModelRenderer(this, 0, 0);
      this.Shape34.addBox(0.0F, 0.0F, 0.0F, 1, 10, 4);
      this.Shape34.setRotationPoint(-2.2F, 0.0F, -2.0F);
      this.Shape34.setTextureSize(64, 32);
      this.Shape34.mirror = true;
      this.setRotation(this.Shape34, 0.0F, 0.0F, 0.0F);
      this.Shape35 = new ModelRenderer(this, 0, 0);
      this.Shape35.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
      this.Shape35.setRotationPoint(-2.0F, -0.2F, -2.0F);
      this.Shape35.setTextureSize(64, 32);
      this.Shape35.mirror = true;
      this.setRotation(this.Shape35, 0.0F, 0.0F, 0.0F);
      super.bipedRightLeg.cubeList.clear();
      super.bipedRightLeg.addChild(this.Shape26);
      super.bipedRightLeg.addChild(this.Shape27);
      super.bipedRightLeg.addChild(this.Shape28);
      super.bipedRightLeg.addChild(this.Shape29);
      super.bipedRightLeg.addChild(this.Shape30);
      super.bipedLeftLeg.cubeList.clear();
      super.bipedLeftLeg.addChild(this.Shape31);
      super.bipedLeftLeg.addChild(this.Shape32);
      super.bipedLeftLeg.addChild(this.Shape33);
      super.bipedLeftLeg.addChild(this.Shape34);
      super.bipedLeftLeg.addChild(this.Shape35);
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

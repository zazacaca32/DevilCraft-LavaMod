package net.minecraft.client.addon.tco.tiny.items.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBukvarik extends ModelBase {

   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;


   public ModelBukvarik() {
      super.textureWidth = 64;
      super.textureHeight = 64;
      (this.Shape1 = new ModelRenderer(this, 0, 47)).addBox(0.0F, 0.0F, 0.0F, 12, 1, 16);
      this.Shape1.setRotationPoint(-6.0F, 18.0F, -8.0F);
      this.Shape1.setTextureSize(64, 64);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      (this.Shape2 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 11, 3, 14);
      this.Shape2.setRotationPoint(-5.0F, 15.0F, -7.0F);
      this.Shape2.setTextureSize(64, 64);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      (this.Shape3 = new ModelRenderer(this, 0, 27)).addBox(0.0F, 0.0F, 0.0F, 1, 4, 16);
      this.Shape3.setRotationPoint(5.5F, 14.5F, -8.1F);
      this.Shape3.setTextureSize(64, 64);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      (this.Shape4 = new ModelRenderer(this, 0, 47)).addBox(0.0F, 0.0F, 0.0F, 12, 1, 16);
      this.Shape4.setRotationPoint(-6.0F, 14.0F, -8.0F);
      this.Shape4.setTextureSize(64, 64);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      (this.Shape5 = new ModelRenderer(this, 0, 28)).addBox(0.0F, 0.0F, 0.0F, 1, 3, 16);
      this.Shape5.setRotationPoint(6.0F, 15.0F, -8.0F);
      this.Shape5.setTextureSize(64, 64);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      (this.Shape6 = new ModelRenderer(this, 42, 50)).addBox(0.0F, 0.0F, 0.0F, 1, 1, 10);
      this.Shape6.setRotationPoint(-0.5F, 13.5F, -5.3F);
      this.Shape6.setTextureSize(64, 64);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, -0.3490659F, 0.0F);
      (this.Shape7 = new ModelRenderer(this, 42, 50)).addBox(0.0F, 0.0F, 0.0F, 1, 1, 10);
      this.Shape7.setRotationPoint(-0.5F, 13.5F, -5.0F);
      this.Shape7.setTextureSize(64, 64);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.3490659F, 0.0F);
      (this.Shape8 = new ModelRenderer(this, 54, 58)).addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape8.setRotationPoint(-2.0F, 13.5F, 0.0F);
      this.Shape8.setTextureSize(64, 64);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape5.render(f5);
      this.Shape6.render(f5);
      this.Shape7.render(f5);
      this.Shape8.render(f5);
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

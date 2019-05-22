package net.minecraft.client.addon.tco.tiny.blocks.models;

import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBlockDecorContainer extends BaseModelBlock {

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
   private int index;


   public ModelBlockDecorContainer(int index) {
      switch(this.index = index) {
      case 0:
         super.textureWidth = 64;
         super.textureHeight = 32;
         (this.Shape1 = new ModelRenderer(this, 0, 19)).addBox(0.0F, 0.0F, 0.0F, 10, 1, 8);
         this.Shape1.setRotationPoint(-5.0F, 23.0F, -4.0F);
         this.Shape1.setTextureSize(64, 32);
         this.Shape1.mirror = true;
         this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
         (this.Shape2 = new ModelRenderer(this, 0, 19)).addBox(0.0F, 0.0F, 0.0F, 8, 1, 10);
         this.Shape2.setRotationPoint(-4.0F, 23.0F, -5.0F);
         this.Shape2.setTextureSize(64, 32);
         this.Shape2.mirror = true;
         this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
         (this.Shape3 = new ModelRenderer(this, 2, 21)).addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
         this.Shape3.setRotationPoint(-2.0F, 8.0F, -2.0F);
         this.Shape3.setTextureSize(64, 32);
         this.Shape3.mirror = true;
         this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
         (this.Shape4 = new ModelRenderer(this, 12, 20)).addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
         this.Shape4.setRotationPoint(-3.0F, 21.5F, -3.0F);
         this.Shape4.setTextureSize(64, 32);
         this.Shape4.mirror = true;
         this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
         (this.Shape5 = new ModelRenderer(this, 4, 19)).addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
         this.Shape5.setRotationPoint(2.0F, 21.5F, -3.0F);
         this.Shape5.setTextureSize(64, 32);
         this.Shape5.mirror = true;
         this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
         (this.Shape6 = new ModelRenderer(this, 0, 19)).addBox(0.0F, 0.0F, 0.0F, 8, 1, 8);
         this.Shape6.setRotationPoint(-4.0F, 22.0F, -4.0F);
         this.Shape6.setTextureSize(64, 32);
         this.Shape6.mirror = true;
         this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
         (this.Shape7 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 14, 1);
         this.Shape7.setRotationPoint(-2.0F, 8.0F, 2.0F);
         this.Shape7.setTextureSize(64, 32);
         this.Shape7.mirror = true;
         this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
         (this.Shape8 = new ModelRenderer(this, 11, 0)).addBox(0.0F, 0.0F, 0.0F, 1, 14, 4);
         this.Shape8.setRotationPoint(2.0F, 8.0F, -2.0F);
         this.Shape8.setTextureSize(64, 32);
         this.Shape8.mirror = true;
         this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
         (this.Shape9 = new ModelRenderer(this, 22, 0)).addBox(0.0F, 0.0F, 0.0F, 1, 14, 4);
         this.Shape9.setRotationPoint(-3.0F, 8.0F, -2.0F);
         this.Shape9.setTextureSize(64, 32);
         this.Shape9.mirror = true;
         this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
         (this.Shape10 = new ModelRenderer(this, 33, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 14, 1);
         this.Shape10.setRotationPoint(-2.0F, 8.0F, -3.0F);
         this.Shape10.setTextureSize(64, 32);
         this.Shape10.mirror = true;
         this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
         (this.Shape11 = new ModelRenderer(this, 3, 0)).addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
         this.Shape11.setRotationPoint(-3.0F, 21.5F, 2.0F);
         this.Shape11.setTextureSize(64, 32);
         this.Shape11.mirror = true;
         this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
         (this.Shape12 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
         this.Shape12.setRotationPoint(2.0F, 21.5F, 2.0F);
         this.Shape12.setTextureSize(64, 32);
         this.Shape12.mirror = true;
         this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
         break;
      case 1:
         super.textureWidth = 64;
         super.textureHeight = 32;
         (this.Shape1 = new ModelRenderer(this, 2, 21)).addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
         this.Shape1.setRotationPoint(-2.0F, 23.0F, -2.0F);
         this.Shape1.setTextureSize(64, 32);
         this.Shape1.mirror = true;
         this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
         (this.Shape2 = new ModelRenderer(this, 2, 21)).addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
         this.Shape2.setRotationPoint(-2.0F, 8.0F, -2.0F);
         this.Shape2.setTextureSize(64, 32);
         this.Shape2.mirror = true;
         this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
         (this.Shape3 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 16, 1);
         this.Shape3.setRotationPoint(-2.0F, 8.0F, 2.0F);
         this.Shape3.setTextureSize(64, 32);
         this.Shape3.mirror = true;
         this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
         (this.Shape4 = new ModelRenderer(this, 11, 0)).addBox(0.0F, 0.0F, 0.0F, 1, 16, 4);
         this.Shape4.setRotationPoint(2.0F, 8.0F, -2.0F);
         this.Shape4.setTextureSize(64, 32);
         this.Shape4.mirror = true;
         this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
         (this.Shape5 = new ModelRenderer(this, 22, 0)).addBox(0.0F, 0.0F, 0.0F, 1, 16, 4);
         this.Shape5.setRotationPoint(-3.0F, 8.0F, -2.0F);
         this.Shape5.setTextureSize(64, 32);
         this.Shape5.mirror = true;
         this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
         (this.Shape6 = new ModelRenderer(this, 33, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 16, 1);
         this.Shape6.setRotationPoint(-2.0F, 8.0F, -3.0F);
         this.Shape6.setTextureSize(64, 32);
         this.Shape6.mirror = true;
         this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
         break;
      case 2:
         super.textureWidth = 64;
         super.textureHeight = 32;
         (this.Shape1 = new ModelRenderer(this, 0, 19)).addBox(0.0F, 0.0F, 0.0F, 10, 1, 8);
         this.Shape1.setRotationPoint(-5.0F, 8.0F, -4.0F);
         this.Shape1.setTextureSize(64, 32);
         this.Shape1.mirror = true;
         this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
         (this.Shape2 = new ModelRenderer(this, 0, 19)).addBox(0.0F, 0.0F, 0.0F, 8, 1, 10);
         this.Shape2.setRotationPoint(-4.0F, 8.0F, -5.0F);
         this.Shape2.setTextureSize(64, 32);
         this.Shape2.mirror = true;
         this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
         (this.Shape3 = new ModelRenderer(this, 2, 21)).addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
         this.Shape3.setRotationPoint(-2.0F, 8.0F, -2.0F);
         this.Shape3.setTextureSize(64, 32);
         this.Shape3.mirror = true;
         this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
         (this.Shape4 = new ModelRenderer(this, 12, 20)).addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
         this.Shape4.setRotationPoint(-3.0F, 9.5F, -3.0F);
         this.Shape4.setTextureSize(64, 32);
         this.Shape4.mirror = true;
         this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
         (this.Shape5 = new ModelRenderer(this, 4, 19)).addBox(0.0F, -14.0F, 0.0F, 1, 1, 1);
         this.Shape5.setRotationPoint(2.0F, 23.5F, -3.066667F);
         this.Shape5.setTextureSize(64, 32);
         this.Shape5.mirror = true;
         this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
         (this.Shape6 = new ModelRenderer(this, 0, 19)).addBox(0.0F, 0.0F, 0.0F, 8, 1, 8);
         this.Shape6.setRotationPoint(-4.0F, 9.0F, -4.0F);
         this.Shape6.setTextureSize(64, 32);
         this.Shape6.mirror = true;
         this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
         (this.Shape7 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 14, 1);
         this.Shape7.setRotationPoint(-2.0F, 10.0F, 2.0F);
         this.Shape7.setTextureSize(64, 32);
         this.Shape7.mirror = true;
         this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
         (this.Shape8 = new ModelRenderer(this, 11, 0)).addBox(0.0F, 0.0F, 0.0F, 1, 14, 4);
         this.Shape8.setRotationPoint(2.0F, 10.0F, -2.0F);
         this.Shape8.setTextureSize(64, 32);
         this.Shape8.mirror = true;
         this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
         (this.Shape9 = new ModelRenderer(this, 22, 0)).addBox(0.0F, 0.0F, 0.0F, 1, 14, 4);
         this.Shape9.setRotationPoint(-3.0F, 10.0F, -2.0F);
         this.Shape9.setTextureSize(64, 32);
         this.Shape9.mirror = true;
         this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
         (this.Shape10 = new ModelRenderer(this, 33, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 14, 1);
         this.Shape10.setRotationPoint(-2.0F, 10.0F, -3.0F);
         this.Shape10.setTextureSize(64, 32);
         this.Shape10.mirror = true;
         this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
         (this.Shape11 = new ModelRenderer(this, 3, 0)).addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
         this.Shape11.setRotationPoint(-3.0F, 9.5F, 2.0F);
         this.Shape11.setTextureSize(64, 32);
         this.Shape11.mirror = true;
         this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
         (this.Shape12 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
         this.Shape12.setRotationPoint(2.0F, 9.5F, 2.0F);
         this.Shape12.setTextureSize(64, 32);
         this.Shape12.mirror = true;
         this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      }

   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      switch(this.index) {
      case 0:
         this.Shape1.render(f5);
         this.Shape2.render(f5);
         this.Shape3.render(f5);
         this.Shape4.render(f5);
         this.Shape5.render(f5);
         this.Shape6.render(f5);
         this.Shape7.render(f5);
         this.Shape8.render(f5);
         this.Shape9.render(f5);
         this.Shape10.render(f5);
         this.Shape11.render(f5);
         this.Shape12.render(f5);
         break;
      case 1:
         this.Shape1.render(f5);
         this.Shape2.render(f5);
         this.Shape3.render(f5);
         this.Shape4.render(f5);
         this.Shape5.render(f5);
         this.Shape6.render(f5);
         break;
      case 2:
         this.Shape1.render(f5);
         this.Shape2.render(f5);
         this.Shape3.render(f5);
         this.Shape4.render(f5);
         this.Shape5.render(f5);
         this.Shape6.render(f5);
         this.Shape7.render(f5);
         this.Shape8.render(f5);
         this.Shape9.render(f5);
         this.Shape10.render(f5);
         this.Shape11.render(f5);
         this.Shape12.render(f5);
      }

   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void render() {
      switch(this.index) {
      case 0:
         this.Shape1.render(0.0625F);
         this.Shape2.render(0.0625F);
         this.Shape3.render(0.0625F);
         this.Shape4.render(0.0625F);
         this.Shape5.render(0.0625F);
         this.Shape6.render(0.0625F);
         this.Shape7.render(0.0625F);
         this.Shape8.render(0.0625F);
         this.Shape9.render(0.0625F);
         this.Shape10.render(0.0625F);
         this.Shape11.render(0.0625F);
         this.Shape12.render(0.0625F);
         break;
      case 1:
         this.Shape1.render(0.0625F);
         this.Shape2.render(0.0625F);
         this.Shape3.render(0.0625F);
         this.Shape4.render(0.0625F);
         this.Shape5.render(0.0625F);
         this.Shape6.render(0.0625F);
         break;
      case 2:
         this.Shape1.render(0.0625F);
         this.Shape2.render(0.0625F);
         this.Shape3.render(0.0625F);
         this.Shape4.render(0.0625F);
         this.Shape5.render(0.0625F);
         this.Shape6.render(0.0625F);
         this.Shape7.render(0.0625F);
         this.Shape8.render(0.0625F);
         this.Shape9.render(0.0625F);
         this.Shape10.render(0.0625F);
         this.Shape11.render(0.0625F);
         this.Shape12.render(0.0625F);
      }

   }
}

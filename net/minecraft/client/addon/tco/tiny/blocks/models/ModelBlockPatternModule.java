package net.minecraft.client.addon.tco.tiny.blocks.models;

import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBlockPatternModule extends BaseModelBlock {

   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;
   float o = 0.0F;


   public ModelBlockPatternModule() {
      super.textureWidth = 64;
      super.textureHeight = 64;
      (this.Shape1 = new ModelRenderer(this, 0, 46)).addBox(-8.0F, 22.0F, -8.0F, 16, 2, 16);
      this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape1.setTextureSize(64, 64);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      (this.Shape2 = new ModelRenderer(this, 0, 30)).addBox(-6.0F, 18.0F, -6.0F, 12, 4, 12);
      this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape2.setTextureSize(64, 64);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      (this.Shape4 = new ModelRenderer(this, 8, 26)).addBox(-4.0F, 16.0F, -1.0F, 8, 2, 2);
      this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape4.setTextureSize(64, 64);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      (this.Shape5 = new ModelRenderer(this, 0, 18)).addBox(-6.0F, 7.0F, -1.0F, 2, 10, 2);
      this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape5.setTextureSize(64, 64);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      (this.Shape6 = new ModelRenderer(this, 8, 26)).addBox(-4.0F, 6.0F, -1.0F, 8, 2, 2);
      this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape6.setTextureSize(64, 64);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      (this.Shape7 = new ModelRenderer(this, 0, 18)).addBox(4.0F, 7.0F, -1.0F, 2, 10, 2);
      this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape7.setTextureSize(64, 64);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      (this.Shape8 = new ModelRenderer(this, 48, 36)).addBox(-2.0F, 9.0F, -2.0F, 4, 6, 4);
      this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape8.setTextureSize(64, 64);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
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

   public void render() {
      this.Shape1.render(0.0625F);
      this.Shape2.render(0.0625F);
      this.Shape4.render(0.0625F);
      this.Shape5.render(0.0625F);
      this.Shape6.render(0.0625F);
      this.Shape7.render(0.0625F);
      this.Shape8.render(0.0625F);
   }
}

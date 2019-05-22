package net.minecraft.client.addon.tco.tiny.blocks.models;

import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBlockUpgradeArmor extends BaseModelBlock {

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
   float o = 0.0F;


   public ModelBlockUpgradeArmor() {
      super.textureWidth = 64;
      super.textureHeight = 64;
      (this.Shape1 = new ModelRenderer(this, 0, 46)).addBox(0.0F, 0.0F, 0.0F, 16, 2, 16);
      this.Shape1.setRotationPoint(-8.0F, 22.0F, -8.0F);
      this.Shape1.setTextureSize(64, 64);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      (this.Shape2 = new ModelRenderer(this, 0, 30)).addBox(0.0F, 0.0F, 0.0F, 12, 4, 12);
      this.Shape2.setRotationPoint(-6.0F, 18.0F, -6.0F);
      this.Shape2.setTextureSize(64, 64);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      (this.Shape3 = new ModelRenderer(this, 0, 50)).addBox(0.0F, 0.0F, 0.0F, 2, 10, 2);
      this.Shape3.setRotationPoint(-8.0F, 12.0F, -1.0F);
      this.Shape3.setTextureSize(64, 64);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      (this.Shape4 = new ModelRenderer(this, 0, 50)).addBox(0.0F, 0.0F, 0.0F, 2, 10, 2);
      this.Shape4.setRotationPoint(6.0F, 12.0F, -1.0F);
      this.Shape4.setTextureSize(64, 64);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      (this.Shape5 = new ModelRenderer(this, 8, 55)).addBox(0.0F, 0.0F, 0.0F, 1, 6, 1);
      this.Shape5.setRotationPoint(-4.0F, 7.5F, -0.5F);
      this.Shape5.setTextureSize(64, 64);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.6632251F);
      (this.Shape6 = new ModelRenderer(this, 8, 56)).addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
      this.Shape6.setRotationPoint(-4.0F, 7.5F, -0.5F);
      this.Shape6.setTextureSize(64, 64);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, -0.3141593F);
      (this.Shape7 = new ModelRenderer(this, 8, 58)).addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
      this.Shape7.setRotationPoint(-3.0F, 10.5F, -1.5F);
      this.Shape7.setTextureSize(64, 64);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, -0.3141593F);
      (this.Shape8 = new ModelRenderer(this, 8, 58)).addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
      this.Shape8.setRotationPoint(-3.0F, 10.5F, 0.5F);
      this.Shape8.setTextureSize(64, 64);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, -0.3141593F);
      (this.Shape9 = new ModelRenderer(this, 0, 22)).addBox(0.0F, 0.0F, 0.0F, 6, 2, 6);
      this.Shape9.setRotationPoint(-3.0F, 16.0F, -3.0F);
      this.Shape9.setTextureSize(64, 64);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
      (this.Shape10 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 12, 1, 12);
      this.Shape10.setRotationPoint(-6.0F, 15.0F, -6.0F);
      this.Shape10.setTextureSize(64, 64);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
      (this.Shape11 = new ModelRenderer(this, 8, 55)).addBox(0.0F, 0.0F, 0.0F, 1, 6, 1);
      this.Shape11.setRotationPoint(3.3F, 7.5F, -0.5F);
      this.Shape11.setTextureSize(64, 64);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, -0.5759587F);
      (this.Shape12 = new ModelRenderer(this, 8, 56)).addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
      this.Shape12.setRotationPoint(3.3F, 7.5F, -0.5F);
      this.Shape12.setTextureSize(64, 64);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.4014257F);
      (this.Shape13 = new ModelRenderer(this, 8, 58)).addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
      this.Shape13.setRotationPoint(2.1F, 10.3F, 0.5F);
      this.Shape13.setTextureSize(64, 64);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, 0.0F, 0.0F, 0.4014257F);
      (this.Shape14 = new ModelRenderer(this, 8, 58)).addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
      this.Shape14.setRotationPoint(2.1F, 10.3F, -1.5F);
      this.Shape14.setTextureSize(64, 64);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, 0.0F, 0.0F, 0.4014257F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
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
      this.Shape13.render(f5);
      this.Shape14.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void render() {
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
      this.Shape13.render(0.0625F);
      this.Shape14.render(0.0625F);
   }
}

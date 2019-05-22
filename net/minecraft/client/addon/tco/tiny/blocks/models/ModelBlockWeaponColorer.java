package net.minecraft.client.addon.tco.tiny.blocks.models;

import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBlockWeaponColorer extends BaseModelBlock {

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
   float o = 0.0F;


   public ModelBlockWeaponColorer() {
      super.textureWidth = 128;
      super.textureHeight = 128;
      this.Shape1 = new ModelRenderer(this, 0, 46);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 16, 2, 16);
      this.Shape1.setRotationPoint(-8.0F, 22.0F, -8.0F);
      this.Shape1.setTextureSize(128, 128);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 0);
      this.Shape2.addBox(0.0F, -1.0F, 0.0F, 8, 1, 1);
      this.Shape2.setRotationPoint(-7.0F, 23.0F, -7.0F);
      this.Shape2.setTextureSize(128, 128);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, -1.22173F);
      this.Shape3 = new ModelRenderer(this, 0, 0);
      this.Shape3.addBox(-8.0F, -1.0F, 0.0F, 8, 1, 1);
      this.Shape3.setRotationPoint(7.0F, 23.0F, -7.0F);
      this.Shape3.setTextureSize(128, 128);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 1.22173F);
      this.Shape4 = new ModelRenderer(this, 0, 0);
      this.Shape4.addBox(-8.0F, -1.0F, 0.0F, 8, 1, 1);
      this.Shape4.setRotationPoint(7.0F, 23.0F, 6.0F);
      this.Shape4.setTextureSize(128, 128);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 1.22173F);
      this.Shape5 = new ModelRenderer(this, 0, 0);
      this.Shape5.addBox(0.0F, -1.0F, 0.0F, 8, 1, 1);
      this.Shape5.setRotationPoint(-7.0F, 23.0F, 6.0F);
      this.Shape5.setTextureSize(128, 128);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, -1.22173F);
      this.Shape6 = new ModelRenderer(this, 0, 4);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 4, 6, 4);
      this.Shape6.setRotationPoint(-2.0F, 16.0F, -2.0F);
      this.Shape6.setTextureSize(128, 128);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 0, 0);
      this.Shape7.addBox(0.0F, 0.0F, -1.0F, 11, 1, 1);
      this.Shape7.setRotationPoint(-5.5F, 15.0F, -6.0F);
      this.Shape7.setTextureSize(128, 128);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 0, 4);
      this.Shape8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 13);
      this.Shape8.setRotationPoint(4.0F, 14.5F, -6.5F);
      this.Shape8.setTextureSize(128, 128);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
      this.Shape9 = new ModelRenderer(this, 0, 4);
      this.Shape9.addBox(0.0F, 0.0F, 0.0F, 1, 1, 13);
      this.Shape9.setRotationPoint(-5.0F, 14.5F, -6.5F);
      this.Shape9.setTextureSize(128, 128);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 0, 0);
      this.Shape10.addBox(0.0F, 0.0F, 0.0F, 11, 1, 1);
      this.Shape10.setRotationPoint(-5.5F, 15.0F, 6.0F);
      this.Shape10.setTextureSize(128, 128);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 0, 0);
      this.Shape11.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
      this.Shape11.setRotationPoint(-1.0F, 13.5F, -1.0F);
      this.Shape11.setTextureSize(128, 128);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 0, 4);
      this.Shape12.addBox(0.0F, 0.0F, 0.0F, 9, 1, 3);
      this.Shape12.setRotationPoint(-4.5F, 14.0F, -1.5F);
      this.Shape12.setTextureSize(128, 128);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
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
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void render() {
      float f5 = 0.0625F;
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

package net.minecraft.client.addon.tco.tiny.blocks.models;

import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBlockInfo extends BaseModelBlock {

   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;


   public ModelBlockInfo() {
      super.textureWidth = 64;
      super.textureHeight = 64;
      this.Shape1 = new ModelRenderer(this, 0, 46);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 16, 2, 16);
      this.Shape1.setRotationPoint(-8.0F, 22.0F, -8.0F);
      this.Shape1.setTextureSize(128, 128);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 5);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 8, 8, 1);
      this.Shape2.setRotationPoint(-4.0F, 6.0F, 4.0F);
      this.Shape2.setTextureSize(128, 128);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, -0.7853982F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 20, 47);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 2, 13, 2);
      this.Shape3.setRotationPoint(-1.0F, 9.0F, 2.0F);
      this.Shape3.setTextureSize(128, 128);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 0);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Shape4.setRotationPoint(-1.0F, 8.0F, 3.0F);
      this.Shape4.setTextureSize(128, 128);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, -0.9075712F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 0);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Shape5.setRotationPoint(-1.0F, 7.2F, 3.0F);
      this.Shape5.setTextureSize(128, 128);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 0, 15);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 13, 1, 7);
      this.Shape6.setRotationPoint(-6.5F, 15.0F, -8.0F);
      this.Shape6.setTextureSize(128, 128);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.3316126F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 18, 47);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 2, 2, 6);
      this.Shape7.setRotationPoint(-1.0F, 17.0F, -4.0F);
      this.Shape7.setTextureSize(128, 128);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 18, 47);
      this.Shape8.addBox(0.0F, 0.0F, 0.0F, 1, 9, 1);
      this.Shape8.setRotationPoint(-0.5F, 14.0F, -4.0F);
      this.Shape8.setTextureSize(128, 128);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.2617994F, 0.0F, 0.0F);
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

   public void render() {
      this.Shape1.render(0.0625F);
      this.Shape2.render(0.0625F);
      this.Shape3.render(0.0625F);
      this.Shape4.render(0.0625F);
      this.Shape6.render(0.0625F);
      this.Shape7.render(0.0625F);
      this.Shape8.render(0.0625F);
      this.Shape5.render(0.0625F);
   }
}

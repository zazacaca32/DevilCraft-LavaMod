package net.minecraft.client.addon.tco.tiny.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class GetExPultModel extends ModelBase {

   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;


   public GetExPultModel() {
      super.textureWidth = 32;
      super.textureHeight = 32;
      this.Shape1 = new ModelRenderer(this, 0, 13);
      this.Shape1.addBox(-3.0F, 8.0F, 5.0F, 6, 16, 3);
      this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape1.setTextureSize(32, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 7);
      this.Shape2.addBox(-4.0F, 3.0F, 4.0F, 8, 3, 6);
      this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape2.setTextureSize(32, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, -0.3141593F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 0);
      this.Shape3.addBox(-5.0F, 4.0F, 3.0F, 10, 1, 6);
      this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape3.setTextureSize(32, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, -0.3141593F, 0.0F, 0.0F);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
   }

   public void render() {
      float f5 = 0.0625F;
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
   }
}

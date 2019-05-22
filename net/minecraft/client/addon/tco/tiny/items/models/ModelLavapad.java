package net.minecraft.client.addon.tco.tiny.items.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLavapad extends ModelBase {

   ModelRenderer Shape1;
   ModelRenderer Shape2;


   public ModelLavapad() {
      super.textureWidth = 32;
      super.textureHeight = 32;
      (this.Shape1 = new ModelRenderer(this, 0, 1)).addBox(-7.0F, -6.0F, 0.0F, 14, 12, 1);
      this.Shape1.setRotationPoint(-2.0F, 0.0F, 0.0F);
      this.Shape1.setTextureSize(32, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      (this.Shape2 = new ModelRenderer(this, 0, 14)).addBox(-0.5F, 4.5F, -0.3F, 1, 1, 1);
      this.Shape2.setRotationPoint(-2.0F, 0.0F, 0.0F);
      this.Shape2.setTextureSize(32, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
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

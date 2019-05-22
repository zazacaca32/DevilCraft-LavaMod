package net.minecraft.client.addon.tco.tiny.items.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPetDesk extends ModelBase {

   ModelRenderer head;
   ModelRenderer body;
   ModelRenderer rightarm;
   ModelRenderer leftarm;
   ModelRenderer rightleg;
   ModelRenderer leftleg;


   public ModelPetDesk() {
      super.textureWidth = 64;
      super.textureHeight = 32;
      (this.head = new ModelRenderer(this, 0, 0)).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
      this.head.setRotationPoint(0.0F, 0.0F, 1.0F);
      this.head.setTextureSize(64, 32);
      this.head.mirror = true;
      this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
      (this.body = new ModelRenderer(this, 32, 0)).addBox(-2.0F, 0.0F, 0.0F, 4, 5, 2);
      this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.body.setTextureSize(64, 32);
      this.body.mirror = true;
      this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
      (this.rightarm = new ModelRenderer(this, 46, 0)).addBox(-2.0F, -0.8F, -0.8F, 2, 4, 2);
      this.rightarm.setRotationPoint(-2.0F, 1.0F, 1.0F);
      this.rightarm.setTextureSize(64, 32);
      this.rightarm.mirror = true;
      this.setRotation(this.rightarm, -0.0261799F, 0.0F, 0.0F);
      (this.leftarm = new ModelRenderer(this, 46, 0)).addBox(0.0F, -0.8F, -0.8F, 2, 4, 2);
      this.leftarm.setRotationPoint(2.0F, 1.0F, 1.0F);
      this.leftarm.setTextureSize(64, 32);
      this.leftarm.mirror = true;
      this.setRotation(this.leftarm, -0.0261799F, 0.0F, 0.0F);
      (this.rightleg = new ModelRenderer(this, 0, 22)).addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
      this.rightleg.setRotationPoint(-1.0F, 5.0F, 1.0F);
      this.rightleg.setTextureSize(64, 32);
      this.rightleg.mirror = true;
      this.setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
      (this.leftleg = new ModelRenderer(this, 8, 22)).addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
      this.leftleg.setRotationPoint(1.0F, 5.0F, 1.0F);
      this.leftleg.setTextureSize(64, 32);
      this.leftleg.mirror = true;
      this.setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.head.render(f5);
      this.body.render(f5);
      this.rightarm.render(f5);
      this.leftarm.render(f5);
      this.rightleg.render(f5);
      this.leftleg.render(f5);
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

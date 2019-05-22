package net.minecraft.client.addon.tchestplate.armors.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class ModelPredatorLavaPlateLeggins extends ModelBaseLavaArmor {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;
   ModelRenderer Shape10;
   ModelRenderer Shape11;
   ModelRenderer Shape12;
   ModelRenderer Shape13;
   ModelRenderer Shape14;
   ModelRenderer Shape15;
   ModelRenderer Shape16;
   ModelRenderer Shape17;
   ModelRenderer Shape18;
   ModelRenderer Shape20;
   ModelRenderer Shape21;
   ModelRenderer Shape22;
   ModelRenderer Shape23;
   ModelRenderer Shape24;
   ModelRenderer Shape25;
   ModelRenderer Shape26;
   ModelRenderer Shape27;
   ModelRenderer Shape28;
   public static boolean invise = false;

   public ModelPredatorLavaPlateLeggins() {
      super.textureWidth = 64;
      super.textureHeight = 32;
      this.Shape1 = new ModelRenderer(this, 0, 0);
      this.Shape1.addBox(-4.5F, 9.1F, -2.5F, 9, 3, 5);
      this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 0);
      this.Shape2.addBox(6.0F, 6.0F, -3.0F, 3, 3, 1);
      this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.8028515F);
      this.Shape3 = new ModelRenderer(this, 0, 0);
      this.Shape3.addBox(6.5F, 6.5F, -3.2F, 3, 3, 1);
      this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.7853982F);
      this.Shape4 = new ModelRenderer(this, 0, 0);
      this.Shape4.addBox(8.0F, 8.0F, -2.9F, 2, 2, 1);
      this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.7853982F);
      this.Shape5 = new ModelRenderer(this, 0, 17);
      this.Shape5.addBox(7.0F, 7.0F, -3.4F, 2, 2, 1);
      this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.7853982F);
      this.Shape6 = new ModelRenderer(this, 0, 17);
      this.Shape6.addBox(-4.6F, 10.0F, -2.8F, 9, 1, 1);
      this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape6.setTextureSize(64, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 0, 0);
      this.Shape7.addBox(6.0F, 6.0F, 2.0F, 4, 4, 1);
      this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape7.setTextureSize(64, 32);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.7853982F);
      this.Shape8 = new ModelRenderer(this, 0, 17);
      this.Shape8.addBox(6.5F, 6.5F, 1.8F, 4, 4, 1);
      this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape8.setTextureSize(64, 32);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.7853982F);
      this.Shape10 = new ModelRenderer(this, 45, 19);
      this.Shape10.addBox(-2.0F, 0.0F, -2.2F, 4, 10, 1);
      this.Shape10.setTextureSize(64, 32);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 0, 17);
      this.Shape11.addBox(1.2F, 0.0F, -2.0F, 1, 10, 4);
      this.Shape11.setTextureSize(64, 32);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 34, 17);
      this.Shape12.addBox(-2.0F, 0.0F, 1.2F, 4, 10, 1);
      this.Shape12.setTextureSize(64, 32);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      this.Shape13 = new ModelRenderer(this, 0, 18);
      this.Shape13.addBox(-2.2F, 0.0F, -2.0F, 1, 10, 4);
      this.Shape13.setTextureSize(64, 32);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
      this.Shape14 = new ModelRenderer(this, 0, 1);
      this.Shape14.addBox(1.6F, -2.0F, -1.0F, 1, 3, 3);
      this.Shape14.setTextureSize(64, 32);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, 0.7853982F, 0.0F, 0.2792527F);
      this.Shape15 = new ModelRenderer(this, 0, 1);
      this.Shape15.addBox(1.9F, -1.0F, -2.0F, 1, 3, 3);
      this.Shape15.setTextureSize(64, 32);
      this.Shape15.mirror = true;
      this.setRotation(this.Shape15, 0.7853982F, 0.0F, 0.2443461F);
      this.Shape16 = new ModelRenderer(this, 0, 1);
      this.Shape16.addBox(2.2F, 0.3F, -2.3F, 1, 2, 2);
      this.Shape16.setTextureSize(64, 32);
      this.Shape16.mirror = true;
      this.setRotation(this.Shape16, 0.7853982F, 0.0F, 0.2443461F);
      this.Shape17 = new ModelRenderer(this, 0, 1);
      this.Shape17.addBox(2.5F, 1.0F, -3.0F, 1, 2, 2);
      this.Shape17.setTextureSize(64, 32);
      this.Shape17.mirror = true;
      this.setRotation(this.Shape17, 0.7853982F, 0.0F, 0.2792527F);
      this.Shape18 = new ModelRenderer(this, 0, 0);
      this.Shape18.addBox(-2.0F, -0.2F, -2.0F, 4, 1, 4);
      this.Shape18.setTextureSize(64, 32);
      this.Shape18.mirror = true;
      this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
      this.Shape20 = new ModelRenderer(this, 39, 19);
      this.Shape20.addBox(-2.0F, 0.0F, -2.2F, 4, 10, 1);
      this.Shape20.setTextureSize(64, 32);
      this.Shape20.mirror = true;
      this.setRotation(this.Shape20, 0.0F, 0.0F, 0.0F);
      this.Shape21 = new ModelRenderer(this, 0, 17);
      this.Shape21.addBox(-2.2F, 0.0F, -2.0F, 1, 10, 4);
      this.Shape21.setTextureSize(64, 32);
      this.Shape21.mirror = true;
      this.setRotation(this.Shape21, 0.0F, 0.0F, 0.0F);
      this.Shape22 = new ModelRenderer(this, 40, 17);
      this.Shape22.addBox(-2.0F, 0.0F, 1.2F, 4, 10, 1);
      this.Shape22.setTextureSize(64, 32);
      this.Shape22.mirror = true;
      this.setRotation(this.Shape22, 0.0F, 0.0F, 0.0F);
      this.Shape23 = new ModelRenderer(this, 0, 18);
      this.Shape23.addBox(1.2F, 0.0F, -2.0F, 1, 10, 4);
      this.Shape23.setTextureSize(64, 32);
      this.Shape23.mirror = true;
      this.setRotation(this.Shape23, 0.0F, 0.0F, 0.0F);
      this.Shape24 = new ModelRenderer(this, 0, 0);
      this.Shape24.addBox(-2.6F, -2.0F, -1.0F, 1, 3, 3);
      this.Shape24.setTextureSize(64, 32);
      this.Shape24.mirror = true;
      this.setRotation(this.Shape24, 0.7853982F, 0.0F, -0.2792527F);
      this.Shape25 = new ModelRenderer(this, 0, 0);
      this.Shape25.addBox(-2.9F, -1.0F, -2.0F, 1, 3, 3);
      this.Shape25.setTextureSize(64, 32);
      this.Shape25.mirror = true;
      this.setRotation(this.Shape25, 0.8028515F, 0.0F, -0.2443461F);
      this.Shape26 = new ModelRenderer(this, 0, 0);
      this.Shape26.addBox(-3.2F, 0.3F, -2.3F, 1, 2, 2);
      this.Shape26.setTextureSize(64, 32);
      this.Shape26.mirror = true;
      this.setRotation(this.Shape26, 0.7853982F, 0.0F, -0.2443461F);
      this.Shape27 = new ModelRenderer(this, 0, 0);
      this.Shape27.addBox(-3.5F, 1.0F, -3.0F, 1, 2, 2);
      this.Shape27.setTextureSize(64, 32);
      this.Shape27.mirror = true;
      this.setRotation(this.Shape27, 0.7853982F, 0.0F, -0.2792527F);
      this.Shape28 = new ModelRenderer(this, 0, 0);
      this.Shape28.addBox(-2.0F, -0.2F, -2.0F, 4, 1, 4);
      this.Shape28.setTextureSize(64, 32);
      this.Shape28.mirror = true;
      this.setRotation(this.Shape28, 0.0F, 0.0F, 0.0F);
      super.bipedRightLeg.cubeList.clear();
      super.bipedRightLeg.addChild(this.Shape20);
      super.bipedRightLeg.addChild(this.Shape21);
      super.bipedRightLeg.addChild(this.Shape22);
      super.bipedRightLeg.addChild(this.Shape23);
      super.bipedRightLeg.addChild(this.Shape24);
      super.bipedRightLeg.addChild(this.Shape25);
      super.bipedRightLeg.addChild(this.Shape26);
      super.bipedRightLeg.addChild(this.Shape27);
      super.bipedRightLeg.addChild(this.Shape28);
      super.bipedLeftLeg.cubeList.clear();
      super.bipedLeftLeg.addChild(this.Shape10);
      super.bipedLeftLeg.addChild(this.Shape11);
      super.bipedLeftLeg.addChild(this.Shape12);
      super.bipedLeftLeg.addChild(this.Shape13);
      super.bipedLeftLeg.addChild(this.Shape14);
      super.bipedLeftLeg.addChild(this.Shape15);
      super.bipedLeftLeg.addChild(this.Shape16);
      super.bipedLeftLeg.addChild(this.Shape17);
      super.bipedLeftLeg.addChild(this.Shape18);
      super.bipedBody.cubeList.clear();
      super.bipedBody.addChild(this.Shape1);
      super.bipedBody.addChild(this.Shape2);
      super.bipedBody.addChild(this.Shape3);
      super.bipedBody.addChild(this.Shape4);
      super.bipedBody.addChild(this.Shape5);
      super.bipedBody.addChild(this.Shape6);
      super.bipedBody.addChild(this.Shape7);
      super.bipedBody.addChild(this.Shape8);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      boolean flag = false;
      if(entity != null && entity instanceof EntityPlayer) {
         flag = true;
      }

      if(entity.isInvisible()) {
         Minecraft.getMinecraft().renderEngine.bindTexture("/mods/underqoder/Armors/Predator/predatorRBT2.png");
         GL11.glEnable(3042);
         GL11.glBlendFunc(1, 1);
      } else {
         Minecraft.getMinecraft().renderEngine.bindTexture("/mods/underqoder/Armors/Predator/predatorRBT.png");
      }

      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      if(entity.isInvisible()) {
         GL11.glDisable(3042);
      }

   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
   }
}

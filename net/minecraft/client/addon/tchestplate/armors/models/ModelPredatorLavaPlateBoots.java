package net.minecraft.client.addon.tchestplate.armors.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class ModelPredatorLavaPlateBoots extends ModelBaseLavaArmor {
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
   ModelRenderer Shape15;
   ModelRenderer Shape16;
   ModelRenderer Shape17;
   ModelRenderer Shape18;
   public static boolean invise = false;

   public ModelPredatorLavaPlateBoots() {
      super.textureWidth = 64;
      super.textureHeight = 32;
      this.Shape1 = new ModelRenderer(this, 0, 17);
      this.Shape1.addBox(-2.5F, 11.1F, -3.5F, 5, 1, 6);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 0);
      this.Shape2.addBox(-2.0F, 9.0F, -1.3F, 4, 3, 1);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, -0.1047198F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 0);
      this.Shape3.addBox(0.3333333F, 9.0F, -2.0F, 1, 3, 4);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, -0.1047198F);
      this.Shape4 = new ModelRenderer(this, 0, 0);
      this.Shape4.addBox(-2.0F, 9.0F, 0.3F, 4, 3, 1);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.1047198F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 0);
      this.Shape5.addBox(-1.3F, 9.0F, -2.0F, 1, 3, 4);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.1047198F);
      this.Shape6 = new ModelRenderer(this, 0, 0);
      this.Shape6.addBox(2.0F, 7.0F, -1.0F, 1, 3, 2);
      this.Shape6.setTextureSize(64, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0872665F);
      this.Shape7 = new ModelRenderer(this, 57, 7);
      this.Shape7.addBox(-0.5F, 8.9F, -7.3F, 1, 1, 3);
      this.Shape7.setTextureSize(64, 32);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.296706F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 58, 7);
      this.Shape8.addBox(0.5F, 9.2F, -7.0F, 1, 1, 2);
      this.Shape8.setTextureSize(64, 32);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.296706F, 0.0F, 0.0F);
      this.Shape9 = new ModelRenderer(this, 58, 7);
      this.Shape9.addBox(-1.5F, 9.2F, -7.0F, 1, 1, 2);
      this.Shape9.setTextureSize(64, 32);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.296706F, 0.0F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 0, 17);
      this.Shape10.addBox(-2.5F, 11.1F, -3.5F, 5, 1, 6);
      this.Shape10.setTextureSize(64, 32);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 1, 0);
      this.Shape11.addBox(-2.0F, 9.0F, -1.3F, 4, 3, 1);
      this.Shape11.setTextureSize(64, 32);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, -0.1047198F, 0.0F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 0, 0);
      this.Shape12.addBox(-1.4F, 9.0F, -2.0F, 1, 3, 4);
      this.Shape12.setTextureSize(64, 32);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.1047198F);
      this.Shape13 = new ModelRenderer(this, 0, 0);
      this.Shape13.addBox(-2.0F, 9.0F, 0.3F, 4, 3, 1);
      this.Shape13.setTextureSize(64, 32);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, 0.1047198F, 0.0F, 0.0F);
      this.Shape14 = new ModelRenderer(this, 0, 0);
      this.Shape14.addBox(0.2F, 9.0F, -2.0F, 1, 3, 4);
      this.Shape14.setTextureSize(64, 32);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, 0.0F, 0.0F, -0.1047198F);
      this.Shape15 = new ModelRenderer(this, 0, 0);
      this.Shape15.addBox(-3.3F, 7.0F, -1.0F, 1, 3, 2);
      this.Shape15.setTextureSize(64, 32);
      this.Shape15.mirror = true;
      this.setRotation(this.Shape15, 0.0F, 0.0F, -0.0872665F);
      this.Shape16 = new ModelRenderer(this, 57, 7);
      this.Shape16.addBox(-0.5F, 8.9F, -7.3F, 1, 1, 3);
      this.Shape16.setTextureSize(64, 32);
      this.Shape16.mirror = true;
      this.setRotation(this.Shape16, 0.296706F, 0.0F, 0.0F);
      this.Shape17 = new ModelRenderer(this, 58, 7);
      this.Shape17.addBox(0.5F, 9.2F, -7.0F, 1, 1, 2);
      this.Shape17.setTextureSize(64, 32);
      this.Shape17.mirror = true;
      this.setRotation(this.Shape17, 0.296706F, 0.0F, 0.0F);
      this.Shape18 = new ModelRenderer(this, 58, 7);
      this.Shape18.addBox(-1.5F, 9.2F, -7.0F, 1, 1, 2);
      this.Shape18.setTextureSize(64, 32);
      this.Shape18.mirror = true;
      this.setRotation(this.Shape18, 0.296706F, 0.0F, 0.0F);
      super.bipedRightLeg.cubeList.clear();
      super.bipedRightLeg.addChild(this.Shape10);
      super.bipedRightLeg.addChild(this.Shape11);
      super.bipedRightLeg.addChild(this.Shape12);
      super.bipedRightLeg.addChild(this.Shape13);
      super.bipedRightLeg.addChild(this.Shape14);
      super.bipedRightLeg.addChild(this.Shape15);
      super.bipedRightLeg.addChild(this.Shape16);
      super.bipedRightLeg.addChild(this.Shape17);
      super.bipedRightLeg.addChild(this.Shape18);
      super.bipedLeftLeg.cubeList.clear();
      super.bipedLeftLeg.addChild(this.Shape1);
      super.bipedLeftLeg.addChild(this.Shape2);
      super.bipedLeftLeg.addChild(this.Shape3);
      super.bipedLeftLeg.addChild(this.Shape4);
      super.bipedLeftLeg.addChild(this.Shape5);
      super.bipedLeftLeg.addChild(this.Shape6);
      super.bipedLeftLeg.addChild(this.Shape7);
      super.bipedLeftLeg.addChild(this.Shape8);
      super.bipedLeftLeg.addChild(this.Shape9);
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

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}

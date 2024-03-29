package net.minecraft.client.addon.tchestplate.armors.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class ModelPredatorLavaPlate extends ModelBaseLavaArmor {
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
   ModelRenderer Shape100;
   ModelRenderer Shape101;
   ModelRenderer Shape102;
   ModelRenderer Shape103;
   ModelRenderer Shape104;
   ModelRenderer Shape105;
   ModelRenderer Shape106;
   ModelRenderer Shape107;
   ModelRenderer Shape108;
   ModelRenderer Shape109;
   ModelRenderer Shape110;
   ModelRenderer Shape111;
   ModelRenderer Shape112;
   ModelRenderer Shape113;
   ModelRenderer Shape114;
   ModelRenderer Shape115;
   ModelRenderer Shape200;
   ModelRenderer Shape201;
   ModelRenderer Shape202;
   ModelRenderer Shape203;
   ModelRenderer Shape204;
   ModelRenderer Shape205;
   ModelRenderer Shape206;
   ModelRenderer Shape207;
   ModelRenderer Shape208;
   ModelRenderer Shape209;
   ModelRenderer Shape210;
   ModelRenderer Shape211;
   ModelRenderer Shape212;
   ModelRenderer Shape213;
   ModelRenderer Shape214;
   ModelRenderer Shape215;

   public ModelPredatorLavaPlate() {
      super.textureWidth = 64;
      super.textureHeight = 32;
      this.Shape1 = new ModelRenderer(this, 29, 19);
      this.Shape1.addBox(-4.0F, 0.0F, -2.4F, 8, 12, 1);
      this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 16);
      this.Shape2.addBox(3.2F, 0.0F, -2.0F, 1, 12, 4);
      this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 17);
      this.Shape3.addBox(-4.0F, 0.0F, 1.4F, 8, 12, 1);
      this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 16);
      this.Shape4.addBox(-4.2F, 0.0F, -2.0F, 1, 12, 4);
      this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 17);
      this.Shape5.addBox(-4.0F, -0.2F, -2.0F, 8, 1, 4);
      this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 0, 0);
      this.Shape6.addBox(-4.0F, 1.0F, -3.4F, 4, 4, 2);
      this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape6.setTextureSize(64, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0698132F);
      this.Shape7 = new ModelRenderer(this, 0, 0);
      this.Shape7.addBox(0.0F, 1.0F, -3.4F, 4, 4, 2);
      this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape7.setTextureSize(64, 32);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, -0.0698132F);
      this.Shape8 = new ModelRenderer(this, 2, 0);
      this.Shape8.addBox(-4.5F, 5.5F, -2.433333F, 3, 1, 2);
      this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape8.setTextureSize(64, 32);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, -0.1745329F, 0.0F);
      this.Shape9 = new ModelRenderer(this, 0, 0);
      this.Shape9.addBox(-4.5F, 6.5F, -2.2F, 2, 1, 2);
      this.Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape9.setTextureSize(64, 32);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, -0.1745329F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 1, 0);
      this.Shape10.addBox(-4.5F, 4.5F, -2.6F, 4, 1, 2);
      this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape10.setTextureSize(64, 32);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, -0.1745329F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 56, 15);
      this.Shape11.addBox(-0.5F, 0.0F, 2.7F, 1, 11, 1);
      this.Shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape11.setTextureSize(64, 32);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, -0.1047198F, 0.0F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 0, 0);
      this.Shape12.addBox(0.5F, 4.5F, -2.6F, 4, 1, 2);
      this.Shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape12.setTextureSize(64, 32);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.1745329F, 0.0F);
      this.Shape13 = new ModelRenderer(this, 0, 0);
      this.Shape13.addBox(1.5F, 5.5F, -2.4F, 3, 1, 2);
      this.Shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape13.setTextureSize(64, 32);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, 0.0F, 0.1745329F, 0.0F);
      this.Shape14 = new ModelRenderer(this, 0, 0);
      this.Shape14.addBox(2.5F, 6.5F, -2.2F, 2, 1, 1);
      this.Shape14.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape14.setTextureSize(64, 32);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, 0.0F, 0.1745329F, 0.0F);
      this.Shape15 = new ModelRenderer(this, 0, 0);
      this.Shape15.addBox(-2.0F, 0.0F, 2.3F, 4, 10, 1);
      this.Shape15.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape15.setTextureSize(64, 32);
      this.Shape15.mirror = true;
      this.setRotation(this.Shape15, -0.0698132F, 0.0F, 0.0F);
      this.Shape16 = new ModelRenderer(this, 0, 0);
      this.Shape16.addBox(-4.1F, 1.0F, -0.3F, 3, 6, 4);
      this.Shape16.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape16.setTextureSize(64, 32);
      this.Shape16.mirror = true;
      this.setRotation(this.Shape16, -0.122173F, 0.0F, 0.0F);
      this.Shape17 = new ModelRenderer(this, 0, 0);
      this.Shape17.addBox(1.1F, 1.0F, -0.3F, 3, 6, 4);
      this.Shape17.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape17.setTextureSize(64, 32);
      this.Shape17.mirror = true;
      this.setRotation(this.Shape17, -0.122173F, 0.0F, 0.0F);
      this.Shape100 = new ModelRenderer(this, 0, 0);
      this.Shape100.addBox(-0.8F, -2.3F, -3.5F, 1, 3, 7);
      this.Shape100.setTextureSize(64, 32);
      this.Shape100.mirror = true;
      this.setRotation(this.Shape100, 0.0F, 0.0F, -0.0872665F);
      this.Shape101 = new ModelRenderer(this, 0, 5);
      this.Shape101.addBox(0.0F, -2.6F, -3.0F, 1, 3, 6);
      this.Shape101.setTextureSize(64, 32);
      this.Shape101.mirror = true;
      this.setRotation(this.Shape101, 0.0F, 0.0F, 0.0F);
      this.Shape102 = new ModelRenderer(this, 0, 4);
      this.Shape102.addBox(0.5F, -3.3F, -2.6F, 1, 2, 5);
      this.Shape102.setTextureSize(64, 32);
      this.Shape102.mirror = true;
      this.setRotation(this.Shape102, 0.0F, 0.0F, 0.4886922F);
      this.Shape103 = new ModelRenderer(this, 0, 4);
      this.Shape103.addBox(1.6F, 1.0F, -2.4F, 2, 1, 5);
      this.Shape103.setTextureSize(64, 32);
      this.Shape103.mirror = true;
      this.setRotation(this.Shape103, 0.0F, 0.0F, -0.9773844F);
      this.Shape104 = new ModelRenderer(this, 0, 0);
      this.Shape104.addBox(2.0F, 0.3F, -2.3F, 2, 1, 5);
      this.Shape104.setTextureSize(64, 32);
      this.Shape104.mirror = true;
      this.setRotation(this.Shape104, 0.0F, 0.0F, -0.5235988F);
      this.Shape105 = new ModelRenderer(this, 0, 1);
      this.Shape105.addBox(2.6F, -2.0F, -1.0F, 1, 3, 3);
      this.Shape105.setTextureSize(64, 32);
      this.Shape105.mirror = true;
      this.setRotation(this.Shape105, 0.7853982F, 0.0F, 0.2792527F);
      this.Shape106 = new ModelRenderer(this, 0, 1);
      this.Shape106.addBox(2.9F, -1.0F, -2.0F, 1, 3, 3);
      this.Shape106.setTextureSize(64, 32);
      this.Shape106.mirror = true;
      this.setRotation(this.Shape106, 0.7853982F, 0.0F, 0.2443461F);
      this.Shape107 = new ModelRenderer(this, 0, 1);
      this.Shape107.addBox(3.2F, 0.3F, -2.3F, 1, 2, 2);
      this.Shape107.setTextureSize(64, 32);
      this.Shape107.mirror = true;
      this.setRotation(this.Shape107, 0.7853982F, 0.0F, 0.2443461F);
      this.Shape108 = new ModelRenderer(this, 0, 1);
      this.Shape108.addBox(3.5F, 1.0F, -3.0F, 1, 2, 2);
      this.Shape108.setTextureSize(64, 32);
      this.Shape108.mirror = true;
      this.setRotation(this.Shape108, 0.7853982F, 0.0F, 0.2792527F);
      this.Shape109 = new ModelRenderer(this, 0, 1);
      this.Shape109.addBox(3.0F, 4.0F, -1.0F, 1, 5, 2);
      this.Shape109.setTextureSize(64, 32);
      this.Shape109.mirror = true;
      this.setRotation(this.Shape109, 0.0F, 0.0F, 0.0872665F);
      this.Shape110 = new ModelRenderer(this, 0, 0);
      this.Shape110.addBox(1.3F, 5.0F, -2.5F, 2, 4, 5);
      this.Shape110.setTextureSize(64, 32);
      this.Shape110.mirror = true;
      this.setRotation(this.Shape110, 0.0F, 0.0F, 0.0F);
      this.Shape111 = new ModelRenderer(this, 45, 20);
      this.Shape111.addBox(-1.0F, -2.0F, -2.2F, 4, 10, 1);
      this.Shape111.setTextureSize(64, 32);
      this.Shape111.mirror = true;
      this.setRotation(this.Shape111, 0.0F, 0.0F, 0.0F);
      this.Shape112 = new ModelRenderer(this, 0, 17);
      this.Shape112.addBox(2.2F, -2.0F, -2.0F, 1, 10, 4);
      this.Shape112.setTextureSize(64, 32);
      this.Shape112.mirror = true;
      this.setRotation(this.Shape112, 0.0F, 0.0F, 0.0F);
      this.Shape113 = new ModelRenderer(this, 34, 20);
      this.Shape113.addBox(-1.0F, -2.0F, 1.2F, 4, 10, 1);
      this.Shape113.setTextureSize(64, 32);
      this.Shape113.mirror = true;
      this.setRotation(this.Shape113, 0.0F, 0.0F, 0.0F);
      this.Shape114 = new ModelRenderer(this, 43, 17);
      this.Shape114.addBox(-1.2F, -2.0F, -2.0F, 1, 10, 4);
      this.Shape114.setTextureSize(64, 32);
      this.Shape114.mirror = true;
      this.setRotation(this.Shape114, 0.0F, 0.0F, 0.0F);
      this.Shape115 = new ModelRenderer(this, 0, 0);
      this.Shape115.addBox(0.1F, -2.9F, -2.8F, 1, 2, 6);
      this.Shape115.setTextureSize(64, 32);
      this.Shape115.mirror = true;
      this.setRotation(this.Shape115, 0.0F, 0.0F, 0.3665191F);
      this.Shape200 = new ModelRenderer(this, 0, 0);
      this.Shape200.addBox(-0.2F, -2.3F, -3.5F, 1, 3, 7);
      this.Shape200.setTextureSize(64, 32);
      this.Shape200.mirror = true;
      this.setRotation(this.Shape200, 0.0F, 0.0F, 0.0872665F);
      this.Shape201 = new ModelRenderer(this, 0, 0);
      this.Shape201.addBox(-1.0F, -2.6F, -3.0F, 1, 3, 6);
      this.Shape201.setTextureSize(64, 32);
      this.Shape201.mirror = true;
      this.setRotation(this.Shape201, 0.0F, 0.0F, 0.0F);
      this.Shape202 = new ModelRenderer(this, 0, 0);
      this.Shape202.addBox(-1.0F, -2.9F, -2.8F, 1, 2, 6);
      this.Shape202.setTextureSize(64, 32);
      this.Shape202.mirror = true;
      this.setRotation(this.Shape202, 0.0F, 0.0F, -0.3665191F);
      this.Shape203 = new ModelRenderer(this, 0, 0);
      this.Shape203.addBox(-1.5F, -3.3F, -2.6F, 1, 2, 5);
      this.Shape203.setTextureSize(64, 32);
      this.Shape203.mirror = true;
      this.setRotation(this.Shape203, 0.0F, 0.0F, -0.4886922F);
      this.Shape204 = new ModelRenderer(this, 0, 0);
      this.Shape204.addBox(-3.6F, 1.0F, -2.4F, 2, 1, 5);
      this.Shape204.setTextureSize(64, 32);
      this.Shape204.mirror = true;
      this.setRotation(this.Shape204, 0.0F, 0.0F, 0.9773844F);
      this.Shape205 = new ModelRenderer(this, 0, 0);
      this.Shape205.addBox(-4.0F, 0.3F, -2.3F, 2, 1, 5);
      this.Shape205.setTextureSize(64, 32);
      this.Shape205.mirror = true;
      this.setRotation(this.Shape205, 0.0F, 0.0F, 0.5235988F);
      this.Shape206 = new ModelRenderer(this, 0, 0);
      this.Shape206.addBox(-3.6F, -2.0F, -1.0F, 1, 3, 3);
      this.Shape206.setTextureSize(64, 32);
      this.Shape206.mirror = true;
      this.setRotation(this.Shape206, 0.7853982F, 0.0F, -0.2792527F);
      this.Shape207 = new ModelRenderer(this, 0, 0);
      this.Shape207.addBox(-3.9F, -1.0F, -2.0F, 1, 3, 3);
      this.Shape207.setTextureSize(64, 32);
      this.Shape207.mirror = true;
      this.setRotation(this.Shape207, 0.8028515F, 0.0F, -0.2443461F);
      this.Shape208 = new ModelRenderer(this, 0, 0);
      this.Shape208.addBox(-4.2F, 0.3F, -2.3F, 1, 2, 2);
      this.Shape208.setTextureSize(64, 32);
      this.Shape208.mirror = true;
      this.setRotation(this.Shape208, 0.7853982F, 0.0F, -0.2443461F);
      this.Shape209 = new ModelRenderer(this, 0, 0);
      this.Shape209.addBox(-4.5F, 1.0F, -3.0F, 1, 2, 2);
      this.Shape209.setTextureSize(64, 32);
      this.Shape209.mirror = true;
      this.setRotation(this.Shape209, 0.7853982F, 0.0F, -0.2792527F);
      this.Shape210 = new ModelRenderer(this, 0, 0);
      this.Shape210.addBox(-4.0F, 4.0F, -1.0F, 1, 5, 2);
      this.Shape210.setTextureSize(64, 32);
      this.Shape210.mirror = true;
      this.setRotation(this.Shape210, 0.0F, 0.0F, -0.0872665F);
      this.Shape211 = new ModelRenderer(this, 0, 0);
      this.Shape211.addBox(-3.3F, 5.0F, -2.5F, 2, 4, 5);
      this.Shape211.setTextureSize(64, 32);
      this.Shape211.mirror = true;
      this.setRotation(this.Shape211, 0.0F, 0.0F, 0.0F);
      this.Shape212 = new ModelRenderer(this, 39, 20);
      this.Shape212.addBox(-3.0F, -2.0F, -2.2F, 4, 10, 1);
      this.Shape212.setTextureSize(64, 32);
      this.Shape212.mirror = true;
      this.setRotation(this.Shape212, 0.0F, 0.0F, 0.0F);
      this.Shape213 = new ModelRenderer(this, 0, 17);
      this.Shape213.addBox(-3.2F, -2.0F, -2.0F, 1, 10, 4);
      this.Shape213.setTextureSize(64, 32);
      this.Shape213.mirror = true;
      this.setRotation(this.Shape213, 0.0F, 0.0F, 0.0F);
      this.Shape214 = new ModelRenderer(this, 40, 20);
      this.Shape214.addBox(-3.0F, -2.0F, 1.2F, 4, 10, 1);
      this.Shape214.setTextureSize(64, 32);
      this.Shape214.mirror = true;
      this.setRotation(this.Shape214, 0.0F, 0.0F, 0.0F);
      this.Shape215 = new ModelRenderer(this, 38, 17);
      this.Shape215.addBox(0.2F, -2.0F, -2.0F, 1, 10, 4);
      this.Shape215.setTextureSize(64, 32);
      this.Shape215.mirror = true;
      this.setRotation(this.Shape215, 0.0F, 0.0F, 0.0F);
      super.bipedBody.addChild(this.Shape1);
      super.bipedBody.addChild(this.Shape2);
      super.bipedBody.addChild(this.Shape3);
      super.bipedBody.addChild(this.Shape4);
      super.bipedBody.addChild(this.Shape5);
      super.bipedBody.addChild(this.Shape6);
      super.bipedBody.addChild(this.Shape7);
      super.bipedBody.addChild(this.Shape8);
      super.bipedBody.addChild(this.Shape9);
      super.bipedBody.addChild(this.Shape10);
      super.bipedBody.addChild(this.Shape11);
      super.bipedBody.addChild(this.Shape12);
      super.bipedBody.addChild(this.Shape13);
      super.bipedBody.addChild(this.Shape14);
      super.bipedBody.addChild(this.Shape15);
      super.bipedBody.addChild(this.Shape16);
      super.bipedBody.addChild(this.Shape17);
      super.bipedRightArm.cubeList.clear();
      super.bipedRightArm.addChild(this.Shape200);
      super.bipedRightArm.addChild(this.Shape201);
      super.bipedRightArm.addChild(this.Shape202);
      super.bipedRightArm.addChild(this.Shape203);
      super.bipedRightArm.addChild(this.Shape204);
      super.bipedRightArm.addChild(this.Shape205);
      super.bipedRightArm.addChild(this.Shape206);
      super.bipedRightArm.addChild(this.Shape207);
      super.bipedRightArm.addChild(this.Shape208);
      super.bipedRightArm.addChild(this.Shape209);
      super.bipedRightArm.addChild(this.Shape210);
      super.bipedRightArm.addChild(this.Shape211);
      super.bipedRightArm.addChild(this.Shape212);
      super.bipedRightArm.addChild(this.Shape213);
      super.bipedRightArm.addChild(this.Shape214);
      super.bipedRightArm.addChild(this.Shape215);
      super.bipedLeftArm.cubeList.clear();
      super.bipedLeftArm.addChild(this.Shape100);
      super.bipedLeftArm.addChild(this.Shape101);
      super.bipedLeftArm.addChild(this.Shape102);
      super.bipedLeftArm.addChild(this.Shape103);
      super.bipedLeftArm.addChild(this.Shape104);
      super.bipedLeftArm.addChild(this.Shape105);
      super.bipedLeftArm.addChild(this.Shape106);
      super.bipedLeftArm.addChild(this.Shape107);
      super.bipedLeftArm.addChild(this.Shape108);
      super.bipedLeftArm.addChild(this.Shape109);
      super.bipedLeftArm.addChild(this.Shape110);
      super.bipedLeftArm.addChild(this.Shape111);
      super.bipedLeftArm.addChild(this.Shape112);
      super.bipedLeftArm.addChild(this.Shape113);
      super.bipedLeftArm.addChild(this.Shape114);
      super.bipedLeftArm.addChild(this.Shape115);
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

package net.minecraft.client.addon.tco.tiny.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class GetExperienceModel extends ModelBase {

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
   ModelRenderer Shape20;
   ModelRenderer Shape21;
   ModelRenderer Shape22;
   ModelRenderer Shape23;
   ModelRenderer Shape24;
   ModelRenderer Shape25;
   ModelRenderer Shape26;
   ModelRenderer Shape27;
   ModelRenderer Shape28;
   ModelRenderer Shape29;
   ModelRenderer Shape30;
   ModelRenderer Shape31;
   ModelRenderer Shape32;
   ModelRenderer Shape33;
   ModelRenderer Shape34;
   ModelRenderer Shape35;
   ModelRenderer Shape36;
   ModelRenderer Shape37;
   ModelRenderer Shape38;
   ModelRenderer Shape39;
   ModelRenderer Shape40;
   ModelRenderer Shape41;
   ModelRenderer Shape42;
   ModelRenderer Shape43;
   ModelRenderer Shape44;
   ModelRenderer Shape45;
   ModelRenderer Shape46;
   ModelRenderer Shape47;
   ModelRenderer Shape48;
   long tim = 0L;
   int ren = 1;
   boolean t = true;


   public GetExperienceModel() {
      super.textureWidth = 128;
      super.textureHeight = 64;
      this.Shape1 = new ModelRenderer(this, 74, 0);
      this.Shape1.addBox(-7.0F, 22.0F, -7.0F, 14, 2, 14);
      this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape1.setTextureSize(128, 64);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 72, 0);
      this.Shape2.addBox(8.0F, 19.8F, -2.0F, 4, 4, 4);
      this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape2.setTextureSize(128, 64);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.7853982F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 72, 0);
      this.Shape3.addBox(-12.0F, 19.8F, -2.0F, 4, 4, 4);
      this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape3.setTextureSize(128, 64);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.7853982F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 72, 0);
      this.Shape4.addBox(-2.0F, 19.8F, -12.0F, 4, 4, 4);
      this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape4.setTextureSize(128, 64);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.7853982F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 72, 0);
      this.Shape5.addBox(-2.0F, 19.8F, 8.0F, 4, 4, 4);
      this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape5.setTextureSize(128, 64);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.7853982F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 0, 0);
      this.Shape6.addBox(-5.0F, 21.0F, -5.0F, 10, 1, 10);
      this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape6.setTextureSize(128, 64);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 72, 0);
      this.Shape7.addBox(-2.0F, -12.8F, 8.0F, 4, 4, 4);
      this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape7.setTextureSize(128, 64);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, -0.7853982F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 74, 0);
      this.Shape8.addBox(-7.0F, -13.0F, -7.0F, 14, 2, 14);
      this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape8.setTextureSize(128, 64);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
      this.Shape9 = new ModelRenderer(this, 72, 0);
      this.Shape9.addBox(8.0F, -12.8F, -2.0F, 4, 4, 4);
      this.Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape9.setTextureSize(128, 64);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, -0.7853982F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 72, 0);
      this.Shape10.addBox(-12.0F, -12.8F, -2.0F, 4, 4, 4);
      this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape10.setTextureSize(128, 64);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, -0.7853982F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 72, 0);
      this.Shape11.addBox(-2.0F, -12.8F, -12.0F, 4, 4, 4);
      this.Shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape11.setTextureSize(128, 64);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, -0.7853982F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 40, 0);
      this.Shape12.addBox(-5.0F, 21.5F, -5.0F, 10, 1, 10);
      this.Shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape12.setTextureSize(128, 64);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      this.Shape20 = new ModelRenderer(this, 0, 35);
      this.Shape20.addBox(-14.0F, 19.0F, -14.0F, 28, 1, 28);
      this.Shape20.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape20.setTextureSize(128, 64);
      this.Shape20.mirror = true;
      this.setRotation(this.Shape20, 0.0F, 0.0F, 0.0F);
      this.Shape21 = new ModelRenderer(this, 0, 35);
      this.Shape21.addBox(-14.0F, 18.0F, -14.0F, 28, 1, 28);
      this.Shape21.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape21.setTextureSize(128, 64);
      this.Shape21.mirror = true;
      this.setRotation(this.Shape21, 0.0F, 0.3490659F, 0.0F);
      this.Shape22 = new ModelRenderer(this, 0, 35);
      this.Shape22.addBox(-14.0F, 17.0F, -14.0F, 28, 1, 28);
      this.Shape22.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape22.setTextureSize(128, 64);
      this.Shape22.mirror = true;
      this.setRotation(this.Shape22, 0.0F, 0.6981317F, 0.0F);
      this.Shape23 = new ModelRenderer(this, 0, 35);
      this.Shape23.addBox(-14.0F, 16.0F, -14.0F, 28, 1, 28);
      this.Shape23.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape23.setTextureSize(128, 64);
      this.Shape23.mirror = true;
      this.setRotation(this.Shape23, 0.0F, 1.047198F, 0.0F);
      this.Shape24 = new ModelRenderer(this, 0, 35);
      this.Shape24.addBox(-14.0F, 15.0F, -14.0F, 28, 1, 28);
      this.Shape24.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape24.setTextureSize(128, 64);
      this.Shape24.mirror = true;
      this.setRotation(this.Shape24, 0.0F, 1.396263F, 0.0F);
      this.Shape25 = new ModelRenderer(this, 0, 35);
      this.Shape25.addBox(-14.0F, 14.0F, -14.0F, 28, 1, 28);
      this.Shape25.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape25.setTextureSize(128, 64);
      this.Shape25.mirror = true;
      this.setRotation(this.Shape25, 0.0F, 1.745329F, 0.0F);
      this.Shape26 = new ModelRenderer(this, 0, 35);
      this.Shape26.addBox(-14.0F, 13.0F, -14.0F, 28, 1, 28);
      this.Shape26.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape26.setTextureSize(128, 64);
      this.Shape26.mirror = true;
      this.setRotation(this.Shape26, 0.0F, 2.094395F, 0.0F);
      this.Shape27 = new ModelRenderer(this, 0, 35);
      this.Shape27.addBox(-14.0F, 12.0F, -14.0F, 28, 1, 28);
      this.Shape27.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape27.setTextureSize(128, 64);
      this.Shape27.mirror = true;
      this.setRotation(this.Shape27, 0.0F, 2.443461F, 0.0F);
      this.Shape28 = new ModelRenderer(this, 0, 35);
      this.Shape28.addBox(-14.0F, 11.0F, -14.0F, 28, 1, 28);
      this.Shape28.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape28.setTextureSize(128, 64);
      this.Shape28.mirror = true;
      this.setRotation(this.Shape28, 0.0F, 2.792527F, 0.0F);
      this.Shape29 = new ModelRenderer(this, 0, 35);
      this.Shape29.addBox(-14.0F, 10.0F, -14.0F, 28, 1, 28);
      this.Shape29.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape29.setTextureSize(128, 64);
      this.Shape29.mirror = true;
      this.setRotation(this.Shape29, 0.0F, 3.141593F, 0.0F);
      this.Shape30 = new ModelRenderer(this, 0, 35);
      this.Shape30.addBox(-14.0F, 9.0F, -14.0F, 28, 1, 28);
      this.Shape30.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape30.setTextureSize(128, 64);
      this.Shape30.mirror = true;
      this.setRotation(this.Shape30, 0.0F, -2.792527F, 0.0F);
      this.Shape31 = new ModelRenderer(this, 0, 35);
      this.Shape31.addBox(-14.0F, 8.0F, -14.0F, 28, 1, 28);
      this.Shape31.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape31.setTextureSize(128, 64);
      this.Shape31.mirror = true;
      this.setRotation(this.Shape31, 0.0F, -2.443461F, 0.0F);
      this.Shape32 = new ModelRenderer(this, 0, 35);
      this.Shape32.addBox(-14.0F, 7.0F, -14.0F, 28, 1, 28);
      this.Shape32.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape32.setTextureSize(128, 64);
      this.Shape32.mirror = true;
      this.setRotation(this.Shape32, 0.0F, -2.094395F, 0.0F);
      this.Shape33 = new ModelRenderer(this, 0, 35);
      this.Shape33.addBox(-14.0F, 6.0F, -14.0F, 28, 1, 28);
      this.Shape33.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape33.setTextureSize(128, 64);
      this.Shape33.mirror = true;
      this.setRotation(this.Shape33, 0.0F, -1.745329F, 0.0F);
      this.Shape34 = new ModelRenderer(this, 0, 35);
      this.Shape34.addBox(-14.0F, 6.0F, -14.0F, 28, 1, 28);
      this.Shape34.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape34.setTextureSize(128, 64);
      this.Shape34.mirror = true;
      this.setRotation(this.Shape34, 0.0F, -1.396263F, 0.0F);
      this.Shape35 = new ModelRenderer(this, 0, 35);
      this.Shape35.addBox(-14.0F, 5.0F, -14.0F, 28, 1, 28);
      this.Shape35.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape35.setTextureSize(128, 64);
      this.Shape35.mirror = true;
      this.setRotation(this.Shape35, 0.0F, -1.047198F, 0.0F);
      this.Shape36 = new ModelRenderer(this, 0, 35);
      this.Shape36.addBox(-14.0F, 4.0F, -14.0F, 28, 1, 28);
      this.Shape36.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape36.setTextureSize(128, 64);
      this.Shape36.mirror = true;
      this.setRotation(this.Shape36, 0.0F, -0.6981317F, 0.0F);
      this.Shape37 = new ModelRenderer(this, 0, 35);
      this.Shape37.addBox(-14.0F, 3.0F, -14.0F, 28, 1, 28);
      this.Shape37.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape37.setTextureSize(128, 64);
      this.Shape37.mirror = true;
      this.setRotation(this.Shape37, 0.0F, -0.3490659F, 0.0F);
      this.Shape38 = new ModelRenderer(this, 0, 35);
      this.Shape38.addBox(-14.0F, 2.0F, -14.0F, 28, 1, 28);
      this.Shape38.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape38.setTextureSize(128, 64);
      this.Shape38.mirror = true;
      this.setRotation(this.Shape38, 0.0F, 0.0F, 0.0F);
      this.Shape39 = new ModelRenderer(this, 0, 35);
      this.Shape39.addBox(-14.0F, 1.0F, -14.0F, 28, 1, 28);
      this.Shape39.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape39.setTextureSize(128, 64);
      this.Shape39.mirror = true;
      this.setRotation(this.Shape39, 0.0F, 0.3490659F, 0.0F);
      this.Shape40 = new ModelRenderer(this, 0, 35);
      this.Shape40.addBox(-14.0F, 0.0F, -14.0F, 28, 1, 28);
      this.Shape40.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape40.setTextureSize(128, 64);
      this.Shape40.mirror = true;
      this.setRotation(this.Shape40, 0.0F, 0.6981317F, 0.0F);
      this.Shape41 = new ModelRenderer(this, 0, 35);
      this.Shape41.addBox(-14.0F, -1.0F, -14.0F, 28, 1, 28);
      this.Shape41.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape41.setTextureSize(128, 64);
      this.Shape41.mirror = true;
      this.setRotation(this.Shape41, 0.0F, 1.047198F, 0.0F);
      this.Shape42 = new ModelRenderer(this, 0, 35);
      this.Shape42.addBox(-14.0F, -2.0F, -14.0F, 28, 1, 28);
      this.Shape42.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape42.setTextureSize(128, 64);
      this.Shape42.mirror = true;
      this.setRotation(this.Shape42, 0.0F, 1.396263F, 0.0F);
      this.Shape43 = new ModelRenderer(this, 0, 35);
      this.Shape43.addBox(-14.0F, -3.0F, -14.0F, 28, 1, 28);
      this.Shape43.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape43.setTextureSize(128, 64);
      this.Shape43.mirror = true;
      this.setRotation(this.Shape43, 0.0F, 1.745329F, 0.0F);
      this.Shape44 = new ModelRenderer(this, 0, 35);
      this.Shape44.addBox(-14.0F, -4.0F, -14.0F, 28, 1, 28);
      this.Shape44.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape44.setTextureSize(128, 64);
      this.Shape44.mirror = true;
      this.setRotation(this.Shape44, 0.0F, 2.094395F, 0.0F);
      this.Shape45 = new ModelRenderer(this, 0, 35);
      this.Shape45.addBox(-14.0F, -5.0F, -14.0F, 28, 1, 28);
      this.Shape45.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape45.setTextureSize(128, 64);
      this.Shape45.mirror = true;
      this.setRotation(this.Shape45, 0.0F, 2.443461F, 0.0F);
      this.Shape46 = new ModelRenderer(this, 0, 35);
      this.Shape46.addBox(-14.0F, -6.0F, -14.0F, 28, 1, 28);
      this.Shape46.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape46.setTextureSize(128, 64);
      this.Shape46.mirror = true;
      this.setRotation(this.Shape46, 0.0F, 2.792527F, 0.0F);
      this.Shape47 = new ModelRenderer(this, 0, 35);
      this.Shape47.addBox(-14.0F, -7.0F, -14.0F, 28, 1, 28);
      this.Shape47.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape47.setTextureSize(128, 64);
      this.Shape47.mirror = true;
      this.setRotation(this.Shape47, 0.0F, 3.141593F, 0.0F);
      this.Shape48 = new ModelRenderer(this, 0, 35);
      this.Shape48.addBox(-14.0F, -8.0F, -14.0F, 28, 1, 28);
      this.Shape48.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape48.setTextureSize(128, 64);
      this.Shape48.mirror = true;
      this.setRotation(this.Shape48, 0.0F, 0.0F, 0.0F);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
   }

   public void render(long l, boolean power) {
      float f5 = 0.0625F;
      this.Shape1.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      int brightness = 15728880;
      int brightMod = brightness % 65536;
      int brightDiv = brightness / 65536;
      GL11.glPushMatrix();
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)brightMod / 1.0F, (float)brightDiv / 1.0F);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape5.render(f5);
      this.Shape7.render(f5);
      if(power) {
         this.Shape12.render(f5);
      } else {
         this.Shape6.render(f5);
      }

      this.Shape8.render(f5);
      this.Shape9.render(f5);
      this.Shape10.render(f5);
      this.Shape11.render(f5);
      if(power) {
         if(l > this.tim + 1L) {
            this.tim = l;
            if(this.ren >= 28) {
               this.t = false;
            }

            if(this.ren <= 1) {
               this.t = true;
            }

            if(this.t) {
               ++this.ren;
            } else {
               --this.ren;
            }
         }

         switch(this.ren) {
         case 1:
            this.Shape20.render(f5);
            this.Shape48.render(f5);
            break;
         case 2:
            this.Shape21.render(f5);
            this.Shape47.render(f5);
            break;
         case 3:
            this.Shape22.render(f5);
            this.Shape46.render(f5);
            break;
         case 4:
            this.Shape23.render(f5);
            this.Shape45.render(f5);
            break;
         case 5:
            this.Shape24.render(f5);
            this.Shape44.render(f5);
            break;
         case 6:
            this.Shape25.render(f5);
            this.Shape43.render(f5);
            break;
         case 7:
            this.Shape26.render(f5);
            this.Shape42.render(f5);
            break;
         case 8:
            this.Shape27.render(f5);
            this.Shape41.render(f5);
            break;
         case 9:
            this.Shape28.render(f5);
            this.Shape40.render(f5);
            break;
         case 10:
            this.Shape29.render(f5);
            this.Shape39.render(f5);
            break;
         case 11:
            this.Shape30.render(f5);
            this.Shape38.render(f5);
            break;
         case 12:
            this.Shape31.render(f5);
            this.Shape37.render(f5);
            break;
         case 13:
            this.Shape32.render(f5);
            this.Shape36.render(f5);
            break;
         case 14:
            this.Shape33.render(f5);
            this.Shape35.render(f5);
            break;
         case 15:
            this.Shape34.render(f5);
            break;
         case 16:
            this.Shape33.render(f5);
            this.Shape35.render(f5);
            break;
         case 17:
            this.Shape32.render(f5);
            this.Shape36.render(f5);
            break;
         case 18:
            this.Shape31.render(f5);
            this.Shape37.render(f5);
            break;
         case 19:
            this.Shape30.render(f5);
            this.Shape38.render(f5);
            break;
         case 20:
            this.Shape29.render(f5);
            this.Shape39.render(f5);
            break;
         case 21:
            this.Shape28.render(f5);
            this.Shape40.render(f5);
            break;
         case 22:
            this.Shape27.render(f5);
            this.Shape41.render(f5);
            break;
         case 23:
            this.Shape26.render(f5);
            this.Shape42.render(f5);
            break;
         case 24:
            this.Shape25.render(f5);
            this.Shape43.render(f5);
            break;
         case 25:
            this.Shape24.render(f5);
            this.Shape44.render(f5);
            break;
         case 26:
            this.Shape23.render(f5);
            this.Shape45.render(f5);
            break;
         case 27:
            this.Shape22.render(f5);
            this.Shape46.render(f5);
            break;
         case 28:
            this.Shape21.render(f5);
            this.Shape47.render(f5);
         }
      }

      GL11.glPopMatrix();
   }
}

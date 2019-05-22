package net.minecraft.client.addon.tco.tiny.blocks.models;

import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelBlockDeadPlayer extends BaseModelBlock {

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
   ModelRenderer Shape19;
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


   public ModelBlockDeadPlayer() {
      super.textureWidth = 64;
      super.textureHeight = 64;
      (this.Shape1 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 16, 16, 2);
      this.Shape1.setRotationPoint(-8.0F, 6.0F, -1.0F);
      this.Shape1.setTextureSize(64, 64);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      (this.Shape2 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 2, 1, 4);
      this.Shape2.setRotationPoint(-1.0F, -3.0F, -2.0F);
      this.Shape2.setTextureSize(64, 64);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      (this.Shape3 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 10, 3, 2);
      this.Shape3.setRotationPoint(-5.0F, 3.0F, -1.0F);
      this.Shape3.setTextureSize(64, 64);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      (this.Shape4 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 8, 1, 2);
      this.Shape4.setRotationPoint(-4.0F, 2.0F, -1.0F);
      this.Shape4.setTextureSize(64, 64);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      (this.Shape5 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 6, 1, 2);
      this.Shape5.setRotationPoint(-3.0F, 1.0F, -1.0F);
      this.Shape5.setTextureSize(64, 64);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      (this.Shape6 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 1, 2);
      this.Shape6.setRotationPoint(-2.0F, 0.0F, -1.0F);
      this.Shape6.setTextureSize(64, 64);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      (this.Shape7 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 2, 6, 4);
      this.Shape7.setRotationPoint(7.0F, 5.0F, -2.0F);
      this.Shape7.setTextureSize(64, 64);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      (this.Shape8 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 2, 5, 4);
      this.Shape8.setRotationPoint(5.0F, 2.0F, -2.0F);
      this.Shape8.setTextureSize(64, 64);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
      (this.Shape9 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 2, 2, 4);
      this.Shape9.setRotationPoint(4.0F, 1.0F, -2.0F);
      this.Shape9.setTextureSize(64, 64);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
      (this.Shape10 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 2, 2, 4);
      this.Shape10.setRotationPoint(3.0F, 0.0F, -2.0F);
      this.Shape10.setTextureSize(64, 64);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
      (this.Shape11 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 2, 2, 4);
      this.Shape11.setRotationPoint(2.0F, -1.0F, -2.0F);
      this.Shape11.setTextureSize(64, 64);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
      (this.Shape12 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 4, 2, 4);
      this.Shape12.setRotationPoint(-2.0F, -2.0F, -2.0F);
      this.Shape12.setTextureSize(64, 64);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      (this.Shape13 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 18, 3, 6);
      this.Shape13.setRotationPoint(-9.0F, 21.0F, -3.0F);
      this.Shape13.setTextureSize(64, 64);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
      (this.Shape14 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 2, 2, 4);
      this.Shape14.setRotationPoint(-4.0F, -1.0F, -2.0F);
      this.Shape14.setTextureSize(64, 64);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
      (this.Shape15 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 2, 2, 4);
      this.Shape15.setRotationPoint(-5.0F, 0.0F, -2.0F);
      this.Shape15.setTextureSize(64, 64);
      this.Shape15.mirror = true;
      this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
      (this.Shape16 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 2, 2, 4);
      this.Shape16.setRotationPoint(-6.0F, 1.0F, -2.0F);
      this.Shape16.setTextureSize(64, 64);
      this.Shape16.mirror = true;
      this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0F);
      (this.Shape17 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 2, 5, 4);
      this.Shape17.setRotationPoint(-7.0F, 2.0F, -2.0F);
      this.Shape17.setTextureSize(64, 64);
      this.Shape17.mirror = true;
      this.setRotation(this.Shape17, 0.0F, 0.0F, 0.0F);
      (this.Shape18 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 2, 6, 4);
      this.Shape18.setRotationPoint(-9.0F, 5.0F, -2.0F);
      this.Shape18.setTextureSize(64, 64);
      this.Shape18.mirror = true;
      this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
      (this.Shape19 = new ModelRenderer(this, 1, 21)).addBox(0.0F, 0.0F, 0.0F, 7, 1, 4);
      this.Shape19.setRotationPoint(-3.5F, 5.0F, -2.0F);
      this.Shape19.setTextureSize(64, 64);
      this.Shape19.mirror = true;
      this.setRotation(this.Shape19, 0.0F, 0.0F, 0.0F);
      (this.Shape20 = new ModelRenderer(this, 0, 21)).addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape20.setRotationPoint(-0.5F, 6.0F, -2.0F);
      this.Shape20.setTextureSize(64, 64);
      this.Shape20.mirror = true;
      this.setRotation(this.Shape20, 0.0F, 0.0F, 0.0F);
      (this.Shape21 = new ModelRenderer(this, 0, 21)).addBox(0.0F, 0.0F, 0.0F, 5, 1, 4);
      this.Shape21.setRotationPoint(-2.5F, 7.0F, -2.0F);
      this.Shape21.setTextureSize(64, 64);
      this.Shape21.mirror = true;
      this.setRotation(this.Shape21, 0.0F, 0.0F, 0.0F);
      (this.Shape22 = new ModelRenderer(this, 1, 21)).addBox(0.0F, 0.0F, 0.0F, 5, 1, 4);
      this.Shape22.setRotationPoint(-2.5F, 3.0F, -2.0F);
      this.Shape22.setTextureSize(64, 64);
      this.Shape22.mirror = true;
      this.setRotation(this.Shape22, 0.0F, 0.0F, 0.0F);
      (this.Shape23 = new ModelRenderer(this, 0, 21)).addBox(0.0F, 0.0F, 0.0F, 3, 1, 4);
      this.Shape23.setRotationPoint(-1.5F, 9.0F, -2.0F);
      this.Shape23.setTextureSize(64, 64);
      this.Shape23.mirror = true;
      this.setRotation(this.Shape23, 0.0F, 0.0F, 0.0F);
      (this.Shape24 = new ModelRenderer(this, 0, 22)).addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.Shape24.setRotationPoint(-2.5F, 8.0F, -1.5F);
      this.Shape24.setTextureSize(64, 64);
      this.Shape24.mirror = true;
      this.setRotation(this.Shape24, 0.0F, 0.0F, 0.0F);
      (this.Shape25 = new ModelRenderer(this, 0, 22)).addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.Shape25.setRotationPoint(1.5F, 8.0F, -1.5F);
      this.Shape25.setTextureSize(64, 64);
      this.Shape25.mirror = true;
      this.setRotation(this.Shape25, 0.0F, 0.0F, 0.0F);
      (this.Shape26 = new ModelRenderer(this, 1, 21)).addBox(0.0F, 0.0F, 0.0F, 3, 1, 3);
      this.Shape26.setRotationPoint(-1.5F, 2.5F, -1.5F);
      this.Shape26.setTextureSize(64, 64);
      this.Shape26.mirror = true;
      this.setRotation(this.Shape26, 0.0F, 0.0F, 0.0F);
      (this.Shape27 = new ModelRenderer(this, 1, 21)).addBox(0.0F, 0.0F, 0.0F, 6, 1, 4);
      this.Shape27.setRotationPoint(-3.0F, 4.0F, -2.0F);
      this.Shape27.setTextureSize(64, 64);
      this.Shape27.mirror = true;
      this.setRotation(this.Shape27, 0.0F, 0.0F, 0.0F);
      (this.Shape28 = new ModelRenderer(this, 0, 22)).addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.Shape28.setRotationPoint(2.0F, 6.0F, -1.5F);
      this.Shape28.setTextureSize(64, 64);
      this.Shape28.mirror = true;
      this.setRotation(this.Shape28, 0.0F, 0.0F, 0.0F);
      (this.Shape29 = new ModelRenderer(this, 0, 22)).addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.Shape29.setRotationPoint(-3.0F, 6.0F, -1.5F);
      this.Shape29.setTextureSize(64, 64);
      this.Shape29.mirror = true;
      this.setRotation(this.Shape29, 0.0F, 0.0F, 0.0F);
      (this.Shape30 = new ModelRenderer(this, 54, 0)).addBox(0.0F, 0.0F, 0.0F, 1, 10, 3);
      this.Shape30.setRotationPoint(3.0F, 11.0F, -1.4F);
      this.Shape30.setTextureSize(64, 64);
      this.Shape30.mirror = true;
      this.setRotation(this.Shape30, 0.0F, 0.0F, 0.9599311F);
      (this.Shape31 = new ModelRenderer(this, 52, 0)).addBox(0.0F, 0.0F, 0.0F, 1, 10, 3);
      this.Shape31.setRotationPoint(-4.0F, 11.6F, -1.6F);
      this.Shape31.setTextureSize(64, 64);
      this.Shape31.mirror = true;
      this.setRotation(this.Shape31, 0.0F, 0.0F, -0.9599311F);
      (this.Shape32 = new ModelRenderer(this, 0, 20)).addBox(0.0F, 0.0F, 0.0F, 2, 2, 4);
      this.Shape32.setRotationPoint(3.0F, 10.0F, -2.0F);
      this.Shape32.setTextureSize(64, 64);
      this.Shape32.mirror = true;
      this.setRotation(this.Shape32, 0.0F, 0.0F, 0.0F);
      (this.Shape33 = new ModelRenderer(this, 0, 20)).addBox(0.0F, 0.0F, 0.0F, 2, 2, 4);
      this.Shape33.setRotationPoint(-5.0F, 10.0F, -2.0F);
      this.Shape33.setTextureSize(64, 64);
      this.Shape33.mirror = true;
      this.setRotation(this.Shape33, 0.0F, 0.0F, 0.0F);
      (this.Shape34 = new ModelRenderer(this, 0, 20)).addBox(0.0F, 0.0F, 0.0F, 2, 2, 4);
      this.Shape34.setRotationPoint(-6.0F, 16.0F, -2.0F);
      this.Shape34.setTextureSize(64, 64);
      this.Shape34.mirror = true;
      this.setRotation(this.Shape34, 0.0F, 0.0F, 0.0F);
      (this.Shape35 = new ModelRenderer(this, 0, 20)).addBox(0.0F, 0.0F, 0.0F, 2, 2, 4);
      this.Shape35.setRotationPoint(4.0F, 16.0F, -2.0F);
      this.Shape35.setTextureSize(64, 64);
      this.Shape35.mirror = true;
      this.setRotation(this.Shape35, 0.0F, 0.0F, 0.0F);
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
      this.Shape15.render(f5);
      this.Shape16.render(f5);
      this.Shape17.render(f5);
      this.Shape18.render(f5);
      this.Shape19.render(f5);
      this.Shape20.render(f5);
      this.Shape21.render(f5);
      this.Shape22.render(f5);
      this.Shape23.render(f5);
      this.Shape24.render(f5);
      this.Shape25.render(f5);
      this.Shape26.render(f5);
      this.Shape27.render(f5);
      this.Shape27.render(f5);
      this.Shape28.render(f5);
      this.Shape29.render(f5);
      this.Shape30.render(f5);
      this.Shape31.render(f5);
      this.Shape32.render(f5);
      this.Shape33.render(f5);
      this.Shape34.render(f5);
      this.Shape35.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void render() {
      float f5 = 0.0625F;
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
      this.Shape15.render(0.0625F);
      this.Shape16.render(0.0625F);
      this.Shape17.render(0.0625F);
      this.Shape18.render(0.0625F);
      this.Shape19.render(0.0625F);
      this.Shape20.render(0.0625F);
      this.Shape21.render(0.0625F);
      this.Shape22.render(0.0625F);
      this.Shape23.render(0.0625F);
      this.Shape24.render(0.0625F);
      this.Shape25.render(0.0625F);
      this.Shape26.render(0.0625F);
      this.Shape27.render(0.0625F);
      this.Shape27.render(0.0625F);
      this.Shape28.render(0.0625F);
      this.Shape29.render(0.0625F);
      int brightness = 15728880;
      boolean brightMod = true;
      boolean brightDiv = true;
      GL11.glEnable('\u803a');
      GL11.glPushMatrix();
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
      this.Shape30.render(0.0625F);
      this.Shape31.render(0.0625F);
      GL11.glPopMatrix();
      GL11.glDisable('\u803a');
      this.Shape32.render(0.0625F);
      this.Shape33.render(0.0625F);
      this.Shape34.render(0.0625F);
      this.Shape35.render(0.0625F);
   }
}
package net.minecraft.client.addon.tchestplate.armors.models;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.src.ModLoader;
import org.lwjgl.opengl.GL11;

public class ModelA1 extends ModelBaseLavaArmor {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape18;
   ModelRenderer Shape22;
   ModelRenderer Shape23;
   ModelRenderer Shape24;
   ModelRenderer Shape25;
   ModelRenderer Shape26;
   ModelRenderer Shape27;
   ModelRenderer Shape28;
   ModelRenderer Shape29;
   ModelRenderer Shape30;
   ModelRenderer Shape32;
   ModelRenderer Shape33;
   ModelRenderer Shape34;
   ModelRenderer Shape35;
   ModelRenderer Shape36;
   ModelRenderer Shape37;
   ModelRenderer Shape38;
   ModelRenderer Shape39;
   ModelRenderer Shape40;
   ModelRenderer Shape42;
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

   public ModelA1() {
      super.textureWidth = 64;
      super.textureHeight = 64;
      this.Shape1 = new ModelRenderer(this, 40, 50);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 8, 12, 1);
      this.Shape1.setRotationPoint(-4.0F, 0.0F, 1.2F);
      this.Shape1.setTextureSize(64, 64);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 40, 50);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 8, 12, 1);
      this.Shape2.setRotationPoint(-4.0F, 0.0F, -2.2F);
      this.Shape2.setTextureSize(64, 64);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 40, 48);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 12, 4);
      this.Shape3.setRotationPoint(3.2F, 0.0F, -2.0F);
      this.Shape3.setTextureSize(64, 64);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 40, 48);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 1, 12, 4);
      this.Shape4.setRotationPoint(-4.2F, 0.0F, -2.0F);
      this.Shape4.setTextureSize(64, 64);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 40, 50);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 8, 1, 4);
      this.Shape5.setRotationPoint(-4.0F, -0.2F, -2.0F);
      this.Shape5.setTextureSize(64, 64);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 40, 59);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 8, 1, 4);
      this.Shape6.setRotationPoint(-4.0F, 11.2F, -2.0F);
      this.Shape6.setTextureSize(64, 64);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape18 = new ModelRenderer(this, 0, 0);
      this.Shape18.addBox(0.0F, 0.0F, 0.0F, 8, 1, 5);
      this.Shape18.setRotationPoint(-4.0F, 11.0F, -2.5F);
      this.Shape18.setTextureSize(64, 64);
      this.Shape18.mirror = true;
      this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
      this.Shape22 = new ModelRenderer(this, 0, 0);
      this.Shape22.addBox(0.0F, 0.0F, 0.0F, 6, 2, 1);
      this.Shape22.setRotationPoint(-3.0F, 0.0F, 1.8F);
      this.Shape22.setTextureSize(64, 64);
      this.Shape22.mirror = true;
      this.setRotation(this.Shape22, 0.0F, 0.0F, 0.0F);
      this.Shape23 = new ModelRenderer(this, 0, 0);
      this.Shape23.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
      this.Shape23.setRotationPoint(-3.0F, -2.6F, -2.0F);
      this.Shape23.setTextureSize(64, 64);
      this.Shape23.mirror = true;
      this.setRotation(this.Shape23, 0.0F, 0.0F, 0.0F);
      this.Shape24 = new ModelRenderer(this, 40, 50);
      this.Shape24.addBox(0.0F, 0.0F, 0.0F, 4, 10, 1);
      this.Shape24.setRotationPoint(-3.0F, -2.0F, -2.2F);
      this.Shape24.setTextureSize(64, 64);
      this.Shape24.mirror = true;
      this.setRotation(this.Shape24, 0.0F, 0.0F, 0.0F);
      this.Shape25 = new ModelRenderer(this, 40, 50);
      this.Shape25.addBox(0.0F, 0.0F, 0.0F, 1, 10, 4);
      this.Shape25.setRotationPoint(-3.2F, -2.0F, -2.0F);
      this.Shape25.setTextureSize(64, 64);
      this.Shape25.mirror = true;
      this.setRotation(this.Shape25, 0.0F, 0.0F, 0.0F);
      this.Shape26 = new ModelRenderer(this, 40, 50);
      this.Shape26.addBox(0.0F, 0.0F, 0.0F, 4, 10, 1);
      this.Shape26.setRotationPoint(-3.0F, -2.0F, 1.2F);
      this.Shape26.setTextureSize(64, 64);
      this.Shape26.mirror = true;
      this.setRotation(this.Shape26, 0.0F, 0.0F, 0.0F);
      this.Shape27 = new ModelRenderer(this, 40, 50);
      this.Shape27.addBox(0.0F, 0.0F, 0.0F, 1, 10, 4);
      this.Shape27.setRotationPoint(0.2F, -2.0F, -2.0F);
      this.Shape27.setTextureSize(64, 64);
      this.Shape27.mirror = true;
      this.setRotation(this.Shape27, 0.0F, 0.0F, 0.0F);
      this.Shape28 = new ModelRenderer(this, 0, 0);
      this.Shape28.addBox(0.0F, 0.0F, 0.0F, 5, 2, 5);
      this.Shape28.setRotationPoint(-3.5F, -2.1F, -2.5F);
      this.Shape28.setTextureSize(64, 64);
      this.Shape28.mirror = true;
      this.setRotation(this.Shape28, 0.0F, 0.0F, 0.0F);
      this.Shape29 = new ModelRenderer(this, 0, 11);
      this.Shape29.addBox(1.0F, -2.0F, -1.0F, 1, 3, 3);
      this.Shape29.setRotationPoint(-4.7F, 1.0F, 0.0F);
      this.Shape29.setTextureSize(64, 64);
      this.Shape29.mirror = true;
      this.setRotation(this.Shape29, 0.7853982F, 0.0F, 0.0F);
      this.Shape30 = new ModelRenderer(this, 0, 0);
      this.Shape30.addBox(0.0F, 0.0F, 0.0F, 1, 9, 2);
      this.Shape30.setRotationPoint(-3.5F, -0.5F, -1.0F);
      this.Shape30.setTextureSize(64, 64);
      this.Shape30.mirror = true;
      this.setRotation(this.Shape30, 0.0F, 0.0F, 0.0F);
      this.Shape32 = new ModelRenderer(this, 0, 0);
      this.Shape32.addBox(0.0F, 0.0F, 0.0F, 2, 9, 1);
      this.Shape32.setRotationPoint(-2.0F, -0.5F, 1.7F);
      this.Shape32.setTextureSize(64, 64);
      this.Shape32.mirror = true;
      this.setRotation(this.Shape32, 0.0F, 0.0F, 0.0F);
      this.Shape33 = new ModelRenderer(this, 0, 0);
      this.Shape33.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
      this.Shape33.setRotationPoint(-1.0F, -2.6F, -2.0F);
      this.Shape33.setTextureSize(64, 64);
      this.Shape33.mirror = true;
      this.setRotation(this.Shape33, 0.0F, 0.0F, 0.0F);
      this.Shape34 = new ModelRenderer(this, 0, 0);
      this.Shape34.addBox(0.0F, 0.0F, 0.0F, 5, 2, 5);
      this.Shape34.setRotationPoint(-1.5F, -2.1F, -2.5F);
      this.Shape34.setTextureSize(64, 64);
      this.Shape34.mirror = true;
      this.setRotation(this.Shape34, 0.0F, 0.0F, 0.0F);
      this.Shape35 = new ModelRenderer(this, 40, 50);
      this.Shape35.addBox(0.0F, 0.0F, 0.0F, 4, 10, 1);
      this.Shape35.setRotationPoint(-1.0F, -2.0F, -2.2F);
      this.Shape35.setTextureSize(64, 64);
      this.Shape35.mirror = true;
      this.setRotation(this.Shape35, 0.0F, 0.0F, 0.0F);
      this.Shape36 = new ModelRenderer(this, 40, 50);
      this.Shape36.addBox(0.0F, 0.0F, 0.0F, 4, 10, 1);
      this.Shape36.setRotationPoint(-1.0F, -2.0F, 1.2F);
      this.Shape36.setTextureSize(64, 64);
      this.Shape36.mirror = true;
      this.setRotation(this.Shape36, 0.0F, 0.0F, 0.0F);
      this.Shape37 = new ModelRenderer(this, 40, 50);
      this.Shape37.addBox(0.0F, 0.0F, 0.0F, 1, 10, 4);
      this.Shape37.setRotationPoint(2.2F, -2.0F, -2.0F);
      this.Shape37.setTextureSize(64, 64);
      this.Shape37.mirror = true;
      this.setRotation(this.Shape37, 0.0F, 0.0F, 0.0F);
      this.Shape38 = new ModelRenderer(this, 40, 50);
      this.Shape38.addBox(0.0F, 0.0F, 0.0F, 1, 10, 4);
      this.Shape38.setRotationPoint(-1.2F, -2.0F, -2.0F);
      this.Shape38.setTextureSize(64, 64);
      this.Shape38.mirror = true;
      this.setRotation(this.Shape38, 0.0F, 0.0F, 0.0F);
      this.Shape39 = new ModelRenderer(this, 0, 11);
      this.Shape39.addBox(-1.0F, -2.0F, -1.0F, 1, 3, 3);
      this.Shape39.setRotationPoint(3.7F, 1.0F, 0.0F);
      this.Shape39.setTextureSize(64, 64);
      this.Shape39.mirror = true;
      this.setRotation(this.Shape39, 0.7853982F, 0.0F, 0.0F);
      this.Shape40 = new ModelRenderer(this, 0, 0);
      this.Shape40.addBox(0.0F, 0.0F, 0.0F, 1, 9, 2);
      this.Shape40.setRotationPoint(2.5F, -0.5F, -1.0F);
      this.Shape40.setTextureSize(64, 64);
      this.Shape40.mirror = true;
      this.setRotation(this.Shape40, 0.0F, 0.0F, 0.0F);
      this.Shape42 = new ModelRenderer(this, 0, 0);
      this.Shape42.addBox(0.0F, 0.0F, 0.0F, 2, 9, 1);
      this.Shape42.setRotationPoint(0.0F, -0.5F, 1.7F);
      this.Shape42.setTextureSize(64, 64);
      this.Shape42.mirror = true;
      this.setRotation(this.Shape42, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 0, 0);
      this.Shape7.addBox(-1.0F, -3.0F, 0.0F, 2, 3, 1);
      this.Shape7.setRotationPoint(0.0F, 11.0F, -2.0F);
      this.Shape7.setTextureSize(64, 64);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.3316126F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 40, 49);
      this.Shape8.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1);
      this.Shape8.setRotationPoint(0.5F, 2.0F, -3.5F);
      this.Shape8.setTextureSize(64, 64);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
      this.Shape9 = new ModelRenderer(this, 40, 49);
      this.Shape9.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1);
      this.Shape9.setRotationPoint(-3.5F, 2.0F, -3.5F);
      this.Shape9.setTextureSize(64, 64);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 40, 55);
      this.Shape10.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1);
      this.Shape10.setRotationPoint(-1.5F, 6.5F, -3.0F);
      this.Shape10.setTextureSize(64, 64);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 25, 51);
      this.Shape11.addBox(0.0F, 0.0F, 0.0F, 5, 2, 1);
      this.Shape11.setRotationPoint(-2.5F, 4.5F, -3.0F);
      this.Shape11.setTextureSize(64, 64);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 40, 49);
      this.Shape12.addBox(0.0F, 0.0F, 0.0F, 8, 3, 1);
      this.Shape12.setRotationPoint(-4.0F, 1.5F, -3.0F);
      this.Shape12.setTextureSize(64, 64);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      this.Shape13 = new ModelRenderer(this, 40, 48);
      this.Shape13.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
      this.Shape13.setRotationPoint(-4.0F, 4.0F, -2.5F);
      this.Shape13.setTextureSize(64, 64);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, 0.0F, 0.0F, -0.5061455F);
      this.Shape14 = new ModelRenderer(this, 40, 48);
      this.Shape14.addBox(-1.0F, 0.0F, 0.0F, 1, 5, 1);
      this.Shape14.setRotationPoint(4.0F, 4.0F, -2.5F);
      this.Shape14.setTextureSize(64, 64);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, 0.0F, 0.0F, 0.5061455F);
      this.Shape15 = new ModelRenderer(this, 0, 0);
      this.Shape15.addBox(0.0F, 0.0F, 0.0F, 2, 9, 1);
      this.Shape15.setRotationPoint(-3.0F, -1.0F, -2.5F);
      this.Shape15.setTextureSize(64, 64);
      this.Shape15.mirror = true;
      this.setRotation(this.Shape15, 0.0349066F, 0.0F, -0.122173F);
      this.Shape16 = new ModelRenderer(this, 0, 0);
      this.Shape16.addBox(-2.0F, 0.0F, 0.0F, 2, 9, 1);
      this.Shape16.setRotationPoint(3.0F, -1.0F, -2.5F);
      this.Shape16.setTextureSize(64, 64);
      this.Shape16.mirror = true;
      this.setRotation(this.Shape16, 0.0349066F, 0.0F, 0.122173F);
      super.bipedBody.cubeList.clear();
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
      super.bipedBody.addChild(this.Shape18);
      super.bipedBody.addChild(this.Shape22);
      super.bipedRightArm.cubeList.clear();
      super.bipedRightArm.addChild(this.Shape23);
      super.bipedRightArm.addChild(this.Shape24);
      super.bipedRightArm.addChild(this.Shape25);
      super.bipedRightArm.addChild(this.Shape26);
      super.bipedRightArm.addChild(this.Shape27);
      super.bipedRightArm.addChild(this.Shape28);
      super.bipedRightArm.addChild(this.Shape29);
      super.bipedRightArm.addChild(this.Shape30);
      super.bipedRightArm.addChild(this.Shape15);
      super.bipedRightArm.addChild(this.Shape32);
      super.bipedLeftArm.cubeList.clear();
      super.bipedLeftArm.addChild(this.Shape33);
      super.bipedLeftArm.addChild(this.Shape34);
      super.bipedLeftArm.addChild(this.Shape35);
      super.bipedLeftArm.addChild(this.Shape36);
      super.bipedLeftArm.addChild(this.Shape37);
      super.bipedLeftArm.addChild(this.Shape38);
      super.bipedLeftArm.addChild(this.Shape39);
      super.bipedLeftArm.addChild(this.Shape40);
      super.bipedLeftArm.addChild(this.Shape16);
      super.bipedLeftArm.addChild(this.Shape42);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void obt() {
      GL11.glPushMatrix();
      GL11.glTranslatef(0.08F, -1.248F, 0.02F);
      float scale = 0.15F;
      GL11.glScalef(0.15F, 0.15F, 0.15F);
      boolean col = false;
      int age = Minecraft.getMinecraft().renderViewEntity.ticksExisted;
      GL11.glPushMatrix();
      float f1 = 0.0F;
      if(!ModLoader.getMinecraftInstance().gameSettings.fancyGraphics) {
         ;
      }

      boolean q = true;
      Tessellator var22 = Tessellator.instance;
      f1 = (float)age / 500.0F;
      float f2 = 0.9F;
      float f3 = 0.0F;
      Random random = new Random(245L);
      GL11.glDisable(3553);
      GL11.glShadeModel(7425);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 1);
      GL11.glDisable(3008);
      GL11.glEnable(2884);
      GL11.glDepthMask(false);
      GL11.glPushMatrix();
      boolean var23 = true;

      for(int i = 0; i < 100; ++i) {
         GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(random.nextFloat() * 360.0F + f1 * 360.0F, 0.0F, 0.0F, 1.0F);
         var22.startDrawing(6);
         float fa = random.nextFloat() * 20.0F + 5.0F + 0.0F;
         float f4 = random.nextFloat() * 2.0F + 1.0F + 0.0F;
         fa /= 10.0F / ((float)Math.min(age, 10) / 10.0F);
         f4 /= 10.0F / ((float)Math.min(age, 10) / 10.0F);
         var22.setColorRGBA_I(16196215, 255);
         var22.addVertex(0.6D, 0.0D, 0.0D);
         var22.setColorRGBA_I(16198159, 0);
         var22.addVertex(-0.866D * (double)f4, (double)fa, (double)(-0.5F * f4));
         var22.addVertex(0.866D * (double)f4, (double)fa, (double)(-0.5F * f4));
         var22.addVertex(0.0D, (double)fa, (double)(1.0F * f4));
         var22.addVertex(-0.866D * (double)f4, (double)fa, (double)(-0.5F * f4));
         var22.draw();
      }

      GL11.glPopMatrix();
      GL11.glDepthMask(true);
      GL11.glDisable(2884);
      GL11.glDisable(3042);
      GL11.glShadeModel(7424);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(3553);
      GL11.glEnable(3008);
      GL11.glBlendFunc(770, 771);
      GL11.glPopMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glPopMatrix();
   }
}

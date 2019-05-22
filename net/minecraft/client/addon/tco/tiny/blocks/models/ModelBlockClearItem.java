package net.minecraft.client.addon.tco.tiny.blocks.models;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.src.ModLoader;
import org.lwjgl.opengl.GL11;

public class ModelBlockClearItem extends BaseModelBlock {

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
   float o = 0.0F;


   public ModelBlockClearItem() {
      super.textureWidth = 64;
      super.textureHeight = 96;
      this.Shape1 = new ModelRenderer(this, 0, 46);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 16, 2, 16);
      this.Shape1.setRotationPoint(-8.0F, 22.0F, -8.0F);
      this.Shape1.setTextureSize(64, 96);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 0);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 14, 1, 14);
      this.Shape2.setRotationPoint(-7.0F, 19.0F, -7.0F);
      this.Shape2.setTextureSize(64, 96);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 0);
      this.Shape3.addBox(-3.0F, 0.0F, -3.0F, 6, 2, 6);
      this.Shape3.setRotationPoint(0.0F, 20.0F, 0.0F);
      this.Shape3.setTextureSize(64, 96);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.7853982F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 22);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape4.setRotationPoint(5.0F, 18.0F, -2.0F);
      this.Shape4.setTextureSize(64, 96);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 22);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape5.setRotationPoint(-6.0F, 18.0F, -2.0F);
      this.Shape5.setTextureSize(64, 96);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 0, 22);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape6.setRotationPoint(-2.0F, 18.0F, -6.0F);
      this.Shape6.setTextureSize(64, 96);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 0, 22);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Shape7.setRotationPoint(-2.0F, 18.0F, 5.0F);
      this.Shape7.setTextureSize(64, 96);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 0, 22);
      this.Shape8.addBox(-3.0F, 0.0F, 0.0F, 6, 1, 1);
      this.Shape8.setRotationPoint(4.0F, 18.0F, -4.0F);
      this.Shape8.setTextureSize(64, 96);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, -0.7853982F, 0.0F);
      this.Shape9 = new ModelRenderer(this, 0, 22);
      this.Shape9.addBox(-3.0F, 0.0F, 0.0F, 6, 1, 1);
      this.Shape9.setRotationPoint(-4.0F, 18.0F, -4.0F);
      this.Shape9.setTextureSize(64, 96);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, 0.7853982F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 0, 22);
      this.Shape10.addBox(-3.0F, 0.0F, -1.0F, 6, 1, 1);
      this.Shape10.setRotationPoint(4.0F, 18.0F, 4.0F);
      this.Shape10.setTextureSize(64, 96);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.7853982F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 0, 22);
      this.Shape11.addBox(-3.0F, 0.0F, -1.0F, 6, 1, 1);
      this.Shape11.setRotationPoint(-4.0F, 18.0F, 4.0F);
      this.Shape11.setTextureSize(64, 96);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, -0.7853982F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 0, 22);
      this.Shape12.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
      this.Shape12.setRotationPoint(5.0F, 14.0F, -6.0F);
      this.Shape12.setTextureSize(64, 96);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      this.Shape13 = new ModelRenderer(this, 0, 22);
      this.Shape13.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
      this.Shape13.setRotationPoint(-6.0F, 14.0F, -6.0F);
      this.Shape13.setTextureSize(64, 96);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
      this.Shape14 = new ModelRenderer(this, 0, 22);
      this.Shape14.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
      this.Shape14.setRotationPoint(5.0F, 14.0F, 5.0F);
      this.Shape14.setTextureSize(64, 96);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
      this.Shape15 = new ModelRenderer(this, 0, 22);
      this.Shape15.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
      this.Shape15.setRotationPoint(-6.0F, 14.0F, 5.0F);
      this.Shape15.setTextureSize(64, 96);
      this.Shape15.mirror = true;
      this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
      this.Shape16 = new ModelRenderer(this, 28, 22);
      this.Shape16.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
      this.Shape16.setRotationPoint(4.5F, 18.5F, -6.5F);
      this.Shape16.setTextureSize(64, 96);
      this.Shape16.mirror = true;
      this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0F);
      this.Shape17 = new ModelRenderer(this, 28, 22);
      this.Shape17.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
      this.Shape17.setRotationPoint(-6.5F, 18.5F, -6.5F);
      this.Shape17.setTextureSize(64, 96);
      this.Shape17.mirror = true;
      this.setRotation(this.Shape17, 0.0F, 0.0F, 0.0F);
      this.Shape18 = new ModelRenderer(this, 28, 22);
      this.Shape18.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
      this.Shape18.setRotationPoint(-6.5F, 18.5F, 4.5F);
      this.Shape18.setTextureSize(64, 96);
      this.Shape18.mirror = true;
      this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
      this.Shape19 = new ModelRenderer(this, 28, 22);
      this.Shape19.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
      this.Shape19.setRotationPoint(4.5F, 18.5F, 4.5F);
      this.Shape19.setTextureSize(64, 96);
      this.Shape19.mirror = true;
      this.setRotation(this.Shape19, 0.0F, 0.0F, 0.0F);
      this.Shape20 = new ModelRenderer(this, 0, 64);
      this.Shape20.addBox(0.0F, 0.0F, 0.0F, 13, 1, 13);
      this.Shape20.setRotationPoint(-6.5F, 14.5F, -6.5F);
      this.Shape20.setTextureSize(64, 96);
      this.Shape20.mirror = true;
      this.setRotation(this.Shape20, 0.0F, 0.0F, 0.0F);
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
      this.reb();
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void render() {
      float f5 = 0.0625F;
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
      this.reb();
   }

   public void reb() {
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, 1.16F, 0.0F);
      float scale = 0.15F;
      GL11.glScalef(0.3F, 0.05F, 0.3F);
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
         float fa = random.nextFloat() * 2.0F + 1.0F + 0.0F;
         float f4 = random.nextFloat() * 2.0F + 10.0F + 0.0F;
         fa /= 10.0F / ((float)Math.min(age, 15) / 10.0F);
         f4 /= 10.0F / ((float)Math.min(age, 15) / 10.0F);
         var22.setColorRGBA_I(155195715, 255);
         var22.addVertex(0.6D, 0.0D, 0.0D);
         var22.setColorRGBA_I(10, 0);
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

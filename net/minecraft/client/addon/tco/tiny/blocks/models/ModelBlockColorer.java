package net.minecraft.client.addon.tco.tiny.blocks.models;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.src.ModLoader;
import org.lwjgl.opengl.GL11;

public class ModelBlockColorer extends BaseModelBlock {

   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape10;
   ModelRenderer Shape12;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;
   ModelRenderer Shape9;
   float o = 0.0F;


   public ModelBlockColorer() {
      super.textureWidth = 64;
      super.textureHeight = 64;
      this.Shape1 = new ModelRenderer(this, 0, 46);
      this.Shape1.addBox(-8.0F, 22.0F, -8.0F, 16, 2, 16);
      this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape1.setTextureSize(64, 64);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 14, 53);
      this.Shape2.addBox(-5.0F, 19.0F, -3.0F, 10, 3, 6);
      this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape2.setTextureSize(64, 64);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 14, 52);
      this.Shape3.addBox(-6.0F, 17.0F, -4.0F, 12, 2, 8);
      this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape3.setTextureSize(64, 64);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 35);
      this.Shape4.addBox(-8.0F, 16.0F, -5.0F, 16, 1, 10);
      this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape4.setTextureSize(64, 64);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 15, 49);
      this.Shape10.addBox(8.0F, 16.0F, -1.5F, 1, 1, 3);
      this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape10.setTextureSize(64, 64);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 15, 49);
      this.Shape12.addBox(-9.0F, 16.0F, -1.5F, 1, 1, 3);
      this.Shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape12.setTextureSize(64, 64);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 23, 0);
      this.Shape5.addBox(0.0F, -10.0F, -1.0F, 1, 10, 2);
      this.Shape5.setRotationPoint(-9.0F, 16.0F, 0.0F);
      this.Shape5.setTextureSize(64, 64);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.2617994F);
      this.Shape6 = new ModelRenderer(this, 27, 0);
      this.Shape6.addBox(-1.0F, -10.0F, -1.0F, 1, 10, 2);
      this.Shape6.setRotationPoint(9.0F, 16.0F, 0.0F);
      this.Shape6.setTextureSize(64, 64);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, -0.2617994F);
      this.Shape7 = new ModelRenderer(this, 33, 7);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 13, 1, 2);
      this.Shape7.setRotationPoint(-6.5F, 6.3F, -1.0F);
      this.Shape7.setTextureSize(64, 64);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 0, 0);
      this.Shape8.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
      this.Shape8.setRotationPoint(-1.0F, 7.0F, -1.0F);
      this.Shape8.setTextureSize(64, 64);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
      this.Shape9 = new ModelRenderer(this, 10, 0);
      this.Shape9.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.Shape9.setRotationPoint(-0.5F, 8.5F, -0.5F);
      this.Shape9.setTextureSize(64, 64);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
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
      this.Shape12.render(f5);
      this.reb();
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void render() {
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
      this.Shape12.render(0.0625F);
      this.reb();
   }

   public void reb() {
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, 0.8F, 0.0F);
      float scale = 0.15F;
      GL11.glScalef(0.15F, 0.55F, 0.15F);
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
         float fa = random.nextFloat() * 2.0F + 2.0F + 0.0F;
         float f4 = random.nextFloat() * 2.0F + 2.0F + 0.0F;
         fa /= 10.0F / ((float)Math.min(age, 15) / 10.0F);
         f4 /= 10.0F / ((float)Math.min(age, 15) / 10.0F);
         var22.setColorRGBA_I(8196215, 255);
         var22.addVertex(0.6D, 0.0D, 0.0D);
         var22.setColorRGBA_I(8198159, 0);
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

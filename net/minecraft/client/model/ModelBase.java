package net.minecraft.client.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.TextureOffset;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.src.ModLoader;
import org.lwjgl.opengl.GL11;

public abstract class ModelBase {
   public float onGround;
   public boolean isRiding = false;
   public List boxList = new ArrayList();
   public boolean isChild = true;
   private Map modelTextureMap = new HashMap();
   public int textureWidth = 64;
   public int textureHeight = 64;

   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
   }

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
   }

   public void setLivingAnimations(EntityLiving par1EntityLiving, float par2, float par3, float par4) {
   }

   public ModelRenderer getRandomModelBox(Random par1Random) {
      return (ModelRenderer)this.boxList.get(par1Random.nextInt(this.boxList.size()));
   }

   protected void setTextureOffset(String par1Str, int par2, int par3) {
      this.modelTextureMap.put(par1Str, new TextureOffset(par2, par3));
   }

   public TextureOffset getTextureOffset(String par1Str) {
      return (TextureOffset)this.modelTextureMap.get(par1Str);
   }

   public void addLvL(int red, int green, int blue, float posX, float posY, float posZ, float scaleX, float scaleY, float scaleZ) {
      GL11.glPushMatrix();
      GL11.glTranslatef(posX, posY, posZ);
      float scale = 0.15F;
      GL11.glScalef(0.2F + scaleX, 0.5F + scaleY, 0.2F + scaleZ);
      boolean col = false;
      int age = Minecraft.getMinecraft().renderViewEntity.ticksExisted;
      GL11.glPushMatrix();
      float f1 = 0.0F;
      if(!ModLoader.getMinecraftInstance().gameSettings.fancyGraphics) {
         ;
      }

      boolean q = true;
      Tessellator var22 = Tessellator.instance;
      f1 = (float)age / 40000000000000.0F;
      float f2 = 0.9F;
      float f3 = 0.0F;
      Random random = new Random(245L);
      GL11.glDisable(3553);
      GL11.glShadeModel(7425);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 1);
      GL11.glDisable(3020);
      GL11.glEnable(2884);
      GL11.glDepthMask(false);
      GL11.glPushMatrix();
      boolean var23 = true;

      for(int i = 0; i < 140; ++i) {
         GL11.glRotatef(random.nextFloat() * 90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(random.nextFloat() * 90.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(random.nextFloat() * 90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(random.nextFloat() * 90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(random.nextFloat() * 90.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(random.nextFloat() * 90.0F + f1 * 1.0F, 0.0F, 0.0F, 1.0F);
         var22.startDrawing(6);
         float fa = random.nextFloat() * 0.1F + 3.0F + 1.0F;
         float f4 = random.nextFloat() * 0.1F + 3.0F + 1.0F;
         fa /= 8.5F / ((float)Math.min(age, 40) / 40F);
         f4 /= 8.5F / ((float)Math.min(age, 20) / 20F);
         var22.setColorRGBA_I(red * 65536 + green * 256 + blue, 125);
         var22.addVertex(0.6D, 0.0D, 0.0D);
         var22.setColorRGBA_I(red * 65536 + green * 256 + blue, 0);
         var22.addVertex(-0.866D * (double)f4, (double)fa, (double)(-0.5F * f4));
         var22.addVertex(0.866D * (double)f4, (double)fa, (double)(-0.5F * f4));
         var22.addVertex(-0.866D, (double)fa, (double)(1.0F * f4));
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

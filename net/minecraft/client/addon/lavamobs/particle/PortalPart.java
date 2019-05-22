package net.minecraft.client.addon.lavamobs.particle;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class PortalPart
  extends EntityFX
{
  public boolean shrink = false;
  float moteParticleScale;
  int moteHalfLife;
  public boolean tinkle = false;
  public int blendmode = 1;
  
  public PortalPart(World world, double d, double d1, double d2, float f, float red, float green, float blue)
  {
    super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
    if (red == 0.0F) {
      red = 1.0F;
    }
    this.particleRed = red;
    this.particleGreen = green;
    this.particleBlue = blue;
    this.particleGravity = 0.0F;
    this.motionX = (this.motionY = this.motionZ = 0.0D);
    this.particleScale *= f;
    this.moteParticleScale = this.particleScale;
    this.particleMaxAge = ((int)(36.0D / (Math.random() * 1.3D + 0.7D)));
    this.moteHalfLife = (this.particleMaxAge / 2);
    this.noClip = false;
    setSize(0.01F, 0.01F);
    EntityLiving renderentity = ModLoader.getMinecraftInstance().renderViewEntity;
    int visibleDistance = 50;
    if (!ModLoader.getMinecraftInstance().gameSettings.fancyGraphics) {
      visibleDistance = 25;
    }
    if (renderentity.getDistance(this.posX, this.posY, this.posZ) > visibleDistance) {
      this.particleMaxAge = 0;
    }
    this.prevPosX = this.posX;
    this.prevPosY = this.posY;
    this.prevPosZ = this.posZ;
  }
  
  public PortalPart(World world, double d, double d1, double d2, float f, int type)
  {
    this(world, d, d1, d2, f, 0.0F, 0.0F, 0.0F);
    switch (type)
    {
    case 0: 
      this.particleRed = (0.75F + world.rand.nextFloat() * 0.25F);
      this.particleGreen = (0.25F + world.rand.nextFloat() * 0.25F);
      this.particleBlue = (0.75F + world.rand.nextFloat() * 0.25F);
      break;
    case 1: 
      this.particleRed = (0.5F + world.rand.nextFloat() * 0.3F);
      this.particleGreen = (0.5F + world.rand.nextFloat() * 0.3F);
      this.particleBlue = 0.2F;
      break;
    case 2: 
      this.particleRed = 0.2F;
      this.particleGreen = 0.2F;
      this.particleBlue = (0.7F + world.rand.nextFloat() * 0.3F);
      break;
    case 3: 
      this.particleRed = 0.2F;
      this.particleGreen = (0.7F + world.rand.nextFloat() * 0.3F);
      this.particleBlue = 0.2F;
      break;
    case 4: 
      this.particleRed = (0.7F + world.rand.nextFloat() * 0.3F);
      this.particleGreen = 0.2F;
      this.particleBlue = 0.2F;
      break;
    case 5: 
      this.blendmode = 771;
      this.particleRed = (world.rand.nextFloat() * 0.1F);
      this.particleGreen = (world.rand.nextFloat() * 0.1F);
      this.particleBlue = (world.rand.nextFloat() * 0.1F);
      break;
    case 6: 
      this.particleRed = (0.8F + world.rand.nextFloat() * 0.2F);
      this.particleGreen = (0.8F + world.rand.nextFloat() * 0.2F);
      this.particleBlue = (0.8F + world.rand.nextFloat() * 0.2F);
    }
  }
  
  public PortalPart(World world, double d, double d1, double d2, double x, double y, double z, float f, int type)
  {
    this(world, d, d1, d2, f, type);
    if (this.particleMaxAge > 0)
    {
      double dx = x - this.posX;
      double dy = y - this.posY;
      double dz = z - this.posZ;
      this.motionX = (dx / this.particleMaxAge);
      this.motionY = (dy / this.particleMaxAge);
      this.motionZ = (dz / this.particleMaxAge);
    }
  }
  
  public void setGravity(float value)
  {
    this.particleGravity = value;
  }
  
  public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
  {
    float agescale = 2.0F;
    if (this.particleMaxAge > 0)
    {
      this.particleScale = (this.moteParticleScale * agescale);
      tessellator.draw();
      GL11.glPushMatrix();
      GL11.glDepthMask(false);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, this.blendmode);
      Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/part/portal_part.png");
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
      float f10 = 0.5F * this.particleScale;
      float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * f - EntityFX.interpPosX);
      float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * f - EntityFX.interpPosY);
      float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * f - EntityFX.interpPosZ);
      tessellator.startDrawingQuads();
      tessellator.setBrightness(240);
      tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, 0.2F);
      tessellator.addVertexWithUV(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10, 0.0D, 1.0D);
      tessellator.addVertexWithUV(f11 - f1 * f10 + f4 * f10, f12 + f2 * f10, f13 - f3 * f10 + f5 * f10, 1.0D, 1.0D);
      tessellator.addVertexWithUV(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10, 1.0D, 0.0D);
      tessellator.addVertexWithUV(f11 + f1 * f10 - f4 * f10, f12 - f2 * f10, f13 + f3 * f10 - f5 * f10, 0.0D, 0.0D);
      tessellator.draw();
      GL11.glDisable(3042);
      GL11.glDepthMask(true);
      GL11.glPopMatrix();
      Minecraft.getMinecraft().renderEngine.bindTexture("/particles.png");
      tessellator.startDrawingQuads();
    }
  }
  
  public void onUpdate()
  {
    this.prevPosX = this.posX;
    this.prevPosY = this.posY;
    this.prevPosZ = this.posZ;
    if (((this.particleAge != 0) || (!this.tinkle) || (this.worldObj.rand.nextInt(3) != 0)) || 
    
      (this.particleAge++ >= this.particleMaxAge)) {
    	setDead();
    }
    this.motionY -= 0.04D * this.particleGravity;
    this.posX += this.motionX;
    this.posY += this.motionY * 6.0D;
    this.posZ += this.motionZ;
    this.motionX *= 0.9800000190734863D;
    this.motionY *= 0.8800000190734864D;
    this.motionZ *= 0.9800000190734863D;
    if (this.onGround)
    {
      this.motionX *= 0.699999988079071D;
      this.motionZ *= 0.699999988079071D;
    }
  }
}

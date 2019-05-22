package net.minecraft.client.addon.tchestplate.items.renders;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.items.ItemPets;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Timer;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class PetographItemRenderer implements IItemRenderer
{
    private FontRenderer fontRenderer;
    private FontRenderer galFontRenderer;
    private float angleHD = 0.0F;
    private float angleVD = 0.0F;

    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return type == ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return false;
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        Entity player = (Entity)data[1];
        Minecraft mc = ModLoader.getMinecraftInstance();
        float par1 = ((Timer)ReflectionHelper.getPrivateValue(Minecraft.class, mc, 10)).renderPartialTicks;
        float pep = ((Float)ObfuscationReflectionHelper.getPrivateValue(ItemRenderer.class, mc.entityRenderer.itemRenderer, 3)).floatValue();
        float ep = ((Float)ObfuscationReflectionHelper.getPrivateValue(ItemRenderer.class, mc.entityRenderer.itemRenderer, 2)).floatValue();
        float var2 = pep + (ep - pep) * par1;
        float var3 = 0.8F;
        EntityClientPlayerMP var4 = mc.thePlayer;
        float var5 = var4.prevRotationPitch + (var4.rotationPitch - var4.prevRotationPitch) * par1;
        float var8;
        float var9;

        if (player.entityId == var4.entityId && RenderManager.instance.playerViewY != 180.0F && mc.gameSettings.thirdPersonView == 0 && !mc.renderViewEntity.isPlayerSleeping() && !mc.gameSettings.hideGUI)
        {
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glEnable('耺');
            GL11.glScalef(2.5F, 2.5F, 2.5F);
            float var24 = var4.getSwingProgress(par1);
            float var25 = MathHelper.sin(var24 * var24 * (float)Math.PI);
            var8 = MathHelper.sin(MathHelper.sqrt_float(var24) * (float)Math.PI);
            GL11.glRotatef(-var8 * 80.0F, -1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-var8 * 20.0F, 0.0F, 0.0F, -1.0F);
            GL11.glRotatef(-var25 * 20.0F, 0.0F, -1.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, -1.0F, 0.0F);
            GL11.glTranslatef(-0.56F, -(-0.52F - (1.0F - var2) * 0.6F), 0.71999997F);
            GL11.glPushMatrix();
            var9 = mc.theWorld.getLightBrightness(MathHelper.floor_double(var4.posX), MathHelper.floor_double(var4.posY), MathHelper.floor_double(var4.posZ));
            var9 = 1.0F;
            int var26 = mc.theWorld.getLightBrightnessForSkyBlocks(MathHelper.floor_double(var4.posX), MathHelper.floor_double(var4.posY), MathHelper.floor_double(var4.posZ), 0);
            int var11 = var26 % 65536;
            int var12 = var26 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var11 / 1.0F, (float)var12 / 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            var26 = Item.itemsList[item.itemID].getColorFromItemStack(item, 0);
            var24 = (float)(var26 >> 16 & 255) / 255.0F;
            var25 = (float)(var26 >> 8 & 255) / 255.0F;
            var8 = (float)(var26 & 255) / 255.0F;
            GL11.glColor4f(var9 * var24, var9 * var25, var9 * var8, 1.0F);
            var25 = MathHelper.sin(var24 * (float)Math.PI);
            var8 = MathHelper.sin(MathHelper.sqrt_float(var24) * (float)Math.PI);
            GL11.glTranslatef(-var8 * 0.4F, MathHelper.sin(MathHelper.sqrt_float(var24) * (float)Math.PI * 2.0F) * 0.2F, -var25 * 0.2F);
            var24 = 1.0F - var5 / 45.0F + 0.1F;

            if (var24 < 0.0F)
            {
                var24 = 0.0F;
            }

            if (var24 > 1.0F)
            {
                var24 = 1.0F;
            }

            var24 = -MathHelper.cos(var24 * (float)Math.PI) * 0.5F + 0.5F;
            GL11.glTranslatef(0.0F, 0.0F - (1.0F - var2) * 1.2F - var24 * 0.5F + 0.04F, -0.71999997F);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var24 * -85.0F, 0.0F, 0.0F, 1.0F);
            GL11.glEnable('耺');
            Minecraft.getMinecraft().renderEngine.bindTexture(mc.thePlayer.getTexture());

            for (var12 = 0; var12 < 2; ++var12)
            {
                int var27 = var12 * 2 - 1;
                GL11.glPushMatrix();
                GL11.glTranslatef(-0.0F, -0.6F, 1.1F * (float)var27);
                GL11.glRotatef((float)(-45 * var27), 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(59.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef((float)(-65 * var27), 0.0F, 1.0F, 0.0F);
                Render var28 = RenderManager.instance.getEntityRenderObject(mc.thePlayer);
                RenderPlayer var15 = (RenderPlayer)var28;
                float var16 = 1.0F;
                GL11.glScalef(1.0F, 1.0F, 1.0F);
                var15.renderFirstPersonArm(mc.thePlayer);
                GL11.glPopMatrix();
            }

            var25 = var4.getSwingProgress(par1);
            var8 = MathHelper.sin(var25 * var25 * (float)Math.PI);
            float var271 = MathHelper.sin(MathHelper.sqrt_float(var25) * (float)Math.PI);
            GL11.glRotatef(-var8 * 20.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-var271 * 20.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-var271 * 80.0F, 1.0F, 0.0F, 0.0F);
            float var281 = 0.38F;
            GL11.glScalef(0.38F, 0.38F, 0.38F);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(-1.0F, -1.0F, 0.0F);
            this.renderThaumometer(item, mc.thePlayer, mc.renderEngine, var24);
            GL11.glPopMatrix();
        }
        else if (item.getItem().requiresMultipleRenderPasses())
        {
            this.renderItemStack(var4, item, 0, false);

            for (int var19 = 1; var19 < item.getItem().getRenderPasses(item.getItemDamage()); ++var19)
            {
                int var20 = Item.itemsList[item.itemID].getColorFromItemStack(item, var19);
                var8 = (float)(var20 >> 16 & 255) / 255.0F;
                var9 = (float)(var20 >> 8 & 255) / 255.0F;
                float var21 = (float)(var20 & 255) / 255.0F;
                GL11.glColor4f(var8, var9, var21, 1.0F);
                this.renderItemStack(var4, item, var19, false);
            }
        }
        else
        {
            this.renderItemStack(var4, item, 0, false);
        }
    }

    public void renderThaumometer(ItemStack item, EntityPlayer player, RenderEngine re, float var20)
    {
        Minecraft mc = ModLoader.getMinecraftInstance();
        EntityClientPlayerMP var21 = mc.thePlayer;
        GL11.glPushMatrix();
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        this.renderItemStack(var21, item, 0, false);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.1F, 0.1F, 0.0F);
        GL11.glScalef(0.8F, 0.8F, 0.8F);
        GL11.glTranslatef(0.5F, 0.5F, 0.0F);
        float closestX = Float.MAX_VALUE;
        float closestY = Float.MAX_VALUE;
        float closestZ = Float.MAX_VALUE;
        double closestDistance = Double.MAX_VALUE;
        boolean foundSomething = false;

        if (player.dimension == 0)
        {
            ExtendedPlayer bob211111 = ExtendedPlayer.get(player);
            ItemStack cX211111 = bob211111.inventoryExt.inventory[2];

            if (cX211111 != null && cX211111.getItem() instanceof ItemPets)
            {
                NBTTagCompound nbt = Utils.getOrCreateNbtData(cX211111);

                if (nbt.hasKey("ptime"))
                {
                    float cY211111 = (float)nbt.getShort("pX");
                    float py = (float)nbt.getShort("pY");
                    float cZ211111 = (float)nbt.getShort("pZ");
                    double _px = (double)cY211111 - player.posX;
                    double angleH211111 = (double)py - player.posY;
                    double _pz = (double)cZ211111 - player.posZ;
                    double distSq = _px * _px + angleH211111 * angleH211111 + _pz * _pz;

                    if (distSq < Double.MAX_VALUE)
                    {
                        closestX = cY211111;
                        closestY = py;
                        closestZ = cZ211111;
                        foundSomething = true;
                    }
                }
            }
        }

        float bob2111111 = MathHelper.sin((float)player.ticksExisted / 5.0F) * 0.015F + 0.015F;

        if (foundSomething)
        {
            double cX2111111 = (double)closestX - player.posX;
            double cY2111111 = (double)closestY - player.posY + (double)bob2111111;
            double cZ2111111 = (double)closestZ - player.posZ;
            float dXZ = MathHelper.sqrt_double(cX2111111 * cX2111111 + cZ2111111 * cZ2111111);
            float angleH2111111 = (float)(Math.atan2(cZ2111111, cX2111111) * 180.0D / Math.PI) - player.rotationYaw + 90.0F;
            float angleV1 = (float)(Math.atan2((double)dXZ, cY2111111) * 180.0D / Math.PI) - player.rotationPitch;

            if (angleH2111111 < 0.0F)
            {
                angleH2111111 += 360.0F;
            }

            if (angleH2111111 > 360.0F)
            {
                angleH2111111 -= 360.0F;
            }

            if (angleH2111111 > this.angleHD)
            {
                this.angleHD += (angleH2111111 - this.angleHD) / 33.0F;
            }
            else if (angleH2111111 < this.angleHD)
            {
                this.angleHD -= (this.angleHD - angleH2111111) / 33.0F;
            }

            angleV1 += var20 * -85.0F;

            if (angleV1 < -180.0F)
            {
                angleV1 += 360.0F;
            }

            if (angleV1 > 180.0F)
            {
                angleV1 -= 360.0F;
            }

            if (angleV1 > this.angleVD)
            {
                this.angleVD += (angleV1 - this.angleVD) / 33.0F;
            }
            else if (angleV1 < this.angleVD)
            {
                this.angleVD -= (this.angleVD - angleV1) / 33.0F;
            }
        }
        else
        {
            if (this.angleHD > 0.0F)
            {
                this.angleHD -= this.angleHD / 33.0F;
            }

            if (this.angleVD > 0.0F)
            {
                this.angleVD -= this.angleVD / 33.0F;
            }
            else if (this.angleVD < 0.0F)
            {
                this.angleVD += this.angleVD / 33.0F;
            }

            this.angleHD = 0.0F;
            this.angleVD = 0.0F;
        }

        GL11.glTranslatef(0.0F, bob2111111, 0.0F);
        GL11.glRotatef(this.angleHD, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(this.angleVD, 1.0F, 0.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
        this.renderItemStack(var21, item, 1, true);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public void renderItemStack(EntityLiving par1EntityLiving, ItemStack par2ItemStack, int par3, boolean glint)
    {
        Minecraft mc = ModLoader.getMinecraftInstance();
        GL11.glPushMatrix();
        Utils.render3DItem(par2ItemStack, par3, 1.0F, par1EntityLiving.getBrightnessForRender(0.0F));
        GL11.glPopMatrix();
    }
}

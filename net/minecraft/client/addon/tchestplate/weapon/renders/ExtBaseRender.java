package net.minecraft.client.addon.tchestplate.weapon.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.BaseModelHammer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class ExtBaseRender extends BaseRenderHammer
{
    public ExtBaseRender(BaseModelHammer var1, String var2, byte var3)
    {
        super(var1, var2, var3);
    }

    public boolean handleRenderType(ItemStack var1, ItemRenderType var2)
    {
        return true;
    }

    public boolean shouldUseRenderHelper(ItemRenderType var1, ItemStack var2, ItemRendererHelper var3)
    {
        return true;
    }

    public void renderItem(ItemRenderType var1, ItemStack var2, Object... var3)
    {
        boolean var4 = false;

        if (var3 != null && var3.length > 1 && var3[1] instanceof EntityPlayer)
        {
            NBTTagCompound var5 = Utils.getOrCreateNbtData(var2);
            String var6 = var5.getString("nubowner");
            var4 = var6 != null && var6.equals(((EntityPlayer)var3[1]).getEntityName());
        }

        if (super.TypeModel == 7)
        {
            this.renderItemSwordHiro(var1, var2, var3, var4);
        }
        else if (super.TypeModel == 8)
        {
            this.renderItemAxeHiro(var1, var2, var3, var4);
        }
        else if (super.TypeModel == 9)
        {
            this.renderItemPickAxe1may(var1, var2, var3, true);
        }
    }

    private void renderItemPickAxe1may(ItemRenderType var1, ItemStack var2, Object[] var3, boolean var4)
    {
        GL11.glPushMatrix();
        EntityClientPlayerMP var5 = Minecraft.getMinecraft().thePlayer;
        int var6 = var5.worldObj.getLightBrightnessForSkyBlocks((int)var5.posX, (int)var5.posY, (int)var5.posZ, 0);
        int var7 = var6 % 65536;
        int var8 = var6 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var7 / 1.0F, (float)var8 / 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        if (var1.equals(ItemRenderType.EQUIPPED_FIRST_PERSON))
        {
            if (var4)
            {
                GL11.glTranslatef(0.5F, 1.4F, 0.7F);
                GL11.glRotatef(150.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScalef(0.7F, 0.7F, 0.7F);
                Minecraft.getMinecraft().renderEngine.bindTexture(super.texture);
                super.baseModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                GL11.glTranslatef(0.0F, -1.24F, 0.0F);
            }
        }
        else if (var1.equals(ItemRenderType.INVENTORY))
        {
            GL11.glTranslatef(0.5F, 0.4F, 0.2F);
            GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(135.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(0.7F, 0.7F, 0.7F);
            Minecraft.getMinecraft().renderEngine.bindTexture(super.texture);
            super.baseModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        }
        else if (var1.equals(ItemRenderType.ENTITY))
        {
            GL11.glTranslatef(0.0F, 0.1F, -0.15F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(140.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(0.4F, 0.4F, 0.4F);
            Minecraft.getMinecraft().renderEngine.bindTexture(super.texture);
            super.baseModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        }
        else if (var4)
        {
            GL11.glTranslatef(0.2F, 1.0F, 0.2F);
            GL11.glRotatef(252.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-163.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(1.2F, 1.2F, 1.2F);
            Minecraft.getMinecraft().renderEngine.bindTexture(super.texture);
            super.baseModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glTranslatef(0.0F, 0.6F, 0.0F);
        }

        GL11.glPopMatrix();
    }

    private void renderItemAxeHiro(ItemRenderType var1, ItemStack var2, Object[] var3, boolean var4)
    {
        GL11.glPushMatrix();
        EntityClientPlayerMP var5 = Minecraft.getMinecraft().thePlayer;
        int var6 = var5.worldObj.getLightBrightnessForSkyBlocks((int)var5.posX, (int)var5.posY, (int)var5.posZ, 0);
        int var7 = var6 % 65536;
        int var8 = var6 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var7 / 1.0F, (float)var8 / 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        if (var1.equals(ItemRenderType.EQUIPPED_FIRST_PERSON))
        {
            if (var4)
            {
                GL11.glTranslatef(0.1F, 1.0F, 0.95F);
                GL11.glRotatef(150.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScalef(0.6F, 0.6F, 0.6F);
                Minecraft.getMinecraft().renderEngine.bindTexture(super.texture);
                super.baseModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                GL11.glTranslatef(0.0F, -1.24F, 0.0F);
                this.reb(0.0F, -0.05F, 0.0F);
            }
        }
        else if (var1.equals(ItemRenderType.INVENTORY))
        {
            GL11.glTranslatef(0.0F, -0.2F, 0.2F);
            GL11.glRotatef(130.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(170.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(0.52F, 0.52F, 0.52F);
            Minecraft.getMinecraft().renderEngine.bindTexture(super.texture);
            super.baseModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        }
        else if (var1.equals(ItemRenderType.ENTITY))
        {
            GL11.glTranslatef(0.0F, -0.15F, 0.05F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(145.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(0.35F, 0.35F, 0.35F);
            Minecraft.getMinecraft().renderEngine.bindTexture(super.texture);
            super.baseModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        }
        else if (var4)
        {
            GL11.glTranslatef(0.6F, 0.8F, 0.6F);
            GL11.glRotatef(250.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-160.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(1.3F, 1.3F, 1.3F);
            Minecraft.getMinecraft().renderEngine.bindTexture(super.texture);
            super.baseModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glTranslatef(0.0F, 0.6F, 0.0F);
            this.reb(0.0F, -1.88F, 0.0F);
        }

        GL11.glPopMatrix();
    }

    private void renderItemSwordHiro(ItemRenderType var1, ItemStack var2, Object[] var3, boolean var4)
    {
        GL11.glPushMatrix();
        EntityClientPlayerMP var5 = Minecraft.getMinecraft().thePlayer;
        int var6 = var5.worldObj.getLightBrightnessForSkyBlocks((int)var5.posX, (int)var5.posY, (int)var5.posZ, 0);
        int var7 = var6 % 65536;
        int var8 = var6 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var7 / 1.0F, (float)var8 / 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        if (var1.equals(ItemRenderType.EQUIPPED_FIRST_PERSON))
        {
            if (var4)
            {
                GL11.glTranslatef(0.2F, 0.8F, 0.8F);
                GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-170.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScalef(0.6F, 0.6F, 0.6F);
                Minecraft.getMinecraft().renderEngine.bindTexture(super.texture);
                super.baseModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                GL11.glTranslatef(0.0F, -0.35F, 0.0F);
                this.reb(0.0F, -0.05F, 0.0F);
            }
        }
        else if (var1.equals(ItemRenderType.INVENTORY))
        {
            GL11.glTranslatef(0.0F, -0.1F, 0.2F);
            GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(16.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(110.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            Minecraft.getMinecraft().renderEngine.bindTexture(super.texture);
            super.baseModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        }
        else if (var1.equals(ItemRenderType.ENTITY))
        {
            GL11.glTranslatef(0.0F, -0.1F, 0.1F);
            GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(0.3F, 0.3F, 0.3F);
            Minecraft.getMinecraft().renderEngine.bindTexture(super.texture);
            super.baseModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        }
        else if (var4)
        {
            GL11.glTranslatef(0.6F, 0.8F, 0.6F);
            GL11.glRotatef(250.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-160.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(1.3F, 1.3F, 1.3F);
            Minecraft.getMinecraft().renderEngine.bindTexture(super.texture);
            super.baseModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glTranslatef(0.0F, 0.6F, 0.0F);
            this.reb(0.0F, -1.0F, 0.0F);
        }

        GL11.glPopMatrix();
    }

    public void reb(float var1, float var2, float var3)
    {
    }
}

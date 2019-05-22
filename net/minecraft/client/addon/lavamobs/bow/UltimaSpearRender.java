package net.minecraft.client.addon.lavamobs.bow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.fx.manager.ManagerFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class UltimaSpearRender implements IItemRenderer
{
    public static UltimaSpearModel model;

    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        EntityPlayer entity = null;
        boolean flag = false;

        if (data.length > 1 && data[1] instanceof EntityPlayer)
        {
            entity = (EntityPlayer)data[1];
            int toch11 = entity.getItemInUseDuration();

            if (toch11 > 0)
            {
                flag = true;
            }
        }

        byte toch111 = 0;

        if (item.stackTagCompound != null && item.stackTagCompound.hasKey("enhance"))
        {
            toch111 = item.stackTagCompound.getByte("enhance");
        }

        GL11.glPushMatrix();

        if (type.equals(ItemRenderType.EQUIPPED_FIRST_PERSON))
        {
            if (!flag)
            {
                GL11.glTranslatef(0.2F, 1.5F, 0.8F);
                GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-160.0F, 1.0F, 0.0F, 0.0F);
            }
            else
            {
                GL11.glTranslatef(0.2F, 1.3F, 0.8F);
                GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(120.0F, 1.0F, 0.0F, 0.0F);
            }

            GL11.glScalef(0.8F, 0.8F, 0.8F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/bow/UltimaSpear.png");
            model.render();
            GL11.glTranslatef(0.0F, 0.6F, 0.0F);
            ManagerFX.showLvLweapon(true, toch111, 0.3F, 2.0F, 0.3F, -0.3F, 0.0F, -0.3F);
        }
        else if (type.equals(ItemRenderType.INVENTORY))
        {
            GL11.glTranslatef(0.7F, 0.7F, 0.2F);
            GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(150.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(0.6F, 0.6F, 0.6F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/bow/UltimaSpear.png");
            model.render();
        }
        else if (type.equals(ItemRenderType.ENTITY))
        {
            GL11.glTranslatef(0.0F, 0.2F, -0.2F);
            GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(0.4F, 0.4F, 0.4F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/bow/UltimaSpear.png");
            model.render();
        }
        else
        {
            if (!flag)
            {
                GL11.glTranslatef(-0.4F, 1.5F, -0.3F);
                GL11.glRotatef(250.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-340.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(40.0F, 1.0F, 0.0F, 0.0F);
            }
            else
            {
                GL11.glTranslatef(0.5F, -0.9F, 0.4F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-60.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
            }

            GL11.glScalef(1.5F, 1.5F, 1.5F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/bow/UltimaSpear.png");
            model.render();
            GL11.glTranslatef(0.0F, 0.8F, 0.0F);
            ManagerFX.showLvLweapon(false, toch111, 0.35F, 1.2F, 0.35F, -0.35F, 0.0F, -0.35F);
        }

        GL11.glPopMatrix();
    }
}

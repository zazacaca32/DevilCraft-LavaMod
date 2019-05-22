package net.minecraft.client.addon.lavamobs.bow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.fx.manager.ManagerFX;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class ShurikenShotRender implements IItemRenderer
{
    public static ShurikenShotModel model;

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
        boolean invise = false;
        byte flag = 0;

        if (data.length > 1 && data[1] instanceof EntityPlayer)
        {
            entity = (EntityPlayer)data[1];
            int toch11 = entity.getItemInUseDuration();
            ExtendedPlayer pi = ExtendedPlayer.get(entity);

            if (pi != null && pi.PredatorMode == 50)
            {
                invise = true;
            }
            else
            {
                invise = false;
            }

            if (toch11 == 0)
            {
                flag = 0;
            }
            else if (toch11 < 10 && toch11 > 0)
            {
                flag = 1;
            }
            else if (toch11 < 20 && toch11 >= 10)
            {
                flag = 2;
            }
            else if (toch11 < 30 && toch11 >= 20)
            {
                flag = 3;
            }
            else if (toch11 < 40 && toch11 >= 30)
            {
                flag = 4;
            }
            else if (toch11 < 50 && toch11 >= 40)
            {
                flag = 5;
            }
            else if (toch11 < 60 && toch11 >= 50)
            {
                flag = 6;
            }
            else if (toch11 >= 60)
            {
                flag = 7;
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
            if (flag > 0)
            {
                GL11.glTranslatef(0.5F, 1.1F, 0.8F);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScalef(0.7F, 0.7F, 0.7F);
            }
            else
            {
                GL11.glTranslatef(0.3F, 1.4F, 0.8F);
                GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(40.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScalef(0.8F, 0.8F, 0.8F);
            }

            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/bow/ShurikensHot.png");
            model.render(flag);
            GL11.glTranslatef(0.0F, 0.6F, -0.5F);
            GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(8.0F, 1.0F, 0.0F, 0.0F);
            ManagerFX.showLvLweapon(true, toch111, 1.0F, 1.5F, 0.1F, -1.0F, 0.0F, -0.1F);
        }
        else if (type.equals(ItemRenderType.INVENTORY))
        {
            GL11.glTranslatef(0.8F, 1.0F, 0.2F);
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(1.1F, 1.1F, 1.1F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/bow/ShurikensHot.png");
            model.render(7);
        }
        else if (type.equals(ItemRenderType.ENTITY))
        {
            GL11.glTranslatef(-0.2F, 0.3F, 0.0F);
            GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(150.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(0.65F, 0.65F, 0.65F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/bow/ShurikensHot.png");
            model.render(7);
        }
        else
        {
            GL11.glTranslatef(0.8F, -0.2F, 0.8F);
            GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-41.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(1.5F, 1.5F, 1.5F);

            if (invise)
            {
                Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/bow/ShurikensHotInv.png");
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
            }
            else
            {
                Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/bow/ShurikensHot.png");
            }

            model.render(flag);
            GL11.glTranslatef(0.0F, 0.6F, -0.48F);
            GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);

            if (invise)
            {
                GL11.glDisable(GL11.GL_BLEND);
            }
            else
            {
                ManagerFX.showLvLweapon(false, toch111, 1.0F, 1.5F, 0.1F, -1.0F, 0.0F, -0.1F);
            }
        }

        GL11.glPopMatrix();
    }
}

package net.minecraft.client.addon.lavamobs.bow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.fx.manager.ManagerFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class LavaBowRender implements IItemRenderer
{
    public static LavaBowModel model = new LavaBowModel();

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
        byte flag = 0;
        int toch11;

        if (data.length > 1 && data[1] instanceof EntityPlayer)
        {
            entity = (EntityPlayer)data[1];
            toch11 = entity.getItemInUseDuration();

            if (toch11 == 0)
            {
                flag = 0;
            }
            else if (toch11 < 30 && toch11 > 0)
            {
                flag = 1;
            }
            else if (toch11 < 60 && toch11 >= 30)
            {
                flag = 2;
            }
            else if (toch11 >= 60)
            {
                flag = 3;
            }
        }

        toch11 = 0;

        if (item.stackTagCompound != null && item.stackTagCompound.hasKey("sup"))
        {
            toch11 = item.stackTagCompound.getInteger("sup");
        }

        GL11.glPushMatrix();

        if (type.equals(ItemRenderType.EQUIPPED_FIRST_PERSON))
        {
            GL11.glTranslatef(0.3F, 0.8F, 0.5F);
            GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(235.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/bow/LavaBow.png");
            model.render(flag);

            if (flag == 0)
            {
                GL11.glTranslatef(-0.1F, 0.4F, 0.32F);
                ManagerFX.showLvLweapon(true, (byte)toch11, 0.3F, 2.0F, 0.3F, -0.3F, 0.0F, -0.3F);
            }
            else
            {
                GL11.glTranslatef(-0.05F, 0.2F, 0.14F);
                ManagerFX.showLvLweapon(true, (byte)toch11, 0.3F, 1.6F, 0.3F, -0.3F, 0.0F, -0.3F);
            }
        }
        else if (type.equals(ItemRenderType.INVENTORY))
        {
            GL11.glTranslatef(0.2F, 0.3F, 0.5F);
            GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/bow/LavaBow.png");
            model.render(0);
        }
        else if (type.equals(ItemRenderType.ENTITY))
        {
            GL11.glTranslatef(0.0F, 0.05F, 0.15F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(140.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/bow/LavaBow.png");
            model.render(0);
        }
        else
        {
            GL11.glTranslatef(0.8F, 0.7F, 0.8F);
            GL11.glRotatef(250.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-160.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(1.5F, 1.5F, 1.5F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/bow/LavaBow.png");
            model.render(flag);
            GL11.glTranslatef(-0.28F, 0.3F, 0.0F);
            ManagerFX.showLvLweapon(false, (byte)toch11, 0.35F, 2.0F, 0.35F, -0.35F, 0.0F, -0.35F);
        }

        GL11.glPopMatrix();
    }
}

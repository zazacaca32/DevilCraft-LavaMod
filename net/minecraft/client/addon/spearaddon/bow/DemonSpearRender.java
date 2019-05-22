package net.minecraft.client.addon.spearaddon.bow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.spearaddon.ClientProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class DemonSpearRender implements IItemRenderer
{
    public static DemonSpearModel model;

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
            int num = entity.getItemInUseDuration();

            if (num > 0)
            {
                flag = true;
            }
        }

        GL11.glPushMatrix();

        if (item.getItem() instanceof DemonSpearItem)
        {
            model.enchant = ((DemonSpearItem)item.getItem()).getEnchantHammer(item);
        }

        float num1;

        if (type.equals(ItemRenderType.EQUIPPED_FIRST_PERSON))
        {
            if (!flag)
            {
                GL11.glTranslatef(0.7F, 1.2F, 0.3F);
                num1 = 0.9F;
                GL11.glScalef(num1, num1, num1);
                GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 2.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-160.0F, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.3F, 0.1F, -0.1F);
                GL11.glRotatef(30.0F, -1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(5.0F, 0.0F, 1.0F, 1.0F);
            }
            else
            {
                num1 = 0.6F;
                GL11.glScalef(num1, num1, num1);
                GL11.glTranslatef(0.2F, 2.0F, 2.0F);
                GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(120.0F, 1.0F, 0.0F, 0.0F);
            }

            GL11.glScalef(0.8F, 0.8F, 0.8F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/provider/ual/hellspear.png");
            GL11.glTranslatef(0.0F, 1.5F, 0.0F);
        }
        else if (type.equals(ItemRenderType.INVENTORY))
        {
            GL11.glTranslatef(0.3F, 0.15F, 0.4F);
            GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(150.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(0.4F, 0.4F, 0.4F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/provider/ual/hellspear.png");
            model.render();
        }
        else if (type.equals(ItemRenderType.ENTITY))
        {
            GL11.glTranslatef(0.0F, 0.2F, -0.2F);
            GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(0.4F, 0.4F, 0.4F);
            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/provider/ual/hellspear.png");
            model.render();
        }
        else if (!flag)
        {
            GL11.glTranslatef(-0.3F, 1.2F, -0.3F);
            num1 = 0.825F;
            GL11.glScalef(num1, num1, num1);
            GL11.glRotatef(53.0F, 1.0F, 0.0F, 1.0F);
            GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(250.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-160.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.1F, 1.0F, -1.0F);
            GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
        }
        else
        {
            GL11.glTranslatef(0.3F, -0.9F, 0.3F);
            GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
        }

        GL11.glScalef(1.5F, 1.5F, 1.5F);
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/provider/ual/hellspear.png");
        model.render();
        GL11.glTranslatef(0.0F, 0.6F, 0.0F);
        GL11.glPopMatrix();
    }

    static
    {
        model = (DemonSpearModel)((DemonSpearModel)(ClientProxy.Models[0] = new DemonSpearModel()));
    }
}

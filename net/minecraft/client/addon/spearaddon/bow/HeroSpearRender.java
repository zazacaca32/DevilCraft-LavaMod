package net.minecraft.client.addon.spearaddon.bow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.spearaddon.ClientProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class HeroSpearRender implements IItemRenderer
{
    public static HeroSpearModel model;

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

        if (data.length > 1 && data[1] instanceof EntityPlayer && (entity = (EntityPlayer)data[1]).getItemInUseDuration() > 0)
        {
            flag = true;
        }

        GL11.glPushMatrix();

        if (item.getItem() instanceof HeroSpearItem)
        {
            model.enchant = ((HeroSpearItem)item.getItem()).getEnchantHammer(item);
        }

        if (type.equals(ItemRenderType.EQUIPPED_FIRST_PERSON))
        {
            float num;

            if (!flag)
            {
                GL11.glTranslatef(0.2F, 1.0F, 0.9F);
                GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(55.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(85.0F, 1.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                num = 0.6F;
                GL11.glScalef(num, num, num);
                GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-160.0F, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.8F, 0.0F, -0.2F);
                GL11.glRotatef(30.0F, -1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(5.0F, 0.0F, 1.0F, 1.0F);
            }
            else
            {
                num = 0.7F;
                GL11.glScalef(num, num, num);
                GL11.glTranslatef(0.2F, 2.0F, 2.0F);
                GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(120.0F, 1.0F, 0.0F, 0.0F);
            }

            GL11.glScalef(0.8F, 0.8F, 0.8F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/provider/ual/hero/kopie.png");
            GL11.glTranslatef(0.0F, 1.5F, 0.0F);
        }
        else if (type.equals(ItemRenderType.INVENTORY))
        {
            GL11.glTranslatef(0.3F, 0.15F, 0.4F);
            GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(150.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(0.4F, 0.4F, 0.4F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/provider/ual/hero/kopie.png");
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
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/provider/ual/hero/kopie.png");
            model.render();
        }
        else if (!flag)
        {
            GL11.glRotatef(105.0F, 1.0F, -0.4F, 0.9F);
            GL11.glRotatef(-28.0F, 7.0F, 1.0F, 0.0F);
            GL11.glTranslatef(0.3F, 0.4F, -1.3F);
            GL11.glRotatef(250.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-160.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.1F, 1.0F, -1.0F);
            GL11.glRotatef(-30.0F, 1.0F, -0.2F, 0.0F);
        }
        else
        {
            GL11.glTranslatef(0.3F, -0.9F, 0.3F);
            GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
        }

        GL11.glScalef(1.5F, 1.5F, 1.5F);
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/provider/ual/hero/kopie.png");
        model.render();
        GL11.glTranslatef(0.0F, 0.6F, 0.0F);
        GL11.glPopMatrix();
    }

    static
    {
        ClientProxy.Models[3] = new HeroSpearModel();
        model = (HeroSpearModel)ClientProxy.Models[3];
    }
}

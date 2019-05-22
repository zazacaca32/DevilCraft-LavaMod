package net.minecraft.client.addon.spearaddon.bow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.spearaddon.ClientProxy;
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
        boolean flag = false;

        if (data.length > 1 && data[1] instanceof EntityPlayer)
        {
            entity = (EntityPlayer)data[1];
            int n = entity.getItemInUseDuration();

            if (n > 0)
            {
                flag = true;
            }
        }

        GL11.glPushMatrix();

        if (item.getItem() instanceof ShurikenShotItem)
        {
            model.enchant = ((ShurikenShotItem)item.getItem()).getEnchantHammer(item);
        }

        float n1;

        if (type.equals(ItemRenderType.EQUIPPED_FIRST_PERSON))
        {
            if (!flag)
            {
                GL11.glTranslatef(-0.4F, 0.5F, 0.8F);
                n1 = 1.2F;
                GL11.glScalef(n1, n1, n1);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, -1.0F);
                GL11.glRotatef(30.0F, -1.0F, 0.0F, 0.0F);
            }
            else
            {
                GL11.glTranslatef(-0.4F, 0.2F, 1.2F);
                n1 = 1.1F;
                GL11.glScalef(n1, n1, n1);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, -1.0F);
                GL11.glRotatef(40.0F, -1.0F, 0.0F, 0.0F);
            }

            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/provider/ual/hu.png");
            model.render(0);
            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
        }
        else if (type.equals(ItemRenderType.INVENTORY))
        {
            GL11.glTranslatef(0.0F, 0.08F, 0.8F);
            n1 = 1.1F;
            GL11.glScalef(n1, n1, n1);
            GL11.glRotatef(95.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(25.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(25.0F, 0.0F, 0.0F, -1.0F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/provider/ual/hu.png");
            model.render(0);
        }
        else if (type.equals(ItemRenderType.ENTITY))
        {
            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/provider/ual/hu.png");
            model.render(0);
        }
        else
        {
            ///       Minecraft.getMinecraft().renderEngine.bindTexture("/mods/provider/ual/hu.png");
            ///       GL11.glTranslatef(1.25F, 1.65F, 0.68F);
            ///       n1 = 1.3F;
            ///       GL11.glScalef(n1, n1, n1);
            ///       GL11.glRotatef(220.0F, 0.0F, 1.0F, 0.0F);
            ///       GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
            ///       GL11.glRotatef(20.0F, -1.0F, 0.0F, 0.0F);
            ///       GL11.glRotatef(5.0F, 0.0F, -1.0F, 0.0F);
            ///       model.render(0);
            ///       GL11.glTranslatef(0.0F, 0.6F, -0.48F);
            ///     GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
        }

        GL11.glPopMatrix();
    }

    static
    {
        model = (ShurikenShotModel)((ShurikenShotModel)(ClientProxy.Models[8] = new ShurikenShotModel()));
    }
}

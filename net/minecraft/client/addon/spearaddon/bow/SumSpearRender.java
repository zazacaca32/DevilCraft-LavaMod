package net.minecraft.client.addon.spearaddon.bow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.spearaddon.ClientProxy;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class SumSpearRender implements IItemRenderer
{
    public static SumSpearModel model;

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

        if (item.getItem() instanceof SumSpearItem)
        {
            model.enchant = ((SumSpearItem)item.getItem()).getEnchantHammer(item);
        }

        NBTTagCompound nbt;
        float num1;

        if (type.equals(ItemRenderType.EQUIPPED_FIRST_PERSON))
        {
            if (!flag)
            {
                GL11.glTranslatef(0.3F, 1.4F, 0.75F);
                GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(47.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-170.0F, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.3F, 0.2F, -0.1F);
                num1 = 0.7F;
                GL11.glScalef(num1, num1, num1);
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(12.0F, -1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(15.0F, 0.0F, 1.0F, 1.0F);
            }
            else
            {
                GL11.glTranslatef(0.2F, 1.3F, 0.8F);
                GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(130.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef(-0.3F, 0.3F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
            }

            GL11.glScalef(0.8F, 0.8F, 0.8F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/provider/ual/sumspear.png");
            GL11.glTranslatef(0.0F, 1.5F, 0.0F);
        }
        else if (type.equals(ItemRenderType.INVENTORY))
        {
            GL11.glTranslatef(0.3F, 0.13F, 0.7F);
            GL11.glRotatef(-55.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(141.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/provider/ual/sumspear.png");
            nbt = Utils.getOrCreateNbtData(item);

            if (nbt.getInteger("winv") == 1)
            {
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
                model.render();
                GL11.glDisable(GL11.GL_BLEND);
            }
        }
        else if (type.equals(ItemRenderType.ENTITY))
        {
            GL11.glTranslatef(0.0F, 0.2F, -0.2F);
            GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(0.5F, 0.4F, 0.4F);
            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/provider/ual/sumspear.png");
            nbt = Utils.getOrCreateNbtData(item);

            if (nbt.getInteger("winv") == 1)
            {
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
                model.render();
                GL11.glDisable(GL11.GL_BLEND);
//         } else {
//            model.render();
            }
        }
        else if (!flag)
        {
            num1 = 0.8F;
            GL11.glScalef(num1, num1, num1);
            GL11.glTranslatef(0.6F, -0.2F, 0.7F);
            GL11.glRotatef(10.0F, -1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(-0.3F, 1.5F, -0.3F);
            GL11.glRotatef(250.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-160.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.1F, 1.0F, -1.0F);
            GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
        }
        else
        {
            GL11.glTranslatef(0.3F, -0.9F, 0.3F);
            GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
        }

        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.1F, -1.0F, 0.0F);
        GL11.glScalef(1.5F, 1.5F, 1.5F);
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/provider/ual/sumspear.png");
        nbt = Utils.getOrCreateNbtData(item);

        if (nbt.getInteger("winv") == 1)
        {
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
            model.render();
            GL11.glDisable(GL11.GL_BLEND);
        }
        else
        {
            model.render();
        }

        GL11.glTranslatef(0.0F, 0.6F, 0.0F);
        GL11.glPopMatrix();
    }

    static
    {
        model = (SumSpearModel)((SumSpearModel)(ClientProxy.Models[1] = new SumSpearModel()));
    }
}

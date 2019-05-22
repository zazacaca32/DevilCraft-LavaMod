package net.minecraft.client.addon.lavamobs.spiner;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class LavaSpinerRender implements IItemRenderer
{
    public static LavaSpinerModel model = new LavaSpinerModel();
    float s = 0.0F;

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
        GL11.glPushMatrix();
        EntityPlayer entity = null;
        int count = 0;

        if (data.length > 1 && data[1] instanceof EntityPlayer)
        {
            entity = (EntityPlayer)data[1];
            count = entity.getItemInUseDuration();
        }

        if (type.equals(ItemRenderType.EQUIPPED_FIRST_PERSON))
        {
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);

            if (count > 0)
            {
                GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
            }
            else
            {
                GL11.glRotatef(235.0F, 0.0F, 1.0F, 0.0F);
            }

            GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);

            if (this.s < 20.0F)
            {
                this.s = (float)count * 0.1F;
            }

            if (count == 0)
            {
                this.s = 0.0F;
            }

            GL11.glRotatef(0.0F + (float)count * this.s, 0.0F, 0.0F, 1.0F);
        }
        else if (type.equals(ItemRenderType.INVENTORY))
        {
            GL11.glTranslatef(0.5F, 0.25F, 0.5F);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
        }
        else if (type.equals(ItemRenderType.ENTITY))
        {
            GL11.glTranslatef(0.0F, 0.0F, 0.1F);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            GL11.glRotatef(-70.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
        }
        else
        {
            GL11.glTranslatef(0.7F, 0.5F, 0.7F);
            GL11.glScalef(0.7F, 0.7F, 0.7F);
            GL11.glRotatef(250.0F, 0.0F, 0.0F, 1.0F);

            if (count > 0)
            {
                GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
            }
            else
            {
                GL11.glRotatef(-160.0F, 0.0F, 1.0F, 0.0F);
            }

            GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);

            if (this.s < 45.0F)
            {
                this.s = (float)count * 0.1F;
            }

            if (count == 0)
            {
                this.s = 0.0F;
            }

            GL11.glRotatef(0.0F + (float)count * this.s, 0.0F, 0.0F, 1.0F);
        }

        int sId = item.getItemDamage();

        if (sId == 0)
        {
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/spiner/spiner.png");
        }
        else
        {
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/spiner/spiner_" + sId + ".png");
        }

        model.render();
        GL11.glPopMatrix();
    }
}

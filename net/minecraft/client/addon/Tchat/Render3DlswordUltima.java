package net.minecraft.client.addon.Tchat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class Render3DlswordUltima implements IItemRenderer
{
    protected Model3DlswordUltima model3DlswordUltima = new Model3DlswordUltima();

    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        switch (ItemRenderType.values()[type.ordinal()].ordinal())
        {
            case 1:
                return true;

            default:
                return false;
        }
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return false;
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch (ItemRenderType.values()[type.ordinal()].ordinal())
        {
            case 1:
                GL11.glPushMatrix();
                GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                boolean isFirstPerson = false;

                if (!(data[1] != null & data[1] instanceof EntityPlayer))
                {
                    GL11.glTranslatef(0.65F, -1.2F, 0.0F);
                }
                else if ((EntityPlayer)data[1] != Minecraft.getMinecraft().renderViewEntity || Minecraft.getMinecraft().gameSettings.thirdPersonView != 0 || (Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)
                {
                    GL11.glTranslatef(0.7F, 0.08F, 0.0F);
                }
                else
                {
                    isFirstPerson = true;
                    GL11.glTranslatef(0.5F, -0.1F, 0.2F);
                }

                float scale = 0.5F;
                GL11.glScalef(scale, scale, scale);
                Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lsword/textures/items/render/swordLavaUltima.png");
                NBTTagCompound nbt = Utils.getOrCreateNbtData(item);

                if (nbt.getInteger("winv") == 1)
                {
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
                    this.model3DlswordUltima.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                    GL11.glDisable(GL11.GL_BLEND);
                }
                else
                {
                    this.model3DlswordUltima.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                }

                GL11.glPopMatrix();

            default:
        }
    }
}

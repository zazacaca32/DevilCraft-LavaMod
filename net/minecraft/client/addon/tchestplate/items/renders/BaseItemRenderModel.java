package net.minecraft.client.addon.tchestplate.items.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.items.IRenderItemChestPlate;
import net.minecraft.client.addon.tchestplate.items.renders.BaseItemRenderModel$1;
import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtGui;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class BaseItemRenderModel implements IItemRenderer
{
    BaseItemModel bm;

    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        switch (BaseItemRenderModel$1.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[type.ordinal()])
        {
            case 1:
                return true;

            case 2:
                return true;

            case 3:
                return true;

            default:
                return false;
        }
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        switch (BaseItemRenderModel$1.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[type.ordinal()])
        {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return true;

            default:
                return false;
        }
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch (BaseItemRenderModel$1.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[type.ordinal()])
        {
            case 1:
                GL11.glPushMatrix();
                GL11.glRotatef(157.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(65.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(18.0F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(-0.75F, -0.2F, 0.0F);
                boolean scale41 = false;

                if (data[1] != null && data[1] instanceof EntityPlayer)
                {
                    if (data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && (!(Minecraft.getMinecraft().currentScreen instanceof GuiInventory) && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && !(Minecraft.getMinecraft().currentScreen instanceof ArmorExtGui) || RenderManager.instance.playerViewY != 180.0F))
                    {
                        scale41 = true;
                        GL11.glTranslatef(0.8F, -0.8F, 0.2F);
                    }
                    else
                    {
                        GL11.glTranslatef(1.6F, -0.7F, 0.15F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-19.0F, 0.0F, 0.0F, 1.0F);
                    }
                }
                else
                {
                    GL11.glTranslatef(0.65F, -1.2F, 0.0F);
                }

                float scale3 = 1.5F;
                GL11.glScalef(1.5F, 1.5F, 1.5F);
                ((IRenderItemChestPlate)item.getItem()).getItemModelStatic(item.getItemDamage()).renderAsItem(0.0625F, 0.0F);
                GL11.glPopMatrix();
                break;

            case 2:
                GL11.glDisable(GL11.GL_CULL_FACE);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0F, -0.1F, 0.0F);
                GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
                float scale4 = 2.5F;
                GL11.glScalef(2.5F, 2.5F, 2.5F);
                ((IRenderItemChestPlate)item.getItem()).getItemModelStatic(item.getItemDamage()).renderAsItem(0.0625F, 0.0F);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glPopMatrix();
                break;

            case 3:
                GL11.glDisable(GL11.GL_CULL_FACE);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glPushMatrix();
                GL11.glRotatef(180.0F, 1.0F, 0.0F, 1.0F);
                GL11.glRotatef(225.0F, 0.0F, 1.0F, 0.0F);
                ((IRenderItemChestPlate)item.getItem()).getItemModelStatic(item.getItemDamage()).renderAsItem(0.0625F, 0.0F);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glPopMatrix();
        }
    }
}

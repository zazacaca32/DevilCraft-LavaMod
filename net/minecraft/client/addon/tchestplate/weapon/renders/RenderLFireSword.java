package net.minecraft.client.addon.tchestplate.weapon.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelLTomahawk;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelLFireSword;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class RenderLFireSword
    implements IItemRenderer
{
    protected ModelLFireSword modelLFireSword = new ModelLFireSword();

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
    {
        return (type == IItemRenderer.ItemRenderType.EQUIPPED) || (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON);
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
    {
        return false;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
    {
        GL11.glPushMatrix();
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-0.1F, 0.1F, -0.08F);

        switch (type.ordinal())
        {
            case 1:
                GL11.glTranslatef(0.8F, -0.15F, 0.05F);
                break;

            case 2:
                GL11.glTranslatef(0.95F, 0.3F, -0.15F);
                float f1 = 0.45F;
        }

        float scale = 0.45F;
        GL11.glScalef(scale, scale, scale);
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/Alo/firesword.png");
        this.modelLFireSword.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.07F);
        GL11.glPopMatrix();
    }
}
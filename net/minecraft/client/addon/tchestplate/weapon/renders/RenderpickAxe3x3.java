package net.minecraft.client.addon.tchestplate.weapon.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelLavaPickAxe;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class RenderpickAxe3x3 implements IItemRenderer
{
    protected ModelLavaPickAxe ModelLavaPickAxe = new ModelLavaPickAxe();

    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return false;
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        GL11.glPushMatrix();
        GL11.glRotatef(190.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(-45.0F, 0.5F, 0.1F, 1.5F);
        GL11.glTranslatef(-0.1F, -0.08F, -0.1F);

        switch (type.ordinal())
        {
            case 1:
                GL11.glTranslatef(0.7F, 0.0F, 0.07F);
                GL11.glScalef(0.25F, 0.25F, 0.25F);
                break;

            case 2:
                GL11.glTranslatef(0.5F, 0.0F, -0.1F);
                GL11.glScalef(0.5F, 0.5F, 0.5F);
        }

        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/provider/ual/pickAxe1may.png");
        this.ModelLavaPickAxe.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.07F);
        GL11.glPopMatrix();
    }
}

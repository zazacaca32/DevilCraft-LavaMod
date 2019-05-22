package net.minecraft.client.addon.tchestplate.aoc;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class RenderDemon implements IItemRenderer
{
    protected ModelDemon model3DlswordLavaRB = new ModelDemon();

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
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);

        switch (type.ordinal())
        {
            case 1:
                GL11.glTranslatef(0.7F, 0.08F, 0.0F);
                break;

            case 2:
                GL11.glTranslatef(0.5F, -0.1F, 0.2F);
        }

        float scale = 0.45F;
        GL11.glScalef(scale, scale, scale);
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/qodermelony/textures/items/Demontexture.png");
        this.model3DlswordLavaRB.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.07F);
        GL11.glPopMatrix();
    }
}

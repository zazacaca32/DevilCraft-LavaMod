package net.minecraft.client.addon.tchestplate.weapon.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelHeroHammer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class RenderHeroAxe implements IItemRenderer
{
    protected ModelHeroHammer model3DlswordLavaRB = new ModelHeroHammer();

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
        GL11.glRotatef(15.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(181.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(181.0F, 1.0F, 1.0F, 0.0F);
        GL11.glRotatef(181.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-46.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
        GL11.glTranslatef(-0.0F, 0.08F, 0.06F);
        float scale;

        switch (type.ordinal())
        {
            case 1:
                GL11.glTranslatef(0.7F, -0.15F, 0.0F);
                break;

            case 2:
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(0.7F, -0.35F, -0.1F);
                scale = 0.45F;
        }

        scale = 0.55F;
        GL11.glScalef(scale, scale, scale);
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/acp/modeltex/heroo.png");
        this.model3DlswordLavaRB.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.07F);
        GL11.glPopMatrix();
    }
}

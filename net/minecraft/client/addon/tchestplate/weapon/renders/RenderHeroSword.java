package net.minecraft.client.addon.tchestplate.weapon.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelHeroSword;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class RenderHeroSword implements IItemRenderer
{
    protected ModelHeroSword model3DlswordLavaRB = new ModelHeroSword();

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
        GL11.glTranslatef(0.3F, -0.4F, 0.0F);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
        float scale;

        switch (type.ordinal())
        {
            case 1:
                GL11.glTranslatef(0.7F, 0.08F, 0.0F);
                break;

            case 2:
                scale = 0.7F;
                GL11.glScalef(scale, scale, scale);
                GL11.glTranslatef(0.8F, -0.3F, 0.2F);
        }

        scale = 0.45F;
        GL11.glScalef(scale, scale, scale);
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/acp/modeltex/heroo.png");
        this.model3DlswordLavaRB.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.07F);
        GL11.glPopMatrix();
    }
}

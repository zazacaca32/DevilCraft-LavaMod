package net.minecraft.client.addon.tchestplate.weapon.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelLTomahawk;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class RenderLTomahawk implements IItemRenderer
{
    protected ModelLTomahawk model3DlswordLavaRB = new ModelLTomahawk();

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
        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-0.1F, 0.10F, -0.08F);
        float scale;

        switch (type.ordinal())
        {
            case 1:
                GL11.glTranslatef(0.8F, -0.15F, 0.05F);
                break;

            case 2:
                GL11.glTranslatef(0.9F, 0.0F, -0.1F);
                scale = 0.45F;
        }

        scale = 0.55F;
        GL11.glScalef(scale, scale, scale);
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/Alo/OGNMolot.png");
        this.model3DlswordLavaRB.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.07F);
        GL11.glPopMatrix();
    }
}

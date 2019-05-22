package net.minecraft.client.addon.tchestplate.weapon.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.spearaddon.ClientProxy;
import net.minecraft.client.addon.spearaddon.bow.ShurikenShotModel;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelLStaffFriz;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelLStaffVedma;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class RenderLStaffVedma implements IItemRenderer
{
    protected ModelLStaffVedma baseModel = new ModelLStaffVedma();
    String texture;

    public RenderLStaffVedma(ModelLStaffVedma baseModel, String texture)
    {
        this.texture = texture;
    }

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
        GL11.glRotatef(180.0F, 1.2F, 0.3F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.1F, 1.0F);
        GL11.glRotatef(180.0F, 0.5F, 0.9F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-1.1F, -0.5F, -0.1F);
        GL11.glScalef(0.6f, 0.6f, 0.6f);

        switch (BaseRenderHammer.NamelessClass923090959.$SwitchMap$net$minecraftforge$client$ItemRenderType[type.ordinal()])
        {
            case 1:
                GL11.glTranslatef(0.7F, 0.08F, 0.0F);
                GL11.glScalef(0.6f, 0.6f, 0.6f);
                break;

            case 2:
                GL11.glRotatef(220.0F, 0.7F, 1.6F, 0.06F);
                GL11.glTranslatef(0.0F, 0.8F, 0.15F);
                GL11.glScalef(0.7f, 0.7f, 0.7f);
        }

        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/AloCods/renders/lstaffvedma.png");
        baseModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }
}

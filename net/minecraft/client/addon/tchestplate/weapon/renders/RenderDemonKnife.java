package net.minecraft.client.addon.tchestplate.weapon.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.addon.tchestplate.weapon.items.BaseLezvie;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.BaseModelHammer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class RenderDemonKnife implements IItemRenderer
{
    protected BaseModelHammer baseModel;
    String texture;

    public RenderDemonKnife(BaseModelHammer baseModel, String texture)
    {
        this.baseModel = baseModel;
        this.texture = texture;
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        switch (type.ordinal())
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
        this.renderTypeNOUN(type, item, new Object[0]);
    }

    public void renderTypeNOUN(ItemRenderType type, ItemStack item, Object... data)
    {
        GL11.glPushMatrix();
        GL11.glRotatef(182.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(185.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(306.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-0.92F, -1.05F, -0.07F);
        Object ent = null;

        switch (type.ordinal())
        {
            case 1:
                GL11.glTranslatef(0.7F, 0.08F, 0.0F);
                float scale = 0.5F;
                GL11.glScalef(scale, scale, scale);
                break;

            case 2:
                GL11.glRotatef(-145.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(65.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(-0.9F, -0.6F, -0.7F);
                GL11.glScalef(0.5F, 0.7F, 0.5F);
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
        this.baseModel.render((Entity)ent, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    protected void ExtRender(EntityPlayer par1Entity, float par2, float par3, float par4, float par5, float par6, float par7, ExtendedPlayer pi)
    {
    }

    public void renders(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7, ItemStack item)
    {
        ExtendedPlayer pi = ExtendedPlayer.get((EntityPlayer)par1Entity);
        this.ExtRender((EntityPlayer)par1Entity, par2, par3, par4, par5, par6, par7, pi);
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

        if (item.getItem() instanceof BaseLezvie)
        {
            this.baseModel.oup = ((BaseLezvie)item.getItem()).getEnchantOther(item);
        }

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
}

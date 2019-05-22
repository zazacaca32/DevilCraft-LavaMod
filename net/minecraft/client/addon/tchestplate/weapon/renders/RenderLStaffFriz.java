package net.minecraft.client.addon.tchestplate.weapon.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelLStaffFriz;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class RenderLStaffFriz implements IItemRenderer
{
    protected ModelLStaffFriz baseModel = new ModelLStaffFriz();
    String texture;

    public RenderLStaffFriz(ModelLStaffFriz baseModel, String texture)
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
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 1.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-1.45F, 0.5F, -0.03F);

        switch (BaseRenderHammer.NamelessClass923090959.$SwitchMap$net$minecraftforge$client$ItemRenderType[type.ordinal()])
        {
            case 1:
                GL11.glTranslatef(0.7F, 0.08F, 0.0F);
                break;

            case 2:
                GL11.glTranslatef(0.7F, 0.2F, 0.0F);
        }

        float scale = 0.5F;
        GL11.glScalef(scale, scale, scale);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
        this.baseModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }
}

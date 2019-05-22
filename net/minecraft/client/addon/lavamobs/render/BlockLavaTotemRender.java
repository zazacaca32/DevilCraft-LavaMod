package net.minecraft.client.addon.lavamobs.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.lavamobs.model.ModelBlockTotem;
import net.minecraft.client.addon.lavamobs.tile.TileBlockLavaTotem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class BlockLavaTotemRender extends TileEntitySpecialRenderer implements IItemRenderer
{
    public static ModelBlockTotem model = new ModelBlockTotem();

    public void renderTileEntityAt(TileEntity tileEntity, double d1, double d2, double d3, float f)
    {
        this.renderTileEntity((TileBlockLavaTotem)tileEntity, d1, d2, d3, f);
    }

    public void renderTileEntity(TileBlockLavaTotem tileEntity, double d1, double d2, double d3, float f)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d1 + 0.5F, (float)d2 + 0.8F, (float)d3 + 0.5F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        int me = tileEntity.getBlockMetadata();

        switch (me)
        {
            case 0:
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                break;

            case 1:
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                break;

            case 2:
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
                break;

            case 3:
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
        }

        float scale = 1.0F;
        GL11.glScalef(scale, scale, scale);
        GL11.glTranslatef(0.0F, -0.7F, 0.0F);
        this.bindTextureByName("/mods/lavamobs/textures/blockraidboss.png");
        model.render();
        GL11.glPopMatrix();
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(0.5F, 0.2F, 0.5F);
        GL11.glScalef(0.4F, 0.4F, 0.4F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/blocks/blocktoten.png");
        model.render();
        GL11.glPopMatrix();
    }
}

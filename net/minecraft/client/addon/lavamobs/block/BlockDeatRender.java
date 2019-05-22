package net.minecraft.client.addon.lavamobs.block;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.lavamobs.model.ModelBlockDeat;
import net.minecraft.client.addon.lavamobs.tile.TileBlockDeat;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class BlockDeatRender extends TileEntitySpecialRenderer implements IItemRenderer
{
    public static ModelBlockDeat model = new ModelBlockDeat();

    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f)
    {
        this.renderTileEntity((TileBlockDeat)tileentity, d0, d1, d2, f);
    }

    public void renderTileEntity(TileBlockDeat tileEntity, double d1, double d2, double d3, float f)
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
        this.bindTextureByName("/mods/lavamobs/textures/blocksmert.png");
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
        GL11.glTranslatef(0.7F, 0.0F, 0.3F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/blocksmert.png");
        model.render();
        GL11.glPopMatrix();
    }
}

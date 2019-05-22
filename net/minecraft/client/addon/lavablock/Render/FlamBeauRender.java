package net.minecraft.client.addon.lavablock.Render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.lavablock.Models.FlamBeau;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class FlamBeauRender extends TileEntitySpecialRenderer implements IItemRenderer
{
    public static FlamBeau model = new FlamBeau();

    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f)
    {
        this.renderTileEntity(tileentity, d0, d1, d2, f);
    }

    public void renderTileEntity(TileEntity tileEntity, double d1, double d2, double d3, float f)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d1 + 0.5F, (float)d2 + 1.0F, (float)d3 + 0.5F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        int me = tileEntity.getBlockMetadata();

        switch (me)
        {
            case 2:
                GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
                break;

            case 3:
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                break;

            case 4:
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                break;

            case 5:
                GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
        }

        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        this.bindTextureByName("/mods/lavablock/textures/models/FlamBeau.png");
        model.render(Minecraft.getMinecraft().theWorld.getWorldTime());
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
        GL11.glTranslatef(0.5F, 0.9F, 0.5F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(0.9F, 0.9F, 0.9F);
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavablock/textures/models/FlamBeau.png");
        model.render(Minecraft.getMinecraft().theWorld.getWorldTime());
        GL11.glPopMatrix();
    }
}

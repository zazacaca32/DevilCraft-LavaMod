package net.minecraft.client.addon.lavablock.Render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.lavablock.Models.Lock;
import net.minecraft.client.addon.lavablock.Tile.GatelockTileBlock;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class GatelockBlockRender extends TileEntitySpecialRenderer implements IItemRenderer
{
    public static Lock model = new Lock();

    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f)
    {
        this.renderTileEntity((GatelockTileBlock)tileentity, d0, d1, d2, f);
    }

    public void renderTileEntity(GatelockTileBlock tileEntity, double d1, double d2, double d3, float f)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d1 + 0.5F, (float)d2 + 1.5F, (float)d3 + 0.5F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        int me = tileEntity.getBlockMetadata();

        switch (me)
        {
            case 0:
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                this.bindTextureByName("/mods/lavablock/textures/models/Lock.png");
                break;

            case 1:
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
                this.bindTextureByName("/mods/lavablock/textures/models/Lock.png");
                break;

            case 2:
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                this.bindTextureByName("/mods/lavablock/textures/models/Lock.png");
                break;

            case 3:
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                this.bindTextureByName("/mods/lavablock/textures/models/Lock.png");
                break;

            case 4:
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                this.bindTextureByName("/mods/lavablock/textures/models/Lock1.png");
                break;

            case 5:
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
                this.bindTextureByName("/mods/lavablock/textures/models/Lock1.png");
                break;

            case 6:
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                this.bindTextureByName("/mods/lavablock/textures/models/Lock1.png");
                break;

            case 7:
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                this.bindTextureByName("/mods/lavablock/textures/models/Lock1.png");
                break;

            case 8:
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                this.bindTextureByName("/mods/lavablock/textures/models/Lock2.png");
                break;

            case 9:
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
                this.bindTextureByName("/mods/lavablock/textures/models/Lock2.png");
                break;

            case 10:
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                this.bindTextureByName("/mods/lavablock/textures/models/Lock2.png");
                break;

            case 11:
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                this.bindTextureByName("/mods/lavablock/textures/models/Lock2.png");
        }

        model.render();

        if (me < 3)
        {
            ;
        }

        if (me < 6 && me > 2)
        {
            ;
        }

        if (me < 9 && me > 5)
        {
            ;
        }

        if (me < 12 && me > 8)
        {
            ;
        }

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
        GL11.glTranslatef(0.3F, 1.4F, 0.7F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.2F, 1.2F, 1.2F);
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavablock/textures/models/Lock.png");
        model.render();
        GL11.glPopMatrix();
    }
}

package net.minecraft.client.addon.lavablock.Render;

import net.minecraft.client.addon.lavablock.Models.Divan;
import net.minecraft.client.addon.lavablock.Tile.DivanTileBlock;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class DivanBlockRender extends TileEntitySpecialRenderer
{
    public static Divan modeld_1 = new Divan(1);
    public static Divan modeld_2 = new Divan(2);
    public static Divan modeld_3 = new Divan(3);
    public static Divan modeld_4 = new Divan(4);

    public void renderTileEntityAt(TileEntity tileEntity, double d1, double d2, double d3, float f)
    {
        this.renderTileEntity((DivanTileBlock)tileEntity, d1, d2, d3, f);
    }

    public void renderTileEntity(DivanTileBlock tileEntity, double d1, double d2, double d3, float f)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d1 + 0.5F, (float)d2 + 1.5F, (float)d3 + 0.5F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        int premeta = tileEntity.getBlockMetadata();
        int meta = premeta & 3;
        int metar = premeta / 4;
        this.bindTextureByName("/mods/lavablock/textures/models/Divan.png");

        if (tileEntity != null)
        {
            switch (meta)
            {
                case 0:
                    GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                    break;

                case 1:
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    break;

                case 2:
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    break;

                case 3:
                    GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            }

            switch (metar)
            {
                case 0:
                    modeld_1.render();
                    break;

                case 1:
                    modeld_2.render();
                    break;

                case 2:
                    modeld_3.render();
                    break;

                case 3:
                    modeld_4.render();
            }

            GL11.glPopMatrix();
        }
    }
}

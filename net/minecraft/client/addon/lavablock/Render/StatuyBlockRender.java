package net.minecraft.client.addon.lavablock.Render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.lavablock.Models.Alien;
import net.minecraft.client.addon.lavablock.Models.Gargoyl;
import net.minecraft.client.addon.lavablock.Models.Goblin;
import net.minecraft.client.addon.lavablock.Models.Tigr;
import net.minecraft.client.addon.lavablock.Tile.StatuyTileBlock;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class StatuyBlockRender extends TileEntitySpecialRenderer implements IItemRenderer
{
    public static Tigr model_1 = new Tigr();
    public static Goblin model_2 = new Goblin();
    public static Gargoyl model_3 = new Gargoyl();
    public static Alien model_4 = new Alien();

    public void renderTileEntityAt(TileEntity tileEntity, double d1, double d2, double d3, float f)
    {
        this.renderTileEntity((StatuyTileBlock)tileEntity, d1, d2, d3, f);
    }

    public void renderTileEntity(StatuyTileBlock tileEntity, double d1, double d2, double d3, float f)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d1 + 0.5F, (float)d2 + 1.5F, (float)d3 + 0.5F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        int me = tileEntity.getBlockMetadata();
        int meta = me / 4;
        int r_meta = me & 3;

        if (tileEntity != null)
        {
            switch (meta)
            {
                case 0:
                    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef((float)(r_meta * -90), 0.0F, 1.0F, 0.0F);
                    this.bindTextureByName("/mods/lavablock/textures/models/Tigr.png");
                    model_1.render();
                    break;

                case 1:
                    GL11.glRotatef((float)(r_meta * -90), 0.0F, 1.0F, 0.0F);
                    this.bindTextureByName("/mods/lavablock/textures/models/Goblin.png");
                    model_2.render();
                    break;

                case 2:
                    GL11.glRotatef((float)(r_meta * -90), 0.0F, 1.0F, 0.0F);
                    this.bindTextureByName("/mods/lavablock/textures/models/Gargoyl.png");
                    model_3.render();
                    break;

                case 3:
                    GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef((float)(r_meta * -90), 0.0F, 1.0F, 0.0F);
                    this.bindTextureByName("/mods/lavablock/textures/models/Alien.png");
                    model_4.render();
            }

            GL11.glPopMatrix();
        }
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
        GL11.glTranslatef(0.5F, 0.8F, 0.5F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glScalef(0.6F, 0.6F, 0.6F);

        switch (item.getItemDamage())
        {
            case 0:
                Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavablock/textures/models/Tigr.png");
                model_1.render();
                break;

            case 1:
                Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavablock/textures/models/Goblin.png");
                model_2.render();
                break;

            case 2:
                Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavablock/textures/models/Gargoyl.png");
                model_3.render();
                break;

            case 3:
                Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavablock/textures/models/Alien.png");
                model_4.render();
        }

        GL11.glPopMatrix();
    }
}

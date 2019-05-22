package net.minecraft.client.addon.lavamobs.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.lavamobs.modelblock.ModelBlockFlySkeleton;
import net.minecraft.client.addon.lavamobs.modelblock.ModelBlockGuardRaid;
import net.minecraft.client.addon.lavamobs.modelblock.ModelBlockMonkey;
import net.minecraft.client.addon.lavamobs.modelblock.ModelBlockWolf;
import net.minecraft.client.addon.lavamobs.tile.TileBlockScarecrow;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class BlockScarecrowRender extends TileEntitySpecialRenderer implements IItemRenderer
{
    public static ModelBlockWolf model_1 = new ModelBlockWolf();
    public static ModelBlockFlySkeleton model_2 = new ModelBlockFlySkeleton();
    public static ModelBlockMonkey model_3 = new ModelBlockMonkey();
    public static ModelBlockGuardRaid model_4 = new ModelBlockGuardRaid();

    public void renderTileEntityAt(TileEntity tileEntity, double d1, double d2, double d3, float f)
    {
        this.renderTileEntity((TileBlockScarecrow)tileEntity, d1, d2, d3, f);
    }

    public void renderTileEntity(TileBlockScarecrow tileEntity, double d1, double d2, double d3, float f)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d1 + 0.5F, (float)d2 + 1.5F, (float)d3 + 0.5F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        int me = tileEntity.getBlockMetadata();
        int meta = me / 4;
        int r_meta = me & 3;

        if (tileEntity != null)
        {
            GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);

            switch (meta)
            {
                case 0:
                    GL11.glRotatef((float)(r_meta * -90), 0.0F, 1.0F, 0.0F);
                    this.bindTextureByName("/mods/lavamobs/textures/wolf.png");
                    model_1.render();
                    break;

                case 1:
                    GL11.glRotatef((float)(r_meta * -90), 0.0F, 1.0F, 0.0F);
                    this.bindTextureByName("/mods/lavamobs/textures/flyskeleton.png");
                    model_2.render();
                    break;

                case 2:
                    GL11.glRotatef((float)(r_meta * -90), 0.0F, 1.0F, 0.0F);
                    this.bindTextureByName("/mods/lavamobs/textures/monkey.png");
                    model_3.render();
                    break;

                case 3:
                    GL11.glRotatef((float)(r_meta * -90), 0.0F, 1.0F, 0.0F);
                    this.bindTextureByName("/mods/lavamobs/textures/rbguard.png");
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
        GL11.glTranslatef(0.6F, 1.1F, 0.4F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(0.9F, 0.9F, 0.9F);

        switch (item.getItemDamage())
        {
            case 0:
                Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/wolf.png");
                model_1.render();
                break;

            case 1:
                GL11.glTranslatef(-0.6F, 1.0F, 0.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/flyskeleton.png");
                model_2.render();
                break;

            case 2:
                GL11.glScalef(0.8F, 0.8F, 0.8F);
                Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/monkey.png");
                model_3.render();
                break;

            case 3:
                GL11.glTranslatef(-0.2F, -0.3F, 0.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavamobs/textures/rbguard.png");
                model_4.render();
        }

        GL11.glPopMatrix();
    }
}

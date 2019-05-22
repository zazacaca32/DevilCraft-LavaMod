package net.minecraft.client.addon.lavablock.Render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.addon.lavablock.ModBlocks;
import net.minecraft.client.addon.lavablock.Block.BlockColorPane;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class BlockColorPaneRender implements ISimpleBlockRenderingHandler
{
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        Icon icon = block.getIcon(0, metadata);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Tessellator tessellator = Tessellator.instance;
        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.4F, 0.05F, 1.4F);
        renderer.setRenderBoundsFromBlock(block);

        if (EntityRenderer.anaglyphEnable)
        {
            ;
        }

        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
        GL11.glTranslatef(-0.72F, 0.35F, -0.6F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        renderer.setRenderBoundsFromBlock(block);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public boolean renderWorldBlock(IBlockAccess world, int par2, int par3, int par4, Block par1, int modelId, RenderBlocks renderer)
    {
        BlockColorPane par1BlockPane = (BlockColorPane)par1;
        int var5 = renderer.blockAccess.getHeight();
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(par1BlockPane.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3, par4));
        int var7 = par1BlockPane.colorMultiplier(renderer.blockAccess, par2, par3, par4);
        float var8 = (float)(var7 >> 16 & 255) / 255.0F;
        float var9 = (float)(var7 >> 8 & 255) / 255.0F;
        float var10 = (float)(var7 & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float var1411 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
            float var6511 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
            float var6611 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
            var8 = var1411;
            var9 = var6511;
            var10 = var6611;
        }

        tessellator.setColorOpaque_F(var8, var9, var10);

        if (renderer.hasOverrideBlockTexture())
        {
            ;
        }

        int var14111 = renderer.blockAccess.getBlockMetadata(par2, par3, par4);
        Icon var65111 = renderer.getBlockIconFromSideAndMetadata(par1BlockPane, 0, var14111);
        Icon var66111 = par1BlockPane.getSideTextureIndex();
        double var68 = (double)var65111.getMinU();
        double var16 = (double)var65111.getInterpolatedU(7.0D);
        double var18 = (double)var65111.getInterpolatedU(9.0D);
        double var20 = (double)var65111.getMaxU();
        double var22 = (double)var65111.getMinV();
        double var24 = (double)var65111.getMaxV();
        double var26 = (double)var66111.getInterpolatedU(7.0D);
        double var28 = (double)var66111.getInterpolatedU(9.0D);
        double var30 = (double)var66111.getMinV();
        double var32 = (double)var66111.getMaxV();
        double var34 = (double)var66111.getInterpolatedV(7.0D);
        double var36 = (double)var66111.getInterpolatedV(9.0D);
        double var38 = (double)par2;
        double var40 = (double)(par2 + 1);
        double var42 = (double)par4;
        double var44 = (double)(par4 + 1);
        double var46 = (double)par2 + 0.5D - 0.0625D;
        double var48 = (double)par2 + 0.5D + 0.0625D;
        double var50 = (double)par4 + 0.5D - 0.0625D;
        double var52 = (double)par4 + 0.5D + 0.0625D;
        boolean var67 = par1BlockPane instanceof BlockColorPane;
        boolean var54 = par1BlockPane.canPaneConnectTo(renderer.blockAccess, par2, par3, par4, ForgeDirection.NORTH);
        boolean var55 = par1BlockPane.canPaneConnectTo(renderer.blockAccess, par2, par3, par4, ForgeDirection.SOUTH);
        boolean var56 = par1BlockPane.canPaneConnectTo(renderer.blockAccess, par2, par3, par4, ForgeDirection.WEST);
        boolean var57 = par1BlockPane.canPaneConnectTo(renderer.blockAccess, par2, par3, par4, ForgeDirection.EAST);
        boolean var64 = !var54 && !var55 && !var56 && !var57;
        double var58 = 0.001D;
        double var60 = 0.999D;
        double var62 = 0.001D;

        if (!var56 && !var64)
        {
            if (!var54 && !var55)
            {
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var50, var16, var22);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var50, var16, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var52, var18, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var52, var18, var22);
            }
        }
        else if (var56 && var57)
        {
            if (!var54)
            {
                tessellator.addVertexWithUV(var40, (double)par3 + 0.999D, var50, var20, var22);
                tessellator.addVertexWithUV(var40, (double)par3 + 0.001D, var50, var20, var24);
                tessellator.addVertexWithUV(var38, (double)par3 + 0.001D, var50, var68, var24);
                tessellator.addVertexWithUV(var38, (double)par3 + 0.999D, var50, var68, var22);
            }
            else
            {
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var50, var16, var22);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var50, var16, var24);
                tessellator.addVertexWithUV(var38, (double)par3 + 0.001D, var50, var68, var24);
                tessellator.addVertexWithUV(var38, (double)par3 + 0.999D, var50, var68, var22);
                tessellator.addVertexWithUV(var40, (double)par3 + 0.999D, var50, var20, var22);
                tessellator.addVertexWithUV(var40, (double)par3 + 0.001D, var50, var20, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var50, var18, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var50, var18, var22);
            }

            if (!var55)
            {
                tessellator.addVertexWithUV(var38, (double)par3 + 0.999D, var52, var68, var22);
                tessellator.addVertexWithUV(var38, (double)par3 + 0.001D, var52, var68, var24);
                tessellator.addVertexWithUV(var40, (double)par3 + 0.001D, var52, var20, var24);
                tessellator.addVertexWithUV(var40, (double)par3 + 0.999D, var52, var20, var22);
            }
            else
            {
                tessellator.addVertexWithUV(var38, (double)par3 + 0.999D, var52, var68, var22);
                tessellator.addVertexWithUV(var38, (double)par3 + 0.001D, var52, var68, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var52, var16, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var52, var16, var22);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var52, var18, var22);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var52, var18, var24);
                tessellator.addVertexWithUV(var40, (double)par3 + 0.001D, var52, var20, var24);
                tessellator.addVertexWithUV(var40, (double)par3 + 0.999D, var52, var20, var22);
            }

            tessellator.addVertexWithUV(var38, (double)par3 + 0.999D, var52, var28, var30);
            tessellator.addVertexWithUV(var40, (double)par3 + 0.999D, var52, var28, var32);
            tessellator.addVertexWithUV(var40, (double)par3 + 0.999D, var50, var26, var32);
            tessellator.addVertexWithUV(var38, (double)par3 + 0.999D, var50, var26, var30);
            tessellator.addVertexWithUV(var40, (double)par3 + 0.001D, var52, var26, var32);
            tessellator.addVertexWithUV(var38, (double)par3 + 0.001D, var52, var26, var30);
            tessellator.addVertexWithUV(var38, (double)par3 + 0.001D, var50, var28, var30);
            tessellator.addVertexWithUV(var40, (double)par3 + 0.001D, var50, var28, var32);
        }
        else
        {
            if (!var54 && !var64)
            {
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var50, var18, var22);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var50, var18, var24);
                tessellator.addVertexWithUV(var38, (double)par3 + 0.001D, var50, var68, var24);
                tessellator.addVertexWithUV(var38, (double)par3 + 0.999D, var50, var68, var22);
            }
            else
            {
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var50, var16, var22);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var50, var16, var24);
                tessellator.addVertexWithUV(var38, (double)par3 + 0.001D, var50, var68, var24);
                tessellator.addVertexWithUV(var38, (double)par3 + 0.999D, var50, var68, var22);
            }

            if (!var55 && !var64)
            {
                tessellator.addVertexWithUV(var38, (double)par3 + 0.999D, var52, var68, var22);
                tessellator.addVertexWithUV(var38, (double)par3 + 0.001D, var52, var68, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var52, var18, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var52, var18, var22);
            }
            else
            {
                tessellator.addVertexWithUV(var38, (double)par3 + 0.999D, var52, var68, var22);
                tessellator.addVertexWithUV(var38, (double)par3 + 0.001D, var52, var68, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var52, var16, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var52, var16, var22);
            }

            tessellator.addVertexWithUV(var38, (double)par3 + 0.999D, var52, var28, var30);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var52, var28, var34);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var50, var26, var34);
            tessellator.addVertexWithUV(var38, (double)par3 + 0.999D, var50, var26, var30);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var52, var26, var34);
            tessellator.addVertexWithUV(var38, (double)par3 + 0.001D, var52, var26, var30);
            tessellator.addVertexWithUV(var38, (double)par3 + 0.001D, var50, var28, var30);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var50, var28, var34);
        }

        if ((var57 || var64) && !var56)
        {
            if (!var55 && !var64)
            {
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var52, var16, var22);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var52, var16, var24);
                tessellator.addVertexWithUV(var40, (double)par3 + 0.001D, var52, var20, var24);
                tessellator.addVertexWithUV(var40, (double)par3 + 0.999D, var52, var20, var22);
            }
            else
            {
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var52, var18, var22);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var52, var18, var24);
                tessellator.addVertexWithUV(var40, (double)par3 + 0.001D, var52, var20, var24);
                tessellator.addVertexWithUV(var40, (double)par3 + 0.999D, var52, var20, var22);
            }

            if (!var54 && !var64)
            {
                tessellator.addVertexWithUV(var40, (double)par3 + 0.999D, var50, var20, var22);
                tessellator.addVertexWithUV(var40, (double)par3 + 0.001D, var50, var20, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var50, var16, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var50, var16, var22);
            }
            else
            {
                tessellator.addVertexWithUV(var40, (double)par3 + 0.999D, var50, var20, var22);
                tessellator.addVertexWithUV(var40, (double)par3 + 0.001D, var50, var20, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var50, var18, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var50, var18, var22);
            }

            tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var52, var28, var36);
            tessellator.addVertexWithUV(var40, (double)par3 + 0.999D, var52, var28, var30);
            tessellator.addVertexWithUV(var40, (double)par3 + 0.999D, var50, var26, var30);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var50, var26, var36);
            tessellator.addVertexWithUV(var40, (double)par3 + 0.001D, var52, var26, var32);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var52, var26, var36);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var50, var28, var36);
            tessellator.addVertexWithUV(var40, (double)par3 + 0.001D, var50, var28, var32);
        }
        else if (!var57 && !var54 && !var55)
        {
            tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var52, var16, var22);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var52, var16, var24);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var50, var18, var24);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var50, var18, var22);
        }

        if (!var54 && !var64)
        {
            if (!var57 && !var56)
            {
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var50, var18, var22);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var50, var18, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var50, var16, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var50, var16, var22);
            }
        }
        else if (var54 && var55)
        {
            if (!var56)
            {
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var42, var68, var22);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var42, var68, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var44, var20, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var44, var20, var22);
            }
            else
            {
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var42, var68, var22);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var42, var68, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var50, var16, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var50, var16, var22);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var52, var18, var22);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var52, var18, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var44, var20, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var44, var20, var22);
            }

            if (!var57)
            {
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var44, var20, var22);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var44, var20, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var42, var68, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var42, var68, var22);
            }
            else
            {
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var50, var16, var22);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var50, var16, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var42, var68, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var42, var68, var22);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var44, var20, var22);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var44, var20, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var52, var18, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var52, var18, var22);
            }

            tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var42, var28, var30);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var42, var26, var30);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var44, var26, var32);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var44, var28, var32);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var42, var26, var30);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var42, var28, var30);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var44, var28, var32);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var44, var26, var32);
        }
        else
        {
            if (!var56 && !var64)
            {
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var42, var68, var22);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var42, var68, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var52, var18, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var52, var18, var22);
            }
            else
            {
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var42, var68, var22);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var42, var68, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var50, var16, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var50, var16, var22);
            }

            if (!var57 && !var64)
            {
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var52, var18, var22);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var52, var18, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var42, var68, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var42, var68, var22);
            }
            else
            {
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var50, var16, var22);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var50, var16, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var42, var68, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var42, var68, var22);
            }

            tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var42, var28, var30);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var42, var26, var30);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var50, var26, var34);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var50, var28, var34);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var42, var26, var30);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var42, var28, var30);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var50, var28, var34);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var50, var26, var34);
        }

        if ((var55 || var64) && !var54)
        {
            if (!var56 && !var64)
            {
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var50, var16, var22);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var50, var16, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var44, var20, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var44, var20, var22);
            }
            else
            {
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var52, var18, var22);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var52, var18, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var44, var20, var24);
                tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var44, var20, var22);
            }

            if (!var57 && !var64)
            {
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var44, var20, var22);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var44, var20, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var50, var16, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var50, var16, var22);
            }
            else
            {
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var44, var20, var22);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var44, var20, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var52, var18, var24);
                tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var52, var18, var22);
            }

            tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var52, var28, var36);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var52, var26, var36);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var44, var26, var32);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var44, var28, var32);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var52, var26, var36);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var52, var28, var36);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var44, var28, var32);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var44, var26, var32);
        }
        else if (!var55 && !var57 && !var56)
        {
            tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var52, var16, var22);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var52, var16, var24);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var52, var18, var24);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var52, var18, var22);
        }

        tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var50, var28, var34);
        tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var50, var26, var34);
        tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var52, var26, var36);
        tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var52, var28, var36);
        tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var50, var26, var34);
        tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var50, var28, var34);
        tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var52, var28, var36);
        tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var52, var26, var36);

        if (var64)
        {
            tessellator.addVertexWithUV(var38, (double)par3 + 0.999D, var50, var16, var22);
            tessellator.addVertexWithUV(var38, (double)par3 + 0.001D, var50, var16, var24);
            tessellator.addVertexWithUV(var38, (double)par3 + 0.001D, var52, var18, var24);
            tessellator.addVertexWithUV(var38, (double)par3 + 0.999D, var52, var18, var22);
            tessellator.addVertexWithUV(var40, (double)par3 + 0.999D, var52, var16, var22);
            tessellator.addVertexWithUV(var40, (double)par3 + 0.001D, var52, var16, var24);
            tessellator.addVertexWithUV(var40, (double)par3 + 0.001D, var50, var18, var24);
            tessellator.addVertexWithUV(var40, (double)par3 + 0.999D, var50, var18, var22);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var42, var18, var22);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var42, var18, var24);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var42, var16, var24);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var42, var16, var22);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.999D, var44, var16, var22);
            tessellator.addVertexWithUV(var46, (double)par3 + 0.001D, var44, var16, var24);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.001D, var44, var18, var24);
            tessellator.addVertexWithUV(var48, (double)par3 + 0.999D, var44, var18, var22);
        }

        return true;
    }

    public boolean shouldRender3DInInventory()
    {
        return true;
    }

    public int getRenderId()
    {
        return ModBlocks.render_id;
    }
}

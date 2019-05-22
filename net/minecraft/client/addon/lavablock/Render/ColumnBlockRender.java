package net.minecraft.client.addon.lavablock.Render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.lavablock.Block.BlockColumn;
import net.minecraft.client.addon.lavablock.Models.ColumnModelB;
import net.minecraft.client.addon.lavablock.Models.ColumnModelD;
import net.minecraft.client.addon.lavablock.Models.ColumnModelG;
import net.minecraft.client.addon.lavablock.Models.ColumnModelW;
import net.minecraft.client.addon.lavablock.Tile.ColumnTileBlock;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class ColumnBlockRender extends TileEntitySpecialRenderer implements IItemRenderer
{
    public static ColumnModelW model1 = new ColumnModelW();
    public static ColumnModelB model2 = new ColumnModelB();
    public static ColumnModelG model3 = new ColumnModelG();
    public static ColumnModelD model4 = new ColumnModelD();

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
        GL11.glTranslatef(0.5F, 1.5F, 0.5F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F, 1.0F, 1.0F);
        int me = item.getItemDamage();
        int id = item.itemID;

        switch (id)
        {
            case 2584:
                Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavablock/textures/blocks/colon_quartz.png");
                break;

            case 2585:
                Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavablock/textures/blocks/colon_blackquartz.png");
                break;

            case 2586:
                Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavablock/textures/blocks/colon_gold.png");
                break;

            case 2587:
                Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavablock/textures/blocks/colon_diamond.png");
        }

        switch (me)
        {
            case 0:
                model1.render(true, false);
                break;

            case 1:
                model2.render(true, false);
                break;

            case 2:
                model3.render(true, false);
                break;

            case 3:
                model4.render(true, false);
        }

        GL11.glPopMatrix();
    }

    public void renderTileEntity(ColumnTileBlock tileEntity, double d1, double d2, double d3, float f)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d1 + 0.5F, (float)d2 + 1.5F, (float)d3 + 0.5F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        this.bindTextureByName("/mods/lavablock/textures/models/shbomj.png");
        int me = tileEntity.getBlockMetadata();
        int id = tileEntity.getBlockType().blockID;
        boolean flag = false;
        boolean flag2 = false;
        Block colo = tileEntity.blockType;

        if (colo instanceof BlockColumn)
        {
            flag = ((BlockColumn)colo).canPaneConnectToUp(tileEntity.getWorldObj(), tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
            flag2 = ((BlockColumn)colo).canPaneConnectToDown(tileEntity.getWorldObj(), tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
        }

        switch (id)
        {
            case 2584:
                this.bindTextureByName("/mods/lavablock/textures/blocks/colon_quartz.png");
                break;

            case 2585:
                this.bindTextureByName("/mods/lavablock/textures/blocks/colon_blackquartz.png");
                break;

            case 2586:
                this.bindTextureByName("/mods/lavablock/textures/blocks/colon_gold.png");
                break;

            case 2587:
                this.bindTextureByName("/mods/lavablock/textures/blocks/colon_diamond.png");
        }

        switch (me)
        {
            case 0:
                model1.render(flag, flag2);
                break;

            case 1:
                model2.render(flag, flag2);
                break;

            case 2:
                model3.render(flag, flag2);
                break;

            case 3:
                model4.render(flag, flag2);
        }

        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f)
    {
        this.renderTileEntity((ColumnTileBlock)tileentity, d0, d1, d2, f);
    }
}

package net.minecraft.client.addon.lavablock.Gui;

import net.minecraft.client.addon.lavablock.Container.ContainerElka;
import net.minecraft.client.addon.lavablock.Tile.ElkaTileBlock;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;

public class GuiElka extends GuiContainer
{
    public ElkaTileBlock tileEntity;

    public GuiElka(ElkaTileBlock tileEntity, EntityPlayer player)
    {
        super(new ContainerElka(tileEntity, player));
        this.tileEntity = tileEntity;
        super.xSize = 176;
        super.ySize = 209;
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        super.mc.renderEngine.bindTexture("/mods/lavablock/textures/gui/GuiElka.png");
        int x = (super.width - super.xSize) / 2;
        int y = (super.height - super.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, super.xSize, super.ySize);
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        super.fontRenderer.drawString("" + this.tileEntity.time_s / 6 + "%", 100, 5, 16763904);
        super.fontRenderer.drawString("" + this.tileEntity.count_p, 76, 64, 0);
        super.fontRenderer.drawString("" + this.tileEntity.count_o, 76, 76, 0);
        super.fontRenderer.drawString("" + this.tileEntity.count_b, 76, 88, 0);
    }
}

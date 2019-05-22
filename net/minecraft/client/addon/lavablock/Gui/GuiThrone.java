package net.minecraft.client.addon.lavablock.Gui;

import net.minecraft.client.addon.lavablock.Container.ContainerThrone;
import net.minecraft.client.addon.lavablock.Tile.ThroneTileBlock;
import net.minecraft.client.gui.inventory.GuiContainer;

public class GuiThrone extends GuiContainer
{
    public ThroneTileBlock tileEntity;

    public GuiThrone(ThroneTileBlock tileEntity)
    {
        super(new ContainerThrone(tileEntity));
        this.tileEntity = tileEntity;
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        super.mc.renderEngine.bindTexture("/mods/lavablock/textures/gui/GuiThrone.png");
        int x = (super.width - super.xSize) / 2;
        int y = (super.height - super.ySize) / 2;
        int t = (int)((double)this.tileEntity.process_status * 0.71D);
        this.drawTexturedModalRect(x, y + 55, 0, 0, super.xSize, 75);
        this.drawTexturedModalRect(53 + x, 22 + y + 55, 176, 0, t, 5);
    }
}

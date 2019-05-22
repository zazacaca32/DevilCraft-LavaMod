package net.minecraft.client.addon.lavamobs.gui;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.addon.lavamobs.container.ContainerLavaTotem;
import net.minecraft.client.addon.lavamobs.packets.PacketMAUpdateInt;
import net.minecraft.client.addon.lavamobs.tile.TileBlockLavaTotem;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class GuiLavaTotem extends GuiContainer
{
    public TileBlockLavaTotem tileEntity;
    private EntityPlayer player;

    public GuiLavaTotem(TileBlockLavaTotem tileEntity, EntityPlayer player)
    {
        super(new ContainerLavaTotem(tileEntity, player));
        this.tileEntity = tileEntity;
        this.player = player;
        super.xSize = 176;
        super.ySize = 172;
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        super.mc.renderEngine.bindTexture("/mods/lavamobs/textures/Gui_Event.png");
        int x = (super.width - super.xSize) / 2;
        int y = (super.height - super.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, super.xSize, super.ySize);
    }

    public void drawGradientRect(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        if (par5 != -2130706433)
        {
            super.drawGradientRect(par1, par2, par3, par4, par5, par6);
        }
    }

    public void initGui()
    {
        super.initGui();

        for (int i = 0; i < 5; ++i)
        {
            super.buttonList.add(new GuiButton(i, (super.width - super.xSize) / 2 + 90, (super.height - super.ySize) / 2 + 40 + i * 25, 74, 20, "Начать квест"));
        }
    }

    protected void actionPerformed(GuiButton par1GuiButton)
    {
        PacketDispatcher.sendPacketToServer((new PacketMAUpdateInt((byte)3, par1GuiButton.id)).makePacket());
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        super.fontRenderer.drawString("Квесты катакомб", 45, 25, 6771769);

        for (int i = 0; i < 5; ++i)
        {
            super.fontRenderer.drawString("Награда -", 15, 44 + i * 25, 6771769);
        }
    }
}

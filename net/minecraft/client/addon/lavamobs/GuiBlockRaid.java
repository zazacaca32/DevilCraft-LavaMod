package net.minecraft.client.addon.lavamobs;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.lavamobs.packets.PacketMAUpdateInt;
import net.minecraft.client.addon.lavamobs.tile.TileBlockRaid;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;

public class GuiBlockRaid extends GuiContainer
{
    public TileBlockRaid tileEntity;
    private GuiTextArea textArea;
    public boolean flag = false;
    private EntityPlayer player;

    public GuiBlockRaid(TileBlockRaid tileEntity, EntityPlayer player)
    {
        super(new ContainerBlockRaid(tileEntity));
        this.tileEntity = tileEntity;
        this.flag = player.capabilities.isCreativeMode;
        this.player = player;
        super.xSize = 176;
        super.ySize = 80;
    }

    public void initGui()
    {
        super.initGui();
        super.buttonList.clear();
        GuiButton button = new GuiButton(1, super.guiLeft + 55, super.guiTop + 50, 66, 20, "Применить");
        button.drawButton = this.flag;
        super.buttonList.add(button);
        this.textArea = new GuiTextArea(super.fontRenderer, super.guiLeft + 55, super.guiTop + 30, 66, 14, 1);
        this.textArea.maxStringLength = 42;
        this.textArea.setFocused(true);
    }

    protected void mouseClicked(int x, int y, int par3)
    {
        super.mouseClicked(x, y, par3);

        if (this.textArea != null)
        {
            this.textArea.mouseClicked(x, y, par3);
        }
    }

    protected void keyTyped(char par1, int par2)
    {
        if (par2 != 1 && (par2 != super.mc.gameSettings.keyBindInventory.keyCode || this.textArea != null && this.textArea.isFocused()))
        {
            if (this.textArea != null && this.textArea.isFocused())
            {
                this.textArea.textAreaKeyTyped(par1, par2);
                return;
            }
        }
        else
        {
            this.actionPerformed((GuiButton)null);
        }

        super.keyTyped(par1, par2);
    }

    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton != null && par1GuiButton.id == 1)
        {
            int tim = Integer.parseInt(this.textArea.getText()[0]);
            PacketDispatcher.sendPacketToServer((new PacketMAUpdateInt((byte)1, tim)).makePacket());
        }

        Minecraft.getMinecraft().thePlayer.closeScreen();
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        super.mc.renderEngine.bindTexture("/mods/lavamobs/textures/rbgui.png");
        int x = (super.width - super.xSize) / 2;
        int y = (super.height - super.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, super.xSize, super.ySize);

        if (this.flag && this.textArea != null)
        {
            this.textArea.drawTextBox();
        }
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String t = this.gettexttime(this.tileEntity.custom_time);
        super.fontRenderer.drawString("Lava Raid Boss", 50, 5, 16763904);
        super.fontRenderer.drawString("Респавн через " + t, 16, 18, 16763904);
    }

    public String gettexttime(int min)
    {
        long s = (long)(min * 60 + 60);
        Long day = Long.valueOf(s / 86400L);
        Long hours = Long.valueOf(s / 3600L - day.longValue() * 24L);
        Long minutes = Long.valueOf(s % 3600L / 60L);
        return day + "Д " + hours + "ч " + minutes + "мин ";
    }
}

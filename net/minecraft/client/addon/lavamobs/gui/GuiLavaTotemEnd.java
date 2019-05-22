package net.minecraft.client.addon.lavamobs.gui;

import cpw.mods.fml.common.network.PacketDispatcher;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.addon.lavamobs.EventMobCount;
import net.minecraft.client.addon.lavamobs.container.ContainerLavaTotemEnd;
import net.minecraft.client.addon.lavamobs.packets.PacketMAUpdateInt;
import net.minecraft.client.addon.lavamobs.tile.TileBlockLavaTotem;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class GuiLavaTotemEnd extends GuiContainer
{
    public TileBlockLavaTotem tileEntity;
    private EntityPlayer player;
    ContainerLavaTotemEnd con;
    List que = new ArrayList();
    String mobname;
    int count;

    public GuiLavaTotemEnd(TileBlockLavaTotem tileEntity, EntityPlayer player)
    {
        super(new ContainerLavaTotemEnd(tileEntity, player));
        this.con = (ContainerLavaTotemEnd)super.inventorySlots;
        this.tileEntity = tileEntity;
        this.player = player;
        super.xSize = 176;
        super.ySize = 212;
        EventMobCount t = EventMobCount.arrquest[this.con.idevent];
        this.mobname = EventMobCount.Mobname[this.con.idmob];
        this.count = this.con.mobcount;
        int s = 0;
        String ss = "";

        for (int i = 0; i < t.mobid.length; ++i)
        {
            String ss1 = EventMobCount.Mobname[t.mobid[i]] + "-" + t.count[i] + " ";

            if (ss.length() + ss1.length() > 27)
            {
                ++s;
                ss = "";
            }
            else
            {
                ss = ss + ss1;
            }

            if (ss.length() > 0)
            {
                if (this.que.size() > s)
                {
                    this.que.set(s, ss);
                }
                else
                {
                    this.que.add(ss);
                }
            }
        }
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        super.mc.renderEngine.bindTexture("/mods/lavamobs/textures/Gui_EventEnd.png");
        int x = (super.width - super.xSize) / 2;
        int y = (super.height - super.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, super.xSize, super.ySize);
    }

    public void initGui()
    {
        super.initGui();

        if (this.con.endQ)
        {
            super.buttonList.add(new GuiButton(2, (super.width - super.xSize) / 2 + 18, (super.height - super.ySize) / 2 + 86, 140, 20, "Получить награду квест"));
            this.que.clear();
        }
        else
        {
            super.buttonList.add(new GuiButton(1, (super.width - super.xSize) / 2 + 18, (super.height - super.ySize) / 2 + 104, 140, 20, "Отказаться от квеста"));
        }
    }

    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.id == 1)
        {
            PacketDispatcher.sendPacketToServer((new PacketMAUpdateInt((byte)4, 0)).makePacket());
        }
        else if (par1GuiButton.id == 2)
        {
            PacketDispatcher.sendPacketToServer((new PacketMAUpdateInt((byte)4, 1)).makePacket());
            this.con.endQ = false;
            super.buttonList.clear();
        }
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        super.fontRenderer.drawString("Информация по квесту", 28, 25, 6771769);

        if (this.con.endQ)
        {
            super.fontRenderer.drawString("Квест пройден!", 48, 48, 6771769);
        }
        else if (this.que.size() > 0)
        {
            super.fontRenderer.drawString("Текущее, ты убил: " + this.count + "-" + this.mobname, 15, 45, 6771769);
            super.fontRenderer.drawString("Задание квеста, убить:", 15, 60, 6771769);

            for (int i = 0; i < this.que.size(); ++i)
            {
                super.fontRenderer.drawString((String)this.que.get(i), 15, 70 + i * 10, 6771769);
            }
        }
    }
}

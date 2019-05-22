package net.minecraft.client.addon.tchestplate.donate.gui;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.donate.DonateManagerInv;
import net.minecraft.client.addon.tchestplate.donate.DonateStack;
import net.minecraft.client.addon.tchestplate.donate.containers.DonateAdmConfigContainer;
import net.minecraft.client.addon.tchestplate.donate.slots.SlotDonateAdm;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtInventory;
import net.minecraft.client.addon.tchestplate.packets.PacketMAOpenGui;
import net.minecraft.client.addon.tchestplate.packets.PacketMASendItemStackInSlotDonate;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class DonateAdmConfigGui extends GuiContainer
{
    public InventoryPlayer invv;
    public ArmorExtInventory holderInventory;
    private GuiTextArea[] textArea = new GuiTextArea[5];
    private int index;
    DonateManagerInv inv;

    public DonateAdmConfigGui(InventoryPlayer var1, ArmorExtInventory var2, int var3)
    {
        super(new DonateAdmConfigContainer(var1, var2, var3));
        this.index = var3;
        this.invv = var1;
        this.holderInventory = var2;
        super.xSize = 179;
        super.ySize = 248;
        this.inv = LavaChestPlate.instanceDonate;
    }

    public void initGui()
    {
        super.initGui();
        this.initControls();
    }

    private void initControls()
    {
        super.buttonList.clear();
        super.buttonList.add(new GuiButton(1, super.guiLeft + super.xSize - 60 - 8, super.guiTop + 220, 60, 20, "Ok"));
        boolean var1 = false;
        DonateStack var2 = this.inv.inv[this.index];

        if (var2 != null && var2.source != null)
        {
            var1 = true;
        }

        this.textArea[0] = new GuiTextArea(super.fontRenderer, super.guiLeft + 8, super.guiTop + 50, super.xSize - 19, 58, 5);
        this.textArea[0].maxStringLength = 42;
        this.textArea[0].setFocused(true);

        if (var1)
        {
            Iterator var3 = var2.getDescriptions().iterator();

            while (var3.hasNext())
            {
                String var4 = (String)var3.next();
                this.textArea[0].writeText(var4);
            }
        }

        this.textArea[1] = new GuiTextArea(super.fontRenderer, super.guiLeft + 8, super.guiTop + 123, 70, 15, 1);
        this.textArea[1].maxStringLength = 42;

        if (var1)
        {
            this.textArea[1].writeText(String.valueOf(var2.getPrice()));
        }

        this.textArea[2] = new GuiTextArea(super.fontRenderer, super.guiLeft + 8, super.guiTop + 153, 70, 15, 1);
        this.textArea[2].maxStringLength = 42;

        if (var1)
        {
            this.textArea[2].writeText(String.valueOf(var2.getDiscount()));
        }
    }

    protected void mouseClicked(int var1, int var2, int var3)
    {
        super.mouseClicked(var1, var2, var3);
        GuiTextArea[] var4 = this.textArea;
        int var5 = var4.length;

        for (int var6 = 0; var6 < var5; ++var6)
        {
            GuiTextArea var7 = var4[var6];

            if (var7 != null)
            {
                var7.mouseClicked(var1, var2, var3);
            }
        }
    }

    protected void actionPerformed(GuiButton var1)
    {
        if (var1 != null && var1.id == 1)
        {
            DonateStack var2 = this.inv.inv[this.index];

            if (var2 != null && var2.source != null)
            {
                ArrayList var3 = new ArrayList();
                var3.add(this.textArea[1].getText()[0]);
                var3.add(this.textArea[2].getText()[0]);
                var3.addAll(Arrays.asList(this.textArea[0].getText()));
                var2.addLoretoItemStack(var3);
                PacketDispatcher.sendPacketToServer((new PacketMASendItemStackInSlotDonate((byte)this.index, new Object[] {var2.source})).makePacket());
            }

            PacketDispatcher.sendPacketToServer((new PacketMAOpenGui(3001)).makePacket());
        }
    }

    public void drawScreen(int var1, int var2, float var3)
    {
        super.drawScreen(var1, var2, var3);
    }

    protected void keyTyped(char var1, int var2)
    {
        GuiTextArea[] var3 = this.textArea;
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5)
        {
            GuiTextArea var6 = var3[var5];

            if (var2 == 1 || var2 == super.mc.gameSettings.keyBindInventory.keyCode && (var6 == null || !var6.isFocused()))
            {
                this.actionPerformed((GuiButton)null);
                return;
            }

            if (var6 != null && var6.isFocused())
            {
                var6.textAreaKeyTyped(var1, var2);
                return;
            }
        }

        super.keyTyped(var1, var2);
    }

    protected void drawSlotInventory(Slot var1)
    {
        if (var1 instanceof SlotDonateAdm)
        {
            ;
        }

        super.drawSlotInventory(var1);
    }

    protected void drawGuiContainerForegroundLayer(int var1, int var2)
    {
        super.fontRenderer.drawString(String.valueOf("Настройки"), 63, 5, 15566638);
        super.fontRenderer.drawString(String.valueOf("Описание итема"), 8, 39, 15566638);
        super.fontRenderer.drawString(String.valueOf("Цена итема"), 8, 112, 15566638);
        super.fontRenderer.drawString(String.valueOf("Скидка в процентах"), 8, 142, 15566638);
    }

    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        super.mc.renderEngine.bindTexture("/mods/LavaChestPlate/textures/gui/donateadmconfig.png");
        this.drawTexturedModalRect(super.guiLeft, super.guiTop, 0, 0, super.xSize, super.ySize);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int var4 = (super.width - super.xSize) / 2;
        int var5 = (super.height - super.ySize) / 2;
        this.drawTexturedModalRect(var4, var5, 0, 0, super.xSize, super.ySize);
        GuiTextArea[] var6 = this.textArea;
        int var7 = var6.length;

        for (int var8 = 0; var8 < var7; ++var8)
        {
            GuiTextArea var9 = var6[var8];

            if (var9 != null)
            {
                var9.drawTextBox();
            }
        }
    }
}

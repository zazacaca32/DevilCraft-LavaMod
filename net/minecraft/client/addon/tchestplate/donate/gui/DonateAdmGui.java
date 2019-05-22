package net.minecraft.client.addon.tchestplate.donate.gui;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.donate.DonateManagerInv;
import net.minecraft.client.addon.tchestplate.donate.containers.DonateAdmContainer;
import net.minecraft.client.addon.tchestplate.donate.slots.SlotDonateAdm;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtInventory;
import net.minecraft.client.addon.tchestplate.packets.PacketMAOpenGui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class DonateAdmGui extends GuiContainer
{
    public InventoryPlayer invv;
    public ArmorExtInventory holderInventory;
    private float xSize_lo;
    private float ySize_lo;
    public static int initialScroll = 0;
    DonateManagerInv inv;

    public DonateAdmGui(InventoryPlayer var1, ArmorExtInventory var2)
    {
        super(new DonateAdmContainer(var1, var2));
        this.invv = var1;
        this.holderInventory = var2;
        super.xSize = 197;
        super.ySize = 203;
        this.inv = LavaChestPlate.instanceDonate;
    }

    public void initGui()
    {
        super.initGui();
        int var1 = super.guiLeft;
        int var2 = super.guiTop;
    }

    protected void mouseClicked(int var1, int var2, int var3)
    {
        int var10000 = var1 - super.guiLeft;
        var10000 = var2 - super.guiTop;
        super.mouseClicked(var1, var2, var3);
    }

    protected void drawSlotInventory(Slot var1)
    {
        if (var1 instanceof SlotDonateAdm)
        {
            ;
        }

        super.drawSlotInventory(var1);
    }

    public void handleMouseClick(Slot var1, int var2, int var3, int var4)
    {
        if (var1 instanceof SlotDonateAdm && var3 > 0)
        {
            PacketDispatcher.sendPacketToServer((new PacketMAOpenGui(3002, var1.getSlotIndex())).makePacket());
        }
        else
        {
            super.handleMouseClick(var1, var2, var3, var4);
        }
    }

    protected void drawGuiContainerForegroundLayer(int var1, int var2)
    {
    }

    public void drawScreen(int var1, int var2, float var3)
    {
        super.drawScreen(var1, var2, var3);
        this.xSize_lo = (float)var1;
        this.ySize_lo = (float)var2;
    }

    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        super.mc.renderEngine.bindTexture("/mods/LavaChestPlate/textures/gui/donateadm.png");
        this.drawTexturedModalRect(super.guiLeft, super.guiTop, 0, 0, super.xSize, super.ySize);
    }
}

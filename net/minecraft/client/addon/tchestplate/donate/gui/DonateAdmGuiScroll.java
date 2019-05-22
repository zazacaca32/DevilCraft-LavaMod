package net.minecraft.client.addon.tchestplate.donate.gui;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.donate.DonateManagerInv;
import net.minecraft.client.addon.tchestplate.donate.containers.DonateAdmContainerScroll;
import net.minecraft.client.addon.tchestplate.donate.slots.SlotDonateAdm;
import net.minecraft.client.addon.tchestplate.donate.slots.SlotDonateAdmScroll;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtInventory;
import net.minecraft.client.addon.tchestplate.packets.PacketMAOpenGui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class DonateAdmGuiScroll extends GuiScrollable
{
    public InventoryPlayer invv;
    public ArmorExtInventory holderInventory;
    private float xSize_lo;
    private float ySize_lo;
    public static int initialScroll = 0;
    private GuiButton but;
    int stwidth;
    DonateManagerInv inv;

    public DonateAdmGuiScroll(InventoryPlayer var1, ArmorExtInventory var2)
    {
        super(new DonateAdmContainerScroll(var1, var2));
        this.invv = var1;
        this.holderInventory = var2;
        super.xSize = 197;
        super.ySize = 203;
        this.inv = LavaChestPlate.instanceDonate;
        super.scrollLeft = 156;
        super.scrollTop = 7;
    }

    public void initGui()
    {
        super.initGui();
        int var1 = super.guiLeft;
        int var2 = super.guiTop;
        this.setScroll(initialScroll);
    }

    public void onScroll()
    {
        if (initialScroll > 0)
        {
            this.setScroll(initialScroll);
            initialScroll = 0;
        }

        boolean var1 = false;
        byte var2 = 96;
        int var3 = (var2 + (var2 % 8 > 0 ? 8 - var2 % 8 : 0)) / 8;
        int var6 = var3 - 6;

        if (var6 < 0)
        {
            var6 = 0;
        }

        this.updateScrollRegion(var6);
        Iterator var4 = super.inventorySlots.inventorySlots.iterator();

        while (var4.hasNext())
        {
            Slot var5 = (Slot)var4.next();

            if (var5 instanceof SlotDonateAdmScroll)
            {
                ((SlotDonateAdmScroll)var5).setScroll(this.getScroll(), 8);
            }
        }
    }

    protected void mouseClicked(int var1, int var2, int var3)
    {
        int var10000 = var1 - super.guiLeft;
        var10000 = var2 - super.guiTop;
        initialScroll = this.getScroll();
        this.onScroll();
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
        if (var1 != null && var1.getHasStack() && var1 instanceof SlotDonateAdmScroll && var3 > 0)
        {
            PacketDispatcher.sendPacketToServer((new PacketMAOpenGui(3002, ((SlotDonateAdmScroll)var1).getScroll())).makePacket());
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

    protected String getTextureScroll()
    {
        return "/mods/LavaChestPlate/textures/gui/donateadmscroll.png";
    }

    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        super.mc.renderEngine.bindTexture("/mods/LavaChestPlate/textures/gui/donateadmscroll.png");
        this.drawTexturedModalRect(super.guiLeft, super.guiTop, 0, 0, super.xSize, super.ySize);
        super.scrollHeight = 108;
        super.drawGuiContainerBackgroundLayer(var1, var2, var3);
    }
}

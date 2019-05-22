package net.minecraft.client.addon.tchestplate;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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

    public DonateAdmGui(InventoryPlayer inventoryplayer, ArmorExtInventory holder)
    {
        super(new DonateAdmContainer(inventoryplayer, holder));
        this.invv = inventoryplayer;
        this.holderInventory = holder;
        super.xSize = 197;
        super.ySize = 203;
        this.inv = LavaChestPlate.instanceDonate;
    }

    public void initGui()
    {
        super.initGui();
        int cornerX = super.guiLeft;
        int cornerY = super.guiTop;
    }

    protected void mouseClicked(int par1, int par2, int par3)
    {
        int var10000 = par1 - super.guiLeft;
        var10000 = par2 - super.guiTop;
        super.mouseClicked(par1, par2, par3);
    }

    protected void drawSlotInventory(Slot s)
    {
        if (s instanceof SlotDonateAdm)
        {
            ;
        }

        super.drawSlotInventory(s);
    }

    public void handleMouseClick(Slot sl, int shift, int par3, int par4)
    {
        if (sl instanceof SlotDonateAdm && par3 > 0)
        {
            PacketDispatcher.sendPacketToServer((new PacketMAOpenGui(3002, sl.getSlotIndex())).makePacket());
        }
        else
        {
            super.handleMouseClick(sl, shift, par3, par4);
        }
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
    }

    public void drawScreen(int par1, int par2, float par3)
    {
        super.drawScreen(par1, par2, par3);
        this.xSize_lo = (float)par1;
        this.ySize_lo = (float)par2;
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        super.mc.renderEngine.bindTexture("/mods/LavaChestPlate/textures/gui/donateadm.png");
        this.drawTexturedModalRect(super.guiLeft, super.guiTop, 0, 0, super.xSize, super.ySize);
    }
}

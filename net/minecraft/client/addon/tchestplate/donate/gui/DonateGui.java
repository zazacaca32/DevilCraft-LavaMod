package net.minecraft.client.addon.tchestplate.donate.gui;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.donate.DonateManagerInv;
import net.minecraft.client.addon.tchestplate.donate.containers.DonateContainer;
import net.minecraft.client.addon.tchestplate.donate.slots.SlotDonateAdm;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtInventory;
import net.minecraft.client.addon.tchestplate.packets.PacketMASendItemStacksDonateBuy;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class DonateGui extends GuiContainer
{
    public InventoryPlayer invv;
    public ArmorExtInventory armor;
    private float xSize_lo;
    private float ySize_lo;
    public static int initialScroll = 0;
    private GuiButton but;
    DonateManagerInv inv;
    int stwidth;
    public static final ItemStack itemCoupon;
    int triigerupdate = 0;
    int price = 0;

    public DonateGui(Container var1)
    {
        super(var1);
    }

    public DonateGui(InventoryPlayer var1, ArmorExtInventory var2)
    {
        super(new DonateContainer(var1, var2));
        this.invv = var1;
        this.armor = var2;
        super.xSize = 176;
        super.ySize = 248;
        this.inv = LavaChestPlate.instanceDonate;
    }

    public void initGui()
    {
        super.initGui();
        this.stwidth = super.fontRenderer.getStringWidth("99999");
        int var1 = super.guiLeft;
        int var2 = super.guiTop;
        super.buttonList.clear();
        this.but = new GuiButton(1, super.guiLeft + 114, super.guiTop + 142, 57, 20, "Купить");
        super.buttonList.add(this.but);
        this.but.enabled = this.armor.statusDonateOffert == 0 && this.armor.coin > 0;
    }

    protected void actionPerformed(GuiButton var1)
    {
        if (var1 != null && var1.id == 1 && this.armor.statusDonateOffert == 0 && this.armor.coin > 0 && this.price > 0 && this.armor.coin >= this.price)
        {
            int var2 = 0;
            ItemStack[] var3 = this.armor.invOfferts;
            int var4 = var3.length;

            for (int var5 = 0; var5 < var4; ++var5)
            {
                ItemStack var6 = var3[var5];

                if (var6 != null)
                {
                    ++var2;
                }
            }

            if (var2 > 0)
            {
                this.armor.statusDonateOffert = 1;
                var1.enabled = false;
                PacketDispatcher.sendPacketToServer((new PacketMASendItemStacksDonateBuy((byte)1, (Object[])this.armor.invOfferts)).makePacket());
            }
        }
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
        super.handleMouseClick(var1, var2, var3, var4);
    }

    protected void drawGuiContainerForegroundLayer(int var1, int var2)
    {
        this.but.enabled = this.armor.statusDonateOffert == 0 && this.armor.coin > 0 && this.price > 0;

        if (this.triigerupdate++ > 20)
        {
            this.triigerupdate = 0;
            this.price = 0;
            this.price = this.inv.checkPrice(this.armor);
        }

        short var3 = 187;
        byte var4 = 16;

        if (this.armor.coin > 0 && this.armor.coin >= this.price)
        {
            var3 = 176;
            var4 = 11;
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        super.mc.renderEngine.bindTexture("/mods/LavaChestPlate/textures/gui/donate.png");
        this.drawTexturedModalRect(99, 144, var3, 0, var4, 16);
        GL11.glPushMatrix();
        Utils.renderItemStack(super.fontRenderer, itemCoupon, 116, 121);
        GL11.glPopMatrix();
        String var5 = String.valueOf(this.armor.coin);
        String var6 = String.valueOf(this.price);
        int var7 = this.stwidth - super.fontRenderer.getStringWidth(var5);
        super.fontRenderer.drawString(var5, 138 + var7, 126, 777777777);
        var7 = this.stwidth - super.fontRenderer.getStringWidth(var6);
        super.fontRenderer.drawString(var6, 82 + var7, 126, 777777777);
        super.fontRenderer.drawString("Купон", 30, 148, 777777777);
    }

    public void drawScreen(int var1, int var2, float var3)
    {
        super.drawScreen(var1, var2, var3);
        this.xSize_lo = (float)var1;
        this.ySize_lo = (float)var2;

        if (this.isPointInRegion(116, 121, 16, 16, var1, var2))
        {
            this.drawItemStackTooltip(itemCoupon, var1, var2);
        }
    }

    protected boolean isPointInRegion(int var1, int var2, int var3, int var4, int var5, int var6)
    {
        int var7 = super.guiLeft;
        int var8 = super.guiTop;
        var5 -= var7;
        var6 -= var8;
        return var5 >= var1 - 1 && var5 < var1 + var3 + 1 && var6 >= var2 - 1 && var6 < var2 + var4 + 1;
    }

    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        super.mc.renderEngine.bindTexture("/mods/LavaChestPlate/textures/gui/donate.png");
        this.drawTexturedModalRect(super.guiLeft, super.guiTop, 0, 0, super.xSize, super.ySize);
    }

    static
    {
        itemCoupon = new ItemStack(LavaChestPlate.ItemDonate, 1, 1);
    }
}

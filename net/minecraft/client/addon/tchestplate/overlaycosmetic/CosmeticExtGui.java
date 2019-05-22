package net.minecraft.client.addon.tchestplate.overlaycosmetic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.overlaygui.InventoryTabGui;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class CosmeticExtGui extends InventoryEffectRenderer
{
    public InventoryPlayer inv;
    public CosmeticExtInventory holderInventory;
    private float xSize_lo;
    private float ySize_lo;

    public CosmeticExtGui(InventoryPlayer inventoryplayer, CosmeticExtInventory holder)
    {
        super(new CosmeticExtContainer(inventoryplayer, holder));
        this.inv = inventoryplayer;
        this.holderInventory = holder;
    }

    public void initGui()
    {
        super.initGui();
        int cornerX = super.guiLeft;
        int cornerY = super.guiTop;
        super.buttonList.clear();
        InventoryTabGui repairButton = new InventoryTabGui(2000, cornerX + 6, cornerY + 164, new ItemStack(Block.workbench), 0);
        super.buttonList.add(repairButton);
        repairButton = new InventoryTabGui(3000, cornerX + 28 + 6, cornerY + 164, new ItemStack(Item.plateDiamond), 1);
        super.buttonList.add(repairButton);
        repairButton = new InventoryTabGui(2999, cornerX + 62, cornerY + 164, new ItemStack(Item.cake), 1);
        repairButton.enabled = false;
        super.buttonList.add(repairButton);
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
        super.mc.renderEngine.bindTexture("/mods/LavaChestPlate/textures/gui/cosmeticext.png");
        int cornerX = super.guiLeft;
        int cornerY = super.guiTop;
        this.drawTexturedModalRect(cornerX, cornerY, 0, 0, super.xSize, super.ySize);

        if (!this.holderInventory.isStackInSlot(0))
        {
            this.drawTexturedModalRect(cornerX + 74, cornerY + 30, 0, 166, 16, 16);
        }

        if (!this.holderInventory.isStackInSlot(1))
        {
            this.drawTexturedModalRect(cornerX + 74, cornerY + 9, 48, 166, 16, 16);
        }

        if (!this.holderInventory.isStackInSlot(2))
        {
            this.drawTexturedModalRect(cornerX + 106, cornerY + 9, 64, 166, 16, 16);
        }

        if (!this.holderInventory.isStackInSlot(3))
        {
            this.drawTexturedModalRect(cornerX + 106, cornerY + 29, 16, 166, 16, 16);
        }

        if (!this.holderInventory.isStackInSlot(4))
        {
            this.drawTexturedModalRect(cornerX + 106, cornerY + 48, 32, 166, 16, 16);
        }

        GuiInventory.drawPlayerOnGui(super.mc, cornerX + 33, cornerY + 75, 30, (float)(cornerX + 51) - this.xSize_lo, (float)(cornerY + 75 - 50) - this.ySize_lo);
    }

    public static void drawPlayerOnGui(Minecraft par0Minecraft, int par1, int par2, int par3, float par4, float par5)
    {
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par1, (float)par2, 50.0F);
        GL11.glScalef((float)(-par3), (float)par3, (float)par3);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f2 = par0Minecraft.thePlayer.renderYawOffset;
        float f3 = par0Minecraft.thePlayer.rotationYaw;
        float f4 = par0Minecraft.thePlayer.rotationPitch;
        par4 -= 19.0F;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float)Math.atan((double)(par5 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        par0Minecraft.thePlayer.renderYawOffset = (float)Math.atan((double)(par4 / 40.0F)) * 20.0F;
        par0Minecraft.thePlayer.rotationYaw = (float)Math.atan((double)(par4 / 40.0F)) * 40.0F;
        par0Minecraft.thePlayer.rotationPitch = -((float)Math.atan((double)(par5 / 40.0F))) * 20.0F;
        par0Minecraft.thePlayer.rotationYawHead = par0Minecraft.thePlayer.rotationYaw;
        GL11.glTranslatef(0.0F, par0Minecraft.thePlayer.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        RenderManager.instance.renderEntityWithPosYaw(par0Minecraft.thePlayer, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        par0Minecraft.thePlayer.renderYawOffset = f2;
        par0Minecraft.thePlayer.rotationYaw = f3;
        par0Minecraft.thePlayer.rotationPitch = f4;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable('耺');
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}

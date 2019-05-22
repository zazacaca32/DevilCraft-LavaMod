package net.minecraft.client.addon.tchestplate.items.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.client.addon.tchestplate.ClientProxy;
import net.minecraft.client.addon.tchestplate.items.ItemPets;
import net.minecraft.client.addon.tchestplate.items.containers.ContainerItemPets;
import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtInventory;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

@SideOnly(Side.CLIENT)
public class GuiItemPetsMiner extends GuiContainer
{
    EntityPlayer player;
    ArmorExtInventory inv;
    float time = 180.0F;

    public GuiItemPetsMiner(InventoryPlayer iinventory, World world, int x, int y, int z, ExtendedPlayer extendedPlayer)
    {
        super(new ContainerItemPets(iinventory, world, x, y, z, extendedPlayer));
        this.player = iinventory.player;
        this.inv = extendedPlayer.inventoryExt;
        super.ySize = 66;
    }

    protected boolean checkHotbarKeys(int par1)
    {
        return false;
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        float f3 = 0.5F;
        super.mc.renderEngine.bindTexture("/mods/LavaChestPlate/textures/gui/guiMinerPets.png");
        int left = (super.width - super.xSize) / 2;
        int top = (super.height - super.ySize) / 2;
        this.drawTexturedModalRect(left, top, 0, 0, super.xSize, super.ySize);
        new ExtendedPlayer(ClientProxy.mc.thePlayer);
        GL11.glPushMatrix();
        ItemStack s = this.player.inventory.getCurrentItem();

        if (s != null && s.getItemDamage() == 8)
        {
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glPushMatrix();
            GL11.glScaled((double)f3, (double)f3, (double)f3);
            this.drawString(super.fontRenderer, " " + s.stackTagCompound.getInteger("tag1"), left / 2 + 350, top / 2 + 250, Color.WHITE.getRGB());
            GL11.glPopMatrix();
            GL11.glEnable(GL11.GL_DEPTH_TEST);
        }

        GL11.glPopMatrix();

        if (s != null && s.getItem() instanceof ItemPets)
        {
            int sp = (int)((double)((ContainerItemPets)super.inventorySlots).sp / Math.exp((double)(((ContainerItemPets)super.inventorySlots).lvl + 1)) * 73.0D);
            int lvl = (int)((float)((ContainerItemPets)super.inventorySlots).lvl / 10.0F * 73.0F);
            this.drawTexturedModalRect(left + 57, top + 16, 176, 0, sp, 3);
            this.drawTexturedModalRect(left + 57, top + 30, 176, 3, lvl, 3);
            BaseItemModel bm = ((ItemPets)s.getItem()).getItemModelStatic(s.getItemDamage());
            this.drawPetsOnGui(bm, left, top);
            this.drawString(super.fontRenderer, String.valueOf(((ContainerItemPets)super.inventorySlots).time_tuman), left + 100, top + 36, 8947882);
            this.drawString(super.fontRenderer, String.valueOf(((ContainerItemPets)super.inventorySlots).sp), left + 100, top + 6, 8947882);
            int lvlp = ((ContainerItemPets)super.inventorySlots).lvl;
            this.drawString(super.fontRenderer, String.valueOf(lvlp), left + 100, top + 20, 8947882);
            int h = lvlp - 4;

            if (h > 0)
            {
                h *= 5;
            }
            else
            {
                boolean var14 = false;
            }
        }
    }

    public void drawPetsOnGui(BaseItemModel bm, int left, int top)
    {
        GL11.glPushMatrix();
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        ScaledResolution scaledresolution = new ScaledResolution(super.mc.gameSettings, super.mc.displayWidth, super.mc.displayHeight);
        GL11.glViewport((scaledresolution.getScaledWidth() - 320) / 2 * scaledresolution.getScaleFactor(), (scaledresolution.getScaledHeight() - 340) / 2 * scaledresolution.getScaleFactor(), 320 * scaledresolution.getScaleFactor(), 240 * scaledresolution.getScaleFactor());
        GL11.glTranslatef(-0.3555F, 0.2F, 0.0F);
        GLU.gluPerspective(90.0F, 1.3333334F, 9.0F, 500.0F);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        RenderHelper.enableStandardItemLighting();
        GL11.glTranslatef(0.0F, 3.3F, -16.0F);
        float f2 = 8.0F;
        GL11.glScalef(8.0F, 8.0F, 8.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        ++this.time;

        if (this.time > 360.0F)
        {
            this.time = 0.0F;
        }

        GL11.glRotatef(this.time, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
        GL11.glEnable('耺');
        bm.renderAsItem(0.0625F, 0.0F);
        GL11.glDisable('耺');
        RenderHelper.disableStandardItemLighting();
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glViewport(0, 0, super.mc.displayWidth, super.mc.displayHeight);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
    }
}

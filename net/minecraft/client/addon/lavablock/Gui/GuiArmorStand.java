package net.minecraft.client.addon.lavablock.Gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.lavablock.Container.ContainerArmorStand;
import net.minecraft.client.addon.lavablock.Render.ArmorStandRender;
import net.minecraft.client.addon.lavablock.Tile.ArmorStandTile;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import org.lwjgl.opengl.GL11;

public class GuiArmorStand extends GuiContainer
{
    public ArmorStandTile tileEntity;

    public GuiArmorStand(ArmorStandTile tileEntity, EntityPlayer player)
    {
        super(new ContainerArmorStand(tileEntity, player));
        this.tileEntity = tileEntity;
        super.xSize = 176;
        super.ySize = 166;
    }

    public void initGui()
    {
        super.initGui();
    }

    protected void actionPerformed(GuiButton par1GuiButton)
    {
        System.out.println(par1GuiButton.id);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        super.mc.renderEngine.bindTexture("/mods/lavablock/textures/gui/GuiArmorStand.png");
        int x = (super.width - super.xSize) / 2;
        int y = (super.height - super.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, super.xSize, super.ySize);
        drawTileOnGui(this.tileEntity, super.mc, super.guiLeft + 142, super.guiTop + 16, 20, (float)(super.guiLeft + 43 - i), (float)(super.guiTop + 45 - 30 - j));
        GuiInventory.drawPlayerOnGui(super.mc, super.guiLeft + 142, super.guiTop + 70, 30, (float)(super.guiLeft + 43 - i), (float)(super.guiTop + 45 - 30 - j));
    }

    public static void drawTileOnGui(ArmorStandTile tileEntity, Minecraft par0Minecraft, int par1, int par2, int par3, float par4, float par5)
    {
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par1 - 89.0F, (float)par2 + 11.5F, 20.0F);
        GL11.glScalef(30.0F, 30.0F, 30.0F);
        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        boolean flag = true;

        if (tileEntity != null && tileEntity.inventoryContents[4] == null)
        {
            flag = false;
        }

        ArmorStandRender.model.setRotation(0.0F, flag);
        par0Minecraft.renderEngine.bindTexture("/mods/lavablock/textures/blocks/armorstand.png");
        ArmorStandRender.model.render();
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable('耺');
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);

        if (tileEntity != null && tileEntity.worldObj != null)
        {
            EntityPlayer fakedPlayer = new EntityPlayer(par0Minecraft.thePlayer.worldObj)
            {
                {
                    super.username = "Faked Player";
                }
                public void sendChatToPlayer(String var1)
                {
                }
                public ChunkCoordinates getPlayerCoordinates()
                {
                    return null;
                }
                public boolean canCommandSenderUseCommand(int var1, String var2)
                {
                    return false;
                }
            };
            fakedPlayer.setInvisible(true);
            fakedPlayer.inventory.armorInventory = tileEntity.inventoryContents;
            fakedPlayer.inventory.setInventorySlotContents(0, tileEntity.inventoryContents[4]);
            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)par1 - 89.0F, (float)par2 + 5.5F, 50.0F);
            GL11.glScalef(-30.0F, 30.0F, 30.0F);
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
            RenderHelper.enableStandardItemLighting();
            fakedPlayer.rotationYawHead = 0.0F;
            RenderManager.instance.renderEntityWithPosYaw(fakedPlayer, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
            GL11.glPopMatrix();
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable('耺');
            OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        }
    }
}

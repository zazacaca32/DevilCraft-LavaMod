package net.minecraft.client.addon.lavablock.Render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.lavablock.Models.ArmorStand;
import net.minecraft.client.addon.lavablock.Tile.ArmorStandTile;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class ArmorStandRender extends TileEntitySpecialRenderer implements IItemRenderer
{
    public static ArmorStand model = new ArmorStand();
    private EntityPlayer fakedPlayer = null;

    public void renderTileEntityAt(TileEntity tileEntity, double d1, double d2, double d3, float f)
    {
        this.renderTileEntity((ArmorStandTile)tileEntity, d1, d2, d3, f);
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    public void renderTileEntity(ArmorStandTile tileEntity, double d1, double d2, double d3, float f)
    {
        GL11.glPushMatrix();
        int rotation = tileEntity.rotation * 360 / 16;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslated(d1, d2 + 2.0D, d3 + 1.0D);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslated(0.5D, 0.5D, 0.5D);
        this.bindTextureByName("/mods/lavablock/textures/blocks/armorstand.png");
        boolean flag = true;

        if (tileEntity != null && tileEntity.inventoryContents[4] == null)
        {
            flag = false;
        }

        model.setRotation((float)rotation, flag);
        model.render();
        GL11.glPopMatrix();

        if (tileEntity != null && tileEntity.worldObj != null)
        {
            if (this.fakedPlayer == null)
            {
                this.fakedPlayer = new EntityPlayer(tileEntity.worldObj)
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
                this.fakedPlayer.setInvisible(true);
            }

            this.fakedPlayer.inventory.armorInventory = tileEntity.inventoryContents;
            this.fakedPlayer.inventory.setInventorySlotContents(0, tileEntity.inventoryContents[4]);
            this.fakedPlayer.worldObj = tileEntity.worldObj;
            this.fakedPlayer.renderYawOffset = this.fakedPlayer.prevRenderYawOffset = (float)rotation;
            this.fakedPlayer.rotationYawHead = this.fakedPlayer.prevRotationYawHead = (float)rotation;
            RenderManager.instance.renderEntityWithPosYaw(this.fakedPlayer, d1 + 0.5D, d2 + 1.6875D, d3 + 0.5D, (float)rotation, f);
        }
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(0.5F, 1.0F, 0.5F);
        GL11.glScalef(0.8F, 0.8F, 0.8F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lavablock/textures/blocks/armorstand.png");
        model.setRotation(0.0F, false);
        model.render();
        GL11.glPopMatrix();
    }
}

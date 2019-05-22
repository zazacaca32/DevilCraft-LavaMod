package net.minecraft.client.addon.tchestplate.overlaygui;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.ClientProxy;
import net.minecraft.client.addon.tchestplate.packets.PacketMAOpenGui;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class InventoryTabGui extends GuiButton
{
    int textureX;
    String texture = "/gui/allitems.png";
    ItemStack renderStack;
    public static boolean isOpenGui = false;

    public InventoryTabGui(int id, int posX, int posY, ItemStack stack, int texX)
    {
        super(id, posX, posY, 28, 32, "");
        this.textureX = texX;
        this.renderStack = stack;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (super.drawButton)
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            mc.renderEngine.bindTexture("/mods/provider/gui/maingui.png");
            boolean yTexPos = !super.enabled;
            boolean ySize = !super.enabled;
            this.drawTexturedModalRect(super.xPosition, super.yPosition, 0, 0, 28, 32);
            RenderHelper.enableGUIStandardItemLighting();
            super.zLevel = 100.0F;
            ClientProxy.itemRenderer.zLevel = 100.0F;
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable('耺');
            ClientProxy.itemRenderer.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, this.renderStack, super.xPosition + 6, super.yPosition + 5);
            ClientProxy.itemRenderer.renderItemOverlayIntoGUI(mc.fontRenderer, mc.renderEngine, this.renderStack, super.xPosition + 6, super.yPosition + 5);
            GL11.glDisable(GL11.GL_LIGHTING);
            ClientProxy.itemRenderer.zLevel = 0.0F;
            super.zLevel = 0.0F;
            RenderHelper.disableStandardItemLighting();
        }
    }

    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        boolean inWindow = super.enabled && super.drawButton && mouseX >= super.xPosition && mouseY >= super.yPosition && mouseX < super.xPosition + super.width && mouseY < super.yPosition + super.height;

        if (inWindow)
        {
            if (mc.thePlayer.inventory.getItemStack() != null)
            {
                return false;
            }

            if (isOpenGui)
            {
                return false;
            }

            EntityClientPlayerMP p = Minecraft.getMinecraft().thePlayer;

            if (super.id == 2000)
            {
                isOpenGui = true;
                PacketDispatcher.sendPacketToServer((new PacketMAOpenGui(2000)).makePacket());
            }
            else if (super.id == 3000)
            {
                isOpenGui = true;
                PacketDispatcher.sendPacketToServer((new PacketMAOpenGui(3000)).makePacket());
            }
        }

        return inWindow;
    }
}

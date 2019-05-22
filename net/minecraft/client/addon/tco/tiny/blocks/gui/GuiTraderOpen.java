package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTrader;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTraderOpen;
import net.minecraft.client.addon.tco.tiny.blocks.gui.LAGui;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMAUpdateInt;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiTraderOpen extends LAGui {

   private TileEntityBlockTrader tileentity;


   public GuiTraderOpen(InventoryPlayer inventoryplayer, TileEntityBlockTrader tileentity) {
      super(new ContainerTraderOpen(inventoryplayer, tileentity));
      this.tileentity = tileentity;
   }

   public void initGui() {
      super.initGui();
      super.buttonList.clear();
      int l = (super.width - super.xSize) / 2;
      int i1 = (super.height - super.ySize) / 2;
      super.buttonList.add(new GuiButton(0, l + 110, i1 + 51, 50, 20, "Купить"));
   }

   protected void useButton(GuiButton btn, boolean rightMouse) {
      if(btn.id == 0) {
         PacketDispatcher.sendPacketToServer((new PacketMAUpdateInt((byte)2, 1)).makePacket());
      }

   }

   protected void drawGuiBackgroundLayer(float var1, int var2, int var3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      if(this.tileentity.ControllerTriggerUpdateClient) {
         super.mc.renderEngine.bindTexture("/mods/tco/gui/GUITradeOMatOpen.png");
         ((GuiButton)super.buttonList.get(0)).enabled = true;
      } else {
         super.mc.renderEngine.bindTexture("/mods/tco/gui/GUITradeOMatOpenOff.png");
         ((GuiButton)super.buttonList.get(0)).enabled = false;
      }

      int l = (super.width - super.xSize) / 2;
      int i1 = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(l, i1, 0, 0, super.xSize, super.ySize);
   }

   protected void drawGuiForegroundLayer(int var1, int var2) {
      super.fontRenderer.drawString("Контроллер: ".concat(this.tileentity.ControllerTriggerUpdateClient?"Да":"Нет"), 90, 7, 4210752);
      if(this.tileentity.ControllerTriggerUpdateClient) {
         super.fontRenderer.drawString("X: ".concat(String.valueOf(this.tileentity.controllerX)), 110, 20, 4210752);
         super.fontRenderer.drawString("Y: ".concat(String.valueOf(this.tileentity.controllerY)), 110, 30, 4210752);
         super.fontRenderer.drawString("Z: ".concat(String.valueOf(this.tileentity.controllerZ)), 110, 40, 4210752);
      }

   }
}

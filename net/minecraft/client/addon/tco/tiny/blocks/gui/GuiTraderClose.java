package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTrader;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTraderClose;
import net.minecraft.client.addon.tco.tiny.blocks.gui.LAGui;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMAUpdateInt;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiTraderClose extends LAGui {

   private TileEntityBlockTrader tileentity;
   InventoryPlayer inventoryplayer;
   private final String s1 = "Спрос:";
   private final String s2 = "Предложение:";
   private final String s3 = "В наличии:";
   private final String s4 = "Контроллер:";


   public boolean isCard(LAItemStack stack) {
      return stack != null && stack.getItemID() == 4361 && (stack.getItemDamage() > 0 && stack.getItemDamage() <= 2 || stack.getItemDamage() == 7);
   }

   public GuiTraderClose(InventoryPlayer inventoryplayer, TileEntityBlockTrader tileentity) {
      super(new ContainerTraderClose(inventoryplayer, tileentity));
      this.tileentity = tileentity;
      this.inventoryplayer = inventoryplayer;
   }

   public void initGui() {
      super.initGui();
      super.buttonList.clear();
      int l = (super.width - super.xSize) / 2;
      int i1 = (super.height - super.ySize) / 2;
      super.buttonList.add(new GuiButton(0, l + 115, i1 + 62, 55, 20, "Купить"));
   }

   protected void useButton(GuiButton btn, boolean rightMouse) {
      if(btn.id == 0) {
         PacketDispatcher.sendPacketToServer((new PacketMAUpdateInt((byte)2, 1)).makePacket());
      }

   }

   protected void mouseMovedOrUp(int par1, int par2, int par3) {
      if(par3 == 0) {
         if(this.isMouseOverSlot1(98, 19, par1, par2) && this.inventoryplayer.getItemStack() == null && this.isCard(this.tileentity.cellinvfake.getStackInSlot(1))) {
            PacketDispatcher.sendPacketToServer((new PacketMAUpdateInt((byte)5, 1)).makePacket());
            return;
         }

         if(this.isMouseOverSlot1(98, 43, par1, par2) && this.inventoryplayer.getItemStack() == null && this.isCard(this.tileentity.cellinvfake.getStackInSlot(0))) {
            PacketDispatcher.sendPacketToServer((new PacketMAUpdateInt((byte)5, 0)).makePacket());
            return;
         }
      }

      super.mouseMovedOrUp(par1, par2, par3);
   }

   protected void drawGuiBackgroundLayer(float var1, int var2, int var3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      if(this.tileentity.ControllerTriggerUpdateClient) {
         super.mc.renderEngine.bindTexture("/mods/tco/gui/GUITradeOMatClosed.png");
         ((GuiButton)super.buttonList.get(0)).enabled = true;
      } else {
         super.mc.renderEngine.bindTexture("/mods/tco/gui/GUITradeOMatClosed.png");
         ((GuiButton)super.buttonList.get(0)).enabled = false;
      }

      int j = (super.width - super.xSize) / 2;
      int k = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(j, k, 0, 0, super.xSize, super.ySize);
      if(this.tileentity.ControllerTriggerUpdateClient) {
         this.drawTexturedModalRect(j + 117, k + 4, 176, 0, 14, 14);
      } else {
         this.drawTexturedModalRect(j + 117, k + 4, 191, 0, 14, 14);
      }

      if(this.isCard(this.tileentity.cellinvfake.getStackInSlot(1))) {
         this.drawTexturedModalRect(j + 98, k + 19, 206, 0, 16, 16);
      }

      if(this.isCard(this.tileentity.cellinvfake.getStackInSlot(0))) {
         this.drawTexturedModalRect(j + 98, k + 43, 206, 0, 16, 16);
      }

   }

   protected void drawGuiForegroundLayer(int var1, int var2) {
      super.fontRenderer.drawString("Спрос:", 8, 24, 4210752);
      super.fontRenderer.drawString("Предложение:", 8, 48, 4210752);
      short offcount = this.tileentity.OfferCounts;
      if(offcount >= 32767) {
         super.fontRenderer.drawString("В наличии:".concat(">=").concat(String.valueOf(offcount)), 8, 70, 4210752);
      } else {
         super.fontRenderer.drawString("В наличии:".concat(String.valueOf(offcount)), 8, 70, 4210752);
      }

      super.fontRenderer.drawString("Контроллер:", 8, 7, 4210752);
      if(this.isCard(this.tileentity.cellinvfake.getStackInSlot(1)) && this.inventoryplayer.getItemStack() == null) {
         this.setTumanSlotSmal(99, 20, var1, var2);
      }

      if(this.isCard(this.tileentity.cellinvfake.getStackInSlot(0)) && this.inventoryplayer.getItemStack() == null) {
         this.setTumanSlotSmal(99, 44, var1, var2);
      }

      this.setTumanSlot(80, 19, var1, var2);
      this.setTumanSlot(80, 43, var1, var2);
      if(!this.tileentity.ControllerTriggerUpdateClient) {
         if(this.tileentity.cellinvfake.getStackInSlot(2) == null) {
            this.setTumanSlot(116, 19, var1, var2);
         }

         this.setTumanSlot(116, 43, var1, var2);
      }

   }

   private void setTumanSlot(int x, int y, int MouseX, int MouseY) {
      if(!this.isMouseOverSlot1(x, y, MouseX, MouseY)) {
         GL11.glDisable(2896);
         GL11.glDisable(2929);
         this.drawGradientRect(x, y, x + 16, y + 16, -2130706433, -2130706433);
         GL11.glEnable(2896);
         GL11.glEnable(2929);
      }

   }

   private void setTumanSlotSmal(int x, int y, int MouseX, int MouseY) {
      if(this.isMouseOverSlot1(x, y, MouseX, MouseY)) {
         GL11.glDisable(2896);
         GL11.glDisable(2929);
         this.drawGradientRect(x, y, x + 14, y + 14, -2130706433, -2130706433);
         GL11.glEnable(2896);
         GL11.glEnable(2929);
      }

   }

   private boolean isMouseOverSlot1(int x, int y, int par2, int par3) {
      return this.isPointInRegion(x, y, 16, 16, par2, par3);
   }
}

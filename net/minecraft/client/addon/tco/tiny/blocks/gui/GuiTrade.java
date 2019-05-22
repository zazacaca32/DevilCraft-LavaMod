package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTrade;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTrade;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMATrade;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiTrade extends GuiContainer {

   private String[] res = new String[]{"Начать", "Отмена", "Завершена", "Принять"};
   TileEntityBlockTrade t;
   private long time = 0L;
   private String PlayerOneName = "Пусто";
   private String PlayerTwoName = "Пусто";
   EntityPlayer p;
   long tick = 0L;
   int tickL = 0;
   String temp = "";
   private long timeY = 0L;
   Boolean flag = Boolean.valueOf(false);


   public GuiTrade(InventoryPlayer inventoryPlayer, TileEntityBlockTrade tileEntity) {
      super(new ContainerTrade(inventoryPlayer, tileEntity));
      this.p = inventoryPlayer.player;
      this.t = tileEntity;
   }

   public void updateScreen() {
      long tick = this.tick + 1L;
      this.tick = tick;
      if(tick % 20L == 0L) {
         if(this.p != null) {
            this.PlayerOneName = this.p.getEntityName();
            Entity e = this.p.worldObj.getEntityByID(this.t.destPlayerID);
            this.PlayerTwoName = e != null?e.getEntityName():"Пусто";
         } else {
            this.PlayerOneName = "Пусто";
            this.PlayerTwoName = "Пусто";
         }
      }

   }

   protected void drawGuiContainerForegroundLayer(int param1, int param2) {
      super.fontRenderer.drawString(this.PlayerOneName, super.xSize - 78, super.ySize - 162, 879024);
      super.fontRenderer.drawString(this.PlayerTwoName, super.xSize - 150, super.ySize - 162, 879024);
      if(this.t.PlayerStatus == 3) {
         this.temp = "Заберите вещи, Осталось " + (this.t.tradeTimeOut == 0?"":Integer.valueOf(this.t.tradeTimeOut));
      } else if(this.t.PlayerStatus == 2) {
         this.temp = "Идет завершение сделки " + (this.t.tradeTimeOut == 0?"":Integer.valueOf(this.t.tradeTimeOut));
      } else if(this.t.PlayerStatus == 1) {
         this.temp = "Ожидаем подтвержение " + (this.t.tradeTimeOut == 0?"":Integer.valueOf(this.t.tradeTimeOut));
      } else if(this.t.PlayerStatus == 0) {
         this.temp = "Обменик готов для обмена...";
      }

      super.fontRenderer.drawString(String.valueOf("Инф:" + this.temp), super.xSize - 172, super.ySize - 115, 10821638);
   }

   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
      String st = "trap" + this.t.PlayerStatus + ".png";
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture("/mods/tco/gui/" + st);
      int x = (super.width - super.xSize) / 2;
      int y = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(x, y, 0, 0, super.xSize + 14, super.ySize + 14);
   }

   public void initGui() {
      super.initGui();
      super.buttonList.add(new GuiButton(1, super.guiLeft + super.xSize - 79, super.guiTop + super.ySize - 104, 55, 20, this.res[0]));
      super.buttonList.add(new GuiButton(2, super.guiLeft + super.xSize - 151, super.guiTop + super.ySize - 104, 55, 20, "Отмена"));
   }

   public void onGuiClosed() {
      super.onGuiClosed();
      if(super.mc.thePlayer != null) {
         ;
      }

   }

   protected void actionPerformed(GuiButton guibutton) {
      if(guibutton.id == 1) {
         if(this.t.destPlayerID == -1) {
            this.temp = "Сервер отменяет сделку";
            return;
         }

         if(this.t.PlayerStatus == 0) {
            long ji = System.currentTimeMillis();
            if(this.timeY > ji) {
               this.temp = "Нажимать раз в 5 секунд.";
               return;
            }

            this.timeY = ji + 7000L;
            this.t.tradeTimeOut = 20;
            this.temp = "Ждем ответ от сервера";
            PacketDispatcher.sendPacketToServer((new PacketMATrade(this.t.xCoord, this.t.yCoord, this.t.zCoord, (byte)1)).makePacket());
         } else if(this.t.PlayerStatus == 1) {
            PacketDispatcher.sendPacketToServer((new PacketMATrade(this.t.xCoord, this.t.yCoord, this.t.zCoord, (byte)2)).makePacket());
         } else if(this.t.PlayerStatus == 2) {
            ;
         }
      } else if(guibutton.id == 2) {
         PacketDispatcher.sendPacketToServer((new PacketMATrade(this.t.xCoord, this.t.yCoord, this.t.zCoord, (byte)10)).makePacket());
      }

   }
}

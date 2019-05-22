package net.minecraft.client.addon.tco.tiny.blocks.gui;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileGetExPult;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileGetExperience;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerGetExPult;
import net.minecraft.client.addon.tco.tiny.devplay.fxgaddon.SlotGetExPult;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import org.lwjgl.opengl.GL11;

public class GuiGetExPult extends GuiContainer {

   public TileGetExPult tileEntity;
   private int exp = 0;
   private boolean isOwner = false;


   public GuiGetExPult(TileGetExPult tileEntity, EntityPlayer player) {
      super(new ContainerGetExPult(tileEntity, player));
      this.tileEntity = tileEntity;
      if(this.tileEntity != null) {
         if(player.getEntityName().equals(tileEntity.Owner)) {
            this.isOwner = true;
         }

         int r = this.tileEntity.rotation;
         TileGetExperience tileXe = null;
         switch(r) {
         case 0:
            tileXe = (TileGetExperience)tileEntity.getWorldObj().getBlockTileEntity(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord + 1);
            break;
         case 1:
            tileXe = (TileGetExperience)tileEntity.getWorldObj().getBlockTileEntity(tileEntity.xCoord - 1, tileEntity.yCoord, tileEntity.zCoord);
            break;
         case 2:
            tileXe = (TileGetExperience)tileEntity.getWorldObj().getBlockTileEntity(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord - 1);
            break;
         case 3:
            tileXe = (TileGetExperience)tileEntity.getWorldObj().getBlockTileEntity(tileEntity.xCoord + 1, tileEntity.yCoord, tileEntity.zCoord);
         }

         if(tileXe != null) {
            this.exp = tileXe.exp;
         }
      }

      super.xSize = 176;
      super.ySize = 166;
   }

   protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
      super.mc.renderEngine.bindTexture("/mods/lavablock/textures/gui/GuiPultEx.png");
      int x = (super.width - super.xSize) / 2;
      int y = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(x, y, 0, 0, super.xSize, super.ySize);
   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
      super.mc.renderEngine.bindTexture("/mods/lavablock/textures/gui/GuiPultEx.png");
      if(this.isOwner) {
         this.drawTexturedModalRect(61, 36, 176, 54, 18, 18);
      } else {
         this.drawTexturedModalRect(114, 14, 176, 0, 54, 54);
         Slot slot = (Slot)super.inventorySlots.inventorySlots.get(1);
         if(slot instanceof SlotGetExPult) {
            this.setTumanSlot(slot.xDisplayPosition, slot.yDisplayPosition, par1, par2);
         }
      }

      super.fontRenderer.drawString("Заряд " + this.exp + " опыта/мин", 6, 5, 0);
      super.fontRenderer.drawString("Цена за 1 минуту:", 6, 17, 0);
      super.fontRenderer.drawString("§cВнимание!", 6, 60, 0);
      super.fontRenderer.drawString("Стойте на синтезаторе!", 6, 72, 0);
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

   private boolean isMouseOverSlot1(int x, int y, int par2, int par3) {
      return this.isPointInRegion(x, y, 16, 16, par2, par3);
   }
}

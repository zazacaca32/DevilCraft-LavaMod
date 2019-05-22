package net.minecraft.client.addon.tco.tiny.blocks.gui;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileGetExperience;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerGetExperience;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;

public class GuiGetExperience extends GuiContainer {

   public TileGetExperience tileEntity;


   public GuiGetExperience(TileGetExperience tileEntity, EntityPlayer player) {
      super(new ContainerGetExperience(tileEntity, player));
      this.tileEntity = tileEntity;
      super.xSize = 176;
      super.ySize = 166;
   }

   protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
      super.mc.renderEngine.bindTexture("/mods/lavablock/textures/gui/GuiEx.png");
      int x = (super.width - super.xSize) / 2;
      int y = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(x, y, 0, 0, super.xSize, super.ySize);
   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
      super.fontRenderer.drawString("x1 = 1 опыта/сек", 64, 12, 0);
      super.fontRenderer.drawString("x1 = 30 опыта/сек", 64, 38, 0);
      super.fontRenderer.drawString("x1 = 35 опыта/сек", 64, 66, 0);
   }

   public void initGui() {
      super.initGui();
   }
}

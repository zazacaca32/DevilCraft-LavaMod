package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockGeneratorLava;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerGeneratorLava;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

@SideOnly(Side.CLIENT)
public class GuiGeneratorLava extends GuiContainer {

   TileEntityBlockGeneratorLava tileEntity;


   public GuiGeneratorLava(InventoryPlayer inventory, TileEntityBlockGeneratorLava tileEntity) {
      super(new ContainerGeneratorLava(inventory, tileEntity));
      this.tileEntity = tileEntity;
   }

   protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
      super.mc.renderEngine.bindTexture("/mods/provider/gui/fabriclava.png");
      int x = (super.width - super.xSize) / 2;
      int y = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(x, y, 0, 0, super.xSize, super.ySize);
   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
      super.fontRenderer.drawString(this.tileEntity.DarkEnergyController / 1000 + "K/" + this.tileEntity.maxDarkEnergy / 1000 + "K", 127, 23, 4223040);
      if(this.tileEntity.isWorking) {
         super.fontRenderer.drawString((int)((float)(this.tileEntity.maxDarkEnergy - this.tileEntity.countDarkEnergy) / (float)this.tileEntity.maxDarkEnergy * 100.0F) + "%", 125, 50, 4223040);
      } else {
         super.fontRenderer.drawString("Выкл.", 125, 50, 4223040);
      }

   }
}

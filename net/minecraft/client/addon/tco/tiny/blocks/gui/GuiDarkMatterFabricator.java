package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkMatterFabricator;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerDarkMatterFabricator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

@SideOnly(Side.CLIENT)
public class GuiDarkMatterFabricator extends GuiContainer {

   TileEntityBlockDarkMatterFabricator tileEntity;


   public GuiDarkMatterFabricator(InventoryPlayer inventory, TileEntityBlockDarkMatterFabricator tileEntity) {
      super(new ContainerDarkMatterFabricator(inventory, tileEntity));
      this.tileEntity = tileEntity;
   }

   protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
      super.mc.renderEngine.bindTexture("/mods/tco/gui/darkmatterfab.png");
      int x = (super.width - super.xSize) / 2;
      int y = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(x, y, 0, 0, super.xSize, super.ySize);
   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
      super.fontRenderer.drawString(this.tileEntity.DarkEnergyController + "/" + this.tileEntity.maxDarkEnergy, 127, 23, 4223040);
      if(this.tileEntity.isWorking) {
         super.fontRenderer.drawString((int)((float)(this.tileEntity.maxDarkEnergy - this.tileEntity.countDarkEnergy) / (float)this.tileEntity.maxDarkEnergy * 100.0F) + "%", 117, 50, 4223040);
      } else {
         super.fontRenderer.drawString("off", 117, 50, 4223040);
      }

   }
}

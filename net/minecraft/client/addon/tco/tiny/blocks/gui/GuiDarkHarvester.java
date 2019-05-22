package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkEnergyHarvest;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerDarkEnergyHarvest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

@SideOnly(Side.CLIENT)
public class GuiDarkHarvester extends GuiContainer {

   TileEntityBlockDarkEnergyHarvest tileEntity;
   private int ii = 0;
   private long time = 0L;
   private long dWork = 0L;
   private boolean w = false;
   private int dualWork = 0;


   public GuiDarkHarvester(InventoryPlayer inventory, TileEntityBlockDarkEnergyHarvest tileEntity) {
      super(new ContainerDarkEnergyHarvest(inventory, tileEntity));
      this.tileEntity = tileEntity;
   }

   protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
      super.mc.renderEngine.bindTexture("/mods/tco/gui/darkharvester.png");
      int x = (super.width - super.xSize) / 2;
      int y = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(x, y + 55, 0, 0, super.xSize, 75);
      long timeg = System.currentTimeMillis();
      if(timeg > this.dWork) {
         this.dWork = timeg + 4100L;
         if(this.tileEntity.Working > this.dualWork) {
            this.dualWork = this.tileEntity.Working;
            if(!this.w) {
               this.w = true;
            }
         } else if(this.w) {
            this.w = false;
         }
      }

      if(this.w) {
         for(int u = 0; u < this.ii; ++u) {
            this.drawTexturedModalRect(82 + x + u * 8, 23 + y + 55, 176, 0, 5, 5);
         }

         if(timeg > this.time) {
            this.time = timeg + 200L;
            if(this.ii++ == 5) {
               this.ii = 0;
            }
         }
      }

   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {}
}

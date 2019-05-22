package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityH1LVL;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerH1LVL;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

@SideOnly(Side.CLIENT)
public class GuiH1LVL extends GuiContainer {

   TileEntityH1LVL tileEntity;
   private int ii = 0;
   private long time = 0L;
   private long dWork = 0L;
   private boolean w = false;
   private int dualWork = 0;


   public GuiH1LVL(InventoryPlayer inventory, TileEntityH1LVL tileEntity) {
      super(new ContainerH1LVL(inventory, tileEntity));
      this.tileEntity = tileEntity;
   }

   protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
      super.mc.renderEngine.bindTexture("/mods/provider/gui/lvl1.png");
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

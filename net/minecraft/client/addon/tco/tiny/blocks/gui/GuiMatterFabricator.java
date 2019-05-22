package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockMatterFabricator;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerMatterFabricator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiMatterFabricator extends GuiContainer {

   float res = 0.0F;
   private TileEntityBlockMatterFabricator mattertile;
   final String ji = "#0.00";
   int i1 = 0;


   public GuiMatterFabricator(InventoryPlayer inventoryplayer, TileEntityBlockMatterFabricator tileentity) {
      super(new ContainerMatterFabricator(inventoryplayer, tileentity));
      this.mattertile = tileentity;
   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
      super.fontRenderer.drawString("" + this.mattertile.progress_BurnMatter + "%", 140, 61, 4210752);
      int jj = (int)((float)this.i1 / 58.0F * 100.0F);
      super.fontRenderer.drawString("Емкость: " + jj + "%", 40, 63, 4210752);
      super.fontRenderer.drawString("Усилитель: " + (int)this.mattertile.UtilModify, 28, 72, 4210752);
   }

   protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture("/mods/tco/gui/matterfab.png");
      int j = (super.width - super.xSize) / 2;
      int k = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(j, k, 0, 0, super.xSize, super.ySize);
      this.i1 = this.mattertile.progress_getUtilScale;
      this.drawTexturedModalRect(j + 7, k + 43 + 30 - this.i1, 175, 58 - this.i1, 17, this.i1 + 1);
      if(!this.mattertile.noSunWorld) {
         if(this.mattertile.sunIsUp) {
            this.drawTexturedModalRect(j + 59, k + 30, 193, 0, 14, 14);
         } else {
            this.drawTexturedModalRect(j + 59, k + 30, 208, 0, 14, 14);
         }
      }

   }
}

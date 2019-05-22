package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockCompressionMatter;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerCompressionMatter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

@SideOnly(Side.CLIENT)
public class GuiCompressionMatter extends GuiContainer {

   float res = 0.0F;
   private TileEntityBlockCompressionMatter mattertile;
   final String ji = "#0.00";
   int i1 = 0;


   public GuiCompressionMatter(InventoryPlayer inventoryplayer, TileEntityBlockCompressionMatter tileentity) {
      super(new ContainerCompressionMatter(inventoryplayer, tileentity));
      this.mattertile = tileentity;
   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {}

   protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
      super.mc.renderEngine.bindTexture("/mods/tco/gui/comprmatter.png");
      int j = (super.width - super.xSize) / 2;
      int k = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(j, k, 0, 0, super.xSize, super.ySize);
      this.i1 = this.mattertile.progress_getUtilScale;
      this.drawTexturedModalRect(j + 7, k + 43 + 30 - this.i1, 175, 58 - this.i1, 17, this.i1 + 1);
   }
}

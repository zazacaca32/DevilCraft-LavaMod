package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderController;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTraderController;
import net.minecraft.client.addon.tco.tiny.blocks.gui.LAGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.InventoryPlayer;

@SideOnly(Side.CLIENT)
public class GuiTraderController extends LAGui {

   private TileEntityBlockTraderController te;
   protected FontRenderer field_73886_k;


   public GuiTraderController(InventoryPlayer inventoryplayer, TileEntityBlockTraderController te) {
      super(new ContainerTraderController(inventoryplayer, te));
      this.te = te;
      super.ySize = 246;
   }

   public void setWorldAndResolution(Minecraft par1Minecraft, int par2, int par3) {
      super.mc = par1Minecraft;
      this.field_73886_k = par1Minecraft.fontRenderer;
      super.setWorldAndResolution(par1Minecraft, par2, par3);
   }

   protected void drawGuiBackgroundLayer(float var1, int var2, int var3) {
      super.mc.renderEngine.bindTexture("/mods/tco/gui/la_chest.png");
      int l = (super.width - super.xSize) / 2;
      int i1 = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(l, i1, 0, 0, super.xSize, super.ySize);
   }

   protected void drawGuiForegroundLayer(int var1, int var2) {
      super.fontRenderer.drawString("Торговых аппаратов: ".concat(String.valueOf(this.te.triggercount)), 30, 10, 4210752);
   }
}

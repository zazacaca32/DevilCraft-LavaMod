package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.text.DecimalFormat;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTurning;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTurning;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiInputFurnace extends GuiContainer {

   float res = 0.0F;
   private TileEntityBlockTurning inputFurnaceInventory;
   final String ji = "#0.00";


   public GuiInputFurnace(InventoryPlayer inventoryplayer, TileEntityBlockTurning tileentityInputFurnace) {
      super(new ContainerTurning(inventoryplayer, tileentityInputFurnace));
      this.inputFurnaceInventory = tileentityInputFurnace;
   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
      this.res = this.inputFurnaceInventory.getCookProgressScaled(100);
      super.fontRenderer.drawString((new DecimalFormat("#0.00")).format((double)this.res) + "%", 135, 18, 4210752);
   }

   protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture("/mods/tco/gui/turning.png");
      int l = (super.width - super.xSize) / 2;
      int i2 = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(l, i2, 0, 0, super.xSize, super.ySize);
      if(this.res > 0.0F) {
         this.drawTexturedModalRect(l + 49, i2 + 25, 176, 0, 64, 44);
      }

      int j2 = this.inputFurnaceInventory.getWaterTimeRemainingScaled(58);
      this.drawTexturedModalRect(l + 5, i2 + 43 + 29 - j2, 175, 103 - j2, 17, j2 + 1);
   }
}

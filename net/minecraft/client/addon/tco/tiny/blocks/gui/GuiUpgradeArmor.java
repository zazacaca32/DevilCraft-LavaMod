package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockUpgradeArmor;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerUpgradeArmor;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiUpgradeArmor extends GuiContainer {

   float res = 0.0F;
   private TileEntityBlockUpgradeArmor tile;


   public GuiUpgradeArmor(InventoryPlayer inventoryplayer, TileEntityBlockUpgradeArmor tileentityInputFurnace) {
      super(new ContainerUpgradeArmor(inventoryplayer, tileentityInputFurnace));
      this.tile = tileentityInputFurnace;
   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
      super.fontRenderer.drawString("Шанс апгрейда брони " + this.tile.chance + "%", 43, 12, 4210752);
   }

   protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture("/mods/tco/gui/uparmor.png");
      int j = (super.width - super.xSize) / 2;
      int k = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(j, k, 0, 0, super.xSize, super.ySize);
      if(this.tile.isReamaning()) {
         int i1 = this.tile.getPowerScaled(24);
         this.drawTexturedModalRect(j + 79, k + 34 - 6, 176, 14, i1 + 1, 16);
      }

   }
}

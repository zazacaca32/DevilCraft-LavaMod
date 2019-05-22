package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPressOther;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerPressOther;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiPressOther extends GuiContainer {

   float res = 0.0F;
   private TileEntityBlockPressOther tile;
   private InventoryPlayer inventoryplayer;


   public GuiPressOther(InventoryPlayer inventoryplayer, TileEntityBlockPressOther tileentityInputFurnace) {
      super(new ContainerPressOther(inventoryplayer, tileentityInputFurnace));
      this.tile = tileentityInputFurnace;
      this.inventoryplayer = inventoryplayer;
   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
      super.fontRenderer.drawString("Шанс спрессовки " + this.tile.chance + "%", 7, 10, 4210752);
   }

   protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture("/mods/provider/ual/guipressother.png");
      int j = (super.width - super.xSize) / 2;
      int k = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(j, k, 0, 0, super.xSize, super.ySize);
      if(this.tile.ItemStacks[0] != null) {
         this.drawTexturedModalRect(j + 54, k + 24, 0, 166, 16, 16);
      }

      if(this.tile.ItemStacks[2] != null) {
         this.drawTexturedModalRect(j + 54, k + 46, 0, 166, 16, 16);
      }

      if(this.tile.ItemStacks[1] != null) {
         this.drawTexturedModalRect(j + 112, k + 35, 0, 166, 16, 16);
      }

      if(this.tile.isReamaning()) {
         int i1 = this.tile.getPowerScaled(24);
         this.drawTexturedModalRect(j + 79, k + 34, 176, 14, i1 + 1, 16);
      }

   }
}

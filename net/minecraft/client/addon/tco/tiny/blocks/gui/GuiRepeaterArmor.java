package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockRepeaterArmor;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerRepeaterArmor;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiRepeaterArmor extends GuiContainer {

   float res = 0.0F;
   private TileEntityBlockRepeaterArmor repeatertile;


   public GuiRepeaterArmor(InventoryPlayer inventoryplayer, TileEntityBlockRepeaterArmor tileentityInputFurnace) {
      super(new ContainerRepeaterArmor(inventoryplayer, tileentityInputFurnace));
      this.repeatertile = tileentityInputFurnace;
   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {}

   protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture("/mods/tco/gui/reparmor.png");
      int j = (super.width - super.xSize) / 2;
      int k = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(j, k, 0, 0, super.xSize, super.ySize);
      if(this.repeatertile.isReamaning()) {
         int i1 = this.repeatertile.getPowerScaled(24);
         this.drawTexturedModalRect(j + 79, k + 34, 176, 14, i1 + 1, 16);
      }

   }
}

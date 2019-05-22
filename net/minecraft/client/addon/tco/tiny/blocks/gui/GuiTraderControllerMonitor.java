package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderControllerMonitor;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTraderControllerMonitor;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiTraderControllerMonitor extends GuiContainer {

   float res = 0.0F;
   private TileEntityBlockTraderControllerMonitor tile;
   int i1 = 0;


   public GuiTraderControllerMonitor(InventoryPlayer inventory, TileEntityBlockTraderControllerMonitor tileEntity) {
      super(new ContainerTraderControllerMonitor(inventory, tileEntity));
      this.tile = tileEntity;
      this.tile.listclient.clear();
      super.ySize = 158;
   }

   public void initGui() {
      super.initGui();
   }

   protected void actionPerformed(GuiButton par1GuiButton) {
      if(par1GuiButton != null && par1GuiButton.id == 1) {
         ;
      }

   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
      super.fontRenderer.drawString("Итемы для пополнения", 28, 10, 4210752);

      for(int j1 = 0; j1 < super.inventorySlots.inventorySlots.size(); ++j1) {
         Slot slot = (Slot)super.inventorySlots.inventorySlots.get(j1);
         this.setTumanSlot(slot.xDisplayPosition, slot.yDisplayPosition, par1, par2);
      }

   }

   protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture("/mods/tco/gui/lamon_chest.png");
      int j = (super.width - super.xSize) / 2;
      int k = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(j, k, 0, 0, super.xSize, super.ySize);
   }

   private void setTumanSlot(int x, int y, int MouseX, int MouseY) {
      if(!this.isMouseOverSlot1(x, y, MouseX, MouseY)) {
         GL11.glDisable(2896);
         GL11.glDisable(2929);
         this.drawGradientRect(x, y, x + 16, y + 16, -2130706433, -2130706433);
         GL11.glEnable(2896);
         GL11.glEnable(2929);
      }

   }

   private boolean isMouseOverSlot1(int x, int y, int par2, int par3) {
      return this.isPointInRegion(x, y, 16, 16, par2, par3);
   }
}

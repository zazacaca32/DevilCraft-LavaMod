package net.minecraft.client.addon.tco.tiny.blocks.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPressBow;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerPressBow;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GuiPressBow extends GuiContainer {

   public TileEntityBlockPressBow tileEntity;
   float xx = 0.0F;
   int var8 = 0;
   long tick = 0L;


   public GuiPressBow(InventoryPlayer inventory, TileEntityBlockPressBow tileEntity, EntityPlayer player) {
      super(new ContainerPressBow((InventoryPlayer)null, tileEntity, player));
      this.tileEntity = tileEntity;
   }

   protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture("/mods/lavamobs/textures/GuiPressBow.png");
      int x = (super.width - super.xSize) / 2;
      int y = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(x, y, 0, 0, super.xSize, super.ySize);
      if(this.tileEntity.inventoryContents[0] != null) {
         this.drawTexturedModalRect(x + 54, y + 35, 0, 166, 16, 16);
      }

      if(this.tileEntity.inventoryContents[1] != null) {
         this.drawTexturedModalRect(x + 112, y + 35, 0, 166, 16, 16);
      }

      if(this.tileEntity.fx == 1 && this.xx <= 0.0F) {
         this.xx = 1.0F;
         this.var8 = 5460819;
         this.tileEntity.fx = 0;
      }

      if(this.tileEntity.fx == 2 && this.xx <= 0.0F) {
         this.var8 = 16711680;
         this.xx = 1.0F;
         this.tileEntity.fx = 0;
      }

      long tickxx = Minecraft.getMinecraft().theWorld.getWorldTime();
      if(this.xx > 0.0F && tickxx > this.tick + 2L) {
         this.tick = tickxx;
         this.xx -= 0.1F;
      }

      GL11.glPushMatrix();
      GL11.glColor4f((float)(this.var8 >> 16) / 255.0F, (float)(this.var8 >> 8 & 255) / 255.0F, (float)(this.var8 & 255) / 255.0F, this.xx);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 1);
      this.drawTexturedModalRect(x + 45, y + 26, 176, 31, 34, 34);
      GL11.glDisable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glPopMatrix();
      if(this.tileEntity.isReamaning()) {
         int i1 = this.tileEntity.getPowerScaled(24);
         this.drawTexturedModalRect(x + 79, y + 34, 176, 14, i1 + 1, 16);
      }

      if(this.tileEntity.isWorking) {
         this.drawTexturedModalRect(x + 110, y + 33, 176, 68, 20, 20);
      }

   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
      super.fontRenderer.drawString("Шанс спрессовки " + this.tileEntity.percent + "%", 40, 10, 4210752);
      if(this.tileEntity.isWorking) {
         super.fontRenderer.drawString("Внимание! В правом слоте вещь", 8, 58, 6771769);
         super.fontRenderer.drawString("исчезает при прессовке!", 8, 66, 6771769);
      }

   }
}

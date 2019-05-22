package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.donate.api.IDonateUser;
import net.minecraft.client.addon.tco.tiny.ClientProxy;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockLavaAnvil;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerLavaAnvil;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMAUpdateInt;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiLavaAnvil extends GuiContainer {

   float res = 0.0F;
   private TileEntityBlockLavaAnvil tile;
   public static final ItemStack iteml0 = new ItemStack(2812, 1, 1);
   public static final ItemStack iteml1 = new ItemStack(20970, 1, 0);
   public static final ItemStack iteml2 = new ItemStack(2486, 1, 0);
   public static final ItemStack iteml3 = new ItemStack(2478, 1, 0);
   IDonateUser iDonateUser;
   private GuiButton but;
   private int stwidth;
   final String ji = "#0.00";
   int i1 = 0;


   public GuiLavaAnvil(InventoryPlayer inventoryplayer, TileEntityBlockLavaAnvil tileentity, IDonateUser iDonateUser) {
      super(new ContainerLavaAnvil(inventoryplayer, tileentity, iDonateUser));
      this.tile = tileentity;
      super.ySize = 172;
      this.iDonateUser = iDonateUser;
   }

   public void initGui() {
      super.initGui();
      this.stwidth = super.fontRenderer.getStringWidth("99999");
      super.buttonList.clear();
      this.but = new GuiButton(1, super.guiLeft + 55, super.guiTop + 63, 66, 20, "Сбросить");
      super.buttonList.add(this.but);
      this.but.enabled = false;
   }

   protected void actionPerformed(GuiButton par1GuiButton) {
      if(par1GuiButton != null && par1GuiButton.id == 1) {
         int price = 0;
         ItemStack st = super.inventorySlots.getSlot(0).getStack();
         if(st != null) {
            Object[] obj = ((Slot_R)Manager.INSTANCE.Get(this.tile.RecipesIndex).get(0)).getSlotAsDataItemID(st);
            if(obj != null) {
               short i = this.tile.getEnchantHammer(st);
               price = ((Integer)obj[0]).intValue();
               if(i > 0) {
                  price *= i;
               }
            }
         }

         if(this.iDonateUser.getStatus() == 0 && this.iDonateUser.getCoin() > 0 && price > 0 && this.iDonateUser.getCoin() >= price) {
            this.iDonateUser.setStatus(1);
            par1GuiButton.enabled = false;
            PacketDispatcher.sendPacketToServer((new PacketMAUpdateInt((byte)3, 1)).makePacket());
         }
      }

   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
      int sum = 0;
      ItemStack st = super.inventorySlots.getSlot(0).getStack();
      if(st != null) {
         Object[] hh1222222 = ((Slot_R)Manager.INSTANCE.Get(this.tile.RecipesIndex).get(0)).getSlotAsDataItemID(st);
         if(hh1222222 != null) {
            sum = ((Integer)hh1222222[0]).intValue();
            short wigth211111 = this.tile.getEnchantHammer(st);
            if(wigth211111 > 0) {
               sum *= wigth211111;
            }

            String hh = String.valueOf(sum);
            int wigth1 = this.stwidth - super.fontRenderer.getStringWidth(hh);
            super.fontRenderer.drawString(hh, 138 + wigth1, 28, 777777777);
         }
      } else {
         int hh12222221 = this.stwidth - super.fontRenderer.getStringWidth("0");
         super.fontRenderer.drawString("0", 138 + hh12222221, 28, 777777777);
      }

      String hh12222222 = String.valueOf(this.iDonateUser.getCoin());
      int wigth2111111 = this.stwidth - super.fontRenderer.getStringWidth(hh12222222);
      super.fontRenderer.drawString(hh12222222, 138 + wigth2111111, 70, 777777777);
      this.but.enabled = this.iDonateUser.getStatus() == 0 && this.iDonateUser.getCoin() > 0 && this.iDonateUser.getCoin() >= sum;
   }

   public void drawScreen(int par1, int par2, float par3) {
      super.drawScreen(par1, par2, par3);

      try {
         if(this.isPointInRegion(8, 26, 16, 16, par1, par2)) {
            this.drawItemStackTooltip(iteml1, par1, par2);
         }

         if(this.isPointInRegion(8, 44, 16, 16, par1, par2)) {
            this.drawItemStackTooltip(iteml2, par1, par2);
         }

         if(this.isPointInRegion(145, 44, 16, 16, par1, par2)) {
            this.drawItemStackTooltip(iteml0, par1, par2);
         }

         if(this.isPointInRegion(8, 62, 16, 16, par1, par2)) {
            this.drawItemStackTooltip(iteml3, par1, par2);
         }
      } catch (Exception var5) {
         ;
      }

   }

   protected boolean isPointInRegion(int left, int top, int w, int h, int mouseX, int mouseY) {
      int k1 = super.guiLeft;
      int l1 = super.guiTop;
      mouseX -= k1;
      mouseY -= l1;
      return mouseX >= left - 1 && mouseX < left + w + 1 && mouseY >= top - 1 && mouseY < top + h + 1;
   }

   protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture("/mods/tco/gui/guilavaanvil.png");
      int j = (super.width - super.xSize) / 2;
      int k = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(j, k, 0, 0, super.xSize, super.ySize);
      RenderHelper.enableGUIStandardItemLighting();
      super.zLevel = 100.0F;
      ClientProxy.itemRenderer.zLevel = 100.0F;
      GL11.glEnable(2896);

      try {
         ClientProxy.renderItemStack(super.fontRenderer, iteml1, super.guiLeft + 8, super.guiTop + 26);
         ClientProxy.renderItemStack(super.fontRenderer, iteml2, super.guiLeft + 8, super.guiTop + 44);
         ClientProxy.renderItemStack(super.fontRenderer, iteml0, super.guiLeft + 145, super.guiTop + 44);
         ClientProxy.renderItemStack(super.fontRenderer, iteml3, super.guiLeft + 8, super.guiTop + 62);
      } catch (Exception var7) {
         ;
      }

      GL11.glDisable(2896);
      ClientProxy.itemRenderer.zLevel = 0.0F;
      super.zLevel = 0.0F;
      RenderHelper.disableStandardItemLighting();
      this.glint(super.guiLeft + 82, super.guiTop + 37);
   }

   public void glint(int x, int y) {
      GL11.glDepthFunc(516);
      GL11.glDisable(2896);
      GL11.glDepthMask(false);
      super.mc.renderEngine.bindTexture("%blur%/misc/glint.png");
      super.zLevel -= 50.0F;
      GL11.glEnable(3042);
      GL11.glBlendFunc(774, 774);
      GL11.glColor4f(0.5F, 0.25F, 0.8F, 1.0F);
      this.renderGlint(x * 431278612 + y * 32178161, x - 2, y - 2, 16, 16);
      GL11.glDisable(3042);
      GL11.glDepthMask(true);
      super.zLevel += 50.0F;
      GL11.glEnable(2896);
      GL11.glDepthFunc(515);
   }

   private void renderGlint(int par1, int par2, int par3, int par4, int par5) {
      for(int j1 = 0; j1 < 2; ++j1) {
         if(j1 == 0) {
            GL11.glBlendFunc(768, 1);
         }

         if(j1 == 1) {
            GL11.glBlendFunc(768, 1);
         }

         float f = 0.00390625F;
         float f1 = 0.00390625F;
         float f2 = (float)(Minecraft.getSystemTime() % (long)(3000 + j1 * 1873)) / (3000.0F + (float)(j1 * 1873)) * 256.0F;
         float f3 = 0.0F;
         Tessellator tessellator = Tessellator.instance;
         float f4 = 4.0F;
         if(j1 == 1) {
            f4 = -1.0F;
         }

         tessellator.startDrawingQuads();
         tessellator.addVertexWithUV((double)(par2 + 0), (double)(par3 + par5), (double)super.zLevel, (double)((f2 + (float)par5 * f4) * f), (double)((f3 + (float)par5) * f1));
         tessellator.addVertexWithUV((double)(par2 + par4), (double)(par3 + par5), (double)super.zLevel, (double)((f2 + (float)par4 + (float)par5 * f4) * f), (double)((f3 + (float)par5) * f1));
         tessellator.addVertexWithUV((double)(par2 + par4), (double)(par3 + 0), (double)super.zLevel, (double)((f2 + (float)par4) * f), (double)((f3 + 0.0F) * f1));
         tessellator.addVertexWithUV((double)(par2 + 0), (double)(par3 + 0), (double)super.zLevel, (double)((f2 + 0.0F) * f), (double)((f3 + 0.0F) * f1));
         tessellator.draw();
      }

   }

}

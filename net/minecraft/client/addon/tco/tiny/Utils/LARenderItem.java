package net.minecraft.client.addon.tco.tiny.Utils;

import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class LARenderItem extends RenderItem {

   public LAItemStack aestack;


   private void renderQuad(Tessellator par1Tessellator, int par2, int par3, int par4, int par5, int par6) {
      par1Tessellator.startDrawingQuads();
      par1Tessellator.setColorOpaque_I(par6);
      par1Tessellator.addVertex((double)(par2 + 0), (double)(par3 + 0), 0.0D);
      par1Tessellator.addVertex((double)(par2 + 0), (double)(par3 + par5), 0.0D);
      par1Tessellator.addVertex((double)(par2 + par4), (double)(par3 + par5), 0.0D);
      par1Tessellator.addVertex((double)(par2 + par4), (double)(par3 + 0), 0.0D);
      par1Tessellator.draw();
   }

   public void renderItemOverlayIntoGUI(FontRenderer par1FontRenderer, RenderEngine par2RenderEngine, ItemStack par3ItemStack, int par4, int par5) {
      this.renderItemOverlayIntoGUI(par1FontRenderer, par2RenderEngine, par3ItemStack, par4, par5, (String)null);
   }

   public void renderItemOverlayIntoGUI(FontRenderer par1FontRenderer, RenderEngine par2RenderEngine, ItemStack par3ItemStack, int par4, int par5, String par6Str) {
      if(par3ItemStack != null) {
         float ScaleFactor = 0.5F;
         float RScaleFactor = 1.0F / ScaleFactor;
         byte offset = -1;
         int X;
         int Y;
         if(par3ItemStack.isItemDamaged()) {
            int amount11111 = (int)Math.round(13.0D - (double)par3ItemStack.getItemDamageForDisplay() * 13.0D / (double)par3ItemStack.getMaxDamage());
            int l = (int)Math.round(255.0D - (double)par3ItemStack.getItemDamageForDisplay() * 255.0D / (double)par3ItemStack.getMaxDamage());
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            GL11.glDisable(3553);
            Tessellator var611111 = Tessellator.instance;
            X = 255 - l << 16 | l << 8;
            Y = (255 - l) / 4 << 16 | 16128;
            this.renderQuad(var611111, par4 + 2, par5 + 13, 13, 2, 0);
            this.renderQuad(var611111, par4 + 2, par5 + 13, 12, 1, Y);
            this.renderQuad(var611111, par4 + 2, par5 + 13, amount11111, 1, X);
            GL11.glEnable(3553);
            GL11.glEnable(2896);
            GL11.glEnable(2929);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         }

         long amount111111 = this.aestack != null?this.aestack.getStackSize():(long)par3ItemStack.stackSize;
         if(amount111111 > 999999999999L) {
            amount111111 = 999999999999L;
         }

         if(amount111111 != 0L) {
            String var6111111 = "" + Math.abs(amount111111);
            if(amount111111 > 999999999L) {
               var6111111 = "" + (int)Math.floor((double)amount111111 / 1.0E9D) + "B";
            } else if(amount111111 > 999999999L) {
               var6111111 = "" + (int)Math.floor((double)amount111111 / 1.0E9D) + "B";
            } else if(amount111111 > 999999L) {
               var6111111 = "" + (int)Math.floor((double)amount111111 / 1000000.0D) + "M";
            } else if(amount111111 > 9999L) {
               var6111111 = "" + (int)Math.floor((double)amount111111 / 1000.0D) + "K";
            }

            GL11.glDisable(2896);
            GL11.glDisable(2929);
            GL11.glPushMatrix();
            GL11.glScaled((double)ScaleFactor, (double)ScaleFactor, (double)ScaleFactor);
            X = (int)(((float)(par4 + offset) + 16.0F - (float)par1FontRenderer.getStringWidth(var6111111) * ScaleFactor) * RScaleFactor);
            Y = (int)(((float)(par5 + offset) + 16.0F - 7.0F * ScaleFactor) * RScaleFactor);
            par1FontRenderer.drawStringWithShadow(var6111111, X, Y, 16777215);
            GL11.glPopMatrix();
            GL11.glEnable(2896);
            GL11.glEnable(2929);
         }
      }

   }
}

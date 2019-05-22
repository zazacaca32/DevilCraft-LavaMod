package net.minecraft.client.addon.tco.tiny.entity.gui;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.entity.containers.ContainerMerchant;
import net.minecraft.client.addon.tco.tiny.entity.inventory.IMerchant;
import net.minecraft.client.addon.tco.tiny.entity.inventory.MerchantRecipe;
import net.minecraft.client.addon.tco.tiny.entity.inventory.MerchantRecipeList;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMAUpdateInt;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiMerchant extends GuiContainer {

   private IMerchant theIMerchant;
   private int currentRecipeIndex = 0;
   private String field_94082_v;


   public GuiMerchant(InventoryPlayer par1, IMerchant par2, World par3World, String par4) {
      super(new ContainerMerchant(par1, par2, par3World));
      this.theIMerchant = par2;
      this.field_94082_v = par4 != null && par4.length() >= 1?par4:StatCollector.translateToLocal("entity.Villager.name");
      super.ySize = 248;
   }

   public void initGui() {
      super.initGui();
      int i = (super.width - super.xSize) / 2;
      int j = (super.height - super.ySize) / 2;
   }

   protected void mouseClicked(int par1, int par2, int par3) {
      super.mouseClicked(par1, par2, par3);
      boolean flag = par3 == super.mc.gameSettings.keyBindPickBlock.keyCode + 100;
      int i1 = super.guiLeft;
      int j1 = super.guiTop;
      if(par3 == 0 || par3 == 1 || flag) {
         int incCurSlot = -1;
         boolean flag2 = par1 < i1 || par2 < j1 || par1 >= i1 + super.xSize || par2 >= j1 + super.ySize;
         if(!flag2) {
            incCurSlot = 0;

            label47:
            for(int var131 = 0; var131 < 6; ++var131) {
               for(int size = 0; size < 9; ++size) {
                  int inventoryplayer = 8 + size * 18;
                  int i2 = 14 + var131 * 18;
                  if(this.isPointInRegion(inventoryplayer, i2, 16, 16, par1, par2)) {
                     break label47;
                  }

                  ++incCurSlot;
               }
            }
         }

         if(incCurSlot >= 0 && incCurSlot < 54 && this.theIMerchant.getRecipes(super.mc.thePlayer).size() > incCurSlot) {
            InventoryPlayer var13 = super.mc.thePlayer.inventory;
            if(var13.getItemStack() == null) {
               this.currentRecipeIndex = incCurSlot;
            }

            ((ContainerMerchant)super.inventorySlots).setCurrentRecipeIndex(this.currentRecipeIndex);
            PacketDispatcher.sendPacketToServer((new PacketMAUpdateInt((byte)4, this.currentRecipeIndex)).makePacket());
         }
      }

   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
      super.fontRenderer.drawString(this.field_94082_v, super.xSize / 2 - super.fontRenderer.getStringWidth(this.field_94082_v) / 2, 4, 4210752);
   }

   public void updateScreen() {
      super.updateScreen();
   }

   protected void actionPerformed(GuiButton par1GuiButton) {}

   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture("/mods/tco/gui/la_etrader.png");
      int k = (super.width - super.xSize) / 2;
      int l = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(k, l, 0, 0, super.xSize, super.ySize);
   }

   public void drawScreen(int par1, int par2, float par3) {
      super.drawScreen(par1, par2, par3);
      MerchantRecipeList merchantrecipelist = this.theIMerchant.getRecipes(super.mc.thePlayer);
      if(merchantrecipelist != null && !merchantrecipelist.isEmpty()) {
         int k = (super.width - super.xSize) / 2;
         int l = (super.height - super.ySize) / 2;
         int i1 = this.currentRecipeIndex;
         MerchantRecipe merchantrecipe = (MerchantRecipe)merchantrecipelist.get(i1);
         if(merchantrecipe.ismaxTradeUses()) {
            super.fontRenderer.drawString("Не продаю", k + 10, l + 100, 4210752);
         } else {
            super.fontRenderer.drawString(String.valueOf(merchantrecipe.getToolUses()), k + 125, l + 130, 4210752);
         }

         GL11.glPushMatrix();
         ItemStack itemstack = merchantrecipe.getItemToBuy();
         ItemStack itemstack2 = merchantrecipe.getSecondItemToBuy();
         ItemStack itemstack3 = merchantrecipe.getItemToSell();
         RenderHelper.enableGUIStandardItemLighting();
         RenderHelper.enableGUIStandardItemLighting();
         GuiContainer.itemRenderer.zLevel = 100.0F;
         int incCurSlot = 0;
         int size = merchantrecipelist.size();
         ItemStack current = null;

         for(int var20 = 0; var20 < 6; ++var20) {
            for(int var21 = 0; var21 < 9; ++var21) {
               int var22 = 8 + var21 * 18;
               int i2 = 14 + var20 * 18;
               if(this.isPointInRegion(var22, i2, 16, 16, par1, par2)) {
                  if(size > incCurSlot) {
                     current = ((MerchantRecipe)merchantrecipelist.get(incCurSlot)).getItemToSell();
                  }

                  GL11.glDisable(2896);
                  GL11.glDisable(2929);
                  this.drawGradientRect(var22 + k, i2 + l, var22 + k + 16, i2 + l + 16, -2130706433, -2130706433);
                  GL11.glEnable(2896);
                  GL11.glEnable(2929);
               }

               if(incCurSlot == this.currentRecipeIndex) {
                  GL11.glDisable(2896);
                  GL11.glDisable(2929);
                  this.drawGradientRect(var22 + k, i2 + l, var22 + k + 16, i2 + l + 16, -2130706433, -2130706433);
                  GL11.glEnable(2896);
                  GL11.glEnable(2929);
               }

               if(size > incCurSlot) {
                  ItemStack itemstack4 = ((MerchantRecipe)merchantrecipelist.get(incCurSlot)).getItemToSell();
                  GuiContainer.itemRenderer.renderItemAndEffectIntoGUI(super.fontRenderer, super.mc.renderEngine, itemstack4, var22 + k, i2 + l);
                  GuiContainer.itemRenderer.renderItemOverlayIntoGUI(super.fontRenderer, super.mc.renderEngine, itemstack4, var22 + k, i2 + l);
               }

               ++incCurSlot;
            }
         }

         InventoryPlayer var201 = super.mc.thePlayer.inventory;
         if(var201.getItemStack() == null && current != null) {
            this.drawItemStackTooltip(current, par1, par2);
         }

         current = null;
         RenderHelper.enableGUIStandardItemLighting();
         if(itemstack != null) {
            GuiContainer.itemRenderer.renderItemAndEffectIntoGUI(super.fontRenderer, super.mc.renderEngine, itemstack, k + 34, l + 124);
            GuiContainer.itemRenderer.renderItemOverlayIntoGUI(super.fontRenderer, super.mc.renderEngine, itemstack, k + 34, l + 124);
         }

         if(itemstack2 != null) {
            GuiContainer.itemRenderer.renderItemAndEffectIntoGUI(super.fontRenderer, super.mc.renderEngine, itemstack2, k + 53, l + 124);
            GuiContainer.itemRenderer.renderItemOverlayIntoGUI(super.fontRenderer, super.mc.renderEngine, itemstack2, k + 53, l + 124);
         }

         if(itemstack3 != null) {
            GuiContainer.itemRenderer.renderItemAndEffectIntoGUI(super.fontRenderer, super.mc.renderEngine, itemstack3, k + 107, l + 124);
            GuiContainer.itemRenderer.renderItemOverlayIntoGUI(super.fontRenderer, super.mc.renderEngine, itemstack3, k + 107, l + 124);
         }

         if(var201.getItemStack() == null) {
            if(this.isPointInRegion(34, 124, 16, 16, par1, par2)) {
               if(itemstack != null) {
                  this.drawItemStackTooltip(itemstack, par1, par2);
               }
            } else if(itemstack2 != null && this.isPointInRegion(53, 124, 16, 16, par1, par2)) {
               if(itemstack2 != null) {
                  this.drawItemStackTooltip(itemstack2, par1, par2);
               }
            } else if(this.isPointInRegion(107, 124, 16, 16, par1, par2) && itemstack3 != null) {
               this.drawItemStackTooltip(itemstack3, par1, par2);
            }
         }

         if((itemstack = var201.getItemStack()) != null) {
            boolean var211 = true;
            String var221 = null;
            if(super.field_94076_q && super.field_94077_p.size() > 1 && itemstack.stackSize == 0) {
               var221 = "" + EnumChatFormatting.YELLOW + "0";
            }

            this.drawItemStack(itemstack, par1 - 8, par2 - 8, var221);
         }

         RenderHelper.enableGUIStandardItemLighting();
         GL11.glPopMatrix();
      }

   }

   private void drawItemStack(ItemStack par1ItemStack, int par2, int par3, String par4Str) {
      GL11.glTranslatef(0.0F, 0.0F, 32.0F);
      super.zLevel = 200.0F;
      GuiContainer.itemRenderer.zLevel = 200.0F;
      FontRenderer font = null;
      if(par1ItemStack != null) {
         font = par1ItemStack.getItem().getFontRenderer(par1ItemStack);
      }

      if(font == null) {
         font = super.fontRenderer;
      }

      GuiContainer.itemRenderer.renderItemAndEffectIntoGUI(font, super.mc.renderEngine, par1ItemStack, par2, par3);
      GuiContainer.itemRenderer.renderItemOverlayIntoGUI(font, super.mc.renderEngine, par1ItemStack, par2, par3, par4Str);
      super.zLevel = 0.0F;
      GuiContainer.itemRenderer.zLevel = 0.0F;
   }

   public IMerchant getIMerchant() {
      return this.theIMerchant;
   }
}

package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.common.network.PacketDispatcher;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.Utils.LARenderItem;
import net.minecraft.client.addon.tco.tiny.blocks.containers.MEContainerBase;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiImgLAButton;
import net.minecraft.client.addon.tco.tiny.blocks.gui.ITooltip;
import net.minecraft.client.addon.tco.tiny.blocks.gui.LATab;
import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotBase;
import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotFake;
import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotFakeUP64;
import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotFakeUP64Donate;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotDisabled;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotME;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public abstract class LAGui extends GuiContainer {

   public MEContainerBase gci;
   LARenderItem aeri;
   private List Tabs = new ArrayList();
   public boolean whichBtnPressed;
   public boolean isShiftClickingDisabled = false;


   protected void useButton(GuiButton btn, boolean rightMouse) {}

   protected final void actionPerformed(GuiButton par1GuiButton) {
      this.useButton(par1GuiButton, this.whichBtnPressed);
   }

   protected void mouseClicked(int par1, int par2, int par3) {
      this.whichBtnPressed = par3 == 1;
      if(par3 == 1) {
         for(int var6 = 0; var6 < super.buttonList.size(); ++var6) {
            GuiButton guibutton = (GuiButton)super.buttonList.get(var6);
            if(guibutton instanceof GuiImgLAButton && guibutton.mousePressed(super.mc, par1, par2)) {
               super.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
               this.actionPerformed(guibutton);
            }
         }
      }

      super.mouseClicked(par1, par2, par3);
      boolean var61 = false;
      this.whichBtnPressed = false;
   }

   protected void addTab(LATab appEngTab) {
      if(this.Tabs.size() == 0) {
         super.xSize += 28;
      }

      this.Tabs.add(appEngTab);
   }

   protected boolean isMouseOnTab(LATab t, int mouse_x, int mouse_y) {
      if(t.isSelected()) {
         return false;
      } else {
         boolean flag1 = true;
         int i = t.getTabColumn();
         int j = i * 28;
         byte k = 0;
         int l = 28 * i + 36 - 28;
         int i1 = super.xSize - 4 - 28;
         byte b0 = 27;
         boolean bx = false;
         if(t.isSelected()) {
            int var12 = k + 32;
            ++i1;
            bx = true;
         }

         if(i > 0) {
            l += i;
         }

         return mouse_x >= i1 && mouse_x <= i1 + 28 && mouse_y >= l && mouse_y <= l + b0;
      }
   }

   protected abstract void drawGuiBackgroundLayer(float var1, int var2, int var3);

   protected abstract void drawGuiForegroundLayer(int var1, int var2);

   protected final void drawGuiContainerBackgroundLayer(float f, int i, int j) {
      Iterator i$ = this.Tabs.iterator();

      LATab tab;
      while(i$.hasNext()) {
         tab = (LATab)i$.next();
         this.renderTab(tab, false);
      }

      this.drawGuiBackgroundLayer(f, i, j);
      i$ = this.Tabs.iterator();

      while(i$.hasNext()) {
         tab = (LATab)i$.next();
         this.renderTab(tab, true);
      }

   }

   protected final void drawGuiContainerForegroundLayer(int par1, int par2) {
      this.drawGuiForegroundLayer(par1, par2);
   }

   protected void mouseMovedOrUp(int par1, int par2, int par3) {
      if(par3 == 0) {
         int l = par1 - super.guiLeft;
         int i1 = par2 - super.guiTop;

         for(int k1 = 0; k1 < this.Tabs.size(); ++k1) {
            LATab tab = (LATab)this.Tabs.get(k1);
            if(tab != null && this.isMouseOnTab(tab, l, i1)) {
               super.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
               this.setTab(tab);
               return;
            }
         }
      }

      super.mouseMovedOrUp(par1, par2, par3);
   }

   public void setTab(LATab target) {}

   public void drawIcon(int x, int y, int iconIndex) {
      super.zLevel = 100.0F;
      int uv_y = (int)Math.floor((double)(iconIndex / 16));
      int uv_x = iconIndex - uv_y * 16;
      this.drawTexturedModalRect(x, y, uv_x * 16, uv_y * 16, 16, 16);
      super.zLevel = 0.0F;
   }

   public void drawTexturedModalRectRotated(int par1, int par2, int par3, int par4, int par5, int par6) {
      float f = 0.00390625F;
      float f1 = 0.00390625F;
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), (double)super.zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + par6) * f1));
      tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), (double)super.zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + 0) * f1));
      tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), (double)super.zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + 0) * f1));
      tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)super.zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + par6) * f1));
      tessellator.draw();
   }

   protected void renderTab(LATab t, boolean inFront) {
      RenderHelper.enableGUIStandardItemLighting();
      this.bindTexture("/gui/allitems.png");
      boolean flag = t.isSelected();
      if(inFront == flag) {
         boolean flag1 = true;
         int i = t.getTabColumn();
         int j = i * 28;
         int k = 0;
         int l = super.guiTop + 28 * i + 36;
         int i1 = super.guiLeft + super.xSize - 4 - 28;
         byte b0 = 26;
         byte bx = 0;
         if(flag) {
            k += 32;
            ++i1;
            bx = 1;
         }

         if(i > 0) {
            l += i;
         }

         l -= 28;
         GL11.glDisable(2896);
         this.drawTexturedModalRectRotated(i1, l, j, k, 28, b0);
         super.zLevel = inFront?100.0F:100.0F;
         GuiContainer.itemRenderer.zLevel = inFront?100.0F:100.0F;
         l += 4;
         i1 += 8 + (flag1?1:-1);
         GL11.glEnable(2896);
         GL11.glEnable('\u803a');
         GuiContainer.itemRenderer.renderItemAndEffectIntoGUI(super.fontRenderer, super.mc.renderEngine, t.getItemStack(), i1 - 3 - bx, l);
         GuiContainer.itemRenderer.renderItemOverlayIntoGUI(super.fontRenderer, super.mc.renderEngine, t.getItemStack(), i1 - 3 - bx, l);
         GL11.glDisable(2896);
         GuiContainer.itemRenderer.zLevel = 0.0F;
         super.zLevel = 0.0F;
      }

   }

   protected void bindTexture(String x) {
      super.mc.renderEngine.bindTexture(x);
   }

   public void drawItem(int x, int y, ItemStack is) {
      super.zLevel = 100.0F;
      GuiContainer.itemRenderer.zLevel = 100.0F;
      GL11.glEnable(2896);
      GL11.glEnable('\u803a');
      RenderHelper.enableGUIStandardItemLighting();
      GuiContainer.itemRenderer.renderItemAndEffectIntoGUI(super.fontRenderer, super.mc.renderEngine, is, x, y);
      GL11.glDisable(2896);
      GuiContainer.itemRenderer.zLevel = 0.0F;
      super.zLevel = 0.0F;
   }

   public void drawScreen(int mouse_x, int mouse_y, float btn) {
      super.drawScreen(mouse_x, mouse_y, btn);
      Iterator i$ = super.buttonList.iterator();

      while(i$.hasNext()) {
         Object c = i$.next();
         if(c instanceof ITooltip) {
            ITooltip tooltip = (ITooltip)c;
            int x = tooltip.xPos();
            int y = tooltip.yPos();
            if(x < mouse_x && x + tooltip.getWidth() > mouse_x && y < mouse_y && y + tooltip.getHeight() > mouse_y) {
               String msg = tooltip.getMsg();
               if(msg != null) {
                  this.drawTooltip(x + 8, y + 4, 0, msg);
               }
            }
         }
      }

   }

   public void drawTooltip(int par2, int par3, int forceWidth, String Msg) {
      GL11.glPushAttrib(1048575);
      GL11.glDisable('\u803a');
      RenderHelper.disableStandardItemLighting();
      GL11.glDisable(2896);
      GL11.glDisable(2929);
      String[] var4 = Msg.split("\n");
      if(var4.length > 1) {
         int var5 = 0;

         int var6;
         int var7;
         for(var6 = 0; var6 < var4.length; ++var6) {
            var7 = super.fontRenderer.getStringWidth(var4[var6]);
            if(var7 > var5) {
               var5 = var7;
            }
         }

         var6 = par2 + 12;
         var7 = par3 - 12;
         int var9 = 8;
         if(var4.length > 1) {
            var9 += 2 + (var4.length - 1) * 10;
         }

         if(super.guiTop + var7 + var9 + 6 > super.height) {
            var7 = super.height - var9 - super.guiTop - 6;
         }

         if(forceWidth > 0) {
            var5 = forceWidth;
         }

         super.zLevel = 300.0F;
         GuiContainer.itemRenderer.zLevel = 300.0F;
         int var10 = -267386864;
         this.drawGradientRect(var6 - 3, var7 - 4, var6 + var5 + 3, var7 - 3, var10, var10);
         this.drawGradientRect(var6 - 3, var7 + var9 + 3, var6 + var5 + 3, var7 + var9 + 4, var10, var10);
         this.drawGradientRect(var6 - 3, var7 - 3, var6 + var5 + 3, var7 + var9 + 3, var10, var10);
         this.drawGradientRect(var6 - 4, var7 - 3, var6 - 3, var7 + var9 + 3, var10, var10);
         this.drawGradientRect(var6 + var5 + 3, var7 - 3, var6 + var5 + 4, var7 + var9 + 3, var10, var10);
         int var11 = 1347420415;
         int var12 = (var11 & 16711422) >> 1 | var11 & -16777216;
         this.drawGradientRect(var6 - 3, var7 - 3 + 1, var6 - 3 + 1, var7 + var9 + 3 - 1, var11, var12);
         this.drawGradientRect(var6 + var5 + 2, var7 - 3 + 1, var6 + var5 + 3, var7 + var9 + 3 - 1, var11, var12);
         this.drawGradientRect(var6 - 3, var7 - 3, var6 + var5 + 3, var7 - 3 + 1, var11, var11);
         this.drawGradientRect(var6 - 3, var7 + var9 + 2, var6 + var5 + 3, var7 + var9 + 3, var12, var12);

         for(int var13 = 0; var13 < var4.length; ++var13) {
            String var14 = var4[var13];
            if(var13 == 0) {
               var14 = "§" + Integer.toHexString(15) + var14;
            } else {
               var14 = "§7" + var14;
            }

            super.fontRenderer.drawStringWithShadow(var14, var6, var7, -1);
            if(var13 == 0) {
               var7 += 2;
            }

            var7 += 10;
         }

         super.zLevel = 0.0F;
         GuiContainer.itemRenderer.zLevel = 0.0F;
      }

      GL11.glPopAttrib();
   }

   static String join(Collection s, String delimiter) {
      StringBuilder builder = new StringBuilder();
      Iterator iter = s.iterator();

      while(iter.hasNext()) {
         builder.append(iter.next());
         if(!iter.hasNext()) {
            break;
         }

         builder.append(delimiter);
      }

      return builder.toString();
   }

   Slot getSlot(int mousex, int mousey) {
      for(int j1 = 0; j1 < super.inventorySlots.inventorySlots.size(); ++j1) {
         Slot slot = (Slot)super.inventorySlots.inventorySlots.get(j1);
         if(this.isPointInRegion(slot.xDisplayPosition, slot.yDisplayPosition, 16, 16, mousex, mousey)) {
            return slot;
         }
      }

      return null;
   }

   protected void drawItemStackTooltip(ItemStack stack, int x, int y) {
      Slot s = this.getSlot(x, y);
      if((s instanceof SlotME || s instanceof MESlotFakeUP64 || s instanceof MESlotFakeUP64Donate) && stack != null) {
         short BigNumber = 9999;
         LAItemStack myStack = null;

         try {
            MESlotBase var4111111 = (MESlotBase)s;
            myStack = var4111111.getAEStackLA();
         } catch (Throwable var8) {
            ;
         }

         List var41111111;
         if(myStack != null) {
            var41111111 = stack.getTooltip(super.mc.thePlayer, super.mc.gameSettings.advancedItemTooltips);
            if(myStack.getStackSize() > (long)BigNumber || myStack.getStackSize() > 1L && stack.isItemDamaged()) {
               var41111111.add("Items Stored: " + NumberFormat.getNumberInstance(Locale.US).format(myStack.getStackSize()));
            }

            if(myStack.getCountRequestable() > 0L) {
               var41111111.add("Items Requestable: " + NumberFormat.getNumberInstance(Locale.US).format(myStack.getCountRequestable()));
            }

            this.drawTooltip(x, y, 0, join(var41111111, "\n"));
         } else if(stack != null && stack.stackSize > BigNumber) {
            var41111111 = stack.getTooltip(super.mc.thePlayer, super.mc.gameSettings.advancedItemTooltips);
            var41111111.add("Items Stored: " + NumberFormat.getNumberInstance(Locale.US).format((long)stack.stackSize));
            this.drawTooltip(x, y, 0, join(var41111111, "\n"));
            return;
         }
      }

      super.drawItemStackTooltip(stack, x, y);
   }

   public List handleItemTooltip(ItemStack stack, int mousex, int mousey, List currenttip) {
      if(stack != null) {
         Slot s = this.getSlot(mousex, mousey);
         if(s instanceof MESlotBase) {
            short BigNumber = 9999;
            LAItemStack myStack = null;

            try {
               MESlotBase theSlotField = (MESlotBase)s;
               myStack = theSlotField.getAEStackLA();
            } catch (Throwable var9) {
               ;
            }

            if(myStack != null) {
               if(myStack.getStackSize() > (long)BigNumber || myStack.getStackSize() > 1L && stack.isItemDamaged()) {
                  currenttip.add("§7Итемов всего: " + NumberFormat.getNumberInstance(Locale.US).format(myStack.getStackSize()));
               }

               if(myStack.getCountRequestable() > 0L) {
                  currenttip.add("§7Items Requestable: " + NumberFormat.getNumberInstance(Locale.US).format(myStack.getCountRequestable()));
               }
            } else if(stack.stackSize > BigNumber || stack.stackSize > 1 && stack.isItemDamaged()) {
               currenttip.add("§7Итемов всего: " + NumberFormat.getNumberInstance(Locale.US).format((long)stack.stackSize));
            }
         }
      }

      return currenttip;
   }

   private void safeDrawSlot(Slot s) {
      try {
         super.drawSlotInventory(s);
      } catch (Exception var4) {
         Tessellator tessellator = Tessellator.instance;
         if(tessellator.isDrawing) {
            tessellator.draw();
         }
      }

   }

   protected void drawSlotInventory(Slot s) {
      if(!(s instanceof SlotME) && !(s instanceof MESlotFakeUP64) && !(s instanceof MESlotFakeUP64Donate)) {
         try {
            ItemStack err1 = s.getStack();
            if(s instanceof MESlotBase && (((MESlotBase)s).renderIconWithItem() || err1 == null)) {
               MESlotBase var172 = (MESlotBase)s;
               if(var172.getIcon() >= 0) {
                  this.bindTexture("/mods/tco/gui/states.png");
                  GL11.glPushAttrib(1048575);
                  Tessellator err12 = Tessellator.instance;

                  try {
                     int var15 = (int)Math.floor((double)(var172.getIcon() / 16));
                     int uv_x = var172.getIcon() - var15 * 16;
                     GL11.glEnable(3042);
                     GL11.glDisable(2896);
                     GL11.glEnable(3553);
                     GL11.glBlendFunc(770, 771);
                     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                     float par1 = (float)var172.xDisplayPosition;
                     float par2 = (float)var172.yDisplayPosition;
                     float par3 = (float)(uv_x * 16);
                     float par4 = (float)(var15 * 16);
                     float par5 = 16.0F;
                     float par6 = 16.0F;
                     float f = 0.00390625F;
                     float f1 = 0.00390625F;
                     err12.startDrawingQuads();
                     err12.setColorRGBA_F(1.0F, 1.0F, 1.0F, var172.getOpacityOfIcon());
                     err12.addVertexWithUV((double)(par1 + 0.0F), (double)(par2 + par6), (double)super.zLevel, (double)((par3 + 0.0F) * f), (double)((par4 + par6) * f1));
                     err12.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), (double)super.zLevel, (double)((par3 + par5) * f), (double)((par4 + par6) * f1));
                     err12.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0.0F), (double)super.zLevel, (double)((par3 + par5) * f), (double)((par4 + 0.0F) * f1));
                     err12.addVertexWithUV((double)(par1 + 0.0F), (double)(par2 + 0.0F), (double)super.zLevel, (double)((par3 + 0.0F) * f), (double)((par4 + 0.0F) * f1));
                     err12.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
                     err12.draw();
                  } catch (Exception var151) {
                     if(err12.isDrawing) {
                        err12.draw();
                     }
                  }

                  GL11.glPopAttrib();
               }
            }

            if(err1 != null && s instanceof MESlotBase) {
               if(((MESlotBase)s).isValid == MESlotBase.hasCalculatedValidness.NotAvailable) {
                  boolean var173 = s.isItemValid(err1) || s instanceof SlotDisabled;
                  ((MESlotBase)s).isValid = var173?MESlotBase.hasCalculatedValidness.Valid:MESlotBase.hasCalculatedValidness.Invalid;
               }

               if(((MESlotBase)s).isValid == MESlotBase.hasCalculatedValidness.Invalid) {
                  super.zLevel = 100.0F;
                  GuiContainer.itemRenderer.zLevel = 100.0F;
                  GL11.glDisable(2896);
                  GuiContainer.drawRect(s.xDisplayPosition, s.yDisplayPosition, 16 + s.xDisplayPosition, 16 + s.yDisplayPosition, 1728013926);
                  GL11.glEnable(2896);
                  super.zLevel = 0.0F;
                  GuiContainer.itemRenderer.zLevel = 0.0F;
               }
            }

            if(s instanceof MESlotBase) {
               ((MESlotBase)s).isDisplay = true;
               this.safeDrawSlot(s);
            } else {
               this.safeDrawSlot(s);
            }
         } catch (Exception var16) {
            this.safeDrawSlot(s);
         }
      } else {
         RenderItem err = GuiContainer.itemRenderer;
         GuiContainer.itemRenderer = this.aeri;

         try {
            if(this.gci instanceof MEContainerBase) {
               MEContainerBase var17 = this.gci;
               if(!var17.isValid()) {
                  super.zLevel = 100.0F;
                  GuiContainer.itemRenderer.zLevel = 100.0F;
                  GL11.glDisable(2896);
                  GuiContainer.drawRect(s.xDisplayPosition, s.yDisplayPosition, 16 + s.xDisplayPosition, 16 + s.yDisplayPosition, 1712394513);
                  GL11.glEnable(2896);
                  super.zLevel = 0.0F;
                  GuiContainer.itemRenderer.zLevel = 0.0F;
               }
            }

            ((MESlotBase)s).isDisplay = true;
            if(s instanceof SlotME) {
               this.aeri.aestack = ((SlotME)s).getAEStackLA();
            } else if(s instanceof MESlotFakeUP64) {
               this.aeri.aestack = ((MESlotFakeUP64)s).getAEStackLA();
            } else if(s instanceof MESlotFakeUP64Donate) {
               this.aeri.aestack = ((MESlotFakeUP64Donate)s).getAEStackLA();
            } else {
               this.aeri.aestack = null;
            }

            this.safeDrawSlot(s);
         } catch (Exception var171) {
            if(Tessellator.instance.isDrawing) {
               Tessellator.instance.draw();
            }
         }

         GuiContainer.itemRenderer = err;
      }

   }

   public LAGui(Container par1Container) {
      super(par1Container);
      this.gci = (MEContainerBase)par1Container;
      this.aeri = new LARenderItem();
      super.ySize = 166;
   }

   public void handleMouseClick(Slot sl, int shift, int par3, int par4) {
      if(sl instanceof MESlotFake && this.gci.wasDragging) {
         this.gci.wasDragging = false;
      } else {
         Packet e1;
         if(sl instanceof MESlotFakeUP64 && this.gci instanceof MEContainerBase) {
            try {
               e1 = this.gci.getMESlotFakeUP64Packet((MESlotFakeUP64)sl, par3, par4);
               if(e1 != null) {
                  PacketDispatcher.sendPacketToServer(e1);
               }
            } catch (IOException var8) {
               var8.printStackTrace();
            }
         } else if(sl instanceof SlotME && this.gci instanceof MEContainerBase) {
            try {
               e1 = this.gci.getSlotMEPacket((SlotME)sl, par3, par4);
               if(e1 != null) {
                  PacketDispatcher.sendPacketToServer(e1);
               }
            } catch (IOException var7) {
               var7.printStackTrace();
            }
         } else if(!Keyboard.isKeyDown(42) && !Keyboard.isKeyDown(54) || !this.isShiftClickingDisabled) {
            super.handleMouseClick(sl, shift, par3, par4);
         }
      }

   }

   public void networkedUpdate() {}
}

package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.Utils.LARenderItem;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTrader;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTraderCloseInfo;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMAUpdateInt;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiTraderCloseInfo extends GuiContainer {

   static final String ITEM_TYPE_TAG = "it";
   static final String ITEM_COUNT_TAG = "ic";
   static final String ITEM_SLOT = "#";
   static final String ITEM_SLOTCOUNT = "@";
   static final String ITEM_PRE_FORMATED_COUNT = "PF";
   static final String ITEM_PRE_FORMATED_SLOT = "PF#";
   static final String ITEM_PRE_FORMATED_NAME = "PN";
   static final String ITEM_PRE_FORMATED_FUZZY = "FP";
   protected static String[] ITEM_SLOT_ARR;
   protected static String[] ITEM_SLOTCOUNT_ARR;
   protected int MAX_ITEM_TYPES = 63;
   LARenderItem aeri;
   private TileEntityBlockTrader tileentity;
   private boolean isInit;
   InventoryPlayer inventoryplayer;


   public boolean isCard(LAItemStack stack) {
      return stack != null && stack.getItemID() == 4361 && (stack.getItemDamage() > 0 && stack.getItemDamage() <= 2 || stack.getItemDamage() == 7);
   }

   public boolean isCard(ItemStack stack) {
      return stack != null && stack.itemID == 4361 && (stack.getItemDamage() > 0 && stack.getItemDamage() <= 2 || stack.getItemDamage() == 7);
   }

   public GuiTraderCloseInfo(InventoryPlayer inventoryplayer, TileEntityBlockTrader tileentity) {
      super(new ContainerTraderCloseInfo(inventoryplayer, tileentity));
      this.inventoryplayer = inventoryplayer;
      this.tileentity = tileentity;
      super.ySize = 168;
      this.isInit = true;
      this.aeri = new LARenderItem();
   }

   public void initGui() {
      super.initGui();
   }

   protected void mouseMovedOrUp(int par1, int par2, int par3) {
      if(par3 == 0 && this.isMouseOverSlot1(153, 145, par1, par2) && this.inventoryplayer.getItemStack() == null) {
         PacketDispatcher.sendPacketToServer((new PacketMAUpdateInt((byte)6, 0)).makePacket());
      } else {
         super.mouseMovedOrUp(par1, par2, par3);
      }

   }

   protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture("/mods/tco/gui/GUITradeOMatClosedInfo.png");
      int j = (super.width - super.xSize) / 2;
      int k = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(j, k, 0, 0, super.xSize, super.ySize);
   }

   protected void drawGuiContainerForegroundLayer(int var1, int var2) {
      super.fontRenderer.drawString("Просмотр карты памяти", 28, 4, 4210752);
      if(this.inventoryplayer.getItemStack() == null) {
         this.setTumanSlotSmal(153, 145, var1, var2);
      }

   }

   public void drawScreen(int par1, int par2, float par3) {
      super.drawScreen(par1, par2, par3);
      int k = super.guiLeft;
      int l = super.guiTop;
      RenderHelper.enableGUIStandardItemLighting();
      GL11.glPushMatrix();
      GL11.glTranslatef((float)k, (float)l, 0.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable('\u803a');
      boolean short1 = true;
      boolean short2 = true;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      ItemStack it;
      if(this.isInit && this.isCard(it = super.inventorySlots.getSlot(0).getStack())) {
         if(ITEM_SLOT_ARR == null) {
            ITEM_SLOT_ARR = new String[this.MAX_ITEM_TYPES];
            ITEM_SLOTCOUNT_ARR = new String[this.MAX_ITEM_TYPES];

            for(int var171 = 0; var171 < this.MAX_ITEM_TYPES; ++var171) {
               ITEM_SLOT_ARR[var171] = "#" + var171;
               ITEM_SLOTCOUNT_ARR[var171] = "@" + var171;
            }
         }

         if(this.MAX_ITEM_TYPES > 63) {
            this.MAX_ITEM_TYPES = 63;
         }

         if(this.MAX_ITEM_TYPES < 1) {
            this.MAX_ITEM_TYPES = 1;
         }

         try {
            NBTTagCompound var16 = Utils.openNbtData(it);
            short var201 = var16.getShort("it");
            int var221 = var16.getInteger("ic");
            if(var201 > 63) {
               var201 = 63;
            }

            for(int var241 = 0; var241 < var201; ++var241) {
               ItemStack var251 = ItemStack.loadItemStackFromNBT(var16.getCompoundTag(ITEM_SLOT_ARR[var241]));
               if(var251 != null) {
                  var251.stackSize = Math.min(Integer.MAX_VALUE, var16.getInteger(ITEM_SLOTCOUNT_ARR[var241]));
                  if(var251.stackSize > 0) {
                     ((ContainerTraderCloseInfo)super.inventorySlots).invInfo.setInventorySlotContents(var241, var251);
                  }
               }
            }
         } catch (Exception var15) {
            ;
         }

         this.isInit = false;
      }

      Slot var17 = null;

      for(int var18 = 0; var18 < ((ContainerTraderCloseInfo)super.inventorySlots).guislots.size(); ++var18) {
         Slot var19 = (Slot)((ContainerTraderCloseInfo)super.inventorySlots).guislots.get(var18);
         RenderItem var23 = GuiContainer.itemRenderer;
         GuiContainer.itemRenderer = this.aeri;
         this.drawSlotInventory(var19);
         GuiContainer.itemRenderer = var23;
         if(this.isMouseOverSlot1(var19.xDisplayPosition, var19.yDisplayPosition, par1, par2)) {
            var17 = var19;
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            int var25 = var19.xDisplayPosition;
            int i1 = var19.yDisplayPosition;
            this.drawGradientRect(var25, i1, var25 + 16, i1 + 16, -2130706433, -2130706433);
            GL11.glEnable(2896);
            GL11.glEnable(2929);
         }
      }

      ItemStack var20 = this.inventoryplayer.getItemStack();
      if(var20 != null) {
         boolean var21 = true;
         String var24 = null;
         if(super.field_94076_q && super.field_94077_p.size() > 1 && var20.stackSize == 0) {
            var24 = "" + EnumChatFormatting.YELLOW + "0";
         }

         this.drawItemStack(var20, par1 - k - 8, par2 - l - 8, var24);
      }

      GL11.glPopMatrix();
      if(this.inventoryplayer.getItemStack() == null && var17 != null && var17.getHasStack()) {
         ItemStack var22 = var17.getStack();
         this.drawItemStackTooltip(var22, par1, par2);
      }

      GL11.glEnable(2896);
      GL11.glEnable(2929);
      RenderHelper.enableGUIStandardItemLighting();
   }

   private void setTumanSlot(int x, int y, int MouseX, int MouseY) {
      if(this.isMouseOverSlot1(x, y, MouseX, MouseY)) {
         GL11.glDisable(2896);
         GL11.glDisable(2929);
         this.drawGradientRect(x, y, x + 16, y + 16, -2130706433, -2130706433);
         GL11.glEnable(2896);
         GL11.glEnable(2929);
      }

   }

   private void setTumanSlotSmal(int x, int y, int MouseX, int MouseY) {
      if(this.isMouseOverSlot1(x, y, MouseX, MouseY)) {
         GL11.glDisable(2896);
         GL11.glDisable(2929);
         this.drawGradientRect(x, y, x + 14, y + 14, -2130706433, -2130706433);
         GL11.glEnable(2896);
         GL11.glEnable(2929);
      }

   }

   private boolean isMouseOverSlot1(int x, int y, int par2, int par3) {
      return this.isPointInRegion(x, y, 16, 16, par2, par3);
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

   protected void drawItemStackTooltip(ItemStack stack, int x, int y) {
      if(stack != null) {
         boolean BigNumber = true;
         if(stack != null && stack.stackSize > 9999) {
            List var4 = stack.getTooltip(super.mc.thePlayer, super.mc.gameSettings.advancedItemTooltips);
            var4.add("Итемов всего: " + NumberFormat.getNumberInstance(Locale.US).format((long)stack.stackSize));
            this.drawTooltip(x, y, 0, join(var4, "\n"));
            return;
         }
      }

      super.drawItemStackTooltip(stack, x, y);
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
         int var8 = 8;
         if(var4.length > 1) {
            var8 += 2 + (var4.length - 1) * 10;
         }

         if(super.guiTop + var7 + var8 + 6 > super.height) {
            var7 = super.height - var8 - super.guiTop - 6;
         }

         if(forceWidth > 0) {
            var5 = forceWidth;
         }

         super.zLevel = 300.0F;
         GuiContainer.itemRenderer.zLevel = 300.0F;
         int var9 = -267386864;
         this.drawGradientRect(var6 - 3, var7 - 4, var6 + var5 + 3, var7 - 3, -267386864, -267386864);
         this.drawGradientRect(var6 - 3, var7 + var8 + 3, var6 + var5 + 3, var7 + var8 + 4, -267386864, -267386864);
         this.drawGradientRect(var6 - 3, var7 - 3, var6 + var5 + 3, var7 + var8 + 3, -267386864, -267386864);
         this.drawGradientRect(var6 - 4, var7 - 3, var6 - 3, var7 + var8 + 3, -267386864, -267386864);
         this.drawGradientRect(var6 + var5 + 3, var7 - 3, var6 + var5 + 4, var7 + var8 + 3, -267386864, -267386864);
         int var10 = 1347420415;
         int var11 = 1344798847;
         this.drawGradientRect(var6 - 3, var7 - 3 + 1, var6 - 3 + 1, var7 + var8 + 3 - 1, 1347420415, 1344798847);
         this.drawGradientRect(var6 + var5 + 2, var7 - 3 + 1, var6 + var5 + 3, var7 + var8 + 3 - 1, 1347420415, 1344798847);
         this.drawGradientRect(var6 - 3, var7 - 3, var6 + var5 + 3, var7 - 3 + 1, 1347420415, 1347420415);
         this.drawGradientRect(var6 - 3, var7 + var8 + 2, var6 + var5 + 3, var7 + var8 + 3, 1344798847, 1344798847);

         for(int var12 = 0; var12 < var4.length; ++var12) {
            String var13 = var4[var12];
            var13 = var12 == 0?"§" + Integer.toHexString(15) + var13:"§7" + var13;
            super.fontRenderer.drawStringWithShadow(var13, var6, var7, -1);
            if(var12 == 0) {
               var7 += 2;
            }

            var7 += 10;
         }

         super.zLevel = 0.0F;
         GuiContainer.itemRenderer.zLevel = 0.0F;
      }

      GL11.glPopAttrib();
   }

   public List handleItemTooltip(ItemStack stack, int mousex, int mousey, List currenttip) {
      if(stack != null) {
         boolean BigNumber = true;
         if(stack.stackSize > 9999 || stack.stackSize > 1 && stack.isItemDamaged()) {
            currenttip.add("§7Итемов всего: " + NumberFormat.getNumberInstance(Locale.US).format((long)stack.stackSize));
         }
      }

      return currenttip;
   }
}

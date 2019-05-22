package net.minecraft.client.gui.inventory;

import codechicken.nei.forge.GuiContainerManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.aaamodule.ModuleCore;
import net.minecraft.client.addon.tchestplate.armors.items.LavaChestPlateItem;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public abstract class GuiContainer extends GuiScreen {
   public static RenderItem itemRenderer = new RenderItem();
   public int xSize = 176;
   public int ySize = 166;
   public Container inventorySlots;
   public int guiLeft;
   public int guiTop;
   private Slot theSlot;
   private Slot clickedSlot = null;
   private boolean isRightMouseClick = false;
   private ItemStack draggedStack = null;
   private int field_85049_r = 0;
   private int field_85048_s = 0;
   private Slot returningStackDestSlot = null;
   private long returningStackTime = 0L;
   private ItemStack returningStack = null;
   private Slot field_92033_y = null;
   private long field_92032_z = 0L;
   protected final Set field_94077_p = new HashSet();
   protected boolean field_94076_q;
   private int field_94071_C = 0;
   private int field_94067_D = 0;
   private boolean field_94068_E = false;
   private int field_94069_F;
   private long field_94070_G = 0L;
   private Slot field_94072_H = null;
   private int field_94073_I = 0;
   private boolean field_94074_J;
   private ItemStack field_94075_K = null;
   public GuiContainerManager manager;

   public GuiContainer(Container par1Container) {
      this.inventorySlots = par1Container;
      this.field_94068_E = true;
   }

   public void setWorldAndResolution(Minecraft mc, int i, int j) {
      super.setWorldAndResolution(mc, i, j);
      if(mc.currentScreen == this) {
         this.manager = new GuiContainerManager(this);
         this.manager.load();
      }

   }

   public void initGui() {
      super.initGui();
      super.mc.thePlayer.openContainer = this.inventorySlots;
      this.guiLeft = (super.width - this.xSize) / 2;
      this.guiTop = (super.height - this.ySize) / 2;
   }

   public void drawScreen(int par1, int par2, float par3) {
      this.manager.preDraw();
      this.drawDefaultBackground();
      int k = this.guiLeft;
      int l = this.guiTop;
      this.drawGuiContainerBackgroundLayer(par3, par1, par2);
      GL11.glDisable('耺');
      RenderHelper.disableStandardItemLighting();
      GL11.glDisable(2896);
      GL11.glDisable(2929);
      super.drawScreen(par1, par2, par3);
      RenderHelper.enableGUIStandardItemLighting();
      GL11.glPushMatrix();
      GL11.glTranslatef((float)k, (float)l, 0.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable('耺');
      this.theSlot = null;
      short short1 = 240;
      short short2 = 240;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)short1 / 1.0F, (float)short2 / 1.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      boolean objectundermouse = this.manager.objectUnderMouse(par1, par2);

      int i1;
      for(int var161 = 0; var161 < this.inventorySlots.inventorySlots.size(); ++var161) {
         Slot var171 = (Slot)this.inventorySlots.inventorySlots.get(var161);
         this.drawSlotInventory(var171);
         if(this.isMouseOverSlot(var171, par1, par2) && !objectundermouse) {
            this.theSlot = var171;
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            int var201 = var171.xDisplayPosition;
            i1 = var171.yDisplayPosition;
            this.drawGradientRect(var201, i1, var201 + 16, i1 + 16, -2130706433, -2130706433);
            GL11.glEnable(2896);
            GL11.glEnable(2929);
         }
      }

      this.drawGuiContainerForegroundLayer(par1, par2);
      GL11.glTranslatef((float)(-k), (float)(-l), 200.0F);
      this.manager.renderObjects(par1, par2);
      GL11.glTranslatef((float)k, (float)l, -200.0F);
      InventoryPlayer var16 = super.mc.thePlayer.inventory;
      ItemStack var17 = this.draggedStack == null?var16.getItemStack():this.draggedStack;
      if(var17 != null) {
         byte var18 = 8;
         i1 = this.draggedStack == null?8:16;
         String var21 = null;
         if(this.draggedStack != null && this.isRightMouseClick) {
            var17 = var17.copy();
            var17.stackSize = MathHelper.ceiling_float_int((float)var17.stackSize / 2.0F);
         } else if(this.field_94076_q && this.field_94077_p.size() > 1) {
            var17 = var17.copy();
            var17.stackSize = this.field_94069_F;
            if(var17.stackSize == 0) {
               var21 = EnumChatFormatting.YELLOW + "0";
            }
         }

         this.drawItemStack(var17, par1 - k - var18, par2 - l - i1, var21);
      }

      if(this.returningStack != null) {
         float var19 = (float)(Minecraft.getSystemTime() - this.returningStackTime) / 100.0F;
         if(var19 >= 1.0F) {
            var19 = 1.0F;
            this.returningStack = null;
         }

         i1 = this.returningStackDestSlot.xDisplayPosition - this.field_85049_r;
         int var211 = this.returningStackDestSlot.yDisplayPosition - this.field_85048_s;
         int i2 = this.field_85049_r + (int)((float)i1 * var19);
         int j2 = this.field_85048_s + (int)((float)var211 * var19);
         this.drawItemStack(this.returningStack, i2, j2, (String)null);
      }

      this.manager.renderToolTips(par1, par2);
      if(this.theSlot != null && this.theSlot.getHasStack()) {
         ItemStack var20 = this.theSlot.getStack();
         this.drawItemStackTooltip(var20, par1 - k, par2 - l);
      }

      GL11.glPopMatrix();
      GL11.glEnable(2896);
      GL11.glEnable(2929);
      RenderHelper.enableStandardItemLighting();
   }

   @SideOnly(Side.CLIENT)
   protected void drawHoveringText2(List par1List, int par2, int par3, FontRenderer font) {
      if(!par1List.isEmpty()) {
         GL11.glDisable('耺');
         RenderHelper.disableStandardItemLighting();
         GL11.glDisable(2896);
         GL11.glDisable(2929);
         int k = 0;
         Iterator iterator = par1List.iterator();

         while(iterator.hasNext()) {
            ItemStack var16 = (ItemStack)iterator.next();
            byte var17 = 32;
            if(var17 > k) {
               k = 16;
            }
         }

         int var161 = par2 + 12;
         int var171 = par3 - 32;
         byte k1 = 12;
         if(par1List.size() > 1) {
            k += 18 * (par1List.size() - 1);
         }

         if(var161 + k > super.width) {
            ;
         }

         if(var171 + k1 + 6 > super.height) {
            var171 = super.height - k1 - 6;
         }

         super.zLevel = 300.0F;
         itemRenderer.zLevel = 300.0F;
         int l1 = -267386864;
         this.drawGradientRect(var161 - 3, var171 - 4, var161 + k + 3, var171 - 3, l1, l1);
         this.drawGradientRect(var161 - 3, var171 + k1 + 3, var161 + k + 3, var171 + k1 + 4, l1, l1);
         this.drawGradientRect(var161 - 3, var171 - 3, var161 + k + 3, var171 + k1 + 3, l1, l1);
         this.drawGradientRect(var161 - 4, var171 - 3, var161 - 3, var171 + k1 + 3, l1, l1);
         this.drawGradientRect(var161 + k + 3, var171 - 3, var161 + k + 4, var171 + k1 + 3, l1, l1);
         int i2 = 1347420415;
         int j2 = (i2 & 16711422) >> 1 | i2 & -16777216;
         this.drawGradientRect(var161 - 3, var171 - 3 + 1, var161 - 3 + 1, var171 + k1 + 3 - 1, i2, j2);
         this.drawGradientRect(var161 + k + 2, var171 - 3 + 1, var161 + k + 3, var171 + k1 + 3 - 1, i2, j2);
         this.drawGradientRect(var161 - 3, var171 - 3, var161 + k + 3, var171 - 3 + 1, i2, i2);
         this.drawGradientRect(var161 - 3, var171 + k1 + 2, var161 + k + 3, var171 + k1 + 3, j2, j2);

         for(int k2 = 0; k2 < par1List.size(); ++k2) {
            ItemStack s1 = (ItemStack)par1List.get(k2);
            RenderHelper.enableStandardItemLighting();
            Utils.renderItemStack(font, s1, var161, var171 - 1);
            if(s1.stackSize > 1) {
               GL11.glPushMatrix();
               float scale = 0.5F;
               GL11.glScalef(scale, scale, scale);
               font.drawString(s1.stackSize + "", (int)((float)var161 / scale) + 24, (int)((float)var171 / scale) - 1, Color.WHITE.getRGB(), true);
               GL11.glPopMatrix();
            }

            RenderHelper.disableStandardItemLighting();
            var161 += 18;
         }

         super.zLevel = 0.0F;
         itemRenderer.zLevel = 0.0F;
         GL11.glEnable(2896);
         GL11.glEnable(2929);
         RenderHelper.enableStandardItemLighting();
         GL11.glEnable('耺');
      }

   }

   private void drawItemStack(ItemStack par1ItemStack, int par2, int par3, String par4Str) {
      GL11.glTranslatef(0.0F, 0.0F, 32.0F);
      super.zLevel = 500.0F;
      itemRenderer.zLevel = 500.0F;
      FontRenderer font = null;
      if(par1ItemStack != null) {
         font = par1ItemStack.getItem().getFontRenderer(par1ItemStack);
      }

      if(font == null) {
         font = super.fontRenderer;
      }

      itemRenderer.renderItemAndEffectIntoGUI(font, super.mc.renderEngine, par1ItemStack, par2, par3);
      itemRenderer.renderItemOverlayIntoGUI(font, super.mc.renderEngine, par1ItemStack, par2, par3 - (this.draggedStack == null?0:8), par4Str);
      super.zLevel = 0.0F;
      itemRenderer.zLevel = 0.0F;
   }

   public List handleTooltip(int mousex, int mousey, List currenttip) {
      return currenttip;
   }

   public List handleItemTooltip(ItemStack stack, int mousex, int mousey, List currenttip) {
      return currenttip;
   }

   public Integer getKDCharge(ItemStack armor) {
      if(armor.stackSize > 1) {
         return null;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return Integer.valueOf(nbtData.getByte("kdi"));
      }
   }
   
   public Integer getExoCharge(ItemStack armor) {
       if(armor.stackSize > 1) {
          return null;
       } else {
          NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
          return Integer.valueOf(nbtData.getByte("exo"));
       }
    }

   public Integer getCDCharge(ItemStack armor) {
      if(armor.stackSize > 1) {
         return null;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return Integer.valueOf(nbtData.getByte("cd"));
      }
   }

   public Integer getEPCharge(ItemStack armor) {
      if(armor.stackSize > 1) {
         return null;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return Integer.valueOf(nbtData.getByte("epc"));
      }
   }

   public Integer getDEPCharge(ItemStack armor) {
      if(armor.stackSize > 1) {
         return null;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return Integer.valueOf(nbtData.getByte("depc"));
      }
   }

   public Integer getYcItem(NBTTagCompound nbtData) {
      return Integer.valueOf(nbtData.getInteger("yc"));
   }
   
   public Integer getWither(NBTTagCompound n) {
	      return Integer.valueOf(n.getInteger("wither"));
	   }

   public String getInfo(ItemStack armor) {
      LavaChestPlateItem item = (LavaChestPlateItem)armor.getItem();
      if(armor.stackSize > 1) {
         return "0/0";
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         int charge = nbtData.getInteger("charge");
         int amp = item.getAmp(nbtData).intValue();
         int dss = item.getDarkShield(nbtData);
         int exo = item.getExoItem(nbtData);
         int heb = item.getHebItem(nbtData);
         int heba = item.getHell(nbtData);
         int heb2 = item.getHebItem2(nbtData);
         int yc = item.getYcItem(nbtData).intValue();
         int shi = item.getShitItem(nbtData).intValue();
         int lpm = item.getLPM(nbtData).intValue();
         int lptm = item.getLPTM(nbtData).intValue();
         int wither = item.getWither(nbtData).intValue();
         return charge + "/" + (wither + 1000 + amp + dss * 500 + exo * 5000 + heba * 2000 + heb * 1000 + yc * 500 + shi + lpm + lptm) + ":" + amp + ":" + dss + ":" + exo + ":" + heba + ":" + heb2 + ":" + heb + ":" + yc + ":" + shi + ":" + lpm + ":" + lptm + ":" + wither;
      }
   }

   public int getHell(NBTTagCompound nbtData) {
      return nbtData.getByte("hell");
   }

   public Integer getLitres(NBTTagCompound n) {
	      return Integer.valueOf(n.getInteger("lavalitres$1"));
	   }
	   public Integer getLitres1(NBTTagCompound n) {
		      return Integer.valueOf(n.getInteger("lavalitres$2"));
		   } 
	   public Integer getLitres2(NBTTagCompound n) {
		      return Integer.valueOf(n.getInteger("lavalitres$3"));
		   }
	   public Integer getLitres3(NBTTagCompound n) {
		      return Integer.valueOf(n.getInteger("lavalitres$4"));
		   }
	   public Integer getLitres4(NBTTagCompound n) {
		      return Integer.valueOf(n.getInteger("lavalitres$5"));
		   }
	   public Integer getLitres5(NBTTagCompound n) {
		      return Integer.valueOf(n.getInteger("lavalitres$6"));
		   }
	   public Integer getLitres6(NBTTagCompound n) {
		      return Integer.valueOf(n.getInteger("lavalitres$7"));
		   }
	   public Integer getLitres7(NBTTagCompound n) {
		      return Integer.valueOf(n.getInteger("lavalitres$8"));
		   }
	   public Integer getLitres8(NBTTagCompound n) {
		      return Integer.valueOf(n.getInteger("lavalitres$9"));
		   }
	   public Integer getLitres9(NBTTagCompound n) {
		      return Integer.valueOf(n.getInteger("lavalitres$10"));
		   }
	   public Integer getLitres10(NBTTagCompound n) {
		      return Integer.valueOf(n.getInteger("lavalitres$11"));
		   }
	   public Integer getLitres11(NBTTagCompound n) {
		      return Integer.valueOf(n.getInteger("lavalitres$12"));
		   }
   public Integer getColor(NBTTagCompound nbtData) {
      return Integer.valueOf(nbtData.hasKey("colorsa")?nbtData.getInteger("colorsa"):-1);
   }

   public int getHebItem(NBTTagCompound nbtData) {
      return nbtData.getByte("heb");
   }
   
   public int getItKorona(NBTTagCompound nbtData)
   {
	 return nbtData.getByte("itKorona");
   }

   protected void drawItemStackTooltip(ItemStack par1ItemStack, int par2, int par3) {
      FontRenderer var6 = par1ItemStack.getItem().getFontRenderer(par1ItemStack);
      if(GuiScreen.isShiftKeyDown() && par1ItemStack.getItem() instanceof LavaChestPlateItem && par1ItemStack.stackSize <= 1) {
         String[] info = this.getInfo(par1ItemStack).split(":");
         ArrayList list2 = new ArrayList();
         if((Integer.valueOf(info[3]).intValue() != 0 || Integer.valueOf(info[3]).intValue() == 20) && Integer.valueOf(info[3]).intValue() != 0 && Integer.valueOf(info[3]).intValue() == 20) {
            list2.add(new ItemStack(ModuleCore.exoItem[1], 1));
         }

         if(this.getHell(par1ItemStack.stackTagCompound) > 0) {
            list2.add(new ItemStack(ModuleCore.itemHeb[1], 1));
         }
         
         if(this.getItKorona(par1ItemStack.stackTagCompound) > 0) {
             list2.add(new ItemStack(ModuleCore.itemKorona[1], 1));
         }

         if(Integer.valueOf(info[1]).intValue() / 500 != 0) {
            list2.add(new ItemStack(ModuleCore.itemCap1[1], Integer.valueOf(info[1]).intValue() / 500));
         }
         

         if(par1ItemStack.getTagCompound() != null) {
            if(this.getKDCharge(par1ItemStack).intValue() > 0) {
               list2.add(new ItemStack(ModuleCore.itemKDI[1], 1));
            }

            if(par1ItemStack.stackTagCompound.getInteger("lpm") != 0) {
               list2.add(new ItemStack(ModuleCore.item[1], 1));
            }

            if(par1ItemStack.stackTagCompound.getInteger("lptm") != 0) {
               list2.add(new ItemStack(ModuleCore.item[2], 1));
            }
            
            if(par1ItemStack.stackTagCompound.getByte("exo") != 0) {
                list2.add(new ItemStack(ModuleCore.exoItem[1], 1));
             }
         }
         if(this.getLitres(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 0 && this.getLitres(Utils.getOrCreateNbtData(par1ItemStack)).intValue() < 100) {
            list2.add(new ItemStack(LavaChestPlate.Umc, this.getLitres(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
         } else if(this.getLitres(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 100) {
            list2.add(new ItemStack(LavaChestPlate.Umc, 100));
         }
         if(this.getLitres1(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 0 && this.getLitres1(Utils.getOrCreateNbtData(par1ItemStack)).intValue() < 100) {
             list2.add(new ItemStack(LavaChestPlate.Umh, this.getLitres1(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
          } else if(this.getLitres1(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 100) {
             list2.add(new ItemStack(LavaChestPlate.Umh, 100));
          }
         if(this.getLitres2(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 0 && this.getLitres2(Utils.getOrCreateNbtData(par1ItemStack)).intValue() < 100) {
             list2.add(new ItemStack(LavaChestPlate.Uml, this.getLitres2(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
          } else if(this.getLitres2(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 100) {
             list2.add(new ItemStack(LavaChestPlate.Uml, 100));
          }
         if(this.getLitres3(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 0 && this.getLitres3(Utils.getOrCreateNbtData(par1ItemStack)).intValue() < 100) {
             list2.add(new ItemStack(LavaChestPlate.Umb, this.getLitres3(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
          } else if(this.getLitres3(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 100) {
             list2.add(new ItemStack(LavaChestPlate.Umb, 100));
          }
         if(this.getLitres4(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 0 && this.getLitres4(Utils.getOrCreateNbtData(par1ItemStack)).intValue() < 100) {
             list2.add(new ItemStack(LavaChestPlate.Hmh, this.getLitres4(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
          } else if(this.getLitres4(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 100) {
             list2.add(new ItemStack(LavaChestPlate.Hmh, 100));
          }
         if(this.getLitres5(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 0 && this.getLitres5(Utils.getOrCreateNbtData(par1ItemStack)).intValue() < 100) {
             list2.add(new ItemStack(LavaChestPlate.Hmc, this.getLitres5(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
          } else if(this.getLitres5(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 100) {
             list2.add(new ItemStack(LavaChestPlate.Hmc, 100));
          }
         if(this.getLitres6(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 0 && this.getLitres6(Utils.getOrCreateNbtData(par1ItemStack)).intValue() < 100) {
             list2.add(new ItemStack(LavaChestPlate.Hml, this.getLitres6(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
          } else if(this.getLitres6(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 100) {
             list2.add(new ItemStack(LavaChestPlate.Hmh, 100));
          }
         if(this.getLitres7(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 0 && this.getLitres7(Utils.getOrCreateNbtData(par1ItemStack)).intValue() < 100) {
             list2.add(new ItemStack(LavaChestPlate.Hmb, this.getLitres7(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
          } else if(this.getLitres7(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 100) {
             list2.add(new ItemStack(LavaChestPlate.Hmb, 100));
          }
         
         
         
         if(this.getLitres8(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 0 && this.getLitres8(Utils.getOrCreateNbtData(par1ItemStack)).intValue() < 100) {
             list2.add(new ItemStack(LavaChestPlate.Omh, this.getLitres8(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
          } else if(this.getLitres8(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 100) {
             list2.add(new ItemStack(LavaChestPlate.Omh, 100));
          }
         if(this.getLitres9(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 0 && this.getLitres9(Utils.getOrCreateNbtData(par1ItemStack)).intValue() < 100) {
             list2.add(new ItemStack(LavaChestPlate.Omc, this.getLitres9(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
          } else if(this.getLitres9(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 100) {
             list2.add(new ItemStack(LavaChestPlate.Omc, 100));
          }
         if(this.getLitres10(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 0 && this.getLitres10(Utils.getOrCreateNbtData(par1ItemStack)).intValue() < 100) {
             list2.add(new ItemStack(LavaChestPlate.Oml, this.getLitres10(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
          } else if(this.getLitres10(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 100) {
             list2.add(new ItemStack(LavaChestPlate.Omh, 100));
          }
         if(this.getLitres11(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 0 && this.getLitres11(Utils.getOrCreateNbtData(par1ItemStack)).intValue() < 100) {
             list2.add(new ItemStack(LavaChestPlate.Omb, this.getLitres11(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
          } else if(this.getLitres11(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 100) {
             list2.add(new ItemStack(LavaChestPlate.Omb, 100));
          }
         
         
         if(this.getWither(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 0 && this.getWither(Utils.getOrCreateNbtData(par1ItemStack)).intValue() < 100) {
             list2.add(new ItemStack(ModuleCore.itemWither[1], this.getWither(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
          } else if(this.getWither(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > 100) {
             list2.add(new ItemStack(ModuleCore.itemWither[1], 10));
          }

         if(Integer.valueOf(info[2]).intValue() != 0 && Integer.valueOf(info[2]).intValue() != 0) {
            list2.add(new ItemStack(ModuleCore.itemDark[9 + Integer.valueOf(info[2]).intValue()], 1, Integer.valueOf(info[2]).intValue()));
         }

         if(this.getCDCharge(par1ItemStack).intValue() > 0) {
            list2.add(new ItemStack(ModuleCore.itemKrid[1], this.getCDCharge(par1ItemStack).intValue()));
         }

         if(this.getYcItem(par1ItemStack.stackTagCompound).intValue() > 0) {
            list2.add(new ItemStack(ModuleCore.itemCap3[1], this.getYcItem(par1ItemStack.stackTagCompound).intValue()));
         }

         if(this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue() > -1) {
            if(this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue() == 0) {
               list2.add(new ItemStack(ModuleCore.itemColor[16], 1, this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
            } else if(this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue() == 1) {
               list2.add(new ItemStack(ModuleCore.itemColor[17], 1, this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
            } else if(this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue() == 2) {
               list2.add(new ItemStack(ModuleCore.itemColor[18], 1, this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
            } else if(this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue() == 3) {
               list2.add(new ItemStack(ModuleCore.itemColor[19], 1, this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
            } else if(this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue() == 4) {
               list2.add(new ItemStack(ModuleCore.itemColor[20], 1, this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
            } else if(this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue() == 5) {
               list2.add(new ItemStack(ModuleCore.itemColor[21], 1, this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
            } else if(this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue() == 6) {
               list2.add(new ItemStack(ModuleCore.itemColor[22], 1, this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
            } else if(this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue() == 7) {
               list2.add(new ItemStack(ModuleCore.itemColor[23], 1, this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
            } else if(this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue() == 8) {
               list2.add(new ItemStack(ModuleCore.itemColor[24], 1, this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
            } else if(this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue() == 9) {
               list2.add(new ItemStack(ModuleCore.itemColor[25], 1, this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
            } else if(this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue() == 10) {
               list2.add(new ItemStack(ModuleCore.itemColor[26], 1, this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
            } else if(this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue() == 11) {
               list2.add(new ItemStack(ModuleCore.itemColor[27], 1, this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
            } else if(this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue() == 12) {
               list2.add(new ItemStack(ModuleCore.itemColor[28], 1, this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
            } else if(this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue() == 13) {
               list2.add(new ItemStack(ModuleCore.itemColor[29], 1, this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
            } else if(this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue() == 14) {
               list2.add(new ItemStack(ModuleCore.itemColor[30], 1, this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
            } else if(this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue() == 15) {
               list2.add(new ItemStack(ModuleCore.itemColor[31], 1, this.getColor(Utils.getOrCreateNbtData(par1ItemStack)).intValue()));
            }
         }

         this.drawHoveringText2(list2, par2, par3, var6 == null?super.fontRenderer:var6);
      }

   }

   /** @deprecated */
   @Deprecated
   protected void drawCreativeTabHoveringText(String par1Str, int par2, int par3) {
      this.func_102021_a(Arrays.asList(new String[]{par1Str}), par2, par3);
   }

   /** @deprecated */
   @Deprecated
   protected void func_102021_a(List par1List, int par2, int par3) {
      this.drawHoveringText(par1List, par2, par3, super.fontRenderer);
   }

   /** @deprecated */
   @Deprecated
   protected void drawHoveringText(List par1List, int par2, int par3, FontRenderer font) {
      if(!par1List.isEmpty()) {
         GL11.glDisable('耺');
         RenderHelper.disableStandardItemLighting();
         GL11.glDisable(2896);
         GL11.glDisable(2929);
         int k = 0;
         Iterator iterator = par1List.iterator();

         int j1;
         while(iterator.hasNext()) {
            String var151 = (String)iterator.next();
            j1 = font.getStringWidth(var151);
            if(j1 > k) {
               k = j1;
            }
         }

         int var15 = par2 + 12;
         j1 = par3 - 12;
         int k1 = 8;
         if(par1List.size() > 1) {
            k1 += 2 + (par1List.size() - 1) * 10;
         }

         if(var15 + k > super.width) {
            var15 -= 28 + k;
         }

         if(j1 + k1 + 6 > super.height) {
            j1 = super.height - k1 - 6;
         }

         super.zLevel = 300.0F;
         itemRenderer.zLevel = 300.0F;
         int l1 = -267386864;
         this.drawGradientRect(var15 - 3, j1 - 4, var15 + k + 3, j1 - 3, l1, l1);
         this.drawGradientRect(var15 - 3, j1 + k1 + 3, var15 + k + 3, j1 + k1 + 4, l1, l1);
         this.drawGradientRect(var15 - 3, j1 - 3, var15 + k + 3, j1 + k1 + 3, l1, l1);
         this.drawGradientRect(var15 - 4, j1 - 3, var15 - 3, j1 + k1 + 3, l1, l1);
         this.drawGradientRect(var15 + k + 3, j1 - 3, var15 + k + 4, j1 + k1 + 3, l1, l1);
         int i2 = 1347420415;
         int j2 = (i2 & 16711422) >> 1 | i2 & -16777216;
         this.drawGradientRect(var15 - 3, j1 - 3 + 1, var15 - 3 + 1, j1 + k1 + 3 - 1, i2, j2);
         this.drawGradientRect(var15 + k + 2, j1 - 3 + 1, var15 + k + 3, j1 + k1 + 3 - 1, i2, j2);
         this.drawGradientRect(var15 - 3, j1 - 3, var15 + k + 3, j1 - 3 + 1, i2, i2);
         this.drawGradientRect(var15 - 3, j1 + k1 + 2, var15 + k + 3, j1 + k1 + 3, j2, j2);

         for(int k2 = 0; k2 < par1List.size(); ++k2) {
            String s1 = (String)par1List.get(k2);
            super.fontRenderer.drawStringWithShadow(s1, var15, j1, -1);
            if(k2 == 0) {
               j1 += 2;
            }

            j1 += 10;
         }

         super.zLevel = 0.0F;
         itemRenderer.zLevel = 0.0F;
         GL11.glEnable(2896);
         GL11.glEnable(2929);
         RenderHelper.enableStandardItemLighting();
         GL11.glEnable('耺');
      }

   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
   }

   protected abstract void drawGuiContainerBackgroundLayer(float var1, int var2, int var3);

   protected void drawSlotInventory(Slot par1Slot) {
      int i = par1Slot.xDisplayPosition;
      int j = par1Slot.yDisplayPosition;
      ItemStack itemstack = par1Slot.getStack();
      boolean flag = false;
      boolean flag1 = par1Slot == this.clickedSlot && this.draggedStack != null && !this.isRightMouseClick;
      ItemStack itemstack1 = super.mc.thePlayer.inventory.getItemStack();
      String s = null;
      if(par1Slot == this.clickedSlot && this.draggedStack != null && this.isRightMouseClick && itemstack != null) {
         itemstack = itemstack.copy();
         itemstack.stackSize /= 2;
      } else if(this.field_94076_q && this.field_94077_p.contains(par1Slot) && itemstack1 != null) {
         if(this.field_94077_p.size() == 1) {
            return;
         }

         if(Container.func_94527_a(par1Slot, itemstack1, true) && this.inventorySlots.func_94531_b(par1Slot)) {
            itemstack = itemstack1.copy();
            flag = true;
            Container.func_94525_a(this.field_94077_p, this.field_94071_C, itemstack, par1Slot.getStack() == null?0:par1Slot.getStack().stackSize);
            if(itemstack.stackSize > itemstack.getMaxStackSize()) {
               s = "" + EnumChatFormatting.YELLOW + itemstack.getMaxStackSize();
               itemstack.stackSize = itemstack.getMaxStackSize();
            }

            if(itemstack.stackSize > par1Slot.getSlotStackLimit()) {
               s = "" + EnumChatFormatting.YELLOW + par1Slot.getSlotStackLimit();
               itemstack.stackSize = par1Slot.getSlotStackLimit();
            }
         } else {
            this.field_94077_p.remove(par1Slot);
            this.func_94066_g();
         }
      }

      super.zLevel = 100.0F;
      itemRenderer.zLevel = 100.0F;
      if(itemstack == null) {
         Icon icon = par1Slot.getBackgroundIconIndex();
         if(icon != null) {
            GL11.glDisable(2896);
            super.mc.renderEngine.bindTexture("/gui/items.png");
            this.drawTexturedModelRectFromIcon(i, j, icon, 16, 16);
            GL11.glEnable(2896);
            flag1 = true;
         }
      }

      if(!flag1) {
         if(flag) {
            drawRect(i, j, i + 16, j + 16, -2130706433);
         }

         this.manager.renderSlotUnderlay(par1Slot);
         GL11.glEnable(2929);
         this.drawSlotItem(par1Slot, itemstack, i, j, s);
         this.manager.renderSlotOverlay(par1Slot);
      }

      itemRenderer.zLevel = 0.0F;
      super.zLevel = 0.0F;
   }

   public void drawSlotItem(Slot par1Slot, ItemStack itemstack, int i, int j, String s) {
      itemRenderer.renderItemAndEffectIntoGUI(super.fontRenderer, super.mc.renderEngine, itemstack, i, j);
      itemRenderer.renderItemOverlayIntoGUI(super.fontRenderer, super.mc.renderEngine, itemstack, i, j, s);
   }

   private void func_94066_g() {
      ItemStack itemstack = super.mc.thePlayer.inventory.getItemStack();
      if(itemstack != null && this.field_94076_q) {
         this.field_94069_F = itemstack.stackSize;

         ItemStack itemstack1;
         int i;
         for(Iterator iterator = this.field_94077_p.iterator(); iterator.hasNext(); this.field_94069_F -= itemstack1.stackSize - i) {
            Slot slot = (Slot)iterator.next();
            itemstack1 = itemstack.copy();
            i = slot.getStack() == null?0:slot.getStack().stackSize;
            Container.func_94525_a(this.field_94077_p, this.field_94071_C, itemstack1, i);
            if(itemstack1.stackSize > itemstack1.getMaxStackSize()) {
               itemstack1.stackSize = itemstack1.getMaxStackSize();
            }

            if(itemstack1.stackSize > slot.getSlotStackLimit()) {
               itemstack1.stackSize = slot.getSlotStackLimit();
            }
         }
      }

   }

   public Slot getSlotAtPosition(int par1, int par2) {
      for(int k = 0; k < this.inventorySlots.inventorySlots.size(); ++k) {
         Slot slot = (Slot)this.inventorySlots.inventorySlots.get(k);
         if(this.isMouseOverSlot(slot, par1, par2)) {
            return slot;
         }
      }

      return null;
   }

   protected void mouseClicked(int par1, int par2, int par3) {
      super.mouseClicked(par1, par2, par3);
      this.field_94068_E = true;
      if(!this.manager.mouseClicked(par1, par2, par3)) {
         boolean flag = par3 == super.mc.gameSettings.keyBindPickBlock.keyCode + 100;
         Slot slot = this.getSlotAtPosition(par1, par2);
         long l = Minecraft.getSystemTime();
         this.field_94074_J = this.field_94072_H == slot && l - this.field_94070_G < 250L && this.field_94073_I == par3;
         this.field_94068_E = false;
         if(par3 == 0 || par3 == 1 || flag) {
            int i1 = this.guiLeft;
            int j1 = this.guiTop;
            boolean flag1 = (par1 < i1 || par2 < j1 || par1 >= i1 + this.xSize || par2 >= j1 + this.ySize) && slot == null;
            int k1 = -1;
            if(slot != null) {
               k1 = slot.slotNumber;
            }

            if(flag1) {
               k1 = -999;
            }

            if(super.mc.gameSettings.touchscreen && flag1 && super.mc.thePlayer.inventory.getItemStack() == null) {
               super.mc.displayGuiScreen((GuiScreen)null);
               return;
            }

            if(k1 != -1) {
               if(super.mc.gameSettings.touchscreen) {
                  if(slot != null && slot.getHasStack()) {
                     this.clickedSlot = slot;
                     this.draggedStack = null;
                     this.isRightMouseClick = par3 == 1;
                  } else {
                     this.clickedSlot = null;
                  }
               } else if(!this.field_94076_q) {
                  if(super.mc.thePlayer.inventory.getItemStack() != null) {
                     this.field_94076_q = true;
                     this.field_94067_D = par3;
                     this.field_94077_p.clear();
                     if(par3 == 0) {
                        this.field_94071_C = 0;
                     } else if(par3 == 1) {
                        this.field_94071_C = 1;
                     }
                  } else {
                     if(par3 == super.mc.gameSettings.keyBindPickBlock.keyCode + 100) {
                        this.manager.handleMouseClick(slot, k1, par3, 3);
                     } else {
                        boolean flag2 = k1 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
                        byte b0 = 0;
                        if(!flag2) {
                           if(k1 == -999) {
                              b0 = 4;
                           }
                        } else {
                           this.field_94075_K = slot != null && slot.getHasStack()?slot.getStack():null;
                           b0 = 1;
                        }

                        this.manager.handleMouseClick(slot, k1, par3, b0);
                     }

                     this.field_94068_E = true;
                  }
               }
            }
         }

         this.field_94072_H = slot;
         this.field_94070_G = l;
         this.field_94073_I = par3;
      }

   }

   protected void func_85041_a(int par1, int par2, int par3, long par4) {
      Slot slot = this.getSlotAtPosition(par1, par2);
      ItemStack itemstack = super.mc.thePlayer.inventory.getItemStack();
      this.manager.mouseDragged(par1, par2, par3, par4);
      if(this.clickedSlot != null && super.mc.gameSettings.touchscreen) {
         if(par3 == 0 || par3 == 1) {
            if(this.draggedStack == null) {
               if(slot != this.clickedSlot) {
                  this.draggedStack = this.clickedSlot.getStack().copy();
               }
            } else if(this.draggedStack.stackSize > 1 && slot != null && Container.func_94527_a(slot, this.draggedStack, false)) {
               long i1 = Minecraft.getSystemTime();
               if(this.field_92033_y == slot) {
                  if(i1 - this.field_92032_z > 500L) {
                     this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, 0);
                     this.handleMouseClick(slot, slot.slotNumber, 1, 0);
                     this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, 0);
                     this.field_92032_z = i1 + 750L;
                     --this.draggedStack.stackSize;
                  }
               } else {
                  this.field_92033_y = slot;
                  this.field_92032_z = i1;
               }
            }
         }
      } else if(this.field_94076_q && slot != null && itemstack != null && itemstack.stackSize > this.field_94077_p.size() && Container.func_94527_a(slot, itemstack, true) && slot.isItemValid(itemstack) && this.inventorySlots.func_94531_b(slot)) {
         this.field_94077_p.add(slot);
         this.func_94066_g();
      }

   }

   protected void mouseMovedOrUp(int par1, int par2, int par3) {
      Slot slot = this.getSlotAtPosition(par1, par2);
      int l = this.guiLeft;
      int i1 = this.guiTop;
      boolean flag = par1 < l || par2 < i1 || par1 >= l + this.xSize || par2 >= i1 + this.ySize;
      int j1 = -1;
      if(slot != null) {
         j1 = slot.slotNumber;
      }

      if(flag) {
         j1 = -999;
      }

      Slot slot1;
      Iterator iterator;
      if(this.field_94074_J && slot != null && par3 == 0 && this.inventorySlots.func_94530_a((ItemStack)null, slot)) {
         if(isShiftKeyDown()) {
            if(slot != null && slot.inventory != null && this.field_94075_K != null) {
               iterator = this.inventorySlots.inventorySlots.iterator();

               while(iterator.hasNext()) {
                  slot1 = (Slot)iterator.next();
                  if(slot1 != null && slot1.canTakeStack(super.mc.thePlayer) && slot1.getHasStack() && slot1.inventory == slot.inventory && Container.func_94527_a(slot1, this.field_94075_K, true)) {
                     this.handleMouseClick(slot1, slot1.slotNumber, par3, 1);
                  }
               }
            }
         } else {
            this.handleMouseClick(slot, j1, par3, 6);
         }

         this.field_94074_J = false;
         this.field_94070_G = 0L;
      } else {
         if(this.field_94076_q && this.field_94067_D != par3) {
            this.field_94076_q = false;
            this.field_94077_p.clear();
            this.field_94068_E = true;
            return;
         }

         if(this.field_94068_E) {
            this.manager.mouseUp(par1, par2, par3);
            this.field_94068_E = false;
            return;
         }

         boolean flag1;
         if(this.clickedSlot != null && super.mc.gameSettings.touchscreen) {
            if(par3 == 0 || par3 == 1) {
               if(this.draggedStack == null && slot != this.clickedSlot) {
                  this.draggedStack = this.clickedSlot.getStack();
               }

               flag1 = Container.func_94527_a(slot, this.draggedStack, false);
               if(j1 != -1 && this.draggedStack != null && flag1) {
                  this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, par3, 0);
                  this.handleMouseClick(slot, j1, 0, 0);
                  if(super.mc.thePlayer.inventory.getItemStack() != null) {
                     this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, par3, 0);
                     this.field_85049_r = par1 - l;
                     this.field_85048_s = par2 - i1;
                     this.returningStackDestSlot = this.clickedSlot;
                     this.returningStack = this.draggedStack;
                     this.returningStackTime = Minecraft.getSystemTime();
                  } else {
                     this.returningStack = null;
                  }
               } else if(this.draggedStack != null) {
                  this.field_85049_r = par1 - l;
                  this.field_85048_s = par2 - i1;
                  this.returningStackDestSlot = this.clickedSlot;
                  this.returningStack = this.draggedStack;
                  this.returningStackTime = Minecraft.getSystemTime();
               }

               this.draggedStack = null;
               this.clickedSlot = null;
            }
         } else if(this.field_94076_q && !this.field_94077_p.isEmpty()) {
            this.handleMouseClick((Slot)null, -999, Container.func_94534_d(0, this.field_94071_C), 5);
            iterator = this.field_94077_p.iterator();

            while(iterator.hasNext()) {
               slot1 = (Slot)iterator.next();
               this.handleMouseClick(slot1, slot1.slotNumber, Container.func_94534_d(1, this.field_94071_C), 5);
            }

            this.handleMouseClick((Slot)null, -999, Container.func_94534_d(2, this.field_94071_C), 5);
         } else if(super.mc.thePlayer.inventory.getItemStack() != null) {
            if(par3 == super.mc.gameSettings.keyBindPickBlock.keyCode + 100) {
               this.handleMouseClick(slot, j1, par3, 3);
            } else {
               flag1 = j1 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
               if(flag1) {
                  this.field_94075_K = slot != null && slot.getHasStack()?slot.getStack():null;
               }

               this.handleMouseClick(slot, j1, par3, flag1?1:0);
            }
         } else if(par3 >= 0) {
            this.manager.mouseUp(par1, par2, par3);
         }
      }

      if(super.mc.thePlayer.inventory.getItemStack() == null) {
         this.field_94070_G = 0L;
      }

      this.field_94076_q = false;
   }

   private boolean isMouseOverSlot(Slot par1Slot, int par2, int par3) {
      return this.isPointInRegion(par1Slot.xDisplayPosition, par1Slot.yDisplayPosition, 16, 16, par2, par3);
   }

   protected boolean isPointInRegion(int par1, int par2, int par3, int par4, int par5, int par6) {
      int k1 = this.guiLeft;
      int l1 = this.guiTop;
      par5 -= k1;
      par6 -= l1;
      return par5 >= par1 - 1 && par5 < par1 + par3 + 1 && par6 >= par2 - 1 && par6 < par2 + par4 + 1;
   }

   public void handleMouseClick(Slot par1Slot, int par2, int par3, int par4) {
      if(par1Slot != null) {
         par2 = par1Slot.slotNumber;
      }

      if(par2 != -1) {
         if(this.isClientOnly()) {
            super.mc.thePlayer.openContainer.slotClick(par2, par3, par4, super.mc.thePlayer);
         } else {
            super.mc.playerController.windowClick(this.inventorySlots.windowId, par2, par3, par4, super.mc.thePlayer);
         }
      }

   }

   public boolean isClientOnly() {
      return false;
   }

   protected void keyTyped(char par1, int par2) {
      if(par2 == 1) {
         super.mc.thePlayer.closeScreen();
      } else if(!this.manager.lastKeyTyped(par2, par1)) {
         this.checkHotbarKeys(par2);
         if(this.theSlot != null && this.theSlot.getHasStack()) {
            if(par2 == super.mc.gameSettings.keyBindPickBlock.keyCode) {
               this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, 0, 3);
            } else if(par2 == super.mc.gameSettings.keyBindDrop.keyCode) {
               this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, isCtrlKeyDown()?1:0, 4);
            }
         }

         if(par2 == super.mc.gameSettings.keyBindInventory.keyCode) {
            super.mc.thePlayer.closeScreen();
         }
      }

   }

   protected boolean checkHotbarKeys(int par1) {
      if(super.mc.thePlayer.inventory.getItemStack() == null && this.theSlot != null) {
         for(int j = 0; j < 9; ++j) {
            if(par1 == 2 + j) {
               this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, j, 2);
               return true;
            }
         }
      }

      return false;
   }

   public void onGuiClosed() {
      if(super.mc.thePlayer != null) {
         this.inventorySlots.onCraftGuiClosed(super.mc.thePlayer);
      }

   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   public void updateScreen() {
      super.updateScreen();
      this.manager.guiTick();
      if(!super.mc.thePlayer.isEntityAlive() || super.mc.thePlayer.isDead) {
         super.mc.thePlayer.closeScreen();
      }

   }

   public void handleKeyboardInput() {
      if(Keyboard.getEventKeyState()) {
         if(Keyboard.getEventKey() == 87) {
            super.mc.toggleFullscreen();
            return;
         }

         if(this.manager.firstKeyTyped(Keyboard.getEventKey(), Keyboard.getEventCharacter())) {
            return;
         }

         this.keyTyped(Keyboard.getEventCharacter(), Keyboard.getEventKey());
      }

   }

   public void handleMouseInput() {
      super.handleMouseInput();
      int i = Mouse.getEventDWheel();
      if(i != 0) {
         this.manager.mouseWheel(i > 0?1:-1);
      }

   }

   public void refresh() {
      this.manager.refresh();
   }
}

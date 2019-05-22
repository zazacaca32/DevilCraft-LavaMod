package codechicken.nei;

import codechicken.nei.GuiEnchantmentModifier;
import codechicken.nei.NEICPH;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIServerUtils;
import java.util.ArrayList;
import net.minecraft.client.gui.Gui;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class ContainerEnchantmentModifier extends ContainerEnchantment {

   public ArrayList slotEnchantment = new ArrayList();
   int level = 5;
   public int scrollclicky = -1;
   public float scrollpercent;
   public int scrollmousey;
   public float percentscrolled;
   public int relx = 60;
   public int rely = 14;
   public int height = 57;
   public int cwidth = 101;
   public int slotheight = 19;
   public GuiEnchantmentModifier parentscreen;


   public ContainerEnchantmentModifier(InventoryPlayer inventoryplayer, World world, int i, int j, int k) {
      super(inventoryplayer, world, i, j, k);
   }

   public int getNumSlots() {
      return this.slotEnchantment.size();
   }

   public int getScrollBarHeight() {
      int sbarh = (int)((float)this.height / (float)this.getContentHeight() * (float)this.height);
      return sbarh > this.height?this.height:(sbarh < this.height / 15?this.height / 15:sbarh);
   }

   public int getScrollBarWidth() {
      return 7;
   }

   public int getContentHeight() {
      return this.slotheight * this.getNumSlots();
   }

   public int getScrolledSlots() {
      int slots = this.getNumSlots();
      int shownslots = this.height / this.slotheight;
      return (int)(this.percentscrolled * (float)(slots - shownslots) + 0.5F);
   }

   private int getClickedSlot(int mousey) {
      return (mousey - this.rely) / this.slotheight + this.getScrolledSlots();
   }

   public void calculatePercentScrolled() {
      int barempty = this.height - this.getScrollBarHeight();
      int sbary;
      if(this.scrollclicky >= 0) {
         sbary = this.scrollmousey - this.scrollclicky;
         this.percentscrolled = (float)sbary / (float)barempty + this.scrollpercent;
      }

      if(this.percentscrolled < 0.0F) {
         this.percentscrolled = 0.0F;
      }

      if(this.percentscrolled > 1.0F) {
         this.percentscrolled = 1.0F;
      }

      sbary = this.rely + (int)((double)((float)barempty * this.percentscrolled) + 0.5D);
      this.percentscrolled = (float)(sbary - this.rely) / (float)barempty;
   }

   public boolean clickScrollBar(int mousex, int mousey, int button) {
      mousex -= this.parentscreen.guiLeft;
      mousey -= this.parentscreen.guiTop;
      int barempty = this.height - this.getScrollBarHeight();
      int sbary = this.rely + (int)((double)((float)barempty * this.percentscrolled) + 0.5D);
      if(button == 0 && this.getScrollBarHeight() < this.height && mousex >= this.relx + this.cwidth && mousex < this.relx + this.cwidth + this.getScrollBarWidth() && mousey >= this.rely && mousey < this.rely + this.height) {
         if(mousey < sbary) {
            this.percentscrolled = (float)(mousey - this.rely) / (float)barempty;
            this.calculatePercentScrolled();
         } else if(mousey > sbary + this.getScrollBarHeight()) {
            this.percentscrolled = (float)(mousey - this.rely - this.getScrollBarHeight() + 1) / (float)barempty;
            this.calculatePercentScrolled();
         } else {
            this.scrollclicky = mousey;
            this.scrollpercent = this.percentscrolled;
            this.scrollmousey = mousey;
         }

         return true;
      } else {
         return false;
      }
   }

   public void mouseUp(int mousex, int mousey, int button) {
      if(this.scrollclicky >= 0 && button == 0) {
         this.scrollclicky = -1;
      }

   }

   public boolean clickButton(int mousex, int mousey, int button) {
      mousex -= this.parentscreen.guiLeft;
      mousey -= this.parentscreen.guiTop;
      if(mousex >= this.relx && mousex < this.relx + this.cwidth && mousey >= this.rely && mousey <= this.rely + this.height) {
         int slot = this.getClickedSlot(mousey);
         if(slot >= this.getNumSlots()) {
            return false;
         } else {
            this.toggleSlotEnchantment(slot);
            return true;
         }
      } else {
         return false;
      }
   }

   private void toggleSlotEnchantment(int slot) {
      ContainerEnchantmentModifier.EnchantmentHash e = (ContainerEnchantmentModifier.EnchantmentHash)this.slotEnchantment.get(slot);
      if(e.state == 2) {
         NEICPH.sendModifyEnchantment(e.enchantment.effectId, 0, false);
         e.state = 0;
      } else {
         if(e.state == 1) {
            return;
         }

         NEICPH.sendModifyEnchantment(e.enchantment.effectId, this.level, true);
         e.state = 2;
      }

      this.updateEnchantmentOptions();
   }

   public boolean addEnchantment(int e, int level) {
      if(e < Enchantment.enchantmentsList.length && Enchantment.enchantmentsList[e] != null) {
         ((Slot)super.inventorySlots.get(0)).getStack().addEnchantment(Enchantment.enchantmentsList[e], level);
         return true;
      } else {
         return false;
      }
   }

   public void removeEnchantment(int e) {
      ItemStack stack = ((Slot)super.inventorySlots.get(0)).getStack();
      NBTTagList nbttaglist = stack.getEnchantmentTagList();
      if(nbttaglist != null) {
         for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            short ID = ((NBTTagCompound)nbttaglist.tagAt(i)).getShort("id");
            if(ID == e) {
               nbttaglist.removeTag(i);
               if(nbttaglist.tagCount() == 0) {
                  stack.getTagCompound().removeTag("ench");
               }

               if(stack.getTagCompound().hasNoTags()) {
                  stack.setTagCompound((NBTTagCompound)null);
               }

               return;
            }
         }
      }

   }

   public boolean canInteractWith(EntityPlayer entityplayer) {
      return true;
   }

   public void onCraftMatrixChanged(IInventory iinventory) {
      if(this.parentscreen != null) {
         this.updateEnchantmentOptions();
      }

   }

   public void updateEnchantmentOptions() {
      int numoptions = this.slotEnchantment.size();
      this.slotEnchantment.clear();
      ItemStack toolstack = this.getSlot(0).getStack();
      if(toolstack == null) {
         this.percentscrolled = 0.0F;
      } else {
         boolean validate = NEIClientConfig.validateEnchantments();
         Item item = toolstack.getItem();
         int enchantablity = item.getItemEnchantability();
         if(enchantablity == 0 && validate) {
            this.percentscrolled = 0.0F;
         } else {
            Enchantment[] var9 = Enchantment.enchantmentsList;
            int var8 = Enchantment.enchantmentsList.length;

            for(int var7 = 0; var7 < var8; ++var7) {
               Enchantment e = var9[var7];
               if(e != null && e.type != null && (e.type.canEnchantItem(item) || !validate)) {
                  byte state = 0;
                  int level = -1;
                  if(NEIServerUtils.stackHasEnchantment(toolstack, e.effectId)) {
                     state = 2;
                     level = NEIServerUtils.getEnchantmentLevel(toolstack, e.effectId);
                  } else if(NEIServerUtils.doesEnchantmentConflict(NEIServerUtils.getEnchantments(toolstack), e) && validate) {
                     state = 1;
                  }

                  this.slotEnchantment.add(new ContainerEnchantmentModifier.EnchantmentHash(e, state, level));
               }
            }

            if(numoptions != this.slotEnchantment.size()) {
               this.percentscrolled = 0.0F;
            }

         }
      }
   }

   public void drawSlots(GuiEnchantmentModifier gui) {
      for(int slot = 0; slot < 3; ++slot) {
         boolean shade = false;
         String text = "";
         int containerslot = slot + this.getScrolledSlots();
         int var7;
         if(containerslot + 1 > this.slotEnchantment.size()) {
            var7 = 1;
         } else {
            ContainerEnchantmentModifier.EnchantmentHash e = (ContainerEnchantmentModifier.EnchantmentHash)this.slotEnchantment.get(containerslot);
            var7 = e.state;
            text = e.enchantment.getTranslatedName(e.level == -1?this.level:e.level);
            if(gui.mc.fontRenderer.getStringWidth(text) > 95 && text.contains("Projectile")) {
               text = text.replace("Projectile", "Proj");
            }

            if(gui.mc.fontRenderer.getStringWidth(text) > 95 && text.contains("Protection")) {
               text = text.replace("Protection", "Protect");
            }

            if(gui.mc.fontRenderer.getStringWidth(text) > 95 && text.contains("Bane of")) {
               text = text.replace("Bane of ", "");
            }
         }

         gui.mc.renderEngine.bindTexture("/gui/enchant.png");
         GL11.glColor3f(1.0F, 1.0F, 1.0F);
         if(this.hasScrollBar()) {
            gui.drawTexturedModalRect(this.relx, this.rely + slot * this.slotheight, 0, gui.ySize + this.slotheight * var7, this.cwidth - 30, this.slotheight);
            gui.drawTexturedModalRect(this.relx + this.cwidth - 30, this.rely + slot * this.slotheight, this.cwidth - 23, gui.ySize + this.slotheight * var7, 30, this.slotheight);
         } else {
            gui.drawTexturedModalRect(this.relx, this.rely + slot * this.slotheight, 0, gui.ySize + this.slotheight * var7, this.cwidth + 7, this.slotheight);
         }

         gui.fontRenderer.drawString(text, this.relx + 4, this.rely + slot * this.slotheight + 5, this.textColourFromState(var7));
      }

   }

   private boolean hasScrollBar() {
      return this.getNumSlots() > 3;
   }

   public void drawScrollBar(GuiEnchantmentModifier gui) {
      if(this.hasScrollBar()) {
         int sbary = this.rely + (int)((double)((float)(this.height - this.getScrollBarHeight()) * this.percentscrolled) + 0.5D);
         int sbarx = this.relx + this.cwidth;
         Gui.drawRect(sbarx, this.rely, sbarx + this.getScrollBarWidth(), this.rely + this.height, -14671840);
         Gui.drawRect(sbarx, sbary, sbarx + this.getScrollBarWidth(), sbary + this.getScrollBarHeight(), -7631989);
         Gui.drawRect(sbarx, sbary, sbarx + this.getScrollBarWidth() - 1, sbary + this.getScrollBarHeight() - 1, -986896);
         Gui.drawRect(sbarx + 1, sbary + 1, sbarx + this.getScrollBarWidth() - 1, sbary + this.getScrollBarHeight() - 1, -11184811);
         Gui.drawRect(sbarx + 1, sbary + 1, sbarx + this.getScrollBarWidth() - 2, sbary + this.getScrollBarHeight() - 2, -3750202);
      }
   }

   private int textColourFromState(int shade) {
      return shade == 0?6839882:(shade == 1?4226832:16777088);
   }

   public void onUpdate(int mousex, int mousey) {
      this.processScrollMouse(mousex, mousey);
   }

   public void processScrollMouse(int mousex, int mousey) {
      int var10000 = mousex - this.parentscreen.guiLeft;
      mousey -= this.parentscreen.guiTop;
      if(this.scrollclicky >= 0) {
         int scrolldiff = mousey - this.scrollclicky;
         int barupallowed = (int)((double)((float)(this.height - this.getScrollBarHeight()) * this.scrollpercent) + 0.5D);
         int bardownallowed = this.height - this.getScrollBarHeight() - barupallowed;
         if(-scrolldiff > barupallowed) {
            this.scrollmousey = this.scrollclicky - barupallowed;
         } else if(scrolldiff > bardownallowed) {
            this.scrollmousey = this.scrollclicky + bardownallowed;
         } else {
            this.scrollmousey = mousey;
         }

         this.calculatePercentScrolled();
      }

   }

   public static class EnchantmentHash {

      Enchantment enchantment;
      int state;
      int level;


      public EnchantmentHash(Enchantment e, int i, int l) {
         this.enchantment = e;
         this.state = i;
         this.level = l;
      }
   }
}

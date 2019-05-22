package net.minecraft.client.addon.tco.tiny.gui;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.addon.tco.tiny.gui.GuiButtonMan;
import net.minecraft.client.addon.tco.tiny.gui.GuiContainerSlotItem;
import net.minecraft.client.addon.tco.tiny.gui.GuiMob;
import net.minecraft.client.addon.tco.tiny.gui.GuiSlotItem;
import net.minecraft.client.addon.tco.tiny.gui.PacketReciving;
import net.minecraft.client.addon.tco.tiny.packets.old.Packet9priz;
import net.minecraft.client.addon.tco.tiny.playermod.GuiEnum;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class Gui9priz extends GuiContainerSlotItem implements PacketReciving {

   protected GuiMob parentScreen;
   protected int worldNumber;
   int weitch = 20;
   int getIndexGolSlot = 0;
   int countGolosovalka = 0;
   public static ArrayList Sslots = new ArrayList();


   public Gui9priz(GuiMob par1GuiScreen, int par4) {
      super(0, 0, 176, 139);
      this.parentScreen = par1GuiScreen;
      this.worldNumber = par4;
      ArrayList slots = new ArrayList();
      byte x = 8;
      byte y = 42;

      for(int i = 0; i < 3; ++i) {
         for(int j = 0; j < 9; ++j) {
            String[] res;
            if(Sslots.size() > i * 9 + j && (res = ((String)Sslots.get(i * 9 + j)).split(":")) != null && res.length == 4) {
               try {
                  slots.add(new GuiSlotItem(new ItemStack(Integer.parseInt(res[0]), 1, Integer.parseInt(res[1])), "", Integer.parseInt(res[2]), Integer.parseInt(res[3]), i * 9 + j, x + 18 * j, y + 18 * i));
               } catch (Exception var10) {
                  ;
               }
            }
         }
      }

      slots.add(new GuiSlotItem((ItemStack)null, "", 64, 1, slots.size(), 8, 96));
      super.slots = slots;
      this.getIndexGolSlot = super.slots.size() - 1;
      super.xDelta = 107;
      (new Packet9priz()).Write(this.parentScreen.EntityId, this.getGuiName(), -1, (byte)-1);
   }

   public void initGui() {
      int k = (super.width - super.xSize - super.xDelta) / 2;
      int l = (super.height - super.ySize - super.yDelta) / 2;
      super.buttonList.add(new GuiButtonMan(50, k + 98, l + 95, 72, 20, "Списать", 0));
      super.buttonList.add(new GuiButtonMan(51, k + 98, l + 114, 72, 20, "Сбросить", 0));
      super.initGui();
      super.buttonList.add(new GuiButtonMan(1, super.width - 102, super.height - 22, 98, 20, "Выйти", 0));
   }

   protected void actionPerformed(GuiButton par1GuiButton) {
      if(par1GuiButton.id == 51) {
         ((GuiSlotItem)super.slots.get(this.getIndexGolSlot)).setSlotStackCopy((GuiSlotItem)null);
      } else if(par1GuiButton.id != 50) {
         this.parentScreen.x = (float)Mouse.getX();
         this.parentScreen.y = (float)Mouse.getY();
         this.parentScreen.confirmClicked(par1GuiButton.id == 1, this.worldNumber);
      } else {
         GuiSlotItem sl = (GuiSlotItem)super.slots.get(this.getIndexGolSlot);
         if(sl.getHasStack() && this.countGolosovalka - sl.SlotStackCost * sl.itemstack.stackSize >= 0) {
            ItemStack st1 = sl.itemstack;
            Iterator var4 = super.slots.iterator();

            while(var4.hasNext()) {
               GuiSlotItem slot = (GuiSlotItem)var4.next();
               ItemStack st = slot.itemstack;
               if(st.itemID == st1.itemID && st.getItemDamage() == st1.getItemDamage()) {
                  (new Packet9priz()).Write(this.parentScreen.EntityId, this.getGuiName(), sl.SlotStackCost * sl.itemstack.stackSize, (byte)slot.getSlotIndex());
                  ((GuiSlotItem)super.slots.get(this.getIndexGolSlot)).setSlotStackCopy((GuiSlotItem)null);
                  break;
               }
            }

            this.countGolosovalka = 0;
         }
      }

   }

   public void drawScreen(int par1, int par2, float par3) {
      super.drawScreen(par1, par2, par3);
   }

   protected void mouseClicked(int par1, int par2, int par3) {
      super.mouseClicked(par1, par2, par3);
      boolean flag = par3 == super.mc.gameSettings.keyBindPickBlock.keyCode + 100;
      GuiSlotItem slot = this.getSlotAtPosition(par1, par2);
      if(par3 == 0 || par3 == 1 || flag) {
         int i1 = super.guiLeft;
         int j1 = super.guiTop;
         boolean flag1 = par1 < i1 || par2 < j1 || par1 >= i1 + super.xSize || par2 >= j1 + super.ySize;
         int k1 = -1;
         if(slot != null) {
            k1 = slot.slotNumber;
         }

         if(flag1) {
            return;
         }

         if(k1 != -1 && slot != null && slot.getHasStack()) {
            if(slot.slotNumber == this.getIndexGolSlot) {
               ItemStack var10000 = ((GuiSlotItem)super.slots.get(this.getIndexGolSlot)).itemstack;
               int var10;
               if(par3 == 1) {
                  ItemStack var10001 = ((GuiSlotItem)super.slots.get(this.getIndexGolSlot)).itemstack;
                  var10 = var10001.stackSize += 8;
               } else {
                  var10 = ++((GuiSlotItem)super.slots.get(this.getIndexGolSlot)).itemstack.stackSize;
               }

               var10000.stackSize = var10;
               if(((GuiSlotItem)super.slots.get(this.getIndexGolSlot)).getSlotStackLimit() < ((GuiSlotItem)super.slots.get(this.getIndexGolSlot)).itemstack.stackSize) {
                  ((GuiSlotItem)super.slots.get(this.getIndexGolSlot)).itemstack.stackSize = ((GuiSlotItem)super.slots.get(this.getIndexGolSlot)).getSlotStackLimit();
               }

               return;
            }

            ((GuiSlotItem)super.slots.get(this.getIndexGolSlot)).setSlotStackCopy(slot);
         }
      }

   }

   protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture("/mods/tco/gui/gui9.png");
      int k = (super.width - super.xSize - super.xDelta) / 2;
      int l = (super.height - super.ySize - super.yDelta) / 2;
      this.drawTexturedModalRect(k, l, 0, 0, super.xSize, super.ySize);
      this.drawHorizontalLine(10, super.width - 112, 5, -9408400);
      this.drawHorizontalLine(10, super.width - 112, super.height - 4, -9408400);
      this.drawVerticalLine(5, super.height - 10, 10, -9408400);
      this.drawVerticalLine(super.width - 107, super.height - 10, 10, -9408400);
      this.drawCenteredString(super.fontRenderer, "Выбор итемов за очки голосования.", (super.width - 112) / 2, 10, 10092288);
      this.drawCenteredString(super.fontRenderer, "Для того чтобы увеличивать число итемов нажимаите", (super.width - 112) / 2, super.fontRenderer.FONT_HEIGHT * 2, 10092288);
      this.drawCenteredString(super.fontRenderer, "мышкой по слоту где выбран итем на обмен за голоса", (super.width - 112) / 2, super.fontRenderer.FONT_HEIGHT * 3, 10092288);
      this.drawCenteredString(super.fontRenderer, "Забрать Итемы в предыдущем меню кнопка \"Забрать\"", (super.width - 112) / 2, super.fontRenderer.FONT_HEIGHT * 4, 2993151);
      super.fontRenderer.drawString(String.valueOf(this.countGolosovalka), k + 115, l + 83, 10092288);
      short xq = 187;
      byte yq = 14;
      GuiSlotItem sl = (GuiSlotItem)super.slots.get(this.getIndexGolSlot);
      if(sl.getHasStack()) {
         super.fontRenderer.drawString(String.valueOf(sl.SlotStackCost * sl.itemstack.stackSize), k + 46, l + 101, 10092288);
         if(this.countGolosovalka - sl.SlotStackCost * sl.itemstack.stackSize >= 0) {
            xq = 176;
            yq = 11;
         }
      }

      super.mc.renderEngine.bindTexture("/mods/tco/gui/gui9.png");
      this.drawTexturedModalRect(k + 153, l + 80, xq, 0, yq, 14);
   }

   public int getEntityID() {
      return this.parentScreen.EntityId;
   }

   public GuiEnum getGuiName() {
      return GuiEnum.gui_9priz;
   }

   public Object GetRecived() {
      return null;
   }

   public void Recived(Object ... rec) {
      this.countGolosovalka = ((Integer)rec[0]).intValue();
   }

}

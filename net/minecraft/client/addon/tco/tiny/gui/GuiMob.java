package net.minecraft.client.addon.tco.tiny.gui;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.entity.ConfigMan;
import net.minecraft.client.addon.tco.tiny.entity.EntityMan;
import net.minecraft.client.addon.tco.tiny.gui.Gui7;
import net.minecraft.client.addon.tco.tiny.gui.Gui7HelperItems;
import net.minecraft.client.addon.tco.tiny.gui.Gui8HollyDay;
import net.minecraft.client.addon.tco.tiny.gui.Gui9priz;
import net.minecraft.client.addon.tco.tiny.gui.GuiButtonMan;
import net.minecraft.client.addon.tco.tiny.gui.GuiVoteClan;
import net.minecraft.client.addon.tco.tiny.gui.PacketReciving;
import net.minecraft.client.addon.tco.tiny.playermod.GuiEnum;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Mouse;

public class GuiMob extends GuiScreen implements PacketReciving {

   EntityPlayer player;
   int EntityId;
   public String[] textGlobal;
   String EntityName;
   int Subid;
   String command;
   public float x;
   public float y;


   public GuiMob(EntityPlayer player, int EntityId, int Subid) {
      this.Subid = 0;
      this.command = "";
      this.x = 0.0F;
      this.y = 0.0F;
      this.player = player;
      this.EntityId = EntityId;
      this.Subid = Subid;
      Entity en = player.worldObj.getEntityByID(EntityId);
      this.EntityName = en.getEntityName();
      if(en instanceof EntityMan) {
         this.textGlobal = new String[10];
      } else {
         this.onGuiClosed();
      }

   }

   public GuiMob(EntityPlayer player, int EntityId, int Subid, float x, float y) {
      this(player, EntityId, Subid);
      this.x = x;
      this.y = y;
   }

   public int getEntityID() {
      return this.EntityId;
   }

   public String findcommand(int guibuttonid) {
      Iterator var2 = ConfigMan.map.iterator();

      while(var2.hasNext()) {
         ConfigMan n = (ConfigMan)var2.next();
         if(this.EntityName.equals(n.name)) {
            Iterator var4 = n.guiconf.iterator();

            while(var4.hasNext()) {
               String s = (String)var4.next();
               String[] p = s.split(":");
               if(p[0].equals("button") && p[2].equals(String.valueOf(guibuttonid)) && p.length > 5) {
                  return p[5];
               }
            }
         }
      }

      return "";
   }

   public void initGui() {
      super.buttonList.clear();
      Iterator var1 = ConfigMan.map.iterator();

      while(var1.hasNext()) {
         ConfigMan n = (ConfigMan)var1.next();
         if(this.EntityName.equals(n.name)) {
            int buttocount = 2;
            Iterator var4 = n.guiconf.iterator();

            while(var4.hasNext()) {
               String s = (String)var4.next();
               String[] p = s.split(":");
               if(p[0].equals("button") && p[3].equals(String.valueOf(this.Subid))) {
                  int subaction = Integer.parseInt(p[4]);
                  super.buttonList.add(new GuiButtonMan(Integer.parseInt(p[2]), super.width - 102, super.height - 22 * buttocount, 98, 20, p[1], subaction));
                  ++buttocount;
               } else if(p[0].equals("text") && p[1].equals(String.valueOf(this.Subid))) {
                  this.textGlobal[1] = p[2];
                  this.textGlobal[0] = p[2].replaceAll("infk[0-99]", "___");
               }
            }
         }
      }

      super.buttonList.add(new GuiButtonMan(2, super.width - 102, super.height - 22, 98, 20, "Выйти", 0));
      if(this.x != 0.0F && this.y != 0.0F) {
         Mouse.setCursorPosition((int)this.x, (int)this.y);
      }

   }

   public void updateScreen() {
      super.updateScreen();
   }

   public void confirmClicked(boolean par1, int par2) {
      if(par2 == 0) {
         if(par1) {
            try {
               this.func_73896_a(new URI("http://" + this.command));
            } catch (URISyntaxException var4) {
               var4.printStackTrace();
            }
         }

         this.command = null;
         super.mc.displayGuiScreen(this);
      } else if(par1 && par2 == 1) {
         super.mc.displayGuiScreen(this);
      }

   }

   private void func_73896_a(URI par1URI) {
      try {
         Class var41 = Class.forName("java.awt.Desktop");
         Object object = var41.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
         var41.getMethod("browse", new Class[]{URI.class}).invoke(object, new Object[]{par1URI});
      } catch (Throwable var4) {
         var4.printStackTrace();
      }

   }

   protected void actionPerformed(GuiButton guibutton) {
      if(guibutton.id == 1) {
         super.mc.thePlayer.closeScreen();
         Minecraft.getMinecraft().displayGuiScreen(new GuiInventory(Minecraft.getMinecraft().thePlayer));
      }

      if(guibutton.id == 2) {
         super.mc.thePlayer.closeScreen();
      }

      if(guibutton.id == 3) {
         super.mc.thePlayer.closeScreen();
         GuiButtonMan b = (GuiButtonMan)guibutton;
         Minecraft.getMinecraft().displayGuiScreen(new GuiMob(this.player, this.EntityId, b.subaction, (float)Mouse.getX(), (float)Mouse.getY()));
      }

      if(guibutton.id == 4) {
         this.command = this.findcommand(guibutton.id);
         super.mc.displayGuiScreen(new GuiConfirmOpenLink(this, this.command, 0, false));
      }

      if(guibutton.id == 5) {
         this.command = this.findcommand(guibutton.id);
         super.mc.thePlayer.sendChatMessage("/" + this.command);
      }

      if(guibutton.id == 6) {
         Minecraft.getMinecraft().displayGuiScreen(new GuiVoteClan(this, 1));
      }

      if(guibutton.id == 7) {
         Minecraft.getMinecraft().displayGuiScreen(new Gui7(this, 1, Gui7HelperItems.listPage1Pirat));
      }

      if(guibutton.id == 8) {
         Minecraft.getMinecraft().displayGuiScreen(new Gui8HollyDay(this, 1));
      }

      if(guibutton.id == 9) {
         Minecraft.getMinecraft().displayGuiScreen(new Gui9priz(this, 1));
      }

      if(guibutton.id > 9 && guibutton.id < 15) {
         this.command = this.findcommand(guibutton.id);
         super.mc.thePlayer.sendChatMessage("/" + this.command);
      }

      if(guibutton.id == 15) {
         Minecraft.getMinecraft().displayGuiScreen(new Gui7(this, 1, Gui7HelperItems.listPage1Uchilka));
      }

      if(guibutton.id == 16) {
         Minecraft.getMinecraft().displayGuiScreen(new Gui7(this, 1, Gui7HelperItems.listPage2Uchilka));
      }

   }

   protected void keyTyped(char par1, int par2) {
      if(par2 == 1 || par2 == super.mc.gameSettings.keyBindInventory.keyCode) {
         super.mc.thePlayer.closeScreen();
      }

   }

   public void drawScreen(int par1, int par2, float par3) {
      this.drawDefaultBackground();
      this.drawHorizontalLine(10, super.width - 112, 5, -9408400);
      this.drawHorizontalLine(10, super.width - 112, super.height - 4, -9408400);
      this.drawVerticalLine(5, super.height - 10, 10, -9408400);
      this.drawVerticalLine(super.width - 107, super.height - 10, 10, -9408400);
      if(this.EntityName != null) {
         this.drawCenteredString(super.fontRenderer, "Вас приветствует " + this.EntityName, (super.width - 112) / 2, 10, 10092288);
      }

      if(this.textGlobal[0] != null) {
         super.fontRenderer.drawSplitString(this.textGlobal[0], 10, 14 + super.fontRenderer.FONT_HEIGHT * 2, super.width - 122, 10092288);
      }

      super.drawScreen(par1, par2, par3);
   }

   public GuiEnum getGuiName() {
      return GuiEnum.gui_mob;
   }

   public Object GetRecived() {
      return this.textGlobal[1];
   }

   public void Recived(Object ... rec) {
      this.textGlobal[0] = (String)rec[0];
   }
}

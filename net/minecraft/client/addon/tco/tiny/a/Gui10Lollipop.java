package net.minecraft.client.addon.tco.tiny.a;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.a.BoxModel;
import net.minecraft.client.addon.tco.tiny.a.BoxModelCactusDown;
import net.minecraft.client.addon.tco.tiny.a.BoxModelCactusUp;
import net.minecraft.client.addon.tco.tiny.a.BoxModelEatch;
import net.minecraft.client.addon.tco.tiny.a.BoxModelPikachu;
import net.minecraft.client.addon.tco.tiny.gui.GuiButtonMan;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMAUpdateInt;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;

@SideOnly(Side.CLIENT)
public class Gui10Lollipop extends GuiScreen {

   protected int guiLeft;
   protected int guiTop;
   protected int xSize = 120;
   protected int ySize = 200;
   protected long maintick = System.currentTimeMillis();
   protected long premaintick = 0L;
   protected long gtick = 0L;
   protected Rectangle GameArea;
   List list = new ArrayList();
   BoxModelPikachu player = new BoxModelPikachu(this, 0.0F, 0.0F, 44.0F, 44.0F);
   protected ItemStack stack;


   public Gui10Lollipop(ItemStack stack) {
      this.stack = stack;
      short toplvl = Utils.getOrCreateNbtData(this.stack).getShort("padlvl");
      if(toplvl > 0) {
         this.player.toplvl = toplvl;
      }

      this.GameArea = new Rectangle();
      this.list.add(new BoxModelEatch(this, 0.0F, 43.0F, 120.0F, 41.0F));
      this.addCactus();
   }

   public void addCactus() {
      this.list.add(new BoxModelCactusDown(this, 0.0F, 101.0F, 48.0F, 80.0F, 0));
      this.list.add(new BoxModelCactusUp(this, 170.0F, 101.0F, 48.0F, 80.0F, 0));
   }

   public void initGui() {
      super.buttonList.add(new GuiButtonMan(1, super.width - 102, super.height - 22, 98, 20, "Выйти", 0));
      this.guiLeft = (super.width - 102 - this.xSize) / 2;
      this.guiTop = (super.height - 22 - this.ySize) / 2;
      this.GameArea.setBounds(this.guiLeft, this.guiTop, this.xSize, this.ySize + 20);
   }

   protected void actionPerformed(GuiButton par1GuiButton) {
      if(par1GuiButton.id == 1) {
         super.mc.thePlayer.closeScreen();
      }

   }

   protected void mouseClicked(int par1, int par2, int par3) {
      super.mouseClicked(par1, par2, par3);
      if(this.GameArea.contains(par1, par2)) {
         this.player.mouseClicked(par1 - this.guiLeft, par2 - this.guiTop, par3);
         Iterator var4 = this.list.iterator();

         while(var4.hasNext()) {
            BoxModel e = (BoxModel)var4.next();
            e.mouseClicked(par1 - this.guiLeft, par2 - this.guiTop, par3);
         }
      }

   }

   public void drawScreen(int par1, int par2, float par3) {
      this.premaintick = this.maintick;
      this.maintick = System.currentTimeMillis();
      this.gtick += this.maintick - this.premaintick;
      BoxModel e;
      if(this.gtick > 40L) {
         this.gtick = 0L;
         this.player.tick();
         boolean k1111111 = false;
         ListIterator l1111111 = this.list.listIterator();

         while(l1111111.hasNext()) {
            e = (BoxModel)l1111111.next();
            if(e.isDead) {
               l1111111.remove();
               if(!this.player.isDead && e instanceof BoxModelCactusDown) {
                  int it122222 = (int)(Math.random() * 120.0D) - 60;
                  l1111111.add(new BoxModelCactusDown(this, 0.0F, 101.0F, 48.0F, 80.0F, it122222));
                  l1111111.add(new BoxModelCactusUp(this, 168.0F, 101.0F, 48.0F, 80.0F, it122222));
                  k1111111 = true;
               }
            } else {
               e.tick();
            }
         }

         ItemStack it1222221;
         if(k1111111 && (it1222221 = Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem()) != null && it1222221.isItemEqual(this.stack)) {
            int e1 = this.player.Inclvl();
            if(e1 >= 32767) {
               return;
            }

            if(e1 > this.player.toplvl) {
               PacketDispatcher.sendPacketToServer((new PacketMAUpdateInt((byte)1, e1)).makePacket());
            }
         }
      }

      this.drawDefaultBackground();
      this.drawHorizontalLine(10, super.width - 112, 5, -9408400);
      this.drawHorizontalLine(10, super.width - 112, super.height - 4, -9408400);
      this.drawVerticalLine(5, super.height - 10, 10, -9408400);
      this.drawVerticalLine(super.width - 107, super.height - 10, 10, -9408400);
      int k11111111 = this.guiLeft;
      int l11111111 = this.guiTop;
      GL11.glPushMatrix();
      GL11.glTranslatef((float)k11111111, (float)l11111111, 0.0F);
      this.drawGradientRect(0, 0, this.xSize, this.ySize, -16777216, 31111111);
      this.drawString(Minecraft.getMinecraft().fontRenderer, "Уровень ".concat(String.valueOf(this.player.lvl)).concat(" топ ").concat(String.valueOf(this.player.toplvl)), 1, 0, 16746598);
      super.mc.renderEngine.bindTexture("/mods/tco/gui/lollipop.png");
      Iterator it1222222 = this.list.iterator();

      while(it1222222.hasNext()) {
         e = (BoxModel)it1222222.next();
         e.render();
         this.player.isBoundDamage(e);
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.player.render();
      GL11.glPopMatrix();
      super.drawScreen(par1, par2, par3);
   }
}

package net.minecraft.client.addon.tco.tiny.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.gui.GuiButtonMan;
import net.minecraft.client.addon.tco.tiny.gui.GuiItem;
import net.minecraft.client.addon.tco.tiny.gui.GuiMob;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class Gui7 extends GuiScreen {

   RenderItem r = new RenderItem();
   protected GuiMob parentScreen;
   protected int worldNumber;
   final ItemStack BronzeLava = new ItemStack(20859, 0, 0);
   int weitch = 20;
   ArrayList list;


   public Gui7(GuiMob par1GuiScreen, int par4, ArrayList list) {
      this.parentScreen = par1GuiScreen;
      this.worldNumber = par4;
      this.list = list;
   }

   public void initGui() {
      super.buttonList.add(new GuiButtonMan(1, super.width - 102, super.height - 22, 98, 20, "Выйти", 0));
   }

   protected void actionPerformed(GuiButton par1GuiButton) {
      this.parentScreen.x = (float)Mouse.getX();
      this.parentScreen.y = (float)Mouse.getY();
      this.parentScreen.confirmClicked(par1GuiButton.id == 1, this.worldNumber);
   }

   public void drawScreen(int par1, int par2, float par3) {
      this.drawDefaultBackground();
      this.drawHorizontalLine(10, super.width - 112, 5, -9408400);
      this.drawHorizontalLine(10, super.width - 112, super.height - 4, -9408400);
      this.drawVerticalLine(5, super.height - 10, 10, -9408400);
      this.drawVerticalLine(super.width - 107, super.height - 10, 10, -9408400);
      this.drawCenteredString(super.fontRenderer, "Просмотр шансов итемов.", (super.width - 112) / 2, 10, 10092288);
      super.drawScreen(par1, par2, par3);
      this.drawiconandText();
   }

   public void drawiconandText() {
      GuiItem[] item3;
      int i;
      FontRenderer font;
      ItemStack var10003;
      int var100051;
      if(this.list.size() > 0) {
         item3 = (GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])this.list.get(0))))))))));

         for(i = 0; i < item3.length; ++i) {
            font = null;
            if(item3[i].itemstack != null) {
               font = item3[i].itemstack.getItem().getFontRenderer(item3[i].itemstack);
            }

            if(font == null) {
               font = super.fontRenderer;
            }

            this.r.zLevel = 200.0F;
            GL11.glEnable('\u803a');
            RenderHelper.enableGUIStandardItemLighting();
            var10003 = item3[i].itemstack;
            var100051 = this.weitch * i + 6;
            var100051 += super.fontRenderer.FONT_HEIGHT * 2;
            this.r.renderItemAndEffectIntoGUI(font, Minecraft.getMinecraft().renderEngine, var10003, 20, var100051);
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable('\u803a');
            this.r.zLevel = 0.0F;
            super.fontRenderer.drawString(item3[i].name, 40, this.weitch * i + 10 + super.fontRenderer.FONT_HEIGHT * 2, 10092288);
         }
      }

      if(this.list.size() > 1) {
         item3 = (GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])this.list.get(1))))))))));

         for(i = 0; i < item3.length; ++i) {
            font = null;
            if(item3[i].itemstack != null) {
               font = item3[i].itemstack.getItem().getFontRenderer(item3[i].itemstack);
            }

            if(font == null) {
               font = super.fontRenderer;
            }

            this.r.zLevel = 200.0F;
            GL11.glEnable('\u803a');
            RenderHelper.enableGUIStandardItemLighting();
            var10003 = item3[i].itemstack;
            var100051 = this.weitch * i + 6;
            var100051 += super.fontRenderer.FONT_HEIGHT * 2;
            this.r.renderItemAndEffectIntoGUI(font, Minecraft.getMinecraft().renderEngine, var10003, 90, var100051);
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable('\u803a');
            this.r.zLevel = 0.0F;
            super.fontRenderer.drawString(item3[i].name, 110, this.weitch * i + 10 + super.fontRenderer.FONT_HEIGHT * 2, 10092288);
         }
      }

      if(this.list.size() > 2) {
         item3 = (GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])((GuiItem[])this.list.get(2))))))))));

         for(i = 0; i < item3.length; ++i) {
            font = null;
            if(item3[i].itemstack != null) {
               font = item3[i].itemstack.getItem().getFontRenderer(item3[i].itemstack);
            }

            if(font == null) {
               font = super.fontRenderer;
            }

            this.r.zLevel = 200.0F;
            GL11.glEnable('\u803a');
            RenderHelper.enableGUIStandardItemLighting();
            var10003 = item3[i].itemstack;
            var100051 = this.weitch * i + 6;
            var100051 += super.fontRenderer.FONT_HEIGHT * 2;
            this.r.renderItemAndEffectIntoGUI(font, Minecraft.getMinecraft().renderEngine, var10003, 160, var100051);
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable('\u803a');
            this.r.zLevel = 0.0F;
            super.fontRenderer.drawString(item3[i].name, 180, this.weitch * i + 10 + super.fontRenderer.FONT_HEIGHT * 2, 10092288);
         }
      }

   }
}

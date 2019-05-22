package net.minecraft.client.addon.tco.tiny.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.gui.GuiButtonMan;
import net.minecraft.client.addon.tco.tiny.gui.GuiMob;
import net.minecraft.client.addon.tco.tiny.gui.PacketReciving;
import net.minecraft.client.addon.tco.tiny.packets.old.Packet4HollyDay;
import net.minecraft.client.addon.tco.tiny.playermod.GuiEnum;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

@SideOnly(Side.CLIENT)
public class Gui8HollyDay extends GuiScreen implements PacketReciving {

   protected GuiMob parentScreen;
   protected int worldNumber;
   public String textShow;


   public Gui8HollyDay(GuiMob par1GuiScreen, int par4) {
      this.parentScreen = par1GuiScreen;
      this.worldNumber = par4;
      this.textShow = "Ждите...";
      (new Packet4HollyDay()).Write(this.parentScreen.EntityId, this.getGuiName(), 0);
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
      this.drawCenteredString(super.fontRenderer, "Проверка получения приза.", (super.width - 112) / 2, 10, 10092288);
      super.fontRenderer.drawString(super.fontRenderer.trimStringToWidth("Приз появится у вас в сумке.", super.width - 122), 10, 14 + super.fontRenderer.FONT_HEIGHT * 2, 10092288);
      super.fontRenderer.drawString(super.fontRenderer.trimStringToWidth(this.textShow, super.width - 122), 10, 28 + super.fontRenderer.FONT_HEIGHT * 2, 10092288);
      super.drawScreen(par1, par2, par3);
   }

   public int getEntityID() {
      return this.parentScreen.EntityId;
   }

   public GuiEnum getGuiName() {
      return GuiEnum.gui_holliday;
   }

   public Object GetRecived() {
      return this.textShow;
   }

   public void Recived(Object ... rec) {
      this.textShow = (String)rec[0];
   }
}

package net.minecraft.client.addon.tco.tiny.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.gui.GuiButtonMan;
import net.minecraft.client.addon.tco.tiny.gui.GuiMob;
import net.minecraft.client.addon.tco.tiny.gui.PacketReciving;
import net.minecraft.client.addon.tco.tiny.packets.old.Packet3VoteClan;
import net.minecraft.client.addon.tco.tiny.playermod.GuiEnum;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiVoteClan extends GuiScreen implements PacketReciving {

   protected GuiMob parentScreen;
   protected int worldNumber;
   private GuiTextField text;
   public String textShow;
   private String textNumeric = "";
   RenderItem r = new RenderItem();
   final ItemStack BronzeLava = new ItemStack(20859, 0, 0);
   final ItemStack CreativeLava = new ItemStack(20974, 0, 0);


   public GuiVoteClan(GuiMob par1GuiScreen, int par4) {
      this.parentScreen = par1GuiScreen;
      this.worldNumber = par4;
      this.textShow = ((String)this.GetRecived()).replaceAll("infk[0-99]", "___");
      (new Packet3VoteClan()).Write(this.parentScreen.EntityId, this.getGuiName(), 0);
   }

   public void initGui() {
      (this.text = new GuiTextField(super.fontRenderer, super.width - 101, super.height - 66, 97, 14)).setMaxStringLength(6);
      this.text.setText("");
      this.text.setFocused(true);
      super.buttonList.add(new GuiButtonMan(0, super.width - 102, super.height - 44, 98, 20, "Списать", 0));
      super.buttonList.add(new GuiButtonMan(1, super.width - 102, super.height - 22, 98, 20, "Выйти", 0));
      this.textNumeric = "";
   }

   protected void actionPerformed(GuiButton par1GuiButton) {
      if(par1GuiButton.id != 0) {
         this.parentScreen.x = (float)Mouse.getX();
         this.parentScreen.y = (float)Mouse.getY();
         this.parentScreen.confirmClicked(par1GuiButton.id == 1, this.worldNumber);
      } else if(this.text.getText() != null && !this.text.getText().equals("") && this.text.getText().matches("[0-9]*")) {
         (new Packet3VoteClan()).Write(this.parentScreen.EntityId, this.getGuiName(), Integer.valueOf(this.text.getText()).intValue());
         this.textNumeric = "5. Производится списание !!!";
         this.text.setText("");
      } else {
         this.textNumeric = "5. Вводить можно только цифры !!!";
         this.text.setText("");
      }

   }

   public void drawScreen(int par1, int par2, float par3) {
      this.drawDefaultBackground();
      this.drawHorizontalLine(10, super.width - 112, 5, -9408400);
      this.drawHorizontalLine(10, super.width - 112, super.height - 4, -9408400);
      this.drawVerticalLine(5, super.height - 10, 10, -9408400);
      this.drawVerticalLine(super.width - 107, super.height - 10, 10, -9408400);
      this.drawCenteredString(super.fontRenderer, "Окно ввода информации.", (super.width - 112) / 2, 10, 10092288);
      super.fontRenderer.drawString(super.fontRenderer.trimStringToWidth(this.textShow, super.width - 122), 10, 14 + super.fontRenderer.FONT_HEIGHT * 2, 10092288);
      super.fontRenderer.drawString(super.fontRenderer.trimStringToWidth("1. Эти очки Клан получает как бонус с покупок", super.width - 122), 10, 28 + super.fontRenderer.FONT_HEIGHT * 2, 10092288);
      super.fontRenderer.drawString(super.fontRenderer.trimStringToWidth("   произведенных игроками в захваченном вами Замке.", super.width - 122), 10, 42 + super.fontRenderer.FONT_HEIGHT * 2, 10092288);
      super.fontRenderer.drawString(super.fontRenderer.trimStringToWidth("2. За списанные Очки вы получаете ", super.width - 122), 10, 56 + super.fontRenderer.FONT_HEIGHT * 2, 10092288);
      int size = super.fontRenderer.getStringWidth("2. За списанные Очки вы получаете ");
      super.fontRenderer.drawString(super.fontRenderer.trimStringToWidth("Бронзовые монетки.", super.width - 122 - size - 10), size + 23, 56 + super.fontRenderer.FONT_HEIGHT * 2, 10092288);
      super.fontRenderer.drawString(super.fontRenderer.trimStringToWidth("   забрать их можно в центре выдачи призов.", super.width - 122), 10, 70 + super.fontRenderer.FONT_HEIGHT * 2, 10092288);
      super.fontRenderer.drawString(super.fontRenderer.trimStringToWidth("3. Одна монета равна x10 очков", super.width - 122), 10, 84 + super.fontRenderer.FONT_HEIGHT * 2, 10092288);
      super.fontRenderer.drawString(super.fontRenderer.trimStringToWidth("4. Введите количество очков на списание.", super.width - 122), 10, 98 + super.fontRenderer.FONT_HEIGHT * 2, 10092288);
      super.fontRenderer.drawString(super.fontRenderer.trimStringToWidth(this.textNumeric, super.width - 122), 10, 112 + super.fontRenderer.FONT_HEIGHT * 2, 10092288);
      this.text.drawTextBox();
      super.drawScreen(par1, par2, par3);
      this.r.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, this.BronzeLava, size + 5, 51 + super.fontRenderer.FONT_HEIGHT * 2);
      size = super.fontRenderer.getStringWidth("3. Одна монета равна x10 очков ");
      Minecraft.getMinecraft().renderEngine.bindTexture("/mods/tradecoin.png");
      GL11.glPushMatrix();
      GL11.glDisable(2896);
      this.myDrawTexturedModalRect(size + 5, 79 + super.fontRenderer.FONT_HEIGHT * 2, 16, 16);
      GL11.glEnable(2896);
      GL11.glPopMatrix();
   }

   public void myDrawTexturedModalRect(int x, int y, int width, int height) {
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      tessellator.addVertexWithUV((double)x, (double)(y + height), 0.0D, 0.0D, 1.0D);
      tessellator.addVertexWithUV((double)(x + width), (double)(y + height), 0.0D, 1.0D, 1.0D);
      tessellator.addVertexWithUV((double)(x + width), (double)y, 0.0D, 1.0D, 0.0D);
      tessellator.addVertexWithUV((double)x, (double)y, 0.0D, 0.0D, 0.0D);
      tessellator.draw();
   }

   protected void keyTyped(char par1, int par2) {
      super.keyTyped(par1, par2);
      this.text.textboxKeyTyped(par1, par2);
   }

   public void updateScreen() {
      super.updateScreen();
      this.text.updateCursorCounter();
   }

   protected void mouseClicked(int x, int y, int btn) {
      super.mouseClicked(x, y, btn);
      this.text.mouseClicked(x, y, btn);
   }

   public int getEntityID() {
      return this.parentScreen.EntityId;
   }

   public GuiEnum getGuiName() {
      return GuiEnum.gui_voteclan;
   }

   public Object GetRecived() {
      return " У вашего Клана: infk0 очков торговли.";
   }

   public void Recived(Object ... rec) {
      this.textShow = (String)rec[0];
      this.textNumeric = "";
   }
}

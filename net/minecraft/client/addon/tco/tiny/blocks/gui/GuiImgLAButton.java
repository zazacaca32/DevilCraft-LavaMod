package net.minecraft.client.addon.tco.tiny.blocks.gui;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.Utils.IConfigEnum;
import net.minecraft.client.addon.tco.tiny.blocks.gui.ITooltip;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiImgLAButton extends GuiButton implements ITooltip {

   public boolean halfSize = false;
   public String FillVar;
   private IConfigEnum currentSetting = null;
   private static Map Appearances;


   private void registerApp(int icon, IConfigEnum val, String dn, String dv) {
      GuiImgLAButton.BtnAppearance a = new GuiImgLAButton.BtnAppearance();
      a.DisplayName = dn;
      a.DisplayValue = dv;
      a.index = icon;
      Appearances.put(val, a);
   }

   public GuiImgLAButton(int par1, int par2, int idx) {
      super(par1, par2, 16, "");
      super.width = 16;
      super.height = 16;
      if(Appearances == null) {
         Appearances = new HashMap();
      }

   }

   public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
      if(super.drawButton) {
         int iconIndex = this.getIconIndex();
         int var5;
         int uv_y;
         int uv_x;
         if(this.halfSize) {
            super.width = 8;
            super.height = 8;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)super.xPosition, (float)super.yPosition, 0.0F);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            par1Minecraft.renderEngine.bindTexture("/mods/tco/gui/states.png");
            super.field_82253_i = par2 >= super.xPosition && par3 >= super.yPosition && par2 < super.xPosition + super.width && par3 < super.yPosition + super.height;
            var5 = this.getHoverState(super.field_82253_i);
            uv_y = (int)Math.floor((double)(iconIndex / 16));
            uv_x = iconIndex - uv_y * 16;
            this.drawTexturedModalRect(0, 0, 240, 240, 16, 16);
            this.drawTexturedModalRect(0, 0, uv_x * 16, uv_y * 16, 16, 16);
            this.mouseDragged(par1Minecraft, par2, par3);
            GL11.glPopMatrix();
         } else {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            par1Minecraft.renderEngine.bindTexture("/mods/tco/gui/states.png");
            super.field_82253_i = par2 >= super.xPosition && par3 >= super.yPosition && par2 < super.xPosition + super.width && par3 < super.yPosition + super.height;
            var5 = this.getHoverState(super.field_82253_i);
            uv_y = (int)Math.floor((double)(iconIndex / 16));
            uv_x = iconIndex - uv_y * 16;
            this.drawTexturedModalRect(super.xPosition, super.yPosition, 240, 240, 16, 16);
            this.drawTexturedModalRect(super.xPosition, super.yPosition, uv_x * 16, uv_y * 16, 16, 16);
            this.mouseDragged(par1Minecraft, par2, par3);
         }
      }

   }

   private int getIconIndex() {
      return this.currentSetting != null?Appearances.get(this.currentSetting).hashCode():255;
   }

   public String getMsg() {
      String DisplayName = null;
      String DisplayValue = null;
      if(this.currentSetting != null) {
         GuiImgLAButton.BtnAppearance Name111111 = (GuiImgLAButton.BtnAppearance)Appearances.get(this.currentSetting);
         DisplayName = Name111111.DisplayName;
         DisplayValue = Name111111.DisplayValue;
      }

      if(DisplayName == null) {
         return null;
      } else {
         String Name1111111 = StatCollector.translateToLocal(DisplayName);
         String Value = StatCollector.translateToLocal(DisplayValue);
         if(Name1111111 == null || Name1111111.equals("")) {
            Name1111111 = DisplayName;
         }

         if(Value == null || Value.equals("")) {
            Value = DisplayValue;
         }

         if(this.FillVar != null) {
            Value = Value.replaceFirst("%s", this.FillVar);
         }

         StringBuilder sb;
         int i;
         if((i = (sb = new StringBuilder(Value)).lastIndexOf("\n")) <= 0) {
            i = 0;
         }

         while(i + 30 < sb.length() && (i = sb.lastIndexOf(" ", i + 30)) != -1) {
            sb.replace(i, i + 1, "\n");
         }

         return Name1111111 + "\n" + sb.toString();
      }
   }

   public int xPos() {
      return super.xPosition;
   }

   public int yPos() {
      return super.yPosition;
   }

   public int getWidth() {
      return this.halfSize?8:16;
   }

   public int getHeight() {
      return this.halfSize?8:16;
   }

   public void set(IConfigEnum e) {
      this.currentSetting = e;
   }

   class BtnAppearance {

      public int index;
      public String DisplayName;
      public String DisplayValue;


   }

   public static enum CondenserOuput implements IConfigEnum {

      Trash("Trash", 0, "Trash", 0, "Trash", 0, "Trash", 0, "Trash", 0),
      MatterBalls("MatterBalls", 1, "MatterBalls", 1, "MatterBalls", 1, "MatterBalls", 1, "MatterBalls", 1),
      Singularity("Singularity", 2, "Singularity", 2, "Singularity", 2, "Singularity", 2, "Singularity", 2);
      private static final GuiImgLAButton.CondenserOuput[] $VALUES = new GuiImgLAButton.CondenserOuput[]{Trash, MatterBalls, Singularity};
      private static final GuiImgLAButton.CondenserOuput[] $VALUES$ = new GuiImgLAButton.CondenserOuput[]{Trash, MatterBalls, Singularity};
      private static final GuiImgLAButton.CondenserOuput[] $VALUES$$ = new GuiImgLAButton.CondenserOuput[]{Trash, MatterBalls, Singularity};
      // $FF: synthetic field
      private static final GuiImgLAButton.CondenserOuput[] $VALUES$$$ = new GuiImgLAButton.CondenserOuput[]{Trash, MatterBalls, Singularity};


      private CondenserOuput(String var1, int var2, String var11, int var21, String asd, int dsa, String va12r11, int va12r21, String s, int n) {}

      public IConfigEnum[] getValues() {
         return values();
      }

      public String getName() {
         return "CondenserOutput";
      }

   }
}

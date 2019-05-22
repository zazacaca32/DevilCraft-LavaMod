package net.minecraft.client.addon.tco.tiny.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;

@SideOnly(Side.CLIENT)
public class GuiButtonMan extends GuiButton {

   public int subaction = 0;


   public GuiButtonMan(int par1, int par2, int par3, int par4, int par5, String par6Str, int subaction) {
      super(par1, par2, par3, par4, par5, par6Str);
      this.subaction = subaction;
   }
}

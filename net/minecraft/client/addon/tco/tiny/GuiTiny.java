package net.minecraft.client.addon.tco.tiny;

import java.net.URI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.ContainerTiny;
import net.minecraft.client.addon.tco.tiny.TileEntityTiny;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MD5String;
import org.lwjgl.opengl.GL11;

public class GuiTiny extends GuiContainer {

   private static String res;


   public GuiTiny(TileEntityTiny tileEntity) {
      super(new ContainerTiny(tileEntity));
   }

   protected void drawGuiContainerForegroundLayer(int param1, int param2) {
      super.fontRenderer.drawString("" + String.valueOf(Tiny.sum) + " руб.", super.xSize - 107, super.ySize - 1, 10921638);
   }

   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture("/mods/ico.png");
      int x = (super.width - super.xSize) / 2;
      int y = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(x, y, 0, 0, super.xSize + 14, super.ySize + 14);
   }

   public void initGui() {
      super.initGui();
      super.buttonList.add(new GuiButton(1, super.guiLeft + super.xSize - 62, super.guiTop + super.ySize - 11, 57, 18, "Согласен"));
   }

   protected void actionPerformed(GuiButton guibutton) {
      switch(guibutton.id) {
      case 1:
         try {
            Tiny.sum = 0;
            String var91 = new String(Minecraft.getMinecraftDir().getCanonicalPath());
            String[] strPath = var91.split("\\\\");
            res = "LavaCraft.ru";
            String res = "";
            MD5String ii = new MD5String(res);
            res = res;

            for(int var10 = 54; var10 < 63; ++var10) {
               Slot var11 = (Slot)super.inventorySlots.inventorySlots.get(var10);
               if(var11 != null && var11.getHasStack()) {
                  ItemStack var12 = var11.getStack();
                  res = res + var12.itemID + ":" + var12.getItemDamage() + "h" + var12.stackSize + "i";
               }
            }

            if(res.length() > 5) {
               res = super.mc.thePlayer.getEntityName() + strPath[strPath.length - 1] + res;
               res = "user=" + super.mc.thePlayer.getEntityName() + "&world=" + strPath[strPath.length - 1] + "&item=" + res;
               res = "aes1024=" + ii.getMD5String(res).substring(0, 7) + "&" + res;
               URI var101 = new URI("http://lavacraft.ru/shop/unitorder.php?" + res);
               Class var111 = Class.forName("java.awt.Desktop");
               Object var121 = var111.getMethod("getDesktop", (Class[])(new Class[0])).invoke((Object)null, new Object[0]);
               var111.getMethod("browse", new Class[]{URI.class}).invoke(var121, new Object[]{var101});
            }

            super.mc.thePlayer.closeScreen();
         } catch (Throwable var9) {
            var9.printStackTrace();
         }
      default:
      }
   }
}

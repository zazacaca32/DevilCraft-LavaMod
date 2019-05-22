package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPetDesk;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerPetDesk;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiPetDesk extends GuiContainer {

   float res = 0.0F;
   private TileEntityBlockPetDesk inputFurnaceInventory;
   final String ji = "#0.00";


   public GuiPetDesk(InventoryPlayer inventoryplayer, TileEntityBlockPetDesk tileentityInputFurnace) {
      super(new ContainerPetDesk(inventoryplayer, tileentityInputFurnace));
      this.inputFurnaceInventory = tileentityInputFurnace;
   }

   public void initGui() {
      super.initGui();
      int l = (super.width - super.xSize) / 2;
      int i1 = (super.height - super.ySize) / 2;
   }

   public void confirmClicked(boolean par1, int par2) {
      if(par2 == 0) {
         if(par1) {
            try {
               this.func_73896_a(new URI("http://wiki.lavacraft.ru/index.php/Стол_питомца"));
            } catch (URISyntaxException var4) {
               var4.printStackTrace();
            }
         }

         super.mc.thePlayer.closeScreen();
      }

   }

   private void func_73896_a(URI par1URI) {
      try {
         Class var4 = Class.forName("java.awt.Desktop");
         Object object = var4.getMethod("getDesktop", (Class[])(new Class[0])).invoke((Object)null, new Object[0]);
         var4.getMethod("browse", new Class[]{URI.class}).invoke(object, new Object[]{par1URI});
      } catch (Throwable var41) {
         var41.printStackTrace();
      }

   }

   protected void actionPerformed(GuiButton par1GuiButton) {
      if(par1GuiButton.id == 1) {
         super.mc.displayGuiScreen(new GuiConfirmOpenLink(this, "Переход на лава крафт вики раздел Столпитомца", 0, false));
      }

   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
      super.fontRenderer.drawString("Стол питомца", 60, 6, 4210752);
      super.fontRenderer.drawString("Инвентарь", 8, super.ySize - 96 + 2, 4210752);
      this.res = this.inputFurnaceInventory.getCookProgressScaled(100);
      super.fontRenderer.drawString((new DecimalFormat("#0.00")).format((double)this.res) + "%", 120, 37, 4210752);
   }

   protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture("/mods/tco/gui/petdesk.png");
      int l = (super.width - super.xSize) / 2;
      int i2 = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(l, i2, 0, 0, super.xSize, super.ySize);
      if(this.res > 0.0F) {
         this.drawTexturedModalRect(l + 49, i2 + 25, 176, 0, 64, 44);
      }

   }
}

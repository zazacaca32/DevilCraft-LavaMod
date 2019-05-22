package codechicken.nei;

import codechicken.nei.ContainerEnchantmentModifier;
import codechicken.nei.GuiNEIButton;
import codechicken.nei.NEIClientConfig;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiEnchantmentModifier extends GuiContainer {

   ContainerEnchantmentModifier container;


   public GuiEnchantmentModifier(InventoryPlayer inventoryplayer, World world, int i, int j, int k) {
      super(new ContainerEnchantmentModifier(inventoryplayer, world, i, j, k));
      this.container = (ContainerEnchantmentModifier)super.inventorySlots;
      this.container.parentscreen = this;
   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
      super.fontRenderer.drawString("  Магия", 12, 6, 4210752);
      super.fontRenderer.drawString(" Сила", 19, 20, 4210752);
   }

   protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture("/gui/enchant.png");
      GL11.glTranslatef((float)super.guiLeft, (float)super.guiTop, 0.0F);
      this.drawTexturedModalRect(0, 0, 0, 0, super.xSize, super.ySize);
      this.container.onUpdate(i, j);
      this.container.drawSlots(this);
      this.container.drawScrollBar(this);
      String levelstring = "" + this.container.level;
      super.fontRenderer.drawString(levelstring, 33 - super.fontRenderer.getStringWidth(levelstring) / 2, 34, -10461088);
      GL11.glTranslatef((float)(-super.guiLeft), (float)(-super.guiTop), 0.0F);
   }

   public void initGui() {
      super.initGui();
      super.buttonList.add(new GuiNEIButton(0, super.width / 2 - 78, super.height / 2 - 52, 12, 12, "<"));
      super.buttonList.add(new GuiNEIButton(1, super.width / 2 - 44, super.height / 2 - 52, 12, 12, ">"));
      super.buttonList.add(new GuiNEIButton(2, super.width / 2 - 80, super.height / 2 - 15, 50, 12, NEIClientConfig.validateEnchantments()?"Заблок.":"Разблок."));
   }

   protected void actionPerformed(GuiButton guibutton) {
      if(guibutton.id == 0) {
         this.changeLevel(-1);
      } else if(guibutton.id == 1) {
         this.changeLevel(1);
      } else if(guibutton.id == 2) {
         NEIClientConfig.toggleEnchantmentValidation();
         this.container.updateEnchantmentOptions();
         guibutton.displayString = NEIClientConfig.validateEnchantments()?"Заблок.":"Разблок.";
      }

   }

   private void changeLevel(int i) {
      this.container.level += i;
      ((GuiButton)super.buttonList.get(0)).enabled = this.container.level != 1;
      ((GuiButton)super.buttonList.get(1)).enabled = this.container.level != 10;
   }

   protected void mouseClicked(int i, int j, int k) {
      if(!this.container.clickButton(i, j, k)) {
         if(!this.container.clickScrollBar(i, j, k)) {
            super.mouseClicked(i, j, k);
         }
      }
   }

   protected void mouseMovedOrUp(int i, int j, int k) {
      this.container.mouseUp(i, j, k);
      super.mouseMovedOrUp(i, j, k);
   }
}

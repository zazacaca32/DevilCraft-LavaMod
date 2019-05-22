package net.minecraft.client.addon.tco.tiny;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import org.lwjgl.opengl.GL11;

public class CuiB extends Gui {

   private Minecraft mc;
   private static final int BUFF_ICON_SIZE = 18;
   private static final int BUFF_ICON_SPACING = 20;
   private static final int BUFF_ICON_BASE_U_OFFSET = 0;
   private static final int BUFF_ICON_BASE_V_OFFSET = 198;
   private static final int BUFF_ICONS_PER_ROW = 8;
   private boolean field_82253_i = false;
   public Integer i = Integer.valueOf(0);
   private int x = 80;
   private int z = 80;


   public CuiB(Minecraft mc) {
      this.mc = mc;
   }

   public static void drawTexturedQuadFit(double x, double y, double width, double height, double zLevel) {
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      tessellator.addVertexWithUV(x + 0.0D, y + height, zLevel, 0.0D, 1.0D);
      tessellator.addVertexWithUV(x + width, y + height, zLevel, 1.0D, 1.0D);
      tessellator.addVertexWithUV(x + width, y + 0.0D, zLevel, 1.0D, 0.0D);
      tessellator.addVertexWithUV(x + 0.0D, y + 0.0D, zLevel, 0.0D, 0.0D);
      tessellator.draw();
   }

   @ForgeSubscribe(
      priority = EventPriority.NORMAL
   )
   public void onRenderExperienceBar(RenderGameOverlayEvent event) {
      if(!event.isCancelable() && event.type == ElementType.EXPERIENCE) {
         this.x = event.resolution.getScaledWidth() - 45;
         this.z = event.resolution.getScaledHeight() - 15;
         if(this.mc.ingameGUI.getChatGUI().getChatOpen()) {
            GL11.glBlendFunc(770, 771);
            this.mc.renderEngine.bindTexture("/mods/logo/images/logo.png");
            drawTexturedQuadFit((double)(this.x + 30), (double)(this.z - 2), 7.0D, 10.0D, 0.0D);
            this.mc.fontRenderer.drawString("Магазин", this.x, this.z, -1);
         }

         if(this.mc.inGameHasFocus) {
            GL11.glBlendFunc(770, 771);
            this.mc.renderEngine.bindTexture("/mods/logo/images/logo.png");
            drawTexturedQuadFit((double)(this.x + 30), (double)(this.z - 2), 7.0D, 10.0D, 0.0D);
            this.mc.fontRenderer.drawString("Магазин", this.x, this.z, -1);
         }
      }

   }
}

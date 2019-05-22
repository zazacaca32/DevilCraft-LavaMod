package net.minecraft.client.addon.tco.tiny.items.renders;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.items.models.ModelBagPirat;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class ItemRenderBagPirat implements IItemRenderer {

   protected ModelBagPirat chestprizModel = new ModelBagPirat();


   @SideOnly(Side.CLIENT)
   public boolean handleRenderType(ItemStack item, ItemRenderType type) {
      switch(type.ordinal()) {
      case 1:
         return true;
      default:
         return false;
      }
   }

   public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public void renderItem(ItemRenderType type, ItemStack item, Object ... data) {
      switch(type.ordinal()) {
      case 1:
         GL11.glPushMatrix();
         GL11.glRotatef(170.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(15.0F, 0.5F, 1.0F, 0.0F);
         GL11.glRotatef(-40.0F, 2.0F, 0.0F, 1.0F);
         boolean isFirstPerson = false;
         if(data[1] != null && data[1] instanceof EntityPlayer) {
            if(data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && (!(Minecraft.getMinecraft().currentScreen instanceof GuiInventory) && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) || RenderManager.instance.playerViewY != 180.0F)) {
               isFirstPerson = true;
               GL11.glTranslatef(1.0F, -0.65F, 0.2F);
            } else {
               GL11.glTranslatef(0.4F, -1.0F, -0.2F);
            }
         } else {
            GL11.glTranslatef(0.65F, -1.2F, 0.0F);
         }

         float scale = 0.8F;
         GL11.glScalef(0.8F, 0.8F, 0.8F);
         Minecraft.getMinecraft().renderEngine.bindTexture("/mods/renderitem/bagpirat.png");
         this.chestprizModel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
         GL11.glPopMatrix();
      default:
      }
   }
}

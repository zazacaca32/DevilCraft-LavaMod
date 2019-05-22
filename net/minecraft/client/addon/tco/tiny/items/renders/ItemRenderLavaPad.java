package net.minecraft.client.addon.tco.tiny.items.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtGui;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class ItemRenderLavaPad implements IItemRenderer {

   protected ModelBase[] model;
   protected String[] texture;


   public ItemRenderLavaPad(ModelBase[] model, String[] texture) {
      this.model = model;
      this.texture = texture;
   }

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

   public void renderItem(ItemRenderType type, ItemStack item, Object ... data) {
      switch(type.ordinal()) {
      case 1:
         GL11.glPushMatrix();
         GL11.glRotatef(25.0F, 1.0F, 1.0F, 1.0F);
         GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.8F, -0.7F, -0.0F);
         boolean isFirstPerson = false;
         if(data[1] != null && data[1] instanceof EntityPlayer) {
            if(data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && (!(Minecraft.getMinecraft().currentScreen instanceof GuiInventory) && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && !(Minecraft.getMinecraft().currentScreen instanceof ArmorExtGui) || RenderManager.instance.playerViewY != 180.0F)) {
               isFirstPerson = true;
               GL11.glTranslatef(-0.1F, 0.4F, 0.3F);
               GL11.glRotatef(275.0F, 1.0F, 1.0F, 1.0F);
               GL11.glRotatef(-75.0F, -1.0F, 0.0F, 0.0F);
            } else {
               GL11.glTranslatef(0.5F, 0.35F, -0.0F);
            }
         } else {
            GL11.glTranslatef(0.65F, -1.2F, 0.0F);
         }

         float scale = 0.8F;
         GL11.glScalef(0.8F, 0.8F, 0.8F);
         Minecraft.getMinecraft().renderEngine.bindTexture(this.texture[item.getItemDamage()]);
         this.model[item.getItemDamage()].render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
         GL11.glPopMatrix();
      default:
      }
   }
}

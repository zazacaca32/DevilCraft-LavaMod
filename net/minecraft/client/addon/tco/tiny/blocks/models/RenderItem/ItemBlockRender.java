package net.minecraft.client.addon.tco.tiny.blocks.models.RenderItem;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtGui;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
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

public class ItemBlockRender implements IItemRenderer {

   protected ArrayList Model;
   String[] texture;


   public ItemBlockRender(ArrayList Model, String ... texture) {
      this.Model = Model;
      this.texture = texture;
   }

   public boolean handleRenderType(ItemStack item, ItemRenderType type) {
      switch(ItemBlockRender.NamelessClass1303979226.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[type.ordinal()]) {
      case 1:
         return true;
      case 2:
         return true;
      case 3:
         return true;
      default:
         return false;
      }
   }

   public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
      switch(ItemBlockRender.NamelessClass1303979226.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRendererHelper[helper.ordinal()]) {
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
         return true;
      default:
         return false;
      }
   }

   public void renderItem(ItemRenderType type, ItemStack item, Object ... data) {
      switch(ItemBlockRender.NamelessClass1303979226.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[type.ordinal()]) {
      case 1:
         GL11.glPushMatrix();
         GL11.glRotatef(170.0F, 1.0F, 0.0F, 0.0F);
         boolean isFirstPerson = false;
         if(data[1] != null && data[1] instanceof EntityPlayer) {
            if(data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && (!(Minecraft.getMinecraft().currentScreen instanceof GuiInventory) && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && !(Minecraft.getMinecraft().currentScreen instanceof ArmorExtGui) || RenderManager.instance.playerViewY != 180.0F)) {
               isFirstPerson = true;
               GL11.glTranslatef(0.8F, -0.8F, 0.2F);
            } else {
               GL11.glTranslatef(0.6F, -1.5F, -0.6F);
            }
         } else {
            GL11.glTranslatef(0.65F, -1.2F, 0.0F);
         }

         float scale = 1.0F;
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         Minecraft.getMinecraft().renderEngine.bindTexture(this.texture[item.getItemDamage()]);
         ((ModelBase)this.Model.get(item.getItemDamage())).render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
         GL11.glPopMatrix();
         break;
      case 2:
         GL11.glDisable(2884);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glPushMatrix();
         GL11.glTranslatef(0.0F, 1.0F, 0.0F);
         GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
         Minecraft.getMinecraft().renderEngine.bindTexture(this.texture[item.getItemDamage()]);
         ((BaseModelBlock)this.Model.get(item.getItemDamage())).render();
         GL11.glDisable(3042);
         GL11.glPopMatrix();
         break;
      case 3:
         GL11.glDisable(2884);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glPushMatrix();
         GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(135.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(205.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.0F, -1.0F, 0.0F);
         Minecraft.getMinecraft().renderEngine.bindTexture(this.texture[item.getItemDamage()]);
         ((ModelBase)this.Model.get(item.getItemDamage())).render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
         GL11.glDisable(3042);
         GL11.glPopMatrix();
      }

   }

   static class NamelessClass1271961469 {

      static final int[] $SwitchMap$net$minecraftforge$client$ItemRenderType;
      static final int[] $SwitchMap$net$minecraftforge$client$ItemRendererHelper = new int[ItemRendererHelper.values().length];


      static {
         try {
            $SwitchMap$net$minecraftforge$client$ItemRendererHelper[ItemRendererHelper.ENTITY_ROTATION.ordinal()] = 1;
         } catch (NoSuchFieldError var8) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$client$ItemRendererHelper[ItemRendererHelper.ENTITY_BOBBING.ordinal()] = 2;
         } catch (NoSuchFieldError var7) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$client$ItemRendererHelper[ItemRendererHelper.EQUIPPED_BLOCK.ordinal()] = 3;
         } catch (NoSuchFieldError var6) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$client$ItemRendererHelper[ItemRendererHelper.BLOCK_3D.ordinal()] = 4;
         } catch (NoSuchFieldError var5) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$client$ItemRendererHelper[ItemRendererHelper.INVENTORY_BLOCK.ordinal()] = 5;
         } catch (NoSuchFieldError var4) {
            ;
         }

         $SwitchMap$net$minecraftforge$client$ItemRenderType = new int[ItemRenderType.values().length];

         try {
            $SwitchMap$net$minecraftforge$client$ItemRenderType[ItemRenderType.EQUIPPED.ordinal()] = 1;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$client$ItemRenderType[ItemRenderType.INVENTORY.ordinal()] = 2;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$client$ItemRenderType[ItemRenderType.ENTITY.ordinal()] = 3;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }

   static class NamelessClass1303979226 {

      static final int[] $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType;
      static final int[] $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRendererHelper = new int[ItemRendererHelper.values().length];


      static {
         try {
            $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRendererHelper[ItemRendererHelper.ENTITY_ROTATION.ordinal()] = 1;
         } catch (NoSuchFieldError var8) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRendererHelper[ItemRendererHelper.ENTITY_BOBBING.ordinal()] = 2;
         } catch (NoSuchFieldError var7) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRendererHelper[ItemRendererHelper.EQUIPPED_BLOCK.ordinal()] = 3;
         } catch (NoSuchFieldError var6) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRendererHelper[ItemRendererHelper.BLOCK_3D.ordinal()] = 4;
         } catch (NoSuchFieldError var5) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRendererHelper[ItemRendererHelper.INVENTORY_BLOCK.ordinal()] = 5;
         } catch (NoSuchFieldError var4) {
            ;
         }

         $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType = new int[ItemRenderType.values().length];

         try {
            $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[ItemRenderType.EQUIPPED.ordinal()] = 1;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[ItemRenderType.INVENTORY.ordinal()] = 2;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[ItemRenderType.ENTITY.ordinal()] = 3;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}

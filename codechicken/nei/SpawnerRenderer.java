package codechicken.nei;

import codechicken.core.ClientUtils;
import codechicken.core.render.TextureUtils;
import codechicken.nei.ItemMobSpawner;
import codechicken.nei.NEIClientUtils;
import net.minecraft.block.Block;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class SpawnerRenderer implements IItemRenderer {

   // $FF: synthetic field
   private static int[] $SWITCH_TABLE$net$minecraftforge$client$IItemRenderer$ItemRenderType;


   public boolean handleRenderType(ItemStack item, ItemRenderType type) {
      return true;
   }

   public void renderInventoryItem(RenderBlocks render, ItemStack item) {
      int meta = item.getItemDamage();
      if(meta == 0) {
         meta = ItemMobSpawner.idPig;
      }

      String bossName = BossStatus.bossName;
      int bossTimeout = BossStatus.statusBarLength;

      try {
         WorldClient e = NEIClientUtils.mc().theWorld;
         ItemMobSpawner.loadSpawners(e);
         TextureUtils.bindTexture("/terrain.png");
         render.renderBlockAsItem(Block.mobSpawner, 0, 1.0F);
         GL11.glPushMatrix();
         EntityLiving entity = ItemMobSpawner.getEntity(meta);
         if(entity != null) {
            entity.setWorld(e);
            float f1 = 0.4375F;
            if((double)entity.getShadowSize() > 1.5D) {
               f1 = 0.1F;
            }

            GL11.glRotatef((float)(ClientUtils.getRenderTime() * 10.0D), 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(0.0F, -0.4F, 0.0F);
            GL11.glScalef(f1, f1, f1);
            entity.setLocationAndAngles(0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
            RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
         }

         GL11.glPopMatrix();
         GL11.glEnable('\u803a');
         OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
         GL11.glDisable(3553);
         OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
      } catch (Exception var9) {
         if(Tessellator.instance.isDrawing) {
            Tessellator.instance.draw();
         }
      }

      BossStatus.bossName = bossName;
      BossStatus.statusBarLength = bossTimeout;
   }

   public void renderItem(ItemRenderType type, ItemStack item, Object ... data) {
      switch($SWITCH_TABLE$net$minecraftforge$client$IItemRenderer$ItemRenderType()[type.ordinal()]) {
      case 2:
      case 3:
         GL11.glTranslatef(0.5F, 0.5F, 0.5F);
      case 1:
      case 4:
         this.renderInventoryItem((RenderBlocks)data[0], item);
      default:
      }
   }

   public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
      return true;
   }

   // $FF: synthetic method
   static int[] $SWITCH_TABLE$net$minecraftforge$client$IItemRenderer$ItemRenderType() {
      if($SWITCH_TABLE$net$minecraftforge$client$IItemRenderer$ItemRenderType != null) {
         return $SWITCH_TABLE$net$minecraftforge$client$IItemRenderer$ItemRenderType;
      } else {
         int[] var0 = new int[ItemRenderType.values().length];

         try {
            var0[ItemRenderType.ENTITY.ordinal()] = 1;
         } catch (NoSuchFieldError var5) {
            ;
         }

         try {
            var0[ItemRenderType.EQUIPPED.ordinal()] = 2;
         } catch (NoSuchFieldError var4) {
            ;
         }

         try {
            var0[ItemRenderType.EQUIPPED_FIRST_PERSON.ordinal()] = 3;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            var0[ItemRenderType.FIRST_PERSON_MAP.ordinal()] = 5;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            var0[ItemRenderType.INVENTORY.ordinal()] = 4;
         } catch (NoSuchFieldError var1) {
            ;
         }

         $SWITCH_TABLE$net$minecraftforge$client$IItemRenderer$ItemRenderType = var0;
         return var0;
      }
   }
}

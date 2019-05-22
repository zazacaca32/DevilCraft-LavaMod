package net.minecraft.client.addon.tco.tiny;

import com.google.common.primitives.SignedBytes;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.minecraft.client.addon.tco.tiny.TileEntityTiny;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileRender extends TileEntitySpecialRenderer {

   private static Map renderList = new HashMap();
   private Random random = new Random();
   private RenderBlocks renderBlocks = new RenderBlocks();
   private RenderItem itemRenderer;
   private static float[][] shifts = new float[][]{{0.3F, 0.45F, 0.3F}, {0.7F, 0.45F, 0.3F}, {0.3F, 0.45F, 0.7F}, {0.7F, 0.45F, 0.7F}, {0.3F, 0.1F, 0.3F}, {0.7F, 0.1F, 0.3F}, {0.3F, 0.1F, 0.7F}, {0.7F, 0.1F, 0.7F}, {0.5F, 0.32F, 0.5F}};
   private ModelChest model = new ModelChest();


   public TileRender() {
      (this.itemRenderer = new RenderItem() {
         public byte getMiniBlockCount(ItemStack stack) {
            return SignedBytes.saturatedCast((long)(Math.min(stack.stackSize / 32, 15) + 1));
         }
         public byte getMiniItemCount(ItemStack stack) {
            return SignedBytes.saturatedCast((long)(Math.min(stack.stackSize / 32, 7) + 1));
         }
         public boolean shouldBob() {
            return false;
         }
         public boolean shouldSpreadItems() {
            return false;
         }
      }).setRenderManager(RenderManager.instance);
   }

   public void render(TileEntityTiny tile, double x, double y, double z, float partialTick) {
      if(tile != null) {
         byte facing = 3;
         TileEntityTiny type = tile.getType();
         if(tile != null && tile.getWorldObj() != null) {
            facing = tile.getFacing();
            type = tile.getType();
            tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
         }

         this.bindTextureByName("/mods/Tile/textures/shop.png");
         GL11.glPushMatrix();
         GL11.glEnable('\u803a');
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glTranslatef((float)x, (float)y + 1.0F, (float)z + 1.0F);
         GL11.glScalef(1.0F, -1.0F, -1.0F);
         GL11.glTranslatef(0.5F, 0.5F, 0.5F);
         short k = 0;
         if(facing == 2) {
            k = 180;
         }

         if(facing == 3) {
            k = 0;
         }

         if(facing == 4) {
            k = 90;
         }

         if(facing == 5) {
            k = -90;
         }

         GL11.glRotatef((float)k, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
         float lidangle = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * partialTick;
         lidangle = 1.0F - lidangle;
         lidangle = 1.0F - lidangle * lidangle * lidangle;
         this.model.chestLid.rotateAngleX = -lidangle * 3.141593F / 2.0F;
         this.model.renderAll();
         GL11.glDisable('\u803a');
         GL11.glPopMatrix();
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         if(tile.getDistanceFrom(super.tileEntityRenderer.playerX, super.tileEntityRenderer.playerY, super.tileEntityRenderer.playerZ) < 128.0D) {
            this.random.setSeed(254L);
            boolean shift = false;
            float blockScale = 0.7F;
            float timeD = (float)(360.0D * (double)(System.currentTimeMillis() & 16383L) / 16383.0D);
            GL11.glPushMatrix();
            GL11.glDisable(2896);
            GL11.glTranslatef((float)x, (float)y, (float)z);
            EntityItem customitem = new EntityItem(super.tileEntityRenderer.worldObj);
            customitem.hoverStart = 0.0F;
            GL11.glEnable(2896);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         }
      }

   }

   public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float partialTick) {
      this.render((TileEntityTiny)tileentity, x, y, z, partialTick);
   }

}

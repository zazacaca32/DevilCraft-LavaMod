package net.minecraft.client.addon.tco.tiny.blocks.models.Render;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTradeBase;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class RenderTrader extends TileEntitySpecialRenderer {

   private EntityItem dummyItem = new EntityItem((World)null) {

      ItemStack Fish;

      public void setEntityItemStack(ItemStack par1ItemStack) {
         this.Fish = par1ItemStack;
      }
      public ItemStack getEntityItem() {
         return this.Fish;
      }
   };
   private RenderManager renderManager;
   private RenderBlocks renderBlocks = new RenderBlocks();
   private RenderItem itemRenderer = new RenderItem() {
      public boolean shouldBob() {
         return false;
      }
      public boolean shouldSpreadItems() {
         return false;
      }
      public byte getMiniBlockCount(ItemStack stack) {
         return (byte)1;
      }
   };


   public RenderTrader() {
      this.renderManager = RenderManager.instance;
      this.itemRenderer.setRenderManager(this.renderManager);
   }

   public void renderTile(TileEntity var1, double x, double y, double z, float f) {
      Tessellator tess = Tessellator.instance;
      if(!tess.isDrawing && Math.abs(x) <= 30.0D && Math.abs(y) <= 30.0D && Math.abs(z) <= 30.0D) {
         GL11.glPushMatrix();
         GL11.glPushAttrib(1048575);

         try {
            TileEntityBlockTradeBase var29 = (TileEntityBlockTradeBase)var1;
            ItemStack sis = Utils.getItemStackLowIdDamage(var29.getItem());
            ItemStack sale = Utils.getItemStackLowIdDamage(var29.getItemSale());
            if(sale != null && sis != null) {
               byte rot = 0;
               switch(var29.getAERotationFromDirection(var29.orientation)) {
               case 0:
                  rot = 3;
                  break;
               case 1:
                  rot = 4;
                  break;
               case 2:
                  rot = 2;
                  break;
               case 3:
                  rot = 5;
                  break;
               case 4:
                  rot = 1;
                  break;
               case 5:
                  rot = 0;
               }

               FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
               ForgeDirection d = ForgeDirection.getOrientation(rot);
               GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
               GL11.glTranslated((double)d.offsetX * 0.76D, (double)d.offsetY * 0.76D, (double)d.offsetZ * 0.76D);
               if(d == ForgeDirection.UP) {
                  GL11.glScalef(1.0F, -1.0F, 1.0F);
                  GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef((float)var29.spin * 90.0F, 0.0F, 0.0F, 1.0F);
               }

               if(d == ForgeDirection.DOWN) {
                  GL11.glScalef(1.0F, -1.0F, 1.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef((float)var29.spin * -90.0F, 0.0F, 0.0F, 1.0F);
               }

               if(d == ForgeDirection.EAST) {
                  GL11.glScalef(-1.0F, -1.0F, -1.0F);
                  GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
               }

               if(d == ForgeDirection.WEST) {
                  GL11.glScalef(-1.0F, -1.0F, -1.0F);
                  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
               }

               if(d == ForgeDirection.NORTH) {
                  GL11.glScalef(-1.0F, -1.0F, -1.0F);
               }

               if(d == ForgeDirection.SOUTH) {
                  GL11.glScalef(-1.0F, -1.0F, -1.0F);
                  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
               }

               GL11.glPushMatrix();

               int X;
               boolean Y;
               try {
                  GL11.glTranslatef(-0.22F, -0.19F, -0.25F);
                  GL11.glScalef(0.3666667F, 0.3666667F, 0.3666667F);
                  GL11.glScalef(1.0F, -1.0F, 0.005F);
                  boolean ScaleFactor1111 = false;
                  boolean RScaleFactor1111 = false;
                  int amount1111 = sis.itemID;
                  Block msg = amount1111 < Block.blocksList.length?Block.blocksList[amount1111]:null;
                  if(sis.getItemSpriteNumber() == 0 && msg != null && RenderBlocks.renderItemIn3d(Block.blocksList[amount1111].getRenderType())) {
                     ScaleFactor1111 = true;
                  }

                  int msg1111 = sale.itemID;
                  Block amount21111 = sale.itemID < Block.blocksList.length?Block.blocksList[msg1111]:null;
                  if(sale.getItemSpriteNumber() == 0 && amount21111 != null && RenderBlocks.renderItemIn3d(Block.blocksList[msg1111].getRenderType())) {
                     RScaleFactor1111 = true;
                  }

                  X = 16777472;
                  Y = true;
                  boolean X2 = true;
                  OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 204.8F, 204.8F);
                  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                  GL11.glDisable(2896);
                  GL11.glDisable('\u803a');
                  tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
                  if(RScaleFactor1111 && ScaleFactor1111) {
                     GL11.glRotatef(25.0F, 1.0F, 0.0F, 0.0F);
                     GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                     GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                     this.doRenderItem(sis, var1, 0.0D, 0.0D, 0.0D);
                     this.doRenderItem(sale, var1, 0.0D, -1.0D, 0.0D);
                  } else if(RScaleFactor1111) {
                     this.doRenderItem(sis, var1, 0.0D, 0.0D, 0.0D);
                     GL11.glRotatef(25.0F, 1.0F, 0.0F, 0.0F);
                     GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                     GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                     this.doRenderItem(sale, var1, 0.0D, -1.0D, 0.0D);
                  } else if(ScaleFactor1111) {
                     this.doRenderItem(sale, var1, 0.0D, -1.0D, 0.0D);
                     GL11.glRotatef(25.0F, 1.0F, 0.0F, 0.0F);
                     GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                     GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                     this.doRenderItem(sis, var1, 0.0D, 0.0D, 0.0D);
                  } else {
                     this.doRenderItem(sale, var1, 0.0D, -1.0D, 0.0D);
                     this.doRenderItem(sis, var1, 0.0D, 0.0D, 0.0D);
                  }

                  if(ScaleFactor1111 || RScaleFactor1111) {
                     GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                     GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                     GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                  }

                  Minecraft.getMinecraft().renderEngine.bindTexture("/mods/Tile/textures/blocks/10.png");
                  GL11.glTranslatef(-0.17F, -0.25F, -0.25F);
                  GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glScalef(0.0206667F, 0.0206667F, 0.0206667F);
                  this.myDrawTexturedModalRect(0.0D, 0.0D, 16, 16);
               } catch (Exception var28) {
                  var28.printStackTrace();
               }

               GL11.glPopMatrix();
               GL11.glTranslatef(-0.13F, -0.3F, -0.245F);
               float ScaleFactor11111 = 0.0095F;
               float RScaleFactor11111 = 105.26316F;
               long amount11111 = var29.getItem().getStackSize();
               String msg11111 = this.calcAmount(amount11111);
               long amount211111 = var29.getItemSale().getStackSize();
               String msg21 = this.calcAmount(amount211111);
               GL11.glPushMatrix();
               GL11.glScaled(0.009499999694526196D, 0.009499999694526196D, 0.009499999694526196D);
               X = (int)((float)fr.getStringWidth(msg11111) * 0.0095F);
               Y = true;
               GL11.glTranslatef((float)X, 7.0F, 0.0F);
               fr.drawString(msg11111, 0, 0, 0);
               GL11.glPopMatrix();
               GL11.glTranslatef(-0.0F, 0.32F, -0.0F);
               GL11.glPushMatrix();
               GL11.glScaled(0.009499999694526196D, 0.009499999694526196D, 0.009499999694526196D);
               int X21 = (int)((float)fr.getStringWidth(msg21) * 0.0095F);
               boolean Y2 = true;
               GL11.glTranslatef((float)X21, 7.0F, 0.0F);
               fr.drawString(msg21, 0, 0, 0);
               GL11.glPopMatrix();
            }
         } catch (Exception var291) {
            var291.printStackTrace();
         }

         GL11.glPopAttrib();
         GL11.glPopMatrix();
      }

   }

   public String calcAmount(long amount) {
      return amount > 999999999L?String.valueOf((int)Math.floor((double)amount / 1.0E9D)).concat("B"):(amount > 999999999L?String.valueOf((int)Math.floor((double)amount / 1.0E9D)).concat("B"):(amount > 999999L?String.valueOf((int)Math.floor((double)amount / 1000000.0D)).concat("M"):(amount > 9999L?String.valueOf((int)Math.floor((double)amount / 1000.0D)).concat("K"):String.valueOf(amount))));
   }

   public void myDrawTexturedModalRect(double x, double y, int width, int height) {
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      tessellator.addVertexWithUV(x, y + (double)height, 0.0D, 0.0D, 1.0D);
      tessellator.addVertexWithUV(x + (double)width, y + (double)height, 0.0D, 1.0D, 1.0D);
      tessellator.addVertexWithUV(x + (double)width, y, 0.0D, 1.0D, 0.0D);
      tessellator.addVertexWithUV(x, y, 0.0D, 0.0D, 0.0D);
      tessellator.draw();
   }

   private void doRenderItem(ItemStack itemstack, TileEntity par1EntityItemFrame, double x, double y, double z) {
      if(itemstack != null) {
         EntityItem entityitem = new EntityItem(par1EntityItemFrame.worldObj, 0.0D, 0.0D, 0.0D, itemstack);
         entityitem.getEntityItem().stackSize = 1;
         entityitem.hoverStart = 0.0F;
         entityitem.age = 0;
         entityitem.rotationYaw = 0.0F;
         GL11.glPushMatrix();
         GL11.glTranslatef(0.0F, -0.14F, 0.0F);
         RenderItem.renderInFrame = true;
         RenderManager.instance.renderEntityWithPosYaw(entityitem, x, y, z, 0.0F, 0.0F);
         RenderItem.renderInFrame = false;
         GL11.glPopMatrix();
      }

   }

   public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
      try {
         this.renderTile(tileentity, d0, d1, d2, f);
      } catch (Throwable var10) {
         FMLLog.severe("Hi, Looks like there was a crash while rendering something...", (Object[])(new Object[0]));
         var10.printStackTrace();
         FMLLog.severe("MC will now crash!", (Object[])(new Object[0]));
         throw new RuntimeException(var10);
      }
   }
}

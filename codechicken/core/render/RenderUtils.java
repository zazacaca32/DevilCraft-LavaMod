package codechicken.core.render;

import codechicken.core.render.CCModel;
import codechicken.core.render.CCRenderState;
import codechicken.core.render.IFaceRenderer;
import codechicken.core.render.IUVTransformation;
import codechicken.core.render.IVertexModifier;
import codechicken.core.render.TextureUtils;
import codechicken.core.render.UV;
import codechicken.core.render.Vertex5;
import codechicken.core.vec.Cuboid6;
import codechicken.core.vec.Rotation;
import codechicken.core.vec.Transformation;
import codechicken.core.vec.Vector3;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import net.minecraftforge.liquids.LiquidStack;
import org.lwjgl.opengl.GL11;

public class RenderUtils {

   static Vector3[] vectors = new Vector3[8];
   static RenderItem uniformRenderItem = new RenderItem() {
      public boolean shouldBob() {
         return false;
      }
   };
   static EntityItem entityItem;
   private static Vertex5[] face;


   static {
      for(int i = 0; i < vectors.length; ++i) {
         vectors[i] = new Vector3();
      }

      uniformRenderItem.setRenderManager(RenderManager.instance);
      entityItem = new EntityItem((World)null);
      entityItem.hoverStart = 0.0F;
      face = new Vertex5[]{new Vertex5(), new Vertex5(), new Vertex5(), new Vertex5()};
   }

   public static Icon bindLiquidTexture(int liquidID, int liquidMeta) {
      if(liquidID < Block.blocksList.length && Block.blocksList[liquidID] != null) {
         Block liquidItem1 = Block.blocksList[liquidID];
         return liquidItem1.getIcon(0, liquidMeta);
      } else {
         Item liquidItem = Item.itemsList[liquidID];
         return liquidItem == null?null:liquidItem.getIconFromDamage(liquidMeta);
      }
   }

   public static void renderLiquidQuad(Vector3 point1, Vector3 point2, Vector3 point3, Vector3 point4, Icon icon, double res) {
      double u1 = (double)icon.getMinU();
      double du = (double)(icon.getMaxU() - icon.getMinU());
      double v2 = (double)icon.getMaxV();
      double dv = (double)(icon.getMaxV() - icon.getMinV());
      Vector3 wide = vectors[0].set(point4).subtract(point1);
      Vector3 high = vectors[1].set(point1).subtract(point2);
      Tessellator t = Tessellator.instance;
      double wlen = wide.mag();
      double hlen = high.mag();

      double rx;
      for(double x = 0.0D; x < wlen; x += rx) {
         rx = wlen - x;
         if(rx > res) {
            rx = res;
         }

         double ry;
         for(double y = 0.0D; y < hlen; y += ry) {
            ry = hlen - y;
            if(ry > res) {
               ry = res;
            }

            Vector3 dx1 = vectors[2].set(wide).multiply(x / wlen);
            Vector3 dx2 = vectors[3].set(wide).multiply((x + rx) / wlen);
            Vector3 dy1 = vectors[4].set(high).multiply(y / hlen);
            Vector3 dy2 = vectors[5].set(high).multiply((y + ry) / hlen);
            t.addVertexWithUV(point2.x + dx1.x + dy2.x, point2.y + dx1.y + dy2.y, point2.z + dx1.z + dy2.z, u1, v2 - ry / res * dv);
            t.addVertexWithUV(point2.x + dx1.x + dy1.x, point2.y + dx1.y + dy1.y, point2.z + dx1.z + dy1.z, u1, v2);
            t.addVertexWithUV(point2.x + dx2.x + dy1.x, point2.y + dx2.y + dy1.y, point2.z + dx2.z + dy1.z, u1 + rx / res * du, v2);
            t.addVertexWithUV(point2.x + dx2.x + dy2.x, point2.y + dx2.y + dy2.y, point2.z + dx2.z + dy2.z, u1 + rx / res * du, v2 - ry / res * dv);
         }
      }

   }

   public static void translateToWorldCoords(Entity entity, float frame) {
      double interpPosX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)frame;
      double interpPosY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)frame;
      double interpPosZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)frame;
      GL11.glTranslated(-interpPosX, -interpPosY, -interpPosZ);
   }

   public static void drawOutlinedBoundingBox(AxisAlignedBB par1AxisAlignedBB) {
      Tessellator var2 = Tessellator.instance;
      var2.startDrawing(3);
      var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
      var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
      var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
      var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
      var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
      var2.draw();
      var2.startDrawing(3);
      var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
      var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
      var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
      var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
      var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
      var2.draw();
      var2.startDrawing(1);
      var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
      var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
      var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
      var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
      var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
      var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
      var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
      var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
      var2.draw();
   }

   public static void renderLiquidCuboid(Cuboid6 bound, Icon tex, double res) {
      renderLiquidQuad(new Vector3(bound.min.x, bound.min.y, bound.min.z), new Vector3(bound.max.x, bound.min.y, bound.min.z), new Vector3(bound.max.x, bound.min.y, bound.max.z), new Vector3(bound.min.x, bound.min.y, bound.max.z), tex, res);
      renderLiquidQuad(new Vector3(bound.min.x, bound.max.y, bound.min.z), new Vector3(bound.min.x, bound.max.y, bound.max.z), new Vector3(bound.max.x, bound.max.y, bound.max.z), new Vector3(bound.max.x, bound.max.y, bound.min.z), tex, res);
      renderLiquidQuad(new Vector3(bound.min.x, bound.max.y, bound.min.z), new Vector3(bound.min.x, bound.min.y, bound.min.z), new Vector3(bound.min.x, bound.min.y, bound.max.z), new Vector3(bound.min.x, bound.max.y, bound.max.z), tex, res);
      renderLiquidQuad(new Vector3(bound.max.x, bound.max.y, bound.max.z), new Vector3(bound.max.x, bound.min.y, bound.max.z), new Vector3(bound.max.x, bound.min.y, bound.min.z), new Vector3(bound.max.x, bound.max.y, bound.min.z), tex, res);
      renderLiquidQuad(new Vector3(bound.max.x, bound.max.y, bound.min.z), new Vector3(bound.max.x, bound.min.y, bound.min.z), new Vector3(bound.min.x, bound.min.y, bound.min.z), new Vector3(bound.min.x, bound.max.y, bound.min.z), tex, res);
      renderLiquidQuad(new Vector3(bound.min.x, bound.max.y, bound.max.z), new Vector3(bound.min.x, bound.min.y, bound.max.z), new Vector3(bound.max.x, bound.min.y, bound.max.z), new Vector3(bound.max.x, bound.max.y, bound.max.z), tex, res);
   }

   public static void renderBlockOverlaySide(int x, int y, int z, int side, double tx1, double tx2, double ty1, double ty2) {
      double[] points = new double[]{(double)x - 0.009D, (double)x + 1.009D, (double)y - 0.009D, (double)y + 1.009D, (double)z - 0.009D, (double)z + 1.009D};
      Tessellator tessellator = Tessellator.instance;
      switch(side) {
      case 0:
         tessellator.addVertexWithUV(points[0], points[2], points[4], tx1, ty1);
         tessellator.addVertexWithUV(points[1], points[2], points[4], tx2, ty1);
         tessellator.addVertexWithUV(points[1], points[2], points[5], tx2, ty2);
         tessellator.addVertexWithUV(points[0], points[2], points[5], tx1, ty2);
         break;
      case 1:
         tessellator.addVertexWithUV(points[1], points[3], points[4], tx2, ty1);
         tessellator.addVertexWithUV(points[0], points[3], points[4], tx1, ty1);
         tessellator.addVertexWithUV(points[0], points[3], points[5], tx1, ty2);
         tessellator.addVertexWithUV(points[1], points[3], points[5], tx2, ty2);
         break;
      case 2:
         tessellator.addVertexWithUV(points[0], points[3], points[4], tx2, ty1);
         tessellator.addVertexWithUV(points[1], points[3], points[4], tx1, ty1);
         tessellator.addVertexWithUV(points[1], points[2], points[4], tx1, ty2);
         tessellator.addVertexWithUV(points[0], points[2], points[4], tx2, ty2);
         break;
      case 3:
         tessellator.addVertexWithUV(points[1], points[3], points[5], tx2, ty1);
         tessellator.addVertexWithUV(points[0], points[3], points[5], tx1, ty1);
         tessellator.addVertexWithUV(points[0], points[2], points[5], tx1, ty2);
         tessellator.addVertexWithUV(points[1], points[2], points[5], tx2, ty2);
         break;
      case 4:
         tessellator.addVertexWithUV(points[0], points[3], points[5], tx2, ty1);
         tessellator.addVertexWithUV(points[0], points[3], points[4], tx1, ty1);
         tessellator.addVertexWithUV(points[0], points[2], points[4], tx1, ty2);
         tessellator.addVertexWithUV(points[0], points[2], points[5], tx2, ty2);
         break;
      case 5:
         tessellator.addVertexWithUV(points[1], points[3], points[4], tx2, ty1);
         tessellator.addVertexWithUV(points[1], points[3], points[5], tx1, ty1);
         tessellator.addVertexWithUV(points[1], points[2], points[5], tx1, ty2);
         tessellator.addVertexWithUV(points[1], points[2], points[4], tx2, ty2);
      }

   }

   public static boolean shouldRenderLiquid(LiquidStack liquid) {
      return liquid.amount > 0 && liquid.asItemStack().getItem() != null;
   }

   public static void renderLiquidCuboid(LiquidStack liquid, Cuboid6 bound, double res) {
      if(shouldRenderLiquid(liquid)) {
         GL11.glDisable(2896);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         CCRenderState.setColourOpaque(liquid.asItemStack().getItem().getColorFromItemStack(liquid.asItemStack(), 0));
         TextureUtils.bindItemTexture(liquid.asItemStack());
         Icon tex = bindLiquidTexture(liquid.itemID, liquid.itemMeta);
         CCRenderState.startDrawing(7);
         renderLiquidCuboid(bound, tex, res);
         CCRenderState.draw();
         GL11.glEnable(2896);
         GL11.glDisable(3042);
      }
   }

   public static void renderItemUniform(ItemStack item) {
      IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(item, ItemRenderType.ENTITY);
      boolean is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(ItemRenderType.ENTITY, item, ItemRendererHelper.BLOCK_3D);
      boolean larger = false;
      if(item.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.blocksList[item.itemID].getRenderType())) {
         int d = Block.blocksList[item.itemID].getRenderType();
         larger = d != 1 && d != 19 && d != 12 && d != 2;
      } else if(is3D) {
         larger = true;
      }

      double d1 = 2.0D;
      double d11 = 1.0D / d1;
      if(larger) {
         GL11.glScaled(d1, d1, d1);
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      entityItem.setEntityItemStack(item);
      uniformRenderItem.doRenderItem(entityItem, 0.0D, larger?0.09D:0.06D, 0.0D, 0.0F, 0.0F);
      if(larger) {
         GL11.glScaled(d1, d1, d1);
      }

   }

   public static void addVertexWithTransform(double x, double y, double z, double u, double v, Transformation t, IUVTransformation ut) {
      UV uv = (new UV(u, v)).apply(ut);
      Vector3 vec = (new Vector3(x, y, z)).apply(t);
      Tessellator.instance.addVertexWithUV(vec.x, vec.y, vec.z, uv.u, uv.v);
   }

   public static void renderBlock(Cuboid6 c, int sideMask, IFaceRenderer r) {
      double x1 = c.min.x;
      double x2 = c.max.x;
      double y1 = c.min.y;
      double y2 = c.max.y;
      double z1 = c.min.z;
      double z2 = c.max.z;
      double u1 = 0.0D;
      double u2 = 0.0D;
      double v1 = 0.0D;
      double v2 = 0.0D;
      if((sideMask & 1) == 0) {
         face[0].set(x1, y1, z2, x1, z2);
         face[1].set(x1, y1, z1, x1, z1);
         face[2].set(x2, y1, z1, x2, z1);
         face[3].set(x2, y1, z2, x2, z2);
         r.renderFace(face, c.min.y > 0.0D?6:0);
      }

      if((sideMask & 2) == 0) {
         u1 = x1 + 2.0D;
         u2 = x2 + 2.0D;
         face[0].set(x2, y2, z2, u2, z2);
         face[1].set(x2, y2, z1, u2, z1);
         face[2].set(x1, y2, z1, u1, z1);
         face[3].set(x1, y2, z2, u1, z2);
         r.renderFace(face, c.max.y < 1.0D?7:1);
      }

      if((sideMask & 4) == 0) {
         u1 = 1.0D - x1 + 4.0D;
         v1 = 1.0D - y2;
         u2 = 1.0D - x2 + 4.0D;
         v2 = 1.0D - y1;
         face[0].set(x1, y1, z1, u1, v2);
         face[1].set(x1, y2, z1, u1, v1);
         face[2].set(x2, y2, z1, u2, v1);
         face[3].set(x2, y1, z1, u2, v2);
         r.renderFace(face, c.min.z > 0.0D?8:2);
      }

      if((sideMask & 8) == 0) {
         u1 = x1 + 6.0D;
         v1 = 1.0D - y2;
         u2 = x2 + 6.0D;
         v2 = 1.0D - y1;
         face[0].set(x2, y1, z2, u2, v2);
         face[1].set(x2, y2, z2, u2, v1);
         face[2].set(x1, y2, z2, u1, v1);
         face[3].set(x1, y1, z2, u1, v2);
         r.renderFace(face, c.max.z < 1.0D?9:3);
      }

      if((sideMask & 16) == 0) {
         u1 = z1 + 8.0D;
         v1 = 1.0D - y2;
         u2 = z2 + 8.0D;
         v2 = 1.0D - y1;
         face[0].set(x1, y1, z2, u2, v2);
         face[1].set(x1, y2, z2, u2, v1);
         face[2].set(x1, y2, z1, u1, v1);
         face[3].set(x1, y1, z1, u1, v2);
         r.renderFace(face, c.min.x > 0.0D?10:4);
      }

      if((sideMask & 32) == 0) {
         u1 = 1.0D - z1 + 10.0D;
         v1 = 1.0D - y2;
         u2 = 1.0D - z2 + 10.0D;
         v2 = 1.0D - y1;
         face[0].set(x2, y1, z1, u1, v2);
         face[1].set(x2, y2, z1, u1, v1);
         face[2].set(x2, y2, z2, u2, v1);
         face[3].set(x2, y1, z2, u2, v2);
         r.renderFace(face, c.max.x < 1.0D?11:5);
      }

   }

   public static void renderBlock(Cuboid6 bounds, int sideMask, final Transformation t, final IUVTransformation u, final IVertexModifier m) {
      renderBlock(bounds, sideMask, new IFaceRenderer() {

         boolean drawNormal = CCRenderState.useNormals();
         boolean computeNormal;
         Vector3 normal;
         Vertex5 vert;
         Vector3 vec;
         UV uv;

         {
            this.computeNormal = this.drawNormal || m != null && m.needsNormals();
            this.normal = new Vector3();
            this.vec = new Vector3();
            this.uv = new UV();
         }
         public void renderFace(Vertex5[] face, int side) {
            Tessellator tess = Tessellator.instance;

            for(int i = 0; i < face.length; ++i) {
               if(this.computeNormal) {
                  if(t != null) {
                     t.applyN(this.normal.set(Rotation.axes[side % 6]));
                  } else {
                     this.normal = Rotation.axes[side % 6];
                  }

                  if(this.drawNormal) {
                     tess.setNormal((float)this.normal.x, (float)this.normal.y, (float)this.normal.z);
                  }
               }

               this.vert = face[i];
               if(t != null) {
                  t.apply(this.vec.set(this.vert.vec));
               } else {
                  this.vec = this.vert.vec;
               }

               if(u != null) {
                  u.transform(this.uv.set(this.vert.uv));
               } else {
                  this.uv = this.vert.uv;
               }

               if(m != null) {
                  m.applyModifiers((CCModel)null, tess, this.vec, this.uv, this.normal, i);
               }

               tess.addVertexWithUV(this.vec.x, this.vec.y, this.vec.z, this.uv.u, this.uv.v);
            }

         }
      });
   }
}

package codechicken.nei;

import codechicken.core.alg.MathHelper;
import codechicken.core.render.RenderUtils;
import codechicken.nei.ClientHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ForgeSubscribe;
import org.lwjgl.opengl.GL11;

public class WorldOverlayRenderer {

   @ForgeSubscribe
   public void onWorldRenderLast(RenderWorldLastEvent event) {
      GL11.glPushMatrix();
      EntityLiving entity = event.context.mc.renderViewEntity;
      RenderUtils.translateToWorldCoords(entity, event.partialTicks);
      this.renderChunkBounds(entity);
      this.renderMobSpawnOverlay(entity);
      GL11.glPopMatrix();
   }

   private void renderMobSpawnOverlay(Entity entity) {
      if(ClientHandler.instance().mobSpawnOverlay != 0) {
         GL11.glDisable(3553);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         GL11.glDisable(2896);
         GL11.glLineWidth(1.5F);
         GL11.glBegin(1);
         GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);
         int curSpawnMode = 2;
         World world = entity.worldObj;
         int x1 = (int)entity.posX;
         int z1 = (int)entity.posZ;
         int y1 = (int)MathHelper.clip(entity.posY, 16.0D, (double)(world.getHeight() - 16));
         AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

         for(int x = x1 - 16; x <= x1 + 16; ++x) {
            for(int z = z1 - 16; z <= z1 + 16; ++z) {
               Chunk chunk = world.getChunkFromBlockCoords(x, z);
               BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
               if(!biome.getSpawnableList(EnumCreatureType.monster).isEmpty() && biome.getSpawningChance() > 0.0F) {
                  for(int y = y1 - 16; y < y1 + 16; ++y) {
                     int spawnMode = this.getSpawnMode(chunk, aabb, x, y, z);
                     if(spawnMode != 0) {
                        if(spawnMode != curSpawnMode) {
                           if(spawnMode == 1) {
                              GL11.glColor4f(1.0F, 1.0F, 0.0F, 1.0F);
                           } else {
                              GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);
                           }

                           curSpawnMode = spawnMode;
                        }

                        GL11.glVertex3d((double)x, (double)y + 0.004D, (double)z);
                        GL11.glVertex3d((double)(x + 1), (double)y + 0.004D, (double)(z + 1));
                        GL11.glVertex3d((double)(x + 1), (double)y + 0.004D, (double)z);
                        GL11.glVertex3d((double)x, (double)y + 0.004D, (double)(z + 1));
                     }
                  }
               }
            }
         }

         GL11.glEnd();
         GL11.glEnable(2896);
         GL11.glEnable(3553);
         GL11.glDisable(3042);
      }
   }

   private int getSpawnMode(Chunk chunk, AxisAlignedBB aabb, int x, int y, int z) {
      if(SpawnerAnimals.canCreatureTypeSpawnAtLocation(EnumCreatureType.monster, chunk.worldObj, x, y, z) && chunk.getSavedLightValue(EnumSkyBlock.Block, x & 15, y, z & 15) < 8) {
         aabb.minX = (double)x + 0.2D;
         aabb.maxX = (double)x + 0.8D;
         aabb.minY = (double)y + 0.01D;
         aabb.maxY = (double)y + 1.8D;
         aabb.minZ = (double)z + 0.2D;
         aabb.maxZ = (double)z + 0.8D;
         return chunk.worldObj.checkNoEntityCollision(aabb) && chunk.worldObj.getCollidingBlockBounds(aabb).isEmpty() && !chunk.worldObj.isAnyLiquid(aabb)?(chunk.getSavedLightValue(EnumSkyBlock.Sky, x & 15, y, z & 15) >= 8?1:2):0;
      } else {
         return 0;
      }
   }

   private void renderChunkBounds(Entity entity) {
      if(ClientHandler.instance().chunkOverlay != 0) {
         GL11.glDisable(3553);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         GL11.glDisable(2896);
         GL11.glLineWidth(1.5F);
         GL11.glBegin(1);

         for(int cx = -4; cx <= 4; ++cx) {
            for(int cz = -4; cz <= 4; ++cz) {
               double x1 = (double)(entity.chunkCoordX + cx << 4);
               double z1 = (double)(entity.chunkCoordZ + cz << 4);
               double x2 = x1 + 16.0D;
               double z2 = z1 + 16.0D;
               double dy = 128.0D;
               double y1 = Math.floor(entity.posY - dy / 2.0D);
               double y2 = y1 + dy;
               if(y1 < 0.0D) {
                  y1 = 0.0D;
                  y2 = dy;
               }

               if(y1 > (double)entity.worldObj.getHeight()) {
                  y2 = (double)entity.worldObj.getHeight();
                  y1 = y2 - dy;
               }

               double dist = Math.pow(1.5D, (double)(-(cx * cx + cz * cz)));
               GL11.glColor4d(0.9D, 0.0D, 0.0D, dist);
               if(cx >= 0 && cz >= 0) {
                  GL11.glVertex3d(x2, y1, z2);
                  GL11.glVertex3d(x2, y2, z2);
               }

               if(cx >= 0 && cz <= 0) {
                  GL11.glVertex3d(x2, y1, z1);
                  GL11.glVertex3d(x2, y2, z1);
               }

               if(cx <= 0 && cz >= 0) {
                  GL11.glVertex3d(x1, y1, z2);
                  GL11.glVertex3d(x1, y2, z2);
               }

               if(cx <= 0 && cz <= 0) {
                  GL11.glVertex3d(x1, y1, z1);
                  GL11.glVertex3d(x1, y2, z1);
               }

               if(ClientHandler.instance().chunkOverlay == 2 && cx == 0 && cz == 0) {
                  dy = 32.0D;
                  y1 = Math.floor(entity.posY - dy / 2.0D);
                  y2 = y1 + dy;
                  if(y1 < 0.0D) {
                     y1 = 0.0D;
                     y2 = dy;
                  }

                  if(y1 > (double)entity.worldObj.getHeight()) {
                     y2 = (double)entity.worldObj.getHeight();
                     y1 = y2 - dy;
                  }

                  GL11.glColor4d(0.0D, 0.9D, 0.0D, 0.4D);

                  double h;
                  for(h = (double)((int)y1); h <= y2; ++h) {
                     GL11.glVertex3d(x2, h, z1);
                     GL11.glVertex3d(x2, h, z2);
                     GL11.glVertex3d(x1, h, z1);
                     GL11.glVertex3d(x1, h, z2);
                     GL11.glVertex3d(x1, h, z2);
                     GL11.glVertex3d(x2, h, z2);
                     GL11.glVertex3d(x1, h, z1);
                     GL11.glVertex3d(x2, h, z1);
                  }

                  for(h = 1.0D; h <= 15.0D; ++h) {
                     GL11.glVertex3d(x1 + h, y1, z1);
                     GL11.glVertex3d(x1 + h, y2, z1);
                     GL11.glVertex3d(x1 + h, y1, z2);
                     GL11.glVertex3d(x1 + h, y2, z2);
                     GL11.glVertex3d(x1, y1, z1 + h);
                     GL11.glVertex3d(x1, y2, z1 + h);
                     GL11.glVertex3d(x2, y1, z1 + h);
                     GL11.glVertex3d(x2, y2, z1 + h);
                  }
               }
            }
         }

         GL11.glEnd();
         GL11.glEnable(2896);
         GL11.glEnable(3553);
         GL11.glDisable(3042);
      }
   }
}

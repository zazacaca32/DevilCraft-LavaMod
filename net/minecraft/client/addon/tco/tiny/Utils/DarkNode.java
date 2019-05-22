package net.minecraft.client.addon.tco.tiny.Utils;

import java.util.HashMap;
import java.util.Iterator;
import net.minecraft.client.addon.tco.tiny.IController;
import net.minecraft.client.addon.tco.tiny.IDarkNode;
import net.minecraft.client.addon.tco.tiny.Utils.Chunk;
import net.minecraft.client.addon.tco.tiny.Utils.Coords;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkEnergyControler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.LongHashMap;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.ChunkEvent.Load;
import net.minecraftforge.event.world.ChunkEvent.Unload;

public class DarkNode implements Cloneable {

   private static LongHashMap loadedChunkHashMap = new LongHashMap();
   public Coords coords;
   public int idNode;
   public boolean isActiv = false;
   public IController controller;
   public IDarkNode ref;


   public DarkNode(int x, int y, int z, IDarkNode ref) {
      this.coords = new Coords(x, y, z);
      this.idNode = ref.getIDNode();
      this.ref = ref;
      if(this.ref instanceof TileEntityBlockDarkEnergyControler) {
         this.controller = (IController)this.ref;
      }

   }

   public DarkNode(int x, int y, int z) {
      this.coords = new Coords(x, y, z);
   }

   public DarkNode(int x, int y, int z, int idNode) {
      this.coords = new Coords(x, y, z);
      this.idNode = idNode;
   }

   public DarkNode() {
      this.coords = new Coords(-1, -1, -1);
   }

   public int hashCode() {
      return this.coords.y << 16 ^ this.coords.x ^ Integer.reverse(this.coords.z);
   }

   public DarkNode clone() throws CloneNotSupportedException {
      return (DarkNode)super.clone();
   }

   public boolean equals(Object obj) {
      if(this == obj) {
         return true;
      } else if(obj == null) {
         return false;
      } else if(this.getClass() != obj.getClass()) {
         return false;
      } else {
         DarkNode other = (DarkNode)obj;
         return this.coords.x != other.coords.x?false:(this.coords.y != other.coords.y?false:this.coords.z == other.coords.z);
      }
   }

   public static void add(int xCoord, int yCoord, int zCoord, IDarkNode ref) {
      Chunk chunk = getOrNewChunk(xCoord >> 4, zCoord >> 4);
      chunk.addNode(new DarkNode(xCoord, yCoord, zCoord, ref));
   }

   public static void removeChunk(int xCoord, int zCoord) {
      if(loadedChunkHashMap.containsItem(chunkXZ2Int(xCoord, zCoord))) {
         loadedChunkHashMap.remove(chunkXZ2Int(xCoord, zCoord));
      }

   }

   public static void remove(int xCoord, int yCoord, int zCoord, int idNode) {
      Chunk chunk = getChunk(xCoord >> 4, zCoord >> 4);
      if(chunk != null) {
         chunk.removeNode(new DarkNode(xCoord, yCoord, zCoord, idNode));
      }

   }

   public static void setUpdateController(World w, int xCoord, int yCoord, int zCoord) {
      DarkNode[] list = new DarkNode[6];
      int x_ = (xCoord >> 4) - 1;
      int z_ = (zCoord >> 4) - 1;
      int count = 0;
      Chunk chunk = null;

      int i;
      label147:
      for(i = 0; i < 3; ++i) {
         for(int z = 0; z < 3; ++z) {
            chunk = (Chunk)loadedChunkHashMap.getValueByKey(chunkXZ2Int(x_ + i, z_ + z));
            if(chunk != null) {
               Iterator col = chunk.nodes.values().iterator();

               DarkNode d;
               while(col.hasNext()) {
                  d = (DarkNode)col.next();
                  if(d != null) {
                     if((d.coords.x == xCoord - 1 || d.coords.x == xCoord + 1) && d.coords.y == yCoord && d.coords.z == zCoord) {
                        if(d.controller != null) {
                           list[count] = d;
                           ++count;
                        }
                     } else if(d.coords.x == xCoord && (d.coords.y == yCoord - 1 || d.coords.y == yCoord + 1) && d.coords.z == zCoord) {
                        if(d.controller != null) {
                           list[count] = d;
                           ++count;
                        }
                     } else if(d.coords.x == xCoord && d.coords.y == yCoord && (d.coords.z == zCoord - 1 || d.coords.z == zCoord + 1)) {
                        if(d.controller != null) {
                           list[count] = d;
                           ++count;
                        }
                     } else if(count == 6) {
                        break label147;
                     }
                  }
               }

               col = chunk.controllers.values().iterator();

               while(col.hasNext()) {
                  d = (DarkNode)col.next();
                  if(d != null) {
                     if((d.coords.x == xCoord - 1 || d.coords.x == xCoord + 1) && d.coords.y == yCoord && d.coords.z == zCoord) {
                        if(d.controller != null) {
                           list[count] = d;
                           ++count;
                        }
                     } else if(d.coords.x == xCoord && (d.coords.y == yCoord - 1 || d.coords.y == yCoord + 1) && d.coords.z == zCoord) {
                        if(d.controller != null) {
                           list[count] = d;
                           ++count;
                        }
                     } else if(d.coords.x == xCoord && d.coords.y == yCoord && (d.coords.z == zCoord - 1 || d.coords.z == zCoord + 1)) {
                        if(d.controller != null) {
                           list[count] = d;
                           ++count;
                        }
                     } else if(count == 6) {
                        break label147;
                     }
                  }
               }
            }
         }
      }

      for(i = 0; i < list.length; ++i) {
         if(list[i] != null) {
            list[i].controller.updateController();
         }
      }

   }

   public static HashMap configureController(int xCoord, int yCoord, int zCoord) {
      HashMap list = new HashMap();
      int x_ = (xCoord >> 4) - 2;
      int z_ = (zCoord >> 4) - 2;
      Chunk chunk = null;

      for(int x = 0; x < 5; ++x) {
         for(int z = 0; z < 5; ++z) {
            chunk = (Chunk)loadedChunkHashMap.getValueByKey(chunkXZ2Int(x_ + x, z_ + z));
            if(chunk != null) {
               list.putAll(chunk.nodes);
               list.putAll(chunk.controllers);
            }
         }
      }

      return list;
   }

   public static void clearControllerByDarkNodes(int xCoord, int yCoord, int zCoord) {
      int x_ = (xCoord >> 4) - 2;
      int z_ = (zCoord >> 4) - 2;
      Chunk chunk = null;

      for(int x = 0; x < 5; ++x) {
         for(int z = 0; z < 5; ++z) {
            chunk = (Chunk)loadedChunkHashMap.getValueByKey(chunkXZ2Int(x_ + x, z_ + z));
            if(chunk != null) {
               Iterator col = chunk.nodes.values().iterator();

               while(col.hasNext()) {
                  DarkNode d = (DarkNode)col.next();
                  if(d != null && d.controller != null) {
                     Coords coords = d.controller.getCoords();
                     if(coords != null && coords.x == xCoord && coords.y == yCoord && coords.z == zCoord) {
                        d.controller = null;
                        if(d.isActiv) {
                           d.isActiv = false;
                        }
                     }
                  }
               }
            }
         }
      }

   }

   public static long chunkXZ2Int(int par0, int par1) {
      return (long)par0 & 4294967295L | ((long)par1 & 4294967295L) << 32;
   }

   @ForgeSubscribe
   public void onUnLoadChunk(Unload event) {
      if(!event.world.isRemote && event.world.getWorldInfo().getDimension() == 0 && loadedChunkHashMap.containsItem(chunkXZ2Int(event.getChunk().xPosition, event.getChunk().zPosition))) {
         loadedChunkHashMap.remove(chunkXZ2Int(event.getChunk().xPosition, event.getChunk().zPosition));
         Chunk chunk = null;
         int x_ = event.getChunk().xPosition - 1;
         int z_ = event.getChunk().zPosition - 1;
         boolean counter = false;

         for(int x = 0; x < 3; ++x) {
            for(int z = 0; z < 3; ++z) {
               chunk = getChunk(x_ + x, z_ + z);
               if(chunk != null && chunk.controllers != null) {
                  Iterator it = chunk.controllers.values().iterator();

                  while(it.hasNext()) {
                     ((DarkNode)it.next()).controller.updateController();
                  }
               }
            }
         }
      }

   }

   public static Chunk getOrNewChunk(int x, int y) {
      long l = chunkXZ2Int(x, y);
      Chunk chunk = (Chunk)loadedChunkHashMap.getValueByKey(l);
      if(chunk == null) {
         chunk = new Chunk(x, y);
         loadedChunkHashMap.add(l, chunk);
      }

      return chunk;
   }

   public static Chunk getChunk(int x, int y) {
      return (Chunk)loadedChunkHashMap.getValueByKey(chunkXZ2Int(x, y));
   }

   @ForgeSubscribe
   public void onLoadChunk(Load event) {
      if(!event.world.isRemote && event.world.getWorldInfo().getDimension() == 0) {
         Chunk chunk = null;
         boolean flagUpdateControllers = false;
         Iterator iterator = event.getChunk().chunkTileEntityMap.values().iterator();

         while(iterator.hasNext()) {
            TileEntity var121 = (TileEntity)iterator.next();
            if(var121 != null && var121.getBlockType() != null && Utils.findDarkEnergyNodeFromID(var121.getBlockType().blockID)) {
               flagUpdateControllers = true;
               if(chunk == null) {
                  chunk = getOrNewChunk(event.getChunk().xPosition, event.getChunk().zPosition);
               }

               chunk.addNode(new DarkNode(var121.xCoord, var121.yCoord, var121.zCoord, (IDarkNode)var121));
            }
         }

         int var12 = 0;
         if(flagUpdateControllers) {
            int x_ = event.getChunk().xPosition - 1;
            int z_ = event.getChunk().zPosition - 1;
            boolean count = false;

            for(int x = 0; x < 3; ++x) {
               for(int z = 0; z < 3; ++z) {
                  chunk = getChunk(x_ + x, z_ + z);
                  if(chunk != null && chunk.controllers != null) {
                     for(Iterator it = chunk.controllers.values().iterator(); it.hasNext(); ++var12) {
                        ((DarkNode)it.next()).controller.updateController();
                     }
                  }
               }
            }
         }
      }

   }

}

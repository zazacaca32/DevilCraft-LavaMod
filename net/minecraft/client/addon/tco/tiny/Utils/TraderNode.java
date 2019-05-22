package net.minecraft.client.addon.tco.tiny.Utils;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTradeBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TraderNode {

   public int x;
   public int y;
   public int z;
   World world;
   public boolean init;
   TileEntityBlockTradeBase node;
   public boolean isRemove = false;
   private short timeload = 1200;
   boolean flagl = true;


   public TraderNode(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public TraderNode(World world, int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.world = world;
   }

   public int hashCode() {
      boolean prime = true;
      byte result = 1;
      int result1 = 31 * result + this.x;
      result1 = 31 * result1 + this.y;
      result1 = 31 * result1 + this.z;
      return result1;
   }

   public boolean equals(Object obj) {
      if(this == obj) {
         return true;
      } else if(obj == null) {
         return false;
      } else if(this.getClass() != obj.getClass()) {
         return false;
      } else {
         TraderNode other = (TraderNode)obj;
         return this.x != other.x?false:(this.y != other.y?false:this.z == other.z);
      }
   }

   public World getWorld() {
      return this.world;
   }

   public void writeToNBT(NBTTagCompound c) {
      c.setShort("x", (short)this.x);
      c.setByte("y", (byte)this.y);
      c.setShort("z", (short)this.z);
   }

   public static TraderNode loadNodeFromNBT(NBTTagCompound c) {
      return c.hasKey("x") && c.hasKey("y") && c.hasKey("z")?new TraderNode(c.getShort("x"), c.getByte("y"), c.getShort("z")):null;
   }

   public TileEntityBlockTradeBase getRef(World world) {
      if(this.node == null) {
         if(this.timeload > 0) {
            short flagremove111 = this.timeload;
            this.timeload = (short)(flagremove111 - 1);
            if(flagremove111 % 60 == 0) {
               this.flagl = true;
            }
         }

         if(this.flagl) {
            this.flagl = false;
            boolean flagremove1111 = world.getChunkProvider().chunkExists(this.x >> 4, this.z >> 4);
            flagremove1111 = flagremove1111 && world.getChunkFromBlockCoords(this.x, this.z).isChunkLoaded;
            if(this.timeload > 0) {
               flagremove1111 = false;
            }

            TileEntity t1;
            if((t1 = world.getBlockTileEntity(this.x, this.y, this.z)) == null || !(t1 instanceof TileEntityBlockTradeBase)) {
               if(flagremove1111) {
                  this.isRemove = true;
               }

               return null;
            }

            this.node = (TileEntityBlockTradeBase)t1;
         }
      }

      return this.node;
   }
}

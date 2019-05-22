package codechicken.core.world;

import codechicken.core.world.WorldExtension;
import java.util.HashSet;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.chunk.Chunk;

public abstract class ChunkExtension {

   public final Chunk chunk;
   public final ChunkCoordIntPair coord;
   public final WorldExtension world;
   public HashSet watchedPlayers;


   public ChunkExtension(Chunk chunk, WorldExtension world) {
      this.chunk = chunk;
      this.coord = chunk.getChunkCoordIntPair();
      this.world = world;
      this.watchedPlayers = new HashSet();
   }

   public void loadData(NBTTagCompound tag) {}

   public void saveData(NBTTagCompound tag) {}

   public void load() {}

   public void unload() {}

   public final void sendPacketToPlayers(Packet packet) {
      Iterator var3 = this.watchedPlayers.iterator();

      while(var3.hasNext()) {
         EntityPlayerMP player = (EntityPlayerMP)var3.next();
         player.playerNetServerHandler.sendPacketToPlayer(packet);
      }

   }

   public final void watchPlayer(EntityPlayerMP player) {
      this.watchedPlayers.add(player);
      this.onWatchPlayer(player);
   }

   public void onWatchPlayer(EntityPlayerMP player) {}

   public final void unwatchPlayer(EntityPlayerMP player) {
      this.watchedPlayers.remove(player);
      this.onUnWatchPlayer(player);
   }

   public void onUnWatchPlayer(EntityPlayerMP player) {}

   public void sendUpdatePackets() {}

   public int hashCode() {
      return this.coord.chunkXPos ^ this.coord.chunkZPos;
   }

   public boolean equals(Object o) {
      return o instanceof ChunkExtension && ((ChunkExtension)o).coord.equals(this.coord) || o instanceof ChunkCoordIntPair && this.coord.equals(o) || o instanceof Long && ((Long)o).longValue() == ((long)this.coord.chunkXPos << 32 | (long)this.coord.chunkZPos);
   }
}

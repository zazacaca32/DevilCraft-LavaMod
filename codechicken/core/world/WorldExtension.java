package codechicken.core.world;

import codechicken.core.world.ChunkExtension;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public abstract class WorldExtension {

   public final World world;
   public HashMap chunkMap = new HashMap();


   public WorldExtension(World world) {
      this.world = world;
   }

   public void load() {}

   public void unload() {}

   public void save() {}

   public void preTick() {}

   public void postTick() {}

   protected final void addChunk(ChunkExtension extension) {
      this.chunkMap.put(extension.chunk, extension);
   }

   protected final void loadChunk(Chunk chunk) {
      ((ChunkExtension)this.chunkMap.get(chunk)).load();
   }

   protected final void unloadChunk(Chunk chunk) {
      ((ChunkExtension)this.chunkMap.get(chunk)).unload();
   }

   protected final void loadChunkData(Chunk chunk, NBTTagCompound tag) {
      ((ChunkExtension)this.chunkMap.get(chunk)).loadData(tag);
   }

   protected final void saveChunkData(Chunk chunk, NBTTagCompound tag) {
      ((ChunkExtension)this.chunkMap.get(chunk)).saveData(tag);
   }

   protected final void remChunk(Chunk chunk) {
      this.chunkMap.remove(chunk);
   }

   protected final void watchChunk(Chunk chunk, EntityPlayerMP player) {
      ((ChunkExtension)this.chunkMap.get(chunk)).watchPlayer(player);
   }

   protected final void unwatchChunk(Chunk chunk, EntityPlayerMP player) {
      ChunkExtension extension = (ChunkExtension)this.chunkMap.get(chunk);
      if(extension != null) {
         extension.unwatchPlayer(player);
      }

   }

   protected final void sendChunkUpdates(Chunk chunk) {
      ((ChunkExtension)this.chunkMap.get(chunk)).sendUpdatePackets();
   }

   public boolean containsChunk(Chunk chunk) {
      return this.chunkMap.containsKey(chunk);
   }

   public ChunkExtension getChunkExtension(int chunkXPos, int chunkZPos) {
      return !this.world.blockExists(chunkXPos << 4, 128, chunkZPos << 4)?null:(ChunkExtension)this.chunkMap.get(this.world.getChunkFromChunkCoords(chunkXPos, chunkZPos));
   }
}

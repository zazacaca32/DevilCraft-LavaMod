package codechicken.core.world;

import codechicken.core.world.ChunkExtension;
import codechicken.core.world.WorldExtension;
import codechicken.core.world.WorldExtensionManager;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public abstract class WorldExtensionInstantiator {

   public int instantiatorID;


   public abstract WorldExtension createWorldExtension(World var1);

   public abstract ChunkExtension createChunkExtension(Chunk var1, WorldExtension var2);

   public WorldExtension getExtension(World world) {
      return WorldExtensionManager.getWorldExtension(world, this.instantiatorID);
   }
}

package codechicken.core.world;

import codechicken.core.world.WorldExtension;
import codechicken.core.world.WorldExtensionInstantiator;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.EmptyChunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.ChunkDataEvent.Load;
import net.minecraftforge.event.world.ChunkDataEvent.Save;
import net.minecraftforge.event.world.ChunkEvent.Unload;
import net.minecraftforge.event.world.ChunkWatchEvent.UnWatch;
import net.minecraftforge.event.world.ChunkWatchEvent.Watch;

public class WorldExtensionManager {

   private static boolean initialised;
   private static ArrayList extensionIntialisers = new ArrayList();
   private static HashMap worldMap = new HashMap();


   public static void registerWorldExtension(WorldExtensionInstantiator init) {
      if(!initialised) {
         init();
      }

      init.instantiatorID = extensionIntialisers.size();
      extensionIntialisers.add(init);
   }

   private static void init() {
      initialised = true;
      MinecraftForge.EVENT_BUS.register(new WorldExtensionManager.WorldExtensionEventHandler());
      TickRegistry.registerTickHandler(new WorldExtensionManager.WorldExtensionServerTickHandler(), Side.SERVER);
      if(FMLCommonHandler.instance().getSide().isClient()) {
         TickRegistry.registerTickHandler(new WorldExtensionManager.WorldExtensionClientTickHandler(), Side.CLIENT);
      }

   }

   private static void onWorldLoad(World world) {
      WorldExtension[] extensions = new WorldExtension[extensionIntialisers.size()];

      for(int extension = 0; extension < extensions.length; ++extension) {
         extensions[extension] = ((WorldExtensionInstantiator)extensionIntialisers.get(extension)).createWorldExtension(world);
      }

      worldMap.put(world, extensions);
      WorldExtension[] var5 = extensions;
      int var4 = extensions.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         WorldExtension var6 = var5[var3];
         var6.load();
      }

   }

   public static void onWorldUnload(World world) {
      WorldExtension[] var4;
      int var3 = (var4 = (WorldExtension[])worldMap.remove(world)).length;

      for(int var2 = 0; var2 < var3; ++var2) {
         WorldExtension extension = var4[var2];
         extension.unload();
      }

   }

   private static void createChunkExtension(World world, Chunk chunk) {
      WorldExtension[] extensions = (WorldExtension[])worldMap.get(world);

      for(int i = 0; i < extensionIntialisers.size(); ++i) {
         if(!extensions[i].containsChunk(chunk)) {
            extensions[i].addChunk(((WorldExtensionInstantiator)extensionIntialisers.get(i)).createChunkExtension(chunk, extensions[i]));
         }
      }

   }

   private static void removeChunk(World world, Chunk chunk) {
      WorldExtension[] var5;
      int var4 = (var5 = (WorldExtension[])worldMap.get(world)).length;

      for(int var3 = 0; var3 < var4; ++var3) {
         WorldExtension extension = var5[var3];
         extension.remChunk(chunk);
      }

   }

   private static void preTick(World world) {
      WorldExtension[] var4;
      int var3 = (var4 = (WorldExtension[])worldMap.get(world)).length;

      for(int var2 = 0; var2 < var3; ++var2) {
         WorldExtension extension = var4[var2];
         extension.preTick();
      }

   }

   private static void postTick(World world) {
      WorldExtension[] var4;
      int var3 = (var4 = (WorldExtension[])worldMap.get(world)).length;

      for(int var2 = 0; var2 < var3; ++var2) {
         WorldExtension extension = var4[var2];
         extension.postTick();
      }

   }

   public static WorldExtension getWorldExtension(World world, int instantiatorID) {
      return ((WorldExtension[])worldMap.get(world))[instantiatorID];
   }

   public static class WorldExtensionClientTickHandler implements ITickHandler {

      public void tickStart(EnumSet type, Object ... tickData) {
         if(type.contains(TickType.CLIENT)) {
            WorldClient world = Minecraft.getMinecraft().theWorld;
            if(WorldExtensionManager.worldMap.containsKey(world)) {
               WorldExtensionManager.preTick(world);
            }
         }

      }

      public void tickEnd(EnumSet type, Object ... tickData) {
         if(type.contains(TickType.CLIENT)) {
            WorldClient world = Minecraft.getMinecraft().theWorld;
            if(WorldExtensionManager.worldMap.containsKey(world)) {
               WorldExtensionManager.postTick(world);
            }
         }

      }

      public EnumSet ticks() {
         return EnumSet.of(TickType.CLIENT);
      }

      public String getLabel() {
         return "WorldExtenstions";
      }
   }

   public static class WorldExtensionEventHandler {

      @ForgeSubscribe
      public void onChunkDataLoad(Load event) {
         if(!WorldExtensionManager.worldMap.containsKey(event.world)) {
            WorldExtensionManager.onWorldLoad(event.world);
         }

         WorldExtensionManager.createChunkExtension(event.world, event.getChunk());
         WorldExtension[] var5;
         int var4 = (var5 = (WorldExtension[])WorldExtensionManager.worldMap.get(event.world)).length;

         for(int var3 = 0; var3 < var4; ++var3) {
            WorldExtension extension = var5[var3];
            extension.loadChunkData(event.getChunk(), event.getData());
         }

      }

      @ForgeSubscribe
      public void onChunkDataSave(Save event) {
         WorldExtension[] var5;
         int var4 = (var5 = (WorldExtension[])WorldExtensionManager.worldMap.get(event.world)).length;

         for(int var3 = 0; var3 < var4; ++var3) {
            WorldExtension extension = var5[var3];
            extension.saveChunkData(event.getChunk(), event.getData());
         }

         if(!event.getChunk().isChunkLoaded) {
            WorldExtensionManager.removeChunk(event.world, event.getChunk());
         }

      }

      @ForgeSubscribe
      public void onChunkLoad(net.minecraftforge.event.world.ChunkEvent.Load event) {
         if(!WorldExtensionManager.worldMap.containsKey(event.world)) {
            WorldExtensionManager.onWorldLoad(event.world);
         }

         WorldExtensionManager.createChunkExtension(event.world, event.getChunk());
         WorldExtension[] var5;
         int var4 = (var5 = (WorldExtension[])WorldExtensionManager.worldMap.get(event.world)).length;

         for(int var3 = 0; var3 < var4; ++var3) {
            WorldExtension extension = var5[var3];
            extension.loadChunk(event.getChunk());
         }

      }

      @ForgeSubscribe
      public void onChunkUnLoad(Unload event) {
         if(!(event.getChunk() instanceof EmptyChunk)) {
            WorldExtension[] var5;
            int var4 = (var5 = (WorldExtension[])WorldExtensionManager.worldMap.get(event.world)).length;

            for(int var3 = 0; var3 < var4; ++var3) {
               WorldExtension extension = var5[var3];
               extension.unloadChunk(event.getChunk());
            }

            if(event.world.isRemote) {
               WorldExtensionManager.removeChunk(event.world, event.getChunk());
            }

         }
      }

      @ForgeSubscribe
      public void onWorldSave(net.minecraftforge.event.world.WorldEvent.Save event) {
         WorldExtension[] var5;
         int var4 = (var5 = (WorldExtension[])WorldExtensionManager.worldMap.get(event.world)).length;

         for(int var3 = 0; var3 < var4; ++var3) {
            WorldExtension extension = var5[var3];
            extension.save();
         }

      }

      @ForgeSubscribe
      public void onWorldLoad(net.minecraftforge.event.world.WorldEvent.Load event) {
         if(!WorldExtensionManager.worldMap.containsKey(event.world)) {
            WorldExtensionManager.onWorldLoad(event.world);
         }

      }

      @ForgeSubscribe
      public void onWorldUnLoad(net.minecraftforge.event.world.WorldEvent.Unload event) {
         WorldExtensionManager.onWorldUnload(event.world);
      }

      @ForgeSubscribe
      public void onChunkWatch(Watch event) {
         Chunk chunk = event.player.worldObj.getChunkFromChunkCoords(event.chunk.chunkXPos, event.chunk.chunkZPos);
         WorldExtension[] var6;
         int var5 = (var6 = (WorldExtension[])WorldExtensionManager.worldMap.get(event.player.worldObj)).length;

         for(int var4 = 0; var4 < var5; ++var4) {
            WorldExtension extension = var6[var4];
            extension.watchChunk(chunk, event.player);
         }

      }

      @ForgeSubscribe
      public void onChunkUnWatch(UnWatch event) {
         Chunk chunk = event.player.worldObj.getChunkFromChunkCoords(event.chunk.chunkXPos, event.chunk.chunkZPos);
         WorldExtension[] var6;
         int var5 = (var6 = (WorldExtension[])WorldExtensionManager.worldMap.get(event.player.worldObj)).length;

         for(int var4 = 0; var4 < var5; ++var4) {
            WorldExtension extension = var6[var4];
            extension.unwatchChunk(chunk, event.player);
         }

      }
   }

   public static class WorldExtensionServerTickHandler implements ITickHandler {

      public void tickStart(EnumSet type, Object ... tickData) {
         if(type.contains(TickType.WORLD)) {
            WorldExtensionManager.preTick((World)tickData[0]);
         }

      }

      public void tickEnd(EnumSet type, Object ... tickData) {
         if(type.contains(TickType.WORLD)) {
            WorldExtensionManager.postTick((World)tickData[0]);
         }

      }

      public EnumSet ticks() {
         return EnumSet.of(TickType.WORLD, TickType.CLIENT);
      }

      public String getLabel() {
         return "WorldExtenstions";
      }
   }
}

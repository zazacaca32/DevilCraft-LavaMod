package codechicken.core.packet;

import codechicken.core.data.MCDataInput;
import codechicken.core.data.MCDataOutput;
import codechicken.core.packet.MetaPacket;
import codechicken.core.vec.BlockCoord;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.ITinyPacketHandler;
import cpw.mods.fml.common.network.NetworkModHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.NetClientHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetServerHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet131MapData;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.liquids.LiquidStack;

public final class PacketCustom implements MCDataInput, MCDataOutput {

   public static PacketCustom.PacketAssembler assembler = new PacketCustom.PacketAssembler();
   public static PacketCustom.IPacketCarrier carrier250 = new PacketCustom.Packet250Carrier();
   public static PacketCustom.IPacketCarrier carrier131 = new PacketCustom.Packet131Carrier();
   private static int assemblyID = 0;
   private Object channel;
   private int type;
   private boolean isChunkDataPacket;
   private ByteArrayOutputStream dataarrayout;
   private DataOutputStream dataout;
   private DataInputStream datain;
   private static HashMap clienthandlermap = new HashMap();
   private static HashMap serverhandlermap = new HashMap();


   public static PacketCustom.IPacketCarrier carrierForChannel(Object channel) {
      return channel instanceof String?carrier250:(FMLNetworkHandler.instance().findNetworkModHandler(channel) != null?carrier131:null);
   }

   public static void writeInt(byte[] b, int pos, int i) {
      b[pos++] = (byte)(i >>> 24);
      b[pos++] = (byte)(i >> 16);
      b[pos++] = (byte)(i >> 8);
      b[pos++] = (byte)i;
   }

   public static int readInt(byte[] b, int pos) {
      return (b[pos++] & 255) << 24 | (b[pos++] & 255) << 16 | (b[pos++] & 255) << 8 | b[pos++] & 255;
   }

   private PacketCustom(Object channel, int type, byte[] data) {
      this.channel = channel;
      this.type = type;
      if(type > 128) {
         data = this.decompress(data);
      }

      this.datain = new DataInputStream(new ByteArrayInputStream(data));
   }

   public PacketCustom(Object channel, int type) {
      if(type > 0 && type < 128) {
         this.channel = channel;
         this.type = type;
         this.isChunkDataPacket = false;
         this.dataarrayout = new ByteArrayOutputStream();
         this.dataout = new DataOutputStream(this.dataarrayout);
      } else {
         throw new IllegalArgumentException("Packet type: " + type + " is not within required 0 < t < 0x80");
      }
   }

   public boolean incoming() {
      return this.dataout == null;
   }

   public int getType() {
      return this.type & 127;
   }

   public PacketCustom setChunkDataPacket() {
      this.isChunkDataPacket = true;
      return this;
   }

   private byte[] decompress(byte[] cdata) {
      if((this.type & 128) == 0) {
         return cdata;
      } else {
         Inflater inflater = new Inflater();

         byte[] var5;
         try {
            byte[] e = new byte[readInt(cdata, 0)];
            inflater.setInput(cdata, 4, cdata.length - 4);
            inflater.inflate(e);
            var5 = e;
         } catch (Exception var8) {
            throw new RuntimeException(var8);
         } finally {
            inflater.end();
         }

         return var5;
      }
   }

   public PacketCustom compressed() {
      if(this.incoming()) {
         throw new IllegalStateException("Tried to compress an incoming packet");
      } else if((this.type & 128) != 0) {
         throw new IllegalStateException("Packet already compressed");
      } else {
         this.type |= 128;
         return this;
      }
   }

   private byte[] compress(byte[] data) {
      Deflater deflater = new Deflater();

      byte[] var6;
      try {
         deflater.setInput(data, 0, data.length);
         deflater.finish();
         byte[] e = new byte[data.length + 4];
         int clen = deflater.deflate(e, 4, data.length);
         if(clen == data.length || !deflater.finished()) {
            this.type &= 127;
            var6 = data;
            return var6;
         }

         writeInt(e, 0, data.length);
         var6 = e;
      } catch (Exception var9) {
         throw new RuntimeException(var9);
      } finally {
         deflater.end();
      }

      return var6;
   }

   public Packet toPacket() {
      if(this.incoming()) {
         throw new IllegalStateException("Tried to write an incoming packet");
      } else {
         try {
            this.dataout.close();
         } catch (IOException var9) {
            throw new RuntimeException(var9);
         }

         byte[] data = this.dataarrayout.toByteArray();
         if(data.length > 32000 || (this.type & 128) != 0) {
            data = this.compress(data);
         }

         PacketCustom.IPacketCarrier carrier = carrierForChannel(this.channel);
         if(data.length > 32000 && carrier.shortCapped()) {
            MetaPacket payload = new MetaPacket(new Packet[0]);
            int asmID = assemblyID++;
            byte[] hdata = new byte[9];
            writeInt(hdata, 0, asmID);
            hdata[4] = (byte)this.type;
            writeInt(hdata, 5, data.length);
            payload.packets.add(carrier.write(this.channel, this.isChunkDataPacket, 128, hdata));

            for(int i = 0; i < data.length; i += 32000) {
               int size = Math.min(data.length - i, 32000);
               byte[] sdata = new byte[size + 4];
               writeInt(sdata, 0, asmID);
               System.arraycopy(data, i, sdata, 4, size);
               payload.packets.add(carrier.write(this.channel, this.isChunkDataPacket, 128, sdata));
            }

            return payload;
         } else {
            return carrier.write(this.channel, this.isChunkDataPacket, this.type, data);
         }
      }
   }

   public PacketCustom writeBoolean(boolean b) {
      try {
         this.dataout.writeBoolean(b);
         return this;
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public PacketCustom writeByte(int b) {
      try {
         this.dataout.writeByte(b);
         return this;
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public PacketCustom writeShort(int s) {
      try {
         this.dataout.writeShort(s);
         return this;
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public PacketCustom writeInt(int i) {
      try {
         this.dataout.writeInt(i);
         return this;
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public PacketCustom writeFloat(float f) {
      try {
         this.dataout.writeFloat(f);
         return this;
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public PacketCustom writeDouble(double d) {
      try {
         this.dataout.writeDouble(d);
         return this;
      } catch (IOException var4) {
         throw new RuntimeException(var4);
      }
   }

   public PacketCustom writeLong(long l) {
      try {
         this.dataout.writeLong(l);
         return this;
      } catch (IOException var4) {
         throw new RuntimeException(var4);
      }
   }

   public PacketCustom writeChar(char c) {
      try {
         this.dataout.writeChar(c);
         return this;
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public PacketCustom writeByteArray(byte[] barray) {
      try {
         this.dataout.write(barray);
         return this;
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public PacketCustom writeCoord(int x, int y, int z) {
      this.writeInt(x);
      this.writeInt(y);
      this.writeInt(z);
      return this;
   }

   public PacketCustom writeCoord(BlockCoord coord) {
      this.writeInt(coord.x);
      this.writeInt(coord.y);
      this.writeInt(coord.z);
      return this;
   }

   public PacketCustom writeString(String s) {
      try {
         if(s.length() > '\uffff') {
            throw new IOException("String length: " + s.length() + "too long.");
         } else {
            this.dataout.writeShort(s.length());
            this.dataout.writeChars(s);
            return this;
         }
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public PacketCustom writeItemStack(ItemStack stack) {
      this.writeItemStack(stack, false);
      return this;
   }

   public PacketCustom writeItemStack(ItemStack stack, boolean large) {
      if(stack == null) {
         this.writeShort(-1);
      } else {
         this.writeShort(stack.itemID);
         if(large) {
            this.writeInt(stack.stackSize);
         } else {
            this.writeByte(stack.stackSize);
         }

         this.writeShort(stack.getItemDamage());
         this.writeNBTTagCompound(stack.stackTagCompound);
      }

      return this;
   }

   public PacketCustom writeNBTTagCompound(NBTTagCompound compound) {
      try {
         if(compound == null) {
            this.writeShort(-1);
         } else {
            byte[] e = CompressedStreamTools.compress(compound);
            this.writeShort((short)e.length);
            this.writeByteArray(e);
         }

         return this;
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public PacketCustom writeLiquidStack(LiquidStack liquid) {
      if(liquid == null) {
         this.writeShort(-1);
      } else {
         this.writeShort(liquid.itemID);
         this.writeInt(liquid.amount);
         this.writeShort(liquid.itemMeta);
      }

      return this;
   }

   public boolean readBoolean() {
      try {
         return this.datain.readBoolean();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }
   }

   public int readUnsignedByte() {
      return this.readByte() & 255;
   }

   public int readUnsignedShort() {
      return this.readShort() & '\uffff';
   }

   public byte readByte() {
      try {
         return this.datain.readByte();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }
   }

   public short readShort() {
      try {
         return this.datain.readShort();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }
   }

   public int readInt() {
      try {
         return this.datain.readInt();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }
   }

   public float readFloat() {
      try {
         return this.datain.readFloat();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }
   }

   public double readDouble() {
      try {
         return this.datain.readDouble();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }
   }

   public long readLong() {
      try {
         return this.datain.readLong();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }
   }

   public char readChar() {
      try {
         return this.datain.readChar();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }
   }

   public BlockCoord readCoord() {
      return new BlockCoord(this.readInt(), this.readInt(), this.readInt());
   }

   public byte[] readByteArray(int length) {
      try {
         byte[] e = new byte[length];
         this.datain.readFully(e, 0, length);
         return e;
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public String readString() {
      try {
         int e = this.datain.readUnsignedShort();
         char[] chars = new char[e];

         for(int i = 0; i < e; ++i) {
            chars[i] = this.readChar();
         }

         return new String(chars);
      } catch (IOException var4) {
         throw new RuntimeException(var4);
      }
   }

   public ItemStack readItemStack() {
      return this.readItemStack(false);
   }

   public ItemStack readItemStack(boolean large) {
      ItemStack var2 = null;
      short itemID = this.readShort();
      if(itemID >= 0) {
         int stackSize = large?this.readInt():this.readByte();
         short damage = this.readShort();
         var2 = new ItemStack(itemID, stackSize, damage);
         var2.stackTagCompound = this.readNBTTagCompound();
      }

      return var2;
   }

   public NBTTagCompound readNBTTagCompound() {
      try {
         short e = this.readShort();
         if(e < 0) {
            return null;
         } else {
            byte[] var3 = this.readByteArray(e);
            return CompressedStreamTools.decompress(var3);
         }
      } catch (IOException var31) {
         throw new RuntimeException(var31);
      }
   }

   public LiquidStack readLiquidStack() {
      LiquidStack var2 = null;
      short liquidID = this.readShort();
      if(liquidID >= 0) {
         var2 = new LiquidStack(liquidID, this.readInt(), this.readUnsignedShort());
      }

      return var2;
   }

   public boolean more() {
      try {
         return this.datain.available() > 0;
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }
   }

   public static void assignHandler(String channel, int firstID, int lastID, PacketCustom.ICustomPacketHandler IHandler) {
      Side side = IHandler instanceof PacketCustom.IClientPacketHandler?Side.CLIENT:Side.SERVER;
      HashMap handlerMap = side.isClient()?clienthandlermap:serverhandlermap;
      Object handler = (PacketCustom.CustomPacketHandler)handlerMap.get(channel);
      if(handler == null) {
         if(side.isClient()) {
            handler = new PacketCustom.ClientPacketHandler(channel);
         } else {
            handler = new PacketCustom.ServerPacketHandler(channel);
         }

         handlerMap.put(channel, handler);
      }

      ((PacketCustom.CustomPacketHandler)handler).registerRange(firstID, lastID, IHandler);
   }

   public static void assignHandler(Object mod, PacketCustom.ICustomPacketHandler handler) {
      NetworkModHandler nmh = FMLNetworkHandler.instance().findNetworkModHandler(mod);
      if(nmh != null && nmh.getTinyPacketHandler() != null && nmh.getTinyPacketHandler() instanceof PacketCustom.CustomTinyPacketHandler) {
         ((PacketCustom.CustomTinyPacketHandler)nmh.getTinyPacketHandler()).registerSidedHandler(handler);
      } else {
         throw new IllegalStateException("Invalid network tiny packet handler for mod: " + mod);
      }
   }

   public void sendToPlayer(EntityPlayer player) {
      sendToPlayer(this.toPacket(), player);
   }

   public static void sendToPlayer(Packet packet, EntityPlayer player) {
      if(player == null) {
         sendToClients(packet);
      } else {
         ((EntityPlayerMP)player).playerNetServerHandler.sendPacketToPlayer(packet);
      }

   }

   public void sendToClients() {
      sendToClients(this.toPacket());
   }

   public static void sendToClients(Packet packet) {
      MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);
   }

   public void sendPacketToAllAround(double x, double y, double z, double range, int dim) {
      sendToAllAround(this.toPacket(), x, y, z, range, dim);
   }

   public static void sendToAllAround(Packet packet, double x, double y, double z, double range, int dim) {
      MinecraftServer.getServer().getConfigurationManager().sendToAllNear(x, y, z, range, dim, packet);
   }

   public void sendToDimension(int dim) {
      sendToDimension(this.toPacket(), dim);
   }

   public static void sendToDimension(Packet packet, int dim) {
      MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayersInDimension(packet, dim);
   }

   public void sendToChunk(World world, int chunkX, int chunkZ) {
      sendToChunk(this.toPacket(), world, chunkX, chunkZ);
   }

   public static void sendToChunk(Packet packet, World world, int chunkX, int chunkZ) {
      PlayerInstance p = ((WorldServer)world).getPlayerManager().getOrCreateChunkWatcher(chunkX, chunkZ, false);
      if(p != null) {
         p.sendToAllPlayersWatchingChunk(packet);
      }

   }

   public void sendToOps() {
      sendToOps(this.toPacket());
   }

   public static void sendToOps(Packet packet) {
      Iterator var2 = MinecraftServer.getServer().getConfigurationManager().playerEntityList.iterator();

      while(var2.hasNext()) {
         EntityPlayerMP player = (EntityPlayerMP)var2.next();
         if(MinecraftServer.getServer().getConfigurationManager().areCommandsAllowed(player.username)) {
            sendToPlayer(packet, player);
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public void sendToServer() {
      sendToServer(this.toPacket());
   }

   @SideOnly(Side.CLIENT)
   public static void sendToServer(Packet packet) {
      Minecraft.getMinecraft().getNetHandler().addToSendQueue(packet);
   }

   // $FF: synthetic method
   PacketCustom(Object var1, int var2, byte[] var3, PacketCustom var4) {
      this(var1, var2, var3);
   }

   private static class ClientPacketHandler extends PacketCustom.CustomPacketHandler {

      public ClientPacketHandler(String channel) {
         super(channel);
      }

      public Side getSide() {
         return Side.CLIENT;
      }

      public void handle(PacketCustom.ICustomPacketHandler handler, PacketCustom packet, Player player) {
         ((PacketCustom.IClientPacketHandler)handler).handlePacket(packet, Minecraft.getMinecraft().getNetHandler(), Minecraft.getMinecraft());
      }
   }

   private abstract static class CustomPacketHandler implements IPacketHandler {

      HashMap handlermap = new HashMap();


      public CustomPacketHandler(String channel) {
         NetworkRegistry.instance().registerChannel(this, channel, this.getSide());
      }

      public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
         PacketCustom packetCustom = PacketCustom.assembler.assemble(PacketCustom.carrier250, packet);
         if(packetCustom != null) {
            PacketCustom.ICustomPacketHandler handler = (PacketCustom.ICustomPacketHandler)this.handlermap.get(Integer.valueOf(packetCustom.getType()));
            if(handler != null) {
               this.handle(handler, packetCustom, player);
            }

         }
      }

      public void registerRange(int firstID, int lastID, PacketCustom.ICustomPacketHandler handler) {
         for(int i = firstID; i <= lastID; ++i) {
            this.handlermap.put(Integer.valueOf(i), handler);
         }

      }

      public abstract Side getSide();

      public abstract void handle(PacketCustom.ICustomPacketHandler var1, PacketCustom var2, Player var3);
   }

   public interface IServerPacketHandler extends PacketCustom.ICustomPacketHandler {

      void handlePacket(PacketCustom var1, NetServerHandler var2, EntityPlayerMP var3);
   }

   public static final class CustomTinyPacketHandler implements ITinyPacketHandler {

      private PacketCustom.ClientTinyPacketHandler clientDelegate;
      private PacketCustom.ServerTinyPacketHandler serverDelegate;


      public void handle(NetHandler handler, Packet131MapData packet) {
         PacketCustom packetCustom = PacketCustom.assembler.assemble(PacketCustom.carrier131, packet);
         if(packetCustom != null) {
            if(handler instanceof NetServerHandler) {
               this.serverDelegate.handle(packetCustom, handler);
            } else {
               this.clientDelegate.handle(packetCustom, handler);
            }

         }
      }

      private void registerSidedHandler(PacketCustom.ICustomPacketHandler handler) {
         if(handler instanceof PacketCustom.IClientPacketHandler) {
            if(this.clientDelegate != null) {
               throw new IllegalStateException("Client handler already registered");
            }

            this.clientDelegate = new PacketCustom.ClientTinyPacketHandler((PacketCustom.IClientPacketHandler)handler);
         } else {
            if(!(handler instanceof PacketCustom.IServerPacketHandler)) {
               throw new IllegalStateException("Handler is not a client or server handler");
            }

            if(this.serverDelegate != null) {
               throw new IllegalStateException("Server handler already registered");
            }

            this.serverDelegate = new PacketCustom.ServerTinyPacketHandler((PacketCustom.IServerPacketHandler)handler);
         }

      }
   }

   public interface ICustomPacketHandler {
   }

   public interface IPacketCarrier {

      int readType(Packet var1);

      byte[] readData(Packet var1);

      Object readChannel(Packet var1);

      Packet write(Object var1, boolean var2, int var3, byte[] var4);

      boolean shortCapped();
   }

   public interface IClientPacketHandler extends PacketCustom.ICustomPacketHandler {

      void handlePacket(PacketCustom var1, NetClientHandler var2, Minecraft var3);
   }

   public static class Packet131Carrier implements PacketCustom.IPacketCarrier {

      public int readType(Packet packet) {
         return ((Packet131MapData)packet).uniqueID & 255;
      }

      public byte[] readData(Packet packet) {
         return ((Packet131MapData)packet).itemData;
      }

      public Object readChannel(Packet packet) {
         return Short.valueOf(((Packet131MapData)packet).itemID);
      }

      public Packet write(Object channel, boolean chunkDataPacket, int type, byte[] data) {
         NetworkModHandler nmh = FMLNetworkHandler.instance().findNetworkModHandler(channel);
         Packet131MapData payload = new Packet131MapData((short)nmh.getNetworkId(), (short)type, data);
         payload.isChunkDataPacket = chunkDataPacket;
         return payload;
      }

      public boolean shortCapped() {
         return true;
      }
   }

   private static class ServerTinyPacketHandler {

      PacketCustom.IServerPacketHandler serverHandler;


      public ServerTinyPacketHandler(PacketCustom.IServerPacketHandler handler) {
         this.serverHandler = handler;
      }

      public void handle(PacketCustom packetCustom, NetHandler handler) {
         this.serverHandler.handlePacket(packetCustom, (NetServerHandler)handler, ((NetServerHandler)handler).playerEntity);
      }
   }

   public static class PacketAssembler {

      public HashMap assemblerMap = new HashMap();


      public PacketCustom assemble(PacketCustom.IPacketCarrier carrier, Packet packet) {
         int type = carrier.readType(packet);
         if(type != 128) {
            return new PacketCustom(carrier.readChannel(packet), carrier.readType(packet), carrier.readData(packet), (PacketCustom)null);
         } else {
            byte[] data = carrier.readData(packet);
            int asmID = PacketCustom.readInt(data, 0);
            PacketCustom.PacketAssembler.AssemblyEntry e = (PacketCustom.PacketAssembler.AssemblyEntry)this.assemblerMap.get(Integer.valueOf(asmID));
            if(e == null) {
               e = new PacketCustom.PacketAssembler.AssemblyEntry(carrier.readChannel(packet), data[4] & 255, PacketCustom.readInt(data, 5));
               this.assemblerMap.put(Integer.valueOf(asmID), e);
               return null;
            } else {
               e.append(data, 4, data.length - 4);
               PacketCustom ret = e.finished();
               if(ret != null) {
                  this.assemblerMap.remove(Integer.valueOf(asmID));
               }

               return ret;
            }
         }
      }

      public class AssemblyEntry {

         Object channel;
         int type;
         int pos;
         byte[] data;


         public AssemblyEntry(Object channel, int type, int length) {
            this.channel = channel;
            this.type = type;
            this.data = new byte[length];
         }

         public void append(byte[] b, int off, int len) {
            System.arraycopy(b, off, this.data, this.pos, len);
            this.pos += len;
         }

         public PacketCustom finished() {
            return this.pos < this.data.length?null:new PacketCustom(this.channel, this.type, this.data, (PacketCustom)null);
         }
      }
   }

   private static class ServerPacketHandler extends PacketCustom.CustomPacketHandler {

      public ServerPacketHandler(String channel) {
         super(channel);
      }

      public Side getSide() {
         return Side.SERVER;
      }

      public void handle(PacketCustom.ICustomPacketHandler handler, PacketCustom packet, Player player) {
         ((PacketCustom.IServerPacketHandler)handler).handlePacket(packet, ((EntityPlayerMP)player).playerNetServerHandler, (EntityPlayerMP)player);
      }
   }

   public static class Packet250Carrier implements PacketCustom.IPacketCarrier {

      public int readType(Packet packet) {
         return ((Packet250CustomPayload)packet).data[0] & 255;
      }

      public byte[] readData(Packet packet) {
         byte[] data = ((Packet250CustomPayload)packet).data;
         return Arrays.copyOfRange(data, 1, data.length);
      }

      public Object readChannel(Packet packet) {
         return ((Packet250CustomPayload)packet).channel;
      }

      public Packet write(Object channel, boolean chunkDataPacket, int type, byte[] data) {
         byte[] pdata = new byte[data.length + 1];
         pdata[0] = (byte)type;
         System.arraycopy(data, 0, pdata, 1, data.length);
         Packet250CustomPayload payload = new Packet250CustomPayload();
         payload.channel = (String)channel;
         payload.isChunkDataPacket = chunkDataPacket;
         payload.data = pdata;
         payload.length = payload.data.length;
         return payload;
      }

      public boolean shortCapped() {
         return true;
      }
   }

   private static class ClientTinyPacketHandler {

      PacketCustom.IClientPacketHandler clientHandler;


      public ClientTinyPacketHandler(PacketCustom.IClientPacketHandler handler) {
         this.clientHandler = handler;
      }

      public void handle(PacketCustom packetCustom, NetHandler handler) {
         this.clientHandler.handlePacket(packetCustom, (NetClientHandler)handler, Minecraft.getMinecraft());
      }
   }
}

package codechicken.core;

import codechicken.core.CommonUtils;
import codechicken.core.NetworkClosedException;
import codechicken.core.internal.ClientTickHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.net.Socket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.MemoryConnection;
import net.minecraft.network.TcpConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class ClientUtils extends CommonUtils {

   private static Minecraft mc() {
      return Minecraft.getMinecraft();
   }

   public static World getWorld() {
      return mc().theWorld;
   }

   public static EntityPlayer getPlayer(String playername) {
      return playername != mc().thePlayer.username && playername != null?null:mc().thePlayer;
   }

   public static boolean isClient(World world) {
      return world instanceof WorldClient;
   }

   public static boolean inWorld() {
      return mc().getNetHandler() != null;
   }

   public static void openSMPGui(int windowId, GuiScreen gui) {
      mc().displayGuiScreen(gui);
      if(windowId != 0) {
         mc().thePlayer.openContainer.windowId = windowId;
      }

   }

   public static float getRenderFrame() {
      return mc().timer.renderPartialTicks;
   }

   public static double getRenderTime() {
      return (double)((float)ClientTickHandler.renderTime + getRenderFrame());
   }

   public static String getServerIP() {
      try {
         INetworkManager e = mc().getNetHandler().netManager;
         if(e instanceof MemoryConnection) {
            return "memory";
         } else {
            Socket socket = ((TcpConnection)e).getSocket();
            if(socket == null) {
               throw new NetworkClosedException();
            } else {
               return socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
            }
         }
      } catch (Exception var2) {
         throw new RuntimeException(var2);
      }
   }

   public static boolean isLocal() {
      return getServerIP().equals("memory");
   }

   @SideOnly(Side.CLIENT)
   public static String getWorldSaveName(String worldName) {
      return !isLocal()?null:MinecraftServer.getServer().getFolderName();
   }
}

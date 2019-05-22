package codechicken.core;

import codechicken.core.CommonUtils;
import codechicken.core.IGuiPacketSender;
import codechicken.core.packet.PacketCustom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.command.CommandHandler;
import net.minecraft.command.ICommand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.server.MinecraftServer;

public class ServerUtils extends CommonUtils {

   public static MinecraftServer mc() {
      return MinecraftServer.getServer();
   }

   public static EntityPlayerMP getPlayer(String playername) {
      return mc().getConfigurationManager().getPlayerForUsername(playername);
   }

   public static ArrayList getAllPlayers() {
      return new ArrayList(mc().getConfigurationManager().playerEntityList);
   }

   public static ArrayList getPlayersInDimension(int dimension) {
      ArrayList allplayers = getAllPlayers();
      Iterator iterator = allplayers.iterator();

      while(iterator.hasNext()) {
         if(((EntityPlayer)iterator.next()).dimension != dimension) {
            iterator.remove();
         }
      }

      return allplayers;
   }

   public static double getBlockReachDistance(EntityPlayerMP entityplayer) {
      return entityplayer.theItemInWorldManager.getBlockReachDistance();
   }

   public static void sendChatToOps(String message) {
      List lines = formatMessage(message);
      Iterator var3 = lines.iterator();

      while(var3.hasNext()) {
         String s = (String)var3.next();
         PacketCustom.sendToOps(new Packet3Chat(s));
      }

   }

   public static void sendChatToAll(String message) {
      List lines = formatMessage(message);
      Iterator var3 = lines.iterator();

      while(var3.hasNext()) {
         String s = (String)var3.next();
         PacketCustom.sendToClients(new Packet3Chat(s));
      }

   }

   public static void sendChatTo(EntityPlayerMP player, String message) {
      List lines = formatMessage(message);
      Iterator var4 = lines.iterator();

      while(var4.hasNext()) {
         String s = (String)var4.next();
         PacketCustom.sendToPlayer(new Packet3Chat(s), player);
      }

   }

   public static void openSMPContainer(EntityPlayerMP player, Container container, IGuiPacketSender packetSender) {
      player.incrementWindowID();
      player.closeInventory();
      packetSender.sendPacket(player, player.currentWindowId);
      player.openContainer = container;
      player.openContainer.windowId = player.currentWindowId;
      player.openContainer.addCraftingToCrafters(player);
   }

   public static boolean isPlayerOP(String username) {
      return mc().getConfigurationManager().areCommandsAllowed(username);
   }

   public static boolean isPlayerOwner(String username) {
      return mc().isSinglePlayer() && mc().getServerOwner().equalsIgnoreCase(username);
   }

   public static void registerCommand(ICommand command) {
      ((CommandHandler)mc().getCommandManager()).registerCommand(command);
   }
}

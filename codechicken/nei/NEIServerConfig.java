package codechicken.nei;

import codechicken.core.CommonUtils;
import codechicken.core.ServerUtils;
import codechicken.core.config.ConfigFile;
import codechicken.core.config.ConfigTag;
import codechicken.core.inventory.ItemKey;
import codechicken.core.packet.PacketCustom;
import codechicken.nei.PlayerSave;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class NEIServerConfig {

   public static HashMap playerSaves = new HashMap();
   public static HashMap bannedblocks = new HashMap();
   public static ConfigFile serverConfig = (new ConfigFile(new File(CommonUtils.getMinecraftDir(), "config/NEIServer.cfg"))).setComment("NEI Server Permissions \n Names are Comma (,) separated \n ALL, OP and NONE are special names");
   public static File worldSaveFile;
   public static File worldSaveDir;
   public static NBTTagCompound worldCompound;
   private static MinecraftServer server;


   public static void load(World world) {
      if(MinecraftServer.getServer() != server) {
         System.out.println("Loading NEI");
         server = MinecraftServer.getServer();
         initDefaults();
         loadBannedBlocks();
         loadSavedConfig(world);
      }
   }

   private static void loadSavedConfig(World world) {
      try {
         worldSaveDir = CommonUtils.getWorldBaseSaveLocation(world);
         worldSaveFile = new File(worldSaveDir, "NEI.dat");
         if(!worldSaveFile.getParentFile().exists()) {
            worldSaveFile.getParentFile().mkdirs();
         }

         if(!worldSaveFile.exists()) {
            worldSaveFile.createNewFile();
         }

         if(worldSaveFile.length() == 0L) {
            worldCompound = new NBTTagCompound();
         } else {
            DataInputStream e = new DataInputStream(new FileInputStream(worldSaveFile));
            worldCompound = (NBTTagCompound)NBTBase.readNamedTag(e);
            e.close();
         }

      } catch (Exception var2) {
         throw new RuntimeException(var2);
      }
   }

   private static void initDefaults() {
      serverConfig.setNewLineMode(1);
      serverConfig.getTag("permissions").useBraces();
      serverConfig.getTag("permissions").setComment("List of players who can use these features. :Eg. time=CodeChicken, Friend1");
      serverConfig.getTag("BannedBlocks").useBraces();
      serverConfig.getTag("BannedBlocks").setComment("List of players who can use these blocks. :Anyone not listed here will not have these blocks appear in their item panel.:format is {itemID}::{itemDamage}:Eg. 12::5=CodeChicken, Friend1");
      setDefaultFeature("time", new String[0]);
      setDefaultFeature("rain", new String[0]);
      setDefaultFeature("heal", new String[0]);
      setDefaultFeature("magnet", new String[0]);
      setDefaultFeature("creative", new String[0]);
      setDefaultFeature("enchant", new String[0]);
      setDefaultFeature("potion", new String[0]);
      setDefaultFeature("save-state", new String[0]);
      setDefaultFeature("item", new String[0]);
      setDefaultFeature("delete", new String[0]);
      setDefaultFeature("notify-item", new String[]{"CONSOLE, OP"});
      serverConfig.getTag("BannedBlocks." + Block.bedrock.blockID + ":0").setDefaultValue("NONE");
   }

   private static void setDefaultFeature(String featurename, String ... names) {
      if(names.length == 0) {
         names = new String[]{"OP"};
      }

      String list = "";

      for(int i = 0; i < names.length; ++i) {
         if(i >= 1) {
            list = list + ", ";
         }

         list = list + names[i];
      }

      serverConfig.getTag("permissions." + featurename).setDefaultValue(list);
   }

   public static boolean canPlayerUseFeature(String playername, String featurename) {
      return isPlayerInList(playername, getPlayerList("permissions." + featurename), true);
   }

   public static boolean isPlayerInList(String playername, HashSet list, boolean allowCards) {
      if(playername.equals("CONSOLE")) {
         return list.contains(playername);
      } else {
         playername = playername.toLowerCase();
         if(allowCards) {
            if(list.contains("ALL")) {
               return true;
            }

            if((ServerUtils.isPlayerOP(playername) || ServerUtils.isPlayerOwner(playername)) && list.contains("OP")) {
               return true;
            }
         }

         return list.contains(playername);
      }
   }

   public static boolean isPropertyDisabled(int dim, String name) {
      return getDimCompound(dim).getBoolean("disabled" + name);
   }

   private static NBTTagCompound getDimCompound(int dim) {
      if(!worldCompound.hasKey("dim" + dim)) {
         worldCompound.setCompoundTag("dim" + dim, new NBTTagCompound());
      }

      return worldCompound.getCompoundTag("dim" + dim);
   }

   public static void setPropertyDisabled(int dim, String name, boolean disable) {
      getDimCompound(dim).setBoolean("disabled" + name, disable);
      saveWorldCompound();
   }

   private static void saveWorldCompound() {
      try {
         DataOutputStream e = new DataOutputStream(new FileOutputStream(worldSaveFile));
         NBTBase.writeNamedTag(worldCompound, e);
         e.close();
      } catch (Exception var1) {
         throw new RuntimeException(var1);
      }
   }

   public static HashSet getPlayerList(String tag) {
      String[] list = serverConfig.getTag(tag).getValue("").replace(" ", "").split(",");
      return new HashSet(Arrays.asList(list));
   }

   public static void addPlayerToList(String playername, String tag) {
      HashSet list = getPlayerList(tag);
      if(!playername.equals("CONSOLE") && !playername.equals("ALL") && !playername.equals("OP")) {
         playername = playername.toLowerCase();
      }

      list.add(playername);
      savePlayerList(tag, list);
   }

   public static void remPlayerFromList(String playername, String tag) {
      HashSet list = getPlayerList(tag);
      if(!playername.equals("CONSOLE") && !playername.equals("ALL") && !playername.equals("OP")) {
         playername = playername.toLowerCase();
      }

      list.remove(playername);
      savePlayerList(tag, list);
   }

   private static void savePlayerList(String tag, Collection list) {
      StringBuilder sb = new StringBuilder();
      int i = 0;

      for(Iterator iterator = list.iterator(); iterator.hasNext(); ++i) {
         if(i != 0) {
            sb.append(", ");
         }

         sb.append((String)iterator.next());
      }

      serverConfig.getTag(tag).setValue(sb.toString());
   }

   private static void loadBannedBlocks() {
      ConfigTag banTag = serverConfig.getTag("BannedBlocks");
      Iterator var2 = banTag.childTagMap().entrySet().iterator();

      while(var2.hasNext()) {
         Entry entry = (Entry)var2.next();
         String ident = (String)entry.getKey();
         String[] num = ident.split(":");
         ItemKey hash = num.length == 1?new ItemKey(Integer.parseInt(num[0]), -1):new ItemKey(Integer.parseInt(num[0]), Integer.parseInt(num[1]));
         bannedblocks.put(hash, getPlayerList(((ConfigTag)entry.getValue()).qualifiedname));
      }

   }

   public static PlayerSave forPlayer(String username) {
      return (PlayerSave)playerSaves.get(username);
   }

   public static void loadPlayer(EntityPlayer player) {
      System.out.println("Loading Player: " + player.username);
      playerSaves.put(player.username, new PlayerSave(player.username, new File(worldSaveDir, "NEI/players")));
   }

   public static void unloadPlayer(EntityPlayer player) {
      System.out.println("Unloading Player: " + player.username);
      PlayerSave playerSave = (PlayerSave)playerSaves.remove(player.username);
      if(playerSave != null) {
         playerSave.save();
      }

   }

   public static boolean authenticatePacket(EntityPlayerMP sender, PacketCustom packet) {
      switch(packet.getType()) {
      case 1:
      case 5:
         return canPlayerUseFeature(sender.username, "item");
      case 2:
      case 3:
      case 10:
      case 11:
      case 12:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
      default:
         return true;
      case 4:
         return canPlayerUseFeature(sender.username, "delete");
      case 6:
         return canPlayerUseFeature(sender.username, "magnet");
      case 7:
         return canPlayerUseFeature(sender.username, "time");
      case 8:
         return canPlayerUseFeature(sender.username, "heal");
      case 9:
         return canPlayerUseFeature(sender.username, "rain");
      case 13:
      case 14:
      case 23:
         return canPlayerUseFeature(sender.username, "creative");
      case 21:
      case 22:
         return canPlayerUseFeature(sender.username, "enchant");
      case 24:
         return canPlayerUseFeature(sender.username, "potion");
      }
   }
}

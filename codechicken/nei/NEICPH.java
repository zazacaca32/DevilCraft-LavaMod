package codechicken.nei;

import codechicken.core.ClientUtils;
import codechicken.core.inventory.InventoryUtils;
import codechicken.core.inventory.ItemKey;
import codechicken.core.packet.PacketCustom;
import codechicken.core.packet.PacketCustom.IClientPacketHandler;
import codechicken.nei.AllowedPropertyMap;
import codechicken.nei.ClientHandler;
import codechicken.nei.ContainerCreativeInv;
import codechicken.nei.ExtendedCreativeInv;
import codechicken.nei.GuiEnchantmentModifier;
import codechicken.nei.GuiExtendedCreativeInv;
import codechicken.nei.GuiPotionCreator;
import codechicken.nei.InterActionMap;
import codechicken.nei.LayoutManager;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.PlayerSave;
import codechicken.nei.forge.GuiContainerManager;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.NetClientHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class NEICPH implements IClientPacketHandler {

   public static final String channel = "NEI";


   public void handlePacket(PacketCustom packet, NetClientHandler nethandler, Minecraft mc) {
      switch(packet.getType()) {
      case 1:
         this.handleSMPCheck(packet.readUnsignedByte(), packet.readString(), mc.theWorld);
      case 2:
      case 3:
      case 4:
      case 5:
      case 8:
      case 9:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
      case 22:
      default:
         break;
      case 6:
         NEIClientConfig.setMagnetMode(packet.readBoolean());
         break;
      case 7:
         NEIClientConfig.setCreativeMode(packet.readUnsignedByte());
         break;
      case 10:
         this.handlePermissableActions(packet);
         break;
      case 11:
         this.handleBannedBlocks(packet);
         break;
      case 12:
         this.handleDisabledProperties(packet);
         break;
      case 13:
         ClientHandler.instance().addSMPMagneticItem(packet.readInt(), mc.theWorld);
         break;
      case 21:
         ClientUtils.openSMPGui(packet.readUnsignedByte(), new GuiEnchantmentModifier(mc.thePlayer.inventory, mc.theWorld, 0, 0, 0));
         break;
      case 23:
         if(packet.readBoolean()) {
            ClientUtils.openSMPGui(packet.readUnsignedByte(), new GuiExtendedCreativeInv(new ContainerCreativeInv(mc.thePlayer, new ExtendedCreativeInv((PlayerSave)null, Side.CLIENT))));
         } else {
            mc.displayGuiScreen(new GuiInventory(mc.thePlayer));
         }
         break;
      case 24:
         ClientUtils.openSMPGui(packet.readUnsignedByte(), new GuiPotionCreator(mc.thePlayer.inventory));
      }

   }

   private void handleDisabledProperties(PacketCustom packet) {
      NEIClientConfig.resetDisabledProperties();
      int num = packet.readUnsignedByte();

      for(int i = 0; i < num; ++i) {
         NEIClientConfig.setPropertyDisabled(packet.readUnsignedByte());
      }

   }

   private void handleBannedBlocks(PacketCustom packet) {
      int num = packet.readInt();
      ArrayList items = new ArrayList(num);

      for(int i = 0; i < num; ++i) {
         items.add(new ItemKey(packet.readUnsignedShort(), packet.readUnsignedShort()));
      }

      NEIClientConfig.setBannedBlocks(items);
      if(NEIClientUtils.getGuiContainer() != null) {
         LayoutManager.instance().refresh(NEIClientUtils.getGuiContainer());
      }

   }

   private void handlePermissableActions(PacketCustom packet) {
      NEIClientConfig.resetPermissableActions();
      int num = packet.readUnsignedByte();

      for(int i = 0; i < num; ++i) {
         NEIClientConfig.addPermissableAction(InterActionMap.values()[packet.readUnsignedByte()]);
      }

   }

   private void handleSMPCheck(int serverprotocol, String worldName, World world) {
      if(serverprotocol > 5) {
         NEIClientUtils.addChatMessage("NEI version mismatch: Outdated Client");
      } else if(serverprotocol < 5) {
         NEIClientUtils.addChatMessage("NEI version mismatch: Outdated Server");
      } else {
         try {
            String nce;
            if(ClientUtils.isLocal()) {
               nce = "local";
               worldName = ClientUtils.getWorldSaveName(worldName);
            } else {
               nce = "remote/" + ClientUtils.getServerIP().replace(':', '~');
            }

            NEIClientConfig.loadWorld(nce + '/' + worldName);
            NEIClientConfig.setHasSMPCounterPart(true);
            sendRequestLoginInfo();
         } catch (Exception var5) {
            var5.printStackTrace();
         }
      }

   }

   public static void sendSpawnItem(ItemStack spawnstack, boolean infinite, boolean doSpawn) {
      PacketCustom packet = new PacketCustom("NEI", 1);
      packet.writeBoolean(infinite);
      packet.writeBoolean(doSpawn);
      List name = GuiContainerManager.itemDisplayNameMultiline(spawnstack, (GuiContainer)null, false);
      packet.writeByte(name.size());
      Iterator var6 = name.iterator();

      while(var6.hasNext()) {
         String s = (String)var6.next();
         packet.writeString(s);
      }

      packet.writeItemStack(spawnstack);
      packet.writeInt(spawnstack.stackSize);
      packet.sendToServer();
   }

   public static void sendDeleteAllItems() {
      PacketCustom packet = new PacketCustom("NEI", 4);
      packet.sendToServer();
   }

   public static void sendStateLoad(ItemStack[] state) {
      sendDeleteAllItems();

      for(int packet = 0; packet < state.length; ++packet) {
         ItemStack item = state[packet];
         if(item != null) {
            sendSetSlot(packet, item, false);
         }
      }

      PacketCustom var3 = new PacketCustom("NEI", 11);
      var3.sendToServer();
   }

   public static void sendSetSlot(int slot, ItemStack stack, boolean container) {
      PacketCustom packet = new PacketCustom("NEI", 5);
      packet.writeBoolean(container);
      packet.writeShort(slot);
      packet.writeItemStack(stack);
      packet.sendToServer();
   }

   private static void sendRequestLoginInfo() {
      PacketCustom packet = new PacketCustom("NEI", 10);
      packet.sendToServer();
   }

   public static void sendToggleMagnetMode() {
      PacketCustom packet = new PacketCustom("NEI", 6);
      packet.sendToServer();
   }

   public static void sendSetTime(int hour) {
      PacketCustom packet = new PacketCustom("NEI", 7);
      packet.writeByte(hour);
      packet.sendToServer();
   }

   public static void sendHeal() {
      PacketCustom packet = new PacketCustom("NEI", 8);
      packet.sendToServer();
   }

   public static void sendToggleRain() {
      PacketCustom packet = new PacketCustom("NEI", 9);
      packet.sendToServer();
   }

   public static void sendOpenEnchantmentWindow() {
      PacketCustom packet = new PacketCustom("NEI", 21);
      packet.sendToServer();
   }

   public static void sendModifyEnchantment(int enchID, int level, boolean add) {
      PacketCustom packet = new PacketCustom("NEI", 22);
      packet.writeByte(enchID);
      packet.writeByte(level);
      packet.writeBoolean(add);
      packet.sendToServer();
   }

   public static void sendSetPropertyDisabled(String name, boolean enable) {
      PacketCustom packet = new PacketCustom("NEI", 12);
      packet.writeByte(((Integer)AllowedPropertyMap.nameToIDMap.get(name)).intValue());
      packet.writeBoolean(enable);
      packet.sendToServer();
   }

   public static void sendCycleCreativeMode() {
      PacketCustom packet = new PacketCustom("NEI", 13);
      packet.sendToServer();
   }

   public static void sendCreativeInv(boolean open) {
      PacketCustom packet = new PacketCustom("NEI", 23);
      packet.writeBoolean(open);
      packet.sendToServer();
   }

   public static void sendCreativeScroll(int steps) {
      PacketCustom packet = new PacketCustom("NEI", 14);
      packet.writeInt(steps);
      packet.sendToServer();
   }

   public static void sendMobSpawnerID(int x, int y, int z, String mobtype) {
      PacketCustom packet = new PacketCustom("NEI", 15);
      packet.writeCoord(x, y, z);
      packet.writeString(mobtype);
      packet.sendToServer();
   }

   public static PacketCustom createContainerPacket() {
      return new PacketCustom("NEI", 20);
   }

   public static void sendOpenPotionWindow() {
      ItemStack[] potionStore = new ItemStack[9];
      InventoryUtils.readItemStacksFromTag(potionStore, NEIClientConfig.saveCompound.getCompoundTag("potionStore").getTagList("items"));
      PacketCustom packet = new PacketCustom("NEI", 24);

      for(int i = 0; i < potionStore.length; ++i) {
         packet.writeItemStack(potionStore[i]);
      }

      packet.sendToServer();
   }

   public static void sendDummySlotSet(int slotNumber, ItemStack stack) {
      PacketCustom packet = new PacketCustom("NEI", 25);
      packet.writeShort(slotNumber);
      packet.writeItemStack(stack, true);
      packet.sendToServer();
   }
}

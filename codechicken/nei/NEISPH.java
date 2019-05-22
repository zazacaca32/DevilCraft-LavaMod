package codechicken.nei;

import codechicken.core.CommonUtils;
import codechicken.core.IGuiPacketSender;
import codechicken.core.ServerUtils;
import codechicken.core.inventory.ContainerExtended;
import codechicken.core.inventory.ItemKey;
import codechicken.core.inventory.SlotDummy;
import codechicken.core.packet.PacketCustom;
import codechicken.core.packet.PacketCustom.IServerPacketHandler;
import codechicken.core.vec.BlockCoord;
import codechicken.nei.AllowedPropertyMap;
import codechicken.nei.ContainerCreativeInv;
import codechicken.nei.ContainerEnchantmentModifier;
import codechicken.nei.ContainerPotionCreator;
import codechicken.nei.ExtendedCreativeInv;
import codechicken.nei.InterActionMap;
import codechicken.nei.NEIServerConfig;
import codechicken.nei.NEIServerUtils;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetServerHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class NEISPH implements IServerPacketHandler {

   public static final String channel = "NEI";


   public void handlePacket(PacketCustom packet, NetServerHandler nethandler, EntityPlayerMP sender) {
      if(NEIServerConfig.authenticatePacket(sender, packet)) {
         switch(packet.getType()) {
         case 1:
            this.handleGiveItem(sender, packet);
         case 2:
         case 3:
         case 16:
         case 17:
         case 18:
         case 19:
         default:
            break;
         case 4:
            NEIServerUtils.deleteAllItems(sender);
            break;
         case 5:
            this.setInventorySlot(sender, packet);
            break;
         case 6:
            NEIServerUtils.toggleMagnetMode(sender);
            break;
         case 7:
            NEIServerUtils.setHourForward(sender.worldObj, packet.readUnsignedByte(), true);
            break;
         case 8:
            NEIServerUtils.healPlayer(sender);
            break;
         case 9:
            NEIServerUtils.toggleRaining(sender.worldObj, true);
            break;
         case 10:
            this.sendPermissableActionsTo(sender);
            this.sendBannedBlocksTo(sender);
            this.sendDisabledPropertiesTo(sender, sender.dimension);
            sendMagnetModeTo(sender, NEIServerUtils.isMagnetMode(sender));
            sendCreativeModeTo(sender, NEIServerUtils.getCreativeMode(sender));
            break;
         case 11:
            sender.sendContainerAndContentsToPlayer(sender.openContainer, sender.openContainer.getInventory());
            break;
         case 12:
            this.handlePropertyChange(sender, packet);
            break;
         case 13:
            NEIServerUtils.toggleCreativeMode(sender);
            break;
         case 14:
            NEIServerUtils.cycleCreativeInv(sender, packet.readInt());
            break;
         case 15:
            this.handleMobSpawnerID(sender.worldObj, packet.readCoord(), packet.readString());
            break;
         case 20:
            this.handleContainerPacket(sender, packet);
            break;
         case 21:
            this.openEnchantmentGui(sender);
            break;
         case 22:
            this.modifyEnchantment(sender, packet.readUnsignedByte(), packet.readUnsignedByte(), packet.readBoolean());
            break;
         case 23:
            this.processCreativeInv(sender, packet.readBoolean());
            break;
         case 24:
            this.openPotionGui(sender, packet);
            break;
         case 25:
            this.handleDummySlotSet(sender, packet);
         }

      }
   }

   private void handleDummySlotSet(EntityPlayerMP sender, PacketCustom packet) {
      short slotNumber = packet.readShort();
      ItemStack stack = packet.readItemStack(true);
      Slot slot = sender.openContainer.getSlot(slotNumber);
      if(slot instanceof SlotDummy) {
         slot.putStack(stack);
      }

   }

   private void handleContainerPacket(EntityPlayerMP sender, PacketCustom packet) {
      if(sender.openContainer instanceof ContainerExtended) {
         ((ContainerExtended)sender.openContainer).handleInputPacket(packet);
      }

   }

   private void handleMobSpawnerID(World world, BlockCoord coord, String mobtype) {
      TileEntity tile = world.getBlockTileEntity(coord.x, coord.y, coord.z);
      if(tile instanceof TileEntityMobSpawner) {
         ((TileEntityMobSpawner)tile).func_98049_a().setMobID(mobtype);
         tile.onInventoryChanged();
         world.markBlockForUpdate(coord.x, coord.y, coord.z);
      }

   }

   private void handlePropertyChange(EntityPlayerMP sender, PacketCustom packet) {
      int id = packet.readUnsignedByte();
      if(NEIServerConfig.canPlayerUseFeature(sender.username, (String)AllowedPropertyMap.idToFeatureClassMap.get(Integer.valueOf(id)))) {
         this.handlePropertyChange(sender.dimension, id, packet.readBoolean());
      }

   }

   private void processCreativeInv(EntityPlayerMP sender, boolean open) {
      if(open) {
         ServerUtils.openSMPContainer(sender, new ContainerCreativeInv(sender, new ExtendedCreativeInv(NEIServerConfig.forPlayer(sender.username), Side.SERVER)), new IGuiPacketSender() {
            public void sendPacket(EntityPlayerMP player, int windowId) {
               PacketCustom packet = new PacketCustom("NEI", 23);
               packet.writeBoolean(true);
               packet.writeByte(windowId);
               packet.sendToPlayer(player);
            }
         });
      } else {
         sender.closeInventory();
         PacketCustom packet = new PacketCustom("NEI", 23);
         packet.writeBoolean(false);
         packet.sendToPlayer(sender);
      }

   }

   private void handlePropertyChange(int dim, int propID, boolean disable) {
      NEIServerConfig.setPropertyDisabled(dim, (String)AllowedPropertyMap.idToNameMap.get(Integer.valueOf(propID)), disable);
      this.sendDisabledPropertiesTo((EntityPlayerMP)null, dim);
   }

   private void sendDisabledPropertiesTo(EntityPlayerMP player, int dim) {
      ArrayList disabledProperties = new ArrayList();
      Iterator sendplayer = AllowedPropertyMap.nameToIDMap.entrySet().iterator();

      while(sendplayer.hasNext()) {
         Entry packet = (Entry)sendplayer.next();
         if(NEIServerConfig.isPropertyDisabled(dim, (String)packet.getKey())) {
            disabledProperties.add((Integer)packet.getValue());
         }
      }

      PacketCustom packet1 = new PacketCustom("NEI", 12);
      packet1.writeByte(disabledProperties.size());
      Iterator var6 = disabledProperties.iterator();

      while(var6.hasNext()) {
         int sendplayer1 = ((Integer)var6.next()).intValue();
         packet1.writeByte(sendplayer1);
      }

      if(player != null) {
         packet1.sendToPlayer(player);
      } else {
         var6 = ServerUtils.getPlayersInDimension(dim).iterator();

         while(var6.hasNext()) {
            EntityPlayer sendplayer2 = (EntityPlayer)var6.next();
            packet1.sendToPlayer(sendplayer2);
         }
      }

   }

   private void handleGiveItem(EntityPlayerMP player, PacketCustom packet) {
      boolean infinite = packet.readBoolean();
      boolean doSpawn = packet.readBoolean();
      int num = packet.readUnsignedByte();
      LinkedList name = new LinkedList();

      for(int item = 0; item < num; ++item) {
         name.add(packet.readString());
      }

      ItemStack var8 = packet.readItemStack();
      if(var8 == null) {
         ServerUtils.sendChatTo(player, "§fNo such item.");
      } else {
         var8.stackSize = packet.readInt();
         NEIServerUtils.givePlayerItem(player, var8, infinite, name, doSpawn);
      }
   }

   private void setInventorySlot(EntityPlayerMP player, PacketCustom packet) {
      boolean container = packet.readBoolean();
      short slot = packet.readShort();
      ItemStack item = packet.readItemStack();
      if(NEIServerConfig.canPlayerUseFeature(player.username, item == null?"delete":"item")) {
         NEIServerUtils.setSlotContents(player, slot, item, container);
      }
   }

   private void modifyEnchantment(EntityPlayerMP player, int e, int lvl, boolean add) {
      ContainerEnchantmentModifier containerem = (ContainerEnchantmentModifier)player.openContainer;
      if(add) {
         containerem.addEnchantment(e, lvl);
      } else {
         containerem.removeEnchantment(e);
      }

   }

   private void openEnchantmentGui(EntityPlayerMP player) {
      ServerUtils.openSMPContainer(player, new ContainerEnchantmentModifier(player.inventory, player.worldObj, 0, 0, 0), new IGuiPacketSender() {
         public void sendPacket(EntityPlayerMP player, int windowId) {
            PacketCustom packet = new PacketCustom("NEI", 21);
            packet.writeByte(windowId);
            packet.sendToPlayer(player);
         }
      });
   }

   private void openPotionGui(EntityPlayerMP player, PacketCustom packet) {
      InventoryBasic b = new InventoryBasic("potionStore", true, 9);

      for(int i = 0; i < b.getSizeInventory(); ++i) {
         b.setInventorySlotContents(i, packet.readItemStack());
      }

      ServerUtils.openSMPContainer(player, new ContainerPotionCreator(player.inventory, b), new IGuiPacketSender() {
         public void sendPacket(EntityPlayerMP player, int windowId) {
            PacketCustom packet = new PacketCustom("NEI", 24);
            packet.writeByte(windowId);
            packet.sendToPlayer(player);
         }
      });
   }

   public static void sendMagnetModeTo(EntityPlayerMP player, boolean enable) {
      PacketCustom packet = new PacketCustom("NEI", 6);
      packet.writeBoolean(enable);
      packet.sendToPlayer(player);
   }

   public static void sendCreativeModeTo(EntityPlayerMP player, int mode) {
      PacketCustom packet = new PacketCustom("NEI", 7);
      packet.writeByte(mode);
      packet.sendToPlayer(player);
   }

   private void sendPermissableActionsTo(EntityPlayerMP player) {
      LinkedList actions = new LinkedList();
      InterActionMap[] var6;
      int var5 = (var6 = InterActionMap.values()).length;

      int i;
      for(i = 0; i < var5; ++i) {
         InterActionMap packet = var6[i];
         if(NEIServerConfig.canPlayerUseFeature(player.username, packet.getName())) {
            actions.add(Integer.valueOf(packet.ordinal()));
         }
      }

      PacketCustom var7 = new PacketCustom("NEI", 10);
      var7.writeByte(actions.size());
      Iterator var8 = actions.iterator();

      while(var8.hasNext()) {
         i = ((Integer)var8.next()).intValue();
         var7.writeByte(i);
      }

      var7.sendToPlayer(player);
   }

   private void sendBannedBlocksTo(EntityPlayerMP player) {
      ArrayList bannedblocks = new ArrayList();
      Iterator hash = NEIServerConfig.bannedblocks.entrySet().iterator();

      while(hash.hasNext()) {
         Entry packet = (Entry)hash.next();
         if(!NEIServerConfig.isPlayerInList(player.username, (HashSet)packet.getValue(), true)) {
            bannedblocks.add((ItemKey)packet.getKey());
         }
      }

      PacketCustom packet1 = new PacketCustom("NEI", 11);
      packet1.writeInt(bannedblocks.size());
      Iterator var5 = bannedblocks.iterator();

      while(var5.hasNext()) {
         ItemKey hash1 = (ItemKey)var5.next();
         packet1.writeShort(hash1.item.itemID);
         packet1.writeShort(hash1.item.getItemDamage());
      }

      packet1.sendToPlayer(player);
   }

   public static void sendHasServerSideTo(EntityPlayerMP player) {
      System.out.println("Sending serverside check to: " + player.username);
      PacketCustom packet = new PacketCustom("NEI", 1);
      packet.writeByte(5);
      packet.writeString(CommonUtils.getWorldName(player.worldObj));
      packet.sendToPlayer(player);
   }

   public static void sendAddMagneticItemTo(EntityPlayerMP player, EntityItem item) {
      PacketCustom packet = new PacketCustom("NEI", 13);
      packet.writeInt(item.entityId);
      packet.sendToPlayer(player);
   }
}

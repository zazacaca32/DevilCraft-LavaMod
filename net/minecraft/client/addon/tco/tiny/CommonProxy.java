package net.minecraft.client.addon.tco.tiny;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.lavablock.Container.ContainerArmorStand;
import net.minecraft.client.addon.lavablock.Tile.ArmorStandTile;
import net.minecraft.client.addon.tchestplate.donate.api.Manager;
import net.minecraft.client.addon.tco.tiny.ConfigHandler;
import net.minecraft.client.addon.tco.tiny.ContainerTiny;
import net.minecraft.client.addon.tco.tiny.ServerTickHandler;
import net.minecraft.client.addon.tco.tiny.TileEntityTiny;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockClearItem;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockColorer;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockCompressionMatter;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkEnergyControler;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkEnergyHarvest;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkMatterFabricator;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockGeneratorLava;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockInfo;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockLavaAnvil;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockMatterFabricator;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPatternModule;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPetDesk;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPressBow;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPressHammer;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPressOther;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockRepeaterArmor;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockSmurfChest;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTrade;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTrader;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderCoin;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderController;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderControllerMonitor;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTurning;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockUpgradeArmor;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockUpgradeUltima;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityH1LVL;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityPressSpear;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileGetExPult;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileGetExperience;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerBlockInfo;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerClearItem;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerColorer;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerCompressionMatter;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerDarkController;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerDarkEnergyHarvest;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerDarkMatterFabricator;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerGeneratorLava;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerGetExPult;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerGetExperience;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerH1LVL;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerLavaAnvil;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerMatterFabricator;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerPatternModule;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerPetDesk;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerPressBow;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerPressHammer;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerPressOther;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerPressSpear;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerRepeaterArmor;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerSmurfChest;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTrade;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTraderClose;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTraderCloseCoin;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTraderCloseInfo;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTraderController;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTraderControllerMonitor;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTraderOpen;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTraderOpenCoin;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTurning;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerUpgradeArmor;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerUpgradeUltima;
import net.minecraft.client.addon.tco.tiny.entity.EntityManTrader;
import net.minecraft.client.addon.tco.tiny.entity.EntityManTraderDedMoroz;
import net.minecraft.client.addon.tco.tiny.entity.containers.ContainerMerchant;
import net.minecraft.client.addon.tco.tiny.entity.containers.ContainerMerchantAdm;
import net.minecraft.client.addon.tco.tiny.entity.inventory.IMerchant;
import net.minecraft.client.texturepacks.ITexturePack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler {

   public void registerRenderInformation() {}

   public void preinit(FMLPreInitializationEvent event) {
      ConfigHandler.init(event.getSuggestedConfigurationFile());
      Tiny.config = new ConfigHandler();
   }

   public void init(FMLInitializationEvent event) {
      TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
   }

   public void wispFX(World worldObj, double posX, double posY, double posZ, float f, float g, float h, float i) {}

   public void wispFX2(World worldObj, double posX, double posY, double posZ, float size, int type, boolean shrink, float gravity) {}

   public void wispFX3(World worldObj, double posX, double posY, double posZ, double posX2, double posY2, double posZ2, float size, int type, boolean shrink, float gravity) {}

   public void register_renderers() {}

   public void initKeybind() {}

   public Minecraft getMinecraftInstance() {
      return null;
   }

   public void openGUI(EntityPlayer player, ItemStack stack) {}

   public ITexturePack getSelectedTexturePack() {
      return null;
   }

   public void stopTessellating() {}

   public void postinit(FMLPostInitializationEvent event) {}

   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      return null;
   }

   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      TileEntity tileEntity;
      Entity tileEntity1;
      switch(ID) {
      case 502:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof ArmorStandTile) {
            return new ContainerArmorStand((ArmorStandTile)tileEntity, player);
         }
         break;
      case 601:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityPressSpear) {
            return new ContainerPressSpear(player.inventory, (TileEntityPressSpear)tileEntity);
         }
         break;
      case 901:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockClearItem) {
            return new ContainerClearItem(player.inventory, (TileEntityBlockClearItem)tileEntity);
         }
         break;
      case 10000:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityTiny) {
            return new ContainerTiny((TileEntityTiny)tileEntity);
         }
      case 10001:
      case 10002:
      case 10003:
      case 10004:
      case 10005:
      case 10006:
      case 10007:
      case 10008:
      case 10009:
      default:
         break;
      case 10010:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockTurning) {
            return new ContainerTurning(player.inventory, (TileEntityBlockTurning)tileEntity);
         }
         break;
      case 10011:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockRepeaterArmor) {
            return new ContainerRepeaterArmor(player.inventory, (TileEntityBlockRepeaterArmor)tileEntity);
         }
         break;
      case 10012:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockMatterFabricator) {
            return new ContainerMatterFabricator(player.inventory, (TileEntityBlockMatterFabricator)tileEntity);
         }
         break;
      case 10013:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockCompressionMatter) {
            return new ContainerCompressionMatter(player.inventory, (TileEntityBlockCompressionMatter)tileEntity);
         }
         break;
      case 10014:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockTrade) {
            return new ContainerTrade(player.inventory, (IInventory)tileEntity);
         }
         break;
      case 10015:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockUpgradeArmor) {
            return new ContainerUpgradeArmor(player.inventory, (TileEntityBlockUpgradeArmor)tileEntity);
         }
         break;
      case 10016:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockDarkEnergyControler) {
            return new ContainerDarkController(player.inventory, (TileEntityBlockDarkEnergyControler)tileEntity);
         }
         break;
      case 10017:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockDarkEnergyHarvest) {
            return new ContainerDarkEnergyHarvest(player.inventory, (TileEntityBlockDarkEnergyHarvest)tileEntity);
         }
         break;
      case 10018:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockDarkMatterFabricator) {
            return new ContainerDarkMatterFabricator(player.inventory, (TileEntityBlockDarkMatterFabricator)tileEntity);
         }
         break;
      case 10019:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockPetDesk) {
            return new ContainerPetDesk(player.inventory, (TileEntityBlockPetDesk)tileEntity);
         }
         break;
      case 10020:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockTrader) {
            return new ContainerTraderOpen(player.inventory, (TileEntityBlockTrader)tileEntity);
         }
         break;
      case 10021:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockTrader) {
            return new ContainerTraderClose(player.inventory, (TileEntityBlockTrader)tileEntity);
         }
         break;
      case 10022:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockTraderController) {
            return new ContainerTraderController(player.inventory, (TileEntityBlockTraderController)tileEntity);
         }
         break;
      case 10023:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockLavaAnvil) {
            return new ContainerLavaAnvil(player.inventory, (TileEntityBlockLavaAnvil)tileEntity, Manager.getDonateUser(player));
         }
         break;
      case 10024:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockTraderControllerMonitor) {
            return new ContainerTraderControllerMonitor(player.inventory, (TileEntityBlockTraderControllerMonitor)tileEntity);
         }
         break;
      case 10025:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockTraderCoin) {
            return new ContainerTraderOpenCoin(player.inventory, (TileEntityBlockTraderCoin)tileEntity);
         }
         break;
      case 10026:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockTraderCoin) {
            return new ContainerTraderCloseCoin(player.inventory, (TileEntityBlockTraderCoin)tileEntity);
         }
         break;
      case 10027:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockPressHammer) {
            return new ContainerPressHammer(player.inventory, (TileEntityBlockPressHammer)tileEntity);
         }
         break;
      case 10028:
         tileEntity1 = world.getEntityByID(x);
         if(tileEntity1 instanceof EntityManTrader) {
            return new ContainerMerchant(player.inventory, (IMerchant)tileEntity1, world);
         }
         break;
      case 10029:
         tileEntity1 = world.getEntityByID(x);
         if(tileEntity1 instanceof EntityManTrader) {
            return new ContainerMerchantAdm(player.inventory, (IMerchant)tileEntity1, world);
         }
         break;
      case 10030:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockTrader) {
            return new ContainerTraderCloseInfo(player.inventory, (TileEntityBlockTrader)tileEntity);
         }
         break;
      case 10031:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockSmurfChest) {
            return new ContainerSmurfChest(player.inventory, (TileEntityBlockSmurfChest)tileEntity);
         }
         break;
      case 10032:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockUpgradeUltima) {
            return new ContainerUpgradeUltima(player.inventory, (TileEntityBlockUpgradeUltima)tileEntity);
         }
         break;
      case 10050:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockInfo) {
            return new ContainerBlockInfo(player.inventory, (TileEntityBlockInfo)tileEntity);
         }
         break;
      case 10075:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockColorer) {
            return new ContainerColorer(player.inventory, (TileEntityBlockColorer)tileEntity);
         }
         break;
      case 10099:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileGetExperience) {
            return new ContainerGetExperience((TileGetExperience)tileEntity, player);
         }
         break;
      case 10100:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileGetExPult) {
            return new ContainerGetExPult((TileGetExPult)tileEntity, player);
         }
         break;
      case 10101:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockPressOther) {
            return new ContainerPressOther(player.inventory, (TileEntityBlockPressOther)tileEntity);
         }
         break;
      case 10102:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockPressBow) {
            return new ContainerPressBow(player.inventory, (TileEntityBlockPressBow)tileEntity, player);
         }
         break;
      case 10996:
         tileEntity1 = world.getEntityByID(x);
         if(tileEntity1 instanceof EntityManTraderDedMoroz) {
            return new ContainerMerchant(player.inventory, (IMerchant)tileEntity1, world);
         }
         break;
      case 11001:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityH1LVL) {
            return new ContainerH1LVL(player.inventory, (TileEntityH1LVL)tileEntity);
         }
         break;
      case 11002:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockGeneratorLava) {
            return new ContainerGeneratorLava(player.inventory, (TileEntityBlockGeneratorLava)tileEntity);
         }
         break;
      case 228228:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockPatternModule) {
            return new ContainerPatternModule(player.inventory, (TileEntityBlockPatternModule)tileEntity);
         }
      }

      return null;
   }

   public World getClientWorld() {
      return null;
   }

   public void registerTileEntitySpecialRenderer() {}

   public int getRenderId(String string) {
      return 0;
   }
}

package net.minecraft.client.addon.tco.tiny;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.block.EnumMobType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.aaamodule.ModuleCore;
import net.minecraft.client.addon.tco.tiny.BlockTiny;
import net.minecraft.client.addon.tco.tiny.CommonProxy;
import net.minecraft.client.addon.tco.tiny.ConfigHandler;
import net.minecraft.client.addon.tco.tiny.PacketHandler;
import net.minecraft.client.addon.tco.tiny.TileEntityTiny;
import net.minecraft.client.addon.tco.tiny.Utils.DarkNode;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.BlockCable;
import net.minecraft.client.addon.tco.tiny.blocks.BlockClearItem;
import net.minecraft.client.addon.tco.tiny.blocks.BlockColorer;
import net.minecraft.client.addon.tco.tiny.blocks.BlockCompressionMatter;
import net.minecraft.client.addon.tco.tiny.blocks.BlockDarkEnergyControler;
import net.minecraft.client.addon.tco.tiny.blocks.BlockDarkEnergyHarvest;
import net.minecraft.client.addon.tco.tiny.blocks.BlockDarkMatterFabricator;
import net.minecraft.client.addon.tco.tiny.blocks.BlockDeadPlayer;
import net.minecraft.client.addon.tco.tiny.blocks.BlockGetExPult;
import net.minecraft.client.addon.tco.tiny.blocks.BlockGetExperience;
import net.minecraft.client.addon.tco.tiny.blocks.BlockLavaAnvil;
import net.minecraft.client.addon.tco.tiny.blocks.BlockMatterFabricator;
import net.minecraft.client.addon.tco.tiny.blocks.BlockMultiDecor;
import net.minecraft.client.addon.tco.tiny.blocks.BlockPatternModule;
import net.minecraft.client.addon.tco.tiny.blocks.BlockPetDesk;
import net.minecraft.client.addon.tco.tiny.blocks.BlockPresent;
import net.minecraft.client.addon.tco.tiny.blocks.BlockPresentPirat;
import net.minecraft.client.addon.tco.tiny.blocks.BlockPressHammer;
import net.minecraft.client.addon.tco.tiny.blocks.BlockPressSpear;
import net.minecraft.client.addon.tco.tiny.blocks.BlockPressureRandom;
import net.minecraft.client.addon.tco.tiny.blocks.BlockRepeaterArmor;
import net.minecraft.client.addon.tco.tiny.blocks.BlockSmurfChest;
import net.minecraft.client.addon.tco.tiny.blocks.BlockTrade;
import net.minecraft.client.addon.tco.tiny.blocks.BlockTrader;
import net.minecraft.client.addon.tco.tiny.blocks.BlockTraderCoin;
import net.minecraft.client.addon.tco.tiny.blocks.BlockTraderController;
import net.minecraft.client.addon.tco.tiny.blocks.BlockTraderControllerMonitor;
import net.minecraft.client.addon.tco.tiny.blocks.BlockTurning;
import net.minecraft.client.addon.tco.tiny.blocks.BlockUpgradeArmor;
import net.minecraft.client.addon.tco.tiny.blocks.BlockUpgradeUltima;
import net.minecraft.client.addon.tco.tiny.blocks.Item.ItemBlockTraderControllerMonitor;
import net.minecraft.client.addon.tco.tiny.blocks.Item.ItemDecorBlock;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockClearItem;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockColorer;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockCompressionMatter;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkEnergyControler;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkEnergyHarvest;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkMatterFabricator;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDeadPlayer;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDecor;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockLavaAnvil;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockMatterFabricator;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPatternModule;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPetDesk;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPresentNewYear;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPresentPirat;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPressHammer;
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
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityCable;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityPressSpear;
import net.minecraft.client.addon.tco.tiny.entities.EntityParta;
import net.minecraft.client.addon.tco.tiny.entity.EntityMan;
import net.minecraft.client.addon.tco.tiny.entity.EntityManTrader;
import net.minecraft.client.addon.tco.tiny.entity.EntityManTraderDedMoroz;
import net.minecraft.client.addon.tco.tiny.event.EventHandler;
import net.minecraft.client.addon.tco.tiny.items.BagPirat;
import net.minecraft.client.addon.tco.tiny.items.BagSanta;
import net.minecraft.client.addon.tco.tiny.items.ConfigureTrader;
import net.minecraft.client.addon.tco.tiny.items.DarkMatter;
import net.minecraft.client.addon.tco.tiny.items.ItemMatterAcelerator;
import net.minecraft.client.addon.tco.tiny.items.Lavapad;
import net.minecraft.client.addon.tco.tiny.items.MatterGenerator;
import net.minecraft.client.addon.tco.tiny.items.Stak;
import net.minecraft.client.addon.tco.tiny.items.Trap1;
import net.minecraft.client.addon.tco.tiny.items.Trap10;
import net.minecraft.client.addon.tco.tiny.items.Trap11;
import net.minecraft.client.addon.tco.tiny.items.Trap2;
import net.minecraft.client.addon.tco.tiny.items.Trap3;
import net.minecraft.client.addon.tco.tiny.items.Trap4;
import net.minecraft.client.addon.tco.tiny.items.Trap5;
import net.minecraft.client.addon.tco.tiny.items.Trap6;
import net.minecraft.client.addon.tco.tiny.items.Trap7;
import net.minecraft.client.addon.tco.tiny.items.Trap8;
import net.minecraft.client.addon.tco.tiny.items.Trap9;
import net.minecraft.client.addon.tco.tiny.items.a1;
import net.minecraft.client.addon.tco.tiny.items.a2;
import net.minecraft.client.addon.tco.tiny.items.a3;
import net.minecraft.client.addon.tco.tiny.items.a4;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

@Mod(
   modid = "Te",
   name = "Te",
   version = "1.7.1",
   useMetadata = true,
   certificateFingerprint = "e43c2b0c74740c469df9c798fecd85541291695e81c34f3e0edd1a269f8da542"
)
@NetworkMod(
   clientSideRequired = true,
   serverSideRequired = false,
   channels = {"l", "ll"},
   packetHandler = PacketHandler.class
)
public class Tiny {

   private static Logger logger = Logger.getLogger("Tiny");
   @Instance("Te")
   public static Tiny instance;
   @SidedProxy(
      clientSide = "net.minecraft.client.addon.tco.tiny.ClientProxy",
      serverSide = "net.minecraft.client.addon.tco.tiny.CommonProxy"
   )
   public static CommonProxy proxy;
   public static ConfigHandler config;
   boolean haveRenderManager = false;
   public static Item BagSanta;
   public static Item BagPirat;
   public static Item MatterGenerator;
   public static Item darkMatter;
   public static Item itemMatterAcelerator;
   public static Item nuControlCard;
   public static Item lavapad;
   public static Item configureTrader;
   public static Item exoItem;
   public static Item YellowCapsule;
   public static Item a1;
   public static Item a2;
   public static Item a3;
   public static Item a4;
   public static Item a5;
   public static Item itemDelColor;
   public static Item trap1;
   public static Item trap2;
   public static Item trap3;
   public static Item trap4;
   public static Item trap5;
   public static Item trap6;
   public static Item trap7;
   public static Item trap8;
   public static Item trap9;
   public static Item trap10;
   public static Item trap11;
   public static Item stak;
   public static Item facetingDisc;
   public static Item facetingDiscEmerald;
   public static Item facetingDiscDiamond;
   public static Item facetingDiscIridium;
   public static Item itemHeart;
   public static int sum = 0;
   public ModuleCore sas;
   public static Block blockSmurfChest;
   public static Block pg;
   public static Block blockTiny;
   public static Block blockPresent;
   public static Block blockPresentPirat;
   public static Block blockDeadPlayer;
   public static Block blockTurning;
   public static Block blockRepeaterArmor;
   public static Block blockMatterFabricator;
   public static Block blockCompressionMatter;
   public static Block blockTrade;
   public static Block blockMultiDecor;
   public static Block car;
   public static Block blockUpgradeArmor;
   public static Block blockCable;
   public static Block blockDarkEnergyControler;
   public static Block blockDarkEnergyHarvest;
   public static Block blockDarkMatterFabricator;
   public static Block blockParta;
   public static Block blockDesk;
   public static Block BlockPressBow;
   public static Block BlockWeaponColorer;
   public static Block blockPetDesk;
   public static Block blockPressureRandom;
   public static Block blockTrader;
   public static Block blockTraderController;
   public static Block blockTraderControllerMonitor;
   public static Block blockLavaAnvil;
   public static Block blockTraderCoin;
   public static Block blockPressHammer;
   public static Block blockUpgradeUltima;
   public static Block BlockPressOther;
   public static Block BlockColorer;
   public static Block GetExPult = new BlockGetExPult(2704);
   public static Block GetExperience = new BlockGetExperience(2703);
   public static Block BlockInfo;
   public static Block l1;
   public static Block l3;
   public static Block l4;
   public static Block l5;
   public static Block l6;
   public static Block l7;
   public static Block l8;
   public static Block l9;
   public static Block l10;
   public static Block l11;
   public static Block l12;
   public static Block s;
   public static Block bitController;
   public static Block bitMiner;
   public static Block bitHasher;
   public static Block bitCable;
   public static Block ba;
   public static Block PressSpear;
   public static Block ClearItemBlock;


   @PreInit
   public void init(FMLPreInitializationEvent event) {
      logger.setParent(FMLLog.getLogger());
      ConfigHandler.init(event.getSuggestedConfigurationFile());
      config = new ConfigHandler();
      ConfigHandler.loadEntityMan();
   }

   public void logConsole(String text) {
      logger.log(Level.INFO, text);
   }

   public void logConsole(String text, Throwable e) {
      logger.log(Level.WARNING, text, e);
   }

   @Init
   public void load(FMLInitializationEvent event) {
      GameRegistry.registerTileEntity(TileEntityBlockSmurfChest.class, "TileEntityBlockSmurfChest");
      blockSmurfChest = (new BlockSmurfChest(2618)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockSmurfChest");
      GameRegistry.registerBlock(blockSmurfChest, "blockSmurfChest");
      LanguageRegistry.addName(blockSmurfChest, "Мешок Смурфика");
      GameRegistry.registerTileEntity(TileEntityBlockPatternModule.class, "TileEntityBlockPatternModule");
      GameRegistry.registerBlock(car = (new BlockPatternModule(2717)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("BlockPatternModule"), "BlockPatternModule");
      LanguageRegistry.addName(car, "Кодировщик модулей");
      this.sas = new ModuleCore();
      this.sas.loadModules(event);
      LanguageRegistry.addName(blockTiny = (new BlockTiny(2389)).setUnlocalizedName("lavashop").setLightValue(10.0F), "Магазин");
      GameRegistry.registerBlock(blockTiny, ItemBlock.class, "lavashop");
      GameRegistry.registerTileEntity(TileEntityTiny.class, "containerTiny");
      GameRegistry.registerTileEntity(TileEntityBlockPresentNewYear.class, "TileEntityBlockPresent");
      GameRegistry.registerBlock(blockPresent = (new BlockPresent(2390)).setCreativeTab(CreativeTabs.tabDecorations).setHardness(2.0F).setUnlocalizedName("BlockPresent"), "BlockPresent");
      LanguageRegistry.addName(blockPresent, "Подарок");
      GameRegistry.registerTileEntity(TileEntityBlockPresentPirat.class, "TileEntityBlockPresentPirat");
      GameRegistry.registerBlock(blockPresentPirat = (new BlockPresentPirat(2391)).setCreativeTab(CreativeTabs.tabDecorations).setHardness(2.0F).setUnlocalizedName("BlockPresentPirat"), "BlockPresentPirat");
      LanguageRegistry.addName(blockPresentPirat, "Сундук пирата");
      GameRegistry.registerTileEntity(TileEntityBlockDeadPlayer.class, "TileEntityBlockDeadPlayer");
      GameRegistry.registerBlock(blockDeadPlayer = (new BlockDeadPlayer(2392)).setUnlocalizedName("lavablockdeadplayer").setCreativeTab(CreativeTabs.tabDecorations).setLightValue(10.0F), "BlockDeadPlayer");
      LanguageRegistry.addName(blockDeadPlayer, "Надгробье");
      GameRegistry.registerTileEntity(TileEntityBlockTurning.class, "TileEntityBlockTurning");
      GameRegistry.registerBlock(blockTurning = (new BlockTurning(2393)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockTurning"), "BlockTurning");
      LanguageRegistry.addName(blockTurning, "Огранщик");
      GameRegistry.registerTileEntity(TileEntityBlockRepeaterArmor.class, "TileEntityBlockRepeaterArmor");
      GameRegistry.registerBlock(blockRepeaterArmor = (new BlockRepeaterArmor(2394)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockRepeaterArmor"), "BlockRepeaterArmor");
      LanguageRegistry.addName(blockRepeaterArmor, "Ремонтная станция");
      GameRegistry.registerTileEntity(TileEntityBlockMatterFabricator.class, "TileEntityBlockMatterFabricator");
      GameRegistry.registerBlock(blockMatterFabricator = (new BlockMatterFabricator(2395)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockMatterFabricator"), "BlockMatterFabricator");
      LanguageRegistry.addName(blockMatterFabricator, "Синтезатор Материи");
      GameRegistry.registerTileEntity(TileEntityBlockCompressionMatter.class, "TileEntityBlockCompressionMatter");
      GameRegistry.registerBlock(blockCompressionMatter = (new BlockCompressionMatter(2396)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockCompressionMatter"), "BlockCompressionMatter");
      LanguageRegistry.addName(blockCompressionMatter, "Компрессор Материи");
      GameRegistry.registerTileEntity(TileEntityBlockTrade.class, "TileEntityBlockTrade");
      GameRegistry.registerBlock(blockTrade = (new BlockTrade(2397)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockTTrade"), "BlockTrade");
      LanguageRegistry.addName(blockTrade, "Обменик");
      GameRegistry.registerTileEntity(TileEntityBlockDecor.class, "TileEntityBlockMultiDecor");
      GameRegistry.registerBlock(blockMultiDecor = (new BlockMultiDecor(2398)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockMultiDecor"), ItemDecorBlock.class, "BlockMultiDecor");
      LanguageRegistry.addName(new ItemStack(blockMultiDecor, 1, 0), "колонна низ");
      LanguageRegistry.addName(new ItemStack(blockMultiDecor, 1, 1), "колонна середина");
      LanguageRegistry.addName(new ItemStack(blockMultiDecor, 1, 2), "колонна верх");
      GameRegistry.registerTileEntity(TileEntityBlockUpgradeArmor.class, "TileEntityBlockUpgradeArmor");
      GameRegistry.registerBlock(blockUpgradeArmor = (new BlockUpgradeArmor(2399)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockUpgradeArmor"), "blockUpgradeArmor");
      LanguageRegistry.addName(blockUpgradeArmor, "Модификатор");
      LanguageRegistry.addName(MatterGenerator = (new MatterGenerator(2400, 64)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("MatterGenerator"), "Материальный генератор");
      GameRegistry.registerTileEntity(TileEntityCable.class, "TileEntityCable");
      GameRegistry.registerBlock(blockCable = (new BlockCable(2601, Material.ground)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockCable"), "blockCable");
      LanguageRegistry.addName(blockCable, "Кабель");
      GameRegistry.registerTileEntity(TileEntityBlockDarkEnergyControler.class, "TileEntityBlockDarkEnergyControler");
      GameRegistry.registerBlock(blockDarkEnergyControler = (new BlockDarkEnergyControler(2602)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockDarkEnergyControler"), "blockDarkEnergyControler");
      LanguageRegistry.addName(blockDarkEnergyControler, "Тёмный Контролер");
      GameRegistry.registerTileEntity(TileEntityBlockDarkEnergyHarvest.class, "TileEntityBlockDarkEnergyHarvest");
      GameRegistry.registerBlock(blockDarkEnergyHarvest = (new BlockDarkEnergyHarvest(2603)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockDarkEnergyHarvest"), "blockDarkEnergyHarvest");
      LanguageRegistry.addName(blockDarkEnergyHarvest, "Сборщик тёмной энергии 1ур");
      GameRegistry.registerTileEntity(TileEntityBlockDarkMatterFabricator.class, "TileEntityBlockDarkMatterFabricator");
      GameRegistry.registerBlock(blockDarkMatterFabricator = (new BlockDarkMatterFabricator(2604)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockDarkMatterFabricator"), "blockDarkMatterFabricator");
      LanguageRegistry.addName(blockDarkMatterFabricator, "Генератор тёмного элексира");
      GameRegistry.registerTileEntity(TileEntityBlockPetDesk.class, "TileEntityBlockPetDesk");
      GameRegistry.registerBlock(blockPetDesk = (new BlockPetDesk(2612)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockPetDesk"), "blockPetDesk");
      LanguageRegistry.addName(blockPetDesk, "Стол питомца");
      GameRegistry.registerTileEntity(TileEntityBlockTrader.class, "TileEntityBlockTrader");
      GameRegistry.registerBlock(blockTrader = (new BlockTrader(2613)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockTrader"), "blockTrader");
      LanguageRegistry.addName(blockTrader, "Торговый совершенный аппарат");
      GameRegistry.registerTileEntity(TileEntityBlockTraderController.class, "TileEntityBlockTraderController");
      GameRegistry.registerBlock(blockTraderController = (new BlockTraderController(2614)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockTraderController"), "blockTraderController");
      LanguageRegistry.addName(blockTraderController, "Контроллер торгового аппарата");
      EntityRegistry.registerGlobalEntityID(EntityParta.class, "entityParta", EntityRegistry.findGlobalUniqueEntityId());
      EntityRegistry.registerModEntity(EntityParta.class, "entityParta", 0, this, 20, 20, true);
      LanguageRegistry.instance().addStringLocalization("entity.entityParta.name", "en_US", "EentityParta");
      Utils.addDarkEnergyNode(new int[]{2601, 2602, 2603, 2604});
      NetworkRegistry.instance().registerGuiHandler(this, proxy);
      EntityRegistry.registerGlobalEntityID(EntityManTraderDedMoroz.class, "Man_LavaDedMoroz", EntityRegistry.findGlobalUniqueEntityId(), 5592415, 1131666);
      EntityRegistry.registerModEntity(EntityManTraderDedMoroz.class, "Man_LavaDedMoroz", 2, this, 80, 100, false);
      LanguageRegistry.instance().addStringLocalization("entity.Man_LavaDedMoroz.name", "en_US", "Дед Мороз");
      EntityRegistry.registerGlobalEntityID(EntityManTrader.class, "Man_LavaTrader", EntityRegistry.findGlobalUniqueEntityId(), 5592415, 1131666);
      EntityRegistry.registerModEntity(EntityManTrader.class, "Man_LavaTrader", 1, this, 80, 100, false);
      LanguageRegistry.instance().addStringLocalization("entity.Man_LavaTrader.name", "en_US", "Бомж из катакомб");
      EntityRegistry.registerModEntity(EntityMan.class, "Man_Lava", 121, this, 80, 100, false);
      LanguageRegistry.instance().addStringLocalization("entity.Tiny.Man_Lava.name", "ЛаваЕнтити");
      registerEntityEgg(EntityMan.class, 16776959, 1280, 121);
      LanguageRegistry.addName(BagSanta = (new BagSanta(20719, 64)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("BagSanta"), "Мешок Санты");
      BagPirat = (new BagPirat(20720, 64, 4)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("bagPirat");
      LanguageRegistry.addName(new ItemStack(BagPirat, 1, 0), "Сундук пирата");
      LanguageRegistry.addName(new ItemStack(BagPirat, 1, 1), "Букварик");
      LanguageRegistry.addName(new ItemStack(BagPirat, 1, 2), "Мешок санты");
      LanguageRegistry.addName(new ItemStack(BagPirat, 1, 3), "Основа питомца");
      darkMatter = (new DarkMatter(20721, 64, 7)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("itemMatter");
      LanguageRegistry.addName(new ItemStack(darkMatter, 1, 0), "Черный элексир");
      LanguageRegistry.addName(new ItemStack(darkMatter, 1, 1), "Шар темного элексира");
      LanguageRegistry.addName(new ItemStack(darkMatter, 1, 2), "Сфера темного элексира");
      LanguageRegistry.addName(new ItemStack(darkMatter, 1, 3), "Синяя сфера");
      LanguageRegistry.addName(new ItemStack(darkMatter, 1, 4), "Красная сфера");
      LanguageRegistry.addName(new ItemStack(darkMatter, 1, 5), "Ручка для сферы");
      LanguageRegistry.addName(new ItemStack(darkMatter, 1, 6), "Медалька");
      itemMatterAcelerator = (new ItemMatterAcelerator(20722, 1, 2)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("itemMatterAcelerator");
      LanguageRegistry.addName(new ItemStack(itemMatterAcelerator, 1, 0), "Ускоритель тёмной энергии");
      LanguageRegistry.addName(new ItemStack(itemMatterAcelerator, 1, 1), "Ускоритель Ремонтной станции");
      lavapad = (new Lavapad(20724, 1, 1)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("itemLavapad");
      LanguageRegistry.addName(new ItemStack(lavapad, 1, 0), "Лава пад");
      GameRegistry.registerBlock(blockPressureRandom = (new BlockPressureRandom(2605, "stone", Material.cloth, EnumMobType.players)).setStepSound(Block.soundWoodFootstep).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockPressureRandom"), "blockPressureRandom");
      LanguageRegistry.addName(blockPressureRandom, "Плита Случаиного телепорта");
      configureTrader = (new ConfigureTrader(20725, 1, 1)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("itemConfigureTrader");
      LanguageRegistry.addName(new ItemStack(configureTrader, 1, 0), "Настройщик пр. торгового аппарата");
      GameRegistry.registerTileEntity(TileEntityBlockLavaAnvil.class, "TileEntityBlockLavaAnvil");
      GameRegistry.registerBlock(blockLavaAnvil = (new BlockLavaAnvil(2606)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockLavaAnvil"), "blockLavaAnvil");
      LanguageRegistry.addName(blockLavaAnvil, "Лава наковальня");
      GameRegistry.registerTileEntity(TileEntityBlockTraderControllerMonitor.class, "TileEntityBlockTraderControllerMonitor");
      GameRegistry.registerBlock(blockTraderControllerMonitor = (new BlockTraderControllerMonitor(2615)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockTraderControllerMonitor"), ItemBlockTraderControllerMonitor.class, "blockTraderControllerMonitor");
      LanguageRegistry.addName(blockTraderControllerMonitor, "Монитор торгового контроллера");
      GameRegistry.registerTileEntity(TileEntityBlockTraderCoin.class, "TileEntityBlockTraderCoin");
      GameRegistry.registerBlock(blockTraderCoin = (new BlockTraderCoin(2616)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockTraderCoin"), "blockTraderCoin");
      LanguageRegistry.addName(blockTraderCoin, "Торговый совершенный аппарат коин");
      GameRegistry.registerTileEntity(TileEntityBlockPressHammer.class, "TileEntityBlockPressHammer");
      GameRegistry.registerBlock(blockPressHammer = (new BlockPressHammer(2617)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockPressHammer"), "blockPressHammer");
      LanguageRegistry.addName(blockPressHammer, "Пресс ЛаваМолотов");
      a1 = (new a1(20905)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("itemsExoItemFFFXSDSADXX");
      LanguageRegistry.addName(a1, "Зелёная сфера");
      a2 = (new a2(20906)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("itemsExoItemFFFXXXSDSXXX");
      LanguageRegistry.addName(a2, "Ускоритель создания лава пластин");
      a3 = (new a3(20907)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("itemsAFafSADXX");
      LanguageRegistry.addName(a3, "Ультима пластина");
      a4 = (new a4(20908)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("itemsSDSD");
      LanguageRegistry.addName(a4, "Продвинутая ультима пластина");
      stak = (new Stak(20891)).setUnlocalizedName("stak");
      LanguageRegistry.addName(stak, "ItemStack");
      trap1 = (new Trap1(6902)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("it1531524211asf");
      LanguageRegistry.addName(trap1, "Свиток демонического копья");
      trap2 = (new Trap2(6903)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("it1531asf3");
      LanguageRegistry.addName(trap2, "Свиток сумрачного копья");
      trap3 = (new Trap3(6904)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("it23yhjh23j");
      LanguageRegistry.addName(trap3, "Свиток герой копья");
      trap4 = (new Trap4(6905)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("it23yhjh23123123j");
      LanguageRegistry.addName(trap4, "Свиток лава лука");
      trap5 = (new Trap5(6909)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("it23gfdgdfgyhjh23123123j");
      LanguageRegistry.addName(trap5, "Свиток РБ лука");
      trap6 = (new Trap6(6910)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("it23yhjdfgdfgh23123123j");
      LanguageRegistry.addName(trap6, "Свиток лава ультима лука");
      trap7 = (new Trap7(6911)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("it23yh123j");
      LanguageRegistry.addName(trap7, "Свиток сюрикен-шота");
      trap8 = (new Trap8(6912)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("it23ysssasdh123j");
      LanguageRegistry.addName(trap8, "Свиток ангел посоха");
      trap9 = (new Trap9(6913)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("Xyiloebanoelava");
      LanguageRegistry.addName(trap9, "Свиток лава арбалета");
      trap10 = (new Trap10(6914)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("Xyiloebanoelavarb");
      LanguageRegistry.addName(trap10, "Свиток лава рб арбалета");
      trap11 = (new Trap11(6915)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("Xyiloebanoelavarb123123");
      LanguageRegistry.addName(trap11, "Свиток ведьма посоха");
      GameRegistry.registerTileEntity(TileEntityBlockUpgradeUltima.class, "TileEntityBlockUpgradeUltima");
      GameRegistry.registerBlock(blockUpgradeUltima = (new BlockUpgradeUltima(2700)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("blockUpgradeUltima"), "blockUpgradeUltima");
      LanguageRegistry.addName(blockUpgradeUltima, "Модификатор оружия");
      GameRegistry.registerTileEntity(TileEntityPressSpear.class, "TileEntityPressSpear");
      GameRegistry.registerBlock(PressSpear = (new BlockPressSpear(2714)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("PressSpear"), "PressSpear");
      LanguageRegistry.addName(PressSpear, "Пресс Копий");
      GameRegistry.registerTileEntity(TileEntityBlockClearItem.class, "TileEntityBlockClearItem");
      GameRegistry.registerBlock(ClearItemBlock = (new BlockClearItem(2715)).setHardness(2.0F).setCreativeTab(LavaChestPlate.tabUnder).setUnlocalizedName("BlockClearItem"), "BlockClearItem");
      LanguageRegistry.addName(ClearItemBlock, "Манипулятор частиц");
      ArrayList lore = new ArrayList();
      ItemStack ad = new ItemStack(2472, 1, 5);
      ItemStack cm = new ItemStack(2472, 1, 12);
      ItemStack pl = new ItemStack(2472, 1, 8);
      GameRegistry.addRecipe(new ItemStack(blockTurning, 1), new Object[]{"i#i", "#p#", "i#i", Character.valueOf('p'), ad, Character.valueOf('i'), cm, Character.valueOf('#'), pl});
      ad = new ItemStack(2472, 1, 5);
      cm = new ItemStack(2472, 1, 11);
      pl = new ItemStack(2472, 1, 10);
      ItemStack plm = new ItemStack(2472, 1, 7);
      GameRegistry.addRecipe(new ItemStack(blockRepeaterArmor, 1), new Object[]{"i#i", "KpK", "i#i", Character.valueOf('p'), ad, Character.valueOf('i'), cm, Character.valueOf('#'), pl, Character.valueOf('K'), plm});
      ad = new ItemStack(2472, 1, 12);
      cm = new ItemStack(2471, 1, 1);
      pl = new ItemStack(2469, 1, 1);
      GameRegistry.addRecipe(new ItemStack(blockMatterFabricator, 1), new Object[]{"i#i", "#p#", "i#i", Character.valueOf('p'), pl, Character.valueOf('i'), ad, Character.valueOf('#'), cm});
      ad = new ItemStack(2472, 1, 12);
      pl = new ItemStack(2467, 1, 0);
      GameRegistry.addRecipe(new ItemStack(blockCompressionMatter, 1), new Object[]{"###", "#p#", "###", Character.valueOf('p'), pl, Character.valueOf('#'), ad});
      ad = new ItemStack(2472, 1, 12);
      cm = new ItemStack(2471, 1, 1);
      pl = new ItemStack(2472, 1, 6);
      GameRegistry.addRecipe(new ItemStack(blockUpgradeArmor, 1), new Object[]{"i#i", "#p#", "i#i", Character.valueOf('p'), pl, Character.valueOf('i'), ad, Character.valueOf('#'), cm});
      ad = new ItemStack(2472, 1, 7);
      cm = new ItemStack(388, 1, 0);
      pl = new ItemStack(2472, 1, 6);
      GameRegistry.addRecipe(new ItemStack(blockDarkEnergyHarvest, 1), new Object[]{"###", "iii", "ipi", Character.valueOf('p'), pl, Character.valueOf('i'), ad, Character.valueOf('#'), cm});
      ad = new ItemStack(2471, 1, 1);
      cm = new ItemStack(2472, 1, 4);
      pl = new ItemStack(2472, 1, 5);
      GameRegistry.addRecipe(new ItemStack(blockDarkEnergyControler, 1), new Object[]{"###", "#i#", "ppp", Character.valueOf('p'), pl, Character.valueOf('i'), ad, Character.valueOf('#'), cm});
      ad = new ItemStack(20, 1, 0);
      cm = new ItemStack(331, 1, 0);
      pl = new ItemStack(2472, 1, 12);
      GameRegistry.addRecipe(new ItemStack(blockCable, 1), new Object[]{"ppp", "#i#", "ppp", Character.valueOf('p'), pl, Character.valueOf('i'), ad, Character.valueOf('#'), cm});
      ad = new ItemStack(blockMatterFabricator, 1, 0);
      cm = new ItemStack(2472, 1, 4);
      pl = new ItemStack(2472, 1, 5);
      GameRegistry.addRecipe(new ItemStack(blockDarkMatterFabricator, 1), new Object[]{"#p#", "pip", "#p#", Character.valueOf('p'), pl, Character.valueOf('i'), ad, Character.valueOf('#'), cm});
      pl = new ItemStack(darkMatter, 1, 0);
      GameRegistry.addRecipe(new ItemStack(darkMatter, 1, 1), new Object[]{"ppp", "ppp", "ppp", Character.valueOf('p'), pl});
      pl = new ItemStack(darkMatter, 1, 1);
      GameRegistry.addRecipe(new ItemStack(darkMatter, 1, 2), new Object[]{"ppp", "ppp", "ppp", Character.valueOf('p'), pl});
      ad = new ItemStack(2472, 1, 4);
      cm = new ItemStack(2467, 1, 1);
      pl = new ItemStack(4362, 1, 3);
      GameRegistry.addRecipe(new ItemStack(itemMatterAcelerator, 1), new Object[]{"iii", "p#p", "iii", Character.valueOf('p'), pl, Character.valueOf('i'), ad, Character.valueOf('#'), cm});
      cm = new ItemStack(2472, 1, 12);
      pl = new ItemStack(darkMatter, 1, 2);
      GameRegistry.addRecipe(new ItemStack(darkMatter, 1, 3), new Object[]{"###", "#p#", "###", Character.valueOf('p'), pl, Character.valueOf('#'), cm});
      cm = new ItemStack(2472, 1, 7);
      pl = new ItemStack(darkMatter, 1, 2);
      GameRegistry.addRecipe(new ItemStack(darkMatter, 1, 4), new Object[]{"###", "#p#", "###", Character.valueOf('p'), pl, Character.valueOf('#'), cm});
      ad = new ItemStack(2467, 1, 1);
      cm = new ItemStack(2472, 1, 4);
      GameRegistry.addRecipe(new ItemStack(darkMatter, 1, 5), new Object[]{"###", "#i#", "#i#", Character.valueOf('i'), ad, Character.valueOf('#'), cm});
      ad = new ItemStack(2472, 1, 0);
      new ItemStack(2467, 1, 1);
      pl = new ItemStack(darkMatter, 1, 2);
      GameRegistry.addRecipe(new ItemStack(3304, 1, 0), new Object[]{"iii", "ipi", "iii", Character.valueOf('p'), ad, Character.valueOf('i'), pl});
      ad = new ItemStack(3304, 1, 0);
      cm = new ItemStack(20862, 1, 0);
      pl = new ItemStack(darkMatter, 1, 2);
      GameRegistry.addRecipe(new ItemStack(3306, 1, 0), new Object[]{"iOi", "OpO", "iOi", Character.valueOf('p'), ad, Character.valueOf('i'), pl, Character.valueOf('O'), cm});
      ad = new ItemStack(3306, 1, 0);
      cm = new ItemStack(ModuleCore.itemCap1[0], 1, 0);
      pl = new ItemStack(darkMatter, 1, 2);
      GameRegistry.addRecipe(new ItemStack(3308, 1, 0), new Object[]{"iOi", "OpO", "iOi", Character.valueOf('p'), ad, Character.valueOf('i'), pl, Character.valueOf('O'), cm});
      ad = new ItemStack(3308, 1, 0);
      cm = new ItemStack(ModuleCore.itemCap1[0], 1, 0);
      pl = new ItemStack(darkMatter, 1, 2);
      GameRegistry.addRecipe(new ItemStack(3310, 1, 0), new Object[]{"iOi", "OpO", "iOi", Character.valueOf('p'), ad, Character.valueOf('i'), pl, Character.valueOf('O'), cm});
      ad = new ItemStack(3310, 1, 0);
      new ItemStack(ModuleCore.itemCap1[0], 1, 0);
      pl = new ItemStack(20862, 1, 0);
      GameRegistry.addRecipe(new ItemStack(3312, 1, 0), new Object[]{"iii", "ipi", "iii", Character.valueOf('p'), ad, Character.valueOf('i'), pl});
      ad = new ItemStack(3312, 1, 0);
      new ItemStack(ModuleCore.itemCap1[0], 1, 0);
      pl = new ItemStack(20862, 1, 0);
      GameRegistry.addRecipe(new ItemStack(3314, 1, 0), new Object[]{"iii", "ipi", "iii", Character.valueOf('p'), ad, Character.valueOf('i'), pl});
      ad = new ItemStack(2472, 1, 6);
      cm = new ItemStack(2472, 1, 9);
      pl = new ItemStack(2472, 1, 12);
      plm = new ItemStack(Block.blockIron);
      GameRegistry.addRecipe(new ItemStack(blockPetDesk, 1, 0), new Object[]{"###", "ipi", "&&&", Character.valueOf('p'), ad, Character.valueOf('i'), pl, Character.valueOf('#'), cm, Character.valueOf('&'), plm});
      ad = new ItemStack(ModuleCore.itemCap1[0], 1, 0);
      cm = new ItemStack(darkMatter, 1, 2);
      GameRegistry.addRecipe(new ItemStack(BagPirat, 1, 2), new Object[]{"iii", "ipi", "iii", Character.valueOf('p'), ad, Character.valueOf('i'), cm});
      ad = new ItemStack(3314, 1, 0);
      cm = new ItemStack(2484, 1, 4);
      pl = new ItemStack(20862, 1, 0);
      plm = new ItemStack(20971, 1, 0);
      GameRegistry.addRecipe(new ItemStack(3316, 1, 0), new Object[]{"iii", "lpl", " & ", Character.valueOf('p'), ad, Character.valueOf('i'), pl, Character.valueOf('l'), cm, Character.valueOf('&'), plm});
      ad = new ItemStack(3316, 1, 0);
      cm = new ItemStack(2484, 1, 1);
      pl = new ItemStack(20862, 1, 0);
      plm = new ItemStack(20972, 1, 0);
      GameRegistry.addRecipe(new ItemStack(3318, 1, 0), new Object[]{"iii", "lpl", " & ", Character.valueOf('p'), ad, Character.valueOf('i'), pl, Character.valueOf('l'), cm, Character.valueOf('&'), plm});
      ad = new ItemStack(3318, 1, 0);
      cm = new ItemStack(2484, 1, 4);
      pl = new ItemStack(20862, 1, 0);
      plm = new ItemStack(20973, 1, 0);
      GameRegistry.addRecipe(new ItemStack(3320, 1, 0), new Object[]{"iii", "lpl", " & ", Character.valueOf('p'), ad, Character.valueOf('i'), pl, Character.valueOf('l'), cm, Character.valueOf('&'), plm});
      ad = new ItemStack(3320, 1, 0);
      pl = new ItemStack(Item.diamond);
      GameRegistry.addRecipe(new ItemStack(3322, 1, 0), new Object[]{"iii", "ipi", "iii", Character.valueOf('p'), ad, Character.valueOf('i'), pl});
      ad = new ItemStack(77, 1, 0);
      pl = new ItemStack(901, 1, 12);
      cm = new ItemStack(2601, 1, 0);
      plm = new ItemStack(4362, 1, 21);
      ItemStack cm1 = new ItemStack(331, 1, 0);
      ItemStack plm1 = new ItemStack(348, 1, 0);
      GameRegistry.addRecipe(new ItemStack(lavapad), new Object[]{"pip", "jlj", "udu", Character.valueOf('p'), ad, Character.valueOf('i'), pl, Character.valueOf('j'), cm, Character.valueOf('l'), plm, Character.valueOf('u'), cm1, Character.valueOf('d'), plm1});
      ad = new ItemStack(901, 1, 14);
      pl = new ItemStack(4362, 1, 4);
      cm = new ItemStack(4362, 1, 1);
      lore.clear();
      lore.add("§a Может подключать к контроллеру до 254 аппаратов");
      ItemStack result = Utils.addLoretoItemStack(new ItemStack(configureTrader), lore);
      GameRegistry.addRecipe(result, new Object[]{"pip", "pjp", "plp", Character.valueOf('p'), ad, Character.valueOf('i'), pl, Character.valueOf('j'), cm});
      ad = new ItemStack(901, 1, 14);
      pl = new ItemStack(900, 1, 5);
      cm = new ItemStack(901, 1, 12);
      plm = new ItemStack(225, 1, 1);
      cm1 = new ItemStack(901, 1, 7);
      lore.clear();
      lore.add("§3Контроллер поддерживает до 254 аппаратов");
      result = Utils.addLoretoItemStack(new ItemStack(blockTrader), lore);
      GameRegistry.addRecipe(result, new Object[]{"pip", "pjp", "ulu", Character.valueOf('p'), ad, Character.valueOf('i'), pl, Character.valueOf('j'), cm, Character.valueOf('l'), plm, Character.valueOf('u'), cm1});
      ad = new ItemStack(901, 1, 14);
      pl = new ItemStack(900, 1, 5);
      cm = new ItemStack(901, 1, 12);
      plm = new ItemStack(225, 1, 1);
      cm1 = new ItemStack(901, 1, 7);
      plm1 = new ItemStack(2812, 1, 0);
      lore.clear();
      lore.add("§3Контроллер поддерживает до 254 аппаратов");
      result = Utils.addLoretoItemStack(new ItemStack(blockTraderCoin), lore);
      GameRegistry.addRecipe(result, new Object[]{"pip", "djp", "ulu", Character.valueOf('p'), ad, Character.valueOf('i'), pl, Character.valueOf('j'), cm, Character.valueOf('l'), plm, Character.valueOf('u'), cm1, Character.valueOf('d'), plm1});
      pl = new ItemStack(900, 1, 5);
      cm = new ItemStack(900, 1, 6);
      plm = new ItemStack(900, 1, 2);
      cm1 = new ItemStack(901, 1, 7);
      plm1 = new ItemStack(900, 1, 7);
      lore.clear();
      lore.add("§3Можно подключать до 254 Торговых аппаратов");
      result = Utils.addLoretoItemStack(new ItemStack(blockTraderController), lore);
      GameRegistry.addRecipe(result, new Object[]{"pip", "ljd", "uuu", Character.valueOf('p'), pl, Character.valueOf('j'), cm, Character.valueOf('l'), plm, Character.valueOf('u'), cm1, Character.valueOf('d'), plm1});
      ad = new ItemStack(901, 1, 14);
      pl = new ItemStack(901, 1, 4);
      cm1 = new ItemStack(265, 1, 0);
      plm1 = new ItemStack(20, 1, 0);
      lore.clear();
      GameRegistry.addRecipe(new ItemStack(blockTraderControllerMonitor), new Object[]{"udd", "pid", "udd", Character.valueOf('p'), ad, Character.valueOf('i'), pl, Character.valueOf('u'), cm1, Character.valueOf('d'), plm1});
      ad = new ItemStack(2467, 1, 0);
      pl = new ItemStack(darkMatter, 1, 4);
      cm1 = new ItemStack(2507, 1, 2);
      plm1 = new ItemStack(2472, 1, 10);
      lore.clear();
      GameRegistry.addRecipe(new ItemStack(blockPressHammer), new Object[]{"upu", "did", "upu", Character.valueOf('p'), ad, Character.valueOf('i'), pl, Character.valueOf('u'), cm1, Character.valueOf('d'), plm1});
      ad = new ItemStack(2467, 1, 0);
      pl = new ItemStack(darkMatter, 1, 4);
      cm1 = new ItemStack(35, 1, 14);
      plm1 = new ItemStack(2472, 1, 7);
      new ItemStack(Item.silk, 1, 0);
      lore.clear();
      GameRegistry.addRecipe(new ItemStack(blockUpgradeUltima), new Object[]{"upu", "did", "upu", Character.valueOf('p'), ad, Character.valueOf('i'), pl, Character.valueOf('u'), cm1, Character.valueOf('d'), plm1});
      new ItemStack(Item.glassBottle);
      new ItemStack(Item.diamond);
      lore.clear();
      proxy.init(event);
      proxy.registerTileEntitySpecialRenderer();
      MinecraftForge.EVENT_BUS.register(new EventHandler());
      MinecraftForge.EVENT_BUS.register(new DarkNode());
      boolean flag = false;
      String[] arr$ = Tiny.class.getProtectionDomain().getCodeSource().getLocation().getPath().split("/");
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         String s1 = arr$[i$];
         if("b6e773f6edfdd000b63af5d302b86f6f".equals(UUID(s1))) {
            flag = true;
         }
      }

      if(!flag && !Utils.isClient()) {
         ;
      }

   }

   @PostInit
   public void postInit(FMLPostInitializationEvent event) {}

   private static String UUID(String s) {
      try {
         MessageDigest var31 = MessageDigest.getInstance("MD5");
         var31.update(s.getBytes(), 0, s.length());
         BigInteger i = new BigInteger(1, var31.digest());
         return String.format("%1$032x", new Object[]{i});
      } catch (NoSuchAlgorithmException var3) {
         var3.printStackTrace();
         return null;
      }
   }

   public static void registerEntityEgg(Class entity, int primaryColor, int secondaryColor, int id) {
      EntityList.IDtoClassMapping.put(Integer.valueOf(id), entity);
      EntityList.entityEggs.put(Integer.valueOf(id), new EntityEggInfo(id, primaryColor, secondaryColor));
   }

   public void gentelman() throws Exception {
      try {
         Minecraft var21 = Minecraft.getMinecraft();
         if(var21.isSingleplayer()) {
            if(var21.isIntegratedServerRunning()) {
               ;
            }

            var21.shutdownMinecraftApplet();
         }

      } catch (Exception var2) {
         throw new RuntimeException();
      }
   }

}

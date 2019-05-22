package net.minecraft.client.addon.lavablock;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.client.addon.lavablock.Block.BlockArmorStand;
import net.minecraft.client.addon.lavablock.Block.BlockBedCarpet;
import net.minecraft.client.addon.lavablock.Block.BlockBomjsh;
import net.minecraft.client.addon.lavablock.Block.BlockCarpet;
import net.minecraft.client.addon.lavablock.Block.BlockClay;
import net.minecraft.client.addon.lavablock.Block.BlockColorGlass;
import net.minecraft.client.addon.lavablock.Block.BlockColorGlassPane;
import net.minecraft.client.addon.lavablock.Block.BlockColumnB;
import net.minecraft.client.addon.lavablock.Block.BlockColumnD;
import net.minecraft.client.addon.lavablock.Block.BlockColumnG;
import net.minecraft.client.addon.lavablock.Block.BlockColumnW;
import net.minecraft.client.addon.lavablock.Block.BlockDivan;
import net.minecraft.client.addon.lavablock.Block.BlockElka;
import net.minecraft.client.addon.lavablock.Block.BlockFlamBeau;
import net.minecraft.client.addon.lavablock.Block.BlockForged;
import net.minecraft.client.addon.lavablock.Block.BlockGate;
import net.minecraft.client.addon.lavablock.Block.BlockGatelock;
import net.minecraft.client.addon.lavablock.Block.BlockJacklamp;
import net.minecraft.client.addon.lavablock.Block.BlockLavaStairs;
import net.minecraft.client.addon.lavablock.Block.BlockLavaStep;
import net.minecraft.client.addon.lavablock.Block.BlockLightFloor;
import net.minecraft.client.addon.lavablock.Block.BlockLock;
import net.minecraft.client.addon.lavablock.Block.BlockPrizent;
import net.minecraft.client.addon.lavablock.Block.BlockQuartz;
import net.minecraft.client.addon.lavablock.Block.BlockStatuy;
import net.minecraft.client.addon.lavablock.Block.BlockThrone;
import net.minecraft.client.addon.lavablock.Block.BlockVenok;
import net.minecraft.client.addon.lavablock.Block.BlockLavaPortal;
import net.minecraft.client.addon.lavablock.Tile.ArmorStandTile;
import net.minecraft.client.addon.lavablock.Tile.BomjshTileBlock;
import net.minecraft.client.addon.lavablock.Tile.ColumnTileBlock;
import net.minecraft.client.addon.lavablock.Tile.DivanTileBlock;
import net.minecraft.client.addon.lavablock.Tile.ElkaTileBlock;
import net.minecraft.client.addon.lavablock.Tile.FlameBeauTile;
import net.minecraft.client.addon.lavablock.Tile.GateTileBlock;
import net.minecraft.client.addon.lavablock.Tile.GatelockTileBlock;
import net.minecraft.client.addon.lavablock.Tile.JacklampTileBlock;
import net.minecraft.client.addon.lavablock.Tile.LightFloorTileBlock;
import net.minecraft.client.addon.lavablock.Tile.LockTileBlock;
import net.minecraft.client.addon.lavablock.Tile.PrizentTileBlock;
import net.minecraft.client.addon.lavablock.Tile.StatuyTileBlock;
import net.minecraft.client.addon.lavablock.Tile.ThroneTileBlock;
import net.minecraft.client.addon.lavablock.Tile.VenokTileBlock;
import net.minecraft.client.addon.lavablock.Tile.TileLavaPortal;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

@Mod(
    modid = "LavaBlock",
    name = "Lava Block",
    version = "0.0.1",
    useMetadata = true,
    certificateFingerprint = "e43c2b0c74740c469df9c798fecd85541291695e81c34f3e0edd1a269f8da542"
)
@NetworkMod(
    clientSideRequired = true,
    serverSideRequired = false,
    versionBounds = "1.0.0"
)
public class ModBlocks
{
    @Instance("LavaBlock")
    public static ModBlocks instance;
    @SidedProxy(
        clientSide = "net.minecraft.client.addon.lavablock.ClientProxy",
        serverSide = "net.minecraft.client.addon.lavablock.CommonProxy"
    )
    public static CommonProxy proxy;
    public static Block СlayBlock;
    public static Block CarpetBlock;
    public static Item QuartzBlack;
    public static Block QuartzBlock;
    public static Block QuartzStairsBlock;
    public static Block LavaStepBlock;
    public static Block ForgedBlock;
    public static Block StatuyBlock;
    public static Block DivanBlock;
    public static Block GatelockBlock;
    public static Item GateItem;
    public static Block GateBlock;
    public static Block ThroneBlock;
    public static Block JacklampBlock;
    public static Block ColorGlass;
    public static Block GlassStairsBlock;
    public static Block whiteGlassStairsBlock;
    public static Block blackGlassStairsBlock;
    public static Block blueGlassStairsBlock;
    public static Block brownGlassStairsBlock;
    public static Block cyanGlassStairsBlock;
    public static Block grayGlassStairsBlock;
    public static Block greenGlassStairsBlock;
    public static Block blueGlassStairsBlock_;
    public static Block limeGlassStairsBlock;
    public static Block magentaGlassStairsBlock;
    public static Block orangeGlassStairsBlock;
    public static Block pinkGlassStairsBlock;
    public static Block purpleGlassStairsBlock;
    public static Block redGlassStairsBlock;
    public static Block silverGlassStairsBlock;
    public static Block yellowGlassStairsBlock;
    public static Block ElkaBlock;
    public static Item ItemElkaToy;
    public static Block PrizentBlock;
    public static Block VenokBlock;
    public static Block BedCarpetBlock;
    public static Block BomjshBlock;
    public static Item NewLavaCoin;
    public static Block ColorGlassPane;
    public static Block FlamBeauBlock;
    public static Block LightFloorBlock;
    public static Block LockBlock;
    public static Block BlockLavaPortal;
    public static Block ColumnBlockW;
    public static Block ColumnBlockB;
    public static Block ColumnBlockG;
    public static Block ColumnBlockD;
    public static Block ArmorStand;
    public static Item LavaCarbonItem;
    public static Block GetExperience;
    public static Block GetExPult;
    public static int render_id;

    @Init
    public void load(FMLInitializationEvent event)
    {
        instance = this;
        NetworkRegistry.instance().registerGuiHandler(this, proxy);
        GameRegistry.registerTileEntity(ArmorStandTile.class, "ArmorStandBlock");
        GameRegistry.registerBlock(ArmorStand, "ArmorStandBlock");
        LanguageRegistry.addName(new ItemStack(ArmorStand), "Стойка для брони");
        GameRegistry.registerTileEntity(TileLavaPortal.class, "BlockLavaPortal");
        GameRegistry.registerBlock(BlockLavaPortal, "BlockLavaPortal");
        LanguageRegistry.addName(new ItemStack(BlockLavaPortal), "Лава Портал");
        GameRegistry.addRecipe(new ItemStack(ArmorStand), new Object[] {" X ", " X ", " Y ", 'X', new ItemStack(LavaCarbonItem), 'Y', new ItemStack(Block.pressurePlateIron)});
        LanguageRegistry.addName(new ItemStack(LavaCarbonItem), "Карбоновый Стержень");
        GameRegistry.addRecipe(new ItemStack(LavaCarbonItem, 2, 0), new Object[] {" X ", " Y ", " X ", 'X', new ItemStack(20977, 1, 2), 'Y', new ItemStack(2467, 1, 0)});
        GameRegistry.registerTileEntity(FlameBeauTile.class, "FlamBeauBlock");
        GameRegistry.registerBlock(FlamBeauBlock, "FlamBeauBlock");
        LanguageRegistry.addName(new ItemStack(FlamBeauBlock), "Факел подземелья");
        GameRegistry.registerTileEntity(LightFloorTileBlock.class, "LightFloorBlock");
        GameRegistry.registerBlock(LightFloorBlock, "LightFloorBlock");
        LanguageRegistry.addName(new ItemStack(LightFloorBlock), "Светильник подземелья");
        GameRegistry.registerTileEntity(LockTileBlock.class, "LockBlock");
        GameRegistry.registerBlock(LockBlock, "LockBlock");
        LanguageRegistry.addName(new ItemStack(LockBlock), "Декоративный замок");
        GameRegistry.registerBlock(BedCarpetBlock, "bed_carpet");
        LanguageRegistry.addName(new ItemStack(BedCarpetBlock), "Бедрок коврик");
        GameRegistry.registerItem(NewLavaCoin, "newlavacoin");
        LanguageRegistry.addName(new ItemStack(NewLavaCoin, 1, 0), "Новогодний Лава Койн");
        LanguageRegistry.addName(new ItemStack(NewLavaCoin, 1, 1), "Новогодний Золотой Лава Койн");
        GameRegistry.registerBlock(СlayBlock, MultiBlock.class, "hardened_clay_stained");
        GameRegistry.addSmelting(Block.blockClay.blockID, new ItemStack(СlayBlock, 1, 0), 1.0F);
        GameRegistry.addRecipe(new ItemStack(СlayBlock, 8, 1), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(СlayBlock, 1, 0), 'Y', new ItemStack(Item.dyePowder, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(СlayBlock, 8, 2), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(СlayBlock, 1, 0), 'Y', new ItemStack(Item.dyePowder, 1, 4)});
        GameRegistry.addRecipe(new ItemStack(СlayBlock, 8, 3), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(СlayBlock, 1, 0), 'Y', new ItemStack(Item.dyePowder, 1, 3)});
        GameRegistry.addRecipe(new ItemStack(СlayBlock, 8, 4), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(СlayBlock, 1, 0), 'Y', new ItemStack(Item.dyePowder, 1, 6)});
        GameRegistry.addRecipe(new ItemStack(СlayBlock, 8, 5), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(СlayBlock, 1, 0), 'Y', new ItemStack(Item.dyePowder, 1, 8)});
        GameRegistry.addRecipe(new ItemStack(СlayBlock, 8, 6), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(СlayBlock, 1, 0), 'Y', new ItemStack(Item.dyePowder, 1, 2)});
        GameRegistry.addRecipe(new ItemStack(СlayBlock, 8, 7), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(СlayBlock, 1, 0), 'Y', new ItemStack(Item.dyePowder, 1, 12)});
        GameRegistry.addRecipe(new ItemStack(СlayBlock, 8, 8), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(СlayBlock, 1, 0), 'Y', new ItemStack(Item.dyePowder, 1, 10)});
        GameRegistry.addRecipe(new ItemStack(СlayBlock, 8, 9), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(СlayBlock, 1, 0), 'Y', new ItemStack(Item.dyePowder, 1, 13)});
        GameRegistry.addRecipe(new ItemStack(СlayBlock, 8, 10), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(СlayBlock, 1, 0), 'Y', new ItemStack(Item.dyePowder, 1, 14)});
        GameRegistry.addRecipe(new ItemStack(СlayBlock, 8, 11), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(СlayBlock, 1, 0), 'Y', new ItemStack(Item.dyePowder, 1, 9)});
        GameRegistry.addRecipe(new ItemStack(СlayBlock, 8, 12), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(СlayBlock, 1, 0), 'Y', new ItemStack(Item.dyePowder, 1, 5)});
        GameRegistry.addRecipe(new ItemStack(СlayBlock, 8, 13), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(СlayBlock, 1, 0), 'Y', new ItemStack(Item.dyePowder, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(СlayBlock, 8, 14), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(СlayBlock, 1, 0), 'Y', new ItemStack(Item.dyePowder, 1, 7)});
        GameRegistry.addRecipe(new ItemStack(СlayBlock, 8, 15), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(СlayBlock, 1, 0), 'Y', new ItemStack(Item.dyePowder, 1, 11)});
        LanguageRegistry.addName(new ItemStack(СlayBlock, 1, 0), "Обожжёная глина");
        LanguageRegistry.addName(new ItemStack(СlayBlock, 1, 1), "Чёрная обожжённая глина");
        LanguageRegistry.addName(new ItemStack(СlayBlock, 1, 2), "Синяя обожжённая глина");
        LanguageRegistry.addName(new ItemStack(СlayBlock, 1, 3), "Коричневая обожжённая глина");
        LanguageRegistry.addName(new ItemStack(СlayBlock, 1, 4), "Бирюзовая обожжённая глина");
        LanguageRegistry.addName(new ItemStack(СlayBlock, 1, 5), "Серая обожжённая глина");
        LanguageRegistry.addName(new ItemStack(СlayBlock, 1, 6), "Зелёная обожжённая глина");
        LanguageRegistry.addName(new ItemStack(СlayBlock, 1, 7), "Голубая обожжённая глина");
        LanguageRegistry.addName(new ItemStack(СlayBlock, 1, 8), "Лаймовая обожжённая глина");
        LanguageRegistry.addName(new ItemStack(СlayBlock, 1, 9), "Пурпурная обожжённая глина");
        LanguageRegistry.addName(new ItemStack(СlayBlock, 1, 10), "Оранжевая обожжённая глина");
        LanguageRegistry.addName(new ItemStack(СlayBlock, 1, 11), "Розовая обожжённая глина");
        LanguageRegistry.addName(new ItemStack(СlayBlock, 1, 12), "Фиолетовая обожжённая глина");
        LanguageRegistry.addName(new ItemStack(СlayBlock, 1, 13), "Красная обожжённая глина");
        LanguageRegistry.addName(new ItemStack(СlayBlock, 1, 14), "Светло-серая обожжённая глина");
        LanguageRegistry.addName(new ItemStack(СlayBlock, 1, 15), "Жёлтая обожжённая глина");
        GameRegistry.registerBlock(CarpetBlock, MultiBlock.class, "wool_colored");
        GameRegistry.addRecipe(new ItemStack(CarpetBlock, 3, 0), new Object[] {"XX ", "   ", "   ", 'X', new ItemStack(Block.cloth, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(CarpetBlock, 3, 1), new Object[] {"XX ", "   ", "   ", 'X', new ItemStack(Block.cloth, 1, 15)});
        GameRegistry.addRecipe(new ItemStack(CarpetBlock, 3, 2), new Object[] {"XX ", "   ", "   ", 'X', new ItemStack(Block.cloth, 1, 11)});
        GameRegistry.addRecipe(new ItemStack(CarpetBlock, 3, 3), new Object[] {"XX ", "   ", "   ", 'X', new ItemStack(Block.cloth, 1, 12)});
        GameRegistry.addRecipe(new ItemStack(CarpetBlock, 3, 4), new Object[] {"XX ", "   ", "   ", 'X', new ItemStack(Block.cloth, 1, 9)});
        GameRegistry.addRecipe(new ItemStack(CarpetBlock, 3, 5), new Object[] {"XX ", "   ", "   ", 'X', new ItemStack(Block.cloth, 1, 7)});
        GameRegistry.addRecipe(new ItemStack(CarpetBlock, 3, 6), new Object[] {"XX ", "   ", "   ", 'X', new ItemStack(Block.cloth, 1, 13)});
        GameRegistry.addRecipe(new ItemStack(CarpetBlock, 3, 7), new Object[] {"XX ", "   ", "   ", 'X', new ItemStack(Block.cloth, 1, 3)});
        GameRegistry.addRecipe(new ItemStack(CarpetBlock, 3, 8), new Object[] {"XX ", "   ", "   ", 'X', new ItemStack(Block.cloth, 1, 5)});
        GameRegistry.addRecipe(new ItemStack(CarpetBlock, 3, 9), new Object[] {"XX ", "   ", "   ", 'X', new ItemStack(Block.cloth, 1, 2)});
        GameRegistry.addRecipe(new ItemStack(CarpetBlock, 3, 10), new Object[] {"XX ", "   ", "   ", 'X', new ItemStack(Block.cloth, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(CarpetBlock, 3, 11), new Object[] {"XX ", "   ", "   ", 'X', new ItemStack(Block.cloth, 1, 6)});
        GameRegistry.addRecipe(new ItemStack(CarpetBlock, 3, 12), new Object[] {"XX ", "   ", "   ", 'X', new ItemStack(Block.cloth, 1, 10)});
        GameRegistry.addRecipe(new ItemStack(CarpetBlock, 3, 13), new Object[] {"XX ", "   ", "   ", 'X', new ItemStack(Block.cloth, 1, 14)});
        GameRegistry.addRecipe(new ItemStack(CarpetBlock, 3, 14), new Object[] {"XX ", "   ", "   ", 'X', new ItemStack(Block.cloth, 1, 8)});
        GameRegistry.addRecipe(new ItemStack(CarpetBlock, 3, 15), new Object[] {"XX ", "   ", "   ", 'X', new ItemStack(Block.cloth, 1, 4)});
        LanguageRegistry.addName(new ItemStack(CarpetBlock, 1, 0), "Ковёр");
        LanguageRegistry.addName(new ItemStack(CarpetBlock, 1, 1), "Чёрный ковёр");
        LanguageRegistry.addName(new ItemStack(CarpetBlock, 1, 2), "Синий ковёр");
        LanguageRegistry.addName(new ItemStack(CarpetBlock, 1, 3), "Коричневый ковёр");
        LanguageRegistry.addName(new ItemStack(CarpetBlock, 1, 4), "Бирюзовый ковёр");
        LanguageRegistry.addName(new ItemStack(CarpetBlock, 1, 5), "Серый ковёр");
        LanguageRegistry.addName(new ItemStack(CarpetBlock, 1, 6), "Зелёный ковёр");
        LanguageRegistry.addName(new ItemStack(CarpetBlock, 1, 7), "Светло-синий ковёр");
        LanguageRegistry.addName(new ItemStack(CarpetBlock, 1, 8), "Лаймовый ковёр");
        LanguageRegistry.addName(new ItemStack(CarpetBlock, 1, 9), "Пурпурный ковёр");
        LanguageRegistry.addName(new ItemStack(CarpetBlock, 1, 10), "Оранжевый ковёр");
        LanguageRegistry.addName(new ItemStack(CarpetBlock, 1, 11), "Розовый ковёр");
        LanguageRegistry.addName(new ItemStack(CarpetBlock, 1, 12), "Фиолетовый ковёр");
        LanguageRegistry.addName(new ItemStack(CarpetBlock, 1, 13), "Красный ковёр");
        LanguageRegistry.addName(new ItemStack(CarpetBlock, 1, 14), "Светло-серый ковёр");
        LanguageRegistry.addName(new ItemStack(CarpetBlock, 1, 15), "Жёлтый ковёр");
        GameRegistry.registerItem(QuartzBlack, "quartz_black");
        LanguageRegistry.addName(new ItemStack(QuartzBlack), "Чёрный Кварц");
        GameRegistry.addRecipe(new ItemStack(QuartzBlack), new Object[] {"XY ", "   ", "   ", 'X', new ItemStack(Item.netherQuartz), 'Y', new ItemStack(Item.coal)});
        GameRegistry.registerTileEntity(ColumnTileBlock.class, "column_block");
        GameRegistry.registerBlock(ColumnBlockW, MultiBlock.class, "column_blockW");
        GameRegistry.registerBlock(ColumnBlockB, MultiBlock.class, "column_blockB");
        GameRegistry.registerBlock(ColumnBlockG, MultiBlock.class, "column_blockG");
        GameRegistry.registerBlock(ColumnBlockD, MultiBlock.class, "column_blockD");
        LanguageRegistry.addName(new ItemStack(ColumnBlockW, 1, 0), "Колонна белая кварцевая 1");
        LanguageRegistry.addName(new ItemStack(ColumnBlockW, 1, 1), "Колонна белая кварцевая 2");
        LanguageRegistry.addName(new ItemStack(ColumnBlockW, 1, 2), "Колонна белая кварцевая 3");
        LanguageRegistry.addName(new ItemStack(ColumnBlockW, 1, 3), "Колонна белая кварцевая 4");
        LanguageRegistry.addName(new ItemStack(ColumnBlockB, 1, 0), "Колонна чёрная кварцевая 1");
        LanguageRegistry.addName(new ItemStack(ColumnBlockB, 1, 1), "Колонна чёрная кварцевая 2");
        LanguageRegistry.addName(new ItemStack(ColumnBlockB, 1, 2), "Колонна чёрная кварцевая 3");
        LanguageRegistry.addName(new ItemStack(ColumnBlockB, 1, 3), "Колонна чёрная кварцевая 4");
        LanguageRegistry.addName(new ItemStack(ColumnBlockG, 1, 0), "Колонна золотая 1");
        LanguageRegistry.addName(new ItemStack(ColumnBlockG, 1, 1), "Колонна золотая 2");
        LanguageRegistry.addName(new ItemStack(ColumnBlockG, 1, 2), "Колонна золотая 3");
        LanguageRegistry.addName(new ItemStack(ColumnBlockG, 1, 3), "Колонна золотая 4");
        LanguageRegistry.addName(new ItemStack(ColumnBlockD, 1, 0), "Колонна алмазная 1");
        LanguageRegistry.addName(new ItemStack(ColumnBlockD, 1, 1), "Колонна алмазная 2");
        LanguageRegistry.addName(new ItemStack(ColumnBlockD, 1, 2), "Колонна алмазная 3");
        LanguageRegistry.addName(new ItemStack(ColumnBlockD, 1, 3), "Колонна алмазная 4");
        GameRegistry.addRecipe(new ItemStack(ColumnBlockW, 1, 0), new Object[] {"XXX", " X ", "XXX", 'X', new ItemStack(Block.blockNetherQuartz, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(ColumnBlockW, 1, 1), new Object[] {"XXX", " Y ", "XXX", 'X', new ItemStack(Block.blockNetherQuartz, 1, 0), 'Y', new ItemStack(ColumnBlockW, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(ColumnBlockW, 1, 2), new Object[] {"XXX", " Y ", "XXX", 'X', new ItemStack(Block.blockNetherQuartz, 1, 0), 'Y', new ItemStack(ColumnBlockW, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(ColumnBlockW, 1, 3), new Object[] {"XXX", " Y ", "XXX", 'X', new ItemStack(Block.blockNetherQuartz, 1, 0), 'Y', new ItemStack(ColumnBlockW, 1, 2)});
        GameRegistry.addRecipe(new ItemStack(ColumnBlockB, 1, 0), new Object[] {"XXX", " X ", "XXX", 'X', new ItemStack(QuartzBlock, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(ColumnBlockB, 1, 1), new Object[] {"XXX", " Y ", "XXX", 'X', new ItemStack(QuartzBlock, 1, 0), 'Y', new ItemStack(ColumnBlockB, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(ColumnBlockB, 1, 2), new Object[] {"XXX", " Y ", "XXX", 'X', new ItemStack(QuartzBlock, 1, 0), 'Y', new ItemStack(ColumnBlockB, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(ColumnBlockB, 1, 3), new Object[] {"XXX", " Y ", "XXX", 'X', new ItemStack(QuartzBlock, 1, 0), 'Y', new ItemStack(ColumnBlockB, 1, 2)});
        GameRegistry.addRecipe(new ItemStack(ColumnBlockG, 1, 0), new Object[] {"XXX", " X ", "XXX", 'X', new ItemStack(Block.blockGold, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(ColumnBlockG, 1, 1), new Object[] {"XXX", " Y ", "XXX", 'X', new ItemStack(Block.blockGold, 1, 0), 'Y', new ItemStack(ColumnBlockG, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(ColumnBlockG, 1, 2), new Object[] {"XXX", " Y ", "XXX", 'X', new ItemStack(Block.blockGold, 1, 0), 'Y', new ItemStack(ColumnBlockG, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(ColumnBlockG, 1, 3), new Object[] {"XXX", " Y ", "XXX", 'X', new ItemStack(Block.blockGold, 1, 0), 'Y', new ItemStack(ColumnBlockG, 1, 2)});
        GameRegistry.addRecipe(new ItemStack(ColumnBlockD, 1, 0), new Object[] {"XXX", " X ", "XXX", 'X', new ItemStack(Block.blockDiamond, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(ColumnBlockD, 1, 1), new Object[] {"XXX", " Y ", "XXX", 'X', new ItemStack(Block.blockDiamond, 1, 0), 'Y', new ItemStack(ColumnBlockD, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(ColumnBlockD, 1, 2), new Object[] {"XXX", " Y ", "XXX", 'X', new ItemStack(Block.blockDiamond, 1, 0), 'Y', new ItemStack(ColumnBlockD, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(ColumnBlockD, 1, 3), new Object[] {"XXX", " Y ", "XXX", 'X', new ItemStack(Block.blockDiamond, 1, 0), 'Y', new ItemStack(ColumnBlockD, 1, 2)});
        GameRegistry.registerBlock(QuartzBlock, MultiBlock.class, "quartzblock_black");
        LanguageRegistry.addName(new ItemStack(QuartzBlock, 1, 0), "Чёрный Кварцевый блок");
        LanguageRegistry.addName(new ItemStack(QuartzBlock, 1, 1), "Чёрный Резной кварцевый блок");
        LanguageRegistry.addName(new ItemStack(QuartzBlock, 1, 2), "Чёрная Кварцевая колонна");
        GameRegistry.addRecipe(new ItemStack(QuartzBlock, 1, 0), new Object[] {"XX ", "XX ", "   ", 'X', new ItemStack(QuartzBlack)});
        GameRegistry.addRecipe(new ItemStack(QuartzBlock, 1, 1), new Object[] {"X  ", "X  ", "   ", 'X', new ItemStack(LavaStepBlock)});
        GameRegistry.addRecipe(new ItemStack(QuartzBlock, 2, 2), new Object[] {"X  ", "X  ", "   ", 'X', new ItemStack(QuartzBlock, 1, 0)});
        GameRegistry.registerBlock(QuartzStairsBlock, "stairsQuartzBlack");
        LanguageRegistry.addName(new ItemStack(QuartzStairsBlock), "Чёрные Кварцевые ступеньки");
        GameRegistry.addRecipe(new ItemStack(QuartzStairsBlock, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(QuartzBlock, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(QuartzStairsBlock, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(QuartzBlock, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(QuartzStairsBlock, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(QuartzBlock, 1, 2)});
        GameRegistry.registerBlock(LavaStepBlock, "stepQuartzBlack");
        LanguageRegistry.addName(new ItemStack(LavaStepBlock, 1, 0), "Чёрная Кварцевая плита");
        GameRegistry.addRecipe(new ItemStack(LavaStepBlock, 6), new Object[] {"XXX", "   ", "   ", 'X', new ItemStack(QuartzBlock, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(LavaStepBlock, 6), new Object[] {"XXX", "   ", "   ", 'X', new ItemStack(QuartzBlock, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(LavaStepBlock, 6), new Object[] {"XXX", "   ", "   ", 'X', new ItemStack(QuartzBlock, 1, 2)});
        GameRegistry.registerBlock(ForgedBlock, MultiBlock.class, "forged_block");
        LanguageRegistry.addName(new ItemStack(ForgedBlock, 1, 0), "Кованый блок");
        LanguageRegistry.addName(new ItemStack(ForgedBlock, 1, 1), "Закалённый Кованый блок");
        LanguageRegistry.addName(new ItemStack(ForgedBlock, 1, 2), "Чёрный Кованый блок");
        GameRegistry.addRecipe(new ItemStack(ForgedBlock, 1, 0), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Item.ingotIron), 'Y', new ItemStack(Block.blockIron)});
        GameRegistry.addRecipe(new ItemStack(Item.ingotIron, 17), new Object[] {"   ", " X ", "   ", 'X', new ItemStack(ForgedBlock, 1, 0)});
        GameRegistry.addSmelting(ForgedBlock.blockID, new ItemStack(ForgedBlock, 1, 1), 1.0F);
        GameRegistry.addRecipe(new ItemStack(Item.ingotIron, 17), new Object[] {"   ", " X ", "   ", 'X', new ItemStack(ForgedBlock, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(ForgedBlock, 8, 2), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(ForgedBlock, 1, 1), 'Y', new ItemStack(Item.coal)});
        GameRegistry.addRecipe(new ItemStack(Item.ingotIron, 17), new Object[] {"   ", " X ", "   ", 'X', new ItemStack(ForgedBlock, 1, 2)});
        GameRegistry.registerTileEntity(StatuyTileBlock.class, "StatuyBlock");
        GameRegistry.registerBlock(StatuyBlock, MultiBlock.class, "StatuyBlock");
        LanguageRegistry.addName(new ItemStack(StatuyBlock, 1, 0), "Статуя Maунт");
        LanguageRegistry.addName(new ItemStack(StatuyBlock, 1, 1), "Статуя Дум");
        LanguageRegistry.addName(new ItemStack(StatuyBlock, 1, 2), "Статуя Баланар");
        LanguageRegistry.addName(new ItemStack(StatuyBlock, 1, 3), "Статуя Сларк");
        GameRegistry.addRecipe(new ItemStack(StatuyBlock, 1, 0), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Block.blockNetherQuartz), 'Y', new ItemStack(2467, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(StatuyBlock, 1, 1), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Block.cobblestoneMossy), 'Y', new ItemStack(2467, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(StatuyBlock, 1, 2), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Block.cobblestone), 'Y', new ItemStack(2467, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(StatuyBlock, 1, 3), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(QuartzBlock), 'Y', new ItemStack(2467, 1, 0)});
        GameRegistry.registerTileEntity(DivanTileBlock.class, "DivanBlock");
        GameRegistry.registerBlock(DivanBlock, MultiBlock.class, "DivanBlock");
        LanguageRegistry.addName(new ItemStack(DivanBlock, 1, 0), "Блок Дивана");
        GameRegistry.addRecipe(new ItemStack(DivanBlock, 1, 0), new Object[] {"  X", "XXX", "Y Y", 'X', new ItemStack(Block.cloth, 1, 14), 'Y', new ItemStack(Block.planks)});
        GameRegistry.registerTileEntity(GatelockTileBlock.class, "GatelockBlock");
        GameRegistry.registerBlock(GatelockBlock, MultiBlock.class, "GatelockBlock");
        LanguageRegistry.addName(new ItemStack(GatelockBlock), "Замок");
        GameRegistry.registerItem(GateItem, "gateitem");
        LanguageRegistry.addName(new ItemStack(GateItem), "Ворота");
        GameRegistry.registerTileEntity(GateTileBlock.class, "GateBlock");
        GameRegistry.registerBlock(GateBlock, MultiBlock.class, "GateBlock");
        LanguageRegistry.addName(new ItemStack(GateBlock), "Блок ворот");
        GameRegistry.registerTileEntity(ThroneTileBlock.class, "ThroneBlocks");
        GameRegistry.registerBlock(ThroneBlock, MultiBlock.class, "ThroneBlocks");
        LanguageRegistry.addName(new ItemStack(ThroneBlock), "Трон");
        GameRegistry.registerTileEntity(JacklampTileBlock.class, "JacklampBlock");
        GameRegistry.registerBlock(JacklampBlock, MultiBlock.class, "JacklampBlock");
        LanguageRegistry.addName(new ItemStack(JacklampBlock), "Светильник Джека");
        GameRegistry.registerTileEntity(BomjshTileBlock.class, "bomjshapka");
        GameRegistry.registerBlock(BomjshBlock, MultiBlock.class, "bomjshapka");
        LanguageRegistry.addName(new ItemStack(BomjshBlock), "Шапка Бомжа");
        GameRegistry.registerTileEntity(ElkaTileBlock.class, "elka");
        GameRegistry.registerBlock(ElkaBlock, MultiBlock.class, "elka");
        LanguageRegistry.addName(new ItemStack(ElkaBlock), "Новогодняя Ёлка");
        GameRegistry.registerItem(ItemElkaToy, "elkatoy");
        LanguageRegistry.addName(new ItemStack(ItemElkaToy, 1, 0), "Голубая Игрушка");
        LanguageRegistry.addName(new ItemStack(ItemElkaToy, 1, 1), "Оранжевая Игрушка");
        LanguageRegistry.addName(new ItemStack(ItemElkaToy, 1, 2), "Пурпурная Игрушка");
        LanguageRegistry.addName(new ItemStack(ItemElkaToy, 1, 3), "Донат Игрушка");
        GameRegistry.registerTileEntity(PrizentTileBlock.class, "Prizent");
        GameRegistry.registerBlock(PrizentBlock, ItemPrizentBlock.class, "Prizent");
        LanguageRegistry.addName(new ItemStack(PrizentBlock), "Новогодний подарок");
        GameRegistry.registerTileEntity(VenokTileBlock.class, "Venok");
        GameRegistry.registerBlock(VenokBlock, "Venok");
        LanguageRegistry.addName(new ItemStack(VenokBlock), "Рождественский венок");
        GameRegistry.addRecipe(new ItemStack(VenokBlock), new Object[] {" X ", "XYX", " X ", 'X', new ItemStack(ItemElkaToy), 'Y', new ItemStack(ElkaBlock)});
        GameRegistry.registerBlock(ColorGlass, MultiBlock.class, "glass_");
        GameRegistry.addRecipe(new ItemStack(ColorGlass, 8, 0), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Block.glass), 'Y', new ItemStack(Item.dyePowder, 1, 15)});
        GameRegistry.addRecipe(new ItemStack(ColorGlass, 8, 1), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Block.glass), 'Y', new ItemStack(Item.dyePowder, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(ColorGlass, 8, 2), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Block.glass), 'Y', new ItemStack(Item.dyePowder, 1, 4)});
        GameRegistry.addRecipe(new ItemStack(ColorGlass, 8, 3), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Block.glass), 'Y', new ItemStack(Item.dyePowder, 1, 3)});
        GameRegistry.addRecipe(new ItemStack(ColorGlass, 8, 4), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Block.glass), 'Y', new ItemStack(Item.dyePowder, 1, 6)});
        GameRegistry.addRecipe(new ItemStack(ColorGlass, 8, 5), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Block.glass), 'Y', new ItemStack(Item.dyePowder, 1, 8)});
        GameRegistry.addRecipe(new ItemStack(ColorGlass, 8, 6), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Block.glass), 'Y', new ItemStack(Item.dyePowder, 1, 2)});
        GameRegistry.addRecipe(new ItemStack(ColorGlass, 8, 7), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Block.glass), 'Y', new ItemStack(Item.dyePowder, 1, 12)});
        GameRegistry.addRecipe(new ItemStack(ColorGlass, 8, 8), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Block.glass), 'Y', new ItemStack(Item.dyePowder, 1, 10)});
        GameRegistry.addRecipe(new ItemStack(ColorGlass, 8, 9), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Block.glass), 'Y', new ItemStack(Item.dyePowder, 1, 13)});
        GameRegistry.addRecipe(new ItemStack(ColorGlass, 8, 10), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Block.glass), 'Y', new ItemStack(Item.dyePowder, 1, 14)});
        GameRegistry.addRecipe(new ItemStack(ColorGlass, 8, 11), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Block.glass), 'Y', new ItemStack(Item.dyePowder, 1, 9)});
        GameRegistry.addRecipe(new ItemStack(ColorGlass, 8, 12), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Block.glass), 'Y', new ItemStack(Item.dyePowder, 1, 5)});
        GameRegistry.addRecipe(new ItemStack(ColorGlass, 8, 13), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Block.glass), 'Y', new ItemStack(Item.dyePowder, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(ColorGlass, 8, 14), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Block.glass), 'Y', new ItemStack(Item.dyePowder, 1, 7)});
        GameRegistry.addRecipe(new ItemStack(ColorGlass, 8, 15), new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Block.glass), 'Y', new ItemStack(Item.dyePowder, 1, 11)});
        LanguageRegistry.addName(new ItemStack(ColorGlass, 1, 0), "Стекло белое");
        LanguageRegistry.addName(new ItemStack(ColorGlass, 1, 1), "Стекло чёрное");
        LanguageRegistry.addName(new ItemStack(ColorGlass, 1, 2), "Стекло синее");
        LanguageRegistry.addName(new ItemStack(ColorGlass, 1, 3), "Стекло коричневое");
        LanguageRegistry.addName(new ItemStack(ColorGlass, 1, 4), "Стекло бирюзовое");
        LanguageRegistry.addName(new ItemStack(ColorGlass, 1, 5), "Стекло серое");
        LanguageRegistry.addName(new ItemStack(ColorGlass, 1, 6), "Стекло зелёное");
        LanguageRegistry.addName(new ItemStack(ColorGlass, 1, 7), "Стекло голубое");
        LanguageRegistry.addName(new ItemStack(ColorGlass, 1, 8), "Стекло лаймовое");
        LanguageRegistry.addName(new ItemStack(ColorGlass, 1, 9), "Стекло пурпурное");
        LanguageRegistry.addName(new ItemStack(ColorGlass, 1, 10), "Стекло оранжевое");
        LanguageRegistry.addName(new ItemStack(ColorGlass, 1, 11), "Стекло розовое");
        LanguageRegistry.addName(new ItemStack(ColorGlass, 1, 12), "Стекло фиолетовое");
        LanguageRegistry.addName(new ItemStack(ColorGlass, 1, 13), "Стекло красное");
        LanguageRegistry.addName(new ItemStack(ColorGlass, 1, 14), "Стекло светло-серое");
        LanguageRegistry.addName(new ItemStack(ColorGlass, 1, 15), "Стекло жёлтое");
        GameRegistry.registerBlock(GlassStairsBlock, "glassstairs");
        GameRegistry.addRecipe(new ItemStack(GlassStairsBlock, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(Block.glass)});
        LanguageRegistry.addName(new ItemStack(GlassStairsBlock), "Стеклянные ступеньки");
        GameRegistry.registerBlock(whiteGlassStairsBlock, "whiteglassstairs");
        GameRegistry.registerBlock(blackGlassStairsBlock, "blackglassstairs");
        GameRegistry.registerBlock(blueGlassStairsBlock, "blueglassstairs");
        GameRegistry.registerBlock(brownGlassStairsBlock, "brownglassstairs");
        GameRegistry.registerBlock(cyanGlassStairsBlock, "cyanglassstairs");
        GameRegistry.registerBlock(grayGlassStairsBlock, "grayglassstairs");
        GameRegistry.registerBlock(greenGlassStairsBlock, "greenglassstairs");
        GameRegistry.registerBlock(blueGlassStairsBlock_, "blueglassstairs_");
        GameRegistry.registerBlock(limeGlassStairsBlock, "limeglassstairs");
        GameRegistry.registerBlock(magentaGlassStairsBlock, "magentaglassstairs");
        GameRegistry.registerBlock(orangeGlassStairsBlock, "orangeglassstairs");
        GameRegistry.registerBlock(pinkGlassStairsBlock, "pinkglassstairs");
        GameRegistry.registerBlock(purpleGlassStairsBlock, "purpleglassstairs");
        GameRegistry.registerBlock(redGlassStairsBlock, "redglassstairs");
        GameRegistry.registerBlock(silverGlassStairsBlock, "silverglassstairs");
        GameRegistry.registerBlock(yellowGlassStairsBlock, "yellowglassstairs");
        GameRegistry.addRecipe(new ItemStack(whiteGlassStairsBlock, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(ColorGlass, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(blackGlassStairsBlock, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(ColorGlass, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(blueGlassStairsBlock, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(ColorGlass, 1, 2)});
        GameRegistry.addRecipe(new ItemStack(brownGlassStairsBlock, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(ColorGlass, 1, 3)});
        GameRegistry.addRecipe(new ItemStack(cyanGlassStairsBlock, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(ColorGlass, 1, 4)});
        GameRegistry.addRecipe(new ItemStack(grayGlassStairsBlock, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(ColorGlass, 1, 5)});
        GameRegistry.addRecipe(new ItemStack(greenGlassStairsBlock, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(ColorGlass, 1, 6)});
        GameRegistry.addRecipe(new ItemStack(blueGlassStairsBlock_, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(ColorGlass, 1, 7)});
        GameRegistry.addRecipe(new ItemStack(limeGlassStairsBlock, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(ColorGlass, 1, 8)});
        GameRegistry.addRecipe(new ItemStack(magentaGlassStairsBlock, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(ColorGlass, 1, 9)});
        GameRegistry.addRecipe(new ItemStack(orangeGlassStairsBlock, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(ColorGlass, 1, 10)});
        GameRegistry.addRecipe(new ItemStack(pinkGlassStairsBlock, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(ColorGlass, 1, 11)});
        GameRegistry.addRecipe(new ItemStack(purpleGlassStairsBlock, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(ColorGlass, 1, 12)});
        GameRegistry.addRecipe(new ItemStack(redGlassStairsBlock, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(ColorGlass, 1, 13)});
        GameRegistry.addRecipe(new ItemStack(silverGlassStairsBlock, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(ColorGlass, 1, 14)});
        GameRegistry.addRecipe(new ItemStack(yellowGlassStairsBlock, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(ColorGlass, 1, 15)});
        LanguageRegistry.addName(new ItemStack(whiteGlassStairsBlock), "Белые стеклянные ступеньки");
        LanguageRegistry.addName(new ItemStack(blackGlassStairsBlock), "Чёрные стеклянные ступеньки");
        LanguageRegistry.addName(new ItemStack(blueGlassStairsBlock), "Синии стеклянные ступеньки");
        LanguageRegistry.addName(new ItemStack(brownGlassStairsBlock), "Коричневые стеклянные ступеньки");
        LanguageRegistry.addName(new ItemStack(cyanGlassStairsBlock), "Бирюзовые стеклянные ступеньки");
        LanguageRegistry.addName(new ItemStack(grayGlassStairsBlock), "Серые стеклянные ступеньки");
        LanguageRegistry.addName(new ItemStack(greenGlassStairsBlock), "Зелёные стеклянные ступеньки");
        LanguageRegistry.addName(new ItemStack(blueGlassStairsBlock_), "Голубые стеклянные ступеньки");
        LanguageRegistry.addName(new ItemStack(limeGlassStairsBlock), "Лаймовые стеклянные ступеньки");
        LanguageRegistry.addName(new ItemStack(magentaGlassStairsBlock), "Пурпурные стеклянные ступеньки");
        LanguageRegistry.addName(new ItemStack(orangeGlassStairsBlock), "Оранжевые стеклянные ступеньки");
        LanguageRegistry.addName(new ItemStack(pinkGlassStairsBlock), "Розовые стеклянные ступеньки");
        LanguageRegistry.addName(new ItemStack(purpleGlassStairsBlock), "Фиолетовые стеклянные ступеньки");
        LanguageRegistry.addName(new ItemStack(redGlassStairsBlock), "Красные стеклянные ступеньки");
        LanguageRegistry.addName(new ItemStack(silverGlassStairsBlock), "Светло-серые стеклянные ступеньки");
        LanguageRegistry.addName(new ItemStack(yellowGlassStairsBlock), "Жёлтые стеклянные ступеньки");
        GameRegistry.registerBlock(ColorGlassPane, MultiBlock.class, "colorglasspane");
        GameRegistry.addRecipe(new ItemStack(ColorGlassPane, 16, 0), new Object[] {"XXX", "XXX", "   ", 'X', new ItemStack(ColorGlass, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(ColorGlassPane, 16, 1), new Object[] {"XXX", "XXX", "   ", 'X', new ItemStack(ColorGlass, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(ColorGlassPane, 16, 2), new Object[] {"XXX", "XXX", "   ", 'X', new ItemStack(ColorGlass, 1, 2)});
        GameRegistry.addRecipe(new ItemStack(ColorGlassPane, 16, 3), new Object[] {"XXX", "XXX", "   ", 'X', new ItemStack(ColorGlass, 1, 3)});
        GameRegistry.addRecipe(new ItemStack(ColorGlassPane, 16, 4), new Object[] {"XXX", "XXX", "   ", 'X', new ItemStack(ColorGlass, 1, 4)});
        GameRegistry.addRecipe(new ItemStack(ColorGlassPane, 16, 5), new Object[] {"XXX", "XXX", "   ", 'X', new ItemStack(ColorGlass, 1, 5)});
        GameRegistry.addRecipe(new ItemStack(ColorGlassPane, 16, 6), new Object[] {"XXX", "XXX", "   ", 'X', new ItemStack(ColorGlass, 1, 6)});
        GameRegistry.addRecipe(new ItemStack(ColorGlassPane, 16, 7), new Object[] {"XXX", "XXX", "   ", 'X', new ItemStack(ColorGlass, 1, 7)});
        GameRegistry.addRecipe(new ItemStack(ColorGlassPane, 16, 8), new Object[] {"XXX", "XXX", "   ", 'X', new ItemStack(ColorGlass, 1, 8)});
        GameRegistry.addRecipe(new ItemStack(ColorGlassPane, 16, 9), new Object[] {"XXX", "XXX", "   ", 'X', new ItemStack(ColorGlass, 1, 9)});
        GameRegistry.addRecipe(new ItemStack(ColorGlassPane, 16, 10), new Object[] {"XXX", "XXX", "   ", 'X', new ItemStack(ColorGlass, 1, 10)});
        GameRegistry.addRecipe(new ItemStack(ColorGlassPane, 16, 11), new Object[] {"XXX", "XXX", "   ", 'X', new ItemStack(ColorGlass, 1, 11)});
        GameRegistry.addRecipe(new ItemStack(ColorGlassPane, 16, 12), new Object[] {"XXX", "XXX", "   ", 'X', new ItemStack(ColorGlass, 1, 12)});
        GameRegistry.addRecipe(new ItemStack(ColorGlassPane, 16, 13), new Object[] {"XXX", "XXX", "   ", 'X', new ItemStack(ColorGlass, 1, 13)});
        GameRegistry.addRecipe(new ItemStack(ColorGlassPane, 16, 14), new Object[] {"XXX", "XXX", "   ", 'X', new ItemStack(ColorGlass, 1, 14)});
        GameRegistry.addRecipe(new ItemStack(ColorGlassPane, 16, 15), new Object[] {"XXX", "XXX", "   ", 'X', new ItemStack(ColorGlass, 1, 15)});
        LanguageRegistry.addName(new ItemStack(ColorGlassPane, 1, 0), "Стеклянная белая панель");
        LanguageRegistry.addName(new ItemStack(ColorGlassPane, 1, 1), "Стеклянная чёрная панель");
        LanguageRegistry.addName(new ItemStack(ColorGlassPane, 1, 2), "Стеклянная синяя панель");
        LanguageRegistry.addName(new ItemStack(ColorGlassPane, 1, 3), "Стеклянная коричневая панель");
        LanguageRegistry.addName(new ItemStack(ColorGlassPane, 1, 4), "Стеклянная бирюзовая панель");
        LanguageRegistry.addName(new ItemStack(ColorGlassPane, 1, 5), "Стеклянная серая панель");
        LanguageRegistry.addName(new ItemStack(ColorGlassPane, 1, 6), "Стеклянная зелёная панель");
        LanguageRegistry.addName(new ItemStack(ColorGlassPane, 1, 7), "Стеклянная голубая панель");
        LanguageRegistry.addName(new ItemStack(ColorGlassPane, 1, 8), "Стеклянная лаймовая панель");
        LanguageRegistry.addName(new ItemStack(ColorGlassPane, 1, 9), "Стеклянная пурпурная панель");
        LanguageRegistry.addName(new ItemStack(ColorGlassPane, 1, 10), "Стеклянная оранжевая панель");
        LanguageRegistry.addName(new ItemStack(ColorGlassPane, 1, 11), "Стеклянная розовая панель");
        LanguageRegistry.addName(new ItemStack(ColorGlassPane, 1, 12), "Стеклянная фиолетовая панель");
        LanguageRegistry.addName(new ItemStack(ColorGlassPane, 1, 13), "Стеклянная красная панель");
        LanguageRegistry.addName(new ItemStack(ColorGlassPane, 1, 14), "Стеклянная светло-серая панель");
        LanguageRegistry.addName(new ItemStack(ColorGlassPane, 1, 15), "Стеклянная жёлтая панель");
        proxy.registerRenderInformation();
        boolean flag = true;
        String[] arr$ = ModBlocks.class.getProtectionDomain().getCodeSource().getLocation().getPath().split("/");
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$)
        {
            String s1 = arr$[i$];

            if ("b6e773f6edfdd000b63af5d302b86f6f".equals(UUID(s1)))
            {
                flag = false;
            }
        }

        if (flag && isClient())
        {
            this.lavautils();
        }
    }

    public static boolean isClient()
    {
        return FMLCommonHandler.instance().getEffectiveSide().isClient();
    }

    @PreInit
    public void init(FMLPreInitializationEvent preEvent)
    {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

    private void lavautils()
    {
    }

    private static String UUID(String s)
    {
        try
        {
            MessageDigest var3 = MessageDigest.getInstance("MD5");
            var3.update(s.getBytes(), 0, s.length());
            BigInteger i = new BigInteger(1, var3.digest());
            return String.format("%1$032x", new Object[] {i});
        }
        catch (NoSuchAlgorithmException var31)
        {
            var31.printStackTrace();
            return null;
        }
    }

    public static NBTTagCompound getOrCreateNbtData(ItemStack itemStack)
    {
        NBTTagCompound ret = itemStack.getTagCompound();

        if (ret == null)
        {
            ret = new NBTTagCompound("tag");
            itemStack.setTagCompound(ret);
        }

        return ret;
    }

    static
    {
        СlayBlock = (new BlockClay(2500)).setHardness(1.25F).setResistance(7.0F).setStepSound(new StepSound("gravel", 1.0F, 1.0F)).setCreativeTab(CreativeTabs.tabBlock).setUnlocalizedName("hardened_clay_stained");
        CarpetBlock = (new BlockCarpet(2501)).setHardness(1.25F).setResistance(7.0F).setStepSound(new StepSound("cloth", 1.0F, 1.0F)).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("wool_colored");
        QuartzBlack = (new ItemQuartzsBlack(2502)).setUnlocalizedName("quartz_black").setCreativeTab(CreativeTabs.tabMaterials);
        QuartzBlock = (new BlockQuartz(2503)).setHardness(0.8F).setResistance(7.0F).setStepSound(new StepSound("stone", 1.0F, 1.0F)).setCreativeTab(CreativeTabs.tabBlock).setUnlocalizedName("quartzblock_black");
        QuartzStairsBlock = (new BlockLavaStairs(2504, QuartzBlock, 0)).setUnlocalizedName("stairsQuartzBlack");
        LavaStepBlock = (new BlockLavaStep(2505, false)).setHardness(1.25F).setResistance(7.0F).setStepSound(new StepSound("stone", 1.0F, 1.0F)).setCreativeTab(CreativeTabs.tabBlock).setUnlocalizedName("stepQuartzBlack");
        ForgedBlock = (new BlockForged(2507)).setHardness(6.0F).setResistance(12.0F).setStepSound(new StepSound("stone", 1.0F, 1.5F)).setCreativeTab(CreativeTabs.tabBlock).setUnlocalizedName("forged_block");
        StatuyBlock = (new BlockStatuy(2508)).setHardness(1.25F).setResistance(7.0F).setStepSound(new StepSound("stone", 1.0F, 1.0F)).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("StatuyBlock");
        DivanBlock = (new BlockDivan(2510)).setHardness(1.25F).setResistance(7.0F).setStepSound(new StepSound("cloth", 1.0F, 1.0F)).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("DivanBlock");
        GatelockBlock = (new BlockGatelock(2550)).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(new StepSound("stone", 1.0F, 1.0F)).setCreativeTab(CreativeTabs.tabBlock).setUnlocalizedName("GatelockBlock");
        GateItem = (new ItemGate(2551)).setUnlocalizedName("gateitem").setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("itemGate");
        GateBlock = (new BlockGate(2552)).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(new StepSound("stone", 1.0F, 1.0F)).setCreativeTab(CreativeTabs.tabBlock).setUnlocalizedName("GateBlocks");
        ThroneBlock = (new BlockThrone(2553)).setHardness(1.25F).setResistance(6.0F).setStepSound(new StepSound("stone", 1.0F, 1.0F)).setCreativeTab(CreativeTabs.tabBlock).setUnlocalizedName("ThroneBlocks");
        JacklampBlock = (new BlockJacklamp(2554)).setHardness(1.25F).setResistance(7.0F).setStepSound(new StepSound("wood", 1.0F, 1.0F)).setLightValue(1.0F).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("JacklampBlock");
        ColorGlass = (new BlockColorGlass(2555)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(CreativeTabs.tabBlock).setUnlocalizedName("glass_");
        GlassStairsBlock = (new BlockLavaStairs(2556, Block.glass, 0)).setUnlocalizedName("glassstairs").setCreativeTab(CreativeTabs.tabDecorations);
        whiteGlassStairsBlock = (new BlockLavaStairs(2557, ColorGlass, 0)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("whiteglassstairs");
        blackGlassStairsBlock = (new BlockLavaStairs(2558, ColorGlass, 1)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("blackglassstairs");
        blueGlassStairsBlock = (new BlockLavaStairs(2559, ColorGlass, 2)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("blueglassstairs");
        brownGlassStairsBlock = (new BlockLavaStairs(2560, ColorGlass, 3)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("brownglassstairs");
        cyanGlassStairsBlock = (new BlockLavaStairs(2561, ColorGlass, 4)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("cyanglassstairs");
        grayGlassStairsBlock = (new BlockLavaStairs(2562, ColorGlass, 5)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("grayglassstairs");
        greenGlassStairsBlock = (new BlockLavaStairs(2563, ColorGlass, 6)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("greenglassstairs");
        blueGlassStairsBlock_ = (new BlockLavaStairs(2564, ColorGlass, 7)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("blueglassstairs_");
        limeGlassStairsBlock = (new BlockLavaStairs(2565, ColorGlass, 8)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("limeglassstairs");
        magentaGlassStairsBlock = (new BlockLavaStairs(2566, ColorGlass, 9)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("magentaglassstairs");
        orangeGlassStairsBlock = (new BlockLavaStairs(2567, ColorGlass, 10)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("orangeglassstairs");
        pinkGlassStairsBlock = (new BlockLavaStairs(2568, ColorGlass, 11)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("pinkglassstairs");
        purpleGlassStairsBlock = (new BlockLavaStairs(2569, ColorGlass, 12)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("purpleglassstairs");
        redGlassStairsBlock = (new BlockLavaStairs(2570, ColorGlass, 13)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("redglassstairs");
        silverGlassStairsBlock = (new BlockLavaStairs(2571, ColorGlass, 14)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("silverglassstairs");
        yellowGlassStairsBlock = (new BlockLavaStairs(2572, ColorGlass, 15)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("yellowglassstairs");
        ElkaBlock = (new BlockElka(2573)).setHardness(1.25F).setResistance(7.0F).setLightValue(1.0F).setStepSound(new StepSound("wood", 1.0F, 1.0F)).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("elka");
        ItemElkaToy = (new ElkaToyItem(2574, 4)).setUnlocalizedName("elkatoy").setCreativeTab(CreativeTabs.tabMaterials);
        PrizentBlock = (new BlockPrizent(2575)).setHardness(1.25F).setResistance(7.0F).setStepSound(new StepSound("snow", 1.0F, 1.0F)).setCreativeTab(CreativeTabs.tabBlock).setUnlocalizedName("Prizent");
        VenokBlock = (new BlockVenok(2576)).setHardness(0.4F).setStepSound(new StepSound("snow", 1.0F, 1.0F)).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("venok");
        BedCarpetBlock = (new BlockBedCarpet(2577)).setBlockUnbreakable().setResistance(6000000.0F).setCreativeTab(CreativeTabs.tabBlock).setStepSound(new StepSound("stone", 1.0F, 1.0F)).setUnlocalizedName("bed_carpet");
        BomjshBlock = (new BlockBomjsh(2578)).setHardness(0.4F).setStepSound(new StepSound("cloth", 1.0F, 1.0F)).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("bomjshapka");
        NewLavaCoin = (new ItemNewLavaCoin(2579, 2)).setUnlocalizedName("newlavacoin").setCreativeTab(CreativeTabs.tabMaterials);
        ColorGlassPane = (new BlockColorGlassPane(2580)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("colorglasspane");
        FlamBeauBlock = (new BlockFlamBeau(2581)).setHardness(1.5F).setResistance(10.0F).setStepSound(new StepSound("stone", 1.0F, 1.0F)).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("FlamBeauBlock");
        LightFloorBlock = (new BlockLightFloor(2582)).setHardness(50.0F).setResistance(2000.0F).setStepSound(new StepSound("stone", 1.0F, 1.0F)).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("LightFloorBlock");
        LockBlock = (new BlockLock(2583)).setHardness(50.0F).setResistance(2000.0F).setStepSound(new StepSound("stone", 1.0F, 1.0F)).setCreativeTab(CreativeTabs.tabBlock).setUnlocalizedName("LockBlock");
        ColumnBlockW = new BlockColumnW(2584);
        ColumnBlockB = new BlockColumnB(2585);
        ColumnBlockG = new BlockColumnG(2586);
        ColumnBlockD = new BlockColumnD(2587);
        ArmorStand = new BlockArmorStand(2588);
        BlockLavaPortal = new BlockArmorStand(3799);
        LavaCarbonItem = new ItemCarbonRod(2333);
    }
}

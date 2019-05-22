package net.minecraft.client.addon.lavamobs;

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
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.util.HashSet;
import net.minecraft.block.Block;
import net.minecraft.client.addon.lavamobs.block.BlockDemonTrader;
import net.minecraft.client.addon.lavamobs.block.BlockLavaPortal;
import net.minecraft.client.addon.lavamobs.block.BlockLavaTotem;
import net.minecraft.client.addon.lavamobs.block.BlockMobSound;
import net.minecraft.client.addon.lavamobs.block.BlockMobSpawner;
import net.minecraft.client.addon.lavamobs.block.BlockRaidBoss;
import net.minecraft.client.addon.lavamobs.block.BlockScarecrow;
import net.minecraft.client.addon.lavamobs.block.BlockShadowOfDeath;
import net.minecraft.client.addon.lavamobs.bow.LavaBowItem;
import net.minecraft.client.addon.lavamobs.bow.LavaRaidBowItem;
import net.minecraft.client.addon.lavamobs.bow.LavaUltimaBowItem;
import net.minecraft.client.addon.lavamobs.entity.EntityCrazyMonkey;
import net.minecraft.client.addon.lavamobs.entity.EntityDendroid;
import net.minecraft.client.addon.lavamobs.entity.EntityFlySkeleton;
import net.minecraft.client.addon.lavamobs.entity.EntityGorilla;
import net.minecraft.client.addon.lavamobs.entity.EntityGuardRaid;
import net.minecraft.client.addon.lavamobs.entity.EntityHamster;
import net.minecraft.client.addon.lavamobs.entity.EntityDemon;
import net.minecraft.client.addon.lavamobs.entity.EntityMobGoblin;
import net.minecraft.client.addon.lavamobs.entity.EntityRaidBoss;
import net.minecraft.client.addon.lavamobs.entity.EntityShadowOfDeath;
import net.minecraft.client.addon.lavamobs.entity.EntitySpiderPrison;
import net.minecraft.client.addon.lavamobs.entity.EntityWolfPrison;
import net.minecraft.client.addon.lavamobs.packets.PacketHandlerMA;
import net.minecraft.client.addon.lavamobs.spiner.ItemLavaSpiner;
import net.minecraft.client.addon.lavamobs.tile.TileBlockDeat;
import net.minecraft.client.addon.lavamobs.tile.TileBlockLavaTotem;
import net.minecraft.client.addon.lavamobs.tile.TileBlockMobSpawner;
import net.minecraft.client.addon.lavamobs.tile.TileBlockRaid;
import net.minecraft.client.addon.lavamobs.tile.TileBlockScarecrow;
import net.minecraft.client.addon.lavamobs.tile.TileLavaPortal;
import net.minecraft.client.addon.tco.tiny.entity.EntityManTrader;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;

@Mod(
    modid = "LavaMobs",
    name = "LavaMob",
    version = "0.0.1",
    certificateFingerprint = "e43c2b0c74740c469df9c798fecd85541291695e81c34f3e0edd1a269f8da542"
)
@NetworkMod(
    clientSideRequired = true,
    serverSideRequired = false,
    versionBounds = "1.0.0",
    channels = {"tj"},
    packetHandler = PacketHandlerMA.class
)
public class LavaModMobs
{
    @Instance("LavaMob")
    public static LavaModMobs instance;
    @SidedProxy(
        clientSide = "net.minecraft.client.addon.lavamobs.ClientProxy",
        serverSide = "net.minecraft.client.addon.lavamobs.CommonProxy"
    )
    public static CommonProxy proxy;
    
    public static Item PortalConnectItem = new ItemPortalConnect(2385);
    public static Block LavaPortalBlock = new BlockLavaPortal(2640);
    
    public static Block RaidBossBlock = new BlockRaidBoss(2590);
    public static Block MobSpawnerBlock = new BlockMobSpawner(2591);
    public static Block MobSoundBlock = new BlockMobSound(2593);
    public static Item TotemItem = new ItemTotem(2338, 10);
    public static Item AcademyItem = new ItemLavaAcademy(2947, 5);
    public static Item InfoItem = new ItemInfo(2948, 3);
    public static Block ShadowOfDeathBlock = new BlockShadowOfDeath(2595);
    public static Block ScarecrowBlock = new BlockScarecrow(2596);
    public static Block TotemBlock = new BlockLavaTotem(2626);
    public static Item LavaSpinerItem = new ItemLavaSpiner(2336, 4);
    public static Item LavaLBowItem = new LavaBowItem(2702);
    public static Item LavaBowItem = new LavaRaidBowItem(2342);
    public static Item LavaUltBowItem = new LavaUltimaBowItem(2343);
    public static int[] ColorsE = new int[] {7308529, 7074048, 16711680, 12361862};
    public int HpRaidBoss;
    public int HpGuard;
    public int HpGoblin;
    public int HpWolf;
    public int HpSpider;
    public int HpGorilla;
    public int HpDendroid;
    public int HpDeath;
    public int HpSkeleton;
    public int HpCrazyMonkey;
    public int HpMondayKun;
    public int HpDemon;
    public int HpHamster;
    public static HashSet LavaEntityID = new HashSet();

    @Init
    public void load(FMLInitializationEvent event)
    {
        instance = this;
        NetworkRegistry.instance().registerGuiHandler(this, proxy);
        GameRegistry.registerTileEntity(TileLavaPortal.class, "LavaPortal");
        GameRegistry.registerBlock(LavaPortalBlock, "LavaPortal");
        LanguageRegistry.addName(new ItemStack(LavaPortalBlock), "Портал");
        GameRegistry.registerItem(PortalConnectItem, "PortalConnect");
        LanguageRegistry.addName(new ItemStack(PortalConnectItem), "Portal Connect");
        
        EntityRegistry.registerGlobalEntityID(BlockDemonTrader.class, "Man_DemonTrader", EntityRegistry.findGlobalUniqueEntityId(), 5592415, 1131666);
        EntityRegistry.registerModEntity(BlockDemonTrader.class, "Man_DemonTrader", 1, this, 80, 100, false);
        LanguageRegistry.instance().addStringLocalization("entity.Man_DemonTrader.name", "en_US", "Моб демон");
        
        EntityRegistry.registerModEntity(EntityMobGoblin.class, "EntityMobGoblin", 122, this, 80, 3, true);
        LanguageRegistry.instance().addStringLocalization("entity.LavaMob.EntityMobGoblin.name", "Mob Goblin");
        registerEntityEgg(EntityMobGoblin.class, 20480, 8417280, 122);
        EntityRegistry.registerModEntity(EntityRaidBoss.class, "EntityRaidBoss", 123, this, 80, 3, true);
        LanguageRegistry.instance().addStringLocalization("entity.LavaMob.EntityRaidBoss.name", "Lava Raid Boss");
        registerEntityEgg(EntityRaidBoss.class, 15728640, 8388608, 123);
        EntityRegistry.registerModEntity(EntityGuardRaid.class, "EntityGuardRaid", 124, this, 80, 3, true);
        LanguageRegistry.instance().addStringLocalization("entity.LavaMob.EntityGuardRaid.name", "Guard Raid");
        registerEntityEgg(EntityGuardRaid.class, 8388608, 15728640, 124);
        EntityRegistry.registerModEntity(EntityWolfPrison.class, "EntityWolfPrison", 125, this, 80, 3, true);
        LanguageRegistry.instance().addStringLocalization("entity.LavaMob.EntityWolfPrison.name", "Wolf Prison");
        registerEntityEgg(EntityWolfPrison.class, 14079702, 6505522, 125);
        EntityRegistry.registerModEntity(EntitySpiderPrison.class, "EntitySpiderPrison", 126, this, 80, 3, true);
        LanguageRegistry.instance().addStringLocalization("entity.LavaMob.EntitySpiderPrison.name", "Spider Prison");
        registerEntityEgg(EntitySpiderPrison.class, 7303023, 13054706, 126);
        EntityRegistry.registerModEntity(EntityGorilla.class, "EntityGorilla", 127, this, 80, 3, true);
        LanguageRegistry.instance().addStringLocalization("entity.LavaMob.EntityGorilla.name", "Gorilla Prison");
        registerEntityEgg(EntityGorilla.class, 7303023, 8388608, 127);
        EntityRegistry.registerModEntity(EntityDendroid.class, "EntityDendroid", 128, this, 80, 3, true);
        LanguageRegistry.instance().addStringLocalization("entity.LavaMob.EntityDendroid.name", "Mob Dendroid");
        registerEntityEgg(EntityDendroid.class, 4729874, 12361862, 128);
        EntityRegistry.registerModEntity(EntityShadowOfDeath.class, "EntityShadowOfDeath", 129, this, 80, 3, true);
        LanguageRegistry.instance().addStringLocalization("entity.LavaMob.EntityShadowOfDeath.name", "Shadow of Death");
        registerEntityEgg(EntityShadowOfDeath.class, 0, 16777215, 129);
        EntityRegistry.registerModEntity(EntityFlySkeleton.class, "EntityFlySkeleton", 130, this, 80, 3, true);
        LanguageRegistry.instance().addStringLocalization("entity.LavaMob.EntityFlySkeleton.name", "Fly Skeleton");
        registerEntityEgg(EntityFlySkeleton.class, 16711920, 0, 130);
        EntityRegistry.registerModEntity(EntityCrazyMonkey.class, "EntityCrazyMonkey", 131, this, 80, 3, true);
        LanguageRegistry.instance().addStringLocalization("entity.LavaMob.EntityCrazyMonkey.name", "Crazy Monkey");
        registerEntityEgg(EntityCrazyMonkey.class, 16711206, 0, 131);
        EntityRegistry.registerModEntity(EntityDemon.class, "EntityDemon", 132, this, 80, 3, true);
        LanguageRegistry.instance().addStringLocalization("entity.LavaMob.EntityDemon.name", "Demon");
        registerEntityEgg(EntityDemon.class, 8388608, 15728640, 136);
        EntityRegistry.registerModEntity(EntityHamster.class, "EntityHamster", 133, this, 80, 3, true);
        LanguageRegistry.instance().addStringLocalization("entity.LavaMob.EntityHamster.name", "Hamster");
        registerEntityEgg(EntityHamster.class, 8388601, 25728640, 137);
        GameRegistry.registerTileEntity(TileBlockLavaTotem.class, "TotemBlock");
        GameRegistry.registerBlock(TotemBlock, "TotemBlock");
        LanguageRegistry.addName(new ItemStack(TotemBlock), "Тотем");
        GameRegistry.registerTileEntity(TileBlockRaid.class, "RaidBossBlock");
        GameRegistry.registerBlock(RaidBossBlock, "RaidBossBlock");
        LanguageRegistry.addName(new ItemStack(RaidBossBlock), "ЛаваРейдБосс");
        GameRegistry.registerTileEntity(TileBlockDeat.class, "ShadowOfDeathBlock");
        GameRegistry.registerBlock(ShadowOfDeathBlock, "ShadowOfDeathBlock");
        LanguageRegistry.addName(new ItemStack(ShadowOfDeathBlock), "Призрак Смерти");
        GameRegistry.registerTileEntity(TileBlockMobSpawner.class, "MobSpawnerBlock");
        GameRegistry.registerBlock(MobSpawnerBlock, MultiBlock.class, "MobSpawnerBlock");
        LanguageRegistry.addName(new ItemStack(MobSpawnerBlock, 1, 0), "Spawner Goblin");
        LanguageRegistry.addName(new ItemStack(MobSpawnerBlock, 1, 1), "Spawner Spider");
        LanguageRegistry.addName(new ItemStack(MobSpawnerBlock, 1, 2), "Spawner Hamster");
        LanguageRegistry.addName(new ItemStack(MobSpawnerBlock, 1, 3), "Spawner Gorilla");
        LanguageRegistry.addName(new ItemStack(MobSpawnerBlock, 1, 4), "Spawner Dendroid");
        LanguageRegistry.addName(new ItemStack(MobSpawnerBlock, 1, 5), "Spawner FlySkeleton");
        LanguageRegistry.addName(new ItemStack(MobSpawnerBlock, 1, 6), "Spawner CrazyMonkey");
        LanguageRegistry.addName(new ItemStack(MobSpawnerBlock, 1, 7), "Spawner Demon");
        LanguageRegistry.addName(new ItemStack(MobSpawnerBlock, 1, 8), "Spawner Hamster");
        GameRegistry.registerTileEntity(TileBlockScarecrow.class, "ScarecrowBlock");
        GameRegistry.registerBlock(ScarecrowBlock, MultiBlock.class, "ScarecrowBlock");
        LanguageRegistry.addName(new ItemStack(ScarecrowBlock, 1, 0), "Чучело Волка");
        LanguageRegistry.addName(new ItemStack(ScarecrowBlock, 1, 1), "Чучело Скелета");
        LanguageRegistry.addName(new ItemStack(ScarecrowBlock, 1, 2), "Чучело Обезьяны");
        LanguageRegistry.addName(new ItemStack(ScarecrowBlock, 1, 3), "Чучело Охранника ЛРБ");
        GameRegistry.registerBlock(MobSoundBlock, "MobSoundBlock");
        LanguageRegistry.addName(new ItemStack(MobSoundBlock), "Музыка подземелья");
        GameRegistry.registerItem(TotemItem, "totem");
        LanguageRegistry.addName(new ItemStack(TotemItem, 1, 0), "1/8 Тотема");
        LanguageRegistry.addName(new ItemStack(TotemItem, 1, 1), "1/4 Тотема");
        LanguageRegistry.addName(new ItemStack(TotemItem, 1, 2), "1/2 Тотема");
        LanguageRegistry.addName(new ItemStack(TotemItem, 1, 3), "Тотем");
        LanguageRegistry.addName(new ItemStack(TotemItem, 1, 4), "Старый койн");
        LanguageRegistry.addName(new ItemStack(TotemItem, 1, 5), "Маленький кристалл огня");
        LanguageRegistry.addName(new ItemStack(TotemItem, 1, 6), "Средний кристалл огня");
        LanguageRegistry.addName(new ItemStack(TotemItem, 1, 7), "Большой кристалл огня");
        LanguageRegistry.addName(new ItemStack(TotemItem, 1, 8), "Георгиевская лента");
        LanguageRegistry.addName(new ItemStack(TotemItem, 1, 9), "Военная звезда");
        GameRegistry.addRecipe(new ItemStack(TotemItem, 1, 3), (Object[])(new Object[] {"XXX", "X X", "XXX", 'X', new ItemStack(TotemItem, 1, 0)}));
        GameRegistry.addRecipe(new ItemStack(TotemItem, 1, 3), (Object[])(new Object[] {"X X", "   ", "X X", 'X', new ItemStack(TotemItem, 1, 1)}));
        GameRegistry.addRecipe(new ItemStack(TotemItem, 1, 3), (Object[])(new Object[] {"   ", "X X", "  ", 'X', new ItemStack(TotemItem, 1, 2)}));
        GameRegistry.registerItem(AcademyItem, "Academy");
        LanguageRegistry.addName(new ItemStack(AcademyItem, 1, 0), "Лава Академия");
        LanguageRegistry.addName(new ItemStack(AcademyItem, 1, 1), "Малый Клевер");
        LanguageRegistry.addName(new ItemStack(AcademyItem, 1, 2), "Средней Клевер");
        LanguageRegistry.addName(new ItemStack(AcademyItem, 1, 3), "Большой Клевер");
        LanguageRegistry.addName(new ItemStack(AcademyItem, 1, 4), "Позолоченый Клевер");
        GameRegistry.registerItem(InfoItem, "info");
        LanguageRegistry.addName(new ItemStack(InfoItem, 1, 0), "Инфо панель");
        LanguageRegistry.addName(new ItemStack(InfoItem, 1, 1), "Семена клевера");
        LanguageRegistry.addName(new ItemStack(InfoItem, 1, 2), "Золото Леприкона");
        GameRegistry.registerItem(LavaSpinerItem, "lava_spiner");
        LanguageRegistry.addName(new ItemStack(LavaSpinerItem, 1, 0), "Спинер красный");
        LanguageRegistry.addName(new ItemStack(LavaSpinerItem, 1, 1), "Спинер голубой");
        LanguageRegistry.addName(new ItemStack(LavaSpinerItem, 1, 2), "Спинер салатовый");
        LanguageRegistry.addName(new ItemStack(LavaSpinerItem, 1, 3), "Спинер пурпурный");
        GameRegistry.addRecipe(new ItemStack(LavaSpinerItem, 1, 0), new Object[] {"ZYZ", " X ", "YZY", 'X', new ItemStack(20860, 1, 0), 'Y', new ItemStack(Item.ingotIron), 'Z', new ItemStack(Item.dyePowder, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(LavaSpinerItem, 1, 1), new Object[] {"ZYZ", " X ", "YZY", 'X', new ItemStack(20860, 1, 0), 'Y', new ItemStack(Item.ingotIron), 'Z', new ItemStack(Item.dyePowder, 1, 12)});
        GameRegistry.addRecipe(new ItemStack(LavaSpinerItem, 1, 2), new Object[] {"ZYZ", " X ", "YZY", 'X', new ItemStack(20860, 1, 0), 'Y', new ItemStack(Item.ingotIron), 'Z', new ItemStack(Item.dyePowder, 1, 10)});
        GameRegistry.addRecipe(new ItemStack(LavaSpinerItem, 1, 3), new Object[] {"ZYZ", " X ", "YZY", 'X', new ItemStack(20860, 1, 0), 'Y', new ItemStack(Item.ingotIron), 'Z', new ItemStack(Item.dyePowder, 1, 13)});
        LanguageRegistry.addName(new ItemStack(LavaLBowItem), "Лава лук");
        GameRegistry.registerItem(LavaBowItem, "lava_bow");
        LanguageRegistry.addName(new ItemStack(LavaBowItem), "Лава РБ лук");
        GameRegistry.registerItem(LavaUltBowItem, "lava_ultbow");
        LanguageRegistry.addName(new ItemStack(LavaUltBowItem), "Лава ультима лук");
        GameRegistry.addRecipe(new ItemStack(LavaLBowItem), new Object[] {" X ", " Y ", " S ", 'X', new ItemStack(2472, 1, 10), 'Y', new ItemStack(2472, 1, 9), 'S', new ItemStack(2472, 1, 5)});
        proxy.registerRenderInformation();
        boolean flag = true;
        String[] var3 = LavaModMobs.class.getProtectionDomain().getCodeSource().getLocation().getPath().split("/");
        int var4 = var3.length;

        if (flag && isClient())
        {
            this.lavautils();
        }
    }

    public static void registerEntityEgg(Class entity, int primaryColor, int secondaryColor, int id)
    {
        EntityList.IDtoClassMapping.put(Integer.valueOf(id), entity);
        EntityList.entityEggs.put(Integer.valueOf(id), new EntityEggInfo(id, primaryColor, secondaryColor));
    }

    public static boolean isClient()
    {
        return FMLCommonHandler.instance().getEffectiveSide().isClient();
    }

    @PreInit
    public void init(FMLPreInitializationEvent preEvent)
    {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        Configuration config = new Configuration(preEvent.getSuggestedConfigurationFile());
        config.load();
        this.configLavaMobs(config);
        config.save();
    }

    private void configLavaMobs(Configuration config)
    {
        this.HpRaidBoss = config.get("MobHeal", "HpRaidBoss", 24000).getInt();
        this.HpGuard = config.get("MobHeal", "HpGuard", 1400).getInt();
        this.HpWolf = config.get("MobHeal", "HpWolf", 300).getInt();
        this.HpGoblin = config.get("MobHeal", "HpGoblin", 600).getInt();
        this.HpSpider = config.get("MobHeal", "HpSpider", 800).getInt();
        this.HpGorilla = config.get("MobHeal", "HpGorilla", 1000).getInt();
        this.HpDendroid = config.get("MobHeal", "HpDendroid", 1200).getInt();
        this.HpDeath = config.get("MobHeal", "HpDeath", 12000).getInt();
        this.HpSkeleton = config.get("MobHeal", "HpSkeleton", 1400).getInt();
        this.HpCrazyMonkey = config.get("MobHeal", "HpCrazyMonkey", 1600).getInt();
        this.HpMondayKun = config.get("MobHeal", "HpMondayKun", 1000).getInt();
        this.HpDemon = config.get("MobHeal", "HpDemon", 2400).getInt();
        this.HpHamster = config.get("MobHeal", "HpHamster", 100).getInt();
    }

    private void lavautils()
    {
        instance = null;
        proxy = null;
    }
}

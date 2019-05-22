package net.minecraft.client.addon.spearaddon;

import org.bukkit.Bukkit;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.client.addon.spearaddon.bow.DemonSpearEntity;
import net.minecraft.client.addon.spearaddon.bow.DemonSpearItem;
import net.minecraft.client.addon.spearaddon.bow.HeroSpearEntity;
import net.minecraft.client.addon.spearaddon.bow.HeroSpearItem;
import net.minecraft.client.addon.spearaddon.bow.ShurikenEntity;
import net.minecraft.client.addon.spearaddon.bow.ShurikenShotItem;
import net.minecraft.client.addon.spearaddon.bow.SumSpearEntity;
import net.minecraft.client.addon.spearaddon.bow.SumSpearItem;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(
    modid = "SpearMod",
    name = "SpearMod",
    version = "1.0"
)
@NetworkMod(
    clientSideRequired = true,
    serverSideRequired = false,
    versionBounds = "1.0.0",
    channels = {"SpearMod"}
)
public class SpearAddon
{
    @Instance("SpearMod")
    public static SpearAddon instance;
    @SidedProxy(
        clientSide = "net.minecraft.client.addon.spearaddon.ClientProxy",
        serverSide = "net.minecraft.client.addon.spearaddon.CommonProxy"
    )
    public static CommonProxy proxy;
    public static Item HeroSpear;
    public static Entity Bullet;
    public static Item DemonSpear;
    public static Item shore;
    public static Item Usums;

    @Init
    public void load(FMLInitializationEvent event)
    {
        instance = this;
        HeroSpear = (new HeroSpearItem(6322)).setUnlocalizedName("HeroSpear");
        DemonSpear = (new DemonSpearItem(6323)).setUnlocalizedName("DemonSpear");
        Usums = (new SumSpearItem(6324)).setUnlocalizedName("SumSpear");
        shore = (new ShurikenShotItem(6325)).setUnlocalizedName("Surikenshot");
        LanguageRegistry.addName(new ItemStack(DemonSpear), "Демон копьё");
        EntityRegistry.registerModEntity(DemonSpearEntity.class, "EntityShadowSpear", 136, this, 64, 20, true);
        LanguageRegistry.addName(new ItemStack(HeroSpear), "Копьё героя");
        EntityRegistry.registerModEntity(HeroSpearEntity.class, "EntityUltSpear", 137, this, 64, 20, true);
        LanguageRegistry.addName(new ItemStack(Usums), "Ультима сумрачное копьё");
        EntityRegistry.registerModEntity(SumSpearEntity.class, "EntitySumSpear", 138, this, 64, 20, true);
        LanguageRegistry.addName(new ItemStack(shore), "Сюрикен-шот");
        EntityRegistry.registerModEntity(ShurikenEntity.class, "EntitySuriken", 140, this, 64, 20, true);
        proxy.registerRenderInformation();
        boolean flag = false;

        if (flag && isClient())
        {
            this.lavautils();
        }
    }

    public static boolean isClient()
    {
        return FMLCommonHandler.instance().getEffectiveSide().isClient();
    }

    private void lavautils()
    {
        instance = null;
        proxy = null;
    }
}

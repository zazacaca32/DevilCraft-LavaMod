package net.minecraft.client.addon.Tchat;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;

@Mod(
    modid = "palkarg",
    name = "palkarg",
    version = "0.0.1"
)
@NetworkMod(
    clientSideRequired = true,
    serverSideRequired = false
)
public class palkaPrivate
{
    private static Configuration config;
    public static Item palka;
    public static Integer ItemIdpalka;
    public static String palkacommnd;

    @Init
    public void load(FMLInitializationEvent event)
    {
        palka = (new classpalkaPrivate(ItemIdpalka.intValue())).setCreativeTab(LavaChestPlate.tabArmor).setUnlocalizedName("palka");
        LanguageRegistry.addName(palka, "Регион инфо");
    }

    @PreInit
    public void init(FMLPreInitializationEvent preEvent)
    {
        config = new Configuration(preEvent.getSuggestedConfigurationFile());

        try
        {
            config.load();
            ItemIdpalka = Integer.valueOf(config.getItem("LavaBow", 20614).getInt());
            palkacommnd = config.get("Bowsettings", "damage", "/rg info").getString();
            config.save();
        }
        catch (Exception var3)
        {
            ;
        }
    }
}

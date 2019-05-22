package net.minecraft.client.addon.Tchat;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.client.addon.Tchat.packets.PacketHandlerMA;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;

@Mod(
    modid = "bowlava",
    name = "bowlava",
    version = "0.0.1",
    certificateFingerprint = "e43c2b0c74740c469df9c798fecd85541291695e81c34f3e0edd1a269f8da542"
)
@NetworkMod(
    clientSideRequired = true,
    serverSideRequired = false,
    channels = {"lm"},
    packetHandler = PacketHandlerMA.class
)
public class lavabow
{
    private static Configuration config;
    @SidedProxy(
        clientSide = "net.minecraft.client.addon.Tchat.ClientProxy",
        serverSide = "net.minecraft.client.addon.Tchat.CommonProxy"
    )
    public static CommonProxy proxy;
    public static Item lbow;
    public static Item lswordLava;
    public static Item lsword;
    public static Integer DamageBow;
    public static Integer ItemIdBow;
    public static Integer LifeBow;
    public static Item lswordRB;
    public static EnumToolMaterial TutMATERIAL = EnumHelper.addToolMaterial("TutMATERIAL", 2, 0, 7.0F, 30, 9);

    @Init
    public void load(FMLInitializationEvent event)
    {
        lbow = (new lbow(ItemIdBow.intValue())).setCreativeTab(LavaChestPlate.tabArmor).setUnlocalizedName("lbow");
        lsword = (new lsword(20714, TutMATERIAL)).setUnlocalizedName("lsword");
        lswordRB = (new lswordRB(20713, TutMATERIAL)).setUnlocalizedName("lswordRB");
        lswordLava = (new lswordLava(20712, TutMATERIAL)).setUnlocalizedName("lswordLava");
        LanguageRegistry.addName(lbow, "Лава лук");
        LanguageRegistry.addName(lsword, "Ультима меч");
        LanguageRegistry.addName(lswordRB, "РБ меч");
        LanguageRegistry.addName(lswordLava, "Лава меч");
        ItemStack d1 = new ItemStack(194, 1, 2);
        GameRegistry.addRecipe(new ItemStack(lbow, 1), new Object[] {"#  ", "#  ", "###", '#', d1});
        GameRegistry.addRecipe(new ItemStack(lswordLava, 1), new Object[] {" # ", " # ", "###", '#', d1});
        proxy.registerRenderers();
    }

    @PreInit
    public void init(FMLPreInitializationEvent preEvent)
    {
        config = new Configuration(preEvent.getSuggestedConfigurationFile());

        try
        {
            config.load();
            ItemIdBow = Integer.valueOf(config.getItem("LavaBow", 20613).getInt());
            DamageBow = Integer.valueOf(config.get("Bowsettings", "damage", 150).getInt());
            LifeBow = Integer.valueOf(config.get("Bowsettings", "Life", 100).getInt());
            config.save();
        }
        catch (Exception var3)
        {
            ;
        }
    }
}

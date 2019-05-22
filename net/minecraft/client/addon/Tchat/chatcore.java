package net.minecraft.client.addon.Tchat;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.item.Item;

@Mod(
    modid = "Many",
    name = "Many",
    version = "0.0.1"
)
public class chatcore
{
    public static Item bMany;
    public static Item sMany;
    public static Item gMany;
    public static Item dMany;
    public static Item DeluxeItem;
    public static Item UltimaItem;
    public static Item premiumItem;
    public static Item DItem;
    public static Item UItem;
    public static Item PItem;

    @Init
    public void load(FMLInitializationEvent event)
    {
        bMany = (new bMany(20603, 64)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("bMany");
        LanguageRegistry.addName(bMany, "Бронзовая монета");
        sMany = (new sMany(20604, 64)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("sMany");
        LanguageRegistry.addName(sMany, "Серебрянная монета");
        gMany = (new gMany(20605, 64)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("gMany");
        LanguageRegistry.addName(gMany, "Золотая монета");
        dMany = (new dMany(20606, 64)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("dMany");
        LanguageRegistry.addName(dMany, "Алмазная монета");
        premiumItem = (new premiumItem(20715, 1)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("premiumItem");
        LanguageRegistry.addName(premiumItem, "§cВолшебный свиток");
        DeluxeItem = (new DeluxeItem(20716, 1)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("DeluxeItem");
        LanguageRegistry.addName(DeluxeItem, "Фиолетовое ведро");
        UltimaItem = (new UltimaItem(20717, 1)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("UltimaItem");
        LanguageRegistry.addName(UltimaItem, "Красное ведро");
        PItem = (new PItem(20718, 1)).setCreativeTab(LavaChestPlate.tabItemss).setUnlocalizedName("CreativeItem");
        LanguageRegistry.addName(PItem, "Утилизатор");
    }

    @PreInit
    public void init(FMLPreInitializationEvent preEvent)
    {
    }
}

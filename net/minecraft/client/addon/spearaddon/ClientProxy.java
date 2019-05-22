package net.minecraft.client.addon.spearaddon;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.addon.spearaddon.bow.DemonSpearEntity;
import net.minecraft.client.addon.spearaddon.bow.DemonSpearEntityRender;
import net.minecraft.client.addon.spearaddon.bow.DemonSpearRender;
import net.minecraft.client.addon.spearaddon.bow.HeroSpearEntity;
import net.minecraft.client.addon.spearaddon.bow.HeroSpearEntityRender;
import net.minecraft.client.addon.spearaddon.bow.HeroSpearRender;
import net.minecraft.client.addon.spearaddon.bow.ShurikenEntity;
import net.minecraft.client.addon.spearaddon.bow.ShurikenEntityRender;
import net.minecraft.client.addon.spearaddon.bow.ShurikenShotRender;
import net.minecraft.client.addon.spearaddon.bow.SumSpearEntity;
import net.minecraft.client.addon.spearaddon.bow.SumSpearEntityRender;
import net.minecraft.client.addon.spearaddon.bow.SumSpearRender;
import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
    public static final ModelBase[] Models = new ModelBase[13];

    public void registerRenderInformation()
    {
        MinecraftForgeClient.registerItemRenderer(SpearAddon.HeroSpear.itemID, new HeroSpearRender());
        RenderingRegistry.registerEntityRenderingHandler(HeroSpearEntity.class, new HeroSpearEntityRender());
        MinecraftForgeClient.registerItemRenderer(SpearAddon.DemonSpear.itemID, new DemonSpearRender());
        RenderingRegistry.registerEntityRenderingHandler(DemonSpearEntity.class, new DemonSpearEntityRender());
        MinecraftForgeClient.registerItemRenderer(SpearAddon.Usums.itemID, new SumSpearRender());
        RenderingRegistry.registerEntityRenderingHandler(SumSpearEntity.class, new SumSpearEntityRender());
        MinecraftForgeClient.registerItemRenderer(SpearAddon.shore.itemID, new ShurikenShotRender());
        RenderingRegistry.registerEntityRenderingHandler(ShurikenEntity.class, new ShurikenEntityRender());
    }
}

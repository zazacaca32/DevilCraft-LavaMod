package net.minecraft.client.addon.lavamobs;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.lavamobs.block.BlockDeatRender;
import net.minecraft.client.addon.lavamobs.block.BlockDemonTrader;
import net.minecraft.client.addon.lavamobs.block.BlockRaidRender;
import net.minecraft.client.addon.lavamobs.bow.LavaBowRender;
import net.minecraft.client.addon.lavamobs.bow.LavaRaidBowRender;
import net.minecraft.client.addon.lavamobs.bow.LavaUltimaBowRender;
import net.minecraft.client.addon.lavamobs.entity.EntityCrazyMonkey;
import net.minecraft.client.addon.lavamobs.entity.EntityDemon;
import net.minecraft.client.addon.lavamobs.entity.EntityDendroid;
import net.minecraft.client.addon.lavamobs.entity.EntityFlySkeleton;
import net.minecraft.client.addon.lavamobs.entity.EntityGorilla;
import net.minecraft.client.addon.lavamobs.entity.EntityGuardRaid;
import net.minecraft.client.addon.lavamobs.entity.EntityHamster;
import net.minecraft.client.addon.lavamobs.entity.EntityMobGoblin;
import net.minecraft.client.addon.lavamobs.entity.EntityRaidBoss;
import net.minecraft.client.addon.lavamobs.entity.EntityShadowOfDeath;
import net.minecraft.client.addon.lavamobs.entity.EntitySpiderPrison;
import net.minecraft.client.addon.lavamobs.entity.EntityWolfPrison;
import net.minecraft.client.addon.lavamobs.entity.LavaEntityMob;
import net.minecraft.client.addon.lavamobs.gui.GuiLavaTotem;
import net.minecraft.client.addon.lavamobs.gui.GuiLavaTotemEnd;
import net.minecraft.client.addon.lavamobs.model.ModelCrazyMonkey;
import net.minecraft.client.addon.lavamobs.model.ModelDemon;
import net.minecraft.client.addon.lavamobs.model.ModelDendroid;
import net.minecraft.client.addon.lavamobs.model.ModelFireDemon;
import net.minecraft.client.addon.lavamobs.model.ModelFlySkeleton;
import net.minecraft.client.addon.lavamobs.model.ModelGoblin;
import net.minecraft.client.addon.lavamobs.model.ModelGorilla;
import net.minecraft.client.addon.lavamobs.model.ModelGuardRaid;
import net.minecraft.client.addon.lavamobs.model.ModelHamster;
import net.minecraft.client.addon.lavamobs.model.ModelRaidBoss;
import net.minecraft.client.addon.lavamobs.model.ModelShadowOfDeath;
import net.minecraft.client.addon.lavamobs.model.ModelSpiderPrison;
import net.minecraft.client.addon.lavamobs.model.ModelWolfPrison;
import net.minecraft.client.addon.lavamobs.render.BlockLavaTotemRender;
import net.minecraft.client.addon.lavamobs.render.BlockScarecrowRender;
import net.minecraft.client.addon.lavamobs.render.FireDemonRender;
import net.minecraft.client.addon.lavamobs.render.LavaPortalRender;
import net.minecraft.client.addon.lavamobs.render.RenderCrazyMonkey;
import net.minecraft.client.addon.lavamobs.render.RenderDemon;
import net.minecraft.client.addon.lavamobs.render.RenderDendroid;
import net.minecraft.client.addon.lavamobs.render.RenderFlySkeleton;
import net.minecraft.client.addon.lavamobs.render.RenderGorillaPrison;
import net.minecraft.client.addon.lavamobs.render.RenderGuardRaid;
import net.minecraft.client.addon.lavamobs.render.RenderHamster;
import net.minecraft.client.addon.lavamobs.render.RenderMobGoblin;
import net.minecraft.client.addon.lavamobs.render.RenderRaidBoss;
import net.minecraft.client.addon.lavamobs.render.RenderShadowOfDeath;
import net.minecraft.client.addon.lavamobs.render.RenderSpiderPrison;
import net.minecraft.client.addon.lavamobs.render.RenderWolfPrison;
import net.minecraft.client.addon.lavamobs.spiner.LavaSpinerRender;
import net.minecraft.client.addon.lavamobs.tile.TileBlockDeat;
import net.minecraft.client.addon.lavamobs.tile.TileBlockLavaTotem;
import net.minecraft.client.addon.lavamobs.tile.TileBlockRaid;
import net.minecraft.client.addon.lavamobs.tile.TileBlockScarecrow;
import net.minecraft.client.addon.lavamobs.tile.TileLavaPortal;
import net.minecraft.client.addon.tco.tiny.entity.EntityMan;
import net.minecraft.client.addon.tco.tiny.entity.RenderMan;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
    Minecraft mc;

    public void registerRenderInformation()
    {
        this.mc = Minecraft.getMinecraft();
        RenderingRegistry.registerEntityRenderingHandler(BlockDemonTrader.class, new FireDemonRender(new ModelFireDemon(), 0.3F));
       
        MinecraftForgeClient.registerItemRenderer(LavaModMobs.TotemBlock.blockID, new BlockLavaTotemRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileLavaPortal.class, new LavaPortalRender());
        MinecraftForgeClient.registerItemRenderer(LavaModMobs.LavaPortalBlock.blockID, new LavaPortalRender());
        RenderingRegistry.registerEntityRenderingHandler(EntityDemon.class, new RenderDemon(new ModelDemon(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(EntityHamster.class, new RenderHamster(new ModelHamster(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(EntityMobGoblin.class, new RenderMobGoblin(new ModelGoblin(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(EntityRaidBoss.class, new RenderRaidBoss(new ModelRaidBoss(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGuardRaid.class, new RenderGuardRaid(new ModelGuardRaid(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(EntityWolfPrison.class, new RenderWolfPrison(new ModelWolfPrison(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(EntitySpiderPrison.class, new RenderSpiderPrison(new ModelSpiderPrison(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGorilla.class, new RenderGorillaPrison(new ModelGorilla(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(EntityDendroid.class, new RenderDendroid(new ModelDendroid(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(EntityShadowOfDeath.class, new RenderShadowOfDeath(new ModelShadowOfDeath(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(EntityFlySkeleton.class, new RenderFlySkeleton(new ModelFlySkeleton(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(EntityCrazyMonkey.class, new RenderCrazyMonkey(new ModelCrazyMonkey(), 0.2F));
        ClientRegistry.bindTileEntitySpecialRenderer(TileBlockRaid.class, new BlockRaidRender());
        MinecraftForgeClient.registerItemRenderer(LavaModMobs.RaidBossBlock.blockID, new BlockRaidRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileBlockDeat.class, new BlockDeatRender());
        MinecraftForgeClient.registerItemRenderer(LavaModMobs.ShadowOfDeathBlock.blockID, new BlockDeatRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileBlockScarecrow.class, new BlockScarecrowRender());
        MinecraftForgeClient.registerItemRenderer(LavaModMobs.ScarecrowBlock.blockID, new BlockScarecrowRender());
        MinecraftForgeClient.registerItemRenderer(LavaModMobs.LavaLBowItem.itemID, new LavaBowRender());
        MinecraftForgeClient.registerItemRenderer(LavaModMobs.LavaUltBowItem.itemID, new LavaUltimaBowRender());
        MinecraftForgeClient.registerItemRenderer(LavaModMobs.LavaBowItem.itemID, new LavaRaidBowRender());
        MinecraftForgeClient.registerItemRenderer(LavaModMobs.LavaSpinerItem.itemID, new LavaSpinerRender());
    }

    public void handleupdatebyte(int entityId)
    {
        Entity entity = this.getEntityByID(entityId);

        if (entity != null)
        {
            LavaEntityMob entityliving = (LavaEntityMob)entity;
            entityliving.swingMob();
        }
    }

    private Entity getEntityByID(int par1)
    {
        return (Entity)(par1 == this.mc.thePlayer.entityId ? this.mc.thePlayer : this.mc.theWorld.getEntityByID(par1));
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (tileEntity != null)
        {
            switch (ID)
            {
                case 520:
                    if (tileEntity instanceof TileBlockRaid)
                    {
                        return new GuiBlockRaid((TileBlockRaid)tileEntity, player);
                    }

                case 521:
                case 522:
                default:
                    break;

                case 523:
                    if (tileEntity instanceof TileBlockLavaTotem)
                    {
                        return new GuiLavaTotem((TileBlockLavaTotem)tileEntity, player);
                    }

                    break;

                case 524:
                    if (tileEntity instanceof TileBlockLavaTotem)
                    {
                        return new GuiLavaTotemEnd((TileBlockLavaTotem)tileEntity, player);
                    }
            }
        }

        return null;
    }
}

package net.minecraft.client.addon.lavablock;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.addon.lavablock.Gui.GuiElka;
import net.minecraft.client.addon.lavablock.Gui.GuiThrone;
import net.minecraft.client.addon.lavablock.Render.ArmorStandRender;
import net.minecraft.client.addon.lavablock.Render.BlockColorPaneRender;
import net.minecraft.client.addon.lavablock.Render.BomjShRender;
import net.minecraft.client.addon.lavablock.Render.CarbonItemRender;
import net.minecraft.client.addon.lavablock.Render.ColumnBlockRender;
import net.minecraft.client.addon.lavablock.Render.DivanBlockRender;
import net.minecraft.client.addon.lavablock.Render.ElkaRender;
import net.minecraft.client.addon.lavablock.Render.FlamBeauRender;
import net.minecraft.client.addon.lavablock.Render.GateBlockRender;
import net.minecraft.client.addon.lavablock.Render.GatelockBlockRender;
import net.minecraft.client.addon.lavablock.Render.JacklampRender;
import net.minecraft.client.addon.lavablock.Render.LightFloorRender;
import net.minecraft.client.addon.lavablock.Render.LockBlockRender;
import net.minecraft.client.addon.lavablock.Render.PrizentRender;
import net.minecraft.client.addon.lavablock.Render.StatuyBlockRender;
import net.minecraft.client.addon.lavablock.Render.ThroneRender;
import net.minecraft.client.addon.lavablock.Render.VenokRender;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
    public void registerRenderInformation()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(StatuyTileBlock.class, new StatuyBlockRender());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.StatuyBlock.blockID, new StatuyBlockRender());
        ClientRegistry.bindTileEntitySpecialRenderer(DivanTileBlock.class, new DivanBlockRender());
        ClientRegistry.bindTileEntitySpecialRenderer(GatelockTileBlock.class, new GatelockBlockRender());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.GatelockBlock.blockID, new GatelockBlockRender());
        ClientRegistry.bindTileEntitySpecialRenderer(GateTileBlock.class, new GateBlockRender());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.GateBlock.blockID, new GateBlockRender());
        ClientRegistry.bindTileEntitySpecialRenderer(ThroneTileBlock.class, new ThroneRender());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.ThroneBlock.blockID, new ThroneRender());
        ClientRegistry.bindTileEntitySpecialRenderer(JacklampTileBlock.class, new JacklampRender());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.JacklampBlock.blockID, new JacklampRender());
        ModBlocks.render_id = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(ModBlocks.render_id, new BlockColorPaneRender());
        ClientRegistry.bindTileEntitySpecialRenderer(ElkaTileBlock.class, new ElkaRender());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.ElkaBlock.blockID, new ElkaRender());
        ClientRegistry.bindTileEntitySpecialRenderer(PrizentTileBlock.class, new PrizentRender());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.PrizentBlock.blockID, new PrizentRender());
        ClientRegistry.bindTileEntitySpecialRenderer(VenokTileBlock.class, new VenokRender());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.VenokBlock.blockID, new VenokRender());
        ClientRegistry.bindTileEntitySpecialRenderer(BomjshTileBlock.class, new BomjShRender());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.BomjshBlock.blockID, new BomjShRender());
        ClientRegistry.bindTileEntitySpecialRenderer(LockTileBlock.class, new LockBlockRender());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.LockBlock.blockID, new LockBlockRender());
        ClientRegistry.bindTileEntitySpecialRenderer(LightFloorTileBlock.class, new LightFloorRender());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.LightFloorBlock.blockID, new LightFloorRender());
        ClientRegistry.bindTileEntitySpecialRenderer(FlameBeauTile.class, new FlamBeauRender());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.FlamBeauBlock.blockID, new FlamBeauRender());
        ClientRegistry.bindTileEntitySpecialRenderer(ColumnTileBlock.class, new ColumnBlockRender());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.ColumnBlockW.blockID, new ColumnBlockRender());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.ColumnBlockB.blockID, new ColumnBlockRender());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.ColumnBlockG.blockID, new ColumnBlockRender());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.ColumnBlockD.blockID, new ColumnBlockRender());
        ClientRegistry.bindTileEntitySpecialRenderer(ArmorStandTile.class, new ArmorStandRender());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.ArmorStand.blockID, new ArmorStandRender());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.LavaCarbonItem.itemID, new CarbonItemRender());
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity;

        switch (ID)
        {
            case 500:
                tileEntity = world.getBlockTileEntity(x, y, z);

                if (tileEntity != null && tileEntity instanceof ThroneTileBlock)
                {
                    return new GuiThrone((ThroneTileBlock)tileEntity);
                }

                break;

            case 501:
                tileEntity = world.getBlockTileEntity(x, y, z);

                if (tileEntity != null && tileEntity instanceof ElkaTileBlock)
                {
                    return new GuiElka((ElkaTileBlock)tileEntity, player);
                }
        }

        return null;
    }
}

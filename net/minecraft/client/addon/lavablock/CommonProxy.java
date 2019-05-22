package net.minecraft.client.addon.lavablock;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.addon.lavablock.Container.ContainerElka;
import net.minecraft.client.addon.lavablock.Container.ContainerThrone;
import net.minecraft.client.addon.lavablock.Tile.ElkaTileBlock;
import net.minecraft.client.addon.lavablock.Tile.ThroneTileBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler
{
    public void registerRenderInformation()
    {
    }

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (tileEntity != null)
        {
            switch (ID)
            {
                case 500:
                    if (tileEntity instanceof ThroneTileBlock)
                    {
                        return new ContainerThrone((ThroneTileBlock)tileEntity);
                    }

                    break;

                case 501:
                    if (tileEntity instanceof ElkaTileBlock)
                    {
                        return new ContainerElka((ElkaTileBlock)tileEntity, player);
                    }
            }
        }

        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }
}

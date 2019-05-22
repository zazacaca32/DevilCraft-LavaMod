package net.minecraft.client.addon.lavamobs;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.addon.lavamobs.tile.TileBlockRaid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler
{
    public void registerRenderInformation()
    {
    }

    public void handleupdatebyte(int entityId)
    {
    }

    private Entity getEntityByID(int par1)
    {
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (tileEntity != null)
        {
            switch (ID)
            {
                case 520:
                    if (tileEntity instanceof TileBlockRaid)
                    {
                        return new ContainerBlockRaid((TileBlockRaid)tileEntity);
                    }
            }
        }

        return null;
    }
}

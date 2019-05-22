package net.minecraft.client.addon.lavablock.Container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.lavablock.Tile.ThroneTileBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;

public class ContainerThrone extends Container
{
    public ThroneTileBlock tileEntity;
    private boolean dualisWorking;
    private int dualprocess_status;

    public ContainerThrone(ThroneTileBlock tileEntity)
    {
        this.tileEntity = tileEntity;
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return this.tileEntity.isUseableByPlayer(entityplayer);
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.tileEntity.isWorking ? 1 : 0);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.tileEntity.process_status);

        if (!this.tileEntity.worldObj.isRemote && par1ICrafting instanceof EntityPlayer)
        {
            this.tileEntity.klplayer = (EntityPlayer)par1ICrafting;
        }
    }

    public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
    {
        this.tileEntity.process_status = 0;
        this.tileEntity.isWorking = false;
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < super.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)super.crafters.get(i);

            if (this.dualisWorking != this.tileEntity.isWorking)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntity.isWorking ? 1 : 0);
            }

            if (this.dualprocess_status != this.tileEntity.process_status)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntity.process_status);
            }
        }

        this.dualisWorking = this.tileEntity.isWorking;
        this.dualprocess_status = this.tileEntity.process_status;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.tileEntity.isWorking = par2 > 0;
        }

        if (par1 == 1)
        {
            this.tileEntity.process_status = par2;
        }
    }
}

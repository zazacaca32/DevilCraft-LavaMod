package net.minecraft.client.addon.lavablock.Container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.lavablock.Slot.SlotElka;
import net.minecraft.client.addon.lavablock.Tile.ElkaTileBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerElka extends Container
{
    public ElkaTileBlock tileEntity;
    private int dualcount_b;
    private int dualcount_o;
    private int dualcount_p;
    private int dualcount_time;

    public ContainerElka(ElkaTileBlock tileEntity, EntityPlayer player)
    {
        this.tileEntity = tileEntity;
        this.addSlotToContainer(new SlotElka(tileEntity, 0, 80, 42));
        this.bindPlayerInventory(player.inventory);
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return this.tileEntity.isUseableByPlayer(entityplayer);
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer)
    {
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 127 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 185));
        }
    }

    public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
    {
    }

    public ItemStack slotClick(int slotNum, int mouseClick, int holdingShift, EntityPlayer p)
    {
        if (slotNum >= 0 && slotNum < super.inventorySlots.size())
        {
            Slot clickSlot = (Slot)super.inventorySlots.get(slotNum);

            if (clickSlot instanceof SlotElka)
            {
                SlotElka mes = (SlotElka)clickSlot;
                ItemStack held = p.inventory.getItemStack();

                if (held != null)
                {
                    if (mes.isItemValid(held))
                    {
                        ItemStack f;

                        if (mouseClick > 0)
                        {
                            f = held.copy();
                            f.stackSize = 1;
                            f = mes.addToInv(f);

                            if (f.stackSize <= 0)
                            {
                                f.stackSize = held.stackSize - 1;

                                if (f.stackSize <= 0)
                                {
                                    f = null;
                                }

                                p.inventory.setItemStack(f);
                            }
                        }
                        else
                        {
                            f = mes.addToInv(held);

                            if (f.stackSize <= 0)
                            {
                                p.inventory.setItemStack((ItemStack)null);
                            }
                            else
                            {
                                p.inventory.setItemStack(f);
                            }
                        }
                    }
                }
                else if (mouseClick > 0)
                {
                    mes.decrStackSize(1);
                }
                else
                {
                    p.inventory.setItemStack((ItemStack)null);
                }

                return null;
            }
        }

        return super.slotClick(slotNum, mouseClick, holdingShift, p);
    }

    public final ItemStack transferStackInSlot(EntityPlayer p, int idx)
    {
        return null;
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 3, this.tileEntity.time_s);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.tileEntity.count_b);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.tileEntity.count_o);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.tileEntity.count_p);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < super.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)super.crafters.get(i);

            if (this.dualcount_b != this.tileEntity.count_b)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileEntity.count_b);
            }
            else if (this.dualcount_o != this.tileEntity.count_o)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntity.count_o);
            }
            else if (this.dualcount_p != this.tileEntity.count_p)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntity.count_p);
            }
            else if (this.dualcount_time != this.tileEntity.time_s)
            {
                icrafting.sendProgressBarUpdate(this, 3, this.tileEntity.time_s);
            }
        }

        this.dualcount_b = this.tileEntity.count_b;
        this.dualcount_o = this.tileEntity.count_o;
        this.dualcount_p = this.tileEntity.count_p;
        this.dualcount_time = this.tileEntity.time_s;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 3)
        {
            this.tileEntity.time_s = par2;
        }

        if (par1 == 2)
        {
            this.tileEntity.count_b = par2;
        }

        if (par1 == 1)
        {
            this.tileEntity.count_o = par2;
        }

        if (par1 == 0)
        {
            this.tileEntity.count_p = par2;
        }
    }
}

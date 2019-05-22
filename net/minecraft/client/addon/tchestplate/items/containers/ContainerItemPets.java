package net.minecraft.client.addon.tchestplate.items.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInvOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ContainerItemPets extends Container
{
    EntityPlayer player;
    private World worldObj;
    private int posX;
    private int posY;
    private int posZ;
    public InventoryItemPets input = new InventoryItemPets(this);
    ItemStack armor = null;
    private int blockSlot;
    private short dualtime_tuman;
    private long time_long_tuman;
    public short time_tuman;
    public int sp;
    public int lvl;
    public int duallvl;
    private int double_sp;
    public static Container d;

    public ContainerItemPets(InventoryPlayer iinventory, World world, int x, int y, int z, ExtendedPlayer extendedPlayer)
    {
        this.worldObj = world;
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.player = iinventory.player;
        this.armor = iinventory.getCurrentItem();
        this.blockSlot = iinventory.currentItem + 28;
        NBTTagCompound decode;

        if (this.armor != null)
        {
            decode = Utils.getOrCreateNbtData(this.armor);
            this.time_long_tuman = decode.getLong("ptime");
            this.sp = decode.getInteger("psp");
            this.lvl = decode.getByte("plvl");

            if (this.time_long_tuman > 0L)
            {
                this.time_long_tuman /= 1000L;
            }
        }

        decode = Utils.getOrCreateNbtData(this.armor);
        byte[] var10000 = new byte[] {(byte)1, (byte)0, (byte)3, (byte)5, (byte)6, (byte)7, (byte)9, (byte)1, (byte)2, (byte)4, (byte)5, (byte)6, (byte)7, (byte)8, (byte)5};
        SlotInvOutput slot = new SlotInvOutput(this.input, 0, 57, 47);
        this.addSlotToContainer(slot);
        int item = decode.getInteger("tag1");

        if (this.armor.getItemDamage() == 8)
        {
            slot.inventory.setInventorySlotContents(0, new ItemStack(LavaChestPlate.itemCrystalCobalt));

            if (!world.isRemote)
            {
                ;
            }
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slot)
    {
        return null;
    }

    public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer)
    {
        if (par1 == this.blockSlot)
        {
            return null;
        }
        else
        {
            InventoryPlayer inventoryplayer = par4EntityPlayer.inventory;
            return par1 == 0 && !this.input.isStackValidForSlot(par1, inventoryplayer.getItemStack()) && (par1 != 0 || inventoryplayer.getItemStack() != null) ? null : super.slotClick(par1, par2, par3, par4EntityPlayer);
        }
    }

    public void putStackInSlot(int par1, ItemStack par2ItemStack)
    {
        if (this.input.isStackValidForSlot(par1, par2ItemStack))
        {
            super.putStackInSlot(par1, par2ItemStack);
        }
    }

    public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
    {
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return true;
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.time_tuman);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.sp);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.lvl);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        long timetum = System.currentTimeMillis() / 1000L;

        if (this.time_long_tuman > timetum)
        {
            this.time_tuman = (short)((int)(this.time_long_tuman - timetum));
        }
        else
        {
            this.time_tuman = 0;
        }

        for (int i = 0; i < super.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)super.crafters.get(i);

            if (this.dualtime_tuman != this.time_tuman)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.time_tuman);
            }

            if (this.double_sp != this.sp)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.sp);
            }

            if (this.duallvl != this.lvl)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.lvl);
            }
        }

        this.dualtime_tuman = this.time_tuman;
        this.double_sp = this.sp;
        this.duallvl = this.lvl;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.time_tuman = (short)par2;
        }

        if (par1 == 1)
        {
            this.sp = par2;
        }

        if (par1 == 2)
        {
            this.lvl = par2;
        }
    }
}

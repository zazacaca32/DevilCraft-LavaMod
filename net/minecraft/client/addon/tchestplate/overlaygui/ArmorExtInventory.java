package net.minecraft.client.addon.tchestplate.overlaygui;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.PlayerDonateEvent;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.donate.api.IDonateUser;
import net.minecraft.client.addon.tchestplate.donate.api.IOffert;
import net.minecraft.client.addon.tchestplate.donate.api.IOffertTraderCoin;
import net.minecraft.client.addon.tchestplate.packets.PacketMASendItemStackInSlot;
import net.minecraft.client.addon.tchestplate.packets.PacketMAUpdateInt;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;

public class ArmorExtInventory implements IInventory, IDonateUser
{
    public ItemStack[] inventory = new ItemStack[7];
    public ItemStack[] doubleinventory = new ItemStack[7];
    public ItemStack Coupon;
    public ItemStack[] invOfferts = new ItemStack[5];
    public int statusDonateOffert = 0;
    public int coin = 0;
    public EntityPlayer player;
    IOffert offert;

    public ArmorExtInventory(EntityPlayer player)
    {
        this.player = player;
    }

    public int getSizeInventory()
    {
        return this.inventory.length;
    }

    public boolean isStackInSlot(int slot)
    {
        return this.inventory[slot] != null;
    }

    public ItemStack getStackInSlot(int slot)
    {
        return this.inventory[slot];
    }

    public ItemStack decrStackSize(int slot, int quantity)
    {
        if (this.inventory[slot] != null)
        {
            ItemStack split;

            if (this.inventory[slot].stackSize <= quantity)
            {
                split = this.inventory[slot];
                this.inventory[slot] = null;
                return split;
            }
            else
            {
                split = this.inventory[slot].splitStack(quantity);

                if (this.inventory[slot].stackSize == 0)
                {
                    this.inventory[slot] = null;
                }

                return split;
            }
        }
        else
        {
            return null;
        }
    }

    public ItemStack getStackInSlotOnClosing(int slot)
    {
        return null;
    }

    public void setInventorySlotContents(int slot, ItemStack itemstack)
    {
        this.inventory[slot] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }

    public String getInvName()
    {
        return "";
    }

    public boolean isInvNameLocalized()
    {
        return false;
    }

    public int getInventoryStackLimit()
    {
        return 1;
    }

    public void onInventoryChanged()
    {
        if (!this.player.worldObj.isRemote && this.player instanceof EntityPlayerMP)
        {
            for (int i = 0; i < this.getSizeInventory(); ++i)
            {
                if (!ItemStack.areItemStacksEqual(this.inventory[i], this.doubleinventory[i]))
                {
                    this.doubleinventory[i] = ItemStack.copyItemStack(this.inventory[i]);
                    ((EntityPlayerMP)this.player).getServerForPlayer().getEntityTracker().sendPacketToAllAssociatedPlayers(this.player, (new PacketMASendItemStackInSlot(this.player.entityId, (byte)1, (byte)i, new Object[] {this.inventory[i]})).makePacket());
                }
            }
        }
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return true;
    }

    public void openChest()
    {
    }

    public void closeChest()
    {
    }

    public boolean isStackValidForSlot(int slot, ItemStack itemstack)
    {
        return false;
    }

    public void clearinvOfferts()
    {
        for (int i = 0; i < this.invOfferts.length; ++i)
        {
            this.invOfferts[i] = null;
        }
    }

    public void saveToNBT(NBTTagCompound tags)
    {
        NBTTagList tagList = new NBTTagList();

        for (int var51 = 0; var51 < this.inventory.length; ++var51)
        {
            if (this.inventory[var51] != null)
            {
                NBTTagCompound invSlot = new NBTTagCompound();
                invSlot.setByte("Slot", (byte)var51);
                this.inventory[var51].writeToNBT(invSlot);
                tagList.appendTag(invSlot);
            }
        }

        tags.setTag("ArmExt.Inventory", tagList);
        NBTTagCompound var5 = new NBTTagCompound();

        if (this.Coupon != null)
        {
            this.Coupon.writeToNBT(var5);
        }

        var5.setInteger("cc", this.coin);
        tags.setCompoundTag("*c", var5);
    }

    public void readFromNBT(NBTTagCompound tags)
    {
        NBTTagList tagList = tags.getTagList("ArmExt.Inventory");

        for (int var71 = 0; var71 < tagList.tagCount(); ++var71)
        {
            NBTTagCompound nbttagcompound = (NBTTagCompound)tagList.tagAt(var71);
            int j = nbttagcompound.getByte("Slot") & 255;
            ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);

            if (itemstack != null)
            {
                this.inventory[j] = itemstack;
            }
        }

        NBTTagCompound var7 = tags.getCompoundTag("*c");

        if (var7 != null)
        {
            this.Coupon = ItemStack.loadItemStackFromNBT(var7);
            this.coin = var7.getInteger("cc");
        }
    }

    @SideOnly(Side.SERVER)
    public void setStatusDonate(int i)
    {
        this.statusDonateOffert = i;

        if (i == 0)
        {
            this.clearinvOfferts();
            LavaChestPlate.instanceDonate.sendUpdateStatusDonate(0, this.player);
        }

        LavaChestPlate.log.info("[LavaChestPlate] Status Offert " + this.player.getEntityName() + " ->" + i);
    }

    @SideOnly(Side.SERVER)
    public void setStatusDonateI(int i)
    {
        this.statusDonateOffert = i;
        LavaChestPlate.instanceDonate.sendUpdateStatusDonate(i, this.player);
        LavaChestPlate.log.info("[LavaChestPlate] Status Offert " + this.player.getEntityName() + " ->" + i);
    }

    @SideOnly(Side.SERVER)
    public boolean startOffert(int coin, IOffert offert)
    {
        if (this.statusDonateOffert == 0)
        {
            this.offert = null;
        }

        if (this.offert != null)
        {
            return false;
        }
        else if (this.coin <= 0)
        {
            return false;
        }
        else
        {
            this.offert = offert;
            LavaChestPlate.log.info("[LavaChestPlate] Старт списания коинов ->" + this.player.getEntityName() + "->" + offert.getClass().getCanonicalName());
            this.JobOffers(0, coin);
            LavaChestPlate.log.info("[LavaChestPlate] Процесс списания коинов ->" + this.player.getEntityName());
            PlayerDonateEvent.createTransaction(this.player.getEntityName(), -1 * coin, 10);
            return true;
        }
    }

    public int getCoin()
    {
        return this.coin;
    }

    public int getStatus()
    {
        return this.statusDonateOffert;
    }

    public void JobOffers(int donate, int getfirstDonate)
    {
        if (this.offert != null)
        {
            this.offert.JobOffer(this, donate, getfirstDonate);
        }

        int c = this.coin;
        this.coin -= getfirstDonate - donate;
        LavaChestPlate.log.info("[LavaChestPlate] Обновление коинов ->" + this.player.getEntityName() + " было->" + c + " стало:" + this.coin + " d=" + getfirstDonate + " dback=" + donate);
        PacketDispatcher.sendPacketToPlayer((new PacketMAUpdateInt((byte)4, this.coin)).makePacket(), (Player)this.player);
        PacketDispatcher.sendPacketToPlayer((new PacketMAUpdateInt((byte)5, this.statusDonateOffert)).makePacket(), (Player)this.player);
        this.offert = null;
    }

    public void setStatus(int status)
    {
        if (Utils.isClient())
        {
            this.statusDonateOffert = status;
        }
        else
        {
            this.setStatusDonateI(status);
        }
    }

    @SideOnly(Side.SERVER)
    public boolean startOffertTraderCoin(String DestName, int coin, IOffertTraderCoin offert)
    {
        if (this.statusDonateOffert == 0)
        {
            this.offert = null;
        }

        if (this.offert != null)
        {
            return false;
        }
        else if (this.coin <= 0)
        {
            return false;
        }
        else
        {
            this.offert = offert;
            LavaChestPlate.log.info("[LavaChestPlate] Старт традер коинов ->" + this.player.getEntityName() + "->" + DestName + "->" + offert.getClass().getCanonicalName());
            LavaChestPlate.log.info("[LavaChestPlate] Процесс традер списания коинов ->" + this.player.getEntityName());
            return true;
        }
    }

    public void JobOffersTraderCoin(String destPlayer, int donate, int getfirstDonate)
    {
        if (this.offert != null && this.offert instanceof IOffertTraderCoin)
        {
            ((IOffertTraderCoin)this.offert).JobOfferTraderCoin(this, destPlayer, donate, getfirstDonate);
            int c = this.coin;
            this.coin -= getfirstDonate - donate;
            LavaChestPlate.log.info("[LavaChestPlate] Обновление коинов ->" + this.player.getEntityName() + " было->" + c + " стало:" + this.coin + " d=" + getfirstDonate + " dback=" + donate);
            PacketDispatcher.sendPacketToPlayer((new PacketMAUpdateInt((byte)4, this.coin)).makePacket(), (Player)this.player);
            EntityPlayerMP Destpl;

            if (c > this.coin && (Destpl = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(destPlayer)) != null)
            {
                ArmorExtInventory dus = ExtendedPlayer.get(Destpl).inventoryExt;
                int old = dus.getCoin();
                dus.setCoin(dus.getCoin() + getfirstDonate);
                PacketDispatcher.sendPacketToPlayer((new PacketMAUpdateInt((byte)4, dus.getCoin())).makePacket(), (Player)Destpl);
                LavaChestPlate.log.info("[LavaChestPlate] Обновление коинов ->" + Destpl.getEntityName() + " было->" + old + " приход->" + getfirstDonate + " стало:" + dus.getCoin());
            }

            PacketDispatcher.sendPacketToPlayer((new PacketMAUpdateInt((byte)5, this.statusDonateOffert)).makePacket(), (Player)this.player);
        }

        this.offert = null;
    }

    @SideOnly(Side.SERVER)
    public int setCoin(int coin)
    {
        this.coin = coin;
        return this.coin;
    }

    @SideOnly(Side.SERVER)
    public void refrechCoin()
    {
    }
}

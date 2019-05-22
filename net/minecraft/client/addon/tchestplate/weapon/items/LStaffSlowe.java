package net.minecraft.client.addon.tchestplate.weapon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.Bukkit.CallEventBukkit;
import net.minecraft.client.addon.tchestplate.entities.projectile.EntityFrostBlue;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LStaffSlowe extends BaseStaffWeapon
{
    private long soundDelay;
    int max;
    int cooldown;

    public LStaffSlowe(int par1, int max, int cooldown, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1, max, cooldown, par2EnumToolMaterial);
        this.max = max;
        this.cooldown = cooldown;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        super.itemIcon = par1IconRegister.registerIcon("LavaChestPlate:lstaffslowe");
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 30;
    }

    public int getDamageVsEntity(Entity par1Entity, ItemStack itemStack)
    {
        return 1;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer p)
    {
        if (!p.worldObj.isRemote && CallEventBukkit.CallBlockCanAttackEvent(p, p))
        {
            return itemstack;
        }
        else if (super.maxCharge.intValue() > this.getCharge(itemstack).intValue())
        {
            if (!world.isRemote)
            {
                p.sendChatToPlayer("§7[Staff] §8Нельзя использовать еще " + (super.maxCharge.intValue() - this.getCharge(itemstack).intValue()) + "c.");
            }

            return itemstack;
        }
        else
        {
            p.swingItem();
            this.discharge(itemstack, 2);

            if (!world.isRemote)
            {
                world.spawnEntityInWorld(new EntityFrostBlue(world, p, this.max * 1000));
            }

            p.stopUsingItem();
            return itemstack;
        }
    }

    public boolean shouldRotateAroundWhenRendering()
    {
        return false;
    }

    public EnumAction getItemUseAction(ItemStack itemstack)
    {
        return EnumAction.bow;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        super.addInformation(par1ItemStack, par2EntityPlayer, par2List, par4);
        par2List.add("§7 Накладывает замедление на игрока");
        par2List.add("§8 Эффекты:");
        par2List.add("§8  Замедление - " + this.max + " секунд");
    }

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }

    public boolean isRepairable()
    {
        return false;
    }

    public Item setNoRepair()
    {
        super.canRepair = false;
        return this;
    }

    ItemStack CreateItem(ItemStack var1)
    {
        return var1;
    }
}

package net.minecraft.client.addon.spearaddon.bow;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class UltimaSpearItem extends BaseSpearItem
{
    private int damage = 8000;

    public UltimaSpearItem(int par1)
    {
        super(par1);
        super.maxStackSize = 1;
        this.setMaxDamage(1500);
        this.setCreativeTab(LavaChestPlate.tabArmor);
        this.setUnlocalizedName("UltimaSpear");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        super.itemIcon = par1IconRegister.registerIcon("provider:herokopie");
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 2000;
    }

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        par2List.add("§dНаносит урон 8000!");
        par2List.add("§dНе имеет износа.");
    }

    public EnumAction getItemUseAction(ItemStack itemstack)
    {
        return EnumAction.bow;
    }

    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        return par1ItemStack;
    }

    public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count)
    {
    }

    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        int j = this.getMaxItemUseDuration(par1ItemStack) - par4;
        ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
        MinecraftForge.EVENT_BUS.post(event);

        if (!event.isCanceled())
        {
            j = event.charge;
            float f = (float)j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if ((double)f >= 0.1D)
            {
                if (f > 1.0F)
                {
                    f = 1.0F;
                }

                UltimaSpearEntity entityarrow = new UltimaSpearEntity(par2World, par3EntityPlayer, f * 2.0F);
                int arrowdam = this.damage;
                entityarrow.setDamage((double)arrowdam / 2.94D);
                entityarrow.setIsCritical(false);
                par2World.playSoundAtEntity(par3EntityPlayer, "tchestplate.spearsong", 1.0F, 1.0F / (Item.itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                if (!par2World.isRemote)
                {
                    par2World.spawnEntityInWorld(entityarrow);
                }
            }
        }
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
        MinecraftForge.EVENT_BUS.post(event);

        if (event.isCanceled())
        {
            return event.result;
        }
        else
        {
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
            return par1ItemStack;
        }
    }
}

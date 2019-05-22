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

public class SumSpearItem extends BaseSpearItem
{
    public SumSpearItem(int par1)
    {
        super(par1);
        super.maxStackSize = 1;
        this.setMaxDamage(1500);
        this.setCreativeTab(LavaChestPlate.tabArmor);
        this.setUnlocalizedName("ShadowSpear");
        super.canRepair = false;
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 2000;
    }

    public int getEnchantHammer(ItemStack par1ItemStack)
    {
        return par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("sup") ? par1ItemStack.stackTagCompound.getInteger("sup") : 0;
    }

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        int sup = this.getEnchantSpear(par1ItemStack);
        par2List.add("§dНаносит урон " + super.Sum(par1ItemStack) + "!");
        par2List.add("§dНе имеет износа.");
        par2List.add("§7Улучшен на §a+" + sup);
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

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        super.itemIcon = par1IconRegister.registerIcon("provider:hellspear");
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

                SumSpearEntity entityarrow = new SumSpearEntity(par2World, par3EntityPlayer, f * 2.0F);
                int arrowdam = super.Sum(par1ItemStack);

                if (j > 80)
                {
                    entityarrow.setFire(100);
                }

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

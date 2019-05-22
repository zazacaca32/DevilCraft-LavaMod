package net.minecraft.client.addon.lavamobs.bow;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class LavaUltimaBowItem extends Item
{
    private int damage = 2100;

    public LavaUltimaBowItem(int par1)
    {
        super(par1);
        super.maxStackSize = 1;
        this.setMaxDamage(1500);
        this.setCreativeTab(CreativeTabs.tabCombat);
        this.setUnlocalizedName("lava_ultbow");
        super.canRepair = false;
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 2000;
    }

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        int plus = 0;
        int t = 0;

        if (par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("sup"))
        {
            t = par1ItemStack.stackTagCompound.getInteger("sup");
            plus = (int)((float)(this.damage / 100) * (2.5F * (float)(t + 1) * (float)(t + 2) - 5.0F));
        }

        par2List.add("§dНаносит урон " + (this.damage + plus));
        par2List.add("§dпри удержании бонус +300.");
        par2List.add("§dНе имеет износа.");
        par2List.add("§7Улучшен на §a+" + t);

        if (par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("bowstart"))
        {
            par2List.add("§aИтем Принадлежит: " + par1ItemStack.stackTagCompound.getString("bowstart"));
        }
        else
        {
            par2List.add("§aИтем Принадлежит: Никому.");
        }
    }

    public EnumAction getItemUseAction(ItemStack itemstack)
    {
        return EnumAction.bow;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
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
            boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

            if (flag || par3EntityPlayer.inventory.hasItem(Item.arrow.itemID))
            {
                float f = (float)j / 20.0F;
                f = (f * f + f * 2.0F) / 3.0F;

                if ((double)f < 0.1D)
                {
                    return;
                }

                if (f > 1.0F)
                {
                    f = 1.0F;
                }

                EntityArrow entityarrow = new EntityArrow(par2World, par3EntityPlayer, f * 2.0F);
                int arrowdam = this.damage;

                if (par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("sup"))
                {
                    int entityarrow111 = par1ItemStack.stackTagCompound.getInteger("sup");
                    arrowdam = (int)((float)(this.damage / 100) * (2.5F * (float)(entityarrow111 + 1) * (float)(entityarrow111 + 2) - 5.0F) + (float)this.damage);
                }

                if (j > 80)
                {
                    arrowdam += 300;
                }

                entityarrow.setDamage((double)arrowdam / 2.94D);
                entityarrow.setIsCritical(false);
                EntityArrow entityarrow1111 = new EntityArrow(par2World, par3EntityPlayer, f * 3.0F);
                entityarrow1111.setDamage(1.0D);
                entityarrow1111.setIsCritical(false);
                EntityArrow entityarrow2 = new EntityArrow(par2World, par3EntityPlayer, f * 1.5F);
                entityarrow2.setDamage(1.0D);
                entityarrow2.setIsCritical(false);
                par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (Item.itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                if (flag)
                {
                    entityarrow.canBePickedUp = 2;
                }
                else
                {
                    par3EntityPlayer.inventory.consumeInventoryItem(Item.arrow.itemID);
                }

                if (!par2World.isRemote)
                {
                    par2World.spawnEntityInWorld(entityarrow);

                    if (j > 80)
                    {
                        par2World.spawnEntityInWorld(entityarrow1111);
                        par2World.spawnEntityInWorld(entityarrow2);
                    }
                }
            }
        }
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par2World.isRemote && this.onItemRightClickServer(par1ItemStack, par2World, par3EntityPlayer) != null)
        {
            return par1ItemStack;
        }
        else
        {
            ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
            MinecraftForge.EVENT_BUS.post(event);

            if (event.isCanceled())
            {
                return event.result;
            }
            else
            {
                if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(Item.arrow.itemID))
                {
                    par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
                }

                return par1ItemStack;
            }
        }
    }

    public ItemStack onItemRightClickServer(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (par1ItemStack.stackTagCompound != null)
        {
            if (!par1ItemStack.stackTagCompound.hasKey("bowstart"))
            {
                par1ItemStack.stackTagCompound.setString("bowstart", par3EntityPlayer.getEntityName());
                par3EntityPlayer.sendChatToPlayer(EnumChatFormatting.GRAY + "" + EnumChatFormatting.DARK_GREEN + "Владелец установлен " + par3EntityPlayer.getEntityName() + " Менять нельзя!");
                return par1ItemStack;
            }
            else
            {
                return !par1ItemStack.stackTagCompound.hasKey("bowstart") ? par1ItemStack : (!par1ItemStack.stackTagCompound.getString("bowstart").equals(par3EntityPlayer.getEntityName()) ? par1ItemStack : null);
            }
        }
        else
        {
            par1ItemStack.stackTagCompound = new NBTTagCompound();
            par1ItemStack.stackTagCompound.setString("bowstart", par3EntityPlayer.getEntityName());
            par3EntityPlayer.sendChatToPlayer(EnumChatFormatting.GRAY + "" + EnumChatFormatting.DARK_GREEN + "Владелец " + par3EntityPlayer.getEntityName() + " Менять нельзя!");
            return par1ItemStack;
        }
    }
}

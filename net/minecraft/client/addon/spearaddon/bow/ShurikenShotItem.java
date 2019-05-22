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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ShurikenShotItem extends BaseSpearItem
{
    public ShurikenShotItem(int par1)
    {
        super(par1);
        super.maxStackSize = 1;
        this.setMaxDamage(1500);
        this.setCreativeTab(LavaChestPlate.tabArmor);
        this.setUnlocalizedName("ShurikenShot");
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
        par2List.add("§dНаносит урон " + super.Shuriken(par1ItemStack) + "!");
        par2List.add("§dНе имеет износа.");
        par2List.add("§7Улучшен на §a+" + sup);

        if (par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("lswordstartr"))
        {
            par2List.add("§aИтем Принадлежит: " + par1ItemStack.stackTagCompound.getString("lswordstartr"));
        }
        else
        {
            par2List.add("§aИтем Принадлежит: Никому.");
        }
    }
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        super.itemIcon = par1IconRegister.registerIcon("Alo:lfireswordt");
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
        if (par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("lswordstartr") && par1ItemStack.stackTagCompound.getString("lswordstartr").equals(par3EntityPlayer.getEntityName()))
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

                    ShurikenEntity entityarrow = new ShurikenEntity(par2World, par3EntityPlayer, f * 2.0F);
                    int arrowdam = super.Shuriken(par1ItemStack);

                    if (j > 70)
                    {
                        arrowdam += 400;
                    }

                    if (j > 80)
                    {
                        arrowdam += 600;
                    }

                    entityarrow.setDamage((double)arrowdam / 2.94D);
                    entityarrow.setIsCritical(false);
                    par2World.playSoundAtEntity(par3EntityPlayer, "tchestplate.shuriken", 1.0F, 1.0F / (Item.itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                    if (!par2World.isRemote)
                    {
                        par2World.spawnEntityInWorld(entityarrow);
                    }
                }
            }
        }
        else
        {
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.poison.id, 200));
        }
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par2World.isRemote)
        {
            if (par1ItemStack.stackTagCompound != null)
            {
                if (!par1ItemStack.stackTagCompound.hasKey("lswordstartr"))
                {
                    par1ItemStack.stackTagCompound.setString("lswordstartr", par3EntityPlayer.getEntityName());
                    par3EntityPlayer.sendChatToPlayer(EnumChatFormatting.GRAY + "" + EnumChatFormatting.DARK_GREEN + "Владелец установлен: " + par3EntityPlayer.getEntityName() + ", менять нельзя!");
                    return par1ItemStack;
                }
            }
            else
            {
                par1ItemStack.stackTagCompound = new NBTTagCompound();
                par1ItemStack.stackTagCompound.setString("lswordstartr", par3EntityPlayer.getEntityName());
                par3EntityPlayer.sendChatToPlayer(EnumChatFormatting.GRAY + "" + EnumChatFormatting.DARK_GREEN + "Владелец установлен: " + par3EntityPlayer.getEntityName() + ", менять нельзя!");
            }
        }

        ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
        MinecraftForge.EVENT_BUS.post(event);

        if (event.isCanceled())
        {
            return event.result;
        }
        else
        {
            par2World.playSoundAtEntity(par3EntityPlayer, "tchestplate.spower", 1.0f, 1.5f);
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
            return par1ItemStack;
        }
    }
}

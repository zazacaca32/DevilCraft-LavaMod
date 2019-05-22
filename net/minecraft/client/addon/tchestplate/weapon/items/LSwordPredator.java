package net.minecraft.client.addon.tchestplate.weapon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;

public class LSwordPredator extends BaseLezvie
{
    final short damage = 100;
    double displaydamage;

    public LSwordPredator(int par1)
    {
        super(par1);
        this.setMaxDamage(5000);
        this.setMaxStackSize(1);
        this.setCreativeTab(LavaChestPlate.tabArmor);
    }

    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    public int getItemEnchantability()
    {
        return 0;
    }

    @ForgeSubscribe
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par2World.isRemote)
        {
            if (par1ItemStack.stackTagCompound != null)
            {
                if (!par1ItemStack.stackTagCompound.hasKey("lswordstart"))
                {
                    par1ItemStack.stackTagCompound.setString("lswordstart", par3EntityPlayer.getEntityName());
                    par3EntityPlayer.sendChatToPlayer(EnumChatFormatting.GRAY + "" + EnumChatFormatting.DARK_GREEN + "Владелец установлен " + par3EntityPlayer.getEntityName() + " Менять нельзя!");
                    return par1ItemStack;
                }
            }
            else
            {
                par1ItemStack.stackTagCompound = new NBTTagCompound();
                par1ItemStack.stackTagCompound.setString("lswordstart", par3EntityPlayer.getEntityName());
                par3EntityPlayer.sendChatToPlayer(EnumChatFormatting.GRAY + "" + EnumChatFormatting.DARK_GREEN + "Владелец " + par3EntityPlayer.getEntityName() + " Менять нельзя!");
            }
        }

        return par1ItemStack;
    }

    public int getEnchantS(ItemStack par1ItemStack)
    {
        return par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("swo") ? par1ItemStack.stackTagCompound.getInteger("swo") : 0;
    }

    @ForgeSubscribe
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        if (!player.worldObj.isRemote)
        {
            if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("lswordstart") && stack.stackTagCompound.getString("lswordstart").equals(player.getEntityName()))
            {
                if (player.inventory.armorInventory[0] != null && player.inventory.armorInventory[1] != null && player.inventory.armorInventory[2] != null && player.inventory.armorInventory[3] != null && player.inventory.armorInventory[0].itemID == LavaChestPlate.lavaPredatorPlateBoots.itemID && player.inventory.armorInventory[1].itemID == LavaChestPlate.lavaPredatorPlateLeggins.itemID && player.inventory.armorInventory[2].itemID == LavaChestPlate.lavaPredatorPlateChest.itemID && player.inventory.armorInventory[3].itemID == LavaChestPlate.lavaPredatorPlateHelmet.itemID)
                {
                    entity.attackEntityFrom(DamageSource.causePlayerDamage(player), this.attachmentDamage(stack) + 200);
                    return true;
                }

                entity.attackEntityFrom(DamageSource.causePlayerDamage(player), this.attachmentDamage(stack));
                return true;
            }

            player.addPotionEffect(new PotionEffect(Potion.poison.id, 200));
        }

        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        short oup = (short)this.getEnchantS(par1ItemStack);
        par2List.add("§cУрон от меча: " + this.attachmentDamage(par1ItemStack));
        par2List.add("§cУровень: " + oup);
        par2List.add("§7 В хантер сете в режиме невидимости");
        par2List.add("§7  Бонус: 80% +200 урона");

        if (par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("lswordstart"))
        {
            par2List.add("§aИтем Принадлежит: " + par1ItemStack.stackTagCompound.getString("lswordstart"));
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
        super.itemIcon = par1IconRegister.registerIcon("LavaChestPlate:predatorSword");
    }

    public int attachmentDamage(ItemStack i)
    {
        int swo = this.getEnchantS(i);
        return swo == 1 ? 850 : (swo == 2 ? 900 : (swo == 3 ? 950 : (swo == 4 ? 1000 : (swo == 5 ? 1050 : (swo == 6 ? 1100 : (swo == 7 ? 1200 : (swo == 8 ? 1300 : (swo == 9 ? 1400 : (swo == 10 ? 1600 : (swo == 0 ? 800 : 800))))))))));
    }

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }

    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }

    public int getDamageVsEntity(Entity par1Entity, ItemStack itemStack)
    {
        return this.attachmentDamage(itemStack);
    }
}

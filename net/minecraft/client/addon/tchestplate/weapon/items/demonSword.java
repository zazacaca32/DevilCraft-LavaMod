package net.minecraft.client.addon.tchestplate.weapon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.armors.items.IShadowPlate;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;

public class demonSword extends Item
{
    double displaydamage;

    public demonSword(int par1)
    {
        super(par1);
        this.setMaxDamage(5000);
        this.setMaxStackSize(1);
        this.setCreativeTab(LavaChestPlate.tabArmor);
        this.setFull3D();
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        super.itemIcon = par1IconRegister.registerIcon("qodermelony:demonsword");
    }

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        if (par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("lawar"))
        {
            par2List.add("§8 Наносит урон 600!");
            par2List.add("§8 Возвращает 160 урона в виде энергии");
            par2List.add("§8 Действует только на демон сет!");
            par2List.add("§a Итем Принадлежит: " + par1ItemStack.stackTagCompound.getString("lawar"));
        }
        else
        {
            par2List.add("§8 Наносит урон 600!");
            par2List.add("§8 Возвращает 160 урона в виде энергии");
            par2List.add("§8 Действует только на демон сет!");
            par2List.add("§a Итем Принадлежит: Никому.");
        }
    }

    @ForgeSubscribe
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par2World.isRemote)
        {
            if (par1ItemStack.stackTagCompound != null)
            {
                if (!par1ItemStack.stackTagCompound.hasKey("lawar"))
                {
                    par1ItemStack.stackTagCompound.setString("lawar", par3EntityPlayer.getEntityName());
                    par3EntityPlayer.sendChatToPlayer(EnumChatFormatting.GRAY + "" + EnumChatFormatting.DARK_GREEN + "Владелец установлен " + par3EntityPlayer.getEntityName() + " Менять нельзя!");
                    return par1ItemStack;
                }

                par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, 1000));
            }
            else
            {
                par1ItemStack.stackTagCompound = new NBTTagCompound();
                par1ItemStack.stackTagCompound.setString("lawar", par3EntityPlayer.getEntityName());
                par3EntityPlayer.sendChatToPlayer(EnumChatFormatting.GRAY + "" + EnumChatFormatting.DARK_GREEN + "Владелец " + par3EntityPlayer.getEntityName() + " Менять нельзя!");
            }
        }

        return par1ItemStack;
    }

    @ForgeSubscribe
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        boolean flag = entity instanceof EntityPlayer;

        if (!player.worldObj.isRemote)
        {
            if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("lawar") && stack.stackTagCompound.getString("lawar").equals(player.getEntityName()))
            {
                if (entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 600))
                {
                    Iterator i = MinecraftServer.getServer().getConfigurationManager().playerEntityList.iterator();

                    if (flag)
                    {
                        return true;
                    }

                    ItemStack[] var4 = player.getLastActiveItems();
                    int var5 = var4.length;

                    for (int var101 = 0; var101 < var5; ++var101)
                    {
                        ItemStack armor = var4[var101];

                        if (armor != null && armor.getItem() instanceof IShadowPlate)
                        {
                            ((IShadowPlate)armor.getItem()).regenerateLavaEnergy(armor, 40, 3);
                        }
                    }

                    while (i.hasNext())
                    {
                        Object var10 = i.next();

                        if (!(var10 instanceof EntityPlayerMP))
                        {
                            return true;
                        }
                    }
                }

                return true;
            }

            player.addPotionEffect(new PotionEffect(Potion.poison.id, 200));
        }

        return true;
    }

    public Item setNoRepair()
    {
        super.canRepair = false;
        return this;
    }

    public int getDamageVsEntity(Entity par1Entity, ItemStack itemStack)
    {
        return par1Entity instanceof EntityPlayer ? 1750 : 2300;
    }

    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }

    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
    {
        return true;
    }

    public boolean full3d()
    {
        super.bFull3D = true;
        return true;
    }
}

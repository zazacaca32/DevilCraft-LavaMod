package net.minecraft.client.addon.Tchat;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import net.minecraft.client.addon.Tchat.packets.PacketMAparticle;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;

public class lswordRB extends ItemSword
{
    public lswordRB(int par1, EnumToolMaterial l)
    {
        super(par1, l);
        super.maxStackSize = 1;
        this.setMaxDamage(30000);
        this.setCreativeTab(LavaChestPlate.tabArmor);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }

    @ForgeSubscribe
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        return par1ItemStack;
    }

    @ForgeSubscribe
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        if (player.worldObj.isRemote)
        {
            return true;
        }
        else
        {
            if (entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 400))
            {
                stack.damageItem(40, player);
                Iterator i = MinecraftServer.getServer().getConfigurationManager().playerEntityList.iterator();

                while (i.hasNext())
                {
                    Object ii = i.next();

                    if (!(ii instanceof EntityPlayerMP))
                    {
                        return true;
                    }

                    EntityPlayerMP p = (EntityPlayerMP)ii;
                    int distance = (int)player.getDistanceSqToEntity(p);

                    if (distance <= 30)
                    {
                        PacketDispatcher.sendPacketToPlayer((new PacketMAparticle(2, entity.posX, entity.posY, entity.posZ, 40)).makePacket(), (Player)p);
                    }
                }
            }

            return true;
        }
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 30000;
    }

    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        super.itemIcon = par1IconRegister.registerIcon("lsword:swordRB");
    }

    @SideOnly(Side.CLIENT)
    public boolean onEntityItemUpdate(EntityItem entityItem)
    {
        return super.onEntityItemUpdate(entityItem);
    }

    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
    {
        return true;
    }

    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving)
    {
        return true;
    }

    public int getDamageVsEntity(Entity par1Entity)
    {
        return 0;
    }
}

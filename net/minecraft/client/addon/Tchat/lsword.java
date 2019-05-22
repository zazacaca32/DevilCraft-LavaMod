package net.minecraft.client.addon.Tchat;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.Tchat.packets.PacketMAparticle;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;

public class lsword extends ItemSword
{
    public lsword(int par1, EnumToolMaterial l)
    {
        super(par1, l);
        super.maxStackSize = 1;
        this.setMaxDamage(1000);
        this.setNoRepair();
        this.setCreativeTab(LavaChestPlate.tabArmor);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        par2List.add("§cУрон от меча: " + this.attachmentDamage(par1ItemStack));
        int swo = this.getEnchantS(par1ItemStack);

        if (swo > 0)
        {
            par2List.add("§cУровень: " + swo);
        }
        else if (swo == 0)
        {
            par2List.add("§cУровень: 0");
        }

        if (par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("lswordstart"))
        {
            par2List.add("§aИтем Принадлежит: " + par1ItemStack.stackTagCompound.getString("lswordstart"));
        }
        else
        {
            par2List.add("§aИтем Принадлежит: Никому.");
        }
    }

    public int getEnchantS(ItemStack par1ItemStack)
    {
        return par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("swo") ? par1ItemStack.stackTagCompound.getInteger("swo") : 0;
    }

    public int attachmentDamage(ItemStack i)
    {
        int swo = this.getEnchantS(i);
        return swo == 1 ? 850 : (swo == 2 ? 900 : (swo == 3 ? 950 : (swo == 4 ? 1000 : (swo == 5 ? 1050 : (swo == 6 ? 1100 : (swo == 7 ? 1200 : (swo == 8 ? 1300 : (swo == 9 ? 1400 : (swo == 10 ? 1600 : (swo == 0 ? 800 : 800))))))))));
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

                par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, 1000));
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

    @ForgeSubscribe
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        if (!player.worldObj.isRemote)
        {
            if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("lswordstart") && stack.stackTagCompound.getString("lswordstart").equals(player.getEntityName()))
            {
                if (entity.attackEntityFrom(DamageSource.causePlayerDamage(player), this.attachmentDamage(stack)))
                {
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
                            PacketDispatcher.sendPacketToPlayer((new PacketMAparticle(1, entity.posX, entity.posY, entity.posZ, 25)).makePacket(), (Player)p);
                        }
                    }
                }

                return true;
            }

            player.addPotionEffect(new PotionEffect(Potion.poison.id, 200));
        }

        return true;
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 10000;
    }

    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        super.itemIcon = par1IconRegister.registerIcon("lsword:swordUltima");
    }

    @SideOnly(Side.CLIENT)
    public boolean onEntityItemUpdate(EntityItem entityItem)
    {
        Minecraft.getMinecraft().effectRenderer.addEffect(new net.minecraft.client.particle.EntityFlameFX(entityItem.worldObj, entityItem.posX, entityItem.posY, entityItem.posZ, 0.0D, 0.0D, 0.0D));
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

    public int getDamageVsEntity(Entity par1Entity)
    {
        return 0;
    }
}

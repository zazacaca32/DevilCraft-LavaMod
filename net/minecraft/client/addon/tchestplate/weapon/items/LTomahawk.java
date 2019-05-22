package net.minecraft.client.addon.tchestplate.weapon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.entities.monster.EntityWisp;
import net.minecraft.client.addon.tchestplate.packets.PacketMAparticle;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;

public class LTomahawk extends BaseHammerWeapon
{
    public LTomahawk(int par1, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1, par2EnumToolMaterial);
        this.setNoRepair();
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        super.itemIcon = par1IconRegister.registerIcon("Alo:OGNMolot");
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

    public int getDamageVsEntity(Entity par1Entity, ItemStack itemStack)
    {
        return 1;
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("lHammerstart") && stack.stackTagCompound.getString("lHammerstart").equals(player.getEntityName()))
        {
            if (player.worldObj.isRemote)
            {
                LavaChestPlate.proxy.runSpawnParticle(entity, player, 2);
            }
            else
            {
                if (super.maxCharge.intValue() > this.getCharge(stack).intValue())
                {
                    return true;
                }

                if (entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 6500))
                {
                    entity.worldObj.playSoundEffect(entity.posX, entity.posY, entity.posZ, "tchestplate.shock1", 2.0F, 1.1F);
                    LavaChestPlate.proxy.sendCustomPacketToAllNear((new PacketMAparticle(entity.entityId, player.entityId, 2)).makePacket(), 64.0D, player);
                    short up = this.getEnchantHammer(stack);

                    if (up > 0)
                    {
                        EntityWisp wisp = new EntityWisp(player.worldObj);
                        wisp.setLocationAndAngles(entity.posX, entity.posY + 1.5D, entity.posZ, entity.worldObj.rand.nextFloat() * 360.0F, 0.0F);
                        wisp.attackCount = up;
                        wisp.attackDamage = 6500;
                        wisp.playLivingSound();
                        wisp.getCanSpawnHere();
                        wisp.setTargetedEntity(player, entity);
                        entity.worldObj.spawnEntityInWorld(wisp);
                    }
                }

                this.discharge(stack, 30);
            }
        }

        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        super.addInformation(par1ItemStack, par2EntityPlayer, par2List, par4);
        par2List.add("§7 Наносит сокрушающий удар");
        par2List.add("§8 Урон от которого в 6500!");
        par2List.add("§8 Регенирируется за 60 секунд");
        par2List.add("§8 Бонус:  В руке за 30 секунд");
        short up = this.getEnchantHammer(par1ItemStack);

        if (up > 0)
        {
            par2List.add("§c Пресс:  Уровень " + up);
        }

        if (par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("lHammerstart"))
        {
            par2List.add("§aИтем Принадлежит: " + par1ItemStack.stackTagCompound.getString("lHammerstart"));
        }
        else
        {
            par2List.add("§aИтем Принадлежит: Никому.");
        }
    }

    @ForgeSubscribe
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par2World.isRemote)
        {
            if (par1ItemStack.stackTagCompound != null)
            {
                if (!par1ItemStack.stackTagCompound.hasKey("lHammerstart"))
                {
                    par1ItemStack.stackTagCompound.setString("lHammerstart", par3EntityPlayer.getEntityName());
                    par3EntityPlayer.sendChatToPlayer(EnumChatFormatting.GRAY + "" + EnumChatFormatting.DARK_GREEN + "Владелец установлен " + par3EntityPlayer.getEntityName() + " Менять нельзя!");
                    return par1ItemStack;
                }

                par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, 1000));
            }
            else
            {
                (par1ItemStack.stackTagCompound = new NBTTagCompound()).setString("lHammerstart", par3EntityPlayer.getEntityName());
                par3EntityPlayer.sendChatToPlayer(EnumChatFormatting.GRAY + "" + EnumChatFormatting.DARK_GREEN + "Владелец " + par3EntityPlayer.getEntityName() + " Менять нельзя!");
            }
        }

        return par1ItemStack;
    }
}

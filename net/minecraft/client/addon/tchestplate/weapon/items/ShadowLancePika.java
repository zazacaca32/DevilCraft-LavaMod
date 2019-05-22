package net.minecraft.client.addon.tchestplate.weapon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.packets.PacketMASwingUseItem;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

public class ShadowLancePika extends Item
{
    public ShadowLancePika(int par1)
    {
        super(par1);
        this.setMaxDamage(5000);
        this.setMaxStackSize(1);
        this.setCreativeTab(LavaChestPlate.tabArmor);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        super.itemIcon = par1IconRegister.registerIcon("LavaChestPlate:nooblsword");
    }

    public int getDamageVsEntity(Entity par1Entity, ItemStack itemStack)
    {
        return 1;
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        if (!player.worldObj.isRemote)
        {
            ((EntityPlayerMP)player).getServerForPlayer().getEntityTracker().sendPacketToAllAssociatedPlayers(player, (new PacketMASwingUseItem((byte)1, player.entityId, (short)8)).makePacket());
        }

        return true;
    }

    public boolean onEntitySwing(EntityLiving entityLiving, ItemStack stack)
    {
        return true;
    }

    public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
    {
        return true;
    }

    private void attackEntity(Entity target, EntityPlayer player)
    {
        if (!MinecraftForge.EVENT_BUS.post(new AttackEntityEvent(player, target)))
        {
            ItemStack stack = player.getCurrentEquippedItem();

            if (target.canAttackWithItem() && !target.func_85031_j(player))
            {
                int var2 = player.inventory.getDamageVsEntity(target);

                if (player.isPotionActive(Potion.damageBoost))
                {
                    var2 += 3 << player.getActivePotionEffect(Potion.damageBoost).getAmplifier();
                }

                if (player.isPotionActive(Potion.weakness))
                {
                    var2 -= 2 << player.getActivePotionEffect(Potion.weakness).getAmplifier();
                }

                int var3 = 0;
                int var4 = 0;

                if (target instanceof EntityLiving)
                {
                    var4 = EnchantmentHelper.getEnchantmentModifierLiving(player, (EntityLiving)target);
                    var3 += EnchantmentHelper.getKnockbackModifier(player, (EntityLiving)target);
                }

                if (player.isSprinting())
                {
                    ++var3;
                }

                if (var2 > 0 || var4 > 0)
                {
                    boolean var5 = player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPotionActive(Potion.blindness) && player.ridingEntity == null && target instanceof EntityLiving;

                    if (var5)
                    {
                        var2 += player.worldObj.rand.nextInt(var2 / 2 + 2);
                    }

                    var2 += var4;
                    boolean var6 = false;
                    int var7 = EnchantmentHelper.getFireAspectModifier(player);

                    if (target instanceof EntityLiving && var7 > 0 && !target.isBurning())
                    {
                        var6 = true;
                        target.setFire(1);
                    }

                    boolean var8 = target.attackEntityFrom(DamageSource.causePlayerDamage(player), var2);

                    if (var8)
                    {
                        if (var3 > 0)
                        {
                            target.addVelocity((double)(-MathHelper.sin(player.rotationYaw * (float)Math.PI / 180.0F) * (float)var3 * 0.5F), 0.1D, (double)(MathHelper.cos(player.rotationYaw * (float)Math.PI / 180.0F) * (float)var3 * 0.5F));
                            player.motionX *= 0.6D;
                            player.motionZ *= 0.6D;
                            player.setSprinting(false);
                        }

                        if (var5)
                        {
                            player.onCriticalHit(target);
                        }

                        if (var4 > 0)
                        {
                            player.onEnchantmentCritical(target);
                        }

                        if (var2 >= 18)
                        {
                            player.triggerAchievement(AchievementList.overkill);
                        }

                        player.setLastAttackingEntity(target);

                        if (target instanceof EntityLiving)
                        {
                            EnchantmentThorns.func_92096_a(player, (EntityLiving)target, player.worldObj.rand);
                        }
                    }

                    ItemStack var9 = player.getCurrentEquippedItem();

                    if (var9 != null && target instanceof EntityLiving)
                    {
                        var9.hitEntity((EntityLiving)target, player);

                        if (var9.stackSize <= 0)
                        {
                            player.destroyCurrentEquippedItem();
                        }
                    }

                    if (target instanceof EntityLiving)
                    {
                        player.addStat(StatList.damageDealtStat, var2);

                        if (var7 > 0 && var8)
                        {
                            target.setFire(var7 * 4);
                        }
                        else if (var6)
                        {
                            target.extinguish();
                        }
                    }

                    player.addExhaustion(0.3F);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }

    public int getItemEnchantability()
    {
        return 0;
    }

    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
    }
}

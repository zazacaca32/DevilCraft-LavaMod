package net.minecraft.client.addon.tchestplate.weapon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.Bukkit.CallEventBukkit;
import net.minecraft.client.addon.tchestplate.fx.FXLightningBolt;
import net.minecraft.client.addon.tchestplate.fx.manager.ManagerFX;
import net.minecraft.client.addon.tchestplate.fx.manager.Shock;
import net.minecraft.client.addon.tchestplate.items.ItemAmulets;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class LStaffFriz extends BaseStaffWeapon
{
    private long soundDelay;
    int max = 4;
    int cooldown = 15;

    public LStaffFriz(int par1, int max, int cooldown, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1, max, 15, par2EnumToolMaterial);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        super.itemIcon = par1IconRegister.registerIcon("LavaChestPlate:lastaff");
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 30;
    }

    public int getDamageVsEntity(Entity par1Entity, ItemStack itemStack)
    {
        return 1;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer p)
    {
        if (!p.getEntityName().equals(this.getOwner(itemstack, p.getEntityName())))
        {
            return itemstack;
        }
        else if (this.KD(itemstack) > this.getCharge(itemstack).intValue())
        {
            if (!world.isRemote)
            {
                p.sendChatToPlayer("§7[Staff] §8Нельзя использовать еще " + (super.maxCharge.intValue() - this.getCharge(itemstack).intValue()) + "c.");
            }

            return itemstack;
        }
        else
        {
            p.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
            return itemstack;
        }
    }

    public boolean shouldRotateAroundWhenRendering()
    {
        return false;
    }

    public EnumAction getItemUseAction(ItemStack itemstack)
    {
        return EnumAction.bow;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        super.addInformation(par1ItemStack, par2EntityPlayer, par2List, par4);
        int sup = this.getEnchant(par1ItemStack);
        this.getOwner(par1ItemStack, par2List);
        par2List.add("§7 Накладывает шок на игрока");
        par2List.add("§7 Улучшен на §a+" + sup);
        par2List.add("§8 Эффекты:");
        par2List.add("§8  Шок - " + this.SHOK(par1ItemStack) + " секунд(ы)");
        par2List.add("§8  Кулдаун - " + this.KD(par1ItemStack) + " секунд(ы)");
    }

    public int get(ItemStack p)
    {
        return p.stackTagCompound.getInteger("sup");
    }

    public int getEnchant(ItemStack par1ItemStack)
    {
        return par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("sup") ? par1ItemStack.stackTagCompound.getInteger("sup") : 0;
    }

    public int KD(ItemStack i)
    {
        int a = this.getEnchant(i);
        return a == 0 ? 15 : (a == 1 ? 14 : (a == 2 ? 14 : (a == 3 ? 13 : (a == 4 ? 13 : (a == 5 ? 12 : (a == 6 ? 12 : (a == 7 ? 11 : (a == 8 ? 11 : (a == 9 ? 10 : (a == 10 ? 10 : (a == 11 ? 9 : (a == 12 ? 9 : (a == 13 ? 8 : (a == 14 ? 8 : (a == 15 ? 7 : (a == 16 ? 7 : 1))))))))))))))));
    }

    public int SHOK(ItemStack i)
    {
        int a = this.getEnchant(i);
        return a == 0 ? 2 : (a == 1 ? 2 : (a == 2 ? 3 : (a == 3 ? 3 : (a == 4 ? 4 : (a == 5 ? 4 : (a == 6 ? 4 : (a == 7 ? 4 : (a == 8 ? 5 : (a == 9 ? 5 : (a == 10 ? 5 : (a == 11 ? 5 : (a == 12 ? 6 : (a == 13 ? 6 : (a == 14 ? 6 : (a == 15 ? 6 : (a == 16 ? 7 : 1))))))))))))))));
    }

    public void getOwner(ItemStack par1ItemStack, List par2List)
    {
        if (par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("lStaffFriz"))
        {
            par2List.add("§aИтем Принадлежит: " + par1ItemStack.stackTagCompound.getString("lStaffFriz"));
        }
        else
        {
            par2List.add("§aИтем Принадлежит: Никому.");
        }
    }

    public static void shootLightning(World world, EntityLiving entityplayer, double xx, double yy, double zz, boolean offset)
    {
        double px = entityplayer.posX;
        double py = entityplayer.posY + 0.6D;
        double pz = entityplayer.posZ;

        if (entityplayer.entityId != ModLoader.getMinecraftInstance().thePlayer.entityId)
        {
            py = entityplayer.boundingBox.minY + (double)(entityplayer.height / 2.0F) + 0.25D;
        }

        px -= (double)(MathHelper.cos(entityplayer.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        py -= 0.05000000014901161D;
        pz -= (double)(MathHelper.sin(entityplayer.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        Vec3 vec3d = entityplayer.getLook(1.0F);
        px += vec3d.xCoord * 0.25D;
        py += vec3d.yCoord * 0.25D;
        pz += vec3d.zCoord * 0.25D;
        FXLightningBolt bolt = new FXLightningBolt(world, px, py, pz, xx, yy, zz, world.rand.nextLong(), 6, 0.5F, 5);
        bolt.defaultFractal();
        bolt.setType(2);
        bolt.setWidth(0.125F);
        bolt.finalizeBolt();
    }

    public void onUsingItemTick(ItemStack stack, EntityPlayer p, int count)
    {
        if (!p.worldObj.isRemote && CallEventBukkit.CallBlockCanAttackEvent(p, p) && super.tick.longValue() % 50L == 0L && p.inventory.getCurrentItem().getItem() instanceof BaseEnergyOilItem && p.isUsingItem())
        {
            this.discharge(p.inventory.getCurrentItem(), this.KD(stack));
            p.stopUsingItem();
        }

        if (this.getCharge(stack).intValue() == 0)
        {
            p.stopUsingItem();
        }
        else
        {
            Entity pointedEntity = Utils.getPointedEntity(p.worldObj, p, 20.0D, 1.1F);

            if (pointedEntity != null)
            {
                this.discharge(stack, this.KD(stack));
                p.stopUsingItem();
            }

            boolean zapped = false;

            if (this.soundDelay < System.currentTimeMillis())
            {
                if (!p.worldObj.isRemote)
                {
                    p.worldObj.playSoundEffect(p.posX + 0.5D, p.posY + 0.5D, p.posZ + 0.5D, "tchestplate.jacobs1", 3.0F, 1.5F);
                }

                this.soundDelay = System.currentTimeMillis() + 90L;
                zapped = true;
            }

            if (p.worldObj.isRemote && zapped)
            {
                MovingObjectPosition charges = Utils.getTargetBlock(p.worldObj, p, false);
                Vec3 v = p.getLook(2.0F);
                double px = p.posX + v.xCoord * 10.0D;
                double py = p.posY + v.yCoord * 10.0D;
                double pz = p.posZ + v.zCoord * 10.0D;

                if (pointedEntity != null)
                {
                    px = pointedEntity.posX;
                    double var10000 = pointedEntity.boundingBox.minY + (double)(pointedEntity.height / 2.0F);
                    pz = pointedEntity.posZ;
                    float s = 1.0F;
                    float s1 = 1.0F;
                    boolean t = true;
                    py = pointedEntity.boundingBox.minY + (double)pointedEntity.height - 0.1D;
                }
                else if (charges != null)
                {
                    px = charges.hitVec.xCoord;
                    py = charges.hitVec.yCoord;
                    pz = charges.hitVec.zCoord;
                }

                shootLightning(p.worldObj, p, px, py, pz, true);
            }
        }
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

    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int count)
    {
        if (!par2World.isRemote)
        {
            Entity pointedEntity = Utils.getPointedEntity(par2World, par3EntityPlayer, 30.0D, 1.1F);

            if (pointedEntity != null && pointedEntity instanceof EntityPlayerMP)
            {
                if (CallEventBukkit.CallBlockCanAttackEvent(par3EntityPlayer, (EntityPlayer)pointedEntity))
                {
                    return;
                }

                ExtendedPlayer Epl = ExtendedPlayer.get((EntityPlayer)pointedEntity);
                ItemStack amulet = Epl.inventoryExt.inventory[3];

                if (amulet != null && amulet.getItem() instanceof ItemAmulets && ((ItemAmulets)amulet.getItem()).isCanceledShok(amulet))
                {
                    return;
                }

                Shock f = new Shock(this.SHOK(par1ItemStack) * 1000, (byte)0);
                Epl.addEffect(f);
                ManagerFX.sendEffectToClientsAllPlayers((EntityPlayerMP)pointedEntity, f);
                pointedEntity.worldObj.playSoundEffect(pointedEntity.posX + 0.5D, pointedEntity.posY + 0.5D, pointedEntity.posZ + 0.5D, "tchestplate.shock2", 3.0F, 1.5F);
            }
        }
    }

    ItemStack CreateItem(ItemStack var1)
    {
        return var1;
    }
}

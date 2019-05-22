package net.minecraft.client.addon.lavamobs.entity;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.addon.lavamobs.LavaModMobs;
import net.minecraft.client.addon.lavamobs.packets.PacketMAUpdateInt;
import net.minecraft.client.addon.lavamobs.tile.TileBlockRaid;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EntityRaidBoss extends LavaEntityMob implements IBossDisplayData
{
    public int smashCounter = 0;
    private boolean smash = false;
    private float jumpValue = 0.5F;
    private long time_count = 0L;
    public int plauercount = 0;
    public int posx;
    public int posy;
    public int posz;
    public int meta;
    private int timer = 200;
    private boolean flag = true;
    private boolean spawnflag = true;
    private boolean flagheal = true;
    public List timesguard = new ArrayList();
    public int deathTicks;
    private boolean deathsound = true;

    public EntityRaidBoss(World par1World)
    {
        super(par1World);
        super.texture = "/mods/lavamobs/textures/raidboss.png";
        super.moveSpeed = 0.25F;
        this.setSize(1.2F, 2.5F);
        super.tasks.addTask(0, new EntityAISwimming(this));
        super.tasks.addTask(1, new EntityAIBreakDoor(this));
        super.tasks.addTask(2, new LavaEntityAIAttackOnCollide(this, EntityPlayer.class, super.moveSpeed, false));
        super.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        super.tasks.addTask(4, new EntityAIWander(this, super.moveSpeed));
        super.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
        this.time_count = System.currentTimeMillis() + 604800000L;
    }

    public void SetBlockLocate(int xCoord, int yCoord, int zCoord, int blockMetadata)
    {
        this.posx = xCoord;
        this.posy = yCoord;
        this.posz = zCoord;
        this.meta = blockMetadata;
    }

    protected void despawnEntity()
    {
        super.entityAge = 0;
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        this.posx = nbttagcompound.getInteger("posx");
        this.posy = nbttagcompound.getInteger("posy");
        this.posz = nbttagcompound.getInteger("posz");
        this.meta = nbttagcompound.getInteger("meta");
        this.time_count = nbttagcompound.getLong("time_count");
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setInteger("posx", this.posx);
        nbttagcompound.setInteger("posy", this.posy);
        nbttagcompound.setInteger("posz", this.posz);
        nbttagcompound.setInteger("meta", this.meta);
        nbttagcompound.setLong("time_count", this.time_count);
    }

    protected void entityInit()
    {
        super.entityInit();
        super.dataWatcher.addObject(16, new Integer(100));
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        super.dataWatcher.updateObject(16, Integer.valueOf(super.health));
    }

    public int getAttackStrength(Entity par1Entity)
    {
        return 400;
    }

    protected boolean isAIEnabled()
    {
        return true;
    }

    public int getMaxHealth()
    {
        return 30000;
    }

    public void spawnraidguard()
    {
        double X = (double)this.posx;
        double Y = (double)this.posy;
        double Z = (double)this.posz;
        EntityGuardRaid eentity = new EntityGuardRaid(super.worldObj);
        eentity.setraidboss(this);
        eentity.setLocationAndAngles(X, Y, Z, super.rotationYaw, 0.0F);
        super.worldObj.spawnEntityInWorld(eentity);
    }

    public void onLivingUpdate()
    {
        if (!super.worldObj.isRemote)
        {
            if (this.flagheal && super.health <= 10000)
            {
                this.flagheal = false;
                this.MesageRaidBoss(2);
            }

            super.dataWatcher.updateObject(16, Integer.valueOf(super.health));

            if (this.spawnflag)
            {
                this.spawnflag = false;
                this.MesageRaidBoss(1);
                MinecraftServer.getServer().worldServers[0].setWorldTime(19999L);

                for (int var81 = 0; var81 < 6; ++var81)
                {
                    this.spawnraidguard();
                }
            }

            if (super.health <= 0 && this.flag)
            {
                this.flag = false;
                MinecraftServer.getServer().worldServers[0].setWorldTime(23000L);
                super.worldObj.setBlock(this.posx, this.posy, this.posz, LavaModMobs.RaidBossBlock.blockID, this.meta, 2);
                TileEntity var7 = super.worldObj.getBlockTileEntity(this.posx, this.posy, this.posz);

                if (var7 != null && var7 instanceof TileBlockRaid)
                {
                    ((TileBlockRaid)var7).UpdateTimeServerL(this.time_count);
                }
            }

            --this.timer;

            if (this.timer <= 0)
            {
                MinecraftServer.getServer().worldServers[0].setWorldTime(19999L);
                Iterator var8 = this.timesguard.iterator();
                long ctime = System.currentTimeMillis();

                while (var8.hasNext())
                {
                    long var91 = ((Long)var8.next()).longValue();

                    if (var91 < ctime)
                    {
                        this.spawnraidguard();
                        var8.remove();
                    }
                }

                List var9 = super.worldObj.selectEntitiesWithinAABB(EntityLiving.class, super.boundingBox.expand(20.0D, 4.0D, 20.0D), new IEntitySelector()
                {
                    public boolean isEntityApplicable(Entity entity)
                    {
                        return entity instanceof EntityPlayer;
                    }
                });
                this.plauercount = var9.size();

                for (int i1 = 0; i1 < var9.size(); ++i1)
                {
                    EntityLiving entityliving = (EntityLiving)var9.get(i1);

                    if (entityliving != this && entityliving.isEntityAlive() && entityliving instanceof EntityPlayer)
                    {
                        PacketDispatcher.sendPacketToPlayer((new PacketMAUpdateInt((byte)2, super.entityId)).makePacket(), (Player)entityliving);

                        if (!((EntityPlayer)entityliving).capabilities.disableDamage)
                        {
                            if (((EntityPlayer)entityliving).capabilities.isFlying)
                            {
                                this.attackEntityAsMob(entityliving);
                                ((EntityPlayer)entityliving).capabilities.allowFlying = false;
                            }

                            this.attackEntityAsMob(entityliving);
                        }
                    }
                }

                if (super.onGround)
                {
                    super.motionY += (double)this.jumpValue;
                }

                this.timer = 200;
            }
        }
        else
        {
            super.health = this.getBossHealth();
            this.SpawnPar(1);
        }

        super.onLivingUpdate();
    }

    protected String getLivingSound()
    {
        return "lavamobs.hurtraidboss";
    }

    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        super.worldObj.playSoundAtEntity(this, "mob.irongolem.walk", 0.5F, 1.0F);
    }

    protected void onDeathUpdate()
    {
        ++this.deathTicks;

        if (this.deathsound && super.worldObj.isRemote)
        {
            this.deathsound = false;
            super.worldObj.playSound(super.posX, super.posY, super.posZ, "lavamobs.deathraidboss", 1.0F, 1.0F, false);
        }

        if (this.deathTicks >= 0 && this.deathTicks <= 50)
        {
            float f = (super.rand.nextFloat() - 0.5F) * 8.0F;
            float f1 = (super.rand.nextFloat() - 0.5F) * 4.0F;
            float f2 = (super.rand.nextFloat() - 0.5F) * 8.0F;
            super.worldObj.spawnParticle("hugeexplosion", super.posX + (double)f, super.posY + 2.0D + (double)f1, super.posZ + (double)f2, 0.0D, 0.0D, 0.0D);
        }

        super.renderYawOffset = super.rotationYaw += 20.0F;

        if (this.deathTicks == 50 && !super.worldObj.isRemote)
        {
            this.MesageRaidBoss(3);
            this.setDropOtherItem();
            this.setDead();
        }
    }

    private void setDropOtherItem()
    {
        try
        {
            this.entityDropItem(new ItemStack(399, 1, 0), 3.0F);
            this.entityDropItem(new ItemStack(20969, 1, 0), 3.0F);
            int var10;

            for (var10 = 0; var10 < 10; ++var10)
            {
                double var111 = super.posX + (double)(super.worldObj.rand.nextInt(10) - 5);
                double d1 = super.posY + 1.0D;
                double d2 = super.posZ + (double)(super.worldObj.rand.nextInt(10) - 5);
                EntityItem entityitem = new EntityItem(super.worldObj, var111, d1, d2, new ItemStack(4015, 1, 0));
                EntityItem entityitem2 = new EntityItem(super.worldObj, var111, d1, d2, new ItemStack(20860, 1, 0));
                super.worldObj.spawnEntityInWorld(entityitem);
                super.worldObj.spawnEntityInWorld(entityitem2);
            }

            var10 = super.worldObj.rand.nextInt(100);

            if (var10 > 40 && var10 < 70)
            {
                int var11 = 2480 + super.worldObj.rand.nextInt(3);
                this.entityDropItem(new ItemStack(var11, 1, 0), 3.0F);
            }
        }
        catch (Exception var101)
        {
            var101.printStackTrace();
        }
    }

    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float par1)
    {
        return 15728880;
    }

    public int getBossHealth()
    {
        return super.dataWatcher.getWatchableObjectInt(16);
    }

    public void SpawnPar(int par)
    {
        super.worldObj.playSoundAtEntity(this, "mob.irongolem.walk", 1.0F, 1.0F);
        double d0 = super.posX - 0.5D;
        double d1 = super.posY - 1.0D;
        double d2 = super.posZ - 0.5D;
        double d3 = Math.random() * 2.0D;
        double d4 = Math.random() * 2.0D;
        double d5 = Math.random() * 5.0D;

        for (int x = 0; x < par; ++x)
        {
            d3 = Math.random() * 2.0D;
            d4 = Math.random() * 2.0D;
            d5 = Math.random() * 5.0D;
            super.worldObj.spawnParticle("smoke", d0 + d3, d1 + d5, d2 + d4, 0.0D, 0.0D, 0.0D);
        }

        super.worldObj.spawnParticle("flame", d0 + d3, d1 + d5 - 1.0D, d2 + d4, 0.0D, 0.0D, 0.0D);
    }

    public void MesageRaidBoss(int par)
    {
        String par1Str = "";

        switch (par)
        {
            case 1:
                par1Str = "[§cRaid Boss§f] §6Я возродился чтоб убить вас и мир погрузить в вечную тьму!";
                break;

            case 2:
                par1Str = "[§cRaid Boss§f] §6Вам не победить меня, потому что я питаюсь вашими душами!";
                break;

            case 3:
                par1Str = "[§cRaid Boss§f] §6В этот раз вы победили!!! Но я вернусь и подчиню этот мир тьме.";
                break;

            default:
                return;
        }

        MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(new Packet3Chat(par1Str));
    }
}

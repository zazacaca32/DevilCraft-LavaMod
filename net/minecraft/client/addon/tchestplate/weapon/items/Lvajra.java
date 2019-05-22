package net.minecraft.client.addon.tchestplate.weapon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet53BlockChange;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class Lvajra extends BaseHammerWeapon
{
    private Block[] blocksEffectiveAgainst;
    public float efficiencyOnProperMaterial = 4.0F;
    public int damageVsEntity;
    protected EnumToolMaterial toolMaterial;

    public Lvajra(int par1, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1, par2EnumToolMaterial);
        this.toolMaterial = par2EnumToolMaterial;
        this.blocksEffectiveAgainst = new Block[0];
        super.maxStackSize = 1;
        this.setMaxDamage(par2EnumToolMaterial.getMaxUses());
        this.efficiencyOnProperMaterial = par2EnumToolMaterial.getEfficiencyOnProperMaterial();
        this.damageVsEntity = par2EnumToolMaterial.getDamageVsEntity();
        super.maxCharge = Integer.valueOf(5000);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        super.itemIcon = par1IconRegister.registerIcon("LavaChestPlate:lvajra");
    }

    public void setActiveMode(ItemStack itemstack)
    {
        Map m = Utils.getLavaEnch(itemstack);
        Integer ii = (Integer)m.get(ActiveMode.ACTIVE);

        if (ii == null)
        {
            m.put(ActiveMode.ACTIVE, Integer.valueOf(0));
        }
        else if (ii.intValue() == 0)
        {
            m.put(ActiveMode.ACTIVE, Integer.valueOf(1));
        }
        else if (ii.intValue() == 1)
        {
            m.put(ActiveMode.ACTIVE, Integer.valueOf(2));
        }
        else if (ii.intValue() == 2)
        {
            m.put(ActiveMode.ACTIVE, Integer.valueOf(1));
        }

        Utils.setLavaEnch(m, itemstack);
        m = null;
    }

    public void setMode(ItemStack itemstack, ActiveMode mode, int lvl)
    {
        Map m = Utils.getLavaEnch(itemstack);
        Integer ii = (Integer)m.get(mode);

        if (ii == null)
        {
            m.put(mode, Integer.valueOf(1));
        }
        else
        {
            m.put(mode, Integer.valueOf(lvl));
        }

        Utils.setLavaEnch(m, itemstack);
        m = null;
    }

    public int getlvlMode(ItemStack itemstack, ActiveMode mode)
    {
        Map m = Utils.getLavaEnch(itemstack);
        Integer activeMode = (Integer)m.get(mode);

        if (activeMode == null)
        {
            m = null;
            return 0;
        }
        else
        {
            Integer lvl = (Integer)m.get(activeMode);
            m = null;

            if (lvl != null)
            {
                return Utils.byteArrayToInt(new byte[] {activeMode.byteValue(), lvl.byteValue()});
            }
            else if (ActiveMode.values()[activeMode.intValue()] == ActiveMode.FORTUNE)
            {
                this.setMode(itemstack, ActiveMode.values()[activeMode.intValue()], 3);
                return Utils.byteArrayToInt(new byte[] {activeMode.byteValue(), (byte)3});
            }
            else
            {
                this.setMode(itemstack, ActiveMode.values()[activeMode.intValue()], 1);
                return Utils.byteArrayToInt(new byte[] {activeMode.byteValue(), (byte)1});
            }
        }
    }

    ItemStack CreateItem(ItemStack itemstack)
    {
        this.setActiveMode(itemstack);
        return itemstack;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if (!world.isRemote)
        {
            this.setActiveMode(itemstack);
            byte[] mode = Utils.intToByteArray(this.getlvlMode(itemstack, ActiveMode.ACTIVE));
            ((EntityPlayerMP)entityplayer).sendChatToPlayer("§7[Когти] Активный режим: " + ActiveMode.values()[mode[0]].getActiveModeStr());
        }

        return itemstack;
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int side, float a, float b, float c)
    {
        return false;
    }

    public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
    {
        int l = player.worldObj.getBlockId(X, Y, Z);
        int i1 = player.worldObj.getBlockMetadata(X, Y, Z);
        Block block = Block.blocksList[l];

        if (block != null)
        {
            ;
        }

        if (player.worldObj.isRemote)
        {
            return false;
        }
        else
        {
            if (player instanceof EntityPlayerMP)
            {
                EntityPlayerMP pl = (EntityPlayerMP)player;
                player.worldObj.playAuxSFXAtEntity(player, 2001, X, Y, Z, l + (player.worldObj.getBlockMetadata(X, Y, Z) << 12));
                boolean flag = false;

                if (pl.theItemInWorldManager.isCreative())
                {
                    this.removeBlock(X, Y, Z, player);
                    pl.playerNetServerHandler.sendPacketToPlayer(new Packet53BlockChange(X, Y, Z, player.worldObj));
                }
                else
                {
                    boolean flag2 = false;

                    if (block != null)
                    {
                        flag2 = block.canHarvestBlock(player, i1);
                    }

                    if (itemstack != null)
                    {
                        itemstack.onBlockDestroyed(player.worldObj, l, X, Y, Z, player);

                        if (itemstack.stackSize == 0)
                        {
                            player.destroyCurrentEquippedItem();
                        }
                    }

                    flag = this.removeBlock(X, Y, Z, player);

                    if (flag && flag2)
                    {
                        if (l != 32 && l != 78 && l != 18 && l != 31 && l != 106)
                        {
                            this.harvestBlock(player.worldObj, player, X, Y, Z, i1, l);
                        }
                        else
                        {
                            Block.blocksList[l].harvestBlock(player.worldObj, player, X, Y, Z, i1);
                        }
                    }
                }
            }

            return true;
        }
    }

    protected ItemStack createStackedBlock(int damage, int blockID)
    {
        int j = 0;

        if (blockID >= 0 && blockID < Item.itemsList.length && Item.itemsList[blockID].getHasSubtypes())
        {
            j = damage;
        }

        return new ItemStack(blockID, 1, j);
    }

    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int damage, int blockID)
    {
        Block b = Block.blocksList[blockID];
        par2EntityPlayer.addStat(StatList.mineBlockStatArray[blockID], 1);
        par2EntityPlayer.addExhaustion(0.025F);
        byte[] mode = Utils.intToByteArray(this.getlvlMode(par2EntityPlayer.getHeldItem(), ActiveMode.ACTIVE));

        if (b.canSilkHarvest(par1World, par2EntityPlayer, par3, par4, par5, damage) && mode[0] == ActiveMode.SILK.ordinal())
        {
            ItemStack var12 = this.createStackedBlock(damage, blockID);

            if (var12 != null)
            {
                this.dropBlockAsItem_do(par1World, par3, par4, par5, var12);
            }
        }
        else if (mode[0] == ActiveMode.FORTUNE.ordinal())
        {
            if (LavaChestPlate.palladiumOre.blockID != blockID && LavaChestPlate.cobaltOre.blockID != blockID && LavaChestPlate.adamantiteOre.blockID != blockID)
            {
                b.dropBlockAsItem(par1World, par3, par4, par5, damage, mode[1]);
            }
            else
            {
                ArrayList items = b.getBlockDropped(par1World, par3, par4, par5, damage, mode[1]);

                if (items.size() > 0)
                {
                    for (int i = 0; i < getRandomInt(3); ++i)
                    {
                        this.dropBlockAsItem_do(par1World, par3, par4, par5, ((ItemStack)items.get(0)).copy());
                    }
                }
            }
        }
        else
        {
            b.dropBlockAsItem(par1World, par3, par4, par5, damage, mode[1]);
        }
    }

    public static int getRandomInt(int max)
    {
        return (int)(Math.random() * (double)(max - 1) + 1.0D);
    }

    protected void dropBlockAsItem_do(World par1World, int par2, int par3, int par4, ItemStack par5ItemStack)
    {
        if (!par1World.isRemote && par1World.getGameRules().getGameRuleBooleanValue("doTileDrops"))
        {
            float f = 0.7F;
            double d0 = (double)(par1World.rand.nextFloat() * 0.7F) + 0.15000000596046448D;
            double d2 = (double)(par1World.rand.nextFloat() * 0.7F) + 0.15000000596046448D;
            double d3 = (double)(par1World.rand.nextFloat() * 0.7F) + 0.15000000596046448D;
            EntityItem entityitem = new EntityItem(par1World, (double)par2 + d0, (double)par3 + d2, (double)par4 + d3, par5ItemStack);
            entityitem.delayBeforeCanPickup = 10;
            par1World.spawnEntityInWorld(entityitem);
        }
    }

    private boolean removeBlock(int par1, int par2, int par3, EntityPlayer player)
    {
        Block block = Block.blocksList[player.worldObj.getBlockId(par1, par2, par3)];
        int damage = player.worldObj.getBlockMetadata(par1, par2, par3);

        if (block != null)
        {
            block.onBlockHarvested(player.worldObj, par1, par2, par3, damage, player);
        }

        boolean flag;

        if (block != null && block.blockID == 2614)
        {
            flag = block != null && block.removeBlockByPlayer(player.worldObj, player, par1, par2, par3);
        }
        else
        {
            flag = block != null && this.removeBlockByPlayer(player.worldObj, player, par1, par2, par3);
        }

        if (block != null && flag)
        {
            block.onBlockDestroyedByPlayer(player.worldObj, par1, par2, par3, damage);
        }

        return flag;
    }

    public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
        return world.setBlockToAir(x, y, z);
    }

    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        return this.getCharge(par1ItemStack).intValue() > 0 ? 20000.0F : 1.0F;
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemstack)
    {
        return EnumRarity.epic;
    }

    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving)
    {
        this.discharge(par1ItemStack, 1);
        return false;
    }

    public int getDamageVsEntity(Entity par1Entity)
    {
        return 1;
    }

    public boolean canHarvestBlock(Block block)
    {
        return block != Block.bedrock;
    }

    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }

    public float getStrVsBlock(ItemStack stack, Block block, int meta)
    {
        return ForgeHooks.isToolEffective(stack, block, meta) ? this.efficiencyOnProperMaterial : this.getStrVsBlock(stack, block);
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        if (!player.worldObj.isRemote)
        {
            if (this.getCharge(stack).intValue() > 30)
            {
                if (entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 25))
                {
                    ;
                }

                this.discharge(stack, 30);
            }
            else
            {
                entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 2);
            }
        }

        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        super.addInformation(par1ItemStack, par2EntityPlayer, par2List, par4);
        byte[] mode = Utils.intToByteArray(this.getlvlMode(par1ItemStack, ActiveMode.ACTIVE));
        par2List.add("§7 Активный режим: " + ActiveMode.values()[mode[0]].getActiveModeStr());
        par2List.add("§7 Уровень режима: " + mode[1]);
        par2List.add("§8 Удача на Когтях дает шанс 1-2 для:");
        par2List.add("§8 Адамантитовой руды.");
        par2List.add("§8 Палладиевой руды.");
        par2List.add("§8 Кобальтовой руды.");
    }
}

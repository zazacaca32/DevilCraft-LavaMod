package net.minecraft.client.addon.tchestplate.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.IUpdateItemExtPlayer;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.addon.tchestplate.util.Coords;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;

public class ItemPetograph extends MultiItemBase implements IUpdateItemExtPlayer
{
    public Icon iconRing;
    public Icon iconCore;
    private Coords CoordClient;

    public ItemPetograph(int i, int j, int count)
    {
        super(i, count);
        super.maxStackSize = j;
        this.setCreativeTab(LavaChestPlate.tabItemss);
    }

    @ForgeSubscribe
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (par2World.isRemote)
        {
            LavaChestPlate var10000 = LavaChestPlate.instance;
            LavaChestPlate.proxy.openCustomGui(10, par3EntityPlayer);
        }

        return par1ItemStack;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        super.addInformation(par1ItemStack, par2EntityPlayer, par2List, par4);

        switch (par1ItemStack.getItemDamage())
        {
            case 0:
                par2List.add("§6Служит для поиска места прокачки питомца");
                par2List.add("§6 Правой кнопкой открыть меню");

            default:
        }
    }

    public EnumRarity getRarity(ItemStack itemstack)
    {
        return EnumRarity.uncommon;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir)
    {
        this.iconRing = ir.registerIcon("LavaChestPlate:petograph");
        this.iconCore = ir.registerIcon("LavaChestPlate:petographcore");
    }

    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1)
    {
        return this.iconRing;
    }

    public int getRenderPasses(int m)
    {
        return 2;
    }

    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamageForRenderPass(int par1, int par2)
    {
        return par2 == 0 ? this.iconRing : this.iconCore;
    }

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }

    public void onUpdate(ItemStack itemstack, World world, ExtendedPlayer player, int slot, boolean isCurrentItem, long tickinslot, IUpdateItemExtPlayer.SideExt side)
    {
        if (isCurrentItem && player != null && player.inventoryExt != null && player.inventoryExt.inventory[2] != null && player.inventoryExt.inventory[2].getItem() instanceof ItemPets)
        {
            NBTTagCompound nbt = Utils.getOrCreateNbtData(player.inventoryExt.inventory[2]);
            long ptime_ = System.currentTimeMillis();

            if (this.CoordClient == null)
            {
                this.CoordClient = new Coords(player.player);
            }
            else
            {
                this.CoordClient.setCoods(player.player);
            }

            short x1 = nbt.getShort("pX");
            short y1 = nbt.getShort("pY");
            short z1 = nbt.getShort("pZ");

            if (!world.isRemote)
            {
                if (nbt.hasKey("ptime"))
                {
                    long var16111111 = nbt.getLong("ptime");

                    if (var16111111 < ptime_)
                    {
                        nbt.setLong("ptime", ptime_ + 3600000L);
                        this.generatenewcoords(nbt, world);
                        player.inventoryExt.onInventoryChanged();
                        return;
                    }

                    Coords var18111111 = new Coords((float)x1, (float)y1, (float)z1);
                    boolean var15 = this.CoordClient.isInCube(new Coords((float)x1, (float)y1, (float)z1), 60);

                    if (var15)
                    {
                        boolean flag = this.CoordClient.isInCube(var18111111, 2);

                        if (flag)
                        {
                            int sp = nbt.getInteger("psp");
                            sp = sp > 32767 ? 32767 : sp + 5;
                            nbt.setInteger("psp", sp);
                            byte lvl = nbt.getByte("plvl");

                            if (sp > 0 && lvl < (int)Math.log((double)sp))
                            {
                                nbt.setByte("plvl", (byte)((int)Math.log((double)sp)));
                            }

                            nbt.setLong("ptime", ptime_ + 3600000L);
                            this.generatenewcoords(nbt, world);
                            player.inventoryExt.onInventoryChanged();
                            return;
                        }

                        if (y1 == 70)
                        {
                            var18111111.y = 150.0F;
                            nbt.setShort("pY", (short)((int)Utils.getBlockBelowLoc(var18111111, world).y));
                            player.inventoryExt.onInventoryChanged();
                        }
                    }
                }
                else
                {
                    nbt.setLong("ptime", ptime_ + 3600000L);
                    this.generatenewcoords(nbt, world);
                    player.inventoryExt.onInventoryChanged();
                }
            }
            else if (nbt.hasKey("ptime") && this.CoordClient.isInCube(new Coords((float)x1, (float)y1, (float)z1), 30))
            {
                LavaChestPlate.proxy.smokeSpiral(player.player.worldObj, (double)x1, (double)y1 + 0.5D, (double)z1, 1.5F, world.rand.nextInt(360), y1);
                float var161111111 = world.rand.nextFloat() * 360.0F;
                float var17 = -MathHelper.sin(var161111111 / 180.0F * (float)Math.PI) / 5.0F;
                float var181111111 = MathHelper.cos(var161111111 / 180.0F * (float)Math.PI) / 5.0F;
                world.spawnParticle("smoke", (double)x1, (double)y1 + 0.10000000149011612D, (double)z1, (double)var17, 0.0D, (double)var181111111);
            }
        }
    }

    private void generatenewcoords(NBTTagCompound nbt, World world)
    {
        nbt.setShort("pY", (short)70);
        nbt.setShort("pX", (short)(world.rand.nextInt(7600) - 3800));
        nbt.setShort("pZ", (short)(world.rand.nextInt(7600) - 3800));
    }
}

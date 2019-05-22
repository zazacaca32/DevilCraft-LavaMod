package net.minecraft.client.addon.tchestplate.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.ClientProxy;
import net.minecraft.client.addon.tchestplate.IFastRightClickItem;
import net.minecraft.client.addon.tchestplate.ILavaItem;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.packets.PacketMAUpdateInt;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class ItemCP extends MultiItemBase implements IFastRightClickItem
{
    public ItemCP(int par1, int count)
    {
        super(par1, count);
        this.setCreativeTab(LavaChestPlate.tabItemss);
    }

    public boolean onEntitySwing(EntityLiving entityLiving, ItemStack stack)
    {
        return true;
    }

    public boolean FastRightClick(EntityPlayer entityPlayer, ItemStack stack)
    {
        int damage = stack.getItemDamage();

        if (damage > 6)
        {
            return false;
        }
        else
        {
            --stack.stackSize;

            if (!entityPlayer.worldObj.isRemote)
            {
                damage = (damage + 1) * 50;
                ItemStack[] arr$ = entityPlayer.getLastActiveItems();
                int len$ = arr$.length;

                for (int i$ = 0; i$ < len$; ++i$)
                {
                    ItemStack armor = arr$[i$];

                    if (armor != null && armor.getItem() instanceof ILavaItem)
                    {
                        ((ILavaItem)armor.getItem()).charge(armor, damage);
                    }
                }

                ((EntityPlayerMP)entityPlayer).getServerForPlayer().getEntityTracker().sendPacketToAllAssociatedPlayers(entityPlayer, (new PacketMAUpdateInt((byte)6, entityPlayer.entityId)).makePacket());
            }
            else
            {
                ClientProxy.blockSparkle1(entityPlayer.worldObj, entityPlayer.posX - 0.5D, entityPlayer.posY - 1.0D, entityPlayer.posZ - 0.5D, 1, 4);
            }

            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        par2List.add("§a" + "Восстанавливает по ".concat(String.valueOf((par1ItemStack.getItemDamage() + 1) * 50)).concat("лэ на каждой лава броне"));
        par2List.add("§8Итем может использоваться горячей клавишей");
        par2List.add("§8он должен лежать в панеле быстрого доступа");
        par2List.add("§8не обязательно должен находиться в руке");
        par2List.add("§8клавиша настраивается в настроиках игры");
    }
}

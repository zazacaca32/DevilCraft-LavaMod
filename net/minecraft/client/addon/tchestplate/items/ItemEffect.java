package net.minecraft.client.addon.tchestplate.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.fx.manager.DeusCloak;
import net.minecraft.client.addon.tchestplate.fx.manager.ManagerFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;

public class ItemEffect extends MultiItemBase
{
    public ItemEffect(int i, int j, int count)
    {
        super(i, count);
        super.maxStackSize = j;
        this.setCreativeTab(LavaChestPlate.tabItemss);
    }

    @ForgeSubscribe
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par2World.isRemote)
        {
            switch (par1ItemStack.getItemDamage())
            {
                case 0:
                    DeusCloak fx = new DeusCloak(30000, (byte)50);
                    ManagerFX.sendEffectToClientsAllPlayers((EntityPlayerMP)par3EntityPlayer, fx);
            }
        }

        return par1ItemStack;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        byte pr = 0;
        super.addInformation(par1ItemStack, par2EntityPlayer, par2List, par4);
        par2List.add("§6 Ускоряет производство материи");

        switch (par1ItemStack.getItemDamage())
        {
            case 0:
                pr = 1;

            default:
                par2List.add("§6 в Синтезаторе материи на " + pr + " %");
        }
    }
}

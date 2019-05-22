package net.minecraft.client.addon.Tchat;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;

public class classpalkaPrivate extends Item
{
    private boolean flag = true;

    public classpalkaPrivate(int par1)
    {
        super(par1);
        super.maxStackSize = 1;
        this.setMaxDamage(100000);
        super.bFull3D = true;
        this.setCreativeTab(LavaChestPlate.tabArmor);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.itemIcon = par1IconRegister.registerIcon("palka:palka");
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 100000;
    }

    @ForgeSubscribe
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (par2World.isRemote)
        {
            Minecraft mc = ModLoader.getMinecraftInstance();

            if (this.flag)
            {
                this.flag = false;
                mc.thePlayer.sendQueue.addToSendQueue(new Packet3Chat("/rg info -s"));
                mc.thePlayer.sendQueue.addToSendQueue(new Packet3Chat("/"));
            }
            else
            {
                this.flag = true;
                mc.thePlayer.sendQueue.addToSendQueue(new Packet3Chat("//sel"));
                mc.thePlayer.sendQueue.addToSendQueue(new Packet3Chat("/"));
            }
        }

        return par1ItemStack;
    }
}

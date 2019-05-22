package net.minecraft.client.addon.tchestplate;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tchestplate.armors.items.LavaChestPlateItem;
import net.minecraft.client.addon.tchestplate.donate.containers.DonateAdmConfigContainer;
import net.minecraft.client.addon.tchestplate.donate.containers.DonateAdmContainerScroll;
import net.minecraft.client.addon.tchestplate.donate.containers.DonateContainerScroll;
import net.minecraft.client.addon.tchestplate.fx.manager.ListenerServerHack;
import net.minecraft.client.addon.tchestplate.items.containers.ContainerItemPets;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtContainer;
import net.minecraft.client.addon.tchestplate.packets.PacketInvisible;
import net.minecraft.client.addon.tchestplate.packets.PacketMAOpenGui;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import org.spigotmc.CustomTimingsHandler;

public class ServerProxy implements IGuiHandler
{
    public CustomTimingsHandler serverTickTimer;
    public CustomTimingsHandler serverHurtTimer;

    public void burst(World var1, double var2, double var4, double var6, float var8)
    {
    }

    public void wispFX(World var1, double var2, double var4, double var6, float var8, float var9, float var10, float var11)
    {
    }

    public void register_renderers()
    {
    }

    public void bolt(World var1, Entity var2, Entity var3)
    {
    }

    public void sparkle(float var1, float var2, float var3, float var4, int var5, float var6, int var7, boolean var8, boolean var9, boolean var10)
    {
    }

    public void sendCustomPacketToAllNear(Packet var1, double var2, Entity var4)
    {
        if (var4 != null)
        {
            ModLoader.getMinecraftServerInstance().getConfigurationManager().sendToAllNear(var4.posX, var4.posY, var4.posZ, var2, var4.worldObj.provider.dimensionId, var1);
        }
    }

    public void setInvisible(EntityPlayer player, boolean invis)
    {
        if (player.inventory.armorInventory[0] != null && player.inventory.armorInventory[1] != null && player.inventory.armorInventory[2] != null && player.inventory.armorInventory[3] != null)
        {
            if (player.inventory.armorInventory[0].itemID == LavaChestPlate.lavaPredatorPlateBoots.itemID && player.inventory.armorInventory[1].itemID == LavaChestPlate.lavaPredatorPlateLeggins.itemID && player.inventory.armorInventory[2].itemID == LavaChestPlate.lavaPredatorPlateChest.itemID && player.inventory.armorInventory[3].itemID == LavaChestPlate.lavaPredatorPlateHelmet.itemID)
            {
                if (LavaChestPlateItem.getChargeInvis(player.inventory.armorInventory[3]).intValue() >= 50)
                {
                    player.setInvisible(invis);
                    PacketDispatcher.sendPacketToPlayer((new PacketInvisible(invis)).makePacket(), (Player)player);
                }
                else if (LavaChestPlateItem.getChargeInvis(player.inventory.armorInventory[3]).intValue() < 50)
                {
                    player.setInvisible(false);
                    PacketDispatcher.sendPacketToPlayer((new PacketInvisible(false)).makePacket(), (Player)player);
                }
            }
        }
        else
        {
            player.setInvisible(false);
            PacketDispatcher.sendPacketToPlayer((new PacketInvisible(false)).makePacket(), (Player)player);
        }
    }

    public void showLvLweapon(boolean var1, byte var2, float var3, float var4, float var5, float var6, float var7, float var8)
    {
    }

    public void initCore()
    {
        this.serverTickTimer = new CustomTimingsHandler("** Full Server Tick LavaChestPlate");
        this.serverHurtTimer = new CustomTimingsHandler("** Full Server HurtEvent LavaChestPlate");
        ListenerServerHack.Start();
        TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
    }

    public void runSpawnParticle(Entity var1, Entity var2, int var3)
    {
    }

    public void sparkle(float var1, float var2, float var3, float var4, int var5, float var6)
    {
    }

    public void spawnParticleFlame(double var1, double var3, double var5, double var7, double var9, double var11, boolean var13)
    {
    }

    public void sparkle(float var1, float var2, float var3, float var4, int var5, float var6, int var7)
    {
    }

    public void sparkle(float var1, float var2, float var3, float var4, int var5, float var6, int var7, boolean var8)
    {
    }

    public void smokeSpiral(World var1, double var2, double var4, double var6, float var8, int var9, int var10)
    {
    }

    public boolean movementEnabled(EntityPlayer var1, float var2, float var3, boolean var4, boolean var5)
    {
        return false;
    }

    public boolean movementDisabled(EntityPlayer var1)
    {
        return false;
    }

    public void openCustomGui(int var1, EntityPlayer var2)
    {
        var2.openGui(LavaChestPlate.instance, var1, var2.worldObj, (int)var2.posX, (int)var2.posY, (int)var2.posZ);
    }

    public void openExtGui(int var1, int var2, EntityPlayer var3)
    {
        var3.closeScreen();

        if (var1 > 3000 && var1 < 3003)
        {
            if (!MinecraftServer.getServer().getConfigurationManager().areCommandsAllowed(var3.getEntityName()))
            {
                return;
            }

            var3.openGui(LavaChestPlate.instance, var1, var3.worldObj, var2, 0, 0);
        }
        else if (var1 != 2000)
        {
            var3.openGui(LavaChestPlate.instance, var1, var3.worldObj, (int)var3.posX, (int)var3.posY, (int)var3.posZ);
        }

        PacketDispatcher.sendPacketToPlayer((new PacketMAOpenGui(var1)).makePacket(), (Player)var3);
    }

    public Object getClientGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
        return null;
    }

    public Object getServerGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
        return var1 == 11 ? new ContainerItemPets(var2.inventory, var3, var4, var5, var6, ExtendedPlayer.get(var2)) : (var1 == 3000 ? new ArmorExtContainer(var2.inventory, ExtendedPlayer.get(var2).inventoryExt)  : (var1 == 3001 ? new DonateAdmContainerScroll(var2.inventory, ExtendedPlayer.get(var2).inventoryExt) : (var1 == 3002 ? new DonateAdmConfigContainer(var2.inventory, ExtendedPlayer.get(var2).inventoryExt, var4) : (var1 == 3003 ? new DonateContainerScroll(var2.inventory, ExtendedPlayer.get(var2).inventoryExt) : null))));
    }

    public void setState(byte var1)
    {
    }

    public void spawnparts(World w, ExtendedPlayer eplayer, int effectid)
    {
    }
}

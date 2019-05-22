package net.minecraft.client.addon.lavamobs.tile;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.lavamobs.particle.PortalPart;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileLavaPortal
  extends TileEntity
{
  public boolean flag;
  public TileLavaPortal.Location location;
  
  public TileLavaPortal()
  {
    this.flag = false;
    
    this.location = new TileLavaPortal.Location();
  }
  
  public boolean addLocation(double x, double y, double z, float yaw)
  {
    if (this.flag) {
      return false;
    }
    this.location.x = x;
    this.location.y = y;
    this.location.z = z;
    this.location.yaw = yaw;
    this.flag = true;
    PacketDispatcher.sendPacketToAllAround(this.xCoord, this.yCoord, this.zCoord, 50.0D, this.worldObj.provider.dimensionId, getDescriptionPacket());
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public void updateEntity()
  {
    if (this.worldObj.isRemote) {
      if (this.flag) {
        for (int i = 0; i < 5; i++) {
          SetEffect(this.worldObj, this.xCoord + 0.5F, this.yCoord + 0.8F, this.zCoord + 0.5F, this.xCoord + 0.25F + this.worldObj.rand.nextFloat() * 0.5F, this.yCoord + 0.8F, this.zCoord + 0.25F + this.worldObj.rand.nextFloat() * 0.5F, 0.55F, 4, true, -0.025F);
        }
      } else {
        SetEffect(this.worldObj, this.xCoord + 0.5F, this.yCoord + 0.8F, this.zCoord + 0.5F, this.xCoord + 0.25F + this.worldObj.rand.nextFloat() * 0.5F, this.yCoord + 0.8F, this.zCoord + 0.25F + this.worldObj.rand.nextFloat() * 0.5F, 0.55F, 5, true, -0.025F);
      }
    }
  }
  
  @SideOnly(Side.CLIENT)
  public void SetEffect(World worldObj, double posX, double posY, double posZ, double posX2, double posY2, double posZ2, float size, int type, boolean shrink, float gravity)
  {
    Entity en = Minecraft.getMinecraft().thePlayer;
    double d3 = posX - en.posX;
    double d4 = posY - en.posY;
    double d5 = posZ - en.posZ;
    int dd = (int)(d3 * d3 + d4 * d4 + d5 * d5);
    
    if (dd > 0)
    {
      int r = Minecraft.getMinecraft().theWorld.rand.nextInt(dd);
      if (r > 50) {
        return;
      }
    }
    PortalPart ef = new PortalPart(worldObj, posX, posY, posZ, posX2, posY2, posZ2, size, type);
    ef.setGravity(gravity);
    ef.shrink = shrink;
    ModLoader.getMinecraftInstance().effectRenderer.addEffect(ef);
  }
  
  public void readFromNBT(NBTTagCompound compound)
  {
    super.readFromNBT(compound);
    this.flag = compound.getBoolean("Flag");
    NBTTagCompound location = (NBTTagCompound)compound.getTag("Location");
    this.location.x = location.getDouble("X");
    this.location.y = location.getDouble("Y");
    this.location.z = location.getDouble("Z");
    this.location.yaw = location.getFloat("YAW");
  }
  
  public void writeToNBT(NBTTagCompound compound)
  {
    super.writeToNBT(compound);
    compound.setBoolean("Flag", this.flag);
    NBTTagCompound location = new NBTTagCompound();
    location.setDouble("X", this.location.x);
    location.setDouble("Y", this.location.y);
    location.setDouble("Z", this.location.z);
    location.setFloat("YAW", this.location.yaw);
    compound.setTag("Location", location);
  }
  
  public Packet getDescriptionPacket()
  {
    NBTTagCompound data = new NBTTagCompound();
    writeToNBT(data);
    Packet132TileEntityData packet = new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, data);
    return packet;
  }
  
  public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
  {
	  readFromNBT(pkt.customParam1);
  }
  
  public class Location
  {
    public double x;
    public double y;
    public double z;
    public float yaw;
    
    public Location() {}
  }
}

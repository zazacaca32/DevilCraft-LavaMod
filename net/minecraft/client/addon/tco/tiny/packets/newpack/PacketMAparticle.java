package net.minecraft.client.addon.tco.tiny.packets.newpack;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMA;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketMAparticle extends PacketMA {

   private int ID;
   private int iDPlayer;
   private int IDEntity;


   public PacketMAparticle(int iDPlayer, int IDEntity, int ID) {
      this.ID = ID;
      this.iDPlayer = iDPlayer;
      this.IDEntity = IDEntity;
   }

   public PacketMAparticle() {}

   protected void write(ByteArrayDataOutput out) {
      out.writeInt(this.ID);
      out.writeInt(this.iDPlayer);
      out.writeInt(this.IDEntity);
   }

   protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException {
      this.ID = in.readInt();
      this.iDPlayer = in.readInt();
      this.IDEntity = in.readInt();
   }

   protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException {
      if(side.isClient()) {
         try {
            Entity pl = player.worldObj.getEntityByID(this.iDPlayer);
            Entity entity = player.worldObj.getEntityByID(this.IDEntity);
            LavaChestPlate.proxy.runSpawnParticle(pl, entity, this.ID);
         } catch (Exception var5) {
            ;
         }

      } else {
         throw new PacketMA.ProtocolException("Cannot send this packet to the server!");
      }
   }

   protected void length(int length) {}
}

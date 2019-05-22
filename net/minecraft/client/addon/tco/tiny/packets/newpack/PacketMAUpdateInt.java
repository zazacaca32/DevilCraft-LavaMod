package net.minecraft.client.addon.tco.tiny.packets.newpack;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerLavaAnvil;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTraderClose;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTraderCloseInfo;
import net.minecraft.client.addon.tco.tiny.blocks.containers.MEContainerBase;
import net.minecraft.client.addon.tco.tiny.entity.containers.ContainerMerchant;
import net.minecraft.client.addon.tco.tiny.items.Lavapad;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMA;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;

public class PacketMAUpdateInt extends PacketMA {

   private byte ID;
   private int idInt;


   public PacketMAUpdateInt(byte ID, int idInt) {
      this.ID = ID;
      this.idInt = idInt;
   }

   public PacketMAUpdateInt() {}

   protected void write(ByteArrayDataOutput out) {
      out.writeByte(this.ID);
      out.writeInt(this.idInt);
   }

   protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException {
      this.ID = in.readByte();
      this.idInt = in.readInt();
   }

   protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException {
      if(side.isServer()) {
         try {
            if(this.ID == 1 && player.getCurrentEquippedItem().getItem() instanceof Lavapad) {
               ((Lavapad)player.getCurrentEquippedItem().getItem()).updateLvL(player.getCurrentEquippedItem(), this.idInt);
            }

            EntityPlayerMP pmp;
            Container c;
            if(this.ID == 2) {
               pmp = (EntityPlayerMP)player;
               c = pmp.openContainer;
               if(c != null && c instanceof MEContainerBase) {
                  ((MEContainerBase)c).HandleUpdateInt(this.idInt, pmp);
               }
            }

            if(this.ID == 3) {
               pmp = (EntityPlayerMP)player;
               c = pmp.openContainer;
               if(c != null && c instanceof ContainerLavaAnvil) {
                  ((ContainerLavaAnvil)c).tileentity.startOffers(pmp);
               }
            }

            if(this.ID == 4) {
               pmp = (EntityPlayerMP)player;
               c = pmp.openContainer;
               if(c != null && c instanceof ContainerMerchant) {
                  ((ContainerMerchant)c).setCurrentRecipeIndex(this.idInt);
               }
            }

            if(this.ID == 5) {
               pmp = (EntityPlayerMP)player;
               c = pmp.openContainer;
               if(c != null && c instanceof ContainerTraderClose) {
                  pmp.openGui(Tiny.instance, 10030, player.worldObj, ((ContainerTraderClose)c).tileEntity.xCoord, ((ContainerTraderClose)c).tileEntity.yCoord, ((ContainerTraderClose)c).tileEntity.zCoord);
               }
            }

            if(this.ID == 6) {
               pmp = (EntityPlayerMP)player;
               c = pmp.openContainer;
               if(c != null && c instanceof ContainerTraderCloseInfo) {
                  pmp.openGui(Tiny.instance, 10021, player.worldObj, ((ContainerTraderCloseInfo)c).tileEntity.xCoord, ((ContainerTraderCloseInfo)c).tileEntity.yCoord, ((ContainerTraderCloseInfo)c).tileEntity.zCoord);
               }
            }
         } catch (Exception var5) {
            ;
         }

      } else {
         throw new PacketMA.ProtocolException("Cannot send this packet to the client!");
      }
   }

   protected void length(int length) {}
}

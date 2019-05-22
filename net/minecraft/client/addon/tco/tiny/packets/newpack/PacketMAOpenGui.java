package net.minecraft.client.addon.tco.tiny.packets.newpack;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMA;
import net.minecraft.entity.player.EntityPlayer;

public class PacketMAOpenGui extends PacketMA {

   private int modGuiId;
   short x;
   short y;
   short z;


   public PacketMAOpenGui(int modGuiId, short x, short y, short z) {
      this.modGuiId = modGuiId;
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public PacketMAOpenGui() {}

   protected void write(ByteArrayDataOutput out) {
      out.writeInt(this.modGuiId);
      out.writeShort(this.x);
      out.writeShort(this.y);
      out.writeShort(this.z);
   }

   protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException {
      try {
         this.modGuiId = in.readInt();
         this.x = in.readShort();
         this.y = in.readShort();
         this.z = in.readShort();
      } catch (Exception var3) {
         ;
      }

   }

   protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException {
      this.executeServer(player, side);
   }

   private void executeServer(EntityPlayer player, Side side) {}

   protected void length(int length) {}
}

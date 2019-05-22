package net.minecraft.client.addon.tco.tiny.packets;

import com.google.common.io.ByteArrayDataInput;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.gui.PacketReciving;
import net.minecraft.client.addon.tco.tiny.packets.PacketTiny;
import net.minecraft.client.addon.tco.tiny.playermod.GuiEnum;
import net.minecraft.client.gui.GuiScreen;

public class Packet3VoteClan extends PacketTiny {

   int IDEntity;
   GuiEnum GuiName;
   int Vote;
   int isRead;


   public void Write(int IDEntity, GuiEnum GuiName, int isRead) {
      this.IDEntity = IDEntity;
      this.GuiName = GuiName;
      this.isRead = isRead;
      this.Write();
   }

   protected void Write() {
      super.out.writeByte(3);
      super.out.writeInt(this.isRead);
      super.out.writeShort(this.IDEntity);
      super.out.writeByte(this.GuiName.ordinal());
      super.Write();
   }

   public void Read(ByteArrayDataInput in) {
      this.IDEntity = in.readUnsignedShort();
      this.GuiName = GuiEnum.values()[in.readByte()];
      this.Vote = in.readInt();
   }

   public void ExecuteRead() {
      GuiScreen gs = Minecraft.getMinecraft().currentScreen;
      if(gs != null && gs instanceof PacketReciving && ((PacketReciving)gs).getEntityID() == this.IDEntity && ((PacketReciving)gs).getGuiName() == this.GuiName) {
         String _gs = (String)((PacketReciving)gs).GetRecived();
         _gs = _gs.replace("infk0", String.valueOf(this.Vote));
         ((PacketReciving)gs).Recived(new Object[]{_gs});
         _gs = null;
      }

   }
}

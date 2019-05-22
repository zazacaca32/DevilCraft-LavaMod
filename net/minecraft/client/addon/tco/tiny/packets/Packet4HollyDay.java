package net.minecraft.client.addon.tco.tiny.packets;

import com.google.common.io.ByteArrayDataInput;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.gui.PacketReciving;
import net.minecraft.client.addon.tco.tiny.packets.PacketTiny;
import net.minecraft.client.addon.tco.tiny.playermod.GuiEnum;
import net.minecraft.client.gui.GuiScreen;

public class Packet4HollyDay extends PacketTiny {

   int IDEntity;
   GuiEnum GuiName;
   int Time;


   public void Write(int IDEntity, GuiEnum GuiName, int Time) {
      this.IDEntity = IDEntity;
      this.GuiName = GuiName;
      this.Time = Time;
      this.Write();
   }

   public void Read(ByteArrayDataInput in) {
      this.IDEntity = in.readUnsignedShort();
      this.GuiName = GuiEnum.values()[in.readByte()];
      this.Time = in.readInt();
   }

   protected void Write() {
      super.out.writeByte(4);
      super.out.writeInt(this.Time);
      super.out.writeShort(this.IDEntity);
      super.out.writeByte(this.GuiName.ordinal());
      super.Write();
   }

   public void ExecuteRead() {
      GuiScreen gs = Minecraft.getMinecraft().currentScreen;
      if(gs != null && gs instanceof PacketReciving && ((PacketReciving)gs).getEntityID() == this.IDEntity && ((PacketReciving)gs).getGuiName() == this.GuiName) {
         String _gs = "Нет свободного места.";
         if(this.Time != 0) {
            _gs = this.Time < 0?"Вы получили приз поздравляем...":"Вы сможете забрать приз через " + this.Time + " мин.";
         }

         ((PacketReciving)gs).Recived(new Object[]{_gs});
         _gs = null;
      }

   }
}

package net.minecraft.client.addon.tco.tiny.packets.old;

import com.google.common.io.ByteArrayDataInput;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.gui.PacketReciving;
import net.minecraft.client.addon.tco.tiny.packets.old.PacketTiny;
import net.minecraft.client.addon.tco.tiny.playermod.GuiEnum;
import net.minecraft.client.gui.GuiScreen;

public class Packet9priz extends PacketTiny {

   int IDEntity;
   GuiEnum GuiName;
   byte ItemIDpos = -1;
   int Vote;


   public void Write(int IDEntity, GuiEnum GuiName, int Vote, byte ItemIDpos) {
      this.IDEntity = IDEntity;
      this.GuiName = GuiName;
      this.Vote = Vote;
      this.ItemIDpos = ItemIDpos;
      this.Write();
   }

   public void Read(ByteArrayDataInput in) {
      this.IDEntity = in.readUnsignedShort();
      this.GuiName = GuiEnum.values()[in.readByte()];
      this.Vote = in.readInt();
      this.ItemIDpos = in.readByte();
   }

   protected void Write() {
      super.out.writeByte(9);
      super.out.writeInt(this.Vote);
      super.out.writeByte(this.ItemIDpos);
      super.out.writeShort(this.IDEntity);
      super.out.writeByte(this.GuiName.ordinal());
      super.Write();
   }

   public void ExecuteRead() {
      GuiScreen gs = Minecraft.getMinecraft().currentScreen;
      if(gs != null && gs instanceof PacketReciving && ((PacketReciving)gs).getEntityID() == this.IDEntity && ((PacketReciving)gs).getGuiName() == this.GuiName) {
         ((PacketReciving)gs).Recived(new Object[]{Integer.valueOf(this.Vote)});
      }

   }
}

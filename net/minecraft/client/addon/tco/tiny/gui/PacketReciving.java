package net.minecraft.client.addon.tco.tiny.gui;

import net.minecraft.client.addon.tco.tiny.playermod.GuiEnum;

public interface PacketReciving {

   void Recived(Object ... var1);

   int getEntityID();

   Object GetRecived();

   GuiEnum getGuiName();
}

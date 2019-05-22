package net.minecraft.client.addon.tco.tiny.packets;

import com.google.common.io.ByteArrayDataInput;

public abstract class PacketTinyAbstract {

   protected abstract void Write();

   public abstract void Read(ByteArrayDataInput var1);

   public abstract void ExecuteRead();
}

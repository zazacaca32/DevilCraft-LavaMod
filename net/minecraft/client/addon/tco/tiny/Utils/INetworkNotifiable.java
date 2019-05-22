package net.minecraft.client.addon.tco.tiny.Utils;

import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;

public interface INetworkNotifiable {

   void notifyExtractItems(LAItemStack var1);

   void notifyAddItems(LAItemStack var1);

   String getName();
}

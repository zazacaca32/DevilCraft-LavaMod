package net.minecraft.client.addon.tco.tiny;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkEnergyControler;

public interface IDarkNode {

   int getIDNode();

   void update(TileEntityBlockDarkEnergyControler var1);

   void stop(TileEntityBlockDarkEnergyControler var1);
}

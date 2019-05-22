package net.minecraft.client.addon.tchestplate.items;

import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;

public interface IRenderItemChestPlate
{
    BaseItemModel getItemModel(int var1);

    BaseItemModel getItemModelStatic(int var1);
}

package codechicken.nei.api;

import codechicken.nei.api.ItemInfo;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public interface IHighlightHandler {

   ItemStack identifyHighlight(World var1, EntityPlayer var2, MovingObjectPosition var3);

   List handleTextData(ItemStack var1, World var2, EntityPlayer var3, MovingObjectPosition var4, List var5, ItemInfo.Layout var6);
}

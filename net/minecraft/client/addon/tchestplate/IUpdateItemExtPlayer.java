package net.minecraft.client.addon.tchestplate;

import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IUpdateItemExtPlayer
{
    void onUpdate(ItemStack var1, World var2, ExtendedPlayer var3, int var4, boolean var5, long var6, IUpdateItemExtPlayer.SideExt var8);

    public static enum SideExt
    {
        INV("INV", 0, "INV", 0, "INV", 0, "INV", 0, "INV", 0, "INV", 0, "INV", 0),
        ARM("ARM", 1, "ARM", 1, "ARM", 1, "ARM", 1, "ARM", 1, "ARM", 1, "ARM", 1),
        EXT("EXT", 2, "EXT", 2, "EXT", 2, "EXT", 2, "EXT", 2, "EXT", 2, "EXT", 2);

        private static final IUpdateItemExtPlayer.SideExt[] $VALUES;
        private static final IUpdateItemExtPlayer.SideExt[] $VALUES$;
        private static final IUpdateItemExtPlayer.SideExt[] $VALUES$$;
        private static final IUpdateItemExtPlayer.SideExt[] $VALUES$$$;
        private static final IUpdateItemExtPlayer.SideExt[] $VALUES$$$$;
        private static final IUpdateItemExtPlayer.SideExt[] $VALUES$$$$$;

        private SideExt(String var11, int var21, String var111, int var211, String var11111, int var11211, String vaar11111, int var21111, String var1111, int var2111, String s, int n, String var111111, int var211111)
        {
        }

        static {
            $VALUES = new IUpdateItemExtPlayer.SideExt[]{INV, ARM, EXT};
            $VALUES$ = new IUpdateItemExtPlayer.SideExt[]{INV, ARM, EXT};
            $VALUES$$ = new IUpdateItemExtPlayer.SideExt[]{INV, ARM, EXT};
            $VALUES$$$ = new IUpdateItemExtPlayer.SideExt[]{INV, ARM, EXT};
            $VALUES$$$$ = new IUpdateItemExtPlayer.SideExt[]{INV, ARM, EXT};
            $VALUES$$$$$ = new IUpdateItemExtPlayer.SideExt[]{INV, ARM, EXT};
        }
    }
}

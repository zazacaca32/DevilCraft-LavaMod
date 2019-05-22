package net.minecraft.client.addon.tchestplate.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class BaseSlotInv extends Slot
{
    public BaseSlotInv.Access access;

    public BaseSlotInv(BaseSlotInv.Access access, IInventory par1iInventory, int SlotIndex, int X, int Y)
    {
        super(par1iInventory, SlotIndex, X, Y);
        this.access = access;
    }

    public abstract boolean accept(ItemStack var1);

    public boolean isItemValid(ItemStack stack)
    {
        return this.canInput() && this.accept(stack);
    }

    public boolean canTakeStack(EntityPlayer player)
    {
        return this.canOutput();
    }

    public boolean canInput()
    {
        return this.access == BaseSlotInv.Access.I || this.access == BaseSlotInv.Access.IO;
    }

    public static boolean canInput(BaseSlotInv.Access access)
    {
        return access == BaseSlotInv.Access.I || access == BaseSlotInv.Access.IO;
    }

    public boolean canOutput()
    {
        return this.access == BaseSlotInv.Access.O || this.access == BaseSlotInv.Access.IO;
    }

    public static enum Access
    {
        NONE("NONE", 0, "NONE", 0, "NONE", 0, "NONE", 0, "NONE", 0, "NONE", 0),
        I("I", 1, "I", 1, "I", 1, "I", 1, "I", 1, "I", 1),
        O("O", 2, "O", 2, "O", 2, "O", 2, "O", 2, "O", 2),
        IO("IO", 3, "IO", 3, "IO", 3, "IO", 3, "IO", 3, "IO", 3);

        private static final BaseSlotInv.Access[] $VALUES;
        private static final BaseSlotInv.Access[] $VALUES$;
        private static final BaseSlotInv.Access[] $VALUES$$;
        private static final BaseSlotInv.Access[] $VALUES$$$;
        private static final BaseSlotInv.Access[] $VALUES$$$$;

        private Access(String var11, int var21, String rats, int asd, String var11111, int var2111111, String var111, int var211, String s, int n, String var1111, int var2111)
        {
        }

        static {
            $VALUES = new BaseSlotInv.Access[]{NONE, I, O, IO};
            $VALUES$ = new BaseSlotInv.Access[]{NONE, I, O, IO};
            $VALUES$$ = new BaseSlotInv.Access[]{NONE, I, O, IO};
            $VALUES$$$ = new BaseSlotInv.Access[]{NONE, I, O, IO};
            $VALUES$$$$ = new BaseSlotInv.Access[]{NONE, I, O, IO};
        }
    }
}

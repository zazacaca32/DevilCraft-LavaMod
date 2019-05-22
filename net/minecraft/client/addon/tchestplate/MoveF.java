package net.minecraft.client.addon.tchestplate;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInput;

@SideOnly(Side.CLIENT)
public class MoveF extends MovementInput
{
    private GameSettings gameSettings;
    public float ForwardDelta = 1.0F;
    public float StrafeDelta = 1.0F;
    public boolean JumpDelta = true;
    public boolean SneakDelta = true;

    public MoveF(GameSettings var1)
    {
        this.gameSettings = var1;
    }

    public void updatePlayerMoveState()
    {
        super.moveStrafe = 0.0F;
        super.moveForward = 0.0F;

        if (this.gameSettings.keyBindForward.pressed)
        {
            ++super.moveForward;
        }

        if (this.gameSettings.keyBindBack.pressed)
        {
            --super.moveForward;
        }

        if (this.gameSettings.keyBindLeft.pressed)
        {
            ++super.moveStrafe;
        }

        if (this.gameSettings.keyBindRight.pressed)
        {
            --super.moveStrafe;
        }

        super.sneak = this.gameSettings.keyBindSneak.pressed;
        super.moveStrafe *= this.StrafeDelta;
        super.moveForward *= this.ForwardDelta;

        if (!this.JumpDelta)
        {
            super.jump = false;
        }

        if (!this.SneakDelta)
        {
            super.sneak = false;
        }

        if (super.sneak)
        {
            super.moveStrafe = (float)((double)super.moveStrafe * 0.3D);
            super.moveForward = (float)((double)super.moveForward * 0.3D);
        }
    }
}

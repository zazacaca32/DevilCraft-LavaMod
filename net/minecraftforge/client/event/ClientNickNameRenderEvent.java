package net.minecraftforge.client.event;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraftforge.event.Event;

public class ClientNickNameRenderEvent extends Event
{
    public final EntityLiving par1EntityLiving;
    public RenderManager renderManager;
    public String par2Str;
    public double par3;
    public double par5;
    public double par7;
    public int par9;

    public ClientNickNameRenderEvent(EntityLiving par1EntityLiving, String par2Str, double par3, double par5, double par7, int par9, RenderManager renderManager)
    {
        this.par1EntityLiving = par1EntityLiving;
        this.par2Str = par2Str;
        this.par3 = par3;
        this.par5 = par5;
        this.par7 = par7;
        this.par9 = par9;
        this.renderManager = renderManager;
    }
}

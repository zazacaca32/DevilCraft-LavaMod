package net.minecraft.client.addon.lavamobs.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockMobSound extends Block
{
    @SideOnly(Side.CLIENT)
    private Icon icon;
    int j = 600;

    public BlockMobSound(int par1)
    {
        super(par1, Material.rock);
        this.setBlockUnbreakable();
        this.setStepSound(new StepSound("stone", 1.0F, 1.0F));
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("MobSoundBlock");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.blockIcon = par1IconRegister.registerIcon("lavamobs:lavaspawner");
    }

    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        --this.j;

        if (this.j <= 0)
        {
            this.j = 600;
            par1World.playSound((double)par2, (double)par3, (double)par4, "lavamobs.catacomb", 1.0F, 1.0F, false);
        }
    }
}

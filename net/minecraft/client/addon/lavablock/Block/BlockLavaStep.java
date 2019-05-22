package net.minecraft.client.addon.lavablock.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockLavaStep extends BlockHalfSlab
{
    @SideOnly(Side.CLIENT)
    private Icon theIcon;

    public BlockLavaStep(int par1, boolean par2)
    {
        super(par1, par2, Material.rock);
    }

    public String getFullSlabName(int i)
    {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.blockIcon = par1IconRegister.registerIcon("lavablock:quartzblock_black_top");
        this.theIcon = par1IconRegister.registerIcon("lavablock:quartzblock_black_side");
    }

    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return super.blockID;
    }
}

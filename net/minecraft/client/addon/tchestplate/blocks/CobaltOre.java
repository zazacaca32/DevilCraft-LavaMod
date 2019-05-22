package net.minecraft.client.addon.tchestplate.blocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.renderer.texture.IconRegister;

public class CobaltOre extends Block
{
    public CobaltOre(int id)
    {
        super(id, Material.rock);
    }

    public void registerIcons(IconRegister par1IconRegister)
    {
        super.blockIcon = par1IconRegister.registerIcon("LavaChestPlate:CobaltOre");
    }

    public int idDropped(int par1, Random par2Random, int par3)
    {
        return LavaChestPlate.itemCrystalCobalt.itemID;
    }
}

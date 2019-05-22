package net.minecraft.client.addon.tchestplate.accesories.models;

import net.minecraft.client.model.ModelRenderer;

public class ModelFineryHelmetM extends ModelBipedAccesories
{
    final String texture = "/mods/models/accesories/finerymen.png";

    public ModelFineryHelmetM()
    {
        super.textureWidth = 32;
        super.textureHeight = 32;
        super.bipedHead.cubeList.clear();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public String getTexture()
    {
        return "/mods/models/accesories/finerymen.png";
    }
}

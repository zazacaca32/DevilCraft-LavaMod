package net.minecraft.client.addon.tchestplate.items.renders;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class LModelRenderer extends ModelRenderer
{
    private boolean isDynamic = false;

    public LModelRenderer(ModelBase par1ModelBase, int par2, int par3)
    {
        super(par1ModelBase, par2, par3);
    }

    public LModelRenderer(ModelBase par1ModelBase, String name)
    {
        super(par1ModelBase, name);
    }

    public LModelRenderer(ModelBase par1ModelBase)
    {
        super(par1ModelBase);
    }

    public ModelRenderer setTextureOffset(int x, int y)
    {
        return super.setTextureOffset(x, y);
    }

    public void setDynamic(boolean isDynamic)
    {
        this.isDynamic = isDynamic;
    }

    public boolean isDynamic()
    {
        return this.isDynamic;
    }
}

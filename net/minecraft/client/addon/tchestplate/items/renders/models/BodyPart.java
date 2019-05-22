package net.minecraft.client.addon.tchestplate.items.renders.models;

import net.minecraft.client.addon.tchestplate.items.renders.LModelRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class BodyPart extends LModelRenderer
{
    public float origRotationX = 0.0F;
    public float origRotationY = 0.0F;
    public float origRotationZ = 0.0F;
    public float rotationX = 0.0F;
    public float rotationY = 0.0F;
    public float rotationZ = 0.0F;

    public BodyPart(ModelBase modelBase, int n, int n2)
    {
        super(modelBase, n, n2);
    }

    public BodyPart(ModelBase modelBase, String s)
    {
        super(modelBase, s);
    }

    public BodyPart(ModelBase modelBase)
    {
        super(modelBase);
    }

    public void setRotationChield(BodyPart bodyPart)
    {
        this.rotationX = bodyPart.rotationX;
        this.rotationY = bodyPart.rotationY;
        this.rotationZ = bodyPart.rotationZ;
    }

    public void setRotationChield(float rotationX, float rotationY, float rotationZ)
    {
        this.rotationX = rotationX;
        this.rotationY = rotationY;
        this.rotationZ = rotationZ;
    }

    public void render(float n)
    {
        super.rotateAngleX = this.origRotationX + this.rotationX;
        super.rotateAngleY = this.origRotationY + this.rotationY;
        super.rotateAngleZ = this.origRotationZ + this.rotationZ;
        super.render(n);
    }

    public void setRotationChield(ModelRenderer modelRenderer)
    {
        this.rotationX = modelRenderer.rotateAngleX;
        this.rotationY = modelRenderer.rotateAngleY;
        this.rotationZ = modelRenderer.rotateAngleZ;
    }
}

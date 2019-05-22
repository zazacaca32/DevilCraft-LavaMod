package net.minecraft.client.addon.lavamobs.bow;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;

public class ShurikenModel extends ModelBase
{
    int l = 0;
    private ModelRenderer[] Shapers;

    public ShurikenModel()
    {
        super.textureWidth = 16;
        super.textureHeight = 16;
        this.Shapers = new ModelRenderer[24];
        this.Shapers[0] = new ModelRenderer(this, 8, 4);
        this.Shapers[0].addBox(-2.0F, 6.03F, 1.25F, 1, 8, 2);
        this.Shapers[0].setRotationPoint(0.0F, 1.7F, 6.93F);
        this.Shapers[0].setTextureSize(16, 16);
        this.Shapers[0].mirror = true;
        this.setRotation(this.Shapers[0], 7.82F, 3.05F, 8.27F);
        this.Shapers[1] = new ModelRenderer(this, 5, 1);
        this.Shapers[1].addBox(-2.0F, 3.94F, 9.17F, 13, 5, 4);
        this.Shapers[1].setRotationPoint(0.0F, 9.62F, 4.84F);
        this.Shapers[1].setTextureSize(16, 16);
        this.Shapers[1].mirror = true;
        this.setRotation(this.Shapers[1], 5.74F, 0.96F, 6.19F);
        this.Shapers[2] = new ModelRenderer(this, 2, 13);
        this.Shapers[2].addBox(-4.0F, 1.86F, 7.08F, 9, 3, 5);
        this.Shapers[2].setRotationPoint(0.0F, 7.53F, 2.76F);
        this.Shapers[2].setTextureSize(16, 16);
        this.Shapers[2].mirror = true;
        this.setRotation(this.Shapers[2], 3.65F, 8.88F, 4.1F);
        this.Shapers[3] = new ModelRenderer(this, 13, 10);
        this.Shapers[3].addBox(-3.0F, 9.77F, 5.0F, 6, 0, 6);
        this.Shapers[3].setRotationPoint(0.0F, 5.45F, 0.67F);
        this.Shapers[3].setTextureSize(16, 16);
        this.Shapers[3].mirror = true;
        this.setRotation(this.Shapers[3], 1.57F, 6.79F, 2.02F);
        this.Shapers[4] = new ModelRenderer(this, 10, 8);
        this.Shapers[4].addBox(1.0F, 7.69F, 2.91F, 3, 12, 8);
        this.Shapers[4].setRotationPoint(0.0F, 3.36F, 8.59F);
        this.Shapers[4].setTextureSize(16, 16);
        this.Shapers[4].mirror = true;
        this.setRotation(this.Shapers[4], 9.48F, 4.71F, 9.93F);
        this.Shapers[5] = new ModelRenderer(this, 7, 5);
        this.Shapers[5].addBox(-3.0F, 5.61F, 0.83F, 0, 9, 9);
        this.Shapers[5].setRotationPoint(0.0F, 1.28F, 6.5F);
        this.Shapers[5].setTextureSize(16, 16);
        this.Shapers[5].mirror = true;
        this.setRotation(this.Shapers[5], 7.4F, 2.62F, 7.85F);
        this.Shapers[6] = new ModelRenderer(this, 4, 2);
        this.Shapers[6].addBox(1.0F, 3.52F, 8.74F, 12, 6, 10);
        this.Shapers[6].setRotationPoint(0.0F, 9.19F, 4.42F);
        this.Shapers[6].setTextureSize(16, 16);
        this.Shapers[6].mirror = true;
        this.setRotation(this.Shapers[6], 5.31F, 0.54F, 5.76F);
        this.Shapers[7] = new ModelRenderer(this, 1, 14);
        this.Shapers[7].addBox(0.0F, 1.44F, 6.66F, 9, 4, 12);
        this.Shapers[7].setRotationPoint(0.0F, 7.11F, 2.33F);
        this.Shapers[7].setTextureSize(16, 16);
        this.Shapers[7].mirror = true;
        this.setRotation(this.Shapers[7], 3.23F, 8.45F, 3.68F);
        this.Shapers[8] = new ModelRenderer(this, 13, 11);
        this.Shapers[8].addBox(0.0F, 9.35F, 4.58F, 6, 1, 13);
        this.Shapers[8].setRotationPoint(-2.0F, 5.02F, 0.25F);
        this.Shapers[8].setTextureSize(16, 16);
        this.Shapers[8].mirror = true;
        this.setRotation(this.Shapers[8], 1.15F, 6.37F, 1.59F);
        this.Shapers[9] = new ModelRenderer(this, 10, 9);
        this.Shapers[9].addBox(0.8F, 7.27F, 2.49F, 3, 13, 14);
        this.Shapers[9].setRotationPoint(0.0F, 2.94F, 8.16F);
        this.Shapers[9].setTextureSize(16, 16);
        this.Shapers[9].mirror = true;
        this.setRotation(this.Shapers[9], 9.06F, 4.28F, 9.51F);
        this.Shapers[10] = new ModelRenderer(this, 7, 6);
        this.Shapers[10].addBox(-1.0F, 5.18F, 0.41F, 14, 10, 0);
        this.Shapers[10].setRotationPoint(0.0F, 0.85F, 6.08F);
        this.Shapers[10].setTextureSize(16, 16);
        this.Shapers[10].mirror = true;
        this.setRotation(this.Shapers[10], 6.98F, 2.2F, 7.42F);
        this.Shapers[11] = new ModelRenderer(this, 3, 3);
        this.Shapers[11].addBox(0.0F, 3.1F, 8.32F, 11, 7, 2);
        this.Shapers[11].setRotationPoint(4.0F, 8.77F, 3.99F);
        this.Shapers[11].setTextureSize(16, 16);
        this.Shapers[11].mirror = true;
        this.setRotation(this.Shapers[11], 4.89F, 0.12F, 5.34F);
        this.Shapers[12] = new ModelRenderer(this, 0, 0);
        this.Shapers[12].addBox(-1.0F, 1.01F, 6.24F, 8, 5, 3);
        this.Shapers[12].setRotationPoint(2.0F, 6.68F, 1.91F);
        this.Shapers[12].setTextureSize(16, 16);
        this.Shapers[12].mirror = true;
        this.setRotation(this.Shapers[12], 2.81F, 8.03F, 3.25F);
        this.Shapers[13] = new ModelRenderer(this, 12, 13);
        this.Shapers[13].addBox(-6.0F, 8.93F, 4.15F, 5, 2, 4);
        this.Shapers[13].setRotationPoint(-4.0F, 4.6F, 9.82F);
        this.Shapers[13].setTextureSize(16, 16);
        this.Shapers[13].mirror = true;
        this.setRotation(this.Shapers[13], 0.72F, 5.95F, 1.17F);
        this.Shapers[14] = new ModelRenderer(this, 9, 10);
        this.Shapers[14].addBox(4.0F, 6.84F, 2.07F, 2, 14, 6);
        this.Shapers[14].setRotationPoint(0.0F, 2.52F, 7.74F);
        this.Shapers[14].setTextureSize(16, 16);
        this.Shapers[14].mirror = true;
        this.setRotation(this.Shapers[14], 8.64F, 3.86F, 9.09F);
        this.Shapers[15] = new ModelRenderer(this, 6, 7);
        this.Shapers[15].addBox(-2.0F, 4.76F, 9.98F, 14, 11, 7);
        this.Shapers[15].setRotationPoint(0.0F, 0.43F, 5.66F);
        this.Shapers[15].setTextureSize(16, 16);
        this.Shapers[15].mirror = true;
        this.setRotation(this.Shapers[15], 6.55F, 1.78F, 7.0F);
        this.Shapers[16] = new ModelRenderer(this, 3, 4);
        this.Shapers[16].addBox(-7.0F, 2.67F, 7.9F, 11, 9, 8);
        this.Shapers[16].setRotationPoint(0.0F, 8.35F, 3.57F);
        this.Shapers[16].setTextureSize(16, 16);
        this.Shapers[16].mirror = true;
        this.setRotation(this.Shapers[16], 4.47F, 9.69F, 4.92F);
        this.Shapers[17] = new ModelRenderer(this, 0, 1);
        this.Shapers[17].addBox(5.0F, 0.59F, 5.81F, 8, 6, 10);
        this.Shapers[17].setRotationPoint(0.0F, 6.26F, 1.49F);
        this.Shapers[17].setTextureSize(16, 16);
        this.Shapers[17].mirror = true;
        this.setRotation(this.Shapers[17], 2.38F, 7.61F, 2.83F);
        this.Shapers[18] = new ModelRenderer(this, 12, 14);
        this.Shapers[18].addBox(-1.8F, 8.5F, 3.73F, 4, 3, 11);
        this.Shapers[18].setRotationPoint(0.0F, 4.18F, 9.4F);
        this.Shapers[18].setTextureSize(16, 16);
        this.Shapers[18].mirror = true;
        this.setRotation(this.Shapers[18], 0.3F, 5.52F, 0.75F);
        this.Shapers[19] = new ModelRenderer(this, 8, 11);
        this.Shapers[19].addBox(-9.0F, 6.42F, 1.64F, 1, 0, 12);
        this.Shapers[19].setRotationPoint(0.0F, 2.09F, 7.32F);
        this.Shapers[19].setTextureSize(16, 16);
        this.Shapers[19].mirror = true;
        this.setRotation(this.Shapers[19], 8.21F, 3.44F, 8.66F);
        this.Shapers[20] = new ModelRenderer(this, 5, 8);
        this.Shapers[20].addBox(3.5F, 4.33F, 9.56F, 13, 12, 13);
        this.Shapers[20].setRotationPoint(0.0F, 0.01F, 5.23F);
        this.Shapers[20].setTextureSize(16, 16);
        this.Shapers[20].mirror = true;
        this.setRotation(this.Shapers[20], 6.13F, 1.35F, 6.58F);
        this.Shapers[21] = new ModelRenderer(this, 2, 5);
        this.Shapers[21].addBox(-1.0F, 2.25F, 7.47F, 10, 10, 0);
        this.Shapers[21].setRotationPoint(0.0F, 7.92F, 3.15F);
        this.Shapers[21].setTextureSize(16, 16);
        this.Shapers[21].mirror = true;
        this.setRotation(this.Shapers[21], 4.04F, 9.27F, 4.49F);
        this.Shapers[22] = new ModelRenderer(this, 14, 2);
        this.Shapers[22].addBox(-5.5F, 0.17F, 5.39F, 7, 7, 1);
        this.Shapers[22].setRotationPoint(0.0F, 5.84F, 1.06F);
        this.Shapers[22].setTextureSize(16, 16);
        this.Shapers[22].mirror = true;
        this.setRotation(this.Shapers[22], 1.96F, 7.18F, 2.41F);
        this.Shapers[23] = new ModelRenderer(this, 4, 4);
        this.Shapers[23].addBox(2.0F, 3.3F, 8.53F, 12, 8, 13);
        this.Shapers[23].setRotationPoint(0.0F, 8.98F, 4.2F);
        this.Shapers[23].setTextureSize(16, 16);
        this.Shapers[23].mirror = true;
        this.setRotation(this.Shapers[23], 5.1F, 0.32F, 5.55F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
    }

    public void render(ShurikenEntity par1EntityArrow)
    {
        float f5 = 0.0625F;

        if (par1EntityArrow.lastTickPosX != par1EntityArrow.posX)
        {
            ++this.l;
            this.Shapers[23].rotateAngleZ = (float)this.l;
        }
        else
        {
            this.l = 0;
        }

        RenderHelper.disableStandardItemLighting();
        ModelRenderer[] arr$ = this.Shapers;
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$)
        {
            ModelRenderer s = arr$[i$];
            s.render(f5);
        }

        RenderHelper.enableStandardItemLighting();
    }
}

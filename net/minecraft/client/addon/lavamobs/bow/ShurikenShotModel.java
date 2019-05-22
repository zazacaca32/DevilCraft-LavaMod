package net.minecraft.client.addon.lavamobs.bow;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ShurikenShotModel extends ModelBase
{
    private ModelRenderer[] Shapers;

    public ShurikenShotModel()
    {
        super.textureWidth = 64;
        super.textureHeight = 64;
        this.Shapers = new ModelRenderer[33];
        this.Shapers[0] = new ModelRenderer(this, 5, 2);
        this.Shapers[0].addBox(-0.5F, 3.81F, 9.03F, 12, 7, 4);
        this.Shapers[0].setRotationPoint(0.0F, 9.48F, 4.71F);
        this.Shapers[0].setTextureSize(64, 64);
        this.Shapers[0].mirror = true;
        this.setRotation(this.Shapers[0], 5.6F, 0.83F, 6.05F);
        this.Shapers[1] = new ModelRenderer(this, 1, 14);
        this.Shapers[1].addBox(-1.0F, 1.72F, 6.95F, 9, 4, 5);
        this.Shapers[1].setRotationPoint(-1.7F, 7.4F, 2.62F);
        this.Shapers[1].setTextureSize(64, 64);
        this.Shapers[1].mirror = true;
        this.setRotation(this.Shapers[1], 3.52F, 8.74F, 3.97F);
        this.Shapers[2] = new ModelRenderer(this, 13, 12);
        this.Shapers[2].addBox(-1.0F, 9.64F, 4.86F, 6, 1, 6);
        this.Shapers[2].setRotationPoint(1.7F, 5.31F, 0.54F);
        this.Shapers[2].setTextureSize(64, 64);
        this.Shapers[2].mirror = true;
        this.setRotation(this.Shapers[2], 1.43F, 6.66F, 1.88F);
        this.Shapers[3] = new ModelRenderer(this, 10, 9);
        this.Shapers[3].addBox(-0.5F, 7.55F, 2.78F, 3, 13, 8);
        this.Shapers[3].setRotationPoint(-1.5F, 3.23F, 8.45F);
        this.Shapers[3].setTextureSize(64, 64);
        this.Shapers[3].mirror = true;
        this.setRotation(this.Shapers[3], 9.35F, 4.57F, 9.8F);
        this.Shapers[4] = new ModelRenderer(this, 7, 6);
        this.Shapers[4].addBox(-0.5F, 5.47F, 0.69F, 0, 10, 9);
        this.Shapers[4].setRotationPoint(1.5F, 1.14F, 6.37F);
        this.Shapers[4].setTextureSize(64, 64);
        this.Shapers[4].mirror = true;
        this.setRotation(this.Shapers[4], 7.26F, 2.49F, 7.71F);
        this.Shapers[5] = new ModelRenderer(this, 4, 3);
        this.Shapers[5].addBox(-0.5F, 3.39F, 8.61F, 12, 8, 10);
        this.Shapers[5].setRotationPoint(-1.5F, 9.06F, 4.28F);
        this.Shapers[5].setTextureSize(64, 64);
        this.Shapers[5].mirror = true;
        this.setRotation(this.Shapers[5], 5.18F, 0.4F, 5.63F);
        this.Shapers[6] = new ModelRenderer(this, 1, 0);
        this.Shapers[6].addBox(0.5F, 1.3F, 6.52F, 9, 5, 12);
        this.Shapers[6].setRotationPoint(1.5F, 6.97F, 2.2F);
        this.Shapers[6].setTextureSize(64, 64);
        this.Shapers[6].mirror = true;
        this.setRotation(this.Shapers[6], 3.09F, 8.32F, 3.54F);
        this.Shapers[7] = new ModelRenderer(this, 13, 13);
        this.Shapers[7].addBox(-4.0F, 9.22F, 4.44F, 5, 2, 13);
        this.Shapers[7].setRotationPoint(0.0F, 4.89F, 0.11F);
        this.Shapers[7].setTextureSize(64, 64);
        this.Shapers[7].mirror = true;
        this.setRotation(this.Shapers[7], 1.01F, 6.23F, 1.46F);
        this.Shapers[8] = new ModelRenderer(this, 10, 10);
        this.Shapers[8].addBox(0.0F, 7.13F, 2.36F, 2, 14, 14);
        this.Shapers[8].setRotationPoint(0.0F, 2.8F, 8.03F);
        this.Shapers[8].setTextureSize(64, 64);
        this.Shapers[8].mirror = true;
        this.setRotation(this.Shapers[8], 8.93F, 4.15F, 9.37F);
        this.Shapers[9] = new ModelRenderer(this, 6, 7);
        this.Shapers[9].addBox(0.0F, 5.05F, 0.27F, 14, 12, 1);
        this.Shapers[9].setRotationPoint(-2.0F, 0.72F, 5.94F);
        this.Shapers[9].setTextureSize(64, 64);
        this.Shapers[9].mirror = true;
        this.setRotation(this.Shapers[9], 6.84F, 2.06F, 7.29F);
        this.Shapers[10] = new ModelRenderer(this, 3, 4);
        this.Shapers[10].addBox(0.8F, 2.96F, 8.19F, 11, 9, 2);
        this.Shapers[10].setRotationPoint(0.0F, 8.63F, 3.86F);
        this.Shapers[10].setTextureSize(64, 64);
        this.Shapers[10].mirror = true;
        this.setRotation(this.Shapers[10], 4.76F, 9.98F, 5.2F);
        this.Shapers[11] = new ModelRenderer(this, 0, 2);
        this.Shapers[11].addBox(-1.0F, 0.88F, 6.1F, 8, 6, 3);
        this.Shapers[11].setRotationPoint(0.0F, 6.55F, 1.77F);
        this.Shapers[11].setTextureSize(64, 64);
        this.Shapers[11].mirror = true;
        this.setRotation(this.Shapers[11], 2.67F, 7.9F, 3.12F);
        this.Shapers[12] = new ModelRenderer(this, 12, 14);
        this.Shapers[12].addBox(0.0F, 8.79F, 4.02F, 5, 3, 5);
        this.Shapers[12].setRotationPoint(4.0F, 4.47F, 9.69F);
        this.Shapers[12].setTextureSize(64, 64);
        this.Shapers[12].mirror = true;
        this.setRotation(this.Shapers[12], 0.59F, 5.81F, 1.03F);
        this.Shapers[13] = new ModelRenderer(this, 9, 11);
        this.Shapers[13].addBox(-6.0F, 6.71F, 1.93F, 2, 0, 6);
        this.Shapers[13].setRotationPoint(-4.0F, 2.38F, 7.6F);
        this.Shapers[13].setTextureSize(64, 64);
        this.Shapers[13].mirror = true;
        this.setRotation(this.Shapers[13], 8.5F, 3.73F, 8.95F);
        this.Shapers[14] = new ModelRenderer(this, 6, 8);
        this.Shapers[14].addBox(-6.2F, 4.62F, 9.85F, 14, 13, 7);
        this.Shapers[14].setRotationPoint(0.0F, 0.3F, 5.52F);
        this.Shapers[14].setTextureSize(64, 64);
        this.Shapers[14].mirror = true;
        this.setRotation(this.Shapers[14], 6.42F, 1.64F, 6.87F);
        this.Shapers[15] = new ModelRenderer(this, 3, 5);
        this.Shapers[15].addBox(5.2F, 2.54F, 7.76F, 10, 10, 8);
        this.Shapers[15].setRotationPoint(0.0F, 8.21F, 3.44F);
        this.Shapers[15].setTextureSize(64, 64);
        this.Shapers[15].mirror = true;
        this.setRotation(this.Shapers[15], 4.33F, 9.56F, 4.78F);
        this.Shapers[16] = new ModelRenderer(this, 0, 3);
        this.Shapers[16].addBox(-1.0F, 0.45F, 5.68F, 7, 7, 10);
        this.Shapers[16].setRotationPoint(0.0F, 6.13F, 1.35F);
        this.Shapers[16].setTextureSize(64, 64);
        this.Shapers[16].mirror = true;
        this.setRotation(this.Shapers[16], 2.25F, 7.47F, 2.7F);
        this.Shapers[17] = new ModelRenderer(this, 11, 0);
        this.Shapers[17].addBox(-7.0F, 8.37F, 3.59F, 4, 4, 11);
        this.Shapers[17].setRotationPoint(0.0F, 4.04F, 9.27F);
        this.Shapers[17].setTextureSize(64, 64);
        this.Shapers[17].mirror = true;
        this.setRotation(this.Shapers[17], 0.16F, 5.39F, 0.61F);
        this.Shapers[18] = new ModelRenderer(this, 8, 12);
        this.Shapers[18].addBox(3.1F, 6.28F, 1.51F, 1, 1, 12);
        this.Shapers[18].setRotationPoint(0.0F, 1.96F, 7.18F);
        this.Shapers[18].setTextureSize(64, 64);
        this.Shapers[18].mirror = true;
        this.setRotation(this.Shapers[18], 8.08F, 3.3F, 8.53F);
        this.Shapers[19] = new ModelRenderer(this, 5, 9);
        this.Shapers[19].addBox(-3.0F, 4.2F, 9.42F, 13, 14, 14);
        this.Shapers[19].setRotationPoint(0.0F, 9.87F, 5.1F);
        this.Shapers[19].setTextureSize(64, 64);
        this.Shapers[19].mirror = true;
        this.setRotation(this.Shapers[19], 5.99F, 1.22F, 6.44F);
        this.Shapers[20] = new ModelRenderer(this, 2, 6);
        this.Shapers[20].addBox(-9.0F, 2.11F, 7.34F, 10, 11, 0);
        this.Shapers[20].setRotationPoint(0.0F, 7.79F, 3.01F);
        this.Shapers[20].setTextureSize(64, 64);
        this.Shapers[20].mirror = true;
        this.setRotation(this.Shapers[20], 3.91F, 9.13F, 4.36F);
        this.Shapers[21] = new ModelRenderer(this, 14, 4);
        this.Shapers[21].addBox(-2.0F, 0.03F, 5.25F, 7, 8, 1);
        this.Shapers[21].setRotationPoint(0.0F, 5.7F, 0.93F);
        this.Shapers[21].setTextureSize(64, 64);
        this.Shapers[21].mirror = true;
        this.setRotation(this.Shapers[21], 1.82F, 7.05F, 2.27F);
        this.Shapers[22] = new ModelRenderer(this, 11, 1);
        this.Shapers[22].addBox(-6.0F, 7.95F, 3.17F, 4, 5, 3);
        this.Shapers[22].setRotationPoint(8.0F, 3.62F, 8.84F);
        this.Shapers[22].setTextureSize(64, 64);
        this.Shapers[22].mirror = true;
        this.setRotation(this.Shapers[22], 9.74F, 4.96F, 0.19F);
        this.Shapers[23] = new ModelRenderer(this, 8, 13);
        this.Shapers[23].addBox(-5.8F, 5.86F, 1.08F, 0, 3, 4);
        this.Shapers[23].setRotationPoint(0.0F, 1.53F, 6.76F);
        this.Shapers[23].setTextureSize(64, 64);
        this.Shapers[23].mirror = true;
        this.setRotation(this.Shapers[23], 7.65F, 2.88F, 8.1F);
        this.Shapers[24] = new ModelRenderer(this, 4, 10);
        this.Shapers[24].addBox(-2.0F, 3.78F, 9.0F, 12, 0, 5);
        this.Shapers[24].setRotationPoint(0.0F, 9.45F, 4.67F);
        this.Shapers[24].setTextureSize(64, 64);
        this.Shapers[24].mirror = true;
        this.setRotation(this.Shapers[24], 5.57F, 0.79F, 6.02F);
        this.Shapers[25] = new ModelRenderer(this, 1, 8);
        this.Shapers[25].addBox(-5.0F, 1.69F, 6.92F, 9, 12, 6);
        this.Shapers[25].setRotationPoint(0.0F, 7.36F, 2.59F);
        this.Shapers[25].setTextureSize(64, 64);
        this.Shapers[25].mirror = true;
        this.setRotation(this.Shapers[25], 3.49F, 8.71F, 3.93F);
        this.Shapers[26] = new ModelRenderer(this, 13, 5);
        this.Shapers[26].addBox(-4.0F, 9.61F, 4.83F, 6, 9, 8);
        this.Shapers[26].setRotationPoint(0.0F, 5.28F, 0.5F);
        this.Shapers[26].setTextureSize(64, 64);
        this.Shapers[26].mirror = true;
        this.setRotation(this.Shapers[26], 1.4F, 6.62F, 1.85F);
        this.Shapers[27] = new ModelRenderer(this, 10, 2);
        this.Shapers[27].addBox(-4.1F, 7.52F, 2.75F, 3, 6, 9);
        this.Shapers[27].setRotationPoint(0.0F, 3.19F, 8.42F);
        this.Shapers[27].setTextureSize(64, 64);
        this.Shapers[27].mirror = true;
        this.setRotation(this.Shapers[27], 9.32F, 4.54F, 9.76F);
        this.Shapers[28] = new ModelRenderer(this, 7, 14);
        this.Shapers[28].addBox(3.0F, 5.44F, 0.66F, 0, 4, 10);
        this.Shapers[28].setRotationPoint(0.0F, 1.11F, 6.33F);
        this.Shapers[28].setTextureSize(64, 64);
        this.Shapers[28].mirror = true;
        this.setRotation(this.Shapers[28], 7.23F, 2.46F, 7.68F);
        this.Shapers[29] = new ModelRenderer(this, 4, 11);
        this.Shapers[29].addBox(-3.0F, 3.35F, 8.58F, 12, 1, 12);
        this.Shapers[29].setRotationPoint(0.0F, 9.03F, 4.25F);
        this.Shapers[29].setTextureSize(64, 64);
        this.Shapers[29].mirror = true;
        this.setRotation(this.Shapers[29], 5.15F, 0.37F, 5.59F);
        this.Shapers[30] = new ModelRenderer(this, 1, 9);
        this.Shapers[30].addBox(-1.0F, 1.27F, 6.49F, 9, 13, 13);
        this.Shapers[30].setRotationPoint(0.0F, 6.94F, 2.16F);
        this.Shapers[30].setTextureSize(64, 64);
        this.Shapers[30].mirror = true;
        this.setRotation(this.Shapers[30], 3.06F, 8.29F, 3.51F);
        this.Shapers[31] = new ModelRenderer(this, 13, 6);
        this.Shapers[31].addBox(0.0F, 9.18F, 4.41F, 5, 10, 14);
        this.Shapers[31].setRotationPoint(-8.0F, 4.86F, 0.08F);
        this.Shapers[31].setTextureSize(64, 64);
        this.Shapers[31].mirror = true;
        this.setRotation(this.Shapers[31], 0.98F, 6.2F, 1.43F);
        this.Shapers[32] = new ModelRenderer(this, 9, 3);
        this.Shapers[32].addBox(-5.1F, 7.1F, 2.32F, 2, 7, 1);
        this.Shapers[32].setRotationPoint(0.0F, 2.77F, 8.0F);
        this.Shapers[32].setTextureSize(64, 64);
        this.Shapers[32].mirror = true;
        this.setRotation(this.Shapers[32], 8.89F, 4.12F, 9.34F);
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

    public void render(int flag)
    {
        float f5 = 0.0625F;

        for (int i = 7; i < this.Shapers.length; ++i)
        {
            this.Shapers[i].render(f5);
        }

        if (flag == 1)
        {
            this.Shapers[3].render(f5);
            this.Shapers[4].render(f5);
            this.Shapers[5].render(f5);
            this.Shapers[6].render(f5);
        }
        else if (flag == 2)
        {
            this.Shapers[0].render(f5);
            this.Shapers[1].render(f5);
            this.Shapers[3].render(f5);
            this.Shapers[5].render(f5);
            this.Shapers[6].render(f5);
        }
        else if (flag == 3)
        {
            this.Shapers[2].render(f5);
            this.Shapers[3].render(f5);
            this.Shapers[4].render(f5);
            this.Shapers[5].render(f5);
        }
        else if (flag == 4)
        {
            this.Shapers[2].render(f5);
            this.Shapers[5].render(f5);
        }
        else if (flag == 5)
        {
            this.Shapers[0].render(f5);
            this.Shapers[1].render(f5);
            this.Shapers[2].render(f5);
            this.Shapers[3].render(f5);
            this.Shapers[4].render(f5);
            this.Shapers[5].render(f5);
        }
        else if (flag == 6)
        {
            this.Shapers[0].render(f5);
            this.Shapers[1].render(f5);
            this.Shapers[2].render(f5);
            this.Shapers[3].render(f5);
        }
        else if (flag == 7)
        {
            this.Shapers[0].render(f5);
            this.Shapers[1].render(f5);
            this.Shapers[2].render(f5);
            this.Shapers[3].render(f5);
            this.Shapers[4].render(f5);
            this.Shapers[5].render(f5);
            this.Shapers[6].render(f5);
        }
    }
}

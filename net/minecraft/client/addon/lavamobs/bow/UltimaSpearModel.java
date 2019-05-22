package net.minecraft.client.addon.lavamobs.bow;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class UltimaSpearModel extends ModelBase
{
    private ModelRenderer[] Shapers;

    public UltimaSpearModel()
    {
        super.textureWidth = 32;
        super.textureHeight = 32;
        this.Shapers = new ModelRenderer[36];
        this.Shapers[0] = new ModelRenderer(this, 8, 8);
        this.Shapers[0].addBox(-0.5F, 6.21F, 1.43F, 1, 13, 11);
        this.Shapers[0].setRotationPoint(0.0F, 1.88F, 7.11F);
        this.Shapers[0].setTextureSize(32, 32);
        this.Shapers[0].mirror = true;
        this.setRotation(this.Shapers[0], 8.0F, 3.23F, 8.45F);
        this.Shapers[1] = new ModelRenderer(this, 5, 5);
        this.Shapers[1].addBox(-1.0F, 4.12F, 9.35F, 13, 10, 12);
        this.Shapers[1].setRotationPoint(0.0F, 9.8F, 5.02F);
        this.Shapers[1].setTextureSize(32, 32);
        this.Shapers[1].mirror = true;
        this.setRotation(this.Shapers[1], 5.92F, 1.14F, 6.37F);
        this.Shapers[2] = new ModelRenderer(this, 2, 3);
        this.Shapers[2].addBox(0.0F, 2.04F, 7.26F, 10, 7, 14);
        this.Shapers[2].setRotationPoint(0.0F, 7.71F, 2.94F);
        this.Shapers[2].setTextureSize(32, 32);
        this.Shapers[2].mirror = true;
        this.setRotation(this.Shapers[2], 3.83F, 9.06F, 4.28F);
        this.Shapers[3] = new ModelRenderer(this, 14, 0);
        this.Shapers[3].addBox(-1.0F, 9.95F, 5.18F, 7, 4, 0);
        this.Shapers[3].setRotationPoint(0.0F, 5.63F, 0.85F);
        this.Shapers[3].setTextureSize(32, 32);
        this.Shapers[3].mirror = true;
        this.setRotation(this.Shapers[3], 1.75F, 6.97F, 2.2F);
        this.Shapers[4] = new ModelRenderer(this, 11, 12);
        this.Shapers[4].addBox(-1.0F, 7.87F, 3.09F, 3, 2, 1);
        this.Shapers[4].setRotationPoint(0.0F, 3.54F, 8.77F);
        this.Shapers[4].setTextureSize(32, 32);
        this.Shapers[4].mirror = true;
        this.setRotation(this.Shapers[4], 9.66F, 4.89F, 0.11F);
        this.Shapers[5] = new ModelRenderer(this, 8, 9);
        this.Shapers[5].addBox(-0.5F, 5.78F, 1.01F, 0, 14, 3);
        this.Shapers[5].setRotationPoint(0.0F, 1.46F, 6.68F);
        this.Shapers[5].setTextureSize(32, 32);
        this.Shapers[5].mirror = true;
        this.setRotation(this.Shapers[5], 7.58F, 2.8F, 8.03F);
        this.Shapers[6] = new ModelRenderer(this, 4, 7);
        this.Shapers[6].addBox(-0.5F, 3.7F, 8.92F, 12, 11, 4);
        this.Shapers[6].setRotationPoint(0.0F, 9.37F, 4.6F);
        this.Shapers[6].setTextureSize(32, 32);
        this.Shapers[6].mirror = true;
        this.setRotation(this.Shapers[6], 5.49F, 0.72F, 5.94F);
        this.Shapers[7] = new ModelRenderer(this, 1, 4);
        this.Shapers[7].addBox(-1.0F, 1.62F, 6.84F, 9, 8, 5);
        this.Shapers[7].setRotationPoint(0.0F, 7.29F, 2.51F);
        this.Shapers[7].setTextureSize(32, 32);
        this.Shapers[7].mirror = true;
        this.setRotation(this.Shapers[7], 3.41F, 8.63F, 3.86F);
        this.Shapers[8] = new ModelRenderer(this, 13, 1);
        this.Shapers[8].addBox(-1.0F, 9.53F, 4.75F, 6, 5, 7);
        this.Shapers[8].setRotationPoint(0.0F, 5.2F, 0.43F);
        this.Shapers[8].setTextureSize(32, 32);
        this.Shapers[8].mirror = true;
        this.setRotation(this.Shapers[8], 1.32F, 6.55F, 1.77F);
        this.Shapers[9] = new ModelRenderer(this, 10, 13);
        this.Shapers[9].addBox(-1.0F, 7.45F, 2.67F, 3, 3, 8);
        this.Shapers[9].setRotationPoint(0.0F, 3.12F, 8.34F);
        this.Shapers[9].setTextureSize(32, 32);
        this.Shapers[9].mirror = true;
        this.setRotation(this.Shapers[9], 9.24F, 4.46F, 9.69F);
        this.Shapers[10] = new ModelRenderer(this, 7, 10);
        this.Shapers[10].addBox(-1.0F, 5.36F, 0.59F, 0, 0, 9);
        this.Shapers[10].setRotationPoint(0.0F, 1.03F, 6.26F);
        this.Shapers[10].setTextureSize(32, 32);
        this.Shapers[10].mirror = true;
        this.setRotation(this.Shapers[10], 7.16F, 2.38F, 7.6F);
        this.Shapers[11] = new ModelRenderer(this, 4, 8);
        this.Shapers[11].addBox(-1.5F, 3.28F, 8.5F, 12, 12, 10);
        this.Shapers[11].setRotationPoint(0.0F, 8.95F, 4.17F);
        this.Shapers[11].setTextureSize(32, 32);
        this.Shapers[11].mirror = true;
        this.setRotation(this.Shapers[11], 5.07F, 0.29F, 5.52F);
        this.Shapers[12] = new ModelRenderer(this, 1, 5);
        this.Shapers[12].addBox(-2.5F, 1.19F, 6.42F, 8, 9, 12);
        this.Shapers[12].setRotationPoint(0.0F, 6.86F, 2.09F);
        this.Shapers[12].setTextureSize(32, 32);
        this.Shapers[12].mirror = true;
        this.setRotation(this.Shapers[12], 2.99F, 8.21F, 3.43F);
        this.Shapers[13] = new ModelRenderer(this, 12, 2);
        this.Shapers[13].addBox(-1.5F, 9.11F, 4.33F, 5, 6, 13);
        this.Shapers[13].setRotationPoint(0.0F, 4.78F, 0.0F);
        this.Shapers[13].setTextureSize(32, 32);
        this.Shapers[13].mirror = true;
        this.setRotation(this.Shapers[13], 0.9F, 6.13F, 1.35F);
        this.Shapers[14] = new ModelRenderer(this, 9, 14);
        this.Shapers[14].addBox(-2.0F, 7.02F, 2.25F, 2, 4, 14);
        this.Shapers[14].setRotationPoint(0.0F, 2.7F, 7.92F);
        this.Shapers[14].setTextureSize(32, 32);
        this.Shapers[14].mirror = true;
        this.setRotation(this.Shapers[14], 8.82F, 4.04F, 9.26F);
        this.Shapers[15] = new ModelRenderer(this, 6, 12);
        this.Shapers[15].addBox(-1.0F, 4.94F, 0.16F, 14, 1, 1);
        this.Shapers[15].setRotationPoint(0.0F, 0.61F, 5.83F);
        this.Shapers[15].setTextureSize(32, 32);
        this.Shapers[15].mirror = true;
        this.setRotation(this.Shapers[15], 6.73F, 1.96F, 7.18F);
        this.Shapers[16] = new ModelRenderer(this, 3, 9);
        this.Shapers[16].addBox(-1.0F, 2.85F, 8.08F, 11, 13, 2);
        this.Shapers[16].setRotationPoint(0.0F, 8.53F, 3.75F);
        this.Shapers[16].setTextureSize(32, 32);
        this.Shapers[16].mirror = true;
        this.setRotation(this.Shapers[16], 4.65F, 9.87F, 5.1F);
        this.Shapers[17] = new ModelRenderer(this, 0, 6);
        this.Shapers[17].addBox(-1.5F, 0.77F, 5.99F, 8, 10, 3);
        this.Shapers[17].setRotationPoint(0.0F, 6.44F, 1.67F);
        this.Shapers[17].setTextureSize(32, 32);
        this.Shapers[17].mirror = true;
        this.setRotation(this.Shapers[17], 2.56F, 7.79F, 3.01F);
        this.Shapers[18] = new ModelRenderer(this, 12, 3);
        this.Shapers[18].addBox(0.5F, 8.68F, 3.91F, 5, 8, 5);
        this.Shapers[18].setRotationPoint(0.0F, 4.36F, 9.58F);
        this.Shapers[18].setTextureSize(32, 32);
        this.Shapers[18].mirror = true;
        this.setRotation(this.Shapers[18], 0.48F, 5.7F, 0.93F);
        this.Shapers[19] = new ModelRenderer(this, 9, 0);
        this.Shapers[19].addBox(-0.5F, 6.6F, 1.82F, 2, 5, 6);
        this.Shapers[19].setRotationPoint(0.5F, 2.27F, 7.5F);
        this.Shapers[19].setTextureSize(32, 32);
        this.Shapers[19].mirror = true;
        this.setRotation(this.Shapers[19], 8.39F, 3.62F, 8.84F);
        this.Shapers[20] = new ModelRenderer(this, 6, 13);
        this.Shapers[20].addBox(-0.5F, 4.51F, 9.74F, 13, 2, 7);
        this.Shapers[20].setRotationPoint(-0.5F, 0.19F, 5.41F);
        this.Shapers[20].setTextureSize(32, 32);
        this.Shapers[20].mirror = true;
        this.setRotation(this.Shapers[20], 6.31F, 1.53F, 6.76F);
        this.Shapers[21] = new ModelRenderer(this, 2, 10);
        this.Shapers[21].addBox(-0.5F, 2.43F, 7.65F, 10, 14, 9);
        this.Shapers[21].setRotationPoint(0.0F, 8.1F, 3.33F);
        this.Shapers[21].setTextureSize(32, 32);
        this.Shapers[21].mirror = true;
        this.setRotation(this.Shapers[21], 4.22F, 9.45F, 4.67F);
        this.Shapers[22] = new ModelRenderer(this, 14, 7);
        this.Shapers[22].addBox(-1.0F, 0.34F, 5.57F, 7, 11, 10);
        this.Shapers[22].setRotationPoint(0.0F, 6.02F, 1.24F);
        this.Shapers[22].setTextureSize(32, 32);
        this.Shapers[22].mirror = true;
        this.setRotation(this.Shapers[22], 2.14F, 7.36F, 2.59F);
        this.Shapers[23] = new ModelRenderer(this, 11, 4);
        this.Shapers[23].addBox(-0.5F, 8.26F, 3.48F, 4, 9, 11);
        this.Shapers[23].setRotationPoint(0.0F, 3.93F, 9.16F);
        this.Shapers[23].setTextureSize(32, 32);
        this.Shapers[23].mirror = true;
        this.setRotation(this.Shapers[23], 0.05F, 5.28F, 0.5F);
        this.Shapers[24] = new ModelRenderer(this, 8, 1);
        this.Shapers[24].addBox(-0.5F, 6.18F, 1.4F, 1, 6, 12);
        this.Shapers[24].setRotationPoint(0.0F, 1.85F, 7.07F);
        this.Shapers[24].setTextureSize(32, 32);
        this.Shapers[24].mirror = true;
        this.setRotation(this.Shapers[24], 7.97F, 3.19F, 8.42F);
        this.Shapers[25] = new ModelRenderer(this, 5, 14);
        this.Shapers[25].addBox(-1.0F, 4.09F, 9.31F, 13, 3, 14);
        this.Shapers[25].setRotationPoint(0.0F, 9.76F, 4.99F);
        this.Shapers[25].setTextureSize(32, 32);
        this.Shapers[25].mirror = true;
        this.setRotation(this.Shapers[25], 5.88F, 1.11F, 6.33F);
        this.Shapers[26] = new ModelRenderer(this, 2, 11);
        this.Shapers[26].addBox(-4.0F, 2.01F, 7.23F, 10, 0, 0);
        this.Shapers[26].setRotationPoint(0.0F, 7.68F, 2.9F);
        this.Shapers[26].setTextureSize(32, 32);
        this.Shapers[26].mirror = true;
        this.setRotation(this.Shapers[26], 3.8F, 9.02F, 4.25F);
        this.Shapers[27] = new ModelRenderer(this, 14, 8);
        this.Shapers[27].addBox(0.0F, 9.92F, 5.15F, 7, 13, 1);
        this.Shapers[27].setRotationPoint(-3.0F, 5.59F, 0.82F);
        this.Shapers[27].setTextureSize(32, 32);
        this.Shapers[27].mirror = true;
        this.setRotation(this.Shapers[27], 1.72F, 6.94F, 2.16F);
        this.Shapers[28] = new ModelRenderer(this, 11, 5);
        this.Shapers[28].addBox(0.0F, 7.84F, 3.06F, 3, 10, 3);
        this.Shapers[28].setRotationPoint(-3.0F, 3.51F, 8.73F);
        this.Shapers[28].setTextureSize(32, 32);
        this.Shapers[28].mirror = true;
        this.setRotation(this.Shapers[28], 9.63F, 4.85F, 0.08F);
        this.Shapers[29] = new ModelRenderer(this, 7, 3);
        this.Shapers[29].addBox(0.0F, 5.75F, 0.98F, 0, 7, 4);
        this.Shapers[29].setRotationPoint(-3.0F, 1.42F, 6.65F);
        this.Shapers[29].setTextureSize(32, 32);
        this.Shapers[29].mirror = true;
        this.setRotation(this.Shapers[29], 7.55F, 2.77F, 7.99F);
        this.Shapers[30] = new ModelRenderer(this, 4, 0);
        this.Shapers[30].addBox(0.0F, 3.67F, 8.89F, 12, 4, 5);
        this.Shapers[30].setRotationPoint(-3.0F, 9.34F, 4.56F);
        this.Shapers[30].setTextureSize(32, 32);
        this.Shapers[30].mirror = true;
        this.setRotation(this.Shapers[30], 5.46F, 0.69F, 5.91F);
        this.Shapers[31] = new ModelRenderer(this, 1, 12);
        this.Shapers[31].addBox(-4.0F, 1.58F, 6.81F, 9, 1, 7);
        this.Shapers[31].setRotationPoint(0.0F, 7.26F, 2.48F);
        this.Shapers[31].setTextureSize(32, 32);
        this.Shapers[31].mirror = true;
        this.setRotation(this.Shapers[31], 3.38F, 8.6F, 3.82F);
        this.Shapers[32] = new ModelRenderer(this, 13, 9);
        this.Shapers[32].addBox(0.0F, 9.5F, 4.72F, 6, 14, 8);
        this.Shapers[32].setRotationPoint(-3.0F, 5.17F, 0.39F);
        this.Shapers[32].setTextureSize(32, 32);
        this.Shapers[32].mirror = true;
        this.setRotation(this.Shapers[32], 1.29F, 6.52F, 1.74F);
        this.Shapers[33] = new ModelRenderer(this, 10, 6);
        this.Shapers[33].addBox(0.0F, 7.41F, 2.64F, 3, 11, 9);
        this.Shapers[33].setRotationPoint(-3.0F, 3.09F, 8.31F);
        this.Shapers[33].setTextureSize(32, 32);
        this.Shapers[33].mirror = true;
        this.setRotation(this.Shapers[33], 9.21F, 4.43F, 9.66F);
        this.Shapers[34] = new ModelRenderer(this, 7, 4);
        this.Shapers[34].addBox(0.0F, 5.33F, 0.55F, 0, 8, 10);
        this.Shapers[34].setRotationPoint(-3.0F, 1.0F, 6.23F);
        this.Shapers[34].setTextureSize(32, 32);
        this.Shapers[34].mirror = true;
        this.setRotation(this.Shapers[34], 7.12F, 2.35F, 7.57F);
        this.Shapers[35] = new ModelRenderer(this, 4, 1);
        this.Shapers[35].addBox(0.0F, 3.24F, 8.47F, 12, 5, 12);
        this.Shapers[35].setRotationPoint(-3.0F, 8.92F, 4.14F);
        this.Shapers[35].setTextureSize(32, 32);
        this.Shapers[35].mirror = true;
        this.setRotation(this.Shapers[35], 5.04F, 0.26F, 5.49F);
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

    public void render()
    {
        float f5 = 0.0625F;
        ModelRenderer[] arr$ = this.Shapers;
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$)
        {
            ModelRenderer s = arr$[i$];
            s.render(f5);
        }
    }
}

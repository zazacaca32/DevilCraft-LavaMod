package codechicken.core.lighting;

import codechicken.core.lighting.PlanarLightModel;
import codechicken.core.render.CCModel;
import codechicken.core.render.CCRenderState;
import codechicken.core.render.IVertexModifier;
import codechicken.core.render.UV;
import codechicken.core.vec.Rotation;
import codechicken.core.vec.Vector3;
import net.minecraft.client.renderer.Tessellator;

public class LightModel implements IVertexModifier {

   public static LightModel standardLightModel = (new LightModel()).setAmbient(new Vector3(0.4D, 0.4D, 0.4D)).addLight((new LightModel.Light(new Vector3(0.2D, 1.0D, -0.7D))).setDiffuse(new Vector3(0.6D, 0.6D, 0.6D))).addLight((new LightModel.Light(new Vector3(-0.2D, 1.0D, 0.7D))).setDiffuse(new Vector3(0.6D, 0.6D, 0.6D)));
   private Vector3 ambient = new Vector3();
   private LightModel.Light[] lights = new LightModel.Light[8];
   private int lightCount;


   public LightModel addLight(LightModel.Light light) {
      this.lights[this.lightCount++] = light;
      return this;
   }

   public LightModel setAmbient(Vector3 vec) {
      this.ambient.set(vec);
      return this;
   }

   public int apply(int colour, Vector3 normal) {
      Vector3 n_colour = this.ambient.copy();

      for(int l = 0; l < this.lightCount; ++l) {
         LightModel.Light light = this.lights[l];
         double n_l = light.position.dotProduct(normal);
         double f = (double)(n_l > 0.0D?1:0);
         n_colour.x += light.ambient.x + f * light.diffuse.x * n_l;
         n_colour.y += light.ambient.y + f * light.diffuse.y * n_l;
         n_colour.z += light.ambient.z + f * light.diffuse.z * n_l;
      }

      if(n_colour.x > 1.0D) {
         n_colour.x = 1.0D;
      }

      if(n_colour.y > 1.0D) {
         n_colour.y = 1.0D;
      }

      if(n_colour.z > 1.0D) {
         n_colour.z = 1.0D;
      }

      n_colour.multiply((double)(colour >>> 24) / 255.0D, (double)(colour >> 16 & 255) / 255.0D, (double)(colour >> 8 & 255) / 255.0D);
      return (int)(n_colour.x * 255.0D) << 24 | (int)(n_colour.y * 255.0D) << 16 | (int)(n_colour.z * 255.0D) << 8 | colour & 255;
   }

   public void applyModifiers(CCModel m, Tessellator tess, Vector3 vec, UV uv, Vector3 normal, int i) {
      CCRenderState.setColour(this.apply(m != null && m.colours != null?m.colours[i]:-1, normal));
   }

   public boolean needsNormals() {
      return true;
   }

   public PlanarLightModel reducePlanar() {
      int[] colours = new int[6];

      for(int i = 0; i < 6; ++i) {
         colours[i] = this.apply(-1, Rotation.axes[i]);
      }

      return new PlanarLightModel(colours);
   }

   public static class Light {

      public Vector3 ambient = new Vector3();
      public Vector3 diffuse = new Vector3();
      public Vector3 position;


      public Light(Vector3 pos) {
         this.position = pos.copy().normalize();
      }

      public LightModel.Light setDiffuse(Vector3 vec) {
         this.diffuse.set(vec);
         return this;
      }

      public LightModel.Light setAmbient(Vector3 vec) {
         this.ambient.set(vec);
         return this;
      }
   }
}

package codechicken.core.lighting;

import codechicken.core.colour.Colour;
import codechicken.core.colour.ColourRGBA;
import codechicken.core.render.CCModel;
import codechicken.core.render.CCRenderState;
import codechicken.core.render.IVertexModifier;
import codechicken.core.render.UV;
import codechicken.core.vec.Vector3;
import net.minecraft.client.renderer.Tessellator;

public class PlanarLightModel implements IVertexModifier {

   public ColourRGBA[] colours = new ColourRGBA[6];


   public PlanarLightModel(int[] colours) {
      for(int i = 0; i < 6; ++i) {
         this.colours[i] = new ColourRGBA(colours[i]);
      }

   }

   public void applyModifiers(CCModel m, Tessellator tess, Vector3 vec, UV uv, Vector3 normal, int i) {
      ColourRGBA light = this.colours[CCModel.findSide(normal)];
      int colour = m != null && m.colours != null?m.colours[i]:-1;
      Colour res = (new ColourRGBA(colour)).multiply(light);
      CCRenderState.vertexColour(res.r & 255, res.g & 255, res.b & 255, res.a & 255);
   }

   public boolean needsNormals() {
      return true;
   }
}

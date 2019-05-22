package codechicken.core.lighting;

import codechicken.core.lighting.LC;
import codechicken.core.render.CCModel;
import codechicken.core.render.Vertex5;

public class CCRBModel extends CCModel {

   public LC[] lightCoefficents;


   public CCRBModel(CCModel m) {
      super(m.vertexMode);
      super.verts = new Vertex5[m.verts.length];
      copy(m, 0, this, 0, m.verts.length);
      if(super.normals == null) {
         this.computeNormals();
      }

      if(super.colours == null) {
         this.setColour(-1);
      }

      this.computeLighting();
   }

   private void computeLighting() {
      this.lightCoefficents = new LC[super.verts.length];

      for(int k = 0; k < super.verts.length; ++k) {
         this.lightCoefficents[k] = LC.compute(super.verts[k].vec, super.normals[k]);
      }

   }
}

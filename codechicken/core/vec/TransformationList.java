package codechicken.core.vec;

import codechicken.core.vec.Matrix4;
import codechicken.core.vec.Transformation;
import codechicken.core.vec.Vector3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;

public class TransformationList extends Transformation {

   private ArrayList transformations = new ArrayList();
   private Matrix4 mat;


   public TransformationList(Transformation ... transforms) {
      Transformation[] var5 = transforms;
      int var4 = transforms.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         Transformation t = var5[var3];
         this.with(t);
      }

   }

   public Matrix4 compile() {
      if(this.mat == null) {
         this.mat = new Matrix4();

         for(int i = this.transformations.size() - 1; i >= 0; --i) {
            ((Transformation)this.transformations.get(i)).apply(this.mat);
         }
      }

      return this.mat;
   }

   public Matrix4 reverseCompile() {
      Matrix4 mat = new Matrix4();
      Iterator var3 = this.transformations.iterator();

      while(var3.hasNext()) {
         Transformation t = (Transformation)var3.next();
         t.apply(mat);
      }

      return mat;
   }

   public void apply(Vector3 vec) {
      if(this.mat != null) {
         this.mat.apply(vec);
      } else {
         Iterator var3 = this.transformations.iterator();

         while(var3.hasNext()) {
            Transformation t = (Transformation)var3.next();
            t.apply(vec);
         }
      }

   }

   public void applyN(Vector3 normal) {
      if(this.mat != null) {
         this.mat.applyN(normal);
      } else {
         Iterator var3 = this.transformations.iterator();

         while(var3.hasNext()) {
            Transformation t = (Transformation)var3.next();
            t.applyN(normal);
         }
      }

   }

   public void apply(Matrix4 mat) {
      mat.multiply(this.compile());
   }

   public TransformationList with(Transformation t) {
      this.mat = null;
      this.transformations.add(t);
      return this;
   }

   @SideOnly(Side.CLIENT)
   public void glApply() {
      for(int i = this.transformations.size() - 1; i >= 0; --i) {
         ((Transformation)this.transformations.get(i)).glApply();
      }

   }

   public Transformation inverse() {
      TransformationList rev = new TransformationList(new Transformation[0]);

      for(int i = this.transformations.size() - 1; i >= 0; --i) {
         rev.with(((Transformation)this.transformations.get(i)).inverse());
      }

      return rev;
   }

   public String toString() {
      String s = "";

      Transformation t;
      for(Iterator var3 = this.transformations.iterator(); var3.hasNext(); s = s + "\n" + t.toString()) {
         t = (Transformation)var3.next();
      }

      return s.trim();
   }
}

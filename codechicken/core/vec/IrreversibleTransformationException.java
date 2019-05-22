package codechicken.core.vec;

import codechicken.core.vec.Transformation;

public class IrreversibleTransformationException extends RuntimeException {

   public Transformation t;


   public IrreversibleTransformationException(Transformation t) {
      this.t = t;
   }

   public String getMessage() {
      return "The following transformation is irreversible:\n" + this.t;
   }
}

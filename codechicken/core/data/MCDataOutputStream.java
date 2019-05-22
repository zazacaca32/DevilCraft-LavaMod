package codechicken.core.data;

import codechicken.core.data.MCDataOutput;
import java.io.OutputStream;

public class MCDataOutputStream extends OutputStream {

   private MCDataOutput out;


   public MCDataOutputStream(MCDataOutput out) {
      this.out = out;
   }

   public void write(int b) {
      this.out.writeByte(b);
   }
}

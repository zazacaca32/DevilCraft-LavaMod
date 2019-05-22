package codechicken.core.data;

import codechicken.core.data.MCDataInput;
import java.io.InputStream;

public class MCDataInputStream extends InputStream {

   private MCDataInput in;


   public MCDataInputStream(MCDataInput in) {
      this.in = in;
   }

   public int read() {
      return this.in.readByte() & 255;
   }
}

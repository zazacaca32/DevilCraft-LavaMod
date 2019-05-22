package codechicken.core;


public class LongArrayList {

   private long[] array = new long[256];
   private int size = 0;


   public void expand(int newsize) {
      if(this.array.length < newsize) {
         int expanded = this.array.length + (this.array.length >> 1);
         if(expanded < newsize) {
            expanded = newsize;
         }

         long[] newarray = new long[expanded];
         System.arraycopy(this.array, 0, newarray, 0, this.array.length);
         this.array = newarray;
      }

   }

   public void add(long element) {
      this.expand(this.size + 1);
      this.array[this.size] = element;
      ++this.size;
   }

   public void clear() {
      this.size = 0;
   }

   public long get(int index) {
      if(index >= this.size) {
         throw new ArrayIndexOutOfBoundsException(index);
      } else {
         return this.array[index];
      }
   }

   public int size() {
      return this.size;
   }
}

package codechicken.core.vec;


public class Rectangle4i {

   public int x;
   public int y;
   public int w;
   public int h;


   public Rectangle4i() {}

   public Rectangle4i(int x, int y, int w, int h) {
      this.x = x;
      this.y = y;
      this.w = w;
      this.h = h;
   }

   public Rectangle4i offset(int dx, int dy) {
      this.x += dx;
      this.y += dy;
      return this;
   }

   public Rectangle4i with(int px, int py) {
      if(this.x > px) {
         this.x = px;
      }

      if(this.y > py) {
         this.y = py;
      }

      if(this.x + this.w <= px) {
         this.w = px - this.x + 1;
      }

      if(this.y + this.h <= py) {
         this.h = py - this.y + 1;
      }

      return this;
   }

   public boolean contains(int px, int py) {
      return this.x <= px && px < this.x + this.w && this.y <= py && py < this.y + this.h;
   }
}

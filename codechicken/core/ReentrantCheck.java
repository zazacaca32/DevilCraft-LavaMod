package codechicken.core;


public class ReentrantCheck {

   private boolean entered = false;


   public boolean entered() {
      return this.entered;
   }

   public void enter() {
      this.entered = true;
   }

   public void exit() {
      this.entered = false;
   }

   public static ThreadLocal threadLocal() {
      return new ThreadLocal() {
         protected ReentrantCheck initialValue() {
            return new ReentrantCheck();
         }
      };
   }
}

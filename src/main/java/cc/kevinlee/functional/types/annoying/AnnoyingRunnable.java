package cc.kevinlee.functional.types.annoying;

/**
 * @author Kevin Lee
 * @since 2017-06-10
 */
@FunctionalInterface
public interface AnnoyingRunnable {
  /**
   * When an object implementing interface <code>Runnable</code> is used
   * to create a thread, starting the thread causes the object's
   * <code>run</code> method to be called in that separately executing
   * thread.
   * <p>
   * The general contract of the method <code>run</code> is that it may
   * take any action whatsoever.
   *
   * @see     java.lang.Thread#run()
   * @throws Exception
   */
  void run() throws Exception;
}

/*--------------------------------------------------------------------*/
/* This abstract class encapsulates two pipes,     */
/* one input pipe and the other output pipe.          */
/* This calss also implements runable, and thus  */
/* any of its subclass will run an independent       */
/* thread.                                                                   */
/*--------------------------------------------------------------------*/

abstract public class Filter implements Runnable
{
  protected Pipe inPipe;
  protected Pipe outPipe;
  private boolean isStarted = false; //thread flag

  public Filter(Pipe input, Pipe output)
  {
    inPipe = input;
    outPipe = output;
  }

  //Start a new thread
  public void start(){
    if(!isStarted)
    {
      isStarted = true;
      Thread thread = new Thread(this);
      thread.start();
    }
  }

  //Stop the current running thread
  public void stop(){
    isStarted = false;
  }

  /*-----------------------------------*/
  /* Run the threas, run() will be     */
  /* invoked automatically by start()  */
  /*-----------------------------------*/
  public void run(){
    processData();
  }

  /*-------------------------------------*/
  /* This method will be implemented     */
  /* by its subclasses to get different  */
  /* functionaslities                    */
  /*-------------------------------------*/
  abstract protected void processData();
}

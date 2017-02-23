package apps.sample.mayank.com.handlerexamples.handlers;

import android.os.Handler;
import android.os.HandlerThread;
/**
 * Created by grofers-mc on 23/02/17.
 */

public class MyWorkerThread extends HandlerThread
{
    private Handler mWorkerHandler;

    public MyWorkerThread(String name)
    {
        super(name);
    }

    public MyWorkerThread(String name, int priority)
    {
        super(name, priority);
    }

    public void postTask(Runnable task)
    {
        mWorkerHandler.post(task);
    }

    public void prepareHandler()
    {
        mWorkerHandler = new Handler(getLooper());
    }
}

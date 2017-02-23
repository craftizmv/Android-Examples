package apps.sample.mayank.com.handlerexamples.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import apps.sample.mayank.com.handlerexamples.R;
import apps.sample.mayank.com.handlerexamples.handlers.MyWorkerThread;

public class MainActivity extends AppCompatActivity
{

    private Handler mUiHandler = new Handler();

    private MyWorkerThread myWorkerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //performing some operation from different thread
        //        performOperationFromDiffThread();

        performOperationFromHandlerThread();
    }

    private void performOperationFromHandlerThread()
    {
        myWorkerThread = new MyWorkerThread("myWorkerThread");
        Runnable task = new Runnable()
        {
            @Override
            public void run()
            {
                for(int i = 0; i < 4; i++)
                {
                    final int countTemp = i;
                    try
                    {
                        TimeUnit.SECONDS.sleep(2);
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    if(i == 2)
                    {
                        mUiHandler.post(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Toast.makeText(MainActivity.this, "Posting this from handler method", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    mUiHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Toast.makeText(MainActivity.this, "Background task is completed : " + countTemp, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        };
        myWorkerThread.start();
        myWorkerThread.prepareHandler();
        for(int i = 0; i < 3; i++)
        {
            myWorkerThread.postTask(task);
        }
    }

    private void performOperationFromDiffThread()
    {
        Thread myThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for(int i = 0; i < 4; i++)
                {
                    final int countTemp = i;
                    try
                    {
                        TimeUnit.SECONDS.sleep(2);
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    if(i == 2)
                    {
                        mUiHandler.post(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Toast.makeText(MainActivity.this, "Posting this from handler method", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    mUiHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Toast.makeText(MainActivity.this, "Background task is completed : " + countTemp, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        myThread.start();
    }
}

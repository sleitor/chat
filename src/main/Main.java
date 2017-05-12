package main;

/**
 * Created by User on 12.05.2017.
 */
public class Main {
    public static void main(String[] args) {

        Thread lisener = new Thread(new Runnable() {
            public void run() {
                MyMessageConcumer myMessageConcumer = new MyMessageConcumer();
//
//                while (true)
//                    try {
//                        Thread.sleep(500);
                        myMessageConcumer.reseive();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
            }
        });
        lisener.start();


        Thread sender = new Thread(new Runnable() {
            public void run() {

                MyMessageProduccer myMessageProduccer = new MyMessageProduccer();
int i=0;
                while (true)
                    try {
                        Thread.sleep(500);
                        myMessageProduccer.send("Hello" + i++);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

            }
        });
        sender.start();


    }
}

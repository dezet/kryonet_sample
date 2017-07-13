import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;

public class SimpleClientAppJ {
    public static void main(String[] args) {
        Log.set(Log.LEVEL_TRACE);


        Client c = new Client();
        NetworkUtilJ.register(c.getKryo());
        c.start();
        try {
            c.connect(5000, "127.0.0.1", 54555, 54777);
            SimpleRequestJ sq = new SimpleRequestJ("10");
            c.sendTCP(sq);
            c.addListener(new Listener() {
                @Override
                public void received(Connection connection, Object object) {
                    if (object instanceof SimpleResponse) {
                        SimpleResponse sr = (SimpleResponse) object;
                        Log.info("Got message back: ");
                        Log.info(String.valueOf(sr.getNumber()));
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

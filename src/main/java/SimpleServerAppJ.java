import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class SimpleServerAppJ {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger();
        logger.info("asdsadsa");
        Server server = new Server();
        NetworkUtilJ.register(server.getKryo());
        server.start();

        try {
            server.bind(54555, 54777);
            server.addListener(new Listener() {
                @Override
                public void received(Connection connection, Object object) {
                    if (object instanceof SimpleRequestJ) {
                        SimpleRequestJ sq = (SimpleRequestJ) object;
                        logger.info("Got some info from the socket: sq.getText()");
                        try {
                            SimpleResponse sr = new SimpleResponse(Double.parseDouble(sq.getText()));
                            connection.sendTCP(sr);
                        } catch (NumberFormatException e) {
                            logger.warn("Exception while trying to parse request text as number.");
                            logger.warn(e.getMessage());
                        }

                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

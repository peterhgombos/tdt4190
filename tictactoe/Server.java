import Connection;
import TicTacToeGui;
import java.rmi.server;
import Tuple;

package tictactoe;

public class Server extends UnicastRemoteObject implements Connection {

    private Client client;

    public void mark( final List state ) {

        /* draw please wait */

        while( this.client.receive_state( state ) != SUCCESS );
    }

    private boolean receive_state( final List state ) throws RemoteException {
        /* integrity check */
    }

    public void setup();
}

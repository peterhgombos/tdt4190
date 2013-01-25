import Connection;
import TicTacToeGui;

package tictactoe;

public class Client extends UnicastRemoteObject implements Connection {

    private Server server;

    public void mark( final List state ) throws RemoteException {
        while( this.server.receive_state( state ) != SUCCESS );
    }

    private boolean receive_state( final List state ) throws RemoteException {
        /* Sets unconditionally as this is the slave */
        for( final coord : state )
            this.gui.setMark( coord.x, coord.y );

        return true;
    }
}

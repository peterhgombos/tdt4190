import java.rmi.*;
import java.rmi.server.*;
import java.util.List;
import java.io.*;

public class Server extends UnicastRemoteObject implements Connection {

    private Client client;
    private TicTacToeGui gui;

    public Server( final String address, TicTacToeGui gui )
        throws ConnectException, NotBoundException, java.net.MalformedURLException, RemoteException {
        this.gui = gui;
        /* sanity check address? */
        System.setSecurityManager( new LiberalSecurityManager() );
        Naming.rebind( "rmi://" + address + "/ttt", this );
        System.out.println( "Registry set up at " + address );
    }

    public void mark( final List state ) throws RemoteException, IntegrityException {

        /* draw please wait */

        while( !this.client.receive_state( state ) );
    }

    public boolean receive_state( final List state ) throws RemoteException, IntegrityException {
        /* integrity check */
        if( state.size() != 1 ) throw new IntegrityException();
        Pair< Integer, Integer > coord = (Pair)state.get( 0 );

        //if( this.gui.legit( coord.x, coord.y ) ) throw new IntegrityException();
        this.gui.setMark( coord.x, coord.y );

        return true;
    }
}

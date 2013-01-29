import java.rmi.*;
import java.rmi.server.*;
import java.util.List;
import java.io.*;

public class Server extends UnicastRemoteObject implements Connection {

    private TicTacToeGui gui;

    public Server( final String address, TicTacToeGui gui )
        throws ConnectException, NotBoundException, java.net.MalformedURLException, RemoteException {
        this.gui = gui;
        /* sanity check address? */
        System.setSecurityManager( new LiberalSecurityManager() );
        Naming.rebind( "rmi://" + address + "/ttt", this );
        System.out.println( "Registry set up at " + address );
    }

    public void mark( final Pair coord ) throws RemoteException, IntegrityException {
        Pair< Integer, Integer > coord = (Pair)state.get( 0 );
        this.gui.setMark( coord.x, coord.y );
    }
}

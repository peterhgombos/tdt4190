import java.rmi.*;
import java.rmi.server.*;
import java.util.List;
import java.io.*;

public class Client extends UnicastRemoteObject implements Connection {

    private Server server;
    private TicTacToeGui gui;

    public Client( final String address, TicTacToeGui gui )
        throws ConnectException, NotBoundException, java.net.MalformedURLException, RemoteException {

        this.gui = gui;
        System.setSecurityManager( new LiberalSecurityManager() );
        this.server = (Server)Naming.lookup( "rmi://" + address + "/ttt" );
        System.out.println( "Connection set up with " + address );
    }

    public void mark( final List state ) throws RemoteException, IntegrityException {
        while( this.server.receive_state( state ) != true );
    }

    public boolean receive_state( final List state ) throws RemoteException {
        /* Sets unconditionally as this is the slave */
        for( final Pair< Integer, Integer > coord : (List<Pair>)state ) {
            this.gui.forceMark( coord.x, coord.y );
        }

        this.gui.repaint();

        return true;
    }
}

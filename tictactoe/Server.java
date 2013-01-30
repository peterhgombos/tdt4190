import java.rmi.*;
import java.rmi.server.*;
import java.util.List;
import java.io.*;

public class Server extends UnicastRemoteObject implements Connection {

    private TicTacToeGui gui;

    public Server( TicTacToeGui gui )
        throws ConnectException, NotBoundException, java.net.MalformedURLException, RemoteException {
        this.gui = gui;

        final String address = "localhost";
        System.setSecurityManager( new LiberalSecurityManager() );
        Naming.rebind( "rmi://" + address + "/ttt", this );
        System.out.println( "Registry set up at " + address );
    }

    public void mark( final Pair coord ) throws RemoteException {
        this.gui.setMark( (Integer)coord.x, (Integer)coord.y );
    }

    public boolean register( final String address ) throws RemoteException {
        return this.gui.prompt_connection( address );
    }

    public boolean new_game() throws RemoteException {
        if( !this.gui.prompt_newgame() )
            return false;

        this.gui.clearBoard();
        return true;
    }
}

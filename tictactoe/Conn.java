import java.rmi.*;
import java.rmi.server.*;
import java.util.List;
import java.io.*;

public class Conn extends UnicastRemoteObject implements Connection {

    private TicTacToeGui gui;

    public Conn( final String address, TicTacToeGui gui )
        throws ConnectException, NotBoundException, java.net.MalformedURLException, RemoteException {

        try{ 
            System.setSecurityManager( new LiberalSecurityManager() );
        } catch( Exception e ) { e.printStackTrace(); }
        Naming.rebind( "rmi://" + address + "/ttt", this );
        this.gui = gui;
    }

    public void mark( final Pair coord, final char mark ) throws RemoteException {
        this.gui.setMark( (Integer)coord.x, (Integer)coord.y, mark  );
    }

    public boolean new_game() throws RemoteException {
        if( !this.gui.prompt_newgame() )
            return false;

        this.gui.clearBoard();
        return true;
    }
}

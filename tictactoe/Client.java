import java.rmi.*;
import java.rmi.server.*;
import java.util.List;
import java.io.*;

public class Client extends UnicastRemoteObject implements Connection {

    private Connection server;

    public Client( final String address )
        throws ConnectException, NotBoundException, java.net.MalformedURLException, RemoteException {

        System.setSecurityManager( new LiberalSecurityManager() );
        this.server = (Connection)Naming.lookup( "rmi://" + address + "/ttt" );
        System.out.println( "Connection set up with " + address );
    }

    public void mark( final Pair coord ) throws RemoteException {
        this.server.mark( coord );
    }

    public boolean register( final String address ) throws RemoteException {
        return this.server.register( address );
    }

    public boolean new_game() throws RemoteException {
        return this.server.new_game();
    }
}

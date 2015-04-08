
package org.opendaylight.bgpmanager.thrift.client.implementation;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class BgpSyncHandle {
    private static BgpSyncHandle handle = null;
    private int more;
    private int state;

    public static final int INITED = 1;
    public static final int ITERATING = 2;
    public static final int DONE = 3;
    public static final int ABORTED = 4;
    public static final int NEVER_DONE = 5;

    public static final int default_tcp_sock_sz = 87380;    //default receive buffer size on linux > 2.4 (SLES 11)

    private BgpSyncHandle() {
        more = 1; 
        state = NEVER_DONE;
    }

    public static synchronized BgpSyncHandle getInstance() {
       if (handle == null) 
           handle = new BgpSyncHandle();
       return handle;
    }

    public synchronized int getState() {
       return state;
    }

    public int getMaxCount() {
        //compute the max count of routes we would like to send
        Socket skt = new Socket();
        int sockBufSz = default_tcp_sock_sz;
        try {
            sockBufSz = skt.getReceiveBufferSize();
        } catch (SocketException s) {
        }
        try {
            skt.close();
        } catch (IOException e) {
        }
        return sockBufSz/getRouteSize();
    }

    public int getRouteSize() {
       //size of one update structure on the wire. ideally
       //this should be computed; or thrift sure has a nice
       //way to tell this to the applciation, but for the
       //moment, we just use 8 bytes more than the size of 
       //the C struct. 

       return 96;
    }

    public int setState(int state) {
       int retval = this.state;
       this.state = state;
       return retval;
    }

    public int setMore(int more) {
       int retval = this.more;
       this.more = more;
       return retval;
    }
}

        
  

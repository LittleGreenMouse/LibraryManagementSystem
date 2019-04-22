/*
 * Implement of interface
 * The file should just be placed in server
 */

import java.rmi.RemoteException;

public class ComputingServiceImpl implements ComputingService {

    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

}
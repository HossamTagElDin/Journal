
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Web application lifecycle listener.
 *
 * @author hossam
 */
public class req implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        
    }
}

package gohil.aru.androidmap.modal;

import java.io.Serializable;

public class AddressModal implements Serializable {

    private String  strAddress;

    public String getStrAddress()
    {
        return strAddress;
    }

    public void setStrAddress(String strAddress) {
        this.strAddress = strAddress;
    }
}

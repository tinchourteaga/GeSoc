package Servidor.Controllers.Hash;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class FuncionHash implements Hash {

    @Override
    public String funcionHash(String variable) {
        String hash = getSHA256Hash(variable);
        return hash;
    }

    private String getSHA256Hash(String data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return bytesToHex(hash);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }
}

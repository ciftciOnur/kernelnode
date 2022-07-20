package tr.edu.yeditepe.kernelnode.domain.model.kernelblock;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Getter
@Setter
@Slf4j
public class KernelBlock {
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;
    private long order;

    public KernelBlock(String data, String previousHash, long timeStamp, long order) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = timeStamp;
        this.order = order;
        this.hash = calculateBlockHash();
    }

    public String calculateBlockHash() {
        String dataToHash = previousHash
                + timeStamp
                + nonce
                + order
                + data;
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(UTF_8));
        } catch (NoSuchAlgorithmException ex) {
        }
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }

    public String mineBlock(int prefix) {
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!hash.substring(0, prefix).equals(prefixString)) {
            nonce++;
            hash = calculateBlockHash();
        }
        return hash;
    }

    public String toString(){
        return order+timeStamp+previousHash+hash+data;
    }
}

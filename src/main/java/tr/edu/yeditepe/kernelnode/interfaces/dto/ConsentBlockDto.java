package tr.edu.yeditepe.kernelnode.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsentBlockDto {
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;
    private long order;
    private UUID minerId;
    private int numberOfConsents;

    public String toString(){
        return "order: "+order+ "\ntimestamp: "+ timeStamp + "\npreviousHash: " + previousHash+ "\nhash: "+hash+ "\ndata: "+data;
    }
}

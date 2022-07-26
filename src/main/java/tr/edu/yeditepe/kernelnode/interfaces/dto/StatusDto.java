package tr.edu.yeditepe.kernelnode.interfaces.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StatusDto {
    private long order;
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;
}

package tr.edu.yeditepe.kernelnode.domain.model.blockchain;

import lombok.Getter;
import lombok.Setter;
import tr.edu.yeditepe.kernelnode.domain.model.kernelblock.KernelBlock;

import java.util.ArrayList;

@Getter
@Setter
public class BlockChain {

    private ArrayList<KernelBlock> kernelBlocks;
    private int diffuculty;

    public BlockChain(){
        kernelBlocks = new ArrayList<>();
        diffuculty=1;
    }
}

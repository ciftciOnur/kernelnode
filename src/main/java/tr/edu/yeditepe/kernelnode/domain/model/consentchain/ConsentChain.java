package tr.edu.yeditepe.kernelnode.domain.model.consentchain;

import lombok.Getter;
import lombok.Setter;
import tr.edu.yeditepe.kernelnode.domain.model.kernelblock.KernelBlock;
import tr.edu.yeditepe.kernelnode.interfaces.dto.ConsentBlockDto;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Getter
@Setter
public class ConsentChain {
    private Queue<ConsentBlockDto> consentBlocks;

    public ConsentChain(){
        consentBlocks = new ConcurrentLinkedQueue<>();
    }

    public void emptyConsentChain(){
        consentBlocks.clear();
    }

    public ConsentBlockDto increaseNumberOfConsent(ConsentBlockDto consent){
        consentBlocks.remove(consent);
        consent.setNumberOfConsents(consent.getNumberOfConsents()+1);
        consentBlocks.add(consent);
        return consent;
    }
}

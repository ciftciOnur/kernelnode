
package tr.edu.yeditepe.kernelnode.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import tr.edu.yeditepe.kernelnode.domain.model.blockchain.BlockChain;
import tr.edu.yeditepe.kernelnode.domain.model.consentchain.ConsentChain;
import tr.edu.yeditepe.kernelnode.domain.model.kernelblock.KernelBlock;
import tr.edu.yeditepe.kernelnode.interfaces.dto.ConsentBlockDto;
import tr.edu.yeditepe.kernelnode.service.ConsensusService;

import java.util.Iterator;

@Service
@RequiredArgsConstructor
public class ConsensusServiceImpl implements ConsensusService {

    private final ConsentChain consentChain;
    private final BlockChain blockChain;
    private final SimpMessagingTemplate webSocket;


    @Override
    public ConsentBlockDto checkConsent(ConsentBlockDto consent){
        Iterator<ConsentBlockDto> consentBlockDtoIterator = consentChain.getConsentBlocks().iterator();
        if(!blockChain.getKernelBlocks().isEmpty()) {
            if (!consentBlockDtoIterator.hasNext() &&
                    blockChain.getKernelBlocks().get(blockChain.getKernelBlocks().size() - 1).getOrder() + 1 == consent.getOrder()) {
                consentChain.getConsentBlocks().add(consent);
                return consent;
            }
        }
        else {
                if(!consentBlockDtoIterator.hasNext()){
                    consentChain.getConsentBlocks().add(consent);
                    return consent;
                }
            }

        while(consentBlockDtoIterator.hasNext()){
            ConsentBlockDto consentBlockDto = consentBlockDtoIterator.next();
            if(consentBlockDto.getHash().contentEquals(consent.getHash())&&consentBlockDto.getMinerId().equals(consent.getMinerId())) {
                ConsentBlockDto consentBlockToReturn = consentChain.increaseNumberOfConsent(consentBlockDto);
                if(consentBlockDto.getNumberOfConsents()>0&&blockChain.getKernelBlocks().isEmpty()){
                    KernelBlock kernelBlock = new KernelBlock(consentBlockDto.getData(),
                            consentBlockDto.getPreviousHash(),
                            consentBlockDto.getTimeStamp(),
                            consentBlockDto.getOrder(),
                            consentBlockDto.getHash());
                    blockChain.getKernelBlocks().add(kernelBlock);
                    consentChain.emptyConsentChain();
                    return consentBlockToReturn;
                }
                 else if(consentBlockDto.getNumberOfConsents()>0 &&
                            consentBlockDto.getOrder()==blockChain.getKernelBlocks()
                                    .get(blockChain.getKernelBlocks().size()-1)
                                    .getOrder()+1){
                    KernelBlock kernelBlock = new KernelBlock(consentBlockDto.getData(),
                            consentBlockDto.getPreviousHash(),
                            consentBlockDto.getTimeStamp(),
                            consentBlockDto.getOrder(),
                            consentBlockDto.getHash());
                    blockChain.getKernelBlocks().add(kernelBlock);
                    consentChain.emptyConsentChain();
                    return consentBlockToReturn;
                }
            }
            else
                consentChain.getConsentBlocks().add(consent);
        }
        return new ConsentBlockDto();
    }
}


package tr.edu.yeditepe.kernelnode.interfaces.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import tr.edu.yeditepe.kernelnode.domain.model.kernelblock.KernelBlock;
import tr.edu.yeditepe.kernelnode.interfaces.dto.ConsentBlockDto;
import tr.edu.yeditepe.kernelnode.service.ConsensusService;

import java.util.UUID;


@Controller
@RequiredArgsConstructor
public class SocketController {

    final static Logger logger = LoggerFactory.getLogger(SocketController.class);
    private final ConsensusService consensusService;

    @MessageMapping("/consent-request")
    @SendTo("/topic/distrubution")
    public ConsentBlockDto getBlockRequest(ConsentBlockDto consentBlock) throws Exception {
        logger.info(consentBlock.toString());
        return consentBlock;
    }

    @MessageMapping("/consent-response")
    @SendTo("/topic/consents")
    public ConsentBlockDto getConsent(ConsentBlockDto consentBlock) {
        //logger.info(consentBlock.toString());
        return consensusService.checkConsent(consentBlock);
    }


}

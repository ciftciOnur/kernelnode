
package tr.edu.yeditepe.kernelnode.interfaces.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import tr.edu.yeditepe.kernelnode.domain.model.kernelblock.KernelBlock;


@Controller
public class SocketController {

    final static Logger logger = LoggerFactory.getLogger(SocketController.class);

    @MessageMapping("/consent-request")
    @SendTo("/topic/distrubution")
    public KernelBlock getProof(KernelBlock consentBlock) throws Exception {
        logger.info(consentBlock.toString());
        return consentBlock;
    }


}

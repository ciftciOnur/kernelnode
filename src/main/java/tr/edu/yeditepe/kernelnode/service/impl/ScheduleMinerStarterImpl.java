/*
 * Copyright (c) 2012-
 * Vodafone Teknoloji Hizmetleri A.S., Istanbul, Turkey
 *
 * All rights reserved. This Software or any portion of it can not be translated,
 * distributed, sold, adapted, arranged, used, copied, modified, de-compiled,
 * reverse assembled or otherwise reverse engineered, disassembled, replaced or made
 * additions to and to be reproduced in whole or in part, in any way, manner or form.
 */
package tr.edu.yeditepe.kernelnode.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tr.edu.yeditepe.kernelnode.domain.model.blockchain.BlockChain;
import tr.edu.yeditepe.kernelnode.domain.model.consentchain.ConsentChain;
import tr.edu.yeditepe.kernelnode.interfaces.dto.ConsentBlockDto;
import tr.edu.yeditepe.kernelnode.service.ScheduleMinerStarter;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleMinerStarterImpl implements ScheduleMinerStarter {

    private Logger logger = LogManager.getLogger(ScheduleMinerStarter.class);

    private final ConsentChain consentChain;
    private final BlockChain blockChain;
    private final SimpMessagingTemplate webSocket;

    @Scheduled(fixedDelay = 20000)
    public void minerStarter(){
        logger.info("check miners are ok to start");
        if(consentChain.getConsentBlocks().isEmpty())
            webSocket.convertAndSend("/topic/minerPayload", ConsentBlockDto.builder()
                    .diffuculty(blockChain.getDiffuculty())
                    .data("Data")
                    .build());
        else
            logger.info(consentChain.getConsentBlocks().toString());
    }

    @Scheduled(fixedDelay = 60000)
    public void consentFlusher(){
        logger.info("check miners are ok to start");
        if(!consentChain.getConsentBlocks().isEmpty()){
            List<ConsentBlockDto> consentToBeRemoved =
                    consentChain.getConsentBlocks().stream()
                            .filter(f->f.getTimeStamp()<System.currentTimeMillis()-60000)
                            .collect(Collectors.toList());
            if(!consentToBeRemoved.isEmpty())
                consentChain.emptyConsentChain();
        }

        else
            logger.info("Consent is empty");
    }
}

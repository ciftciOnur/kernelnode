/*
 * Copyright (c) 2012-
 * Vodafone Teknoloji Hizmetleri A.S., Istanbul, Turkey
 *
 * All rights reserved. This Software or any portion of it can not be translated,
 * distributed, sold, adapted, arranged, used, copied, modified, de-compiled,
 * reverse assembled or otherwise reverse engineered, disassembled, replaced or made
 * additions to and to be reproduced in whole or in part, in any way, manner or form.
 */
package tr.edu.yeditepe.kernelnode.interfaces.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import tr.edu.yeditepe.kernelnode.domain.model.blockchain.BlockChain;
import tr.edu.yeditepe.kernelnode.domain.model.consentchain.ConsentChain;
import tr.edu.yeditepe.kernelnode.interfaces.dto.ConsentBlockDto;
import tr.edu.yeditepe.kernelnode.interfaces.dto.StatusDto;
import tr.edu.yeditepe.kernelnode.service.NodeService;

import java.util.List;

@RestController
@RequestMapping("/api/node")
@RequiredArgsConstructor
public class NodeController {

    private final ConsentChain consentChain;
    private final BlockChain blockChainChain;
    private final NodeService nodeService;
    private final SimpMessagingTemplate webSocket;

    @GetMapping
    public List<StatusDto> getBlockChainStatus() {
        return nodeService.status();
    }

    @PostMapping
    public void startMining() {
        webSocket.convertAndSend("/topic/minerPayload", ConsentBlockDto.builder()
                .data("KICKSTART")
                .build());
    }

    @PatchMapping
    public void setDiffuculty(@RequestParam Integer diffuculty) {
        blockChainChain.setDiffuculty(diffuculty);
    }

    @DeleteMapping
    public void cleanConcensus() {
        consentChain.emptyConsentChain();
    }

}

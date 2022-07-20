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
import org.springframework.stereotype.Service;
import tr.edu.yeditepe.kernelnode.domain.model.blockchain.BlockChain;
import tr.edu.yeditepe.kernelnode.domain.model.kernelblock.KernelBlock;
import tr.edu.yeditepe.kernelnode.interfaces.dto.StatusDto;
import tr.edu.yeditepe.kernelnode.service.BlockRecorder;
import tr.edu.yeditepe.kernelnode.service.NodeService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class NodeServiceImpl implements NodeService {

    private final BlockChain blockChain;
    private final BlockRecorder blockRecorder;


    @Override
    public List<StatusDto> status(){
        return blockChain.getKernelBlocks().stream().map(m -> StatusDto.builder()
                .hash(m.getHash())
                .previousHash(m.getPreviousHash())
                .data(m.getData())
                .timeStamp(m.getTimeStamp())
                .build()).collect(Collectors.toList());
    }

    public void saveBlockChain() throws IOException {
        for(KernelBlock block : blockChain.getKernelBlocks()){
            if(!blockRecorder.checkBlockExist(block)){
                blockRecorder.saveKernel(block);
            }
        }
    }
}

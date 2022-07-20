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
import tr.edu.yeditepe.kernelnode.domain.model.kernelblock.KernelBlock;
import tr.edu.yeditepe.kernelnode.service.BlockRecorder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlockRecorderImpl implements BlockRecorder {

    private final String FILE_LOCATION = "/blocks/block_";
    private final String TYPE = ".block";

    @Override
    public void saveKernel(KernelBlock block) throws IOException {
        Path blockPath = Paths.get(FILE_LOCATION+block.getOrder()+TYPE);
        Files.createFile(blockPath);
        byte[] strToBytes = block.toString().getBytes();
        Files.write(blockPath, strToBytes);
    }

    @Override
    public boolean checkBlockExist(KernelBlock block) {
        Path blockPath = Paths.get(FILE_LOCATION+block.getOrder()+TYPE);
        return Files.exists(blockPath);
    }
}

/*
 * Copyright (c) 2012-
 * Vodafone Teknoloji Hizmetleri A.S., Istanbul, Turkey
 *
 * All rights reserved. This Software or any portion of it can not be translated,
 * distributed, sold, adapted, arranged, used, copied, modified, de-compiled,
 * reverse assembled or otherwise reverse engineered, disassembled, replaced or made
 * additions to and to be reproduced in whole or in part, in any way, manner or form.
 */
package tr.edu.yeditepe.kernelnode.service;

import tr.edu.yeditepe.kernelnode.domain.model.kernelblock.KernelBlock;

import java.io.IOException;

public interface BlockRecorder {
    void saveKernel(KernelBlock block) throws IOException;

    boolean checkBlockExist(KernelBlock block);
}

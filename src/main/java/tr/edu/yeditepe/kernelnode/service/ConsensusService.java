package tr.edu.yeditepe.kernelnode.service;

import tr.edu.yeditepe.kernelnode.interfaces.dto.ConsentBlockDto;

public interface ConsensusService {
    ConsentBlockDto checkConsent(ConsentBlockDto consent);
}

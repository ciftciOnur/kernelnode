package tr.edu.yeditepe.kernelnode.service;

import tr.edu.yeditepe.kernelnode.interfaces.dto.StatusDto;

import java.util.List;

public interface NodeService {


    List<StatusDto> status();
}

package com.Numerify.inventory.service;

import com.Numerify.inventory.dto.ManagerDto;
import com.Numerify.inventory.model.Manager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Component
public interface ManagerService {

    @Transactional
    void saveManager(Manager manager);

    @Transactional
    List<Manager> listAllManagers();

    @Transactional
    void deleteAllManagers();

    @Transactional
    void deleteManagerById(int id);

    @Transactional
    Manager findById(int managerId);

    @Transactional
    List<ManagerDto> listAllDtos();

    @Transactional
    void updateManager(Manager manager);

    @Transactional
    ManagerDto updateManagerDto(ManagerDto managerDto);

    @Transactional
    Manager getManagerFromDto(ManagerDto managerDto);

    @Transactional
    ManagerDto getDtoFromManager(Manager manager);

    @Transactional
    ManagerDto getMinimalDto(Manager manager);

    @Transactional
    ManagerDto getManagerById(int id);

    @Transactional
    ManagerDto createManager(ManagerDto managerDto);

}

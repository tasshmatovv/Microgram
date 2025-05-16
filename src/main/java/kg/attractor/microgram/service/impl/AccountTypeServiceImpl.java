package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.exceptions.NotFoundException;
import kg.attractor.microgram.model.AccountTypeModel;
import kg.attractor.microgram.repository.AccountTypeRepository;
import kg.attractor.microgram.service.AccountTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService {

    private final AccountTypeRepository accountTypeRepository;

    @Override
    public AccountTypeModel getUserAccountType() {
        return accountTypeRepository.findByName("USER")
                .orElseThrow(() -> new NotFoundException("User type not found"));
    }

}
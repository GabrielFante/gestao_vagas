package br.com.gabrielfante.API.modules.company.useCases;

import br.com.gabrielfante.API.exceptions.UserFoundException;
import br.com.gabrielfante.API.modules.company.entities.CompanyEntity;
import br.com.gabrielfante.API.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.data.util.ClassUtils.ifPresent;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity){
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
        });

        return this.companyRepository.save(companyEntity);
    }
}
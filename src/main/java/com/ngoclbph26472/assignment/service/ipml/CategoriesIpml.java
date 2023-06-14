package com.ngoclbph26472.assignment.service.ipml;

import com.ngoclbph26472.assignment.dto.AccountsDTO;
import com.ngoclbph26472.assignment.dto.CategoriesDTO;
import com.ngoclbph26472.assignment.entity.Accounts;
import com.ngoclbph26472.assignment.entity.Categories;
import com.ngoclbph26472.assignment.repositories.CategoryRepository;
import com.ngoclbph26472.assignment.service.CategoriesService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesIpml implements CategoriesService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    CategoryRepository repo;

    @Override
    public List<CategoriesDTO> getAll() {
        List<Categories> categories = repo.findAll();
        TypeToken<List<CategoriesDTO>> typeToken = new TypeToken<>() {
        };
        List<CategoriesDTO> categoriesDTOList = mapper.map(categories, typeToken.getType());
        return categoriesDTOList;
    }
}

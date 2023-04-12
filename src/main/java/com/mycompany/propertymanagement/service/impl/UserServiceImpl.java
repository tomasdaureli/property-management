package com.mycompany.propertymanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.propertymanagement.converter.UserConverter;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BusinessException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;
    
    @Override
    public UserDTO register(UserDTO userDTO) {

        Optional<UserEntity> optUE = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
        if (optUE.isPresent()) {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("EMAIL_ALREADY_EXISTS");
            errorModel.setMessage("The email is already register.");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }
        UserEntity userEntity = userConverter.convertDTOtoEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        return userConverter.convertEntitytoDTO(userEntity);

    }

    @Override
    public UserDTO login(String email, String password) {
        
        UserDTO userDTO = null;
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmailAndPassword(email, password);
        if (optionalUserEntity.isPresent()) {
            userDTO = userConverter.convertEntitytoDTO(optionalUserEntity.get());
        } else {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage("Incorrect email or password");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }
        return userDTO;
        
    }
    


}

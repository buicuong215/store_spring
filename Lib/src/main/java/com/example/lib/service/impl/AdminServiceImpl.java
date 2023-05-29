package com.example.lib.service.impl;

import com.example.lib.dto.AdminDto;
import com.example.lib.models.Admin;
import com.example.lib.repository.AdminRepository;
import com.example.lib.repository.RoleRepository;
import com.example.lib.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Admin save(AdminDto adminDto) {
        Admin admin= modelMapper.map(adminDto,Admin.class);
        admin.setRoles(Collections.singletonList(roleRepository.findByName("ADMIN")));
        admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        return adminRepository.save(admin);
    }
}

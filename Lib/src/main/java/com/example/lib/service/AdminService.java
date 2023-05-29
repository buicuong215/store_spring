package com.example.lib.service;

import com.example.lib.dto.AdminDto;
import com.example.lib.models.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    Admin findByUsername(String username);
    Admin save(AdminDto adminDto);
}

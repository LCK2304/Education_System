package com.CovidHygiene.repository.user;

import com.CovidHygiene.entity.Admin;
import com.CovidHygiene.repository.IRepository;

import java.util.Set;

public interface AdminRepository extends IRepository<Admin, String> {

    Set<Admin> getAll();
}

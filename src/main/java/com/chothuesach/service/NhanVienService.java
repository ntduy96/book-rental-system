package com.chothuesach.service;

import com.chothuesach.dto.NhanVienDto;
import com.chothuesach.exception.EmailExistsException;
import com.chothuesach.exception.ResourceNotFoundException;
import com.chothuesach.model.NhanVien;
import com.chothuesach.model.Role;
import com.chothuesach.repository.NhanVienRepository;
import com.chothuesach.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private ChucVuService chucVuService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private NguoiDungService nguoiDungService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public List<NhanVien> getAllNhanVien() {
        return nhanVienRepository.getAllByOrderByHoTenNguoiDung();
    }

    public List<NhanVien> getAllNhanVienByChucVu(Short maChucVu) {
        return nhanVienRepository.getAllByChucVu(chucVuService.getByMaChucVu(maChucVu));
    }

    public NhanVien getByMaNhanVien(String maNhanVien) {
        Optional<NhanVien> result = nhanVienRepository.findById(maNhanVien);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ResourceNotFoundException("Can't find any Nhan vien matching maNhanVien: " + maNhanVien);
    }

    public NhanVien getByTenNguoiDung(String tenNguoiDung) {
        Optional<NhanVien> result = nhanVienRepository.getByTenNguoiDung(tenNguoiDung);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ResourceNotFoundException("Can't find any Nhan vien matching tenNhanVien: " + tenNguoiDung);
    }

    public NhanVien createNewNhanVien(NhanVienDto nhanVienDto) {
        if (nguoiDungService.emailExist(nhanVienDto.getEmail())) {
            throw new EmailExistsException(nhanVienDto.getEmail());
        } else {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setChucVu(chucVuService.getByMaChucVu(nhanVienDto.getMaChucVu()));
            nhanVien.setTenNguoiDung(nhanVienDto.getTenNguoiDung());
            nhanVien.setHoTenNguoiDung(nhanVienDto.getHoTenNguoiDung());
            nhanVien.setEmail(nhanVienDto.getEmail());
            nhanVien.setSoCmnd(nhanVienDto.getSoCmnd());
            nhanVien.setDiaChiNguoiDung(nhanVienDto.getDiaChiNguoiDung());
            nhanVien.setPassword(passwordEncoder.encode(nhanVienDto.getPassword()));
            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findOneByRoleName("ROLE_USER");// Assign ROLE_USER as default for new account
            Role staffRole = roleRepository.findOneByRoleName("ROLE_STAFF");// Assign ROLE_USER as default for new account
            roles.add(userRole);
            roles.add(staffRole);
            nhanVien.setRoles(roles);
            return nhanVienRepository.save(nhanVien);
        }
    }

    public void addRole(String maNhanVien, String roleName) {
        NhanVien nhanVien = getByMaNhanVien(maNhanVien);
        Set<Role> roles = (Set<Role>) nhanVien.getRoles();
        roles.add(roleRepository.findOneByRoleName(roleName));
        nhanVienRepository.save(nhanVien);
    }

    public void removeRole(String maNhanVien, String roleName) {
        NhanVien nhanVien = getByMaNhanVien(maNhanVien);
        Collection<Role> roles = nhanVien.getRoles();
        roles.remove(roleRepository.findOneByRoleName(roleName));
        nhanVienRepository.save(nhanVien);
    }

    public void deleteNhanVien(String maNhanVien) {
        removeRole(maNhanVien, "ROLE_STAFF");
        NhanVien nhanVien = getByMaNhanVien(maNhanVien);
        nhanVienRepository.delete(nhanVien);
    }

}

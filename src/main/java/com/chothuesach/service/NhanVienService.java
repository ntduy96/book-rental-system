package com.chothuesach.service;

import com.chothuesach.dto.NhanVienDto;
import com.chothuesach.exception.EmailExistsException;
import com.chothuesach.exception.UserNotFoundException;
import com.chothuesach.model.NhanVien;
import com.chothuesach.model.Role;
import com.chothuesach.repository.ChucVuRepository;
import com.chothuesach.repository.NhanVienRepository;
import com.chothuesach.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private ChucVuRepository chucVuRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private NguoiDungService nguoiDungService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public List<NhanVien> getAllNhanVien() {
        return nhanVienRepository.getAllByOrderByHoTenNguoiDung();
    }

    public List<NhanVien> getAllNhanVienByChucVu(Short maChucVu) {
        return nhanVienRepository.getAllByChucVu(chucVuRepository.findById(maChucVu).orElseThrow(RuntimeException::new));
    }

    public NhanVien getByMaNhanVien(String maNhanVien) {
        return nhanVienRepository.findById(maNhanVien).orElseThrow(UserNotFoundException::new);
    }

    public NhanVien getByTenNguoiDung(String tenNguoiDung) {
        return nhanVienRepository.getByTenNguoiDung(tenNguoiDung);
    }

    public NhanVien createNewNhanVien(NhanVienDto nhanVienDto) {
        if (nguoiDungService.emailExist(nhanVienDto.getEmail())) {
            throw new EmailExistsException(nhanVienDto.getEmail());
        } else {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setChucVu(chucVuRepository.findById(nhanVienDto.getMaChucVu()).get());
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
        NhanVien nhanVien = nhanVienRepository.findById(maNhanVien).get();
        Set<Role> roles = (Set<Role>) nhanVien.getRoles();
        roles.add(roleRepository.findOneByRoleName(roleName));
        nhanVienRepository.save(nhanVien);
    }

    public void removeRole(String maNhanVien, String roleName) {
        NhanVien nhanVien = nhanVienRepository.findById(maNhanVien).get();
        Collection<Role> roles = nhanVien.getRoles();
        roles.remove(roleRepository.findOneByRoleName(roleName));
        nhanVienRepository.save(nhanVien);
    }

    public void deleteNhanVien(String maNhanVien) {
        removeRole(maNhanVien, "ROLE_STAFF");
        nhanVienRepository.deleteNhanVienByMaNguoiDung(maNhanVien);
    }

}

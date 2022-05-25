package com.demo6.shop.service.impl;

import com.demo6.shop.common.ICommon;
import com.demo6.shop.convert.UserConvert;
import com.demo6.shop.dao.UserDao;
import com.demo6.shop.dto.RoleDTO;
import com.demo6.shop.dto.UserDTO;
import com.demo6.shop.dto.UserPrincipal;
import com.demo6.shop.entity.User;
import com.demo6.shop.service.OrderService;
import com.demo6.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserConvert userConvert;
    @Autowired
    private ICommon iCommon;
    @Autowired
    private OrderService orderService;

    @Override
    public void userUpdate(String email, long userId, String fullName, boolean gender, String phone, String address, long roleId, String password, String repassword, MultipartFile avatarFile, String avatar) {
        Optional<UserDTO> userDTO = Optional.ofNullable(findById(userId));
        if (!userDTO.isPresent()) {
            throw new UsernameNotFoundException("not found");
        }
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(roleId);
        String avatarFilename = avatarFile != null && avatarFile.getSize() > 0 ? iCommon.image(avatarFile) : avatar;
        UserDTO userUpdate = new UserDTO(email, userId, new BCryptPasswordEncoder().encode(repassword), fullName, phone, address, gender, roleDTO, avatarFilename,true);
        this.update(userUpdate);
    }

    @Override
    public void userCreate(String email, String fullName, boolean gender, String phone, String address, long roleId, String password, String repassword, MultipartFile avatarFile) {
        String image = null;
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(roleId);
        if (avatarFile != null && avatarFile.getSize() > 0) {
            image = iCommon.image(avatarFile);
        }
        UserDTO userDTO = new UserDTO(email, new BCryptPasswordEncoder().encode(repassword), fullName, phone, address, gender, true, roleDTO, image);
        this.insert(userDTO);

    }

    @Override
    public UserDTO insert(UserDTO userDTO) {
        User user = userConvert.toEntity(userDTO);
        userDao.insert(user);
        return userConvert.toDTO(user);
    }

    @Override
    public void update(UserDTO userDTO) {
        userDao.update(userConvert.toEntity(userDTO));
    }

    @Override
    public void delete(long userId) {
        orderService.deleteByUserId(userId);
        userDao.delete(userId);
    }

    @Override
    public UserDTO findById(long userId) {
        User user = userDao.findById(userId);
        return userConvert.toDTO(user);
    }

    @Override
    public List<UserDTO> findAll(int pageIndex, int PageSize) {
        List<User> users = userDao.findAll(pageIndex, PageSize);
        return users.stream().map(s -> userConvert.toDTO(s)).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByEmailOrPhoneAndPassword(String account, String password, boolean verity) {
        User user = userDao.findByEmailOrPhoneAndPassword(account, password, verity);
        return userConvert.toDTO(user);
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        System.out.println("0--------------");
        User user = userDao.loadUserByUsername(account);
        System.out.println("1---------------");
        if (user == null) {
            throw new UsernameNotFoundException("Not Found!");
        }

        List<SimpleGrantedAuthority> roleList = new ArrayList<>();
        user.getRole().getPermissions().forEach(s -> {
            roleList.add(new SimpleGrantedAuthority(s.getPermissionKey()));
        });
        roleList.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
        UserPrincipal userPrincipal = new UserPrincipal(user.getEmail(), user.getPassword(), roleList, user.getUserId(),
                user.getEmail(), user.getFullname(), user.getPhone(), user.getAddress(), user.isGender(), user.isVerify(), user.getRole(), user.getAvatar());
        return userPrincipal;
    }

    @Override
    public int count() {
        return userDao.count();
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = userDao.findByEmail(email);
        if (user != null) {
            return userConvert.toDTO(user);
        }
        return null;
    }

}

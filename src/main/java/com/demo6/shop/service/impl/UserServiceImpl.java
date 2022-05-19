package com.demo6.shop.service.impl;

import com.demo6.shop.common.ICommon;
import com.demo6.shop.convert.UserConvert;
import com.demo6.shop.dao.UserDao;
import com.demo6.shop.entity.Role;
import com.demo6.shop.entity.User;
import com.demo6.shop.model.RoleDTO;
import com.demo6.shop.model.UserDTO;
import com.demo6.shop.model.UserPrincipal;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

    @Override
    public void userUpdate(long userId, String fullName, boolean gender, String phone, String address, long roleId, String password, String repassword, MultipartFile avatarFile, String avatar) {
        String avatarFilename = iCommon.image(avatarFile);
        Optional<UserDTO> userDTO = Optional.ofNullable(findById(userId));
        if (userDTO.isPresent()) {
            throw new UsernameNotFoundException("not found");
        }

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(roleId);
/*
        userDTO.setFullname(fullName);
        userDTO.setGender(gender);
        userDTO.setPhone(phone);
        userDTO.setAddress(address);
        userDTO.setRoleDTO(roleDTO);*/
        // avatarFilename = iCommon.image(avatarFile);

        avatarFilename = avatarFile != null && avatarFile.getSize() > 0 ? avatarFilename : avatar;
      /*  if (avatarFile != null && avatarFile.getSize() > 0) {
           avatarFilename=  iCommon.image(avatarFile);
            userDTO.setAvatar(avatarFilename);
        } else {
            userDTO.setAvatar(avatar);
        }
*/
        // userDTO.setPassword(new BCryptPasswordEncoder().encode(repassword));
        UserDTO userUpdate = new UserDTO(userId, new BCryptPasswordEncoder().encode(repassword), fullName, phone, address, gender, roleDTO, avatarFilename);
        this.update(userUpdate);
    }

    @Override
    public void userCreate(String email, String fullName, boolean gender, String phone, String address, long roleId, String password, String repassword, MultipartFile avatarFile) {
        String image = null;

/*
        UserDTO userDTO = new UserDTO();
*/
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(roleId);

     /*   userDTO.setEmail(email);
        userDTO.setFullname(fullName);
        userDTO.setGender(gender);
        userDTO.setPhone(phone);
        userDTO.setAddress(address);
        userDTO.setRoleDTO(roleDTO);
        userDTO.setVerify(true);*/
        if (avatarFile != null && avatarFile.getSize() > 0) {
       /*     String originalFilename = avatarFile.getOriginalFilename();
            int lastIndex = originalFilename.lastIndexOf(".");
            String ext = originalFilename.substring(lastIndex);
            String avatarFilename = System.currentTimeMillis() + ext;
            File newfile = new File("C:\\image_spring_boot\\" + avatarFilename);
            FileOutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(newfile);
                fileOutputStream.write(avatarFile.getBytes());
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            // iCommon.image(avatarFile);
            image = iCommon.image(avatarFile);
            //  userDTO.setAvatar(  iCommon.image(avatarFile));
        }
        //  userDTO.setPassword(new BCryptPasswordEncoder().encode(repassword));
        UserDTO userDTO = new UserDTO(email, new BCryptPasswordEncoder().encode(repassword), fullName, phone, address, gender, true, roleDTO, image);
        this.insert(userDTO);

    }

    @Override
    public UserDTO insert(UserDTO userDTO) {
      /*  Role role = new Role();
        role.setRoleId(userDTO.getRoleDTO().getRoleId());

        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setAvatar(userDTO.getAvatar());
        user.setFullname(userDTO.getFullname());
        user.setVerify(userDTO.isVerify());
        user.setGender(userDTO.isGender());
        user.setPassword(userDTO.getPassword());
        user.setRole(role);*/
        User user = userConvert.toEntity(userDTO);
        userDao.insert(user);
       /* userDao.insert(user);

        return userConvert.toDTO(user);*/
        return userConvert.toDTO(user);
    }

    @Override
    public void update(UserDTO userDTO) {
     /*   Role role = new Role();
        role.setRoleId(userDTO.getRoleDTO().getRoleId());

        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setAvatar(userDTO.getAvatar());
        user.setFullname(userDTO.getFullname());
        user.setVerify(userDTO.isVerify());
        user.setGender(userDTO.isGender());
        user.setPassword(userDTO.getPassword());
        user.setRole(role);*/
        userDao.update(userConvert.toEntity(userDTO));
        //   userDao.update(user);
    }

    @Override
    public void delete(long userId) {
        userDao.delete(userId);
    }

    @Override
    public UserDTO findById(long userId) {
        User user = userDao.findById(userId);
    /*    RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(user.getRole().getRoleId());
        roleDTO.setRoleName(user.getRole().getRoleName());*/
        UserDTO userDTO = userConvert.toDTO(user);
        //    userDTO.setRoleDTO(roleDTO);
        return userDTO;
    }

    @Override
    public List<UserDTO> findAll(int pageIndex, int PageSize) {
        List<User> users = userDao.findAll(pageIndex, PageSize);

        List<UserDTO> userDTOs = users.stream().map(s -> userConvert.toDTO(s)).collect(Collectors.toList());
        /*    for (User user : users) {
         *//*    RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRoleId(user.getRole().getRoleId());
            roleDTO.setRoleName(user.getRole().getRoleName());*//*

            UserDTO userDTO = userConvert.toDTO(user);
            // userDTO.setRoleDTO(roleDTO);

            userDTOs.add(userDTO);
        }*/
        return userDTOs;
    }

    @Override
    public UserDTO findByEmailOrPhoneAndPassword(String account, String password, boolean verity) {
        User user = userDao.findByEmailOrPhoneAndPassword(account, password, verity);
     /*   RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(user.getRole().getRoleId());
        roleDTO.setRoleName(user.getRole().getRoleName());*/
/*
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(user.getUserId());
		userDTO.setEmail(user.getEmail());
		userDTO.setPhone(user.getPhone());
		userDTO.setAddress(user.getAddress());
		userDTO.setAvatar(user.getAvatar());
		userDTO.setFullname(user.getFullname());
		userDTO.setVerify(user.isVerify());
		userDTO.setGender(user.isGender());
		userDTO.setPassword(user.getPassword());
		userDTO.setRoleDTO(roleDTO);*/
        UserDTO userDTO = userConvert.toDTO(user);
        //    userDTO.setRoleDTO(roleDTO);
        return userDTO;
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
            //  UserDTO userDTO = new UserDTO();
     /*       RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRoleId(user.getRole().getRoleId());
            roleDTO.setRoleName(user.getRole().getRoleName());*/

           /* userDTO.setUserId(user.getUserId());
            userDTO.setEmail(user.getEmail());
            userDTO.setPhone(user.getPhone());
            userDTO.setAddress(user.getAddress());
            userDTO.setAvatar(user.getAvatar());
            userDTO.setFullname(user.getFullname());
            userDTO.setVerify(user.isVerify());
            userDTO.setGender(user.isGender());
            userDTO.setPassword(user.getPassword());*/
            UserDTO userDTO = userConvert.toDTO(user);
            //        userDTO.setRoleDTO(roleDTO);
            return userDTO;
        }
        return null;
    }

}

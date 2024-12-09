package com.nana.personalblogsystem.service;

import com.nana.personalblogsystem.model.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */


public interface UserService {

    UserDTO getUserByUuid(String userUuid);
    UserDTO getUserByToken(HttpServletRequest request);
}

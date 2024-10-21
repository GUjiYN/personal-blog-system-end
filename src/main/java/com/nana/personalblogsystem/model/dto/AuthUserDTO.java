package com.nana.personalblogsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserDTO {
    private UserDTO user;
    private String token;
}


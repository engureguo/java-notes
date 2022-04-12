package com.engure.juc.threadlocal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class UserInfo {
    private String name;
    private String addr;
    private Character gender;
}
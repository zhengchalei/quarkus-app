package io.github.zhengchalei.module.system.domain;

public enum UserStatus {

    ACTIVE,

    // 表示用户账号由于某种原因被锁定，可能是由于密码错误次数过多、安全原因或者其他系统策略导致的锁定状态。
    LOCKED,

    // 表示用户账号处于停用状态，通常是因为用户暂时不需要登录系统或者账号被管理员停用。
    DISABLED;
}

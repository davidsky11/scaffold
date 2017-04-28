package com.hrp.entity.system;

import java.io.Serializable;
import java.util.List;

/**
 * Role
 * 类名称： 角色
 * @author KVLT
 * @date 2017-03-14.
 */
public class Role implements Serializable {

    private Integer roleId;  // 角色ID
    private String roleName;  // 角色名称
    private String cnName;  // 中文名
    private String supId;  // 父角色ID
    private String remark;  // 备注
    private boolean isSys;  // 是否系统管理管理角色

    private Role supRole;  // 父角色
    private List<Role> subRoles;  // 子角色集合

    private List<Button> subBtns;  // 角色对应的按钮
    private List<Menu> subMenus;  // 角色对应的菜单

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isSys() {
        return isSys;
    }

    public void setSys(boolean sys) {
        isSys = sys;
    }

    public Role getSupRole() {
        return supRole;
    }

    public void setSupRole(Role supRole) {
        this.supRole = supRole;
    }

    public List<Role> getSubRoles() {
        return subRoles;
    }

    public void setSubRoles(List<Role> subRoles) {
        this.subRoles = subRoles;
    }

    public List<Button> getSubBtns() {
        return subBtns;
    }

    public void setSubBtns(List<Button> subBtns) {
        this.subBtns = subBtns;
    }

    public List<Menu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<Menu> subMenus) {
        this.subMenus = subMenus;
    }
}

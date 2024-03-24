package com.lantu.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author laocai
 * @since 2024-03-02
 */
@TableName("device")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "did")
    private Integer did;

    private String dname;

    private Integer status;

    private Integer lid;

    private Integer deleted;

}

package com.lantu.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author laocai
 * @since 2024-03-03
 */
@TableName("x_lab")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lab implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "lid")
    private Integer lid;

    private String lname;

    private Integer status;

    private Integer deleted;
}

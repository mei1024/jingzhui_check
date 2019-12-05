package com.solar.uc.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 小程序会员信息DTO对象
 * 
 * @author codegen
 * 
 * @version 20181030
 * 
 */
@Getter 
@Setter
@ApiModel(value = "小程序会员信息")
public class MemberQueryDto {

	@ApiModelProperty(name = "id", value = "用户ID", required = true)
    private String id;
	
	@ApiModelProperty(name = "nickName", value = "微信昵称", required = false)
    private String nickName;
	
	@ApiModelProperty(name = "avatarUrl", value = "微信头像地址", required = false)
    private String avatarUrl;
	
	@ApiModelProperty(name = "sex", value = "性别  男  女", required = false)
	private String sex;
	
	@ApiModelProperty(name = "profession", value = "职业", required = false)
	private String profession;
	
	@ApiModelProperty(name = "age", value = "年龄", required = false)
	private String age;
	
	@ApiModelProperty(name = "height", value = "身高", required = false)
	private String height;
	
	@ApiModelProperty(name = "weight", value = "体重", required = false)
	private String weight;
	
}
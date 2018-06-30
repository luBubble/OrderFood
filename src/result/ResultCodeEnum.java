package result;

import org.apache.commons.lang.StringUtils;

public enum ResultCodeEnum
{
	SUCCESS("1000", "成功"),
	NOT_LOGIN("1001", "未登录"),
	ERROR("1002", "服务器异常"),
	DATA_EMPTY("1003","没有相关数据"),
	OLDPASSWORD_WRONG("3001", "原密码不符"),
	ACCOUNT_NOT_EXISTS("2003", "账号不存在"),
	PASSWORD_WRONG("2004", "密码错误"),
	
	PHONE_EXISTS("2001", "手机号重复"),
	EMAIL_EXISTS("2002", "邮箱重复"),
	
		
	ANSWER_WRONG("3001", "答案不符"),	
	PARA_INCOMPLETE("4002","参数不全"),
	PARA_ERROR("4003","参数错误"),
	INFOR_NOTEXIST("4004","信息不存在"),
	DATASTYLE_ERROR("4005","数据格式错误");

	private String code;
    private String msg;

    ResultCodeEnum(String code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public String getCode()
    {
        return code;
    }

    public String getMsg()
    {
        return msg;
    }
    public static ResultCodeEnum getResultCodeEnumByCode(String code)
    {
        for(ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()){
          if(StringUtils.equals(code, resultCodeEnum.getCode())){
            return resultCodeEnum;
          }
        }
        return null;
      }
}

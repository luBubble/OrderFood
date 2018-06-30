package result;

import net.sf.json.JSON;

public class DataResponse
{
	private Object data;
	private String code;
	private String msg;

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public void setResult(ResultCodeEnum rce)
	{
		this.code = rce.getCode();
		this.msg = rce.getMsg();
	}
	
	public ResultCodeEnum getResult()
	{
		ResultCodeEnum rce = ResultCodeEnum.getResultCodeEnumByCode(this.getCode());
		return rce;
	}
	


}
